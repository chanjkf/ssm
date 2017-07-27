
<#assign rootRoute=request.contextPath />
<nav class="fh5co-nav" role="navigation">
    <div class="top-menu">
        <div class="container-fluid">
            <div class="row">
                <div class="col-xs-2">
                    <div id="fh5co-logo" style="margin-left: 50px;"><a href="index.ftl">Air<span>.</span></a></div>
                </div>
                <div class="col-xs-10 text-right menu-1">
                    <ul>
                        <li class="" id="home"><a href="index.ftl">Home</a></li>
                        <li class="" id="video123"><a href="${rootRoute}/video/page">视频中心</a></li>
                        <li class="has-dropdown" id="photo">
                            <a href="blog.html">Blog</a>
                            <ul class="dropdown">
                                <li><a href="${rootRoute}/photo/index">个人图片</a></li>
                                <li><a href="#">日志</a></li>
                                <li><a href="#">笔记</a></li>
                                <li><a href="#">API</a></li>
                            </ul>
                        </li>
                        <li><a href="about.html">About</a></li>
                        <li class="btn-cta" style="margin-left: 50px">
                        <#if SPRING_SECURITY_CONTEXT.authentication.principal.username == "">
                            <a href="${rootRoute}/toLogin/page" ><span>Login</span></a>
                        <#else>
                            <a href="javascript:void(0)">
										<span>
                                        ${SPRING_SECURITY_CONTEXT.authentication.principal.username}
										</span>
                            </a>
                            <a href="${rootRoute}/logout"><span>LogOut</a></span>
                        </#if>
                        </li>

                    </ul>
                </div>
            </div>

        </div>
    </div>
</nav>