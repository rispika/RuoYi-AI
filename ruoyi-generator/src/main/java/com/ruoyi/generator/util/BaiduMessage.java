package com.ruoyi.generator.util;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class BaiduMessage {

    private String role = "user";
    private String content;

    public BaiduMessage(String content) {
        this.content = content;
    }
}
