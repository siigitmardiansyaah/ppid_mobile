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
import com.bnpb.ppid_app.model.LoginData;
import com.bnpb.ppid_app.model.ResponseData;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Permohonan extends AppCompatActivity {
    TextInputEditText nama_pemohon,alamat_pemohon,nomor_pemohon,email_pemohon,informasi_pemohon,
            alasan_pemohon,nama_pengguna,alamat_pengguna,nomor_pengguna,email_pengguna,tujuan_pengguna;
    String nama_pemohon1,alamat_pemohon1,nomor_pemohon1,email_pemohon1,informasi_pemohon1,
            alasan_pemohon1,nama_pengguna1,alamat_pengguna1,nomor_pengguna1,email_pengguna1,tujuan_pengguna1,iduser;
    Button btn_simpan;
    ImageView btn_close;
    ApiInterface apiInterface;
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permohonan);
        sessionManager = new SessionManager(Permohonan.this);
        if (!sessionManager.isLoggedIn()) {
            moveToLogin();
        }
        nama_pemohon = findViewById(R.id.nama_pemohon);
        alamat_pemohon = findViewById(R.id.alamat_pemohon);
        nomor_pemohon = findViewById(R.id.nomor_pemohon);
        email_pemohon = findViewById(R.id.email_pemohon);
        informasi_pemohon = findViewById(R.id.informasi_pemohon);
        alasan_pemohon = findViewById(R.id.alasan_pemohon);
        nama_pengguna = findViewById(R.id.nama_pengguna);
        alamat_pengguna = findViewById(R.id.alamat_pengguna);
        nomor_pengguna = findViewById(R.id.nomor_pengguna);
        email_pengguna = findViewById(R.id.email_pengguna);
        tujuan_pengguna = findViewById(R.id.tujuan_pengguna);
        btn_simpan = (Button) findViewById(R.id.btn_simpan);
        btn_close = (ImageView) findViewById(R.id.btn_close);
        iduser = sessionManager.getUserDetail().get(SessionManager.USER_ID);

        btn_simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_simpan.setEnabled(false);
                nama_pemohon1 = nama_pemohon.getText().toString();
                alamat_pemohon1 = alamat_pemohon.getText().toString();
                nomor_pemohon1 = nomor_pemohon.getText().toString();
                email_pemohon1 = email_pemohon.getText().toString();
                informasi_pemohon1 = informasi_pemohon.getText().toString();
                alasan_pemohon1 = alasan_pemohon.getText().toString();
                nama_pengguna1 = nama_pengguna.getText().toString();
                alamat_pengguna1 = alamat_pengguna.getText().toString();
                nomor_pengguna1 = nomor_pengguna.getText().toString();
                email_pengguna1 = email_pengguna.getText().toString();
                tujuan_pengguna1 = tujuan_pengguna.getText().toString();
                apiInterface = ApiClient.getClient().create(ApiInterface.class);
                if(nama_pemohon1.length() < 1 && alamat_pemohon1.length() < 1 && nomor_pemohon1.length() < 1 && nomor_pemohon1.length() < 1 && email_pemohon1.length() < 1 &&
                informasi_pemohon1.length() < 1 && alasan_pemohon1.length() < 1 && nama_pengguna1.length() < 1 && alamat_pengguna1.length() < 1 && nomor_pengguna1.length() < 1
                && email_pengguna1.length() < 1 && tujuan_pengguna1.length() < 1 && tujuan_pengguna1.length() < 1) {
                    Toast.makeText(Permohonan.this,"Semua Kolom Harus Di Isi", Toast.LENGTH_LONG).show();
                    btn_simpan.setEnabled(true);
                } else if(nama_pemohon1.length() < 1 || alamat_pemohon1.length() < 1 || nomor_pemohon1.length() < 1 || nomor_pemohon1.length() < 1 || email_pemohon1.length() < 1 &&
                        informasi_pemohon1.length() < 1 || alasan_pemohon1.length() < 1 || nama_pengguna1.length() < 1 || alamat_pengguna1.length() < 1 || nomor_pengguna1.length() < 1
                        && email_pengguna1.length() < 1 || tujuan_pengguna1.length() < 1 || tujuan_pengguna1.length() < 1) {
                    Toast.makeText(Permohonan.this,"Kolom Harus Di Isi", Toast.LENGTH_LONG).show();
                    btn_simpan.setEnabled(true);
                }else{
                    Call<ResponseData> loginCall = apiInterface.permohonanResponse(iduser,nama_pemohon1,alamat_pemohon1,nomor_pemohon1,
                            email_pemohon1,informasi_pemohon1,alasan_pemohon1,nama_pengguna1,alamat_pengguna1,nomor_pengguna1,
                            email_pengguna1,tujuan_pengguna1 );
                    loginCall.enqueue(new Callback<ResponseData>() {
                        @Override
                        public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                            if(response.isSuccessful() && response.body().isError() == false){

                                Toast.makeText(Permohonan.this,response.body().getMessage(), Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(Permohonan.this, ListPermohonan.class);
                                startActivity(intent);
                                btn_simpan.setEnabled(true);
                            } else {
                                Toast.makeText(Permohonan.this, response.body().getMessage(), Toast.LENGTH_LONG).show();
                                btn_simpan.setEnabled(true);
                            }
                        }
                        @Override
                        public void onFailure(Call<ResponseData> call, Throwable t) {
//                        Toast.makeText(Login.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                            btn_simpan.setEnabled(true);
                        }
                    });
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
        Intent intent = new Intent(Permohonan.this, Login.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_HISTORY);
        sessionManager.logoutSession();
        startActivity(intent);
        finish();
    }


    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Permohonan.this, MainActivity.class);
        startActivity(intent);
    }
}