<div id="create-progress" class="content scaffold-create" role="main">
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <g:hasErrors bean="${this.progress}">
        <ul class="errors" role="alert">
            <g:eachError bean="${this.progress}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message
                        error="${error}"/></li>
            </g:eachError>
        </ul>
    </g:hasErrors>
    <g:uploadForm id="${this.progress.id}" controller="operation4Progress" action="save" method="POST">
        <fieldset class="form">
            <!--f:all bean="progress"/-->
            <div class="fieldcontain ${hasErrors(bean: this.progress, field: 'prevProgress', 'error')} ">
                <label for="prevProgress">
                    <g:message code="progress.prevProgress.label" default="前情回顾"/>
                </label>
                <g:select id="prevProgress"
                          name="prevProgress.id"
                          from="${cn.edu.cup.lims.Progress.list()}"
                          optionKey="id" value="${this.progress?.prevProgress?.id}" class="many-to-one"
                          noSelection="['null': '']"/>

            </div>

            <div class="fieldcontain ${hasErrors(bean: this.progress, field: 'currentStatus', 'error')} required">
                <label for="currentStatus">
                    <g:message code="progress.currentStatus.label" default="本次进展"/>
                    <span class="required-indicator">*</span>
                </label>
                <g:textField name="currentStatus" required="" value="${this.progress?.currentStatus}"/>

            </div>

            <div class="fieldcontain ${hasErrors(bean: this.progress, field: 'problemEncounter', 'error')} ">
                <label for="problemEncounter">
                    <g:message code="progress.problemEncounter.label" default="发现的问题"/>

                </label>
                <g:textField name="problemEncounter" value="${this.progress?.problemEncounter}"/>

            </div>

            <div class="fieldcontain ${hasErrors(bean: this.progress, field: 'supportFileName', 'error')} ">
                <label for="supportFileName">
                    <g:message code="progress.supportFileName.label" default="支撑文件"/>
                </label>
                <!--g:textField name="supportFileName" value="${this.progress?.supportFileName}"/-->
                <input type="text" name="supportFileName" id="supportFileName"
                       value="${this.progress?.supportFileName}"/>
                <input type="file" name="uploadedFile" id="uploadedFile" onchange="updateUploadFileName(this.value)"/>
            </div>

            <div class="fieldcontain ${hasErrors(bean: this.progress, field: 'contributor', 'error')} required">
                <label for="contributor">
                    <g:message code="progress.contributor.label" default="Contributor"/>
                    <span class="required-indicator">*</span>
                </label>
                <g:select id="contributor" name="contributor.id" from="${cn.edu.cup.lims.Person.list()}" optionKey="id"
                          required="" value="${this.progress?.contributor?.id}" class="many-to-one"/>

            </div>

            <div class="fieldcontain ${hasErrors(bean: this.progress, field: 'regDate', 'error')} required">
                <label for="regDate">
                    <g:message code="progress.regDate.label" default="时间戳"/>
                    <span class="required-indicator">*</span>
                </label>
                <g:datePicker name="regDate" value="${this.progress?.regDate}"/>
            </div>

            <div class="fieldcontain ${hasErrors(bean: this.progress, field: 'team', 'error')} required">
                <label for="team">
                    <g:message code="progress.team.label" default="Team"/>
                    <span class="required-indicator">*</span>
                </label>
                <g:select id="team" name="team.id" from="${cn.edu.cup.lims.Team.list()}" optionKey="id" required=""
                          value="${this.progress?.team?.id}" class="many-to-one"/>

            </div>

        </fieldset>

        <fieldset class="buttons">
            <g:submitButton name="create" class="save"
                            value="${message(code: 'default.button.create.label', default: 'Create')}"/>
        </fieldset>
    </g:uploadForm>
</div>
