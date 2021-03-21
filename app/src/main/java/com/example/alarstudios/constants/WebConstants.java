package com.example.alarstudios.constants;

import androidx.annotation.StringDef;
import androidx.annotation.StringRes;

@StringDef({
        WebConstants.LOGIN_URL,
        WebConstants.USERNAME_PARAM,
        WebConstants.PASSWORD_PARAM,
        WebConstants.DATA_URL,
        WebConstants.CODE_PARAM,
        WebConstants.PAGE_PARAM,
        WebConstants.PICTURE_URL
})
public @interface WebConstants {
    String PICTURE_URL = "https://source.unsplash.com/random/100x100?sig=";
    String LOGIN_URL = "https://www.alarstudios.com/test/auth.cgi";
    String DATA_URL = "https://www.alarstudios.com/test/data.cgi";
    String USERNAME_PARAM = "username=";
    String PASSWORD_PARAM = "password=";
    String CODE_PARAM = "code=";
    String PAGE_PARAM = "p=";
}
