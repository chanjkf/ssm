<#assign rootRoute=request.contextPath />
<!DOCTYPE html>
<html>
<head lang="en">
    <title>Login</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
    <link href="${rootRoute}/view/css/login.css" rel='stylesheet' type='text/css' />
    <script src="${rootRoute}/view/js/jquery.min.js"></script>
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