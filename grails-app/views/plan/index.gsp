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
        <g:set var="entityName" value="${message(code: 'plan.label', default: 'Plan')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#list-plan" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="list-plan" class="content scaffold-list" role="main">
            <h1><g:message code="default.list.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>
            <f:table collection="${planList}" />

            <div class="paginationGrails">
                <g:paginate total="${planCount ?: 0}" />
            </div>
        </div>
    </body>
</html>