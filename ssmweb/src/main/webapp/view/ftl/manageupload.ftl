<#assign rootRoute=request.contextPath />
     <form action="${rootRoute}/video/upload" enctype="multipart/form-data" method="post">
         上传文件1：<input type="file" name="file1"><br/>
         <input type="submit" value="提交">
     </form>
