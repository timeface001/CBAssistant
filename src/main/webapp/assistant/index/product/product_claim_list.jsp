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
    <!--[if IE 6]>
    <script type="text/javascript"
            src="<%=request.getContextPath()%>/assistant/lib/DD_belatedPNG_0.0.8a-min.js"></script>
    <script>DD_belatedPNG.fix('*');</script>
    <![endif]-->
    <title>认领产品</title>
</head>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 产品管理 <span
        class="c-gray en">&gt;</span> 认领产品 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px"
                                              id="refresh"
                                              href="javascript:location.replace(location.href);" title="刷新"><i
        class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
    <form id="productForm" class="form form-horizontal">
        <div class="row cl">
            <label class="form-label col-xs-1 col-sm-1">分类：</label>
            <div class="formControls col-xs-2 col-sm-2">
                <select id="typeId" name="typeId" class="select" style="height: 32px">
                    <option value="">请选择</option>
                </select>
            </div>
            <label class="form-label col-xs-1 col-sm-1">标题：</label>
            <div class="formControls col-xs-2 col-sm-2">
                <input type="text" name="name" placeholder=" " class="input-text">
            </div>
            <label class="form-label col-xs-1 col-sm-1">SKU：</label>
            <div class="formControls col-xs-2 col-sm-2">
                <input type="text" name="sku" placeholder=" " class="input-text">
            </div>

            <div class="formControls col-xs-1 col-sm-1">
                <button id="search" class="btn btn-success" type="button"><i class="Hui-iconfont">&#xe665;</i>
                </button>
            </div>

        </div>
        <div class="row cl">
            <label class="form-label col-xs-1 col-sm-1">状态：</label>
            <div class="formControls col-xs-2 col-sm-2">
                <select id="pStatus" name="pStatus" class="select" style="height: 32px">
                    <option value="">请选择</option>
                    <option value="1">已加入</option>
                    <option value="0">未加入</option>
                </select>
            </div>
            <label class="form-label col-xs-1 col-sm-1">创建人：</label>
            <div class="formControls col-xs-2 col-sm-2">
                <select id="salesMan" name="salesMan" class="select" style="height: 32px">
                    <option value="">请选择</option>
                </select>
            </div>
            <input id="roleId" type="hidden" value="${sessionScope.user.ROLE_ID}">
            <input id="companyId" type="hidden" value="${sessionScope.user.USER_COMPANY}">
        </div>
    </form>
    <div class="mt-20">
        <%--<div id="btn-div" class="row text-c">
            <a href="javascript:;" onclick="reloadTable(0)" class="btn btn-success radius">全部</a>
            <a href="javascript:;" onclick="reloadTable(1)" class="btn btn-default radius">未认领</a>
            <a href="javascript:;" onclick="reloadTable(2)" class="btn btn-default radius">已认领</a>
        </div>--%>
        <div class="cl pd-5 bg-1 bk-gray" id="count-div"><span class="l">
            <a href="javascript:;"
               onclick="preBatchPublish()"
               class="btn  btn-primary radius"><i
                    class="Hui-iconfont">
                &#xe619;</i> 批量发布</a>
            </span></div>
        <table id="productTable" class="table table-border table-bordered table-bg table-hover mt-10">
            <thead>
            <tr class="text-c">
                <th width="25"><input type="checkbox" value="" name=""></th>
                <th width="150">产品图</th>
                <th width="150">标题</th>
                <th width="150">分类</th>
                <th width="150">sku</th>
                <th width="60">售价</th>
                <th width="100">库存</th>
                <th width="100">状态</th>
                <th width="100">创建人</th>
                <th width="100">操作时间</th>
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
    });
    $("#search").click(function () {
        productTable.ajax.reload();
    });
    /*产品-添加*/
    function addProduct(title, url, w) {
        layer_show(title, url, w);
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
    /*查询产品*/
    function reloadTable(id) {
        if (id == 0) {
            document.getElementById('pStatus').value = "";
        } else {
            $("#pStatus").val(id);
        }
        productTable.ajax.reload();
        var btnDiv = document.getElementById("btn-div");
        var btns = btnDiv.getElementsByTagName("a");
        for (var i = 0; i < btns.length; i++) {
            if (i == id) {
                btns[i].className = 'btn btn-success radius'
            } else {
                btns[i].className = 'btn btn-default radius'
            }
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
                "url": "<%=request.getContextPath()%>/product/claim/list",
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
                        var imagePath = "";
                        if (val.imagePath != null && val.imagePath != "" && val.imagePath != undefined && val.imagePath.indexOf(",") != -1) {
                            imagePath = val.imagePath.substring(0, val.imagePath.indexOf(","));
                        } else {
                            imagePath = val.imagePath;
                        }
                        return val.imagePath == null ? "" : "<img width='100px' height='90px'  src='<%=session.getAttribute("productPath")%>" + imagePath + "'/>";
                    }
                },
                {
                    "data": function (val) {
                        return val.itemCn == null ? "" : "<a target='_blank' href='" + val.source + "'>" + val.itemCn + "</a>";
                    }
                },
                {
                    "data": function (val) {
                        return val.typeName == null ? "" : val.typeName;
                    }
                },
                {
                    "data": function (val) {
                        return val.sku == null ? "" : val.sku;
                    }
                },
                {"data": "price"},
                {
                    "data": function (val) {
                        return val.quantity == null ? "" : val.quantity;
                    }
                },
                {
                    "data": function (val) {
                        switch (val.isPrepublish) {
                            case "0":
                                return "未发布";
                                break;
                            case "1":
                                return "发布失败";
                                break;
                            case "2":
                                return "发布成功";
                                break;
                            case "3":
                                return "发布中";
                                break;
                            default:
                                return " ";
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
                        return "<p style='text-align: left'>创建于</p><p style='text-align: left'>" + getMyDate(val.createTime) + "</p>" + (val.publishTime != null ? ("<p style='text-align: left'>发布于</p><p style='text-align: left'>" + getMyDate(val.publishTime) + "</p>") : "");
                    }
                }
            ],
            "columnDefs": [
                {
                    "targets": [0],
                    "data": "id",
                    "render": function (data, type, full) {
                        if (full.updateState == "1") {
                            return "<input type='checkbox' value=" + full.id + ">";
                        } else {
                            return "";
                        }
                    }
                },
                {
                    "targets": [10],
                    "data": "id",
                    "render": function (data, type, full) {
                        return ( full.updateState == "1" ? "<a style='text-decoration:none' title='发布'  onClick=\"prePublish('" + full.id + "')\"')>发布</a>" : "") +
                                "&nbsp;&nbsp;" +
                                "<a style='text-decoration:none' title='编辑'  onClick=\"editProduct('" + full.id + "')\"')>编辑</a>&nbsp;&nbsp;"+(full.updateState == "1" ?"":"<a style='text-decoration:none' title='删除'  onClick=\"deleteProduct('" + full.id + "')\"')>删除</a>");
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
        return table;
    }

    function editProduct(id) {
        location.href = '<%=request.getContextPath()%>/product/claim/detail?id=' + id;

    }

    /**
     * 移入待发布
     * @param ids
     */
    function prePublish(id) {
        var index = layer.open({
            type: 2,
            title: "发布产品",
            content: '<%=request.getContextPath()%>/product/publish/detail?id=' + id + "&type=0",
            end: function () {
                location.reload();
            }
        });
        layer.full(index);
    }

    /**
     * 批量移入待发布
     * @param ids
     */
    function preBatchPublish() {
        var ids = getIDs();
        if (ids == null || $.trim(ids).length == 0) {
            layer.msg('请选择需要批量发布的商品!', {icon: 5, time: 1000});
            return;
        }
        var index = layer.open({
            type: 2,
            title: "发布产品",
            content: '<%=request.getContextPath()%>/product/publish/detail?id=' + ids + "&type=2",
            end: function () {
                location.reload();
            }
        });
        layer.full(index);
    }



    /*产品-删除*/
    function deleteProduct(id) {

        if (id == null || id == "") {
            id = getIDs();
        }
        layer.confirm('产品删除须谨慎，确认要删除吗？', function (index) {
            $.ajax({
                type: 'POST',
                url: '<%=request.getContextPath()%>/product/claim/delete',
                dataType: 'json',
                data: {"id": id},
                success: function (data) {
                    if (data.success) {
                        setTimeout(layer.msg(data.msg, {icon: 6, time: 1000}), 1000);
                        document.getElementById("refresh").click();
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
        var ids = [];
        $("#productTable td input:checkbox:checked").each(function (i, val) {
            ids.push($(val).val());
        });
        return ids.join(",");
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
                oTime = oYear + '-' + getzf(oMonth) + '-' + getzf(oDay) + ' ' + getzf(oHour) + ':' + getzf(oMin) + ':' + getzf(oSen);//最后拼接时间
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