package com.stylefeng.guns.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.io.File;

import static com.stylefeng.guns.core.util.ToolUtil.getTempPath;
import static com.stylefeng.guns.core.util.ToolUtil.isEmpty;

/**
 * guns项目配置
 *
 * @author stylefeng
 * @Date 2017/5/23 22:31
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "guns")
public class GunsProperties {

    //是否开启验证码
    private Boolean kaptchaOpen = true;
    //验证码有效时长
    private long kaptchaInvalidateTime = 120L;

    private Boolean swaggerOpen = false;
    //记住功能（建议禁用）
    private Boolean rememberMeOpen = false;

    private String fileUploadPath = "d:\\\\tmp";

    private Boolean haveCreatePath = false;

    private Boolean springSessionOpen = false;

    /**
     * session 失效时间（默认为30分钟 单位：秒）
     */
    private Integer sessionInvalidateTime = 30 * 60;

    /**
     * session 验证失效时间（默认为15分钟 单位：秒）
     */
    private Integer sessionValidationInterval = 15 * 60;

    public String getFileUploadPath() {
        //如果没有写文件上传路径,保存到临时目录
        if (isEmpty(fileUploadPath)) {
            return getTempPath();
        } else {
            //判断有没有结尾符,没有得加上
            if (!fileUploadPath.endsWith(File.separator)) {
                fileUploadPath = fileUploadPath + File.separator;
            }
            //判断目录存不存在,不存在得加上
            if (!haveCreatePath) {
                File file = new File(fileUploadPath);
                file.mkdirs();
                haveCreatePath = true;
            }
            return fileUploadPath;
        }
    }

}
