package com.bnpb.ppid_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bnpb.ppid_app.api.ApiClient;
import com.bnpb.ppid_app.api.ApiInterface;
import com.bnpb.ppid_app.model.FormPermohonan;
import com.bnpb.ppid_app.model.LoginData;
import com.bnpb.ppid_app.model.PermohonanData;
import com.bnpb.ppid_app.model.ResponseData;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Detail_Pengajuan extends AppCompatActivity {
    TextInputEditText nama_pemohon,alamat_pemohon,nomor_pemohon,email_pemohon,informasi_pemohon,
            alasan_pemohon,nama_pengguna,alamat_pengguna,nomor_pengguna,email_pengguna,tujuan_pengguna,jawab_pemohon;
    Button btn_download;
    ImageView btn_close;
    ApiInterface apiInterface;
    SessionManager sessionManager;
    String id_pengajuan,iduser,filedownload;
    DownloadManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_pengajuan);
        sessionManager = new SessionManager(Detail_Pengajuan.this);
        if (!sessionManager.isLoggedIn()) {
            moveToLogin();
        }
        nama_pemohon = findViewById(R.id.nama_pemohon1);
        alamat_pemohon = findViewById(R.id.alamat_pemohon1);
        nomor_pemohon = findViewById(R.id.nomor_pemohon1);
        email_pemohon = findViewById(R.id.email_pemohon1);
        informasi_pemohon = findViewById(R.id.informasi_pemohon1);
        alasan_pemohon = findViewById(R.id.alasan_pemohon1);
        nama_pengguna = findViewById(R.id.nama_pengguna1);
        alamat_pengguna = findViewById(R.id.alamat_pengguna1);
        nomor_pengguna = findViewById(R.id.nomor_pengguna1);
        email_pengguna = findViewById(R.id.email_pengguna1);
        tujuan_pengguna = findViewById(R.id.tujuan_pengguna1);
        jawab_pemohon = findViewById(R.id.jawab_pemohon);
        btn_download = (Button) findViewById(R.id.btn_download);
        btn_close = (ImageView) findViewById(R.id.btn_close1);
        iduser = sessionManager.getUserDetail().get(SessionManager.USER_ID);
        id_pengajuan = getIntent().getStringExtra("pengajuan_id");
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<ResponseData> loginCall = apiInterface.getDataPermohonanResponse(iduser,id_pengajuan);
        loginCall.enqueue(new Callback<ResponseData>() {
            @Override
            public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                if(response.isSuccessful() && response.body().isError() == false){
                    nama_pemohon.setText(response.body().getData_pemohon().getNama_pemohon());
                    alamat_pemohon.setText(response.body().getData_pemohon().getAlamat_pemohon());
                    nomor_pemohon.setText(response.body().getData_pemohon().getNo_pemohon());
                    email_pemohon.setText(response.body().getData_pemohon().getEmail_pemohon());
                    informasi_pemohon.setText(response.body().getData_pemohon().getInfo_butuh());
                    alasan_pemohon.setText(response.body().getData_pemohon().getInfo_alasan());
                    nama_pengguna.setText(response.body().getData_pemohon().getNama_info());
                    alamat_pengguna.setText(response.body().getData_pemohon().getAlamat_info());
                    nomor_pengguna.setText(response.body().getData_pemohon().getNo_info());
                    email_pengguna.setText(response.body().getData_pemohon().getEmail_info());
                    tujuan_pengguna.setText(response.body().getData_pemohon().getInfo_tujuan());
                    jawab_pemohon.setText(response.body().getData_pemohon().getJawab_pemohon());
                    if(response.body().getData_pemohon().getData_file().equals("")) {
                                    btn_download.setVisibility(View.GONE);
                    }else{
                                    filedownload = response.body().getData_pemohon().getData_file();
                                    btn_download.setVisibility(View.VISIBLE);
                        btn_download.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Toast.makeText(Detail_Pengajuan.this, "Proses Download..", Toast.LENGTH_LONG).show();
                                manager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
                                Uri uri = Uri.parse("http://60.60.60.153/ppid-api/file/" + filedownload);
                                DownloadManager.Request request = new DownloadManager.Request(uri);
                                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE);
                                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                                long reference = manager.enqueue(request);
                            }
                        });
                    }
                } else {
                    Toast.makeText(Detail_Pengajuan.this, response.body().getMessage(), Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<ResponseData> call, Throwable t) {
//                        Toast.makeText(Login.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
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
        Intent intent = new Intent(Detail_Pengajuan.this, Login.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_HISTORY);
        sessionManager.logoutSession();
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Detail_Pengajuan.this, ListPermohonan.class);
        startActivity(intent);
    }
}