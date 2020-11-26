package com.github.library.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * springmvc 拓展
 *
 * @author zwl
 * @date 2020/11/25 22:49
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    /**
     * 自定义 消息转换器
     *
     * @param converters {@link HttpMessageConverter} 列表
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        // 在所有的 HttpMessageConverter 实例集合中，
        // StringHttpMessageConverter 要比其它的 Converter 排得靠前一些。
        // 我们需要 移除 StringHttpMessageConverter
        converters.removeIf(converter -> converter instanceof StringHttpMessageConverter);
    }
}
