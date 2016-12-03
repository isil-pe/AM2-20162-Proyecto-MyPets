package com.isil.mypets;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.isil.mypets.entity.UserEntity;
import com.isil.mypets.presenter.LogInView;
import com.isil.mypets.presenter.LogInPresenter;
import com.isil.mypets.storage.PreferencesHelper;

public class LoginActivity extends ActionBarActivity implements LogInView {

    private Button btnLogin;
    private EditText eteEmail;
    private EditText etePassword;
    private String email;
    private String password;
    private String id;
    private View rlayLoading,container;
    private LogInView logInView;

    private LogInPresenter logInPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        logInPresenter = new LogInPresenter();
        logInPresenter.attachedView(this);

        if(PreferencesHelper.isSignedIn(getApplicationContext())) {
            gotoMain();
        }else {
            init();
        }
    }

    private void init() {
        eteEmail=(EditText)findViewById(R.id.eteEmail);
        etePassword=(EditText)findViewById(R.id.etePassword);
        btnLogin=(Button)findViewById(R.id.btnLogin);
        /*rlayLoading=findViewById(R.id.rlayLoading);*/
        container=findViewById(R.id.container);

        btnLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (validateForm()) {
                    logInPresenter.logIn(email,password);
                }
            }
        });

        etePassword.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (event != null) {
                    Log.v("CONSOLE ", "keycode " + event.getKeyCode() + " actionId " + actionId);
                }

                if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE)) {
                    if (validateForm()) {
                        logInPresenter.logIn(email, password);
                    }
                }
                return false;
            }
        });

    }

    private boolean validateForm() {
        email= eteEmail.getText().toString();
        password= etePassword.getText().toString();

        if(email.isEmpty())
        {
            eteEmail.setError("Error campo username");
            return false;
        }
        if(password.isEmpty())
        {
            etePassword.setError("Error campo password");
            return false;
        }
        return true;
    }

    @Override
    public void showLoading() {
        /*this.rlayLoading.setVisibility(View.VISIBLE);*/

    }

    @Override
    public void hideLoading() {
        /*this.rlayLoading.setVisibility(View.GONE);*/

    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void onMessageError(String message) {
        Snackbar snackbar = Snackbar
                .make(container,message, Snackbar.LENGTH_LONG);

        snackbar.show();
    }

    @Override
    public void gotoMain(UserEntity userEntity) {
        userEntity.setPassword(password);
        savePreferences(userEntity);
        Intent intent = new Intent(this,DashboardActivity.class);
        startActivity(intent);
    }

    public void gotoMain() {
        Intent intent = new Intent(this,DashboardActivity.class);
        startActivity(intent);
    }

    private void savePreferences(UserEntity userEntity) {
        PreferencesHelper.saveSession(getApplicationContext(),userEntity.getEmail(),userEntity.getPassword(),userEntity.getObjectId(),userEntity.getAlias());
    }
}
