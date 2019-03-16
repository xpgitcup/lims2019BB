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
        <th>名称</th>
        <th>类型</th>
        <th>甲方</th>
        <th>开始</th>
        <th>延续</th>
        <td>进度</td>
        </thead>
        <tbody>
        <g:each in="${objectList}" var="item" status="i">
            <tr class="${(i % 2 == 0 ? 'even' : 'odd')}">
                <td>
                    ${item.name}
                    <g:if test="${(item.progresses?.size()<1) && (cn.edu.cup.lims.Team.countByThing(item)<1)}">
                        <a href="javascript: deleteCourse(${item.id})">删除</a>
                    </g:if>
                    <g:else>
                        <a>不能删除，相关团队：${cn.edu.cup.lims.Team.countByThing(item)}，相关进度：${item.progresses?.size()}</a>
                    </g:else>
                    <a href="javascript: editProject(${item.id})">编辑</a>
                </td>
                <td>${item.thingType}</td>
                <td>${item.companyA}</td>
                <td>${item.startDate}</td>
                <td>${item.duration}</td>
                <td>${item.progresses?.size()}</td>
            </tr>
        </g:each>
        </tbody>
    </table>
</div>
</body>
</html>