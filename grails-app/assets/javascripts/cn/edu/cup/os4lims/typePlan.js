/*
* 处理通用计划
* 2019.03.14
* */

var operation4PlanDiv;
var operation4ThingTypeUL;
var jsTitle = "通用计划";
var title4Plan = [jsTitle]
var isTreeView4Plan = [false]
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
            treeViewUl.tree("collapseAll");
            if (cnodeid != 0) {
                console.info("扩展到：" + cnodeid);
                var cnode = $("#" + cnodeid);
                treeViewUl.tree("expandTo", cnode);
                treeViewUl.tree("select", cnode);
            }
        }
    })
});

/*
* 节点被选择。。。
* */
function changeUpNode(node) {
    console.info("修改根节点的id...")
    $("#createItem").attr('href', 'javascript: createItem(' + node.attributes[0] + ')');
    $("#createItem").html("创建" + node.attributes[0] + '的计划');
    $("#editItem").attr('href', 'javascript: editItem(' + node.attributes[0] + ')');
    $("#editItem").html("编辑" + node.attributes[0] + '计划');
    //  $("#deleteItem").attr('href', 'operation4ThingType/delete?id=' + node.attributes[0]);   // 不能POST
    //$("#deleteItem").attr('href', 'javascript: deleteItem(' + node.attributes[0] + ')');
    //$("#deleteItem").html("删除" + node.attributes[0] + '节点');
    $("#currentTitle").html(node.text);
    ajaxRun("operation4Plan/list?key=" + jsTitle + "&thingOrTypeId=" + node.attributes[0], 0, "operation4PlanDiv");
}

function createItem(id) {
    console.info("创建计划...");
    ajaxRun("operation4Plan/create?thingOrTypeId=" + id + "view=create", 0, "operation4PlanDiv");
}