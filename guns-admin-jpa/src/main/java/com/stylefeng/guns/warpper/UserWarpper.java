package com.stylefeng.guns.warpper;

import com.stylefeng.guns.core.base.warpper.BeanWarpper;
import com.stylefeng.guns.core.common.constant.factory.ConstantFactory;
import com.stylefeng.guns.core.common.constant.factory.IConstantFactory;
import com.stylefeng.guns.po.User;
import com.stylefeng.guns.vo.UserVo;
import lombok.Data;
import org.springframework.beans.BeanUtils;

/**
 * 用户管理的包装类
 *
 * @author fengshuonan
 * @date 2017年2月13日 下午10:47:03
 */
@Data
public class UserWarpper extends BeanWarpper<User, UserVo> {

    @Override
    public UserVo warpBean(User po) {
        UserVo userVo = new UserVo();
        BeanUtils.copyProperties(po, userVo);
        IConstantFactory factory = ConstantFactory.me();
        userVo.setSexName(factory.getSexName(po.getSex()));
        userVo.setRoleName(factory.getMultiRoleName(po.getRoleid()));
        userVo.setDeptName(factory.getDeptName(po.getDeptid()));
        userVo.setStatusName(factory.getStatusName(po.getStatus()));
        return userVo;
    }

}
