package com.ruoyi.web.service;


import com.ruoyi.generator.domain.GenTable;

import java.util.List;

public interface AiService {
    List<GenTable> askForTable(String content);
}
