package com.haiwai.administrator.japanhouse.presenter;

import android.app.Activity;

import com.haiwai.administrator.japanhouse.MyApplication;
import com.haiwai.administrator.japanhouse.bean.HotSearchBean;
import com.haiwai.administrator.japanhouse.callback.JsonCallback;
import com.haiwai.administrator.japanhouse.model.SimpleBean;
import com.haiwai.administrator.japanhouse.model.TopSearchHintBean;
import com.haiwai.administrator.japanhouse.utils.MyUrls;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

/**
 * Created by   admin on 2018/5/21.
 */

public class MainSearchPresenter {
    private Activity activity;
    private MainSearchCallBack callBack;

    public MainSearchPresenter(Activity activity, MainSearchCallBack callBack) {
        this.activity = activity;
        this.callBack = callBack;
    }

    /**
     * 首页顶部搜索-提示语
     *
     * @param searchType
     * @param searchText
     */
    public void getSearchHint(int searchType, String searchText) {
        String languageType = "0";
        if (MyApplication.isJapanese()) {
            languageType = "1";
        }
        HttpParams params = new HttpParams();
        params.put("searchType", searchType);
        params.put("searchText", searchText);
        params.put("languageType", languageType);
        OkGo.<TopSearchHintBean>post(MyUrls.BASEURL + "/app/index/gettheclues")
                .tag(this)
                .params(params)
                .execute(new JsonCallback<TopSearchHintBean>(TopSearchHintBean.class) {
                    @Override
                    public void onSuccess(Response<TopSearchHintBean> response) {
                        callBack.getSearchHint(response);
                    }
                });
    }

    /**
     * 首页顶部搜索-提示语--二级的
     *
     * @param searchType
     * @param searchText
     */
    public void getSearchHint(int searchType, int searchsType, String searchText) {
        String languageType = "0";
        if (MyApplication.isJapanese()) {
            languageType = "1";
        }
        HttpParams params = new HttpParams();
        params.put("searchType", searchType);
        params.put("searchsType", searchsType);
        params.put("searchText", searchText);
        params.put("languageType", languageType);
        OkGo.<TopSearchHintBean>post(MyUrls.BASEURL + "/app/index/gettheclues")
                .tag(this)
                .params(params)
                .execute(new JsonCallback<TopSearchHintBean>(TopSearchHintBean.class) {
                    @Override
                    public void onSuccess(Response<TopSearchHintBean> response) {
                        callBack.getSearchHint(response);
                    }
                });
    }

    /**
     * 热门搜索
     */
    public void getHotSearchData(int pageNo, int type) {
        HttpParams params = new HttpParams();
        params.put("pageNo", pageNo);
        params.put("type", type);
        OkGo.<HotSearchBean>post(MyUrls.BASEURL + "/app/searchtag/hotsearchtag")
                .tag(this)
                .params(params)
                .execute(new JsonCallback<HotSearchBean>(HotSearchBean.class) {
                    @Override
                    public void onSuccess(Response<HotSearchBean> response) {
                        callBack.getHotSearchData(response);
                    }
                });
    }

    public void getWdSearchHint(String searchText) {
        HttpParams params = new HttpParams();
        params.put("searchText", searchText);
        OkGo.<SimpleBean>post(MyUrls.BASEURL + "/app/askinfo/theclues")
                .tag(this)
                .params(params)
                .execute(new JsonCallback<SimpleBean>(SimpleBean.class) {
                    @Override
                    public void onSuccess(Response<SimpleBean> response) {
                        callBack.getWdSearchHint(response);
                    }
                });
    }

    public interface MainSearchCallBack {
        void getSearchHint(Response<TopSearchHintBean> response);

        void getHotSearchData(Response<HotSearchBean> response);

        void getWdSearchHint(Response<SimpleBean> response);
    }
}
