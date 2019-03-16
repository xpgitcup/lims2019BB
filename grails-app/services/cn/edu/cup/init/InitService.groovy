package cn.edu.cup.init

import cn.edu.cup.system.SystemAttribute
import cn.edu.cup.system.SystemCarousel
import cn.edu.cup.system.SystemMenu
import cn.edu.cup.system.SystemSponser
import cn.edu.cup.system.SystemTitle
import cn.edu.cup.system.SystemUser
import com.alibaba.fastjson.JSON
import grails.gorm.transactions.Transactional

import javax.servlet.ServletContext

@Transactional
class InitService {

    def grailsApplication
    def dataSource
    def systemMenuService
    def commonService

    /**
     * 初始化代码__开发环境下的初始化代码
     */
    def configureForDevelopment(ServletContext servletContext) {
        println "这是开发环境..."
        println(grailsApplication.metadata.getApplicationName())
        processConfigFile(servletContext)
        // 初始化底层管理--控制器列表
        def domains = grailsApplication.controllerClasses
        initSystemMenuItems(domains)
        //属性数据
        fillBasicSystemAttributes()
        //程序标题
        fillSampleTitle()
        // 初始化用户--
        initSystemUsers()
    }

    def fillSampleTitle() {
        println("初始化系统标题......")
        if (SystemTitle.count() < 1) {
            def systemTitle = new SystemTitle(
                    applicationTitle: "Lims 2018 实验室信息管理系统",
                    applicationLogo: "cuplogoA.png",
                    applicationLayout: "mainEasyUI"
            )
            systemTitle.save(true)
            //----------------------------------------------------------------------------------------------------------
            if (SystemSponser.countBySystemTitle(systemTitle) < 1) {
                newSponser(systemTitle, "中国石油大学", "cuplogoA.png")
                //newSponser(systemTitle, "中海油", "logo_cnooc.png")
                //newSponser(systemTitle, "中联煤", "logo_cbm.png")
            }
            //----------------------------------------------------------------------------------------------------------
            if (SystemCarousel.countBySystemTitle(systemTitle) < 1) {
                newCarousel(systemTitle, "课题组", "课题组.jpg")
                newCarousel(systemTitle, "多相流", "多相流.png")
                newCarousel(systemTitle, "抽油机", "u68.jpg")
            }
        }
    }

    private void newSponser(systemTitle, name, logo) {
        def ns = new SystemSponser(systemTitle: systemTitle, name: name, logo: logo)
        ns.save(true)
    }

    private void newCarousel(systemTitle, name, png) {
        def nc = new SystemCarousel(systemTitle: systemTitle, name: name, imageName: png)
        nc.save(true)
    }


    /*
    * 初始化系统菜单
    * 定义一个文件格式--jsong格式
    * 先输出一个输出的，然后定义导入的
    * */

    def initSystemMenuItems(domains) {
        // 首先从配置文件中导入
        def fileName = commonService.menuConfigFileName()
        //--------------------------------------------------------------------------------------------------------------
        if (SystemMenu.count() < 1) {
            importFromJsonFile(fileName)
            setupDomainMenuItems(domains)
        }
    }

    private SystemMenu setupDomainMenuItems(domains) {
        def m0 = new SystemMenu(
                menuContext: "底层管理",
                menuAction: "#",
                menuDescription: "对系统的菜单结构进行底层维护",
                upMenuItem: null,
                roleAttribute: "底层管理",
                menuOrder: 100
        )
        //m0.save(true)
        systemMenuService.save(m0)
        //----------------------------------------------------------------------------------------------------------
        //创建正对各个域类控制器的菜单
        domains.sort { a, b ->
            return a.name.compareTo(b.name)
        }
        println("${domains}")
        domains.eachWithIndex { e, i ->
            def m01 = new SystemMenu(
                    menuContext: "${e.name}",
                    menuAction: "${e.name}/index",
                    menuDescription: "对${e.name}属性进行维护",
                    upMenuItem: m0,
                    roleAttribute: "底层管理",
                    menuOrder: i
            )
            //m01.save(true)
            systemMenuService.save(m01)
        }
        m0
    }

    /*
    * 从json文件中导入菜单
    * */

    def importFromJsonFile(fileName) {
        def jsonFile = new File(fileName)
        if (jsonFile.exists()) {
            def json = jsonFile.text
            def menus = commonService.importFromJson4Tree(json, SystemMenu.class, "menuItems")
            println("导入：${menus}")
            menus.each { e ->
                systemMenuService.save(e)
            }
        }
    }

    /**
     * 初始化用户数据
     **/
    def initSystemUsers() {
        if (SystemUser.count() < 1) {
            def attribute = SystemAttribute.findByName("系统操作权限")
            newUser("李晓平", "3764", attribute)
            newUser("宫敬", "2156", attribute)
            newUser("吴海浩", "3181", attribute)
        }
    }

    private void newUser(userName, password, attribute) {
        def u5 = new SystemUser(userName: userName, password: password, roleAttribute: attribute)
        u5.save(true)
    }

    void fillBasicSystemAttributes() {
        if (SystemAttribute.count() < 1) {
            println("测试性的属性...")
            def attributeA = new SystemAttribute(name: "系统操作权限")
            attributeA.save(true)
            SystemMenu.findAllByUpMenuItemIsNull().each { e ->
                def aa = new SystemAttribute(name: e.menuContext, upAttribute: attributeA)
                aa.save(true)
            }
        }
    }

    /*
    * 处理ocnfig.json文件
    * */

    private void processConfigFile(ServletContext servletContext) {
        def webRootDir = servletContext.getRealPath("/")
        def configFileName = "${webRootDir}config.json"
        def configFile = new File(configFileName)
        def config = [:]
        if (configFile.exists()) {
            config = com.alibaba.fastjson.JSON.parse(configFile.text)
            if (config.scripts) {
                println("脚本文件：" + config.scripts)
                config.scripts.each { e ->
                    loadScripts("${webRootDir}${e}")
                }
            }
        } else {
            config.put("scripts", ["脚本1", "脚本2"])
            def printWriter = new PrintWriter(configFile, "utf-8")
            printWriter.write(JSON.toJSONString(config))
            printWriter.close()
            println("创建配置文件。")
        }
    }

    /**
     * 发布后的初始化代码
     */
    def configureForProduction(ServletContext servletContext) {
        println "这是发布环境..."
        processConfigFile(servletContext)
    }

    /*
    * 加载数据库初始化脚本
    * */

    def loadScripts(String dir) {
        File sf = new File(dir)
        if (sf.exists()) {
            println "load scripts ${dir}"
            if (sf.isDirectory()) {
                sf.eachFile { f ->
                    if (f.isFile()) {
                        executeScript(f)
                    }
                }
            } else {
                println("执行${sf}...")
                executeScript(sf)
            }
        }
    }

    /**
     * 执行附加脚本
     * */

    def executeScript(File sf) {
        //def File sf = new File(fileName)
        println "init - ${sf}"
        if (sf) {
            def db
            def sql = sf.text
            db = new groovy.sql.Sql(dataSource)
            //println "init - ${sql}"
            def lines = sql.split(";")
            lines.each() { it ->
                //println "line: ${it}"
                it = it.trim()
                if (!it.isEmpty()) {
                    db.executeUpdate(it)
                }
            }
        }
    }

}
