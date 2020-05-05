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
                <div class="col-md-6 column">
                    <table class="table table-bordered">
                        <thead>
                        <tr>
                            <th>订单id</th>
                            <th>订单总金额</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>${order.orderId}</td>
                            <td>${order.orderAmount}</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        <#--订单详情-->
            <div class="row clearfix">
                <div class="col-md-12 column">
                    <table class="table table-bordered">
                        <thead>
                        <tr>
                            <th>商品id</th>
                            <th>商品名称</th>
                            <th>价格</th>
                            <th>数量</th>
                            <th>总额</th>
                        </tr>
                        </thead>
                        <tbody>
                        <#list order.orderDetailList as detail>
                        <tr>
                            <td>${detail.productId}</td>
                            <td>${detail.productName}</td>
                            <td>${detail.productPrice}</td>
                            <td>${detail.productQuantity}</td>
                            <td>${detail.productPrice * detail.productQuantity}</td>
                        </tr>
                        </#list>
                        </tbody>
                    </table>
                </div>
            </div>
        <#--订单操作-->
            <div class="row clearfix">
            <#if order.orderStatus == 0>
                <div class="col-md-12 column">
                    <a href="/sell/seller/order/finish?orderId=${order.orderId}" type="button" class="btn btn-default btn-primary">完结订单</a>
                    <a href="/sell/seller/order/cancel?orderId=${order.orderId}" type="button" class="btn btn-default btn-danger">取消订单</a>
                </div>
            </#if>

            </div>
        </div>
    </div>
</div>
</body>
</html>