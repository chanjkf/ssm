/**
* Created by Administrator on 2015/7/2.
*/
/* 修改和删除操作的索引值 */
var pathname = window.location.pathname;
var arr = pathname.split("/");
var proName = arr[1];
var rootPath = "http://" + window.location.host + "/" + proName;


var dbOpRowIndex = 0;
var curPageNumber = 0;
var preventDefaultFlag = false;
var preventDefaultFlag2 = false;
var isForceRevoke = false;
var id=$("#directoryId").val();

// var $metaDataTree;
// var drawMetaTreeData;

$(document).ready(function () {
    //type页选中蓝色
    var url = document.URL
    var str=[];
    var name;
    str = url.split("/");
    name = str[5];
    str = name.split("?")
    if("datadirectorys" ==str[0] ){
        $("#demandExchange").css("color","#017aff");
    }


var $createButton = $("#createButton");
var $selectAll = $("#selectAll");
var $deleteButton = $("#deleteButton");
var $ids = $("#allEndpointTable input[name='ids']");
var $contentRow = $("#allEndpointTable tr:gt(0)");
var $filter = $("#btn_filter");
var $clear = $("#btn_clear");

/* 需要注意清空绑定关联值 */
    $clear.click(function () {
        $("#directoryInfo").val("");
        $("#synchFlag").val("");
        $('#state').val(-1);
    });

});

function deleteData(obj, id, pageNumber) {
    $.post(rootPath + "/directory/datadirectory/delete",{
            id: dbOpRowIndex,
            isForceRevoke: isForceRevoke
        },
        function (data, status) {
            if (data.result == "success") {
                var erroList = data.erroList;
                if(erroList != null && erroList != undefined && erroList.length != 0){
                    for(var i = 0; i < erroList.length; i++){
                        dmallError(erroList[i]);
                    }


                }else{
                    location.href = "http://" + window.location.host + "/dmall/" + "plat_admin/datadirectorys" + "?directoryNo=" + $("#directoryNo").attr("value")
                        + "&directoryName=" + $("#directoryName").attr("value") + "&startTime=" + $("#directoryStartTime").attr("value") + "&endTime=" + $("#directoryEndTime").attr("value")
                        + "&pageNumber=" + pageNum;
                }
            } else {
                // $(".notifications").empty();
                dmallError(data.result);
            }
        },
        "json");


}


function modifyData(obj, id, pageNumber){
    $.get(rootPath + "/directory/datadirectory/update/check", {
            id:id,
            type:"update"
        },
        function (data) {
            if (data.result == "success") {
                var tds=$(obj).parent().parent().find('td');
                dbOpRowIndex = id;
                location.href = "../directory/datadirectory/"+id;
            }else {
                $(".notifications ").empty();
                dmallError(data.result);
            }
        })
}
$("#pageTag").on("click",".pageTag",function(){
    if(!$(this).hasClass("disabled")){
        var pageNumber = $(this).children("a").text();
        var active = parseInt($("#pageTag li.active a").text());
        if(pageNumber != "<" && pageNumber != ">" && pageNumber!="..."){
            pageNumber = parseInt(pageNumber);
            turnToPage(pageNumber);
        }else if(pageNumber == "<"){
            turnToPage(parseInt(active-1));
        }else if (pageNumber == ">"){
            turnToPage(parseInt(active+1));
        }
    }
});

function initPage(pageNumber,type) {
    $("#pageTag").empty();
    var pageSize = 10;
    var item = momentArr;
    var totalPages;

    if( item.length == 0){
        totalPages = 1;
    }else if(item.length%10 == 0){
        totalPages = item.length/10;
    }else{
        totalPages = parseInt(item.length/10)+1;
    }

    if(pageNumber == 1){
        $("#pageTag").append('<li class="disabled pageTag"><a>&lt;</a></li>');
    }else{
        $("#pageTag").append('<li class="pageTag"><a>&lt;</a></li>');
        if(pageNumber >= 3 && totalPages >= 5){
            $("#pageTag").append('<li class="pageTag"><a>1</a></li>');
        }
    }
    if(pageNumber > 3 && totalPages > 5){
        $("#pageTag").append('<li class="pageTag"><a>...</a></li>');
    }
    if((totalPages - pageNumber) < 2 && pageNumber > 2){
        if((totalPages == pageNumber) && pageNumber > 3){
            $("#pageTag").append('<li class="pageTag"><a>'+(parseInt(pageNumber) - 3)+'</a></li>');
        }
        $("#pageTag").append('<li class="pageTag"><a>'+(parseInt(pageNumber) - 2)+'</a></li>');
    }

    if(pageNumber > 1){
        $("#pageTag").append('<li class="pageTag"><a>'+(parseInt(pageNumber)-1)+'</a></li>');
    }
    $("#pageTag").append('<li class="active pageTag"><a>'+parseInt(pageNumber)+'</a></li>');
    if((totalPages - pageNumber) >= 1){
        $("#pageTag").append('<li class="pageTag"><a>'+(parseInt(pageNumber)+1)+'</a></li>');
    }
    if(pageNumber <3){
        if((pageNumber + 2) < totalPages){
            $("#pageTag").append('<li class="pageTag"><a>'+(parseInt(pageNumber)+2)+'</a></li>');
        }
        if((pageNumber < 2)&& ((pageNumber + 3) < totalPages) ){
            $("#pageTag").append('<li class="pageTag"><a>'+(parseInt(pageNumber)+3)+'</a></li>');
        }
    }
    if((totalPages - pageNumber) >= 3 && totalPages > 5){
        $("#pageTag").append('<li class="pageTag"><a >...</a></li>');
    }

    if(pageNumber == totalPages || type == "create"){
        $("#pageTag").append('<li class="disabled pageTag"><a>&gt;</a></li>');
    }else{
        if((totalPages - pageNumber) >= 2){
            $("#pageTag").append('<li class="pageTag"><a>'+totalPages+'</a></li>');
        }
        $("#pageTag").append('<li class="pageTag"><a>&gt;</a></li>');
    }
}

function turnToPage(pageNumber){
    var arr1 = momentArr.slice((pageNumber-1)*10,pageNumber*10);

    //刷新表格数据
    $("#itemListTable tbody").empty();
    for(var j=0;j<arr1.length;j++){
        $("#itemListTable tbody").append('<tr style="display:table-row">'+
            '<td class="longText" title="'+htmlEncode(arr1[j].name)+'">'+htmlEncode(arr1[j].name)+'</td>'+
            '<td>'+arr1[j].typeDescription+'</td>'+
            '<td class="longText" title="'+htmlEncode(arr1[j].REMARKS)+'">'+htmlEncode(arr1[j].REMARKS)+'</td>'+
            '</tr>');
    }
    initPage(pageNumber);
}

