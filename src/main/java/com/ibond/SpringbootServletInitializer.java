package com.ibond;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 *
 * <p>
 * Servlet启动类 不使用SpringBoot内置Tomcat启动时可用此类作为Servlet启动类
 * </p>
 *
 */
@SpringBootApplication
public class SpringbootServletInitializer extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SpringbootServletInitializer.class);
    }
}
