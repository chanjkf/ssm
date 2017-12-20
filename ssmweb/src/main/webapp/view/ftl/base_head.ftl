
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
                        <li class="" id="home"><a href="${rootRoute}/index">Home</a></li>
                        <li class="" id="video123">
                            <a href="${rootRoute}/video/page">视频中心</a>
                        </li>
                        <li class="has-dropdown" id="photo">
                            <a href="${rootRoute}/album/index">Blog</a>
                            <ul class="dropdown">
                                <li><a href="${rootRoute}/album/index">个人图片</a></li>
                                <li><a href="#">日志</a></li>
                                <li><a href="#">笔记</a></li>
                                <li><a href="#">API</a></li>
                            </ul>
                        </li>
                        <li class="has-dropdown">
                            <a href="${rootRoute}/manage/album">管理</a>
                            <ul class="dropdown">
                                <li><a href="${rootRoute}/manage/album">图片管理</a></li>
                                <li><a href="${rootRoute}/manage/video">视频管理</a></li>
                                <li><a href="${rootRoute}/manage/album/type">图片类型</a></li>
                            </ul>
                        </li>
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