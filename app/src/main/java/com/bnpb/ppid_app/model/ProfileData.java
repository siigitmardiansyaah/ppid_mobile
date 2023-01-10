package com.bnpb.ppid_app.model;

import com.google.gson.annotations.SerializedName;

public class ProfileData {

    @SerializedName("user_id")
    private String user_id;

    @SerializedName("user_name")
    private String user_name;

    @SerializedName("user_email")
    private String user_email;

    @SerializedName("no_hp")
    private String no_hp;

    @SerializedName("user_level")
    private String user_level;


    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getNo_hp() {
        return no_hp;
    }

    public void setNo_hp(String no_hp) {
        this.no_hp = no_hp;
    }


}
