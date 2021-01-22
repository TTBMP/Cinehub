package com.ttbmp.cinehub.core.usecase;

import com.ttbmp.cinehub.core.utilities.notification.Notification;

/**
 * @author Fabio Buracchi
 */
public abstract class Request {

    protected Notification notification = new Notification();

    public abstract void validate();

    public Notification getNotification() {
        return notification;
    }

}
