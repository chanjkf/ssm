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

            <li id="dbaccess">
                <a href="${rootRoute}/plat_admin/endpoint"><span class="menu-title">引擎管理</span></a>
            </li>

            <li id="metadata">
                <a href="${rootRoute}/plat_admin/metadata"><span class="menu-title">数据元管理</span></a>
            </li>

            <li id="securebox">
                <a href="${rootRoute}/plat_admin/securebox"><span class="menu-title">安全空间管理</span></a>
            </li>










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
            } else if($menu === "resourceTable"){
                $("#resource").addClass('active');
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