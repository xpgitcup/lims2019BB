package cn.edu.cup.os4lims

import cn.edu.cup.lims.Person
import cn.edu.cup.lims.PersonTitle
import cn.edu.cup.lims.Progress
import cn.edu.cup.lims.ProjectPlan
import cn.edu.cup.lims.ProjectPlanController
import cn.edu.cup.lims.Team
import cn.edu.cup.lims.ThingTypeCircle
import cn.edu.cup.system.JsFrame
import grails.converters.JSON

class Operation4ProjectPlanAndProgressController extends Operation4ProjectPlanController {

    def getTreeViewData() {
        def team = teamService.get(params.currentTeam)
        def projectPlan
        if (ProjectPlan.countByTeam(team) < 1) {
            projectPlan = createProjectPlan(team)
        } else {
            projectPlan = ProjectPlan.findByTeamAndUpProjectPlanIsNull(team)
        }
        //params.context = "description"
        params.context = "showDetail"
        params.subItems = "subItems"
        params.attributes = "id"    //
        params.useMethod = true
        def result = treeViewService.generateNodesString(projectPlan, params, JsFrame.EasyUI)
        if (request.xhr) {
            render result as JSON
        } else {
            result
        }
    }

    protected def processResult(result, params) {
        def status = [:]
        def projectPlan
        switch (params.key) {
            case "进度维护":
            case "进度归档":
                result.objectList.each { e ->
                    println("检查：${e}的归档信息...")
                    projectPlan = ProjectPlan.findByTeamAndUpProjectPlanIsNull(e)
                    if (!projectPlan) {
                        projectPlan = createProjectPlan(e)
                    }
                }
                break
            case "进度列表":
                if (result.objectList?.size()>0) {
                    projectPlan = ProjectPlan.findByTeamAndUpProjectPlanIsNull(result.objectList[0].team)
                    result.objectList.each { e ->
                        println("检查：${e}的归档情况...")
                        def sql = "select count(*) from project_plan_progress where progress_id=${e.id}"
                        def db = new groovy.sql.Sql(dataSource)
                        def s = db.rows(sql)
                        println("进度：${s}")
                        status.put(e, s.getAt("count(*)"))
                    }
                }
                result.status = status
                break;
        }
        return result
    }

    protected void prepareParams() {
        def myself = Person.get(session.realId)
        switch (params.key) {
            case "进度维护":
            case "进度归档":
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
            case "进度列表":
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
