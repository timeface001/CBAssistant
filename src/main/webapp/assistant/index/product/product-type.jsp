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
    <title>产品分类</title>
</head>
<body>
<div id="content-div" style="padding: 5px 5px 5px 5px;">
    <div id="div1"
         style="display: block;width: 240px;height: 300px; float: left;border: 1px solid #ddd;border-radius: 4px;padding: 5px 5px 5px 5px;margin-right: 10px;">
        <select id="level-1" name="source" class="select" style="height: 32px;" onchange="loadTypes(this.value,2)">
            <option value="">请选择</option>
        </select>
    </div>
    <div id="div2"
         style="display: none;width: 240px;height: 300px; float: left;border: 1px solid #ddd;border-radius: 4px;padding: 5px 5px 5px 5px;margin-right: 10px;">
        <select id="level-2" name="source" class="select" style="height: 32px" onchange="loadTypes(this.value,3)">
            <option value="">请选择</option>
        </select>
    </div>
    <div id="div3"
         style="display: none;width: 240px;height: 300px; float: left;border: 1px solid #ddd;border-radius: 4px;padding: 5px 5px 5px 5px;margin-right: 10px;">
        <select id="level-3" name="source" class="select" style="height: 32px" onchange="loadTypes(this.value,4)">
            <option value="">请选择</option>
        </select>
    </div>
    <div id="div4"
         style="display: none;width: 240px;height: 300px; float: left;border: 1px solid #ddd;border-radius: 4px;padding: 5px 5px 5px 5px;margin-right: 10px;">
        <select id="level-4" name="source" class="select" style="height: 32px" onchange="loadTypes(this.value,5)">
            <option value="">请选择</option>
        </select>
    </div>
    <div id="div5"
         style="display: none;width: 240px;height: 300px; float: left;border: 1px solid #ddd;border-radius: 4px;padding: 5px 5px 5px 5px;margin-right: 10px;">
        <select id="level-5" name="source" class="select" style="height: 32px" onchange="loadTypes(this.value,6)">
            <option value="">请选择</option>
        </select>
    </div>
    <div id="div6"
         style="display: none;width: 240px;height: 300px; float: left;border: 1px solid #ddd;border-radius: 4px;padding: 5px 5px 5px 5px;margin-right: 10px;">
        <select id="level-6" name="source" class="select" style="height: 32px" onchange="loadTypes(this.value,7)">
            <option value="">请选择</option>
        </select>
    </div>
    <div id="div7"
         style="display: none;width: 240px;height: 300px; float: left;border: 1px solid #ddd;border-radius: 4px;padding: 5px 5px 5px 5px;margin-right: 10px;">
        <select id="level-7" name="source" class="select" style="height: 32px" onchange="loadTypes(this.value,8)">
            <option value="">请选择</option>
        </select>
    </div>
    <div id="div8"
         style="display: none;width: 240px;height: 300px; float: left;border: 1px solid #ddd;border-radius: 4px;padding: 5px 5px 5px 5px;margin-right: 10px;">
        <select id="level-8" name="source" class="select" style="height: 32px">
            <option value="">请选择</option>
        </select>
    </div>
    <div id="div9"
         style="display: none;width: 240px;height: 300px; float: left;border: 1px solid #ddd;border-radius: 4px;padding: 5px 5px 5px 5px;margin-right: 10px;">
        <select id="level-9" name="source" class="select" style="height: 32px">
            <option value="">请选择</option>
        </select>
    </div>
    <div id="div10"
         style="display: none;width: 240px;height: 300px; float: left;border: 1px solid #ddd;border-radius: 4px;padding: 5px 5px 5px 5px;margin-right: 10px;">
        <select id="level-10" name="source" class="select" style="height: 32px">
            <option value="">请选择</option>
        </select>
    </div>
</div>
<script type="text/javascript"
        src="<%=request.getContextPath()%>/assistant/lib/jquery/1.9.1/jquery.min.js"></script>
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
    $(function () {
        initLevel1();
    })
    function initLevel1() {
        $.ajax({
            type: 'POST',
            url: '<%=request.getContextPath()%>/common/getList',
            dataType: 'json',
            data: {
                "code": "parentTypes"
            },
            success: function (data) {
                if (data.code == 0) {
                    var data = data.data;
                    for (var i = 0; i < data.length; i++) {
                        $("#level-1").append($('<option value=' + data[i].L_C_ID + '>' + data[i].L_C_NAME + '</option>'));
                    }
                }
            },
            error: function (data) {
                layer.msg(data.msg, {icon: 2, time: 1000});
            },
        });
    }
    function loadTypes(value, index) {
        $.ajax({
            type: 'POST',
            url: '<%=request.getContextPath()%>/common/selectTypes',
            dataType: 'json',
            data: {
                "id": value
            },
            success: function (data) {
                if (data.code == 0) {
                    document.getElementById("content-div").style.width = index * 262 + "px";
                    document.getElementById("div" + index).style.display = "block";
                    var data = data.data;
                    for (var i = 0; i < data.length; i++) {
                        $("#level-" + index).append($('<option value=' + data[i].L_C_ID + '>' + data[i].L_C_NAME + '</option>'));
                    }
                } else if (data.code == 1) {
                    document.getElementById("content-div").style.width = (index - 1) * 262 + "px";
                    for (var i = 0; i <= (10 - index); i++) {
                        document.getElementById("div" + (index + i)).style.display = "none";
                    }
                }
            },
            error: function (data) {
                layer.msg(data.msg, {icon: 2, time: 1000});
            }
        });
    }
</script>
</body>
</html>
