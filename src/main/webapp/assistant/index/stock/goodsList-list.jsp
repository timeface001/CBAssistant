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
    <title>商品清单</title>
</head>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 库存管理 <span
        class="c-gray en">&gt;</span> 商品清单 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px"
                                              href="javascript:location.replace(location.href);" title="刷新"><i
        class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
    <form id="goodsListForm" class="form form-horizontal">
        <div class="row cl">
            <label class="form-label col-xs-2 col-sm-2">商品sku：</label>
            <div class="formControls col-xs-2 col-sm-2">
                <input type="text" id="pSku" name="pSku" placeholder=" "
                       class="input-text">
            </div>
            <label class="form-label col-xs-2 col-sm-2">分类：</label>
            <div class="formControls col-xs-2 col-sm-2">
                <input type="text" id="pCategory" name="pCategory" placeholder=" "
                       class="input-text">
            </div>
            <label class="form-label col-xs-2 col-sm-2">货架编号：</label>
            <div class="formControls col-xs-2 col-sm-2">
                <input type="text" id="shelNo" name="shelNo" placeholder=" "
                       class="input-text">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-2 col-sm-2">仓库：</label>
            <div class="formControls col-xs-2 col-sm-2">
                <input type="text" id="wareRomm" name="wareRomm" placeholder=" "
                       class="input-text">
            </div>
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
        </div>
        <div class="text-c cl row">
            <button id="search" class="btn btn-success" type="button"><i class="Hui-iconfont">&#xe665;</i> 搜索
            </button>
        </div>
    </form>
    <div class="mt-20">
        <table id="goodsListTable" class="table table-border table-bordered table-bg table-hover">
            <thead>
            <tr class="text-c">
                <th width="100">图片</th>
                <th width="100">商品sku</th>
                <th width="100">分类</th>
                <th width="100">库存数</th>
                <th width="100">运输数量</th>
                <th width="100">单价</th>
                <th width="100">总价</th>
                <th width="100">货架编码</th>
                <th width="100">仓库</th>
                <th width="100">公司</th>
                <th width="100">操作人</th>
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
    var goodsListTable = null;
    $(function () {
        initSelect();
        var sd = new Date();
        sd.setDate(sd.getDate() - 6);
        $("#logmin").val(sd.format("yyyy-MM-dd"));
        $("#logmax").val(new Date().format("yyyy-MM-dd"));
        goodsListTable = initializeTable();
        $("#search").click(function () {
            goodsListTable.ajax.reload();
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
                layer.msg(data.msg, {icon: 2, time: 1000});
            }
        });
    }
    /*查询商品清单*/
    function reloadTable(id) {
        layer.load();
        goodsListTable.ajax.reload();
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
        var table = $("#goodsListTable").DataTable({
            "processing": true,
            "serverSide": true,
            "pagingType": "full_numbers",
            "ordering": false,
            "searching": false,
            "bLengthChange": false,
            "ajax": {
                "url": "<%=request.getContextPath()%>/stock/getGoodsList",
                "type": "POST",
                "data": function (d) {
                    return $.extend({}, d, {
                        "data": JSON.stringify(getFormJson("#goodsListForm"))
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
                {"data": "P_CATEGORY"},
                {"data": "P_STOCK_NUM"},
                {"data": "P_WAY_NUM"},
                {"data": "PRICE"},
                {"data": "TOTAL_PRICE"},
                {"data": "SHELF_NO"},
                {"data": "WAREROOM"},
                {"data": "COMPANY_NAME"},
                {"data": "USER_NAME"},
                {"data": "UPDATE_TIME"},
                {"data": "UPDATE_TIME"}
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
                    "targets": [11],
                    "data": "UPDATE_TIME",
                    "render": function (data, type, full) {
                        return "<div>" + getMyDate(data) + "</div>"
                    }
                },
                {
                    "targets": [12],
                    "data": "UPDATE_TIME",
                    "render": function (data, type, full) {
                        return "<a style='text-decoration:none' title='单独出库'  onClick=\"addGoodsOut('" + full.P_SKU + "')\"'>单独出库</a>" +
                                "&nbsp;&nbsp;" +
                                "<a style='text-decoration:none' title='批量出库'  onClick=\"addAllGoodsOut('" + full.P_SKU + "')\"'>批量出库</a>";
                    }
                }
            ],
            "rowCallback": function (row, data, displayIndex) {
                $(row).attr("class", "text-c");
            },
            "initComplete": function (settings, json) {
            },
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
    function addGoodsOut(pSku) {
        layer_show("单独出库",
                "<%=request.getContextPath()%>/assistant/index/stock/goodsOut-add.jsp?pSku=" + pSku, 400, 300);
    }
    function addAllGoodsOut(pSku) {
        layer_show("批量出库",
                "<%=request.getContextPath()%>/assistant/index/stock/goodsOut-addAll.jsp?pSku=" + pSku, 400, 300);
    }
</script>
</body>
</html>