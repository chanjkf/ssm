var pathname = window.location.pathname;
var arr = pathname.split("/");
var proName = arr[1];
var rootPath = "http://" + window.location.host + "/" + proName;
$().ready(function () {

})
function submit_type() {

    var type = $("#type").val();
    $.post(rootPath+"/manage/album/type",
        {
            type:type
        },

        function (data, status) {
            if (data.result == "success") {
                window.location.reload();
            } else {
            }
        },
        "json");
}
$("#type").blur(function () {
    var val = $("#type").val();
    if (val == null) {
        $(".error2").html("请输入type");
    }
})
$("#type").focus(function () {
    $(".error2").html("");
})
function openCreate() {
    $('#createModel').modal({backdrop: 'static', keyboard: false});
}

function modifyData (obj, id, pageNum){

}
function deleteData (obj, id, pageNum){

}