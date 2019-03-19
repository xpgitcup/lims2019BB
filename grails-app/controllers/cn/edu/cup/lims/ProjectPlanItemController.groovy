package cn.edu.cup.lims

import grails.converters.JSON
import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class ProjectPlanItemController {

    ProjectPlanItemService projectPlanItemService
    def commonQueryService
    def commonService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        def model = [:]
        def userResult = false
        params.max = Math.min(max ?: 10, 100)
        if (params.title) {
            model.projectPlanItemTitle = params.title
            userResult = true
        }
        if (params.jsRoutine) {
            model.projectPlanItemJsRoutine = params.jsRoutine
            userResult = true
        }

        if (userResult) {
            model
        } else {
            respond projectPlanItemService.list(params), model:[projectPlanItemCount: projectPlanItemService.count()]
        }
    }

    def show(Long id) {
        def view = "show"
        if (params.view) {
            view = params.view
        }

        def projectPlanItem = projectPlanItemService.get(id)

        if (request.xhr) {
            render(template: view, model: [projectPlanItem: projectPlanItem])
        } else {
            respond projectPlanItem
        }
    }

    def create() {
        def view = "create"
        if (params.view) {
            view = params.view
        }

        def projectPlanItem = new ProjectPlanItem(params)

        if (request.xhr) {
            render(template: view, model: [projectPlanItem: projectPlanItem])
        } else {
            respond projectPlanItem
        }
    }

    def save(ProjectPlanItem projectPlanItem) {

        if (projectPlanItem == null) {
            notFound()
            return
        }

        def action = "index"
        if (params.nextAction) {
            action = params.nextAction
        }

        def controller = params.controller
        if (params.nextController) {
            controller = params.nextController
        }

        try {
            projectPlanItemService.save(projectPlanItem)
            flash.message = message(code: 'default.created.message', args: [message(code: 'projectPlanItem.label', default: 'ProjectPlanItem'), projectPlanItem.id])
        } catch (ValidationException e) {
            flash.message = projectPlanItem.errors
        }

        if (params.url) {
            redirect(params.url)
        } else {
            redirect(controller: controller, action: action)
        }
    }

    def edit(Long id) {
        def view = "edit"
        if (params.view) {
            view = params.view
        }

        def projectPlanItem = projectPlanItemService.get(id)

        if (request.xhr) {
            render(template: view, model: [projectPlanItem: projectPlanItem])
        } else {
            respond projectPlanItem
        }
    }

    def update(ProjectPlanItem projectPlanItem) {
        if (projectPlanItem == null) {
            notFound()
            return
        }

        def action = "index"
        if (params.nextAction) {
            action = params.nextAction
        }

        def controller = ""
        if (params.nextController) {
            controller = params.nextController
        }

        try {
            projectPlanItemService.save(projectPlanItem)
            flash.message = message(code: 'default.updated.message', args: [message(code: 'projectPlanItem.label', default: 'ProjectPlanItem'), projectPlanItem.id])
        } catch (ValidationException e) {
            flash.message = projectPlanItem.errors
        }

        if (controller == "")
        {
            redirect(action: action)
        } else {
            redirect(controller: controller, action: action)
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        projectPlanItemService.delete(id)
        flash.message = message(code: 'default.deleted.message', args: [message(code: 'projectPlanItem.label', default: 'ProjectPlanItem'), id])

        def action = "index"
        if (params.nextAction) {
            action = params.nextAction
        }

        def controller = ""
        if (params.nextController) {
            controller = params.nextController
        }

        if (controller == "")
        {
            redirect(action: action)
        } else {
            redirect(controller: controller, action: action)
        }
    }

    def list() {
        prepareParams()
        def result = commonQueryService.listFunction(params)
        result = processResult(result, params)
        def view = result.view
        flash.message = result.message
        if (request.xhr) {
            render(template: view, model: [objectList: result.objectList, flash: flash])
        } else {
            respond result.objectList
        }
    }

    def count() {
        prepareParams()
        def count = commonQueryService.countFunction(params)
        def result = [count: count]

        if (request.xhr) {
            render result as JSON
        } else {
            result
        }
    }

    protected void prepareParams() {}

    protected def processResult(result, params) {
        return result
    }

    def importFromJsonFile() {

        def fileName = "${commonService.webRootPath}/${params.fileName}"

        // 先清空
        ProjectPlanItem.list().each { e ->
            projectPlanItemService.delete(e.id)
        }

        def jsonFile = new File(fileName)
        if (jsonFile.exists()) {
            def json = jsonFile.text
            def querys = commonService.importFromJson(json, ProjectPlanItem.class)
            querys.each { e ->
                projectPlanItemService.save(e)
            }
        }

        def action = "index"
        if (params.nextAction) {
           action = params.nextAction
         }

        def controller = ""
        if (params.nextController) {
            controller = params.nextController
        }

        if (controller == "")
        {
            redirect(action: action)
        } else {
            redirect(controller: controller, action: action)
        }
    }

    def exportToJsonFile() {

        def fileName = "${commonService.webRootPath}/${params.fileName}"

       def fjson = commonService.exportObjects2JsonString(ProjectPlanItem.list())
        def printer = new File(fileName).newPrintWriter('utf-8')    //写入文件
        printer.println(fjson)
        printer.close()

        def action = "index"
        if (params.nextAction) {
            action = params.nextAction
        }

        def controller = ""
        if (params.nextController) {
            controller = params.nextController
        }

        if (controller == "")
        {
            redirect(action: action)
        } else {
            redirect(controller: controller, action: action)
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'projectPlanItem.label', default: 'ProjectPlanItem'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
