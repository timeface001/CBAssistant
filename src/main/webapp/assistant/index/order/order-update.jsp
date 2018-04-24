<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="Content-Type" content="multipart/form-data; charset=utf-8"/>
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
    <title>退款</title>
</head>
<body>
<div class="pd-20">
    <div class="Huiform">
        <form id="fileForm" class="form form-horizontal">
            <div class="row cl">
                <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>批量修改：</label>
                <div class="formControls col-xs-8 col-sm-9">
                    <input class="input-text" type="text" name="fileUrl" id="fileUrl" readonly
                           style="width:200px">
                    <span class="btn-upload form-group">
			            <a href="javascript:;" class="btn btn-primary upload-btn"><i class="Hui-iconfont">&#xe642;</i>
                            上传Excel</a>
			                <input type="file"
                                   accept="application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"
                                   name="file" id="file" class="input-file" onchange="fileChange(this)">
			            </span>
                </div>
            </div>
            <div class="row cl">
                <div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-3">
                    <input class="btn btn-primary radius" id="submit" type="button" value="&nbsp;&nbsp;提交&nbsp;&nbsp;">
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
        $("#fileForm").validate({
            rules: {
                uploadfile: {
                    required: true,
                }
            },
            onkeyup: false,
            focusCleanup: true,
            success: "valid",
            submitHandler: function (form) {
                var index = parent.layer.getFrameIndex(window.name);
                parent.layer.close(index);
            }
        });
    });
    function fileChange(file) {
        document.getElementById("fileUrl").value = file.value;
    }
    $("#submit").click(function () {
        var fileObj = document.getElementById("file").files[0];
        var form = new FormData();
        form.append("file", fileObj);
        $.ajax({
            type: 'POST',
            url: '<%=request.getContextPath()%>/order/updateByExcel',
            dataType: 'json',
            data: form,
            processData: false,//用于对data参数进行序列化处理 这里必须false
            contentType: false, //必须
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
