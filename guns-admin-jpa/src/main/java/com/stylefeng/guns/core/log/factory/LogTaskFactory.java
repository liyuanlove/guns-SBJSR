package com.stylefeng.guns.core.log.factory;

import com.stylefeng.guns.core.common.constant.enums.LogResult;
import com.stylefeng.guns.core.common.constant.enums.LogType;
import com.stylefeng.guns.core.db.Db;
import com.stylefeng.guns.core.log.LogManager;
import com.stylefeng.guns.core.util.ToolUtil;
import com.stylefeng.guns.dao.LoginLogDao;
import com.stylefeng.guns.dao.OperationLogDao;
import com.stylefeng.guns.po.LoginLog;
import com.stylefeng.guns.po.OperationLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.TimerTask;

/**
 * 日志操作任务创建工厂
 *
 * @author fengshuonan
 * @date 2016年12月6日 下午9:18:27
 */
public class LogTaskFactory {

    private static Logger logger = LoggerFactory.getLogger(LogManager.class);
    private static LoginLogDao loginLogDao = Db.getDao(LoginLogDao.class);
    private static OperationLogDao operationLogDao = Db.getDao(OperationLogDao.class);

    /**
     * 登录成功日志
     *
     * @param userId
     * @param ip
     * @return
     */
    public static TimerTask loginLog(final Integer userId, final String ip) {
        return new TimerTask() {
            @Override
            public void run() {
                try {
                    LoginLog loginLog = LogFactory.createLoginLog(LogType.LOGIN, userId, "登录成功", ip);
                    loginLogDao.save(loginLog);
                } catch (Exception e) {
                    logger.error("创建登录日志异常!", e);
                }
            }
        };
    }

    /**
     * 登录失败日志
     *
     * @param username
     * @param msg
     * @param ip
     * @return
     */
    public static TimerTask loginLog(final String username, final String msg, final String ip) {
        return new TimerTask() {
            @Override
            public void run() {
                LoginLog loginLog = LogFactory.createLoginLog(
                        LogType.LOGIN_FAIL, null, "账号:" + username + "," + msg, ip);
                try {
                    loginLogDao.save(loginLog);
                } catch (Exception e) {
                    logger.error("创建登录失败异常!", e);
                }
            }
        };
    }

    /**
     * 退出日志
     *
     * @param userId
     * @param ip
     * @return
     */
    public static TimerTask exitLog(final Integer userId, final String ip) {
        return new TimerTask() {
            @Override
            public void run() {
                LoginLog loginLog = LogFactory.createLoginLog(LogType.EXIT, userId, null, ip);
                try {
                    loginLogDao.save(loginLog);
                } catch (Exception e) {
                    logger.error("创建退出日志异常!", e);
                }
            }
        };
    }

    /**
     * 记录业务日志
     *
     * @param userId
     * @param bussinessName
     * @param clazzName
     * @param methodName
     * @param msg
     * @return
     */
    public static TimerTask bussinessLog(final Integer userId, final String bussinessName, final String clazzName, final String methodName, final String msg) {
        return new TimerTask() {
            @Override
            public void run() {
                OperationLog operationLog = LogFactory.createOperationLog(
                        LogType.BUSSINESS, userId, bussinessName, clazzName, methodName, msg, LogResult.SUCCESS);
                try {
                    operationLogDao.save(operationLog);
                } catch (Exception e) {
                    logger.error("创建业务日志异常!", e);
                }
            }
        };
    }

    /**
     * 记录异常日志
     *
     * @param userId
     * @param exception
     * @return
     */
    public static TimerTask exceptionLog(final Integer userId, final Exception exception) {
        return new TimerTask() {
            @Override
            public void run() {
                String msg = ToolUtil.getExceptionMsg(exception);
                OperationLog operationLog = LogFactory.createOperationLog(
                        LogType.EXCEPTION, userId, "", null, null, msg, LogResult.FAIL);
                try {
                    operationLogDao.save(operationLog);
                } catch (Exception e) {
                    logger.error("创建异常日志异常!", e);
                }
            }
        };
    }
}
