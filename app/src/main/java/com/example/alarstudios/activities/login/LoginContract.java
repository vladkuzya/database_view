package com.example.alarstudios.activities.login;

/**
 * Contract between LoginActivity and LoginPresenter
 *
 * @author Vladislav Kuzmin
 * @since 1.0
 */
public interface LoginContract {
    interface Presenter {
        void login(String login, String password);
    }

    interface Activity {
        void showWrongPasswordDialog();
        void goToNextActivity(String code);
    }
}
