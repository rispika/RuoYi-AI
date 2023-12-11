package com.ruoyi.web.controller.ai;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.web.service.AiService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("ai")
public class AiController extends BaseController {

    @Resource
    private AiService aiService;

    /**
     * AI生成表
     * @param content 数据库名称或表名称或表注释等等，如：MySQL数据库名称、MySQL表名称、MySQL表注释等等，如：MySQL数据�
     * @return {@link AjaxResult}
     */
    @GetMapping("/askForTable/{content}")
    @Log(title = "AI生成表")
    public AjaxResult askForTable(@PathVariable String content) {
        return success(aiService.askForTable(content));
    }

}
