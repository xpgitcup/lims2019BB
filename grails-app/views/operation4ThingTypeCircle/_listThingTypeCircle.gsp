<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'thingTypeCircle.label', default: 'ThingTypeCircle')}"/>
    <title><g:message code="default.list.label" args="[entityName]"/></title>
</head>

<body>
<div id="list-thingTypeCircle" class="content scaffold-list" role="main">
    <table>
        <thead>
        <th>任务</th>
        <th>子任务</th>
        <th>人员</th>
        </thead>
        <tbody>
        <g:each in="${objectList}" status="i" var="item">
            <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
                <td>
                    ${item.thingType}
                    <a href="javascript: deleteItem(${item.id})">删除</a>
                </td>
                <td>${item.thingType.relatedThingTypeList()}</td>
                <td>${item.personTitle}</td>
            </tr>
        </g:each>
        </tbody>
    </table>
</div>
</body>
</html>