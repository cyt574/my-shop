<%--
  Created by IntelliJ IDEA.
  User: chenyt
  Date: 2019/9/16
  Time: 20:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<header class="main-header">
    <a href="index2.html" class="logo">
        <!-- mini logo for sidebar mini 50x50 pixels -->
        <span class="logo-mini"><b>SH</b>OP</span>
        <!-- logo for regular state and mobile devices -->
        <span class="logo-lg"><b>我的</b>商城</span>
    </a>

    <nav class="navbar navbar-static-top">
        <!-- Sidebar toggle button-->
        <a href="#" class="sidebar-toggle" data-toggle="push-menu" role="button">
            <span class="sr-only">Toggle navigation</span>
        </a>

        <div class="navbar-custom-menu">
            <ul class="nav navbar-nav">
                <!-- User Account: style can be found in dropdown.less -->
                <li class="dropdown user user-menu">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        <img src="/static/assets/img/user2-160x160.jpg" class="user-image" alt="User Image">
                        <span class="hidden-xs">${user.email}</span>
                    </a>
                    <ul class="dropdown-menu">
                        <!-- User image -->
                        <li class="user-header">
                            <img src="/static/assets/img/user2-160x160.jpg" class="img-circle" alt="User Image">

                            <p>
                                ${user.username} - JavaWeb Developer
                                <small><fmt:formatDate value="${user.created}" pattern="yyyy-MM-dd HH:mm:ss"/></small>
                            </p>
                        </li>
                        <!-- Menu Body -->

                        <!-- Menu Footer-->
                        <li class="user-footer">
                            <div class="pull-left">
                                <a href="#" class="btn btn-default btn-flat">个人信息</a>
                            </div>
                            <div class="pull-right">
                                <a href="/logout" class="btn btn-default btn-flat">注销</a>
                            </div>
                        </li>
                    </ul>
                    <!-- Control Sidebar Toggle Button -->
                <li>
            </ul>
        </div>
    </nav>
</header>
<!-- Left side column. contains the logo and sidebar -->