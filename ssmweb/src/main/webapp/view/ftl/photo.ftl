<#import "layout.ftl" as layout>
<@layout.layout title="首页" topMenu="index">
    <#assign rootRoute=request.contextPath />
<div class="fh5co-loader"></div>
<div id="page">
    <div id="fh5co-blog">
        <div class="container">

            <div class="row">
                <#list datas as data>
                    <div class="col-md-4">
                        <div class="fh5co-blog animate-box">
                            <a href="#" class="blog-bg" style="background-image: url(${data.url});"></a>
                            <div class="blog-text">
                                <span class="posted_on">Feb. 15th 2016</span>
                                <h3><a href="#">${data.imgName}</a></h3>
                                <p>${data.description}</p>
                                <ul class="stuff">
                                    <li><i class="icon-heart2"></i>0</li>
                                    <li><i class="icon-eye2"></i>0</li>
                                    <li><a href="#">Read More<i class="icon-arrow-right22"></i></a></li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </#list>
            </div>
            <form id="listForm" action="${rootRoute}/album/index" method="get">
            <#--<input id="" type="hidden" name="directoryInfo" value="${directoryInfo}"/>-->
                <#include "pagination.ftl">
            </form>
        </div>
    </div>
</div>

<script src="${rootRoute}/view/js/photo.js"></script>
</@layout.layout>
