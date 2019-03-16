var operation4SystemUserDiv;
var jsTitle = "系统用户";
var title4SystemUser = [jsTitle]

$(function () {
    console.info(jsTitle + "......");

    operation4SystemUserDiv = $("#operation4SystemUserDiv");
    var settings = {
        divId: operation4SystemUserDiv,
        titles: title4SystemUser,
        paginationMessage: "",
        pageList: [],
        showPageList: false,
        loadFunction: loadSystemUser,
        countFunction: countSystemUser
    }

    configDisplayUI(settings);
});

/*
 * 新建
 * */
function createSystemUser(id) {
    console.info("创建SystemUser. 上级节点：" + id);
    ajaxRun("operation4SystemUser/create", id, "showSystemUserDiv");
}

/*
 * 编辑
 * */
function editSystemUser(id) {
    console.info("编辑SystemUser." + id);
    ajaxRun("operation4SystemUser/edit", id, "showSystemUserDiv");
}

/*
 * 显示当前属性
 * */
function showSystemUser(id) {
    console.info("显示当前" + id);
    if (id) {
        ajaxRun("operation4SystemUser/show", id, "showSystemUserDiv");
    }
}

/*
* 统计函数
* */
function countSystemUser(title) {
    console.info(jsTitle + "+统计......");
    var total = ajaxCalculate("operation4SystemUser/count?key=" + title);
    return total
}

/*
* 数据加载函数
* */
function loadSystemUser(title, page, pageSize) {
    console.info(jsTitle + "+数据加载......" + title + " 第" + page + "页/" + pageSize);
    var params = getParams(page, pageSize);    //getParams必须是放在最最前面！！
    ajaxRun("operation4SystemUser/list" + params + "&key=" + title, 0, "list" + title + "Div");
}