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
        </div>

    </div>

</body>
</html>