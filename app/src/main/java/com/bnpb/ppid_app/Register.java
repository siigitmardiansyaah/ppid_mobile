package com.bnpb.ppid_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bnpb.ppid_app.api.ApiClient;
import com.bnpb.ppid_app.api.ApiInterface;
import com.bnpb.ppid_app.model.ResponseData;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Register extends AppCompatActivity {
    TextView txt_login;
    ImageView btn_regis;
    TextInputEditText editTextName,editTextEmail,editTextMobile,editTextPassword,editTextPasswordCon;
    String nama,password,email,no_hp,password1;
    ApiInterface apiInterface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        txt_login = (TextView) findViewById(R.id.txt_login);
        btn_regis = (ImageView) findViewById(R.id.btn_regis);
        editTextName = findViewById(R.id.editTextName);
        editTextPassword = findViewById(R.id.editTextPassword);
        editTextPasswordCon = findViewById(R.id.editTextPasswordConfirm);
        editTextEmail = findViewById(R.id.editTextEmail1);
        editTextMobile = findViewById(R.id.editTextMobile);

        txt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                click(view);
            }
        });
        btn_regis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_regis.setEnabled(false);
                nama = editTextName.getText().toString();
                password = editTextPassword.getText().toString();
                password1 = editTextPasswordCon.getText().toString();
                email = editTextEmail.getText().toString();
                no_hp = editTextMobile.getText().toString();
                apiInterface = ApiClient.getClient().create(ApiInterface.class);
                if(nama.length() < 1 && email.length() < 1 && no_hp.length() < 1 && password.length() < 1 && password1.length() < 1 ) {
                    Toast.makeText(Register.this,"Semua Kolom Harus Di Isi", Toast.LENGTH_LONG).show();
                    btn_regis.setEnabled(true);
                }else if(nama.length() < 1 || email.length() < 1 || no_hp.length() < 1 || password.length() < 1 || password1.length() < 1 ){
                    Toast.makeText(Register.this,"Kolom Harus Di Isi", Toast.LENGTH_LONG).show();
                    btn_regis.setEnabled(true);
                }else if(password.equals(password1)) {
                    Call<ResponseData> loginCall = apiInterface.regisResponse(nama,email,no_hp,password);
                    loginCall.enqueue(new Callback<ResponseData>() {
                        @Override
                        public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                            if(response.isSuccessful() && response.body().isError() == false){
                                //Ini untuk pindah
                                Toast.makeText(Register.this, response.body().getMessage(), Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(Register.this, Login.class);
                                startActivity(intent);
                                btn_regis.setEnabled(true);
                            } else {
                                Toast.makeText(Register.this, response.body().getMessage(), Toast.LENGTH_LONG).show();
                                btn_regis.setEnabled(true);
                            }
                        }
                        @Override
                        public void onFailure(Call<ResponseData> call, Throwable t) {
                            btn_regis.setEnabled(true);
//                        Toast.makeText(Register.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }else{
                    Toast.makeText(Register.this, "Password Tidak Sama", Toast.LENGTH_LONG).show();
                    btn_regis.setEnabled(true);
                }

            }
        });
    }

    public void click(View v) {
        Intent intent;
        switch(v.getId()) {
            case R.id.txt_login: // R.id.textView1
                intent = new Intent(this, Login.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Register.this, Login.class);
        startActivity(intent);
    }
}