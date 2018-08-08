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
    <!--[if IE 6]>
    <script type="text/javascript"
            src="<%=request.getContextPath()%>/assistant/lib/DD_belatedPNG_0.0.8a-min.js"></script>
    <script>DD_belatedPNG.fix('*');</script>
    <![endif]-->
    <title>添加商品</title>
</head>
<body>
<article class="page-container">
    <form class="form form-horizontal" id="goodsForm">
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>商品sku：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" class="input-text" id="pSku"
                       name="pSku">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>类型：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <select id="pType" name="pType" class="select" style="height: 32px">
                    <option value="1">商品</option>
                    <option value="2">赠品</option>
                    <option value="3">包材</option>
                </select>
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>公司：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <select id="company" name="company" class="select" style="height: 32px">
                    <option value="">请选择</option>
                </select>
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>所属分类：</label>
            <div class="formControls col-xs-6 col-sm-7">
                <input type="text" class="input-text" placeholder="请选择" id="categoryName" name="categoryName" readonly>
                <input type="hidden" name="pCategory" id="pCategory">
            </div>
            <div class="formControls col-xs-2 col-sm-2">
                <button type="button" class="btn btn-success radius" id="typeBtn"><i
                        class="icon-ok"></i> 选择分类
                </button>
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>中文名称：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" class="input-text" value="" placeholder="" id="nameCn"
                       name="nameCn">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>英文名称：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" class="input-text" value="" placeholder="" id="nameEn"
                       name="nameEn">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>平台sku：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" class="input-text" value="" placeholder="" id="platSku" name="platSku">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3">来源链接：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" class="input-text" value="" placeholder="" id="sourceUrl" name="sourceUrl">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3">图片路径：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" class="input-text" value="" placeholder="" id="image" name="image">
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
    <div id="modal-demo" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
         aria-hidden="true">
        <div class="modal-dialog" style="min-width: 1000px">
            <div class="modal-content radius">
                <div class="modal-header">
                    <h3 class="modal-title">选择分类</h3>

                    <a class="close" data-dismiss="modal" aria-hidden="true" href="javascript:">×</a>
                </div>
                <div class="modal-body" style="height: 330px;overflow-x: auto">
                    <div id="content-div" class="mt-10">
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
<script type="text/javascript"
        src="<%=request.getContextPath()%>/assistant/lib/datatables/1.10.0/jquery.dataTables.min.js"></script>
<script type="text/javascript">
    $(function () {
        initSelect();
        $("#goodsForm").validate({
            rules: {
                pSku: {
                    required: true
                },
                pType: {
                    required: true
                },
                pCategory: {
                    required: true
                },
                nameCn: {
                    required: true
                },
                nameEn: {
                    required: true
                },
                platSku: {
                    required: true
                }
            },
            onkeyup: false,
            focusCleanup: true,
            success: "valid",
            submitHandler: function (form) {
                ajaxSubmit();
            }
        });
    });
    function initSelect() {
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
                        $("#company").append($('<option value=' + data[i].COMPANY_ID + '>' + data[i].COMPANY_NAME + '</option>'));
                    }
                }
            },
            error: function (data) {
                layer.msg(data.msg, {icon: 2, time: 1000});
            }
        });
    }
    function ajaxSubmit() {
        layer.load();
        $.ajax({
            type: 'POST',
            dataType: 'json',
            data: {
                "data": JSON.stringify(getFormJson("#goodsForm"))
            },
            url: "<%=request.getContextPath()%>/stock/addGoods",
            error: function () {
                layer.closeAll("loading");
                layer.msg('新增失败!', {icon: 2, time: 1000});
            },
            success: function (data) {
                layer.closeAll("loading");
                if (data.code == 0) {
                    var index = parent.layer.getFrameIndex(window.name);
                    parent.layer.close(index);
                } else {
                    layer.msg('新增失败!', {icon: 2, time: 1000});
                }
            }
        });
    }
    $("#typeBtn").click(function () {
        $("#modal-demo").modal("show");
        initLevel1();
    });
    function initLevel1() {
        $("#level-1").empty();
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
        var categoryName = "";
        var categoryId = "";
        for (var i = 1; i <= 10; i++) {
            var options = $("#level-" + i + " option:selected");
            if (options.val() != null && options.val() != "") {
                categoryName += (options.text() + " > ");
                categoryId = options.val();
            }
        }
        $("#categoryName").val(categoryName.substring(0, categoryName.length - 3));
        $("#pCategory").val(categoryId);
    }
</script>
</body>
</html>
