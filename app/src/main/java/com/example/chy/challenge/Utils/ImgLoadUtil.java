package com.example.chy.challenge.Utils;

import android.graphics.Bitmap;
import android.widget.ImageView;

import com.example.chy.challenge.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;




/**
 * Created by Administrator on 2016/3/14.
 */
public class ImgLoadUtil {

    private static DisplayImageOptions options =new DisplayImageOptions.Builder()
            .showImageOnLoading(R.mipmap.ic_tiaozhangaoxin)
            .showImageOnFail(R.mipmap.ic_tiaozhangaoxin)
            .showImageForEmptyUri(R.mipmap.ic_tiaozhangaoxin)
            .cacheInMemory(true)
            .cacheOnDisc(true)
            .bitmapConfig(Bitmap.Config.RGB_565)
            .resetViewBeforeLoading(true)
            .imageScaleType(ImageScaleType.EXACTLY_STRETCHED)
            .displayer(new FadeInBitmapDisplayer(200))
            .build();

    private static DisplayImageOptions headerOptions = new DisplayImageOptions.Builder()
            .showImageOnLoading(R.mipmap.ic_tiaozhangaoxin)
            .showImageOnFail(R.mipmap.ic_tiaozhangaoxin)
            .showImageForEmptyUri(R.mipmap.ic_tiaozhangaoxin)
            .cacheInMemory(true)
            .cacheOnDisc(true)
            .bitmapConfig(Bitmap.Config.RGB_565)
            .resetViewBeforeLoading(true)
            .imageScaleType(ImageScaleType.EXACTLY_STRETCHED)
            .displayer(new FadeInBitmapDisplayer(200))
            .build();

    public static void display(String uri,ImageView imageView){
        ImageLoader.getInstance().displayImage(uri, imageView, options);
    }
    public static void displayHeader(String uri,ImageView imageView){
        ImageLoader.getInstance().displayImage(uri, imageView, headerOptions);
    }

//    public static void displayUserHeader(ImageView imageView){
//        String uri = MyApplication.getInstance().getUser().getImg();
//        ImageLoader.getInstance().displayImage(uri, imageView, headerOptions);
//    }

    /**下载gif*/
    public static Bitmap disPlayGif(String url){
        return  ImageLoader.getInstance().loadImageSync(url);
    }
}
