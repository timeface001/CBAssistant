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
    <title>新建网站角色 - 管理员管理 - H-ui.admin v3.1</title>
</head>
<body>
<article class="page-container">
    <form class="form form-horizontal" id="addRoleForm">
        <input type="hidden" value="<%=id%>" name="id">
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>角色名称：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" class="input-text" value="" placeholder="" id="roleName" name="roleName">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3">状态：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <select id="state" name="state" class="select" style="height: 32px">
                    <option value="1" selected>启用</option>
                    <option value="0">停用</option>
                </select>
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3">备注：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" class="input-text" value="" placeholder="" id="roleDesc" name="roleDesc">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3">菜单：</label>
            <div class="formControls col-xs-8 col-sm-9" id="permission-div">

            </div>
        </div>
        <div class="row cl">
            <div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-3">
                <button type="button" class="btn btn-success radius" id="role-save" name="role-save"><i
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
<script type="text/javascript">
    var id = '<%=id%>';
    $(function () {
        $("#addRoleForm").validate({
            rules: {
                roleName: {
                    required: true,
                },
            },
            onkeyup: false,
            focusCleanup: true,
            success: "valid",
            submitHandler: function (form) {
                $(form).ajaxSubmit();
                var index = parent.layer.getFrameIndex(window.name);
                parent.layer.close(index);
            }
        });
        initMenu();
    });
    function initMenu() {
        $.ajax({
            type: 'POST',
            url: '<%=request.getContextPath()%>/common/getTreeMenus',
            dataType: 'json',
            data: {
                "code": "treeMenus"
            },
            success: function (data) {
                if (data.code == 0) {
                    var data = data.data;
                    for (var i = 0; i < data.length; i++) {
                        var dl1 = "<dl class='permission-list'>" +
                                "<dt>" +
                                "<label>" +
                                "<input type='checkbox' value=" + data[i].menuId + " name='parentMenu'>" +
                                data[i].menuName +
                                "</label>" +
                                "</dt>" +
                                "<dd>";
                        var menus = data[i].menuList;
                        for (var j = 0; j < menus.length; j++) {
                            var dl2 = "<dl class='cl permission-list2'>" +
                                    "<dt>" +
                                    "<label>" +
                                    "<input type='checkbox' value=" + menus[j].menuId + " name='childMenu'>" +
                                    menus[j].menuName +
                                    "</label>" +
                                    "</dt>" +
                                    "<dd>" +
                                    "<label >" +
                                    "<input type='checkbox' value='' name='user-Character-1-0-0'>添加" +
                                    "</label>" +
                                    "<label>" +
                                    "<input type='checkbox' value='' name='user-Character-1-0-0'>修改" +
                                    "</label>" +
                                    "<label>" +
                                    "<input type='checkbox' value='' name='user-Character-1-0-0'>删除" +
                                    "</label>" +
                                    "<label>" +
                                    "<input type='checkbox' value='' name='user-Character-1-0-0'>查看" +
                                    "</label>" +
                                    "</dd>" +
                                    "</dl>";
                            dl1 += dl2;
                        }
                        var dlend = "</dd> </dl>"
                        dl1 += dlend;
                        $("#permission-div").append(dl1);
                    }
                    $(".permission-list dt input:checkbox").click(function () {
                        $(this).closest("dl").find("dd input:checkbox").prop("checked", $(this).prop("checked"));
                    });
                    $(".permission-list2 dd input:checkbox").click(function () {
                        var l = $(this).parent().parent().find("input:checked").length;
                        var l2 = $(this).parents(".permission-list").find(".permission-list2 dd").find("input:checked").length;
                        if ($(this).prop("checked")) {
                            $(this).closest("dl").find("dt input:checkbox").prop("checked", true);
                            $(this).parents(".permission-list").find("dt").first().find("input:checkbox").prop("checked", true);
                        }
                        else {
                            if (l == 0) {
                                $(this).closest("dl").find("dt input:checkbox").prop("checked", false);
                            }
                            if (l2 == 0) {
                                $(this).parents(".permission-list").find("dt").first().find("input:checkbox").prop("checked", false);
                            }
                        }
                    });
                    initRoleInfo();
                } else {
                    layer.msg('查询失败!', {icon: 2, time: 1000});
                }
            },
            error: function (data) {
                layer.msg('查询失败!', {icon: 2, time: 1000});
            }
        });
    }
    function initRoleInfo() {
        if (id != null && id != "" && id != "null" && id != undefined) {
            $.ajax({
                type: 'POST',
                url: '<%=request.getContextPath()%>/system/selectRoles',
                dataType: 'json',
                data: {
                    "id": id
                },
                success: function (data) {
                    if (data.code == 0) {
                        var menus = data.menus;
                        var menuStr = new Array();
                        for (var i = 0; i < menus.length; i++) {
                            menuStr.push(menus[i].MENU_ID);
                        }
                        var parentMenus = document.getElementsByName("parentMenu");
                        var childMenus = document.getElementsByName("childMenu");
                        for (var i = 0; i < parentMenus.length; i++) {
                            if (menuStr.indexOf(parentMenus[i].value) != -1) {
                                parentMenus[i].checked = true;
                            }
                        }
                        for (var i = 0; i < childMenus.length; i++) {
                            if (menuStr.indexOf(childMenus[i].value) != -1) {
                                childMenus[i].checked = true;
                            }
                        }
                        var data = data.data[0];
                        $("#roleName").val(data.ROLE_NAME);
                        $("#state").val(data.ROLE_STATE);
                    } else {
                        layer.msg(data.msg, {icon: 2, time: 1000});
                    }
                },
                error: function (data) {
                    layer.msg(data.msg, {icon: 2, time: 1000});
                },
            });
        }
    }
    $("#role-save").click(function () {
        var url = '<%=request.getContextPath()%>/system/addRole';
        if (id != null && id != "" && id != "null" && id != undefined) {
            url = '<%=request.getContextPath()%>/system/updateRole';
        } else {
            url = '<%=request.getContextPath()%>/system/addRole';
        }
        layer.load();
        $.ajax({
            type: 'POST',
            url: url,
            dataType: 'json',
            data: {
                "data": JSON.stringify(getFormJson("#addRoleForm"))
            },
            success: function (data) {
                layer.closeAll("loading");
                layer.msg(data.msg, {icon: 1, time: 2000});
                layer_close();
            },
            error: function (data) {
                layer.closeAll("loading");
                layer.msg(data.msg, {icon: 2, time: 2000});
            },
        });
    });
</script>
</body>
</html>
