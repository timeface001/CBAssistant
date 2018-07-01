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
    <form class="form form-horizontal" id="addShopForm">
        <input type="hidden" value="<%=id%>" name="id">
        <div class="panel panel-success">
            <div class="panel-header">订单信息</div>
            <div class="panel-body">
                <div class="row cl">
                    <label class="form-label col-xs-1 col-sm-1"><span class="c-red">*</span>订单号：</label>
                    <div class="formControls col-xs-2 col-sm-2">
                        <input type="text" class="input-text" value="" placeholder="" id="shopName" name="shopName">
                    </div>
                    <label class="form-label col-xs-1 col-sm-1">国家：</label>
                    <div class="formControls col-xs-2 col-sm-2">
                        <select id="buyerCounty" name="buyerCounty" class="select" style="height: 32px">
                            <option value="">请选择</option>
                        </select>
                    </div>
                    <label class="form-label col-xs-1 col-sm-1">业务员：</label>
                    <div class="formControls col-xs-2 col-sm-2">
                        <select id="salesMan" name="salesMan" class="select" style="height: 32px">
                            <option value="">请选择</option>
                        </select>
                    </div>
                </div>
                <div class="row cl">
                    <label class="form-label col-xs-1 col-sm-1">公司：</label>
                    <div class="formControls col-xs-2 col-sm-2">
                        <select id="salesCompany" name="salesCompany" class="select" style="height: 32px">
                            <option value="">请选择</option>
                        </select>
                    </div>
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
        <div class="row cl">
            <div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-3">
                <button type="button" class="btn btn-success radius" id="shop-save" name="shop-save"><i
                        class="icon-ok"></i> 确定
                </button>
            </div>
        </div>
    </form>
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
<script type="text/javascript">
    var id = '<%=id%>';
    $(function () {
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
                        $("#countryCode").append($('<option value=' + data[i].ID + '>' + data[i].NAME + '</option>'));
                    }
                    initShopInfo();
                }
            },
            error: function (data) {
                layer.msg(data.msg, {icon: 5, time: 1000});
            }
        });
        $("#addshopForm").validate({
            rules: {
                roleName: {
                    required: true,
                },
            },
            onkeyup: false,
            focusCleanup: true,
            success: "valid",
            submitHandler: function (form) {
                $(form).ajaxSubmit();
                var index = parent.layer.getFrameIndex(window.name);
                parent.layer.close(index);
            }
        });
    });
    function initShopInfo() {
        if (id != null && id != "" && id != "null" && id != undefined) {
            $.ajax({
                type: 'POST',
                url: '<%=request.getContextPath()%>/shop/selectShops',
                dataType: 'json',
                data: {
                    "id": id
                },
                success: function (data) {
                    if (data.code == 0) {
                        var data = data.data[0];
                        $("#shopName").val(data.SHOP_NAME);
                        $("#amazonAccount").val(data.AMAZON_ACCOUNT);
                        $("#countryCode").val(data.COUNTRY_CODE);
                        $("#merchantId").val(data.MERCHANT_ID);
                        $("#merchantId").attr("readOnly", true);
                        $("#accesskeyId").val(data.ACCESSKEY_ID);
                        $("#accesskeyId").attr("readOnly", true);
                        $("#secretKey").val(data.SECRET_KEY);
                        $("#secretKey").attr("readOnly", true);
                        $("#state").val(data.SHOP_STATE);
                    } else {
                        layer.msg(data.msg, {icon: 6, time: 1000});
                    }
                },
                error: function (data) {
                    layer.msg(data.msg, {icon: 5, time: 1000});
                }
            });
        }
    }
    $("#shop-save").click(function () {
        var url = '<%=request.getContextPath()%>/shop/addShop';
        if (id != null && id != "" && id != "null" && id != undefined) {
            url = '<%=request.getContextPath()%>/shop/updateShop';
        } else {
            url = '<%=request.getContextPath()%>/shop/addShop';
        }
        $.ajax({
            type: 'POST',
            url: url,
            dataType: 'json',
            data: {
                "data": JSON.stringify(getFormJson("#addShopForm"))
            },
            success: function (data) {
                layer.msg(data.msg, {icon: 6, time: 1000});
                layer_close();
            },
            error: function (data) {
                layer.msg(data.msg, {icon: 5, time: 1000});
            },
        });
    });
</script>
</body>
</html>
