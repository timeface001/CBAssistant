<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String id = request.getParameter("id");
%>
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8">
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,shop-scalable=no"/>
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
    <title>添加店铺</title>
</head>
<body>
<article class="page-container">
    <input type="hidden" value="<%=id%>" name="id">
    <div class="panel panel-success">
        <div class="panel-header">订单信息</div>
        <div class="panel-body">
            <form class="form form-horizontal" id="orderForm">
                <div class="row cl">
                    <label class=" col-xs-1 col-sm-1"><span class="c-red">*</span>订单号：</label>
                    <div class=" col-xs-2 col-sm-2">
                        <input type="text" class="input-text" id="amazonOrderId"
                               name="amazonOrderId">
                    </div>
                    <label class=" col-xs-1 col-sm-1"><span class="c-red">*</span>业务员：</label>
                    <div class=" col-xs-2 col-sm-2">
                        <select id="salesMan" name="salesMan" class="select" style="height: 32px">
                            <option value="">请选择</option>
                        </select>
                    </div>
                    <label class="col-xs-1 col-sm-1"><span class="c-red">*</span>公司：</label>
                    <div class=" col-xs-2 col-sm-2">
                        <select id="salesCompany" name="salesCompany" class="select" style="height: 32px">
                            <option value="">请选择</option>
                        </select>
                    </div>
                </div>
                <div class="row cl">
                    <label class=" col-xs-1 col-sm-1"><span class="c-red">*</span>站点国家：</label>
                    <div class=" col-xs-2 col-sm-2">
                        <select id="marketPlaceId" name="marketPlaceId" class="select" style="height: 32px">
                            <option value="">请选择</option>
                        </select>
                    </div>
                    <label class=" col-xs-1 col-sm-1"><span class="c-red">*</span>销售来源：</label>
                    <div class=" col-xs-2 col-sm-2">
                        <select id="salesSource" name="salesSource" class="select" style="height: 32px">
                            <option value="">请选择</option>
                        </select>
                    </div>
                    <label class=" col-xs-1 col-sm-1"><span class="c-red">*</span>渠道：</label>
                    <div class=" col-xs-2 col-sm-2">
                        <select id="fulfillmentChannel" name="fulfillmentChannel" class="select"
                                style="height: 32px">
                            <option value="FBM">FBM</option>
                            <option value="FBA">FBA</option>
                        </select>
                    </div>
                </div>
            </form>
        </div>
    </div>
    <div class="panel panel-success">
        <div class="panel-header">收货人信息</div>
        <div class="panel-body">
            <form class="form form-horizontal" id="addressForm">
                <div class="row cl">
                    <label class=" col-xs-1 col-sm-1 text-r"><span class="c-red">*</span>收货人：</label>
                    <div class=" col-xs-2 col-sm-2">
                        <input type="text" id="name" name="name" placeholder=" "
                               class="input-text"></div>
                    <label class=" col-xs-1 col-sm-1 text-r">收货公司：</label>
                    <div class=" col-xs-2 col-sm-2">
                        <input type="text" id="company" name="company" placeholder=" "
                               class="input-text"></div>
                    <label class=" col-xs-1 col-sm-1 text-r"><span class="c-red">*</span>电话：</label>
                    <div class=" col-xs-2 col-sm-2">
                        <input type="text" id="phone" name="phone" placeholder=" "
                               class="input-text"></div>
                </div>
                <div class="row cl mt-10">
                    <label class=" col-xs-1 col-sm-1 text-r"><span class="c-red">*</span>国家：</label>
                    <div class=" col-xs-2 col-sm-2">
                        <input type="text" id="countryCode" name="countryCode" placeholder=" "
                               class="input-text"></div>
                    <label class=" col-xs-1 col-sm-1 text-r"><span class="c-red">*</span>邮编：</label>
                    <div class=" col-xs-2 col-sm-2">
                        <input type="text" id="postalCode" name="postalCode" placeholder=" "
                               class="input-text"></div>
                </div>
                <div class="row cl mt-10">
                    <label class=" col-xs-1 col-sm-1 text-r"><span class="c-red">*</span>城市：</label>
                    <div class=" col-xs-2 col-sm-2">
                        <input type="text" id="city" name="city" placeholder=" "
                               class="input-text"></div>
                    <label class=" col-xs-1 col-sm-1 text-r"><span class="c-red">*</span>州名：</label>
                    <div class=" col-xs-2 col-sm-2">
                        <input type="text" id="stateOrRegion" name="stateOrRegion" placeholder=" "
                               class="input-text"></div>
                </div>
                <div class="row cl mt-10">
                    <label class=" col-xs-1 col-sm-1 text-r"><span class="c-red">*</span>地址一：</label>
                    <div class=" col-xs-5 col-sm-5">
                        <input type="text" id="addressLine1" name="addressLine1" placeholder=" "
                               class="input-text"></div>
                    <label class=" col-xs-1 col-sm-1 text-r">地址二：</label>
                    <div class=" col-xs-5 col-sm-5">
                        <input type="text" id="addressLine2" name="addressLine2" placeholder=" "
                               class="input-text"></div>
                </div>
                <div class="row cl mt-10">
                    <label class=" col-xs-1 col-sm-1 text-r">地址三：</label>
                    <div class=" col-xs-5 col-sm-5">
                        <input type="text" id="addressLine3" name="addressLine3" placeholder=" "
                               class="input-text"></div>
                </div>
            </form>
        </div>
    </div>
    <div class="panel panel-success">
        <div class="panel-header">商品信息</div>
        <div class="panel-body">
            <div class="cl pd-5 bg-1 bk-gray mt-10" id="count-div"><span class="l">
            <a class="btn btn-primary radius" href="javascript:;"
               onclick="addRow()" id="addRow"><i
                    class="Hui-iconfont">
                &#xe600;</i>添加一行</a></span></div>
            <form class="form form-horizontal" id="orderItemForm">
                <table id="orderItemTable" class="table table-border table-bordered table-bg table-hover">
                    <thead>
                    <tr class="text-c">
                        <th width="100"><span class="c-red">*</span>OrderItemId</th>
                        <th width="100"><span class="c-red">*</span>SKU</th>
                        <th width="100"><span class="c-red">*</span>图片</th>
                        <th width="100"><span class="c-red">*</span>产品信息</th>
                        <th width="100"><span class="c-red">*</span>ASIN</th>
                        <th width="100"><span class="c-red">*</span>数量</th>
                        <th width="100"><span class="c-red">*</span>销售额</th>
                        <th width="100"><span class="c-red">*</span>佣金</th>
                        <th width="100"><span class="c-red">*</span>亚马逊运费</th>
                        <th width="100"><span class="c-red">*</span>礼物费用</th>
                        <th width="100">操作</th>
                    </tr>
                    </thead>
                </table>
            </form>
        </div>
    </div>
    <div class="row cl mt-5">
        <div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-3">
            <button type="button" class="btn btn-success radius" id="addOrder" name="addOrder" onclick="addOrder()">
                <i class="icon-ok"></i> 确定
            </button>
        </div>
    </div>
</article>

<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="<%=request.getContextPath()%>/assistant/lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/assistant/lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/assistant/static/h-ui/js/H-ui.min.js"></script>
<script type="text/javascript"
        src="<%=request.getContextPath()%>/assistant/static/h-ui.admin/js/H-ui.admin.js"></script>
<!--/_footer 作为公共模版分离出去-->

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript"
        src="<%=request.getContextPath()%>/assistant/lib/jquery.validation/1.14.0/jquery.validate.js"></script>
<script type="text/javascript"
        src="<%=request.getContextPath()%>/assistant/lib/jquery.validation/1.14.0/validate-methods.js"></script>
<script type="text/javascript"
        src="<%=request.getContextPath()%>/assistant/lib/jquery.validation/1.14.0/messages_zh.js"></script>
<script type="text/javascript"
        src="<%=request.getContextPath()%>/assistant/lib/datatables/1.10.0/jquery.dataTables.min.js"></script>
<script type="text/javascript">
    $(function () {
        initOrderItemTable();
        initSalesSelect();
    });
    function initSalesSelect() {
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
                        $("#marketPlaceId").append($('<option value=' + data[i].MARKETPLACEID + '|' + data[i].EXRATE + '>' + data[i].NAME + '</option>'));
                    }
                }
            },
            error: function (data) {
                layer.msg(data.msg, {icon: 2, time: 1000});
            }
        });
    }
    var orderItemTable;
    var count = 0;
    function initOrderItemTable() {
        orderItemTable = $("#orderItemTable").DataTable({
            "ordering": false,
            "searching": false,
            "bLengthChange": false,
            "columns": [
                {"data": "ORDERITEMID"},
                {"data": "SKU"},
                {"data": "SMALLIMAGE"},
                {"data": "TITLE"},
                {"data": "ASIN"},
                {"data": "QUANTITYSHIPPED"},
                {"data": "ITEMPRICESUM"},
                {"data": "SHIPPINGPRICE"},
                {"data": "GIFTWRAPPRICE"},
                {"data": "COMMISSION"},
                {"data": "STATUS"}
            ],
            "columnDefs": [
                {
                    "targets": [0],
                    "data": "ORDERITEMID",
                    "render": function (data, type, full) {
                        return "<input type='text' value=''  class='input-text' name='orderItemId'/>";
                    }
                },
                {
                    "targets": [1],
                    "data": "SKU",
                    "render": function (data, type, full) {
                        return "<input type='text' value='' class='input-text' name='sku'/>";
                    }
                },
                {
                    "targets": [2],
                    "data": "SMALLIMAGE",
                    "render": function (data, type, full) {
                        return "<input type='text' value=''  class='input-text' name='smallImage'/>";
                    }
                },
                {
                    "targets": [3],
                    "data": "TITLE",
                    "render": function (data, type, full) {
                        return "<input type='text' value=''  class='input-text' name='title'/>";
                    }
                },
                {
                    "targets": [4],
                    "data": "ASIN",
                    "render": function (data, type, full) {
                        return "<input type='text' value=''  class='input-text' name='asin'/>";
                    }
                },
                {
                    "targets": [5],
                    "data": "QUANTITYSHIPPED",
                    "render": function (data, type, full) {
                        return "<input type='text' value='1'  class='input-text' name='quantityShipped'/>";
                    }
                },
                {
                    "targets": [6],
                    "data": "ITEMPRICESUM",
                    "render": function (data, type, full) {
                        return "<input type='text' value='0'  class='input-text' name='itemPriceSum'/>";
                    }
                },
                {
                    "targets": [7],
                    "data": "SHIPPINGPRICE",
                    "render": function (data, type, full) {
                        return "<input type='text' value='0'  class='input-text' name='shippingPrice'/>";
                    }
                },
                {
                    "targets": [8],
                    "data": "GIFTWRAPPRICE",
                    "render": function (data, type, full) {
                        return "<input type='text' value='0'  class='input-text' name='giftWrapPrice'/>";
                    }
                },
                {
                    "targets": [9],
                    "data": "COMMISSION",
                    "render": function (data, type, full) {
                        return "<input type='text' value='0'  class='input-text' name='commission'/>";
                    }
                },
                {
                    "targets": [10],
                    "data": "STATUS",
                    "render": function (data, type, full) {
                        if (count > 1) {
                            return "<a style='text-decoration:none' title='删除'  onClick='removeRow(this)'>删除</a>";
                        } else {
                            return "<a style='text-decoration:none'></a>";
                        }
                    }
                }
            ],
            "bFilter": false,
            "paging": false,
            "info": false
        });
        $("#addRow").trigger("click");
    }

    function addRow() {
        orderItemTable.row.add([
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
        orderItemTable.row($(obj).parents('tr')).remove().draw();
        count -= 1;
    }
    function addOrder() {
        var orderItemData = getFormJson("#orderItemForm");
        var orderData = getFormJson("#orderForm");
        var addressData = getFormJson("#addressForm");
        var orderItems = new Array();
        if (orderData.amazonOrderId == null || orderData.amazonOrderId == '' || orderData.amazonOrderId == undefined) {
            layer.msg('订单号不能为空', {icon: 2, time: 2000});
            return false;
        }
        if (orderData.salesMan == null || orderData.salesMan == '' || orderData.salesMan == undefined) {
            layer.msg('业务员不能为空', {icon: 2, time: 2000});
            return false;
        }
        if (orderData.salesCompany == null || orderData.salesCompany == '' || orderData.salesCompany == undefined) {
            layer.msg('公司不能为空', {icon: 2, time: 2000});
            return false;
        }
        if (orderData.salesSource == null || orderData.salesSource == '' || orderData.salesSource == undefined) {
            layer.msg('销售来源不能为空', {icon: 2, time: 2000});
            return false;
        }
        if (orderData.fulfillmentChannel == null || orderData.fulfillmentChannel == '' || orderData.fulfillmentChannel == undefined) {
            layer.msg('渠道不能为空', {icon: 2, time: 2000});
            return false;
        }
        if (orderData.marketPlaceId == null || orderData.marketPlaceId == '' || orderData.marketPlaceId == undefined) {
            layer.msg('站点国家不能为空', {icon: 2, time: 2000});
            return false;
        }
        if (addressData.name == null || addressData.name == '' || addressData.name == undefined) {
            layer.msg('收货人不能为空', {icon: 2, time: 2000});
            return false;
        }
        if (addressData.phone == null || addressData.phone == '' || addressData.phone == undefined) {
            layer.msg('电话不能为空', {icon: 2, time: 2000});
            return false;
        }
        if (addressData.countryCode == null || addressData.countryCode == '' || addressData.countryCode == undefined) {
            layer.msg('国家不能为空', {icon: 2, time: 2000});
            return false;
        }
        if (addressData.postalCode == null || addressData.postalCode == '' || addressData.postalCode == undefined) {
            layer.msg('邮编不能为空', {icon: 2, time: 2000});
            return false;
        }
        if (addressData.city == null || addressData.city == '' || addressData.city == undefined) {
            layer.msg('城市不能为空', {icon: 2, time: 2000});
            return false;
        }
        if (addressData.stateOrRegion == null || addressData.stateOrRegion == '' || addressData.stateOrRegion == undefined) {
            layer.msg('州名不能为空', {icon: 2, time: 2000});
            return false;
        }
        if (addressData.addressLine1 == null || addressData.addressLine1 == '' || addressData.addressLine1 == undefined) {
            layer.msg('地址一不能为空', {icon: 2, time: 2000});
            return false;
        }
        if (count > 1) {
            for (var i = 0; i < orderItemData.orderItemId.length; i++) {
                if (orderItemData.orderItemId[i] == null || orderItemData.orderItemId[i] == '' || orderItemData.orderItemId[i] == undefined) {
                    layer.msg('商品OrderItemId不能为空', {icon: 2, time: 2000});
                    return false;
                }
                if (orderItemData.sku[i] == null || orderItemData.sku[i] == '' || orderItemData.sku[i] == undefined) {
                    layer.msg('商品SKU不能为空', {icon: 2, time: 2000});
                    return false;
                }
                if (orderItemData.smallImage[i] == null || orderItemData.smallImage[i] == '' || orderItemData.smallImage[i] == undefined) {
                    layer.msg('商品图片不能为空', {icon: 2, time: 2000});
                    return false;
                }
                if (orderItemData.title[i] == null || orderItemData.title[i] == '' || orderItemData.title[i] == undefined) {
                    layer.msg('商品信息不能为空', {icon: 2, time: 2000});
                    return false;
                }
                if (orderItemData.asin[i] == null || orderItemData.asin[i] == '' || orderItemData.asin[i] == undefined) {
                    layer.msg('商品ASIN不能为空', {icon: 2, time: 2000});
                    return false;
                }
                if (orderItemData.quantityShipped[i] == null || orderItemData.quantityShipped[i] == '' || orderItemData.quantityShipped[i] == undefined) {
                    layer.msg('商品数量不能为空', {icon: 2, time: 2000});
                    return false;
                }
                if (orderItemData.itemPriceSum[i] == null || orderItemData.itemPriceSum[i] == '' || orderItemData.itemPriceSum[i] == undefined) {
                    layer.msg('商品销售额不能为空', {icon: 2, time: 2000});
                    return false;
                }
                var orderItem = new Object();
                orderItem.orderItemId = orderItemData.orderItemId[i];
                orderItem.sellerSKU = orderItemData.sku[i];
                orderItem.smallImage = orderItemData.smallImage[i];
                orderItem.title = orderItemData.title[i];
                orderItem.asin = orderItemData.asin[i];
                orderItem.quantityShipped = orderItemData.quantityShipped[i];
                orderItem.itemPrice = orderItemData.itemPriceSum[i];
                orderItem.shippingPrice = orderItemData.shippingPrice[i];
                orderItem.commission = orderItemData.commission[i];
                orderItem.giftWrapPrice = orderItemData.giftWrapPrice[i];
                orderItems.push(orderItem);
            }
        } else {
            if (orderItemData.orderItemId == null || orderItemData.orderItemId == '' || orderItemData.orderItemId == undefined) {
                layer.msg('商品OrderItemId不能为空', {icon: 2, time: 2000});
                return false;
            }
            if (orderItemData.sku == null || orderItemData.sku == '' || orderItemData.sku == undefined) {
                layer.msg('商品SKU不能为空', {icon: 2, time: 2000});
                return false;
            }
            if (orderItemData.smallImage == null || orderItemData.smallImage == '' || orderItemData.smallImage == undefined) {
                layer.msg('数量不能为空', {icon: 2, time: 2000});
                return false;
            }
            if (orderItemData.title == null || orderItemData.title == '' || orderItemData.title == undefined) {
                layer.msg('单价不能为空', {icon: 2, time: 2000});
                return false;
            }
            if (orderItemData.asin == null || orderItemData.asin == '' || orderItemData.asin == undefined) {
                layer.msg('商品ASIN不能为空', {icon: 2, time: 2000});
                return false;
            }
            if (orderItemData.quantityShipped == null || orderItemData.quantityShipped == '' || orderItemData.quantityShipped == undefined) {
                layer.msg('商品数量不能为空', {icon: 2, time: 2000});
                return false;
            }
            if (orderItemData.itemPriceSum == null || orderItemData.itemPriceSum == '' || orderItemData.itemPriceSum == undefined) {
                layer.msg('商品销售额不能为空', {icon: 2, time: 2000});
                return false;
            }
            var orderItem = new Object();
            orderItem.orderItemId = orderItemData.orderItemId;
            orderItem.sku = orderItemData.sku;
            orderItem.smallImage = orderItemData.smallImage;
            orderItem.title = orderItemData.title;
            orderItem.asin = orderItemData.asin;
            orderItem.quantityShipped = orderItemData.quantityShipped;
            orderItem.itemPriceSum = orderItemData.itemPriceSum;
            orderItem.shippingPrice = orderItemData.shippingPrice;
            orderItem.commission = orderItemData.commission;
            orderItem.giftWrapPrice = orderItemData.giftWrapPrice;
            orderItems.push(orderItem);
        }
        layer.load();
        $.ajax({
            type: 'POST',
            url: '<%=request.getContextPath()%>/order/addOrder',
            dataType: 'json',
            "data": {
                "orderData": JSON.stringify(orderData),
                "orderItemData": JSON.stringify(orderItems),
                "addressData": JSON.stringify(addressData)
            },
            success: function (data) {
                layer.closeAll("loading");
                if (data.code == 0) {
                    layer.msg('添加成功!', {icon: 1, time: 1000});
                } else {
                    layer.msg(data.msg, {icon: 1, time: 2000});
                }
            },
            error: function (data) {
                layer.closeAll("loading");
                layer.msg("请求错误！", {icon: 2, time: 2000});
            }
        });
    }
</script>
</body>
</html>
