package io.rong.imkit;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.AttributeSet;

import java.lang.reflect.Field;

import io.rong.imageloader.core.DisplayImageOptions;
import io.rong.imageloader.core.ImageLoader;
import io.rong.imageloader.core.assist.ImageSize;
import io.rong.imageloader.core.display.RoundedBitmapDisplayer;
import io.rong.imageloader.core.display.SimpleBitmapDisplayer;
import io.rong.imageloader.core.imageaware.ImageViewAware;
import io.rong.imageloader.core.listener.ImageLoadingListener;
import io.rong.imageloader.core.listener.ImageLoadingProgressListener;
import io.rong.imkit.widget.AsyncImageView;

public class CImageView extends AsyncImageView {
    public CImageView(Context context) {
        super(context);
    }

    public CImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void setAvatar(Uri imageUri) {
        if(imageUri != null) {
            ImageViewAware imageViewAware = new ImageViewAware(this);
            ImageSize imageSize = new ImageSize(80, 80);
            DisplayImageOptions options = createDisplayImageOptions(0, true, null);
            ImageLoader.getInstance().displayImage(imageUri.toString(), imageViewAware, options, imageSize, (ImageLoadingListener)null, (ImageLoadingProgressListener)null);
        }
    }

    @Override
    public void setAvatar(String imageUri, int defaultResId) {
        ImageViewAware imageViewAware = new ImageViewAware(this);
        ImageSize imageSize = new ImageSize(80, 80);
        DisplayImageOptions options = createDisplayImageOptions(defaultResId, true, null);
        ImageLoader.getInstance().displayImage(imageUri, imageViewAware, options, imageSize, (ImageLoadingListener)null, (ImageLoadingProgressListener)null);

    }

    private DisplayImageOptions createDisplayImageOptions(int defaultResId, boolean cacheInMemory, Object extraForDownloader) {
        DisplayImageOptions.Builder builder = new DisplayImageOptions.Builder();
        Drawable defaultDrawable = null;
        boolean isCircle = false;
        int cornerRadius = 0;
        try {
            Class supClass = getClass().getSuperclass();
            Field mDefaultDrawable = supClass.getDeclaredField("mDefaultDrawable");
            mDefaultDrawable.setAccessible(true);
            defaultDrawable = (Drawable) mDefaultDrawable.get(this);

            Field mIsCircle = supClass.getDeclaredField("isCircle");
            mIsCircle.setAccessible(true);
            isCircle = (boolean) mIsCircle.get(this);

            Field mCornerRadius = supClass.getDeclaredField("mCornerRadius");
            mCornerRadius.setAccessible(true);
            cornerRadius = (int) mCornerRadius.get(this);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        if (defaultResId > 0) {
            try {
                defaultDrawable = getContext().getResources().getDrawable(defaultResId);
            } catch (Resources.NotFoundException e) {
                e.printStackTrace();
            }
        }

        if (defaultDrawable != null) {
            builder.showImageOnLoading(defaultDrawable);
            builder.showImageForEmptyUri(defaultDrawable);
            builder.showImageOnFail(defaultDrawable);
        }
        if (extraForDownloader != null) {
            builder.extraForDownloader(extraForDownloader);
        }

        if (isCircle) {
            builder.displayer(new CircleBitmapDisplayer());
        } else if (cornerRadius > 0) {
            builder.displayer(new RoundedBitmapDisplayer(cornerRadius));
        } else {
            builder.displayer(new SimpleBitmapDisplayer());
        }

        DisplayImageOptions options = builder.resetViewBeforeLoading(false)
                .cacheInMemory(cacheInMemory)
                .cacheOnDisk(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();
        return options;
    }

}
