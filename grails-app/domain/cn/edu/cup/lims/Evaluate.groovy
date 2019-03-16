package cn.edu.cup.lims

class Evaluate {

    Progress progress
    Person evaluator
    Double score
    Date evaluateDate = new Date()

    static constraints = {
        progress()
        evaluator(nullable: false)
        score()
        evaluateDate()
    }

    String toString() {
        return "${progress}.${evaluator}.${score}"
    }
}
