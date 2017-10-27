package net.javasec.simplecleanlogin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    SessionManager sessionManager;

    TextView msgWelcome;
    Button btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Init Session Manager
        sessionManager = new SessionManager(getApplicationContext());
        sessionManager.checkLogin(); //Check Login

        HashMap<String, String> userDetails = sessionManager.getUserDetails();
        String username = userDetails.get(sessionManager.KEY_USERNAME);

        Toast.makeText(this, "Session status : " + sessionManager.isLoggedIn(), Toast.LENGTH_SHORT).show();

        msgWelcome = (TextView)findViewById(R.id.msgWelcome);
        msgWelcome.setText("Welcome " + username);

        //Button Logout Listener
        btnLogoutListener();
    }

    public void btnLogoutListener(){
        btnLogout = (Button)findViewById(R.id.btnLogout);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sessionManager.logoutSession();
                finish();
            }
        });
    }
}
