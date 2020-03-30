package com.jeremy.service;

import com.jeremy.model.ProductCategory;

import java.util.List;

/**
 * @Auther: laizc
 * @Date: 2020/3/26 23:40
 * @Description:
 */
public interface ProductCategoryService {

    ProductCategory findOne(Integer categoryId);

    List<ProductCategory> findAll();

    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);

    ProductCategory save(ProductCategory productCategory);
}
