package cn.edu.cup.lims

class ProjectPlan {

    Team team
    Date updateDate

    static hasMany = [projectPlaneItems: ProjectPlanItem]

    static constraints = {
        team()
        updateDate()
    }

    String toString() {
        return "${team}.计划"
    }
}
