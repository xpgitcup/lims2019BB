var operation4ThingTypeDiv;
var jsTitle = "项目类型";
var title4ThingType = [jsTitle]
var isTreeView4ThingType = [true]
var treeData4ThingType = ["operation4ThingType/getTreeViewData"]

$(function () {
    console.info(jsTitle + "......");

    operation4ThingTypeDiv = $("#operation4ThingTypeDiv");
    var settings = {
        divId: operation4ThingTypeDiv,
        titles: title4ThingType,
        // 有关树形结构的设置
        isTreeView: isTreeView4ThingType,
        treeData: treeData4ThingType,
        treeNodeDoSomeThing: changeUpNode, //当节点被选择
        //paginationMessage: "",
        pageList: [],
        showPageList: false,
        loadFunction: loadThingType,
        countFunction: countThingType
    }

    configDisplayUI(settings);
});

function showCurrent(title) {
    $("#currentTitle").html("请选择...");
}

function deleteItem(id) {
    console.info("删除：" + id);
    ajaxExecuteWithMethod("operation4ThingType/delete?id=" + id, 'DELETE');
    console.info("删除：" + id + "了！");
    location.reload();
}

function editItem(id) {
    //var title = getCurrentTabTitle(operation4ThingTypeDiv);
    ajaxRun("operation4ThingType/edit", id, "showThingTypeDiv");
}

function createItem(id) {
    //var title = getCurrentTabTitle(operation4ThingTypeDiv);
    //ajaxRun("operation4ThingType/create/?upTitle=" + id, 0, "list" + title + "Div");
    ajaxRun("operation4ThingType/create?upType=" + id, 0, "showThingTypeDiv");
}

function createProject(id) {
    //var title = getCurrentTabTitle(operation4ThingTypeDiv);
    //ajaxRun("operation4ThingType/create/?upTitle=" + id, 0, "list" + title + "Div");
    ajaxRun("operation4ThingType/createProject", id, "showThingTypeDiv");
}

function createCourse(id) {
    //var title = getCurrentTabTitle(operation4ThingTypeDiv);
    //ajaxRun("operation4ThingType/create/?upTitle=" + id, 0, "list" + title + "Div");
    ajaxRun("operation4ThingType/createCourse", id, "showThingTypeDiv");
}

/*
* 节点被选择。。。
* */
function changeUpNode(node) {
    console.info("修改根节点的id...")
    $("#createItem").attr('href', 'javascript: createItem(' + node.attributes[0] + ')');
    $("#createItem").html("创建" + node.attributes[0] + '的子节点');
    $("#editItem").attr('href', 'javascript: editItem(' + node.attributes[0] + ')');
    $("#editItem").html("编辑" + node.attributes[0] + '节点');
    //  $("#deleteItem").attr('href', 'operation4ThingType/delete?id=' + node.attributes[0]);   // 不能POST
    //$("#deleteItem").attr('href', 'javascript: deleteItem(' + node.attributes[0] + ')');
    //$("#deleteItem").html("删除" + node.attributes[0] + '节点');
    $("#currentTitle").html(node.text);
    ajaxRun("operation4ThingType/show", node.attributes[0], "showThingTypeDiv");
}

/*
* 统计函数
* */
function countThingType(title) {
    console.info(jsTitle + "+统计......");
    var total = ajaxCalculate("operation4ThingType/count?key=" + title);
    return total
}

/*
* 数据加载函数
* */
function loadThingType(title, page, pageSize) {
    console.info(jsTitle + "+数据加载......" + title + " 第" + page + "页/" + pageSize);
    var params = getParams(page, pageSize);    //getParams必须是放在最最前面！！
    ajaxRun("operation4ThingType/list" + params + "&key=" + title, 0, "list" + title + "Div");
}