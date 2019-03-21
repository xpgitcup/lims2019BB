package cn.edu.cup.lims

class Plan {

    Plan upPlan
    ThingType thingType
    String description
    Date updateDate = new Date()

    static hasMany = [subItems: Plan]

    static constraints = {
        upPlan(nullable: true)
        description()
        thingType()
        updateDate()
    }

    String toString() {
        return "${description}"
    }

}
