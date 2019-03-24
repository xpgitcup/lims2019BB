<!DOCTYPE html>

<div id="list-progress" class="content scaffold-list" role="main">
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <g:if test="${objectList.size() < 1}">
        <a class="create" href="javascript: createCurrentProgress()">上报进度[${team}]</a>
    </g:if>
    <g:else>
        <h1>[${objectList[0].team}]---进度列表</h1>
        <!--f:table collection="${objectList}"/-->
        <table>
            <thead>
            <td>id</td>
            <th>前情</th>
            <th>状态</th>
            <th>归档状态</th>
            </thead>
            <tbody>
            <g:each in="${objectList}" var="item" status="i">
                <tr class="${(i % 2 == 0 ? 'even' : 'odd')}">
                    <td>${item.id}</td>
                    <td>${item?.prevProgress?.currentStatus}</td>
                    <td>
                        ${item.currentStatus}
                        <g:if test="${statusList.get(item)[0] < 1}">
                            <a class="chose" href="javascript: selectProgress(${item.id})"></a>
                        </g:if>
                    </td>
                    <td>
                        ${statusList.get(item)}
                    </td>
                </tr>
            </g:each>
            </tbody>
        </table>
    </g:else>
</div>
