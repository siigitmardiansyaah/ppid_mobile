package com.bnpb.ppid_app.api;



import com.bnpb.ppid_app.model.ResponseData;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiInterface {

    @FormUrlEncoded
    @POST("Login")
    Call<ResponseData> loginResponse(
            @Field("email") String email,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("Login/total_permohonan")
    Call<ResponseData> totalResponse(
            @Field("iduser") String iduser
    );

    @FormUrlEncoded
    @POST("Register")
    Call<ResponseData> regisResponse(
            @Field("nama") String nama,
            @Field("email") String email,
            @Field("no_hp") String no_hp,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("Permohonan")
    Call<ResponseData> permohonanResponse(
            @Field("iduser") String iduser,
            @Field("nama_pemohon") String nama_pemohon,
            @Field("alamat_pemohon") String alamat_pemohon,
            @Field("nomor_pemohon") String nomor_pemohon,
            @Field("email_pemohon") String email_pemohon,
            @Field("informasi_pemohon") String informasi_pemohon,
            @Field("alasan_pemohon") String alasan_pemohon,
            @Field("nama_pengguna") String nama_pengguna,
            @Field("alamat_pengguna") String alamat_pengguna,
            @Field("nomor_pengguna") String nomor_pengguna,
            @Field("email_pengguna") String email_pengguna,
            @Field("tujuan_pengguna") String tujuan_pengguna
            );

    @FormUrlEncoded
    @POST("Permohonan/list")
    Call<ResponseData> permohonanListResponse(
            @Field("iduser") String iduser
    );

    @FormUrlEncoded
    @POST("Permohonan/pengajuan")
    Call<ResponseData> getDataPermohonanResponse(
            @Field("iduser") String iduser,
            @Field("id_pengajuan") String id_pengajuan
            );

    @FormUrlEncoded
    @POST("Permohonan/aduan")
    Call<ResponseData> getAduanResponse(
            @Field("iduser") String iduser
    );

    @FormUrlEncoded
    @POST("Profile/get")
    Call<ResponseData> getProfile(
            @Field("iduser") String iduser
    );

    @FormUrlEncoded
    @POST("Profile")
    Call<ResponseData> updateProfile(
            @Field("iduser") String iduser,
            @Field("nama") String nama,
            @Field("email") String email,
            @Field("phone") String phone,
            @Field("password") String password
            );




}
