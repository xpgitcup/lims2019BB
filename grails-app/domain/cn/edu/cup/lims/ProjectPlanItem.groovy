package cn.edu.cup.lims

class ProjectPlanItem {

    ProjectPlanItem upProjectItem
    String description
    String status
    String progressList
    Double percent = 0

    static belongsTo = [projectPlan: ProjectPlan]

    static constraints = {
        upProjectItem(nullable: true)
        description()
        status()
        progressList()
        percent()
    }

    String toString() {
        return "${description}"
    }

}
