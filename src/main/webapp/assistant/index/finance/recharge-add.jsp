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
    <link rel="stylesheet" type="text/css"
          href="<%=request.getContextPath()%>/assistant/lib/layui/css/layui.css"/>
    <!--[if IE 6]>
    <script type="text/javascript"
            src="<%=request.getContextPath()%>/assistant/lib/DD_belatedPNG_0.0.8a-min.js"></script>
    <script>DD_belatedPNG.fix('*');</script>
    <![endif]-->
    <title>充值</title>
</head>
<body>
<article class="page-container">
    <form class="form form-horizontal" id="accountForm">
        <input id="roleId" type="hidden" value="${sessionScope.user.ROLE_ID}">
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>用户公司：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <select id="companyId" name="companyId" class="select" style="height: 32px"
                        onchange="loadBalance(this.value)">
                    <option value="">请选择</option>
                </select>
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>流水号：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" class="input-text" id="id"
                       name="id">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>账户总余额：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" class="input-text" value="0" placeholder="" id="balance"
                       name="balance" readonly>
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>账户可用总余额：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" class="input-text" value="0" placeholder="" id="aBalance"
                       name="aBalance" readonly>
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>汇款方式：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <select id="mode" name="mode" class="select" style="height: 32px">
                    <option value="1">银联</option>
                    <option value="2">支付宝</option>
                    <option value="3">微信</option>
                </select>
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>收款账号：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <select id="account" name="account" class="select" style="height: 32px">
                    <option value="1">招商银行太原府西街支行/张永军/6214 8335 1693 6849</option>
                    <option value="2">支付宝：15034048635</option>
                    <option value="3">微信：15034048635</option>
                </select>
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>汇款时间：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" onfocus="WdatePicker({maxDate:'%y-%M-%d'})"
                       name="createTime" class="input-text Wdate" id="createTime" readonly>
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>充值金额：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" class="input-text" value="" placeholder="" id="amount" name="amount">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3">上传充值凭证：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="hidden" class="input-text" id="voucher" name="voucher">
                <button type="button" class="layui-btn" id="imagePath">
                    <i class="layui-icon">&#xe67c;</i>上传图片
                </button>
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class=""></span></label>
            <div class="formControls col-xs-8 col-sm-9" id="imagePathSrc">

            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3">备注：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" class="input-text" value="" placeholder="" id="remark" name="remark">
            </div>
        </div>
        <div class="row cl">
            <div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-3">
                <button type="submit" class="btn btn-success radius"><i
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
<script type="text/javascript"
        src="<%=request.getContextPath()%>/assistant/lib/My97DatePicker/4.8/WdatePicker.js"></script>
<!--/_footer 作为公共模版分离出去-->

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript"
        src="<%=request.getContextPath()%>/assistant/lib/jquery.validation/1.14.0/jquery.validate.js"></script>
<script type="text/javascript"
        src="<%=request.getContextPath()%>/assistant/lib/jquery.validation/1.14.0/validate-methods.js"></script>
<script type="text/javascript"
        src="<%=request.getContextPath()%>/assistant/lib/jquery.validation/1.14.0/messages_zh.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/assistant/lib/layui/layui.js"></script>
<script type="text/javascript">
    var roleId = $("#roleId").val();
    $(function () {
        $("#createTime").val(new Date().format("yyyy-MM-dd"));
        initSelect();
        $("#accountForm").validate({
            rules: {
                id: {
                    required: true,
                },
                balance: {
                    required: true,
                    number: true
                },
                aBalance: {
                    required: true,
                    number: true
                },
                mode: {
                    required: true,
                },
                account: {
                    required: true,
                },
                createTime: {
                    required: true,
                },
                voucher: {
                    required: true,
                },
                amount: {
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
        layui.use('upload', function () {
            var upload = layui.upload;
            //执行实例
            var uploadInst = upload.render({
                accept: "images",
                acceptMime: "image/*",
                elem: '#imagePath' //绑定元素
                , url: '<%=request.getContextPath()%>/upload/image' //上传接口
                , done: function (res) {
                    $("#imagePathSrc").html("<img width='100px' height='90px' src=<%=session.getAttribute("productPath")%>" + res.data + " />")
                    $("input[name='voucher']").val(res.data);
                    //上传完毕回调
                }
                , error: function () {
                    //请求异常回调
                }
            });
        });
    });
    function loadBalance(id) {
        $.ajax({
            type: 'POST',
            url: '<%=request.getContextPath()%>/account/getAccountsByCompanyId',
            dataType: 'json',
            data: {
                "companyId": id
            },
            success: function (data) {
                if (data.code == 0) {
                    var data = data.data;
                    if (data.length == 1) {
                        $("#aBalance").val(data[0].ABALANCE);
                        $("#balance").val(data[0].BALANCE);
                    } else {
                        $("#aBalance").val("0");
                        $("#balance").val("0");
                    }
                }
            },
            error: function (data) {
                layer.msg(data.msg, {icon: 2, time: 1000});
            }
        });
    }
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
    function ajaxSubmit() {
        layer.load();
        $.ajax({
            type: 'POST',
            dataType: 'json',
            data: {
                "data": JSON.stringify(getFormJson("#accountForm"))
            },
            url: "<%=request.getContextPath()%>/account/recharge",
            error: function () {
                layer.closeAll("loading");
                layer.msg('充值失败!', {icon: 2, time: 2000});
            },
            success: function (data) {
                layer.closeAll("loading");
                if (data.code == 0) {
                    var index = parent.layer.getFrameIndex(window.name);
                    parent.layer.close(index);
                } else {
                    layer.msg('充值失败!', {icon: 2, time: 2000});
                }
            }
        });
    }
</script>
</body>
</html>
