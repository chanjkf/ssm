
    <#assign rootRoute=request.contextPath />
<div class="row" id="top-panel">
    <input id="rootRoute" class="hidden text" value="${rootRoute}">
    <div id="topNav">
        <div class="col-md-2 col-sm-2 col-xs-2">
            <a href="${rootRoute}/index">
            </a>
        </div>
        <div class="col-md-7 col-sm-7 col-xs-7" id="top-menu">
            <input id="topMenuValue" class="hidden" type="text" value="${topMenu}"/>
            <nav role="navigation">
                <ul class="horizontal-list">
                    <a id="menu-index" href="${rootRoute}/index"
                        <#if (Session["manageFlow"] == 1 && (Session["platDBAFlag"] == 1 || Session["securityAuditorFlag"] == 1)
                        && Session["maintainFlag"] == 0 && Session["platAdminFlag"] == 0)> style="display: none"
                        </#if>>
                        <li>首页</li>
                    </a>
                    <#if Session["manageFlow"] == null || Session["manageFlow"] != 1>
                        <a id="menu-catalog" href="${rootRoute}/catalog">
                            <li><span>数据目录</span></li>
                        </a>

                    <#--<a id="menu-dataapi" href="${rootRoute}/dataapi">-->
                    <#--<li><span>API服务目录</span></li>-->
                    <#--</a>-->

                        <#if Session["userFlag"] == 1>
                            <a id="menu-mydata" href="${rootRoute}/mydata">
                                <li><span>我的数据<span id="myObtainData" class="hidden pending-item-head"></span></span></li>
                            </a>
                        </#if>

                    <#else>

                        <#if Session["catalogFlag"] == 1 || Session["authorFlag"] == 1 || Session["deptAdminFlag"] == 1 ||
                        Session["platAdminFlag"] == 1 || Session["maintainFlag"] == 1 || Session["securityAuditorFlag"] == 1>
                            <a id="menu-exchange" href="${rootRoute}/exchange">
                                <li>
                                    <span>数据交换<span id="demandPending" class="hidden pending-item-head"></span></span>
                                </li>
                            </a>
                        </#if>

                        <#if Session["catalogFlag"] == 1 || Session["reviewFlag"] == 1 || Session["authorFlag"] == 1 || Session["deptAdminFlag"] == 1>
                            <a id="menu-dept-catalog" href="${rootRoute}/dept/content/manage/catalogs">
                                <li><span>部门目录管理<span id="deptCatalogPending" class="hidden pending-item-head"></span></span></li>
                            </a>
                        </#if>

                        <#if Session["maintainFlag"] == 1 || Session["platAdminFlag"] == 1>
                            <a id="menu-center-content" href="${rootRoute}/center/content/manage/catalogs">
                                <li><span>中心目录管理</span><span id="centerCatalogPending" class="hidden pending-item-head"></span></li>
                            </a>
                            <a id="menu-center-service" href="${rootRoute}/center/service/manage/catalogs">
                                <li><span>中心目录服务</span></li>
                            </a>
                        </#if>

                        <#if Session["platDBAFlag"] == 1 && Session["catalogMode"] == 1>
                            <a id="data-base" href="${rootRoute}/center/db/manage/database">
                                <li><span>中间库管理</span><span id="centerDataBasePending" class="hidden pending-item-head"></span></li>
                            </a>
                        </#if>

                        <#if Session["deptAdminFlag"] == 1 || Session["platAdminFlag"] == 1 || Session["securityAuditorFlag"] == 1>
                            <a id="menu-admin" href="${rootRoute}/admin_home">
                                <li><span>管理中心</span></li>
                            </a>
                        </#if>

                    </#if>
                </ul>
            </nav>
        </div>
        <div class="col-md-3 col-sm-3 col-xs-3">
            <div id="top-info">
                <#if topMenu != "index"&& (Session["manageFlow"] == null || Session["manageFlow"] != 1)>
                    <a href="#" id="search-icon" role="button"></a>
                </#if>
                <#if Session["User"] == null || Session["User"] =="">
                    &nbsp;<a href="${rootRoute}/index/welcome" style="padding:8px 20px;background: #017aff;border-radius: 5px;color:#fff">登录</a>
                <#else>
                    <span class="dropdown">
                        <a id="change-org" class="dropdown-toggle" data-orgid="${Session["activeOrgId"]}" data-toggle="dropdown" href="#">
                            <span id="user-name" class="longText">
                            <#if Session["User"] ? length gt 16>${Session["User"] ? substring(0,16)}...<#else >${Session["User"]}</#if>
                                <#if Session["activeOrgName"] != null && Session["activeOrgName"] != "">
                                    (<#if Session["activeOrgName"] ? length gt 8>${Session["activeOrgName"] ? substring(0,8)}...<#else >${Session["activeOrgName"]}</#if>)
                                </#if></span>
                            <span class="caret"></span>
                        </a>
                        <ul class="drop-menu drop-shadow-top" id="drop-profile-menu">
                            <div class="bottom-top-triangle drop-shadow-top"></div>
                            <a href="${rootRoute}/user/profile"><li id="drop-menu-profile">个人信息 </li></a>
                            <#if Session["activeOrgName"] != null && Session["activeOrgName"] != "">
                                <li id="drop-menu-change">切换部门
                                    <ul id="org-list" class="drop-menu drop-shadow-top"></ul>
                                </li>
                            </#if>
                            <a href="${rootRoute}/logout/cas"><li id="drop-menu-logout">退出 <i class="fa fa-sign-out"></i></li></a>
                        </ul>
                    </span>
                </#if>
            </div>
        </div>
    </div>
<#--搜索-->
    <#if topMenu != "index"&& ( Session["manageFlow"] == null || Session["manageFlow"] != 1)>
        <div id="search-cover"></div>
        <div id="search-swapper" class="row col-md-12">
            <div class="col-md-3 col-sm-3 col-xs-3">
                <a href="${rootRoute}/index">
                    <img src="${rootRoute}/view/ftl/oem/logo.png" alt="DataMall" id="header-logo" class="img-responsive" style="max-width:60%;height: 52px">
                </a>
            </div>

            <div class="col-md-8 col-sm-8 col-xs-8">
                <input type="hidden" id="isApi" value="${isApi}">

                <form id="search-field" class="form-inline hidden-print">
                    <div class="row">
                        <div class="col-md-2 col-sm-2 col-xs-2" id="searchResource" hidden>
                            <select id="searchResourceList" name="" class="form-control" style=" width: 100%;">
                            </select>
                        </div>
                        <div id="search-input" class="col-md-8 col-sm-8 col-xs-8">
                            <input class="form-control search-query" type="text" placeholder="请输入要搜索的关键字" id="searchKeys2"
                                   value="<#if searchKeys!="">${searchKeys}</#if>">
                        </div>
                        <div class="col-md-2 col-sm-2 col-xs-2">
                            <button class="btn btn-primary" type="button" id="pageHeadSearch2">搜索</button>
                        <#--<button class="btn btn-default" type="button" id="pageHeadSearchOrg">搜组织</button>-->
                        </div>
                    </div>
                </form>
            </div>

            <div class="col-md-1 col-sm-1 col-xs-1" id="search"><a href="#" id="cross-icon" role="button"></a></div>

        </div>
        <script src="${rootRoute}/view/js/header-search.js"></script>
    </#if>
</div>


<script type="application/javascript">
    $(document).ready(function () {
        init();

        //top menu highlight
        function init() {
            var $topMenu = $("#topMenuValue").val();
            if ($topMenu === "index") {
                $("#menu-index").addClass("active");
            } else if ($topMenu === "catalog") {
                $("#menu-catalog").addClass("active");
            } else if ($topMenu === "dataapi") {
                $("#menu-dataapi").addClass("active");
            } else if ($topMenu === "mydata") {
                $("#menu-mydata").addClass("active");
            } else if ($topMenu === "deptCatalog") {
                $("#menu-dept-catalog").addClass("active");
            } else if ($topMenu === "centerContent") {
                $("#menu-center-content").addClass("active");
            } else if ($topMenu === "centerService") {
                $("#menu-center-service").addClass("active");
            } else if ($topMenu === "exchange") {
                $("#menu-exchange").addClass("active");
            } else if ($topMenu === "admin") {
                $("#menu-admin").addClass("active");
            }else if ($topMenu === "database") {
                $("#data-base").addClass("active");
            } else if ($topMenu === "none") {
                //不激活任何菜单
            }
        };
    });
</script>
