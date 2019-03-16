package cn.edu.cup.os4lims

import cn.edu.cup.lims.Person
import cn.edu.cup.lims.PersonTitle
import cn.edu.cup.lims.Team
import cn.edu.cup.lims.TeamController
import cn.edu.cup.lims.Thing
import cn.edu.cup.lims.ThingTypeCircle

class Operation4TeamController extends TeamController {

    def recruit() {
        def person = Person.findByName(params.name)
        def team = teamService.get(params.team)
        if (!team.members.contains(person)) {
            team.members.add(person)
            teamService.save(team)
        } else {
            flash.message = "已经加入了!"
        }
        chain(action: "index")
    }

    def disband(Team team) {
        if (team) {
            teamService.delete(team.id)
        }
        redirect(action: "index")
    }

    def dismiss() {
        println("${params}")
        def person = Person.get(params.person)
        def team = Team.get(params.currentTeam)
        if (team) {
            if (team.members.contains(person)) {
                team.members.remove(person)
                teamService.save(team)
            }
        }
        chain(action: "index")
    }

    def quitTeam(Team team) {
        def myself = Person.get(session.realId)
        if (team.members.contains(myself)) {
            team.members.remove(myself)
            teamService.save(team)
        } else {
            flash.message = "不属于该团队!"
        }
        chain(action: "index")
    }

    def joinTeam(Team team) {
        def myself = Person.get(session.realId)
        if (!team.members.contains(myself)) {
            team.members.add(myself)
            teamService.save(team)
        } else {
            flash.message = "已经加入了!"
        }
        chain(action: "index")
    }

    def createTeam(Thing thing) {
        println("${params}")
        def leader = Person.get(session.realId)
        if (leader) {
            if (!Team.findAllByThingAndLeader(thing, leader)) {
                def team = new Team(leader: leader, thing: thing)
                teamService.save(team)
                println("create ok ${team}")
            } else {
                flash.message = "不能重复！"
            }
        } else {
            flash.message = "找不到这个成员！"
        }
        chain(action: "index")
    }

    protected void prepareParams() {
        // 任务
        def currentTitle = PersonTitle.get(session.realTitle)
        def list = ThingTypeCircle.allRelatedThingTypes(currentTitle)
        switch (params.key) {
            case "可选题目":
                params.thingTypeList = list
                break
            case "相关团队":
                def currentThing = Thing.get(params.currentThing)
                params.currentThing = currentThing
                break
            case "队员列表":
                break
        }
    }

    protected def processResult(result, params) {
        switch (params.key) {
            case "队员列表":
                def currentTeam = Team.get(params.currentTeam)
                if (currentTeam) {
                    result.objectList = [currentTeam]
                    result.view = "listMember"
                }
                break
        }
        return result
    }

    def index() {}
}
