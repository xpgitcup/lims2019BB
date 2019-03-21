package cn.edu.cup.os4lims

import cn.edu.cup.lims.Plan
import cn.edu.cup.lims.PlanController
import cn.edu.cup.lims.ThingType
import com.alibaba.fastjson.JSON

class Operation4PlanController extends PlanController {

    def thingTypeService
    def commonService

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
                    description: "${thingType.name}.计划",
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
                if (Plan.countByUpPlanAndDescription(plan, e) < 1) {
                    createSubPlanItem(plan, e)
                }
            }
        } else {
            println("继承上级计划：${thingTypeName}")
            def ut = thingType.upType
            if (ut) {
                //如果有上级类型,找到有详细计划的
                def pp = Plan.findByThingTypeAndUpPlanIsNull(ut)
                while (pp && (pp.subItems?.size() < 1) && (ut.upType)) {
                    ut = ut.upType
                    pp = Plan.findByThingTypeAndUpPlanIsNull(ut)
                }
                println("回溯完成：${pp}")
                pp.subItems?.each { e ->
                    if (Plan.countByUpPlanAndDescription(plan, e.description) < 1) {
                        copyPlanItem(plan, e)
                    }
                }
            }
        }
    }

    private void copyPlanItem(Plan plan, e) {
        def newItem = new Plan(
                description: e.description,
                upPlan: plan,
                thingType: plan.thingType,
                updateDate: new Date(),
                serialNumber: e.serialNumber
        )
        planService.save(newItem)
    }

    private void createSubPlanItem(Plan plan, e) {
        def newItem = new Plan(
                description: e.name,
                upPlan: plan,
                thingType: plan.thingType,
                updateDate: new Date(),
                serialNumber: e.sn
        )
        planService.save(newItem)
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
