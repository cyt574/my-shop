<%--
  Created by IntelliJ IDEA.
  User: chenyt
  Date: 2019/9/17
  Time: 20:51
  To change this template use File | Settings | File Templates.
--%>
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

    <title>我的商城 | 分类管理</title>
    <jsp:include page="../includes/header.jsp"/>

    <link rel="stylesheet" href="/static/assets/plugins/treetable/themes/vsStyle/treeTable.min.css" type="text/css"/>

</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
    <jsp:include page="../includes/nav.jsp"/>
    <jsp:include page="../includes/menu.jsp"/>
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>
                分类管理
                <small></small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="#"><i class="fa fa-dashboard"></i> 主页</a></li>
                <li class="active">分类管理</li>
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

            <div class="box">
                <div class="box-header">
                    <h3 class="box-title">分类列表</h3>
                </div>
                <div class="box-body">
                    <a href="/content/category/form" type="button" class="btn btn-sm btn-default"><i class="fa fa-plus"></i>新增</a>&nbsp;&nbsp;&nbsp;
                    <a type="button" class="btn btn-sm btn-default"><i class="fa fa-upload"></i>导入</a>&nbsp;&nbsp;&nbsp;
                    <a type="button" class="btn btn-sm btn-default"><i class="fa fa-download"></i>导出</a>&nbsp;&nbsp;&nbsp;
                </div>
                <!-- /.box-header -->
                <div class="box-body table-responsive">
                    <table class="table table-hover" id="treeTable">
                        <thead>
                        <tr>
                            <th>ID</th>
                            <th>名称</th>
                            <th>排序</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${tbContentCategories}" var="tbContentCategory">
                                <tr id="${tbContentCategory.id}" pId="${tbContentCategory.parent.id}">
                                    <td>${tbContentCategory.id}</td>
                                    <td>${tbContentCategory.name}</td>
                                    <td>${tbContentCategory.sortOrder}</td>
                                    <td> <a href="/content/category/form?id=${tbContentCategory.id}" type="button" class="btn btn-sm btn-primary"><i class="fa fa-search"></i>编辑</a>&nbsp;&nbsp;&nbsp;
                                        <button type="button" class="btn btn-sm btn-danger"><i class="fa fa-edit">删除</i></button>&nbsp;&nbsp;&nbsp;
                                        <a href="/content/category/form?parent.id=${tbContentCategory.id}&parent.name=${tbContentCategory.name}" type="button" class="btn btn-sm btn-default" onclick="check()"><i class="fa fa-trash"></i>新增下级菜单</a>&nbsp;&nbsp;&nbsp;</td>
                                </tr>
                            </c:forEach>
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

<script src="/static/assets/plugins/treetable/jquery.treeTable.js"/>
<sys:modal msg="第一个模态框" url="http://www.chenyt.xyz"/>
<script>
    $(function () {
        $("#treeTable").treeTable({
            column: 1,
            expandLevel: 2
        })
    });
</script>
</body>
</html>
