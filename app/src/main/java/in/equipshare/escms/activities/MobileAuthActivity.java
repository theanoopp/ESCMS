package in.equipshare.escms.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

import in.equipshare.escms.R;

public class MobileAuthActivity extends AppCompatActivity {

    private static final String TAG = "MobileAuthActivity";


    private ConstraintLayout phoneLayout,codeLayout;
    private TextInputLayout phoneET,codeET;
    private ProgressBar phoneProgress,codeProgress;
    private Button sendCodeBT;
    private TextView errorText;


    private FirebaseAuth mAuth;

    private int btnType = 0;

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
    private String mVerificationId;
    private PhoneAuthProvider.ForceResendingToken mResendToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mobile_auth);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);


        phoneLayout = findViewById(R.id.phoneLayout);
        codeLayout  = findViewById(R.id.codeLayout);

        phoneET = findViewById(R.id.phoneET);
        codeET = findViewById(R.id.codeET);

        phoneProgress = findViewById(R.id.phoneProgress);
        codeProgress = findViewById(R.id.codeProgress);

        sendCodeBT = findViewById(R.id.sendCodeBT);

        errorText = findViewById(R.id.errorText);


        mAuth = FirebaseAuth.getInstance();


        sendCodeBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                sendCodeBT.setError(null);

                if (btnType==0) {

                    String phoneNumber = phoneET.getEditText().getText().toString();

                    if(phoneNumber.length()==10){

                        phoneProgress.setVisibility(View.VISIBLE);
                        phoneET.setEnabled(false);
                        //sendCodeBT.setVisibility(View.GONE);


                        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                                "+91"+phoneNumber,
                                60,
                                TimeUnit.SECONDS,
                                MobileAuthActivity.this,
                                mCallbacks);

                    }else {
                        phoneET.setError("Please enter a valid number...");
                    }



                }else {

                    String code = codeET.getEditText().getText().toString();

                    if(code.length()!=6){

                        codeET.setError("Please enter a valid code");

                    }else {

                        sendCodeBT.setEnabled(false);
                        codeProgress.setVisibility(View.VISIBLE);

                        PhoneAuthCredential phoneAuthCredential = PhoneAuthProvider.getCredential(mVerificationId,code);

                        signInWithPhoneAuthCredential(phoneAuthCredential);

                    }



                }
            }
        });



        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {


                Log.d("MOBILE","OTP is fine");

                signInWithPhoneAuthCredential(phoneAuthCredential);


            }

            @Override
            public void onVerificationFailed(FirebaseException e) {


                if (e instanceof FirebaseAuthInvalidCredentialsException) {
                    errorText.setText(e.getMessage());
                    Log.d("error",e.getLocalizedMessage());
                } else if (e instanceof FirebaseAuthInvalidUserException) {
                    errorText.setText("Invalid mobile number");
                    Log.d("error",e.getLocalizedMessage());
                } else {
                    errorText.setText(e.getMessage());
                    Log.d("error",e.getLocalizedMessage());
                }

                errorText.setVisibility(View.VISIBLE);

            }

            @Override
            public void onCodeSent(String verificationId, PhoneAuthProvider.ForceResendingToken forceResendingToken) {

                mVerificationId = verificationId;
                mResendToken = forceResendingToken;

                phoneProgress.setVisibility(View.INVISIBLE);

                codeLayout.setVisibility(View.VISIBLE);

                btnType = 1;

                sendCodeBT.setEnabled(true);
                sendCodeBT.setText("Verify Code");

                Toast.makeText(MobileAuthActivity.this,"Code sent", Toast.LENGTH_SHORT).show();



            }
        };






    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information

                            // TODO: 7/4/2018 send to email screen
                            startActivity(new Intent(MobileAuthActivity.this,RegisterActivity.class));
                            finish();


                            // ...
                        } else {
                            // Sign in failed, display a message and update the UI

                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {

                                codeProgress.setVisibility(View.INVISIBLE);

                                errorText.setText("The sms verification code is invalid. Please check the code");
                                Log.d(TAG,task.getException().getLocalizedMessage());

                                errorText.setVisibility(View.VISIBLE);
                                sendCodeBT.setEnabled(true);


                            }
                        }
                    }
                });
    }



}
