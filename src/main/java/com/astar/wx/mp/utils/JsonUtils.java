package com.astar.wx.mp.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Astar（一颗星）
 */
public class JsonUtils {
    public static String toJson(Object obj) {
        Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .disableHtmlEscaping()
            .create();
        return gson.toJson(obj);
    }
}
