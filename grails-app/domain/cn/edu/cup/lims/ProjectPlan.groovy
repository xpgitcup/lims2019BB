package cn.edu.cup.lims

class ProjectPlan {

    ProjectPlan upProjectPlan
    Team team
    String description
    String status = ""
    String progressList = ""
    Date updateDate
    Double percent = 0
    Integer serialNumber = 0

    static hasMany = [subItems: ProjectPlan]

    static constraints = {
        upProjectPlan(nullable: true)
        team()
        description()
        status()
        progressList()
        updateDate()
        percent()
        serialNumber()
    }

    static mapping = {
        subItems sort: 'serialNumber'  //这是排序的标准做法
    }

    String toString() {
        return "${description}"
    }
}
