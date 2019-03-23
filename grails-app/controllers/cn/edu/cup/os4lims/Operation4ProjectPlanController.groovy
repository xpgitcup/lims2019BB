package cn.edu.cup.os4lims

import cn.edu.cup.lims.Person
import cn.edu.cup.lims.PersonTitle
import cn.edu.cup.lims.Plan
import cn.edu.cup.lims.Progress
import cn.edu.cup.lims.ProjectPlan
import cn.edu.cup.lims.ProjectPlanController

import cn.edu.cup.lims.Team
import cn.edu.cup.lims.ThingTypeCircle
import cn.edu.cup.system.JsFrame
import grails.converters.JSON

class Operation4ProjectPlanController extends ProjectPlanController {

    def teamService
    def treeViewService
    def progressService

    def addProgress() {
        def projectPlan = projectPlanService.get(params.projectPlan)
        if (projectPlan) {
            def progress = progressService.get(params.progress)
            if (progress) {
                if (!projectPlan.progresses.contains(progress)) {
                    projectPlan.progresses.add(progress)
                    projectPlanService.save(projectPlan)
                }
            }
        }
        redirect(action: "index")
    }

    def createProjectPlan(Team team) {
        def projectPlan = new ProjectPlan(
                upProjectPlan: null,
                description: "${team}.进度管理",
                team: team,
                precent: 0,
                updateDate: new Date(),
                serialNumber: 0
        )
        projectPlanService.save(projectPlan)
        checkProjectPlanItems(team, projectPlan)
        return projectPlan
    }

    private void checkProjectPlanItems(Team team, projectPlan) {
        if (!projectPlan.subItems) {
            def typePlan = Plan.findByThingType(team.thing.thingType)
            typePlan.subItems.each { e ->
                def newItem = new ProjectPlan(
                        upProjectPlan: projectPlan,
                        description: e.description,
                        precent: 0,
                        team: team,
                        updateDate: new Date(),
                        serialNumber: e.serialNumber
                )
                projectPlanService.save(newItem)
            }
        }
    }

    def getTreeViewData() {
        def team = teamService.get(params.currentTeam)
        def projectPlan
        if (ProjectPlan.countByTeam(team) < 1) {
            projectPlan = createProjectPlan(team)
        } else {
            projectPlan = ProjectPlan.findByTeamAndUpProjectPlanIsNull(team)
        }
        //params.context = "description"
        params.context = "toString"
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

    protected void prepareParams() {
        def myself = Person.get(session.realId)
        switch (params.key) {
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

    protected def processResult(result, params) {
        def status = [:]
        def projectPlan
        switch (params.key) {
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
                if (result.objectList.size()>0) {
                    projectPlan = ProjectPlan.findByTeamAndUpProjectPlanIsNull(result.objectList[0].team)
                    result.objectList.each { e ->
                        println("检查：${e}的归档情况...")
                        //def s = ProjectPlan.countByProgresses([e])
                        //status.put(e, s)
                    }
                }
                result.status = status
                break;
        }
        return result
    }

    def list() {
        prepareParams()
        def result = commonQueryService.listFunction(params)
        result = processResult(result, params)
        def view = result.view
        flash.message = result.message
        if (request.xhr) {
            render(template: view, model: [objectList: result.objectList, flash: flash, statusList: result.status])
        } else {
            respond result.objectList
        }
    }

    def index() {}
}
