package com.stylefeng.guns.core.common.page;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.tan.jpa.util.HttpKit;

import javax.servlet.http.HttpServletRequest;

/**
 * BootStrap Table默认的分页参数创建
 *
 * @author fengshuonan
 * @date 2017-04-05 22:25
 */
public class PageableFactory {

    public Pageable defaultPage(Sort defaultsort) {
        Pageable pageable = null;
        HttpServletRequest request = HttpKit.getRequest();
        //每页多少条数据
        int pageSize = 14;
        String limit = request.getParameter("limit");
        if (StringUtils.isNotBlank(limit)) {
            pageSize = Integer.valueOf(limit);
        }
        //偏移量(本页当前有多少条)
        int offset = 0;
        String off = request.getParameter("offset");
        if (StringUtils.isNotBlank(off)) {
            offset = Integer.valueOf(off);
        }
        String sortField = request.getParameter("sort");         //排序字段名称
        String ascOrDesc = request.getParameter("order");       //asc或desc(升序或降序)
        if (StringUtils.isBlank(sortField)) {
            //页数从0开始
            pageable = PageRequest.of((offset / pageSize), pageSize, defaultsort);
        } else {
            Sort.Order order;
            if (Sort.Direction.ASC.equals(ascOrDesc)) {
                order = new Sort.Order(Sort.Direction.ASC, sortField);
            } else {
                order = new Sort.Order(Sort.Direction.DESC, sortField);
            }
            Sort sort = Sort.by(order);
            pageable = PageRequest.of((offset / pageSize), pageSize, sort);
        }
        return pageable;
    }

}
