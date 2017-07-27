var pathname = window.location.pathname;
var arr = pathname.split("/");
var proName = arr[1];
var rootPath = "http://" + window.location.host + "/" + proName;
//校验用户名
function checkUserName(){
    var string = $("#username").val();
    var ret = checkInputname(string);
    if(string =="" || !ret){
        $("#username").addClass("border-red");
        $(".erro_pass2").html("*用户名格式错误");
        $(".erro_pass2").css("display", "block");
    }else{
        $.get(rootPath+"/checkName",
            {
                username:string
            },
            function (data, status) {

                if (data.result == "success") {
                    $("#register_button").attr("disabled",false);
                } else {
                    $(".erro_pass2").html("用户名已存在");
                    $(".erro_pass2").css("display", "block");
                    $("#register_button").attr("disabled","disabled");
                }
            },
            "json");
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
    }
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
    }
};
$("#password2").blur(function(){
    checkpassword2();
});
$("#password2").focus(function () {
    // $("#password2").removeClass("border-red");
    $(".erro_pass2").hide();
});

function checkInputname(string){
    var regex =  /^[a-zA-Z0-9]{1,6}$/;
    return regex.test(string);
}
function checkPassword(string){
    var regex =  /^[a-zA-Z0-9]{1,8}$/;
    return regex.test(string);
}

function register(){
    var userName = $("#username").val();
    var password1 = $("#password1").val();
    var password2 = $("#password2").val();
    if(userName == ""|| userName == undefined){
        $(".erro_pass2").html("*用户名不能为空");
        $(".erro_pass2").css("display", "block");
        return false
    }
    if(password1 == "" || password1 == undefined){
        $(".erro_pass2").html("*密码不能为空");
        $(".erro_pass2").css("display", "block");
        return false
    }
    if(password2 == "" || password2 == undefined){
        $(".erro_pass2").html("*确认密码不能为空");
        $(".erro_pass2").css("display", "block");
        return false
    }
    if(password1!=password2){
        $("#password1").addClass("border-red");
        $(".erro_pass2").html("*两次密码输入不一致");
        $(".erro_pass2").css("display", "block");
        return false;
    }
    $.post("/register/data",
        {
            username:userName,
            password:password1
        },
        function (data, status) {

            if (data.result == "success") {
                window.location.href = "/index";
            } else {
                dmallError(data.result);
            }
        },
        "json");

}