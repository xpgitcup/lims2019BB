var operation4PersonDiv;
var jsTitlePerson = ["教师", "学生", "专业", "年级"];
var title4Person = jsTitlePerson;
var tabsTitle = "人员维护";
var localPageSizePerson = 10;

$(function () {
    console.info(jsTitlePerson + "......");

    operation4PersonDiv = $("#operation4PersonDiv");
    var settings = {
        divId: operation4PersonDiv,
        titles: title4Person,
        tabsTitle: tabsTitle,
        pageSize: localPageSizePerson,
        pageList: [1, 3, 5, 10],
        loadFunction: loadPerson,
        countFunction: countPerson
    }

    configDisplayUI(settings);
});

/*
* 定位到需要编辑的记录
* */
function listToDo() {
    console.info(jsTitlePerson + "+待完成......");
    var title = jsTitlePerson;
    var page = 1;   //每次都定位到第一页
    var params = getParams(page, localPageSizePerson);    //getParams必须是放在最最前面！！
    ajaxRun("operation4Person/list" + params + "&key=" + title + ".TODO", 0, "list" + title + "Div");
}

function shiftDisplay(title) {
    console.info("显示当前提示...")
    $("#currentTemplate").html("下载[" + title + "]模板");
    $("#currentTemplate").attr("href", "operation4Person/downloadTemplate?key=" + title);
    $("#currentImport").html("导入[" + title + "]数据");
    $("#importKey").attr("value", title);
    $("#imputManual").html("手输[" + title + "]");
    switch (title) {
        case "教师":
            //$("#inputTeacher").style.display = "block";
            //$("#inputTeacher").className = ""; //不起作用
            $("#inputTeacher").attr("class", "")
            $("#inputStudent").attr("class", "hidden")
            console.info("----显示" + title);
            break
        case "学生":
            $("#inputTeacher").attr("class", "hidden")
            $("#inputStudent").attr("class", "")
            break
        default:
            $("#inputTeacher").attr("class", "hidden")
            $("#inputStudent").attr("class", "hidden")
    }
}

/*
* 统计函数
* */
function countPerson(title) {
    console.info(jsTitlePerson + "+统计......");
    shiftDisplay(title);
    var total = ajaxCalculate("operation4Person/count?key=" + title);
    return total
}

/*
* 数据加载函数
* */
function loadPerson(title, page, pageSize) {
    console.info(jsTitlePerson + "+数据加载......" + title + " 第" + page + "页/" + pageSize);
    var params = getParams(page, pageSize);    //getParams必须是放在最最前面！！
    ajaxRun("operation4Person/list" + params + "&key=" + title, 0, "list" + title + "Div");
}