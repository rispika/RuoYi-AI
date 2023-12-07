package com.ruoyi.generator.util;

import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
@ToString
public class BaiduMessages {
    private List<BaiduMessage> messages = new ArrayList<>();

    public void addContent(String content) {
        BaiduMessage baiduMessage = new BaiduMessage(content);
        messages.add(baiduMessage);
    }
}
