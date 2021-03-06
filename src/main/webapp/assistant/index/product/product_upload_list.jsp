﻿<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8">
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no"/>
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
          href="<%=request.getContextPath()%>/assistant/static/layui/css/layui.css"/>
    <!--[if IE 6]>
    <script type="text/javascript"
            src="<%=request.getContextPath()%>/assistant/lib/DD_belatedPNG_0.0.8a-min.js"></script>
    <script>DD_belatedPNG.fix('*');</script>
    <![endif]-->
    <title>发布产品</title>
</head>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 产品管理 <span
        class="c-gray en">&gt;</span> 发布产品 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px"
                                              id="refresh"
                                              href="javascript:location.replace(location.href);" title="刷新"><i
        class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
    <form id="productForm" class="form form-horizontal">
        <div class="row cl">
            <label class="form-label col-xs-1 col-sm-1">国家：</label>
            <div class="formControls col-xs-2 col-sm-2">
                <select id="language" name="language" class="select" style="height: 32px">
                    <option value="">请选择</option>
                </select>
            </div>
            <label class="form-label col-xs-1 col-sm-1">标题：</label>
            <div class="formControls col-xs-2 col-sm-2">
                <input type="text" name="name" placeholder=" " class="input-text">
            </div>
            <label class="form-label col-xs-1 col-sm-1">状态：</label>
            <div class="formControls col-xs-2 col-sm-2">
                <select id="pStatus" name="pStatus" class="select" style="height: 32px">
                    <option value="">请选择</option>
                    <option value="0">未发布</option>
                    <option value="3">发布中</option>
                    <option value="2">发布成功</option>
                    <option value="1">发布失败</option>
                </select>
            </div>

            <div class="formControls col-xs-1 col-sm-1">
                <button id="search" class="btn btn-success" type="button"><i class="Hui-iconfont">&#xe665;</i>
                </button>
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-1 col-sm-1">创建人：</label>
            <div class="formControls col-xs-2 col-sm-2">
                <select id="salesMan" name="salesMan" class="select" style="height: 32px">
                    <option value="">请选择</option>
                </select>
            </div>
            <input id="roleId" type="hidden" value="${sessionScope.user.ROLE_ID}">
            <input id="companyId" type="hidden" value="${sessionScope.user.USER_COMPANY}">
            <label class="form-label col-xs-1 col-sm-1">SKU：</label>
            <div class="formControls col-xs-2 col-sm-2">
                <input type="text" name="sku" placeholder=" " class="input-text">
            </div>
        </div>

        <div class="row cl">
            <label class="form-label col-xs-1 col-sm-1">店铺：</label>
            <div class="formControls col-xs-2 col-sm-2">

                    <select required name="shopId" id="shopId" lay-filter="shop" class="select" style="height: 32px;">

                    </select>
            </div>
        </div>
    </form>
    <div class="mt-20">
        <%--<div id="btn-div" class="row text-c">
            <a href="javascript:;" onclick="reloadTable(0)" class="btn btn-success radius">全部</a>
            <a href="javascript:;" onclick="reloadTable(1)" class="btn btn-default radius">未认领</a>
            <a href="javascript:;" onclick="reloadTable(2)" class="btn btn-default radius">已认领</a>
        </div>--%>
        <div class="cl pd-5 bg-1 bk-gray" id="count-div"><span class="l">
            <button class="layui-btn  layui-btn-sm" onclick="claimProduct(0)">批量发布</button>
            <button class="layui-btn  layui-btn-xs" onclick="claimProduct(1)">同步价格</button>
            <button class="layui-btn  layui-btn-xs" onclick="claimProduct(2)">同步库存</button>
            <button class="layui-btn  layui-btn-xs" onclick="claimProduct(3)">同步关系</button>
            <button class="layui-btn  layui-btn-xs" onclick="claimProduct(4)">同步图片</button>
            </span><span style="color: orange;float: left;margin-left: 10px;margin-top: 5px;">注意:同一个店铺一次最多可以发布五个国家，等发布成功后再点击发布剩余的国家产品。</span>
        </div>
        <table id="productTable" class="table table-border table-bordered table-bg table-hover mt-10">
            <thead>
            <tr class="text-c">
                <th width="25"><input type="checkbox" value="" name=""></th>
                <th width="100">产品图</th>
                <th width="150">标题</th>
                <th width="60">国家</th>
                <th width="60">店铺</th>
                <th width="100">SKU</th>
                <th width="60">售价</th>
                <th width="100">库存</th>
                <th width="150">状态</th>
                <th width="100">发布人</th>
                <th width="100">时间</th>
                <th width="100">操作</th>
            </tr>
            </thead>
        </table>
    </div>
</div>
<script type="text/javascript" src="<%=request.getContextPath()%>/assistant/lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/assistant/lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/assistant/static/h-ui/js/H-ui.min.js"></script>
<script type="text/javascript"
        src="<%=request.getContextPath()%>/assistant/static/h-ui.admin/js/H-ui.admin.js"></script>
<script type="text/javascript"
        src="<%=request.getContextPath()%>/assistant/lib/My97DatePicker/4.8/WdatePicker.js"></script>
<script type="text/javascript"
        src="<%=request.getContextPath()%>/assistant/lib/datatables/1.10.0/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/assistant/lib/laypage/1.2/laypage.js"></script>
<script type="text/javascript">
    var productTable = null;
    var roleId = $("#roleId").val();
    var companyId = $("#companyId").val();
    pids=[];
    $(function () {
        if (roleId == 100 || roleId == 200) {
            initSalesSelect("all");
        } else if (roleId == 400) {
            $("#localStatus").val(2);
            initSalesSelect("all");
        } else if (roleId == 500) {
            initSalesSelect("owner");
        } else if (roleId == 600) {
            $("#salesMan").empty();
            $("#salesMan").append($("<option value='${sessionScope.user.USER_ID}'>${sessionScope.user.USER_NAME}</option>"));
        }
        var sd = new Date();
        sd.setDate(sd.getDate() - 6);
        $("#logmin").val(sd.format("yyyy-MM-dd"));
        $("#logmax").val(new Date().format("yyyy-MM-dd"));
        productTable = initializeTable();
        initSelect();
    });
    $("#search").click(function () {
        productTable.ajax.reload();
    });

    $(function () {
        /*layui.use('form', function () {
            var form = layui.form;
            form.render("select");
        });*/


        $.ajax({
            type: 'POST',
            url: '<%=request.getContextPath()%>/product/shop/select',
            dataType: 'json',
            data: {

            },
            success: function (data) {

                var html='<option value="">请选择</option>';
                for(var key in  data){
                    var single="<optgroup label='"+key+"'>";
                    for(var kk in data[key]){
                        single+="<option value='"+kk+"'>"+data[key][kk]+"</option>";
                    }
                    single+=" </optgroup>";
                    html+=single;
                }

                $("#shopId").append(html);



            },
            error: function (data) {
                layer.msg(data.msg, {icon: 2, time: 1000});
            }
        });
        //店铺初始化
    })




    /*产品-添加*/
    function addProduct(title, url, w) {
        layer_show(title, url, w);
    }
    function initSelect() {
        $.ajax({
            type: 'POST',
            url: '<%=request.getContextPath()%>/common/getList',
            dataType: 'json',
            data: {
                "code": "countries"
            },
            success: function (data) {
                if (data.code == 0) {
                    var data = data.data;
                    for (var i = 0; i < data.length; i++) {
                        $("#language").append($('<option value=' + data[i].ID + '>' + data[i].NAME + '</option>'));
                    }
                }
            },
            error: function (data) {
                layer.msg(data.msg, {icon: 2, time: 1000});
            }
        });
    }
    function initSalesSelect(type) {
        if (type == "all") {
            $.ajax({
                type: 'POST',
                url: '<%=request.getContextPath()%>/common/getList',
                dataType: 'json',
                data: {
                    "code": "users"
                },
                success: function (data) {
                    if (data.code == 0) {
                        var data = data.data;
                        for (var i = 0; i < data.length; i++) {
                            $("#salesMan").append($('<option value=' + data[i].USER_ID + '>' + data[i].USER_NAME + '</option>'));
                        }
                    }
                },
                error: function (data) {
                    layer.msg(data.msg, {icon: 2, time: 1000});
                }
            });
        } else {
            $.ajax({
                type: 'POST',
                url: '<%=request.getContextPath()%>/system/selectUsers',
                dataType: 'json',
                data: {
                    "companyId": companyId
                },
                success: function (data) {
                    if (data.code == 0) {
                        var data = data.data;
                        for (var i = 0; i < data.length; i++) {
                            $("#salesMan").append($('<option value=' + data[i].USER_ID + '>' + data[i].USER_NAME + '</option>'));
                        }
                    }
                },
                error: function (data) {
                    layer.msg(data.msg, {icon: 2, time: 1000});
                }
            });
        }
    }
    /*初始化table*/
    function initializeTable() {
        var table = $("#productTable").DataTable({
            "serverSide": true,
            "pagingType": "full_numbers",
            "processing": true,
            "ordering": false,
            "searching": false,
            "bLengthChange": false,
            "ajax": {
                "url": "<%=request.getContextPath()%>/product/publish/list",
                "type": "POST",
                "data": function (d) {
                    return $.extend({}, d, {
                        "data": JSON.stringify(getFormJson("#productForm"))
                    });
                }
            },
            "columns": [
                {"data": "id"},
                {
                    "data": function (val) {
                        return val.mainPath == null ? "" : "<img width='100px' height='90px'  src='<%=session.getAttribute("productPath")%>" + val.mainPath + "'/>";
                    }
                },
                {
                    "data": function (val) {
                        return val.itemName == null ? "" : val.itemName;
                    }
                },
                {
                    "data": function (val) {
                        return val.countryName == null ? "" : val.countryName;
                    }
                },
                {
                    "data": function (val) {
                        return val.shopName == null ? "" : val.shopName;
                    }
                },

                {
                    "data": function (val) {
                        return val.itemSku == null ? "" : val.itemSku + "-" + val.amzSku;
                    }
                },
                {"data": "standardPrice"},
                {
                    "data": function (val) {
                        return val.quantity == null ? "" : val.quantity;
                    }
                },
                {
                    "data": function (val) {
                        var desc = "";
                        var pc=val.publishContent;
                        console.log(pc);
                        if (pc == null || pc.indexOf("0")>-1) {
                            desc = "【全部】"
                        } else {
                            desc = "【";

                                if (pc.indexOf("1")>-1) {
                                    desc += "价格|";
                                }

                                if (pc.indexOf("2")>-1) {
                                    desc += "库存|";
                                }

                                if (pc.indexOf("3")>-1) {
                                    desc += "关系|";
                                }

                                if (pc.indexOf("4")>-1) {
                                    desc += "图片|";
                                }

                           desc= desc.substring(0,desc.length-1)+"】";
                        }

                        if (val.publishStatus == "0") {
                            return desc+"预发布";
                        }
                        if (val.publishStatus == "1") {
                            return "<p style='text-align: left'>"+desc+"发布失败：</p><p title='" + val.uploadDesc + "' style='color: #ff515b'>" + (typeof(val.uploadDesc) == 'undefined' ? '' : getUploadDesc(val.uploadDesc)) + "</p>";
                        }
                        if (val.publishStatus == "2") {
                            return desc+"发布成功";
                        }
                        if (val.publishStatus == "3") {
                            return desc+"发布中";
                        }
                    }
                },
                {
                    "data": function (val) {
                        return val.userName == null ? "" : val.userName;
                    }
                },
                {
                    "data": function (val) {
                        return "<p style='text-align: left'>创建:</p><p style='text-align: left'>" + getMyDate(val.createTime, true) + "</p>" + "<p style='text-align: left'>发布时间</p><p style='text-align: left'>" + getMyDate(val.publishTime, false) + "</p>";
                    }
                }
            ],
            "columnDefs": [
                {
                    "targets": [0],
                    "data": "id",
                    "render": function (data, type, full) {
                        return "<input type='checkbox' value=" + full.id + ">"
                    }
                },
                {
                    "targets": [11],
                    "data": "id",
                    "render": function (data, type, full) {
                        return ( "<a style='text-decoration:none' title=''  onClick=\"publishProduct('" + full.id + "')\"')>发布</a>") +
                                "&nbsp;&nbsp;" +
                                "<a style='text-decoration:none' title='删除'  onClick=\"deleteProduct('" + full.id + "')\"')>删除</a>";
                    }
                }
            ],
            "rowCallback": function (row, data, displayIndex) {
                $(row).attr("class", "text-c");
            },
            "initComplete": function (settings, json) {

            },
            /*"dom": "t<'dataTables_info'il>p",*/
            "language": {
                "sProcessing": "正在加载中......",
                "lengthMenu": "每页 _MENU_ 条",
                "zeroRecords": "没有找到记录",
                "info": "当前显示 _START_ 到 _END_ 条，共 _TOTAL_条",
                "infoEmpty": "无记录",
                "paginate": {
                    "first": "首页",
                    "previous": "前一页",
                    "next": "后一页",
                    "last": "末页"
                }
            }
        });

        //监听一下上一页下一页的点击事件
        $(".dataTables_paginate").on("click", "a", function() {
            pids=pids.concat(getIdsArr());
        });
        return table;
    }

    function getCountryName(id) {
        if (id === "GB") {
            return "英语";
        }
        if (id === "JP") {
            return "日语";
        }
        if (id === "CN") {
            return "中文";
        }
        if (id === "DE") {
            return "德语";
        }
        if (id === "FR") {
            return "法语";
        }
        if (id === "ES") {
            return "西班牙语";
        }
        if (id === "IT") {
            return "意大利语";
        }

        return "";

    }

    function getUploadDesc(str) {
        if (str == null || str == "") {
            return str;
        }

        if (str == "undefined") {
            return '';
        }


        if (str.length > 200) {
            return str.substring(0, 200) + "...";
        }

        return str;
    }

    /*打开订单详情页*/
    function toDetail(amazonOrderId) {
        var index = layer.open({
            type: 2,
            title: '订单详情',
            content: 'order-Detail.jsp?amazonOrderId=' + amazonOrderId
        });
        layer.full(index);
    }

    function editProduct(id) {
        location.href = '<%=request.getContextPath()%>/product/claim/detail?id=' + id;

    }


    /**
     * 批量发布操作
     * @param ids
     */
    function claimProduct(ptype) {
        var ids =getIDs();


        if (ids == null || ids.length == 0) {
            layer.msg("请选择发布产品！", {icon: 5, time: 1000});
            return;
        }

        $.ajax({
            type: 'POST',
            url: '<%=request.getContextPath()%>/product/publish/batch',
            dataType: 'json',
            data: {
                "ids": ids,
                "pType": ptype
            },
            success: function (data) {
                if (data.success) {
                    setTimeout(layer.msg("发布成功", {icon: 6, time: 1000}), 1000);
                    document.getElementById("refresh").click();
                }
            },
            error: function (data) {
                layer.msg(data.msg, {icon: 2, time: 1000});
            }
        });

    }

    /*产品-删除*/
    function deleteProduct(id) {


        if (id == null || id == "") {
            id = getIDs();
        }
        layer.confirm('产品删除须谨慎，确认要删除吗？', function (index) {
            $.ajax({
                type: 'POST',
                url: '<%=request.getContextPath()%>/public/delete',
                dataType: 'json',
                data: {"id": id},
                success: function (data) {
                    if (data.success) {
                        setTimeout(layer.msg(data.msg, {icon: 6, time: 1000}), 1000);
                        document.getElementById("refresh").click();
                        productTable.ajax.reload();
                    } else {
                        layer.msg('删除失败!', {icon: 5, time: 1000});
                    }
                },
                error: function (data) {
                    layer.msg('删除失败!', {icon: 5, time: 1000});
                },
            });
        });
    }

    function getIDs() {

        return pids.concat(getIdsArr()).join(",");
    }

    function getIdsArr() {
        var ids = [];
        $("#productTable td input:checkbox:checked").each(function (i, val) {
            ids.push($(val).val());
        });
        return ids;
    }

    function publishProduct(id) {
        var index = layer.open({
            type: 2,
            title: "发布产品",
            content: '<%=request.getContextPath()%>/product/publish/detail?id=' + id + "&type=1",
            end: function () {
                location.reload();
            }
        });
        layer.full(index);
    }

    //将时间戳格式化
    function getMyDate(time, bol) {
        if (typeof(time) == "undefined") {
            return "";
        }
        var oDate = new Date(time),
                oYear = oDate.getFullYear(),
                oMonth = oDate.getMonth() + 1,
                oDay = oDate.getDate(),
                oHour = oDate.getHours(),
                oMin = oDate.getMinutes(),
                oSen = oDate.getSeconds(), oTime;
        if (bol) {

            oTime = oYear + '-' + getzf(oMonth) + '-' + getzf(oDay);//+' '+ getzf(oHour) +':'+ getzf(oMin) +':'+getzf(oSen);//最后拼接时间

        } else {
            oTime = oYear + '-' + getzf(oMonth) + '-' + getzf(oDay) + ' ' + getzf(oHour) + ':' + getzf(oMin) + ':' + getzf(oSen);//最后拼接时间
        }
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