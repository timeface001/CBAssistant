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
                <input type="text" name="itemSku" id="skuPre" lay-verify="required" placeholder="" autocomplete="off"
                       class="layui-input" value="${product.itemSku}">
            </div>
        </div>


        <c:if test="${type!='2'}" >
            <div class="layui-form-item">
                <label class="layui-form-label">产品标题</label>
                <div class="layui-input-block">
                    <input type="text" name="itemName" value="${product.itemName}" id="itemName"
                           lay-verify="required" placeholder="" autocomplete="off" class="layui-input">
                </div>
            </div>
        </c:if>

        <div class="layui-form-item">
            <label class="layui-form-label">分类ID</label>
            <div class="layui-input-block">
                <input type="text" name="productTypeId" value="${product.productTypeId}" id="productTypeId"
                       lay-verify="required" placeholder="" autocomplete="off" class="layui-input">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">分类模板</label>
            <div class="layui-input-block">
                <select required name="productTypeName" id="productTypeName" lay-filter="productTypeName">
                    <option>选择模板</option>
                    <c:forEach var="va" items="${values}">
                        <option value="${va.displayName}" val="${va.necValues}">${va.typeValue}</option>
                    </c:forEach>
                </select>
            </div>
        </div>

        <div class="layui-form-item" id="valueProp">
            <label class="layui-form-label">必要属性</label>
        </div>

        <div class="layui-form-item" id="itemTypeShow" style="display: none;">
            <label class="layui-form-label">ITEM_TYPE</label>
            <div class="layui-input-block">
                <input type="text" name="itemType" value="${product.itemType}" id="itemType"
                       placeholder="" autocomplete="off" class="layui-input ">
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
    var countryCode = '${product.languageId}';
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
        $("#productTypeName").val('${product.productTypeName}');
        var valuePropMap = eval("(" + '${valuePropMap}' + ")");
        var valueTypeMap=eval("(" + '${valueTypeMap}' + ")");

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

        var type = '${type}';
        if (type == "1") {
            $("#shopId").attr("disabled", "disabled");
            $("#skuPre").attr("readonly", "readonly");
        }

        layui.use('form', function () {
            var form = layui.form;
            form.on('submit', function (data) {
                var shopId=$("#shopId").val();
                if(shopId==null||shopId==''){
                    layer.msg("请选择店铺", {icon: 5, time: 1000});
                    return false;
                }

                var valueMap={};
                $("#valueProp input").each(function (i,val) {

                     valueMap[$(val).attr('name')]=$(val).val();
                });

                var str=JSON.stringify(valueMap);
                if(str=="{}"){
                    str="";
                }

                data.field['productType']=str;
                data.field['type'] = '${type}';
                //data.field['ids'] = '${id}';
                //data.field['type'] = '${type}';
                layer.load();
                if(type=="2"){//批量预发布
                    $.ajax({
                        type: 'POST',
                        url: '<%=request.getContextPath()%>/product/publish/batch/pre',
                        dataType: 'json',
                        data: data.field,
                        success: function (data, index) {
                            layer.closeAll('loading');
                            if (data.success) {
                                layer.msg(data.msg, {icon: 6, time: 2000});
                                /* setTimeout(function () {
                                     layer_close(index);
                                 }, 1000);*/
                            } else {
                                layer.msg(data.msg, {icon: 5, time: 1000});
                            }
                        },
                        error: function (data) {
                            layer.closeAll('loading');
                            layer.msg(data.msg, {icon: 5, time: 1000});
                        }
                    });
                }else{

                    $.ajax({
                        type: 'POST',
                        url: '<%=request.getContextPath()%>/product/publish',
                        dataType: 'json',
                        data: data.field,
                        success: function (data, index) {
                            layer.closeAll('loading');
                            if (data.success) {
                                layer.msg(data.msg, {icon: 6, time: 2000});
                                if(type=="1"){
                                     setTimeout(function () {
                                     layer_close(index);
                                 }, 1000);
                                }
                            } else {
                                layer.msg(data.msg, {icon: 5, time: 1000});
                            }
                        },
                        error: function (data) {
                            layer.closeAll('loading');
                            layer.msg(data.msg, {icon: 5, time: 1000});
                        }
                    });
                }
            });

            valueShow('${product.productTypeName}',true);
            form.on('select(productTypeName)', function (data) {
                valueShow(data.value,false);
            });

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
                            $.ajax({
                                url: "<%=request.getContextPath()%>/product/claim/single",
                                dataType: "json",
                                data: {id: '${product.id}'},
                                success: function (data) {
                                    var translte = "";
                                    if (countryCode == "ES" || countryCode == 'MX') {
                                        translte = data.itemEs;
                                    }
                                    if (countryCode == "US" || countryCode == "CA" || countryCode == "AU" || countryCode == "GB") {
                                        translte = data.itemUk;
                                    }
                                    if (countryCode == "JP") {
                                        translte = data.itemJp;
                                    }
                                    if (countryCode == "FR") {
                                        translte = data.itemFr;
                                    }
                                    if (countryCode == "IT") {
                                        translte = data.itemIt;
                                    }
                                    if (countryCode == "DE") {
                                        translte = data.itemDe;
                                    }
                                    if (countryCode == "CN") {
                                        translte = data.itemCn;
                                    }

                                    if (countryCode == "US") {
                                        $("#itemTypeShow").css("display", "");
                                    } else {
                                        $("#itemTypeShow").css("display", "none");
                                    }

                                    $("#itemName").val(translte);
                                }

                            });


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

        function valueShow(dataValue,isDis) {
            $("#valueProp").html("<label class=\"layui-form-label\">必要属性</label>");
            var isHas=false;
            for (var k in valuePropMap) {
                if (k == dataValue) {
                    isHas=true;
                    for (var i = 0; i < valuePropMap[k].length; i++) {
                        var vvv='';
                        if(isDis){
                            vvv=valueTypeMap[valuePropMap[k][i]];
                        }

                        $("#valueProp").append("<div class=\"layui-inline\">\n" +
                            "<label style='float: left;\n" +
                            "    display: block;\n" +
                            "    padding: 9px 15px;\n" +
                            "    font-weight: 400;\n" +
                            "    text-align: right;'>" + valuePropMap[k][i] + "</label>\n" +
                            " <div class=\"layui-input-inline\" style=\"width: 100px;\">\n" +
                            "<input type=\"text\" name="+valuePropMap[k][i]+" value="+vvv+" id=\"\"\n" +
                            "  placeholder=\"\" lay-verify=\"required\" autocomplete=\"off\"  lay-v class=\"layui-input \">\n" +
                            "                </div>\n" +
                            "            </div>");
                    }
                    break;
                }
            }

            if(isHas){
                $("#valueProp").css("display","");
            }else{
                $("#valueProp").css("display","none");
            }
        }
    });


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
    $("#browseNodes").click(function () {
        $("#modal-demo").modal("show");
        if (countryCode == 'US') {
            document.getElementById("itemLabel").style.display = "";
            document.getElementById("itemDiv").style.display = "";
        } else {
            document.getElementById("itemLabel").style.display = "none";
            document.getElementById("itemDiv").style.display = "none";
        }
        initLevel1();
    });

    function initLevel1() {
        $("#level-1").empty();
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
        $("#browseNodes").val(typeName.substring(0, typeName.length - 3));
        $("#typeId").val(typeId);
        $("#productType").val($("#categoryType").val());
        $("#productTypeName").val($("#categoryModel").val());
        $("#itemType").val($("#cateType").val());

        var selfDefine = $("#categoryId").val();
        if (selfDefine != null && $.trim(selfDefine).length > 0) {
            $("#typeId").val(selfDefine);
            $("#browseNodes").val($("#categoryType").val());
        }
    }

</script>
</body>
</html>
