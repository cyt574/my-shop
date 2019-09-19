<%--
  Created by IntelliJ IDEA.
  User: chenyt
  Date: 2019/9/18
  Time: 10:58
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

    <title>我的商城 | 新增分类</title>
    <jsp:include page="../includes/header.jsp"/>
    <link rel="stylesheet" href="/static/assets/plugins/jquery-ztree/css/zTreeStyle/zTreeStyle.min.css"/>
</head>

</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
    <jsp:include page="../includes/nav.jsp"/>
    <jsp:include page="../includes/menu.jsp"/>
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>
                ${tbUser.id == null ? '新增': '编辑'}分类
                <small></small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="#"><i class="fa fa-dashboard"></i> 主页</a></li>
                <li class="active">${tbUser.id == null ? '新增': '编辑'}分类</li>
            </ol>
        </section>

        <!-- Main content -->
        <section class="content">
            <c:if test="${baseResult!=null}">
                <div class="alert alert-${baseResult.status == 200 ? "success" : "danger"} alert-dismissible">
                    <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                        ${baseResult.message}
                </div>
            </c:if>


            <!-- Horizontal Form -->
            <div class="box box-info">
                <div class="box-header with-border">
                    <h3 class="box-title">分类表单提交</h3>
                </div>
                <!-- /.box-header -->
                <!-- form start -->
                <form:form cssClass="form-horizontal" action="/content/category/save" method="POST"
                           modelAttribute="tbContentCategory" id="inputForm">
                    <div class="box-body">
                        <div class="form-group">
                            <label for="parentName" class="col-sm-2 control-label">父级品类</label>
                            <div class="col-sm-10">
                                <form:hidden path="id"/>
                                <form:hidden id="parentId" path="parent.id"/>
                                <input id="parentName" class="form-control" placeholder="请输入品类" readonly="true"
                                       data-toggle="modal" data-target="#modal-default"  value="${tbContentCategory.parent.name}"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="name" class="col-sm-2 control-label">分类名称</label>
                            <div class="col-sm-10">
                                <form:input path="name" cssClass="form-control required" placeholder="请输入分类名称"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="sortOrder" class="col-sm-2 control-label">分类排序</label>
                            <div class="col-sm-10">
                                <form:input path="sortOrder" cssClass="form-control required digits"
                                            placeholder="请输入分类排序"/>
                            </div>
                        </div>
                    </div>
                        <!-- /.box-body -->
                        <div class="box-footer">
                            <button type="button" class="btn btn-default" onclick="history.go(-1)">返回</button>
                            <button type="submit" class="btn btn-info pull-right">提交</button>
                        </div>
                </form:form>
            </div>
        </section>


    </div>

    <jsp:include page="../includes/copyright.jsp"/>
</div>
<jsp:include page="../includes/footer.jsp"/>
<script src="/static/assets/plugins/jquery-ztree/js/jquery.ztree.core-3.5.min.js"></script>

<sys:modal title="请选择" msg=" <ul id='myTree' class='ztree'>  </ul> "/>
<script>
    $(function () {
        App.initZTree("/content/category/tree", ["id"], function (nodes) {
            var node = nodes[0];
            $("#parentId").val(node.id);
            $("#parentName").val(node.name);
        })
    });
</script>
</body>
</html>