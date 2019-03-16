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
    <g:set var="entityName" value="QueryStatement"/>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>${entityName}维护</title>
    <asset:javascript src="cn/edu/cup/system/${entityName}.js"/>
</head>

<body>

<div class="nav">
    <ul id="operation4QueryStatementUl">
        <li></li>
        <li>
            <a href="operation4QueryStatement/exportToJsonFile?fileName=systemConfig/queryStatement.json" class="save">导出Json文件</a>
        </li>
        <li>
            <a href="operation4QueryStatement/importFromJsonFile?fileName=systemConfig/queryStatement.json" class="create">从Json文件导入</a>
        </li>
        <li>
            <a id="filter" href="javascript: listToDo()">待编辑&完成</a>
        </li>
        <li>
            <a href="javascript: clearFilter()">清除筛选条件</a>
        </li>
        <li>
            <g:form onsubmit="return queryStatement()">
                <label>关键字</label>
                <input type="text" id="keyString" name="keyString" value=""/>
                <input type="submit" name="查询" value="查询">
            </g:form>
        </li>
    </ul>
</div>

<g:if test="${flash.message}">
    <div class="message" role="status">${flash.message}</div>
</g:if>

<div id="operation4QueryStatementDiv" class="easyui-panel">
</div>
</body>
</html>
