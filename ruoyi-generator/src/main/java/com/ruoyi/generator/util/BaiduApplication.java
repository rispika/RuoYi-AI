package com.ruoyi.generator.util;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.ruoyi.common.utils.http.HttpUtils;
import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;
import java.util.List;

@Data
@ToString
@Component
@Slf4j
@ConfigurationProperties(prefix = "baidu")
@PropertySource(value = {"classpath:generator.yml"})
public class BaiduApplication {

    @Value("${api_key}")
    private String API_KEY;
    @Value("${secret_key}")
    private String SECRET_KEY;

    /**
     * 获取访问令牌
     *
     * @return {@link String}
     */
    public String getAccessToken() {
        String url = "https://aip.baidubce.com/oauth/2.0/token?grant_type=client_credentials&client_id=" + API_KEY + "&client_secret=" + SECRET_KEY;
        String result = HttpUtils.sendPost(url, null);
        log.info("result:{}", result);
        JSONObject jsonObject = JSON.parseObject(result);
        log.info("access_token:{}", jsonObject.getString("access_token"));
        return jsonObject.getString("access_token");
    }

    public String askAI(BaiduMessages messages) {
        String jsonString = JSON.toJSONString(messages);
        log.info("jsonString:{}", jsonString);
        String url = "https://aip.baidubce.com/rpc/2.0/ai_custom/v1/wenxinworkshop/chat/eb-instant?access_token=" + getAccessToken();
        String result = HttpUtil.post(url, jsonString);
        log.info("result:{}", result);
        return result;
    }

    /**
     * 请求 jsonstring
     *
     * @return {@link String}
     */
    public String askAIForJSONString(BaiduMessages messages) {
        String result = askAI(messages);
        log.info("result:{}", result);
        int startIndex = result.indexOf("```json") + 7;
        int endIndex = result.lastIndexOf("```");
        String jsonStr = result.substring(startIndex, endIndex)
                .replace("\\n", "")
                .replace("\\","")
                .replace(" ", "");
        log.info("jsonStr:{}", jsonStr);
        return jsonStr;
    }

    /**
     * 询问for json
     *
     * @param messages 消息
     * @param clazz    克拉兹
     * @return {@link String}
     */
    public String askAIForJSON(BaiduMessages messages, Type clazz) {
        String jsonStr = askAIForJSONString(messages);
        return JSON.parseObject(jsonStr, clazz);
    }

    public JSONObject askAIForJSON(BaiduMessages messages) {
        String jsonStr = askAIForJSONString(messages);
        return JSON.parseObject(jsonStr);
    }
}
