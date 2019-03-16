package cn.edu.cup.system

import grails.converters.JSON
import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class QueryStatementController {

    QueryStatementService queryStatementService
    def commonQueryService
    def commonService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond queryStatementService.list(params), model:[queryStatementCount: queryStatementService.count()]
    }

    def show(Long id) {
        def view = "show"
        if (params.view) {
            view = params.view
        }

        def queryStatement =queryStatementService.get(id)

        if (request.xhr) {
            render(template: view, model: [queryStatement: queryStatement])
        } else {
            respond queryStatement
        }
    }

    def create() {
        def view = "create"
        if (params.view) {
            view = params.view
        }

        def queryStatement = new QueryStatement(params)

        if (request.xhr) {
            render(template: view, model: [queryStatement: queryStatement])
        } else {
            respond queryStatement
        }
    }

    def save(QueryStatement queryStatement) {

        if (queryStatement == null) {
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
            queryStatementService.save(queryStatement)
            flash.message = message(code: 'default.created.message', args: [message(code: 'queryStatement.label', default: 'QueryStatement'), queryStatement.id])
        } catch (ValidationException e) {
            flash.message = queryStatement.errors
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

        def queryStatement = queryStatementService.get(id)

        if (request.xhr) {
            render(template: view, model: [queryStatement: queryStatement])
        } else {
            respond queryStatement
        }
    }

    def update(QueryStatement queryStatement) {
        if (queryStatement == null) {
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
            queryStatementService.save(queryStatement)
            flash.message = message(code: 'default.updated.message', args: [message(code: 'queryStatement.label', default: 'QueryStatement'), queryStatement.id])
        } catch (ValidationException e) {
            flash.message = queryStatement.errors
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

        queryStatementService.delete(id)
        flash.message = message(code: 'default.deleted.message', args: [message(code: 'queryStatement.label', default: 'QueryStatement'), id])

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
        println("${params}")
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

    def importFromJsonFile() {

        def fileName = "${commonService.webRootPath}/${params.fileName}"

        // 先清空
        QueryStatement.list().each { e ->
            queryStatementService.delete(e.id)
        }

        def jsonFile = new File(fileName)
        if (jsonFile.exists()) {
            def json = jsonFile.text
            def querys = commonService.importFromJson(json, QueryStatement.class)
            querys.each { e ->
                queryStatementService.save(e)
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

       def fjson = commonService.exportObjects2JsonString(QueryStatement.list())
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
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'queryStatement.label', default: 'QueryStatement'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
