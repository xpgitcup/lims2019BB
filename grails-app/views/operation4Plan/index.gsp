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
    <g:set var="entityName" value="${planTitle}"/>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>${entityName}维护</title>
    <asset:javascript src="cn/edu/cup/os4lims/${planJsRoutine}.js"/>
</head>

<body>

<div class="nav">
    <ul id="operation4PlanUl">
        <li class="icon-help">&nbsp&nbsp&nbsp&nbsp：</li>
        <li id="tipsOperation4Progress"></li>
        <li>当前：${planTitle}.${planJsRoutine}</li>
        <li id="currentTitle"></li>
        <li><a id="createItem"></a></li>
        <li><a id="editItem"></a></li>
    </ul>
</div>

<g:if test="${flash.message}">
    <div class="message" role="status">${flash.message}</div>
</g:if>

<div class="container-fluid">
    <!--左面显示类型-->
    <div class="col-md-6">
        <div class="easyui-panel">
            <ul id="operation4ThingTypeUL" class="easyui-tree"></ul>
        </div>
    </div>
    <!--右边显示计划-->
    <div class="col-md-6">
        <div id="operation4PlanDiv" class="easyui-panel">
        </div>
    </div>
</div>

</body>
</html>
