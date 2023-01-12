package com.bnpb.ppid_app;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bnpb.ppid_app.api.ApiClient;
import com.bnpb.ppid_app.api.ApiInterface;
import com.bnpb.ppid_app.model.LoginData;
import com.bnpb.ppid_app.model.ResponseData;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {
    TextView txt_regis;
    ImageView btn_login;
    String email,password;
    ApiInterface apiInterface;
    SessionManager sessionManager;
    TextInputEditText editTextEmail,editTextPassword;
    private long pressedTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        txt_regis = (TextView) findViewById(R.id.txt_register);
        btn_login = (ImageView) findViewById(R.id.btn_login);
        txt_regis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                click(view);
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_login.setEnabled(false);
                email = editTextEmail.getText().toString();
                password = editTextPassword.getText().toString();
                apiInterface = ApiClient.getClient().create(ApiInterface.class);
                if(email.length() < 1 && password.length() < 1) {
                    Toast.makeText(Login.this,"Kedua Kolom Harus Di Isi", Toast.LENGTH_LONG).show();
                    btn_login.setEnabled(true);
                }else if(email.length() < 1 || password.length() < 1) {
                    Toast.makeText(Login.this,"Kolom Tidak Boleh Kosong", Toast.LENGTH_LONG).show();
                    btn_login.setEnabled(true);
                }else{
                    Call<ResponseData> loginCall = apiInterface.loginResponse(email,password);
                    loginCall.enqueue(new Callback<ResponseData>() {
                        @Override
                        public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                            if(response.isSuccessful() && response.body().isError() == false){

                                // Ini untuk menyimpan sesi
                                sessionManager = new SessionManager(Login.this);
                                LoginData loginData = response.body().getLogin();
                                sessionManager.createLoginSession(loginData);

                                //Ini untuk pindah
                                Toast.makeText(Login.this,"Selamat Datang " + response.body().getLogin().getUser_name(), Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(Login.this, MainActivity.class);
                                startActivity(intent);
                                btn_login.setEnabled(true);
                            } else {
                                Toast.makeText(Login.this, response.body().getMessage(), Toast.LENGTH_LONG).show();
                                btn_login.setEnabled(true);
                            }
                        }
                        @Override
                        public void onFailure(Call<ResponseData> call, Throwable t) {
//                        Toast.makeText(Login.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                            btn_login.setEnabled(true);
                        }
                    });
                }
            }
        });
    }
    public void click(View v) {
        Intent intent;
        switch(v.getId()) {
            case R.id.txt_register: // R.id.textView1
                intent = new Intent(this, Register.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onBackPressed() {

        if (pressedTime + 2000 > System.currentTimeMillis()) {
            finishAffinity();
        } else {
            Toast.makeText(getBaseContext(), "Ketuk lagi untuk keluar", Toast.LENGTH_SHORT).show();
        }
        pressedTime = System.currentTimeMillis();
    }
}