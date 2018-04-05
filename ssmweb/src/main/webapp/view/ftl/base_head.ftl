
<#assign rootRoute=request.contextPath />
<nav class="fh5co-nav" role="navigation">
    <div class="top-menu">
        <div class="container-fluid">
            <div class="row">
                <div class="col-xs-2">
                    <div id="fh5co-logo" style="margin-left: 50px;"><a href="index.ftl">Air<span>.</span></a></div>
                </div>

                <div class="col-xs-2" >
                    <div id="weather" style="margin-top: -15px;">
                        <iframe allowtransparency="true" frameborder="0" width="290" height="96" scrolling="no"
                                src="//tianqi.2345.com/plugin/widget/index.htm?s=1&z=1&t=0&v=0&d=2&bd=0&k=000000&f=&ltf=009944&htf=cc0000&q=1&e=1&a=1&c=54511&w=290&h=96&align=center"></iframe>
                    </div>

                </div>
                <div class="col-xs-8 text-right menu-1">
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
                        <li class="has-dropdown" id="manage">
                            <a href="${rootRoute}/manage/album">管理</a>
                            <ul class="dropdown">
                                <li><a href="${rootRoute}/manage/album">图片管理</a></li>
                                <li><a href="${rootRoute}/manage/video">视频管理</a></li>
                                <li><a href="${rootRoute}/manage/album/type">图片类型</a></li>
                            </ul>
                        </li>
                        <li class="btn-cta" style="margin-left: 50px">
                        <#if SPRING_SECURITY_CONTEXT.authentication.principal.username == "">
                            <a href="${rootRoute}/index/welcome"><span>Login</span></a>
                        <#else>
                            <a href="javascript:void(0)">
										<span>
                                        ${SPRING_SECURITY_CONTEXT.authentication.principal.username}
										</span>
                            </a>
                            <a href="/photo/logout/cas"><span>LogOut</a></span>
                        </#if>
                        </li>

                    </ul>
                </div>
            </div>

        </div>
    </div>
</nav>