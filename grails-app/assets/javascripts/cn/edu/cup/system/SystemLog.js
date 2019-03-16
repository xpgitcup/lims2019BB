var operation4SystemLogDiv;
var jsTitle = "系统日志";
var title4SystemLog = [jsTitle]

$(function () {
    console.info(jsTitle + "......");

    operation4SystemLogDiv = $("#operation4SystemLogDiv");
    var settings = {
        divId: operation4SystemLogDiv,
        titles: title4SystemLog,
        pageSize: 10,
        loadFunction: loadSystemLog,
        countFunction: countSystemLog
    }

    configDisplayUI(settings);
});

/*
 * 新建
 * */
function createSystemLog(id) {
    console.info("创建SystemLog. 上级节点：" + id);
    ajaxRun("operation4SystemLog/createSystemLog", id, "showSystemLogDiv");
}

/*
 * 编辑
 * */
function editSystemLog(id) {
    console.info("编辑SystemLog." + id);
    ajaxRun("operation4SystemLog/edit", id, "showSystemLogDiv");
}

/*
 * 显示当前属性
 * */
function showSystemLog(id) {
    console.info("显示当前" + id);
    if (id) {
        ajaxRun("operation4SystemLog/show", id, "showSystemLogDiv");
    }
}

/*
* 统计函数
* */
function countSystemLog(title) {
    console.info(jsTitle + "+统计......");
    var total = ajaxCalculate("operation4SystemLog/count?key=" + title);
    return total
}

/*
* 数据加载函数
* */
function loadSystemLog(title, page, pageSize) {
    console.info(jsTitle + "+数据加载......" + title + " 第" + page + "页/" + pageSize);
    var params = getParams(page, pageSize);    //getParams必须是放在最最前面！！
    ajaxRun("operation4SystemLog/list" + params + "&key=" + title, 0, "list" + title + "Div");
}