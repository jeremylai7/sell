package com.jeremy.controller;

import com.jeremy.form.CategoryForm;
import com.jeremy.model.ProductCategory;
import com.jeremy.service.ProductCategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

/**
 * @Auther: laizc
 * @Date: 2020/5/5 17:58
 * @Description: 商品类目
 */
@CrossOrigin
@Controller
@RequestMapping("seller/category")
public class SellerCategoryController {
    @Autowired
    private ProductCategoryService productCategoryService;

    /**
     * 列表
     * @return
     */
    @GetMapping("/list")
    public ModelAndView list(){
        ModelAndView view = new ModelAndView("category/list");
        List<ProductCategory> list = productCategoryService.findAll();
        view.addObject("category",list);
        return view;
    }


    @GetMapping("/index")
    public ModelAndView index(Integer categoryId){
        ModelAndView view = new ModelAndView("category/index");
        if (categoryId != null){
            ProductCategory productCategory = productCategoryService.findOne(categoryId);
            view.addObject("category",productCategory);
        }
        return view;
    }

    @PostMapping("/save")
    public ModelAndView save(@Valid CategoryForm categoryForm,BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            ModelAndView view = new ModelAndView("common/error");
            view.addObject("msg",bindingResult.getFieldError().getDefaultMessage());
            view.addObject("url","/sell/seller/category/index");
            return view;
        }
        ProductCategory productCategory = new ProductCategory();
        //修改
        if (categoryForm.getCategoryId() != null){
            productCategory = productCategoryService.findOne(categoryForm.getCategoryId());
        }
        BeanUtils.copyProperties(categoryForm,productCategory);
        productCategoryService.save(productCategory);
        ModelAndView modelAndView = new ModelAndView("common/success");
        modelAndView.addObject("url","/sell/seller/category/list");
        return modelAndView;

    }

}
