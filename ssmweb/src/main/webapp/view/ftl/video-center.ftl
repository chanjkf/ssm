<#assign rootRoute=request.contextPath />
<#include "base1.ftl">
<div class="fh5co-loader"></div>
<div id="page">
<#include "base_head.ftl">
<div>

    <div id="fh5co-features" style="background:white">
        <div class="container">
            <div class="services-padding">
                <div class="row">
                    <#list entityList as data>
                        <div class="col-md-4 animate-box">
                            <div class="feature-left">
                            <#--<h2>Default Skin</h2>-->

                                <video width="380" height="210" src=${data.address} type="video/mp4"
                                       id="player1" poster="${rootRoute}/view/img/index/blog-1.jpg"
                                       controls="controls" preload="none"></video>
                            </div>
                        </div>
                    </#list>


                </div>
            </div>
        </div>
    </div>
    <div class="box"></div>
</div>
    <footer id="fh5co-footer" role="contentinfo">
        <div class="container">
            <div class="row row-pb-md">
                <div class="col-md-4 fh5co-widget">
                    <h4>Air</h4>
                    <p>Facilis ipsum reprehenderit nemo molestias. Aut cum mollitia reprehenderit. Eos cumque dicta adipisci architecto culpa amet.</p>
                </div>
                <div class="col-md-2 col-md-push-1 fh5co-widget">
                    <h4>Links</h4>
                    <ul class="fh5co-footer-links">
                        <li><a href="#">Home</a></li>
                        <li><a href="#">Portfolio</a></li>
                        <li><a href="#">Blog</a></li>
                        <li><a href="#">About</a></li>
                    </ul>
                </div>

                <div class="col-md-2 col-md-push-1 fh5co-widget">
                    <h4>Categories</h4>
                    <ul class="fh5co-footer-links">
                        <li><a href="#">Landing Page</a></li>
                        <li><a href="#">Real Estate</a></li>
                        <li><a href="#">Personal</a></li>
                        <li><a href="#">Business</a></li>
                        <li><a href="#">e-Commerce</a></li>
                    </ul>
                </div>

                <div class="col-md-4 col-md-push-1 fh5co-widget">
                    <h4>Contact Information</h4>
                    <ul class="fh5co-footer-links">
                        <li>198 West 21th Street, <br> Suite 721 New York NY 10016</li>
                        <li><a href="tel://1234567920">+ 1235 2355 98</a></li>
                        <li><a href="mailto:info@yoursite.com">info@yoursite.com</a></li>
                        <li><a href="http://#">freehtml5.co</a></li>
                    </ul>
                </div>

            </div>

            <div class="row copyright">
                <div class="col-md-12 text-center">
                    <p>
                        <small class="block">&copy; 2017 All Rights Reserved.</small>
                        <small class="block">More Templates <a href="http://www.chanjkf.xyz/index" target="_blank" title="chanjkf">chanjkf</a></small>
                    </p>
                    <p>
                    <ul class="fh5co-social-icons">
                        <li><a href="#"><i class="icon-twitter"></i></a></li>
                        <li><a href="#"><i class="icon-facebook"></i></a></li>
                        <li><a href="#"><i class="icon-linkedin"></i></a></li>
                        <li><a href="#"><i class="icon-dribbble"></i></a></li>
                    </ul>
                    </p>
                </div>
            </div>

        </div>
    </footer>
    <script src="${rootRoute}/view/js/video-center.js"></script>
    <script src="${rootRoute}/view/js/jquery.pagination.js"></script>