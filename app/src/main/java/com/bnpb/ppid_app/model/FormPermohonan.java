package com.bnpb.ppid_app.model;

import com.google.gson.annotations.SerializedName;

public class FormPermohonan {
    @SerializedName("id_pengajuan")
    private String id_pengajuan;

    @SerializedName("nama_pemohon")
    private String nama_pemohon;

    @SerializedName("alamat_pemohon")
    private String alamat_pemohon;

    @SerializedName("no_pemohon")
    private String no_pemohon;

    @SerializedName("email_pemohon")
    private String email_pemohon;

    @SerializedName("info_butuh")
    private String info_butuh;

    @SerializedName("info_alasan")
    private String info_alasan;

    @SerializedName("nama_info")
    private String nama_info;

    @SerializedName("alamat_info")
    private String alamat_info;

    @SerializedName("no_info")
    private String no_info;

    @SerializedName("email_info")
    private String email_info;

    @SerializedName("waktu_buat")
    private String waktu_buat;

    @SerializedName("info_tujuan")
    private String info_tujuan;

    @SerializedName("jawab_pemohon")
    private String jawab_pemohon;

    @SerializedName("data_file")
    private String data_file;

    public String getId_pengajuan() {
        return id_pengajuan;
    }

    public void setId_pengajuan(String id_pengajuan) {
        this.id_pengajuan = id_pengajuan;
    }

    public String getNama_pemohon() {
        return nama_pemohon;
    }

    public void setNama_pemohon(String nama_pemohon) {
        this.nama_pemohon = nama_pemohon;
    }

    public String getAlamat_pemohon() {
        return alamat_pemohon;
    }

    public void setAlamat_pemohon(String alamat_pemohon) {
        this.alamat_pemohon = alamat_pemohon;
    }

    public String getNo_pemohon() {
        return no_pemohon;
    }

    public void setNo_pemohon(String no_pemohon) {
        this.no_pemohon = no_pemohon;
    }

    public String getEmail_pemohon() {
        return email_pemohon;
    }

    public void setEmail_pemohon(String email_pemohon) {
        this.email_pemohon = email_pemohon;
    }

    public String getInfo_butuh() {
        return info_butuh;
    }

    public void setInfo_butuh(String info_butuh) {
        this.info_butuh = info_butuh;
    }

    public String getInfo_alasan() {
        return info_alasan;
    }

    public void setInfo_alasan(String info_alasan) {
        this.info_alasan = info_alasan;
    }

    public String getNama_info() {
        return nama_info;
    }

    public void setNama_info(String nama_info) {
        this.nama_info = nama_info;
    }

    public String getAlamat_info() {
        return alamat_info;
    }

    public void setAlamat_info(String alamat_info) {
        this.alamat_info = alamat_info;
    }

    public String getNo_info() {
        return no_info;
    }

    public void setNo_info(String no_info) {
        this.no_info = no_info;
    }

    public String getEmail_info() {
        return email_info;
    }

    public void setEmail_info(String email_info) {
        this.email_info = email_info;
    }

    public String getWaktu_buat() {
        return waktu_buat;
    }

    public void setWaktu_buat(String waktu_buat) {
        this.waktu_buat = waktu_buat;
    }

    public String getJawab_pemohon() {
        return jawab_pemohon;
    }

    public void setJawab_pemohon(String jawab_pemohon) {
        this.jawab_pemohon = jawab_pemohon;
    }

    public String getData_file() {
        return data_file;
    }

    public void setData_file(String data_file) {
        this.data_file = data_file;
    }

    public String getInfo_tujuan() {
        return info_tujuan;
    }

    public void setInfo_tujuan(String info_tujuan) {
        this.info_tujuan = info_tujuan;
    }
}
