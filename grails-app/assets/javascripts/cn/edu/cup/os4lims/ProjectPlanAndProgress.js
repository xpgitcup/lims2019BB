var status = ["选择团队", "进度维护"];
var currentTeam;
var jsTitle4ProjectPlanAndProgress = "进度维护"
var operation4ProjectPlanAndProgressTeamListDiv;
var pageSize4ProjectPlan = 10;

$(function () {
    console.info("进度归档...")

    operation4ProjectPlanAndProgressTeamListDiv = $("#operation4ProjectPlanAndProgressTeamListDiv");
    pagination4ProjectPlanAndProgressTeamListDiv = $("#pagination4ProjectPlanAndProgressTeamListDiv");

    /*
    * 状态：选择项目，进度归档,
    * 首先读取当前状态，如果有项目---进入进度归档，如果没有，先选择项目
    * */

    var currentPage = readCookie("currentPage" + jsTitle4ProjectPlanAndProgress, 1);
    var total = countTeams();

    currentTeam = readCookie("currentTeam" + jsTitle4ProjectPlanAndProgress, 0);

    shiftDisplay(currentTeam, currentPage);

    pagination4ProjectPlanAndProgressTeamListDiv.pagination({
        pageSize: pageSize4ProjectPlan,
        total: total,
        pageNumber: currentPage,
        onSelectPage: function (pageNumber, pageSize) {
            $.cookie("currentPage" + jsTitle4ProjectPlanAndProgress, pageNumber);
            loadTeams(pageNumber, pageSize)
        }
    })

});

function loadTeams(page, pageSize) {
    console.info("调入所有相关的项目");
    console.info(jsTitle4ProjectPlanAndProgress + "+数据加载......" + " 第" + page + "页/" + pageSize);
    var params = getParams(page, pageSize);    //getParams必须是放在最最前面！！
    ajaxRun("operation4ProjectPlanAndProgress/list" + params + "&key=" + jsTitle4ProjectPlanAndProgress, 0, "operation4ProjectPlanAndProgressTeamListDiv");
}

function countTeams() {
    console.info(jsTitle4ProjectPlanAndProgress + "+统计......");
    var total = ajaxCalculate("operation4ProjectPlanAndProgress/count?key=" + jsTitle4ProjectPlanAndProgress);
    console.info("统计队伍总数：" + total);
    return total
}

function shiftDisplay(currentTeam, currentPage) {
    var projectPlanDiv = document.getElementById("projectPlanDiv");
    var teamListDiv = document.getElementById("teamListDiv");
    var guidMessage = document.getElementById("guidMessage");

    if (currentTeam > 0) {
        $("#projectSelect").html("当前团队:" + currentTeam);
        teamListDiv.style.display = "none";
        projectPlanDiv.style.display = "block";
        guidMessage.style.display = "block";

        var title = jsTitle4ProjectPlanAndProgress;
        operation4ProjectPlanAndProgressUL = $("#operation4ProjectPlanAndProgressUL");
        operation4ProjectPlanAndProgressUL.tree({
            url: "operation4ProjectPlanAndProgress/getTreeViewData?currentTeam=" + currentTeam,
            onSelect: function (node) {
                console.info("树形结构节点选择：" + node.target.id);
                $.cookie("currentNode" + title, node.target.id);
                changeUpNode(node);
            },
            onLoadSuccess: function () {
                var cnodeid = readCookie("currentNode" + title, 0);
                console.info("上一次：" + cnodeid);
                operation4ProjectPlanAndProgressUL.tree("collapseAll");
                if (cnodeid != 0) {
                    console.info("扩展到：" + cnodeid);
                    var cnode = $("#" + cnodeid);
                    operation4ProjectPlanAndProgressUL.tree("expandTo", cnode);
                    operation4ProjectPlanAndProgressUL.tree("select", cnode);
                }
            }
        })

        var total = countProgress();
        var currentPageProgress = readCookie("currentPageProgress" + jsTitle4ProjectPlanAndProgress, 1);
        loadProgress(currentPageProgress, pageSize4ProjectPlan);

        pagination4ProjectPlanProgressListDiv = $("#pagination4ProjectPlanProgressListDiv");
        pagination4ProjectPlanProgressListDiv.pagination({
            pageSize: pageSize4ProjectPlan,
            total: total,
            pageNumber: currentPageProgress,
            onSelectPage: function (pageNumber, pageSize) {
                $.cookie("currentPageProgress" + jsTitle4ProjectPlanAndProgress, pageNumber);
                loadProgress(pageNumber, pageSize)
            }
        });

    } else {
        $("#projectSelect").html("选择团队");
        teamListDiv.style.display = "block";
        projectPlanDiv.style.display = "none";
        guidMessage.style.display = "noone";

        loadTeams(currentPage, pageSize4ProjectPlan);
    }
}

function changeUpNode(node) {
    console.info(node.target);
    $(".chose").html("归档->" + node.attributes[0]);
    $.cookie("currentPlanItem", node.attributes[0]);
}

function selectTeam(id) {
    $.cookie("currentTeam" + jsTitle4ProjectPlanAndProgress, id);
    $("#projectSelect").html("当前团队:" + id);
    location.reload();
}

function clearTeam() {
    $.cookie("currentTeam" + jsTitle4ProjectPlanAndProgress, 0);
    $("#projectSelect").html("选择团队:");
    location.reload();
}

function loadProgress(page, pageSize) {
    currentTeam = readCookie("currentTeam" + jsTitle4ProjectPlanAndProgress, 0);
    var params = getParams(page, pageSize);    //getParams必须是放在最最前面！！
    ajaxRun("operation4ProjectPlanAndProgress/list" + params + "&key=进度列表&currentTeam=" + currentTeam, 0, "operation4ProjectPlanAndProgressProgressListDiv");
}

function countProgress() {
    console.info(jsTitle4ProjectPlanAndProgress + "+统计......");
    var total = ajaxCalculate("operation4ProjectPlanAndProgress/count?key=进度列表&currentTeam=" + currentTeam);
    console.info("统计进度：" + total);
    return total
}

function selectProgress(id) {
    console.info("归档：" + id);
    var planItem = readCookie("currentPlanItem", 0);
    ajaxExecute("operation4ProjectPlanAndProgress/addProgress?projectPlan=" + planItem + "&progress=" + id);
    location.reload();
}