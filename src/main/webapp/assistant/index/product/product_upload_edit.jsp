<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
        <input name="id" type="hidden" value="${product.id}" />
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
                <input type="text" name="sku" lay-verify="required" placeholder="" autocomplete="off"
                       class="layui-input" value="${product.sku}">
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
                <select name="skuMuti" lay-filter="skuMuti">
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



        <div id="priceInfo">
            <blockquote class="layui-elem-quote">价格信息</blockquote>
        <div class="layui-form-item">
                <label class="layui-form-label">价格</label>
                <div class="layui-inline">
                    <input type="text" name="price" value="${product.price}" placeholder="" autocomplete="off" class="layui-input">
                </div>

        </div>

            <div class="layui-form-item">
                <label class="layui-form-label">促销价</label>
                <div class="layui-inline">
                    <input type="text" name="salePrice" value="${productVar.salePrice}"   placeholder="" autocomplete="off" class="layui-input">
                </div>

            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">促销时间</label>
                <div class="layui-inline">
                    <input type="text" name="saleStartTime" id="saleStartTime" value="<fmt:formatDate value="${productVar.saleStartTime}" pattern="yyyy-MM-dd" />"  placeholder="" autocomplete="off" class="layui-input">
                </div>
                -
                <div class="layui-inline">
                    <input type="text" name="saleEndTime" id="saleEndTime" value="<fmt:formatDate value="${productVar.saleEndTime}" pattern="yyyy-MM-dd" />"   placeholder="" autocomplete="off" class="layui-input">
                </div>

            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">数量</label>
                <div class="layui-inline">
                    <input type="text" name="quantity" value="${productVar.quantity}"  placeholder="请输入数量" autocomplete="off"
                           class="layui-input">
                </div>
            </div>

        </div>


        <blockquote class="layui-elem-quote">描述信息</blockquote>
        <div class="layui-form-item" >
            <label class="layui-form-label">产品描述</label>
            <div class="layui-input-block">
                <textarea name="pDesc" placeholder=""   class="layui-textarea">${product.productDescriptionCn}</textarea>
            </div>

        </div>

        <div class="layui-form-item" >
            <label class="layui-form-label">简要描述</label>

            <div class="layui-inline">
                <input type="text" name="bulletPoint1"  style="width: 300px"  value="${points[0]}" lay-verify="required" placeholder="" autocomplete="off" class="layui-input ">
            </div>
            <div class="layui-inline">
                <input type="text" name="bulletPoint2" style="width: 300px" value="${points[1]}"  lay-verify="required"   placeholder="" autocomplete="off" class="layui-input">
            </div>

        </div>


        <div class="layui-form-item" >
            <label class="layui-form-label"></label>

            <div class="layui-inline">
                <input type="text" name="bulletPoint3" value="${points[2]}" style="width: 300px" lay-verify="required"  placeholder="" autocomplete="off" class="layui-input">
            </div>
            <div class="layui-inline">
                <input type="text" name="bulletPoint4" value="${points[3]}" style="width: 300px"  lay-verify="required"   placeholder="" autocomplete="off" class="layui-input">
            </div>

        </div>

        <div class="layui-form-item" >
            <label class="layui-form-label"></label>

            <div class="layui-inline">
                <input type="text" name="bulletPoint5" value="${points[4]}" style="width: 300px"  lay-verify="required"   placeholder="" autocomplete="off" class="layui-input">
            </div>
        </div>


        <blockquote class="layui-elem-quote">关键词信息</blockquote>
        <div class="layui-form-item" >
            <label class="layui-form-label">关键词</label>

            <div class="layui-inline">
                <input type="text" name="keyword1" value="${keywords[0]}"  style="width: 300px" lay-verify="required"  placeholder="" autocomplete="off" class="layui-input ">
            </div>
            <div class="layui-inline">
                <input type="text" name="keyword2" value="${keywords[1]}" style="width: 300px" lay-verify="required"   placeholder="" autocomplete="off" class="layui-input">
            </div>

        </div>


        <div class="layui-form-item" >
            <label class="layui-form-label"></label>

            <div class="layui-inline">
                <input type="text" name="keyword3" value="${keywords[2]}" style="width: 300px"  lay-verify="required" placeholder="" autocomplete="off" class="layui-input">
            </div>
            <div class="layui-inline">
                <input type="text" name="keyword4" value="${keywords[3]}" style="width: 300px" lay-verify="required"    placeholder="" autocomplete="off" class="layui-input">
            </div>

        </div>

        <div class="layui-form-item" >
            <label class="layui-form-label"></label>

            <div class="layui-inline">
                <input type="text" name="keyword5" value="${keywords[4]}" style="width: 300px"  lay-verify="required"   placeholder="" autocomplete="off" class="layui-input">
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

        var skuArr=[];
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

            laydate.render({
                elem: '.saleStart' //促销开始时间
            });

            laydate.render({
                elem: '.saleEnd' //促销结束时间
            });
        });

        function  initSkuSaleDate() {
            var laydate = layui.laydate;
            layui.use('laydate', function(){

                laydate.render({
                    elem: '.saleStart' //促销开始时间
                });

                laydate.render({
                    elem: '.saleEnd' //促销结束时间
                });
            });
        }

        var selectValue=null;
        layui.use('form', function(){
            var form = layui.form;
            form.on('radio(skuType)', function(data){//切换变体类型
                if($(this).val()=="2"){//选中多变体
                    mutiSku();

                }else{//单体
                    singleSku();
                }
            });

            form.on('checkbox(skuMutiCheck)', function(data){//切换变体类型

                refrehSKuPath();
                form.render(null,"skuMutiPath");
            });

            form.on('select(skuMuti)', function(data){//刷新
                selectValue=data.value;
                $("#skuRender").html("");
                $("#skuRender").append(genSkuTypeDom($("#skuMutiDiv option:selected").text()));

                $(".skuBtn").on("click", function () {
                    var text = $(this).parent().parent().find("input").val();
                    if (text != null && $.trim(text).length > 0) {
                        text=$.trim(text);
                        var $skuCheclk=$(this).parent().parent().prev().find(".skuCheckbox").eq(0);
                        var skarr=$skuCheclk.find("input[type='checkbox']");
                        var isExist=false;
                        for(var i=0;i<skarr.length;i++){
                            if($(skarr[i]).next().find("span").text()==text){
                                isExist=true;
                                break;
                            }
                        }
                        if(!isExist){
                            $skuCheclk.append("<input type=\"checkbox\" lay-filter='skuMutiCheck' name=\"\" title="+text+" lay-skin=\"primary\" checked>");
                            form.render('checkbox');
                            refrehSKuPath();
                            form.render(null,"skuMutiPath");
                            form.render(null,"skuTable");
                        }

                    }
                });

                form.render(null, 'skuRender');
                form.render('checkbox');

            });

            form.on('submit', function(data){
                var skuType=data.field.skuType;
                //组合关键词和简要描述
                var keys=[],points=[];
                points.push($("input[name='bulletPoint1']").val());
                points.push($("input[name='bulletPoint2']").val());
                points.push($("input[name='bulletPoint3']").val());
                points.push($("input[name='bulletPoint4']").val());
                points.push($("input[name='bulletPoint5']").val());

                keys.push($("input[name='keyword1']").val());
                keys.push($("input[name='keyword2']").val());
                keys.push($("input[name='keyword3']").val());
                keys.push($("input[name='keyword4']").val());
                keys.push($("input[name='keyword5']").val());

                data.field["bulletPointCn"]=JSON.stringify(points);
                data.field["keywordsCn"]=JSON.stringify(keys);
                console.log("type"+skuType);
                if(skuType==1){//单体

                }else{//多变中
                    if(selectValue==null||selectValue==""){
                        layer.msg("请选择变种主题！", {icon: 5, time: 1000});
                        return false;
                    }


                    var skuVar=[];
                    $("#skuTable tbody tr").each(function (i,val) {
                        var item={};
                        item["productId"]=$("input[name='id']").val();
                        item["mainPath"]=$(val).eq(0).find(".trMainPath").val();
                        item["attachPath"]=$(val).eq(0).find(".trOtherPath").val();
                        item["sku"]=$(val).eq(0).find("input[type='text']").val();
                        item["price"]=$(val).find(".price").val();
                        item["salePrice"]=$(val).find(".salePrice").val();
                        item["saleStartTime"]=$(val).find(".saleStart").val();
                        item["saleEndTime"]=$(val).find(".saleEnd").val();
                        item["quantity"]=$(val).find(".quantity").val();
                        item["variationType"]=selectValue;

                        var firstValue,secondValue;
                        $(val).find(".skuVarType").each(function (i,v) {
                            if(i==0){firstValue=$(v).html();}
                            if(i==1){secondValue=$(v).html();}
                        });
                        if(selectValue=="Color"){
                            item["colorName"]=firstValue;
                            item["colorMap"]=firstValue;
                        }else if(secondValue=="Size"){
                             item["sizeMap"]=firstValue;
                            item["sizeName"]=firstValue;
                        }else if(secondValue=="material"){
                            item["materialType"]=firstValue;
                        }else if(secondValue=="size-material"){
                            item["sizeMap"]=firstValue;
                            item["sizeName"]=firstValue;
                            item["materialType"]=secondValue;
                        }else if(secondValue=="color-material"){
                            item["colorName"]=firstValue;
                            item["colorMap"]=firstValue;
                            item["materialType"]=secondValue;
                        }else if(secondValue=="itempackagequantity"){
                            item["itemPackageQuantity"]=firstValue;
                        }else if(secondValue=="color-itempackagequantity"){
                            item["colorName"]=firstValue;
                            item["colorMap"]=firstValue;
                            item["itemPackageQuantity"]=secondValue;
                        }else if(secondValue=="itempackagequantity-size"){
                            item["itemPackageQuantity"]=firstValue;
                            item["sizeMap"]=secondValue;
                            item["sizeName"]=secondValue;
                        }else if(secondValue=="itempackagequantity-material"){
                            item["itempackagequantity"]=firstValue;
                            item["materialType"]=secondValue;
                        }

                        skuVar.push(item);

                    });




                }

                data.field["vars"]=JSON.stringify(skuVar);

                var url = '<%=request.getContextPath()%>/product/claim/save';
                $.ajax({
                    type: 'POST',
                    url: url,
                    dataType: 'json',
                    data:
                         data.field
                    ,
                    success: function (data) {
                        layer.msg(data.msg, {icon: 6, time: 1000});
                        if(data.success){
                            setTimeout(function () {
                                location.href='<%=request.getContextPath()%>/assistant/index/product/product_claim_list.jsp';
                            },1000);

                        }else{
                            layer.msg(data.msg, {icon: 5, time: 1000});
                        }

                    },
                    error: function (data) {
                        layer.msg("保存失败", {icon: 5, time: 1000});
                    }
                });
                return false;
            });
        });

        function refrehSKuPath() {
            $("#skuMutiPath").html("");
            var arr=[];
            var skuCheckRow=$(".skuCheckbox");
            if(skuCheckRow==null||skuCheckRow.length==0){
                return;
            }

            //做多2中sku组合 暂时不考虑
            var first=[];
            var second=[];
            var fistRow=[];
            var secondRow=[];
            if(skuCheckRow.length==1){//只有一个变种
                var skuTypeDesc=$(".skuCheckbox").prev().find("span").text();
                var checked=$(".skuCheckbox .layui-form-checked");
                for(var i=0;i<checked.length;i++){
                    arr.push(skuTypeDesc+":"+$(checked[i]).find("span").text());
                    fistRow.push($(checked[i]).find("span").text());
                    skuTable(skuTypeDesc,fistRow,null,null);
                }
            }else {
                var fisrtDesc=$(".skuCheckbox").eq(0).prev().find("span").text();
                var secondDesc=$(".skuCheckbox").eq(1).prev().find("span").text();
                first=$(".skuCheckbox").eq(0).find(".layui-form-checked");
                second=$(".skuCheckbox").eq(1).find(".layui-form-checked");
                if(first.length==0&&second.length==0)return;
                if(first.length>0&&second.length>0){
                    for(var i=0;i<first.length;i++){
                        for(var k=0;k<second.length;k++){
                            arr.push(fisrtDesc+":"+$(first[i]).find("span").text()+" "+secondDesc+":"+$(second[k]).find("span").text());
                            fistRow.push($(first[i]).find("span").text());
                            secondRow.push($(second[k]).find("span").text());
                        }
                    }

                    skuTable(fisrtDesc,fistRow,secondDesc,secondRow);
                }else{
                    var desc=(first.length==0)?$(".skuCheckbox").eq(1).prev().find("span").text():$(".skuCheckbox").eq(0).prev().find("span").text();
                    var checked=(first.length==0)?second:first;
                    var fisrtDesc=$(".skuCheckbox").eq(0).prev().find("span").text();
                    for(var i=0;i<checked.length;i++){
                        arr.push(desc+":"+$(checked[i]).find("span").text());
                        fistRow.push($(first[i]).find("span").text());

                    }
                    skuTable(desc,fistRow,null,null);
                }
            }

            $("#skuMutiPath").append(getSKuPathDom(arr));
            initSKuPath(null,null);

        }

        function skuTable(fisrtDesc,fisrtArr,secondDesc,secondArr) {
           $("skuTable tbody").html("");
           if(fisrtDesc==null){
               return;
           }

           var dom="";
           if(fisrtDesc!=null&&secondDesc!=null){
               $("#skuTable colgroup").html(getCgroup(false));
               $("#skuTable thead").html(getThead("<th>"+fisrtDesc+"</th><th>"+secondDesc+"</th>"));
               for(var i=0;i<fisrtArr.length;i++){
                    dom+=getTR(false,fisrtArr[i],secondArr[i]);
               }
           }else{
               $("#skuTable colgroup").html(getCgroup(true));
               $("#skuTable thead").html(getThead("<th>"+fisrtDesc+"</th>"));
               for(var i=0;i<fisrtArr.length;i++){
                   dom+=getTR(true,fisrtArr[i],null);
               }
           }

            $("#skuTable tbody").html(dom);
           initSkuSaleDate();

        }

        function getTR(isSingle,first,second) {
            var sku=$("input[name='sku']").val()+"-"+(isSingle?first:(first+"-"+second));
            return "<tr val="+(isSingle?first:(first+":"+second))+">"+
                "<td><input type='hidden' class='trMainPath' /> <input type='hidden' class='trOtherPath' /> <input type='text' value='"+sku+"'  lay-verify='required'  autocomplete='off' class='layui-input'></td>"+
                (isSingle?("<td class='skuVarType'>"+first+"</td>"):("<td class='skuVarType'>"+first+"</td><td>"+second+"</td>"))
                 +
                "<td><input type='text' lay-verify='required'  autocomplete='off' class='layui-input price'></td>"+
                "<td><input type='text'  autocomplete='off' class='layui-input salePrice'></td>"+
                "<td> <div class='layui-inline'>"+
                "<input type='text'  autocomplete='off' class='layui-input saleStart'>"+
                "</div>"+
                "-"+
                "<div class='layui-inline'>"+
                "<input type='text' autocomplete='off' class='layui-input saleEnd'>"+
                "</div>"+
                "</td>"+
                "<td><input type='text' lay-verify='required'  autocomplete='off' class='layui-input quantity'></td>"+
                "</tr>";
        }

        //监听parentSKU值变化
        $("input[name='sku']").bind("input propertychange change",function(event){
            $("#skuTable tbody tr").each(function (i,val) {
                var text=$(val).find("td").eq(0).find("input[type='text']").val();
                $(val).find("td").eq(0).find("input[type='text']").val($("input[name='sku']").val()+text.substring(text.indexOf("-")))
            });
        });

        function getCgroup(isSingle) {
            return "<col width=\"200\">"+
                (isSingle?("<col width=\"100\">"):("<col width=\"100\"><col width=\"100\">"))+
                " <col width=\"50\">"+
                "  <col width=\"50\">"+
                "  <col width=\"300\">"+
                "  <col width=\"100\">";
        }

        function getThead(desc) {
            return "<tr> <th>sku</th>"+desc+"<th>价格</th> <th>促销价</th> <th>促销时间</th> <th>数量</th></tr> ";
        }

        function genSkuTypeDom(type) {
            var dom = "";
            if (type == "") {
                return;
            }

            if (type.indexOf("-") > 0) {
                var arr = type.split("-");
                for (var i = 0; i < arr.length; i++) {
                    dom += (getSkuItemDom(arr[i]));
                }

            } else {
                dom = (getSkuItemDom(type));
            }

            dom+="<div class=\"layui-form-item\" id='skuMutiPath' lay-filter=\"skuMutiPath\" >\n" +
                "            <label class=\"layui-form-label\">变种图片</label>\n" +
                "</div>";

            dom+="<div class=\"layui-form-item\">"+
             "<label class=\"layui-form-label\">变种参数</label>"+
                "<div class=\"layui-input-block\">"+
                "<table class=\"layui-table\" id=\"skuTable\" lay-filter='skuTable'>"+
               " <colgroup>"+

             "   </colgroup><thead></thead> <tbody>"+

                "</tbody>"+
                "</table>"+
                "</div>"+
                "</div>";

            return dom;
        }

        function getSKuPathDom(arr) {
            if(arr==null||arr.length==0){
               return "";
            }

           var dom="";
            for(var i=0;i<arr.length;i++){
                var id="skuMainPath"+i;
                var sid="skuOtherPath"+i;
                dom+="<div class=\"layui-input-block\" val="+(arr[i].substring(arr[i].indexOf(":")+1))+">" +
                    "    <div class='layui-inline layui-bg-gray' style='margin-top: 10px;'>变种属性    "+arr[i]+"</div>" +
                    "            </div>"+
                    "            <div class=\"layui-input-block\">\n" +
                    "                <button id="+id+" type=\"button\" class=\"layui-btn skuMainPath\" >\n" +
                    "            <i class=\"layui-icon\">&#xe67c;</i>上传主图\n" +
                    "        </button>" +
                    "            </div>\n" +


                    "            <div class=\"layui-input-block\" style='margin-top: 5px'>" +
                                    "<img src='http://bpic.588ku.com/element_origin_min_pic/01/47/02/12574338a640011.jpg!r650' width='100' height='90' />"+
                    "                     </div>" +


                    "            <div class=\"layui-input-block\" style='margin-top: 10px;'>\n" +
                    "                <button type=\"button\" id="+sid+" class=\"layui-btn skuOtherPath\"  >\n" +
                    "            <i class=\"layui-icon\">&#xe67c;</i>上传附图\n" +
                    "        </button>" +
                    "            </div>"+
                    "            <div class=\"layui-input-block\" style='margin-top: 5px'>" +
                "<img class=\"pathDemo\" src='http://bpic.588ku.com/element_origin_min_pic/01/47/02/12574338a640011.jpg!r650' width='100' height='90' />"+
                "                     </div>";


            }




            return dom;
        }

        function getSkuItemDom(desc) {
            return "<div class='layui-form-item' >" +
                "<div class='layui-input-block'>" +
                "    <div class='layui-inline' style='width: 50px;'><span>" + desc +
                "  </span>  :</div>" +
                "    <div class='layui-inline skuCheckbox'>" +
                "    </div>" +
                "</div>" +
                "      <div class='layui-input-block'>" +
                "    <div class=\"layui-input-inline\" style=\"width: 150px;\">" +
                "      <input type=\"text\" name=\"\" autocomplete=\"off\" class=\"layui-input\">" +
                "    </div>" +
                "   <div class=\"layui-input-inline\" style=\"width: 100px;\">" +
                "      <button type='button' class=\"layui-btn skuBtn\">添加</button>\n" +
                "    </div>" +
                "      </div>" +
                "</div> " +
                "</div>";
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

        function initSKuPath(id,sid) {

            layui.use('upload', function(){

                var upload = layui.upload;
                //执行实例
                var uploadInst = upload.render({
                    accept:"images",
                    acceptMime:"image/*",
                    elem: ".skuMainPath" //绑定元素
                    ,url: '<%=request.getContextPath()%>/upload/image' //上传接口
                    ,done: function(res){
                        var $this=$($(this)[0].elem[0]);
                        var key=$this.parent().prev().attr("val");
                        $this.parent().next().html("<img width='100px' height='90px' src=<%=request.getContextPath()%>/upload/"+res.data+" />");
                        $("#skuTable tbody tr").each(function (i,val) {

                            if($(val).attr("val")==key){
                                 $(val).find(".trMainPath").val(res.data);
                            }

                        });
                        //上传完毕回调
                    }
                    ,error: function(){
                        //请求异常回调
                    }
                });


                var uploadMuti = upload.render({
                    accept:"images",
                    acceptMime:"image/*",
                    elem: ".skuOtherPath", //绑定元素
                    multiple:true
                    ,number:9
                    ,url: '<%=request.getContextPath()%>/upload/image' //上传接口
                    ,done: function(res){
                        var $this=$($(this)[0].elem[0]);
                        var key=$this.parent().prev().attr("val");
                        $this.parent().next().find(".pathDemo").remove();
                        $this.parent().next().append("<img width='100px' height='90px' style='padding-right:5px;' src=<%=request.getContextPath()%>/upload/"+res.data+" />");
                        $("#skuTable tbody tr").each(function (i,val) {
                            if($(val).attr("val")==key){
                                var exVal=$(val).find(".trOtherPath").val();
                                $(val).find(".trOtherPath").val((exVal==null||exVal=="")?res.data:(exVal+=","+res.data));
                            }

                        });
                        //上传完毕回调
                        if($this.parent().next().find("img").length>9){//最多上传9张
                            $this.parent().next().find("img").each(function (i,val) {
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

        }



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
