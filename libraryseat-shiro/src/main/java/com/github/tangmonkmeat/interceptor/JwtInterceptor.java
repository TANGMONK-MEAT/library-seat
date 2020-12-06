package com.github.tangmonkmeat.interceptor;

import com.github.tangmonkmeat.annotation.JwtIgnore;
import com.github.tangmonkmeat.common.constant.JwtConstant;
import com.github.tangmonkmeat.config.shiro.JwtToken;
import com.github.tangmonkmeat.exception.BusinessException;
import com.github.tangmonkmeat.result.ResultEnum;
import com.github.tangmonkmeat.utils.IpUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * token验证拦截器
 *
 * @author zwl
 * @date 2020/12/1 22:27
 */
public class JwtInterceptor implements HandlerInterceptor {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * @throws Exception
     *     <p>{@link UnknownAccountException} 用户不存在</p>
     *     <p>{@link AuthenticationException} 密码错误</p>
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            // 忽略 有@JwtIgnore 注解标注的方法
            final HandlerMethod handlerMethod = (HandlerMethod) handler;
            if (handlerMethod.hasMethodAnnotation(JwtIgnore.class)) {
                return true;
            }

            // 获取请求头的 'authorization': token
            final String token = request.getHeader(JwtConstant.AUTH_HEADER_KEY);
            String ip = IpUtil.getIp(request);
            String uri = request.getRequestURI();
            logger.info("user request，client: {}，url：{}，authorization：{}", ip,uri,token);

            // 用户未登录
            if (token == null || "".equals(token) || " ".equals(token)) {
                logger.warn("user not login in，client: {}, uri: {},",ip,uri);
                throw new BusinessException(ResultEnum.USER_NOT_LOGGED_IN);
            }

            // 验证token是否有效---由shiro 对用户的token 进行认证
            // 如果认证失败，会抛出异常，由全局异常拦截器捕获
            Subject subject = SecurityUtils.getSubject();
            subject.login(new JwtToken(token));
            return true;
        }
        return true;
    }
}
