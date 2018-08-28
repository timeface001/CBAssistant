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
    <title>翻译管理</title>
</head>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 系统管理 <span
        class="c-gray en">&gt;</span> 翻译管理 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px"
                                              href="javascript:location.replace(location.href);" title="刷新"
                                              id="refresh"><i
        class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
    <div class="cl pd-5 bg-1 bk-gray" id="count-div"><span class="l"> <a href="javascript:;"
                                                                         onclick="deleteTranslation()"
                                                                         class="btn btn-danger radius"><i
            class="Hui-iconfont">
        &#xe6e2;</i> 批量删除</a> <a class="btn btn-primary radius" href="javascript:;"
                                 onclick="addTranslation('添加翻译','<%=request.getContextPath()%>/assistant/index/system/translation-add.jsp','800','400')"><i
            class="Hui-iconfont">
        &#xe600;</i> 添加翻译</a> </span></div>
    <table id="translationTable" class="table table-border table-bordered table-hover table-bg">
        <thead>
        <tr class="text-c">
            <th width="25"><input type="checkbox" value="" name=""></th>
            <th width="100">翻译账号</th>
            <th width="200">秘钥</th>
            <th width="100">翻译方式</th>
            <th width="100">状态</th>
            <th width="100">创建人</th>
            <th width="100">创建时间</th>
            <th width="100">操作</th>
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
            url: '<%=request.getContextPath()%>/system/selectTranslations',
            dataType: 'json',
            data: {},
            success: function (data) {
                if (data.code == 0) {
                    var data = data.data;
                    $("#count-div").append("<span class='r'>共有数据：<strong>" + data.length + "</strong> 条</span>");
                    $('#translationTable').dataTable({
                        "data": data,
                        "columns": [
                            {"data": "ID"},
                            {"data": "ACCOUNT"},
                            {"data": "SECRET_KEY"},
                            {"data": "TYPE"},
                            {"data": "STATE"},
                            {"data": "USER_NAME"},
                            {"data": "CREATE_TIME"},
                            {"data": "STATE"}
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
                                "targets": [3],
                                "data": "TYPE",
                                "render": function (data, type, full) {
                                    if (data == 1) {
                                        return "<span>Google</span>";
                                    } else {
                                        return "<span>百度</span>";
                                    }
                                }
                            },
                            {
                                "targets": [4],
                                "data": "STATE",
                                "render": function (data, type, full) {
                                    if (data == 1) {
                                        return "<span class='label label-success radius'>已启用</span>";
                                    } else {
                                        return "<span class='label radius'>已停用</span>";
                                    }
                                }
                            },
                            {
                                "targets": [6],
                                "data": "CREATE_TIME",
                                "render": function (data, type, full) {
                                    return "<div>" + getMyDate(data) + "</div>"
                                }
                            },
                            {
                                "targets": [7],
                                "data": "STATE",
                                "render": function (data, type, full) {
                                    if (data == 1) {
                                        return "<a style='text-decoration:none' title='停用' onClick=\"stopTranslation('" + full.ID + "')\"'><i class='Hui-iconfont'>&#xe631;</i></a>" +
                                                "<a style='text-decoration:none' title='编辑' onClick=\"editTranslation('" + full.ID + "')\"'><i class='Hui-iconfont'>&#xe6df;</i></a>" +
                                                "<a style='text-decoration:none' title='删除' onClick=\"deleteTranslation('" + full.ID + "')\"'><i class='Hui-iconfont'>&#xe6e2;</i></a>";
                                    } else {
                                        return "<a style='text-decoration:none' title='启用' onClick=\"starttranslation('" + full.ID + "')\"'><i class='Hui-iconfont'>&#xe615;</i></a>" +
                                                "<a style='text-decoration:none' title='编辑'  onClick=\"editTranslation('" + full.ID + "')\"'><i class='Hui-iconfont'>&#xe6df;</i></a>" +
                                                "<a style='text-decoration:none' title='删除'  onClick=\"deleteTranslation('" + full.ID + "')\"'><i class='Hui-iconfont'>&#xe6e2;</i></a>";
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
    /*翻译-添加*/
    function addTranslation(title, url, w, h) {
        layer_show(title, url, w, h);
    }
    /*翻译-编辑*/
    function editTranslation(id) {
        layer_show('编辑翻译', '<%=request.getContextPath()%>/assistant/index/system/translation-add.jsp?id=' + id, '800', '400');
    }
    /*翻译-删除*/
    function deleteTranslation(id) {
        layer.confirm('翻译删除须谨慎，确认要删除吗？', function (index) {
            $.ajax({
                type: 'POST',
                url: '<%=request.getContextPath()%>/system/deleteTranslation',
                dataType: 'json',
                data: {"id": id},
                success: function (data) {
                    if (data.code == 0) {
                        layer.msg('已删除!', {icon: 1, time: 2000});
                        document.getElementById("refresh").click();
                    } else {
                        layer.msg('删除失败!', {icon: 1, time: 2000});
                    }
                },
                error: function (data) {
                    layer.msg('删除失败!', {icon: 1, time: 2000});
                },
            });
        });
    }
    /*翻译-停用*/
    function stoptranslation(id) {
        layer.confirm('确认要停用吗？', function (index) {
            $.ajax({
                type: 'POST',
                url: '<%=request.getContextPath()%>/system/updateTranslationState',
                dataType: 'json',
                data: {"id": id, "state": "0"},
                success: function (data) {
                    if (data.code == 0) {
                        layer.msg('已停用!', {icon: 5, time: 2000});
                        document.getElementById("refresh").click();
                    } else {
                        layer.msg('停用失败!', {icon: 6, time: 2000});
                    }
                },
                error: function (data) {
                    layer.msg('停用失败!', {icon: 6, time: 2000});
                }
            });


        });
    }
    /*翻译-启用*/
    function startTranslation(id) {
        layer.confirm('确认要启用吗？', function (index) {
            $.ajax({
                type: 'POST',
                url: '<%=request.getContextPath()%>/system/updateTranslationState',
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
