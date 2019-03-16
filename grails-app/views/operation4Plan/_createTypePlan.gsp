<!DOCTYPE html>

<div id="create-plan" class="content scaffold-create" role="main">
    <h1><g:message code="default.create.label" args="[entityName]" /></h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <g:hasErrors bean="${this.plan}">
        <ul class="errors" role="alert">
            <g:eachError bean="${this.plan}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
            </g:eachError>
        </ul>
    </g:hasErrors>
    <g:form id="${this.plan.id}" action="save" controller="${params.controller}" method="POST">
        <fieldset class="form">
            <f:all bean="plan"/>
            <g:hiddenField name="nextController" value="${params.nextController}"/>
            <g:hiddenField name="nextAction" value="${params.nextAction}"/>
        </fieldset>
        <fieldset class="buttons">
            <g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" />
        </fieldset>
    </g:form>
</div>
