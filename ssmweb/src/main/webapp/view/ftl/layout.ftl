<#--调用该宏处需要指定顶层菜单的激活选项，参数值可以为："index"、"demand"、"supply"、"review"、"auth"、"admin"或"none"，默认是"index"-->
<#macro layout title="数梦工场" topMenu="index">
<#assign rootRoute=request.contextPath />
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>DataMall</title>
    <link type="text/css" rel="stylesheet" href="${rootRoute}/view/css/bootstrap.css">
    <link type="text/css" rel="stylesheet" href="${rootRoute}/view/css/dt-style.css">
    <link type="text/css" rel="stylesheet" href="${rootRoute}/view/css/font-awesome.min.css">
    <link type="text/css" rel="stylesheet" href="${rootRoute}/view/css/fontawesome-stars.css">
    <link type="text/css" rel="stylesheet" href="${rootRoute}/view/css/font/font.css">
</head>

<body style="min-width:1320px;">
    <script src="${rootRoute}/view/js/jquery-1.8.3.js"></script>
    <script src="${rootRoute}/view/js/jquery.ui.js"></script>
    <script src="${rootRoute}/view/js/jquery.cookie.js"></script>
    <script src="${rootRoute}/view/js/bootstrap.min.js"></script>
    <script src="${rootRoute}/view/js/common.js"></script>
    <script src="${rootRoute}/view/js/list.js"></script>
    <script src="${rootRoute}/view/js/modal.js"></script>
    <script src="${rootRoute}/view/js/bootstrap-notify.js"></script>
    <script src="${rootRoute}/view/js/page-header.js"></script>

    <script type="text/javascript" src="${rootRoute}/view/js/simple-search.js"></script>

    <script type="text/javascript" src="${rootRoute}/view/js/bootstrap-datetimepicker.min.js" charset="UTF-8"></script>
    <script type="text/javascript" src="${rootRoute}/view/js/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
    <script type="text/javascript" src="${rootRoute}/view/js/date-picker.js"></script>

    <script type="text/javascript" src="${rootRoute}/view/js/bootstrap-treeview.js"></script>

    <div class="page-body">
        <div class="container-fluid">
            <#--<#import "page-header.ftl" as pageHeader><@pageHeader.pageHeader topMenu="${topMenu}"/>-->
            <#--<#if pageName ??>-->
                <#--<#nested>-->
            <#--<#else>-->
                <div class="fixed-container">
                    <#nested>
                </div>
            <#--</#if>-->
        </div>
        <div class="push"></div>
    </div>
    <#--<#include "page-footer.ftl">-->
    <#--获取数据项详情-->
    <#--<#include "dept-catalog-dataItemDetail-modal.ftl">-->
</body>

</html>

</#macro>