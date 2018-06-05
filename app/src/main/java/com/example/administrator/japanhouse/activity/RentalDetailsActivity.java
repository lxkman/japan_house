package com.example.administrator.japanhouse.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.media.MediaMetadataRetriever;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.japanhouse.R;
import com.example.administrator.japanhouse.adapter.RentalDetailsPicAdapter;
import com.example.administrator.japanhouse.base.BaseActivity;
import com.example.administrator.japanhouse.bean.RentalDetailsBean;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 出租 / 出售 详情
 * Created by   admin on 2018/4/17.
 */

public class RentalDetailsActivity extends BaseActivity {

    @BindView(R.id.act_rental_details_title)
    TextView tvTitle;
    @BindView(R.id.act_rental_details_rentalState)
    TextView tvRentalState;
    @BindView(R.id.act_rental_details_liner_rentalState)
    LinearLayout ltRentalState;
    @BindView(R.id.act_rental_details_refuseReason)
    TextView tvRefuseReason;
    @BindView(R.id.act_rental_details_liner_refuseReason)
    LinearLayout ltRefuseReason;
    @BindView(R.id.act_rental_details_lessorName)
    TextView tvLessorName;
    @BindView(R.id.act_rental_details_liner_lessorName)
    LinearLayout ltLessorName;
    @BindView(R.id.act_rental_details_lessorPhone)
    TextView tvLessorPhone;
    @BindView(R.id.act_rental_details_liner_lessorPhone)
    LinearLayout ltLessorPhone;
    @BindView(R.id.act_rental_details_location)
    TextView tvLocation;
    @BindView(R.id.act_rental_details_liner_location)
    LinearLayout ltLoction;
    @BindView(R.id.act_rental_details_distance)
    TextView tvDistance;
    @BindView(R.id.act_rental_details_liner_distance)
    LinearLayout ltDistance;
    @BindView(R.id.act_rental_details_floor)
    TextView tvFloor;
    @BindView(R.id.act_rental_details_liner_floor)
    LinearLayout ltFloor;
    @BindView(R.id.act_rental_details_area)
    TextView tvArea;
    @BindView(R.id.act_rental_details_liner_area)
    LinearLayout ltArea;
    @BindView(R.id.act_rental_details_pattern)
    TextView tvPattern;
    @BindView(R.id.act_rental_details_liner_pattern)
    LinearLayout ltPattern;
    @BindView(R.id.act_rental_details_bathroom)
    TextView tvBathroom;
    @BindView(R.id.act_rental_details_liner_bathroom)
    LinearLayout ltBathroom;
    @BindView(R.id.act_rental_details_toward)
    TextView tvToward;
    @BindView(R.id.act_rental_details_liner_toward)
    LinearLayout ltToward;
    @BindView(R.id.act_rental_details_equipment)
    TextView tvEquipment;
    @BindView(R.id.act_rental_details_liner_equipment)
    LinearLayout ltEquipment;
    @BindView(R.id.act_rental_details_roomPic)
    GridView roomPic;
    @BindView(R.id.act_rental_details_liner_roomPic)
    LinearLayout ltRoomPic;
    @BindView(R.id.act_rental_details_img)
    ImageView roomVideo;
    @BindView(R.id.act_rental_details_liner_roomVideo)
    LinearLayout ltRoomVideo;

    private RentalDetailsPicAdapter picAdapter;

    private String path;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rental_details);
        ButterKnife.bind(this);

        final RentalDetailsBean detailsBean = getIntent().getParcelableExtra("detailsBean");

        if (!TextUtils.isEmpty(detailsBean.getTitle())) {
            tvTitle.setText(detailsBean.getTitle());
        }

        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.act_rental_details_relative);

        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        float density = dm.density;
        int mWidth = (int) (width / density);
        LinearLayout.LayoutParams linearParams = (LinearLayout.LayoutParams) relativeLayout.getLayoutParams();
        linearParams.height = (mWidth - 46) / 3 * 22 / 15;
        linearParams.width = (width - 52) / 3;
        relativeLayout.setLayoutParams(linearParams);

        init(detailsBean);

        findViewById(R.id.act_rental_details_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if(detailsBean.getIsCreate() != 0){
//                    Intent intent = new Intent(RentalDetailsActivity.this, SellHouseActivity.class);
//                    startActivity(intent);
//                    setResult(102);
//                    finish();
//                }else{
                finish();
//                }

            }
        });

        roomVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FullScreenActivity.invoke(RentalDetailsActivity.this, path);
            }
        });
    }

    private void init(RentalDetailsBean detailsBean) {
//        switch (detailsBean.getRoomState()) {
//            case 1:
//                tvRentalState.setText(getString(R.string.activity_rental_details_audit));
//                tvRentalState.setTextColor(Color.GREEN);
//                break;
//            case 2:
//                tvRentalState.setText(getString(R.string.activity_rental_details_refused));
//                tvRentalState.setTextColor(Color.RED);
//                break;
//            case 3:
//
//                break;
//        }
//0审核中 1已拒绝 2已通过
        if (TextUtils.equals(detailsBean.getRoomState(), "0")) {
            tvRentalState.setText(getString(R.string.activity_rental_details_audit));
            tvRentalState.setTextColor(Color.GREEN);
        } else if (TextUtils.equals(detailsBean.getRoomState(), "1")) {
            tvRentalState.setText(getString(R.string.activity_rental_details_refused));
            tvRentalState.setTextColor(Color.RED);
        } else if (TextUtils.equals(detailsBean.getRoomState(), "2")) {

        }

        if (!TextUtils.isEmpty(detailsBean.getRefuseReason())) {
            tvRefuseReason.setText(detailsBean.getRefuseReason());
        } else {
            ltRefuseReason.setVisibility(View.GONE);
        }

        if (!TextUtils.isEmpty(detailsBean.getArea())) {
            tvArea.setText(detailsBean.getArea());
        } else {
            ltArea.setVisibility(View.GONE);
        }

        if (!TextUtils.isEmpty(detailsBean.getBathroom())) {
            if (TextUtils.equals(detailsBean.getBathroom(), "0")) {
                tvBathroom.setText(getString(R.string.yes));
            } else {
                tvBathroom.setText(getString(R.string.no));
            }

        } else {
            ltBathroom.setVisibility(View.GONE);
        }

        if (!TextUtils.isEmpty(detailsBean.getDistance())) {
            tvDistance.setText(detailsBean.getDistance());
        } else {
            ltDistance.setVisibility(View.GONE);
        }

        if (!TextUtils.isEmpty(detailsBean.getEquipment())) {
            tvEquipment.setText(detailsBean.getEquipment());
        } else {
            ltEquipment.setVisibility(View.GONE);
        }

        if (!TextUtils.isEmpty(detailsBean.getFloor())) {
            tvFloor.setText(detailsBean.getFloor());
        } else {
            ltFloor.setVisibility(View.GONE);
        }

        if (!TextUtils.isEmpty(detailsBean.getLessorName())) {
            tvLessorName.setText(detailsBean.getLessorName());
        } else {
            ltLessorName.setVisibility(View.GONE);
        }

        if (!TextUtils.isEmpty(detailsBean.getLessorPhone())) {
            tvLessorPhone.setText(detailsBean.getLessorPhone());
        } else {
            ltLessorPhone.setVisibility(View.GONE);
        }

        if (!TextUtils.isEmpty(detailsBean.getPattern())) {
            tvPattern.setText(detailsBean.getPattern());
        } else {
            ltPattern.setVisibility(View.GONE);
        }

        if (!TextUtils.isEmpty(detailsBean.getLocation())) {
            tvLocation.setText(detailsBean.getLocation());
        } else {
            ltLoction.setVisibility(View.GONE);
        }

        if (!TextUtils.isEmpty(detailsBean.getToward())) {
            tvToward.setText(detailsBean.getToward());
        } else {
            ltToward.setVisibility(View.GONE);
        }

        if (detailsBean.getPicList() != null && detailsBean.getPicList().size() > 0) {
            picAdapter = new RentalDetailsPicAdapter(this, detailsBean.getPicList());
            roomPic.setAdapter(picAdapter);
        } else {
            ltRoomPic.setVisibility(View.GONE);
        }

        if (detailsBean.getVideoPic() != null) {
            path = detailsBean.getVideoList();
            Glide.with(this).load(detailsBean.getVideoPic()).into(roomVideo);
        } else {
            if (!TextUtils.isEmpty(detailsBean.getVideoList())) {
                path = detailsBean.getVideoList();
                roomVideo.setImageBitmap(getLocalVideoBitmap(path));
            } else {
                ltRoomVideo.setVisibility(View.GONE);
            }
        }
    }

    public static Bitmap getNetVideoBitmap(String videoUrl) {
        Bitmap bitmap = null;

        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
        try {
            //根据url获取缩略图
            retriever.setDataSource(videoUrl, new HashMap());
            //获得第一帧图片
            bitmap = retriever.getFrameAtTime();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } finally {
            retriever.release();
        }
        return bitmap;
    }

    public static Bitmap getLocalVideoBitmap(String localPath) {
        Bitmap bitmap = null;
        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
        try {
            //根据文件路径获取缩略图
            retriever.setDataSource(localPath);
            //获得第一帧图片
            bitmap = retriever.getFrameAtTime();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } finally {
            retriever.release();
        }
        return bitmap;
    }

    public static void invoke(Context context, RentalDetailsBean detailsBean) {
        Intent intent = new Intent(context, RentalDetailsActivity.class);
        intent.putExtra("detailsBean", detailsBean);
        context.startActivity(intent);
    }
}
