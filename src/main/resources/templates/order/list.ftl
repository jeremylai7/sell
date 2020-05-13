<!DOCTYPE html>
<html>
<#include "../common/head.ftl">
<body>
<div id="wrapper" class="toggled">
    <#--侧边栏-->
    <#include "../common/nav.ftl">
    <#--内容-->
    <div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="row clearfix">
                <div class="col-md-12 column">
                    <table class="table table-bordered table-condensed">
                        <thead>
                        <tr>
                            <th>订单id</th>
                            <th>姓名</th>
                            <th>手机号</th>
                            <th>地址</th>
                            <th>金额</th>
                            <th>订单状态</th>
                            <th>支付方式</th>
                            <th>支付状态</th>
                            <th>创建时间</th>
                            <th colspan="2">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <#list orderPage.content as order>
                        <tr>
                            <td>${order.orderId}</td>
                            <td>${order.buyerName}</td>
                            <td>${order.buyerPhone}</td>
                            <td>${order.buyerAddress}</td>
                            <td>${order.orderAmount}</td>
                            <td>${order.getOrderStatusEnum().message}</td>
                            <td>${order.orderStatus}</td>
                            <td>${order.getPayStatusEnum().message}</td>
                            <td>${order.createTime}</td>
                            <td><a href="/sell/seller/order/detail?orderId=${order.orderId}">详情</a></td>
                            <td>
                                <#if order.orderStatus == 0>
                                    <a href="/sell/seller/order/cancel?orderId=${order.orderId}">取消</a>
                                </#if>
                            </td>
                        </tr>
                        </#list>
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="col-md-12 column">
                <ul class="pagination pull-right">
                <#if currentPage lte 1>
                    <li class="disabled"><a href="#">上一页</a></li>
                <#else>
                    <li><a href="/sell/seller/order/list?page=${currentPage -1}&pageSize=${pageSize}">上一页</a></li>
                </#if>

                <#list 1..orderPage.getTotalPages() as idx>
                    <#if currentPage == idx>
                        <li class="disabled"><a href="#">${idx}</a></li>
                    <#else>
                        <li><a href="/sell/seller/order/list?page=${idx}&pageSize=${pageSize}">${idx}</a></li>
                    </#if>
                </#list>
                <#if currentPage gte orderPage.getTotalPages()>
                    <li class="disabled"><a href="#">下一页</a></li>
                <#else>
                    <li><a href="/sell/seller/order/list?page=${currentPage +1}&pageSize=${pageSize}">下一页</a></li>
                </#if>
                </ul>
            </div>
        </div>
        <audio id="notice" controls>
            <source src="/sell/mp3/song.mp3" type="audio/mpeg"/>
        </audio>
    </div>
</div>
<div class="modal fade" id="myMode" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 class="modal-title" id="myModalLabel">
                    提醒
                </h4>
            </div>
            <div class="modal-body">
                你有新的订单
            </div>
            <div class="modal-footer">
                <button onclick="javascript:document.getElementById("notice").pause()" type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button onclick="location.reload()" type="button" class="btn btn-primary">保存</button>
            </div>
        </div>
    </div>
</div>

<script src="https://cdn.bootcdn.net/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="https://cdn.bootcdn.net/ajax/libs/twitter-bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script>
    document.getElementById("notice").play();
    var wesocket = null
    if ("WebSocket" in window){
        wesocket = new WebSocket("ws://selles.natapp1.cc/sell/webSocket")
    }else {
        alert("您的浏览器不支持 WebSocket!");
    }

    wesocket.onopen = function (event) {
        console.log("建立连接");
    }

    wesocket.onmessage = function (event) {
        console.log("发送消息:"+event.data);
        $("#myMode").modal("show");
        //谷歌浏览器禁止音乐播放
        //document.getElementById("notice").play();
    }

    wesocket.onclose = function (event) {
        console.log("连接关闭");
    }

    wesocket.onerror = function () {
        alert("wesocket通信发生错误");
    }

    //关闭页面将wesocket关闭
    window.onbeforeunload = function () {
        wesocket.close();
    }

</script>
</body>
</html>