import com.alibaba.fastjson2.JSON;
import com.ruoyi.RuoYiApplication;
import com.ruoyi.generator.domain.GenTable;
import com.ruoyi.generator.domain.GenTableColumn;
import com.ruoyi.generator.mapper.GenTableColumnMapper;
import com.ruoyi.generator.mapper.GenTableMapper;
import com.ruoyi.generator.service.IGenTableService;
import com.ruoyi.generator.util.BaiduApplication;
import com.ruoyi.generator.util.BaiduMessage;
import com.ruoyi.generator.util.BaiduMessages;
import com.ruoyi.generator.util.GenUtils;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest(classes = RuoYiApplication.class)
@Slf4j
public class GenTest {

    @Resource
    private GenTableColumnMapper genTableColumnMapper;
    @Resource
    private IGenTableService genTableService;
    @Resource
    private GenTableMapper genTableMapper;
    @Resource
    private BaiduApplication baiduApplication;

    @Test
    public void askAIForJSON() {
        BaiduMessages messages = new BaiduMessages();
        messages.addContent("作为一名DB工程师，你需要帮助我实现一个数据库，请列出可能包含在该数据库中的数据表，并以以下JSON格式告诉我(仅需回复JSON，不需要回复其他语句)： [ { \"tableComment\": \"\", \"tableName\": \"\" }, ... ]");
        baiduApplication.askAIForJSON(messages);
    }

    @Test
    public void askAIForJSONString() {
        BaiduMessages messages = new BaiduMessages();
        messages.addContent("作为一名DB工程师，你需要帮助我实现一个数据库，请列出可能包含在该数据库中的数据表，并以以下JSON格式告诉我(仅需回复JSON，不需要回复其他语句)： [ { 'tableComment': '', 'tableName': '' }, ... ]");
        baiduApplication.askAIForJSONString(messages);
    }

    @Test
    public void getAccessToken() {
        String accessToken = baiduApplication.getAccessToken();
        System.out.println(accessToken);
    }

    @Test
    public void selectDbTableListByNames() {
        List<GenTableColumn> genTableColumnList = genTableColumnMapper.selectDbTableColumnsByName("test");
        for (int i = 0; i < genTableColumnList.size(); i++) {
            GenTableColumn genTableColumn = genTableColumnList.get(i);
            log.info("genTableColumn{}:{}", i, genTableColumn.toString());
        }
        /**
         * genTableColumn0:GenTableColumn(columnId=null, tableId=null, columnName=id, columnComment=, columnType=int(11), javaType=null, javaField=null, isPk=0, isIncrement=0, isRequired=1, isInsert=null, isEdit=null, isList=null, isQuery=null, queryType=null, htmlType=null, dictType=null, sort=1)
         * genTableColumn1:GenTableColumn(columnId=null, tableId=null, columnName=name, columnComment=, columnType=varchar(244), javaType=null, javaField=null, isPk=0, isIncrement=0, isRequired=1, isInsert=null, isEdit=null, isList=null, isQuery=null, queryType=null, htmlType=null, dictType=null, sort=2)
         * genTableColumn2:GenTableColumn(columnId=null, tableId=null, columnName=aa, columnComment=, columnType=varchar(222), javaType=null, javaField=null, isPk=0, isIncrement=0, isRequired=1, isInsert=null, isEdit=null, isList=null, isQuery=null, queryType=null, htmlType=null, dictType=null, sort=3)
         * genTableColumn3:GenTableColumn(columnId=null, tableId=null, columnName=sex, columnComment=, columnType=tinyint(1), javaType=null, javaField=null, isPk=0, isIncrement=0, isRequired=1, isInsert=null, isEdit=null, isList=null, isQuery=null, queryType=null, htmlType=null, dictType=null, sort=4)
         */
    }

    @Test
    public void insertTable() {
        String json = "[  \n" +
                "    {  \n" +
                "        \"tableName\": \"students\",  \n" +
                "        \"tableComment\": \"学生表\"  \n" +
                "    },  \n" +
                "    {  \n" +
                "        \"tableName\": \"courses\",  \n" +
                "        \"tableComment\": \"课程表\"  \n" +
                "    },  \n" +
                "    {  \n" +
                "        \"tableName\": \"teachers\",  \n" +
                "        \"tableComment\": \"教师表\"  \n" +
                "    },  \n" +
                "    {  \n" +
                "        \"tableName\": \"student_courses\",  \n" +
                "        \"tableComment\": \"学生选课表\"  \n" +
                "    },  \n" +
                "    {  \n" +
                "        \"tableName\": \"student_teachers\",  \n" +
                "        \"tableComment\": \"学生教师关系表\"  \n" +
                "    }  \n" +
                "]";
        List<GenTable> genTableList = JSON.parseArray(json, GenTable.class);
        for (GenTable genTable : genTableList) {
            GenUtils.initTable(genTable, "pika");
            genTableMapper.insertGenTable(genTable);
        }
    }

    @Test
    public void insertColumn() {
        GenTable genTable = genTableMapper.selectGenTableById(4L);
        String json = "[  \n" +
                "  {  \n" +
                "    \"columnComment\": \"学生ID\",  \n" +
                "    \"columnName\": \"student_id\",  \n" +
                "    \"columnType\": \"INT\",  \n" +
                "    \"isPk\": \"1\",  \n" +
                "    \"isIncrement\": \"1\",  \n" +
                "    \"isRequired\": \"1\"  \n" +
                "  },  \n" +
                "  {  \n" +
                "    \"columnComment\": \"姓名\",  \n" +
                "    \"columnName\": \"name\",  \n" +
                "    \"columnType\": \"VARCHAR(100)\",  \n" +
                "    \"isPk\": \"0\",  \n" +
                "    \"isIncrement\": \"0\",  \n" +
                "    \"isRequired\": \"1\"  \n" +
                "  },  \n" +
                "  {  \n" +
                "    \"columnComment\": \"性别\",  \n" +
                "    \"columnName\": \"gender\",  \n" +
                "    \"columnType\": \"VARCHAR(20)\",  \n" +
                "    \"isPk\": \"0\",  \n" +
                "    \"isIncrement\": \"0\",  \n" +
                "    \"isRequired\": \"1\"  \n" +
                "  },  \n" +
                "  {  \n" +
                "    \"columnComment\": \"出生日期\",  \n" +
                "    \"columnName\": \"birthdate\",  \n" +
                "    \"columnType\": \"DATE\",  \n" +
                "    \"isPk\": \"0\",  \n" +
                "    \"isIncrement\": \"0\",  \n" +
                "    \"isRequired\": \"1\"  \n" +
                "  },  \n" +
                "  {  \n" +
                "    \"columnComment\": \"联系方式\",  \n" +
                "    \"columnName\": \"contact_info\",  \n" +
                "    \"columnType\": \"VARCHAR(100)\",  \n" +
                "    \"isPk\": \"0\",  \n" +
                "    \"isIncrement\": \"0\",  \n" +
                "    \"isRequired\": \"1\"  \n" +
                "  },  \n" +
                "  {  \n" +
                "    \"columnComment\": \"地址\",  \n" +
                "    \"columnName\": \"address\",  \n" +
                "    \"columnType\": \"VARCHAR(200)\",  \n" +
                "    \"isPk\": \"0\",  \n" +
                "    \"isIncrement\": \"0\",  \n" +
                "    \"isRequired\": \"1\"  \n" +
                "  },  \n" +
                "  {  \n" +
                "    \"columnComment\": \"城市\",  \n" +
                "    \"columnName\": \"city\",  \n" +
                "    \"columnType\": \"VARCHAR(100)\",  \n" +
                "    \"isPk\": \"0\",  \n" +
                "    \"isIncrement\": \"0\",  \n" +
                "    \"isRequired\": \"1\"  \n" +
                "  },  \n" +
                "  {  \n" +
                "    \"columnComment\": \"国家\",  \n" +
                "    \"columnName\": \"country\",  \n" +
                "    \"columnType\": \"VARCHAR(100)\",  \n" +
                "    \"isPk\": \"0\",  \n" +
                "    \"isIncrement\": \"0\",  \n" +
                "    \"isRequired\": \"1\"  \n" +
                "  },  \n" +
                "  {  \n" +
                "    \"columnComment\": \"邮政编码\",  \n" +
                "    \"columnName\": \"zip_code\",  \n" +
                "    \"columnType\": \"VARCHAR(20)\",  \n" +
                "    \"isPk\": \"0\",  \n" +
                "    \"isIncrement\": \"0\",  \n" +
                "    \"isRequired\": \"1\"  \n" +
                "  },  \n" +
                "  {  \n" +
                "    \"columnComment\": \"电子邮件\",  \n" +
                "    \"columnName\": \"email\",  \n" +
                "    \"columnType\": \"VARCHAR(100)\",  \n" +
                "    \"isPk\": \"0\",  \n" +
                "    \"isIncrement\": \"0\",  \n" +
                "    \"isRequired\": \"1\"  \n" +
                "  }  \n" +
                "]";
        List<GenTableColumn> tableColumns = JSON.parseArray(json, GenTableColumn.class);
        for (GenTableColumn tableColumn : tableColumns) {
            GenUtils.initColumnField(tableColumn, genTable);
            genTableColumnMapper.insertGenTableColumn(tableColumn);
        }
    }

    @Test
    public void importTable() {
        // 查询表信息
        /**
         * tableName
         * tableComment
         */
        List<GenTable> tableList = genTableService.selectDbTableListByNames(new String[]{"test"});
        for (GenTable table : tableList) {
            String tableName = table.getTableName();
            GenUtils.initTable(table, "pika");
            // 保存列信息
            List<GenTableColumn> genTableColumns = genTableColumnMapper.selectDbTableColumnsByName(tableName);
            for (int i = 0; i < genTableColumns.size(); i++) {
                GenTableColumn genTableColumn = genTableColumns.get(i);
                GenUtils.initColumnField(genTableColumn, table);
                log.info("genTableColumn{}:{}", i, genTableColumn.toString());
            }
            /**
             * columnComment
             * columnName
             * columnType
             * isPk
             * isIncrement
             * isRequired
             * sort
             */
        }

    }

}
