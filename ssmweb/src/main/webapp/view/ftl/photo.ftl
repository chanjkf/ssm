<#assign rootRoute=request.contextPath />
<#include "base1.ftl">
<div class="fh5co-loader"></div>
<div id="page">

<#include "base_head.ftl">
    <div id="fh5co-blog">
        <div class="container">

            <div class="row">
                <#list datas as data>
                    <div class="col-md-4">
                        <div class="fh5co-blog animate-box">
                            <a href="#" class="blog-bg" style="background-image: url(${rootRoute}${data.url});"></a>
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
</div>

<script src="${rootRoute}/view/js/photo.js"></script>

