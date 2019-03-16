package cn.edu.cup.os

import cn.edu.cup.lims.Person
import cn.edu.cup.lims.PersonTitle
import cn.edu.cup.system.SystemAttribute
import cn.edu.cup.system.SystemLog
import cn.edu.cup.system.SystemMenu
import cn.edu.cup.system.SystemUser
import grails.gorm.transactions.Transactional

@Transactional
class SystemCommonService {

    def systemUserService
    def grailsApplication

    def getRealName(SystemUser systemUser) {
        def user = Person.findByCode(systemUser.userName)
        return user
    }

    boolean removePersonFromUser(person) {
        def u = SystemUser.findByUserName(person.code)
        if (u) {
            systemUserService.delete(u.id)
            return true
        } else {
            return false
        }
    }

    boolean addPersonToUser(person) {
        def role = SystemAttribute.findByName("系统操作权限")
        /*
        def teacherTitle = PersonTitle.findByName("教师")
        def studentTitle = PersonTitle.findByName("学生")
        if (person.bePartOf(teacherTitle)) {
            role = SystemAttribute.findByName("教师权限")
        }
        if (person.bePartOf(studentTitle)) {
            role = SystemAttribute.findByName("学生权限")
        }
        */
        switch (person.class.simpleName) {
            case "Teacher":
                role = SystemAttribute.findByName("教师权限")
                break
            case "Student":
                role = SystemAttribute.findByName("学生权限")
                break
        }
        if (SystemUser.countByUserName(person.code) < 1) {
            def app = person.class.simpleName
            def u = new SystemUser(
                    userName: person.code,
                    password: "12345678",
                    roleAttribute: role,
                    appendAttribute: "${app}=${person.name}"
            )
            systemUserService.save(u)
            return true
        } else {
            return false
        }
    }

    @Transactional(readOnly = false)
    def deleteBefore(aDate) {
        SystemLog.executeUpdate("delete SystemLog a where a.actionDate < :d", [d: aDate])
    }

    @Transactional(readOnly = false)
    def recordLog(session, request, params) {
        def ps = params
        if (ps.password) {
            ps.password = "********"
        }
        SystemLog log = new SystemLog();
        log.sessionId = session.getId();
        log.actionDate = new Date();
        log.userName = session.systemUser.userName;
        log.hostIP = request.getRemoteAddr();
        def pps = "${ps}"
        if (pps.length() > 100) {
            log.doing = pps.substring(0, 100)
        } else {
            log.doing = pps
        }
        log.save();
    }

    def updateSystemUserList(request) {
        def pscontext = request.session.servletContext
        Map serviceMap = pscontext.getAttribute("systemUserList")
        if (serviceMap) {
            //当前在线人员列表
            def m = Math.min(5, serviceMap.size())
            def users = ""
            for (int i = 0; i < m; i++) {
                if (i != 0) {
                    users += ", "
                }
                users += serviceMap.keySet()[i]
            }
            if (serviceMap.size() > 5) {
                users += "..."
            }
            request.session.systemUserList = users
            //统计人数
            request.session.onlineCount = serviceMap.size()
            //println("${users}")
        }
    }

    //获取顶级菜单---?????
    def getAllTopLevelMenus(params) {
        def q = SystemMenu.createCriteria()
        def systemMenuList = []
        if (params.user) {
            SystemUser user = params.user
            def roles = user.userRoles()
            println("${roles}")
            if (roles) {
                systemMenuList = q.list(params) {
                    isNull('upMenuItem')
                    'in'('menuContext', roles)      // 只要菜单的名字在其中就可以 20181208
                    order('menuOrder')
                }
            }
        }
        return systemMenuList
    }

}
