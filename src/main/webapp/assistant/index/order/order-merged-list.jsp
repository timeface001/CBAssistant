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
    <title>已合并</title>
</head>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 订单管理 <span
        class="c-gray en">&gt;</span> 已合并 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px"
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
            <label class="form-label col-xs-2 col-sm-2"></label>
            <div class="formControls col-xs-2 col-sm-2">
                <button id="search" class="btn btn-success" type="button"><i class="Hui-iconfont">&#xe665;</i> 搜订单
                </button>
            </div>
        </div>
        <input id="localStatus" type="hidden" name="localStatus" value="1">
        <input id="roleId" type="hidden" value="${sessionScope.user.ROLE_ID}">
        <input id="userId" type="hidden" value="${sessionScope.user.USER_ID}">
        <input id="companyId" type="hidden" value="${sessionScope.user.USER_COMPANY}">
    </form>
    <div class="mt-20">
        <table id="orderTable" class="table table-border table-bordered table-bg table-hover">
            <thead>
            <tr class="text-c">
                <th width="80">序号</th>
                <th width="100">产品图</th>
                <th width="100">合并ID</th>
                <th width="100">亚马逊订单号</th>
                <th width="100">收件人邮箱</th>
                <th width="100">收件地址1</th>
                <th width="100">收件地址2</th>
                <th width="100">收件地址3</th>
                <th width="100">创建人</th>
                <th width="100">创建时间</th>
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
        initSelect();
        $("#downloadOrder").click(function () {
            downloadOrder();
        });
        $("#search").click(function () {
            orderTable.ajax.reload();
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
    function reloadTable() {
        layer.load();
        orderTable.ajax.reload();
    }
    /*初始化table*/
    function initializeTable() {
        var table = $("#orderTable").DataTable({
            "processing": true,
            "serverSide": true,
            "pagingType": "full_numbers",
            "ordering": false,
            "searching": false,
            "bLengthChange": false,
            "ajax": {
                "url": "<%=request.getContextPath()%>/order/selectMergedOrder",
                "type": "POST",
                "data": function (d) {
                    layer.closeAll("loading");
                    return $.extend({}, d, {
                        "data": JSON.stringify(getFormJson("#orderForm"))
                    });
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    layer.closeAll("loading");
                    if (jqXHR.getResponseHeader("sessionstatus") == "timeOut") {
                        top.window.location.replace("<%=request.getContextPath()%>/assistant/index/login.jsp");
                    }
                }
            },
            "columns": [
                {"data": "ID"},
                {"data": "SMALLIMAGE"},
                {"data": "MERGEDID"},
                {"data": "AMAZONORDERID"},
                {"data": "BUYEREMAIL"},
                {"data": "ADDRESSLINE1"},
                {"data": "ADDRESSLINE2"},
                {"data": "ADDRESSLINE3"},
                {"data": "USER_NAME"},
                {"data": "CREATE_TIME"}
            ],
            "columnDefs": [
                {
                    "targets": [0],
                    "data": "ID",
                    "render": function (data, type, full) {
                        return "<a class='maincolor' href='javascript:;' onClick=\"toDetail('" + full.MERGEDID + "')\"'>" + data + "</a>";
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
                    "data": "CREATE_TIME",
                    "render": function (data, type, full) {
                        return "<div>" + getMyDate(data) + "</div>"
                    }
                },
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
    /*打开订单详情页*/
    function toDetail(mergedId) {
        var index = layer.open({
            type: 2,
            title: '订单详情',
            content: 'order-Detail.jsp?amazonOrderId=' + mergedId,
            end: function () {
                /*location.reload();*/
            }
        });
        layer.full(index);
    }
</script>
</body>
</html>