<#assign rootRoute=request.contextPath />
<!-- HOME 1 -->
<#include "base_head.ftl">
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
                <button type="button" class="access-btn" data-toggle="modal" data-target="#enquirypopup">咱不支持登陆</button>
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
                <div class="row" style="">
                    <video id="my_video_1″" class="video-js vjs-default-skin" controls preload="auto" width="800" height="640"
                           data-setup="{}">
                        <source src="${rootRoute}/video/${movie}.mp4" type="video/mp4">
                    </video>
                </div>
            </section>
        </div>
    </div>
</div>
<!-- TABS -->


<!-- JAVA SCRIPT -->
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="js/jquery-1.12.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/lity.js"></script>
<script>
    $(".nav .dropdown").hover(function() {
        $(this).find(".dropdown-toggle").dropdown("toggle");
    });
</script>
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
                <form name="info_form" class="form-inline" action="#" method="post">
                    <div class="form-group col-sm-12">
                        <input type="text" class="form-control" name="name" id="name" placeholder="Enter Name">
                    </div>
                    <div class="form-group col-sm-12">
                        <input type="email" class="form-control" name="email" id="email" placeholder="Enter Email">
                    </div>
                    <div class="form-group col-sm-12">
                        <button class="subscribe-btn pull-right" type="submit" title="Subscribe">Subscribe</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>