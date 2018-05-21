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

    <form class="layui-form" action="" id="addProductForm">

        <blockquote class="layui-elem-quote">店铺信息</blockquote>

        <div class="layui-form-item">
            <label class="layui-form-label">店铺</label>

            <div class="layui-inline">
                <select required name="shopId" id="shopId" lay-filter="shop">
                    <option value="">请选择</option>
                    <c:forEach var="keys" items="${maps}">
                        <optgroup label="${keys.key}">
                            <c:set value="${keys.value}" var="keysName"/>
                            <c:forEach var="keyName" items="${keysName}">
                                <option value="${keyName.key}">${keyName.value}</option>
                            </c:forEach>
                        </optgroup>
                    </c:forEach>
                </select>
            </div>
        </div>

        <blockquote class="layui-elem-quote">产品信息</blockquote>

        <input name="id" type="hidden" value="${product.id}"/>


        <div class="layui-form-item">
            <label class="layui-form-label">SKU前缀</label>
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
            <label class="layui-form-label">产品分类</label>
            <div class="layui-inline" style="width: 50%">
                <input type="text" name="productType" value="" id="productType" lay-filter="productType"
                       placeholder="" autocomplete="off" class="layui-input ">
                <input type="hidden" name="typeId" id="typeId">
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">分类类型</label>
                <div class="layui-input-inline" style="width: 100px;">
                    <input type="text" name="externalProductId" value="" id="type"
                           placeholder="" autocomplete="off" class="layui-input ">
                </div>
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">产品ID</label>
            <div class="layui-inline">
                <input type="text" name="externalProductId" value="${product.externalProductId}" id="externalProductId"
                       lay-verify="required" placeholder="" autocomplete="off" class="layui-input ">
            </div>
            <div class="layui-inline">
                <select name="externalProductIdType" lay-verify="required" lay id="externalProductIdType">
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

            <div class="layui-inline">
                <button class="layui-btn" type="button" id="genPid">一键生成</button>
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
                <input type="text" name="websiteShippingWeight" value="${product.websiteShippingWeight}"
                       id="websiteShippingWeight"
                       lay-verify="required" placeholder="" autocomplete="off" class="layui-input ">
            </div>
        </div>


        <div class="layui-form-item">
            <label class="layui-form-label">运输方式</label>
            <div class="layui-input-block">
                <input type="radio" name="websiteType" value="FBM" title="FBM" checked>
                <input type="radio" name="websiteType" value="FBA" title="FBA">
            </div>
        </div>


        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" type="button" lay-submit lay-filter="formDemo">发布</button>
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
    var countryCode = '';
    $(function () {
        function getParam(paramName) {
            paramValue = "", isFound = !1;
            if (this.location.search.indexOf("?") == 0 && this.location.search.indexOf("=") > 1) {
                arrSource = unescape(this.location.search).substring(1, this.location.search.length).split("&"), i = 0;
                while (i < arrSource.length && !isFound) arrSource[i].indexOf("=") > 0 && arrSource[i].split("=")[0].toLowerCase() == paramName.toLowerCase() && (paramValue = arrSource[i].split("=")[1], isFound = !0), i++
            }
            return paramValue == "" && (paramValue = null), paramValue
        }

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


        $("#shopId").val('${product.shopId}');

        $("input[name='websiteType']").each(function (i, val) {
            if ($(val).val() == '${product.websiteType}') {
                $(val).click();
            }
        });


        $("#externalProductIdType option").each(function (i, val) {
            if ($(val).text() == '${product.externalProductIdType}') {
                $(val).attr("selected", "true");
            }
        });

        layui.use('form', function () {
                    var form = layui.form;
                    form.on('submit', function (data) {
                        data.field['type'] = getParam("type");
                        console.log(123);
                        data.field['type'] = '${type}';
                        $.ajax({
                            type: 'POST',
                            url: '<%=request.getContextPath()%>/product/publish',
                            dataType: 'json',
                            data: data.field,
                            success: function (data, index) {
                                if (data.success) {
                                    layer.msg(data.msg, {icon: 6, time: 2000});
                                    setTimeout(function () {
                                        layer_close();
                                    }, 1000);
                                    layer_close(index);
                                }
                                ,
                                1000
                                );
                    }
                    else
                    {
                        layer.msg(data.msg, {icon: 5, time: 1000});
                        //layer_close(index);
                    }
                },
                error
        :
        function (data) {
            layer.msg(data.msg, {icon: 5, time: 1000});
        }
    });
    })
    ;

    form.on('select(shop)', function (data) {
        $.ajax({
            type: 'POST',
            url: '<%=request.getContextPath()%>/shop/selectShops',
            dataType: 'json',
            data: {
                "id": data.value
            },
            success: function (data) {
                if (data.code == 0) {
                    countryCode = data.data[0].COUNTRY_CODE;
                } else {
                    layer.msg(data.msg, {icon: 5, time: 1000});
                    $("#productType").click(function () {
                        if (id == null) {
                            layer.msg("请选择店铺！", {icon: 5, time: 1000});
                            return;
                        }

                        layer.open({
                                    type: 2,
                                    area: [800 + 'px', ($(window).height() - 50) + 'px'],
                                    fix: false, //不固定
                                    maxmin: true,
                                    shade: 0.4,
                                    title: "产品类型选择",
                                    content: '<%=request.getContextPath()%>/assistant/index/product/product_category.jsp?shopId=' + id,
                                    end: function () {
                                    }
                                },
                                error
                        :
                        function (data) {
                            layer.msg(data.msg, {icon: 5, time: 1000});
                        }
                    });
                }
                );

        $("#genPid").click(function () {
            $.ajax({
                type: 'POST',
                url: '<%=request.getContextPath()%>/productid/use',
                dataType: 'json',
                data: {
                    "type": $("#externalProductIdType").val()
                },
                success: function (data) {
                    if (data.success) {
                        if (data.data != null) {
                            $("#externalProductIdType").val(data.data.type);
                            $("#externalProductId").val(data.data.productId);
                        } else {
                            layer.msg("该类型产品ID库中都已使用完", {icon: 5, time: 1000});
                            $("#externalProductId").val("");
                        }
                        form.render("select");
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
    })
    ;

    $("#productType").click(function () {
        $("#modal-demo").modal("show");
        initLevel1();
    });

    function initLevel1() {
        $.ajax({
            type: 'POST',
            url: '<%=request.getContextPath()%>/publish/category',
            dataType: 'json',
            data: {
                "countryCode": countryCode,
                "parentId": '-1'
            },
            success: function (data) {
                if (data.code == 0) {
                    var data = data.data;
                    for (var i = 0; i < data.length; i++) {
                        $("#level-1").append($('<option value=' + data[i].id + '>' + data[i].name + '</option>'));
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
            url: '<%=request.getContextPath()%>/publish/category',
            dataType: 'json',
            data: {
                "countryCode": countryCode,
                "parentId": value
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
                        $("#level-" + index).append($('<option value=' + data[i].id + '>' + data[i].name + '</option>'));
                    }
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
        $("#productType").val(typeName.substring(0, typeName.length - 3));
        $("#typeId").val(typeId);
    }
</script>
</body>
</html>
