package cn.edu.cup.os4lims

import cn.edu.cup.lims.Person
import cn.edu.cup.lims.PersonTitle
import cn.edu.cup.lims.Progress
import cn.edu.cup.lims.ProgressController
import cn.edu.cup.lims.Team
import cn.edu.cup.lims.ThingTypeCircle

import java.text.SimpleDateFormat

class Operation4ProgressController extends ProgressController {

    def createNextProgress(Progress prevProgress) {
        def myself = Person.get(session.realId)
        def progress = new Progress(
                team: prevProgress.team,
                prevProgress: prevProgress,
                contributor: myself
        )
        Date prev = prevProgress.regDate
        Date now = progress.regDate
        def dif = (now.getTime() - prev.getTime()) / 1000 / 60
        println("时间差：${dif}")
        if (dif < 1) {
            def year = prevProgress.regDate[Calendar.YEAR]
            def month = prevProgress.regDate[Calendar.MONTH]
            def day = prevProgress.regDate[Calendar.DATE]
            def hour = prevProgress.regDate[Calendar.HOUR_OF_DAY]
            def minute = prevProgress.regDate[Calendar.MINUTE] + 1
            println("时间没有错开！${year} ${month} ${day} ${hour} ${minute}")
            //progress.regDate = new Date(year: year, month: month, date: day, hours: hour, minutes: minute)    //不对--完全是乱的
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm")
            progress.regDate = df.parse("${year}-${month + 1}-${day} ${hour}:${minute}")    // 月份+1
            println("修正后的时间：${progress.regDate}")
        } else {
            println("两个时间：${prevProgress.regDate} ${prevProgress.regDate}")
        }
        def view = "createProgress"
        if (request.xhr) {
            render(template: view, model: [progress: progress])
        } else {
            respond progress
        }
    }

    def createProgress(Team team) {
        def myself = Person.get(session.realId)
        def progress = new Progress(team: team, contributor: myself)
        def view = "createProgress"
        if (request.xhr) {
            render(template: view, model: [progress: progress])
        } else {
            respond progress
        }
    }

    protected def processResult(result, params) {
        switch (params.key) {
            case "我参与的":
                def teams = []
                result.objectList.each { e ->
                    //println("查找 ${e}")
                    teams.add(Team.get(e.team_members_id))
                }
                //println("转换后：${teams}")
                result.objectList = teams
                break
        }
        return result
    }

    protected void prepareParams() {
        def myself = Person.get(session.realId)
        switch (params.key) {
            case "我管理的":
                // 任务
                def currentTitle = PersonTitle.get(session.realTitle)
                def list = ThingTypeCircle.allRelatedThingTypes(currentTitle)
                params.thingTypeList = list
                break
            case "反馈信息":
                def currentProgress = Progress.get(params.currentProgress)
                if (currentProgress) {
                    params.currentProgress = currentProgress
                }
                break
            case "进度查看":
                def currentTeam = Team.get(params.currentTeam)
                if (currentTeam) {
                    params.currentTeam = currentTeam
                }
                break
            case "我参与的":
                params.myself = myself.id
                break
            default:
                params.myself = myself
        }
    }

    def index() {}
}
