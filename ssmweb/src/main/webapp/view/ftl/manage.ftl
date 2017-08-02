<#import "layout.ftl" as layout>
<@layout.layout title="数据标准目录" topMenu="admin">
    <#assign rootRoute=request.contextPath />
<div class="row data-content" xmlns="http://java.sun.com/jsf/facelets">
    <div class="col-md-1 page-padding"></div>
    <div class="col-md-10" id="data-table">
        <#--<div class="row data-table-row">-->
            <#--<div id="nav" class="col-md-12"><span><a href="${rootRoute}/index">首页</a>&nbsp;>&nbsp;-->
                <#--<a href="../plat_admin">系统管理</a>&nbsp;>&nbsp;-->
                <#--<a href="#">数据标准目录</a></span>-->
            <#--</div>-->
        <#--</div>-->
        <#--<div class="row data-table-head data-table-row">-->
            <#--<div class="col-md-10"><div class="title"><i class="fa fa-th-large"></i><h4>系统管理</h4></div></div>-->
            <#--<div class="col-md-2"></div>-->
        <#--</div>-->
        <div class="row table-content">
            <div class="col-md-2 data-table-nav">
                <#import "manage-nav.ftl" as platAdminNav><@platAdminNav.platAdminNav menu="datadirectorymng"/>
            </div>
            <div class="col-md-10 data-table-content">


    <#--/* 搜索部分 */-->
                    <form class="form-horizontal" id="formExport" name="formExport">
                        <div class="form-group">
                            <div class="col-lg-3 dt-no-padding">
                                <div class="col-lg-5 data-form-label">
                                    <label class="control-label pull-right">编号或名称：</label>
                                </div>
                                <div class="col-lg-7 data-form-input">
                                    <input type="text" class="form-control" id="directoryInfo" name="directoryInfo" value="${directoryInfo}" maxlength="64"/>
                                    <input type="hidden" class="form-control" id="pageNum" name="pageNum" value="${pageNumber}" />
                                </div>
                            </div>

                            <div class="col-lg-3 dt-no-padding">
                                <div class="col-lg-5 data-form-label">
                                    <label class="control-label pull-right">是否同步：</label>
                                </div>
                                <div class="col-lg-7 data-form-input">
                                    <select class="form-control" id="synchFlag" name="synchFlag" value="${synchFlag}" >
                                        <option value="">请选择</option>
                                        <#if synchFlag == "true">
                                            <option value= "true" selected>已同步数据</option>
                                            <option value= "false" >未同步数据</option>
                                        <#elseif synchFlag == "false">
                                            <option value="true" >已同步数据</option>
                                            <option value="false" selected>未同步数据</option>
                                        <#else>
                                            <option value="true" >已同步数据</option>
                                            <option value="false" >未同步数据</option>
                                        </#if>
                                    </select>
                                </div>
                            </div>
                            <div class="col-lg-1">
                                <button id="btn_filter" type="submit" class="btn btn-primary" onclick="find()">查询</button>
                            </div>
                            <div class="col-lg-2">
                                <button id="btn_clear" type="button" class="btn btn-default  pull-left" >清除查询条件</button>
                            </div>
                            <div class="col-lg-3" style="padding-left: 0px">
                                <a href="../directory/datadirectory" button type="button" class="btn btn-primary pull-right" >创建</a>
                                <a href="javascript:void(0)" button type="button" class="btn btn-primary pull-right"style="margin-right: 5px" onclick="toDownPage()">同步</a>


                            </div>
                        </div>
                         <div class="form-group">
                            <div class="col-lg-3 dt-no-padding"hidden>
                                <div class="col-lg-5 data-form-label">
                                    <label class="control-label pull-right">创建日期：&nbsp;&nbsp;</label>
                                </div>
                                <div class="col-lg-7 data-form-input date-picker-field">
                                    <input id="directoryStartTime" name="startTime" readonly="readonly" class="form-control"
                                           type="text" value="${startTime}" data-date="" data-date-format="yyyy-mm-dd"/>
                                </div>
                            </div>

                            <div class="col-lg-3 dt-no-padding"hidden>
                                <div class="col-lg-5 data-form-label">
                                    <label class="control-label pull-right">至：&nbsp;&nbsp;</label>
                                </div>
                                <div class="col-lg-7 data-form-input date-picker-field">
                                    <input id="directoryEndTime" name="endTime" readonly="readonly" class="form-control"
                                           type="text" value="${endTime}" data-date="" data-date-format="yyyy-mm-dd"/>
                                </div>
                            </div>

                            <div class="col-lg-6">


                            </div>
                        </div>
                    </form>

                <#--表格部分-->
                    <table class="table data-table tableBorderBottom" id="listTable"style="margin-top: -15px;" >
                        <thead>
                        <tr class="data-table-content-head">
                            <th class="text-center" style="width:2%;"></th>
                            <th class="text-center" style="width:14%;">数据标准目录编号</th>
                            <th class="text-center" style="width:14%;">数据标准目录名称</th>
                            <th class="text-center" style="width:14%;">数据标准目录描述</th>
                            <th class="text-center" style="width:15%;">创建日期</th>
                            <th class="text-center" style="width:8%;">版本</th>
                            <th class="text-center" style="width:10%;">操作</th>
                        </tr>
                        </thead>

                        <tbody class="text-center a-admin" id="dataTable">
                        <#--<#assign var = 0 >-->
                            <#list dataDirectoryList as data>
                            <tr>
                            <#-- <#assign dataID=dbaccess.id>-->
                            <#--可通过参数传参，也可通过data-id=${dbaccess.id}，js调用this.data("id")获取到该条索引的id -->
                                <td>
                                    <#if data.type == "synch">

                                    <#else>
                                        <input type="checkbox" name="ids" value="${data.id}">
                                        </input>
                                    </#if>

                                </td>
                                <td class="text-left"> <#if data.directoryNo?length gt 17><i class="iconfont" style="color:#007546;">&#xe618;</i>
                                <#else><i class="iconfont" style="color: red;">&#xe6f1;</i>
                                </#if><a href="../directory/datadirectory/directoryDetail/${data.id}">${data.directoryNo}</a>

                                </td>
                                <td class="longText" title="${data.directoryName}">${data.directoryName}</td>
                                <td class="longText" title="${data.dataDesc}">${data.dataDesc}</td>
                                <td>${data.create_time?string('yyyy-MM-dd')}</td>
                                <#--<td style="display: none">${endpoint.access_id}</td>-->
                                <#--<td style="display: none">${endpoint.access_key}</td>-->
                                <td style="display: none">${data.industryCode}</td>
                                <td style="display: none">${data.businessCode}</td>
                                <td style="display: none">${data.data1stCode}</td>
                                <td style="display: none">${data.data2ndCode}</td>
                                <td style="display: none">${data.dataDetailCode}</td>
                                <td style="display: none">${data.dataPropertyCode}</td>
                                <#if data.version ==0.0>
                                    <td class="longText" title="${data.version}"></td>
                                <#else>
                                    <td class="longText" title="${data.version}"><a href="${rootRoute}/directory/datadirectory/all_version?directoryNo=${data.directoryNo}">V${data.version}</a></td>
                                </#if>
                                <td>
                                    <#if data.type == "synch">
                                        --
                                    <#else>
                                        <a href="javascript:void(0)" onclick="modifyData(this, ${data.id}, ${pageNumber});">修改</a>
                                        | <a href="javascript:void(0)" onclick="deleteData(this, ${data.id}, ${pageNumber});">删除</a>
                                    </#if>
                                </td>
                            </tr>
                            </#list>
                        </tbody>

                    </table>
                    <#if (dataDirectoryList?size != 0)>
                        <div id="actionDiv" class="mtm md ml-md pull-left" style="margin-left: 8px;">
                            <input id="selectAll" class="pagination" type="checkbox">
                            &nbsp;全选 &nbsp;
                            </input>
                        </div>
                    </#if>

                <#if (dataDirectoryList?size == 0)>
                    <label>没有查询到记录。</label>
                </#if>
                <form id="form1">
                    <input type="hidden" name="ids" id="idStr" >
                    </input>
                </form>
                <form id="listForm" action="datadirectorys" method="get">
                    <input id="directoryInfo" type="hidden" name="directoryInfo" value="${directoryInfo}"/>
                    <input id="synchFlag" type="hidden" name="synchFlag" value="${synchFlag}"/>
                    <#include "pagination.ftl">
                </form>
            </div>
        </div>
    </div>
    <div class="col-md-1 page-padding"></div>

<#--弹出模态框-删除-->
    <div class="container">
        <div id="deleteModal" class="modal" role="dialog" aria-labelledby="gridSystemModalLabel">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title" id="gridSystemModalLabel">删除数据标准目录</h4>
                    </div>
                    <div class="modal-body">
                        <p class="text-center lead" id="deleteDatadirectory">是否确认删除该数据标准目录？</p>
                    </div>
                    <div class="modal-footer">
                        <button id="btn_commitDeleteInfo" type="button" class="btn btn-primary" data-dismiss="modal">确定</button>
                        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    </div>
                </div>
                <!-- /.modal-content -->
            </div>
            <!-- /.modal-dialog -->
        </div>
        <!-- /.modal -->
    </div>

    <div class="container">
        <div id="datadirectoryDown" class="modal" role="dialog" aria-labelledby="gridSystemModalLabel">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close" id="closeProgressModel"><span id="closeSynchModel" aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title" id="gridSystemModalLabel">同步进度</h4>
                    </div>
                    <div class="modal-body">
                        <form class="form-horizontal">

                            <div class="progress progress-striped active">
                                <div class="progress-bar progress-bar-success" role="progressbar" id="progressBar1"
                                     aria-valuenow="60" aria-valuemin="0" aria-valuemax="100"
                                     style="width: 0%;">
                                </div>
                            </div>
                            <div id="progress1"></div>

                        </form>
                    </div>

                </div>
                <!-- /.modal-content -->
            </div>
            <!-- /.modal-dialog -->
        </div>
        <!-- /.modal -->
    </div>
    <#--<#include "alert.ftl">-->
</div>
<script src="${rootRoute}/view/js/manage.js"></script>
<#--<script src="${rootRoute}/view/js/data-directory-import.js"></script>-->
</@layout.layout>