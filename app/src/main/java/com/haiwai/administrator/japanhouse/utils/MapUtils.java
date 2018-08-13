package com.haiwai.administrator.japanhouse.utils;

import android.util.Log;

/**
 * Created by Administrator on 2018/6/5.
 */

public class MapUtils {
    private static final double PI = 3.14159265358979323; //圆周率
    private static final double R = 6371229;              //地球的半径

    /**
     * 计算地球上任意两点(经纬度)距离
     *
     * @param long1 第一点经度
     * @param lat1  第一点纬度
     * @param long2 第二点经度
     * @param lat2  第二点纬度
     * @return 返回距离 单位：米
     */
    public static double distanceByLongNLat(double long1, double lat1, double long2, double lat2) {
        double a, b, R;
        R = 6378137;//地球半径
        lat1 = lat1 * Math.PI / 180.0;
        lat2 = lat2 * Math.PI / 180.0;
        a = lat1 - lat2;
        b = (long1 - long2) * Math.PI / 180.0;
        double d;
        double sa2, sb2;
        sa2 = Math.sin(a / 2.0);
        sb2 = Math.sin(b / 2.0);
        d = 2 * R * Math.asin(Math.sqrt(sa2 * sa2 + Math.cos(lat1) * Math.cos(lat2) * sb2 * sb2));
        return d;
    }

    /**
     * 根据经纬度和半径计算经纬度范围
     *
     * @param raidus 单位米
     * @return minLat, minLng, maxLat, maxLng
     */
    public static double[] getAround(double lat, double lon, int raidus) {

        Double latitude = lat;
        Double longitude = lon;

        Double degree = (24901 * 1609) / 360.0;
        double raidusMile = raidus;

        Double dpmLat = 1 / degree;
        Double radiusLat = dpmLat * raidusMile;
        Double minLat = latitude - radiusLat;
        Double maxLat = latitude + radiusLat;

        Double mpdLng = degree * Math.cos(latitude * (PI / 180));
        Double dpmLng = 1 / mpdLng;
        Double radiusLng = dpmLng * raidusMile;
        Double minLng = longitude - radiusLng;
        Double maxLng = longitude + radiusLng;
        return new double[]{minLat, minLng, maxLat, maxLng};
    }

    /*
    * 召鹏是这样写的，为了统一我也这样写吧
    * */
    public static double[] getjingweidufanwei(double lat, double lon, double raidus) {
        double weiducha = raidus / 111000.0;
        weiducha=(double)Math.round(weiducha*1000000)/1000000;
        Log.e("xxx当前位置","纬度："+lat+"    经度："+lon);
        Log.e("xxx纬度差",weiducha+"");
        double startWd = lat - weiducha;
        startWd=(double)Math.round(startWd*1000000)/1000000;
        double endWd = lat + weiducha;
        endWd=(double)Math.round(endWd*1000000)/1000000;
        double cos1 = Math.cos(lat);
        cos1=(double)Math.round(cos1*1000000)/1000000;
        double cos = Math.abs(cos1);
        double jingducha = weiducha / cos;
        jingducha=(double)Math.round(jingducha*1000000)/1000000;
        Log.e("xxxcos后",cos+"");
        Log.e("xxx经度差",jingducha+"");
        double startJd = lon - jingducha;
        double endJd = lon + jingducha;
        return new double[]{startJd, endJd, startWd, endWd};
    }

}
