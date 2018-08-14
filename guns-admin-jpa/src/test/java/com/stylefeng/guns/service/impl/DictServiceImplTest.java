package com.stylefeng.guns.service.impl;

import com.stylefeng.guns.base.BaseJunit;
import com.stylefeng.guns.po.Dict;
import com.stylefeng.guns.service.IDictService;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * DictServiceImpl Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>08/04/2018</pre>
 */
@Slf4j
public class DictServiceImplTest extends BaseJunit {

    @Autowired
    private IDictService dictService;

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: addDict(String dictCode, String dictName, String dictTips, String dictValues)
     */
    @Test
    public void testAddDict() throws Exception {
        //TODO: Test goes here...
        String dictCode = "test";
        String dictName = "字典测试";
        String dictTips = "这是一个字典测试";
        String dictValues = "1:测试1:1;2:测试2:2";
        Dict dict = dictService.addDict(dictCode, dictName, dictTips, dictValues);
        log.info("{}", dict);

        Integer id = dict.getId();
        dictService.editDict(id, "tes", "测试", "备注", "1:测试1:1;2:测试2:2");
        log.info("{}", dict);

    }

    /**
     * Method: editDict(Integer dictId, String dictCode, String dictName, String dictTips, String dicts)
     */
    @Test
    public void testEditDict() throws Exception {
        //TODO: Test goes here...
    }

    /**
     * Method: deleteDictCascade(Integer dictId)
     */
    @Test
    public void testDeleteDictCascade() throws Exception {
        //TODO: Test goes here... 
    }

    /**
     * Method: selectByParentCode(String code)
     */
    @Test
    public void testSelectByParentCode() throws Exception {
        //TODO: Test goes here... 
    }

    /**
     * Method: list(String conditiion)
     */
    @Test
    public void testList() throws Exception {
        //TODO: Test goes here...
        List<Dict> list = dictService.list(null);
        log.info("{}", list);
    }

    /**
     * Method: findSubDict(Integer pid)
     */
    @Test
    public void testFindSubDict() throws Exception {
        //TODO: Test goes here...
        List<Dict> subDict = dictService.findSubDict(0);
        log.info("{}", subDict);
    }


}
