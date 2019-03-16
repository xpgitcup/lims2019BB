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
    <g:set var="entityName" value="ThingTypeCircle"/>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>${entityName}维护</title>
    <asset:javascript src="cn/edu/cup/os4lims/${entityName}.js"/>
</head>

<body>

<div class="nav">
    <ul id="operation4ThingTypeCircleUl">
        <li>
            <g:form controller="operation4ThingTypeCircle" action="save">
                <label>任务</label>
                <g:textField name="thingType" value="" id="thingType"/>
                <label>人员</label>
                <g:textField name="personTitle" value="" id="personTitle"/>
                <g:submitButton name="create"/>
            </g:form>
        </li>
    </ul>
</div>

<g:if test="${flash.message}">
    <div class="message" role="status">${flash.message}</div>
</g:if>

<div id="operation4ThingTypeCircleDiv" class="easyui-panel">
</div>

<div id="edit4ThingTypeCircleDiv">
    <div class="container-fluid">
        <div class="col-md-6">
            <h1>任务类型</h1>
            <hr>
            <ul class="easyui-tree" id="thingTypeTree"></ul>
        </div>

        <div class="col-md-6">
            <h1>人员类型</h1>
            <hr>
            <ul class="easyui-tree" id="personTitleTree"></ul>
        </div>
    </div>
</div>
</body>
</html>
