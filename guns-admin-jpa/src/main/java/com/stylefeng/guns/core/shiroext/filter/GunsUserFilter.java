package com.stylefeng.guns.core.shiroext.filter;

import com.stylefeng.guns.core.shiroext.kit.ShiroKit;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 自定义的访问过滤器
 */
@Slf4j
public class GunsUserFilter extends AccessControlFilter {

    public GunsUserFilter(String loginUrl) {
        setLoginUrl(loginUrl);
    }

    /**
     * 是否允许访问
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        if (isLoginRequest(request, response)) {
            return true;
        } else {
            Subject subject = getSubject(request, response);
            // If principal is not null, then the getUserByAccount is known and should be allowed access.
            if (subject.getPrincipal() == null) {
                //还未登录
                return false;
            }
            String url = getPathWithinApplication(request);
            //超级管理员可以为所欲为（若存在风险请注释掉）
            if (ShiroKit.isAdmin()) {
                log.debug("admin can access all");
                return true;
            }
            return subject.isPermitted(url);
        }
    }

    /**
     * Processes requests where the subject was denied access as determined by the
     * {@link #isAccessAllowed(javax.servlet.ServletRequest, javax.servlet.ServletResponse, Object) isAccessAllowed}
     * method.
     *
     * @param request  the incoming <code>ServletRequest</code>
     * @param response the outgoing <code>ServletResponse</code>
     * @return <code>true</code> if the request should continue to be processed; false if the subclass will
     * handle/render the response directly.
     * @throws Exception if there is an error processing the request.
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = WebUtils.toHttp(request);
        HttpServletResponse httpServletResponse = WebUtils.toHttp(response);
        /**
         * 如果是ajax请求则不进行跳转
         */
        if (ShiroKit.isAjax(request)) {
            httpServletResponse.setHeader("sessionstatus", "timeout");
        } else {
            /**
             * 第一次点击页面
             */
            String referer = httpServletRequest.getHeader("Referer");
            if (referer == null) {
                saveRequestAndRedirectToLogin(request, response);
            } else {

                /**
                 * 从别的页面跳转过来的
                 */
                if (ShiroKit.getSessionAttr("sessionFlag") == null) {
                    httpServletRequest.setAttribute("tips", "session超时");
                    httpServletRequest.getRequestDispatcher("/login").forward(request, response);
                } else {
                    saveRequestAndRedirectToLogin(request, response);
                }
            }
        }
        // 返回 false 表示已经处理，例如页面跳转啥的，表示不在走以下的拦截器了（如果还有配置的话）
        return false;
    }
}
