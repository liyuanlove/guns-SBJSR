package com.stylefeng.guns.service.impl;

import com.stylefeng.guns.base.BaseJunit;
import com.stylefeng.guns.core.common.constant.enums.DoubleStatus;
import com.stylefeng.guns.core.common.page.PageableFactory;
import com.stylefeng.guns.core.ztree.ZTreeNode;
import com.stylefeng.guns.po.Seller;
import com.stylefeng.guns.vo.SellerVo;
import com.stylefeng.guns.warpper.SellerWarpper;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.sql.Timestamp;
import java.util.List;

/**
 * SellerServiceImpl Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>08/02/2018</pre>
 */
@Slf4j
public class SellerServiceImplTest extends BaseJunit {

    @Autowired
    private SellerServiceImpl sellerService;

    @Before
    public void before() throws Exception {

    }

    @After
    public void after() throws Exception {

    }

    /**
     * Method: existsByNameEquals(String name)
     */
    @Test
    public void testExistsByNameEquals() throws Exception {
        //TODO: Test goes here...
        Seller seller1 = new Seller();
        seller1.setName("test1");
        seller1.setAddr("dd");
        int enableCode = DoubleStatus.ENABLE.getCode();
        seller1.setStatus(enableCode);
        seller1.setCreatetime(new Timestamp(System.currentTimeMillis()));
        sellerService.save(seller1);
        log.info("{}", seller1);

        log.info("{}", sellerService.existsByNameEquals("test1"));

        Seller seller2 = new Seller();
        seller2.setId(seller1.getId());
        int disableCode = DoubleStatus.DISABLE.getCode();
        seller2.setStatus(disableCode);
        seller2.setAddr("xxxxxxxx");
        sellerService.updateNotNullField(seller1, seller2);
        log.info("{}", seller1);

        int count = sellerService.setStatus(seller2.getId(), enableCode);
        log.info("{}", count);

        sellerService.deleteById(seller2.getId());
    }

    /**
     * Method: setSellerScope(Integer userId, String sellerIds)
     */
    @Test
    public void testSetSellerScope() throws Exception {
        //TODO: Test goes here... 
    }

    /**
     * Method: setStatus(Integer id, int status)
     */
    @Test
    public void testSetStatus() throws Exception {
        //TODO: Test goes here... 
    }

    /**
     * Method: list(Pageable pageable, String condition)
     */
//    Hibernate: select next_val as id_val from hibernate_sequence for updateAllCol
//    Hibernate: updateAllCol hibernate_sequence set next_val= ? where next_val=?
//    Hibernate: insertAllCol into seller (addr, createtime, name, status, id) values (?, ?, ?, ?, ?)
//            17:27:22 INFO  - Seller{id=4, name='test1', addr='dd', status=1, createtime=2018-08-02 17:27:21.463} com.stylefeng.guns.service.impl.SellerServiceImplTest Line:78
//    Hibernate: select seller0_.id as id1_11_0_, seller0_.addr as addr2_11_0_, seller0_.createtime as createti3_11_0_, seller0_.name as name4_11_0_, seller0_.status as status5_11_0_ from seller seller0_ where seller0_.id=?
//    Hibernate: updateAllCol seller set addr=?, createtime=?, name=?, status=? where id=?
//            17:27:22 INFO  - Seller{id=4, name='test1', addr='abcd', status=null, createtime=null} com.stylefeng.guns.service.impl.SellerServiceImplTest Line:85
    @Test
    public void testList() throws Exception {
        //TODO: Test goes here...
        //默认的排序依据
        Sort.Order order1 = new Sort.Order(Sort.Direction.ASC, "createtime");
        Sort.Order order2 = new Sort.Order(Sort.Direction.ASC, "id");
        Sort sort = Sort.by(order1, order2);
        Pageable pageable = new PageableFactory().defaultPage(sort);
        Page<Seller> page = sellerService.list(pageable, null);
        List<Seller> content = page.getContent();
        List<SellerVo> list = new SellerWarpper().warpList(content);
        log.info("{}", page);
        log.info("{}", content);
        log.info("{}", list);
    }

    /**
     * Method: getCheckedSellerTree(Integer userId)
     */
    @Test
    public void testGetCheckedSellerTree() throws Exception {
        //TODO: Test goes here...
        List<ZTreeNode> checkedSellerTree = sellerService.getCheckedSellerTree(1);
        for (ZTreeNode node : checkedSellerTree) {
            log.info("[{}]", node);
        }
        List<ZTreeNode> sellerTree = sellerService.getSellerTree();

        for (ZTreeNode node : sellerTree) {
            log.info("[{}]", node);
        }
        List<ZTreeNode> sellerTreeByScope = sellerService.getSellerTreeByScope(1);

        for (ZTreeNode node : sellerTreeByScope) {
            log.info("[{}]", node);
        }
    }

    /**
     * Method: getSellerTree()
     */
    @Test
    public void testGetSellerTree() throws Exception {
        //TODO: Test goes here... 
    }

    /**
     * Method: getSellerTreeByScope(Integer userId)
     */
    @Test
    public void testGetSellerTreeByScope() throws Exception {
        //TODO: Test goes here... 
    }


}
