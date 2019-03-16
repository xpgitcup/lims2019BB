var operation4SystemMenuDiv;
var jsTitle = "系统菜单";
var title4SystemMenu = [jsTitle]
var isTreeView4SystemMenu = [true]
var treeData4SystemMenu = ["operation4SystemMenu/getTreeViewData"]

$(function () {
    console.info(jsTitle + "......");

    operation4SystemMenuDiv = $("#operation4SystemMenuDiv");
    var settings = {
        divId: operation4SystemMenuDiv,
        titles: title4SystemMenu,
        //树形结构
        isTreeView: isTreeView4SystemMenu,
        treeData: treeData4SystemMenu,
        treeNodeDoSomeThing: showSystemMenu, //当节点被选择
        //分页设置
        paginationMessage: "",
        pageList: [],
        showPageList: false,
        loadFunction: loadSystemMenu,
        countFunction: countSystemMenu
    }

    configDisplayUI(settings);
});

/*
 * 新建
 * */
function createSystemMenu(id) {
    console.info("创建SystemMenu. 上级节点：" + id);
    ajaxRun("operation4SystemMenu/createSystemMenu", id, "showSystemMenuDiv");
}

/*
 * 编辑
 * */
function editSystemMenu(id) {
    console.info("编辑SystemMenu." + id);
    ajaxRun("operation4SystemMenu/edit", id, "showSystemMenuDiv");
}

/*
 * 显示当前属性
 * */
function showSystemMenu(node) {
    console.info("显示当前系统属性" + node);
    if (node) {
        $("#createSystemMenu").attr('href', 'javascript: createSystemMenu(' + node.attributes[0] + ')');
        var id = node.attributes[0];
        ajaxRun("operation4SystemMenu/getSystemMenu", id, "showSystemMenuDiv");
    }
}

/*
* 统计函数
* */
function countSystemMenu(title) {
    console.info(jsTitle + "+统计......");
    var total = ajaxCalculate("operation4SystemMenu/count?key=" + title);
    return total
}

/*
* 数据加载函数
* */
function loadSystemMenu(title, page, pageSize) {
    console.info(jsTitle + "+数据加载......" + title + " 第" + page + "页/" + pageSize);
    var params = getParams(page, pageSize);    //getParams必须是放在最最前面！！
    ajaxRun("operation4SystemMenu/list" + params + "&key=" + title, 0, "list" + title + "Div");
}