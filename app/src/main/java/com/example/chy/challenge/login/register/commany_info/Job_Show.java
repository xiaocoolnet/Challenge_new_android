package com.example.chy.challenge.login.register.commany_info;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.Gallery;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chy.challenge.NetInfo.UserRequest;
import com.example.chy.challenge.R;
import com.example.chy.challenge.Utils.NetBaseUtils;
import com.example.chy.challenge.WheelView.adapters.ArrayWheelAdapter;
import com.example.chy.challenge.button.CustomDialog;
import com.example.chy.challenge.button.Public_static_all;
import com.example.chy.challenge.button.WaveView;
import com.example.chy.challenge.login.register.adapter.GridView_Adapter;
import com.example.chy.challenge.login.register.register_bean.UserInfo;
import com.example.chy.challenge.login.register.register_bean.UserInfoBean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/29 0029.
 */

public class Job_Show extends Activity implements View.OnClickListener{
    private static final int GETEDUCATION = 1;
    private GridView jobshow_gridview;
    private Context mContext;
    private WaveView back,submit_job_experice;
    private UserInfo info;
    private GridView_Adapter gridviewadapter;
    private List<String> skilllist = new ArrayList<>();
    private CheckBox tvlist;
    private String[] gridviewitem;
    private List<String> gridviewlist = new ArrayList<>();
    private String griditem;
    private int num = 0;


    private Handler handler = new Handler(Looper.myLooper()){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case GETEDUCATION:
                    if (msg.obj != null){
                        String result = (String) msg.obj;
                        skilllist.clear();
                        try {
                            JSONObject json = new JSONObject(result);
                            if ("success".equals(json.optString("status"))){
                                JSONArray jsonarray = json.getJSONArray("data");
                                if (jsonarray != null&&jsonarray.length() > 0){
                                    gridviewitem = new String[jsonarray.length()];
                                    for (int i = 0;i < jsonarray.length();i++){
                                        JSONObject jsonobj = jsonarray.getJSONObject(i);
                                        skilllist.add(jsonobj.getString("name"));
                                        if (Public_static_all.isJobC&&Public_static_all.isJobc){//技能展示
                                            if (info.getSkills_show1().equals(jsonobj.getString("name"))){
                                                gridviewitem[i] = "1";
                                            }else if (info.getSkills_show2().equals(jsonobj.getString("name"))){
                                                gridviewitem[i] = "1";
                                            }else if (info.getSkills_show3().equals(jsonobj.getString("name"))){
                                                gridviewitem[i] = "1";
                                            }else{
                                                gridviewitem[i] = "0";
                                            }
                                        }else {
                                            gridviewitem[i] = "0";
                                        }
                                    }
                                    skilllist.add("");
                                }
                                gridviewadapter = new GridView_Adapter( skilllist,mContext,0,gridviewitem);
                                jobshow_gridview.setAdapter(gridviewadapter);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }else{
                        Toast.makeText(mContext, R.string.net_error, Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.job_show);
        mContext = this;
        info = new UserInfo(mContext);
        getview();

    }

    private void getview() {
        jobshow_gridview = (GridView) findViewById(R.id.jobshow_gridview);//列表
        back = (WaveView) findViewById(R.id.back);
        back.setOnClickListener(this);
        submit_job_experice = (WaveView) findViewById(R.id.submit_job_experice);//提交
        submit_job_experice.setOnClickListener(this);
        jobshow_gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (num < 3){
                    View v = parent.getChildAt(position);
                    if (v != null) {
                        tvlist = (CheckBox) v.findViewById(R.id.tv_list_item);
                    }
                    for(int i = 0;i<gridviewitem.length;i++) {
                        if (i == position){
                            if ("0".equals(gridviewitem[i])) {
                                tvlist.setChecked(true);
                                gridviewitem[i] = "1";
                                num+=1;
                                griditem = String.valueOf(position);
                            }else if ("1".equals(gridviewitem[i])){
                                tvlist.setChecked(false);
                                gridviewitem[i] = "0";
                                num-=1;
                            }
                        }
                    }
                    gridviewadapter.notifyDataSetChanged();
                }else {
                    if (griditem != null&&griditem.length() > 0){
                        View v = parent.getChildAt(position);
                        if (v != null) {
                            tvlist = (CheckBox) v.findViewById(R.id.tv_list_item);
                        }
                        for(int i = 0;i<gridviewitem.length;i++) {
                            if (i == position){
                                if (griditem.equals(String.valueOf(position))){
                                    tvlist.setChecked(false);
                                    gridviewitem[i] = "0";
                                    griditem = "";
                                    num-=1;
                                }else{
                                    if ("0".equals(gridviewitem[i])) {
                                        gridviewitem[Integer.valueOf(griditem)] = "0";
                                        tvlist.setChecked(true);
                                        gridviewitem[i] = "1";
                                        griditem = String.valueOf(i);
                                    }else if ("1".equals(gridviewitem[i])){
                                        tvlist.setChecked(false);
                                        gridviewitem[i] = "0";
                                        num-=1;
                                    }
                                }
                            }
                        }
                        gridviewadapter.notifyDataSetChanged();
                    }else{
                        View v = parent.getChildAt(position);
                        if (v != null) {
                            tvlist = (CheckBox) v.findViewById(R.id.tv_list_item);
                        }
                        for(int i = 0;i<gridviewitem.length;i++) {
                            if (i == position){
                                if ("0".equals(gridviewitem[i])) {
                                    tvlist.setChecked(true);
                                    gridviewitem[i] = "1";
                                    num+=1;
                                    griditem = String.valueOf(i);
                                }else if ("1".equals(gridviewitem[i])){
                                    tvlist.setChecked(false);
                                    gridviewitem[i] = "0";
                                    num-=1;
                                }
                            }
                        }
                        gridviewadapter.notifyDataSetChanged();
                    }
                }
            }
        });
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back:
//                if (gridviewitem != null&&gridviewitem.length > 0) {
//                    for (int i = 0;i<gridviewitem.length;i++) {
//                        if ("1".equals(gridviewitem[i])){
//                            if (info.getSkills_show1().equals(skilllist.get(i))&&info.getSkills_show2().equals(skilllist.get(i))&&info.getSkills_show3().equals(skilllist.get(i))){
//                                CustomDialog.Builder builder = new CustomDialog.Builder(this);
//                                builder.setMessage("您没有任何修改，确定退出？");
//                                builder.setTitle("!");
//                                builder.setPositiveButton("取消", new DialogInterface.OnClickListener() {
//                                    public void onClick(DialogInterface dialogone, int which) {
//                                        dialogone.dismiss();
//                                    }
//                                });
//                                builder.setNegativeButton("确定", new DialogInterface.OnClickListener() {
//                                            public void onClick(DialogInterface dialogone, int which) {
//                                               finish();
//                                            }
//                                        });
//                                builder.create().show();
//                            }else {
//                                CustomDialog.Builder builder = new CustomDialog.Builder(this);
//                                builder.setMessage("您有修改没保存，确定退出？");
//                                builder.setTitle("!");
//                                builder.setPositiveButton("取消", new DialogInterface.OnClickListener() {
//                                    public void onClick(DialogInterface dialogone, int which) {
//                                        dialogone.dismiss();
//                                    }
//                                });
//                                builder.setNegativeButton("确定", new DialogInterface.OnClickListener() {
//                                    public void onClick(DialogInterface dialogone, int which) {
//                                        finish();
//                                    }
//                                });
//                                builder.create().show();
//                            }
//                        }
//                    }
//                }else {
                    finish();
//                }
                break;
            case R.id.submit_job_experice:
//                info.setSkills_show();
                gridviewlist.clear();
                if (gridviewitem != null&&gridviewitem.length > 0) {
                    for (int i = 0;i<gridviewitem.length;i++) {
                        if ("1".equals(gridviewitem[i])){
                            gridviewlist.add(skilllist.get(i));
                        }
                    }
                    if (gridviewlist.size() == 3) {
                        info.setSkills_show1(gridviewlist.get(0) + "");
                        info.setSkills_show2(gridviewlist.get(1) + "");
                        info.setSkills_show3(gridviewlist.get(2) + "");
                        info.setSkills_show("3项技能");
                        Public_static_all.isJobc = true;
                        finish();
                    }else if (gridviewlist.size() == 2){
                        info.setSkills_show1(gridviewlist.get(0) + "");
                        info.setSkills_show2(gridviewlist.get(1) + "");
                        info.setSkills_show("2项技能");
                        Public_static_all.isJobc = true;
                        finish();
                    }else if (gridviewlist.size() == 1){
                        info.setSkills_show1(gridviewlist.get(0) + "");
                        info.setSkills_show(gridviewlist.get(0)+"");
                        Public_static_all.isJobc = true;
                        finish();
                    }else if (gridviewlist.size() == 0||gridviewlist == null){
                        Toast.makeText(mContext, "请选择技能", Toast.LENGTH_SHORT).show();
                    }

                }else {
                    Toast.makeText(mContext, "请选择技能", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        getresources();
    }

    private void getresources() {
        if (NetBaseUtils.isConnnected(mContext)) {
            new UserRequest(mContext, handler).GETDICTIONARYLIST("36", GETEDUCATION);
        } else {
            Toast.makeText(mContext, R.string.net_error, Toast.LENGTH_SHORT).show();
        }
    }
}
