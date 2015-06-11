package com.cs446.foodiehub.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.cs446.foodiehub.R;

/**
 * Created by Alex on 15-06-09.
 */
public class LoginActivity extends Activity{

    private Button btnLogin;
    private EditText etUserName;
    private EditText etPassword;

    private static final String EXTRA_USERNAME="extra_username";
    private static final String EXTRA_PASSWORD="extra_password";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogin = (Button) findViewById(R.id.btn_login);
        etUserName = (EditText) findViewById(R.id.et_username);
        etPassword = (EditText) findViewById(R.id.et_password);

        setupEventListener();
    }

    public void setupEventListener(){
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                intent.putExtra(EXTRA_USERNAME, etUserName.getText());
                intent.putExtra(EXTRA_PASSWORD, etPassword.getText());
                startActivity(intent);
            }
        });
    }

    public static String getUsername(Bundle bundle){
        return bundle == null ? null : bundle.getString(EXTRA_USERNAME);
    }

    public static String getPassword(Bundle bundle){
        return bundle == null? null : bundle.getString(EXTRA_PASSWORD);
    }
}
