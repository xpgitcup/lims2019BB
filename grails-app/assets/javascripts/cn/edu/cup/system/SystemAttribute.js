var operation4SystemAttributeDiv;
var jsTitle = "系统属性";
var title4SystemAttribute = [jsTitle]
var isTreeView4SystemAttribute = [true]
var treeData4SystemAttribute = ["operation4SystemAttribute/getTreeViewData"]

$(function () {
    console.info(jsTitle + "......");

    operation4SystemAttributeDiv = $("#operation4SystemAttributeDiv");
    var settings = {
        divId: operation4SystemAttributeDiv,
        titles: title4SystemAttribute,
        // 有关树形结构的设置
        isTreeView: isTreeView4SystemAttribute,
        treeData: treeData4SystemAttribute,
        treeNodeDoSomeThing: systemAttributeNodeSelect, //当节点被选择
        //paginationMessage: "",
        pageList: [],
        showPageList: false,
        loadFunction: loadSystemAttribute,
        countFunction: countSystemAttribute
    }

    configDisplayUI(settings);
});

/*
* 新建
* */
function createSystemAttribute(id) {
    console.info("创建SystemAttribute. 上级节点：" + id);
    ajaxRun("operation4SystemAttribute/createSystemAttribute", id, "showSystemAttributeDiv");
}

/*
* 编辑
* */
function editSystemAttribute(id) {
    console.info("编辑SystemAttribute." + id);
    ajaxRun("operation4SystemAttribute/edit", id, "showSystemAttributeDiv");
}

/*
* 显示节点信息
* */
function showSystemAttribute(node) {
    console.info(jsTitle + "+节点显示......" + node);
    if (node) {
        var id = node.attributes[0];
        ajaxRun("operation4SystemAttribute/show", id, "showSystemAttributeDiv");
    }
}

/*
* 节点被选择。。。
* */
function systemAttributeNodeSelect(node) {
    console.info(jsTitle + "+节点选择......" + node);
    showSystemAttribute(node);
    $("#createSystemAttribute").attr('href', 'javascript: createSystemAttribute(' + node.attributes[0] + ')');
    console.info(node);
    console.info("当前节点：" + node.target.id);
    $.cookie("currentSystemAttribute", node.target.id);
}

/*
* 统计函数
* */
function countSystemAttribute(title) {
    console.info(jsTitle + "+统计......");
    var total = ajaxCalculate("operation4SystemAttribute/count?key=" + title);
    return total
}

/*
* 数据加载函数
* */
function loadSystemAttribute(title, page, pageSize) {
    console.info(jsTitle + "+数据加载......" + title + " 第" + page + "页/" + pageSize);
    var params = getParams(page, pageSize);    //getParams必须是放在最最前面！！
    ajaxRun("operation4SystemAttribute/list" + params + "&key=" + title, 0, "list" + title + "Div");
}