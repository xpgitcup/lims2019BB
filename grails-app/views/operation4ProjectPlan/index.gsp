<!--
  To change this license header, choose License Headers in Project Properties.
  To change this template file, choose Tools | Templates
  and open the template in the editor.
-->

<%@ page contentType="text/html;charset=UTF-8" %>

<html>
<head>
<!--meta name="layout" content="main"/-->
<!-- 实现可定制的布局 -->
    <g:if test="${layout}">
        <meta name="layout" content="${layout}"/>
    </g:if>
    <g:else>
        <g:if test="${session.layout}">
            <meta name="layout" content="${session.layout}"/>
        </g:if>
        <g:else>
            <meta name="layout" content="main"/>
        </g:else>
    </g:else>
<!-- end 实现可定制的布局 -->
<!--这个地方设置成固定的-->
    <g:set var="entityName" value="进度归档"/>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>${entityName}维护</title>
    <asset:javascript src="cn/edu/cup/os4lims/projectPlan.js"/>
</head>

<body>

<div class="nav">
    <ul>
        <li class="icon-help">&nbsp&nbsp&nbsp&nbsp：</li>
        <li id="projectSelect">选择团队</li>
        <li>==></li>
        <li id="progressOperation">进度归档</li>
        <li>
            <a href="javascript: clearTeam()">重新选择</a>
        </li>
    </ul>
</div>

<g:if test="${flash.message}">
    <div class="message" role="status">${flash.message}</div>
</g:if>

<div class="container-fluid">
    <div id="teamListDiv">
        <!--当前项目-->
        <div id="operation4ProjectPlanTeamListDiv" class="easyui-panel"></div>
        <div id="pagination4ProjectPlanTeamListDiv" class="easyui-pagination"></div>
    </div>

    <div id="projectPlanDiv">
        <!--左面显示类型-->
        <div class="col-md-4">
            <div class="easyui-panel">
                <!--这是进度计划的树形结构-->
                <ul id="operation4ProjectPlanUL" class="easyui-tree"></ul>
            </div>
        </div>
        <!--右边显示计划-->
        <div class="col-md-8">
            <div id="operation4ProjectPlanProgressListDiv" class="easyui-panel"></div>

            <div id="pagination4ProjectPlanProgressListDiv" class="easyui-pagination"></div>
        </div>
    </div>
</div>

</body>
</html>
