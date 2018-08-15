package com.stylefeng.guns.core.aop;

import com.stylefeng.guns.core.common.annotion.BizLog;
import com.stylefeng.guns.core.common.annotion.BizNameType;
import com.stylefeng.guns.core.common.constant.cache.CacheKey;
import com.stylefeng.guns.core.common.constant.factory.ConstantFactory;
import com.stylefeng.guns.core.log.LogManager;
import com.stylefeng.guns.core.log.factory.LogTaskFactory;
import com.stylefeng.guns.core.shiroext.kit.ShiroKit;
import com.stylefeng.guns.core.shiroext.vo.ShiroUser;
import com.stylefeng.guns.core.support.HttpKit;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.TimerTask;

/**
 * 日志记录
 *
 * @author fengshuonan
 * @date 2016年12月6日 下午8:48:30
 */
@Slf4j
@Aspect
@Component
public class LogAop {

    /**
     * 抓取带有该注解的所有方法
     */
    @Pointcut(value = "@annotation(com.stylefeng.guns.core.common.annotion.BizLog)")
    public void cutService() {
    }

    @Around("cutService()")
    public Object recordSysLog(ProceedingJoinPoint point) throws Throwable {

        //先执行业务
        Object result = point.proceed();

        //如果当前用户未登录，不做日志
        ShiroUser user = ShiroKit.getUser();
        boolean online = (user != null);
        try {
            if (online) {
                Object target = point.getTarget();
                Signature sig = point.getSignature();
                if (!(sig instanceof MethodSignature)) {
                    throw new IllegalArgumentException("this annotation only apply to method");
                }
                MethodSignature methodSignature = (MethodSignature) sig;
                Method currentMethod = target.getClass().getMethod(methodSignature.getName(), methodSignature.getParameterTypes());
                String methodName = currentMethod.getName();
                //获取操作名称
                BizLog annotation = currentMethod.getAnnotation(BizLog.class);
                //获取拦截方法的参数
                String className = target.getClass().getName();

                String moduleName = annotation.value();
                String idkey = annotation.key();
                String type = annotation.type();

                String msg;
                /**
                 * 可根据情况进一步简化
                 *  该功能可辅助审计和BUG调试
                 *  故不需要之前那么繁杂的功能
                 *  若需要转义字段，可缓存该对象的VO对象
                 */
                if (type.equals(BizNameType.ADD)) {
                    msg = "新增数据：" + HttpKit.getReqParamToString();
                } else if (type.equals(BizNameType.DELETE)) {
                    String id = HttpKit.getReqParam(idkey);
                    Object object1 = ConstantFactory.me().getCrudObject(CacheKey.PO_BEFORE + id);
                    msg = "删除数据：" + object1;
                } else {
                    String id = HttpKit.getReqParam(idkey);
                    Object object1 = ConstantFactory.me().getCrudObject(CacheKey.PO_BEFORE + id);
                    Object object2 = ConstantFactory.me().getCrudObject(CacheKey.PO_AFTER + id);
                    msg = "更新前：" + object1 + ";;;更新后：" + object2;
                }
                TimerTask task = LogTaskFactory.bussinessLog(user.getId(), type + moduleName, className, methodName, msg);
                LogManager.me().executeLog(task);
            }

        } catch (Exception e) {
            log.error("日志记录出错!", e);
        }
        return result;
    }

}