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
                        <div class="layui-tab-item layui-show"><input type="text" name="itemCn"
                                                                      value="${product.itemCn}"
                                                                      lay-verify="required" placeholder=""
                                                                      autocomplete="off" class="layui-input"
                                                                      maxlength="600"></div>
                        <div class="layui-tab-item"><input type="text" name="itemUk" value="${product.itemUk}"
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
                <button class="layui-btn translate" type="button" style="margin-bottom: 75px;">一键翻译</button>
            </div>

        </div>
        </div>

        <div id="skuMutiDiv">
            <blockquote class="layui-elem-quote">变种信息</blockquote>
            <div class="layui-form-item">
                <label class="layui-form-label">变种主题</label>
                <div class="layui-input-block">
                    <select name="skuMuti" lay-filter="skuMuti" id="selectMuti">
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
                           value="<fmt:formatDate value="${productVar.saleStartTime}" pattern="yyyy-MM-dd" />"
                           placeholder="" autocomplete="off" class="layui-input">
                </div>
                -
                <div class="layui-inline">
                    <input type="text" name="saleEndTime" id="saleEndTime"
                           value="<fmt:formatDate value="${productVar.saleEndTime}" pattern="yyyy-MM-dd" />"
                           placeholder="" autocomplete="off" class="layui-input">
                </div>

            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">数量</label>
                <div class="layui-inline">
                    <input type="text" name="quantity" value="${productVar.quantity}" placeholder="请输入数量"
                           autocomplete="off"
                           class="layui-input">
                </div>
            </div>

        </div>


        <blockquote class="layui-elem-quote">描述信息</blockquote>
        <div class="layui-form-item">
            <label class="layui-form-label">产品描述</label>
            <div class="layui-inline">
                <div class="layui-tab layui-tab-card" style="width: 670px;">
                    <ul class="layui-tab-title">
                        <li class="layui-this">中文</li>
                        <li>英语</li>
                        <li>日语</li>
                        <li>德语</li>
                        <li>法语</li>
                        <li>西班牙语</li>
                        <li>意大利语</li>
                    </ul>
                    <div class="layui-tab-content" style="height: 100px;">
                        <div class="layui-tab-item layui-show"><textarea name="productDescriptionCn" placeholder=""
                                                                         class="layui-textarea"
                                                                         style="resize: none"
                                                                         maxlength="600">${product.productDescriptionCn}</textarea>
                        </div>
                        <div class="layui-tab-item"><textarea name="productDescriptionUk" placeholder=""
                                                              class="layui-textarea"
                                                              style="resize: none"
                                                              maxlength="1800">${product.productDescriptionUk}</textarea>
                        </div>
                        <div class="layui-tab-item "><textarea name="productDescriptionJp" placeholder=""
                                                               class="layui-textarea"
                                                               style="resize: none"
                                                               maxlength="600">${product.productDescriptionJp}</textarea>
                        </div>
                        <div class="layui-tab-item "><textarea name="productDescriptionDe" placeholder=""
                                                               class="layui-textarea"
                                                               style="resize: none"
                                                               maxlength="1800">${product.productDescriptionDe}</textarea>
                        </div>
                        <div class="layui-tab-item "><textarea name="productDescriptionFr" placeholder=""
                                                               class="layui-textarea"
                                                               style="resize: none"
                                                               maxlength="1800">${product.productDescriptionFr}</textarea>
                        </div>
                        <div class="layui-tab-item "><textarea name="productDescriptionEs" placeholder=""
                                                               class="layui-textarea"
                                                               style="resize: none"
                                                               maxlength="1800">${product.productDescriptionEs}</textarea>
                        </div>
                        <div class="layui-tab-item "><textarea name="productDescriptionIt" placeholder=""
                                                               class="layui-textarea"
                                                               style="resize: none"
                                                               maxlength="1800">${product.productDescriptionIt}</textarea>
                        </div>
                    </div>
                </div>
            </div>
            <div class="layui-inline">
                <button class="layui-btn translate" type="button" style="margin-bottom: 125px;">一键翻译</button>
            </div>
        </div>

        <div class="layui-form-item" style="width: 1000px;">
            <label class="layui-form-label">简要描述</label>
            <div class="layui-inline">
                <div class="layui-tab layui-tab-card" style="width: 670px;">
                    <ul class="layui-tab-title">
                        <li class="layui-this">中文</li>
                        <li>英语</li>
                        <li>日语</li>
                        <li>德语</li>
                        <li>法语</li>
                        <li>西班牙语</li>
                        <li>意大利语</li>
                    </ul>
                    <div class="layui-tab-content" style="height: 150px;">
                        <div class="layui-tab-item layui-show" id="pointsCn">
                            <div class="layui-inline">
                                <input type="text" name="bulletPoint1" style="width: 300px" value="${pointsCn[0]}"
                                       lay-verify="required" placeholder="" autocomplete="off" class="layui-input"
                                       maxlength="150">
                            </div>
                            <div class="layui-inline">
                                <input type="text" name="bulletPoint2" style="width: 300px" value="${pointsCn[1]}"
                                       lay-verify="required" placeholder="" autocomplete="off" class="layui-input"
                                       maxlength="150">
                            </div>
                            <div class="layui-inline">
                                <input type="text" name="bulletPoint3" value="${pointsCn[2]}" style="width: 300px"
                                       lay-verify="required" placeholder="" autocomplete="off" class="layui-input"
                                       maxlength="150">
                            </div>
                            <div class="layui-inline">
                                <input type="text" name="bulletPoint4" value="${pointsCn[3]}" style="width: 300px"
                                       lay-verify="required" placeholder="" autocomplete="off" class="layui-input"
                                       maxlength="150">
                            </div>

                            <div class="layui-inline">
                                <input type="text" name="bulletPoint5" value="${pointsCn[4]}" style="width: 300px"
                                       lay-verify="required" placeholder="" autocomplete="off" class="layui-input"
                                       maxlength="150">
                            </div>
                        </div>
                        <div class="layui-tab-item" id="pointsUk">
                            <div class="layui-inline">
                                <input type="text" name="bulletPoint1" style="width: 300px" value="${pointsUk[0]}"
                                       lay-verify="required" placeholder="" autocomplete="off" class="layui-input"
                                       maxlength="450">
                            </div>
                            <div class="layui-inline">
                                <input type="text" name="bulletPoint2" style="width: 300px" value="${pointsUk[1]}"
                                       lay-verify="required" placeholder="" autocomplete="off" class="layui-input"
                                       maxlength="450">
                            </div>

                            <div class="layui-inline">
                                <input type="text" name="bulletPoint3" value="${pointsUk[2]}" style="width: 300px"
                                       lay-verify="required" placeholder="" autocomplete="off" class="layui-input"
                                       maxlength="450">
                            </div>
                            <div class="layui-inline">
                                <input type="text" name="bulletPoint4" value="${pointsUk[3]}" style="width: 300px"
                                       lay-verify="required" placeholder="" autocomplete="off" class="layui-input"
                                       maxlength="450">
                            </div>

                            <div class="layui-inline">
                                <input type="text" name="bulletPoint5" value="${pointsUk[4]}" style="width: 300px"
                                       lay-verify="required" placeholder="" autocomplete="off" class="layui-input"
                                       maxlength="450">
                            </div>
                        </div>
                        <div class="layui-tab-item " id="pointsJp">
                            <div class="layui-inline">
                                <input type="text" name="bulletPoint1" style="width: 300px" value="${pointsJp[0]}"
                                       lay-verify="required" placeholder="" autocomplete="off" class="layui-input"
                                       maxlength="150">
                            </div>
                            <div class="layui-inline">
                                <input type="text" name="bulletPoint2" style="width: 300px" value="${pointsJp[1]}"
                                       lay-verify="required" placeholder="" autocomplete="off" class="layui-input"
                                       maxlength="150">
                            </div>

                            <div class="layui-inline">
                                <input type="text" name="bulletPoint3" value="${pointsJp[2]}" style="width: 300px"
                                       lay-verify="required" placeholder="" autocomplete="off" class="layui-input"
                                       maxlength="150">
                            </div>
                            <div class="layui-inline">
                                <input type="text" name="bulletPoint4" value="${pointsJp[3]}" style="width: 300px"
                                       lay-verify="required" placeholder="" autocomplete="off" class="layui-input"
                                       maxlength="150">
                            </div>

                            <div class="layui-inline">
                                <input type="text" name="bulletPoint5" value="${pointsJp[4]}" style="width: 300px"
                                       lay-verify="required" placeholder="" autocomplete="off" class="layui-input"
                                       maxlength="150">
                            </div>
                        </div>
                        <div class="layui-tab-item " id="pointsDe">
                            <div class="layui-inline">
                                <input type="text" name="bulletPoint1" style="width: 300px" value="${pointsDe[0]}"
                                       lay-verify="required" placeholder="" autocomplete="off" class="layui-input"
                                       maxlength="450">
                            </div>
                            <div class="layui-inline">
                                <input type="text" name="bulletPoint2" style="width: 300px" value="${pointsDe[1]}"
                                       lay-verify="required" placeholder="" autocomplete="off" class="layui-input"
                                       maxlength="450">
                            </div>

                            <div class="layui-inline">
                                <input type="text" name="bulletPoint3" value="${pointsDe[2]}" style="width: 300px"
                                       lay-verify="required" placeholder="" autocomplete="off" class="layui-input"
                                       maxlength="450">
                            </div>
                            <div class="layui-inline">
                                <input type="text" name="bulletPoint4" value="${pointsDe[3]}" style="width: 300px"
                                       lay-verify="required" placeholder="" autocomplete="off" class="layui-input"
                                       maxlength="450">
                            </div>

                            <div class="layui-inline">
                                <input type="text" name="bulletPoint5" value="${pointsDe[4]}" style="width: 300px"
                                       lay-verify="required" placeholder="" autocomplete="off" class="layui-input"
                                       maxlength="450">
                            </div>
                        </div>
                        <div class="layui-tab-item" id="pointsFr">
                            <div class="layui-inline">
                                <input type="text" name="bulletPoint1" style="width: 300px" value="${pointsFr[0]}"
                                       lay-verify="required" placeholder="" autocomplete="off" class="layui-input"
                                       maxlength="450">
                            </div>
                            <div class="layui-inline">
                                <input type="text" name="bulletPoint2" style="width: 300px" value="${pointsFr[1]}"
                                       lay-verify="required" placeholder="" autocomplete="off" class="layui-input"
                                       maxlength="450">
                            </div>

                            <div class="layui-inline">
                                <input type="text" name="bulletPoint3" value="${pointsFr[2]}" style="width: 300px"
                                       lay-verify="required" placeholder="" autocomplete="off" class="layui-input"
                                       maxlength="450">
                            </div>
                            <div class="layui-inline">
                                <input type="text" name="bulletPoint4" value="${pointsFr[3]}" style="width: 300px"
                                       lay-verify="required" placeholder="" autocomplete="off" class="layui-input"
                                       maxlength="450">
                            </div>

                            <div class="layui-inline">
                                <input type="text" name="bulletPoint5" value="${pointsFr[4]}" style="width: 300px"
                                       lay-verify="required" placeholder="" autocomplete="off" class="layui-input"
                                       maxlength="450">
                            </div>
                        </div>
                        <div class="layui-tab-item" id="pointsEs">
                            <div class="layui-inline">
                                <input type="text" name="bulletPoint1" style="width: 300px" value="${pointsEs[0]}"
                                       lay-verify="required" placeholder="" autocomplete="off" class="layui-input"
                                       maxlength="450">
                            </div>
                            <div class="layui-inline">
                                <input type="text" name="bulletPoint2" style="width: 300px" value="${pointsEs[1]}"
                                       lay-verify="required" placeholder="" autocomplete="off" class="layui-input"
                                       maxlength="450">
                            </div>

                            <div class="layui-inline">
                                <input type="text" name="bulletPoint3" value="${pointsEs[2]}" style="width: 300px"
                                       lay-verify="required" placeholder="" autocomplete="off" class="layui-input"
                                       maxlength="450">
                            </div>
                            <div class="layui-inline">
                                <input type="text" name="bulletPoint4" value="${pointsEs[3]}" style="width: 300px"
                                       lay-verify="required" placeholder="" autocomplete="off" class="layui-input"
                                       maxlength="450">
                            </div>

                            <div class="layui-inline">
                                <input type="text" name="bulletPoint5" value="${pointsEs[4]}" style="width: 300px"
                                       lay-verify="required" placeholder="" autocomplete="off" class="layui-input"
                                       maxlength="450">
                            </div>
                        </div>
                        <div class="layui-tab-item" id="pointsIt">
                            <div class="layui-inline">
                                <input type="text" name="pointsIt0" style="width: 300px" value="${pointsIt[0]}"
                                       lay-verify="required" placeholder="" autocomplete="off" class="layui-input"
                                       maxlength="450">
                            </div>
                            <div class="layui-inline">
                                <input type="text" name="pointsIt1" style="width: 300px" value="${pointsIt[1]}"
                                       lay-verify="required" placeholder="" autocomplete="off" class="layui-input"
                                       maxlength="450">
                            </div>

                            <div class="layui-inline">
                                <input type="text" name="pointsIt2" value="${pointsIt[2]}" style="width: 300px"
                                       lay-verify="required" placeholder="" autocomplete="off" class="layui-input"
                                       maxlength="450">
                            </div>
                            <div class="layui-inline">
                                <input type="text" name="pointsIt3" value="${pointsIt[3]}" style="width: 300px"
                                       lay-verify="required" placeholder="" autocomplete="off" class="layui-input"
                                       maxlength="450">
                            </div>

                            <div class="layui-inline">
                                <input type="text" name="pointsIt4" value="${pointsIt[4]}" style="width: 300px"
                                       lay-verify="required" placeholder="" autocomplete="off" class="layui-input"
                                       maxlength="450">
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="layui-inline">
                <button class="layui-btn translate" type="button" style="margin-bottom: 175px;">一键翻译</button>
            </div>
        </div>

        <blockquote class="layui-elem-quote">关键词信息</blockquote>
        <div class="layui-form-item">
            <label class="layui-form-label">关键词</label>
            <div class="layui-inline">
                <div class="layui-tab layui-tab-card" style="width: 670px;">
                    <ul class="layui-tab-title">
                        <li class="layui-this">中文</li>
                        <li>英语</li>
                        <li>日语</li>
                        <li>德语</li>
                        <li>法语</li>
                        <li>西班牙语</li>
                        <li>意大利语</li>
                    </ul>
                    <div class="layui-tab-content" style="height: 150px;">
                        <div class="layui-tab-item layui-show" id="keywordsCn">
                            <div class="layui-inline">
                                <input type="text" name="keywordsCn0" style="width: 300px" value="${keywordsCn[0]}"
                                       lay-verify="required" placeholder="" autocomplete="off" class="layui-input"
                                       maxlength="15">
                            </div>
                            <div class="layui-inline">
                                <input type="text" name="keywordsCn1" style="width: 300px" value="${keywordsCn[1]}"
                                       lay-verify="required" placeholder="" autocomplete="off" class="layui-input"
                                       maxlength="15">
                            </div>
                            <div class="layui-inline">
                                <input type="text" name="keywordsCn2" value="${keywordsCn[2]}" style="width: 300px"
                                       lay-verify="required" placeholder="" autocomplete="off" class="layui-input"
                                       maxlength="15">
                            </div>
                            <div class="layui-inline">
                                <input type="text" name="keywordsCn3" value="${keywordsCn[3]}" style="width: 300px"
                                       lay-verify="required" placeholder="" autocomplete="off" class="layui-input"
                                       maxlength="15">
                            </div>
                            <div class="layui-inline">
                                <input type="text" name="keywordsCn4" value="${keywordsCn[4]}" style="width: 300px"
                                       lay-verify="required" placeholder="" autocomplete="off" class="layui-input"
                                       maxlength="15">
                            </div>
                        </div>
                        <div class="layui-tab-item" id="keywordsUk">
                            <div class="layui-inline">
                                <input type="text" name="keywordsUk0" style="width: 300px" value="${keywordsUk[0]}"
                                       lay-verify="required" placeholder="" autocomplete="off" class="layui-input"
                                       maxlength="45">
                            </div>
                            <div class="layui-inline">
                                <input type="text" name="keywordsUk1" style="width: 300px" value="${keywordsUk[1]}"
                                       lay-verify="required" placeholder="" autocomplete="off" class="layui-input"
                                       maxlength="45">
                            </div>
                            <div class="layui-inline">
                                <input type="text" name="keywordsUk2" value="${keywordsUk[2]}" style="width: 300px"
                                       lay-verify="required" placeholder="" autocomplete="off" class="layui-input"
                                       maxlength="45">
                            </div>
                            <div class="layui-inline">
                                <input type="text" name="keywordsUk3" value="${keywordsUk[3]}" style="width: 300px"
                                       lay-verify="required" placeholder="" autocomplete="off" class="layui-input"
                                       maxlength="45">
                            </div>
                            <div class="layui-inline">
                                <input type="text" name="keywordsUk4" value="${keywordsUk[4]}" style="width: 300px"
                                       lay-verify="required" placeholder="" autocomplete="off" class="layui-input"
                                       maxlength="45">
                            </div>
                        </div>
                        <div class="layui-tab-item" id="keywordsJp">
                            <div class="layui-inline">
                                <input type="text" name="keywordsJp0" style="width: 300px" value="${keywordsJp[0]}"
                                       lay-verify="required" placeholder="" autocomplete="off" class="layui-input"
                                       maxlength="15">
                            </div>
                            <div class="layui-inline">
                                <input type="text" name="keywordsJp1" style="width: 300px" value="${keywordsJp[1]}"
                                       lay-verify="required" placeholder="" autocomplete="off" class="layui-input"
                                       maxlength="15">
                            </div>
                            <div class="layui-inline">
                                <input type="text" name="keywordsJp2" value="${keywordsJp[2]}" style="width: 300px"
                                       lay-verify="required" placeholder="" autocomplete="off" class="layui-input"
                                       maxlength="15">
                            </div>
                            <div class="layui-inline">
                                <input type="text" name="keywordsJp3" value="${keywordsJp[3]}" style="width: 300px"
                                       lay-verify="required" placeholder="" autocomplete="off" class="layui-input"
                                       maxlength="15">
                            </div>
                            <div class="layui-inline">
                                <input type="text" name="keywordsJp4" value="${keywordsJp[4]}" style="width: 300px"
                                       lay-verify="required" placeholder="" autocomplete="off" class="layui-input"
                                       maxlength="15">
                            </div>
                        </div>
                        <div class="layui-tab-item" id="keywordsDe">
                            <div class="layui-inline">
                                <input type="text" name="keywordsUk0" style="width: 300px" value="${keywordsDe[0]}"
                                       lay-verify="required" placeholder="" autocomplete="off" class="layui-input"
                                       maxlength="45">
                            </div>
                            <div class="layui-inline">
                                <input type="text" name="keywordsDe1" style="width: 300px" value="${keywordsDe[1]}"
                                       lay-verify="required" placeholder="" autocomplete="off" class="layui-input"
                                       maxlength="45">
                            </div>
                            <div class="layui-inline">
                                <input type="text" name="keywordsDe2" value="${keywordsDe[2]}" style="width: 300px"
                                       lay-verify="required" placeholder="" autocomplete="off" class="layui-input"
                                       maxlength="45">
                            </div>
                            <div class="layui-inline">
                                <input type="text" name="keywordsDe3" value="${keywordsDe[3]}" style="width: 300px"
                                       lay-verify="required" placeholder="" autocomplete="off" class="layui-input"
                                       maxlength="45">
                            </div>
                            <div class="layui-inline">
                                <input type="text" name="keywordsDe4" value="${keywordsDe[4]}" style="width: 300px"
                                       lay-verify="required" placeholder="" autocomplete="off" class="layui-input"
                                       maxlength="45">
                            </div>
                        </div>
                        <div class="layui-tab-item" id="keywordsFr">
                            <div class="layui-inline">
                                <input type="text" name="keywordsFr0" style="width: 300px" value="${keywordsFr[0]}"
                                       lay-verify="required" placeholder="" autocomplete="off" class="layui-input"
                                       maxlength="45">
                            </div>
                            <div class="layui-inline">
                                <input type="text" name="keywordsFr1" style="width: 300px" value="${keywordsFr[1]}"
                                       lay-verify="required" placeholder="" autocomplete="off" class="layui-input"
                                       maxlength="45">
                            </div>
                            <div class="layui-inline">
                                <input type="text" name="keywordsFr2" value="${keywordsFr[2]}" style="width: 300px"
                                       lay-verify="required" placeholder="" autocomplete="off" class="layui-input"
                                       maxlength="45">
                            </div>
                            <div class="layui-inline">
                                <input type="text" name="keywordsFr3" value="${keywordsFr[3]}" style="width: 300px"
                                       lay-verify="required" placeholder="" autocomplete="off" class="layui-input"
                                       maxlength="45">
                            </div>
                            <div class="layui-inline">
                                <input type="text" name="keywordsFr4" value="${keywordsFr[4]}" style="width: 300px"
                                       lay-verify="required" placeholder="" autocomplete="off" class="layui-input"
                                       maxlength="45">
                            </div>
                        </div>
                        <div class="layui-tab-item" id="keywordsEs">
                            <div class="layui-inline">
                                <input type="text" name="keywordsEs0" style="width: 300px" value="${keywordsEs[0]}"
                                       lay-verify="required" placeholder="" autocomplete="off" class="layui-input"
                                       maxlength="45">
                            </div>
                            <div class="layui-inline">
                                <input type="text" name="keywordsEs1" style="width: 300px" value="${keywordsEs[1]}"
                                       lay-verify="required" placeholder="" autocomplete="off" class="layui-input"
                                       maxlength="45">
                            </div>
                            <div class="layui-inline">
                                <input type="text" name="keywordsEs2" value="${keywordsEs[2]}" style="width: 300px"
                                       lay-verify="required" placeholder="" autocomplete="off" class="layui-input"
                                       maxlength="45">
                            </div>
                            <div class="layui-inline">
                                <input type="text" name="keywordsEs3" value="${keywordsEs[3]}" style="width: 300px"
                                       lay-verify="required" placeholder="" autocomplete="off" class="layui-input"
                                       maxlength="45">
                            </div>
                            <div class="layui-inline">
                                <input type="text" name="keywordsEs4" value="${keywordsEs[4]}" style="width: 300px"
                                       lay-verify="required" placeholder="" autocomplete="off" class="layui-input"
                                       maxlength="45">
                            </div>
                        </div>
                        <div class="layui-tab-item" id="keywordsIt">
                            <div class="layui-inline">
                                <input type="text" name="keywordsUk0" style="width: 300px" value="${keywordsIt[0]}"
                                       lay-verify="required" placeholder="" autocomplete="off" class="layui-input"
                                       maxlength="45">
                            </div>
                            <div class="layui-inline">
                                <input type="text" name="keywordsIt1" style="width: 300px" value="${keywordsIt[1]}"
                                       lay-verify="required" placeholder="" autocomplete="off" class="layui-input"
                                       maxlength="45">
                            </div>
                            <div class="layui-inline">
                                <input type="text" name="keywordsIt2" value="${keywordsIt[2]}" style="width: 300px"
                                       lay-verify="required" placeholder="" autocomplete="off" class="layui-input"
                                       maxlength="45">
                            </div>
                            <div class="layui-inline">
                                <input type="text" name="keywordsIt3" value="${keywordsIt[3]}" style="width: 300px"
                                       lay-verify="required" placeholder="" autocomplete="off" class="layui-input"
                                       maxlength="45">
                            </div>
                            <div class="layui-inline">
                                <input type="text" name="keywordsIt4" value="${keywordsIt[4]}" style="width: 300px"
                                       lay-verify="required" placeholder="" autocomplete="off" class="layui-input"
                                       maxlength="45">
                            </div>
                        </div>

                    </div>
                </div>
            </div>
            <div class="layui-inline">
                <button class="layui-btn translate" type="button" style="margin-bottom: 175px;">一键翻译</button>
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

        var skuArr = [];
        //singleSku();
        //日期
        initSku('${product}')


        var skuType = '${skuType}';


        function initSku() {

        }


        layui.use('element', function () {
            var element = layui.element;

            //…
        });

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
            form.on('radio(skuType)', function (data) {//切换变体类型

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
                $("#selectMuti").val("Color");
                form.render("select", "skuMuti");
                selectValue = vars[0].variationType;
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
                            refrehSKuPath();
                            form.render(null, "skuMutiPath");
                            form.render(null, "skuTable");
                            $this.parent().parent().find("input").val('');
                        }

                    }
                });


                for (var i = 0; i < vars.length; i++) {

                    var vvType = vars[i].variationType;
                    console.log(vvType);
                    if (vvType != null && vvType != 'undefined') {
                        console.log(vars[i]);
                        if (vvType = 'Color') {
                            $("#skuRender button").parent().prev().find('input').val(vars[i].colorMap);

                        }

                        $(".skuBtn").click();
                        form.render(null, 'skuRender');
                        form.render('checkbox');

                    }

                }

                var index = 0;

                for (var i = 0; i < vars.length; i++) {
                    var vvType = vars[i].variationType;
                    if (vvType != null && vvType != 'undefined') {

                        var ttImages = (vars[i].mainPath + "," + vars[i].attachPath).split(",");
                        var ttHtml = '';
                        for (var j = 0; j < ttImages.length; j++) {
                            ttHtml += "<div style='width:100px;height:110px;margin-left:2px;float:left;'><img width='100px' height='90px' style='padding-right:5px;' src=<%=request.getContextPath()%>/upload/" + ttImages[j] + " /><i class='layui-icon delImage' style='font-size:20px;margin-left:35px;'>&#xe640;</i></div>";
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

                    var ppKey = $(this).prev().attr("src").substring(8);
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
                $("input[name='skuType']").eq(0).attr("checked", 'checked');
                $("input[name='skuType']").eq(1).removeAttr("checked");


            }

            form.render("radio");


            form.on('checkbox(skuMutiCheck)', function (data) {//切换变体类型

                refrehSKuPath();
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
                            refrehSKuPath();
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
                var skuTypeA = data.field.skuType;
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
                if (skuTypeA == 1) {//单体

                } else {//多变中
                    if (selectValue == null || selectValue == "") {
                        layer.msg("请选择变种主题！", {icon: 5, time: 1000});
                        return false;
                    }
                    var skuVar = [];
                    var isItemImags = true;
                    $("#skuTable tbody tr").each(function (i, val) {
                        var item = {};
                        var itemImgs = $(val).eq(0).find(".trOtherPath").val().split(",");
                        if (itemImgs.length < 1) {
                            isItemImags = false;
                        }
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
                        } else if (secondValue == "Size") {
                            item["sizeMap"] = firstValue;
                            item["sizeName"] = firstValue;
                        } else if (secondValue == "material") {
                            item["materialType"] = firstValue;
                        } else if (secondValue == "size-material") {
                            item["sizeMap"] = firstValue;
                            item["sizeName"] = firstValue;
                            item["materialType"] = secondValue;
                        } else if (secondValue == "color-material") {
                            item["colorName"] = firstValue;
                            item["colorMap"] = firstValue;
                            item["materialType"] = secondValue;
                        } else if (secondValue == "itempackagequantity") {
                            item["itemPackageQuantity"] = firstValue;
                        } else if (secondValue == "color-itempackagequantity") {
                            item["colorName"] = firstValue;
                            item["colorMap"] = firstValue;
                            item["itemPackageQuantity"] = secondValue;
                        } else if (secondValue == "itempackagequantity-size") {
                            item["itemPackageQuantity"] = firstValue;
                            item["sizeMap"] = secondValue;
                            item["sizeName"] = secondValue;
                        } else if (secondValue == "itempackagequantity-material") {
                            item["itempackagequantity"] = firstValue;
                            item["materialType"] = secondValue;
                        }
                        skuVar.push(item);
                    });
                    if (!isItemImags) {
                        layer.msg("请上传变体图片", {icon: 5, time: 1000});
                        return false;
                    }
                }
                data.field["vars"] = JSON.stringify(skuVar);
                var url = '<%=request.getContextPath()%>/product/claim/save';
                $.ajax({
                    type: 'POST',
                    url: url,
                    dataType: 'json',
                    data: data.field
                    ,
                    success: function (data) {
                        layer.msg(data.msg, {icon: 6, time: 1000});
                        if (data.success) {
                            layer.msg("保存成功", {icon: 6, time: 1000});
                            setTimeout(function () {
                                location.href = '<%=request.getContextPath()%>/assistant/index/product/product_claim_list.jsp';
                            }, 500);

                        } else {
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
            var arr = [];
            var skuCheckRow = $(".skuCheckbox");
            if (skuCheckRow == null || skuCheckRow.length == 0) {
                return;
            }

            console.log(111);
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
            initSKuPath(null, null);

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

        }

        function getTR(isSingle, first, second) {
            var sku = $("input[name='sku']").val() + "-" + (isSingle ? first : (first + "-" + second));
            return "<tr val=" + (isSingle ? first : (first + ":" + second)) + ">" +
                    "<td><input type='hidden' class='trMainPath' /> <input type='hidden' class='trOtherPath' /> <input type='text' value='" + sku + "'  lay-verify='required'  autocomplete='off' class='layui-input'></td>" +
                    (isSingle ? ("<td class='skuVarType'>" + first + "</td>") : ("<td class='skuVarType'>" + first + "</td><td>" + second + "</td>"))
                    +
                    "<td><input type='text' lay-verify='required'  autocomplete='off' class='layui-input price'></td>" +
                    "<td><input type='text'  autocomplete='off' class='layui-input salePrice'></td>" +
                    "<td> <div class='layui-inline'>" +
                    "<input type='text'  autocomplete='off' class='layui-input saleStart'>" +
                    "</div>" +
                    "-" +
                    "<div class='layui-inline'>" +
                    "<input type='text' autocomplete='off' class='layui-input saleEnd'>" +
                    "</div>" +
                    "</td>" +
                    "<td><input type='text' lay-verify='required'  autocomplete='off' class='layui-input quantity'></td>" +
                    "</tr>";
        }

        //监听parentSKU值变化
        $("input[name='sku']").bind("input propertychange change", function (event) {
            $("#skuTable tbody tr").each(function (i, val) {
                var text = $(val).find("td").eq(0).find("input[type='text']").val();
                $(val).find("td").eq(0).find("input[type='text']").val($("input[name='sku']").val() + text.substring(text.indexOf("-")))
            });
        });

        function getCgroup(isSingle) {
            return "<col width=\"200\">" +
                    (isSingle ? ("<col width=\"100\">") : ("<col width=\"100\"><col width=\"100\">")) +
                    " <col width=\"50\">" +
                    "  <col width=\"50\">" +
                    "  <col width=\"300\">" +
                    "  <col width=\"100\">";
        }

        function getThead(desc) {
            return "<tr> <th>sku</th>" + desc + "<th>价格</th> <th>促销价</th> <th>促销时间</th> <th>数量</th></tr> ";
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
                    "            <label class=\"layui-form-label\">变种图片</label>\n" +
                    "</div>";

            dom += "<div class=\"layui-form-item\">" +
                    "<label class=\"layui-form-label\">变种参数</label>" +
                    "<div class=\"layui-input-block\">" +
                    "<table class=\"layui-table\" id=\"skuTable\" lay-filter='skuTable'>" +
                    " <colgroup>" +

                    "   </colgroup><thead></thead> <tbody>" +

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
                dom += "<div class=\"layui-input-block\" val=" + (arr[i].substring(arr[i].indexOf(":") + 1)) + ">" +
                        "    <div class='layui-inline layui-bg-gray' style='margin-top: 10px;'>变种属性    " + arr[i] + "</div>" +
                        "            </div>" +
                            /*"            <div class=\"layui-input-block\">\n" +
                             "                <button id=" + id + " type=\"button\" class=\"layui-btn skuMainPath\" >\n" +
                             "            <i class=\"layui-icon\">&#xe67c;</i>上传主图\n" +
                             "        </button>" +
                             "            </div>\n" +*/


                            /*  "            <div class=\"layui-input-block\" style='margin-top: 5px'>" +
                             "<img src='http://bpic.588ku.com/element_origin_min_pic/01/47/02/12574338a640011.jpg!r650' width='100' height='90' />" +
                             "                     </div>" +*/


                        "            <div class=\"layui-input-block\" style='margin-top: 10px;'>\n" +
                        "                <button type=\"button\" id=" + sid + " class=\"layui-btn skuOtherPath\"  >\n" +
                        "            <i class=\"layui-icon\">&#xe67c;</i>上传附图\n" +
                        "        </button>" +
                        "            </div>" +
                        "            <div class=\"layui-input-block\" style='margin-top: 5px;height: 110px;'>" +
                        "<img class=\"pathDemo\" src='http://bpic.588ku.com/element_origin_min_pic/01/47/02/12574338a640011.jpg!r650' width='100' height='90' />" +
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
            $("#skuMutiDiv").css("display", "none");
            $("#skuSingleDiv").css("display", "");
            $("#priceInfo").css("display", "");
        }

        function mutiSku() {
            $("#skuMutiDiv").css("display", "");
            $("#skuSingleDiv").css("display", "none");
            $("#priceInfo").css("display", "none");
        }


        function initSKuPath(id, sid) {

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
                        $this.parent().next().html("<div style='width:100px;height:110px;'><img width='100px' height='90px' src=<%=request.getContextPath()%>/upload/" + res.data + " /><i class='layui-icon delImageMain' style='font-size:20px;margin-left:35px;'>&#xe640;</i>  </div>");
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
                        })

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
                        console.log($this);
                        $this.parent().next().append("<div style='width:100px;height:110px;margin-left:2px;float:left;'><img width='100px' height='90px' style='padding-right:5px;' src=<%=request.getContextPath()%>/upload/" + res.data + " /><i class='layui-icon delImage' style='font-size:20px;margin-left:35px;'>&#xe640;</i></div>");

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
                            var ppKey = $(this).prev().attr("src").substring(8);
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
            var arr = [];
            $("#" + id).find("input").each(function (i, val) {
                arr.push($(val).val());
            });
            return JSON.stringify(arr);
        }

        //一键翻译
        $(".translate").click(function () {

            var currentLanguage = $(this).parent().prev().find(".layui-this").text();
            var inputs = $(this).parent().prev().find(".layui-show input");
            var vArr = [];

            var isContainsEmpty = false;
            if (inputs.length > 0) {
                for (var i = 0; i < inputs.length; i++) {

                    vArr.push($(inputs[i]).val());
                    if ($(inputs[i]).val() == null || $.trim($(inputs[i]).val()).length == 0) {
                        isContainsEmpty = true;
                    }
                }
            } else {
                var areas = $(this).parent().prev().find(".layui-show textarea");
                for (var i = 0; i < areas.length; i++) {
                    vArr.push($(areas[i]).val());
                    if ($(areas[i]).val() == null || $.trim($(areas[i]).val()).length == 0) {
                        isContainsEmpty = true;
                    }
                }
            }
            if (isContainsEmpty) {
                layer.msg('翻译词条不能为空', {icon: 5, time: 1000});
                return;
            }

            var content = $(this).parent().prev().find(".layui-tab-content");
            if (currentLanguage === "中文") {
                valueDisplay("cn", content, vArr);
            } else if (currentLanguage === "英语") {
                valueDisplay("uk", content, vArr);
            } else if (currentLanguage === "日语") {
                valueDisplay("jp", content, vArr);
            } else if (currentLanguage === "德语") {
                valueDisplay("de", content, vArr);
            } else if (currentLanguage === "法语") {
                valueDisplay("fr", content, vArr);
            } else if (currentLanguage === "西班牙语") {
                valueDisplay("es", content, vArr);
            } else if (currentLanguage === "意大利语") {
                valueDisplay("it", content, vArr);
            }

        });

        function valueDisplay(language, $content, arrv) {

            $.ajax({
                type: 'POST',
                url: '<%=request.getContextPath()%>/product/translate',
                dataType: 'json',
                data: {"data": JSON.stringify(arrv), language: language},
                success: function (data) {

                    if (data.success) {
                        var arr = data.data;
                        $content.children("div ").each(function (i, val) {
                            var isSingle = arr.length == 1, isContainsTextarea = $(val).find("textarea").length > 0;
                            if (isSingle) {
                                if (isContainsTextarea) {
                                    $(val).find("textarea").eq(0).val(getSingleValue(arr[0], i));
                                } else {
                                    $(val).find("input").eq(0).val(getSingleValue(arr[0], i));
                                }
                            } else {
                                $(val).find("div input").eq(0).val(getSingleValue(arr[0], i));
                                $(val).find("div input").eq(1).val(getSingleValue(arr[1], i));
                                $(val).find("div input").eq(2).val(getSingleValue(arr[2], i));
                                $(val).find("div input").eq(3).val(getSingleValue(arr[3], i));
                                $(val).find("div input").eq(4).val(getSingleValue(arr[4], i));
                            }
                        });
                    } else {
                        layer.msg('翻译失败!', {icon: 5, time: 1000});

                    }
                },
                error: function (data) {
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
    ;

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
