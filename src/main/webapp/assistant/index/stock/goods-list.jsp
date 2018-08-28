<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <title>商品列表</title>
</head>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 商品管理 <span
        class="c-gray en">&gt;</span> 商品列表 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px"
                                              href="javascript:location.replace(location.href);" title="刷新"><i
        class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
    <form id="goodsForm" class="form form-horizontal">
        <div class="row cl">
            <label class="form-label col-xs-2 col-sm-2">公司：</label>
            <div class="formControls col-xs-2 col-sm-2">
                <select id="company" name="company" class="select" style="height: 32px"
                        onchange="loadUsers(this.value)">
                    <option value="">请选择</option>
                </select>
            </div>
            <label class="form-label col-xs-2 col-sm-2">操作人：</label>
            <div class="formControls col-xs-2 col-sm-2">
                <select id="createUser" name="createUser" class="select" style="height: 32px">
                    <option value="">请选择</option>
                </select>
            </div>
            <label class="form-label col-xs-2 col-sm-2">商品sku：</label>
            <div class="formControls col-xs-2 col-sm-2">
                <input type="text" id="pSku" name="pSku" placeholder=" "
                       class="input-text">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-2 col-sm-2">类型：</label>
            <div class="formControls col-xs-2 col-sm-2">
                <select id="pType" name="pType" class="select" style="height: 32px">
                    <option value="1">商品</option>
                    <option value="2">赠品</option>
                    <option value="3">包材</option>
                </select>
            </div>
            <label class="form-label col-xs-2 col-sm-2">中文名称：</label>
            <div class="formControls col-xs-2 col-sm-2">
                <input type="text" id="nameCn" name="nameCn" placeholder=" "
                       class="input-text">
            </div>
            <label class="form-label col-xs-2 col-sm-2">英文名称：</label>
            <div class="formControls col-xs-2 col-sm-2">
                <input type="text" id="nameEn" name="nameEn" placeholder=" "
                       class="input-text">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-2 col-sm-2">开始日期：</label>
            <div class="formControls col-xs-2 col-sm-2">
                <input type="text" onfocus="WdatePicker({ maxDate:'#F{$dp.$D(\'logmax\')||\'%y-%M-%d\'}' })"
                       name="logmin" class="input-text Wdate" id="logmin" readonly>
            </div>
            <label class="form-label col-xs-2 col-sm-2">结束日期：</label>
            <div class="formControls col-xs-2 col-sm-2">
                <input type="text" onfocus="WdatePicker({ minDate:'#F{$dp.$D(\'logmin\')}',maxDate:'%y-%M-%d' })"
                       name="logmax" class="input-text Wdate" id="logmax" readonly>
            </div>
            <label class="form-label col-xs-2 col-sm-2">平台sku：</label>
            <div class="formControls col-xs-2 col-sm-2">
                <input type="text" id="platSku" name="platSku" placeholder=" "
                       class="input-text">
            </div>
        </div>
        <div class="text-c cl row">
            <button id="search" class="btn btn-success" type="button"><i class="Hui-iconfont">&#xe665;</i> 搜索
            </button>
        </div>
    </form>
    <div class="mt-20">
        <div class="cl pd-5 bg-1 bk-gray mt-10" id="count-div"><span class="l">
            <a class="btn btn-primary radius" href="javascript:;"
               onclick="addGoods('添加商品','<%=request.getContextPath()%>/assistant/index/stock/goods-add.jsp')"><i
                    class="Hui-iconfont">&#xe600;</i> 添加商品</a> </span></div>
        <table id="goodsTable" class="table table-border table-bordered table-bg table-hover">
            <thead>
            <tr class="text-c">
                <th width="100">图片</th>
                <th width="100">商品sku</th>
                <th width="50">类型</th>
                <th width="100">所属分类</th>
                <th width="150">来源链接</th>
                <th width="100">中文名称</th>
                <th width="100">英文名称</th>
                <th width="100">平台sku</th>
                <th width="100">公司</th>
                <th width="50">操作人</th>
                <th width="100">操作时间</th>
                <th width="100">操作</th>
            </tr>
            </thead>
        </table>
    </div>
</div>

<script type="text/javascript"
        src="<%=request.getContextPath()%>/assistant/lib/jquery/1.9.1/jquery.min.js"></script>
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
    var goodsTable = null;
    $(function () {
        initSelect();
        var sd = new Date();
        sd.setDate(sd.getDate() - 6);
        $("#logmin").val(sd.format("yyyy-MM-dd"));
        $("#logmax").val(new Date().format("yyyy-MM-dd"));
        goodsTable = initializeTable();
        $("#search").click(function () {
            goodsTable.ajax.reload();
        });
    });
    function initSelect() {
        $.ajax({
            type: 'POST',
            url: '<%=request.getContextPath()%>/common/getList',
            dataType: 'json',
            data: {
                "code": "companies"
            },
            success: function (data) {
                if (data.code == 0) {
                    var data = data.data;
                    for (var i = 0; i < data.length; i++) {
                        $("#company").append($('<option value=' + data[i].COMPANY_ID + '>' + data[i].COMPANY_NAME + '</option>'));
                    }
                }
            },
            error: function (data) {
                layer.msg(data.msg, {icon: 2, time: 2000});
            }
        });
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
                        $("#createUser").append($('<option value=' + data[i].USER_ID + '>' + data[i].USER_NAME + '</option>'));
                    }
                }
            },
            error: function (data) {
                layer.msg(data.msg, {icon: 2, time: 2000});
            },
        });
    }
    function addGoods(title, url) {
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
    /*查询商品*/
    function reloadTable(id) {
        goodsTable.ajax.reload();
    }
    /*初始化table*/
    function initializeTable() {
        var table = $("#goodsTable").DataTable({
            "processing": true,
            "serverSide": true,
            "pagingType": "full_numbers",
            "ordering": false,
            "searching": false,
            "bLengthChange": false,
            "ajax": {
                "url": "<%=request.getContextPath()%>/stock/getGoods",
                "type": "POST",
                "data": function (d) {
                    return $.extend({}, d, {
                        "data": JSON.stringify(getFormJson("#goodsForm"))
                    });
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    if (jqXHR.getResponseHeader("sessionstatus") == "timeOut") {
                        top.window.location.replace("<%=request.getContextPath()%>/assistant/index/login.jsp");
                    }
                }
            },
            "columns": [
                {"data": "IMAGE"},
                {"data": "P_SKU"},
                {"data": "P_TYPE"},
                {"data": "L_C_NAME"},
                {"data": "SOURCE_URL"},
                {"data": "NAME_CN"},
                {"data": "NAME_EN"},
                {"data": "PLAT_SKU"},
                {"data": "COMPANY_NAME"},
                {"data": "USER_NAME"},
                {"data": "OPERA_TIME"},
                {"data": "OPERA_TIME"}
            ],
            "columnDefs": [
                {
                    "targets": [0],
                    "data": "IMAGE",
                    "render": function (data, type, full) {
                        return data == null ? "" : "<img width='100px' height='90px'  src='<%=session.getAttribute("productPath")%>" + data + "'/>";
                    }
                },
                {
                    "targets": [2],
                    "data": "P_TYPE",
                    "render": function (data, type, full) {
                        if (data == 1) {
                            return "<div>商品</div>";
                        } else if (data == 2) {
                            return "<div>赠品</div>";
                        } else if (data == 3) {
                            return "<div>包材</div>";
                        }
                    }
                },
                {
                    "targets": [4],
                    "data": "SOURCE_URL",
                    "render": function (data, type, full) {
                        return data == null ? "" : "<a target='_blank' href='" + data + "'>" + data + "</a>";
                    }
                },
                {
                    "targets": [10],
                    "data": "OPERA_TIME",
                    "render": function (data, type, full) {
                        return "<div>" + getMyDate(data) + "</div>"
                    }
                },
                {
                    "targets": [11],
                    "data": "OPERA_TIME",
                    "render": function (data, type, full) {
                        return "<a style='text-decoration:none' title='编辑'  onClick=\"editGoods('" + full.P_SKU + "')\"'>编辑</a>" +
                                "&nbsp;&nbsp;" +
                                "<a style='text-decoration:none' title='删除'  onClick=\"delGoods('" + full.P_SKU + "')\"'>删除</a>";
                    }
                }
            ],
            "rowCallback": function (row, data, displayIndex) {
                $(row).attr("class", "text-c");
            },
            "initComplete": function (settings, json) {

            },
            /* "dom": "t<'dataTables_info'il>p",*/
            "language": {
                "processing": "正在加载中......",
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
    function delGoods(pSku) {
        layer.confirm('您确定要删除入库记录吗？', function (i) {
            $.ajax({
                type: 'POST',
                url: '<%=request.getContextPath()%>/stock/delGoods',
                dataType: 'json',
                data: {
                    "pSku": pSku
                },
                success: function (data) {
                    layer.closeAll("loading");
                    if (data.code == 0) {
                        layer.msg(data.msg, {icon: 1, time: 2000});
                        reloadTable()
                    } else {
                        layer.msg(data.msg, {icon: 2, time: 2000});
                    }
                },
                error: function (data) {
                    layer.msg(data.msg, {icon: 2, time: 2000});
                }
            });
        });
    }
    function loadUsers(value) {
        $.ajax({
            type: 'POST',
            url: '<%=request.getContextPath()%>/system/selectUsers',
            dataType: 'json',
            data: {
                "companyId": value
            },
            success: function (data) {
                if (data.code == 0) {
                    var data = data.data;
                    $("#createUser").empty();
                    $("#createUser").append($("<option value=''>请选择</option>"));
                    for (var i = 0; i < data.length; i++) {
                        $("#createUser").append($('<option value=' + data[i].USER_ID + '>' + data[i].USER_NAME + '</option>'));
                    }
                }
            },
            error: function (data) {
                layer.msg(data.msg, {icon: 2, time: 2000});
            }
        });
    }
    function editGoods(pSku) {
        var index = layer.open({
            type: 2,
            title: "编辑商品",
            content: "<%=request.getContextPath()%>/assistant/index/stock/goods-add.jsp?pSku=" + pSku,
            end: function () {
                location.reload();
            }
        });
        layer.full(index);
    }
</script>
</body>
</html>