﻿<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8">
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no"/>
    <meta http-equiv="Cache-Control" content="no-siteapp"/>
    <!--[if lt IE 9]>
    <script type="text/javascript" src="<%=request.getContextPath()%>/assistant/lib/html5shiv.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/assistant/lib/respond.min.js"></script>
    <![endif]-->
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/assistant/static/h-ui/css/H-ui.min.css"/>
    <link rel="stylesheet" type="text/css"
          href="<%=request.getContextPath()%>/assistant/static/h-ui.admin/css/H-ui.admin.css"/>
    <link rel="stylesheet" type="text/css"
          href="<%=request.getContextPath()%>/assistant/lib/Hui-iconfont/1.0.8/iconfont.css"/>
    <link rel="stylesheet" type="text/css"
          href="<%=request.getContextPath()%>/assistant/static/h-ui.admin/skin/default/skin.css" id="skin"/>
    <link rel="stylesheet" type="text/css"
          href="<%=request.getContextPath()%>/assistant/static/h-ui.admin/css/style.css"/>
    <!--[if IE 6]>
    <script type="text/javascript"
            src="<%=request.getContextPath()%>/assistant/lib/DD_belatedPNG_0.0.8a-min.js"></script>
    <script>DD_belatedPNG.fix('*');</script>
    <![endif]-->
    <title>产品列表</title>
</head>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 产品管理 <span
        class="c-gray en">&gt;</span> 产品列表 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px"
                                              id="refresh"
                                              href="javascript:location.replace(location.href);" title="刷新"><i
        class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
    <form id="productForm" class="form form-horizontal">
        <div class="row cl">
            <label class="form-label col-xs-1 col-sm-1">来源：</label>
            <div class="formControls col-xs-2 col-sm-2">
                <select id="source" name="source" class="select" style="height: 32px">
                    <option value="">请选择</option>
                </select>
            </div>
            <label class="form-label col-xs-2 col-sm-2">开始日期：</label>
            <div class="formControls col-xs-2 col-sm-2">
                <input type="text" onfocus="WdatePicker({ maxDate:'#F{$dp.$D(\'logmax\')||\'%y-%M-%d\'}' })"
                       name="startTime" class="input-text Wdate" id="logmin" readonly>
            </div>
            <label class="form-label col-xs-2 col-sm-2">结束日期：</label>
            <div class="formControls col-xs-2 col-sm-2">
                <input type="text" onfocus="WdatePicker({ minDate:'#F{$dp.$D(\'logmin\')}',maxDate:'%y-%M-%d' })"
                       name="endTime" class="input-text Wdate" id="logmax" readonly>
            </div>
            <div class="formControls col-xs-1 col-sm-1">
                <button id="search" class="btn btn-success" type="button"><i class="Hui-iconfont">&#xe665;</i>
                </button>
            </div>
            <input type="hidden" name="pState" id="pStatus">
        </div>
        <div class="row cl">
            <label class="form-label col-xs-1 col-sm-1">创建人：</label>
            <div class="formControls col-xs-2 col-sm-2">
                <select id="salesMan" name="salesMan" class="select" style="height: 32px">
                    <option value="">请选择</option>
                </select>
            </div>
            <input id="roleId" type="hidden" value="${sessionScope.user.ROLE_ID}">
            <input id="companyId" type="hidden" value="${sessionScope.user.USER_COMPANY}">
        </div>
    </form>
    <div class="mt-10">
        <div id="btn-div" class="row text-c">
            <a href="javascript:;" onclick="reloadTable(0)" class="btn btn-success radius">全部</a>
            <a href="javascript:;" onclick="reloadTable(1)" class="btn btn-default radius">未认领</a>
            <a href="javascript:;" onclick="reloadTable(2)" class="btn btn-default radius">已认领</a>
        </div>
        <div class="cl pd-5 bg-1 bk-gray mt-10" id="count-div">
            <span class="l"> <a href="javascript:;"
                                onclick="deleteProduct(null)"
                                class="btn btn-danger radius"><i
                    class="Hui-iconfont">
                &#xe6e2;</i> 批量删除</a>
            <a href="javascript:;"
               onclick="claimProduct(null)"
               class="btn btn-danger radius"><i
                    class="Hui-iconfont">
                &#xe6e2;</i> 批量认领</a>
            <a class="btn btn-primary radius" href="javascript:;"
               onclick="addProduct('添加产品','<%=request.getContextPath()%>/assistant/index/product/product-add.jsp')"><i
                    class="Hui-iconfont">
                &#xe600;</i> 添加产品</a> </span></div>
        <table id="productTable" class="table table-border table-bordered table-bg table-hover">
            <thead>
            <tr class="text-c">
                <th width="25"><input type="checkbox" value="" name=""></th>
                <th width="80">序号</th>
                <th width="100">产品图</th>
                <th width="100">产品链接</th>
                <th width="50">来源</th>
                <th width="150">标题</th>
                <th width="150">分类</th>
                <th width="150">描述</th>
                <th width="60">售价</th>
                <th width="100">认领记录</th>
                <th width="100">创建人</th>
                <th width="100">创建时间</th>
                <th width="100">操作</th>
            </tr>
            </thead>
        </table>
    </div>
</div>
<script type="text/javascript" src="<%=request.getContextPath()%>/assistant/lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/assistant/lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/assistant/static/h-ui/js/H-ui.min.js"></script>
<script type="text/javascript"
        src="<%=request.getContextPath()%>/assistant/static/h-ui.admin/js/H-ui.admin.js"></script>
<script type="text/javascript"
        src="<%=request.getContextPath()%>/assistant/lib/My97DatePicker/4.8/WdatePicker.js"></script>
<script type="text/javascript"
        src="<%=request.getContextPath()%>/assistant/lib/datatables/1.10.0/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/assistant/lib/laypage/1.2/laypage.js"></script>
<script type="text/javascript">
    var productTable = null;
    var roleId = $("#roleId").val();
    var companyId = $("#companyId").val();
    $(function () {
        if (roleId == 100 || roleId == 200) {
            initSalesSelect("all");
        } else if (roleId == 400) {
            $("#localStatus").val(2);
            initSalesSelect("all");
        } else if (roleId == 500) {
            initSalesSelect("owner");
        } else if (roleId == 600) {
            $("#salesMan").empty();
            $("#salesMan").append($("<option value='${sessionScope.user.USER_ID}'>${sessionScope.user.USER_NAME}</option>"));
        }
        var sd = new Date();
        sd.setDate(sd.getDate() - 6);
        $("#logmin").val(sd.format("yyyy-MM-dd"));
        $("#logmax").val(new Date().format("yyyy-MM-dd"));
        productTable = initializeTable();
    });
    $("#search").click(function () {
        productTable.ajax.reload();
    });
    /*产品-添加*/
    function addProduct(title, url) {
        var index = layer.open({
            type: 2,
            title: title,
            content: url,
            end: function () {
                location.reload();
            }
        });
        layer.full(index);
    }
    function initSalesSelect(type) {
        if (type == "all") {
            $.ajax({
                type: 'POST',
                url: '<%=request.getContextPath()%>/common/getList',
                dataType: 'json',
                data: {
                    "code": "users"
                },
                success: function (data) {
                    if (data.code == 0) {
                        var data = data.data;
                        for (var i = 0; i < data.length; i++) {
                            $("#salesMan").append($('<option value=' + data[i].USER_ID + '>' + data[i].USER_NAME + '</option>'));
                        }
                    }
                },
                error: function (data) {
                    layer.msg(data.msg, {icon: 2, time: 1000});
                }
            });
        } else {
            $.ajax({
                type: 'POST',
                url: '<%=request.getContextPath()%>/system/selectUsers',
                dataType: 'json',
                data: {
                    "companyId": companyId
                },
                success: function (data) {
                    if (data.code == 0) {
                        var data = data.data;
                        for (var i = 0; i < data.length; i++) {
                            $("#salesMan").append($('<option value=' + data[i].USER_ID + '>' + data[i].USER_NAME + '</option>'));
                        }
                    }
                },
                error: function (data) {
                    layer.msg(data.msg, {icon: 2, time: 1000});
                }
            });
        }
    }
    /*查询订单*/
    function reloadTable(id) {
        if (id == 0) {
            document.getElementById('pStatus').value = "";
        } else {
            $("#pStatus").val(id);
        }
        productTable.ajax.reload();
        var btnDiv = document.getElementById("btn-div");
        var btns = btnDiv.getElementsByTagName("a");
        for (var i = 0; i < btns.length; i++) {
            if (i == id) {
                btns[i].className = 'btn btn-success radius'
            } else {
                btns[i].className = 'btn btn-default radius'
            }
        }
    }
    /*初始化table*/
    function initializeTable() {
        var table = $("#productTable").DataTable({
            "serverSide": true,
            "pagingType": "full_numbers",
            "processing": true,
            "ordering": false,
            "searching": false,
            "bLengthChange": false,
            "ajax": {
                "url": "<%=request.getContextPath()%>/product/list",
                "type": "POST",
                "data": function (d) {
                    return $.extend({}, d, {
                        "data": JSON.stringify(getFormJson("#productForm"))
                    });
                }
            },
            "columns": [
                {"data": "ID"},
                {"data": "ID"},
                {
                    "data": function (val) {
                        return val.MAIN_PATH == null ? "" : "<img width='100px' height='90px'  src='<%=session.getAttribute("productPath")%>" + val.MAIN_PATH + "'/>";
                    }
                },
                {
                    "data": function (val) {
                        return val.SOURCE == null ? "" : "<a target='_blank' href='" + val.SOURCE + "'>" + val.SOURCE + "</a>";
                    }
                },
                {
                    "data": function (val) {
                        return val.SOURCE_TYPE == null ? "" : "<a target='_blank' href='" + val.SOURCE_TYPE + "'>" + val.SOURCE_TYPE + "</a>";
                    }
                },
                {
                    "data": function (val) {
                        return val.NAME == null ? "" : val.NAME;
                    }
                },
                {
                    "data": function (val) {
                        return val.TYPENAME == null ? "" : val.TYPENAME;
                    }
                },
                {
                    "data": function (val) {
                        return val.INFO == null ? "" : val.INFO;
                    }
                },
                {
                    "data": function (val) {
                        return val.PRICE == null ? "" : val.PRICE;
                    }
                },
                {
                    "data": function (val) {
                        return val.STATE_TIME == null ? "" : "在" + getMyDate(val.STATE_TIME) + "时被认领";
                    }
                },
                {
                    "data": function (val) {
                        return val.USER_NAME == null ? "" : val.USER_NAME;
                    }
                },
                {
                    "data": function (val) {
                        return getMyDate(val.CREATE_TIME);
                    }
                }
            ],
            "columnDefs": [
                {
                    "targets": [0],
                    "data": "ID",
                    "render": function (data, type, full) {
                        return "<input type='checkbox' value=" + full.ID + ">"
                    }
                },
                {
                    "targets": [7],
                    "data": "INFO",
                    "visible": false
                },
                {
                    "targets": [12],
                    "data": "ID",
                    "render": function (data, type, full) {
                        return ( full.P_STATE == "1" ? "<a style='text-decoration:none' title='认领'  onClick=\"claimProduct('" + full.ID + "')\"')>认领</a>" +
                                "&nbsp;&nbsp;" +
                                "<a style='text-decoration:none' title='编辑'  onClick=\"editProduct('" + full.ID + "')\"')>编辑</a>" : "") +
                                "&nbsp;&nbsp;" +
                                "<a style='text-decoration:none' title='删除'  onClick=\"deleteProduct('" + full.ID + "')\"')>删除</a>";
                    }
                }
            ],
            "rowCallback": function (row, data, displayIndex) {
                $(row).attr("class", "text-c");
            },
            "initComplete": function (settings, json) {

            },
            /*"dom": "t<'dataTables_info'il>p",*/
            "language": {
                "sProcessing": "正在加载中......",
                "lengthMenu": "每页 _MENU_ 条",
                "zeroRecords": "没有找到记录",
                "info": "当前显示 _START_ 到 _END_ 条，共 _TOTAL_条",
                "infoEmpty": "无记录",
                "paginate": {
                    "first": "首页",
                    "previous": "前一页",
                    "next": "后一页",
                    "last": "末页"
                }
            }
        });
        return table;
    }

    function editProduct(id) {
        var index = layer.open({
            type: 2,
            title: "编辑产品",
            content: '<%=request.getContextPath()%>/assistant/index/product/product-add.jsp?id=' + id,
            end: function () {
                location.reload();
            }
        });
        layer.full(index);
    }

    /**
     * 认领操作
     * @param ids
     */
    function claimProduct(ids) {
        if (ids == null) {
            ids = getIDs();
        }
        layer.confirm('确定认领商品吗？', function (index) {
            layer.load();
            $.ajax({
                type: 'POST',
                url: '<%=request.getContextPath()%>/product/state',
                dataType: 'json',
                data: {
                    "data": ids,
                    "type": 2
                },
                success: function (data) {
                    layer.closeAll('loading');
                    if (data.success) {
                        layer.msg(data.msg, {icon: 6, time: 1000});
                        setTimeout(function () {
                            document.getElementById("refresh").click();
                        }, 1000);
                    } else {
                        layer.msg(data.msg, {icon: 5, time: 1000});
                    }
                },
                error: function (data) {
                    layer.closeAll('loading');
                    layer.msg(data.msg, {icon: 2, time: 1000});
                }
            });
        });
    }

    /*产品-删除*/
    function deleteProduct(id) {
        if (id == null || id == "") {
            id = getIDs();
        }
        layer.confirm('产品删除须谨慎，确认要删除吗？', function (index) {
            $.ajax({
                type: 'POST',
                url: '<%=request.getContextPath()%>/product/delete',
                dataType: 'json',
                data: {"data": id},
                success: function (data) {
                    if (data.success) {
                        layer.msg(data.msg, {icon: 6, time: 1000});
                        setTimeout(function () {
                            document.getElementById("refresh").click();
                        }, 1000);

                    } else {
                        layer.msg('删除失败!', {icon: 5, time: 1000});
                    }
                },
                error: function (data) {
                    layer.msg('删除失败!', {icon: 5, time: 1000});
                },
            });
        });
    }

    function getIDs() {
        var ids = [];
        $("#productTable td input:checkbox:checked").each(function (i, val) {
            ids.push($(val).val());
        });
        return ids.join(",");
    }

    //将时间戳格式化
    function getMyDate(time) {
        if (typeof(time) == "undefined") {
            return "";
        }
        var oDate = new Date(time),
                oYear = oDate.getFullYear(),
                oMonth = oDate.getMonth() + 1,
                oDay = oDate.getDate(),
                oHour = oDate.getHours(),
                oMin = oDate.getMinutes(),
                oSen = oDate.getSeconds(),
                oTime = oYear + '-' + getzf(oMonth) + '-' + getzf(oDay) + ' ' + getzf(oHour) + ':' + getzf(oMin) + ':' + getzf(oSen);//最后拼接时间
        return oTime;
    }

    //补0操作,当时间数据小于10的时候，给该数据前面加一个0
    function getzf(num) {
        if (parseInt(num) < 10) {
            num = '0' + num;
        }
        return num;
    }
</script>
</body>
</html>