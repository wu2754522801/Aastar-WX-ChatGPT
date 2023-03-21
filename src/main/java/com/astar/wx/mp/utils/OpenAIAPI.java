package com.astar.wx.mp.utils;

import cn.hutool.core.convert.ConvertException;
import cn.hutool.http.HttpException;
import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Astar
 * ClassName:OpenAIAPI.java
 * date:2023-03-03 16:49
 * Description:
 */
@UtilityClass
public class OpenAIAPI {
    /**
     * 聊天端点 楼主提供国内免费调用ChatGPT站点，不需要开梯子，请求稍微慢一点，请耐心等待哦
     */
    String chatEndpoint = "http://nginx.web-framework-1qoh.1045995386668294.us-west-1.fc.devsapp.net/v1/chat/completions";
    /**
     * api密匙
     */
    String apiKey = "Bearer 自己创建的API KEY";

    /**
     * 发送消息
     *
     * @param txt 内容
     * @return {@link String}
     */
    public String chat(String txt) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("model", "gpt-3.5-turbo");
        List<Map<String, String>> dataList = new ArrayList<>();
        dataList.add(new HashMap<String, String>(){{
            put("role", "user");
            put("content", txt);
        }});
        paramMap.put("messages", dataList);
        JSONObject message = null;
        try {
            String body = HttpRequest.post(chatEndpoint)
                .header("Authorization", apiKey)
                .header("Content-Type", "application/json")
                .body(JsonUtils.toJson(paramMap))
                .execute()
                .body();
            JSONObject jsonObject = JSONUtil.parseObj(body);
            JSONArray choices = jsonObject.getJSONArray("choices");
            JSONObject result = choices.get(0, JSONObject.class, Boolean.TRUE);
            message = result.getJSONObject("message");
        } catch (HttpException e) {
            return "请联系作者Q:2754522801进行问题修复";
        } catch (ConvertException e) {
            return "请联系作者Q:2754522801进行问题修复";
        }
        return message.getStr("content");
    }
}
