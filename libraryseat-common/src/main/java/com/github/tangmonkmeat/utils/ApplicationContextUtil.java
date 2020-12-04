package com.github.tangmonkmeat.utils;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Objects;

/**
 * 实现接口（ApplicationContextAware），这个类就可以方便获得ApplicationContext中的所有bean;
 * <p>
 * 注意：
 * 仅仅适用于当前运行的代码和已启动的Spring代码处于同一个Spring上下文，否则获取到的ApplicationContext是空的
 *
 * @author zwl
 * @date 2020/9/6 16:43
 */
@Component
public class ApplicationContextUtil implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    /**
     * 获取　ApplicationContext 实例
     *
     * @param applicationContext　{@link ApplicationContext}
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ApplicationContextUtil.applicationContext = applicationContext;
    }

    /**
     * 获取　ServletRequestAttributes
     */
    public static ServletRequestAttributes getServletRequestAttributes(){
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes());
    }

    public static ServletContext getServletContext(){
        return Objects.requireNonNull(ContextLoader.getCurrentWebApplicationContext()).getServletContext();
    }

    /**
     * 获取　HttpServletRequest
     */
    public static HttpServletRequest getRequest(){
        return getServletRequestAttributes().getRequest();
    }

    /**
     * 获取　HttpServletResponse
     */
    public static HttpServletResponse getResponse() {
        return getServletRequestAttributes().getResponse();
    }

    /**
     * 获取此次会话的session
     */
    public static HttpSession getSession() {
        return getRequest().getSession();
    }

    /**
     * 通过name获取 Bean.
     */
    public static Object getBean(String beanName) {
        return applicationContext.getBean(beanName);
    }

    /**
     * 通过类型获取 Bean.
     */
    public static <T> T getBean(Class<T> tClass) {
        return applicationContext.getBean(tClass);
    }
}

