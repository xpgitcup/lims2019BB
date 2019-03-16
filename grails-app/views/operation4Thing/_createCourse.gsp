<g:form id="${this.course.id}" controller="operation4Thing" action="saveCourse" method="POST">
    <fieldset class="form">
        <f:all bean="course"/>
        <label>名称</label>
        <g:field type="text" name="name"/>
        <label>教师</label>
        <g:field type="text" name="teacher" value="${course.teacher}"/>
    </fieldset>
    <fieldset class="buttons">
        <g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" />
    </fieldset>
</g:form>
