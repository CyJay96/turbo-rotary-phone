package com.example.turborotaryphone.model.util;

import com.example.turborotaryphone.model.User;

public abstract class MessageHelper {

    public static String getUserName(User user) {
        return user != null ? user.getUsername() : "The author is unknown";
    }

}
