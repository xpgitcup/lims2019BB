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
    <title><g:message code="default.edit.label" args="[entityName]"/></title>
</head>

<body>
<a href="#edit-queryStatement" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                                     default="Skip to content&hellip;"/></a>

<div id="edit-queryStatement" class="content scaffold-edit" role="main">
    <h1><g:message code="default.edit.label" args="[entityName]"/></h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <g:hasErrors bean="${this.queryStatement}">
        <ul class="errors" role="alert">
            <g:eachError bean="${this.queryStatement}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message
                        error="${error}"/></li>
            </g:eachError>
        </ul>
    </g:hasErrors>
    <!--g:form resource="${this.queryStatement}" method="PUT"-->
        <g:form controller="operation4QueryStatement" action="update" id="${this.queryStatement.id}" method="PUT">
        <g:hiddenField name="version" value="${this.queryStatement?.version}"/>
        <fieldset class="form">
            <!--f:all bean="queryStatement"/-->
            <table>
            <tr>
                <td>关键字</td>
                <td><g:textField name="keyString" value="${queryStatement.keyString}" style="width: 50em"></g:textField></td>
            </tr>
            <tr>
                <td>查询语句</td>
                <td><g:textField name="hql" value="${queryStatement.hql}" style="width: 80em"></g:textField></td>
            </tr>
            <tr>
                <td>SQL查询语句</td>
                <td><g:textField name="isSQL" value="${queryStatement.isSQL}"></g:textField></td>
            </tr>
            <tr>
                <td>参数</td>
                <td><g:textField name="paramsList" value="${queryStatement.paramsList}" style="width: 80em"></g:textField></td>
            </tr>
            <tr>
                <td>视图</td>
                <td><g:textField name="viewName" value="${queryStatement.viewName}"></g:textField></td>
            </tr>
            <tr>
                <td>是否需要执行</td>
                <td><g:textField name="needToQuery" value="${queryStatement.needToQuery}"></g:textField></td>
            </tr>
            </table>
        </fieldset>
        <fieldset class="buttons">
            <input class="save" type="submit"
                   value="${message(code: 'default.button.update.label', default: 'Update')}"/>
            <a href="operation4QueryStatement/index">返回</a>
        </fieldset>
    </g:form>
</div>
</body>
</html>
