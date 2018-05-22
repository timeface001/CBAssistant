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
    <title>产品UPC</title>
</head>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 产品管理 <span
        class="c-gray en">&gt;</span> UPC管理 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px"
                                               id="refresh"
                                               href="javascript:location.replace(location.href);" title="刷新"><i
        class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
    <form id="productForm" class="form form-horizontal">

        <div class="row cl">

            <label class="form-label col-xs-1 col-sm-1">类型：</label>
            <div class="formControls col-xs-2 col-sm-2">
                <select name="type" class="select" style="height: 32px">
                    <option value="">请选择</option>
                    <option value="ISBN">ISBN</option>
                    <option value="UPC">UPC</option>
                    <option value="EAN">EAN</option>
                    <option value="ASIN">ASIN</option>
                    <option value="GTIN">GTIN</option>
                    <option value="GCID">GCID</option>
                    <option value="PZN">PZN</option>
                </select>
            </div>


            <label class="form-label col-xs-1 col-sm-1">状态：</label>
            <div class="formControls col-xs-2 col-sm-2">
                <select id="status" name="status" class="select" style="height: 32px">
                    <option value="">请选择</option>
                    <option value="0">未使用</option>
                    <option value="1">已使用</option>
                </select>
            </div>
            <div class="formControls col-xs-1 col-sm-1">
                <button id="search" class="btn btn-success" type="button"><i class="Hui-iconfont">&#xe665;</i>
                </button>
            </div>
            <input type="hidden" name="pState" id="pStatus">
        </div>
    </form>
    <div class="mt-20">

        <div class="cl pd-5 bg-1 bk-gray" id="count-div"><span class="l">

            <a class="btn btn-primary radius" href="javascript:;"
               onclick="addProduct('添加ID','<%=request.getContextPath()%>/assistant/index/product/productid_gen_add.jsp','800')"><i
                    class="Hui-iconfont">
                &#xe600;</i> 添加产品ID</a> </span></div>
        <table id="productTable" class="table table-border table-bordered table-bg table-hover mt-10">
            <thead>
            <tr class="text-c">
                <th width="25"><input type="checkbox" value="" name=""></th>
                <th width="100">产品ID</th>
                <th width="100">类型</th>
                <th width="100">状态</th>
                <th width="100">创建人</th>
                <th width="100">创建时间</th>
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
    $(function () {
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
    /*产品-添加*/
    function addProduct(title, url, w) {
        layer_show(title, url, w);
    }
    function initSelect() {

    }
    /*查询订单*/
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
                "url": "<%=request.getContextPath()%>/productid/gen/list",
                "type": "POST",
                "data": function (d) {
                    return $.extend({}, d, {
                        "data": JSON.stringify(getFormJson("#productForm"))
                    });
                }
            },
            "columns": [
                {"data": "ID"},
                {
                    "data": function (val) {
                        return val.productId == null ? "" : val.productId;
                    }
                },
                {
                    "data": function (val) {
                        return val.type == null ? "" : val.type;
                    }
                },
                {
                    "data": function (val) {
                        return val.useStatus == 0 ? "未使用" : "已使用";
                    }
                },
                {
                    "data": function (val) {
                        return val.userName == null ? "" : val.userName;
                    }
                },
                {
                    "data": function (val) {
                        return getMyDate(val.createTime);
                    }
                }
            ],
            "columnDefs": [
                {
                    "targets": [0],
                    "data": "ID",
                    "render": function (data, type, full) {
                        return "<input type='checkbox' value=" + full.ID + ">"
                    }
                }
            ],
            "rowCallback": function (row, data, displayIndex) {
                $(row).attr("class", "text-c");
            },
            "initComplete": function (settings, json) {

            },
           /* "dom": "t<'dataTables_info'il>p",*/
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
        layer_show("编辑产品", '<%=request.getContextPath()%>/assistant/index/product/product-add.jsp?id=' + id, 800);

    }

    /**
     * 认领操作
     * @param ids
     */
    function claimProduct(ids) {
        if (ids == null) {
            ids = getIDs();
        }

        layer.confirm('确定认领商品吗？', function (index) {
            $.ajax({
                type: 'POST',
                url: '<%=request.getContextPath()%>/product/state',
                dataType: 'json',
                data: {
                    "data": ids,
                    "type": 2
                },
                success: function (data) {
                    if (data.success) {
                        layer.msg(data.msg, {icon: 6, time: 1000});
                        setTimeout(function () {
                            document.getElementById("refresh").click();
                        }, 1000);

                    }
                },
                error: function (data) {
                    layer.msg(data.msg, {icon: 2, time: 1000});
                }
            });
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
                url: '<%=request.getContextPath()%>/product/delete',
                dataType: 'json',
                data: {"data": id},
                success: function (data) {
                    if (data.success) {
                        layer.msg(data.msg, {icon: 6, time: 1000});
                        setTimeout(function () {
                            document.getElementById("refresh").click();
                        }, 1000);

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