<%--
  Created by IntelliJ IDEA.
  User: s
  Date: 2018/5/29
  Time: 15:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <style>
        body
        {
            background: #fff;
            height: 100%;
            font-family: Arial, Helvetica, sans-serif;
            font-size: 10pt;
        }
        .img-div img {
            width: 200px;
            height: 200px;
            float: left;
        }

        .img-div {
            width: 100%;
            height: 200px;
        }

        .sorthelper {
            background-color: #f00;
            float: left;
            width: 200px;
            height: 200px;
        }

        .sortableactive {
        }

        .sortablehover {
        }
    </style>

</head>
<body>
<div id="div" class="img-div">
    <img src="http://photos.tuchong.com/38538/f/6864556.jpg" class="sortableitem">
    <img src="http://photos.tuchong.com/349669/f/6695960.jpg" class="sortableitem">
    <img src="http://photos.tuchong.com/349669/f/6683901.jpg" class="sortableitem">
    <img src="http://photos.tuchong.com/349669/f/5121337.jpg" class="sortableitem">
</div>
<script type="text/javascript"
        src="<%=request.getContextPath()%>/assistant/lib/interface/jquery.js"></script>
<script type="text/javascript"
        src="<%=request.getContextPath()%>/assistant/lib/interface/interface.js"></script>
<script>
    $(document).ready(function () {
        $('#div').Sortable({
            accept: 'sortableitem',   //拖拽元素class名
            helperclass: 'sorthelper',     //拖拽时投放位置的样式
            activeclass: 'sortableactive', //拖拽时悬空时class
            hoverclass: 'sortablehover',  //拖拽时经过时class
            opacity: 0.8,              //拖拽时透明度
            fx: 200,              //拖拽时回位速度
            revert: true,
            floats: true,
            tolerance: 'pointer'
        });
        /*
         // 正在拖动的图片的父级DIV
         var $srcImgDiv = null;

         // 开始拖动
         $(".img-div img").bind("dragstart", function () {
         $srcImgDiv = $(this).parent();
         });

         // 拖动到.drop-left,.drop-right上方时触发的事件
         $(".drop-left,.drop-right").bind("dragover", function (event) {

         // 必须通过event.preventDefault()来设置允许拖放
         event.preventDefault();
         });*/

        // 结束拖动放开鼠标的事件
        /* $(".drop-left").bind("drop", function (event) {
         event.preventDefault();
         if ($srcImgDiv[0] != $(this).parent()[0]) {
         $(this).parent().before($srcImgDiv);
         }
         });
         $(".drop-right").bind("drop", function (event) {
         event.preventDefault();
         if ($srcImgDiv[0] != $(this).parent()[0]) {
         $(this).parent().after($srcImgDiv);
         }
         });*/
    });
    function changedata() {
    }
</script>
</body>
</html>
