package cn.edu.cup.lims

class ProjectPlan {

    Integer teamId
    String projectName
    Date updateDate

    static hasMany = [projectPlaneItems: ProjectPlanItem]

    static constraints = {
        teamId()
        projectName()
        updateDate()
    }

    String toString() {
        return "${projectName}.计划"
    }
}
