<!--
  To change this license header, choose License Headers in Project Properties.
  To change this template file, choose Tools | Templates
  and open the template in the editor.
-->

<%@ page contentType="text/html;charset=UTF-8" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="layout" content="welcome"/>
    <title>退出</title>
</head>

<body>
<div class="welcomePanel">
    <div class="title">
        ${cn.edu.cup.system.SystemTitle.last()?.applicationTitle}
    </div>

    <div class="welcomeForm">
        <ul>
            <li>
                <label>欢迎：${logoutUser} 再来!</label>
            </li>
        </ul>
        <ul>
            <li style="padding-left: 100px">
                <a href="${createLink(uri: '/home/loginUI')}">再次登录</a>
            </li>
        </ul>
    </div>
</div>
</body>
</html>
