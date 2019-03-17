package cn.edu.cup.lims

class Plan {

    Integer thingOrTypeId        //id
    String thingOrTypeName      //thing ID 以及名称
    Date updateDate
    Boolean isTypePlan = true   // 针对类型的计划
    Integer planVersion = 0

    static hasMany = [planItems: PlanItem]

    static constraints = {
        thingOrTypeId()
        thingOrTypeName()
        updateDate()
        isTypePlan()
    }

    String toString() {
        return "${thingOrTypeName}.计划"
    }

}
