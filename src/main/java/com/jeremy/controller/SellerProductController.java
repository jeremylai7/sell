package com.jeremy.controller;

import com.jeremy.exception.BusineseException;
import com.jeremy.form.ProductForm;
import com.jeremy.model.ProductCategory;
import com.jeremy.model.ProductInfo;
import com.jeremy.service.ProductCategoryService;
import com.jeremy.service.ProductInfoService;
import com.jeremy.util.KeyUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

/**
 * @Auther: laizc
 * @Date: 2020/5/5 10:55
 * @Description: 买家商品
 */
@Controller
@RequestMapping("/seller/product")
public class SellerProductController {

    @Autowired
    private ProductInfoService productInfoService;

    @Autowired
    private ProductCategoryService productCategoryService;

    /**
     * 列表
     * @param page
     * @param pageSize
     * @return
     */
    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "page",defaultValue = "1") Integer page,
                             @RequestParam(value = "pageSize",defaultValue = "10")Integer pageSize){
        ModelAndView modelAndView = new ModelAndView("/product/list");
        PageRequest pageRequest = new PageRequest(page-1,pageSize);
        Page<ProductInfo> productInfoPage = productInfoService.findAll(pageRequest);
        modelAndView.addObject("currentPage",page);
        modelAndView.addObject("pageSize",pageSize);
        modelAndView.addObject("productInfoPage",productInfoPage);
        return modelAndView;
    }

    /**
     * 上架
     * @param productId
     * @return
     */
    @GetMapping("/on-sale")
    public ModelAndView onSale(@RequestParam("productId")String productId){
        try {
            productInfoService.onSale(productId);
        } catch (BusineseException e) {
            ModelAndView view = new ModelAndView("common/error");
            view.addObject("msg",e.getMessage());
            view.addObject("url","/sell/seller/product/list");
            return view;
        }
        ModelAndView modelAndView = new ModelAndView("common/success");
        modelAndView.addObject("msg","商品上架成功");
        modelAndView.addObject("url","/sell/seller/product/list");
        return modelAndView;
    }

    /**
     * 下架
     * @param productId
     * @return
     */
    @GetMapping("/off-sale")
    public ModelAndView offSale(@RequestParam("productId")String productId){
        try {
            productInfoService.offSale(productId);
        } catch (BusineseException e) {
            ModelAndView view = new ModelAndView("common/error");
            view.addObject("msg",e.getMessage());
            view.addObject("url","/sell/seller/product/list");
            return view;
        }
        ModelAndView modelAndView = new ModelAndView("common/success");
        modelAndView.addObject("msg","商品下架成功");
        modelAndView.addObject("url","/sell/seller/product/list");
        return modelAndView;
    }

    /**
     * 跳转订单详情
     * @param productId
     * @return
     */
    @GetMapping("/index")
    public ModelAndView index(String productId){
        ModelAndView view = new ModelAndView("product/index");
        if (StringUtils.isNotBlank(productId)){
            ProductInfo productInfo = productInfoService.findOne(productId);
            view.addObject("productInfo",productInfo);
        }
        List<ProductCategory> categories = productCategoryService.findAll();
        view.addObject("categories",categories);
        return view;

    }

    /**
     * 新增修改
     * @return
     */
    @PostMapping("/save")
    public ModelAndView save(@Valid ProductForm productForm,BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            ModelAndView view = new ModelAndView("common/error");
            view.addObject("msg",bindingResult.getFieldError().getDefaultMessage());
            view.addObject("url","/sell/seller/product/index");
            return view;
        }
        ProductInfo productInfo = new ProductInfo();
        if (StringUtils.isNotBlank(productForm.getProductId())){
            productInfo = productInfoService.findOne(productForm.getProductId());
        }else {
            productForm.setProductId(KeyUtil.getUniqueKey());
        }
        BeanUtils.copyProperties(productForm,productInfo);
        productInfoService.save(productInfo);
        ModelAndView modelAndView = new ModelAndView("common/success");
        modelAndView.addObject("url","/sell/seller/product/list");
        return modelAndView;
    }

}
