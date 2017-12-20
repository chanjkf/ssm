<#--调用该宏处需要指定子菜单的激活选项，参数值可以为："org"、"category"、"goods"、"trade"、"dbaccess"、"securebox"、"config"或"log"，默认是"org"-->
<#macro platAdminNav menu="standard">
<#assign rootRoute = request.contextPath />
<nav id="sidebar" role="navigation" data-step="2" data-intro="Template has &lt;b&gt;many navigation styles&lt;/b&gt;"
     data-position="right">
    <input id="menuValue" class="hidden" type="text" value="${menu}"/>
    <!--左侧导航栏-->
    <div class="sidebar-collapse menu-scroll">
        <ul id="side-menu" class="nav">
            <li id="sync">
                <a href="${rootRoute}/directory/datadirectorys/subordinate?dataSource=synch"><span class="menu-title">同步数据标准目录</span></a>
            </li>

            <li id="local">
                <a href="${rootRoute}/directory/datadirectorys/subordinate?dataSource=local"><span class="menu-title">本地数据标准目录</span></a>
            </li>

            <#if Session["maintainFlag"] == 1>
                <#if Session["datamall_usage"] == 0 || Session["datamall_usage"] == 1 || Session["datamall_usage"] == 2>
                    <li id="draft">
                    <a href="${rootRoute}/directory/drafts"><span class="menu-title">草稿箱
                        <span class="hidden pending-item-head" style="top: inherit" id="directoryDraftSubmitNum"></span></span></a>
                    </li>
                </#if>
                <#if Session["datamall_usage"] == 1>
                    <li id="total">
                        <a href="${rootRoute}/directory/synch/logPage"><span class="menu-title">同步统计</span></a>
                    </li>
                    <li id="record">
                        <a href="${rootRoute}/directory/synch/detailsPage"><span class="menu-title">同步记录</span></a>
                    </li>
                </#if>
                <#if Session["datamall_usage"] == 0 || Session["datamall_usage"] == 1>
                    <li id="version">
                        <a href="${rootRoute}/directory/synch/versionDesc"><span class="menu-title">版本记录</span></a>
                    </li>
                <#elseif Session["datamall_usage"] == 2>
                    <li id="version">
                        <a href="${rootRoute}/directory/synch/versionDesc?type=2"><span class="menu-title">版本记录</span></a>
                    </li>
                </#if>
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
            if ($menu === "syncStandard") {
                $("#sync").addClass('active');
            } else if ($menu === "localStandard") {
                $("#local").addClass('active');
            } else if ($menu === "dataStandard") {
                $("#standard").addClass('active');
            } else if($menu === "draftStandard"){
                $("#draft").addClass('active');
            } else if($menu === "totalStandard"){
                $("#total").addClass('active');
            } else if($menu === "recordStandard"){
                $("#record").addClass('active');
            }else if($menu === "versionStandard"){
                $("#version").addClass('active');
            }
        };
    });
</script>
</#macro>