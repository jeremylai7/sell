<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>订单列表</title>
    <link href="https://cdn.bootcdn.net/ajax/libs/twitter-bootstrap/3.0.1/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container">
        <div class="row clearfix">
            <div class="col-md-12 column">
                <table class="table table-bordered">
                    <thead>
                    <tr>
                        <th>订单id</th>
                        <th>姓名 </th>
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
                                <td>详情</td>
                                <td>
                                    <#if order.orderStatus != 2>
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
</body>
</html>