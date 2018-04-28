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
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>产品名称：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" class="input-text layui-input" value="" placeholder="" id="pName" name="name" lay-verify="required" required>
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3">来源：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" class="input-text" value="" placeholder="" id="pSource" name="source" required>
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>产品主图：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input name="mainPath" type="hidden"/>
                <button type="button" class="layui-btn" id="mainPath">
                    <i class="layui-icon">&#xe67c;</i>上传主图
                </button>
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class=""></span></label>
            <div class="formControls col-xs-8 col-sm-9" id="mainPathSrc">

            </div>
        </div>

        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>产品附图：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input name="imagePath" type="hidden"/>
                <button type="button" class="layui-btn" id="imagePath">
                    <i class="layui-icon">&#xe67c;</i>上传附图
                </button>
                <span style="font-size: 10px;">(最多9张)</span>
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class=""></span></label>
            <div class="formControls col-xs-8 col-sm-9" id="imagePathSrc">

            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class=""></span>产品分类：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <select id="pType" name="typeId" class="select" style="height: 32px">
                    <option value="">请选择</option>
                </select>
            </div>
        </div>

        <%--<div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>产品SKU：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" class="input-text" value="" placeholder="" id="pSku" name="sku">
            </div>
        </div>--%>

        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>价格：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" class="input-text" value="" placeholder="" id="pPrice" name="price"
                       lay-verify="number"/>
            </div>
        </div>

        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>产品信息：</label>
            <div class="formControls col-xs-8 col-sm-9">

                <textarea name="info" placeholder="" class="layui-textarea" id="pInfo" lay-verify="required"></textarea>
            </div>
        </div>
        <input type="hidden" name="id" id="id" />
        <div class="row cl">
            <div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-3">
                <button type="button" class="btn btn-success radius " lay-submit  name="product_save"><i
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
<script type="text/javascript" src="<%=request.getContextPath()%>/assistant/lib/layui/layui.js"></script>
<script type="text/javascript">
    function getParam(paramName) {
        paramValue = "", isFound = !1;
        console.log(this.location);
        if (this.location.search.indexOf("?") == 0 && this.location.search.indexOf("=") > 1) {
            arrSource = unescape(this.location.search).substring(1, this.location.search.length).split("&"), i = 0;
            while (i < arrSource.length && !isFound) arrSource[i].indexOf("=") > 0 && arrSource[i].split("=")[0].toLowerCase() == paramName.toLowerCase() && (paramValue = arrSource[i].split("=")[1], isFound = !0), i++
        }
        return paramValue == "" && (paramValue = null), paramValue
    }
    var id = '';
    $(function () {
        id = getParam("id");
        if(id!=null&&id!=""){
            initProductInfo();
        }

        layui.use('form', function () {
            var form = layui.form;

            form.on("submit",function (data) {
                var url = '<%=request.getContextPath()%>/product/save';
                var imgs=[];
                $("#imagePathSrc img").each(function (i,val) {
                    imgs.push($(val).attr("val"));
                });
                $("input[name='imagePath']").val(imgs.join(","));
                $.ajax({
                    type: 'POST',
                    url: url,
                    dataType: 'json',
                    data: {
                        "data": JSON.stringify(getFormJson("#addProductForm"))
                    },
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

            //各种基于事件的操作，下面会有进一步介绍
        });
        layui.use('upload', function () {
            var upload = layui.upload;

            //执行实例
            var uploadInst = upload.render({
                accept: "images",
                acceptMime: "image/*",
                elem: '#mainPath' //绑定元素
                , url: '<%=request.getContextPath()%>/upload/image' //上传接口
                , done: function (res) {
                    $("#mainPathSrc").html("<img width='100px' height='90px' src=<%=request.getContextPath()%>/upload/" + res.data + " />")
                    $("input[name='mainPath']").val(res.data);
                    //上传完毕回调
                }
                , error: function () {
                    //请求异常回调
                }
            });


            var uploadMuti = upload.render({
                accept: "images",
                acceptMime: "image/*",
                elem: '#imagePath', //绑定元素
                multiple: true
                , number: 9
                , url: '<%=request.getContextPath()%>/upload/image' //上传接口
                , done: function (res) {
                    $("#imagePathSrc").append("<img width='100px' height='90px' style='margin-left:2px;margin-top:2px' src=<%=request.getContextPath()%>/upload/" + res.data + " val='"+res.data+"' />")
                    //上传完毕回调
                    if ($("#imagePathSrc img").length > 9) {//最多上传9张
                        $("#imagePathSrc img").each(function (i, val) {
                            if (i > 8) {
                                $(val).remove();
                            }else{

                            }
                        });
                    }
                }
                , error: function () {
                    //请求异常回调
                }
            });
        });

    });
    function initProductInfo() {
        if (id != null && id != "" && id != "null" && id != undefined) {
            $("#id").val(id);
            $.ajax({
                type: 'POST',
                url: '<%=request.getContextPath()%>/product/detail',
                dataType: 'json',
                data: {
                    "id": id
                },
                success: function (data) {

                    if (data.success) {
                        var data = data.data;
                        $("#pName").val(data.NAME);
                        $("#mainPathSrc").html("<img width='100px' height='90px' src=<%=request.getContextPath()%>/upload/" + data.MAIN_PATH + " />")
                        $("input[name='mainPath']").val(data.MAIN_PATH);
                        $("#pSource").val(data.SOURCE);
                        if(data.IMAGE_PATH!=null){
                             var ims=data.IMAGE_PATH.split(",");
                             if(ims.length>0){
                                 for(var i=0;i<ims.length;i++){
                                     $("#imagePathSrc").append("<img width='100px' style='margin-left:2px;margin-top:2px' height='90px' src=<%=request.getContextPath()%>/upload/" + ims[i] + " val='"+ims[i]+"' />")
                                 }

                             }

                        }
                        $("#pPrice").val(data.PRICE);
                        $("#pInfo").val(data.INFO);
                    } else {
                        layer.msg(data.msg, {icon: 5, time: 1000});
                    }
                },
                error: function (data) {
                    layer.msg(data.msg, {icon: 5, time: 1000});
                }
            });
        }
    }
    $("#product_save").click(function () {



    });
</script>
</body>
</html>
