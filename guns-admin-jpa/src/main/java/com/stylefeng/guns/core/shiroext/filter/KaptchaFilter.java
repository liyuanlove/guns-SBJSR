package com.stylefeng.guns.core.shiroext.filter;

import com.baomidou.kaptcha.Kaptcha;
import com.stylefeng.guns.core.log.LogManager;
import com.stylefeng.guns.core.log.factory.LogTaskFactory;
import com.stylefeng.guns.core.shiroext.kit.ShiroKit;
import com.stylefeng.guns.core.shiroext.token.CaptchaToken;
import com.stylefeng.guns.core.shiroext.vo.ShiroUser;
import lombok.Data;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import static org.tc.jpa.util.HttpKit.getIp;

/**
 * 登录过滤器
 */
@Data
public class KaptchaFilter extends FormAuthenticationFilter {

    //验证码key值
    public static final String DEFAULT_CAPTCHA_PARAM = "kaptcha";
    private String captchaParam = DEFAULT_CAPTCHA_PARAM;
    //kaptcha操作接口
    private Kaptcha kaptcha;
    private long invalidateTime = 120;

    /**
     * 构造注入
     *
     * @param kaptcha
     */
    public KaptchaFilter(Kaptcha kaptcha) {
        this.kaptcha = kaptcha;
    }

    public KaptchaFilter(Kaptcha kaptcha, long invalidateTime) {
        this.kaptcha = kaptcha;
        this.invalidateTime = invalidateTime;
    }

    /**
     * 登录验证
     */
    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) {
        CaptchaToken token = createToken(request, response);

        //异常全局捕获
        kaptcha.init(request, response);
        kaptcha.validate(token.getCaptcha(), invalidateTime);
        Subject subject = getSubject(request, response);
        subject.login(token);

        ShiroUser shiroUser = ShiroKit.getUser();
        ShiroKit.setSessionAttr("getShiroUser", shiroUser);
        ShiroKit.setSessionAttr("username", shiroUser.getAccount());
        ShiroKit.setSessionAttr("sessionFlag", true);
        //登录成功日志
        LogManager.me().executeLog(LogTaskFactory.loginLog(shiroUser.getId(), getIp()));

        return true;
    }

    /**
     * 填充自定义的token
     *
     * @param request
     * @param response
     * @return
     */
    @Override
    protected CaptchaToken createToken(ServletRequest request, ServletResponse response) {
        String username = getUsername(request);
        String password = getPassword(request);
        String captcha = getCaptcha(request);
        //不建议开启该功能
        boolean rememberMe = isRememberMe(request);
        String host = getHost(request);
        return new CaptchaToken(username, password.toCharArray(), rememberMe, host, captcha);
    }

    /**
     * 获取验证码值
     *
     * @param request
     * @return
     */
    protected String getCaptcha(ServletRequest request) {
        return WebUtils.getCleanParam(request, getCaptchaParam());
    }


}
