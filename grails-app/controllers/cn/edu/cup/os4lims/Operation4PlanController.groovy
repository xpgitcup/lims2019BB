package cn.edu.cup.os4lims

import cn.edu.cup.lims.Plan
import cn.edu.cup.lims.PlanController

class Operation4PlanController extends PlanController {

    def thingTypeService
    def thingService

    def create() {

        println("${params}")

        def view = "create"
        if (params.view) {
            view = params.view
        }

        if (params.thingTypeId) {
            def thingType = thingTypeService.get(params.thingTypeId)
            if (thingType) {
                params.thingTypeName = thingType.name
                def list = []
                thingType.relatedThingTypeList().each { e ->
                    list.add(e.id)
                }
                def liststr = com.alibaba.fastjson.JSON.toJSONString(list)
                println("类型列表：${liststr}")
                params.thingTypeIdList = liststr
            }
            //计算planVersion
            def q = Plan.executeQuery("select max(plan.planVersion) from Plan plan where (plan.thingTypeName=${params.thingTypeName})")
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
        //println("${params}")
        return super.index()
    }
}
