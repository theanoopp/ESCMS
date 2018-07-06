package in.equipshare.escms;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import in.equipshare.escms.activities.LoginActivity;
import in.equipshare.escms.activities.MobileAuthActivity;
import in.equipshare.escms.activities.RegisterActivity;

public class StartActivity extends AppCompatActivity {

    private static final String TAG = "StartActivity";

    private FirebaseUser firebaseUser;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        /*

        mAuth = FirebaseAuth.getInstance();
        firebaseUser = mAuth.getCurrentUser();

        if(firebaseUser != null){

            String email = firebaseUser.getEmail();
            if(email == null || email.length() < 3){
                startActivity(new Intent(StartActivity.this,RegisterActivity.class));
                finish();
            }


        }

        */

        startActivity(new Intent(StartActivity.this, RegisterActivity.class));
        finish();

    }
}
