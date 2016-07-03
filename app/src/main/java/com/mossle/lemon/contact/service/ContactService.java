package com.mossle.lemon.contact.service;

import com.mossle.lemon.contact.domain.Contact;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LucasLee on 16/7/3.
 */
public class ContactService {

    private static ContactService mSharedInstance = null;

    private ContactService() {}

    public static ContactService sharedInstance() {
        if (mSharedInstance == null) {
            synchronized (ContactService.class) {
                if (mSharedInstance == null) {
                    mSharedInstance = new ContactService();
                }
            }
        }
        return mSharedInstance;
    }

    public List<Contact> getContacts() {
        List<Contact> results = new ArrayList<>();

        results.add(new Contact("公司高层"));
        results.add(new Contact("总经办"));
        results.add(new Contact("人事行政部"));
        results.add(new Contact("财务部"));
        results.add(new Contact("市场部"));
        results.add(new Contact("技术部"));

        return results;
    }
}
