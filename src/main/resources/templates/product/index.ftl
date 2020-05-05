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
            <div class="col-md-12 column">
                <form role="form" method="post" action="/sell/seller/product/save">
                    <div class="form-group">
                        <label>名称</label>
                        <input class="form-control" name="productName" value="${(productInfo.productName)!''}"/>
                    </div>
                    <div class="form-group">
                        <label>价格</label>
                        <input class="form-control" name="productPrice" value="${(productInfo.productPrice)!''}"/>
                    </div>
                    <div class="form-group">
                        <label>库存</label>
                        <input type="number" name="productStock" class="form-control" value="${(productInfo.productStock)!''}"/>
                    </div>
                    <div class="form-group">
                        <label>描述</label>
                        <input class="form-control" name="productDescription" value="${(productInfo.productDescription)!''}"/>
                    </div>
                    <div class="form-group">
                        <label>图片</label>
                        <img width="100" height="100" src="${(productInfo.productIcon)!''}"/>
                        <input class="form-control" name="productIcon" value="${(productInfo.productIcon)!''}" />
                    </div>
                    <div class="form-group">
                        <label>类目</label>
                        <select class="form-control" name="categoryType">
                            <#list categories as category>
                                <option value="${category.categoryType}"
                                <#if (productInfo.categoryType)?? && productInfo.categoryType == category.categoryType>
                                    selected
                                </#if>
                                >${category.categoryName}</option>
                            </#list>
                        </select>
                    </div>
                    <input name="productId" type="hidden" value="${(productInfo.productId)!''}">
                    <button type="submit" class="btn btn-primary">提交</button>
                </form>
            </div>
        </div>
    </div>

</div>

</body>
</html>