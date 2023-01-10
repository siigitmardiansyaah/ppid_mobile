package com.bnpb.ppid_app.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseData {
    private List<PermohonanData> pengajuan;
    @SerializedName("message")
    private String message;
    @SerializedName("error")
    private boolean error;
    @SerializedName("login")
    private LoginData login;
    @SerializedName("profile")
    private ProfileData profile;
    @SerializedName("data_pemohon")
    private FormPermohonan data_pemohon;
    @SerializedName("aduan")
    private String aduan;


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public LoginData getLogin() {
        return login;
    }

    public void setLogin(LoginData login) {
        this.login = login;
    }

    public ProfileData getProfile() {
        return profile;
    }

    public void setProfile(ProfileData profile) {
        this.profile = profile;
    }

    public FormPermohonan getData_pemohon() {
        return data_pemohon;
    }

    public void setData_pemohon(FormPermohonan data_pemohon) {
        this.data_pemohon = data_pemohon;
    }

    public List<PermohonanData> getPengajuan() {
        return pengajuan;
    }

    public void setPengajuan(List<PermohonanData> pengajuan) {
        this.pengajuan = pengajuan;
    }

    public String getAduan() {
        return aduan;
    }

    public void setAduan(String aduan) {
        this.aduan = aduan;
    }
}