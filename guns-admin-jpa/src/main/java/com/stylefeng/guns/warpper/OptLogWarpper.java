package com.stylefeng.guns.warpper;

import com.stylefeng.guns.core.common.constant.factory.ConstantFactory;
import com.stylefeng.guns.core.util.ToolUtil;
import com.stylefeng.guns.po.OperationLog;
import com.stylefeng.guns.vo.OperationLogVo;
import org.springframework.beans.BeanUtils;
import org.tan.jpa.warpper.BeanWarpper;

/**
 * 日志列表的包装类
 *
 * @author fengshuonan
 * @date 2017年4月5日22:56:24
 */
public class OptLogWarpper extends BeanWarpper<OperationLog, OperationLogVo> {

    @Override
    public OperationLogVo warpBean(OperationLog loginLog) {
        OperationLogVo vo = new OperationLogVo();
        BeanUtils.copyProperties(loginLog, vo);
        vo.setUserName(ConstantFactory.me().getUserNameById(loginLog.getUserid()));
        String message = loginLog.getMessage();
        //如果信息过长,则只截取前100位字符串
        if (ToolUtil.isNotEmpty(message) && message.length() >= 100) {
            String subMessage = message.substring(0, 100) + "...";
            vo.setMessage(subMessage);
        }
        //如果信息中包含分割符号;;;   则分割字符串返给前台
//        if (ToolUtil.isNotEmpty(message) && message.indexOf(Contrast.separator) != -1) {
//            String[] msgs = message.split(Contrast.separator);
//            vo.setRegularMessage(msgs);
//        } else {
//            vo.setRegularMessage(message);
//        }
        return vo;
    }

}
