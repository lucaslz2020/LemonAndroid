package com.mossle.lemon.message.service;

import com.mossle.lemon.message.domain.Session;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LucasLee on 16/7/3.
 */
public class SessionService {

    private static SessionService sharedInstance = null;

    private SessionService() {}

    public static SessionService sharedInstance() {
        if (sharedInstance == null) {
            synchronized (SessionService.class) {
                if (sharedInstance == null) {
                    sharedInstance = new SessionService();
                }
            }
        }
        return sharedInstance;
    }

    public List<Session> getSessions() {
        List<Session> results = new ArrayList<Session>();
        results.add(new Session());
        results.add(new Session());
        results.add(new Session());
        results.add(new Session());
        results.add(new Session());
        results.add(new Session());
        results.add(new Session());
        results.add(new Session());
        results.add(new Session());
        results.add(new Session());
        results.add(new Session());
        results.add(new Session());
        results.add(new Session());
        results.add(new Session());
        results.add(new Session());
        return results;
    }
}
