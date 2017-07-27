<#assign rootRoute=request.contextPath />
<#include "base.ftl">
<!--SIGN UP-->
<h1>注册页面</h1>
<div class="login-form">
    <div class="close"> </div>
    <div class="head-info">
        <label class="lbl-1"> </label>
        <label class="lbl-2"> </label>
        <label class="lbl-3"> </label>
    </div>
    <div class="clear"> </div>
    <div class="avtar">
        <img src="${rootRoute}/view/img/login/avtar.png" />
    </div>
    <form method="post" action="${rootRoute}/login">
        <input type="text" id="username" class="text" value="" placeholder="用户名" autocomplete="off">
        <div class="key">
            <input type="password" id="password1" value="" placeholder="密码" autocomplete="off">
        </div>
        <div class="key">
            <input class="password2" type="password" id="password2" value="" placeholder="密码" autocomplete="off">
        </div>
        <div class="signin"style="display:block;padding-top: 10px;margin-top: -25px;margin-bottom: 16px;">
            <small class="erro_pass2" style="font-size: 18px;color: green"></small>
        </div>
        <div class="signin">
            <input type="button"class="button_big" value="注册"id="register_button" onclick="register()" >
        </div>

    </form>

</div>

<div class="copy-rights">
    <p>
        <small class="block">&copy; 2017 All Rights Reserved.</small>
        <small class="block">More Templates <a href="http://www.chanjkf.xyz/index" target="_blank" title="chanjkf">chanjkf</a></small>
    </p>
</div>
<script src="${rootRoute}/view/js/register.js"></script>