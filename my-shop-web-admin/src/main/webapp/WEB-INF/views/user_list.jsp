<%--
  Created by IntelliJ IDEA.
  User: chenyt
  Date: 2019/9/16
  Time: 20:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ taglib prefix="sys" tagdir="/WEB-INF/tags/sys" %>
<!DOCTYPE html>
<html>
<head>

    <title>我的商城 | 用户管理</title>
    <jsp:include page="../includes/header.jsp"/>

</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
    <jsp:include page="../includes/nav.jsp"/>
    <jsp:include page="../includes/menu.jsp"/>
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>
                用户管理
                <small></small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="#"><i class="fa fa-dashboard"></i> 主页</a></li>
                <li class="active">用户管理</li>
            </ol>
        </section>

        <!-- Main content -->
        <section class="content">
            <c:if test="${baseResult!=null}">
                <div class="alert alert-${baseResult.status == 200?"success":"danger"} alert-dismissible">
                    <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                    <h4><i class="icon fa fa-warning"></i> 警告!</h4>
                        ${baseResult.message}
                </div>

            </c:if>

            <div class="box box-info box-info-search" style="display: none">
                <div class="box-header with-border">
                    <h3 class="box-title">高级搜索</h3>
                </div>

                <div class="box-body">
                    <div class="row form-horizontal">
                        <div class="col-xs-3">
                            <div class="form-group">
                                <label for="username" class="col-sm-2 control-label">姓名</label>
                                <div class="col-sm-8">
                                    <input id="username" class="form-control" placeholder="请输入姓名">
                                </div>
                            </div>
                        </div>
                        <div class="col-xs-3">
                            <div class="form-group">
                                <label for="email" class="col-sm-2 control-label">邮箱</label>
                                <div class="col-sm-8">
                                    <input id="email" class="form-control" placeholder="请输入邮箱">
                                </div>
                            </div>
                        </div>
                        <div class="col-xs-3">
                            <div class="form-group">
                                <label for="phone" class="col-sm-2 control-label">手机</label>
                                <div class="col-sm-8">
                                    <input id="phone" class="form-control" placeholder="请输入手机">
                                </div>
                            </div>
                        </div>
                        <div class="row" style="padding-right: 85px">
                            <div class="col-xs-12">

                                <button type="button" class="btn btn-info pull-right" onclick="search();">搜索</button>
                            </div>
                        </div>

                    </div>
                </div>

                <!-- /.box-header -->
                <!-- form start -->
                <%--<form:form action="/user/search" cssClass="form-horizontal" method="post" modelAttribute="tbUser">--%>
                <%--      <div class="col-xs-3">
                          <div class="form-group">
                              <label for="username" class="col-sm-2 control-label">姓名</label>
                              <div class="col-sm-8">
                                  <form:input path="username" cssClass="form-control required" placeholder="请输入姓名"/>
                              </div>
                          </div>
                      </div>
                      <div class="col-xs-3">
                          <div class="form-group">
                              <label for="email" class="col-sm-2 control-label">邮箱</label>
                              <div class="col-sm-8">
                                  <form:input path="email" cssClass="form-control required email" placeholder="请输入邮箱"/>
                              </div>
                          </div>
                      </div>
                      <div class="col-xs-3">
                          <div class="form-group">
                              <label for="phone" class="col-sm-2 control-label">手机</label>
                              <div class="col-sm-8">
                                  <form:input path="phone" cssClass="form-control required mobile" placeholder="请输入手机"/>
                              </div>
                          </div>
                      </div>
                      <div class="row" style="padding-right: 85px">
                          <div class="col-xs-12">
                              <button type="submit" class="btn btn-info pull-right">搜索</button>
                          </div>
                      </div>

                  </form:form>--%>

            </div>

            <div class="box">
                <div class="box-header">
                    <h3 class="box-title">用户信息列表</h3>
                </div>
                <div class="box-body">
                    <a href="/user/form" type="button" class="btn btn-sm btn-default"><i class="fa fa-plus"></i>新增</a>&nbsp;&nbsp;&nbsp;
                    <button type="button" class="btn btn-sm btn-default" onclick="App.deleteMulti('/user/delete')"><i
                            class="fa fa-trash"></i>删除
                    </button>&nbsp;&nbsp;&nbsp;
                    <a type="button" class="btn btn-sm btn-default"><i class="fa fa-upload"></i>导入</a>&nbsp;&nbsp;&nbsp;
                    <a type="button" class="btn btn-sm btn-default"><i class="fa fa-download"></i>导出</a>&nbsp;&nbsp;&nbsp;
                    <button type="button" class="btn btn-sm btn-primary"
                            onclick="$('.box-info-search').css('display') == 'none'?$('.box-info-search').show('fast'):$('.box-info-search').hide('slow')">
                        <i class="fa fa-search"></i>搜索
                    </button>
                </div>
                <!-- /.box-header -->
                <div class="box-body table-responsive">
                    <table class="table table-hover" id="dataTable">
                        <thead>
                        <tr>
                            <th><input type="checkbox" class="minimal checkbox-master"></th>
                            <th>ID</th>
                            <th>用户名</th>
                            <th>手机号</th>
                            <th>邮箱</th>
                            <th>更新时间</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody>

                        <%--                        <c:forEach items="${tbUsers}" var="tbUser">
                                                    <tr>
                                                        <td><input id="${tbUser.id}" type="checkbox" class="minimal"></td>
                                                        <td>${tbUser.id}</td>
                                                        <td>${tbUser.phone}</td>
                                                        <td>${tbUser.username}</td>
                                                        <td>${tbUser.email}</td>
                                                        <td><fmt:formatDate value="${tbUser.updated}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                                                        <td>
                                                            <a href="#" type="button" class="btn btn-sm btn-default"><i class="fa fa-search"></i>查看</a>&nbsp;&nbsp;&nbsp;
                                                            <a href="#" type="button" class="btn btn-sm btn-primary"><i class="fa fa-edit"></i>编辑</a>&nbsp;&nbsp;&nbsp;
                                                            <a href="#" type="button" class="btn btn-sm btn-danger" onclick="check()"><i class="fa fa-trash"></i>删除</a>&nbsp;&nbsp;&nbsp;
                                                        </td>
                                                    </tr>
                                                </c:forEach>--%>
                        </tbody>
                    </table>
                </div>
                <!-- /.box-body -->
            </div>
            <!-- /.box -->
        </section>


    </div>

    <jsp:include page="../includes/copyright.jsp"/>
</div>


<jsp:include page="../includes/footer.jsp"/>
<sys:modal msg="第一个模态框" url="http://www.chenyt.xyz"/>
<script>

    var _dataTable = null;

    $(function () {
        var columns = [
            {
                "data": function (row, type, val, meta) {
                    return '<input id="' + row.id + '" type="checkbox" class="minimal"/>';
                }
            },
            {"data": "id"},
            {"data": "username"},
            {"data": "phone"},
            {"data": "email"},
            {"data": "updated"},
            {
                "data": function (row, type, val, meta) {
                    var detailUrl = "/user/detail?id=" + row.id;
                    return '                                    <button onclick="App.showDetail(\'' + detailUrl + '\');" type="button" class="btn btn-sm btn-default"><i class="fa fa-search"></i>查看</button>&nbsp;&nbsp;&nbsp;\n' +
                        '                                    <a href="/user/form?id=' + row.id + '" type="button" class="btn btn-sm btn-primary"><i class="fa fa-edit"></i>编辑</a>&nbsp;&nbsp;&nbsp;\n' +
                        '                                    <a href="#" type="button" class="btn btn-sm btn-danger" onclick="check()"><i class="fa fa-trash"></i>删除</a>&nbsp;&nbsp;&nbsp;';
                }
            },

        ];
        _dataTable = App.initDataTable("/user/page", columns);


    });

    function search() {
        var username = $("#username").val();
        var phone = $("#phone").val();
        var email = $("#email").val();

        var param = {
            "username": username,
            "email": email,
            "phone": phone
        };
        _dataTable.settings()[0].ajax.data = param;
        _dataTable.ajax.reload();
    }


</script>
</body>
</html>
