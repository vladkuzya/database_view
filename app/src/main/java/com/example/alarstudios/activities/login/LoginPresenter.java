package com.example.alarstudios.activities.login;

import android.util.Log;

import com.example.alarstudios.constants.WebConstants;
import com.example.alarstudios.models.Response;
import com.example.alarstudios.utils.WebUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.net.URL;

/**
 * Presenter which tries login and validates entered data
 *
 * @author Vladislav Kuzmin
 * @since 1.0
 */
public class LoginPresenter implements LoginContract.Presenter {
    private final String STATUS_OK = "ok";
    private final String TAG = LoginPresenter.class.getSimpleName();
    private final Gson gson = new GsonBuilder().create();
    private final LoginContract.Activity activity;

    public LoginPresenter(LoginContract.Activity activity) {
        this.activity = activity;
    }

    /**
     * Sending the GET request to server and getting the response from it
     *
     * @param login - your entered login
     * @param password - your entered password
     * @since 1.0
     */
    @Override
    public void login(String login, String password) {
        new Thread(() -> {
            try {
                URL url = new URL(WebConstants.LOGIN_URL + "?" + WebConstants.USERNAME_PARAM + login + "&" + WebConstants.PASSWORD_PARAM + password);
                String response = WebUtil.getResponse(url);

                Response data = gson.fromJson(response, Response.class);
                enteredDataValidation(data);
                } catch (IOException | InterruptedException e) {
                    throw new RuntimeException(e.getLocalizedMessage());
                }
        }).start();
    }

    /**
     * Validation your entered data by response from server
     *
     * @param response - response which you got from server
     * @since 1.0
     */
    private void enteredDataValidation(Response response) {
        if (response.getStatus().equals(STATUS_OK)) {
            activity.goToNextActivity(response.getCode());
        } else {
            activity.showWrongPasswordDialog();
        }
    }

}
