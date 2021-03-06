var url = window.location.href;
$().ready(function () {

})

// (function(){
//     var video, output;
//     var scale = 0.8;
//     var initialize = function() {
//         output = document.getElementById("player1");
//         video = document.getElementById("video");
//         video.addEventListener('loadeddata',captureImage);
//     };
//     var captureImage = function() {
//         var canvas = document.createElement("canvas");
//         canvas.width = video.videoWidth * scale;
//         canvas.height = video.videoHeight * scale;
//         canvas.getContext('2d').drawImage(video, 0, 0, canvas.width, canvas.height);
//
//         var img = document.createElement("img");
//         img.src = canvas.toDataURL("image/png");
//         img.width = 400;
//         img.height = 300;
//         output.appendChild(img);
//     };
//     initialize();
// })();
























function initDirectoryPage(pageNumber,totalPages, type) {
    $("#pageNum").empty();

    if (pageNumber == 1) {
        $("#pageNum").append('<li class="disabled pageTag"><a>&lt;</a></li>');
    } else {
        $("#pageNum").append('<li class="pageTag"><a>&lt;</a></li>');
        if (pageNumber >= 3 && totalPages >= 5) {
            $("#pageNum").append('<li class="pageTag"><a>1</a></li>');
        }
    }
    if (pageNumber > 3 && totalPages > 5) {
        $("#pageNum").append('<li class="pageTag"><a>...</a></li>');
    }
    if ((totalPages - pageNumber) < 2 && pageNumber > 2) {
        if ((totalPages == pageNumber) && pageNumber > 3) {
            $("#pageNum").append('<li class="pageTag"><a>' + (parseInt(pageNumber) - 3) + '</a></li>');
        }
        $("#pageNum").append('<li class="pageTag"><a>' + (parseInt(pageNumber) - 2) + '</a></li>');
    }

    if (pageNumber > 1) {
        $("#pageNum").append('<li class="pageTag"><a>' + (parseInt(pageNumber) - 1) + '</a></li>');
    }
    $("#pageNum").append('<li class="active pageTag"><a>' + parseInt(pageNumber) + '</a></li>');
    if ((totalPages - pageNumber) >= 1) {
        $("#pageNum").append('<li class="pageTag"><a>' + (parseInt(pageNumber) + 1) + '</a></li>');
    }
    if (pageNumber < 3) {
        if ((pageNumber + 2) < totalPages) {
            $("#pageNum").append('<li class="pageTag"><a>' + (parseInt(pageNumber) + 2) + '</a></li>');
        }
        if ((pageNumber < 2) && ((pageNumber + 3) < totalPages)) {
            $("#pageNum").append('<li class="pageTag"><a>' + (parseInt(pageNumber) + 3) + '</a></li>');
        }
    }
    if ((totalPages - pageNumber) >= 3 && totalPages > 5) {
        $("#pageNum").append('<li class="pageTag"><a >...</a></li>');
    }

    if (pageNumber == totalPages || type == "create") {
        $("#pageNum").append('<li class="disabled pageTag"><a>&gt;</a></li>');
    } else {
        if ((totalPages - pageNumber) >= 2) {
            $("#pageNum").append('<li class="pageTag"><a>' + totalPages + '</a></li>');
        }
        $("#pageNum").append('<li class="pageTag"><a>&gt;</a></li>');
    }
}
// 点击下面的页码改变的页面数据
$("#pageNum").on("click", ".pageTag", function () {
    if (!$(this).hasClass("disabled")) {
        var pageNumber = $(this).children("a").text();
        var active = parseInt($("#pageNum li.active a").text());
        if (pageNumber != "<" && pageNumber != ">"  && pageNumber!= '...') {
            getDirectoryCode(parseInt(pageNumber));
        } else if (pageNumber == "<") {
            getDirectoryCode(parseInt(active - 1));
        } else if (pageNumber == ">") {
            getDirectoryCode(parseInt(active + 1));
        }
    }
});
initDirectoryPage(1,1);