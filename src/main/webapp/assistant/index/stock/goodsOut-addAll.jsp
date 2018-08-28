<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String pSku = request.getParameter("pSku");
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
    <title>出库</title>
</head>
<body>
<div class="pd-20">
    <div class="Huiform">
        <form id="goodsOutForm" class="form form-horizontal">
            <input type="hidden" value="<%=pSku%>" name="pSku">
            <div class="row cl">
                <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>出库量：</label>
                <div class="formControls col-xs-8 col-sm-9">
                    <input type="text" class="input-text" value="" placeholder="" id="outNum"
                           name="outNum">
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
    $(function () {
        $("#goodsOutForm").validate({
            rules: {
                orderItemId: {
                    required: true
                },
                outNum: {
                    required: true,
                    digits: true
                }
            },
            onkeyup: false,
            focusCleanup: true,
            success: "valid",
            submitHandler: function (form) {
                ajaxSubmit();
            }
        });
    });
    function ajaxSubmit() {
        layer.load();
        $.ajax({
            type: 'POST',
            dataType: 'json',
            data: {"data": JSON.stringify(getFormJson("#goodsOutForm"))},
            url: "<%=request.getContextPath()%>/stock/addAllGoodsOut",
            error: function () {//请求失败处理函数
                layer.closeAll("loading");
                layer.msg('出库失败!', {icon: 2, time: 2000});
            },
            success: function (data) { //请求成功后处理函数
                layer.closeAll("loading");
                if (data.code == 0) {
                    var index = parent.layer.getFrameIndex(window.name);
                    parent.layer.close(index);
                } else {
                    layer.msg(data.msg, {icon: 2, time: 2000});
                }
            }
        });
    }
</script>
</body>
</html>
