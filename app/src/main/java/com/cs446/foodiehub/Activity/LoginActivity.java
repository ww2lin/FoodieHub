package com.cs446.foodiehub.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.beardedhen.androidbootstrap.BootstrapEditText;
import com.cs446.foodiehub.Api.LoginRequest;
import com.cs446.foodiehub.Fragment.CheckoutFragment;
import com.cs446.foodiehub.Interface.LoginResponse;
import com.cs446.foodiehub.R;
import com.pnikosis.materialishprogress.ProgressWheel;

/**
 * Created by Alex on 15-06-09.
 */
public class LoginActivity extends Activity{

    private Button btnLogin;
    private BootstrapEditText etUserName;
    private BootstrapEditText etPassword;

    private ProgressWheel progressWheel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogin = (Button) findViewById(R.id.btn_login);
        etUserName = (BootstrapEditText) findViewById(R.id.et_username);
        etPassword = (BootstrapEditText) findViewById(R.id.et_password);

        progressWheel = (ProgressWheel) findViewById(R.id.progress_wheel);

        etUserName.setText("test4@test.com");
        etPassword.setText("Test");
        setupEventListener();
    }

    public void setupEventListener(){
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressWheel.setVisibility(View.VISIBLE);
                btnLogin.setVisibility(View.GONE);
                progressWheel.spin();

                LoginRequest.login(etUserName.getText().toString(), etPassword.getText().toString(), loginResponse);
            }
        });
    }

    @Override
    public void onResume(){
        super.onResume();
        resetLoading();

        CheckoutFragment.killAllFoodStatusThread();
    }

    @Override
    public void onPause(){
        super.onPause();
    }

    public void resetLoading(){
        btnLogin.setVisibility(View.VISIBLE);
        progressWheel.setVisibility(View.GONE);
    }

    private LoginResponse loginResponse = new LoginResponse() {
        @Override
        public void loginFail(int statusCode, String responseString) {
            Toast.makeText(LoginActivity.this, responseString, Toast.LENGTH_SHORT).show();
            resetLoading();
        }

        @Override
        public void success() {
            etUserName.getText().clear();
            etPassword.getText().clear();
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        }
    };
}
