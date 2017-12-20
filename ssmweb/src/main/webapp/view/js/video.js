
var pathname = window.location.pathname;
var arr = pathname.split("/");
var proName = arr[1];
var rootPath = "http://" + window.location.host + "/" + proName;


function submitPic() {
    $("#myModal").modal({backdrop: 'static', keyboard: false});
    var fileObj = document.getElementById("upload_file").files[0]; // js 获取文件对象
    var url = rootPath+"/manage/video/upload"; // 接收上传文件的后台地址
    // FormData 对象---进行无刷新上传
    var form = new FormData();
    form.append("name", $("#name").val()); // 可以增加表单数据
    form.append("desc", $("#desc").val()); // 文件对象
    form.append("file", fileObj); // 文件对象
    // XMLHttpRequest 对象
    var xhr = new XMLHttpRequest();
    xhr.open("post", url, true);
    xhr.onload = function() {
        //$('#myModal').modal('hide');
    };
    //监听progress事件
    xhr.upload.addEventListener("progress", progressFunction, false);
    xhr.send(form);
}
function progressFunction(evt) {

    var progressBar = document.getElementById("progressBar");

    var percentageDiv = document.getElementById("percentage");

    if (evt.lengthComputable) {

        progressBar.max = evt.total;

        progressBar.value = evt.loaded;

        percentageDiv.innerHTML = Math.round(evt.loaded / evt.total * 100)
            + "%";

    }

}

$("#file").click(function () {
    $(".error1").html("");
})

