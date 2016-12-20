package com.example.chy.challenge.login.register.personal_pop;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chy.challenge.NetInfo.UserRequest;
import com.example.chy.challenge.R;
import com.example.chy.challenge.Utils.NetBaseUtils;
import com.example.chy.challenge.WheelView.OnWheelChangedListener;
import com.example.chy.challenge.WheelView.WheelView;
import com.example.chy.challenge.WheelView.adapters.ArrayWheelAdapter;
import com.example.chy.challenge.login.register.adapter.Search_dictionary_adapter;
import com.example.chy.challenge.login.register.bean.Search_dictionary;
import com.example.chy.challenge.login.register.commany_info.Register_Mine_intention;
import com.example.chy.challenge.login.register.commany_info.Write_personal_jobnews;
import com.example.chy.challenge.model.CityModel;
import com.example.chy.challenge.model.DistrictModel;
import com.example.chy.challenge.model.ProvinceModel;
import com.example.chy.challenge.service.XmlParserHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * Created by Administrator on 2016/7/21.
 */
public class Pop_mine_joblocation implements PopupWindow.OnDismissListener, View.OnClickListener, OnWheelChangedListener {

    private static final int GETEDUCATION = 1;
    private Register_Mine_intention mactivity;
    private PopupWindow popupWindow;
    private TextView mine_pop_cancle, mine_pop_sure;
    private WheelView id_province, id_city,id_centeryear;
    private String et_content;
    private View rootView;
    private TextView tv_job_location,tv_want_salary,pop_title;
    private List<String> yearlist = new ArrayList<>();
    private String pagetype;

    //所有省
	protected static String[] mProvinceDatas;
    //key - 省 value - 市
	protected Map<String, String[]> mCitisDatasMap = new HashMap<String, String[]>();
    //key - 市 values - 区
	protected Map<String, String[]> mDistrictDatasMap = new HashMap<String, String[]>();
    //key - 区 values - 邮编
    protected Map<String, String> mZipcodeDatasMap = new HashMap<String, String>();
    //当前省的名称
	protected String mCurrentProviceName;
    //当前市的名称
	protected String mCurrentCityName;
    //当前区的名称
	protected String mCurrentDistrictName;
    //当前区的邮政编码
    protected String mCurrentZipCode;
    private String[] salary;


    private Handler handler = new Handler(Looper.myLooper()){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case GETEDUCATION:
                    if (msg.obj != null){
                        String result = (String) msg.obj;
                        try {
                            JSONObject json = new JSONObject(result);
                            if ("success".equals(json.optString("status"))){
                                JSONArray jsonarray = json.getJSONArray("data");
                                if (jsonarray != null&&jsonarray.length() > 0){
                                    salary = new String[jsonarray.length()];
                                    for (int i = 0;i < jsonarray.length();i++){
                                        JSONObject jsonobj = jsonarray.getJSONObject(i);
                                        salary[i] = jsonobj.getString("name");
                                    }
                                }
                                id_province.setViewAdapter(new ArrayWheelAdapter<String>(mactivity, salary));
                                id_city.setViewAdapter(new ArrayWheelAdapter<String>(mactivity, salary));
                                id_province.setVisibleItems(3);
                                id_city.setVisibleItems(3);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }else{
                        Toast.makeText(mactivity, R.string.net_error, Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        }
    };



    public Pop_mine_joblocation(Register_Mine_intention mActivity) {
        this.mactivity = mActivity;

        View view = LayoutInflater.from(mActivity).inflate(R.layout.mine_location_popupwindow, null);
        pop_title = (TextView) view.findViewById(R.id.pop_title);
        mine_pop_cancle = (TextView) view.findViewById(R.id.mine_pop_cancle);
        mine_pop_cancle.setOnClickListener(this);
        mine_pop_sure = (TextView) view.findViewById(R.id.mine_pop_sure);
        mine_pop_sure.setOnClickListener(this);
        id_province = (WheelView) view.findViewById(R.id.id_leftyear);

        id_city = (WheelView) view.findViewById(R.id.id_rightyear);

        id_centeryear = (WheelView) view.findViewById(R.id.id_centeryear);
        //获取activity根视图,rootView设为全局变量
        rootView = mactivity.getWindow().getDecorView();
        tv_job_location = (TextView) rootView.findViewById(R.id.intention_tv_job_location);//tv工作地点
        tv_want_salary = (TextView) rootView.findViewById(R.id.intention_tv_want_salary);//tv期望薪资

//        initProvinceDatas();
        popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        //在PopupWindow里面就加上下面代码，让键盘弹出时，不会挡住pop窗口。
        popupWindow.setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED);
        popupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        //设置popwindow的动画效果
        popupWindow.setAnimationStyle(R.style.popWindow_anim_style);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popupWindow.setOnDismissListener(this);// 当popWindow消失时的监听

    }
    private void setUpData() {
        initProvinceDatas();
        id_province.setViewAdapter(new ArrayWheelAdapter<String>(mactivity, mProvinceDatas));
        // 设置可见条目数量
        id_province.setVisibleItems(3);
        id_city.setVisibleItems(3);
        updateCities();
        updateAreas();
        updatedist();
    }
    private void updateCities() {
        int pCurrent = id_province.getCurrentItem();
        mCurrentProviceName = mProvinceDatas[pCurrent];
        String[] cities = mCitisDatasMap.get(mCurrentProviceName);
        if (cities == null) {
            cities = new String[]{""};
        }
        id_city.setViewAdapter(new ArrayWheelAdapter<String>(mactivity, cities));
        id_city.setCurrentItem(0);
        updateAreas();
    }
    private void updateAreas() {
        int pCurrent = id_city.getCurrentItem();
        mCurrentCityName = mCitisDatasMap.get(mCurrentProviceName)[pCurrent];
        String[] areas = mDistrictDatasMap.get(mCurrentCityName);

        if (areas == null) {
            areas = new String[]{""};
        }
        id_centeryear.setViewAdapter(new ArrayWheelAdapter<String>(mactivity, areas));
        id_centeryear.setCurrentItem(0);
        updatedist();
    }
    private void updatedist() {
        int pCurrent = id_centeryear.getCurrentItem();
        mCurrentDistrictName = mDistrictDatasMap.get(mCurrentCityName)[pCurrent];
        mCurrentZipCode = mZipcodeDatasMap.get(mCurrentDistrictName);
    }




    private void initProvinceDatas() {
        List<ProvinceModel> provinceList = null;
        AssetManager asset = mactivity.getAssets();
        try {
            InputStream input = asset.open("province_data.xml");
            // 创建一个解析xml的工厂对象
            SAXParserFactory spf = SAXParserFactory.newInstance();
            // 解析xml
            SAXParser parser = spf.newSAXParser();
            XmlParserHandler handler = new XmlParserHandler();
            parser.parse(input, handler);
            input.close();
            // 获取解析出来的数据
            provinceList = handler.getDataList();
            //*/ 初始化默认选中的省、市、区
            if (provinceList != null && !provinceList.isEmpty()) {
                mCurrentProviceName = provinceList.get(0).getName();
                List<CityModel> cityList = provinceList.get(0).getCityList();
                if (cityList != null && !cityList.isEmpty()) {
                    mCurrentCityName = cityList.get(0).getName();
                    List<DistrictModel> districtList = cityList.get(0).getDistrictList();
                    mCurrentDistrictName = districtList.get(0).getName();
//                    mCurrentZipCode = districtList.get(0).getZipcode();
                }
            }
            //*/
            mProvinceDatas = new String[provinceList.size()];
            for (int i = 0; i < provinceList.size(); i++) {
                // 遍历所有省的数据
                mProvinceDatas[i] = provinceList.get(i).getName();
                List<CityModel> cityList = provinceList.get(i).getCityList();
                String[] cityNames = new String[cityList.size()];
                for (int j = 0; j < cityList.size(); j++) {
                    // 遍历省下面的所有市的数据
                    cityNames[j] = cityList.get(j).getName();
                    List<DistrictModel> districtList = cityList.get(j).getDistrictList();
                    String[] distrinctNameArray = new String[districtList.size()];
                    DistrictModel[] distrinctArray = new DistrictModel[districtList.size()];
                    for (int k = 0; k < districtList.size(); k++) {
                        // 遍历市下面所有区/县的数据
                        DistrictModel districtModel = new DistrictModel(districtList.get(k).getName(), districtList.get(k).getZipcode());
                        // 区/县对于的邮编，保存到mZipcodeDatasMap
                        mZipcodeDatasMap.put(districtList.get(k).getName(), districtList.get(k).getZipcode());
                        distrinctArray[k] = districtModel;
                        distrinctNameArray[k] = districtModel.getName();
                    }
//                     市-区/县的数据，保存到mDistrictDatasMap
                    mDistrictDatasMap.put(cityNames[j], distrinctNameArray);
                }
                // 省-市的数据，保存到mCitisDatasMap
                mCitisDatasMap.put(provinceList.get(i).getName(), cityNames);
            }
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {

        }
//        return new String[0];
    }

    @Override
    public void onDismiss() {
        backgroundAlpha(1f);
    }

    /**
     * 弹窗显示的位置
     */
    public void showAsDropDown(View parent,String pagetype) {
        this.pagetype = pagetype;
        if ("location".equals(pagetype)){
            setUpData();
            pop_title.setText("工作地点");
            id_centeryear.setVisibility(View.GONE);
            id_province.addChangingListener(this);
            id_city.addChangingListener(this);
        }else if ("salary".equals(pagetype)){
            pop_title.setText("薪酬范围");
            id_centeryear.setVisibility(View.GONE);
            if (NetBaseUtils.isConnnected(mactivity)) {
                new UserRequest(mactivity, handler).GETDICTIONARYLIST("89", GETEDUCATION);
            } else {
                Toast.makeText(mactivity, R.string.net_error, Toast.LENGTH_SHORT).show();
            }
        }
        backgroundAlpha(0.4f);
        int[] location = new int[2];
        parent.getLocationOnScreen(location);
        WindowManager manager = (WindowManager) mactivity
                .getSystemService(Context.WINDOW_SERVICE);
        Display display = manager.getDefaultDisplay();
        int screenHeight = display.getHeight();
        popupWindow.showAtLocation(parent, Gravity.BOTTOM, 0, screenHeight - location[1]);

        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.update();
    }

    /**
     * 消除弹窗
     */
    public void dissmiss() {
        backgroundAlpha(1f);
        popupWindow.dismiss();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mine_pop_cancle:
                dissmiss();
                break;
            case R.id.mine_pop_sure:
                if ("location".equals(pagetype)){
                    et_content = mCurrentProviceName.substring(0,3) +"-"+ mCurrentCityName.substring(0,3);
                    tv_job_location.setText(et_content);
                    dissmiss();
                }else if ("salary".equals(pagetype)){
                    int leftyear = id_province.getCurrentItem();
                    int rightyear = id_city.getCurrentItem();
                    if (leftyear<rightyear){
                        tv_want_salary.setText(salary[leftyear]+"-"+salary[rightyear]);
                        dissmiss();
                    }else{
                        Toast.makeText(mactivity, "请选择正确的期望薪资", Toast.LENGTH_SHORT).show();
                    }
                }

                break;
        }
    }

    public String getString() {
        return et_content;
    }
    /**
     * 设置添加屏幕的背景透明度
     * @param bgAlpha
     */
    public void backgroundAlpha(float bgAlpha)
    {
        WindowManager.LayoutParams lp = mactivity.getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        mactivity.getWindow().setAttributes(lp);
    }


    @Override
    public void onChanged(WheelView wheel, int oldValue, int newValue) {
        if (wheel == id_province) {
            updateCities();
        } else if (wheel == id_city) {
            updateAreas();
        }
        else if (wheel == id_centeryear) {
            mCurrentDistrictName = mDistrictDatasMap.get(mCurrentCityName)[newValue];
            mCurrentZipCode = mZipcodeDatasMap.get(mCurrentDistrictName);
        }
    }
}
