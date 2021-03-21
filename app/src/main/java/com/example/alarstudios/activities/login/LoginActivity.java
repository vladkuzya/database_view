package com.example.alarstudios.activities.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.alarstudios.R;
import com.example.alarstudios.activities.main.MainActivity;
import com.example.alarstudios.constants.SharedPrefKeys;
import com.example.alarstudios.databinding.ActivityLoginBinding;
import com.example.alarstudios.utils.UtilSharedPreferences;

/**
 * The activity in which the login occurs by the name "test" and the password "123"
 * If you enter incorrect data, you will receive a message about it.
 *
 * @author Vladislav Kuzmin
 * @since 1.0
 */
public class LoginActivity extends AppCompatActivity implements LoginContract.Activity {
    private ActivityLoginBinding binding;
    private LoginContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        presenter = new LoginPresenter(this);
        initEnterButton();
    }

    /**
     * Initialization button "Enter". When you press it, you will try to log in
     *
     * @since 1.0
     */
    private void initEnterButton() {
        binding.btnEnter.setOnClickListener(v -> presenter.login(binding.etLogin.getText().toString(), binding.etPassword.getText().toString()));
    }

    /**
     * This method will show you Alert Dialog, if you entered wrong data
     *
     * @since 1.0
     */
    @Override
    public void showWrongPasswordDialog() {
        this.runOnUiThread(() -> {
            AlertDialog alertDialog = new AlertDialog.Builder(this).create();
            alertDialog.setMessage(getString(R.string.wrong_entered_data));
            alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, getString(R.string.btn_ok),
                    (dialog, which) -> dialog.dismiss());
            alertDialog.show();
        });
    }

    @Override
    public void goToNextActivity(String code) {
        this.runOnUiThread(() -> {
            UtilSharedPreferences.saveValue(this, SharedPrefKeys.CODE_KEY, code);
            this.startActivity(new Intent(LoginActivity.this, MainActivity.class)
                    .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
            this.finish();
        });
    }
}