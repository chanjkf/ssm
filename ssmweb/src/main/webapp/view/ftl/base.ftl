<#assign rootRoute=request.contextPath />
<!DOCTYPE html>
<html>
<head lang="en">
    <title>Login</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
    <link href="${rootRoute}/view/css/bootstrap.min.css" rel='stylesheet' type='text/css' />
    <link href="${rootRoute}/view/css/gloab.css" rel='stylesheet' type='text/css' />
    <link href="${rootRoute}/view/css/index.css" rel='stylesheet' type='text/css' />
    <link rel="icon" type="image/png" href="${rootRoute}/view/img/index/head.png">
    <script src="${rootRoute}/view/js/jquery.min.js"></script>
    <script src="${rootRoute}/view/js/jquery.easing.1.3.js"></script>
    <!-- Bootstrap -->
    <script src="${rootRoute}/view/js/bootstrap.min.js"></script>
    <!-- Waypoints -->
    <script src="${rootRoute}/view/js/jquery.waypoints.min.js"></script>
    <!-- Flexslider -->
    <script src="${rootRoute}/view/js/jquery.flexslider-min.js"></script>
    <!-- Main -->
    <script src="${rootRoute}/view/js/main.js"></script>

    <script src="${rootRoute}/view/js/common.js"></script>
    <script src="${rootRoute}/view/js/list.js"></script>

    <script src="${rootRoute}/view/js/jquery-form.js" type="text/javascript"></script>
    <script src="${rootRoute}/view/js/bootstrap-notify.js" type="text/javascript"></script>
    <style>
        input:-webkit-autofill {
            -webkit-box-shadow: 0 0 0px 1000px #262333 inset !important;//关于解决输入框背景颜色
        -webkit-text-fill-color: rgba(255,255,255,1)!important;//关于接输入框文字颜色
        }

    </style>
</head>

<body style="min-width:1320px;">
<script>
    $(document).ready(function(c) {
    $('.close').on('click', function(c){
        $('.login-form').fadeOut('slow', function(c){
            $('.login-form').remove();
        });
    });
});
</script>

</body>

</html>