package com.example.administrator.japanhouse.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.administrator.japanhouse.MyApplication;

public class SpUtils {
    private static SharedPreferences mSharedPreferences;

    public static void putString(String key, String value){
        mSharedPreferences =  MyApplication.getGloableContext().getSharedPreferences(MyUrls.FILENAME,Context.MODE_PRIVATE);
        mSharedPreferences.edit().putString(key,value).commit();
    }

    public static String getString(String key,String defValue){
        mSharedPreferences=MyApplication.getGloableContext().getSharedPreferences(MyUrls.FILENAME,Context.MODE_PRIVATE);
        return mSharedPreferences.getString(key,defValue);
    }

    public static void putBoolean(String key,boolean value){
        mSharedPreferences=MyApplication.getGloableContext().getSharedPreferences(MyUrls.FILENAME,Context.MODE_PRIVATE);
        mSharedPreferences.edit().putBoolean(key,value).commit();
    }

    public static boolean getBoolean(String key,boolean defValue){
        mSharedPreferences=MyApplication.getGloableContext().getSharedPreferences(MyUrls.FILENAME,Context.MODE_PRIVATE);
        return mSharedPreferences.getBoolean(key,defValue);
    }

    public static void putInt(String key,int value){
        mSharedPreferences=MyApplication.getGloableContext().getSharedPreferences(MyUrls.FILENAME,Context.MODE_PRIVATE);
        mSharedPreferences.edit().putInt(key,value).commit();
    }

    public static int getInt(String key,int defValue){
        mSharedPreferences=MyApplication.getGloableContext().getSharedPreferences(MyUrls.FILENAME,Context.MODE_PRIVATE);
        return mSharedPreferences.getInt(key,defValue);
    }

}
