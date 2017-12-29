<#import "layout.ftl" as layout>
<@layout.layout title="首页" topMenu="index">
    <#assign rootRoute=request.contextPath />
<div class="fh5co-loader"></div>
<div id="page">
    <div id="fh5co-blog" class="blog_css" style="">
        <div class="container">
            <div class="col-lg-12" style="padding-left: 0px;margin-bottom: 20px;">
                <a href="javascript:void(0)" onclick="openCreate()" button type="button" class="btn btn-primary pull-right" >创建</a>
            </div>
            <div class="bg-white form-horizontal">
                <table class="table data-table tableBorderBottom" id="listTable"style="margin-top: -15px;" >
                    <thead>
                    <tr class="data-table-content-head">
                        <th class="text-center" style="width:2%;"></th>
                        <th class="text-center" style="width:14%;">类别</th>
                        <th class="text-center" style="width:14%;">创建时间</th>
                        <th class="text-center" style="width:14%;">创建人</th>
                        <th class="text-center" style="width:10%;">操作</th>
                    </tr>
                    </thead>

                    <tbody class="text-center a-admin" id="dataTable">
                    <#--<#assign var = 0 >-->
                    <#list dataList as data>
                    <tr <#if data.invalid_flag == true>class="changeColor" </#if>>
                    <#-- <#assign dataID=dbaccess.id>-->
                    <#--可通过参数传参，也可通过data-id=${dbaccess.id}，js调用this.data("id")获取到该条索引的id -->
                        <td>
                        </td>

                        <td class="longText" title="${data.typeName}">${data.typeName}</td>
                        <td>${data.create_time?string('yyyy-MM-dd')}</td>
                        <td>${data.creator_id}</td>

                        <td>
                            <a href="javascript:void(0)" onclick="modifyData(this, ${data.id}, ${pageNumber});">修改</a>
                            <a href="javascript:void(0)" onclick="deleteData(this, ${data.id}, ${pageNumber});">删除</a>
                        </td>
                    </tr>
                    </#list>
                    </tbody>

                </table>


                <#--<div class="form-group">-->
                    <#--<label class="control-label col-md-3"><span class="required">*</span>类型:</label>-->
                    <#--<div class="col-md-6">-->
                        <#--<input class="form-control" type="text" id="type" name="type" style="width:80%; height: 35px;margin-top: 5px;" maxlength="64">-->
                        <#--<small class="error2 text-danger"></small>-->
                    <#--</div>-->
                <#--</div>-->

                <#--<div class="form-group">-->
                    <#--<div class="col-md-6 col-md-offset-3">-->
                        <#--<button class="btn btn-primary" id="submit" onclick="submit_type()">提交</button>-->
                        <#--<button class="btn btn-default" id="cancel">取消</button>-->
                    <#--</div>-->
                <#--</div>-->
            </div>
        </div>


        <div class="container">
            <div id="createModel" class="modal" role="dialog" aria-labelledby="gridSystemModalLabel">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                            <h4 class="modal-title" id="gridSystemModalLabel">创建</h4>
                        </div>
                        <div class="modal-body">
                            <div class="form-group">
                                <label class="control-label col-md-3"><span class="required">*</span>类型:</label>
                                <div class="col-md-6">
                                    <input class="form-control" type="text" id="type" name="type" style="width:120%; height: 35px;margin-top: -5px;" maxlength="64">
                                    <small class="error2 text-danger"></small>
                                </div>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button id="btn_commitDeleteInfo" type="button" class="btn btn-primary" onclick="submit_type()" data-dismiss="modal">确定</button>
                            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                        </div>
                    </div>
                    <!-- /.modal-content -->
                </div>
                <!-- /.modal-dialog -->
            </div>
            <!-- /.modal -->
        </div>
    </div>
</div>

<script src="${rootRoute}/view/js/photo_type.js"></script>
</@layout.layout>
