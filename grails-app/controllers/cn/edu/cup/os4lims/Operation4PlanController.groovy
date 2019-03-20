package cn.edu.cup.os4lims

import cn.edu.cup.lims.Plan
import cn.edu.cup.lims.PlanController
import cn.edu.cup.lims.PlanItem
import cn.edu.cup.lims.ThingType

class Operation4PlanController extends PlanController {

    def thingTypeService

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

    private void createPlan(thingType) {
        if (Plan.findByThingType(thingType)<1) {
            def newPlan = new Plan(
                    thingType: thingType,
                    updateDate: new Date()
            )
            planService.save(newPlan)
        }
    }

    def index() {
        //println("${params}")
        ThingType.list().each { e->
            createPlan(e)
        }
        return super.index()
    }
}
