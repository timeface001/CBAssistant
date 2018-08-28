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
    <title>订单列表</title>
</head>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 订单管理 <span
        class="c-gray en">&gt;</span> 订单列表 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px"
                                              href="javascript:location.replace(location.href);" title="刷新"><i
        class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
    <form id="orderForm" class="form form-horizontal">
        <div class="row cl">
            <label class="form-label col-xs-2 col-sm-2">公司：</label>
            <div class="formControls col-xs-2 col-sm-2">
                <select id="salesCompany" name="salesCompany" class="select" style="height: 32px"
                        onchange="loadUsers(this.value)">
                    <option value="">请选择</option>
                </select>
            </div>
            <label class="form-label col-xs-2 col-sm-2">业务员：</label>
            <div class="formControls col-xs-2 col-sm-2">
                <select id="salesMan" name="salesMan" class="select" style="height: 32px">
                    <option value="">请选择</option>
                </select>
            </div>
            <label class="form-label col-xs-2 col-sm-2">国家：</label>
            <div class="formControls col-xs-2 col-sm-2">
                <select id="buyerCounty" name="buyerCounty" class="select" style="height: 32px">
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
            <label class="form-label col-xs-2 col-sm-2">销售来源：</label>
            <div class="formControls col-xs-2 col-sm-2">
                <select id="salesSource" name="salesSource" class="select" style="height: 32px">
                    <option value="">请选择</option>
                </select>
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-2 col-sm-2">SKU：</label>
            <div class="formControls col-xs-2 col-sm-2">
                <input type="text" name="sku" placeholder=" " class="input-text"
                       onkeyup="this.value=this.value.replace(/(^\s+)|(\s+$)/g,'')">
            </div>
            <label class="form-label col-xs-2 col-sm-2">订单号：</label>
            <div class="formControls col-xs-2 col-sm-2">
                <input type="text" name="amazonOrderId" placeholder=" " class="input-text"
                       onkeyup="this.value=this.value.replace(/(^\s+)|(\s+$)/g,'')">
            </div>
            <label class="form-label col-xs-2 col-sm-2">货运公司：</label>
            <div class="formControls col-xs-2 col-sm-2">
                <select id="transportCompany" name="transportCompany" class="select" style="height: 32px">
                    <option value="">请选择</option>
                </select>
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-2 col-sm-2">国内跟踪号：</label>
            <div class="formControls col-xs-2 col-sm-2">
                <input type="text" name="trackNum" placeholder=" " class="input-text"
                       onkeyup="this.value=this.value.replace(/(^\s+)|(\s+$)/g,'')">
            </div>
            <label class="form-label col-xs-2 col-sm-2">国际跟踪号：</label>
            <div class="formControls col-xs-2 col-sm-2">
                <input type="text" name="intlTrackNum" placeholder=" " class="input-text"
                       onkeyup="this.value=this.value.replace(/(^\s+)|(\s+$)/g,'')">
            </div>
        </div>
        <input id="localStatus" type="hidden" name="localStatus" value="1">
        <input id="roleId" type="hidden" value="${sessionScope.user.ROLE_ID}">
        <input id="userId" type="hidden" value="${sessionScope.user.USER_ID}">
        <input id="companyId" type="hidden" value="${sessionScope.user.USER_COMPANY}">
        <div class="text-c cl row">
            <button id="search" class="btn btn-success" type="button"><i class="Hui-iconfont">&#xe665;</i> 搜订单
            </button>
            <button id="downloadOrder" class="btn btn-success" type="button"><i
                    class="Hui-iconfont">
                &#xe665;</i> 下载订单
            </button>
        </div>
    </form>
    <div class="mt-20">
        <div id="btn-div" class="row text-c">
            <c:choose>
                <c:when test="${sessionScope.user.ROLE_ID eq '400'}">
                    <a href="javascript:;" onclick="reloadTable(1)" class="btn btn-success radius">备货</a>
                    <a href="javascript:;" onclick="reloadTable(3)" class="btn btn-default radius">发货</a>
                </c:when>
                <c:otherwise>
                    <a href="javascript:;" onclick="reloadTable(0)" class="btn btn-success radius">新单</a>
                    <a href="javascript:;" onclick="reloadTable(1)" class="btn btn-default radius">备货</a>
                    <a href="javascript:;" onclick="reloadTable(2)" class="btn btn-default radius">缺货</a>
                    <a href="javascript:;" onclick="reloadTable(3)" class="btn btn-default radius">发货</a>
                    <a href="javascript:;" onclick="reloadTable(4)" class="btn btn-default radius">问题</a>
                    <a href="javascript:;" onclick="reloadTable(5)" class="btn btn-default radius">退款</a>
                    <a href="javascript:;" onclick="reloadTable(6)" class="btn btn-default radius">妥投</a>
                    <a href="javascript:;" onclick="reloadTable(7)" class="btn btn-default radius">代发</a>
                    <a href="javascript:;" onclick="reloadTable(8)" class="btn btn-default radius">全部</a>
                    <a href="javascript:;" onclick="reloadTable(9)" class="btn btn-default radius">FBA</a>
                </c:otherwise>
            </c:choose>
        </div>
        <div class="panel panel-success mt-10">
            <div class="panel-body row cl">
                <div class="formControls col-xs-2 col-sm-2">
                    <label class="form-label f-l">销售额：</label>
                    <label class="form-label f-l" id="total">0.00</label>
                </div>
                <div class="formControls col-xs-2 col-sm-2">
                    <label class="form-label f-l">成本：</label>
                    <label class="form-label f-l" id="cost">0.00</label>
                </div>
                <div class="formControls col-xs-2 col-sm-2">
                    <label class="form-label f-l">运费：</label>
                    <label class="form-label f-l" id="shippingPrice">0.00</label>
                </div>
                <div class="formControls col-xs-2 col-sm-2">
                    <label class="form-label f-l">退款：</label>
                    <label class="form-label f-l" id="refund">0.00</label>
                </div>
                <div class="formControls col-xs-2 col-sm-2">
                    <label class="form-label f-l">利润：</label>
                    <label class="form-label f-l" id="profit">0.00</label>
                </div>
            </div>
        </div>
        <c:if test="${sessionScope.user.ROLE_ID eq '100'}">
            <div class="cl pd-5 bg-1 bk-gray mt-10" id="count-div"><span class="l">
            <a class="btn btn-primary radius" href="javascript:;"
               onclick="addOrder('添加订单','<%=request.getContextPath()%>/assistant/index/order/order-add.jsp','800')"><i
                    class="Hui-iconfont">
                &#xe600;</i> 添加订单</a></span></div>
        </c:if>
        <c:if test="${sessionScope.user.ROLE_ID eq '200'}">
            <div class="cl pd-5 bg-1 bk-gray mt-10" id="count-div"><span class="l">
            <a class="btn btn-primary radius" href="javascript:;"
               onclick="addOrder('添加订单','<%=request.getContextPath()%>/assistant/index/order/order-add.jsp','800')"><i
                    class="Hui-iconfont">
                &#xe600;</i> 添加订单</a> </span></div>
        </c:if>
        <table id="orderTable" class="table table-border table-bordered table-bg table-hover">
            <thead>
            <tr class="text-c">
                <th width="80">序号</th>
                <th width="100">产品图</th>
                <th width="100">亚马逊订单号</th>
                <th width="100">SKU</th>
                <th width="100">业务员</th>
                <th width="150">公司</th>
                <th width="60">运行时间</th>
                <th width="100">销售来源</th>
                <th width="100">国家</th>
                <th width="50">销售额（元）</th>
                <th width="50">成本（元）</th>
                <th width="50">运费（元）</th>
                <th width="50">退款额（元）</th>
                <th width="50">利润（元）</th>
                <th width="100">货运公司</th>
                <th width="100">国际跟踪号</th>
                <th width="50">渠道</th>
                <th width="50">状态</th>
                <th width="100">创建时间</th>
                <th width="90">操作</th>
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
    var orderTable = null;
    var roleId = $("#roleId").val();
    var userId = $("#userId").val();
    var companyId = $("#companyId").val();
    $(function () {
        if (roleId == 100 || roleId == 200) {
            initSalesSelect("all");
        } else if (roleId == 400) {
            $("#localStatus").val(2);
            initSalesSelect("all");
        } else if (roleId == 500) {
            $("#salesCompany").empty();
            $("#salesCompany").append($("<option value='${sessionScope.user.USER_COMPANY}'>${sessionScope.user.COMPANY_NAME}</option>"));
            initSalesSelect("owner");
        } else if (roleId == 600) {
            $("#salesMan").empty();
            $("#salesCompany").empty();
            $("#salesMan").append($("<option value='${sessionScope.user.USER_ID}'>${sessionScope.user.USER_NAME}</option>"));
            $("#salesCompany").append($("<option value='${sessionScope.user.USER_COMPANY}'>${sessionScope.user.COMPANY_NAME}</option>"));
            initSalesSource();
        }
        var sd = new Date();
        sd.setDate(sd.getDate() - 6);
        $("#logmin").val(sd.format("yyyy-MM-dd"));
        $("#logmax").val(new Date().format("yyyy-MM-dd"));
        orderTable = initializeTable();
        selectFees();
        initSelect();
        $("#downloadOrder").click(function () {
            downloadOrder();
        });
        $("#search").click(function () {
            orderTable.ajax.reload();
            selectFees();
        });
    });
    function initSelect() {
        $.ajax({
            type: 'POST',
            url: '<%=request.getContextPath()%>/common/getList',
            dataType: 'json',
            data: {
                "code": "countries"
            },
            success: function (data) {
                if (data.code == 0) {
                    var data = data.data;
                    for (var i = 0; i < data.length; i++) {
                        $("#buyerCounty").append($('<option value=' + data[i].ID + '>' + data[i].NAME + '</option>'));
                    }
                }
            },
            error: function (data) {
                layer.msg(data.msg, {icon: 2, time: 1000});
            }
        });
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
            $.ajax({
                type: 'POST',
                url: '<%=request.getContextPath()%>/common/getList',
                dataType: 'json',
                data: {
                    "code": "shops"
                },
                success: function (data) {
                    if (data.code == 0) {
                        var data = data.data;
                        for (var i = 0; i < data.length; i++) {
                            $("#salesSource").append($('<option value=' + data[i].MERCHANT_ID + '>' + data[i].SHOP_NAME + '</option>'));
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
            /*$.ajax({
             type: 'POST',
             url: '
            <%=request.getContextPath()%>/system/selectCompanies',
             dataType: 'json',
             data: {
             "id": companyId
             },
             success: function (data) {
             if (data.code == 0) {
             var data = data.data;
             $("#salesCompany").empty();
             for (var i = 0; i < data.length; i++) {
             $("#salesCompany").append($('<option value=' + data[i].COMPANY_ID + '>' + data[i].COMPANY_NAME + '</option>'));
             }
             /!*var salesCompany = document.getElementById('salesCompany');
             salesCompany[0].selected = true;*!/
             }
             },
             error: function (data) {
             layer.msg(data.msg, {icon: 2, time: 1000});
             }
             });*/
            $.ajax({
                type: 'POST',
                url: '<%=request.getContextPath()%>/shop/selectShopsById',
                dataType: 'json',
                success: function (data) {
                    if (data.code == 0) {
                        var data = data.data;
                        for (var i = 0; i < data.length; i++) {
                            $("#salesSource").append($('<option value=' + data[i].MERCHANT_ID + '>' + data[i].SHOP_NAME + '</option>'));
                        }
                    }
                },
                error: function (data) {
                    layer.msg(data.msg, {icon: 2, time: 1000});
                }
            });
        }
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
                    $("#salesMan").empty();
                    $("#salesMan").append($("<option value=''>请选择</option>"));
                    for (var i = 0; i < data.length; i++) {
                        $("#salesMan").append($('<option value=' + data[i].USER_ID + '>' + data[i].USER_NAME + '</option>'));
                    }
                }
            },
            error: function (data) {
                layer.msg(data.msg, {icon: 2, time: 2000});
            }
        });
    }
    /*查询个人授权店铺*/
    function initSalesSource() {
        $.ajax({
            type: 'POST',
            url: '<%=request.getContextPath()%>/shop/selectShopsById',
            dataType: 'json',
            success: function (data) {
                if (data.code == 0) {
                    var data = data.data;
                    for (var i = 0; i < data.length; i++) {
                        $("#salesSource").append($('<option value=' + data[i].MERCHANT_ID + '>' + data[i].SHOP_NAME + '</option>'));
                    }
                }
            },
            error: function (data) {
                layer.msg(data.msg, {icon: 2, time: 1000});
            }
        });
    }
    /*查询订单*/
    var isShowStatus = false;
    var index = 0;
    function reloadTable(id) {
        layer.load();
        index = id;
        if (id == 8) {
            isShowStatus = true;
            document.getElementById('localStatus').value = "";
        } else {
            $("#localStatus").val(id + 1);
        }
        orderTable.ajax.reload();
        selectFees();
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
        var vis = true;
        if (roleId == 400) {
            vis = false;
        }
        var table = $("#orderTable").DataTable({
            "processing": true,
            "serverSide": true,
            "pagingType": "full_numbers",
            "ordering": false,
            "searching": false,
            "bLengthChange": false,
            "ajax": {
                "url": "<%=request.getContextPath()%>/order/selectLocalOrder",
                "type": "POST",
                "data": function (d) {
                    return $.extend({}, d, {
                        "data": JSON.stringify(getFormJson("#orderForm"))
                    });
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    if (jqXHR.getResponseHeader("sessionstatus") == "timeOut") {
                        top.window.location.replace("<%=request.getContextPath()%>/assistant/index/login.jsp");
                    }
                }
            },
            "columns": [
                {"data": "ID"},
                {"data": "SMALLIMAGE"},
                {"data": "AMAZONORDERID"},
                {"data": "SKU"},
                {"data": "USER_NAME"},
                {"data": "COMPANY_NAME"},
                {"data": "RUNNINGTIME"},
                {"data": "SHOP_NAME"},
                {"data": "COUNTRYNAME"},
                {"data": "ITEMPRICE"},
                {"data": "COST"},
                {"data": "SHIPPINGPRICE"},
                {"data": "REFUNDMENT"},
                {"data": "PROFIT"},
                {"data": "TRANSPORTCOMPANY"},
                {"data": "INTLTRACKNUM"},
                {"data": "FULFILLMENTCHANNEL"},
                {"data": "LOCALSTATUS"},
                {"data": "LOCALSTATUS"},
                {"data": "LOCALSTATUS"}
            ],
            "columnDefs": [
                {
                    "targets": [0],
                    "data": "ID",
                    "render": function (data, type, full) {
                        return "<a class='maincolor' href='javascript:;' onClick=\"toDetail('" + full.AMAZONORDERID + "')\"'>" + data + "</a>";
                    }
                },
                {
                    "targets": [1],
                    "data": "SMALLIMAGE",
                    "render": function (data, type, full) {
                        if (data != null) {
                            return "<img id='" + full.ID + "' onmousemove=\"moveBig()\" onmouseout=\"hiddenBig()\" onmouseover=\"showBig('" + full.ID + "','" + data.split(",")[0] + "')\"src='" + data.split(",")[0] + "'/>";
                        } else {
                            return "<img  src='" + data + "'/>";
                        }
                    }
                },
                {
                    "targets": [9],
                    "data": "ITEMPRICE",
                    "visible": vis
                },
                {
                    "targets": [10],
                    "data": "COST",
                    "visible": vis
                },
                {
                    "targets": [11],
                    "data": "SHIPPINGPRICE",
                    "visible": vis
                },
                {
                    "targets": [12],
                    "data": "REFUNDMENT",
                    "visible": vis
                },
                {
                    "targets": [13],
                    "data": "PROFIT",
                    "visible": vis
                },
                {
                    "targets": [17],
                    "data": "LOCALSTATUS",
                    "render": function (data, type, full) {
                        if (data == 1) {
                            return "<div>新单</div>";
                        } else if (data == 2) {
                            return "<div>备货</div>";
                        } else if (data == 3) {
                            return "<div>缺货</div>";
                        } else if (data == 4) {
                            return "<div>发货</div>";
                        } else if (data == 5) {
                            return "<div>问题</div>";
                        } else if (data == 6) {
                            return "<div>退款</div>";
                        } else if (data == 7) {
                            return "<div>妥投</div>";
                        } else if (data == 8) {
                            return "<div>代发</div>";
                        }
                    }
                },
                {
                    "targets": [18],
                    "data": "LOCALSTATUS",
                    "render": function (data, type, full) {
                        if (data == 4 && full.FULFILLMENTCHANNEL != "FBA") {
                            return "<div>" + full.UPDATE_TIME + "</div>";
                        } else {
                            return "<div>" + full.PURCHASEDATE + "</div>";
                        }
                    }
                },
                {
                    "targets": [19],
                    "data": "LOCALSTATUS",
                    "render": function (data, type, full) {
                        if (roleId == 100) {
                            return "<a style='text-decoration:none' title='获取佣金'  onClick=\"getCommission('" + full.AMAZONORDERID + "','" + full.MERCHANT_ID + "','" + full.MARKETPLACEID + "')\"'>获取佣金</a>" +
                                    "&nbsp;&nbsp;" +
                                    "<a style='text-decoration:none' title='克隆'  onClick=\"cloneOrder('" + full.AMAZONORDERID + "')\"'>克隆</a>" +
                                    "&nbsp;&nbsp;" +
                                    "<a style='text-decoration:none' title='删除'  onClick=\"delOrder('" + full.AMAZONORDERID + "')\"'>删除</a>";
                        } else {
                            return "<a style='text-decoration:none' title='获取佣金'  onClick=\"getCommission('" + full.AMAZONORDERID + "','" + full.MERCHANT_ID + "','" + full.MARKETPLACEID + "')\"'>获取佣金</a>" +
                                    "&nbsp;&nbsp;" +
                                    "<a style='text-decoration:none' title='克隆'  onClick=\"cloneOrder('" + full.AMAZONORDERID + "')\"'>克隆</a>";
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
    /*
     查询订单总费用明细
     */
    function selectFees() {
        $.ajax({
            type: 'POST',
            url: '<%=request.getContextPath()%>/order/selectFees',
            dataType: 'json',
            data: {
                "data": JSON.stringify(getFormJson("#orderForm"))
            },
            success: function (data) {
                layer.closeAll("loading");
                if (data.code == 0) {
                    var data = data.data;
                    $("#total").html(data.ITEMPRICE);
                    $("#cost").html(data.COST);
                    $("#shippingPrice").html(data.SHIPPINGPRICE);
                    $("#refund").html(data.REFUNDMENT);
                    $("#profit").html(data.PROFIT);
                } else {
                    layer.msg(data.msg, {icon: 2, time: 1000});
                }
            },
            error: function (data) {
                layer.msg(data.msg, {icon: 2, time: 1000});
            }
        });
    }
    /*订单-添加*/
    function addOrder(title, url, w) {
        layer_show(title, url, w);
    }
    /*下载订单*/
    function downloadOrder() {
        layer.load();
        $.ajax({
            type: 'POST',
            url: '<%=request.getContextPath()%>/order/downloadOrder',
            dataType: 'json',
            data: {
                "data": JSON.stringify(getFormJson("#orderForm"))
            },
            success: function (data) {
                layer.closeAll("loading");
                if (data.code == 0) {
                    layer.msg(data.msg, {icon: 1, time: 1000});
                    reloadTable(index)
                } else {
                    layer.msg(data.msg, {icon: 2, time: 1000});
                }
            },
            error: function (data) {
                layer.closeAll("loading");
                layer.msg(data.msg, {icon: 2, time: 1000});
            }
        });
    }
    /*打开订单详情页*/
    function toDetail(amazonOrderId) {
        var index = layer.open({
            type: 2,
            title: '订单详情',
            content: 'order-Detail.jsp?amazonOrderId=' + amazonOrderId,
            end: function () {
                /*location.reload();*/
            }
        });
        layer.full(index);
    }
    function delOrder(amazonOrderId) {
        layer.confirm('您确定要删除订单吗？', function (i) {
            $.ajax({
                type: 'POST',
                url: '<%=request.getContextPath()%>/order/delOrder',
                dataType: 'json',
                data: {
                    "amazonOrderId": amazonOrderId
                },
                success: function (data) {
                    layer.closeAll("loading");
                    if (data.code == 0) {
                        layer.msg(data.msg, {icon: 1, time: 1000});
                        reloadTable(index)
                    } else {
                        layer.msg(data.msg, {icon: 2, time: 1000});
                    }
                },
                error: function (data) {
                    layer.msg(data.msg, {icon: 2, time: 1000});
                }
            });
        });
    }
    function cloneOrder(amazonOrderId) {
        layer.confirm('您确定要克隆订单吗？', function (i) {
            $.ajax({
                type: 'POST',
                url: '<%=request.getContextPath()%>/order/cloneOrder',
                dataType: 'json',
                data: {
                    "amazonOrderId": amazonOrderId
                },
                success: function (data) {
                    layer.closeAll("loading");
                    if (data.code == 0) {
                        layer.msg(data.msg, {icon: 1, time: 1000});
                        reloadTable(index)
                    } else {
                        layer.msg(data.msg, {icon: 2, time: 1000});
                    }
                },
                error: function (data) {
                    layer.msg(data.msg, {icon: 2, time: 1000});
                }
            });
        });
    }
    function getCommission(amazonOrderId, merchantId, marketplaceId) {
        layer.confirm('您确定要获取佣金吗？', function (i) {
            layer.load();
            $.ajax({
                type: 'POST',
                url: '<%=request.getContextPath()%>/order/getCommission',
                dataType: 'json',
                data: {
                    "amazonOrderId": amazonOrderId,
                    "merchantId": merchantId,
                    "marketplaceId": marketplaceId
                },
                success: function (data) {
                    layer.closeAll("loading");
                    if (data.code == 0) {
                        layer.msg(data.msg, {icon: 1, time: 1000});
                        reloadTable(index)
                    } else {
                        layer.msg(data.msg, {icon: 2, time: 1000});
                    }
                },
                error: function (data) {
                    layer.msg(data.msg, {icon: 2, time: 1000});
                }
            });
        });
    }
    var x = 10;
    var y = -300;
    function showBig(id, url, e) {
        e = window.event || e;
        url = url.replace("SL75");
        var bigDiv = "<div id='bigDiv' style='position: absolute;width: 300px;height: 300px'><img src='" + url + "' width='300px' height='300px' /></div>";
        $("body").append(bigDiv);
        $("#bigDiv").css({
            "top": (e.pageY + y) + "px",
            "left": (e.pageX + x) + "px"
        }).show("fast");
    }
    function hiddenBig() {
        $("#bigDiv").remove();
    }
    function moveBig(e) {
        e = window.event || e;
        $("#bigDiv").css({
            "top": (e.pageY + y) + "px",
            "left": (e.pageX + x) + "px"
        });
    }
</script>
</body>
</html>