package com.haiwai.administrator.japanhouse.utils;

import com.bumptech.glide.request.RequestOptions;
import com.haiwai.administrator.japanhouse.R;

/**
 * Created by Administrator on 2018/7/24.
 */

public class GlideReqUtils {
    public static RequestOptions getReq() {
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.error(R.drawable.default_img);
        return requestOptions;
    }
}
