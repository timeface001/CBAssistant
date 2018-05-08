<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
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
    <input id="roleId" type="hidden" value="${sessionScope.user.ROLE_ID}">
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
        <div class="row cl mt-10">
            <label class=" col-xs-1 col-sm-1 text-r">地址三：</label>
            <div class=" col-xs-5 col-sm-5">
                <input type="text" id="addressLine3" placeholder=" "
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
<div class="panel panel-success" id="customsDiv">
    <div class="panel-header">海关申报信息</div>
    <div class="panel-body">
        <div class="row cl">
            <label class=" col-xs-1 col-sm-1 text-r"><span class="c-red">*</span>运输公司：</label>
            <div class=" col-xs-2 col-sm-2">
                <select id="transportCompany" name="transportCompany" class="select" style="height: 32px"
                        onchange="loadShips(this.value)">
                    <option value="">请选择</option>
                </select>
            </div>
            <label class=" col-xs-2 col-sm-2 text-r"><span class="c-red">*</span>运输方式：</label>
            <div class=" col-xs-2 col-sm-2">
                <select id="transType" name="transType" class="select" style="height: 32px">
                    <option value="">请选择</option>
                </select>
            </div>
            <label class=" col-xs-2 col-sm-2 text-r"><span class="c-red">*</span>客户单号：</label>
            <div class=" col-xs-2 col-sm-2">
                <input type="text" id="amazonId" placeholder=" "
                       class="input-text"></div>
        </div>
        <div class="row cl">
            <label class=" col-xs-1 col-sm-1 text-r"><span class="c-red">*</span>货物重量(kg)：</label>
            <div class=" col-xs-2 col-sm-2">
                <input type="text" id="weight" placeholder=" "
                       class="input-text"></div>
            <label class=" col-xs-2 col-sm-2 text-r"><span class="c-red">*</span>包装件数：</label>
            <div class=" col-xs-2 col-sm-2">
                <input type="text" id="count" placeholder=" "
                       class="input-text"></div>

        </div>
        <div class="row cl">
            <label class=" col-xs-1 col-sm-1 text-r">长(cm)：</label>
            <div class=" col-xs-2 col-sm-2">
                <input type="text" id="length" placeholder=" " value="1"
                       class="input-text">
            </div>
            <label class=" col-xs-2 col-sm-2 text-r">宽(cm)：</label>
            <div class=" col-xs-2 col-sm-2">
                <input type="text" id="width" placeholder=" " value="1"
                       class="input-text">
            </div>
            <label class=" col-xs-2 col-sm-2 text-r">高(cm)：</label>
            <div class=" col-xs-2 col-sm-2">
                <input type="text" id="height" placeholder=" " value="1"
                       class="input-text">
            </div>
        </div>
        <div class="cl pd-5 bg-1 bk-gray mt-10" id="count-div"><span class="l">
            <a class="btn btn-primary radius" href="javascript:;"
               onclick="addRow()"><i
                    class="Hui-iconfont">
                &#xe600;</i>添加一行</a></span></div>
        <form class="form form-horizontal" id="tableForm">
            <table id="customsTable" class="table table-border table-bordered table-bg table-hover">
                <thead>
                <tr class="text-c">
                    <th width="100"><span class="c-red">*</span>英文品名</th>
                    <th width="100"><span class="c-red">*</span>中文品名</th>
                    <th width="100"><span class="c-red">*</span>数量</th>
                    <th width="100">单位</th>
                    <th width="100"><span class="c-red">*</span>单价</th>
                    <th width="100">总价</th>
                    <th width="100"><span class="c-red">*</span>重量(kg)</th>
                    <th width="100">总重</th>
                    <th width="100">SKU</th>
                    <th width="100">海关协制编号</th>
                    <th width="100">配货信息</th>
                    <th width="100">销售地址</th>
                    <th width="50">操作</th>
                </tr>
                </thead>
            </table>
        </form>
        <div class="row cl">
            <div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-3">
                <button type="button" class="btn btn-success radius" id="delivery" name="delivery" onclick="delivery()">
                    <i class="icon-ok"></i>发货
                </button>
                <button type="button" class="btn btn-success radius" id="print" name="print" onclick="print()">
                    <i class="icon-ok"></i>打印
                </button>
            </div>
        </div>
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
    var roleId = $("#roleId").val();
    var preStatus = "1";
    var trackNum = "";
    var countryCode;
    $(function () {
        $.post("<%=request.getContextPath()%>/order/selectOrderInfo",
                {
                    "amazonOrderId": amazonOrderId
                },
                function (data) {
                    var map = JSON.parse(data);
                    countryCode = map.address.COUNTRYCODE;
                    initBusiness(map.localOrder);
                    initAddressInfo(map.address);
                });
        initOrderItem();
        initOperationLog();
        initCustomsTable();
        initSelect();
    });
    function initBusiness(localOrder) {
        preStatus = localOrder.LOCALSTATUS;
        trackNum = localOrder.INTLTRACKNUM;
        $("#salesCompany").val(localOrder.COMPANY_NAME);
        $("#salesMan").val(localOrder.USER_NAME);
        $("#salesSource").val(localOrder.SHOP_NAME);
        $("#amazonOrderId").val(localOrder.AMAZONORDERID);
        $("#amazonId").val(localOrder.AMAZONORDERID);
        $("#remark").val(localOrder.REMARK);
        var customsDiv = document.getElementById("customsDiv");
        if (roleId == 100 || roleId == 400) {
            if (localOrder.LOCALSTATUS == 2) {
                customsDiv.style.display = '';
            } else {
                customsDiv.style.display = 'none';
            }
        } else {
            customsDiv.style.display = 'none';
        }
        if (localOrder.LOCALSTATUS == 4) {
            var dataSet = new Array();
            dataSet[0] = localOrder;
            $('#orderTable').dataTable({
                "data": dataSet,
                "columns": [
                    {"data": "ID"},
                    {"data": "INTLTRACKNUM"},
                    {"data": "ASIN"},
                    {"data": "ITEMPRICE"},
                    {"data": "PAYMENTMETHOD"},
                    {"data": "SHIPPINGPRICE"},
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
                "rowCallback": function (row, data, displayIndex) {
                    $(row).attr("class", "text-c");
                },
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
        $("#addressLine3").val(address.ADDRESSLINE3);
        if (roleId == 400) {
            $("#name").attr("readOnly", false);
            $("#company").attr("readOnly", false);
            $("#phone").attr("readOnly", false);
            $("#countryCode").attr("readOnly", false);
            $("#postalCode").attr("readOnly", false);
            $("#city").attr("readOnly", false);
            $("#stateOrRegion").attr("readOnly", false);
            $("#addressLine1").attr("readOnly", false);
            $("#addressLine2").attr("readOnly", false);
            $("#addressLine3").attr("readOnly", false);
        }
    }
    function initOrderItem() {
        var vis = true;
        if (roleId == 400) {
            vis = false;
        }
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
                {"data": "SMALLIMAGE"},
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
                    "targets": [5],
                    "data": "ITEMPRICE",
                    "visible": vis
                },
                {
                    "targets": [6],
                    "data": "COST",
                    "visible": vis
                },
                {
                    "targets": [7],
                    "data": "REFUNDMENT",
                    "visible": vis
                },
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
                        if (data == 1 || data == 3) {
                            if (roleId == 300) {
                                return "";
                            } else {
                                return "<a style='text-decoration:none' title='备货'  onClick=\"updateOrder(2,'" + full.SELLERSKU + "')\"'>备货</a>" +
                                        "&nbsp;&nbsp;&nbsp;&nbsp;" +
                                        "<a style='text-decoration:none' title='缺货'  onClick=\"updateOrder(3,'" + full.SELLERSKU + "')\"'>缺货</a>" +
                                        "&nbsp;&nbsp;&nbsp;&nbsp;" +
                                        "<a style='text-decoration:none' title='退款'  onClick=\"updateOrder(6,'" + full.SELLERSKU + "')\"'>退款</a>" +
                                        "&nbsp;&nbsp;&nbsp;&nbsp;" +
                                        "<a style='text-decoration:none' title='问题'  onClick=\"updateOrder(5,'" + full.SELLERSKU + "')\"'>问题</a>" +
                                        "&nbsp;&nbsp;&nbsp;&nbsp;";
                            }
                        } else if (data == 2) {
                            if (roleId == 300) {
                                return "";
                            } else if (roleId == 400) {
                                return "";
                            } else if (roleId == 500) {
                                return "<a style='text-decoration:none' title='缺货'  onClick=\"updateOrder(3,'" + full.SELLERSKU + "')\"'>缺货</a>" +
                                        "&nbsp;&nbsp;&nbsp;&nbsp;" +
                                        "<a style='text-decoration:none' title='退款'  onClick=\"updateOrder(6,'" + full.SELLERSKU + "')\"'>退款</a>" +
                                        "&nbsp;&nbsp;&nbsp;&nbsp;" +
                                        "<a style='text-decoration:none' title='问题'  onClick=\"updateOrder(5,'" + full.SELLERSKU + "')\"'>问题</a>" +
                                        "&nbsp;&nbsp;&nbsp;&nbsp;";
                            } else {
                                return "<a style='text-decoration:none' title='备货'  onClick=\"updateOrder(2,'" + full.SELLERSKU + "')\"'>备货</a>" +
                                        "&nbsp;&nbsp;&nbsp;&nbsp;" +
                                        "<a style='text-decoration:none' title='缺货'  onClick=\"updateOrder(3,'" + full.SELLERSKU + "')\"'>缺货</a>" +
                                        "&nbsp;&nbsp;&nbsp;&nbsp;" +
                                        "<a style='text-decoration:none' title='退款'  onClick=\"updateOrder(6,'" + full.SELLERSKU + "')\"'>退款</a>" +
                                        "&nbsp;&nbsp;&nbsp;&nbsp;" +
                                        "<a style='text-decoration:none' title='问题'  onClick=\"updateOrder(5,'" + full.SELLERSKU + "')\"'>问题</a>" +
                                        "&nbsp;&nbsp;&nbsp;&nbsp;";
                            }
                        } else if (data == 4) {
                            if (roleId == 400 || roleId == 300) {
                                return "";
                            } else {
                                return "<a style='text-decoration:none' title='退款'  onClick=\"updateOrder(6,'" + full.SELLERSKU + "')\"'>退款</a>" +
                                        "&nbsp;&nbsp;&nbsp;&nbsp;" +
                                        "<a style='text-decoration:none' title='妥投'  onClick=\"updateOrder(7,'" + full.SELLERSKU + "')\"'>妥投</a>" +
                                        "&nbsp;&nbsp;&nbsp;&nbsp;";
                            }
                        } else if (data == 5 || data == 6 || data == 7 || data == 8) {
                            if (roleId == 300) {
                                return "";
                            } else {
                                return "<a style='text-decoration:none' title='退款'  onClick=\"updateOrder(6,'" + full.SELLERSKU + "')\"'>退款</a>" +
                                        "&nbsp;&nbsp;&nbsp;&nbsp;" +
                                        "<a style='text-decoration:none' title='问题'  onClick=\"updateOrder(5,'" + full.SELLERSKU + "')\"'>问题</a>" +
                                        "&nbsp;&nbsp;&nbsp;&nbsp;" +
                                        "<a style='text-decoration:none' title='妥投'  onClick=\"updateOrder(7,'" + full.SELLERSKU + "')\"'>妥投</a>" +
                                        "&nbsp;&nbsp;&nbsp;&nbsp;";
                            }
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
                                "<div class='comment-meta'><a class='comment-author'>" + item.USER_NAME + "</a> 操作于" +
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
    var customsTable;
    function initCustomsTable() {
        customsTable = $("#customsTable").DataTable({
            "ordering": false,
            "searching": false,
            "bLengthChange": false,
            "columns": [
                {"data": "ENNAME"},
                {"data": "CHNNAME"},
                {"data": "COUNT"},
                {"data": "UNIT"},
                {"data": "PRICE"},
                {"data": "TOTALPRICE"},
                {"data": "WEIGHT"},
                {"data": "TOTALWEIGHT"},
                {"data": "SKU"},
                {"data": "CODE"},
                {"data": "INFO"},
                {"data": "ADDRESS"},
                {"data": "STATUS"}
            ],
            "columnDefs": [
                {
                    "targets": [0],
                    "data": "ENNAME",
                    "render": function (data, type, full) {
                        return "<input type='text' value=''  class='input-text' name='enName'/>";
                    }
                },
                {
                    "targets": [1],
                    "data": "CHNNAME",
                    "render": function (data, type, full) {
                        return "<input type='text' value='' class='input-text' name='chnName'/>";
                    }
                },
                {
                    "targets": [2],
                    "data": "COUNT",
                    "render": function (data, type, full) {
                        return "<input type='text' value=''  class='input-text' name='count'/>";
                    }
                },
                {
                    "targets": [3],
                    "data": "UNIT",
                    "render": function (data, type, full) {
                        return "<input type='text' value=''  class='input-text' name='unit'/>";
                    }
                },
                {
                    "targets": [4],
                    "data": "PRICE",
                    "render": function (data, type, full) {
                        return "<input type='text' value=''  class='input-text' name='price'/>";
                    }
                },
                {
                    "targets": [5],
                    "data": "TOTALPRICE",
                    "render": function (data, type, full) {
                        return "<input type='text' value=''  class='input-text' name='address'totalPrice/>";
                    }
                },
                {
                    "targets": [6],
                    "data": "WEIGHT",
                    "render": function (data, type, full) {
                        return "<input type='text' value=''  class='input-text' name='weight'/>";
                    }
                },
                {
                    "targets": [7],
                    "data": "TOTALWEIGHT",
                    "render": function (data, type, full) {
                        return "<input type='text' value=''  class='input-text' name='totalWeight'/>";
                    }
                },
                {
                    "targets": [8],
                    "data": "SKU",
                    "render": function (data, type, full) {
                        return "<input type='text' value=''  class='input-text' name='sku'/>";
                    }
                }, {
                    "targets": [9],
                    "data": "CODE",
                    "render": function (data, type, full) {
                        return "<input type='text' value=''  class='input-text' name='code'/>";
                    }
                },
                {
                    "targets": [10],
                    "data": "INFO",
                    "render": function (data, type, full) {
                        return "<input type='text' value=''  class='input-text' name='info'/>";
                    }
                },
                {
                    "targets": [11],
                    "data": "ADDRESS",
                    "render": function (data, type, full) {
                        return "<input type='text' value=''  class='input-text' name='address'/>";
                    }
                },
                {
                    "targets": [12],
                    "data": "STATUS",
                    "render": function (data, type, full) {
                        return "<a style='text-decoration:none' title='删除'  onClick='removeRow(this)'>删除</a>";
                    }
                }
            ],
            "bFilter": false,
            "paging": false,
            "info": false
        });
    }
    var count = 0;
    function addRow() {
        customsTable.row.add([
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            ""]).draw();
        count += 1;
    }
    function removeRow(obj) {
        customsTable.row($(obj).parents('tr')).remove().draw();
        count -= 1;
    }
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
    function loadShips(value) {
        $.ajax({
            type: 'POST',
            url: '<%=request.getContextPath()%>/common/getShipTypes',
            dataType: 'json',
            data: {
                "companyId": value
            },
            success: function (data) {
                if (data.code == 0) {
                    var data = data.data;
                    for (var i = 0; i < data.length; i++) {
                        $("#transType").append($('<option value=' + data[i].ID + '>' + data[i].FULL_NAME + '</option>'));
                    }
                }
            },
            error: function (data) {
                layer.msg(data.msg, {icon: 2, time: 1000});
            }
        });
    }
    function updateOrder(status, sku) {
        if (status == 2) {
            layer_show("备货", "<%=request.getContextPath()%>/assistant/index/order/stocking.jsp?amazonOrderId=" + amazonOrderId + "&sku=" + sku + "&preStatus=" + preStatus, 400, 300);
        } else if (status == 3) {
            layer.confirm('您确定缺货吗？', function (index) {
                confirm(3, sku)
            });
        } else if (status == 4) {
            layer_show("发货", "<%=request.getContextPath()%>/assistant/index/order/stocking.jsp?amazonOrderId=" + amazonOrderId + "&sku=" + sku + "&preStatus=" + preStatus, 400, 300);
        } else if (status == 5) {
            layer.confirm('您确定此订单有问题吗？', function (index) {
                confirm(5, sku)
            });
        } else if (status == 6) {
            layer_show("退款", "<%=request.getContextPath()%>/assistant/index/order/refund.jsp?amazonOrderId=" + amazonOrderId + "&sku=" + sku + "&preStatus=" + preStatus, 400, 200);
        } else if (status == 7) {
            layer.confirm('您确定要妥投吗？', function (index) {
                confirm(7, sku)
            });
        } else if (status == 8) {
            layer_show("修改运费", "<%=request.getContextPath()%>/assistant/index/order/stocking.jsp?amazonOrderId=" + amazonOrderId + "&sku=" + sku + "&preStatus=" + preStatus, 400, 300);
        }
    }
    function confirm(status, sku) {
        $.ajax({
            type: 'POST',
            url: '<%=request.getContextPath()%>/order/updateOrderInfo',
            dataType: 'json',
            "data": {
                "amazonOrderId": amazonOrderId,
                "preStatus": preStatus,
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
    /*发货*/
    function delivery() {
        if ($("#amazonId").val() == null || $("#amazonId").val() == '' || $("#amazonId").val() == undefined) {
            layer.msg('客户单号不能为空', {icon: 2, time: 2000});
            return false;
        }
        if ($("#transType").val() == null || $("#transType").val() == '' || $("#transType").val() == undefined) {
            layer.msg('运输方式不能为空', {icon: 2, time: 2000});
            return false;
        }
        if ($("#count").val() == null || $("#count").val() == '' || $("#count").val() == undefined) {
            layer.msg('包装件数不能为空', {icon: 2, time: 2000});
            return false;
        }
        if ($("#weight").val() == null || $("#weight").val() == '' || $("#weight").val() == undefined) {
            layer.msg('货物重量不能为空', {icon: 2, time: 2000});
            return false;
        }
        var wayBills = new Array();
        var wayBill = new Object();
        var ShippingInfo = new Object();
        var SenderInfo = new Object();
        var ApplicationInfos = new Array();
        wayBill.OrderNumber = $("#amazonId").val();
        wayBill.transportCompany = $("#transportCompany").val();
        wayBill.ShippingMethodCode = $("#transType").val();
        wayBill.Length = $("#length").val();
        wayBill.Width = $("#width").val();
        wayBill.Height = $("#height").val();
        wayBill.PackageNumber = $("#count").val();
        wayBill.Weight = $("#weight").val();
        wayBill.trackNum = trackNum;
        ShippingInfo.CountryCode = $("#countryCode").val();
        ShippingInfo.ShippingFirstName = $("#name").val();
        ShippingInfo.ShippingLastName = $("#name").val();
        ShippingInfo.ShippingCompany = $("#company").val();
        ShippingInfo.ShippingAddress = $("#addressLine1").val();
        ShippingInfo.ShippingAddress1 = $("#addressLine2").val();
        ShippingInfo.ShippingAddress2 = $("#addressLine3").val();
        ShippingInfo.ShippingCity = $("#city").val();
        ShippingInfo.ShippingState = $("#stateOrRegion").val();
        ShippingInfo.ShippingZip = $("#postalCode").val();
        ShippingInfo.ShippingPhone = $("#phone").val();
        wayBill.ShippingInfo = ShippingInfo;
        var tableData = getFormJson("#tableForm");
        if (count > 1) {
            for (var i = 0; i < tableData.enName.length; i++) {
                if (tableData.chnName[i] == null || tableData.chnName[i] == '' || tableData.chnName[i] == undefined) {
                    layer.msg('中文品名不能为空', {icon: 2, time: 2000});
                    return false;
                }
                if (tableData.enName[i] == null || tableData.enName[i] == '' || tableData.enName[i] == undefined) {
                    layer.msg('英文品名不能为空', {icon: 2, time: 2000});
                    return false;
                }
                if (tableData.count[i] == null || tableData.count[i] == '' || tableData.count[i] == undefined) {
                    layer.msg('数量不能为空', {icon: 2, time: 2000});
                    return false;
                }
                if (tableData.price[i] == null || tableData.price[i] == '' || tableData.price[i] == undefined) {
                    layer.msg('单价不能为空', {icon: 2, time: 2000});
                    return false;
                }
                if (tableData.weight[i] == null || tableData.weight[i] == '' || tableData.weight[i] == undefined) {
                    layer.msg('重量不能为空', {icon: 2, time: 2000});
                    return false;
                }
                var applicationInfo = new Object();
                applicationInfo.PickingName = tableData.chnName[i];
                applicationInfo.ApplicationName = tableData.enName[i];
                applicationInfo.Qty = tableData.count[i];
                applicationInfo.UnitPrice = tableData.price[i];
                applicationInfo.UnitWeight = tableData.weight[i];
                ApplicationInfos.push(applicationInfo);
            }
        } else {
            if (tableData.chnName == null || tableData.chnName == '' || tableData.chnName == undefined) {
                layer.msg('中文品名不能为空', {icon: 2, time: 2000});
                return false;
            }
            if (tableData.enName == null || tableData.enName == '' || tableData.enName == undefined) {
                layer.msg('英文品名不能为空', {icon: 2, time: 2000});
                return false;
            }
            if (tableData.count == null || tableData.count == '' || tableData.count == undefined) {
                layer.msg('数量不能为空', {icon: 2, time: 2000});
                return false;
            }
            if (tableData.price == null || tableData.price == '' || tableData.price == undefined) {
                layer.msg('单价不能为空', {icon: 2, time: 2000});
                return false;
            }
            if (tableData.weight == null || tableData.weight == '' || tableData.weight == undefined) {
                layer.msg('重量不能为空', {icon: 2, time: 2000});
                return false;
            }
            var applicationInfo = new Object();
            applicationInfo.PickingName = tableData.chnName;
            applicationInfo.ApplicationName = tableData.enName;
            applicationInfo.Qty = tableData.count;
            applicationInfo.UnitPrice = tableData.price;
            applicationInfo.UnitWeight = tableData.weight;
            ApplicationInfos.push(applicationInfo);
        }
        wayBill.ApplicationInfos = ApplicationInfos;
        wayBills.push(wayBill);
        $.ajax({
            type: 'POST',
            url: '<%=request.getContextPath()%>/common/confirmOrder',
            dataType: 'json',
            "data": {
                "json": JSON.stringify(wayBills),
                "amazonOrderId": amazonOrderId
            },
            success: function (data) {
                if (data.code == 0) {
                    layer.msg('发货成功!', {icon: 1, time: 1000});
                } else {
                    layer.msg('发货失败!', {icon: 1, time: 1000});
                }
            },
            error: function (data) {
                layer.msg('发货失败!', {icon: 2, time: 1000});
            }
        });
    }
    function print() {
        var orderNumbers = new Array();
        orderNumbers.push($("#amazonId").val());
        $.ajax({
            type: 'POST',
            url: '<%=request.getContextPath()%>/common/print',
            dataType: 'json',
            "data": {
                "orderNumbers": JSON.stringify(orderNumbers)
            },
            success: function (data) {
                if (data.code == 0) {
                    layer.msg('打印成功!', {icon: 1, time: 1000});
                } else {
                    layer.msg('打印失败!', {icon: 1, time: 1000});
                }
            },
            error: function (data) {
                layer.msg('打印失败!', {icon: 2, time: 1000});
            }
        });
    }
</script>
</body>
</html>

