package com.example.chy.challenge.NetInfo;

import android.content.Context;
import android.os.Message;
import android.widget.Toast;

import com.example.chy.challenge.Utils.LogUtils;
import com.example.chy.challenge.Utils.NetBaseUtils;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 77588 on 2016/9/20.
 */
public class GetCode {
    private String phone;
    String code = "false";
    public String GetCode(final String phone, final Context mContext) {
        this.phone = phone;
        new Thread() {
            Message msg = Message.obtain();
            public void run() {
                Message msg = new Message();
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("phone",phone));
                LogUtils.i("TuiSong", "device_token" + params.toString());
                String result = NetBaseUtils.getResponseForPost(UserNetConstant.NET_USER_CODE, params, mContext);
                if (result!=null){
                    try {
                        JSONObject jsonObject = new JSONObject(result);
                        code = jsonObject.optString("data");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    LogUtils.i("Tip","后台获取结果"+code);
                }
            };
        }.start();
        for (int i = 0;i<10;i++){
            if ("false".equals(code)) {
                LogUtils.i("Tip","等待数据次数"+i);
                new Thread() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(1000);
                            LogUtils.i("Tip","进程休眠");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }.start();
            }else {
                return code;
            }
            if (i==9){
                Toast.makeText(mContext,"等待超时",Toast.LENGTH_SHORT).show();
            }
        }
        return code;
    }

}
