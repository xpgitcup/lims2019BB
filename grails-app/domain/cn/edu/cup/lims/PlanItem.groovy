package cn.edu.cup.lims

class PlanItem {

    String description
    PlanItem upPlanItem

    static belongsTo = [plan: Plan]

    static hasMany = [subPlanItems: PlanItem]

    static constraints = {
        upPlanItem(nullable: true)
        description()
    }

    String toString() {
        return "${plan}.${description}"
    }
}
