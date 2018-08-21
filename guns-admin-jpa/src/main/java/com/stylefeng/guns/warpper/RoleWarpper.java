package com.stylefeng.guns.warpper;

import com.stylefeng.guns.core.common.constant.factory.ConstantFactory;
import com.stylefeng.guns.po.Role;
import com.stylefeng.guns.vo.RoleVo;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import org.tc.jpa.warpper.BeanWarpper;

/**
 * 角色列表的包装类
 *
 * @author fengshuonan
 * @date 2017年2月19日10:59:02
 */
@Data
public class RoleWarpper extends BeanWarpper<Role, RoleVo> {

    @Override
    public RoleVo warpBean(Role po) {
        RoleVo roleVo = new RoleVo();
        BeanUtils.copyProperties(po, roleVo);
        roleVo.setPName(ConstantFactory.me().getSingleRoleName(po.getPid()));
        roleVo.setDeptName(ConstantFactory.me().getDeptName(po.getDeptid()));
        return roleVo;
    }

}
