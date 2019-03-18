package cn.edu.cup.lims

class Plan {

    Integer thingTypeId       //id
    String thingTypeIdList
    String thingTypeName      //thing ID 以及名称
    Date updateDate
    Integer planVersion = 0

    static hasMany = [planItems: PlanItem]

    static constraints = {
        thingTypeId()
        thingTypeName()
        thingTypeIdList()
        updateDate()
    }

    String toString() {
        return "${thingTypeName}.计划"
    }

}
