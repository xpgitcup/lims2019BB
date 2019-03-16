var operation4ProgressDiv;
var operation4ProgressUl;
var jsTitleProgress = ["我管理的", "我领导的", "我参与的", "进度查看"];
var title4Progress = jsTitleProgress;
var tabsTitle = "任务管理";
var localPageSizeProgress = 5;
var tipsOperation4Progress;
var currentSelect;

$(function () {
    console.info(jsTitleProgress + "......");

    tipsOperation4Progress = $("#tipsOperation4Progress");
    operation4ProgressUl = $("#operation4ProgressUl");
    operation4ProgressDiv = $("#operation4ProgressDiv");
    currentSelect = $("#currentSelect");

    var settings = {
        divId: operation4ProgressDiv,
        titles: title4Progress,
        tabsTitle: tabsTitle,
        pageSize: localPageSizeProgress,
        pageList: [1, 3, 5, 10],
        loadFunction: loadProgress,
        countFunction: countProgress
    }

    configDisplayUI(settings);

    setupDisplayUl(operation4ProgressUl, jsTitleProgress);
    reflashDisplayUl(operation4ProgressUl, jsTitleProgress);

});

function updateUploadFileName(fileName) {
    var aainput = document.getElementById("uploadedFile");
    $("#supportFileName").attr("value", aainput.files[0].name);
    console.info("所选择的文件：" + fileName);
    console.info(aainput.files[0].name)
}

function createNextProgress(id) {
    console.info("创建新进度...")
    ajaxRun("operation4Progress/createNextProgress", id, "editProgressDiv");
}

function createCurrentProgress() {
    var key = "currentKey当前团队";
    var id = readCookie(key, 0);
    ajaxRun("operation4Progress/createProgress", id, "editProgressDiv");
}

function createProgress(id) {
    console.info("创建新进度...")
    ajaxRun("operation4Progress/createProgress", id, "editProgressDiv");
    //checkProgress(id);
}

function checkEvaluate(id) {
    selectCurrentItem(id)
    operation4ProgressDiv.tabs("select", "反馈信息");
}

function checkProgress(id) {
    selectCurrentItem(id)
    operation4ProgressDiv.tabs("select", "进度查看");
}

function selectCurrentItem(id) {
    var title = getCurrentTabTitle(operation4ProgressDiv)
    $.cookie("currentKey" + title, id);
    switch (title) {
        case "我管理的":
            $.cookie("currentKey" + "当前团队", id);
            break
        case "我领导的":
            $.cookie("currentKey" + "当前团队", id);
            break
        case "我参与的":
            $.cookie("currentKey" + "当前团队", id);
            break
    }
    location.reload();
}

/*
* 在这里构建参数
* */
function shiftDisplay(title) {
    console.info("显示当前提示...")
    var key = "currentKey";
    var id;
    var param = ""
    switch (title) {
        case "我领导的":
            tipsOperation4Progress.html("可以选择团队！");
            currentSelect.html(title);
            break;
        case "我参与的":
            tipsOperation4Progress.html("可以选择团队！");
            currentSelect.html(title);
            break;
        case "进度查看":
            key += "当前团队";
            id = readCookie(key, 0);
            console.info("当前id:" + key + "=" + id);
            if (id > 0) {
                param = "&currentTeam=" + id;
                tipsOperation4Progress.html("查看当前团队：");
            } else {
                tipsOperation4Progress.html("请先选择团队！");
            }
            break
        case "反馈信息":
            key += "进度查看";
            id = readCookie(key, 0);
            console.info("当前id:" + key + "=" + id);
            if (id > 0) {
                param = "&currentProgress=" + id;
                tipsOperation4Progress.html("查看当前进度：");
            } else {
                tipsOperation4Progress.html("请先选择进度！");
            }
            break
    }
    return param
}

/*
* 统计函数
* */
function countProgress(title) {
    console.info(jsTitleProgress + "+统计......");
    var append = shiftDisplay(title);
    var total = ajaxCalculate("operation4Progress/count?key=" + title + append);
    return total
}

/*
* 数据加载函数
* */
function loadProgress(title, page, pageSize) {
    console.info(jsTitleProgress + "+数据加载......" + title + " 第" + page + "页/" + pageSize);
    var append = shiftDisplay(title);
    var params = getParams(page, pageSize);    //getParams必须是放在最最前面！！
    ajaxRun("operation4Progress/list" + params + "&key=" + title + append, 0, "list" + title + "Div");
}