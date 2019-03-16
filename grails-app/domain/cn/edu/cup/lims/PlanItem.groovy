package cn.edu.cup.lims

class PlanItem {

    String description
    String status
    String fileName
    String progressList
    Double percent = 0

    static belongsTo = [plan: Plan]

    static constraints = {
        description()
        status()
        fileName(nullable: true)
        progressList(nullable: true)
        percent()
    }

    String toString() {
        return "${plan}.${description}"
    }
}
