package com.example.chy.challenge;

import android.app.Application;
import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

/**
 * Created by Administrator on 2016/6/22.
 */
public class AppApplication extends Application {
    public static DisplayImageOptions options;
    private static final String TAG = "AliyunApplication";
    private Context applicationContext;
    private static RequestQueue requestQueue;
    private static RequestQueue requestQueueFile;
    private static AppApplication myApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        applicationContext = this;
        myApplication = new AppApplication();
        //请求队列
        requestQueue = Volley.newRequestQueue(this);
        requestQueueFile = Volley.newRequestQueue(this);
        initImageLoader();
    }

    private void initImageLoader() {
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext())
                .memoryCacheSizePercentage(30)
                .build();
        ImageLoader.getInstance().init(config);

        options = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .showImageForEmptyUri(R.mipmap.ic_wode)
                .showImageOnFail(R.mipmap.ic_wode)
                .build();
    }

    /**
     * 拿到消息队列
     */
    public static RequestQueue getRequestQueue() {
        return requestQueue;
    }

    /**
     * 拿到消息队列
     */
    public static RequestQueue getFileRequestQueue() {
        return requestQueueFile;
    }

    /**
     * 拿到消息队列
     */
    public static AppApplication getApplication() {
        return myApplication;
    }

}