var pathname = window.location.pathname;
var arr = pathname.split("/");
var proName = arr[1];
var rootPath = "http://" + window.location.host + "/" + proName;
//校验用户名
function checkUserName(){
    var string = $("#username").val();
    var ret = checkInputname(string);
    if (string == "") {
        $("#username").addClass("border-red");
        $(".erro_pass2").html("*用户名不能为空");
        $(".erro_pass2").css("display", "block");
        return false;
    }
    if (string.length > 8) {
        $("#username").addClass("border-red");
        $(".erro_pass2").html("*用户名过长");
        $(".erro_pass2").css("display", "block");
        return false;
    }
    if(!ret){
        $("#username").addClass("border-red");
        $(".erro_pass2").html("*用户名格式错误");
        $(".erro_pass2").css("display", "block");
        return false;
    }else{
        return true;
    }
};
$("#username").blur(function(){
    checkUserName();
});
$("#username").focus(function () {
    $("#username").removeClass("border-red");
    $(".erro_pass2").hide();

});

//校验密码
function checkpassword1(){
    var string = $("#password1").val();
    var ret = checkPassword(string);
    if(string =="" || !ret){
        $("#password1").addClass("border-red");
        $(".erro_pass2").html("*密码格式错误");
        $(".erro_pass2").css("display", "block");
        return false;
    }
    return true;
};
$("#password1").blur(function(){
    checkpassword1();
});
$("#password1").focus(function () {
    $("#password1").removeClass("border-red");
    $(".erro_pass2").hide();
});
//
function checkpassword2(){
    var string = $("#password2").val();
    var ret = checkPassword(string);
    if(string =="" || !ret){
        // $("#password2").addClass("border-red");
        $(".erro_pass2").html("*再次密码格式错误");
        $(".erro_pass2").css("display", "block");
        return false;
    }
    return true;
};
$("#password2").blur(function(){
    checkpassword2();
});
$("#password2").focus(function () {
    // $("#password2").removeClass("border-red");
    $(".erro_pass2").hide();
});


function checkEmail() {
    var email = $("#email").val();
    var regex = /^[A-Za-zd0-9]+([-_.][A-Za-zd]+)*@([A-Za-zd]+[-.])+[A-Za-zd]{2,5}$/;
    if (!regex.test(email)) {
        $("#email").addClass("border-red");
        $(".erro_pass2").html("*邮箱格式错误");
        $(".erro_pass2").css("display", "block");
        return false;
    }
    return true;

}
$("#email").blur(function () {
    checkEmail();
})
$("#email").focus(function () {
    $(".erro_pass2").hide();
})

function checkInputname(string){
    var regex =  /^[a-zA-Z0-9!_,.*@#$]{1,9}$/;
    return regex.test(string);
}
function checkPassword(string){
    var regex =  /^[a-zA-Z0-9!_,.*@#$]{1,8}$/;
    return regex.test(string);
}

function register(){
    var userName = $("#username").val();
    var password1 = $("#password1").val();
    var password2 = $("#password2").val();
    if(!checkUserName()){
        return false;
    }
    if (!checkEmail()) {
        return false;
    }
    if(!checkpassword1()){
        return false;
    }
    if(!checkpassword2()){
        return false;
    }
    if(password1!=password2){
        $("#password1").addClass("border-red");
        $(".erro_pass2").html("*两次密码输入不一致");
        $(".erro_pass2").css("display", "block");
        return false;
    }

    $.post(rootPath+"/register/data",
        {
            username:userName,
            password:password1,
            email:$("#email").val()
        },
        function (data, status) {

            if (data.result == "success") {
                window.location.href = rootPath+"/index";
            } else {
                dmallError(data.result);
            }
        },
        "json");

}