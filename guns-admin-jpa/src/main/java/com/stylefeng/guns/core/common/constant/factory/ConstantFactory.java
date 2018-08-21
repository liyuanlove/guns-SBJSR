package com.stylefeng.guns.core.common.constant.factory;


import com.stylefeng.guns.core.common.constant.cache.Cache;
import com.stylefeng.guns.core.common.constant.cache.CacheKey;
import com.stylefeng.guns.core.common.constant.enums.TrebleStatus;
import com.stylefeng.guns.core.support.CollectionKit;
import com.stylefeng.guns.core.support.StrKit;
import com.stylefeng.guns.core.util.SpringContextHolder;
import com.stylefeng.guns.core.util.ToolUtil;
import com.stylefeng.guns.dao.*;
import com.stylefeng.guns.po.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;
import org.tc.redis.cache.RedisCacheDao;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * 常量的生产工厂
 *
 * @author fengshuonan
 * @date 2017年2月13日 下午10:55:21
 */
@Component
@DependsOn("springContextHolder")
public class ConstantFactory implements IConstantFactory {

    /**
     * 获取mapper
     */
    private RoleDao roleDao = SpringContextHolder.getBean(RoleDao.class);
    private DeptDao deptDao = SpringContextHolder.getBean(DeptDao.class);
    private DictDao dictDao = SpringContextHolder.getBean(DictDao.class);
    private UserDao userDao = SpringContextHolder.getBean(UserDao.class);
    private MenuDao menuDao = SpringContextHolder.getBean(MenuDao.class);
    private NoticeDao noticeDao = SpringContextHolder.getBean(NoticeDao.class);
    private SellerDao sellerDao = SpringContextHolder.getBean(SellerDao.class);
    private RedisCacheDao redisCacheDao = SpringContextHolder.getBean(RedisCacheDao.class);

    public static IConstantFactory me() {
        return SpringContextHolder.getBean("constantFactory");
    }

    @Override
    public String getUserNameById(Integer userId) {
        if (userId == null) {
            return "--";
        }
        try {
            User user = userDao.getOne(userId);
            return user.getName();
        } catch (EntityNotFoundException e) {
            return "--";
        }
    }

    @Override
    public String getUserAccountById(Integer userId) {
        if (userId == null) {
            return "--";
        }
        try {
            User user = userDao.getOne(userId);
            return user.getAccount();
        } catch (EntityNotFoundException e) {
            return "--";
        }
    }

    @Override
    @Cacheable(value = Cache.CONSTANT, key = "'" + CacheKey.ROLES_NAME + "'+#roleids")
    public String getMultiRoleName(String roleids) {
        if (StringUtils.isBlank(roleids)) {
            return "--";
        }
        List<String> split = StrKit.split(roleids, ',');
        List<String> roleNameList = roleDao.nameList(split);
        return CollectionKit.join(roleNameList, ",");
    }

    @Override
    @Cacheable(value = Cache.CONSTANT, key = "'" + CacheKey.SINGLE_ROLE_NAME + "'+#roleid")
    public String getSingleRoleName(Integer roleid) {
        if (roleid == null || 0 == roleid) {
            return "--";
        }
        Role roleObj = roleDao.getOne(roleid);
        if (ToolUtil.isNotEmpty(roleObj) && ToolUtil.isNotEmpty(roleObj.getName())) {
            return roleObj.getName();
        }
        return "";
    }

    @Override
    @Cacheable(value = Cache.CONSTANT, key = "'" + CacheKey.SINGLE_ROLE_TIP + "'+#roleid")
    public String getRoleEnName(Integer roleid) {
        if (roleid == null || 0 == roleid) {
            return "--";
        }
        Role roleObj = roleDao.getOne(roleid);
        if (ToolUtil.isNotEmpty(roleObj) && ToolUtil.isNotEmpty(roleObj.getName())) {
            return roleObj.getTips();
        }
        return "--";
    }

    @Override
    @Cacheable(value = Cache.CONSTANT, key = "'" + CacheKey.DEPT_NAME + "'+#deptId")
    public String getDeptName(Integer deptId) {
        if (deptId == null || deptId == 0) {
            return "--";
        }
        Dept dept = deptDao.getOne(deptId);
        if (ToolUtil.isNotEmpty(dept) && ToolUtil.isNotEmpty(dept.getFullname())) {
            return dept.getFullname();
        }
        return "--";
    }


    @Override
    public List<Integer> getSubDeptId(Integer deptId) {
        if (deptId == null) {
            return null;
        }
        List<Integer> list = this.deptDao.subDeptIdList("%[" + deptId + "]%");
        return list;
    }

    @Override
    public List<Integer> getParentDeptIds(Integer deptId) {
        if (deptId == null) {
            return null;
        }
        Dept dept = deptDao.getOne(deptId);
        String pids = dept.getPids();
        String[] split = pids.split(",");
        ArrayList<Integer> parentDeptIds = new ArrayList<>();
        for (String s : split) {
            parentDeptIds.add(Integer.valueOf(StrKit.removeSuffix(StrKit.removePrefix(s, "["), "]")));
        }
        return parentDeptIds;
    }

    @Override
    public String getMenuNames(String menuids) {
        if (StringUtils.isBlank(menuids)) {
            return "--";
        }
        List<String> list = menuDao.menuNameList(menuids);
        return CollectionKit.join(list, ",");
    }

    @Override
    public String getDictName(Integer dictId) {
        if (ToolUtil.isEmpty(dictId)) {
            return "";
        }
        try {
            Dict dict = dictDao.getOne(dictId);
            return dict.getName();
        } catch (EntityNotFoundException e) {
            return "";
        }
    }

    @Override
    public String getDictsByName(String name, Integer val) {
        Dict dict = dictDao.findByNameEquals(name);
        if (dict != null) {
            List<Dict> dicts = dictDao.findByPidEquals(dict.getId());
            for (Dict item : dicts) {
                if (item.getNum() != null && item.getNum().equals(val)) {
                    return item.getName();
                }
            }
        }
        return "";
    }

    /**
     * 获取性别名称
     */
    @Override
    public String getSexName(Integer sex) {
        return getDictsByName("性别", sex);
    }

    @Override
    public String getStatusName(Integer status) {
        return TrebleStatus.valueOf(status);
    }

    @Override
    public List<Dict> findChildrenInDict(Integer id) {
        if (ToolUtil.isEmpty(id)) {
            return null;
        }
        List<Dict> dicts = dictDao.findByPidEquals(id);
        return dicts;
    }

    @Override
    public Object getCrudObject(Object key) {
        return redisCacheDao.getAndRemove(Cache.CRUD, key);
    }

    @Override
    public String getSellerName(Integer sellerid) {
        if (sellerid == null) {
            return "--";
        }
        try {
            Seller seller = sellerDao.getOne(sellerid);
            return seller.getName();
        } catch (EntityNotFoundException e) {
            return "";
        }
    }

    @Override
    public String getNoticeTitle(Integer dictId) {
        if (ToolUtil.isEmpty(dictId)) {
            return "";
        }
        try {
            Notice notice = noticeDao.getOne(dictId);
            return notice.getTitle();
        } catch (EntityNotFoundException e) {
            return "";
        }
    }
}
