<#assign rootRoute=request.contextPath />
<div class="page-body">
    <div class="container-fluid">
    <#include "head.ftl">
        <section id="slider" style="margin-bottom: 50px;" class="banner" xmlns="http://www.w3.org/1999/html">
            <ul style="position: relative; left: 0%; width: 100%; height: 580px; overflow: hidden;">
                <li style="background-image: url(${rootRoute}/view/img/index/bannernew.jpg); background-size: 100% 100%; float: left; width: 1674px;">
                    <div style="background-color:rgba(0,0,0,0.4);z-index: 7777;">
                        <div class="search-bar" style="min-height:580px;">
                            1
                        </div>
                    </div>
                </li>
            </ul>
        </section>

    <section class="fixed-container" style="width: 1300px!important;">

    </section>
    </div>
    <div class="push"></div>
</div>

<script>
    $("#top-panel").css({background:"transparent",position:"absolute",top:"0",left:"0",right:"0",zIndex:"1",borderBottom:"0"});

    $("#change-org").css({color:"#fff"});
</script>
<#include "page-footer.ftl">