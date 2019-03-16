<!DOCTYPE html>
<html>
<head>
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
<!--meta name="layout" content="main" /-->
    <g:set var="entityName" value="${message(code: 'queryStatement.label', default: 'QueryStatement')}"/>
    <title><g:message code="default.list.label" args="[entityName]"/></title>
</head>

<body>
<div id="list-queryStatement" class="content scaffold-list" role="main">
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
<!--f:table collection="${objectList}"/-->
    <table>
        <thead>
        <th>关键字</th>
        <th>HQL</th>
        <th>属性</th>
        <th>视图</th>
        <th>参数</th>
        <th>执行</th>
        </thead>
        <tbody>
        <g:each in="${objectList}" var="item" status="i">
            <tr class="${(i % 2 == 0) ? 'even' : 'odd'}">
                <td>
                    ${item.keyString}
                    <a href="operation4QueryStatement/edit/${item.id}">编辑</a>
                    <a href="javascript: deleteItem(${item.id})">删除</a>
                </td>
                <td>${item.hql}</td>
                <td>${item.isSQL}</td>
                <td>${item.viewName}</td>
                <td>${item.paramsList}</td>
                <td>${item.needToQuery}</td>
            </tr>
        </g:each>
        </tbody>
    </table>
</div>
</body>
</html>