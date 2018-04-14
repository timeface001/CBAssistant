<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8">
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,Shop-scalable=no"/>
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
    <title>店铺授权</title>
</head>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 店铺管理 <span
        class="c-gray en">&gt;</span> 店铺授权 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px"
                                              href="javascript:location.replace(location.href);" title="刷新"
                                              id="refresh"><i
        class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
    <div class="cl pd-5 bg-1 bk-gray" id="count-div"><span class="l">
        <a class="btn btn-primary radius"
           href="javascript:;"
           onclick="addShop('添加店铺','<%=request.getContextPath()%>/assistant/index/shop/shop-add.jsp','800')"><i
                class="Hui-iconfont">
            &#xe600;</i> 添加店铺</a> </span></div>
    <input ID="roleId" type="hidden" value="${sessionScope.user.ROLE_ID}">
    <table id="shopTable" class="table table-border table-bordered table-hover table-bg">
        <thead>
        <tr class="text-c">
            <th width="25"><input type="checkbox" value="" name=""></th>
            <th width="100">店铺ID</th>
            <th width="200">店铺名称</th>
            <th width="200">授权国家</th>
            <th width="200">代理邮箱</th>
            <th width="300">授权时间</th>
            <th width="100">状态</th>
            <th width="70">操作</th>
        </tr>
        </thead>
    </table>
</div>
<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="<%=request.getContextPath()%>/assistant/lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/assistant/lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/assistant/static/h-ui/js/H-ui.min.js"></script>
<script type="text/javascript"
        src="<%=request.getContextPath()%>/assistant/static/h-ui.admin/js/H-ui.admin.js"></script>
<!--/_footer 作为公共模版分离出去-->
<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript"
        src="<%=request.getContextPath()%>/assistant/lib/datatables/1.10.0/jquery.dataTables.min.js"></script>
<script type="text/javascript">
    var roleId = $("#roleId").val();
    $(function () {
        initTable();
    });
    function initTable() {
        $.ajax({
            type: 'POST',
            url: '<%=request.getContextPath()%>/shop/selectShops',
            dataType: 'json',
            data: {},
            success: function (data) {
                if (data.code == 0) {
                    var data = data.data;
                    $("#count-div").append("<span class='r'>共有数据：<strong>" + data.length + "</strong> 条</span>");
                    $('#shopTable').dataTable({
                        "data": data,
                        "columns": [
                            {"data": "SHOP_ID"},
                            {"data": "SHOP_ID"},
                            {"data": "SHOP_NAME"},
                            {"data": "COUNTRY_NAME"},
                            {"data": "EMAIL"},
                            {"data": "CREATE_TIME"},
                            {"data": "SHOP_STATE"},
                            {"data": "SHOP_STATE"}
                        ],
                        "columnDefs": [
                            {
                                "targets": [0],
                                "data": "SHOP_ID",
                                "render": function (data, type, full) {
                                    return "<input type='checkbox' value=" + data + ">";
                                }
                            },
                            {
                                "targets": [5],
                                "data": "CREATE_TIME",
                                "render": function (data, type, full) {
                                    return "<div>" + new Date(data).format("yyyy-MM-dd hh:mm:ss") + "</div>";
                                }
                            },
                            {
                                "targets": [6],
                                "data": "SHOP_STATE",
                                "render": function (data, type, full) {
                                    if (data == 1) {
                                        return "<span class='label label-success radius'>已启用</span>";
                                    } else {
                                        return "<span class='label radius'>已停用</span>";
                                    }
                                }
                            },
                            {
                                "targets": [7],
                                "data": "SHOP_STATE",
                                "render": function (data, type, full) {
                                    if (data == 1) {
                                        if (roleId == 100) {
                                            return "<a style='text-decoration:none' title='停用' onClick=\"stopShop('" + full.SHOP_ID + "')\"'><i class='Hui-iconfont'>&#xe631;</i></a>" +
                                                    "<a style='text-decoration:none' title='编辑' onClick=\"editShop('" + full.SHOP_ID + "')\"'><i class='Hui-iconfont'>&#xe6df;</i></a>" +
                                                    "<a style='text-decoration:none' title='删除' onClick=\"deleteShop('" + full.SHOP_ID + "')\"'><i class='Hui-iconfont'>&#xe6e2;</i></a>";
                                        } else {
                                            return "<a style='text-decoration:none' title='停用' onClick=\"stopShop('" + full.SHOP_ID + "')\"'><i class='Hui-iconfont'>&#xe631;</i></a>" +
                                                    "<a style='text-decoration:none' title='编辑' onClick=\"editShop('" + full.SHOP_ID + "')\"'><i class='Hui-iconfont'>&#xe6df;</i></a>";
                                        }
                                    } else {
                                        if (roleId == 100) {
                                            return "<a style='text-decoration:none' title='启用' onClick=\"startShop('" + full.SHOP_ID + "')\"'><i class='Hui-iconfont'>&#xe615;</i></a>" +
                                                    "<a style='text-decoration:none' title='编辑'  onClick=\"editShop('" + full.SHOP_ID + "')\"'><i class='Hui-iconfont'>&#xe6df;</i></a>" +
                                                    "<a style='text-decoration:none' title='删除'  onClick=\"deleteShop('" + full.SHOP_ID + "')\"'><i class='Hui-iconfont'>&#xe6e2;</i></a>";
                                        } else {
                                            return "<a style='text-decoration:none' title='启用' onClick=\"startShop('" + full.SHOP_ID + "')\"'><i class='Hui-iconfont'>&#xe615;</i></a>" +
                                                    "<a style='text-decoration:none' title='编辑'  onClick=\"editShop('" + full.SHOP_ID + "')\"'><i class='Hui-iconfont'>&#xe6df;</i></a>";
                                        }
                                    }
                                }
                            }
                        ],
                        "rowCallback": function (row, data, displayIndex) {
                            $(row).attr("class", "text-c");
                        },
                        "bFilter": false,
                        "paging": false,
                        "info": false
                    });
                } else {
                    layer.msg('查询失败!', {icon: 2, time: 1000});
                }
            },
            error: function (data) {
                layer.msg('查询失败!', {icon: 2, time: 1000});
            }
        });
    }
    /*店铺-添加*/
    function addShop(title, url, w) {
        layer_show(title, url, w);
    }
    /*店铺-编辑*/
    function editShop(id) {
        layer_show('编辑店铺', '<%=request.getContextPath()%>/assistant/index/shop/shop-add.jsp?id=' + id, '800');
    }
    /*店铺-删除*/
    function deleteShop(id) {
        layer.confirm('店铺删除须谨慎，确认要删除吗？', function (index) {
            $.ajax({
                type: 'POST',
                url: '<%=request.getContextPath()%>/shop/deleteShop',
                dataType: 'json',
                data: {"id": id},
                success: function (data) {
                    if (data.code == 0) {
                        layer.msg('已删除!', {icon: 1, time: 1000});
                        document.getElementById("refresh").click();
                    } else {
                        layer.msg('删除失败!', {icon: 1, time: 1000});
                    }
                },
                error: function (data) {
                    layer.msg('删除失败!', {icon: 1, time: 1000});
                },
            });
        });
    }
    /*店铺-停用*/
    function stopShop(id) {
        layer.confirm('确认要停用吗？', function (index) {
            $.ajax({
                type: 'POST',
                url: '<%=request.getContextPath()%>/shop/updateShopState',
                dataType: 'json',
                data: {"id": id, "state": "0"},
                success: function (data) {
                    if (data.code == 0) {
                        layer.msg('已停用!', {icon: 6, time: 1000});
                        document.getElementById("refresh").click();
                    } else {
                        layer.msg('停用失败!', {icon: 5, time: 1000});
                    }
                },
                error: function (data) {
                    layer.msg('停用失败!', {icon: 5, time: 1000});
                }
            });


        });
    }
    /*店铺-启用*/
    function startShop(id) {
        layer.confirm('确认要启用吗？', function (index) {
            $.ajax({
                type: 'POST',
                url: '<%=request.getContextPath()%>/shop/updateShopState',
                dataType: 'json',
                data: {"id": id, "state": "1"},
                success: function (data) {
                    if (data.code == 0) {
                        layer.msg('已启用!', {icon: 6, time: 1000});
                        document.getElementById("refresh").click();
                    } else {
                        layer.msg('启用失败!', {icon: 5, time: 1000});
                    }
                },
                error: function (data) {
                    layer.msg('启用失败!', {icon: 5, time: 1000});
                }
            });
        });
    }
</script>
</body>
</html>
