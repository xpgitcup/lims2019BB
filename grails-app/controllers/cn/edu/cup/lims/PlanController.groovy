package cn.edu.cup.lims

import grails.converters.JSON
import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class PlanController {

    PlanService planService
    def thingTypeService
    def thingService
    def commonQueryService
    def commonService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        def model = [:]
        def userResult = false
        params.max = Math.min(max ?: 10, 100)
        if (params.title) {
            model.planTitle = params.title
            userResult = true
        }
        if (params.jsRoutine) {
            model.planJsRoutine = params.jsRoutine
            userResult = true
        }

        if (userResult) {
            model
        } else {
            respond planService.list(params), model:[planCount: planService.count()]
        }
    }

    def show(Long id) {
        def view = "show"
        if (params.view) {
            view = params.view
        }

        def plan = planService.get(id)

        if (request.xhr) {
            render(template: view, model: [plan: plan])
        } else {
            respond plan
        }
    }

    def create() {

        println("${params}")

        def view = "create"
        if (params.view) {
            view = params.view
        }

        if (params.thingOrTypeId) {
            def aname
            if (params.isTypePlan) {
                def thingType = thingTypeService.get(params.thingOrTypeId)
                if (thingType) {
                    params.thingOrTypeName = thingType.name
                }
            } else {
                def thing = thingService.get(params.thingOrTypeId)
                if (thing) {
                    params.thingOrTypeName = thing.name
                }
            }
        }

        def plan = new Plan(params)

        if (request.xhr) {
            render(template: view, model: [plan: plan])
        } else {
            respond plan
        }
    }

    def save(Plan plan) {

        if (plan == null) {
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
            planService.save(plan)
            flash.message = message(code: 'default.created.message', args: [message(code: 'plan.label', default: 'Plan'), plan.id])
        } catch (ValidationException e) {
            flash.message = plan.errors
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

        def plan = planService.get(id)

        if (request.xhr) {
            render(template: view, model: [plan: plan])
        } else {
            respond plan
        }
    }

    def update(Plan plan) {
        if (plan == null) {
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
            planService.save(plan)
            flash.message = message(code: 'default.updated.message', args: [message(code: 'plan.label', default: 'Plan'), plan.id])
        } catch (ValidationException e) {
            flash.message = plan.errors
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

        planService.delete(id)
        flash.message = message(code: 'default.deleted.message', args: [message(code: 'plan.label', default: 'Plan'), id])

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
        Plan.list().each { e ->
            planService.delete(e.id)
        }

        def jsonFile = new File(fileName)
        if (jsonFile.exists()) {
            def json = jsonFile.text
            def querys = commonService.importFromJson(json, Plan.class)
            querys.each { e ->
                planService.save(e)
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

       def fjson = commonService.exportObjects2JsonString(Plan.list())
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
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'plan.label', default: 'Plan'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
