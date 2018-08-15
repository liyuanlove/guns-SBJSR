package com.stylefeng.guns.core.shiroext.token;

import lombok.Data;
import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * Description  : 拓展登陆验证字段
 */
@Data
public class CaptchaToken extends UsernamePasswordToken {

    //验证码字符串
    private String captcha;

    public CaptchaToken(String username, char[] password,
                        boolean rememberMe, String host,
                        String captcha) {
        super(username, password, rememberMe, host);
        this.captcha = captcha;
    }

}
