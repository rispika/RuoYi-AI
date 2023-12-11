package com.ruoyi.web.service;

import com.ruoyi.generator.domain.GenTable;
import com.ruoyi.generator.util.BaiduApplication;
import com.ruoyi.generator.util.BaiduMessages;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
@Slf4j
public class AiServiceImpl implements AiService{

    @Resource
    private BaiduApplication baiduApplication;

    @Override
    public List<GenTable> askForTable(String content) {
        BaiduMessages messages = new BaiduMessages();
        String prompt = "作为一名DB工程师，你需要帮助我实现一个%s的数据库，请列出可能包含在该数据库中的数据表，并以以下JSON格式告诉我(仅需回复JSON，不需要回复其他语句)： [ { \"tableComment\": \"\", \"tableName\": \"\" }, ... ]";
        prompt = String.format(prompt, content);
        messages.addContent(prompt);
        return baiduApplication.askAIForJSON(messages, GenTable.class);
    }
}
