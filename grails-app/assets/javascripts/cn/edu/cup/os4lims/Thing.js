var operation4ThingDiv;
var jsTitleThing = ["科研", "教学"];
var title4Thing = jsTitleThing;
var tabsTitle = "项目数据";
var localPageSizeThing = 10;

$(function () {
    console.info(jsTitleThing + "......");

    operation4ThingDiv = $("#operation4ThingDiv");
    var settings = {
        divId: operation4ThingDiv,
        titles: title4Thing,
        tabsTitle: tabsTitle,
        pageSize: localPageSizeThing,
        pageList: [1, 3, 5, 10],
        loadFunction: loadThing,
        countFunction: countThing
    }

    configDisplayUI(settings);
});

function shiftDisplay(title) {
    console.info("显示当前提示...")
    $("#currentTemplate").html("下载[" + title + "]模板");
    $("#currentTemplate").attr("href", "operation4Thing/downloadTemplate?key=" + title);
    $("#currentImport").html("导入[" + title + "]数据");
    $("#importKey").attr("value", title);
    $("#createItem").html("创建：" + title + " 数据");
    switch (title) {
        case "科研":
            $("#createItem").attr("href", "javascript: createProject('" + title + "')");
            break
        case "教学":
            $("#createItem").attr("href", "javascript: createCourse('" + title + "')");
            break
    }
}

function editProject(id) {
    console.info("编辑项目信息...");
    var title = getCurrentTabTitle(operation4ThingDiv);
    ajaxRun("operation4Thing/editProject", id, "list" + title + "Div");
}

function createProject(title) {
    console.info("创建教学任务...");
    //var title = getCurrentTabTitle(operation4ThingDiv);
    ajaxRun("operation4Thing/createProject", 0, "list" + title + "Div");
}

function createCourse(title) {
    console.info("创建教学任务...");
    //var title = getCurrentTabTitle(operation4ThingDiv);
    ajaxRun("operation4Thing/createCourse", 0, "list" + title + "Div");
}

function deleteCourse(id) {
    console.info("删除教学任务：" + id);
    ajaxExecuteWithMethod("operation4Thing/delete?id=" + id, 'DELETE');
    console.info("删除：" + id + "了！");
    location.reload();
}

/*
* 定位到需要编辑的记录
* */
function listToDo() {
    console.info(jsTitleThing + "+待完成......");
    var title = jsTitleThing;
    var page = 1;   //每次都定位到第一页
    var params = getParams(page, localPageSizeThing);    //getParams必须是放在最最前面！！
    ajaxRun("operation4Thing/list" + params + "&key=" + title + ".TODO", 0, "list" + title + "Div");
}

/*
* 统计函数
* */
function countThing(title) {
    console.info(jsTitleThing + "+统计......");
    shiftDisplay(title);
    var total = ajaxCalculate("operation4Thing/count?key=" + title);
    return total
}

/*
* 数据加载函数
* */
function loadThing(title, page, pageSize) {
    console.info(jsTitleThing + "+数据加载......" + title + " 第" + page + "页/" + pageSize);
    var params = getParams(page, pageSize);    //getParams必须是放在最最前面！！
    ajaxRun("operation4Thing/list" + params + "&key=" + title, 0, "list" + title + "Div");
}