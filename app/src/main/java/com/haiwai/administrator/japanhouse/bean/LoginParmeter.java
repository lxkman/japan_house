package com.haiwai.administrator.japanhouse.bean;

/**
 * Created by   admin on 2018/5/11.
 */

public class LoginParmeter {
    public String id;
    public String nickname;
    public String avatar;
    public String gender;
    public String age;
    public String email;
    public String accessToken;


    @Override
    public String toString() {
        return "LoginParmeter{" +
                "id='" + id + '\'' +
                ", nickname='" + nickname + '\'' +
                ", avatar='" + avatar + '\'' +
                ", gender='" + gender + '\'' +
                ", age='" + age + '\'' +
                ", email='" + email + '\'' +
                ", accessToken='" + accessToken + '\'' +
                '}';
    }
}
