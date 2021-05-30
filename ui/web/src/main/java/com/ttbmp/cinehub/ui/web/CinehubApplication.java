package com.ttbmp.cinehub.ui.web;

import com.ttbmp.cinehub.ui.web.appbar.AppBarInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;

/**
 * @author Fabio Buracchi
 */
@SpringBootApplication
public class CinehubApplication {

    public static void main(String[] args) {
        SpringApplication.run(CinehubApplication.class, args);
    }


    @Configuration
    static class InterceptorAppConfig implements WebMvcConfigurer {

        @Override
        public void addInterceptors(InterceptorRegistry registry) {
            registry.addInterceptor(new AppBarInterceptor())
                    .addPathPatterns("/**")
                    .excludePathPatterns(Arrays.asList(
                            "/css/**",
                            "/js/**",
                            "/images/**",
                            "/webjars/**"
                    ));
        }

    }

}
