package cn.edu.cup.os4lims

import cn.edu.cup.lims.Person
import cn.edu.cup.lims.PersonTitle
import cn.edu.cup.lims.Plan
import cn.edu.cup.lims.Progress
import cn.edu.cup.lims.ProjectPlan
import cn.edu.cup.lims.ProjectPlanController
import cn.edu.cup.lims.ProjectPlanItem
import cn.edu.cup.lims.Team
import cn.edu.cup.lims.ThingType
import cn.edu.cup.lims.ThingTypeCircle
import cn.edu.cup.system.JsFrame
import grails.converters.JSON

class Operation4ProjectPlanController extends ProjectPlanController {

    def teamService
    def treeViewService
    def projectPlanItemService

    def createProjectPlan(Team team) {
        def projectPlan = new ProjectPlan(
                team: team,
                updateDate: new Date()
        )
        projectPlanService.save(projectPlan)
        checkProjectPlanItems(team, projectPlan)
        return projectPlan
    }

    private void checkProjectPlanItems(Team team, projectPlan) {
        if (!projectPlan.projectPlaneItems) {
            def typePlan = Plan.findByThingType(team.thing.thingType)
            typePlan.planItems.each { e ->
                def newItem = new ProjectPlanItem(description: e.description, projectPlan: projectPlan)
                projectPlanItemService.save(newItem)
            }
        }
    }

    def getTreeViewData() {
        def team = teamService.get(params.currentTeam)
        def projectPlan
        if (ProjectPlan.countByTeam(team) < 1) {
            projectPlan = createProjectPlan(team)
        } else {
            projectPlan = ProjectPlan.findByTeam(team)
        }
        def data = projectPlan.projectPlaneItems
        params.context = "description"
        params.subItems = "projectPlaneItems"
        params.attributes = "id"    //
        def result = treeViewService.generateNodesString(data, params, JsFrame.EasyUI)
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
        switch (params.key) {
            case "进度归档":
                result.objectList.each { e ->
                    def projectPlan = ProjectPlan.findByTeam(e)
                    if (!projectPlan) {
                        createProjectPlan(e)
                    }
                }
                break
        }
        return result
    }

    def index() {}
}
