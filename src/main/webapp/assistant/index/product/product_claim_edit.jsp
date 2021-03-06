<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%
    List pointsCn = (List) request.getAttribute("pointsCn");
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

        .sorthelper {
            background-color: #f00;
            float: left;
        }

        .sortableactive {
        }

        .sortablehover {
        }

        .sign_desc {

            margin-left: 15px;
            margin-top: 3px;
            color: orange;
            margin-bottom: 3px;
        }
    </style>
</head>
<body>
<article class="page-container">
    <blockquote class="layui-elem-quote">产品信息</blockquote>
    <form class="layui-form" action="">
        <input name="id" type="hidden" value="${product.id}"/>
        <div class="layui-form-item">
            <label class="layui-form-label">售卖形式</label>
            <div class="layui-input-block">
                <input type="radio" name="skuType" lay-filter="skuType" value="1" title="单体">
                <input type="radio" name="skuType" lay-filter="skuType" value="2" title="多变种">
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
            <div class="layui-inline">
                <div class="layui-tab layui-tab-card" style="width: 670px;">
                    <ul class="sign_desc">英文首字母大写，最好不超过80，最大不超过200</ul>
                    <ul class="layui-tab-title">
                        <li class="layui-this">中文</li>
                        <li>英语</li>
                        <li>日语</li>
                        <li>德语</li>
                        <li>法语</li>
                        <li>西班牙语</li>
                        <li>意大利语</li>
                    </ul>
                    <div class="layui-tab-content" style="height: 50px;">
                        <div class="layui-tab-item layui-show">
                            <input type="text" name="itemCn"
                                   value="${product.itemCn}"
                                   lay-verify="required" placeholder=""
                                   autocomplete="off" class="layui-input"
                                   maxlength="600"></div>
                        <div class="layui-tab-item"><input type="text" id="itemUk" name="itemUk"
                                                           value="<c:out value='${product.itemUk}'/>"
                                                           lay-verify="required" placeholder="" autocomplete="off"
                                                           class="layui-input" maxlength="1800"></div>
                        <div class="layui-tab-item"><input type="text" name="itemJp" value="${product.itemJp}"
                                                           lay-verify="required" placeholder="" autocomplete="off"
                                                           class="layui-input" maxlength="600"></div>
                        <div class="layui-tab-item"><input type="text" name="itemDe" value="${product.itemDe}"
                                                           lay-verify="required" placeholder="" autocomplete="off"
                                                           class="layui-input" maxlength="1800"></div>
                        <div class="layui-tab-item"><input type="text" name="itemFr" value="${product.itemFr}"
                                                           lay-verify="required" placeholder="" autocomplete="off"
                                                           class="layui-input" maxlength="1800"></div>
                        <div class="layui-tab-item"><input type="text" name="itemEs" value="${product.itemEs}"
                                                           lay-verify="required" placeholder="" autocomplete="off"
                                                           class="layui-input" maxlength="1800"></div>
                        <div class="layui-tab-item"><input type="text" name="itemIt" value="${product.itemIt}"
                                                           lay-verify="required" placeholder="" autocomplete="off"
                                                           class="layui-input" maxlength="1800"></div>
                    </div>
                </div>
            </div>
            <div class="layui-inline">
                <button class="layui-btn translate1" type="button" style="margin-bottom: 75px;">一键翻译</button>
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">商品重量</label>
            <div class="layui-input-block">
                <input type="text" name="productWeight" lay-verify="required" placeholder="" autocomplete="off"
                       class="layui-input" value="${product.productWeight}">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">运输重量</label>
            <div class="layui-input-block">
                <input type="text" name="deliveryWeight" lay-verify="required" placeholder="" autocomplete="off"
                       class="layui-input" value="${product.deliveryWeight}">
            </div>
        </div>
        </div>

        <div id="skuMutiDiv">
            <blockquote class="layui-elem-quote">变种信息</blockquote>
            <div class="layui-form-item">
                <label class="layui-form-label">变种主题</label>
                <div class="layui-input-block">
                    <%--<select name="skuMuti" lay-filter="skuMuti" id="selectMuti">
                        <option value=""></option>
                        <c:forEach items="${typeList}" var="type">
                            <option value="${type.variationType}">${type.variationName}</option>
                        </c:forEach>
                    </select>--%>
                    <select name="skuMuti" lay-filter="skuMuti" id="selectMuti">
                        <option value=""></option>

                        <option value="Color">颜色</option>

                        <option value="Size">尺寸</option>

                        <option value="colorsize">颜色-尺寸</option>

                        <option value="material">材质</option>

                        <option value="size-material">尺寸-材质</option>

                        <option value="color-material">颜色-材质</option>

                        <option value="itempackagequantity">包装数量</option>

                        <option value="color-itempackagequantity">颜色-包装数量</option>

                        <option value="itempackagequantity-size">包装数量-尺寸</option>

                        <option value="itempackagequantity-material">包装数量-材质</option>

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
                    <input type="text" name="price" value="${product.price}" placeholder="" autocomplete="off"
                           class="layui-input">
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">促销价</label>
                <div class="layui-inline">
                    <input type="text" name="salePrice" value="${productVar.salePrice}" placeholder=""
                           autocomplete="off" class="layui-input">
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">促销时间</label>
                <div class="layui-inline">
                    <input type="text" name="saleStartTime" id="saleStartTime"
                           value="<fmt:formatDate value="${productVar.saleStartTime}" pattern="yyyy-MM-dd"/>"
                           placeholder="" autocomplete="off" class="layui-input">
                </div>
                -
                <div class="layui-inline">
                    <input type="text" name="saleEndTime" id="saleEndTime"
                           value="<fmt:formatDate value="${productVar.saleEndTime}" pattern="yyyy-MM-dd"/>"
                           placeholder="" autocomplete="off" class="layui-input">
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">数量</label>
                <div class="layui-inline">
                    <input type="text" name="quantity" value="${productVar.quantity==null?30:productVar.quantity}"
                           placeholder="请输入数量"
                           autocomplete="off"
                           class="layui-input">
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">图片</label>
                <div class="layui-inline">
                    <input id="imagePathValue" name="imagePath" type="hidden" value="${product.imagePath}"/>
                    <button type="button" class="layui-btn" id="imagePathBtn">
                        <i class="layui-icon">&#xe67c;</i>上传图片
                    </button>
                    <span style="font-size: 10px;">(最多9张，每张尺寸要求1001*1001以上)</span>
                </div>
                <div id="imagePathSrc" class="layui-input-block" style='margin-top: 5px;height: 110px;'>
                    <%--<img class="pathDemo"
                         src='http://bpic.588ku.com/element_origin_min_pic/01/47/02/12574338a640011.jpg!r650'
                         width='100' height='90'/>--%>
                </div>
            </div>
        </div>

        <blockquote class="layui-elem-quote">描述信息</blockquote>
        <div class="layui-form-item">
            <label class="layui-form-label">产品描述</label>
            <div class="layui-inline">
                <div class="layui-tab layui-tab-card" style="width: 670px;">
                    <ul class="sign_desc">不要输入html标签，字符长度不超过2000</ul>
                    <ul class="layui-tab-title">
                        <li class="layui-this">中文</li>
                        <li>英语</li>
                        <li>日语</li>
                        <li>德语</li>
                        <li>法语</li>
                        <li>西班牙语</li>
                        <li>意大利语</li>
                    </ul>
                    <div class="layui-tab-content" style="height: 320px;">
                        <div class="layui-tab-item layui-show">
    <textarea id="productDescriptionCn"
              name="productDescriptionCn"
              style="width: 100%;height: 150px"
              maxlength="600">${product.productDescriptionCn}</textarea>
                        </div>
                        <div class="layui-tab-item">
    <textarea id="productDescriptionUk" name="productDescriptionUk"
              style="width: 100%;height: 150px"
              maxlength="1800">${product.productDescriptionUk}</textarea>
                        </div>
                        <div class="layui-tab-item ">
    <textarea id="productDescriptionJp" name="productDescriptionJp"
              style="width: 100%;height: 140px"
              maxlength="600">${product.productDescriptionJp}</textarea>
                        </div>
                        <div class="layui-tab-item ">
    <textarea id="productDescriptionDe" name="productDescriptionDe"
              style="width: 100%;height: 150px"
              maxlength="1800">${product.productDescriptionDe}</textarea>
                        </div>
                        <div class="layui-tab-item ">
    <textarea id="productDescriptionFr" name="productDescriptionFr"
              style="width: 100%;height: 150px"
              maxlength="1800">${product.productDescriptionFr}</textarea>
                        </div>
                        <div class="layui-tab-item ">
    <textarea id="productDescriptionEs" name="productDescriptionEs"
              style="width: 100%; height: 150px"
              maxlength="1800">${product.productDescriptionEs}</textarea>
                        </div>
                        <div class="layui-tab-item ">
    <textarea id="productDescriptionIt" name="productDescriptionIt"
              style="width: 100%;height: 150px"
              maxlength="1800">${product.productDescriptionIt}</textarea>
                        </div>
                    </div>
                </div>
            </div>
            <div class="layui-inline">
                <button class="layui-btn translate2" type="button" style="margin-bottom: 125px;">一键翻译</button>
            </div>
        </div>

        <div class="layui-form-item" style="width: 1000px;">
            <label class="layui-form-label">简要描述</label>
            <div class="layui-inline">
                <div class="layui-tab layui-tab-card" style="width: 670px;" lay-filter="demo">
                    <ul class="sign_desc">回车分隔，不超过5行，字符总长度不超过500</ul>
                    <ul class="layui-tab-title">
                        <li class="layui-this">中文</li>
                        <li>英语</li>
                        <li>日语</li>
                        <li>德语</li>
                        <li>法语</li>
                        <li>西班牙语</li>
                        <li>意大利语</li>
                    </ul>
                    <div class="layui-tab-content" style="height: 210px;">
                        <div class="layui-tab-item layui-show" id="pointsCn">
    <textarea id="pointCn" style="width: 100%;height: 200px;"
              placeholder="" autocomplete="off" class="layui-textarea"
              maxlength="800">${pointsCn[0]}\n${pointsCn[1]}\n${pointsCn[2]}\n${pointsCn[3]}\n${pointsCn[4]}</textarea>
                            <%-- <div class="layui-inline">
                                 <input type="text" name="bulletPoint2" style="width: 600px" value="${pointsCn[1]}"
                                        placeholder="" autocomplete="off" class="layui-input"
                                        maxlength="150">
                             </div>
                             <div class="layui-inline">
                                 <input type="text" name="bulletPoint3" value="${pointsCn[2]}" style="width: 600px"
                                        placeholder="" autocomplete="off" class="layui-input"
                                        maxlength="150">
                             </div>
                             <div class="layui-inline">
                                 <input type="text" name="bulletPoint4" value="${pointsCn[3]}" style="width: 600px"
                                        placeholder="" autocomplete="off" class="layui-input"
                                        maxlength="150">
                             </div>

                             <div class="layui-inline">
                                 <input type="text" name="bulletPoint5" value="${pointsCn[4]}" style="width: 600px"
                                        placeholder="" autocomplete="off" class="layui-input"
                                        maxlength="150">
                             </div>--%>
                        </div>
                        <div class="layui-tab-item" id="pointsUk">
    <textarea id="pointUk" style="width: 100%;height: 200px;"
              placeholder="" autocomplete="off" class="layui-textarea"
              maxlength="2500">${pointsUk[0]}\n${pointsUk[1]}\n${pointsUk[2]}\n${pointsUk[3]}\n${pointsUk[4]}</textarea>
                            <%--<div class="layui-inline">
                                <input type="text" name="bulletPoint2" style="width: 600px" value="${pointsUk[1]}"
                                       placeholder="" autocomplete="off" class="layui-input"
                                       maxlength="450">
                            </div>
                            <div class="layui-inline">
                                <input type="text" name="bulletPoint3" value="${pointsUk[2]}" style="width: 600px"
                                       placeholder="" autocomplete="off" class="layui-input"
                                       maxlength="450">
                            </div>
                            <div class="layui-inline">
                                <input type="text" name="bulletPoint4" value="${pointsUk[3]}" style="width: 600px"
                                       placeholder="" autocomplete="off" class="layui-input"
                                       maxlength="450">
                            </div>

                            <div class="layui-inline">
                                <input type="text" name="bulletPoint5" value="${pointsUk[4]}" style="width: 600px"
                                       placeholder="" autocomplete="off" class="layui-input"
                                       maxlength="450">
                            </div>--%>
                        </div>
                        <div class="layui-tab-item " id="pointsJp">
    <textarea id="pointJp" style="width: 100%;height: 200px;"
              placeholder="" autocomplete="off" class="layui-textarea"
              maxlength="800">${pointsJp[0]}\n${pointsJp[1]}\n${pointsJp[2]}\n${pointsJp[3]}\n${pointsJp[4]}</textarea>
                            <%--<div class="layui-inline">
                                <input type="text" name="bulletPoint2" style="width: 600px" value="${pointsJp[1]}"
                                       placeholder="" autocomplete="off" class="layui-input"
                                       maxlength="150">
                            </div>

                            <div class="layui-inline">
                                <input type="text" name="bulletPoint3" value="${pointsJp[2]}" style="width: 600px"
                                       placeholder="" autocomplete="off" class="layui-input"
                                       maxlength="150">
                            </div>
                            <div class="layui-inline">
                                <input type="text" name="bulletPoint4" value="${pointsJp[3]}" style="width: 600px"
                                       placeholder="" autocomplete="off" class="layui-input"
                                       maxlength="150">
                            </div>

                            <div class="layui-inline">
                                <input type="text" name="bulletPoint5" value="${pointsJp[4]}" style="width: 600px"
                                       placeholder="" autocomplete="off" class="layui-input"
                                       maxlength="150">
                            </div>--%>
                        </div>
                        <div class="layui-tab-item " id="pointsDe">
    <textarea id="pointDe" style="width: 100%;height: 200px;"
              placeholder="" autocomplete="off" class="layui-textarea"
              maxlength="2500">${pointsDe[0]}\n${pointsDe[1]}\n${pointsDe[2]}\n${pointsDe[3]}\n${pointsDe[4]}</textarea>
                            <%--<div class="layui-inline">
                                <input type="text" name="bulletPoint2" style="width: 600px" value="${pointsDe[1]}"
                                       placeholder="" autocomplete="off" class="layui-input"
                                       maxlength="450">
                            </div>
                            <div class="layui-inline">
                                <input type="text" name="bulletPoint3" value="${pointsDe[2]}" style="width: 600px"
                                       placeholder="" autocomplete="off" class="layui-input"
                                       maxlength="450">
                            </div>
                            <div class="layui-inline">
                                <input type="text" name="bulletPoint4" value="${pointsDe[3]}" style="width: 600px"
                                       placeholder="" autocomplete="off" class="layui-input"
                                       maxlength="450">
                            </div>
                            <div class="layui-inline">
                                <input type="text" name="bulletPoint5" value="${pointsDe[4]}" style="width: 600px"
                                       placeholder="" autocomplete="off" class="layui-input"
                                       maxlength="450">
                            </div>--%>
                        </div>
                        <div class="layui-tab-item" id="pointsFr">
    <textarea id="pointFr" style="width: 100%;height: 200px;"
              placeholder="" autocomplete="off" class="layui-textarea"
              maxlength="2500">${pointsFr[0]}\n${pointsFr[1]}\n${pointsFr[2]}\n${pointsFr[3]}\n${pointsFr[4]}</textarea>
                            <%--<div class="layui-inline">
                                <input type="text" name="bulletPoint2" style="width: 600px" value="${pointsFr[1]}"
                                       placeholder="" autocomplete="off" class="layui-input"
                                       maxlength="450">
                            </div>

                            <div class="layui-inline">
                                <input type="text" name="bulletPoint3" value="${pointsFr[2]}" style="width: 600px"
                                       placeholder="" autocomplete="off" class="layui-input"
                                       maxlength="450">
                            </div>
                            <div class="layui-inline">
                                <input type="text" name="bulletPoint4" value="${pointsFr[3]}" style="width: 600px"
                                       placeholder="" autocomplete="off" class="layui-input"
                                       maxlength="450">
                            </div>

                            <div class="layui-inline">
                                <input type="text" name="bulletPoint5" value="${pointsFr[4]}" style="width: 600px"
                                       placeholder="" autocomplete="off" class="layui-input"
                                       maxlength="450">
                            </div>--%>
                        </div>
                        <div class="layui-tab-item" id="pointsEs">
    <textarea id="pointEs" style="width: 100%;height: 200px;"
              placeholder="" autocomplete="off" class="layui-textarea"
              maxlength="2500">${pointsEs[0]}\n${pointsEs[1]}\n${pointsEs[2]}\n${pointsEs[3]}\n${pointsEs[4]}</textarea>
                            <%--<div class="layui-inline">
                                <input type="text" name="bulletPoint2" style="width: 600px" value="${pointsEs[1]}"
                                       placeholder="" autocomplete="off" class="layui-input"
                                       maxlength="450">
                            </div>

                            <div class="layui-inline">
                                <input type="text" name="bulletPoint3" value="${pointsEs[2]}" style="width: 600px"
                                       placeholder="" autocomplete="off" class="layui-input"
                                       maxlength="450">
                            </div>
                            <div class="layui-inline">
                                <input type="text" name="bulletPoint4" value="${pointsEs[3]}" style="width: 600px"
                                       placeholder="" autocomplete="off" class="layui-input"
                                       maxlength="450">
                            </div>

                            <div class="layui-inline">
                                <input type="text" name="bulletPoint5" value="${pointsEs[4]}" style="width: 600px"
                                       placeholder="" autocomplete="off" class="layui-input"
                                       maxlength="450">
                            </div>--%>
                        </div>
                        <div class="layui-tab-item" id="pointsIt">
    <textarea id="pointIt" style="width: 100%;height: 200px;"
              placeholder="" autocomplete="off" class="layui-textarea"
              maxlength="2500">${pointsIt[0]}\n${pointsIt[1]}\n${pointsIt[2]}\n${pointsIt[3]}\n${pointsIt[4]}</textarea>
                            <%--<div class="layui-inline">
                                <input type="text" name="pointsIt1" style="width: 600px" value="${pointsIt[1]}"
                                       placeholder="" autocomplete="off" class="layui-input"
                                       maxlength="450">
                            </div>

                            <div class="layui-inline">
                                <input type="text" name="pointsIt2" value="${pointsIt[2]}" style="width: 600px"
                                       placeholder="" autocomplete="off" class="layui-input"
                                       maxlength="450">
                            </div>
                            <div class="layui-inline">
                                <input type="text" name="pointsIt3" value="${pointsIt[3]}" style="width: 600px"
                                       placeholder="" autocomplete="off" class="layui-input"
                                       maxlength="450">
                            </div>

                            <div class="layui-inline">
                                <input type="text" name="pointsIt4" value="${pointsIt[4]}" style="width: 600px"
                                       placeholder="" autocomplete="off" class="layui-input"
                                       maxlength="450">
                            </div>--%>
                        </div>
                    </div>
                </div>
            </div>
            <div class="layui-inline">
                <button class="layui-btn translate3" type="button" style="margin-bottom: 175px;">一键翻译</button>
            </div>
        </div>

        <blockquote class="layui-elem-quote">关键词信息</blockquote>
        <div class="layui-form-item">
            <label class="layui-form-label">关键词</label>
            <div class="layui-inline">
                <div class="layui-tab layui-tab-card" style="width: 670px;">
                    <ul class="sign_desc">回车分隔，不超过5行，字符总长度不超过200</ul>
                    <ul class="layui-tab-title">
                        <li class="layui-this">中文</li>
                        <li>英语</li>
                        <li>日语</li>
                        <li>德语</li>
                        <li>法语</li>
                        <li>西班牙语</li>
                        <li>意大利语</li>
                    </ul>
                    <div class="layui-tab-content" style="height: 210px;">
                        <div class="layui-tab-item layui-show" id="keywordsCn">
    <textarea id="keywordCn" style="width: 100%;height: 200px;"
              placeholder="" autocomplete="off" class="layui-textarea"
              maxlength="80">${keywordsCn[0]}\n${keywordsCn[1]}\n${keywordsCn[2]}\n${keywordsCn[3]}\n${keywordsCn[4]}</textarea>
                        </div>
                        <div class="layui-tab-item" id="keywordsUk">
    <textarea id="keywordUk" style="width: 100%;height: 200px;"
              placeholder="" autocomplete="off" class="layui-textarea"
              maxlength="250">${keywordsUk[0]}\n${keywordsUk[1]}\n${keywordsUk[2]}\n${keywordsUk[3]}\n${keywordsUk[4]}</textarea>
                        </div>
                        <div class="layui-tab-item" id="keywordsJp">
    <textarea id="keywordJp" style="width: 100%;height: 200px;"
              placeholder="" autocomplete="off" class="layui-textarea"
              maxlength="80">${keywordsJp[0]}\n${keywordsJp[1]}\n${keywordsJp[2]}\n${keywordsJp[3]}\n${keywordsJp[4]}</textarea>
                        </div>
                        <div class="layui-tab-item" id="keywordsDe">
    <textarea id="keywordDe" style="width: 100%;height: 200px;"
              placeholder="" autocomplete="off" class="layui-textarea"
              maxlength="250">${keywordsDe[0]}\n${keywordsDe[1]}\n${keywordsDe[2]}\n${keywordsDe[3]}\n${keywordsDe[4]}</textarea>
                        </div>
                        <div class="layui-tab-item" id="keywordsFr">
    <textarea id="keywordFr" style="width: 100%;height: 200px;"
              placeholder="" autocomplete="off" class="layui-textarea"
              maxlength="250">${keywordsFr[0]}\n${keywordsFr[1]}\n${keywordsFr[2]}\n${keywordsFr[3]}\n${keywordsFr[4]}</textarea>
                        </div>
                        <div class="layui-tab-item" id="keywordsEs">
    <textarea id="keywordEs" style="width: 100%;height: 200px;"
              placeholder="" autocomplete="off" class="layui-textarea"
              maxlength="250">${keywordsEs[0]}\n${keywordsEs[1]}\n${keywordsEs[2]}\n${keywordsEs[3]}\n${keywordsEs[4]}</textarea>
                        </div>
                        <div class="layui-tab-item" id="keywordsIt">
    <textarea id="keywordIt" style="width: 100%;height: 200px;"
              placeholder="" autocomplete="off" class="layui-textarea"
              maxlength="250">${keywordsIt[0]}\n${keywordsIt[1]}\n${keywordsIt[2]}\n${keywordsIt[3]}\n${keywordsIt[4]}</textarea>
                        </div>
                    </div>
                </div>
            </div>
            <div class="layui-inline">
                <button class="layui-btn translate3" type="button" style="margin-bottom: 175px;">一键翻译</button>
            </div>
        </div>

        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit lay-filter="formDemo">保存</button>
                <a type="button" class="layui-btn layui-btn-primary"
                   onclick="location.href='<%=request.getContextPath()%>/assistant/index/product/product_claim_list.jsp'"
                   href="javascript:void(0);">
                    返回
                </a>
            </div>
        </div>
    </form>
</article>

<!--_footer 作为公共模版分离出去-->
<%--<script type="text/javascript"
        src="<%=request.getContextPath()%>/assistant/lib/interface/jquery.js"></script>
<script type="text/javascript"
        src="<%=request.getContextPath()%>/assistant/lib/interface/interface.js"></script>--%>
<script type="text/javascript" src="<%=request.getContextPath()%>/assistant/lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript"
        src="<%=request.getContextPath()%>/assistant/lib/jquery-ui/jquery-ui-1.10.4.custom.min.js"></script>
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
    /*var ukName = '${product.itemUk}';*/
    var imagePathSrc = '${product.imagePath}';
    var imagePathArr = imagePathSrc.split(',');
    var skuType = '${skuType}';
    var id = '';
    var layedit;
    var cnedit;
    var ukedit;
    var jpedit;
    var deedit;
    var fredit;
    var esedit;
    var itedit;
    $(function () {
        $("#imagePathSrc").sortable();
        $("#imagePathSrc").disableSelection();
        /*$("#itemUk").val(ukName);*/
        /*$('#imagePathSrc').Sortable(
         {
         accept: 'sortableitem',
         helperclass: 'sorthelper',
         activeclass: 'sortableactive',
         hoverclass: 'sortablehover',
         opacity: 0.8,
         /!*fx: 200,*!/
         revert: true,
         floats: true,
         tolerance: 'pointer'
         }
         )*/
        var pointCn = document.getElementById("pointCn");
        pointCn.innerHTML = pointCn.innerHTML.replace(/\\n/g, "\n");
        /*pointCn.innerHTML = "
        ${pointsCn[0]}" + "\n" + "
        ${pointsCn[1]}" + "\n" + "
        ${pointsCn[2]}" + "\n" + "
        ${pointsCn[3]}" + "\n" + "
        ${pointsCn[4]}";*/
        var pointUk = document.getElementById("pointUk");
        pointUk.innerHTML = pointUk.innerHTML.replace(/\\n/g, "\n");
        <%--pointUk.innerHTML = "${pointsUk[0]}" + "\n" + "${pointsUk[1]}" + "\n" + "${pointsUk[2]}" + "\n" + "${pointsUk[3]}" + "\n" + "${pointsUk[4]}";--%>
        var pointJp = document.getElementById("pointJp");
        pointJp.innerHTML = pointJp.innerHTML.replace(/\\n/g, "\n");
        <%--pointJp.innerHTML = "${pointsJp[0]}" + "\n" + "${pointsJp[1]}" + "\n" + "${pointsJp[2]}" + "\n" + "${pointsJp[3]}" + "\n" + "${pointsJp[4]}";--%>
        var pointDe = document.getElementById("pointDe");
        pointDe.innerHTML = pointDe.innerHTML.replace(/\\n/g, "\n");
        <%--pointDe.innerHTML = "${pointsDe[0]}" + "\n" + "${pointsDe[1]}" + "\n" + "${pointsDe[2]}" + "\n" + "${pointsDe[3]}" + "\n" + "${pointsDe[4]}";--%>
        var pointFr = document.getElementById("pointFr");
        pointFr.innerHTML = pointFr.innerHTML.replace(/\\n/g, "\n");
        <%--pointFr.innerHTML = "${pointsFr[0]}" + "\n" + "${pointsFr[1]}" + "\n" + "${pointsFr[2]}" + "\n" + "${pointsFr[3]}" + "\n" + "${pointsFr[4]}";--%>
        var pointEs = document.getElementById("pointEs");
        pointEs.innerHTML = pointEs.innerHTML.replace(/\\n/g, "\n");
        <%--pointEs.innerHTML = "${pointsEs[0]}" + "\n" + "${pointsEs[1]}" + "\n" + "${pointsEs[2]}" + "\n" + "${pointsEs[3]}" + "\n" + "${pointsEs[4]}";--%>
        var pointIt = document.getElementById("pointIt");
        pointIt.innerHTML = pointIt.innerHTML.replace(/\\n/g, "\n");
        <%--pointIt.innerHTML = "${pointsIt[0]}" + "\n" + "${pointsIt[1]}" + "\n" + "${pointsIt[2]}" + "\n" + "${pointsIt[3]}" + "\n" + "${pointsIt[4]}";--%>

        var keywordCn = document.getElementById("keywordCn");
        keywordCn.innerHTML = keywordCn.innerHTML.replace(/\\n/g, "\n");
        <%--keywordCn.innerHTML = "${keywordsCn[0]}" + "\n" + "${keywordsCn[1]}" + "\n" + "${keywordsCn[2]}" + "\n" + "${keywordsCn[3]}" + "\n" + "${keywordsCn[4]}";--%>
        var keywordUk = document.getElementById("keywordUk");
        keywordUk.innerHTML = keywordUk.innerHTML.replace(/\\n/g, "\n");
        <%--keywordUk.innerHTML = "${keywordsUk[0]}" + "\n" + "${keywordsUk[1]}" + "\n" + "${keywordsUk[2]}" + "\n" + "${keywordsUk[3]}" + "\n" + "${keywordsUk[4]}";--%>
        var keywordJp = document.getElementById("keywordJp");
        keywordJp.innerHTML = keywordJp.innerHTML.replace(/\\n/g, "\n");
        <%--keywordJp.innerHTML = "${keywordsJp[0]}" + "\n" + "${keywordsJp[1]}" + "\n" + "${keywordsJp[2]}" + "\n" + "${keywordsJp[3]}" + "\n" + "${keywordsJp[4]}";--%>
        var keywordDe = document.getElementById("keywordDe");
        keywordDe.innerHTML = keywordDe.innerHTML.replace(/\\n/g, "\n");
        <%--keywordDe.innerHTML = "${keywordsDe[0]}" + "\n" + "${keywordsDe[1]}" + "\n" + "${keywordsDe[2]}" + "\n" + "${keywordsDe[3]}" + "\n" + "${keywordsDe[4]}";--%>
        var keywordFr = document.getElementById("keywordFr");
        keywordFr.innerHTML = keywordFr.innerHTML.replace(/\\n/g, "\n");
        <%--keywordFr.innerHTML = "${keywordsFr[0]}" + "\n" + "${keywordsFr[1]}" + "\n" + "${keywordsFr[2]}" + "\n" + "${keywordsFr[3]}" + "\n" + "${keywordsFr[4]}";--%>
        var keywordEs = document.getElementById("keywordEs");
        keywordEs.innerHTML = keywordEs.innerHTML.replace(/\\n/g, "\n");
        <%--keywordEs.innerHTML = "${keywordsEs[0]}" + "\n" + "${keywordsEs[1]}" + "\n" + "${keywordsEs[2]}" + "\n" + "${keywordsEs[3]}" + "\n" + "${keywordsEs[4]}";--%>
        var keywordIt = document.getElementById("keywordIt");
        keywordIt.innerHTML = keywordIt.innerHTML.replace(/\\n/g, "\n");
        <%--keywordIt.innerHTML = "${keywordsIt[0]}" + "\n" + "${keywordsIt[1]}" + "\n" + "${keywordsIt[2]}" + "\n" + "${keywordsIt[3]}" + "\n" + "${keywordsIt[4]}";--%>

        layui.use('element', function () {
            var element = layui.element;
        });
        layui.use('layedit', function () {
            layedit = layui.layedit;
            cnedit = layedit.build('productDescriptionCn');
            ukedit = layedit.build('productDescriptionUk');
            jpedit = layedit.build('productDescriptionJp');
            deedit = layedit.build('productDescriptionDe');
            fredit = layedit.build('productDescriptionFr');
            esedit = layedit.build('productDescriptionEs');
            itedit = layedit.build('productDescriptionIt');
        });

        function initSkuSaleDate() {
            layui.use('laydate', function () {
                var laydate = layui.laydate;
                lay('.saleStart').each(function () {
                    laydate.render({
                        elem: this
                        , trigger: 'click'
                    });
                });
                lay('.saleEnd').each(function () {
                    laydate.render({
                        elem: this
                        , trigger: 'click'
                    });
                });
            });
            /*layui.use('laydate', function () {
             var laydate = layui.laydate;
             laydate.render({
             elem: '.saleStart' //促销开始时间
             });

             laydate.render({
             elem: '.saleEnd' //促销结束时间
             });
             });*/
        }


        imgKeys = [], imgValues = [];


        var selectValue = null;
        layui.use('form', function () {
            var form = layui.form;
            form.on('radio(skuType)', function (data) {
                if ($(this).val() == "2") {//选中多变体
                    mutiSku();
                } else {//单体
                    singleSku();
                }
            });
            if (skuType == "2") {//选中多变体
                mutiSku();
                $("input[name='skuType']").eq(1).attr("checked", 'checked');
                $("input[name='skuType']").eq(0).removeAttr("checked");
                var vars = JSON.parse('${productVars}');
                var theme = "";
                for (var j = 0; j < vars.length; j++) {
                    var vv1Type = vars[j].variationType;
                    if (vv1Type != null && vv1Type != 'undefined') {
                        theme = vv1Type;
                    }
                }
                $("#selectMuti").val(theme);
                form.render("select");
                selectValue = theme;
                $("#skuRender").html("");
                $("#skuRender").append(genSkuTypeDom($("#skuMutiDiv option:selected").text()));
                $(".skuBtn").on("click", function () {
                    var $this = $(this);
                    var text = $(this).parent().parent().find("input").val();
                    if (text != null && $.trim(text).length > 0) {
                        text = $.trim(text);
                        var $skuCheclk = $(this).parent().parent().prev().find(".skuCheckbox").eq(0);
                        var skarr = $skuCheclk.find("input[type='checkbox']");
                        var isExist = false;
                        for (var i = 0; i < skarr.length; i++) {
                            if ($(skarr[i]).next().find("span").text() == text) {
                                isExist = true;
                                break;
                            }
                        }
                        if (!isExist) {
                            $skuCheclk.append("<input type=\"checkbox\" lay-filter='skuMutiCheck' name=\"\" title=" + text + " lay-skin=\"primary\" checked>");
                            form.render('checkbox');
                            refrehSKuPath(true);
                            form.render(null, "skuMutiPath");
                            form.render(null, "skuTable");
                            $this.parent().parent().find("input").val('');
                        }
                    }
                });
                for (var i = 0; i < vars.length; i++) {
                    var vvType = vars[i].variationType;
                    if (vvType != null && vvType != 'undefined') {
                        if (vvType == 'Color') {
                            $("#skuRender button").parent().prev().find('input').val(vars[i].colorMap);
                            $(".skuBtn").click();
                            form.render(null, 'skuRender');
                        } else if (vvType == 'Size') {
                            $("#skuRender button").parent().prev().find('input').val(vars[i].sizeMap);
                            $(".skuBtn").click();
                            form.render(null, 'skuRender');
                        } else if (vvType == "material") {
                            $("#skuRender button").parent().prev().find('input').val(vars[i].materialType);
                            $(".skuBtn").click();
                            form.render(null, 'skuRender');
                        } else if (vvType == "size-material") {
                            $("#skuRender button").parent().eq(0).prev().find('input').val(vars[i].sizeMap);
                            $(".skuBtn").click();
                            form.render(null, 'skuRender');
                            $("#skuRender button").parent().eq(1).prev().find('input').val(vars[i].materialType);
                            $(".skuBtn").click();
                            form.render(null, 'skuRender');
                        } else if (vvType == "color-material") {
                            $("#skuRender button").eq(0).parent().prev().find('input').val(vars[i].colorMap);
                            $(".skuBtn").click();
                            form.render(null, 'skuRender');
                            $("#skuRender button").eq(1).parent().prev().find('input').val(vars[i].materialType);
                            $(".skuBtn").click();
                            form.render(null, 'skuRender');
                        } else if (vvType == "itempackagequantity") {
                            $("#skuRender button").parent().prev().find('input').val(vars[i].materialType);
                            $(".skuBtn").click();
                            form.render(null, 'skuRender');
                        } else if (vvType == "color-itempackagequantity") {
                            $("#skuRender button").eq(0).parent().prev().find('input').val(vars[i].colorMap);
                            $(".skuBtn").click();
                            form.render(null, 'skuRender');
                            $("#skuRender button").eq(1).parent().prev().find('input').val(vars[i].materialType);
                            $(".skuBtn").click();
                            form.render(null, 'skuRender');
                        } else if (vvType == "itempackagequantity-size") {
                            $("#skuRender button").eq(0).parent().prev().find('input').val(vars[i].materialType);
                            $(".skuBtn").click();
                            form.render(null, 'skuRender');
                            $("#skuRender button").eq(1).parent().prev().find('input').val(vars[i].sizeMap);
                            $(".skuBtn").click();
                            form.render(null, 'skuRender');
                        } else if (vvType == "itempackagequantity-material") {
                            $("#skuRender button").eq(0).parent().prev().find('input').val(vars[i].materialType);
                            $(".skuBtn").click();
                            form.render(null, 'skuRender');
                            $("#skuRender button").eq(1).parent().prev().find('input').val(vars[i].materialType);
                            $(".skuBtn").click();
                            form.render(null, 'skuRender');
                        } else if (vvType == 'colorsize') {
                            $("#skuRender button").eq(0).parent().prev().find('input').val(vars[i].colorMap);
                            $(".skuBtn").click();
                            form.render(null, 'skuRender');
                            $("#skuRender button").eq(1).parent().prev().find('input').val(vars[i].sizeMap);
                            $(".skuBtn").click();
                            form.render(null, 'skuRender');
                        }
                        //form.render('checkbox');
                        //initSKuPath(null,null);
                    }
                }
                initSKuPath(null, null);
                var index = 0;
                for (var i = 0; i < vars.length; i++) {
                    var vvType = vars[i].variationType;
                    if (vvType != null && vvType != 'undefined') {
                        var ttImages = new Array();
                        if (vars[i].attachPath == null || vars[i].attachPath == "") {//如果没有附图就加载主图
                            ttImages.push(vars[i].mainPath);
                        } else {
                            ttImages = (vars[i].mainPath + "," + vars[i].attachPath).split(",");
                        }
                        var ttHtml = '';
                        for (var j = 0; j < ttImages.length; j++) {
                            ttHtml += "<div style='width:100px;height:110px;margin-left:2px;float:left;'><img width='100px' height='90px' style='padding-right:5px;' src=<%=session.getAttribute("productPath")%>" + ttImages[j] + " /><i class='layui-icon delImage' style='font-size:20px;margin-left:35px;'>&#xe640;</i></div>";
                        }
                        $("#skuMutiPath > div :eq(" + (index * 3 + 2) + ")").html(ttHtml);
                        $("#skuTable> tbody tr").eq(index).find("td").each(function (ii, val) {
                            var isTwo = $(val).parent().attr("val").indexOf(":") > 0;
                            if (ii == 0) {
                                $(val).find(".trOtherPath").val(ttImages.join(","));
                            }
                            if (isTwo) {
                                if (ii == 3) {
                                    $(val).find("input").val(vars[i].price);
                                }
                                if (ii == 4) {
                                    $(val).find("input").val(vars[i].salePrice);
                                }
                                if (ii == 5) {
                                    $(val).find("input:eq(0)").val(getMyDate(vars[i].saleStartTime));
                                    $(val).find("input:eq(1)").val(getMyDate(vars[i].saleEndTime));
                                }
                                if (ii == 6) {
                                    $(val).find("input").val(vars[i].quantity);
                                }
                            } else {
                                if (ii == 2) {
                                    $(val).find("input").val(vars[i].price);
                                }
                                if (ii == 3) {
                                    $(val).find("input").val(vars[i].salePrice);
                                }
                                if (ii == 4) {
                                    $(val).find("input:eq(0)").val(getMyDate(vars[i].saleStartTime));
                                    $(val).find("input:eq(1)").val(getMyDate(vars[i].saleEndTime));
                                }
                                if (ii == 5) {
                                    $(val).find("input").val(vars[i].quantity);
                                }
                            }
                        });
                        index++;
                    }
                }

                $(".delImage").on("click", function () {
                    var pprent = $(this).parent().parent();
                    var ppKey = $(this).prev().attr("src").substring(55);
                    $("#skuTable tbody tr").each(function (i, val) {
                        var exVal = $(val).find(".trOtherPath").val();
                        var delVals = [];
                        if (exVal != null && exVal != "") {
                            var exArr = exVal.split(",");
                            for (var i = 0; i < exArr.length; i++) {
                                if (exArr[i] != "" && ppKey != exArr[i]) {
                                    delVals.push(exArr[i]);
                                }
                            }
                        }
                        $(val).find(".trOtherPath").val(delVals.join(","));
                    });
                    $(this).parent().remove();
                    if (pprent.find("div").length == 0) {
                        pprent.append("<img class='pathDemo' src='http://bpic.588ku.com/element_origin_min_pic/01/47/02/12574338a640011.jpg!r650' width='100' height='90' />");
                    }
                })
            } else {//单体
                singleSku();
                loadSingleImage();
                $("input[name='skuType']").eq(0).attr("checked", 'checked');
                $("input[name='skuType']").eq(1).removeAttr("checked");
            }
            form.render("radio");
            form.on('checkbox(skuMutiCheck)', function (data) {//切换变体类型
                refrehSKuPath(false);
                form.render(null, "skuMutiPath");
            });

            form.on('select(skuMuti)', function (data) {//刷新
                selectValue = data.value;
                $("#skuRender").html("");
                $("#skuRender").append(genSkuTypeDom($("#skuMutiDiv option:selected").text()));

                $(".skuBtn").on("click", function () {
                    var $this = $(this);
                    var text = $(this).parent().parent().find("input").val();
                    if (text != null && $.trim(text).length > 0) {
                        text = $.trim(text);
                        var $skuCheclk = $(this).parent().parent().prev().find(".skuCheckbox").eq(0);
                        var skarr = $skuCheclk.find("input[type='checkbox']");
                        var isExist = false;
                        for (var i = 0; i < skarr.length; i++) {
                            if ($(skarr[i]).next().find("span").text() == text) {
                                isExist = true;
                                break;
                            }
                        }
                        if (!isExist) {
                            $skuCheclk.append("<input type=\"checkbox\" lay-filter='skuMutiCheck' name=\"\" title=" + text + " lay-skin=\"primary\" checked>");
                            form.render('checkbox');
                            refrehSKuPath(false);
                            form.render(null, "skuMutiPath");
                            form.render(null, "skuTable");
                            $this.parent().parent().find("input").val('');
                        }
                    }
                });
                form.render(null, 'skuRender');
                form.render('checkbox');
            });

            form.on('submit', function (data) {
                var skuTypeA = data.field.skuType, skuValue = data.field.sku;
                var isExs = false;
                $.ajax({
                    url: "<%=request.getContextPath()%>/product/skuValid",
                    dataType: 'json',
                    data: {sku: skuValue},
                    async: false,
                    success: function (data) {
                        if (data.success) {
                            isExs = true;
                        }
                    }
                });

                if (isExs && skuValue != '${product.sku}') {
                    layer.msg("sku已存在，请重新修改！", {icon: 5, time: 1000});
                    setTimeout(function () {

                    }, 1500);
                    return false;
                }

                //详细描述
                data.field["productDescriptionUk"] = layedit.getContent(ukedit);
                data.field["productDescriptionFr"] = layedit.getContent(fredit);
                data.field["productDescriptionDe"] = layedit.getContent(deedit);
                data.field["productDescriptionIt"] = layedit.getContent(itedit);
                data.field["productDescriptionEs"] = layedit.getContent(esedit);
                data.field["productDescriptionJp"] = layedit.getContent(jpedit);
                data.field["productDescriptionCn"] = layedit.getContent(cnedit);
                //组合关键词和简要描述
                data.field["bulletPointCn"] = getContentByLanguage("pointsCn");
                data.field["bulletPointJp"] = getContentByLanguage("pointsJp");
                data.field["bulletPointEs"] = getContentByLanguage("pointsEs");
                data.field["bulletPointDe"] = getContentByLanguage("pointsDe");
                data.field["bulletPointFr"] = getContentByLanguage("pointsFr");
                data.field["bulletPointUk"] = getContentByLanguage("pointsUk");
                data.field["bulletPointIt"] = getContentByLanguage("pointsIt");

                data.field["keywordsCn"] = getContentByLanguage("keywordsCn");
                data.field["keywordsFr"] = getContentByLanguage("keywordsFr");
                data.field["keywordsJp"] = getContentByLanguage("keywordsJp");
                data.field["keywordsDe"] = getContentByLanguage("keywordsDe");
                data.field["keywordsEs"] = getContentByLanguage("keywordsEs");
                data.field["keywordsIt"] = getContentByLanguage("keywordsIt");
                data.field["keywordsUk"] = getContentByLanguage("keywordsUk");
                var isItemImags = true;
                if (skuTypeA == 1) {//单体
                    var imagePathValue = "";
                    var arr = $("#imagePathSrc img");
                    if (arr.length > 0) {
                        for (var i = 0; i < arr.length; i++) {
                            if ($(arr[i]).attr("src").indexOf("product") > -1) {
                                imagePathValue += "," + $(arr[i]).attr("src").substring(55);
                            }
                        }
                        imagePathValue = imagePathValue.substring(1);
                    }
                    if (imagePathValue == null || imagePathValue == "") {
                        isItemImags = false;
                    }
                    data.field["imagePath"] = imagePathValue;
                    if (data.field['quantity'] == null || $.trim(data.field['quantity']).length == 0) {
                        layer.msg("库存数量不能为空！", {icon: 5, time: 1000});
                        return false;
                    }
                } else {//多变中
                    if (selectValue == null || selectValue == "") {
                        layer.msg("请选择变种主题！", {icon: 5, time: 1000});
                        return false;
                    }

                    var allKey = [];
                    var allValue = [];

                    $("#skuMutiPath>div").each(function (i, val) {
                        if (i % 3 == 0) {

                            var imgs = [];
                            $(val).next().next().find("img").each(function (k, vv) {
                                imgs.push($(vv).attr("src").substring(55));
                            });

                            if (imgs.length > 0) {
                                allKey.push($(val).attr("val"));
                                allValue.push(imgs);
                            }
                        }
                    });


                    var skuVar = [];
                    $("#skuTable tbody tr").each(function (i, val) {
                        var item = {};
                        var itemImgs = $(val).eq(0).find(".trOtherPath").val().split(",");
                        if ((itemImgs.length < 1 || itemImgs[0].length == 0) && i == 0) {
                            layer.msg("请上传变体图片", {icon: 5, time: 1000});
                            return false;
                        }

                        var key = $(val).attr("val");
                        var index = allKey.indexOf(key);

                        itemImgs = allValue[index];

                        item["productId"] = $("input[name='id']").val();
                        item["mainPath"] = itemImgs[0];
                        item["attachPath"] = itemImgs.slice(1).join(",");
                        item["sku"] = $(val).eq(0).find("input[type='text']").val();
                        item["price"] = $(val).find(".price").val();
                        item["salePrice"] = $(val).find(".salePrice").val();
                        item["saleStartTime"] = $(val).find(".saleStart").val();
                        item["saleEndTime"] = $(val).find(".saleEnd").val();
                        item["quantity"] = $(val).find(".quantity").val();
                        item["variationType"] = selectValue;

                        var firstValue, secondValue;
                        $(val).find(".skuVarType").each(function (i, v) {
                            if (i == 0) {
                                firstValue = $(v).html();
                            }
                            if (i == 1) {
                                secondValue = $(v).html();
                            }
                        });

                        if (selectValue == "Color") {
                            item["colorName"] = firstValue;
                            item["colorMap"] = firstValue;
                        } else if (selectValue == "Size") {
                            item["sizeMap"] = firstValue;
                            item["sizeName"] = firstValue;
                        } else if (selectValue == "material") {
                            item["materialType"] = firstValue;
                        } else if (selectValue == "size-material") {
                            item["sizeMap"] = firstValue;
                            item["sizeName"] = firstValue;
                            item["materialType"] = secondValue;
                        } else if (selectValue == "color-material") {
                            item["colorName"] = firstValue;
                            item["colorMap"] = firstValue;
                            item["materialType"] = secondValue;
                        } else if (selectValue == "itempackagequantity") {
                            item["itemPackageQuantity"] = firstValue;
                        } else if (selectValue == "color-itempackagequantity") {
                            item["colorName"] = firstValue;
                            item["colorMap"] = firstValue;
                            item["itemPackageQuantity"] = secondValue;
                        } else if (selectValue == "itempackagequantity-size") {
                            item["itemPackageQuantity"] = firstValue;
                            item["sizeMap"] = secondValue;
                            item["sizeName"] = secondValue;
                        } else if (selectValue == "itempackagequantity-material") {
                            item["itempackagequantity"] = firstValue;
                            item["materialType"] = secondValue;
                        }
                        else if (selectValue == "colorsize") {
                            item["colorName"] = firstValue;
                            item["colorMap"] = firstValue;
                            item["sizeMap"] = secondValue;
                            item["sizeName"] = secondValue;
                        }
                        skuVar.push(item);
                    });
                }

                //return false;
                /* if (!isItemImags) {
                 layer.msg("请上传变体图片", {icon: 5, time: 1000});
                 return false;
                 }*/
                data.field["vars"] = JSON.stringify(skuVar);
                var url = '<%=request.getContextPath()%>/product/claim/save';
                layer.load();
                $.ajax({
                    type: 'POST',
                    url: url,
                    dataType: 'json',
                    data: data.field,
                    success: function (data) {
                        layer.closeAll('loading');
                        if (data.success) {
                            layer.msg(data.msg, {icon: 6, time: 1000});
                            setTimeout(function () {
                                location.href = '<%=request.getContextPath()%>/assistant/index/product/product_claim_list.jsp';
                            }, 500);
                        } else {
                            layer.msg(data.msg, {icon: 5, time: 1000});
                        }
                    },
                    error: function (data) {
                        layer.closeAll('loading');
                        layer.msg("保存失败", {icon: 5, time: 1000});
                    }
                });
                return false;
            });
        });

        function refrehSKuPath(isDisplay) {
            $("#skuMutiPath").html("");
            var arr = [];
            var skuCheckRow = $(".skuCheckbox");
            if (skuCheckRow == null || skuCheckRow.length == 0) {
                return;
            }
            //做多2中sku组合 暂时不考虑
            var first = [];
            var second = [];
            var fistRow = [];
            var secondRow = [];
            if (skuCheckRow.length == 1) {//只有一个变种
                var skuTypeDesc = $(".skuCheckbox").prev().find("span").text();
                var checked = $(".skuCheckbox .layui-form-checked");
                for (var i = 0; i < checked.length; i++) {
                    arr.push(skuTypeDesc + ":" + $(checked[i]).find("span").text());
                    fistRow.push($(checked[i]).find("span").text());
                    skuTable(skuTypeDesc, fistRow, null, null);
                }
            } else {
                var fisrtDesc = $(".skuCheckbox").eq(0).prev().find("span").text();
                var secondDesc = $(".skuCheckbox").eq(1).prev().find("span").text();
                first = $(".skuCheckbox").eq(0).find(".layui-form-checked");
                second = $(".skuCheckbox").eq(1).find(".layui-form-checked");
                if (first.length == 0 && second.length == 0)return;
                if (first.length > 0 && second.length > 0) {
                    for (var i = 0; i < first.length; i++) {
                        for (var k = 0; k < second.length; k++) {
                            arr.push(fisrtDesc + ":" + $(first[i]).find("span").text() + " " + secondDesc + ":" + $(second[k]).find("span").text());
                            fistRow.push($(first[i]).find("span").text());
                            secondRow.push($(second[k]).find("span").text());
                        }
                    }
                    skuTable(fisrtDesc, fistRow, secondDesc, secondRow);
                } else {
                    var desc = (first.length == 0) ? $(".skuCheckbox").eq(1).prev().find("span").text() : $(".skuCheckbox").eq(0).prev().find("span").text();
                    var checked = (first.length == 0) ? second : first;
                    var fisrtDesc = $(".skuCheckbox").eq(0).prev().find("span").text();
                    for (var i = 0; i < checked.length; i++) {
                        arr.push(desc + ":" + $(checked[i]).find("span").text());
                        fistRow.push($(first[i]).find("span").text());
                    }
                    skuTable(desc, fistRow, null, null);
                }
            }
            $("#skuMutiPath").append(getSKuPathDom(arr));
            if (!isDisplay) {
                initSKuPath(null, null);
            }
        }

        function skuTable(fisrtDesc, fisrtArr, secondDesc, secondArr) {
            $("#skuTable tbody").html("");
            if (fisrtDesc == null) {
                return;
            }

            var dom = "";
            if (fisrtDesc != null && secondDesc != null) {
                $("#skuTable colgroup").html(getCgroup(false));
                $("#skuTable thead").html(getThead("<th>" + fisrtDesc + "</th><th>" + secondDesc + "</th>"));
                for (var i = 0; i < fisrtArr.length; i++) {
                    dom += getTR(false, fisrtArr[i], secondArr[i]);
                }
            } else {
                $("#skuTable colgroup").html(getCgroup(true));
                $("#skuTable thead").html(getThead("<th>" + fisrtDesc + "</th>"));
                for (var i = 0; i < fisrtArr.length; i++) {
                    dom += getTR(true, fisrtArr[i], null);
                }
            }
            $("#skuTable tbody").html(dom);
            initSkuSaleDate();
            /*//监听价格变化
             $("#skuTable .price").on('blur', function () {
             var $this = $(this);
             $("#skuTable .price").each(function (i, val) {
             if ($(val).val() == null || $.trim($(val).val()).length == 0) {
             $(val).val($this.val());
             }
             });
             });*/
        }

        function getTR(isSingle, first, second) {
            var sku = $("input[name='sku']").val() + "-" + (isSingle ? first : (first + "-" + second));
            return "<tr val=" + (isSingle ? first : (first + ":" + second)) + ">" +
                    "<td><input type='hidden' class='trMainPath' /> <input type='hidden' class='trOtherPath' /> <input type='text' value='" + sku + "' lay-verify='required' autocomplete='off' class='layui-input'></td>" +
                    (isSingle ? ("<td class='skuVarType'>" + first + "</td>") : ("<td class='skuVarType'>" + first + "</td><td class='skuVarType'>" + second + "</td>"))
                    +
                    "<td><input type='text' lay-verify='required' autocomplete='off' class='layui-input price'></td>" +
                    "<td><input type='text' autocomplete='off' class='layui-input salePrice'></td>" +
                    "<td> <div class='layui-inline'>" +
                    "<input type='text' autocomplete='off' class='layui-input saleStart'>" +
                    "</div>" +
                    "-" +
                    "<div class='layui-inline'>" +
                    "<input type='text' autocomplete='off' class='layui-input saleEnd'>" +
                    "</div>" +
                    "</td>" +
                    "<td><input type='text' lay-verify='required' value='30' autocomplete='off' class='layui-input quantity'></td>" +
                    "</tr>";
        }

        //监听parentSKU值变化
        $("input[name='sku']").bind("input propertychange change", function (event) {
            $("#skuTable tbody tr").each(function (i, val) {
                var text = $(val).find("td").eq(0).find("input[type='text']").val();
                var kk = $(val).attr("val") + "";
                $(val).find("td").eq(0).find("input[type='text']").val($("input[name='sku']").val() + "-" + $.trim(kk).split(",").join("-"))
            });
        });

        function getCgroup(isSingle) {
            return "<col width=\"200\">" +
                    (isSingle ? ("<col width=\"100\">") : ("<col width=\"100\"><col width=\"100\">")) +
                    " <col width=\"50\">" +
                    " <col width=\"50\">" +
                    " <col width=\"300\">" +
                    " <col width=\"100\">";
        }

        function getThead(desc) {
            return "<tr> <th>sku</th>" + desc + "<th width='50px;'>价格</th> <th width='50px;'>促销价</th> <th>促销时间</th> <th>数量</th></tr> ";
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

            dom += "<div class=\"layui-form-item\" id='skuMutiPath' lay-filter=\"skuMutiPath\" >\n" +
                    " <label class=\"layui-form-label\">变种图片</label>\n" +
                    "</div>";

            dom += "<div class=\"layui-form-item\">" +
                    "<label class=\"layui-form-label\">变种参数</label>" +
                    "<div class=\"layui-input-block\">" +
                    "<table class=\"layui-table\" id=\"skuTable\" lay-filter='skuTable'>" +
                    " <colgroup>" +
                    " </colgroup><thead></thead> <tbody>" +
                    "</tbody>" +
                    "</table>" +
                    "</div>" +
                    "</div>";
            return dom;
        }

        function getSKuPathDom(arr) {
            if (arr == null || arr.length == 0) {
                return "";
            }
            var dom = "";
            for (var i = 0; i < arr.length; i++) {
                var id = "skuMainPath" + i;
                var sid = "skuOtherPath" + i;
                var imageSrcId = "skuImageSrc" + i;
                var key = "";
                var arrKeys = $.trim(arr[i]).split(/\s+/);
                for (var ind = 0; ind < arrKeys.length; ind++) {
                    if (arrKeys[ind] != null && arrKeys[ind].indexOf(":") == 0) {
                        key += ":" + arrKeys[ind].substring(1);
                    }
                }
                dom += "<div class=\"layui-input-block\" val=" + key.substring(1) + ">" +
                        " <div class='layui-inline layui-bg-gray' style='margin-top: 10px;'>变种属性 " + arr[i] + "</div>" +
                        " </div>" +
                        " <div class=\"layui-input-block\" style='margin-top: 10px;'>\n" +
                        " <button type=\"button\" id=" + sid + " class=\"layui-btn skuOtherPath\" >\n" +
                        " <i class=\"layui-icon\">&#xe67c;</i>上传图片\n" +
                        " </button>" +
                        " </div>" +
                        " <div id=" + imageSrcId + " class=\"layui-input-block skuImageSrc\" style='margin-top: 5px;height: 110px;'>" +
                        "<img class=\"pathDemo\" src='http://bpic.588ku.com/element_origin_min_pic/01/47/02/12574338a640011.jpg!r650' width='100' height='90' />" +
                        " </div>";
            }
            return dom;
        }

        function getSkuItemDom(desc) {
            return "<div class='layui-form-item' >" +
                    "<div class='layui-input-block'>" +
                    " <div class='layui-inline' style='width: 50px;'><span>" + desc +
                    " </span> :</div>" +
                    " <div class='layui-inline skuCheckbox'>" +
                    " </div>" +
                    "</div>" +
                    " <div class='layui-input-block'>" +
                    " <div class=\"layui-input-inline\" style=\"width: 150px;\">" +
                    " <input type=\"text\" name=\"\" autocomplete=\"off\" class=\"layui-input\">" +
                    " </div>" +
                    " <div class=\"layui-input-inline\" style=\"width: 100px;\">" +
                    " <button type='button' class=\"layui-btn skuBtn\">添加</button>\n" +
                    " </div>" +
                    " </div>" +
                    "</div> " +
                    "</div>";
        }


        function initSKuPath(id, sid) {
            $(".skuImageSrc").sortable();
            $(".skuImageSrc").disableSelection();
            layui.use('upload', function () {
                var upload = layui.upload;
                //执行实例
                var uploadInst = upload.render({
                    accept: "images",
                    acceptMime: "image/*",
                    elem: ".skuMainPath" //绑定元素
                    , url: '<%=request.getContextPath()%>/upload/image' //上传接口
                    , done: function (res) {
                        var $this = $($(this)[0].item[0]);
                        var key = $this.parent().prev().attr("val");
                        $this.parent().next().html("<div class='sortableitem' style='width:100px;height:110px;'><img width='100px' height='90px' src=<%=session.getAttribute("productPath")%>" + res.data + " /><i class='layui-icon delImageMain' style='font-size:20px;margin-left:35px;'>&#xe640;</i> </div>");
                        $("#skuTable tbody tr").each(function (i, val) {
                            if ($(val).attr("val") == key) {
                                $(val).find(".trMainPath").val(res.data);
                            }
                        });

                        $(".delImageMain").on("click", function () {
                            $("#skuTable tbody tr").each(function (i, val) {
                                if ($(val).attr("val") == key) {
                                    $(val).find(".trMainPath").val("");
                                }
                            });
                            var pprent = $(this).parent().parent();
                            $(this).parent().remove();
                            pprent.append("<img src='http://bpic.588ku.com/element_origin_min_pic/01/47/02/12574338a640011.jpg!r650' width='100' height='90' />");
                        });
                        //上传完毕回调
                    }
                    , error: function () {
                        //请求异常回调
                    }
                });
                var uploadMuti = upload.render({
                    accept: "images",
                    acceptMime: "image/*",
                    elem: ".skuOtherPath", //绑定元素
                    multiple: true
                    , number: 9
                    , url: '<%=request.getContextPath()%>/upload/image' //上传接口
                    , done: function (res, index, upload) {
                        var $this = $($(this)[0].item[0]);
                        var key = $this.parent().prev().attr("val");
                        $this.parent().next().find(".pathDemo").remove();
                        $this.parent().next().append("<div class='sortableitem' style='width:100px;height:110px;margin-left:2px;float:left;'><img width='100px' height='90px' style='padding-right:5px;' src=<%=session.getAttribute("productPath")%>" + res.data + " /><i class='layui-icon delImage' style='font-size:20px;margin-left:35px;'>&#xe640;</i></div>");
                        $("#skuTable tbody tr").each(function (i, val) {
                            if ($(val).attr("val") == key) {
                                var exVal = $(val).find(".trOtherPath").val();
                                var exVals = "";
                                if (exVal == null || exVal == "") {
                                    exVals = res.data;
                                } else if (exVal.split(",").length < 9) {
                                    exVals = exVal += "," + res.data;
                                } else {
                                    exVals = exVal;
                                }
                                $(val).find(".trOtherPath").val(exVals);
                            }
                        });
                        //上传完毕回调
                        if ($this.parent().next().find("img").length > 9) {//最多上传9张
                            $this.parent().next().find("img").each(function (i, val) {
                                if (i > 9) {
                                    $(val).parent().remove();
                                }
                            });
                        }
                        $(".delImage").on("click", function () {
                            var pprent = $(this).parent().parent();
                            var ppKey = $(this).prev().attr("src").substring(55);
                            $("#skuTable tbody tr").each(function (i, val) {
                                var exVal = $(val).find(".trOtherPath").val();
                                var delVals = [];
                                if (exVal != null && exVal != "") {
                                    var exArr = exVal.split(",");
                                    for (var i = 0; i < exArr.length; i++) {
                                        if (exArr[i] != "" && ppKey != exArr[i]) {
                                            delVals.push(exArr[i]);
                                        }
                                    }
                                }
                                $(val).find(".trOtherPath").val(delVals.join(","));
                            });

                            $(this).parent().remove();
                            if (pprent.find("div").length == 0) {
                                pprent.append("<img class='pathDemo' src='http://bpic.588ku.com/element_origin_min_pic/01/47/02/12574338a640011.jpg!r650' width='100' height='90' />");
                            }
                        })
                    }
                    , error: function () {
                        //请求异常回调
                    }
                });
            });
        }


        function getContentByLanguage(id) {
            var arr = ["", "", "", "", ""];
            var value = "";
            $("#" + id).find("textarea").each(function (i, val) {
                value = $(val).val();
            });
            var values = value.split("\n");
            for (var i = 0; i < values.length; i++) {
                arr[i] = values[i];
            }
            return JSON.stringify(arr);
        }

        //一键翻译
        $(".translate1").click(function () {//标题
            var currentLanguage = $(this).parent().prev().find(".layui-this").text();
            var isContainsEmpty = false;
            var vArr = [];
            var inputs = $(this).parent().prev().find(".layui-show input");
            for (var i = 0; i < inputs.length; i++) {
                vArr.push($(inputs[i]).val());
                if ($(inputs[i]).val() == null || $.trim($(inputs[i]).val()).length == 0) {
                    isContainsEmpty = true;
                }
            }
            var content = $(this).parent().prev().find(".layui-tab-content");
            if (currentLanguage === "中文") {
                valueDisplay("cn", content, vArr, 1);
            } else if (currentLanguage === "英语") {
                valueDisplay("uk", content, vArr, 1);
            } else if (currentLanguage === "日语") {
                valueDisplay("jp", content, vArr, 1);
            } else if (currentLanguage === "德语") {
                valueDisplay("de", content, vArr, 1);
            } else if (currentLanguage === "法语") {
                valueDisplay("fr", content, vArr, 1);
            } else if (currentLanguage === "西班牙语") {
                valueDisplay("es", content, vArr, 1);
            } else if (currentLanguage === "意大利语") {
                valueDisplay("it", content, vArr, 1);
            }
            if (isContainsEmpty) {
                layer.msg('翻译标题不能为空', {icon: 5, time: 1000});
                return;
            }
        });
        $(".translate2").click(function () {//产品描述
            var currentLanguage = $(this).parent().prev().find(".layui-this").text();
            var content = $(this).parent().prev().find(".layui-tab-content");
            var vArr = [];
            if (currentLanguage === "中文") {
                vArr.push(layedit.getContent(cnedit));
                valueDisplay("cn", content, vArr, 2);
            } else if (currentLanguage === "英语") {
                vArr.push(layedit.getContent(ukedit));
                valueDisplay("uk", content, vArr, 2);
            } else if (currentLanguage === "日语") {
                vArr.push(layedit.getContent(jpedit));
                valueDisplay("jp", content, vArr, 2);
            } else if (currentLanguage === "德语") {
                vArr.push(layedit.getContent(deedit));
                valueDisplay("de", content, vArr, 2);
            } else if (currentLanguage === "法语") {
                vArr.push(layedit.getContent(fredit));
                valueDisplay("fr", content, vArr, 2);
            } else if (currentLanguage === "西班牙语") {
                vArr.push(layedit.getContent(esedit));
                valueDisplay("es", content, vArr, 2);
            } else if (currentLanguage === "意大利语") {
                vArr.push(layedit.getContent(itedit));
                valueDisplay("it", content, vArr, 2);
            }
        });
        $(".translate3").click(function () {//简要描述
            var currentLanguage = $(this).parent().prev().find(".layui-this").text();
            var isContainsEmpty = false;
            var vArr = [];
            var textareas = $(this).parent().prev().find(".layui-show textarea");
            for (var i = 0; i < textareas.length; i++) {
                vArr.push($(textareas[i]).val());
                if ($(textareas[i]).val() == null || $.trim($(textareas[i]).val()).length == 0) {
                    isContainsEmpty = true;
                }
            }
            var content = $(this).parent().prev().find(".layui-tab-content");
            if (currentLanguage === "中文") {
                valueDisplay("cn", content, vArr, 3);
            } else if (currentLanguage === "英语") {
                valueDisplay("uk", content, vArr, 3);
            } else if (currentLanguage === "日语") {
                valueDisplay("jp", content, vArr, 3);
            } else if (currentLanguage === "德语") {
                valueDisplay("de", content, vArr, 3);
            } else if (currentLanguage === "法语") {
                valueDisplay("fr", content, vArr, 3);
            } else if (currentLanguage === "西班牙语") {
                valueDisplay("es", content, vArr, 3);
            } else if (currentLanguage === "意大利语") {
                valueDisplay("it", content, vArr, 3);
            }
            if (isContainsEmpty) {
                layer.msg('翻译标题不能为空', {icon: 5, time: 1000});
                return;
            }
        });
        function valueDisplay(language, $content, arrv, index) {
            layer.load();
            $.ajax({
                type: 'POST',
                url: '<%=request.getContextPath()%>/product/translate',
                dataType: 'json',
                data: {"data": JSON.stringify(arrv), language: language, type: index},
                success: function (data) {
                    layer.closeAll('loading');
                    if (data.success) {
                        var arr = data.data;
                        if (index == 2) {
                            layedit.setContent(cnedit, arr[0].cn);
                            layedit.setContent(ukedit, arr[0].uk);
                            layedit.setContent(jpedit, arr[0].jp);
                            layedit.setContent(deedit, arr[0].de);
                            layedit.setContent(fredit, arr[0].fr);
                            layedit.setContent(esedit, arr[0].es);
                            layedit.setContent(itedit, arr[0].it);
                        } else {
                            $content.children("div ").each(function (i, val) {
                                if (index == 1) {
                                    $(val).find("input").eq(0).val(getSingleValue(arr[0], i));
                                } else {
                                    $(val).find("textarea").eq(0).val(getSingleValue(arr[0], i));
                                }
                            });
                        }
                    } else {
                        layer.msg('翻译失败!', {icon: 5, time: 1000});

                    }
                },
                error: function (data) {
                    layer.closeAll('loading');
                    layer.msg('翻译失败!', {icon: 5, time: 1000});
                }
            });

        }

        function getSingleValue(dto, i) {
            if (i == 0) {
                return dto.cn;
            }
            if (i == 1) {
                return dto.uk;
            }
            if (i == 2) {
                return dto.jp;
            }
            if (i == 3) {
                return dto.de;
            }
            if (i == 4) {
                return dto.fr;
            }
            if (i == 5) {
                return dto.es;
            }
            if (i == 6) {
                return dto.it;
            }
            return "";
        }
    });

    /*初始化单体信息*/
    function initSingleImage() {
        layui.use('upload', function () {
            var upload = layui.upload;
            var uploadMuti = upload.render({
                accept: "images",
                acceptMime: "image/*",
                elem: "#imagePathBtn", //绑定元素
                multiple: true,
                number: 9,
                url: '<%=request.getContextPath()%>/upload/image', //上传接口
                done: function (res, index, upload) {
                    var $this = $($(this)[0].item[0]);
                    var key = $this.parent().prev().attr("val");
                    $this.parent().next().find(".pathDemo").remove();
                    $this.parent().next().append("<div class='sortableitem' style='width:100px;height:110px;margin-left:2px;float:left;'><img width='100px' height='90px' style='padding-right:5px;' src=<%=session.getAttribute("productPath")%>" + res.data + " /><i class='layui-icon delImage' style='font-size:20px;margin-left:35px;'>&#xe640;</i></div>");
                    var exVal = $("#imagePathValue").val();
                    if (exVal == null || exVal == "") {
                        exVal = res.data;
                    } else {
                        exVal = exVal + "," + res.data;
                    }
                    $("#imagePathValue").val(exVal);
                    if ($this.parent().next().find("img").length > 9) {//最多上传9张
                        $this.parent().next().find("img").each(function (i, val) {
                            if (i > 9) {
                                $(val).parent().remove();
                            }
                        });
                    }
                    $(".delImage").on("click", function () {
                        var pprent = $(this).parent().parent();
                        var ppKey = $(this).prev().attr("src").substring(55);
                        var exVal = $("#imagePathValue").val();
                        var exArr = exVal.split(",");
                        var delVals = [];
                        for (var i = 0; i < exArr.length; i++) {
                            if (exArr[i] != "" && ppKey != exArr[i]) {
                                delVals.push(exArr[i]);
                            }
                        }
                        $("#imagePathValue").val(delVals.join(","));
                        $(this).parent().remove();
                        if (pprent.find("div").length == 0) {
                            pprent.append("<img class='pathDemo' src='http://bpic.588ku.com/element_origin_min_pic/01/47/02/12574338a640011.jpg!r650' width='100' height='90' />");
                        }
                    })
                }
                , error: function () {

                }
            });
        });
        layui.use('laydate', function () {
            var laydate = layui.laydate;
            laydate.render({
                elem: '#saleStartTime' //促销开始时间
            });
            laydate.render({
                elem: '#saleEndTime' //促销结束时间
            });
        });
    }
    function singleSku() {
        $("#skuMutiDiv").css("display", "none");
        $("#skuSingleDiv").css("display", "");
        $("#priceInfo").css("display", "");
        initSingleImage();

    }
    function loadSingleImage() {
        for (var i = 0; i < imagePathArr.length; i++) {
            $("#imagePathSrc").append("<div class='sortableitem' style='width:100px;height:110px;margin-left:2px;float:left;'><img width='100px' height='90px' style='padding-right:5px;' src=<%=session.getAttribute("productPath")%>" + imagePathArr[i] + " /><i class='layui-icon delImage' style='font-size:20px;margin-left:35px;'>&#xe640;</i></div>");
            $(".delImage").on("click", function () {
                var pprent = $(this).parent().parent();
                var ppKey = $(this).prev().attr("src").substring(55);
                var exVal = $("#imagePathValue").val();
                var exArr = exVal.split(",");
                var delVals = [];
                for (var i = 0; i < exArr.length; i++) {
                    if (exArr[i] != "" && ppKey != exArr[i]) {
                        delVals.push(exArr[i]);
                    }
                }
                $("#imagePathValue").val(delVals.join(","));
                $(this).parent().remove();
                if (pprent.find("div").length == 0) {
                    pprent.append("<img class='pathDemo' src='http://bpic.588ku.com/element_origin_min_pic/01/47/02/12574338a640011.jpg!r650' width='100' height='90' />");
                }
            })
        }
    }
    function mutiSku() {
        $("#skuMutiDiv").css("display", "");
        $("#skuSingleDiv").css("display", "none");
        $("#priceInfo").css("display", "none");
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
</script>
</body>
</html>
