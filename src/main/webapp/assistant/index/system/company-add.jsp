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
    <title>添加公司</title>
</head>
<body>
<article class="page-container">
    <form action="<%=request.getContextPath()%>/system/addCompany" method="post" class="form form-horizontal"
          id="addCompanyForm">
        <input type="hidden" value="<%=id%>" name="id">
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>公司名称：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" class="input-text" value="" placeholder="" id="companyName" name="companyName">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3">推荐人：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" class="input-text" value="" placeholder="" id="comReferee" name="comReferee">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3">状态：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <select id="state" name="state" class="select" style="height: 32px">
                    <option value="1" selected>启用</option>
                    <option value="0">停用</option>
                </select>
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3">备注：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" class="input-text" value="" placeholder="" id="comDesc" name="comDesc">
            </div>
        </div>
        <div class="row cl">
            <div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-3">
                <button type="button" class="btn btn-success radius" id="company-save" name="company-save"><i
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
        if (id != null && id != "" && id != "null" && id != undefined) {
            $.ajax({
                type: 'POST',
                url: '<%=request.getContextPath()%>/system/selectCompanies',
                dataType: 'json',
                data: {
                    "id": id
                },
                success: function (data) {
                    if (data.code == 0) {
                        var data = data.data[0];
                        $("#companyName").val(data.COMPANY_NAME);
                        $("#comReferee").val(data.COM_REFEREE);
                        $("#state").val(data.COMPANY_STATE);
                        $("#comDesc").val(data.COM_DESC);
                    } else {
                        layer.msg(data.msg, {icon: 2, time: 1000});
                    }
                },
                error: function (data) {
                    layer.msg(data.msg, {icon: 2, time: 1000});
                },
            });
        }
        $("#addCompanyForm").validate({
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
    $("#company-save").click(function () {
        var url = '<%=request.getContextPath()%>/system/addCompany';
        if (id != null && id != "" && id != "null" && id != undefined) {
            url = '<%=request.getContextPath()%>/system/updateCompany';
        } else {
            url = '<%=request.getContextPath()%>/system/addCompany';
        }
        $.ajax({
            type: 'POST',
            url: url,
            dataType: 'json',
            data: {
                "data": JSON.stringify(getFormJson("#addCompanyForm"))
            },
            success: function (data) {
                layer.msg(data.msg, {icon: 1, time: 1000});
                layer_close();
            },
            error: function (data) {
                layer.msg(data.msg, {icon: 2, time: 1000});
            },
        });
    });
</script>
<!--/请在上方写此页面业务相关的脚本-->
</body>
</html>
