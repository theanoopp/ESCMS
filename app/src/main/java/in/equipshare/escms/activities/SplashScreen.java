package in.equipshare.escms.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.HashMap;

import in.equipshare.escms.R;
import in.equipshare.escms.StartActivity;
import retrofit2.Call;
import retrofit2.Callback;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
    }


    @Override
    public void onResume() {
        super.onResume();
        if (!checkInternet()) {
            new AlertDialog.Builder(SplashScreen.this)
                    .setTitle("No Internet Connection")
                    .setMessage("Your device is not connected to Internet")
                    .setPositiveButton("Go To Settings", new DialogInterface.OnClickListener()
                    {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            startActivityForResult(new Intent(android.provider.Settings.ACTION_SETTINGS), 0);

                        }

                    })
                    .setNegativeButton("Exit", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            finish();
                            System.exit(0);
                        }
                    })
                    .show();
        } else {
            Splash();
        }
    }

    @Override
    public void onBackPressed() {
        finish();
        System.exit(0);
        super.onBackPressed();
    }

    public boolean checkInternet() {
        boolean mobileNwInfo;
        ConnectivityManager conxMgr = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        try {
            mobileNwInfo = conxMgr.getActiveNetworkInfo().isConnected();
        } catch (NullPointerException e) {
            mobileNwInfo = false;
        }
        return mobileNwInfo;
    }


    public void Splash() {
        int SPLASH_TIME_OUT = 2000;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                startActivity(new Intent(SplashScreen.this,StartActivity.class));
                finish();
                /*

                //session = new SessionManagement(getApplicationContext());
                // Toast.makeText(getApplicationContext(), "User Login Status: " + session.isLoggedIn(), Toast.LENGTH_LONG).show();

                if(!SplashScreen.this.session.isLoggedIn())
                {
                    Log.e("TAG", "execterd");
                    Intent i=new Intent(SplashScreen.this,LoginActivity.class);
                    startActivity(i);
                    finish();


                }
                else{   HashMap<String, String> user = session.getUserDetails();

                    String token=user.get(SessionManagement.KEY_TOKEN);
                    Call<Result> call=retrofitInterface.session_manage(token);

                    call.enqueue(new Callback<Result>() {
                        @Override
                        public void onResponse(Call<Result> call, retrofit2.Response<Result> response) {

                            result=  response.body();// have your all data
                            boolean check=false;
                            if(result!=null)  {check=result.getSuccess();}

                            if((!check)||result.getSuccess()==null)
                            {
                                new AlertDialog.Builder(SplashScreen.this)
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
                            else {
                                Log.e("TAG", "response 33: " + new Gson().toJson(response.body()));
                                Log.e("TAG", "response 33: " + response.body());
                                Intent intent = new Intent(getApplicationContext(), DashBoard.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                intent.putExtra("Result", result);
                                startActivity(intent);
                                finish();
                            }
                        }
                        @Override
                        public void onFailure(Call<Result> call, Throwable t) {

                            Log.e("TAG", "response 33: " +t.getMessage());
                            if(t.getMessage().contains("connect"))
                            {
                                new AlertDialog.Builder(SplashScreen.this)
                                        .setMessage("Can't Connect to server Please Check after some time")
                                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                finish();
                                                System.exit(0);
                                            }
                                        }).show();
                            }
                            Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_SHORT).show();

                        }

                    });}

                    */
            }
        }, SPLASH_TIME_OUT);
    }


}
