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

    <form class="layui-form" action="">
        <input name="id" type="hidden" value="${product.id}"/>

        <div id="cateDiv">

            <div class="layui-form-item">
                <label class="layui-form-label"></label>
                <div class="layui-input-block layui-form" lay-filter="cate1">
                    <select name="cate1"  lay-filter="cate11"  id="cate1">
                        <option value="">请选择</option>
                    </select>
                </div>
            </div>

            <div class="layui-form-item" >
                <label class="layui-form-label"></label>
                <div class="layui-input-block layui-form" lay-filter="cate2">
                    <select name="cate2" class="layui-form" lay-filter="cate22" id="cate2">
                        <option value="">请选择</option>
                    </select>
                </div>
            </div>
        </div>




        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" type="button" lay-filter="formDemo">保存</button>
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


        console.log("<%=request.getParameter("shopId")%>");


        layui.use('element', function () {
            var element = layui.element;

            //…
        });


        function initSkuSaleDate() {
            var laydate = layui.laydate;
            layui.use('laydate', function () {

                laydate.render({
                    elem: '.saleStart' //促销开始时间
                });

                laydate.render({
                    elem: '.saleEnd' //促销结束时间
                });
            });
        }

        var selectValue = null;
        layui.use('form', function () {
            var form = layui.form;

            $.ajax({
                type: 'POST',
                url: '<%=request.getContextPath()%>/publish/category',
                dataType: 'json',
                data: {"shopId": "<%=request.getParameter("shopId")%>", parentId: -1},
                success: function (data) {
                    for (var i = 0; i < data.length; i++) {
                        $("#cate1").append("<option value='"+data[i].id+"' val="+data[i].hasChild+" type="+data[i]+">"+data[i].name+"</option>");
                    }
                    form.render("select","cate1");
                },
                error: function (data) {
                    layer.msg('翻译失败!', {icon: 5, time: 1000});
                }
            });

            form.on('select(cate11)', function (data) {
                if(data.value!=null &&data.value!=''){
                    $.ajax({
                        type: 'POST',
                        url: '<%=request.getContextPath()%>/publish/category',
                        dataType: 'json',
                        data: {"shopId": "<%=request.getParameter("shopId")%>", parentId: data.value},
                        success: function (data) {
                            $("#cate2").html("<option value=''>请选择</option>");
                            for (var i = 0; i < data.length; i++) {
                                $("#cate2").append("<option value='"+data[i].id+","+data[i].hasChild+"' val="+data[i].hasChild+" type="+data[i]+">"+data[i].name+"</option>");
                            }

                            $("#cateDiv > div").each(function (i,val) {
                                if(i>1){
                                    $(val).remove();

                                }
                            });
                            form.render("select","cate2");
                        },
                        error: function (data) {
                            layer.msg('翻译失败!', {icon: 5, time: 1000});
                        }
                    });
                }
            });

            $("").click();

            form.on('select(cate22)', function (data) {
                if(data.value!=null &&data.value!=''&&data.value.split(",")[1]=="1"){
                    $.ajax({
                        type: 'POST',
                        url: '<%=request.getContextPath()%>/publish/category',
                        dataType: 'json',
                        data: {"shopId": "<%=request.getParameter("shopId")%>", parentId: data.value.split(",")[0]},
                        success: function (data) {
                            $("#cateDiv > div").each(function (i,val) {
                                if(i>1){
                                    $(val).remove();

                                }
                            });
                            $("#cateDiv > div :eq(1)").after(addNext(data,3));
                            form.render("select","cate3");
                        },
                        error: function (data) {
                            layer.msg('翻译失败!', {icon: 5, time: 1000});
                        }
                    });
                }else{
                    $("#cateDiv > div").each(function (i,val) {
                        if(i>1){
                            $(val).remove();

                        }
                    });
                }
            });

            form.on('select(cate33)', function (data) {
                if(data.value!=null &&data.value!=''&&data.value.split(",")[1]=="1"){
                    $.ajax({
                        type: 'POST',
                        url: '<%=request.getContextPath()%>/publish/category',
                        dataType: 'json',
                        data: {"shopId": "<%=request.getParameter("shopId")%>", parentId: data.value.split(",")[0]},
                        success: function (data) {
                            $("#cateDiv > div").each(function (i,val) {
                                if(i>2){
                                    $(val).remove();

                                }
                            });
                            $("#cateDiv > div :eq(2)").after(addNext(data,4));
                            form.render("select","cate4");
                        },
                        error: function (data) {
                            layer.msg('翻译失败!', {icon: 5, time: 1000});
                        }
                    });
                }else{
                    $("#cateDiv > div").each(function (i,val) {
                        if(i>3){
                            $(val).remove();

                        }
                    });
                }
            });


        });


        function addNext(data,index) {
            var opt="";
            for (var i = 0; i < data.length; i++) {
                opt+="<option value='"+data[i].id+","+data[i].hasChild+"' val="+data[i].hasChild+" type="+data[i].typeDef+">"+data[i].name+"</option>";
            }
            return "<div class=\"layui-form-item\">\n" +
                "            <label class=\"layui-form-label\"></label>\n" +
                "            <div class=\"layui-input-block layui-form\" lay-filter=\"cate3\">\n" +
                "                <select name='cate'"+index+" class=\"layui-form\" lay-filter=\"cate\""+index+''+index+" id=\"cate\""+index+">" +
                "                    <option value=\"\">请选择</option>\n" +opt
                "                </select>\n" +
                "            </div>\n" +
                "        </div>";
        }

        //将时间戳格式化
        function getMyDate(time) {
            if (typeof(time) == "undefined") {
                return "";
            }
            var oDate = new Date(time),
                oYear = oDate.getFullYear(),
                oMonth = oDate.getMonth() + 1,
                oDay = oDate.getDate(),
                oHour = oDate.getHours(),
                oMin = oDate.getMinutes(),
                oSen = oDate.getSeconds(),
                oTime = oYear + '-' + getzf(oMonth) + '-' + getzf(oDay);//+' '+ getzf(oHour) +':'+ getzf(oMin) +':'+getzf(oSen);//最后拼接时间

            return oTime;
        }


        //补0操作,当时间数据小于10的时候，给该数据前面加一个0
        function getzf(num) {
            if (parseInt(num) < 10) {
                num = '0' + num;
            }
            return num;
        }

    });


</script>
</body>
</html>
