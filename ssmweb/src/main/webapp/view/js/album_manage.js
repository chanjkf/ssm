var pathname = window.location.pathname;
var arr = pathname.split("/");
var proName = arr[1];
var rootPath = "http://" + window.location.host + "/" + proName;
$().ready(function () {

})

function submitPic() {
    if($("#file").val()==""){
        $(".error1").html("选择文件");
        return false;
    }
    var name = $("#name").val();
    var type = $("#select_type option:selected").val();
    var desc = $("#desc").val();

    var form = $("#form1");
    var options  = {
        url:rootPath + "/manage/album/upload",
        type:'post',
        dataType: 'json',
        clearForm:true,
        success:function(data){
            messageNotify("上传成功");
            window.location.reload();
        }
    };
    form.ajaxSubmit(options);
}
function submit_type() {

    var type = $("#type").val();
    $.post(rootPath+"/album/type",
        {
            type:type
        },

        function (data, status) {
            if (data.result == "success") {

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
    $('#modifyModel').modal({backdrop: 'static', keyboard: false});
    var url = $(obj).parent().find("input").eq(0).val();
    var type = $(obj).parent().find("input").eq(1).val();
    var name = $(obj).parent().find("input").eq(2).val();
    var desc = $(obj).parent().find("input").eq(3).val();
    $("#select_type_modify").find("option[value='"+type+"']").attr("selected",true);
    $("#targetPic").attr("src",url);
    $("#name_modify").val(name);
    $("#desc_modify").val(desc);
}
function deleteData (obj, id, pageNum){

}
