package cn.edu.cup.os4lims

import cn.edu.cup.lims.Plan
import cn.edu.cup.lims.PlanController
import cn.edu.cup.lims.PlanItem
import cn.edu.cup.lims.ThingType
import com.alibaba.fastjson.JSON

class Operation4PlanController extends PlanController {

    def thingTypeService
    def commonService
    def planItemService

    def createAuto() {
        def thingType = thingTypeService.get(params.thingTypeId)
        if (thingType) {
            createPlan(thingType)
            thingType.subTypes.each { e ->
                createPlan(e)
            }
        }
        redirect(action: "index")
    }

    private void createPlan(thingType, plans) {
        def plan
        if (Plan.countByThingType(thingType) < 1) {
            def newPlan = new Plan(
                    thingType: thingType,
                    updateDate: new Date()
            )
            planService.save(newPlan)
            plan = newPlan
        } else {
            plan = Plan.findByThingType(thingType)
        }
        //完善计划
        def thingTypeName = plan.thingType.name
        def items = plans.get(thingTypeName)
        if (items) {
            println("完善：${thingTypeName}")
            items.each { e ->
                if (PlanItem.countByPlanAndDescription(plan, e) < 1) {
                    def newItem = new PlanItem(description: e, plan: plan)
                    planItemService.save(newItem)
                }
            }
        } else {
            def ut = thingType.upType
            if (ut) {
                //如果有上级类型,找到有详细计划的
                def pp = Plan.findByThingType(ut)
                while (pp && (pp.planItems?.size() < 1) && (ut.upType)) {
                    ut = ut.upType
                    pp = Plan.findByThingType(ut)
                }
                pp.planItems?.each { e->
                    if (PlanItem.countByPlanAndDescription(plan, e.description) < 1) {
                        def newItem = new PlanItem(description: e.description, plan: plan)
                        planItemService.save(newItem)
                    }
                }
            }
        }
    }

    def index() {
        //println("${params}")
        def fileName = "${commonService.webRootPath}/systemConfig/thingTypePlan.json"
        def jsonFile = new File(fileName)
        def plans
        if (jsonFile.exists()) {
            def json = jsonFile.text
            plans = JSON.parse(json)
            println("计划配置：${plans}")
        }

        ThingType.list().each { e ->
            println("创建：${e}.计划")
            createPlan(e, plans)
        }
        return super.index()
    }
}
