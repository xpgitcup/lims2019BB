package cn.edu.cup.lims

import grails.converters.JSON
import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class ThingTypeCircleController {

    ThingTypeCircleService thingTypeCircleService
    def commonQueryService
    def commonService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond thingTypeCircleService.list(params), model:[thingTypeCircleCount: thingTypeCircleService.count()]
    }

    def show(Long id) {
        def view = "show"
        if (params.view) {
            view = params.view
        }

        def thingTypeCircle =thingTypeCircleService.get(id)

        if (request.xhr) {
            render(template: view, model: [thingTypeCircle: thingTypeCircle])
        } else {
            respond thingTypeCircle
        }
    }

    def create() {
        def view = "create"
        if (params.view) {
            view = params.view
        }

        def thingTypeCircle = new ThingTypeCircle(params)

        if (request.xhr) {
            render(template: view, model: [thingTypeCircle: thingTypeCircle])
        } else {
            respond thingTypeCircle
        }
    }

    def save(ThingTypeCircle thingTypeCircle) {

        if (thingTypeCircle == null) {
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
            thingTypeCircleService.save(thingTypeCircle)
            flash.message = message(code: 'default.created.message', args: [message(code: 'thingTypeCircle.label', default: 'ThingTypeCircle'), thingTypeCircle.id])
        } catch (ValidationException e) {
            flash.message = thingTypeCircle.errors
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

        def thingTypeCircle = thingTypeCircleService.get(id)

        if (request.xhr) {
            render(template: view, model: [thingTypeCircle: thingTypeCircle])
        } else {
            respond thingTypeCircle
        }
    }

    def update(ThingTypeCircle thingTypeCircle) {
        if (thingTypeCircle == null) {
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
            thingTypeCircleService.save(thingTypeCircle)
            flash.message = message(code: 'default.updated.message', args: [message(code: 'thingTypeCircle.label', default: 'ThingTypeCircle'), thingTypeCircle.id])
        } catch (ValidationException e) {
            flash.message = thingTypeCircle.errors
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

        thingTypeCircleService.delete(id)
        flash.message = message(code: 'default.deleted.message', args: [message(code: 'thingTypeCircle.label', default: 'ThingTypeCircle'), id])

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
        def view = result.view
        flash.message = result.message
        processResult(result)
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

    protected void processResult(result) {}

    def importFromJsonFile(String fileName) {
        // 先清空
        ThingTypeCircle.list().each { e ->
            thingTypeCircleService.delete(e.id)
        }

        def jsonFile = new File(fileName)
        if (jsonFile.exists()) {
            def json = jsonFile.text
            def querys = commonService.importFromJson(json, ThingTypeCircle.class)
            querys.each { e ->
                thingTypeCircleService.save(e)
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

    def exportToJsonFile(fileName) {
        def fjson = commonService.exportObjects2JsonString(ThingTypeCircle.list())
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
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'thingTypeCircle.label', default: 'ThingTypeCircle'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
