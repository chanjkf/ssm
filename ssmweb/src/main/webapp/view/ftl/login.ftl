<#assign rootRoute=request.contextPath />
<#include "base.ftl">
<!--SIGN UP-->
<h1>登录页面</h1>
<div id="page">
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
            <input type="text" name="username" class="text" value="" placeholder="用户名" autocomplete="off">
            <div class="key">
                <input class="password2" type="password" name="password" value="" placeholder="密码" autocomplete="off">
            </div>

            <span style="display:block;padding-top: 10px;margin-top: -31px;margin-bottom: 16px;">没有账号？点此<a href="${rootRoute}/register/page">注册</a></span>
            <div class="signin">
                <input type="submit" value="登录" >
            </div>
        </form>
    </div>
</div>

