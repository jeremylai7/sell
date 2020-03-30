package com.jeremy.dao;

import com.jeremy.model.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Auther: laizc
 * @Date: 2018/9/1 10:41
 * @Description:
 */
public interface ProductCategoryDao extends JpaRepository<ProductCategory,Integer> {

    List<ProductCategory> findByCategoryTypeIn(List<Integer> list);
}
