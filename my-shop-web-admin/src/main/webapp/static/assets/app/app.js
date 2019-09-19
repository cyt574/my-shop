var App = function () {

    var _idArray = null;
    var masterCheckBox = null;
    var checkBox = null;

    var defaultDropZoneOpts = {
        url: '',
        dictDefaultMessage: '拖动文件至此或者点击上传', // 设置默认的提示语句
        paramName: "dropzFile", // 传到后台的参数名称
        maxFiles: 1,// 一次性上传的文件数量上限
        maxFilesize: 2, // 文件大小，单位：MB
        acceptedFiles: ".jpg,.gif,.png,.jpeg", // 上传的类型
        addRemoveLinks: true,
        parallelUploads: 1,// 一次上传的文件数量
        //previewsContainer:"#preview", // 上传图片的预览窗口
        dictDefaultMessage: '拖动文件至此或者点击上传',
        dictMaxFilesExceeded: "上传个文件个数达到限制！",
        dictResponseError: '文件上传失败!',
        dictInvalidFileType: "文件类型只能是*.jpg,*.gif,*.png,*.jpeg。",
        dictFallbackMessage: "浏览器不受支持",
        dictFileTooBig: "文件过大上传文件最大支持.",
        dictRemoveLinks: "删除",
        dictCancelUpload: "取消",
    };

    /**
     * CheckBox初始化
     */
    var handlerInitCheckBox = function () {
        $('input[type="checkbox"].minimal, input[type="radio"].minimal').iCheck({
            checkboxClass: 'icheckbox_minimal-blue',
            radioClass   : 'iradio_minimal-blue'
        });
    };

    /**
     * 全选功能实现
     */
    var handlerCheckBoxSelectAll = function () {
        masterCheckBox =  $('input[type="checkbox"].minimal.checkbox-master');
        checkBox =  $('input[type="checkbox"].minimal');
        masterCheckBox.on('ifClicked',function (e) {
            if(e.target.checked) {
                checkBox.iCheck('uncheck');
            } else {
                checkBox.iCheck('check');
            }
        })
    };


    /**
     * 删除模态框弹出
     * @param url
     */
    var handlerDeleteMulti  = function (url) {
        _idArray = new Array();
        checkBox.each(function () {
            let _id = $(this).attr("id");
            if(_id != null && _id != undefined && $(this).is(':checked')) {
                _idArray.push(_id);
            }
        });
        if(_idArray.length === 0) {
            $("#modal-message").html("您还没有选择任何数据项，请选择至少一项");
        } else {
            $("#modal-message").html("您确定要删除该对象吗？");
        }

        $("#btn-confirm").bind("click", function () {
            post();
        });

        $("#modal-default").modal("show");



        function post() {
            $("#modal-default").modal("hide");
            if(_idArray.length > 0) {
                console.log("Submit Data")
                setTimeout(function () {
                    $.ajax({
                        url: url,
                        type: "POST",
                        data: {
                            "ids": _idArray.toString()
                        },
                        dataType: "JSON",
                        success: function (data) {
                            // 请求成功后，无论是成功或是失败都需要弹出模态框进行提示，所以这里需要先解绑原来的 click 事件
                            $("#btnModalOk").unbind("click");
                            if(data.status === 200) {
                                $("#btn-confirm").bind("click", function () {
                                    window.location.reload();
                                });
                            } else {
                                $("#btn-confirm").bind("click", function () {
                                    $("#modal-default").modal("hide");
                                });
                            }
                            $("#modal-message").html(data.message);
                            $("#modal-default").modal("show");
                        }
                    })
                }, 500)
            }
        }
    };


    /**
     * 表格参数初始化
     * @param url
     * @param columns
     * @returns {jQuery|*}
     */
    var handlerInitDataTables = function (url, columns) {
        var _dataTable = $("#dataTable").DataTable({
            "padding": false,
            "info": true,
            "lengthChange": false,
            "processing": true,
            "searching": false,
            "serverSide": true,
            "deferRender": true,
            "ordering": false,
            "ajax": {
                "url": url,
                "data": {

                }
            },
            language: {
                "sProcessing": "处理中...",
                "sLengthMenu": "显示 _MENU_ 项结果",
                "sZeroRecords": "没有匹配结果",
                "sInfo": "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
                "sInfoEmpty": "显示第 0 至 0 项结果，共 0 项",
                "sInfoFiltered": "(由 _MAX_ 项结果过滤)",
                "sInfoPostFix": "",
                "sSearch": "搜索:",
                "sUrl": "",
                "sEmptyTable": "表中数据为空",
                "sLoadingRecords": "载入中...",
                "sInfoThousands": ",",
                "oPaginate": {
                    "sFirst": "首页",
                    "sPrevious": "上页",
                    "sNext": "下页",
                    "sLast": "末页"
                },
                "oAria": {
                    "sSortAscending": ": 以升序排列此列",
                    "sSortDescending": ": 以降序排列此列"
                }
            },
            "columns": columns,
            "drawCallback": function(settings, json) {
                handlerInitCheckBox();
                handlerCheckBoxSelectAll();
            }
        });
        return _dataTable;
    }


    /**
     * ajax请求详情页数据
     * @param url
     */
    var handlerShowDetail = function (url) {
        $.ajax({
            url: url,
            type: "GET",
            dataType: "html",
            success: function (data) {
                $("#modal-detail-body").html(data);
                $("#modal-detail").modal("show");
            }
        });
    }

    var handlerInitZTree = function(url, param, callback) {
        var setting ={
            view: {
                dblClickExpand: true,
                showLine: true,
                selectedMulti: false
            },
            async: {
                enable: true,
                url: url,
                autoParam: param,
                // otherParam: [],
                // dataFilter: filter
            }
        }
        $.fn.zTree.init($("#myTree"), setting);

        $("#btn-confirm").bind("click", function () {
            var zTree = $.fn.zTree.getZTreeObj("myTree");
            var nodes = zTree.getSelectedNodes();

            if( nodes.length == 0) {
                alert("请选择一个节点");
            } else {
                callback(nodes);
            }
        })
    }
    
    var handlerInitDropZone = function (opts) {
        Dropzone.autoDiscover = false;

        $.extend(defaultDropZoneOpts, opts);
        // $(""+defaultDropZoneOpts.id).dropzone(defaultDropZoneOpts);
        new Dropzone(defaultDropZoneOpts.id, defaultDropZoneOpts);
    } 

    return {

        /**
         * 初始化
         */
        init: function () {
            handlerInitCheckBox();
            handlerCheckBoxSelectAll();
        },

        /**
         * 批量删除
         * @param url
         */
        deleteMulti: function (url) {
            handlerDeleteMulti(url)
        },

        /**
         * 初始化表格
         * @param url
         * @param columns
         * @returns {jQuery|*}
         */
        initDataTable: function (url, columns) {
            return handlerInitDataTables(url, columns);
        },

        /**
         * 显示详情页
         * @param url
         */
        showDetail: function (url) {
            handlerShowDetail(url)
        },

        initZTree: function (url, param, callback) {
            handlerInitZTree(url, param, callback);
        },
        initDropZone: function (opts) {
            handlerInitDropZone(opts);
        }
    }
}();

$(document).ready(function () {
   App.init()
});