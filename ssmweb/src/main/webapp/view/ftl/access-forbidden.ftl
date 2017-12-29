<#assign rootRoute=request.contextPath />
<#include "base_access.ftl">
<div class="fh5co-loader"></div>
<div id="page">
<#include "base_head.ftl">
    <div class="row table-content">

        <div class="col-md-12 data-table-content top-content bg-white">

            <div class="nors">
                <div class="mt col-md-1">
                    <img src="${rootRoute}/view/img/error.jpg">
                </div>
                <div class="norsSuggest">
                    <h3 class="norsTitle">没有权限访问，请联系管理员</h3>

                    <p class="norsTitle2">请返回<a href="${rootRoute}/index">首页</a></p>

                </div>
            </div>

        </div>
    </div>

    <footer id="fh5co-footer" role="contentinfo">
        <div class="container">
            <div class="row copyright">
                <div class="col-md-12 text-center">
                    <p>
                        <small class="block">&copy; 2017 All Rights Reserved.</small>
                        <small class="block"> ICP证：<a href="http://www.miitbeian.gov.cn/" target="_blank" title="chanjkf">浙ICP备17060564号</a></small>
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

<script src="${rootRoute}/view/js/index.js"></script>

