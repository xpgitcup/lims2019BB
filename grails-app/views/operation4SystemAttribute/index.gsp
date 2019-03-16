<!--
  To change this license header, choose License Headers in Project Properties.
  To change this template file, choose Tools | Templates
  and open the template in the editor.
-->

<%@ page contentType="text/html;charset=UTF-8" %>

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
    <g:set var="entityName" value="SystemAttribute"/>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>${entityName}维护</title>
    <asset:javascript src="cn/edu/cup/system/${entityName}.js"/>
</head>

<body>

<div class="container-fluid">
    <div class="row-fluid">
        <div class="col-md-6">
            <div class="panel panel-default">
                <div class="nav">
                    <ul>
                        <li>
                            <a class="list">
                                系统属性维护——(重新登录后，更新)
                            </a>
                        </li>
                    </ul>
                </div>

                <div id="operation4SystemAttributeDiv" class="easyui-panel">
                </div>
            </div>
        </div>

        <div class="col-md-6">
            <div class="panel panel-default">
                <div class="nav" role="navigation">
                    <ul>
                        <li><a class="create" href="javascript: createSystemAttribute(0)">新建根节点</a></li>
                        <li><a id="createSystemAttribute" class="create" href="#">新建子节点</a></li>
                    </ul>
                </div>

                <div class="easyui-panel">
                    <div id="showSystemAttributeDiv"></div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
