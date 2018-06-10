<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>Sortables demo - Interface plugin for jQuery</title>
    <script type="text/javascript"
            src="<%=request.getContextPath()%>/assistant/lib/interface/jquery.js"></script>
    <script type="text/javascript"
            src="<%=request.getContextPath()%>/assistant/lib/interface/interface.js"></script>
    <style type="text/css" media="all">
        body
        {
            background: #fff;
            height: 100%;
            font-family: Arial, Helvetica, sans-serif;
            font-size: 10pt;
        }
        div
        {
            width: 100%;
            height: 200px;
        }
        div img
        {
            float: left;
            width: 200px;
            height: 200px;
        }
        .sorthelper
        {
            background-color: #f00;
            float: left;
        }
        .sortableactive
        {
        }
        .sortablehover
        {
        }
    </style>
</head>
<body>
<div>
    <img src="http://photos.tuchong.com/38538/f/6864556.jpg" class="sortableitem">
    <img src="http://photos.tuchong.com/349669/f/6695960.jpg" class="sortableitem">
    <img src="http://photos.tuchong.com/349669/f/6683901.jpg" class="sortableitem">
    <img src="http://photos.tuchong.com/349669/f/5121337.jpg" class="sortableitem">
</div>
<script type="text/javascript">
    $(document).ready(
            function () {
                $('div').Sortable(
                        {
                            accept : 		'sortableitem',
                            helperclass : 	'sorthelper',
                            activeclass : 	'sortableactive',
                            hoverclass : 	'sortablehover',
                            opacity: 		0.8,
                            /*fx:				200,*/
                            revert:			true,
                            floats:			true,
                            tolerance:		'pointer'
                        }
                )
            }
    );
</script>
</body>
</html>