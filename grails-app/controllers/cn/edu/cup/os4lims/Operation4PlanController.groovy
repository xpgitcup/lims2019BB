package cn.edu.cup.os4lims

import cn.edu.cup.lims.Plan
import cn.edu.cup.lims.PlanController
import cn.edu.cup.system.JsFrame
import grails.converters.JSON

class Operation4PlanController extends PlanController {

    def thingTypeService
    def thingService

    def create() {

        println("${params}")

        def view = "create"
        if (params.view) {
            view = params.view
        }

        if (params.thingOrTypeId) {
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
            //计算planVersion
            def q = Plan.executeQuery("select max(plan.planVersion) from Plan plan where plan.thingOrTypeName=${params.thingOrTypeName}")
            println("控制器内统计: ${q}")
            def maxV = q[0]
            def pv = 0
            if (maxV) {
                pv = maxV + 1
            } else {
                pv = 1
            }
            params.planVersion = pv
        }

        def plan = new Plan(params)

        if (request.xhr) {
            render(template: view, model: [plan: plan])
        } else {
            respond plan
        }
    }

    def index() {
        println("${params}")
        return super.index()
    }
}
