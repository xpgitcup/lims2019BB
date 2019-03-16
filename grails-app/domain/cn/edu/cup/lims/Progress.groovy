package cn.edu.cup.lims

import java.sql.Timestamp

class Progress {

    Progress prevProgress
    String currentStatus
    String problemEncounter
    String supportFileName
    Person contributor
    Date regDate = new Date()

    static belongsTo = [team: Team]

    static constraints = {
        prevProgress(nullable: true)
        currentStatus(nullable: false)
        problemEncounter(nullable: true)
        supportFileName(nullable: true)
        contributor(nullable: false)
        regDate()
    }

    String toString() {
        return "${team}.${regDate}.${currentStatus}"
    }

}
