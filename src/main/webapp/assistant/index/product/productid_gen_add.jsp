<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <title>添加产品</title>
</head>
<body>
<article class="page-container">
    <form class="form form-horizontal layui-form" id="addProductForm">

        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3">类型：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <select lay-verify="required" name="type" id="externalProductIdType">
                    <option value="">--</option>
                    <option value="ISBN">ISBN</option>
                    <option value="UPC">UPC</option>
                    <option value="EAN">EAN</option>
                    <option value="ASIN">ASIN</option>
                    <option value="GTIN">GTIN</option>
                    <option value="GCID">GCID</option>
                    <option value="PZN">PZN</option>
                </select>
            </div>
        </div>

        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3">产品ID：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <textarea type="text" class="layui-textarea" value="" placeholder="" id="pName" name="data"
                          lay-verify="required" onkeyup="this.value=this.value.replace(/[^\d]/g,'')"></textarea>
            </div>
        </div>

        <div class="row cl">
            <div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-3">
                <button type="button" class="btn btn-success radius " lay-submit name="product_save"><i
                        class="icon-ok"></i>保存
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
<script type="text/javascript" src="<%=request.getContextPath()%>/assistant/lib/layui/layui.js"></script>
<script type="text/javascript">
    $(function () {
        layui.use('form', function () {
            var form = layui.form;
            form.on("submit", function (data) {
                var url = '<%=request.getContextPath()%>/productid/add';
                $.ajax({
                    type: 'POST',
                    url: url,
                    dataType: 'json',
                    data: data.field,
                    success: function (data) {
                        if (data.success) {
                            setTimeout(layer.msg(data.msg, {icon: 6, time: 1000}), 1300);
                            layer_close();
                        } else {
                            layer.msg(data.msg, {icon: 5, time: 1000});
                        }
                    },
                    error: function (data) {
                        layer.msg(data.msg, {icon: 5, time: 1000});
                    },
                });
            });
        });
    });
</script>
</body>
</html>
