package net.javasec.simplecleanlogin;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

/**
 * Created by Wiguna R on 12/06/2017.
 */

public class LoginActivity extends AppCompatActivity {
    final String IN_USERNAME = "user";
    final String IN_PASSWORD = "user";

    SessionManager sessionManager;

    //Declare Layout Variables
    Button btnLogin;
    TextView btnRegister, btnForgot;
    EditText inUsername, inPassword;
    TextInputLayout input_layout_username, input_layout_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow(); // in Activity's onCreate() for instance
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }

        //Initialization Session Manager
        sessionManager = new SessionManager(getApplicationContext());

        //Declare Button
        btnLogin = (Button)findViewById(R.id.btnLogin);
        btnRegister = (TextView)findViewById(R.id.btnRegister);
        btnForgot = (TextView)findViewById(R.id.btnForgotPass);

        //Declare EditText
        inUsername = (EditText)findViewById(R.id.inUsername);
        inPassword = (EditText)findViewById(R.id.inPassword);

        //Declare TextInputLayout
        input_layout_username = (TextInputLayout)findViewById(R.id.input_layout_username);
        input_layout_password = (TextInputLayout)findViewById(R.id.input_layout_password);

        //Button Login Listener
        btnLoginListener();
    }

    private void btnLoginListener(){
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!validateUsername() || !validatePassword()){ //Check empty input
                    return;
                }else{
                    if(Objects.equals(inUsername.getText().toString(), IN_USERNAME) && Objects.equals(inPassword.getText().toString(), IN_PASSWORD)) {
                        sessionManager.createLoginSession(inUsername.getText().toString());
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }else{
                        Toast.makeText(LoginActivity.this, "Incorrect username/password", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private Boolean validateUsername(){
        if(inUsername.getText().toString().isEmpty()){
            input_layout_username.setError("Email/Username cannot be empty");
            requestFocus(inUsername);
            return false;
        }else{
            input_layout_username.setErrorEnabled(false);
        }
        return true;
    }

    private Boolean validatePassword(){
        if(inPassword.getText().toString().isEmpty()){
            input_layout_password.setError("Password cannot be empty");
            requestFocus(inPassword);
            return false;
        }else{
            input_layout_password.setErrorEnabled(false);
        }
        return true;
    }

    private void requestFocus(View view){
        if(view.requestFocus()){
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    private class MyTextWatcher implements TextWatcher{
        private View view;

        private MyTextWatcher(View view) {
            this.view = view;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            switch (view.getId()) {
                case R.id.inUsername:
                    validateUsername();
                    break;
                case R.id.inPassword:
                    validatePassword();
                    break;
            }
        }
    }

}
