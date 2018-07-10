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
import com.google.gson.GsonBuilder;

import java.util.HashMap;

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

public class SplashScreen extends AppCompatActivity {

    SessionManagement session;
    Gson gson = new GsonBuilder().setLenient().create();
    Result result;

    OkHttpClient client = new OkHttpClient();
    Retrofit.Builder builder=new Retrofit.Builder().baseUrl(ApiUtils.BASE_URL).client(client).addConverterFactory(GsonConverterFactory.create(gson));
    Retrofit retrofit=builder.build();
    APIService retrofitInterface=retrofit.create(APIService.class);

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
            splash();
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


    public void splash() {
        int SPLASH_TIME_OUT = 2000;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                //startActivity(new Intent(SplashScreen.this,StartActivity.class));
                //finish();


                session = new SessionManagement(getApplicationContext());


                if(session.isLoggedIn()) {

                    Log.e("TAG", "execterd");
                    Intent i=new Intent(SplashScreen.this,StartActivity.class);
                    startActivity(i);
                    finish();

                } else{

                    Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.putExtra("Result", result);
                    startActivity(intent);
                    finish();

                }

            }
        }, SPLASH_TIME_OUT);
    }


}
