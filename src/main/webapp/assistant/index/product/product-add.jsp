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
    <link rel="stylesheet" type="text/css"
          href="<%=request.getContextPath()%>/assistant/release/wangEditor.min.css"/>
    <script type="text/javascript" charset="utf-8"
            src="<%=request.getContextPath()%>/assistant/release/wangEditor.min.js"></script>
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
                <input type="text" class="input-text" value="" placeholder="" id="pName" name="name"
                       lay-verify="required" required>
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
                <input name="mainPath" type="hidden" lay-verify="required"/>
                <button type="button" class="layui-btn" id="mainPath">
                    <i class="layui-icon">&#xe67c;</i>上传图片
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
                <input name="imagePath" type="hidden" lay-verify="required"/>
                <button type="button" class="layui-btn" id="imagePath">
                    <i class="layui-icon">&#xe67c;</i>上传图片
                </button>
                <span style="font-size: 10px;">(最多8张)</span>
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class=""></span></label>
            <div class="formControls col-xs-8 col-sm-9" id="imagePathSrc">

            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>产品分类：</label>
            <div class="formControls col-xs-6 col-sm-7">
                <input type="text" class="input-text" value="" placeholder="请选择" id="typeName" name="typeName"
                       lay-verify="required" readonly>
                <input type="hidden" name="typeId" id="typeId">
            </div>
            <div class="formControls col-xs-2 col-sm-2">
                <button type="button" class="btn btn-success radius" id="typeBtn"><i
                        class="icon-ok"></i> 选择分类
                </button>
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
            <div class="formControls col-xs-8 col-sm-9" id="editor">
                <textarea name="info" id="pInfo" style="width: 100%" maxlength="2000"></textarea>
            </div>
        </div>
        <input type="hidden" name="id" id="id"/>
        <div class="row cl">
            <div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-3">
                <button type="button" class="btn btn-success radius " lay-submit name="product_save"><i
                        class="icon-ok"></i> 确定
                </button>
            </div>
        </div>
    </form>
    <div id="modal-demo" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
         aria-hidden="true">
        <div class="modal-dialog" style="min-width: 1000px">
            <div class="modal-content radius">
                <div class="modal-header">
                    <h3 class="modal-title">选择分类</h3>
                    <a class="close" data-dismiss="modal" aria-hidden="true" href="javascript:">×</a>
                </div>
                <div class="modal-body" style="height: 330px;overflow-x: auto">
                    <div id="content-div">
                        <div id="div1"
                             style="display: block;width: 240px;height: 300px; float: left;border: 1px solid #ddd;border-radius: 4px;padding: 5px 5px 5px 5px;margin-right: 10px;">
                            <select id="level-1" name="level-1" class="select" style="height: 32px;"
                                    onchange="loadTypes(this.value,2)">
                                <option value="">请选择</option>
                            </select>
                        </div>
                        <div id="div2"
                             style="display: none;width: 240px;height: 300px; float: left;border: 1px solid #ddd;border-radius: 4px;padding: 5px 5px 5px 5px;margin-right: 10px;">
                            <select id="level-2" name="level-2" class="select" style="height: 32px"
                                    onchange="loadTypes(this.value,3)">
                                <option value="">请选择</option>
                            </select>
                        </div>
                        <div id="div3"
                             style="display: none;width: 240px;height: 300px; float: left;border: 1px solid #ddd;border-radius: 4px;padding: 5px 5px 5px 5px;margin-right: 10px;">
                            <select id="level-3" name="level-3" class="select" style="height: 32px"
                                    onchange="loadTypes(this.value,4)">
                                <option value="">请选择</option>
                            </select>
                        </div>
                        <div id="div4"
                             style="display: none;width: 240px;height: 300px; float: left;border: 1px solid #ddd;border-radius: 4px;padding: 5px 5px 5px 5px;margin-right: 10px;">
                            <select id="level-4" name="level-4" class="select" style="height: 32px"
                                    onchange="loadTypes(this.value,5)">
                                <option value="">请选择</option>
                            </select>
                        </div>
                        <div id="div5"
                             style="display: none;width: 240px;height: 300px; float: left;border: 1px solid #ddd;border-radius: 4px;padding: 5px 5px 5px 5px;margin-right: 10px;">
                            <select id="level-5" name="level-5" class="select" style="height: 32px"
                                    onchange="loadTypes(this.value,6)">
                                <option value="">请选择</option>
                            </select>
                        </div>
                        <div id="div6"
                             style="display: none;width: 240px;height: 300px; float: left;border: 1px solid #ddd;border-radius: 4px;padding: 5px 5px 5px 5px;margin-right: 10px;">
                            <select id="level-6" name="level-6" class="select" style="height: 32px"
                                    onchange="loadTypes(this.value,7)">
                                <option value="">请选择</option>
                            </select>
                        </div>
                        <div id="div7"
                             style="display: none;width: 240px;height: 300px; float: left;border: 1px solid #ddd;border-radius: 4px;padding: 5px 5px 5px 5px;margin-right: 10px;">
                            <select id="level-7" name="level-7" class="select" style="height: 32px"
                                    onchange="loadTypes(this.value,8)">
                                <option value="">请选择</option>
                            </select>
                        </div>
                        <div id="div8"
                             style="display: none;width: 240px;height: 300px; float: left;border: 1px solid #ddd;border-radius: 4px;padding: 5px 5px 5px 5px;margin-right: 10px;">
                            <select id="level-8" name="level-8" class="select" style="height: 32px"
                                    onchange="loadTypes(this.value,9)">
                                <option value="">请选择</option>
                            </select>
                        </div>
                        <div id="div9"
                             style="display: none;width: 240px;height: 300px; float: left;border: 1px solid #ddd;border-radius: 4px;padding: 5px 5px 5px 5px;margin-right: 10px;">
                            <select id="level-9" name="level-9" class="select" style="height: 32px"
                                    onchange="loadTypes(this.value,10)">
                                <option value="">请选择</option>
                            </select>
                        </div>
                        <div id="div10"
                             style="display: none;width: 240px;height: 300px; float: left;border: 1px solid #ddd;border-radius: 4px;padding: 5px 5px 5px 5px;margin-right: 10px;">
                            <select id="level-10" name="level-10" class="select" style="height: 32px">
                                <option value="">请选择</option>
                            </select>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button class="btn btn-success radius" onclick="chooseType()">确定</button>
                    <button class="btn radius" data-dismiss="modal" aria-hidden="true">关闭</button>
                </div>
            </div>
        </div>
    </div>
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
    /*var E = window.wangEditor;
     var editor = new E('#editor');
     editor.create();*/
    function getParam(paramName) {
        paramValue = "", isFound = !1;
        if (this.location.search.indexOf("?") == 0 && this.location.search.indexOf("=") > 1) {
            arrSource = unescape(this.location.search).substring(1, this.location.search.length).split("&"), i = 0;
            while (i < arrSource.length && !isFound) arrSource[i].indexOf("=") > 0 && arrSource[i].split("=")[0].toLowerCase() == paramName.toLowerCase() && (paramValue = arrSource[i].split("=")[1], isFound = !0), i++
        }
        return paramValue == "" && (paramValue = null), paramValue
    }
    var id = '';
    var pInfo;
    var layedit;
    $(function () {
        id = getParam("id");
        if (id != null && id != "") {
            initProductInfo();
        }
        layui.use('layedit', function () {
            layedit = layui.layedit;
            pInfo = layedit.build('pInfo');
        });
        layui.use('form', function () {
            var form = layui.form;
            form.on("submit", function (data) {
                var url = '<%=request.getContextPath()%>/product/save';
                $("#pInfo").val(layedit.getContent(pInfo));
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
                    }
                });
            });
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
                , number: 8
                , url: '<%=request.getContextPath()%>/upload/image' //上传接口
                , done: function (res) {
                    $("#imagePathSrc").append("<img width='100px' height='90px' style='margin-left:2px;margin-top:2px' src=<%=request.getContextPath()%>/upload/" + res.data + " val='" + res.data + "' />")
                    var imgs = [];
                    $("#imagePathSrc img").each(function (i, val) {
                        imgs.push($(val).attr("val"));
                    });
                    $("input[name='imagePath']").val(imgs.join(","));
                    //上传完毕回调
                    if ($("#imagePathSrc img").length > 8) {//最多上传9张
                        $("#imagePathSrc img").each(function (i, val) {
                            if (i > 8) {
                                $(val).remove();
                            } else {

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
                        $("input[name='imagePath']").val(data.IMAGE_PATH);
                        $("#pSource").val(data.SOURCE);
                        if (data.IMAGE_PATH != null) {
                            var ims = data.IMAGE_PATH.split(",");
                            if (ims.length > 0) {
                                for (var i = 0; i < ims.length; i++) {
                                    $("#imagePathSrc").append("<img width='100px' style='margin-left:2px;margin-top:2px' height='90px' src=<%=request.getContextPath()%>/upload/" + ims[i] + " val='" + ims[i] + "' />")
                                }
                            }
                        }
                        $("#typeId").val(data.TYPE_ID);
                        $("#typeName").val(data.TYPENAME);
                        $("#pPrice").val(data.PRICE);
                        layedit.setContent(pInfo, data.INFO);
                        /*$("#pInfo").val(data.INFO);*/
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
    $("#typeBtn").click(function () {
        $("#modal-demo").modal("show");
        initLevel1();
    });
    function initLevel1() {
        $.ajax({
            type: 'POST',
            url: '<%=request.getContextPath()%>/common/getList',
            dataType: 'json',
            data: {
                "code": "parentTypes"
            },
            success: function (data) {
                if (data.code == 0) {
                    var data = data.data;
                    for (var i = 0; i < data.length; i++) {
                        $("#level-1").append($('<option value=' + data[i].L_C_ID + '>' + data[i].L_C_NAME + '</option>'));
                    }
                }
            },
            error: function (data) {
                layer.msg(data.msg, {icon: 2, time: 1000});
            },
        });
    }
    function loadTypes(value, index) {
        $.ajax({
            type: 'POST',
            url: '<%=request.getContextPath()%>/common/selectTypes',
            dataType: 'json',
            data: {
                "id": value
            },
            success: function (data) {
                document.getElementById("content-div").style.width = (index - 1) * 262 + "px";
                for (var i = 0; i <= (10 - index); i++) {
                    document.getElementById("div" + (index + i)).style.display = "none";
                }
                if (data.code == 0) {
                    document.getElementById("content-div").style.width = index * 262 + "px";
                    document.getElementById("div" + index).style.display = "block";
                    var data = data.data;
                    $("#level-" + index).empty();
                    for (var i = 0; i < data.length; i++) {
                        $("#level-" + index).append($('<option value=' + data[i].L_C_ID + '>' + data[i].L_C_NAME + '</option>'));
                    }
                } else if (data.code == 1) {

                }
            },
            error: function (data) {
                layer.msg(data.msg, {icon: 2, time: 1000});
            }
        });
    }
    function chooseType() {
        $("#modal-demo").modal("hide");
        var typeName = "";
        var typeId = "";
        for (var i = 1; i <= 10; i++) {
            var options = $("#level-" + i + " option:selected");
            if (options.val() != null && options.val() != "") {
                typeName += (options.text() + " > ");
                typeId = options.val();
            }
        }
        $("#typeName").val(typeName.substring(0, typeName.length - 3));
        $("#typeId").val(typeId);
    }
</script>
</body>
</html>
