package com.astar.wx.mp.push;

import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;

/**
 * @author wuzhenyong
 * ClassName:PushTemplateUtil.java
 * date:2023-03-27 15:21
 * Description: 模板推送工具类
 */
@UtilityClass
public class PushTemplateUtil {

    @SneakyThrows
    public void sendMessage(String openId, String question, String chat, WxMpService weixinService) {
        WxMpTemplateMessage templateMessage = WxMpTemplateMessage.builder()
            .toUser(openId)
            .templateId("V90A8am_U4ssb5jLjeDT7gY-8PKAmySqjs69XNRgfNA")
            .url("https://blog.csdn.net/A_yonga?spm=1000.2115.3001.5343")
            .build();

        templateMessage
            .addData(new WxMpTemplateData("first", "微信搜一颗星宇宙公众号体验"))
            .addData(new WxMpTemplateData("keyword1", question))
            .addData(new WxMpTemplateData("keyword2", chat))
            .addData(new WxMpTemplateData("remark", "欢迎加入交流Q群学习：258695438"));

        weixinService.getTemplateMsgService().sendTemplateMsg(templateMessage);
    }
}
