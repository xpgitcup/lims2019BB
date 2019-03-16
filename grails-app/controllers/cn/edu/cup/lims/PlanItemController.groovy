package cn.edu.cup.lims

import grails.converters.JSON
import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class PlanItemController {

    PlanItemService planItemService
    def commonQueryService
    def commonService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond planItemService.list(params), model:[planItemCount: planItemService.count()]
    }

    def show(Long id) {
        def view = "show"
        if (params.view) {
            view = params.view
        }

        def planItem =planItemService.get(id)

        if (request.xhr) {
            render(template: view, model: [planItem: planItem])
        } else {
            respond planItem
        }
    }

    def create() {
        def view = "create"
        if (params.view) {
            view = params.view
        }

        def planItem = new PlanItem(params)

        if (request.xhr) {
            render(template: view, model: [planItem: planItem])
        } else {
            respond planItem
        }
    }

    def save(PlanItem planItem) {

        if (planItem == null) {
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
            planItemService.save(planItem)
            flash.message = message(code: 'default.created.message', args: [message(code: 'planItem.label', default: 'PlanItem'), planItem.id])
        } catch (ValidationException e) {
            flash.message = planItem.errors
        }

        if (controller == "")
        {
            redirect(action: action)
        } else {
            redirect(controller: controller, action: action)
        }
    }

    def edit(Long id) {
        def view = "edit"
        if (params.view) {
            view = params.view
        }

        def planItem = planItemService.get(id)

        if (request.xhr) {
            render(template: view, model: [planItem: planItem])
        } else {
            respond planItem
        }
    }

    def update(PlanItem planItem) {
        if (planItem == null) {
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
            planItemService.save(planItem)
            flash.message = message(code: 'default.updated.message', args: [message(code: 'planItem.label', default: 'PlanItem'), planItem.id])
        } catch (ValidationException e) {
            flash.message = planItem.errors
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

        planItemService.delete(id)
        flash.message = message(code: 'default.deleted.message', args: [message(code: 'planItem.label', default: 'PlanItem'), id])

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
        PlanItem.list().each { e ->
            planItemService.delete(e.id)
        }

        def jsonFile = new File(fileName)
        if (jsonFile.exists()) {
            def json = jsonFile.text
            def querys = commonService.importFromJson(json, PlanItem.class)
            querys.each { e ->
                planItemService.save(e)
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

       def fjson = commonService.exportObjects2JsonString(PlanItem.list())
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
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'planItem.label', default: 'PlanItem'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
