package in.equipshare.escms.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import in.equipshare.escms.R;
import in.equipshare.escms.model.Result;
import in.equipshare.escms.rest.APIService;
import in.equipshare.escms.utils.ApiUtils;
import in.equipshare.escms.utils.SessionManagement;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class StartActivity extends AppCompatActivity {

    private static final String TAG = "StartActivity";

    private TextInputLayout emailInput;
    private TextInputLayout passInput;

    private TextView forgotpass;

    private Button loginBT;
    private Button signupBT;

    private ProgressDialog progressDialog;

    Result result=null;
    Context context;
    SessionManagement session;//to store user credentials


    private String email,password;


    //code to initialize retrofit api to connect to server
    Gson gson = new GsonBuilder().setLenient().create();
    OkHttpClient client = new OkHttpClient();
    Retrofit.Builder builder=new Retrofit.Builder().baseUrl(ApiUtils.BASE_URL).client(client).addConverterFactory(GsonConverterFactory.create(gson));
    Retrofit retrofit=builder.build();
    APIService retrofitInterface=retrofit.create(APIService.class);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);


        emailInput = findViewById(R.id.emailInput);
        passInput = findViewById(R.id.passInput);

        loginBT = findViewById(R.id.loginBT);
        signupBT = findViewById(R.id.signBT);

        forgotpass = findViewById(R.id.forgot_button);



        context = this.getApplicationContext();
        session = new SessionManagement(getApplicationContext());


        loginBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                email = emailInput.getEditText().getText().toString();
                password = passInput.getEditText().getText().toString();

                if(checkInput(email,password)){

                    startSignin();

                }


            }
        });

        signupBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(StartActivity.this,RegisterActivity.class));

            }
        });



    }

    private void startSignin() {

        progressDialog=new ProgressDialog(StartActivity.this);
        progressDialog.setMessage("Signing In");
        progressDialog.setCancelable(false);
        progressDialog.show();

        Call<Result> call=retrofitInterface.login(email,password);

        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, @NonNull retrofit2.Response<Result> response) {

                result =  response.body(); // have your all data
                if(result == null){

                    progressDialog.cancel();
                    int code = response.code();
                    if(code == 404)Toast.makeText(StartActivity.this,"Server error : "+response.message(),Toast.LENGTH_SHORT).show();


                }else if(result.getMsg()!=null&&result.getMsg().equals("wrong password")) {

                    progressDialog.cancel();
                    passInput.setError("Incorrect Password");
                    passInput.requestFocus();

                }else if(result.getMsg()!=null&&result.getMsg().equals("user with this username does not exist"))
                {
                    progressDialog.cancel();
                    emailInput.setError("Invalid Username");
                    emailInput.requestFocus();

                }else if(result.getSuccess()){

                    progressDialog.cancel();
                    session.createLoginSession(email, password,result.getToken());
                    // TODO: 7/7/18 create dashboard
                    Intent intent = new Intent(StartActivity.this, HomeActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.putExtra("Result", result);
                    startActivity(intent);
                    finish();


                }else {

                    new AlertDialog.Builder(StartActivity.this)
                            .setTitle("Can't Connect to Server")
                            .setMessage("Can't Connect to server Please Check after some time")
                            .setPositiveButton("Exit", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    finish();
                                    System.exit(0);
                                }
                            })
                            .show();


                }

            }
            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                Toast.makeText(StartActivity.this,"Something went wrong",Toast.LENGTH_SHORT).show();
                progressDialog.cancel();
            }

        });



    }

    private boolean checkInput(String email, String pass) {


        boolean cancel = true;

        emailInput.setError(null);
        passInput.setError(null);

        if(!email.contains("@")){

            emailInput.setError(getString(R.string.error_invalid_email));
            cancel = false;

        }

        if(pass.length() < 6){

            passInput.setError(getString(R.string.error_invalid_password));
            cancel = false;
        }


        return cancel;


    }
}
