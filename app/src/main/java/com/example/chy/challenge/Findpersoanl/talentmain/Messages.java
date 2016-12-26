package com.example.chy.challenge.Findpersoanl.talentmain;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.chy.challenge.Adepter.MsgAdapter;
import com.example.chy.challenge.Adepter.MsgBeans;
import com.example.chy.challenge.NetInfo.UserRequest;
import com.example.chy.challenge.R;
import com.example.chy.challenge.Utils.NoScrollListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 77588 on 2016/9/1.
 */
public class Messages extends Fragment implements View.OnClickListener{
    private Context context;
    private MsgAdapter msgAdapter;
    private String uid;
    private NoScrollListView lv_view;

    private List<MsgBeans.DataBean> msgBeans = new ArrayList<>();
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    String result = (String) msg.obj;
                    JSONObject jsonObject = null;

                    try {
                        jsonObject = new JSONObject(result);
                        String status = jsonObject.getString("status");
                        if (status.equals("success")) {
                            JSONArray jsonArray = jsonObject.getJSONArray("data");
                            int length = jsonArray.length();
                            JSONObject dataObject;
                            for (int i = 0; i < length; i++) {
                                dataObject = (JSONObject) jsonArray.get(i);
                                MsgBeans.DataBean msgBean= new MsgBeans.DataBean();
                                msgBean.setId(dataObject.getString("id"));
                                msgBean.setUid(dataObject.getString("uid"));
                                uid=dataObject.getString("uid");
                                msgBean.setChat_uid(dataObject.getString("chat_uid"));
                                msgBean.setCreate_time(dataObject.getString("create_time"));
                                msgBean.setLast_chat_id(dataObject.getString("last_chat_id"));
                                msgBean.setMy_face(dataObject.getString("my_face"));
                                msgBean.setOther_face(dataObject.getString("other_face"));
                                msgBean.setOther_nickname(dataObject.getString("other_nickname"));
                                msgBeans.add(msgBean);
                            }
                        }
                        msgAdapter = new MsgAdapter(context,R.layout.fragment_msg_item,msgBeans);
                        lv_view.setAdapter(msgAdapter);
                    }catch (JSONException e) {
                        e.printStackTrace();
                    }
                    break;
            }
        }
    };

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_message, container, false);
        context = getActivity();
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        new UserRequest(context,handler).GetChatListData("301",1);
    }

    private void initView(){
        lv_view = (NoScrollListView) getView().findViewById(R.id.lv_msg);
    }

    @Override
    public void onClick(View view) {

    }

}
