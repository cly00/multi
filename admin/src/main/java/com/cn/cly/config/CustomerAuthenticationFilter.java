package com.cn.cly.config;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录验证过滤
 * Created by chen on 2017/8/9.
 */
public class CustomerAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        // 在这里进行验证码的校验
        // 取出验证码
        String validateCode = (String) request.getSession().getAttribute("validateCode");
        // 取出页面的验证码
        String randomcode = request.getParameter("randomcode");
        // 输入的验证码和session中的验证进行对比
        if (randomcode != null && validateCode != null && !randomcode.equals(validateCode)) {
            // 如果校验失败，将验证码错误失败信息，通过shiroLoginFailure设置到request中
            throw new AuthenticationServiceException("validateCode is invalidate");
        }
        return super.attemptAuthentication(request, response);
    }
}
