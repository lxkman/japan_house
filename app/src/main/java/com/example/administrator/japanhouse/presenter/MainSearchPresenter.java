package com.example.administrator.japanhouse.presenter;

import android.app.Activity;

import com.example.administrator.japanhouse.MyApplication;
import com.example.administrator.japanhouse.bean.BieShuListBean;
import com.example.administrator.japanhouse.bean.OldHouseListBean;
import com.example.administrator.japanhouse.callback.DialogCallback;
import com.example.administrator.japanhouse.callback.JsonCallback;
import com.example.administrator.japanhouse.model.SearchBusinessBean;
import com.example.administrator.japanhouse.model.SearchLandBean;
import com.example.administrator.japanhouse.model.TopSearchHintBean;
import com.example.administrator.japanhouse.utils.MyUrls;
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
     * searchType 0 2 4
     * 新房 二手房 租房
     *
     * @param pageNo
     * @param searchType
     * @param searchText
     */
    public void getSearchNewHouse(int pageNo, int searchType, String searchText) {
        int languageType = 0;
        if (MyApplication.isJapanese()) {
            languageType = 1;
        }

        HttpParams params = new HttpParams();
        params.put("pageNo", pageNo);
        params.put("searchType", searchType);
        params.put("searchText", searchText);
        params.put("languageType", languageType);
        OkGo.<OldHouseListBean>post(MyUrls.BASEURL + "/app/index/indexsearch")
                .tag(this)
                .params(params)
                .execute(new DialogCallback<OldHouseListBean>(activity, OldHouseListBean.class) {
                    @Override
                    public void onSuccess(Response<OldHouseListBean> response) {
//                        callBack.getSearchNewHouse(response);
                    }
                });
    }

    /**
     * searchType 1
     * 别墅
     *
     * @param pageNo
     * @param searchText
     */
    public void getSearchVilla(int pageNo, String searchText) {
        int languageType = 0;

        if (MyApplication.isJapanese()) {
            languageType = 1;
        }

        HttpParams params = new HttpParams();
        params.put("pageNo", pageNo);
        params.put("searchType", 1);
        params.put("searchText", searchText);
        params.put("languageType", languageType);
        OkGo.<BieShuListBean>post(MyUrls.BASEURL + "/app/index/indexsearch")
                .tag(this)
                .params(params)
                .execute(new DialogCallback<BieShuListBean>(activity, BieShuListBean.class) {
                    @Override
                    public void onSuccess(Response<BieShuListBean> response) {
//                        callBack.getSearchVilla(response);
                    }
                });
    }

    /**
     * searchType 3
     * 土地
     *
     * @param pageNo
     * @param searchText
     */
    public void getSearchLand(int pageNo, String searchText) {
        int languageType = 0;
        if (MyApplication.isJapanese()) {
            languageType = 1;
        }

        HttpParams params = new HttpParams();
        params.put("pageNo", pageNo);
        params.put("searchType", 3);
        params.put("searchText", searchText);
        params.put("languageType", languageType);
        OkGo.<SearchLandBean>post(MyUrls.BASEURL + "/app/index/indexsearch")
                .tag(this)
                .params(params)
                .execute(new DialogCallback<SearchLandBean>(activity, SearchLandBean.class) {
                    @Override
                    public void onSuccess(Response<SearchLandBean> response) {
//                        callBack.getSearchLand(response);
                    }
                });
    }

    /**
     * searchType 5
     * 商业地产
     *
     * @param pageNo
     * @param searchText
     */
    public void getSearchBusiness(int pageNo, String searchText) {
        int languageType = 0;
        if (MyApplication.isJapanese()) {
            languageType = 1;
        }

        HttpParams params = new HttpParams();
        params.put("pageNo", pageNo);
        params.put("searchType", 5);
        params.put("searchText", searchText);
        params.put("languageType", languageType);
        OkGo.<SearchBusinessBean>post(MyUrls.BASEURL + "/app/index/indexsearch")
                .tag(this)
                .params(params)
                .execute(new DialogCallback<SearchBusinessBean>(activity, SearchBusinessBean.class) {
                    @Override
                    public void onSuccess(Response<SearchBusinessBean> response) {
//                        callBack.getSearchBusiness(response);
                    }
                });
    }

    public interface MainSearchCallBack {
        void getSearchHint(Response<TopSearchHintBean> response);

//        void getSearchNewHouse(Response<OldHouseListBean> response);
//
//        void getSearchVilla(Response<BieShuListBean> response);
//
//        void getSearchLand(Response<SearchLandBean> response);
//
//        void getSearchBusiness(Response<SearchBusinessBean> response);
    }
}
