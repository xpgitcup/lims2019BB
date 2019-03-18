package cn.edu.cup.lims

class Plan {

    Integer thingTypeId       //id
    String thingTypeName      //thing ID 以及名称
    Date updateDate = new Date()
    Integer planVersion = 0

    static hasMany = [planItems: PlanItem]

    static constraints = {
        thingTypeId()
        thingTypeName()
        updateDate()
        planVersion()
    }

    String toString() {
        return "${thingTypeName}.计划"
    }

}
