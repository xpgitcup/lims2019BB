var operation4ThingTypeCircleDiv;
var jsTitleThingTypeCircle = ["任务分配"];
var title4ThingTypeCircle = jsTitleThingTypeCircle;
var tabsTitle = "任务分配";
var localPageSizeThingTypeCircle = 10;

$(function () {
    console.info(jsTitleThingTypeCircle + "......");

    operation4ThingTypeCircleDiv = $("#operation4ThingTypeCircleDiv");
    var settings = {
        divId: operation4ThingTypeCircleDiv,
        titles: title4ThingTypeCircle,
        tabsTitle: tabsTitle,
        pageSize: localPageSizeThingTypeCircle,
        pageList: [1, 3, 5, 10, 20, 30],
        loadFunction: loadThingTypeCircle,
        countFunction: countThingTypeCircle
    }

    configDisplayUI(settings);

    //静态设置--树形结构
    thingTypeTree = $("#thingTypeTree");
    personTitleTree = $("#personTitleTree");

    thingTypeTree.tree({
        url: "operation4ThingType//getTreeViewData",
        onSelect: function (node) {
            $("#thingType").attr("value", node.attributes[0])
        }
    })

    personTitleTree.tree({
        url: "operation4PersonTitle/getTreeViewData",
        onSelect: function (node) {
            $("#personTitle").attr("value", node.attributes[0])
        }
    })

});

function deleteItem(id) {
    console.info("删除：" + id);
    ajaxExecuteWithMethod("operation4ThingTypeCircle/delete?id=" + id, 'DELETE');
    console.info("删除：" + id + "了！");
    location.reload();
}

/*
* 定位到需要编辑的记录
* */
function listToDo() {
    console.info(jsTitleThingTypeCircle + "+待完成......");
    var title = jsTitleThingTypeCircle;
    var page = 1;   //每次都定位到第一页
    var params = getParams(page, localPageSizeThingTypeCircle);    //getParams必须是放在最最前面！！
    ajaxRun("operation4ThingTypeCircle/list" + params + "&key=" + title + ".TODO", 0, "list" + title + "Div");
}

function shiftDisplay(title) {
    console.info("显示当前提示...")
    $("#currentTemplate").html("下载[" + title + "]模板");
    $("#currentTemplate").attr("href", "operation4ThingTypeCircle/downloadTemplate?key=" + title);
    $("#currentImport").html("导入[" + title + "]数据");
    $("#importKey").attr("value", title);
}

function changeUpNode(node) {
    console.info("修改根节点的id...")
    $("#createItem").attr('href', 'javascript: createItem(' + node.attributes[0] + ')');
    $("#createItem").html("创建" + node.attributes[0] + '的子节点');
    $("#editItem").attr('href', 'javascript: editItem(' + node.attributes[0] + ')');
    $("#editItem").html("编辑" + node.attributes[0] + '节点');
    $("#deleteItem").attr('href', 'operation4ThingTypeCircle/delete/' + node.attributes[0]);
    $("#deleteItem").html("删除" + node.attributes[0] + '节点');
    $("#currentTitle").html(node.text);
    ajaxRun("operation4ThingTypeCircle/show", node.attributes[0], "showInformationDiv");
}

/*
* 统计函数
* */
function countThingTypeCircle(title) {
    console.info(jsTitleThingTypeCircle + "+统计......");
    shiftDisplay(title);
    var total = ajaxCalculate("operation4ThingTypeCircle/count?key=" + title);
    return total
}

/*
* 数据加载函数
* */
function loadThingTypeCircle(title, page, pageSize) {
    console.info(jsTitleThingTypeCircle + "+数据加载......" + title + " 第" + page + "页/" + pageSize);
    var params = getParams(page, pageSize);    //getParams必须是放在最最前面！！
    ajaxRun("operation4ThingTypeCircle/list" + params + "&key=" + title, 0, "list" + title + "Div");
}