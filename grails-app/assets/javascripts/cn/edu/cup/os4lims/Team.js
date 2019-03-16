var operation4TeamDiv;
var operation4TeamUl;
var jsTitleTeam = ["可选题目", "相关团队", "队员列表"];
var title4Team = jsTitleTeam;
var tabsTitle = "人员维护";
var localPageSizeTeam = 10;
var tipsOperation4Team;

$(function () {
    console.info(jsTitleTeam + "......");

    tipsOperation4Team = $("#tipsOperation4Team");
    operation4TeamUl = $("#operation4TeamUl");
    operation4TeamDiv = $("#operation4TeamDiv");

    var settings = {
        divId: operation4TeamDiv,
        titles: title4Team,
        tabsTitle: tabsTitle,
        pageSize: localPageSizeTeam,
        pageList: [1, 3, 5, 10],
        loadFunction: loadTeam,
        countFunction: countTeam
    }

    configDisplayUI(settings);

    setupDisplayUl(operation4TeamUl, jsTitleTeam);
    reflashDisplayUl(operation4TeamUl, jsTitleTeam);

});

function disband(id) {
    ajaxExecute("operation4Team/disband/" + id)
    location.reload();
}

function dismiss(id) {
    var ids = loadAllDisplayTitleId(jsTitleTeam);
    ajaxExecute("operation4Team/dismiss/?person=" + id + "&currentTeam=" + ids[1])
    console.info("解聘：" + id);
    location.reload();
}

function quitTeam(id) {
    ajaxExecute("operation4Team/quitTeam/" + id);
    operation4TeamDiv.tabs("select", "相关队员");
    location.reload();
}

function joinTeam(id) {
    ajaxExecute("operation4Team/joinTeam/" + id)
    selectCurrentItem(id)
    operation4TeamDiv.tabs("select", "队员列表");
}

function createTeam(id) {
    console.info("创建团队...");
    selectCurrentItem(id)
    ajaxExecute("operation4Team/createTeam/" + id)
    operation4TeamDiv.tabs("select", "相关团队");
}

function listTeam(id) {
    selectCurrentItem(id)
    operation4TeamDiv.tabs("select", "相关团队");
}

function selectCurrentItem(id) {
    var title = getCurrentTabTitle(operation4TeamDiv)
    $.cookie("currentKey" + title, id);
}

function listMembers(id) {
    selectCurrentItem(id)
    operation4TeamDiv.tabs("select", "队员列表");
}

/*
* 在这里构建参数
* */
function shiftDisplay(title) {
    console.info("显示当前提示...")
    var key = "currentKey";
    var id;
    var param = ""
    switch (title) {
        case "可选题目":
            tipsOperation4Team.html("可以创建团队，也可以查看团队！");
            break;
        case "相关团队":
            key += "可选题目";
            id = readCookie(key, 0);
            console.info("当前id:" + key + "=" + id);
            if (id > 0) {
                param = "&currentThing=" + id;
                tipsOperation4Team.html("查看当前任务相关团队：");
            } else {
                tipsOperation4Team.html("请先选择任务！");
            }
            break;
        case "队员列表":
            key += "相关团队";
            id = readCookie(key, 0);
            console.info("当前id:" + key + "=" + id);
            if (id > 0) {
                param = "&currentTeam=" + id;
                tipsOperation4Team.html("查看当前团队：");
            } else {
                tipsOperation4Team.html("请先选择团队！");
            }
            break
    }
    return param
}

/*
* 统计函数
* */
function countTeam(title) {
    console.info(jsTitleTeam + "+统计......");
    var append = shiftDisplay(title);
    var total = ajaxCalculate("operation4Team/count?key=" + title + append);
    return total
}

/*
* 数据加载函数
* */
function loadTeam(title, page, pageSize) {
    console.info(jsTitleTeam + "+数据加载......" + title + " 第" + page + "页/" + pageSize);
    var append = shiftDisplay(title);
    var params = getParams(page, pageSize);    //getParams必须是放在最最前面！！
    ajaxRun("operation4Team/list" + params + "&key=" + title + append, 0, "list" + title + "Div");
}