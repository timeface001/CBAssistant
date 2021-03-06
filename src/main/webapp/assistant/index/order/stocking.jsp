<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String amazonOrderId = request.getParameter("amazonOrderId");
    String orderItemId = request.getParameter("orderItemId");
    String preStatus = request.getParameter("preStatus");
%>
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8">
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
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
    <title>备货</title>
</head>
<body>
<div class="pd-20">
    <div class="Huiform">
        <form id="stockingForm" class="form form-horizontal">
            <input type="hidden" value="<%=amazonOrderId%>" name="amazonOrderId">
            <input type="hidden" value="<%=orderItemId%>" name="orderItemId">
            <input type="hidden" value="2" name="status">
            <input type="hidden" value="<%=preStatus%>" name="preStatus">
            <div class="row cl">
                <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>国内追踪号：</label>
                <div class="formControls col-xs-8 col-sm-9">
                    <input type="text" class="input-text" value="" placeholder="" id="trackNum" name="trackNum">
                </div>
            </div>

            <div class="row cl">
                <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>采购编号：</label>
                <div class="formControls col-xs-8 col-sm-9">
                    <input type="text" class="input-text" value="" placeholder="" id="purchaseNum" name="purchaseNum">
                </div>
            </div>

            <div class="row cl">
                <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>成本：</label>
                <div class="formControls col-xs-8 col-sm-9">
                    <input type="text" class="input-text" value="" placeholder="" id="cost" name="cost">
                </div>
            </div>
            <div class="row cl">
                <div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-3">
                    <input class="btn btn-primary radius" type="submit" value="&nbsp;&nbsp;提交&nbsp;&nbsp;">
                </div>
            </div>
        </form>
    </div>
</div>
<script type="text/javascript" src="<%=request.getContextPath()%>/assistant/lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/assistant/lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/assistant/static/h-ui/js/H-ui.min.js"></script>
<script type="text/javascript"
        src="<%=request.getContextPath()%>/assistant/static/h-ui.admin/js/H-ui.admin.js"></script>
<script type="text/javascript"
        src="<%=request.getContextPath()%>/assistant/lib/jquery.validation/1.14.0/jquery.validate.js"></script>
<script type="text/javascript"
        src="<%=request.getContextPath()%>/assistant/lib/jquery.validation/1.14.0/validate-methods.js"></script>
<script type="text/javascript"
        src="<%=request.getContextPath()%>/assistant/lib/jquery.validation/1.14.0/messages_zh.js"></script>
<script type="text/javascript">
    var amazonOrderId = "<%=amazonOrderId%>";
    var orderItemId = "<%=orderItemId%>";
    $(function () {
        $("#stockingForm").validate({
            rules: {
                trackNum: {
                    required: true,
                    digits: true
                },
                purchaseNum: {
                    required: true,
                    digits: true
                },
                cost: {
                    required: true,
                    number: true
                }
            },
            onkeyup: false,
            focusCleanup: true,
            success: "valid",
            submitHandler: function (form) {
                ajaxSubmit();
            }
        });
        $.ajax({
            type: 'POST',
            url: '<%=request.getContextPath()%>/order/selectLocalOrderItem',
            dataType: 'json',
            "data": {
                "amazonOrderId": amazonOrderId,
                "orderItemId": orderItemId
            },
            success: function (data) {
                var orderItem = data.data;
                $("#trackNum").val(orderItem[0].TRACKNUM);
                $("#purchaseNum").val(orderItem[0].PURCHASENUM);
                $("#cost").val(orderItem[0].COST);
            },
            error: function (data) {
                layer.msg('查询失败!', {icon: 2, time: 1000});
            }
        });
    });
    function ajaxSubmit() {
        layer.load();
        $.ajax({
            type: 'POST',
            dataType: 'json',
            data: $("#stockingForm").serialize(),
            url: "<%=request.getContextPath()%>/order/updateOrderInfo",//请求的action路径
            error: function () {//请求失败处理函数
                layer.closeAll("loading");
                layer.msg('备货失败!', {icon: 2, time: 1000});
            },
            success: function (data) { //请求成功后处理函数。
                layer.closeAll("loading");
                if (data.code == 0) {
                    var index = parent.layer.getFrameIndex(window.name);
                    parent.layer.close(index);
                } else {
                    layer.msg('备货失败!', {icon: 2, time: 1000});
                }
            }
        });
    }
</script>
</body>
</html>
