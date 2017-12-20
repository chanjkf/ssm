<#--
用法：
    form id必须为listForm, action为请求的地址
    <form id="listForm" action="new_mngt_page" method="get">
        <#include "pagination.ftl">
    </form>
显示样例：
    cur=1, < 1 2 3 4 .. 9 >
    cur=4, < 1 .. 3 4 5 .. 9 >
    cur=6, < 1 .. 5 6 7 .. 9 >
    cur=7, < 1 .. 6 7 8 9 >
-->
<#if pageNumber gt 0 && totalPages gt 0>
    <#--查询页-->
    <#assign pageNumberInput = pageNumber>

    <#--如果查询页大于最大页，选中最大页-->
    <#if pageNumber gt totalPages>
        <#assign pageNumber = totalPages>
    </#if>

<ul class="pagination mtm mb pull-right">

    <#--当前页是第一页-->
    <#if pageNumber == 1>
        <li class="disabled"><a href="#">&lt;</a></li>
    <#else>
        <li><a href="javascript: $.pageSkip(${pageNumber - 1});">&lt;</a></li>
        <#if pageNumber gte 3 && totalPages gte 5>
            <li><a href="javascript: $.pageSkip(1);">1</a></li>
        </#if>
    </#if>

    <#if pageNumber gt 3 && totalPages gt 5>
        <li><a href="#">...</a></li>
    </#if>

    <#if (totalPages - pageNumber) lt 2 && pageNumber gt 2>
        <#if (totalPages == pageNumber) && pageNumber gt 3>
            <li><a href="javascript: $.pageSkip(${pageNumber - 3});">${pageNumber - 3}</a></li>
        </#if>
        <li><a href="javascript: $.pageSkip(${pageNumber - 2});">${pageNumber - 2}</a></li>
    </#if>

    <#--前一页，当前页，后一页-->
    <#if pageNumber gt 1>
        <li><a href="javascript: $.pageSkip(${pageNumber - 1});">${pageNumber - 1}</a></li>
    </#if>

    <li class="active"><a href="javascript: $.pageSkip(${pageNumber});">${pageNumber}</a></li>

    <#if totalPages - pageNumber gte 1>
        <li><a href="javascript: $.pageSkip(${pageNumber + 1});">${pageNumber + 1}</a></li>
    </#if>

    <#if pageNumber lt 3>
        <#if (pageNumber + 2) lt totalPages>
            <li><a href="javascript: $.pageSkip(${pageNumber + 2});">${pageNumber + 2}</a></li>
        </#if>

        <#if (pageNumber lt 2) && (pageNumber + 3 lt totalPages)>
            <li><a href="javascript: $.pageSkip(${pageNumber + 3});">${pageNumber + 3}</a></li>
        </#if>
    </#if>

    <#if (totalPages - pageNumber) gte 3 && totalPages gt 5>
        <li><a href="#">...</a></li>
    </#if>

    <#--当前页是最后一页-->
    <#if pageNumber == totalPages>
        <li class="disabled"><a href="#">&gt;</a></li>
    <#else>
        <#if (totalPages - pageNumber) gte 2>
            <li><a href="javascript: $.pageSkip(${totalPages});">${totalPages}</a></li>
        </#if>
        <li><a href="javascript: $.pageSkip(${pageNumber + 1});">&gt;</a></li>
    </#if>

    <span class="pageSkip" style="margin-left: -20px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;到第&nbsp;
		<input type="hidden" id="pageNumber" name="pageNumber" value="${pageNumber}"/>
		<input type="number" id="pageNumberInput" value="${pageNumberInput}" min="1" style="width: 7%;height: 34px;">&nbsp;&nbsp;页&nbsp;&nbsp;
		<button type="submit" id="submitButton" class="btn btn-default" >确定</button>
	</span>
</ul>
</#if>
