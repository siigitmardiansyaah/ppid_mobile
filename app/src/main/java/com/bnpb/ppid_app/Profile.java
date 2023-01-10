package com.bnpb.ppid_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bnpb.ppid_app.api.ApiClient;
import com.bnpb.ppid_app.api.ApiInterface;
import com.bnpb.ppid_app.model.PermohonanData;
import com.bnpb.ppid_app.model.ProfileData;
import com.bnpb.ppid_app.model.ResponseData;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Profile extends AppCompatActivity {
    ApiInterface apiInterface;
    SessionManager sessionManager;
    TextInputEditText nama_pemohon,alamat_pemohon,nomor_pemohon,email_pemohon,informasi_pemohon,
            alasan_pemohon;
    String nama_pemohon1,alamat_pemohon1,nomor_pemohon1,email_pemohon1,informasi_pemohon1,
            alasan_pemohon1,iduser;
    Button btn_simpan;
    ImageView btn_close;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        sessionManager = new SessionManager(Profile.this);
        if (!sessionManager.isLoggedIn()) {
            moveToLogin();
        }
        iduser = sessionManager.getUserDetail().get(SessionManager.USER_ID);

        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        nama_pemohon = findViewById(R.id.txt_nama);
        alamat_pemohon = findViewById(R.id.txt_email);
        nomor_pemohon = findViewById(R.id.no_telpon);
        email_pemohon = findViewById(R.id.password_1);
        informasi_pemohon = findViewById(R.id.confirm_password);
        btn_simpan = (Button) findViewById(R.id.btn_simpan1);
        btn_close = (ImageView) findViewById(R.id.btn_close1);
        getData();

        btn_simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_simpan.setEnabled(false);
                nama_pemohon1 = nama_pemohon.getText().toString();
                alamat_pemohon1 = alamat_pemohon.getText().toString();
                nomor_pemohon1 = nomor_pemohon.getText().toString();
                email_pemohon1 = email_pemohon.getText().toString();
                informasi_pemohon1 = informasi_pemohon.getText().toString();
                if(email_pemohon1.equals(informasi_pemohon1) && (email_pemohon1.length() > 0 && informasi_pemohon1.length() > 0)) {
                    apiInterface = ApiClient.getClient().create(ApiInterface.class);
                    Call<ResponseData> loginCall = apiInterface.updateProfile(iduser,nama_pemohon1,alamat_pemohon1,nomor_pemohon1,
                            email_pemohon1);
                    loginCall.enqueue(new Callback<ResponseData>() {
                        @Override
                        public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                            if(response.isSuccessful() && response.body().isError() == false){

                                Toast.makeText(Profile.this,response.body().getMessage(), Toast.LENGTH_LONG).show();
                                btn_simpan.setEnabled(true);
                                email_pemohon.setText("");
                                informasi_pemohon.setText("");
                                getData();
                            } else {
                                Toast.makeText(Profile.this, response.body().getMessage(), Toast.LENGTH_LONG).show();
                                btn_simpan.setEnabled(true);
                            }
                        }
                        @Override
                        public void onFailure(Call<ResponseData> call, Throwable t) {
//                        Toast.makeText(Login.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                            btn_simpan.setEnabled(true);
                        }
                    });
                }else if(email_pemohon1.length() < 1 || informasi_pemohon1.length() < 1){
                    Toast.makeText(Profile.this,"Kolom Password dan Konfirmasi Password Tidak Boleh Kosong", Toast.LENGTH_LONG).show();
                    btn_simpan.setEnabled(true);
                }else{
                    Toast.makeText(Profile.this,"Password Tidak Sama", Toast.LENGTH_LONG).show();
                    btn_simpan.setEnabled(true);
                }

            }
        });

        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }

    private void moveToLogin() {
        Intent intent = new Intent(Profile.this, Login.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_HISTORY);
        sessionManager.logoutSession();
        startActivity(intent);
        finish();
    }

    private void getData() {
        Call<ResponseData> loginCall = apiInterface.getProfile(iduser);
        loginCall.enqueue(new Callback<ResponseData>() {
            @Override
            public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                if(response.isSuccessful() && response.body().isError() == false){
                    nama_pemohon.setText(response.body().getProfile().getUser_name());
                    alamat_pemohon.setText(response.body().getProfile().getUser_email());
                    nomor_pemohon.setText(response.body().getProfile().getNo_hp());
                } else {
                    Toast.makeText(Profile.this, response.body().getMessage(), Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<ResponseData> call, Throwable t) {
//                        Toast.makeText(Login.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Profile.this, MainActivity.class);
        startActivity(intent);
    }
}