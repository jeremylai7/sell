package com.jeremy.controller;

import com.jeremy.model.ProductCategory;
import com.jeremy.model.ProductInfo;
import com.jeremy.service.ProductCategoryService;
import com.jeremy.service.ProductInfoService;
import com.jeremy.util.OutUtil;
import com.jeremy.view.Result;
import com.jeremy.view.ViewProduct;
import com.jeremy.view.ViewProductInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Auther: laizc
 * @Date: 2020/3/28 16:34
 * @Description: 买家商品
 */
@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {
    @Autowired
    private ProductInfoService productInfoService;

    @Autowired
    private ProductCategoryService productCategoryService;
    @GetMapping("/list")
    public Result list(){
        //查询所有上架产品
        List<ProductInfo> productInfoList = productInfoService.findUpAll();
        //查询类目
        List<Integer> categoryTypeList = productInfoList.stream()
                .map(e -> e.getCategoryType())
                .collect(Collectors.toList());
        List<ProductCategory> productCategoryList = productCategoryService.findByCategoryTypeIn(categoryTypeList);
        //拼装数据
        List<ViewProduct> viewProductList = new ArrayList<>();
        for (ProductCategory productCategory : productCategoryList){
            ViewProduct viewProduct = new ViewProduct();
            viewProduct.setCategoryName(productCategory.getCategoryName());
            viewProduct.setCategoryType(productCategory.getCategoryType());
            List<ViewProductInfo> viewProductInfoList = new ArrayList<>();
            for(ProductInfo productInfo : productInfoList){
                if (productCategory.getCategoryType().equals(productInfo.getCategoryType())){
                    ViewProductInfo viewProductInfo = new ViewProductInfo();
                    BeanUtils.copyProperties(productInfo,viewProductInfo);
                    viewProductInfoList.add(viewProductInfo);
                }
            }
            viewProduct.setList(viewProductInfoList);
            viewProductList.add(viewProduct);
        }
        return OutUtil.success(viewProductList);
    }


}
