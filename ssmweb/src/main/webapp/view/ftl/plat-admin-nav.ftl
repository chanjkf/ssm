<#--调用该宏处需要指定子菜单的激活选项，参数值可以为："org"、"category"、"goods"、"trade"、"dbaccess"、"securebox"、"config"或"log"，默认是"org"-->
<#macro platAdminNav menu="dbaccess">
<#assign rootRoute = request.contextPath />
<nav id="sidebar" role="navigation" data-step="2" data-intro="Template has &lt;b&gt;many navigation styles&lt;/b&gt;"
     data-position="right">
    <input id="menuValue" class="hidden" type="text" value="${menu}"/>
    <!--左侧导航栏-->
    <div class="sidebar-collapse menu-scroll">
        <ul id="side-menu" class="nav">
            <div class="clearfix"></div>

            <#if Session["platAdminFlag"] == 1>
            <li id="dbaccess">
                <a href="${rootRoute}/plat_admin/endpoint"><span class="menu-title">引擎管理</span></a>
                </li>
            </#if>
            <#if Session["platAdminFlag"] == 1 && Session["metadataMode"] == 0>
                <li id="metadata">
                    <a href="${rootRoute}/plat_admin/metadata"><span class="menu-title">数据元管理</span></a>
                </li>
            </#if>

            <#if Session["securityAuditorFlag"] == 1>
            <li id="securebox">
                <a href="${rootRoute}/plat_admin/securebox"><span class="menu-title">安全空间管理</span></a>
            </li>
            </#if>

            <#if Session["platAdminFlag"] == 1>
            <#--<li id="prefix">-->
                <#--<a href="${rootRoute}/plat_admin/prefix"><span class="menu-title">前段码管理</span></a>-->
            <#--</li>-->
            <#--<li id="subject">
                <a href="${rootRoute}/plat_admin/subjectTable"><span class="menu-title">主题类目管理</span></a>
            </li>-->
            <#--<li id="industry">
                <a href="${rootRoute}/plat_admin/industryTable"><span class="menu-title">行业类目管理</span></a>
            </li>-->
            <#--<li id="resource">-->
                <#--<a href="${rootRoute}/plat_admin/resourceTable"><span class="menu-title">资源形态管理</span></a>-->
            <#--</li>-->
            <li id="template">
                <a href="${rootRoute}/plat_admin/catalogTemplate"><span class="menu-title">编目模板管理</span></a>
            </li>
            <#--<li id="area">
                <a href="${rootRoute}/plat_admin/areaTable"><span class="menu-title">区域管理</span></a>
            </li>-->
            </#if>

            <#if Session["platAdminFlag"] == 1>
                <li id="datadirectorymng">
                    <a href="${rootRoute}/plat_admin/datadirectorys"><span class="menu-title">数据标准目录</span></a>
                </li>
            </#if>

            <#if Session["platAdminFlag"] == 1 || Session["securityAuditorFlag"] == 1>
                <li id="area">
                    <a href="${rootRoute}/plat_admin/areaTable"><span class="menu-title">行政区划管理</span></a>
                </li>
                <#if Session["catalogConfigMode"] != 1>
                <li id="institution">
                    <a href="${rootRoute}/plat_admin/institutionTable"><span class="menu-title">全国组织机构管理</span></a>
                </li>
                </#if>
                <li id="depart">
                    <a href="${rootRoute}/plat_admin/departTable"><span class="menu-title">公安机关机构管理</span></a>
                </li>
            </#if>

            <#if Session["platAdminFlag"] == 1>
            <li id="rolemng">
                <a href="${rootRoute}/plat_admin/rolemng"><span class="menu-title">角色管理</span></a>
            </li>
            <li id="catalogConfig">
                <a href="${rootRoute}/plat_admin/catalogConfig"><span class="menu-title">编目配置管理</span></a>
            </li>
            <li id="homepage">
                <a href="${rootRoute}/plat_admin/homepage"><span class="menu-title">背景管理</span></a>
            </li>
            <li id="log">
                <a href="${rootRoute}/plat_admin/log"><span class="menu-title">操作日志</span></a>
            </li>
            </#if>

        </ul>
    </div>
</nav>
<script type="application/javascript">
    $(document).ready(function() {
        init();

        //for menu highlight
        function init() {

            /* 初始化子菜单 */
            var $menu = $("#menuValue").val();
            if ($menu === "dbaccess") {
                $("#dbaccess").addClass('active');
            } else if ($menu === "securebox") {
                $("#securebox").addClass('active');
            } else if ($menu === "config") {
                $("#config").addClass('active');
//            } else if($menu === "prefix"){
//                $("#prefix").addClass('active');
//            } else if($menu === "subjectTable"){
//                $("#subject").addClass('active');
//            } else if($menu === "industryTable"){
//                $("#industry").addClass('active');
            } else if($menu === "resourceTable"){
                $("#resource").addClass('active');
//            } else if($menu === "areaTable"){
//                $("#area").addClass('active');
            } else if($menu === "datadirectorymng"){
                $("#datadirectorymng").addClass('active');
            } else if($menu === "organTable"){
                $("#organ").addClass('active');
            }else if($menu === "departTable"){
                $("#depart").addClass('active');
            }else if($menu === "areaTable"){
                $("#area").addClass('active');
            }else if($menu === "institutionTable"){
                $("#institution").addClass('active');
            }else if($menu === "rolemng"){
                $("#rolemng").addClass('active');
            }else if($menu === "catalogConfig"){
                $("#catalogConfig").addClass('active');
            }else if($menu === "log"){
                $("#log").addClass('active');
            } else if($menu === "homepage"){
                $("#homepage").addClass('active');
            } else if($menu === "template"){
                $("#template").addClass('active');
            } else if ($menu === "metadata") {
                $("#metadata").addClass('active');
            }
        };
    });
</script>
</#macro>