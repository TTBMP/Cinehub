package com.ttbmp.cinehub.ui.web.manageemployeeshift;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

public class EventListener implements ApplicationListener {
    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        System.out.println(event.toString());
    }
}
