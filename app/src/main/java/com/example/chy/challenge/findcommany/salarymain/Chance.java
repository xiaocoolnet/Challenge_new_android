package com.example.chy.challenge.findcommany.salarymain;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.Poi;
import com.example.chy.challenge.Adepter.Detail_Adepter;
import com.example.chy.challenge.Adepter.RecruitmentInfo;
import com.example.chy.challenge.Findpersoanl.Choose_Searching;
import com.example.chy.challenge.Findpersoanl.talentmain.talent.FindPersonal;
import com.example.chy.challenge.NetInfo.LocationService;
import com.example.chy.challenge.R;
import com.example.chy.challenge.Srearch;
import com.example.chy.challenge.Utils.LogUtils;
import com.example.chy.challenge.button.WaveView;
import com.example.chy.challenge.findcommany.Work_Searching;
import com.example.chy.challenge.findcommany.chance.Find_Job;
import com.example.chy.challenge.findcommany.chance.Find_work;
import com.example.chy.challenge.findcommany.chance.Search_company;
import com.example.chy.challenge.pnlllist.PullToRefreshBase;
import com.example.chy.challenge.pnlllist.PullToRefreshListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by 77588 on 2016/9/1.
 */
public class Chance extends Fragment implements View.OnClickListener {
    private View view;
    private Context mContext;
    private WaveView location_city,salary_retrieva,salary_search;
    private TextView tv_now_location;
    private Button btnfindWork,btnfindBoss;
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    private Bundle bundle;
    private Intent intent;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_chance, container, false);
        mContext = getActivity();
        initview();

        btnfindWork.setBackgroundResource(R.drawable.border_chance_left);
        btnfindWork.setTextColor(getResources().getColor(R.color.green));
        fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        Find_work findwork = new Find_work();
        bundle = new Bundle();
        bundle.putString("pagename", "找工作");
        findwork.setArguments(bundle);
        transaction.replace(R.id.salary_fragment_layout, findwork);
        transaction.commitAllowingStateLoss();
        return view;
    }

    private void initview() {
        location_city = (WaveView) view.findViewById(R.id.talent_location_city);//定位当前
        location_city.setOnClickListener(this);
        tv_now_location = (TextView) view.findViewById(R.id.talent_now_location);//tv当前地理位置

        salary_retrieva = (WaveView) view.findViewById(R.id.salary_retrieva);//分类
        salary_retrieva.setOnClickListener(this);
        salary_search = (WaveView) view.findViewById(R.id.salary_search);//搜索
        salary_search.setOnClickListener(this);

        btnfindWork = (Button) view.findViewById(R.id.btnfindWork);//找工作
        btnfindWork.setOnClickListener(this);
        btnfindBoss = (Button) view.findViewById(R.id.btnfindBoss);//找雇主
        btnfindBoss.setOnClickListener(this);

    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.talent_location_city://定位当前

                break;
            case R.id.btnfindWork://找工作
                salary_retrieva.setVisibility(View.VISIBLE);
                salary_retrieva.setOnClickListener(this);
                btnfindWork.setBackgroundResource(R.drawable.border_chance_left);
                btnfindWork.setTextColor(getResources().getColor(R.color.green));
                btnfindBoss.setBackgroundResource(R.drawable.border_chance_right);
                btnfindBoss.setTextColor(getResources().getColor(R.color.white));
                fragmentManager = getFragmentManager();
                transaction = fragmentManager.beginTransaction();
                Find_work findwork = new Find_work();
                bundle = new Bundle();
                bundle.putString("pagename", "找工作");
                findwork.setArguments(bundle);
                transaction.replace(R.id.salary_fragment_layout, findwork);
                transaction.commitAllowingStateLoss();
                break;
            case R.id.btnfindBoss://找雇主
                salary_retrieva.setVisibility(View.GONE);
                btnfindBoss.setBackgroundResource(R.drawable.border_white_right);
                btnfindBoss.setTextColor(getResources().getColor(R.color.green));
                btnfindWork.setBackgroundResource(R.drawable.border_chancegreen_left);
                btnfindWork.setTextColor(getResources().getColor(R.color.white));
                fragmentManager = getFragmentManager();
                transaction = fragmentManager.beginTransaction();
                Find_Job findJob = new Find_Job();
                bundle = new Bundle();
                bundle.putString("pagename", "找雇主");
                findJob.setArguments(bundle);
                transaction.replace(R.id.salary_fragment_layout, findJob);
                transaction.commitAllowingStateLoss();
                break;
            case R.id.salary_retrieva://分类
                    intent = new Intent(mContext, Work_Searching.class);
                    startActivity(intent);
                break;
            case R.id.salary_search://搜索
                intent = new Intent(mContext, Search_company.class);
                startActivity(intent);
                break;

        }
    }
    public void getIntentView1() {
        fragmentManager = getFragmentManager();
        transaction = fragmentManager.beginTransaction();
        Find_work findwork = new Find_work();
        bundle = new Bundle();
        bundle.putString("pagename", "最新");
        findwork.setArguments(bundle);
        transaction.replace(R.id.salary_fragment_layout, findwork);
        transaction.commitAllowingStateLoss();
    }
    public void getIntentView2() {
        fragmentManager = getFragmentManager();
        transaction = fragmentManager.beginTransaction();
        Find_work findwork = new Find_work();
        bundle = new Bundle();
        bundle.putString("pagename", "最热");
        findwork.setArguments(bundle);
        transaction.replace(R.id.salary_fragment_layout, findwork);
        transaction.commitAllowingStateLoss();
    }
    public void getIntentView3() {
        fragmentManager = getFragmentManager();
        transaction = fragmentManager.beginTransaction();
        Find_work findwork = new Find_work();
        bundle = new Bundle();
        bundle.putString("pagename", "最近");
        findwork.setArguments(bundle);
        transaction.replace(R.id.salary_fragment_layout, findwork);
        transaction.commitAllowingStateLoss();
    }
    public void getIntentView4() {
        fragmentManager = getFragmentManager();
        transaction = fragmentManager.beginTransaction();
        Find_work findwork = new Find_work();
        bundle = new Bundle();
        bundle.putString("pagename", "评价");
        findwork.setArguments(bundle);
        transaction.replace(R.id.salary_fragment_layout, findwork);
        transaction.commitAllowingStateLoss();
    }
    public void getIntentView5() {
        fragmentManager = getFragmentManager();
        transaction = fragmentManager.beginTransaction();
        Find_work findwork = new Find_work();
        bundle = new Bundle();
        bundle.putString("pagename", "薪资");
        findwork.setArguments(bundle);
        transaction.replace(R.id.salary_fragment_layout, findwork);
        transaction.commitAllowingStateLoss();
    }
    public void getIntentView6() {
        fragmentManager = getFragmentManager();
        transaction = fragmentManager.beginTransaction();
        Find_work findwork = new Find_work();
        bundle = new Bundle();
        bundle.putString("pagename", "职位红包");
        findwork.setArguments(bundle);
        transaction.replace(R.id.salary_fragment_layout, findwork);
        transaction.commitAllowingStateLoss();
    }
    public void getIntentView7() {
        fragmentManager = getFragmentManager();
        transaction = fragmentManager.beginTransaction();
        Find_work findwork = new Find_work();
        bundle = new Bundle();
        bundle.putString("pagename", "面试红包");
        findwork.setArguments(bundle);
        transaction.replace(R.id.salary_fragment_layout, findwork);
        transaction.commitAllowingStateLoss();
    }
    public void getIntentView8() {
        fragmentManager = getFragmentManager();
        transaction = fragmentManager.beginTransaction();
        Find_work findwork = new Find_work();
        bundle = new Bundle();
        bundle.putString("pagename", "就职红包");
        findwork.setArguments(bundle);
        transaction.replace(R.id.salary_fragment_layout, findwork);
        transaction.commitAllowingStateLoss();
    }
//
//    @SuppressLint("HandlerLeak")
//    private Handler handler = new Handler() {
//        public void handleMessage(Message msg){
//            switch (msg.what){
//                case KEY:
//                    String result = msg.obj.toString();
//                    try {
//                        JSONObject demojson = new JSONObject(result);
//                        JSONArray jsonlist = demojson.getJSONArray("data");
//                        for (int i=0; i<jsonlist.length(); i++){
//                            list.clear();
//                            list.add(new RecruitmentInfo(jsonlist.getJSONObject(i).getString("title"),
//                                    jsonlist.getJSONObject(i).getString("company_name"),
//                                    jsonlist.getJSONObject(i).getString("realname"),
//                                    jsonlist.getJSONObject(i).getString("myjob"),
//                                    jsonlist.getJSONObject(i).getString("count"),
//                                    jsonlist.getJSONObject(i).getString("company_score"),
//                                    jsonlist.getJSONObject(i).getString("salary"),
//                                    jsonlist.getJSONObject(i).getString("distance"),
//                                    jsonlist.getJSONObject(i).getString("address"),
//                                    jsonlist.getJSONObject(i).getString("experience"),
//                                    jsonlist.getJSONObject(i).getString("education"),
//                                    jsonlist.getJSONObject(i).getString("work_property")
//                                    ));
//                        }
//                        LogUtils.e("Tip",list.get(0).toString());
//                        detail_adepter = new Detail_Adepter(mContext,R.layout.detail_adepter, list);
//                        lv_view.setAdapter(detail_adepter);
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                    break;
//            }
//        }
//    };
//
//    public void onrefrsh(){
//        // -----------location config ------------
//        locationService = ((com.example.chy.challenge.Utils.application)getActivity().getApplication()).locationService;
//        //获取locationservice实例，建议应用中只初始化1个location实例，然后使用，可以参考其他示例的activity，都是通过此种方式获取locationservice实例的
//        locationService.registerListener(mListener);
//        //注册监听
//        int type = getActivity().getIntent().getIntExtra("from", 0);
//        if (type == 0) {
//            locationService.setLocationOption(locationService.getDefaultLocationClientOption());
//        } else if (type == 1) {
//            locationService.setLocationOption(locationService.getOption());
//        }
//        locationService.start();// 定位SDK
//        // start之后会默认发起一次定位请求，开发者无须判断isstart并主动调用request
//    }
//
//
//    private BDLocationListener mListener = new BDLocationListener() {
//
//        @Override
//        public void onReceiveLocation(BDLocation location) {
//            // TODO Auto-generated method stub
//            if (null != location && location.getLocType() != BDLocation.TypeServerError) {
//                StringBuffer sb = new StringBuffer(256);
//                sb.append("time : ");
//                /**
//                 * 时间也可以使用systemClock.elapsedRealtime()方法 获取的是自从开机以来，每次回调的时间；
//                 * location.getTime() 是指服务端出本次结果的时间，如果位置不发生变化，则时间不变
//                 */
//                sb.append(location.getTime());
//                sb.append("\nerror code : ");
//                sb.append(location.getLocType());
//                sb.append("\nlatitude : ");
//                sb.append(location.getLatitude());
//                sb.append("\nlontitude : ");
//                sb.append(location.getLongitude());
//                sb.append("\nradius : ");
//                sb.append(location.getRadius());
//                sb.append("\nCountryCode : ");
//                sb.append(location.getCountryCode());
//                sb.append("\nCountry : ");
//                sb.append(location.getCountry());
//                sb.append("\ncitycode : ");
//                sb.append(location.getCityCode());
//                sb.append("\ncity : ");
//                sb.append(location.getCity());
//                sb.append("\nDistrict : ");
//                sb.append(location.getDistrict());
//                sb.append("\nStreet : ");
//                sb.append(location.getStreet());
//                sb.append("\naddr : ");
//                sb.append(location.getAddrStr());
//                sb.append("\nDescribe: ");
//                sb.append(location.getLocationDescribe());
//                sb.append("\nDirection(not all devices have value): ");
//                sb.append(location.getDirection());
//                sb.append("\nPoi: ");
//                if (location.getPoiList() != null && !location.getPoiList().isEmpty()) {
//                    for (int i = 0; i < location.getPoiList().size(); i++) {
//                        Poi poi = (Poi) location.getPoiList().get(i);
//                        sb.append(poi.getName() + ";");
//                    }
//                }
//                if (location.getLocType() == BDLocation.TypeGpsLocation) {// GPS定位结果
//                    sb.append("\nspeed : ");
//                    sb.append(location.getSpeed());// 单位：km/h
//                    sb.append("\nsatellite : ");
//                    sb.append(location.getSatelliteNumber());
//                    sb.append("\nheight : ");
//                    sb.append(location.getAltitude());// 单位：米
//                    sb.append("\ndescribe : ");
//                    sb.append("gps定位成功");
//                } else if (location.getLocType() == BDLocation.TypeNetWorkLocation) {// 网络定位结果
//                    // 运营商信息
//                    sb.append("\noperationers : ");
//                    sb.append(location.getOperators());
//                    sb.append("\ndescribe : ");
//                    sb.append("网络定位成功");
//                } else if (location.getLocType() == BDLocation.TypeOffLineLocation) {// 离线定位结果
//                    sb.append("\ndescribe : ");
//                    sb.append("离线定位成功，离线定位结果也是有效的");
//                } else if (location.getLocType() == BDLocation.TypeServerError) {
//                    sb.append("\ndescribe : ");
//                    sb.append("服务端网络定位失败，可以反馈IMEI号和大体定位时间到loc-bugs@baidu.com，会有人追查原因");
//                } else if (location.getLocType() == BDLocation.TypeNetWorkException) {
//                    sb.append("\ndescribe : ");
//                    sb.append("网络不同导致定位失败，请检查网络是否通畅");
//                } else if (location.getLocType() == BDLocation.TypeCriteriaException) {
//                    sb.append("\ndescribe : ");
//                    sb.append("无法获取有效定位依据导致定位失败，一般是由于手机的原因，处于飞行模式下一般会造成这种结果，可以试着重启手机");
//                }
//                Log.e("sb=", sb.toString());
//                tv_chance_local.setText(location.getCity());
//                locationService.stop();
//            }
//        }
//    };


}
