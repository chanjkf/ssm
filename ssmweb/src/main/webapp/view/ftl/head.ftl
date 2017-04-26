<#include "base.ftl">
<#assign rootRoute=request.contextPath />
<div class="page-body">
    <div class="container-fluid">
        <div class="row" id="top-panel">
            <input id="rootRoute" class="hidden text" value="/dmall">
            <div id="topNav">
                <div class="col-md-2 col-sm-2 col-xs-2">
                    <a href="/dmall/index">
                        <img src="${rootRoute}/view/img/index/logo.jpg" alt="" id="header-logo" class="img-responsive" style="height: 90px;">
                    </a>
                </div>
                <div class="col-md-7 col-sm-7 col-xs-7" id="top-menu">
                    <input id="topMenuValue" class="hidden" type="text" value="index"/>
                    <nav role="navigation">
                        <ul class="horizontal-list">
                            <a id="menu-index" href="/dmall/index">
                                <li>首页</li>
                            </a>
                            <a id="menu-catalog" href="/dmall/catalog">
                                <li><span>图片</span></li>
                            </a>
                        </ul>
                    </nav>
                </div>
                <div class="col-md-3 col-sm-3 col-xs-3">
                    <div id="top-info">
                    <span class="dropdown">
                        <a id="change-org" class="dropdown-toggle" data-orgid="1" data-toggle="dropdown" href="#">
                            <span id="user-name" class="longText">
                            chanjkf
                            </span>
                            <span class="caret"></span>
                        </a>
                        <ul class="drop-menu drop-shadow-top" id="drop-profile-menu">
                            <div class="bottom-top-triangle drop-shadow-top"></div>
                            <a href="/dmall/user/profile"><li id="drop-menu-profile">后台代码 </li></a>

                            <a href="/dmall/logout/cas"><li id="drop-menu-logout">退出 <i class="fa fa-sign-out"></i></li></a>
                        </ul>
                    </span>
                    </div>
                </div>
            </div>
        </div>

