<#assign rootRoute=request.contextPath />
<#include "base1.ftl">
<div class="fh5co-loader"></div>
<div id="page">
<#include "base_head.ftl">

	<div id="fh5co-blog" class="data-table-content blog_css" style="">
		<div class="container">
			<div class="bg-white form-horizontal">
				<form id="form1" name="form1" method="post" enctype="multipart/form-data" action="${rootRoute}/album/upload">

					<div class="form-group">
						<label class="control-label col-md-3"><span class="required">*</span>文件:</label>
						<div class="col-md-6">
							<input type="file" name="files" id="upload_file" style="width:80%; height: 35px;margin-top: 10px;" maxlength="64">
							<small class="error1 text-danger"></small>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-md-3"><span class="required">*</span>名称:</label>
						<div class="col-md-6">
							<input class="form-control" type="text" id="name" name="name" style="width:80%; height: 35px;margin-top: 5px;" maxlength="64">
							<small class="error2 text-danger"></small>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-md-3"><span class="required">*</span>描述:</label>
						<div class="col-md-6">
							<input class="form-control" type="text" id="desc" name="desc" style="width:80%; height: 35px;margin-top: 5px;" maxlength="64">
							<small class="error3 text-danger"></small>
						</div>
					</div>

				</form>

					<div class="form-group">
						<div class="col-md-6 col-md-offset-3">
							<button class="btn btn-primary" id="submit" onclick="submitPic()">提交</button>
							<button class="btn btn-default" id="cancel">取消</button>
						</div>
					</div>

			</div>
		</div>
	<#include "alert.ftl">

        <div class="modal fade" id="myModal" tabindex="-1" role="dialog"
             aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal"
                                aria-hidden="true">×</button>
                        <h4 class="modal-title" id="myModalLabel">文件上传进度</h4>
                    </div>
                    <div class="modal-body">
                        <progress id="progressBar" value="0" max="100"
                                  style="width: 100%;height: 20px; "> </progress>
                        <span id="percentage" style="color:blue;"></span> <br>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                        </button>
                    </div>
                </div>
                <!-- /.modal-content -->
            </div>
            <!-- /.modal -->
        </div>
	</div>
<footer id="fh5co-footer" role="contentinfo">
	<div class="container">
		<div class="row copyright">
			<div class="col-md-12 text-center">
				<p>
					<small class="block">&copy; 2017 All Rights Reserved.</small>
					<small class="block">More Templates <a href="http://www.chanjkf.xyz/index" target="_blank" title="chanjkf">chanjkf</a></small>
				</p>
				<p>
					<ul class="fh5co-social-icons">
						<li><a href="#"><i class="icon-twitter"></i></a></li>
						<li><a href="#"><i class="icon-facebook"></i></a></li>
						<li><a href="#"><i class="icon-linkedin"></i></a></li>
						<li><a href="#"><i class="icon-dribbble"></i></a></li>
					</ul>
				</p>
			</div>
		</div>

	</div>
</footer>
</div>

<script src="${rootRoute}/view/js/video.js"></script>

