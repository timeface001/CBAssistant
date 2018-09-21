<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String amazonOrderId = request.getParameter("amazonOrderId");
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
        <form id="otherForm" class="form form-horizontal">
            <input type="hidden" value="<%=amazonOrderId%>" name="amazonOrderId">
            <input type="hidden" value="2" name="status">
            <input type="hidden" value="<%=preStatus%>" name="preStatus">
            <div class="row cl">
                <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>状态：</label>
                <div class="formControls col-xs-8 col-sm-9">
                    <select id="localStatus" name="localStatus" class="select" style="height: 32px">
                        <option value="11">加急</option>
                        <option value="12">拦截</option>
                    </select>
                </div>
            </div>
            <div class="row cl">
                <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>备注：</label>
                <div class="formControls col-xs-8 col-sm-9">
                    <input type="text" class="textarea" value="" placeholder="" id="remark" name="remark">
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
    $(function () {
        $("#otherForm").validate({
            rules: {
                localStatus: {
                    required: true
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
            data: $("#otherForm").serialize(),
            url: "<%=request.getContextPath()%>/order/updateOrderInfo",//请求的action路径
            error: function () {//请求失败处理函数
                layer.closeAll("loading");
                layer.msg('操作失败!', {icon: 2, time: 1000});
            },
            success: function (data) { //请求成功后处理函数。
                layer.closeAll("loading");
                if (data.code == 0) {
                    var index = parent.layer.getFrameIndex(window.name);
                    parent.layer.close(index);
                } else {
                    layer.msg('操作失败!', {icon: 2, time: 1000});
                }
            }
        });
    }
</script>
</body>
</html>
