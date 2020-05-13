package com.jeremy.service;

import com.jeremy.alter.AlteringOrder;
import com.jeremy.config.WeixinConfig;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Auther: laizc
 * @Date: 2020/5/10 21:04
 * @Description:
 */
@Service
@Slf4j
public class PushMessageServiceImpl implements PushMessageService{

    @Autowired
    private WxMpService wxMpService;

    @Autowired
    private WeixinConfig weixinConfig;

    @Override
    public void orderStatus(AlteringOrder alteringOrder) {
        WxMpTemplateMessage templateMessage = new WxMpTemplateMessage();
        templateMessage.setToUser(alteringOrder.getBuyerOpenid());
        templateMessage.setTemplateId(weixinConfig.getTemplateId().get("orderStatus"));
        List<WxMpTemplateData> data = Arrays.asList(
                new WxMpTemplateData("name","value"),
                new WxMpTemplateData("name","value")
                //.....
        );
        templateMessage.setData(data);
        try {
            wxMpService.getTemplateMsgService().sendTemplateMsg(templateMessage);
        } catch (WxErrorException e) {

            e.printStackTrace();
        }
    }
}
