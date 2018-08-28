<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String id = request.getParameter("id");
%>
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
    <title>入库商品</title>
</head>
<body>
<article class="page-container">
    <form class="form form-horizontal" id="goodsInForm">
        <input type="hidden" value="<%=id%>" name="id">
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>商品sku：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <select id="pSku" name="pSku" class="select" style="height: 32px">
                    <option value="">请选择</option>
                </select>
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>总价：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" class="input-text" id="totalPrice"
                       name="totalPrice">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>入库数量：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" class="input-text" id="pInNum"
                       name="pInNum">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>跟踪号：</label>
            <div class="formControls col-xs-6 col-sm-7">
                <input type="text" class="input-text" id="trackNo"
                       name="trackNo">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>货架编号：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" class="input-text" value="" placeholder="" id="shelfNo"
                       name="shelfNo">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>仓库：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" class="input-text" value="" placeholder="" id="wareRoom"
                       name="wareRoom">
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
    var id = '<%=id%>';
    $(function () {
        initSelect();
        $("#goodsInForm").validate({
            rules: {
                pSku: {
                    required: true
                },
                price: {
                    required: true
                },
                pInNum: {
                    required: true
                },
                trackNo: {
                    required: true
                },
                shelfNo: {
                    required: true
                },
                wareRoom: {
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
                "code": "goods"
            },
            success: function (data) {
                if (data.code == 0) {
                    var data = data.data;
                    for (var i = 0; i < data.length; i++) {
                        $("#pSku").append($('<option value=' + data[i].P_SKU + '>' + data[i].P_SKU + '</option>'));
                    }
                    initGoodsInfo();
                }
            },
            error: function (data) {
                layer.msg(data.msg, {icon: 2, time: 1000});
            }
        });
    }
    function initGoodsInfo() {
        if (id != null && id != "" && id != "null" && id != undefined) {
            $.ajax({
                type: 'POST',
                url: '<%=request.getContextPath()%>/stock/getGoodsInById',
                dataType: 'json',
                data: {
                    "id": id
                },
                success: function (data) {
                    if (data.code == 0) {
                        var data = data.data[0];
                        $("#pSku").val(data.P_SKU);
                        $("#totalPrice").val(data.TOTAL_PRICE);
                        $("#pInNum").val(data.P_IN_NUM);
                        $("#trackNo").val(data.TRACK_NUBMER);
                        $("#shelfNo").val(data.SHELF_NO);
                        $("#wareRoom").val(data.WAREROOM);
                    } else {
                        layer.msg(data.msg, {icon: 6, time: 1000});
                    }
                },
                error: function (data) {
                    layer.msg(data.msg, {icon: 5, time: 1000});
                }
            });
        }
    }
    function ajaxSubmit() {
        layer.load();
        var url = '<%=request.getContextPath()%>/stock/addGoods';
        if (id != null && id != "" && id != "null" && id != undefined) {
            url = '<%=request.getContextPath()%>/stock/updateGoodsIn';
        } else {
            url = '<%=request.getContextPath()%>/stock/addGoodsIn';
        }
        $.ajax({
            type: 'POST',
            dataType: 'json',
            data: {
                "data": JSON.stringify(getFormJson("#goodsInForm"))
            },
            url: url,
            error: function (data) {
                layer.closeAll("loading");
                layer.msg("网络错误，请求失败!", {icon: 2, time: 2000});
            },
            success: function (data) {
                layer.closeAll("loading");
                if (data.code == 0) {
                    var index = parent.layer.getFrameIndex(window.name);
                    parent.layer.close(index);
                } else {
                    layer.msg(data.msg, {icon: 2, time: 2000});
                }
            }
        });
    }
</script>
</body>
</html>
