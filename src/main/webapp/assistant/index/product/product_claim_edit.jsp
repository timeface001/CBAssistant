<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
        .width300{width: 300px;}
    </style>
</head>
<body>
<article class="page-container">

    <blockquote class="layui-elem-quote">产品信息</blockquote>
    <form class="layui-form" action="">
        <div class="layui-form-item">
            <label class="layui-form-label">售卖形式</label>
            <div class="layui-input-block" >
                <input type="radio" name="skuType" lay-filter="skuType" value="1" title="单体" checked="checked">
                <input type="radio" name="skuType" lay-filter="skuType" value="2" title="多变种" >
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">ParentSKU</label>
            <div class="layui-input-block">
                <input type="text" name="title" required  lay-verify="required" placeholder="请输入ParentSKU" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">产品标题</label>
            <div class="layui-input-block">
                <input type="text" name="title" value="${product.itemCn}"  id="pName" readonly="readonly"  lay-verify="required" placeholder=""  autocomplete="off" class="layui-input layui-disabled">
            </div>
        </div>

        <div id="skuMutiDiv" >
        <blockquote class="layui-elem-quote">变种信息</blockquote>
        <div class="layui-form-item" >
            <label class="layui-form-label">变种主题</label>
            <div class="layui-input-block">
                <select name="city" lay-filter="skuMuti" >
                    <option value=""></option>
                    <c:forEach items="${typeList}" var="type">
                        <option value="${type.variationType}">${type.variationName}</option>
                    </c:forEach>

                </select>
            </div>
        </div>

            <div lay-filter="skuRender" id="skuRender">

            </div>
        </div>


        <blockquote class="layui-elem-quote">价格信息</blockquote>
        <div id="priceInfo">
        <div class="layui-form-item">
                <label class="layui-form-label">价格</label>
                <div class="layui-inline">
                    <input type="text" name="price" required lay-verType="msg"  lay-verify="required" placeholder="" autocomplete="off" class="layui-input">
                </div>

        </div>

            <div class="layui-form-item">
                <label class="layui-form-label">促销价</label>
                <div class="layui-inline">
                    <input type="text" name="salePrice"   placeholder="" autocomplete="off" class="layui-input">
                </div>

            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">促销时间</label>
                <div class="layui-inline">
                    <input type="text" name="saleStartTime" id="saleStartTime" lay-verify="date"   placeholder="" autocomplete="off" class="layui-input">
                </div>
                -
                <div class="layui-inline">
                    <input type="text" name="saleEndTime" id="saleEndTime"  lay-verify="date"  placeholder="" autocomplete="off" class="layui-input">
                </div>

            </div>



        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">数量</label>
            <div class="layui-inline">
                <input type="text" name="quantity" required  lay-verify="required" placeholder="请输入数量" autocomplete="off" class="layui-input">
            </div>
        </div>


        <blockquote class="layui-elem-quote">描述信息</blockquote>
        <div class="layui-form-item" >
            <label class="layui-form-label">产品描述</label>
            <div class="layui-input-block">
                <textarea name="pDesc" placeholder="请输入产品描述" class="layui-textarea"></textarea>
            </div>

        </div>

        <div class="layui-form-item" >
            <label class="layui-form-label">简要描述</label>

            <div class="layui-inline">
                <input type="text" name="bulletPoint1"  style="width: 300px"  lay-verify="required" placeholder="" autocomplete="off" class="layui-input ">
            </div>
            <div class="layui-inline">
                <input type="text" name="bulletPoint2" style="width: 300px"  lay-verify="required"   placeholder="" autocomplete="off" class="layui-input">
            </div>

        </div>


        <div class="layui-form-item" >
            <label class="layui-form-label"></label>

            <div class="layui-inline">
                <input type="text" name="bulletPoint3" style="width: 300px" lay-verify="required"  placeholder="" autocomplete="off" class="layui-input">
            </div>
            <div class="layui-inline">
                <input type="text" name="bulletPoint4" style="width: 300px"  lay-verify="required"   placeholder="" autocomplete="off" class="layui-input">
            </div>

        </div>

        <div class="layui-form-item" >
            <label class="layui-form-label"></label>

            <div class="layui-inline">
                <input type="text" name="bulletPoint5" style="width: 300px"  lay-verify="required"   placeholder="" autocomplete="off" class="layui-input">
            </div>
        </div>


        <blockquote class="layui-elem-quote">关键词信息</blockquote>
        <div class="layui-form-item" >
            <label class="layui-form-label">关键词</label>

            <div class="layui-inline">
                <input type="text" name="keyword1"  style="width: 300px" lay-verify="required"  placeholder="" autocomplete="off" class="layui-input ">
            </div>
            <div class="layui-inline">
                <input type="text" name="keyword2" style="width: 300px" lay-verify="required"   placeholder="" autocomplete="off" class="layui-input">
            </div>

        </div>


        <div class="layui-form-item" >
            <label class="layui-form-label"></label>

            <div class="layui-inline">
                <input type="text" name="keyword3" style="width: 300px"  lay-verify="required" placeholder="" autocomplete="off" class="layui-input">
            </div>
            <div class="layui-inline">
                <input type="text" name="keyword4" style="width: 300px" lay-verify="required"    placeholder="" autocomplete="off" class="layui-input">
            </div>

        </div>

        <div class="layui-form-item" >
            <label class="layui-form-label"></label>

            <div class="layui-inline">
                <input type="text" name="keyword5" style="width: 300px"  lay-verify="required"   placeholder="" autocomplete="off" class="layui-input">
            </div>
        </div>

        <div class="layui-form-item" >
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit lay-filter="formDemo">保存</button>
                <button type="reset" class="layui-btn layui-btn-primary" href="location.href='<%=request.getContextPath()%>/assistant/index/product/product_claim_list.jsp'">返回</button>
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


        singleSku();
        //日期
        layui.use('laydate', function(){
            var laydate = layui.laydate;

            laydate.render({
                elem: '#saleStartTime' //促销开始时间
            });

            laydate.render({
                elem: '#saleEndTime' //促销结束时间
            });
        });

        layui.use('form', function(){
            var form = layui.form;

            form.on('radio(skuType)', function(data){//切换变体类型
                if($(this).val()=="2"){//选中多变体
                    mutiSku();

                }else{//单体
                   singleSku();
                }
            });

            form.on('select(skuMuti)', function(data){//刷新

                console.log(data.value);
                $("#skuRender").append("<div class=\"layui-form-item\" >\n" +
                    "<div class=\"layui-input-block\">\n" +
                    "   Size: </div>" +
                    "<div class=\"layui-input-block\">\n" +
                    "    <div class=\"layui-input-inline\" style=\"width: 100px;\">\n" +
                    "      <input type=\"text\" name=\"\" autocomplete=\"off\" class=\"layui-input\">\n" +
                    "    </div>\n" +
                    "   <div class=\"layui-input-inline\" style=\"width: 100px;\">\n" +
                    "      <button class=\"layui-btn\">添加</button>\n" +
                    "    </div>\n" +
                    "    </div>" +
                    "            </div>");
                form.render(null, 'skuRender');
            });
        });

        function genSkuTypeDom(type) {
            "<div class=\"layui-form-item\" >\n" +
            "<div class=\"layui-input-block\">\n" +
            "   Size: </div>" +
            "<div class=\"layui-input-block\">\n" +
            "    <div class=\"layui-input-inline\" style=\"width: 100px;\">\n" +
            "      <input type=\"text\" name=\"\" autocomplete=\"off\" class=\"layui-input\">\n" +
            "    </div>\n" +
            "   <div class=\"layui-input-inline\" style=\"width: 100px;\">\n" +
            "      <button class=\"layui-btn\">添加</button>\n" +
            "    </div>\n" +
            "    </div>" +
            "            </div>"
        }

        function singleSku() {
            $("#skuMutiDiv").css("display","none");
            $("#skuSingleDiv").css("display","");
            $("#priceInfo").css("display","");
        }

        function mutiSku() {
            $("#skuMutiDiv").css("display","");
            $("#skuSingleDiv").css("display","none");
            $("#priceInfo").css("display","none");
        }

        initProductInfo();

        layui.use('upload', function(){
            var upload = layui.upload;

            //执行实例
            var uploadInst = upload.render({
                accept:"images",
                acceptMime:"image/*",
                elem: '#mainPath' //绑定元素
                ,url: '<%=request.getContextPath()%>/upload/image' //上传接口
                ,done: function(res){
                    $("#mainPathSrc").html("<img width='100px' height='90px' src=<%=request.getContextPath()%>/upload/"+res.data+" />")
                    $("#mainPath").val(res.data);
                    //上传完毕回调
                }
                ,error: function(){
                    //请求异常回调
                }
            });


            var uploadMuti = upload.render({
                accept:"images",
                acceptMime:"image/*",
                elem: '#imagePath', //绑定元素
                multiple:true
                ,number:9
                ,url: '<%=request.getContextPath()%>/upload/image' //上传接口
                ,done: function(res){
                    $("#imagePathSrc").append("<img width='100px' src=<%=request.getContextPath()%>/upload/"+res.data+" />")
                    $("#imagePath").val(res.data);
                    //上传完毕回调
                    if($("#imagePathSrc img").length>9){//最多上传9张
                        $("#imagePathSrc img").each(function (i,val) {
                            if(i>8){
                                $(val).remove();
                            }
                        });
                    }
                }
                ,error: function(){
                    //请求异常回调
                }
            });
        });

    });
    function initProductInfo() {

        /*var product=JSON.parse();
        console.log(product.itemCn);
        console.log(product["itemCn"]);
        console.log(JSON.stringify(product));
        $("#pName").val(product.itemCn);*/
    }
    $("#product_save").click(function () {

        var url = '<%=request.getContextPath()%>/product/claim/save';
        console.log(getFormJson("#addProductForm"));
        $.ajax({
            type: 'POST',
            url: url,
            dataType: 'json',
            data: {
                "data": JSON.stringify(getFormJson("#addProductForm"))
            },
            success: function (data) {
                if(data.success){
                    setTimeout(layer.msg(data.msg, {icon: 6, time: 1000}),1300);
                    location.href='<%=request.getContextPath()%>/assistant/index/product/product_claim_edit.jsp?id='+id;
                }else{
                    layer.msg(data.msg, {icon: 5, time: 1000});
                }

            },
            error: function (data) {
                layer.msg(data.msg, {icon: 5, time: 1000});
            },
        });
    });
</script>
</body>
</html>
