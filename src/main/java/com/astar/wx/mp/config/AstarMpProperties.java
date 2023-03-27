package com.astar.wx.mp.config;

import com.astar.wx.mp.utils.JsonUtils;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * wechat mp properties
 *
 * Astar（一颗星）
 */
@Data
@ConfigurationProperties(prefix = "astar")
public class AstarMpProperties {
    /**
     * 是否启用模板推送
     */
    private boolean templateEnable;

    @Override
    public String toString() {
        return JsonUtils.toJson(this);
    }
}
