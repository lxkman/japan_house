package com.haiwai.administrator.japanhouse.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Mr赵 on 2018/4/17.
 */

public  class ToastUtils {
    private static Toast toast;
    public static  void getToast(Context ctx, String msg){
        if(toast==null){
            toast=Toast.makeText(ctx,"",Toast.LENGTH_SHORT);
        }
        toast.setText(msg);
        toast.show();
    }

}
