<%--
  Created by IntelliJ IDEA.
  User: chenyt
  Date: 2019/9/16
  Time: 21:43
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

    <title>我的商城 | 内容表单</title>
    <jsp:include page="../includes/header.jsp"/>
    <link rel="stylesheet" href="/static/assets/plugins/jquery-ztree/css/zTreeStyle/zTreeStyle.min.css" /></head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
    <jsp:include page="../includes/nav.jsp"/>
    <jsp:include page="../includes/menu.jsp"/>
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>
                ${tbContent.id == null ? '新增': '编辑'}内容
                <small></small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="#"><i class="fa fa-dashboard"></i> 主页</a></li>
                <li class="active">${tbContent.id == null ? '新增': '编辑'}内容</li>
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


            <!-- Horizontal Form -->
            <div class="box box-info">
                <div class="box-header with-border">
                    <h3 class="box-title">内容表单提交</h3>
                </div>
                <!-- /.box-header -->
                <!-- form start -->
                <form:form cssClass="form-horizontal" action="/content/save" method="POST" modelAttribute="tbContent" id="contentInputForm">
                    <div class="box-body">
                        <div class="form-group">
                            <label for="categoryId" class="col-sm-2 control-label">父级品类</label>

                            <div class="col-sm-10">
                                <form:hidden path="categoryId"/>
                                <input id="categoryName" Class="form-control required" placeholder="请输入品类" readonly="true" data-toggle="modal" data-target="#modal-default"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="title" class="col-sm-2 control-label">标题</label>
                            <div class="col-sm-10">
                                <form:input path="title" cssClass="form-control required" placeholder="请输入标题"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="subTitle" class="col-sm-2 control-label">子标题</label>
                            <div class="col-sm-10">
                                <form:input path="subTitle" cssClass="form-control required" placeholder="请输入子标题"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="titleDesc" class="col-sm-2 control-label">标题描述</label>
                            <div class="col-sm-10">
                                <form:input path="titleDesc" cssClass="form-control required" placeholder="请输入标题描述"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="url" class="col-sm-2 control-label">链接</label>
                            <div class="col-sm-10">
                                <form:input path="url" cssClass="form-control required" placeholder="请输入链接"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="pic" class="col-sm-2 control-label">图片一</label>
                            <div class="col-sm-10">
                                <form:input path="pic" cssClass="form-control required" placeholder="请输入图片一"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="pic2" class="col-sm-2 control-label">图片二</label>
                            <div class="col-sm-10">
                                <form:input path="pic2" cssClass="form-control required" placeholder="请输入图片二"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="content" class="col-sm-2 control-label">详情</label>
                            <div class="col-sm-10">
                                <form:textarea path="content" cssClass="form-control required" placeholder="请输入详情" cssStyle="min-height: 200px"/>
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
            $("#categoryId").val(node.id);
            $("#categoryName").val(node.name);
            $("#mod")
        })
    });
</script>

</body>
</html>