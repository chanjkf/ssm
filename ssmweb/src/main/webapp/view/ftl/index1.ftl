<#include "base_head.ftl" >
<#assign rootRoute=request.contextPath />

<!-- HOME 1 -->
<div id="home1" class="container-fluid standard-bg">
    <!-- HEADER -->
    <div class="row header-top">
        <div class="col-lg-3 col-md-6 col-sm-5 col-xs-8">
            <a class="main-logo" href="#"><img src="${rootRoute}/view/img/test/logo.png" class="main-logo img-responsive" alt="Muvee Reviews" title="Muvee Reviews"></a>
        </div>
        <div class="col-lg-6 hidden-md text-center hidden-sm hidden-xs">
            <img src="${rootRoute}/view/img/test/heng.png" class="img-responsive" alt="Muvee Reviews Video Magazine HTML5 Bootstrap">
        </div>
        <div class="col-lg-3 col-md-6 col-sm-7 hidden-xs">
            <div class="right-box">
                <button type="button" class="access-btn"onclick="register()">注册</button>
                <button type="button" class="access-btn"onclick="login()">登陆</button>
            </div>
        </div>
    </div>
    <!-- MENU -->
    <div class="row home-mega-menu ">
        <div class="col-md-12">
            <nav class="navbar navbar-default">
                <div class="navbar-header">
                    <button class="navbar-toggle" type="button" data-toggle="collapse" data-target=".js-navbar-collapse">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                </div>
                <div class="collapse navbar-collapse js-navbar-collapse megabg dropshd ">
                    <ul class="nav navbar-nav">
                        <li><a href="index.index.ftl">Home</a></li>
                        <li><a href="single-video.html">我的图片</a></li>
                        <li><a href="single-page.html">我的日志</a></li>
                        <li><a href="single-page-with-img.html">我的视频</a></li>
                        <li><a href="login.html">？？？</a></li>
                        <li><a href="gallery-video-boxed.html">？？？</a></li>
                        <li><a href="contact.html">？？？</a></li>
                    </ul>
                    <ul class="social">
                        <li class="social-facebook"><a href="#" class="fa fa-facebook social-icons"></a></li>
                        <li class="social-google-plus"><a href="#" class="fa fa-google-plus social-icons"></a></li>
                        <li class="social-twitter"><a href="#" class="fa fa-twitter social-icons"></a></li>
                        <li class="social-youtube"><a href="#" class="fa fa-youtube social-icons"></a></li>
                        <li class="social-rss"><a href="#" class="fa fa-rss social-icons"></a></li>
                    </ul>
                    <div class="search-block">
                        <form>
                            <input type="search" placeholder="Search">
                        </form>
                    </div>
                </div>
                <!-- /.nav-collapse -->
            </nav>
        </div>
    </div>
    <!-- CORE -->
    <div class="row">
        <!-- SIDEBAR -->
        <div class="col-lg-2 col-md-4 hidden-sm hidden-xs">
            <aside class="dark-bg">
                <article>
                    <h2 class="icon"><i class="fa fa-flash" aria-hidden="true"></i>放啥</h2>
                    <ul class="sidebar-links">
                        <li class="fa fa-chevron-right"><a href="#">视频1</a><span>4.000</span></li>
                        <li class="fa fa-chevron-right"><a href="#">视频1</a><span>2.000</span></li>
                        <li class="fa fa-chevron-right"><a href="#">视频1</a><span>650</span></li>
                        <li class="fa fa-chevron-right"><a href="#">视频1</a><span>4.000</span></li>
                        <li class="fa fa-chevron-right"><a href="#">视频1</a><span>7.800</span></li>
                        <li class="fa fa-chevron-right"><a href="#">视频1</a><span>200</span></li>
                        <li class="fa fa-chevron-right"><a href="#">视频1</a><span>15</span></li>
                    </ul>
                </article>
                <div class="clearfix spacer"></div>
                <#--<article>-->
                    <#--<h2 class="icon"><i class="fa fa-gears" aria-hidden="true"></i>categories</h2>-->
                    <#--<ul class="sidebar-links">-->
                        <#--<li class="fa fa-chevron-right"><a href="#">Lifestyle</a><span>4.000</span></li>-->
                        <#--<li class="fa fa-chevron-right"><a href="#">World News</a><span>2.000</span></li>-->
                        <#--<li class="fa fa-chevron-right"><a href="#">Funny videos</a><span>650</span></li>-->
                        <#--<li class="fa fa-chevron-right"><a href="#">Hot Stories</a><span>4.000</span></li>-->
                        <#--<li class="fa fa-chevron-right"><a href="#">Music Clips</a><span>7.800</span></li>-->
                        <#--<li class="fa fa-chevron-right"><a href="#">Premier Trailers</a><span>200</span></li>-->
                    <#--</ul>-->
                <#--</article>-->
                <div class="clearfix spacer"></div>
            </aside>
        </div>
        <!-- HOME MAIN POSTS -->
        <div class="col-lg-10 col-md-8">
            <section id="home-main">
                <h2 class="icon"><i class="fa fa-television" aria-hidden="true"></i>我的视频</h2>
                <div class="row">
                    <!-- ARTICLES -->
                    <div class="col-lg-9 col-md-12 col-sm-12">
                        <div class="row auto-clear">
                            <article class="col-lg-3 col-md-6 col-sm-4">
                                <!-- POST L size -->
                                <div class="post post-medium">
                                    <div class="thumbr">
                                        <a class="afterglow post-thumb" href="${rootRoute}/player?id=1" data-lity>
                                            <span class="play-btn-border" title="Play"><i class="fa fa-play-circle headline-round" aria-hidden="true"></i></span>
                                            <img class="" src="${rootRoute}/view/img/test/1.png" alt="#">
                                        </a>

                                    </div>
                                    <div class="infor">
                                        <h4>
                                            <a class="title" href="#" clolr="red"><font color=blue>朱刚尬舞</font></a>
                                        </h4>
                                        <span class="posts-txt" title="Posts from Channel"><i class="fa fa-thumbs-up" aria-hidden="true"></i>20.895</span>
                                        <div class="ratings">
                                            <i class="fa fa-star" aria-hidden="true"></i>
                                            <i class="fa fa-star" aria-hidden="true"></i>
                                            <i class="fa fa-star-half-o" aria-hidden="true"></i>
                                            <i class="fa fa-star-o"></i>
                                            <i class="fa fa-star-half"></i>
                                        </div>
                                    </div>
                                </div>
                            </article>
                            <article class="col-lg-3 col-md-6 col-sm-4">
                                <!-- POST L size -->
                                <div class="post post-medium">
                                    <div class="thumbr">
                                        <a class="afterglow post-thumb" href="${rootRoute}/player?id=2" data-lity>
                                            <span class="play-btn-border" title="Play"><i class="fa fa-play-circle headline-round" aria-hidden="true"></i></span>
                                            <img class="" src="${rootRoute}/view/img/test/2.png" alt="#">
                                        </a>
                                    </div>
                                    <div class="infor">
                                        <h4>
                                            <a class="title" href="#"><font color=blue>灵魂歌手谢伟</font></a>
                                        </h4>
                                        <span class="posts-txt" title="Posts from Channel"><i class="fa fa-thumbs-up" aria-hidden="true"></i>20.895</span>
                                        <div class="ratings">
                                            <i class="fa fa-star" aria-hidden="true"></i>
                                            <i class="fa fa-star" aria-hidden="true"></i>
                                            <i class="fa fa-star-half-o" aria-hidden="true"></i>
                                            <i class="fa fa-star-o"></i>
                                            <i class="fa fa-star-half"></i>
                                        </div>
                                    </div>
                                </div>
                            </article>
                            <article class="col-lg-3 col-md-6 col-sm-4">
                                <!-- POST L size -->
                                <div class="post post-medium">
                                    <div class="thumbr">
                                        <a class="afterglow post-thumb" href="${rootRoute}/player?id=3" data-lity>
                                            <span class="play-btn-border" title="Play"><i class="fa fa-play-circle headline-round" aria-hidden="true"></i></span>
                                            <img class="" src="${rootRoute}/view/img/test/3.png" alt="#">
                                        </a>
                                    </div>
                                    <div class="infor">
                                        <h4>
                                            <a class="title" href="#"><font color=blue>柔性：老板，俊俊</font></a>
                                        </h4>
                                        <span class="posts-txt" title="Posts from Channel"><i class="fa fa-thumbs-up" aria-hidden="true"></i>20.895</span>
                                        <div class="ratings">
                                            <i class="fa fa-star" aria-hidden="true"></i>
                                            <i class="fa fa-star" aria-hidden="true"></i>
                                            <i class="fa fa-star-half-o" aria-hidden="true"></i>
                                            <i class="fa fa-star-o"></i>
                                            <i class="fa fa-star-half"></i>
                                        </div>
                                    </div>
                                </div>
                            </article>
                            <article class="col-lg-3 col-md-6 col-sm-4">
                                <!-- POST L size -->
                                <div class="post post-medium">
                                    <div class="thumbr">
                                        <a class="afterglow post-thumb" href="${rootRoute}/player?id=4" data-lity>
                                            <span class="play-btn-border" title="Play"><i class="fa fa-play-circle headline-round" aria-hidden="true"></i></span>
                                            <img class="" src="${rootRoute}/view/img/test/4.png" alt="#">
                                        </a>
                                    </div>
                                    <div class="infor">
                                        <h4>
                                            <a class="title" href="#"><font color=blue>激情放光芒</font></a>
                                        </h4>
                                        <span class="posts-txt" title="Posts from Channel"><i class="fa fa-thumbs-up" aria-hidden="true"></i>20.895</span>
                                        <div class="ratings">
                                            <i class="fa fa-star" aria-hidden="true"></i>
                                            <i class="fa fa-star" aria-hidden="true"></i>
                                            <i class="fa fa-star-half-o" aria-hidden="true"></i>
                                            <i class="fa fa-star-o"></i>
                                            <i class="fa fa-star-half"></i>
                                        </div>
                                    </div>
                                </div>
                            </article>
                            <article class="col-lg-3 col-md-6 col-sm-4">
                                <!-- POST L size -->
                                <div class="post post-medium">
                                    <div class="thumbr">
                                        <a class="afterglow post-thumb" href="${rootRoute}/player?id=5" data-lity>
                                            <span class="play-btn-border" title="Play"><i class="fa fa-play-circle headline-round" aria-hidden="true"></i></span>
                                            <img class="" src="${rootRoute}/view/img/test/5.png" alt="#">
                                        </a>
                                    </div>
                                    <div class="infor">
                                        <h4>
                                            <a class="title" href="#"><font color=blue>要抱抱</font></a>
                                        </h4>
                                        <span class="posts-txt" title="Posts from Channel"><i class="fa fa-thumbs-up" aria-hidden="true"></i>20.895</span>
                                        <div class="ratings">
                                            <i class="fa fa-star" aria-hidden="true"></i>
                                            <i class="fa fa-star" aria-hidden="true"></i>
                                            <i class="fa fa-star-half-o" aria-hidden="true"></i>
                                            <i class="fa fa-star-o"></i>
                                            <i class="fa fa-star-half"></i>
                                        </div>
                                    </div>
                                </div>
                            </article>
                            <article class="col-lg-3 col-md-6 col-sm-4">
                                <!-- POST L size -->
                                <div class="post post-medium">
                                    <div class="thumbr">
                                        <a class="afterglow post-thumb" href="${rootRoute}/player?id=6" data-lity>
                                            <span class="play-btn-border" title="Play"><i class="fa fa-play-circle headline-round" aria-hidden="true"></i></span>
                                            <img class="" src="${rootRoute}/view/img/test/6.png" alt="#">
                                        </a>
                                    </div>
                                    <div class="infor">
                                        <h4>
                                            <a class="title" href="#"><font color=blue>上了陈成的床？</font></a>
                                        </h4>
                                        <span class="posts-txt" title="Posts from Channel"><i class="fa fa-thumbs-up" aria-hidden="true"></i>20.895</span>
                                        <div class="ratings">
                                            <i class="fa fa-star" aria-hidden="true"></i>
                                            <i class="fa fa-star" aria-hidden="true"></i>
                                            <i class="fa fa-star-half-o" aria-hidden="true"></i>
                                            <i class="fa fa-star-o"></i>
                                            <i class="fa fa-star-half"></i>
                                        </div>
                                    </div>
                                </div>
                            </article>
                            <article class="col-lg-3 col-md-6 col-sm-4">
                                <!-- POST L size -->
                                <div class="post post-medium">
                                    <div class="thumbr">
                                        <a class="afterglow post-thumb" href="${rootRoute}/player?id=7" data-lity>
                                            <span class="play-btn-border" title="Play"><i class="fa fa-play-circle headline-round" aria-hidden="true"></i></span>
                                            <img class="" src="${rootRoute}/view/img/test/7.png" alt="#">
                                        </a>
                                    </div>
                                    <div class="infor">
                                        <h4>
                                            <a class="title" href="#"><font color=blue>装酷</font></a>
                                        </h4>
                                        <span class="posts-txt" title="Posts from Channel"><i class="fa fa-thumbs-up" aria-hidden="true"></i>20.895</span>
                                        <div class="ratings">
                                            <i class="fa fa-star" aria-hidden="true"></i>
                                            <i class="fa fa-star" aria-hidden="true"></i>
                                            <i class="fa fa-star-half-o" aria-hidden="true"></i>
                                            <i class="fa fa-star-o"></i>
                                            <i class="fa fa-star-half"></i>
                                        </div>
                                    </div>
                                </div>
                            </article>
                            <article class="col-lg-3 col-md-6 col-sm-4">
                                <!-- POST L size -->
                                <div class="post post-medium">
                                    <div class="thumbr">
                                        <a class="afterglow post-thumb" href="${rootRoute}/player?id=8" data-lity>
                                            <span class="play-btn-border" title="Play"><i class="fa fa-play-circle headline-round" aria-hidden="true"></i></span>
                                            <img class="" src="${rootRoute}/view/img/test/8.png" alt="#">
                                        </a>
                                    </div>
                                    <div class="infor">
                                        <h4>
                                            <a class="title" href="#"><font color=blue>？背媳妇</font></a>
                                        </h4>
                                        <span class="posts-txt" title="Posts from Channel"><i class="fa fa-thumbs-up" aria-hidden="true"></i>20.895</span>
                                        <div class="ratings">
                                            <i class="fa fa-star" aria-hidden="true"></i>
                                            <i class="fa fa-star" aria-hidden="true"></i>
                                            <i class="fa fa-star-half-o" aria-hidden="true"></i>
                                            <i class="fa fa-star-o"></i>
                                            <i class="fa fa-star-half"></i>
                                        </div>
                                    </div>
                                </div>
                            </article>
                        </div>
                        <div class="clearfix spacer"></div>
                    </div>
                    <!-- RIGHT ASIDE -->
                    <div class="col-lg-3 hidden-md col-sm-12 text-center top-sidebar">
                        <!-- SUBSCRIBE BOX -->
                        <div class="subscribe-box">
                            <h2 class="icon"><i class="fa fa-plug" aria-hidden="true"></i>subscribe</h2>
                            <!-- SUBSCRIBE FIELD -->
                            <form name="search-submit" method="post" action="#" id="subscribe-submit">
                                <fieldset class="search-fieldset">
                                    <input id="subscribe" type="text" name="search" size="12" class="search-field" placeholder="Your email address" value="">
                                    <button class="subscribe-btn" type="submit" title="Subscribe">Subscribe</button>
                                </fieldset>
                            </form>
                        </div>
                        <!-- SIDEBAR ADVERTISE BOX -->
                    </div>
                </div>
            </section>
        </div>
    </div>
</div>
<!-- TABS -->

<!-- BOTTOM BANNER -->
<div id="bottom-banner" class="container text-center">
    <!-- BOTTOM ADVERTISE BOX -->
    <a href="" class="banner-xl"><font color="red">
      霸王条款：  打开本页面，即代表你同意本人观点，本人有权拒绝接受任何建议，以及威胁</font>
    </a>
</div>
<!-- JAVA SCRIPT -->
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<!-- MODAL -->
<div id="enquirypopup" class="modal fade in " role="dialog">
    <div class="modal-dialog">
        <!-- Modal content-->
        <div class="modal-content row">
            <div class="modal-header custom-modal-header">
                <button type="button" class="close" data-dismiss="modal">×</button>
                <h2 class="icon"><i class="fa fa-television" aria-hidden="true"></i>free access</h2>
            </div>
            <div class="modal-body">
                    <div class="form-group col-sm-12">
                        <input type="text" class="form-control" name="username" id="username" placeholder="name">
                    </div>
                    <div class="form-group col-sm-12">
                        <input type="password" class="form-control" name="password" id="password" placeholder="password">
                    </div>
                    <div class="form-group col-sm-12">
                        <span id ="login_erro" style="color: red"></span>
                        <button class="subscribe-btn pull-right" onclick="login_submit()" ">登陆</button>
                    </div>
            </div>
        </div>
    </div>
</div>
<#--弹出模态框-删除-->
<div class="container">
    <div id="register" class="modal" role="dialog" aria-labelledby="gridSystemModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="gridSystemModalLabel">注册</h4>
                </div>
                <div class="form-group col-sm-12">
                    <input type="text" class="form-control" name="username" id="username_r" placeholder="用户名">
                </div>
                <div class="form-group col-sm-12">
                    <input type="password" class="form-control" name="password" id="password_r" placeholder="密码">
                </div>
                <div class="form-group col-sm-12">
                    <input type="password" class="form-control" name="password" id="password1_r" placeholder="确认密码">
                </div>
                <div class="form-group col-sm-12">
                    <span id ="login_erro" style="color: red"></span>
                    <button class="subscribe-btn pull-right" onclick="register_submit()" ">注册</button>
                    <small class="error text-danger"></small>
                </div>
            </div>
            <!-- /.modal-content -->
        </div>
        <!-- /.modal-dialog -->
    </div>
    <!-- /.modal -->
</div>
<script src="${rootRoute}/view/js/index_1.js"></script>
<#include "base_head.ftl" >