package cn.edu.cup.lims

class Plan {

    ThingType thingType
    Date updateDate = new Date()

    static hasMany = [planItems: PlanItem]

    static constraints = {
        thingType()
        updateDate()
    }

    String toString() {
        return "${thingType}.计划"
    }

}
