<%@ page import="cn.edu.cup.lims.Team" %>
<div id="list-teacher" class="content scaffold-list" role="main">
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <!--f:table collection="${objectList}"/-->
    <table>
        <thead>
        <th>名称</th>
        <th>教师</th>
        <th>学年</th>
        <th>类型</th>
        <th>进度</th>
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
                </td>
                <td>${item.teacher}</td>
                <td>${item.schoolYear}</td>
                <td>${item.thingType}</td>
                <td>${item.progresses?.size()}</td>
            </tr>
        </g:each>
        </tbody>
    </table>
</div>