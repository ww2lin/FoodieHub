package com.cs446.foodiehub.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

import com.beardedhen.androidbootstrap.BootstrapEditText;
import com.cs446.foodiehub.R;
import com.pnikosis.materialishprogress.ProgressWheel;

/**
 * Created by Alex on 15-06-09.
 */
public class LoginActivity extends Activity{

    private Button btnLogin;
    private BootstrapEditText etUserName;
    private BootstrapEditText etPassword;

    private static final String EXTRA_USERNAME="extra_username";
    private static final String EXTRA_PASSWORD="extra_password";

    private ProgressWheel progressWheel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogin = (Button) findViewById(R.id.btn_login);
        etUserName = (BootstrapEditText) findViewById(R.id.et_username);
        etPassword = (BootstrapEditText) findViewById(R.id.et_password);

        progressWheel = (ProgressWheel) findViewById(R.id.progress_wheel);

        setupEventListener();
    }

    public void setupEventListener(){
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressWheel.setVisibility(View.VISIBLE);
                btnLogin.setVisibility(View.GONE);
                progressWheel.spin();

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        intent.putExtra(EXTRA_USERNAME, etUserName.getText());
                        intent.putExtra(EXTRA_PASSWORD, etPassword.getText());
                        startActivity(intent);
                    }
                }, 2000);

            }
        });
    }

    @Override
    public void onResume(){
        super.onResume();
        btnLogin.setVisibility(View.VISIBLE);
    }

    @Override
    public void onPause(){
        super.onPause();
        progressWheel.setVisibility(View.GONE);
    }

    public static String getUsername(Bundle bundle){
        return bundle == null ? null : bundle.getString(EXTRA_USERNAME);
    }

    public static String getPassword(Bundle bundle){
        return bundle == null? null : bundle.getString(EXTRA_PASSWORD);
    }
}
