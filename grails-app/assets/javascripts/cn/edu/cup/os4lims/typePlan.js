/*
* 处理通用计划
* 2019.03.14
* */

var operation4PlanDiv;
var operation4ThingTypeUL;
var jsTitle = "通用计划";
var title4Plan = [jsTitle]
var isTreeView4Plan = [false]
var pagination4PlanDiv;
//var treeData4PlanTitle = ["operation4PlanTitle/getTreeViewData"]


$(function () {
    console.info(jsTitle + "......");
    var title = jsTitle;
    /*
    * 左边显示任务类型
    * */
    operation4ThingTypeUL = $("#operation4ThingTypeUL");
    operation4ThingTypeUL.tree({
        url: "operation4ThingType/getTreeViewData",
        onSelect: function (node) {
            console.info("树形结构节点选择：" + node.target.id);
            $.cookie("currentNode" + title, node.target.id);
            changeUpNode(node);
        },
        onLoadSuccess: function () {
            var cnodeid = readCookie("currentNode" + title, 0);
            console.info("上一次：" + cnodeid);
            operation4ThingTypeUL.tree("collapseAll");
            if (cnodeid != 0) {
                console.info("扩展到：" + cnodeid);
                var cnode = $("#" + cnodeid);
                operation4ThingTypeUL.tree("expandTo", cnode);
                operation4ThingTypeUL.tree("select", cnode);
            }
        }
    })

    /*
    * 右边的
    * */

    var total = countPlan();
    var title = jsTitle;
    var localPageSize = 10;

    pagination4PlanDiv = $("#pagination4PlanDiv");
    pagination4PlanDiv.pagination({
        pageSize: localPageSize,
        total: total,
        pageList: [1, 3, 5, 10, 20, 30],
        showPageList: true,
        //pageNumber: currentPage,
        //displayMsg: paginationMessage,
        onSelectPage: function (pageNumber, localPageSize) {
            //console.info("setupPaginationParams4TabPage: " + title)
            $.cookie("currentPage" + title, pageNumber);     //记录当前页面
            loadPlan();
        }
    })

});

function loadPlan() {
    var node = readCookie("currentNode", 0);
    ajaxRun("operation4Plan/list?key=" + jsTitle + "&keyString=" + node + "&thingTypeId=" + node, 0, "operation4PlanDiv");
}

function countPlan() {
    var node = readCookie("currentNode", 0);
    var count = ajaxCalculate("operation4Plan/count?key=" + jsTitle + "&keyString=" + node + "&thingTypeId=" + node);
    return count;
}

/*
* 节点被选择。。。
* */
function changeUpNode(node) {
    console.info("修改根节点的id...")
    $.cookie("currentNode", node.attributes[0]);
    var total = countPlan();
    $("#createItem").attr('href', 'javascript: createItem(' + node.attributes[0] + ')');
    $("#createItem").html("创建" + node.attributes[0] + '的计划');
    $("#editItem").attr('href', 'javascript: editItem(' + node.attributes[0] + ')');
    $("#editItem").html("编辑" + node.attributes[0] + '计划');
    $("#currentTitle").html(node.text);
    loadPlan();
}

function deleteItem(id) {
    ajaxExecuteWithMethod("operation4Plan/delete?id=" + id, 'DELETE');
    location.reload();
}

function editItem(id) {
    console.info("编辑计划...");
    ajaxRun("operation4Plan/edit?thingTypeId=" + id +
        "&view=editTypePlan",
        0, "operation4PlanDiv");
}

function showItem(id) {
    ajaxRun("operation4Plan/show?view=showTypePlan", id, "operation4PlanDiv");
}

function createItem(id) {
    console.info("创建计划...");
    ajaxRun("operation4Plan/create?thingTypeId=" + id +
        "&view=createTypePlan",
        0, "operation4PlanDiv");
}

function createPlanItem(id) {
    console.info("创建计划...");
    ajaxRun("operation4Plan/create?thingTypeId=" + id + "&view=createTypePlan",
        0, "operation4PlanDiv");
}