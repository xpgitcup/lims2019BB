package cn.edu.cup.os4lims

import cn.edu.cup.lims.Course
import cn.edu.cup.lims.Person
import cn.edu.cup.lims.Project
import cn.edu.cup.lims.ThingType
import cn.edu.cup.lims.ThingTypeController
import cn.edu.cup.system.JsFrame
import grails.converters.JSON

class Operation4ThingTypeController extends ThingTypeController {

    def treeViewService

    def createProject() {
        println("${params}")
        def aThingType = thingTypeService.get(params.id)
        def project = new Project(
                thingType: aThingType
        )

        def view = "createProject"
        if (request.xhr) {
            render(template: view, model: [project: project])
        } else {
            respond project
        }
    }

    def createCourse() {
        println("${params}")
        def aThingType = thingTypeService.get(params.id)
        def myself = Person.get(session.realId)
        Calendar calendar = Calendar.getInstance();
        def y = calendar.get(Calendar.YEAR);
        def m = calendar.get(Calendar.MONTH);
        def sy
        if (m > 6) {
            sy = "${y}-${y + 1}-1"
        } else {
            sy = "${y - 1}-${y}-2"
        }
        def course = new Course(
                thingType: aThingType,
                teacher: myself,
                name: "${aThingType.name}.${sy}",
                schoolYear: sy
        )

        def view = "createCourse"
        if (request.xhr) {
            render(template: view, model: [course: course])
        } else {
            respond course
        }
    }

    def show(Long id) {
        def thingType = thingTypeService.get(id)
        //println("show ${thingType}")
        def isProject = ThingType.findByName("科研项目").bePartOf(thingType)
        def isCourse = ThingType.findByName("教学任务").bePartOf(thingType)
        def view = "show"
        if (request.xhr) {
            render(template: view, model: [thingType: thingType, isProject: isProject, isCourse: isCourse])
        } else {
            respond thingType
        }
    }

    /*
    * 获取json格式的树形结构数据
    * */

    def getTreeViewData() {
        def data = ThingType.findAllByUpTypeIsNull(params);
        params.context = "name"
        params.subItems = "subTypes"
        params.attributes = "id"    //
        def result = treeViewService.generateNodesString(data, params, JsFrame.EasyUI)
        if (request.xhr) {
            render result as JSON
        } else {
            result
        }
    }

    def index() {}
}
