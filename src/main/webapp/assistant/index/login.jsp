<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
    <link href="<%=request.getContextPath()%>/assistant/static/h-ui/css/H-ui.min.css" rel="stylesheet" type="text/css"/>
    <link href="<%=request.getContextPath()%>/assistant/static/h-ui.admin/css/H-ui.login.css" rel="stylesheet"
          type="text/css"/>
    <link href="<%=request.getContextPath()%>/assistant/static/h-ui.admin/css/style.css" rel="stylesheet"
          type="text/css"/>
    <link href="<%=request.getContextPath()%>/assistant/lib/Hui-iconfont/1.0.8/iconfont.css" rel="stylesheet"
          type="text/css"/>
    <!--[if IE 6]>
    <script type="text/javascript"
            src="<%=request.getContextPath()%>/assistant/lib/DD_belatedPNG_0.0.8a-min.js"></script>
    <script>DD_belatedPNG.fix('*');</script>
    <![endif]-->
    <title>跨境助手</title>
</head>
<body>
<input type="hidden" id="TenantId" name="TenantId" value=""/>
<div class="header"></div>
<div class="loginWraper">
    <div class="loginBox">
        <form class="form form-horizontal" id="loginForm">
            <div class="row cl">
                <label class="form-label col-xs-3"><i class="Hui-iconfont">&#xe60d;</i></label>
                <div class="formControls col-xs-8">
                    <input id="userId" name="" type="text" placeholder="账户" class="input-text size-L">
                </div>
            </div>
            <div class="row cl">
                <label class="form-label col-xs-3"><i class="Hui-iconfont">&#xe60e;</i></label>
                <div class="formControls col-xs-8">
                    <input id="password" name="" type="password" placeholder="密码" class="input-text size-L">
                </div>
            </div>
            <div class="row cl" style="display: none">
                <div class="formControls col-xs-8 col-xs-offset-3">
                    <input class="input-text size-L" type="text" placeholder="验证码"
                           onblur="if(this.value==''){this.value='验证码:'}"
                           onclick="if(this.value=='验证码:'){this.value='';}" value="验证码:" style="width:150px;">
                    <img src=""> <a id="kanbuq" href="javascript:;">看不清，换一张</a></div>
            </div>
            <div class="row cl">
                <div class="formControls col-xs-8 col-xs-offset-3">
                    <label for="online">
                        <input type="checkbox" name="online" id="online" value="">
                        使我保持登录状态</label>
                </div>
            </div>
            <div class="row cl">
                <div class="formControls col-xs-8 col-xs-offset-3">
                    <input id="loginBtn" type="button" class="btn btn-success radius size-L"
                           value="&nbsp;登&nbsp;&nbsp;&nbsp;&nbsp;录&nbsp;">
                    <input name="" type="reset" class="btn btn-default radius size-L"
                           value="&nbsp;取&nbsp;&nbsp;&nbsp;&nbsp;消&nbsp;">
                </div>
            </div>
        </form>
    </div>
</div>
<div class="footer">Copyright@2018-2018 Borien 太原博瑞恩贸易有限公司 版权所有</div>
<script type="text/javascript" src="<%=request.getContextPath()%>/assistant/lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/assistant/lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/assistant/static/h-ui/js/H-ui.min.js"></script>
<script type="text/javascript"
        src="<%=request.getContextPath()%>/assistant/lib/jquery.validation/1.14.0/jquery.validate.js"></script>
<script type="text/javascript"
        src="<%=request.getContextPath()%>/assistant/lib/jquery.validation/1.14.0/validate-methods.js"></script>
<script type="text/javascript"
        src="<%=request.getContextPath()%>/assistant/lib/jquery.validation/1.14.0/messages_zh.js"></script>
<script type="text/javascript">
    $(function () {
        history.pushState(null, null, document.URL);
        window.addEventListener('popstate', function () {
            history.pushState(null, null, document.URL);
        });
        $("#loginForm").validate({
            rules: {
                userName: {
                    required: true,
                },
                password: {
                    required: true,
                }
            },
            onkeyup: false,
            focusCleanup: true,
            success: "valid"
        });
    });
    $("#loginBtn").click(function () {
        $.ajax({
            type: 'POST',
            url: '<%=request.getContextPath()%>/common/login',
            dataType: 'json',
            "data": {
                "userId": $("#userId").val(),
                "password": $("#password").val()
            },
            success: function (data) {
                if (data.code == 0) {
                    var data = data.data;
                    window.location.href = "<%=request.getContextPath()%>/common/selectMenus?userId=" + data.USER_ID + "&roleId=" + data.ROLE_ID;
                } else if (data.code == 1) {
                    layer.msg(data.msg, {icon: 1, time: 1000});
                } else {
                    layer.msg('登录失败!', {icon: 2, time: 1000});
                }
            },
            error: function (data) {
                layer.msg('登录失败!', {icon: 2, time: 1000});
            }
        });
    });
</script>
</body>
</html>