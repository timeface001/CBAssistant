<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
    <title>产品编辑</title>
    <style>
        .width300 {
            width: 300px;
        }
    </style>
</head>
<body>
<article class="page-container">

    <blockquote class="layui-elem-quote">产品信息</blockquote>
    <form class="layui-form" action="" id="addProductForm">
        <input name="id" type="hidden" value="${product.id}"/>

        <div class="layui-form-item">
            <label class="layui-form-label">ParentSKU</label>
            <div class="layui-input-block">
                <input type="text" name="itemSku" lay-verify="required" placeholder="" autocomplete="off"
                       class="layui-input" value="${product.itemSku}">
            </div>
        </div>


        <div class="layui-form-item">
            <label class="layui-form-label">产品标题</label>
            <div class="layui-input-block">
                <input type="text" name="itemName" value="${product.itemName}" id="itemName"
                       lay-verify="required" placeholder="" autocomplete="off" class="layui-input">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">产品ID</label>
            <div class="layui-inline">
                <input type="text" name="externalProductId" value="${product.externalProductId}" id="externalProductId"
                       lay-verify="required" placeholder="" autocomplete="off" class="layui-input ">
            </div>
            <div class="layui-inline">
                <select name="externalProductIdType" lay-verify="required" id="externalProductIdType">
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

        <div class="layui-form-item">
            <label class="layui-form-label">品牌</label>
            <div class="layui-input-block">
                <input type="text" name="brandName" value="${product.brandName}" id="brandName"
                       lay-verify="required" placeholder="" autocomplete="off" class="layui-input ">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">制造商</label>
            <div class="layui-input-block">
                <input type="text" name="manufacturer" value="${product.manufacturer}" id="manufacturer"
                       lay-verify="required" placeholder="" autocomplete="off" class="layui-input">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">商品重量</label>
            <div class="layui-input-block">
                <input type="text" name="itemWeight" value="${product.itemWeight}" id="itemWeight"
                       lay-verify="number" placeholder="" autocomplete="off" class="layui-input ">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">运输重量</label>
            <div class="layui-input-block">
                <input type="text" name="websiteShippingWeight" value="${product.websiteShippingWeight}" id="websiteShippingWeight"
                       lay-verify="required" placeholder="" autocomplete="off" class="layui-input ">
            </div>
        </div>


        <div class="layui-form-item">
            <label class="layui-form-label">运输方式</label>
            <div class="layui-input-block">
                <input type="radio" name="websiteType" value="FBM" title="FBM" checked>
                <input type="radio" name="websiteType" value="FBA" title="FBA" >
            </div>
        </div>



        <div class="layui-form-item">
            <div class="layui-input-block">
                <%--<button class="layui-btn" lay-submit lay-filter="formDemo">保存</button>--%>
                <button class="layui-btn" lay-submit lay-filter="formDemo">发布</button>
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
    var id = '';
    $(function () {

        var skuArr = [];
        //日期

        layui.use('laydate', function () {
            var laydate = layui.laydate;
            laydate.render({
                elem: '#saleStartTime' //促销开始时间
            });

            laydate.render({
                elem: '#saleEndTime' //促销结束时间
            });

            laydate.render({
                elem: '.saleStart' //促销开始时间
            });

            laydate.render({
                elem: '.saleEnd' //促销结束时间
            });
        });

        layui.use('form', function () {
            var form = layui.form;

            form.on('submit', function (data) {
                /*console.log(data);
                return false;*/
                $.ajax({
                    type: 'POST',
                    url: '<%=request.getContextPath()%>/product/publish',
                    dataType: 'json',
                    data:
                         data.field
                    ,
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
                    }
                });

            });
        });
    });
</script>
</body>
</html>
