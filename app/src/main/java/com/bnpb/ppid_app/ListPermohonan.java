package com.bnpb.ppid_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bnpb.ppid_app.api.ApiClient;
import com.bnpb.ppid_app.api.ApiInterface;
import com.bnpb.ppid_app.model.PermohonanData;
import com.bnpb.ppid_app.model.ResponseData;
import com.bnpb.ppid_app.adapter.AdapterPermohonan;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListPermohonan extends AppCompatActivity {
    ApiInterface apiInterface;
    private RecyclerView rvData;
    private RecyclerView.Adapter adData;
    private RecyclerView.LayoutManager lmData;
    private List<PermohonanData> listData = new ArrayList<>();
    private SwipeRefreshLayout srlData;
    private ProgressBar pbData;
    private String iduser;
    SessionManager sessionManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_permohonan);
        rvData = findViewById(R.id.rv_data);
        srlData = findViewById(R.id.srl_data);
        pbData = findViewById(R.id.pb_data);
        lmData = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvData.setLayoutManager(lmData);
        sessionManager = new SessionManager(ListPermohonan.this);
        if (!sessionManager.isLoggedIn()) {
            moveToLogin();
        }

        srlData.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                srlData.setRefreshing(true);
                retrieveData();
                srlData.setRefreshing(false);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        retrieveData();
    }

    public void onBackPressed() {
        Intent intent = new Intent(ListPermohonan.this, MainActivity.class);
        startActivity(intent); }


    public void retrieveData() {
        pbData.setVisibility(View.VISIBLE);
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        iduser = sessionManager.getUserDetail().get(SessionManager.USER_ID);
        Call<ResponseData> tampilData = apiInterface.permohonanListResponse(iduser);

        tampilData.enqueue(new Callback<ResponseData>() {
            @Override
            public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                if(response.body() != null) {
                    listData = response.body().getPengajuan();
                    adData = new AdapterPermohonan(ListPermohonan.this, listData);
                    rvData.setAdapter(adData);
                    adData.notifyDataSetChanged();
                    pbData.setVisibility(View.INVISIBLE);
                }else{
                    Toast.makeText(ListPermohonan.this, "Gagal Menarik Data", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<ResponseData> call, Throwable t) {
                Toast.makeText(ListPermohonan.this, "Gagal Menghubungi Server  ", Toast.LENGTH_SHORT).show();
                pbData.setVisibility(View.INVISIBLE);
            }
        });
    }

    private void moveToLogin() {
        Intent intent = new Intent(ListPermohonan.this, Login.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);
        finish();
    }


}