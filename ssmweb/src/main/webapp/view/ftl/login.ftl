<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
    <meta name="keywords" content="Flat Dark Web Login Form Responsive Templates, Iphone Widget Template, Smartphone login forms,Login form, Widget Template, Responsive Templates, a Ipad 404 Templates, Flat Responsive Templates" />
    <link href="../css/login.css" rel='stylesheet' type='text/css' />
    <script src="../js/jquery.min.js"></script>
    <style>
        input:-webkit-autofill {
            -webkit-box-shadow: 0 0 0px 1000px #262333 inset !important;//关于解决输入框背景颜色
        -webkit-text-fill-color: rgba(255,255,255,1)!important;//关于接输入框文字颜色
        }

    </style>
</head>
<body>
<script>$(document).ready(function(c) {
    $('.close').on('click', function(c){
        $('.login-form').fadeOut('slow', function(c){
            $('.login-form').remove();
        });
    });
});
</script>
<!--SIGN UP-->
<h1>klasikal Login Form</h1>
<div class="login-form">
    <div class="close"> </div>
    <div class="head-info">
        <label class="lbl-1"> </label>
        <label class="lbl-2"> </label>
        <label class="lbl-3"> </label>
    </div>
    <div class="clear"> </div>
    <div class="avtar">
        <img src="../img/login/avtar.png" />
    </div>
    <form method="post" action="/j_spring_security_check">
        <input type="text" name="j_username" class="text" value="Username" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'Username';}" >
        <div class="key">
            <input type="password" name="j_password" value="Password" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'Password';}">
        </div>
        <div class="signin">
            <input type="submit" value="Login" >
        </div>
    </form>

</div>
<div class="copy-rights">
    <p>Copyright &copy; 2015.Company name All rights reserved.More Templates <a href="http://www.cssmoban.com/" target="_blank" title="ģ��֮��">ģ��֮��</a> - Collect from <a href="http://www.cssmoban.com/" title="��ҳģ��" target="_blank">��ҳģ��</a></p>
</div>

</body>
</html>
