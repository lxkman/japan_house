package com.example.administrator.japanhouse.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.RelativeSizeSpan;
import android.text.style.SuperscriptSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.administrator.japanhouse.MyApplication;
import com.example.administrator.japanhouse.R;
import com.example.administrator.japanhouse.activity.adapter.GridViewAddImgesAdpter;
import com.example.administrator.japanhouse.activity.adapter.GridViewAddVideoAdapter;
import com.example.administrator.japanhouse.adapter.PicRentalAdapter;
import com.example.administrator.japanhouse.bean.RentalDetailsBean;
import com.example.administrator.japanhouse.login.LoginActivity;
import com.example.administrator.japanhouse.model.FileBean;
import com.example.administrator.japanhouse.model.NoDataBean;
import com.example.administrator.japanhouse.presenter.RentalPresenter;
import com.example.administrator.japanhouse.presenter.UpFilePresenter;
import com.example.administrator.japanhouse.utils.TUtils;
import com.example.administrator.japanhouse.view.HeightGridView;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.compress.Luban;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.lzy.okgo.model.Response;
import com.wevey.selector.dialog.DialogInterface;
import com.wevey.selector.dialog.NormalSelectionDialog;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import id.zelory.compressor.Compressor;

/**
 * admin  2018/6/26
 */
public class RentalFragment extends Fragment  implements PicRentalAdapter.onItemClickListener, RentalPresenter.RentalCallBack, UpFilePresenter.UpFileCallBack{

    @BindView(R.id.act_rental_call)
    EditText etCall;
    @BindView(R.id.act_rental_contact)
    EditText etContact;
    @BindView(R.id.act_rental_location)
    EditText etLocation;
    @BindView(R.id.act_rental_distance)
    EditText etDistance;
    @BindView(R.id.act_rental_floor)
    EditText etFloor;
    @BindView(R.id.act_rental_area)
    EditText etArea;
    @BindView(R.id.act_rental_areaSpan)
    TextView tvAreaSpan;
    @BindView(R.id.act_rental_pattern)
    EditText etPattern;
    @BindView(R.id.act_rental_check)
    RadioButton rbCheck;
    @BindView(R.id.act_rental_checked)
    RadioButton rbChecked;
    @BindView(R.id.act_rental_toward)
    EditText etToward;
    @BindView(R.id.act_rental_equipment)
    EditText etEquipment;
    @BindView(R.id.act_rental_picRecyclerView)
    GridView picRecyclerView;
    @BindView(R.id.act_rental_videoImg)
    GridView imgVideo;
    @BindView(R.id.act_rental_entrust)
    TextView tvEntrust;
    Unbinder unbinder;

    private boolean isBathroom = true;

    private boolean isHaveVideo = false;

    //相册，拍照
    private List<String> cameraList;
    private List<String> cameraListvideo;
    private List<String> imgPathList = new ArrayList<>();
    private List<String> videoPathList = new ArrayList<>();

    private String videoPath = "";

    private List<File> files = new ArrayList<>();

    GridViewAddImgesAdpter adapter;
    GridViewAddVideoAdapter videoAdapter;

    private NestedScrollView nestedScrollView;

    private RentalPresenter presenter;

    private UpFilePresenter filePresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_rental, null);
        unbinder = ButterKnife.bind(this, view);

        filePresenter = new UpFilePresenter(getActivity(), this);

        nestedScrollView = (NestedScrollView) view.findViewById(R.id.act_rental_scrollView);

        cameraList = new ArrayList<>();
        cameraList.add(getString(R.string.camera_pic));
        cameraList.add(getString(R.string.chooise_camera));
        cameraListvideo = new ArrayList<>();
        cameraListvideo.add(getString(R.string.camera_video));
        cameraListvideo.add(getString(R.string.chooise_camera));

        //显示平方米
        SpannableString m2 = new SpannableString("m2");
        m2.setSpan(new RelativeSizeSpan(0.5f), 1, 2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        m2.setSpan(new SuperscriptSpan(), 1, 2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvAreaSpan.setText(m2);


        videoAdapter = new GridViewAddVideoAdapter(videoPathList, getActivity());
        imgVideo.setAdapter(videoAdapter);
        imgVideo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (videoPathList != null && videoPathList.size() == position) {
                    showVideo();
                } else if (videoPathList != null && videoPathList.size() == 0 && position == 0) {
                    showVideo();
                } else {
                    FullScreenActivity.invoke(getActivity(), videoPath);
                }
            }
        });

//        GridLayoutManager manager = new GridLayoutManager(this, 3);
//        picRecyclerView.setLayoutManager(manager);
        adapter = new GridViewAddImgesAdpter(imgPathList, getActivity());
//        adapter.setOnItemClickListener(this);
        picRecyclerView.setAdapter(adapter);

        picRecyclerView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (imgPathList != null && imgPathList.size() == position) {
                    showImage();
                } else if (imgPathList != null && imgPathList.size() == 0 && position == 0) {
                    showImage();
                }
            }
        });

        presenter = new RentalPresenter(getActivity(), this);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.act_rental_check, R.id.act_rental_checked, R.id.act_rental_entrust})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.act_rental_check:
                isBathroom = true;
                break;
            case R.id.act_rental_checked:
                isBathroom = false;
                break;
            case R.id.act_rental_entrust:
                if (!TextUtils.isEmpty(etCall.getText().toString()) && !TextUtils.isEmpty(etContact.getText().toString())) {
                    if (imgPathList.size() > 0 || !videoPath.equals("")) {

                        if (imgPathList != null && imgPathList.size() > 0) {
                            for (int i = 0; i < imgPathList.size(); i++) {
//                                files.add(new File(BitmapUtil.compressImage(imgPathList.get(i), 100)));
                                try {
                                    files.add(new Compressor(getActivity())
                                            .setMaxWidth(640)
                                            .setMaxHeight(480)
                                            .setQuality(75)
                                            .compressToFile(new File(imgPathList.get(i))));
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }

                        if (videoPath != null && !"".equals(videoPath)) {

//                            final LocalMediaConfig config = new LocalMediaConfig.Buidler()
//                                    .setVideoPath(videoPath)
//                                    .captureThumbnailsTime(1)
//                                    .doH264Compress(new AutoVBRMode(18))
//                                    .setFramerate(15)
//                                    .build();
//                            OnlyCompressOverBean onlyCompressOverBean = new LocalMediaCompress(config).startCompress();

                            files.add(new File(videoPath));
                            isHaveVideo = true;
                        } else {
                            isHaveVideo = false;
                        }

                        filePresenter.upFileRequest(files);

                    } else {
                        presenter.requestRental(etCall.getText().toString(),
                                etContact.getText().toString(),
                                etLocation.getText().toString(),
                                etDistance.getText().toString(),
                                etFloor.getText().toString(),
                                etArea.getText().toString(),
                                etPattern.getText().toString(),
                                isBathroom ? "0" : "1",
                                etToward.getText().toString(),
                                etEquipment.getText().toString(),
                                "1",
                                "",
                                "");
                    }
                } else if (TextUtils.isEmpty(etCall.getText().toString())) {
                    TUtils.showFail(getActivity(), getString(R.string.activity_rental_call_hint));
                } else if (TextUtils.isEmpty(etContact.getText().toString())) {
                    TUtils.showFail(getActivity(), getString(R.string.activity_rental_contact_hint));
                }
                break;
        }
    }

    //选择相册
    private void showImage() {

        new NormalSelectionDialog.Builder(getActivity()).setlTitleVisible(false)   //设置是否显示标题
                .setItemHeight(55)  //设置item的高度
                .setItemWidth(0.9f)  //屏幕宽度*0.9
                .setItemTextColor(R.color.text_black)  //设置item字体颜色
                .setItemTextSize(16)  //设置item字体大小
                .setCancleButtonText(getString(R.string.cancel))  //设置最底部“取消”按钮文本
                .setOnItemListener(new DialogInterface.OnItemClickListener<NormalSelectionDialog>() {
                    @Override
                    public void onItemClick(NormalSelectionDialog dialog, View button, int position) {
                        switch (position) {
                            case 1://从相册选择
                                requestPhoto();
                                break;
                            case 0://拍照
                                requestImage();
                                break;
                        }
                        dialog.dismiss();
                    }
                })
                .setCanceledOnTouchOutside(true)  //设置是否可点击其他地方取消dialog
                .build()
                .setDatas(cameraList)
                .show();
    }

    private void requestPhoto() {
        // 进入相册 以下是例子：不需要的api可以不写
        PictureSelector.create(this)
                .openGallery(PictureMimeType.ofImage())// 全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
                .theme(R.style.picture_default_style)// 主题样式设置 具体参考 values/styles   用法：R.style.picture.white.style
                .maxSelectNum(9)// 最大图片选择数量
                .minSelectNum(1)// 最小选择数量
                .imageSpanCount(4)// 每行显示个数
                .selectionMode(PictureConfig.MULTIPLE)// 多选 or 单选 PictureConfig.SINGLE
                .previewImage(true)// 是否可预览图片
                .previewVideo(false)// 是否可预览视频
                .enablePreviewAudio(false) // 是否可播放音频
                .compressGrade(Luban.THIRD_GEAR)// luban压缩档次，默认3档 Luban.FIRST_GEAR、Luban.CUSTOM_GEAR
                .isCamera(true)// 是否显示拍照按钮
                .isZoomAnim(true)// 图片列表点击 缩放效果 默认true
                //.setOutputCameraPath("/CustomPath")// 自定义拍照保存路径
                .enableCrop(false)// 是否裁剪
                .compress(false)// 是否压缩
                .compressMode(PictureConfig.LUBAN_COMPRESS_MODE)//系统自带 or 鲁班压缩 PictureConfig.SYSTEM_COMPRESS_MODE or LUBAN_COMPRESS_MODE
                //.sizeMultiplier(0.5f)// glide 加载图片大小 0~1之间 如设置 .glideOverride()无效
//                .glideOverride(200, 200)// glide 加载宽高，越小图片列表越流畅，但会影响列表图片浏览的清晰度
                //                .withAspectRatio(aspect_ratio_x, aspect_ratio_y)// 裁剪比例 如16:9 3:2 3:4 1:1 可自定义
                .hideBottomControls(true)// 是否显示uCrop工具栏，默认不显示
                .isGif(false)// 是否显示gif图片
                .freeStyleCropEnabled(false)// 裁剪框是否可拖拽
                .circleDimmedLayer(false)// 是否圆形裁剪
                .showCropFrame(false)// 是否显示裁剪矩形边框 圆形裁剪时建议设为false
                .showCropGrid(false)// 是否显示裁剪矩形网格 圆形裁剪时建议设为false
                .openClickSound(false)// 是否开启点击声音
                //                .selectionMedia(list)// 是否传入已选图片
                //                        .videoMaxSecond(15)
                //                        .videoMinSecond(10)
                //.previewEggs(false)// 预览图片时 是否增强左右滑动图片体验(图片滑动一半即可看到上一张是否选中)
                //.cropCompressQuality(90)// 裁剪压缩质量 默认100
                //.compressMaxKB()//压缩最大值kb compressGrade()为Luban.CUSTOM_GEAR有效
                //.compressWH() // 压缩宽高比 compressGrade()为Luban.CUSTOM_GEAR有效
                //.cropWH()// 裁剪宽高比，设置如果大于图片本身宽高则无效
                //.rotateEnabled() // 裁剪是否可旋转图片
                .scaleEnabled(false)// 裁剪是否可放大缩小图片
                //.videoQuality()// 视频录制质量 0 or 1
                //.videoSecond()//显示多少秒以内的视频or音频也可适用
                //.recordVideoSecond()//录制视频秒数 默认60s
                .forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code
    }

    private void requestImage() {
        PictureSelector.create(this)
                .openCamera(PictureMimeType.ofImage())// 单独拍照，也可录像或也可音频 看你传入的类型是图片or视频
                .theme(R.style.picture_default_style)// 主题样式设置 具体参考 values/styles
                .enableCrop(false)// 是否裁剪
                .compress(false)// 是否压缩
                .compressMode(PictureConfig.LUBAN_COMPRESS_MODE)//系统自带 or 鲁班压缩 PictureConfig.SYSTEM_COMPRESS_MODE or LUBAN_COMPRESS_MODE
                .freeStyleCropEnabled(true)// 裁剪框是否可拖拽
                .circleDimmedLayer(true)// 是否圆形裁剪
                .showCropFrame(false)// 是否显示裁剪矩形边框 圆形裁剪时建议设为false
                .showCropGrid(false)// 是否显示裁剪矩形网格 圆形裁剪时建议设为false
                .scaleEnabled(false)// 裁剪是否可放大缩小图片
                //                .glideOverride(160, 160)// glide 加载宽高，越小图片列表越流畅，但会影响列表图片浏览的清晰度
                //                .selectionMedia(list)// 是否传入已选图片
                //                .previewEggs(true)//预览图片时 是否增强左右滑动图片体验(图片滑动一半即可看到上一张是否选中)
                .forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code
    }

    //选择相册
    private void showVideo() {

        new NormalSelectionDialog.Builder(getActivity()).setlTitleVisible(false)   //设置是否显示标题
                .setItemHeight(55)  //设置item的高度
                .setItemWidth(0.9f)  //屏幕宽度*0.9
                .setItemTextColor(R.color.text_black)  //设置item字体颜色
                .setItemTextSize(16)  //设置item字体大小
                .setCancleButtonText(getString(R.string.cancel))  //设置最底部“取消”按钮文本
                .setOnItemListener(new DialogInterface.OnItemClickListener<NormalSelectionDialog>() {
                    @Override
                    public void onItemClick(NormalSelectionDialog dialog, View button, int position) {
                        switch (position) {
                            case 1://从相册选择
                                requestVideo();
                                break;
                            case 0://拍照
                                requestCamera();
                                break;
                        }
                        dialog.dismiss();
                    }
                })
                .setCanceledOnTouchOutside(true)  //设置是否可点击其他地方取消dialog
                .build()
                .setDatas(cameraListvideo)
                .show();
    }

    private void requestVideo() {
        // 进入相册 以下是例子：不需要的api可以不写
        PictureSelector.create(this)
                .openGallery(PictureMimeType.ofVideo())// 全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
                .theme(R.style.picture_default_style)// 主题样式设置 具体参考 values/styles   用法：R.style.picture.white.style
                .maxSelectNum(1)// 最大图片选择数量
                .minSelectNum(1)// 最小选择数量
                .imageSpanCount(4)// 每行显示个数
                .selectionMode(PictureConfig.SINGLE)// 多选 or 单选 PictureConfig.SINGLE
                .previewImage(true)// 是否可预览图片
                .previewVideo(false)// 是否可预览视频
                .enablePreviewAudio(false) // 是否可播放音频
                .compressGrade(Luban.THIRD_GEAR)// luban压缩档次，默认3档 Luban.FIRST_GEAR、Luban.CUSTOM_GEAR
                .isCamera(true)// 是否显示拍照按钮
                .isZoomAnim(true)// 图片列表点击 缩放效果 默认true
                //.setOutputCameraPath("/CustomPath")// 自定义拍照保存路径
                .enableCrop(false)// 是否裁剪
                .compress(true)// 是否压缩
                .compressMode(PictureConfig.LUBAN_COMPRESS_MODE)//系统自带 or 鲁班压缩 PictureConfig.SYSTEM_COMPRESS_MODE or LUBAN_COMPRESS_MODE
                //.sizeMultiplier(0.5f)// glide 加载图片大小 0~1之间 如设置 .glideOverride()无效
//                .glideOverride(200, 200)// glide 加载宽高，越小图片列表越流畅，但会影响列表图片浏览的清晰度
                //                .withAspectRatio(aspect_ratio_x, aspect_ratio_y)// 裁剪比例 如16:9 3:2 3:4 1:1 可自定义
                .hideBottomControls(true)// 是否显示uCrop工具栏，默认不显示
                .isGif(false)// 是否显示gif图片
                .freeStyleCropEnabled(false)// 裁剪框是否可拖拽
                .circleDimmedLayer(false)// 是否圆形裁剪
                .showCropFrame(false)// 是否显示裁剪矩形边框 圆形裁剪时建议设为false
                .showCropGrid(false)// 是否显示裁剪矩形网格 圆形裁剪时建议设为false
                .openClickSound(false)// 是否开启点击声音
                //                .selectionMedia(list)// 是否传入已选图片
                //                        .videoMaxSecond(15)
                //                        .videoMinSecond(10)
                //.previewEggs(false)// 预览图片时 是否增强左右滑动图片体验(图片滑动一半即可看到上一张是否选中)
                //.cropCompressQuality(90)// 裁剪压缩质量 默认100
                //.compressMaxKB()//压缩最大值kb compressGrade()为Luban.CUSTOM_GEAR有效
                //.compressWH() // 压缩宽高比 compressGrade()为Luban.CUSTOM_GEAR有效
                //.cropWH()// 裁剪宽高比，设置如果大于图片本身宽高则无效
                //.rotateEnabled() // 裁剪是否可旋转图片
                .scaleEnabled(false)// 裁剪是否可放大缩小图片
                //.videoQuality()// 视频录制质量 0 or 1
                .videoSecond(60)//显示多少秒以内的视频or音频也可适用
                //.recordVideoSecond()//录制视频秒数 默认60s
                .forResult(PictureConfig.REQUEST_CAMERA);//结果回调onActivityResult code
    }

    private void requestCamera() {
        PictureSelector.create(this)
                .openCamera(PictureMimeType.ofVideo())// 单独拍照，也可录像或也可音频 看你传入的类型是图片or视频
                .theme(R.style.picture_default_style)// 主题样式设置 具体参考 values/styles
                .enableCrop(false)// 是否裁剪
                .compress(true)// 是否压缩
                .compressMode(PictureConfig.LUBAN_COMPRESS_MODE)//系统自带 or 鲁班压缩 PictureConfig.SYSTEM_COMPRESS_MODE or LUBAN_COMPRESS_MODE
                .freeStyleCropEnabled(true)// 裁剪框是否可拖拽
                .circleDimmedLayer(true)// 是否圆形裁剪
                .videoQuality(0)
                .showCropFrame(false)// 是否显示裁剪矩形边框 圆形裁剪时建议设为false
                .showCropGrid(false)// 是否显示裁剪矩形网格 圆形裁剪时建议设为false
                .scaleEnabled(false)// 裁剪是否可放大缩小图片
                //                .glideOverride(160, 160)// glide 加载宽高，越小图片列表越流畅，但会影响列表图片浏览的清晰度
                //                .selectionMedia(list)// 是否传入已选图片
                //                .previewEggs(true)//预览图片时 是否增强左右滑动图片体验(图片滑动一半即可看到上一张是否选中)
                .forResult(PictureConfig.REQUEST_CAMERA);//结果回调onActivityResult code
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == getActivity().RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:

                    // 图片选择结果回调
                    List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
                    for (int i = 0; i < selectList.size(); i++) {
                        if (imgPathList.size() < 9) {
                            imgPathList.add(selectList.get(i).getPath());
                        }
                    }

                    adapter.notifyDataSetChanged();
                    break;

                case PictureConfig.REQUEST_CAMERA:
                    List<LocalMedia> list = PictureSelector.obtainMultipleResult(data);
                    videoPath = list.get(0).getPath();
                    videoPathList.clear();
                    videoPathList.add(videoPath);
                    videoAdapter = new GridViewAddVideoAdapter(videoPathList, getActivity());
                    imgVideo.setAdapter(videoAdapter);
                    break;
            }
        }
    }

    public static void invoke(Context context) {
        Intent intent = new Intent(context, RentalActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void onClickAdd() {
        showImage();
    }

    @Override
    public void onClickDel(int position) {
        imgPathList.remove(position);
    }

    @Override
    public void requestRental(Response<NoDataBean> response) {
        if (TextUtils.equals(response.body().getCode(), "201")) {
            startActivity(new Intent(getActivity(), LoginActivity.class));
            MyApplication.logOut();
            return;
        }

        String isbathRoom;
        if (isBathroom) {
            isbathRoom = "0";
        } else {
            isbathRoom = "1";
        }


        RentalDetailsBean bean = new RentalDetailsBean(getString(R.string.sell_details),
                "0",
                null,
                etCall.getText().toString(),
                etContact.getText().toString(),
                etLocation.getText().toString(),
                etDistance.getText().toString(),
                etFloor.getText().toString(),
                etArea.getText().toString(),
                etPattern.getText().toString(),
                isbathRoom,
                etToward.getText().toString(),
                etEquipment.getText().toString(),
                imgPathList,
                videoPath,
                null);

        Intent intent = new Intent(getActivity(), RentalDetailsActivity.class);
        intent.putExtra("detailsBean", bean);
        startActivity(intent);

        getActivity().finish();
    }

    @Override
    public void upFileRequest(Response<FileBean> response) {
        if (response != null && response.body() != null && response.body().getDatas() != null) {
            if (isHaveVideo) {
                List<String> paths = getList(response.body().getDatas());

                String videoPath = "";

                StringBuilder builder = new StringBuilder();

                for (int i = 0; i < paths.size(); i++) {
                    if (i == paths.size() - 1) {
                        videoPath = paths.get(i);
                    } else if (i == paths.size() - 2) {
                        builder.append(paths.get(i));
                    } else {
                        builder.append(paths.get(i) + ",");
                    }
                }

                presenter.requestRental(etCall.getText().toString(),
                        etContact.getText().toString(),
                        etLocation.getText().toString(),
                        etDistance.getText().toString(),
                        etFloor.getText().toString(),
                        etArea.getText().toString(),
                        etPattern.getText().toString(),
                        isBathroom ? "0" : "1",
                        etToward.getText().toString(),
                        etEquipment.getText().toString(),
                        "1",
                        builder.toString(),
                        videoPath);
            } else {
                presenter.requestRental(etCall.getText().toString(),
                        etContact.getText().toString(),
                        etLocation.getText().toString(),
                        etDistance.getText().toString(),
                        etFloor.getText().toString(),
                        etArea.getText().toString(),
                        etPattern.getText().toString(),
                        isBathroom ? "0" : "1",
                        etToward.getText().toString(),
                        etEquipment.getText().toString(),
                        "1",
                        response.body().getDatas(),
                        "");
            }
        }
    }

    private List<String> getList(String pic) {
        String d[] = pic.split(",");
        List<String> picList = new ArrayList();

        for (int i = 0; i < d.length; i++) {
            picList.add(d[i]);
        }
        return picList;
    }
}
