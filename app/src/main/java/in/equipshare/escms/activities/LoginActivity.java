package in.equipshare.escms.activities;


import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.AsyncTask;


import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


import in.equipshare.escms.R;


public class LoginActivity extends AppCompatActivity {



    // UI references.
    private TextInputLayout mEmailView;
    private TextInputLayout mPasswordView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Set up the login form.
        mEmailView = findViewById(R.id.emailEditText);

        mPasswordView = findViewById(R.id.passEditText);


        Button mEmailSignInButton = findViewById(R.id.signBT);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });

        Button newUserButton = findViewById(R.id.newUserBT);
        newUserButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(LoginActivity.this,MobileAuthActivity.class));

            }
        });


    }

    private void attemptLogin() {

        // TODO: 7/5/2018 login

    }

}

