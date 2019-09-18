<%--
  Created by IntelliJ IDEA.
  User: chenyt
  Date: 2019/9/17
  Time: 11:42
  To change this template use File | Settings | File Templates.
--%>
<%@ tag language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="title" type="java.lang.String" required="false" description="模态框的标题" %>
<%@ attribute name="msg" type="java.lang.String" required="true" description="模态框的内容" %>
<%@ attribute name="opts" type="java.lang.String" required="false" description="操作类型：info/信息提示，confirm/确认对话框" %>
<%@ attribute name="url" type="java.lang.String" required="false" description="跳转链接，确认对话框时使用" %>

<div class="modal fade" id="modal-default">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">${title != null ? title:'温馨提示'}</h4>
            </div>
            <div class="modal-body">
                <p id="modal-message">${msg}</p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default pull-left" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" id="btn-confirm">好的</button>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>
<!-- /.modal -->

<script>
    $(function(){
        $("#btn-confirm").on("click", function () {
            <c:if test="${opts != 'confirm'}" >
                $("#modal-default").modal("hide");
            </c:if>
            <c:if test="${opts == 'confirm'}" >
                console.log('${url}');
            </c:if>
        })
    });
</script>