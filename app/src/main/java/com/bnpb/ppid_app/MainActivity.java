package com.bnpb.ppid_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bnpb.ppid_app.api.ApiClient;
import com.bnpb.ppid_app.api.ApiInterface;
import com.bnpb.ppid_app.model.ResponseData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    SessionManager sessionManager;
    TextView username,total;
    String username1,total1,iduser;
    private long pressedTime;
    LinearLayout permohonan,listpermohonan,profile,logout;
    ApiInterface apiInterface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sessionManager = new SessionManager(MainActivity.this);
        if (!sessionManager.isLoggedIn()) {
            moveToLogin();
        }

        username = findViewById(R.id.txt_username);
        total = findViewById(R.id.total_txt);
        permohonan = (LinearLayout) findViewById(R.id.permohonan_cv);
        listpermohonan = (LinearLayout) findViewById(R.id.list_cv);
        profile = (LinearLayout) findViewById(R.id.profile_cv);
        logout = (LinearLayout) findViewById(R.id.logout_cv);
        iduser = sessionManager.getUserDetail().get(SessionManager.USER_ID);
        username1 = "Hai, " + sessionManager.getUserDetail().get(SessionManager.USER_NAME);
        username.setText(username1);

        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<ResponseData> loginCall = apiInterface.getAduanResponse(iduser);
        loginCall.enqueue(new Callback<ResponseData>() {
            @Override
            public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                if(response.isSuccessful() && response.body().isError() == false){
                    total1 = response.body().getAduan() + " Pengajuan";
                    total.setText(total1);
                } else {
                    Toast.makeText(MainActivity.this, response.body().getMessage(), Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<ResponseData> call, Throwable t) {
//                        Toast.makeText(Login.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        permohonan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Permohonan.class));
            }
        });

        listpermohonan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ListPermohonan.class));
            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Profile.class));
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveToLogin();
            }
        });
    }

    private void moveToLogin() {
        Intent intent = new Intent(MainActivity.this, Login.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_HISTORY);
        sessionManager.logoutSession();
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {

        if (pressedTime + 2000 > System.currentTimeMillis()) {
//            Intent a = new Intent(Intent.ACTION_MAIN);
//            a.addCategory(Intent.CATEGORY_HOME);
//            a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            startActivity(a);
            finishAffinity();
        } else {
            Toast.makeText(getBaseContext(), "Ketuk lagi untuk keluar", Toast.LENGTH_SHORT).show();
        }
        pressedTime = System.currentTimeMillis();
    }
}