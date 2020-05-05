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
                <form role="form" method="post" action="/sell/seller/category/save">
                    <div class="form-group">
                        <label>名称</label>
                        <input class="form-control" name="categoryName" value="${(category.categoryName)!''}"/>
                    </div>
                    <div class="form-group">
                        <label>type</label>
                        <input class="form-control" name="categoryType" value="${(category.categoryType)!''}"/>
                    </div>
                    <input name="categoryId" type="hidden" value="${(category.categoryId)!''}">
                    <button type="submit" class="btn btn-primary">提交</button>
                </form>
            </div>
        </div>
    </div>

</div>

</body>
</html>