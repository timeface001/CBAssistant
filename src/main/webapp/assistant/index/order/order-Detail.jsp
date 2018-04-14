<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String amazonOrderId = request.getParameter("amazonOrderId");
%>
<!DOCTYPE HTML>
<html>
<head>
    <title>订单详情</title>
    <meta charset="utf-8">
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no"/>
    <meta http-equiv="Cache-Control" content="no-siteapp"/>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/assistant/static/h-ui/css/H-ui.min.css"/>
    <link rel="stylesheet" type="text/css"
          href="<%=request.getContextPath()%>/assistant/static/h-ui.admin/css/H-ui.admin.css"/>
    <link rel="stylesheet" type="text/css"
          href="<%=request.getContextPath()%>/assistant/lib/Hui-iconfont/1.0.8/iconfont.css"/>
    <link rel="stylesheet" type="text/css"
          href="<%=request.getContextPath()%>/assistant/static/h-ui.admin/skin/default/skin.css" id="skin"/>
    <link rel="stylesheet" type="text/css"
          href="<%=request.getContextPath()%>/assistant/static/h-ui.admin/css/style.css"/>
</head>
<body>
<div class="panel panel-success">
    <div class="panel-header">业务来源</div>
    <div class="panel-body">
        <div class="row cl">
            <label class=" col-xs-1 col-sm-1 text-r">公司：</label>
            <div class=" col-xs-2 col-sm-2">
                <input type="text" id="salesCompany" placeholder=" "
                       class="input-text" readonly></div>
            <label class=" col-xs-2 col-sm-2 text-r">业务员：</label>
            <div class=" col-xs-2 col-sm-2">
                <input type="text" id="salesMan" placeholder=" "
                       class="input-text" readonly></div>
            <label class=" col-xs-2 col-sm-2 text-r">销售来源：</label>
            <div class=" col-xs-2 col-sm-2">
                <input type="text" id="salesSource" placeholder=" "
                       class="input-text" readonly></div>
        </div>
    </div>
</div>
<div class="panel panel-success">
    <div class="panel-header">收货人信息</div>
    <div class="panel-body">
        <div class="row cl">
            <label class=" col-xs-1 col-sm-1 text-r">收货人：</label>
            <div class=" col-xs-2 col-sm-2">
                <input type="text" id="name" placeholder=" "
                       class="input-text" readonly></div>
            <label class=" col-xs-2 col-sm-2 text-r">收货公司：</label>
            <div class=" col-xs-2 col-sm-2">
                <input type="text" id="company" placeholder=" "
                       class="input-text" readonly></div>
            <label class=" col-xs-2 col-sm-2 text-r">电话：</label>
            <div class=" col-xs-2 col-sm-2">
                <input type="text" id="phone" placeholder=" "
                       class="input-text" readonly></div>
        </div>
        <div class="row cl mt-10">
            <label class=" col-xs-1 col-sm-1 text-r">国家：</label>
            <div class=" col-xs-2 col-sm-2">
                <input type="text" id="countryCode" placeholder=" "
                       class="input-text" readonly></div>
            <label class=" col-xs-2 col-sm-2 text-r">邮编：</label>
            <div class=" col-xs-2 col-sm-2">
                <input type="text" id="postalCode" placeholder=" "
                       class="input-text" readonly></div>
        </div>
        <div class="row cl mt-10">
            <label class=" col-xs-1 col-sm-1 text-r">城市：</label>
            <div class=" col-xs-2 col-sm-2">
                <input type="text" id="city" placeholder=" "
                       class="input-text" readonly></div>
            <label class=" col-xs-2 col-sm-2 text-r">州名：</label>
            <div class=" col-xs-2 col-sm-2">
                <input type="text" id="stateOrRegion" placeholder=" "
                       class="input-text" readonly></div>
        </div>
        <div class="row cl mt-10">
            <label class=" col-xs-1 col-sm-1 text-r">地址一：</label>
            <div class=" col-xs-5 col-sm-5">
                <input type="text" id="addressLine1" placeholder=" "
                       class="input-text" readonly></div>
            <label class=" col-xs-1 col-sm-1 text-r">地址二：</label>
            <div class=" col-xs-5 col-sm-5">
                <input type="text" id="addressLine2" placeholder=" "
                       class="input-text" readonly></div>
        </div>
    </div>
</div>
<div class="panel panel-success">
    <div class="panel-header">订单备注</div>
    <div class="panel-body">
        <div class="row cl">
            <label class=" col-xs-1 col-sm-1 text-r">订单ID：</label>
            <div class=" col-xs-2 col-sm-2">
                <input type="text" id="amazonOrderId" placeholder=" "
                       class="input-text" readonly></div>
        </div>
        <div class="row cl mt-10">
            <div class="col-xs-12 col-sm-12">
                <input type="text" id="remark" placeholder=" "
                       class="textarea" readonly></div>
        </div>
        <div class="text-c mt-10">
            <a href="javascript:;" onclick="editRemark()" class="btn btn-danger radius">
                <i class="Hui-iconfont">&#xe6e2;</i> 编辑</a>
            <a class="btn btn-primary radius" onclick="saveRemark()" href="javascript:;">
                <i class="Hui-iconfont">&#xe600;</i> 保存</a>
        </div>
    </div>
</div>
<%--<div class="panel panel-success">
    <div class="panel-header">亚马逊订单ID</div>
    <div class="panel-body">
        <div class="row cl">
            <div class="col-xs-12 col-sm-12">
                <input type="text" id="amazonOrderId" placeholder=" "
                       class="input-text"></div>

        </div>
    </div>
</div>--%>
<div class="panel panel-success">
    <div class="panel-header">发货商品</div>
    <div class="panel-body">
        <table id="itemTable" class="table table-border table-bordered table-bg table-hover">
            <thead>
            <tr class="text-c">
                <th width="100">SKU</th>
                <th width="150">图片</th>
                <th width="150">产品信息</th>
                <th width="60">ASIN</th>
                <th width="100">数量</th>
                <th width="100">销售额</th>
                <th width="100">成本（元）</th>
                <th width="100">退款（元）</th>
                <th width="100">国内跟踪号</th>
                <th width="100">采购编号</th>
                <th width="100">状态</th>
                <th width="100">操作</th>
            </tr>
            </thead>
        </table>
    </div>
</div>
<div class="panel panel-success">
    <div class="panel-header">已生成快递商品</div>
    <div class="panel-body">
        <table id="orderTable" class="table table-border table-bordered table-bg table-hover">
            <thead>
            <tr class="text-c">
                <th width="100">序号</th>
                <th width="150">国际跟踪号</th>
                <th width="150">产品编号</th>
                <th width="100">销售额</th>
                <th width="100">付款方式</th>
                <th width="100">运费（元）</th>
                <th width="100">货运公司</th>
                <th width="100">状态</th>
            </tr>
            </thead>
        </table>
    </div>
</div>
<div class="panel panel-success">
    <div class="panel-header">操作记录</div>
    <div class="panel-body">
        <ul class="commentList" id="logul">
        </ul>
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
    var amazonOrderId = '<%=amazonOrderId%>';
    $(function () {
        $.post("<%=request.getContextPath()%>/order/selectOrderInfo",
                {
                    "amazonOrderId": amazonOrderId
                },
                function (data) {
                    var map = JSON.parse(data);
                    console.log(map.address);
                    initBusiness(map.localOrder);
                    initAddressInfo(map.address);

                });
        initOrderItem();
        initOperationLog();
    });
    function initBusiness(localOrder) {
        $("#salesCompany").val(localOrder.COMPANY_NAME);
        $("#salesMan").val(localOrder.SALESMAN);
        $("#salesSource").val(localOrder.SALESSOURCE);
        $("#amazonOrderId").val(localOrder.AMAZONORDERID);
        $("#remark").val(localOrder.REMARK);
        if (localOrder.LOCALSTATUS == 4) {
            var dataSet = new Array();
            dataSet[0] = localOrder;
            $('#orderTable').dataTable({
                "data": dataSet,
                "columns": [
                    {"data": "ID"},
                    {"data": "INTLTRACKNUM"},
                    {"data": "INTLTRACKNUM"},
                    {"data": "ORDERTOTAL"},
                    {"data": "ORDERTOTAL"},
                    {"data": "ITEMPRICE"},
                    {"data": "TRANSPORTCOMPANY"},
                    {"data": "LOCALSTATUS"}
                ],
                "columnDefs": [
                    {
                        "targets": [7],
                        "data": "LOCALSTATUS",
                        "render": function (data, type, full) {
                            if (data == 1) {
                                return "<div>新单</div>"
                            } else if (data == 2) {
                                return "<div>备货</div>"
                            } else if (data == 3) {
                                return "<div>缺货</div>"
                            } else if (data == 4) {
                                return "<div>发货</div>"
                            } else if (data == 5) {
                                return "<div>问题</div>"
                            } else if (data == 6) {
                                return "<div>退款</div>"
                            } else if (data == 7) {
                                return "<div>妥投</div>"
                            } else if (data == 8) {
                                return "<div>代发</div>"
                            }
                        }
                    }
                ],
                "bFilter": false,
                "paging": false,
                "info": false
            });
        }
    }
    function initAddressInfo(address) {
        $("#name").val(address.USERNAME);
        $("#company").val(address.COMPANY);
        $("#phone").val(address.PHONE);
        $("#countryCode").val(address.COUNTRYCODE);
        $("#postalCode").val(address.POSTALCODE);
        $("#city").val(address.CITY);
        $("#stateOrRegion").val(address.STATEORREGION);
        $("#addressLine1").val(address.ADDRESSLINE1);
        $("#addressLine2").val(address.ADDRESSLINE2);
    }
    function initOrderItem() {
        $("#itemTable").DataTable({
            "serverSide": true,
            "ajax": {
                "url": "<%=request.getContextPath()%>/order/selectLocalOrderItem",
                "type": "POST",
                "data": {
                    "amazonOrderId": amazonOrderId
                }
            },
            "columns": [
                {"data": "SELLERSKU"},
                {"data": "TITLE"},
                {"data": "TITLE"},
                {"data": "ASIN"},
                {"data": "QUANTITYSHIPPED"},
                {"data": "ITEMPRICE"},
                {"data": "COST"},
                {"data": "REFUNDMENT"},
                {"data": "TRACKNUM"},
                {"data": "PURCHASENUM"},
                {"data": "STATUS"},
                {"data": "STATUS"}
            ],
            "columnDefs": [
                {
                    "targets": [10],
                    "data": "STATUS",
                    "render": function (data, type, full) {
                        if (data == 1) {
                            return "<div>新单</div>"
                        } else if (data == 2) {
                            return "<div>备货</div>"
                        } else if (data == 3) {
                            return "<div>缺货</div>"
                        } else if (data == 4) {
                            return "<div>发货</div>"
                        } else if (data == 5) {
                            return "<div>问题</div>"
                        } else if (data == 6) {
                            return "<div>退款</div>"
                        } else if (data == 7) {
                            return "<div>妥投</div>"
                        } else if (data == 8) {
                            return "<div>代发</div>"
                        }
                    }
                },
                {
                    "targets": [11],
                    "data": "STATUS",
                    "render": function (data, type, full) {
                        if (data == 1 || data == 2 || data == 3) {
                            return "<a style='text-decoration:none' title='备货'  onClick=\"updateOrder(2,'" + full.SELLERSKU + "')\"'>备货</a>" +
                                    "&nbsp;&nbsp;&nbsp;&nbsp;" +
                                    "<a style='text-decoration:none' title='缺货'  onClick=\"updateOrder(3,'" + full.SELLERSKU + "')\"'>缺货</a>" +
                                    "&nbsp;&nbsp;&nbsp;&nbsp;" +
                                    "<a style='text-decoration:none' title='退款'  onClick=\"updateOrder(6,'" + full.SELLERSKU + "')\"'>退款</a>" +
                                    "&nbsp;&nbsp;&nbsp;&nbsp;" +
                                    "<a style='text-decoration:none' title='问题'  onClick=\"updateOrder(5,'" + full.SELLERSKU + "')\"'>问题</a>" +
                                    "&nbsp;&nbsp;&nbsp;&nbsp;";
                        } else if (data == 4 || data == 5 || data == 6 || data == 7 || data == 8) {
                            return "<a style='text-decoration:none' title='退款'  onClick=\"updateOrder(6,'" + full.SELLERSKU + "')\"'>退款</a>" +
                                    "&nbsp;&nbsp;&nbsp;&nbsp;" +
                                    "<a style='text-decoration:none' title='问题'  onClick=\"updateOrder(5,'" + full.SELLERSKU + "')\"'>问题</a>" +
                                    "&nbsp;&nbsp;&nbsp;&nbsp;" +
                                    "<a style='text-decoration:none' title='妥投'  onClick=\"updateOrder(7,'" + full.SELLERSKU + "')\"'>妥投</a>" +
                                    "&nbsp;&nbsp;&nbsp;&nbsp;";
                        }
                    }
                }
            ],
            "rowCallback": function (row, data, displayIndex) {
                $(row).attr("class", "text-c");
            },
            "initComplete": function (settings, json) {

            },
            "dom": "t<'dataTables_info'il>p",
            "paging": false,
            "processing": true,
            "ordering": false,
            "info": false
        });
    }
    function initOperationLog() {
        $.ajax({
            type: 'POST',
            url: '<%=request.getContextPath()%>/order/selectOperationLog',
            dataType: 'json',
            "data": {
                "amazonOrderId": amazonOrderId
            },
            success: function (data) {
                if (data.code == 0) {
                    var data = data.data;
                    for (var i = 0; i < data.length; i++) {
                        var item = data[i];
                        var li = "<li class='item cl comment-flip'>" +
                                "<div class='comment-main'>" +
                                "<header class='comment-header'>" +
                                "<div class='comment-meta'><a class='comment-author'>" + item.OPERATION_USER + "</a> 操作于" +
                                "<time>" + item.OPERATION_TIME + "</time>" +
                                "</div></header>" +
                                "<div class='comment-body'>" +
                                "<p>" + item.OPERATION_INFO + "</p></div></div></li>";
                        $("#logul").append(li)
                    }
                } else {
                    layer.msg('查询订单操作日志失败!', {icon: 2, time: 1000});
                }
            },
            error: function (data) {
                layer.msg('查询订单操作日志失败!', {icon: 2, time: 1000});
            }
        });
    }
    function updateOrder(status, sku) {
        if (status == 2) {
            layer_show("备货", "<%=request.getContextPath()%>/assistant/index/order/stocking.jsp?amazonOrderId=" + amazonOrderId + "&sku=" + sku, 400, 300);
        } else if (status == 3) {
            layer.confirm('您确定缺货吗？', function (index) {
                confirm(3, sku)
            });
        } else if (status == 4) {

        } else if (status == 5) {
            layer.confirm('您确定此订单有问题吗？', function (index) {
                confirm(5, sku)
            });
        } else if (status == 6) {
            layer_show("退款", "<%=request.getContextPath()%>/assistant/index/order/refund.jsp?amazonOrderId=" + amazonOrderId + "&sku=" + sku, 400, 200);
        } else if (status == 7) {
            layer.confirm('您确定要妥投吗？', function (index) {
                confirm(7, sku)
            });
        }
    }
    function confirm(status, sku) {
        $.ajax({
            type: 'POST',
            url: '<%=request.getContextPath()%>/order/updateOrderInfo',
            dataType: 'json',
            "data": {
                "amazonOrderId": amazonOrderId,
                "status": status,
                "cost": "",
                "refundment": "",
                "trackNum": "",
                "purchaseNum": "",
                "sku": sku
            },
            success: function (data) {
                layer.msg('更新订单成功!', {icon: 1, time: 1000});
            },
            error: function (data) {
                layer.msg('更新订单失败!', {icon: 2, time: 1000});
            }
        });
    }
    function editRemark() {
        $("#remark").attr("readonly", false);
    }
    function saveRemark() {
        $("#remark").attr("readonly", true);
        $.ajax({
            type: 'POST',
            url: '<%=request.getContextPath()%>/order/updateOrderRemark',
            dataType: 'json',
            "data": {
                "amazonOrderId": amazonOrderId,
                "remark": $("#remark").val()
            },
            success: function (data) {
                layer.msg('添加备注成功!', {icon: 1, time: 1000});
            },
            error: function (data) {
                layer.msg('添加备注失败!', {icon: 2, time: 1000});
            }
        });
    }
</script>
</body>
</html>

