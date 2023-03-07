package com.astar.wx.mp.builder;

import com.astar.wx.mp.utils.OpenAIAPI;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutTextMessage;

import java.util.HashMap;
import java.util.Map;

/**
 * Astar（一颗星）
 */
public class TextBuilder extends AbstractBuilder {
    public  static  final Map<String, Integer> dataMap = new HashMap<>();


    @Override
    public WxMpXmlOutMessage build(String content, WxMpXmlMessage wxMessage,
                                   WxMpService service) {
        if ("我的信息".equals(wxMessage.getContent())) {
            String text = "用户：" + wxMessage.getFromUser() + "\n" +
                "剩余次数：" + dataMap.get(wxMessage.getFromUser());
            WxMpXmlOutTextMessage m = WxMpXmlOutMessage.TEXT().content(text)
                .fromUser(wxMessage.getToUser()).toUser(wxMessage.getFromUser())
                .build();
            return m;
        }
        if (!"ou9Qi531biZIdwQb28fRbNRNQtts".equals(wxMessage.getFromUser())) {
            // 计数
            Integer count = dataMap.get(wxMessage.getFromUser());
            count = count == null ? 20 : count;
            int i = count - 1;
            if (i <= 0) {
                WxMpXmlOutTextMessage m = WxMpXmlOutMessage.TEXT().content("次数不足，请联系Q：2754522801获取免费次数")
                    .fromUser(wxMessage.getToUser()).toUser(wxMessage.getFromUser())
                    .build();
                dataMap.put(wxMessage.getFromUser(), count);
                return m;
            }
        } else {
            if (wxMessage.getContent().contains("充值用户")) {
                // 充值用户:ou9Qi531biZIdwQb28fRbNRNQtts-100
                String push = wxMessage.getContent();
                String[] user = push.split(":")[1].split("-");
                String openId = user[0];
                Integer cishu = Integer.parseInt(user[1]);
                dataMap.put(openId, dataMap.get(wxMessage.getFromUser()).intValue() + cishu.intValue());
                WxMpXmlOutTextMessage m = WxMpXmlOutMessage.TEXT().content("充值成功：余额：" + dataMap.get(wxMessage.getFromUser()))
                    .fromUser(wxMessage.getToUser()).toUser(wxMessage.getFromUser())
                    .build();
                return m;
            }
        }
        WxMpXmlOutTextMessage m = WxMpXmlOutMessage.TEXT().content(OpenAIAPI.chat(wxMessage.getContent()))
            .fromUser(wxMessage.getToUser()).toUser(wxMessage.getFromUser())
            .build();
        return m;
    }

}
