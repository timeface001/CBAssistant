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
    <title>充值列表</title>
</head>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 财务管理 <span
        class="c-gray en">&gt;</span> 充值列表 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px"
                                              href="javascript:location.replace(location.href);" title="刷新"><i
        class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
    <form id="rechargeForm" class="form form-horizontal">
        <input id="roleId" type="hidden" value="${sessionScope.user.ROLE_ID}">
        <div class="row cl">
            <label class="form-label col-xs-2 col-sm-2">用户公司：</label>
            <div class="formControls col-xs-2 col-sm-2">
                <select id="companyId" name="companyId" class="select" style="height: 32px">
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
        </div>
        <div class="text-c cl row">
            <button id="search" class="btn btn-success" type="button"><i class="Hui-iconfont">&#xe665;</i> 搜索
            </button>
        </div>
    </form>
    <div class="mt-20">
        <div class="cl pd-5 bg-1 bk-gray mt-10" id="count-div"><span class="l">
            <a class="btn btn-primary radius" href="javascript:;"
               onclick="recharge('充值','<%=request.getContextPath()%>/assistant/index/finance/recharge-add.jsp','800')"><i
                    class="Hui-iconfont">
                &#xe600;</i> 充值</a>
            <a class="btn btn-primary radius" href="javascript:;"
               onclick="qrcode('扫码支付（支付宝/微信）','<%=request.getContextPath()%>/assistant/index/finance/qrcode.jsp','800')"><i
                    class="Hui-iconfont">
                &#xe500;</i> 扫码支付（支付宝/微信）</a> </span></div>
        <table id="rechargeTable" class="table table-border table-bordered table-bg table-hover">
            <thead>
            <tr class="text-c">
                <th width="100">流水号</th>
                <th width="100">用户公司</th>
                <th width="100">账户余额</th>
                <th width="100">账户可用余额</th>
                <th width="100">汇款方式</th>
                <th width="100">收款账号</th>
                <th width="100">充值金额</th>
                <th width="100">充值凭证</th>
                <th width="100">状态</th>
                <th width="100">备注</th>
                <th width="100">汇款人</th>
                <th width="100">汇款时间</th>
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
    var rechargeTable = null;
    var roleId = $("#roleId").val();
    $(function () {
        initSelect();
        var sd = new Date();
        sd.setDate(sd.getDate() - 6);
        $("#logmin").val(sd.format("yyyy-MM-dd"));
        $("#logmax").val(new Date().format("yyyy-MM-dd"));
        rechargeTable = initializeTable();
        $("#search").click(function () {
            rechargeTable.ajax.reload();
        });
    });
    function initSelect() {
        if (roleId != 100) {
            $("#companyId").empty();
            $("#companyId").append($("<option value='${sessionScope.user.USER_COMPANY}'>${sessionScope.user.COMPANY_NAME}</option>"));
        } else {
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
                            $("#companyId").append($('<option value=' + data[i].COMPANY_ID + '>' + data[i].COMPANY_NAME + '</option>'));
                        }
                    }
                },
                error: function (data) {
                    layer.msg(data.msg, {icon: 2, time: 1000});
                }
            });
        }
    }
    function recharge(title, url, w) {
        layer_show(title, url, w);
    }
    function qrcode(title, url, w){
        layer_show(title, url, w);
    }
    /*查询充值*/
    function reloadTable() {
        rechargeTable.ajax.reload(function (data) {

        }, false);
    }
    /*初始化table*/
    function initializeTable() {
        var table = $("#rechargeTable").DataTable({
            "processing": true,
            "serverSide": true,
            "pagingType": "full_numbers",
            "ordering": false,
            "searching": false,
            "bLengthChange": false,
            "ajax": {
                "url": "<%=request.getContextPath()%>/account/selectRecharges",
                "type": "POST",
                "data": function (d) {
                    return $.extend({}, d, {
                        "data": JSON.stringify(getFormJson("#rechargeForm"))
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
                {"data": "COMPANY_NAME"},
                {"data": "BALANCE"},
                {"data": "AVAILABLE_BALANCE"},
                {"data": "REMITTANCE_MODE"},
                {"data": "RECEIVABLE_ACCOUNT"},
                {"data": "RECHARGE_AMOUNT"},
                {"data": "VOUCHER"},
                {"data": "STATUS"},
                {"data": "REMARK"},
                {"data": "USER_NAME"},
                {"data": "REMITTANCE_DATE"},
                {"data": "STATUS"}
            ],
            "columnDefs": [
                {
                    "targets": [4],
                    "data": "REMITTANCE_MODE",
                    "render": function (data, type, full) {
                        if (data == 1) {
                            return "<div>银联</div>";
                        } else if (data == 2) {
                            return "<div>支付宝</div>";
                        } else if (data == 3) {
                            return "<div>微信</div>";
                        }
                    }
                },
                {
                    "targets": [5],
                    "data": "RECEIVABLE_ACCOUNT",
                    "render": function (data, type, full) {
                        if (data == 1) {
                            return "<div>招商银行太原府西街支行/张永军/6214 8335 1693 6849</div>";
                        } else if (data == 2) {
                            return "<div>支付宝：15034048635</div>";
                        } else if (data == 3) {
                            return "<div>微信：15034048635</div>";
                        }
                    }
                },
                {
                    "targets": [7],
                    "data": "VOUCHER",
                    "render": function (data, type, full) {
                        return data == null ? "" : "<img width='100px' height='90px'  id='" + full.ID + "' onmousemove=\"moveBig()\" onmouseout=\"hiddenBig()\" onmouseover=\"showBig('" + full.ID + "','" + data + "')\" src='<%=session.getAttribute("productPath")%>" + data + "'/>";
                    }
                },
                {
                    "targets": [8],
                    "data": "STATUS",
                    "render": function (data, type, full) {
                        if (data == 1) {
                            return "<div>审核中</div>";
                        } else if (data == 2) {
                            return "<div>审核通过</div>";
                        } else if (data == 3) {
                            return "<div>审核失败</div>";
                        }
                    }
                },
                {
                    "targets": [11],
                    "data": "REMITTANCE_DATE",
                    "render": function (data, type, full) {
                        return "<div>" + getDate(data) + "</div>"
                    }
                },
                {
                    "targets": [12],
                    "data": "STATUS",
                    "render": function (data, type, full) {
                        if (roleId == 100) {
                            if (data == 1) {
                                return "<a style='text-decoration:none' title='通过'  onClick=\"auditRecharge('" + full.ID + "','" + 2 + "')\"'>通过</a>" +
                                        "&nbsp;&nbsp;" +
                                        "<a style='text-decoration:none' title='不通过'  onClick=\"auditRecharge('" + full.ID + "','" + 3 + "')\"'>不通过</a>";
                            } else {
                                return "";
                            }
                        } else {
                            return "";
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
    function auditRecharge(id, status) {
        var msg = "您确定要通过该充值吗？";
        if (status == 2) {
            msg = "您确定要通过该充值吗？";
        } else {
            msg = "您确定不通过该充值吗？";
        }
        layer.confirm(msg, function (i) {
            $.ajax({
                type: 'POST',
                url: '<%=request.getContextPath()%>/account/auditRecharge',
                dataType: 'json',
                data: {
                    "id": id,
                    "status": status
                },
                success: function (data) {
                    layer.closeAll("loading");
                    if (data.code == 0) {
                        layer.msg(data.msg, {icon: 1, time: 1000});
                        reloadTable();
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
        var bigDiv = "<div id='bigDiv' style='position: absolute;width: 300px;height: 300px'><img src='<%=session.getAttribute("productPath")%>" + url + "' width='300px' height='300px' /></div>";
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
    //将时间戳格式化
    function getDate(time) {
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
                oTime = oYear + '-' + getzf(oMonth) + '-' + getzf(oDay);//最后拼接时间
        return oTime;
    }
</script>
</body>
</html>