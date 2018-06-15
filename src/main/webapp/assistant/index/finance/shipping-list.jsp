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
    <title>运费列表</title>
</head>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 财务管理 <span
        class="c-gray en">&gt;</span> 运费管理 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px"
                                              href="javascript:location.replace(location.href);" title="刷新"><i
        class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
    <form id="shippingForm" class="form form-horizontal">
        <div class="row cl">
            <label class="form-label col-xs-2 col-sm-2">公司：</label>
            <div class="formControls col-xs-2 col-sm-2">
                <select id="salesCompany" name="salesCompany" class="select" style="height: 32px">
                    <option value="">请选择</option>
                </select>
            </div>
            <label class="form-label col-xs-2 col-sm-2">业务员：</label>
            <div class="formControls col-xs-2 col-sm-2">
                <select id="salesMan" name="salesMan" class="select" style="height: 32px">
                    <option value="">请选择</option>
                </select>
            </div>
            <label class="form-label col-xs-2 col-sm-2">货运公司：</label>
            <div class="formControls col-xs-2 col-sm-2">
                <select id="transportCompany" name="transportCompany" class="select" style="height: 32px">
                    <option value="">请选择</option>
                </select>
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
            <label class="form-label col-xs-2 col-sm-2">国际跟踪号：</label>
            <div class="formControls col-xs-2 col-sm-2">
                <input type="text" name="intlTrackNum" placeholder=" " class="input-text"
                       onkeyup="this.value=this.value.replace(/(^\s+)|(\s+$)/g,'')">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-2 col-sm-2">订单号：</label>
            <div class="formControls col-xs-2 col-sm-2">
                <input type="text" name="amazonOrderId" placeholder=" " class="input-text"
                       onkeyup="this.value=this.value.replace(/(^\s+)|(\s+$)/g,'')">
            </div>
            <div class="formControls col-xs-2 col-sm-2">
                <button id="search" class="btn btn-success" type="button"><i class="Hui-iconfont">&#xe665;</i>
                </button>
            </div>
        </div>
        <input id="status" type="hidden" name="status" value="0">
        <input id="roleId" type="hidden" value="${sessionScope.user.ROLE_ID}">
        <input id="userId" type="hidden" value="${sessionScope.user.USER_ID}">
        <input id="companyId" type="hidden" value="${sessionScope.user.USER_COMPANY}">
    </form>
    <div class="mt-20">
        <div id="btn-div" class="row text-c">
            <a href="javascript:;" onclick="reloadTable(0)" class="btn btn-success radius">未获取</a>
            <a href="javascript:;" onclick="reloadTable(1)" class="btn btn-default radius">已获取</a>
            <a href="javascript:;" onclick="reloadTable(2)" class="btn btn-default radius">已审核</a>
        </div>
        <div class="panel panel-success mt-10">
            <div class="panel-body row cl">
                <div class="formControls col-xs-2 col-sm-2">
                    <label class="form-label f-l">物流运费：</label>
                    <label class="form-label f-l" id="totalShippingPrice">0.00</label>
                </div>
                <div class="formControls col-xs-2 col-sm-2">
                    <label class="form-label f-l">实需运费：</label>
                    <label class="form-label f-l" id="totalFreight">0.00</label>
                </div>
            </div>
        </div>
        <div class="cl pd-5 bg-1 bk-gray mt-10" id="count-div"><span class="l">
            <a class="btn btn-primary radius" href="javascript:;"
               onclick="updateAllShipping('批量修改','<%=request.getContextPath()%>/assistant/index/finance/shipping-allupdate.jsp','800')"><i
                    class="Hui-iconfont">
                &#xe600;</i> 批量修改</a></span></div>
        <table id="shippingTable" class="table table-border table-bordered table-bg table-hover">
            <thead>
            <tr class="text-c">
                <th width="25"><input type="checkbox" value="" name=""></th>
                <th width="100">订单号</th>
                <th width="100">客户单号</th>
                <th width="100">国际跟踪号</th>
                <th width="100">业务员</th>
                <th width="100">公司</th>
                <th width="100">物流运费（元）</th>
                <th width="100">实需运费（元）</th>
                <th width="100">货运公司</th>
                <th width="100">货运公司ID</th>
                <th width="50">状态</th>
                <th width="100">操作人</th>
                <th width="100">创建时间</th>
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
    var shippingTable = null;
    var roleId = $("#roleId").val();
    var userId = $("#userId").val();
    var companyId = $("#companyId").val();
    $(function () {
        initSalesSelect("all");
        var sd = new Date();
        sd.setDate(sd.getDate() - 6);
        $("#logmin").val(sd.format("yyyy-MM-dd"));
        $("#logmax").val(new Date().format("yyyy-MM-dd"));
        shippingTable = initializeTable();
        initSelect();
        $("#search").click(function () {
            shippingTable.ajax.reload();
        });
    });
    function initSelect() {
        $.ajax({
            type: 'POST',
            url: '<%=request.getContextPath()%>/common/getList',
            dataType: 'json',
            data: {
                "code": "transportCompanies"
            },
            success: function (data) {
                if (data.code == 0) {
                    var data = data.data;
                    for (var i = 0; i < data.length; i++) {
                        $("#transportCompany").append($('<option value=' + data[i].ID + '>' + data[i].NAME + '</option>'));
                    }
                }
            },
            error: function (data) {
                layer.msg(data.msg, {icon: 2, time: 1000});
            }
        });
    }
    function initSalesSelect(type) {
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
            },
        });
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
                        $("#salesCompany").append($('<option value=' + data[i].COMPANY_ID + '>' + data[i].COMPANY_NAME + '</option>'));
                    }
                }
            },
            error: function (data) {
                layer.msg(data.msg, {icon: 2, time: 1000});
            }
        });
    }
    /*切换状态查询*/
    var index = 0;
    function reloadTable(id) {
        index = id;
        $("#status").val(id);
        shippingTable.ajax.reload();
        var btnDiv = document.getElementById("btn-div");
        var btns = btnDiv.getElementsByTagName("a");
        for (var i = 0; i < btns.length; i++) {
            if (i == id) {
                btns[i].className = 'btn btn-success radius'
            } else {
                btns[i].className = 'btn btn-default radius'
            }
        }
        selectFees();
    }
    /*初始化table*/
    function initializeTable() {
        selectFees();
        var table = $("#shippingTable").DataTable({
            "processing": true,
            "serverSide": true,
            "pagingType": "full_numbers",
            "ordering": false,
            "searching": false,
            "bLengthChange": false,
            "ajax": {
                "url": "<%=request.getContextPath()%>/finance/selectShippings",
                "type": "POST",
                "data": function (d) {
                    return $.extend({}, d, {
                        "data": JSON.stringify(getFormJson("#shippingForm"))
                    });
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    if (jqXHR.getResponseHeader("sessionstatus") == "timeOut") {
                        top.window.location.replace("<%=request.getContextPath()%>/assistant/index/login.jsp");
                    }
                }
            },
            "columns": [
                {"data": "ORDER_ID"},
                {"data": "ORDER_ID"},
                {"data": "CUST_ORDER_ID"},
                {"data": "TRACKNUMBER"},
                {"data": "SALESMAN_NAME"},
                {"data": "COMPANY_NAME"},
                {"data": "SHIPPINGPRICE"},
                {"data": "FREIGHT"},
                {"data": "TRANS_COMPANY_NAME"},
                {"data": "TRANS_COMPANY_ID"},
                {"data": "STATUS"},
                {"data": "OPERATION_NAME"},
                {"data": "CREATE_TIME"},
                {"data": "STATUS"}
            ],
            "columnDefs": [
                {
                    "targets": [0],
                    "data": "ORDER_ID",
                    "render": function (data, type, full) {
                        return "<input type='checkbox' value=" + full.ID + ">"
                    }
                },
                {
                    "targets": [9],
                    "data": "TRANS_COMPANY_ID",
                    "visible": false
                },
                {
                    "targets": [10],
                    "data": "STATUS",
                    "render": function (data, type, full) {
                        if (data == 0) {
                            return "<div>未获取</div>"
                        } else if (data == 1) {
                            return "<div>已获取</div>"
                        } else if (data == 2) {
                            return "<div>已审核</div>"
                        }
                    }
                },
                {
                    "targets": [12],
                    "data": "CREATE_TIME",
                    "render": function (data, type, full) {
                        return "<div>" + getMyDate(data) + "</div>"
                    }
                },
                {
                    "targets": [13],
                    "data": "STATUS",
                    "render": function (data, type, full) {
                        if (data == 0) {
                            return "<a style='text-decoration:none' title='获取运费' onClick=\"getShippingPrice('" + full.ORDER_ID + "','" + full.TRACKNUMBER + "','" + full.TRANS_COMPANY_ID + "')\">获取运费</a>" +
                                    "&nbsp;&nbsp;" +
                                    "<a style='text-decoration:none' title='修改'  onClick=\"updateShipping('" + full.ORDER_ID + "','" + full.FREIGHT + "','" + full.TRACKNUMBER + "')\">修改</a>";
                        } else if (data == 1) {
                            return "<a style='text-decoration:none' title='修改'  onClick=\"updateShipping('" + full.ORDER_ID + "','" + full.FREIGHT + "','" + full.TRACKNUMBER + "')\">修改</a>" +
                                    "&nbsp;&nbsp;" +
                                    "<a style='text-decoration:none' title='审核' onClick=\"auditShipping('" + full.ORDER_ID + "','" + full.FREIGHT + "')\">审核</a>";
                        } else {
                            return "<a style='text-decoration:none'  id='edit' data-id='" + data + "'  )>已审核</a>";
                        }
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

    /*批量修改*/
    function updateAllShipping(title, url, w) {
        layer_show(title, url, w);
    }
    function updateShipping(orderId, freight, trackNum) {
        layer_show("修改运费", "<%=request.getContextPath()%>/assistant/index/finance/shipping-update.jsp?orderId=" + orderId + "&freight=" + freight + "&trackNum=" + trackNum, 400, 300);
    }
    function auditShipping(orderId, freight) {
        layer.confirm('您确定要审核运费吗？', function (i) {
            layer.load();
            $.ajax({
                type: 'POST',
                url: '<%=request.getContextPath()%>/finance/auditShipping',
                dataType: 'json',
                data: {
                    "orderId": orderId,
                    "freight": freight
                },
                success: function (data) {
                    layer.closeAll("loading");
                    if (data.code == 0) {
                        layer.closeAll('dialog');
                        reloadTable(index)
                    } else {
                        layer.msg(data.msg, {icon: 2, time: 2000});
                    }
                },
                error: function (data) {
                    layer.closeAll("loading");
                    layer.msg("请求错误！", {icon: 2, time: 2000});
                }
            });
        });
    }
    function getShippingPrice(orderId, custId, companyId) {
        layer.load();
        $.ajax({
            type: 'POST',
            url: '<%=request.getContextPath()%>/finance/getShippingPrice',
            dataType: 'json',
            data: {
                "orderId": orderId,
                "custId": custId,
                "companyId": companyId
            },
            success: function (data) {
                layer.closeAll("loading");
                layer.msg(data.msg, {icon: 2, time: 2000});
                if (data.code == 0) {
                    reloadTable(index)
                }
            },
            error: function (data) {
                layer.closeAll("loading");
                layer.msg("请求错误！", {icon: 2, time: 2000});
            }
        });
    }

    /*
     查询运费总费用明细
     */
    function selectFees() {
        $.ajax({
            type: 'POST',
            url: '<%=request.getContextPath()%>/finance/selectFees',
            dataType: 'json',
            data: {
                "data": JSON.stringify(getFormJson("#shippingForm"))
            },
            success: function (data) {
                if (data.code == 0) {
                    var data = data.data;
                    $("#totalShippingPrice").html(data.TOTALSHIPPINGPRICE);
                    $("#totalFreight").html(data.TOTALFREIGHT);
                } else {
                    layer.msg(data.msg, {icon: 2, time: 1000});
                }
            },
            error: function (data) {
                layer.msg(data.msg, {icon: 2, time: 1000});
            }
        });
    }
</script>
</body>
</html>