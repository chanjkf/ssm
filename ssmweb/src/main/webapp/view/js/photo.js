var pathname = window.location.pathname;
var arr = pathname.split("/");
var proName = arr[1];
var rootPath = "http://" + window.location.host + "/" + proName;
$().ready(function () {
    setTimeout(addClass(),10000);
})

function addClass() {
    $("#photo").addClass("active");
}