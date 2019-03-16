<%@ page import="cn.edu.cup.lims.Team" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'teacher.label', default: 'Project')}"/>
    <title><g:message code="default.list.label" args="[entityName]"/></title>
</head>

<body>
<div id="list-teacher" class="content scaffold-list" role="main">
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <!--f:table collection="${objectList}"/-->
    <table>
        <thead>
        <th>任务名称</th>
        <th>任务类型</th>
        <th>相关团队数</th>
        </thead>
        <tbody>
        <g:each in="${objectList}" var="item" status="i">
            <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
                <td>
                    ${item.name}
                    <a href="javascript: createTeam(${item.id})">创建团队</a>
                    <a href="javascript: listTeam(${item.id})">查看团队</a>
                </td>
                <td>${item.thingType}</td>
                <td>${cn.edu.cup.lims.Team.countByThing(item)}</td>
            </tr>
        </g:each>
        </tbody>
    </table>
</div>
</body>
</html>