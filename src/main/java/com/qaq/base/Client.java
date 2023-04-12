package com.qaq.base;

import com.qaq.base.service.notify.NotifyService;
import com.qaq.base.service.uniauth.UniauthService;

public class Client {

    private final Config config;
    private NotifyService notifyService;
    private UniauthService uniauth;

    public Client(String appName, String jwt) {
        config = new Config(appName, jwt);
        notifyService = new NotifyService(config);
        uniauth = new UniauthService(config);
    }

    public NotifyService notifyService() {
        return notifyService;
    }

    public UniauthService uniauth() {
        return uniauth;
    }

    public record Config(String appName, String jwt) {
    }

}
