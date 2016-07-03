package com.lucaslee.lemon.message.domain;

import java.util.Date;

/**
 * Created by LucasLee on 16/7/3.
 */
public class Session {

    private String avatarURLString;

    private String name;

    private String content;

    private Date createdTime;

    public Session() {
        name = "张三";
        content = "今天天气真的不错哦！";
        createdTime = new Date();
    }

    public String getAvatarURLString() {
        return avatarURLString;
    }

    public String getName() {
        return name;
    }

    public String getContent() {
        return content;
    }

    public Date getCreatedTime() {
        return createdTime;
    }
}
