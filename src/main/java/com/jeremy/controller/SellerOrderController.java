package com.jeremy.controller;

import com.jeremy.alter.AlteringOrder;
import com.jeremy.exception.BusineseException;
import com.jeremy.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Auther: laizc
 * @Date: 2020/5/3 23:08
 * @Description:
 */
@CrossOrigin
@Controller
@RequestMapping("/seller/order")
public class SellerOrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "page",defaultValue = "1") Integer page,
                             @RequestParam(value = "pageSize",defaultValue = "10")Integer pageSize){
        ModelAndView modelAndView = new ModelAndView("/order/list");
        Sort sort = new Sort(Sort.Direction.DESC,"createTime");
        PageRequest pageRequest = PageRequest.of(page-1,pageSize,sort);
        Page<AlteringOrder> alteringOrderPage = orderService.findList(pageRequest);
        modelAndView.addObject("currentPage",page);
        modelAndView.addObject("pageSize",pageSize);
        modelAndView.addObject("orderPage",alteringOrderPage);
        return modelAndView;
    }

    /**
     * 订单取消
     * @param orderId
     * @return
     */
    @GetMapping("/cancel")
    public ModelAndView cancel(@RequestParam("orderId")String orderId) {
        try {
            AlteringOrder alteringOrder = orderService.findOne(orderId);
            orderService.cancel(alteringOrder);
        } catch (BusineseException e) {
            ModelAndView view =new ModelAndView("common/error");
            view.addObject("msg",e.getMessage());
            view.addObject("url","/sell/seller/order/list");
            return view;
        }
        ModelAndView modelAndView = new ModelAndView("common/success");
        modelAndView.addObject("msg","订单取消成功");
        modelAndView.addObject("url","/sell/seller/order/list");
        return modelAndView;
    }

    @GetMapping("/detail")
    public ModelAndView detail(@RequestParam("orderId")String orderId){
        AlteringOrder alteringOrder;
        try {
             alteringOrder = orderService.findOne(orderId);
        } catch (BusineseException e) {
            ModelAndView view =new ModelAndView("common/error");
            view.addObject("msg",e.getMessage());
            view.addObject("url","/sell/seller/order/list");
            return view;
        }
        ModelAndView modelAndView = new ModelAndView("order/detail");
        modelAndView.addObject("order",alteringOrder);
        return modelAndView;
    }

    @GetMapping("/finish")
    public ModelAndView finish(@RequestParam("orderId")String orderId){
        try {
            AlteringOrder alteringOrder = orderService.findOne(orderId);
            orderService.finish(alteringOrder);
        } catch (BusineseException e) {
            ModelAndView view =new ModelAndView("common/error");
            view.addObject("msg",e.getMessage());
            view.addObject("url","/sell/seller/order/list");
            return view;
        }
        ModelAndView modelAndView = new ModelAndView("common/success");
        modelAndView.addObject("msg","订单完结成功");
        modelAndView.addObject("url","/sell/seller/order/list");
        return modelAndView;
    }

}
