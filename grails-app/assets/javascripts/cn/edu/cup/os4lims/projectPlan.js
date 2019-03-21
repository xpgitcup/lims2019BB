var status = ["选择团队", "进度归档"];
var currentTeam;
var jsTitle4ProjectPlan = "进度归档"
var operation4ProjectPlanTeamListDiv;
var pageSize4ProjectPlan = 10;

$(function () {
    console.info("进度归档...")

    operation4ProjectPlanTeamListDiv = $("#operation4ProjectPlanTeamListDiv");
    pagination4ProjectPlanTeamListDiv = $("#pagination4ProjectPlanTeamListDiv");

    /*
    * 状态：选择项目，进度归档,
    * 首先读取当前状态，如果有项目---进入进度归档，如果没有，先选择项目
    * */

    var currentPage = readCookie("currentPage" + jsTitle4ProjectPlan, 1);
    var total = countTeams();

    currentTeam = readCookie("currentTeam" + jsTitle4ProjectPlan, 0);

    shiftDisplay(currentTeam, currentPage);

    pagination4ProjectPlanTeamListDiv.pagination({
        pageSize: pageSize4ProjectPlan,
        total: total,
        pageNumber: currentPage,
        onSelectPage: function (pageNumber, pageSize) {
            $.cookie("currentPage" + jsTitle4ProjectPlan, pageNumber);
            loadTeams(pageNumber, pageSize)
        }
    })

    $('.dragitem').draggable({
        revert:true,
        deltaX:10,
        deltaY:10,
        proxy:function(source){
            var n = $('<div class="proxy"></div>');
            n.html($(source).html()).appendTo('body');
            return n;
        }
    });

});

function loadTeams(page, pageSize) {
    console.info("调入所有相关的项目");
    console.info(jsTitle4ProjectPlan + "+数据加载......" + " 第" + page + "页/" + pageSize);
    var params = getParams(page, pageSize);    //getParams必须是放在最最前面！！
    ajaxRun("operation4ProjectPlan/list" + params + "&key=" + jsTitle4ProjectPlan, 0, "operation4ProjectPlanTeamListDiv");
}

function countTeams() {
    console.info(jsTitle4ProjectPlan + "+统计......");
    var total = ajaxCalculate("operation4ProjectPlan/count?key=" + jsTitle4ProjectPlan);
    console.info("统计队伍总数：" + total);
    return total
}

function shiftDisplay(currentTeam, currentPage) {
    var projectPlanDiv = document.getElementById("projectPlanDiv");
    var teamListDiv = document.getElementById("teamListDiv");
    if (currentTeam > 0) {
        $("#projectSelect").html("当前团队:" + currentTeam);
        teamListDiv.style.display = "none";
        projectPlanDiv.style.display = "block";

        var title = jsTitle4ProjectPlan;
        operation4ProjectPlanUL = $("#operation4ProjectPlanUL");
        operation4ProjectPlanUL.tree({
            url: "operation4ProjectPlan/getTreeViewData?currentTeam=" + currentTeam,
            onSelect: function (node) {
                console.info("树形结构节点选择：" + node.target.id);
                $.cookie("currentNode" + title, node.target.id);
                changeUpNode(node);
            },
            onLoadSuccess: function () {
                var cnodeid = readCookie("currentNode" + title, 0);
                console.info("上一次：" + cnodeid);
                operation4ProjectPlanUL.tree("collapseAll");
                if (cnodeid != 0) {
                    console.info("扩展到：" + cnodeid);
                    var cnode = $("#" + cnodeid);
                    operation4ProjectPlanUL.tree("expandTo", cnode);
                    operation4ProjectPlanUL.tree("select", cnode);
                }
            }
        })

        var total = countProgress();
        var currentPageProgress = readCookie("currentPageProgress" + jsTitle4ProjectPlan, 1);
        loadProgress(currentPageProgress, pageSize4ProjectPlan);

        pagination4ProjectPlanProgressListDiv = $("#pagination4ProjectPlanProgressListDiv");
        pagination4ProjectPlanProgressListDiv.pagination({
            pageSize: pageSize4ProjectPlan,
            total: total,
            pageNumber: currentPageProgress,
            onSelectPage: function (pageNumber, pageSize) {
                $.cookie("currentPageProgress" + jsTitle4ProjectPlan, pageNumber);
                loadProgress(pageNumber, pageSize)
            }
        });

    } else {
        $("#projectSelect").html("选择团队");
        teamListDiv.style.display = "block";
        projectPlanDiv.style.display = "none";
        loadTeams(currentPage, pageSize4ProjectPlan);
    }
}

function selectTeam(id) {
    $.cookie("currentTeam" + jsTitle4ProjectPlan, id);
    $("#projectSelect").html("当前团队:" + id);
    location.reload();
}

function clearTeam() {
    $.cookie("currentTeam" + jsTitle4ProjectPlan, 0);
    $("#projectSelect").html("选择团队:");
    location.reload();
}

function loadProgress(page, pageSize) {
    currentTeam = readCookie("currentTeam" + jsTitle4ProjectPlan, 0);
    var params = getParams(page, pageSize);    //getParams必须是放在最最前面！！
    ajaxRun("operation4ProjectPlan/list" + params + "&key=进度列表&currentTeam=" + currentTeam, 0, "operation4ProjectPlanProgressListDiv");
}

function countProgress() {
    console.info(jsTitle4ProjectPlan + "+统计......");
    var total = ajaxCalculate("operation4ProjectPlan/count?key=进度列表&currentTeam=" + currentTeam);
    console.info("统计进度：" + total);
    return total
}

