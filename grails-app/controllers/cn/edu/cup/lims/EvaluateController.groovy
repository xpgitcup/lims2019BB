package cn.edu.cup.lims

import grails.converters.JSON
import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class EvaluateController {

    EvaluateService evaluateService
    def commonQueryService
    def commonService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond evaluateService.list(params), model:[evaluateCount: evaluateService.count()]
    }

    def show(Long id) {
        def view = "show"
        if (params.view) {
            view = params.view
        }

        def evaluate =evaluateService.get(id)

        if (request.xhr) {
            render(template: view, model: [evaluate: evaluate])
        } else {
            respond evaluate
        }
    }

    def create() {
        def view = "create"
        if (params.view) {
            view = params.view
        }

        def evaluate = new Evaluate(params)

        if (request.xhr) {
            render(template: view, model: [evaluate: evaluate])
        } else {
            respond evaluate
        }
    }

    def save(Evaluate evaluate) {

        if (evaluate == null) {
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
            evaluateService.save(evaluate)
            flash.message = message(code: 'default.created.message', args: [message(code: 'evaluate.label', default: 'Evaluate'), evaluate.id])
        } catch (ValidationException e) {
            flash.message = evaluate.errors
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

        def evaluate = evaluateService.get(id)

        if (request.xhr) {
            render(template: view, model: [evaluate: evaluate])
        } else {
            respond evaluate
        }
    }

    def update(Evaluate evaluate) {
        if (evaluate == null) {
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
            evaluateService.save(evaluate)
            flash.message = message(code: 'default.updated.message', args: [message(code: 'evaluate.label', default: 'Evaluate'), evaluate.id])
        } catch (ValidationException e) {
            flash.message = evaluate.errors
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

        evaluateService.delete(id)
        flash.message = message(code: 'default.deleted.message', args: [message(code: 'evaluate.label', default: 'Evaluate'), id])

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
        Evaluate.list().each { e ->
            evaluateService.delete(e.id)
        }

        def jsonFile = new File(fileName)
        if (jsonFile.exists()) {
            def json = jsonFile.text
            def querys = commonService.importFromJson(json, Evaluate.class)
            querys.each { e ->
                evaluateService.save(e)
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
        def fjson = commonService.exportObjects2JsonString(Evaluate.list())
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
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'evaluate.label', default: 'Evaluate'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
