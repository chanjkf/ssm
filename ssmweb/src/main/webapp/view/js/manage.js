/**
* Created by Administrator on 2015/7/2.
*/
/* 修改和删除操作的索引值 */
var pathname = window.location.pathname;
var arr = pathname.split("/");
var proName = arr[1];
var rootPath = "http://" + window.location.host + "/" + proName;

function submit() {
    // document.getElementById("form1").setAttribute("action",rootPath+"/excel/export/directorys");
    // document.getElementById("form1").setAttribute("method","POST");
    if($("#file").val()==""){
        $(".error1").html("选择文件");
        return false;
    }
    document.getElementById("form1").submit();
}

$("#file").click(function () {
    $(".error1").html("");
})

