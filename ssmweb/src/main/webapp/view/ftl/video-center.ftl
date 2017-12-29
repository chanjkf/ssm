<#import "layout.ftl" as layout>
<@layout.layout title="首页" topMenu="index">
    <#assign rootRoute=request.contextPath />
<div class="fh5co-loader"></div>
<div id="page">
<div>
    <div id="fh5co-features" style="background:white">
        <div class="container">
            <div class="services-padding">
                <div class="row">
                    <#list entityList as data>
                        <div class="col-md-4 animate-box">
                            <div class="feature-left">
                            <#--<h2>Default Skin</h2>-->
                                <video width="380" height="210" src="${data.address}" type="video/mp4"
                                       id="player1" poster="${rootRoute}/view/img/index/blog-1.jpg"
                                       controls="controls" preload="none"></video>
                            </div>
                            <div class="blog-text" style="margin-left: 65px;padding-top: 4px;">
                                <span class="posted_on">Feb. 15th 2016</span>
                                <h3><a href="#">${data.imgName}</a></h3>
                                <p>描述：${data.description}</p>
                                <p><a href="${rootRoute}/video/detail?id=${data.id}">Read More<i class="icon-arrow-right22"></i></a></p>

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

<script src="${rootRoute}/view/js/video-center.js"></script>
<script src="${rootRoute}/view/js/jquery.pagination.js"></script>
</@layout.layout>