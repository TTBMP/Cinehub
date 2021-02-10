package com.ttbmp.cinehub.ui.web;

import com.ttbmp.cinehub.ui.web.di.AppContainer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CinehubApplication {

    public static final AppContainer APP_CONTAINER = new AppContainer();

    public static void main(String[] args) {
        SpringApplication.run(CinehubApplication.class, args);
    }
}
