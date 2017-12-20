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
                                <video width="380" height="210" src=${rootRoute}${data.address} type="video/mp4"
                                       id="player1" poster="${rootRoute}/view/img/index/blog-1.jpg"
                                       controls="controls" preload="none"></video>
                            </div>
                            <div class="blog-text" style="margin-left: 65px;padding-top: 4px;">
                                <span class="posted_on">Feb. 15th 2016</span>
                                <h3><a href="#">${data.imgName}</a></h3>
                                <p>${data.description}</p>
                                <#--<ul class="stuff">-->
                                    <#--<li><i class="icon-heart2"></i>0</li>-->
                                    <#--<li><i class="icon-eye2"></i>0</li>-->
                                <#--</ul>-->
                            </div>
                        </div>
                    </#list>
                </div>
                <form id="listForm" action="${rootRoute}/video/page" method="get">
                    <#--<input id="" type="hidden" name="directoryInfo" value="${directoryInfo}"/>-->
                <#include "pagination.ftl">
                </form>
            </div>
        </div>
    </div>
    <div class="box"></div>
</div>
    <footer id="fh5co-footer" role="contentinfo">
        <div class="container">

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