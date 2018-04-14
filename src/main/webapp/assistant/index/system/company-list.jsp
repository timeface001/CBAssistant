<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <title>公司管理</title>
</head>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 系统管理 <span
        class="c-gray en">&gt;</span> 公司管理 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px"
                                              href="javascript:location.replace(location.href);" title="刷新" id="refresh"><i
        class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
    <div class="cl pd-5 bg-1 bk-gray" id="count-div"><span class="l"> <a href="javascript:;" onclick="deleteCompany()"
                                                                         class="btn btn-danger radius"><i
            class="Hui-iconfont">
        &#xe6e2;</i> 批量删除</a> <a class="btn btn-primary radius" href="javascript:;"
                                 onclick="addCompany('添加公司','<%=request.getContextPath()%>/assistant/index/system/company-add.jsp','800','400')"><i
            class="Hui-iconfont">
        &#xe600;</i> 添加公司</a> </span></div>
    <table id="companyTable" class="table table-border table-bordered table-hover table-bg">
        <thead>
        <tr class="text-c">
            <th width="25"><input type="checkbox" value="" name=""></th>
            <th width="100">公司ID</th>
            <th width="200">公司名称</th>
            <th width="300">描述</th>
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
    $(function () {
        initTable();
    });
    function initTable() {
        $.ajax({
            type: 'POST',
            url: '<%=request.getContextPath()%>/system/selectCompanies',
            dataType: 'json',
            data: {},
            success: function (data) {
                if (data.code == 0) {
                    var data = data.data;
                    $("#count-div").append("<span class='r'>共有数据：<strong>" + data.length + "</strong> 条</span>");
                    $('#companyTable').dataTable({
                        "data": data,
                        "columns": [
                            {"data": "COMPANY_ID"},
                            {"data": "COMPANY_ID"},
                            {"data": "COMPANY_NAME"},
                            {"data": "COM_DESC"},
                            {"data": "COMPANY_STATE"},
                            {"data": "COMPANY_STATE"}
                        ],
                        "columnDefs": [
                            {
                                "targets": [0],
                                "data": "ID",
                                "render": function (data, type, full) {
                                    return "<input type='checkbox' value=" + data + ">";
                                }
                            },
                            {
                                "targets": [4],
                                "data": "COMPANY_STATE",
                                "render": function (data, type, full) {
                                    if (data == 1) {
                                        return "<span class='label label-success radius'>已启用</span>";
                                    } else {
                                        return "<span class='label radius'>已停用</span>";
                                    }
                                }
                            },
                            {
                                "targets": [5],
                                "data": "COMPANY_STATE",
                                "render": function (data, type, full) {
                                    if (data == 1) {
                                        return "<a style='text-decoration:none' title='停用' onClick=\"stopCompany('" + full.COMPANY_ID + "')\"'><i class='Hui-iconfont'>&#xe631;</i></a>" +
                                                "<a style='text-decoration:none' title='编辑' onClick=\"editCompany('" + full.COMPANY_ID + "')\"'><i class='Hui-iconfont'>&#xe6df;</i></a>" +
                                                "<a style='text-decoration:none' title='删除' onClick=\"deleteCompany('" + full.COMPANY_ID + "')\"'><i class='Hui-iconfont'>&#xe6e2;</i></a>";
                                    } else {
                                        return "<a style='text-decoration:none' title='启用' onClick=\"startCompany('" + full.COMPANY_ID + "')\"'><i class='Hui-iconfont'>&#xe615;</i></a>" +
                                                "<a style='text-decoration:none' title='编辑'  onClick=\"editCompany('" + full.COMPANY_ID + "')\"'><i class='Hui-iconfont'>&#xe6df;</i></a>" +
                                                "<a style='text-decoration:none' title='删除'  onClick=\"deleteCompany('" + full.COMPANY_ID + "')\"'><i class='Hui-iconfont'>&#xe6e2;</i></a>";
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
    /*公司-添加*/
    function addCompany(title, url, w, h) {
        layer_show(title, url, w, h);
    }
    /*公司-编辑*/
    function editCompany(id) {
        layer_show('编辑公司', '<%=request.getContextPath()%>/assistant/index/system/company-add.jsp?id=' + id, '800','400');
    }
    /*公司-删除*/
    function deleteCompany(id) {
        layer.confirm('公司删除须谨慎，确认要删除吗？', function (index) {
            $.ajax({
                type: 'POST',
                url: '<%=request.getContextPath()%>/system/deleteCompany',
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
    /*公司-停用*/
    function stopCompany(id) {
        layer.confirm('确认要停用吗？', function (index) {
            $.ajax({
                type: 'POST',
                url: '<%=request.getContextPath()%>/system/updateCompanyState',
                dataType: 'json',
                data: {"id": id, "state": "0"},
                success: function (data) {
                    if (data.code == 0) {
                        layer.msg('已停用!', {icon: 5, time: 1000});
                        document.getElementById("refresh").click();
                    } else {
                        layer.msg('停用失败!', {icon: 6, time: 1000});
                    }
                },
                error: function (data) {
                    layer.msg('停用失败!', {icon: 6, time: 1000});
                }
            });


        });
    }
    /*公司-启用*/
    function startCompany(id) {
        layer.confirm('确认要启用吗？', function (index) {
            $.ajax({
                type: 'POST',
                url: '<%=request.getContextPath()%>/system/updateCompanyState',
                dataType: 'json',
                data: {"id": id, "state": "1"},
                success: function (data) {
                    if (data.code == 0) {
                        layer.msg('已启用!', {icon: 6, time: 1000});
                        document.getElementById("refresh").click();
                    } else {
                        layer.msg('启用失败!', {icon: 6, time: 1000});
                    }
                },
                error: function (data) {
                    layer.msg('启用失败!', {icon: 6, time: 1000});
                }
            });
        });
    }
</script>
</body>
</html>
