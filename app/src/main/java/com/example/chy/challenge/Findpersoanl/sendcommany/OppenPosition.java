package com.example.chy.challenge.Findpersoanl.sendcommany;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chy.challenge.Findpersoanl.talentmain.talentbean.FindPersonalBean;
import com.example.chy.challenge.NetInfo.UserRequest;
import com.example.chy.challenge.R;
import com.example.chy.challenge.Utils.NetBaseUtils;
import com.example.chy.challenge.button.RevealButton;
import com.example.chy.challenge.button.WaveView;
import com.example.chy.challenge.login.register.Write_personal_info;
import com.example.chy.challenge.login.register.register_bean.UserInfoBean;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by 77588 on 2016/9/3.
 */
public class OppenPosition extends Activity implements View.OnClickListener {
    private static final int GETRESUMELIST = 1;
    private WaveView back, ril_information, ril_positiontype, ril_jobtitle, ril_skillrequired, ril_salaryrange, ril_jobnature,
            ril_Experience, ril_education, ril_workcity, ril_workplace, ril_jobdescription,ril_welfare;
    private TextView tv_information, tv_positiontype, tv_jobtitle, tv_skillrequired, tv_salaryrange, tv_jobnature,
            tv_experience, tv_education, tv_workcity, tv_workplace, tv_jobdescription,tv_welfare;
    private RevealButton send_resume;
    private Intent intent;
    private Activity mactivity;
    private UserInfoBean infobean;
    private FindPersonalBean fpb;
    private FindPersonalBean.DataBean fpbd;
    private Handler handler = new Handler(Looper.myLooper()){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case GETRESUMELIST:
                    if (msg.obj!= null){
                        String result = (String) msg.obj;
                        try {
                            JSONObject json = new JSONObject(result);
                            if ("success".equals(json.optString("status"))){
//                                Gson gson = new Gson();
//                                fpb = gson.fromJson(result,FindPersonalBean.class);
                            }else{
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }else{
                        Toast.makeText(mactivity, R.string.net_error,Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_position);
        mactivity = this;
        infobean = new UserInfoBean(mactivity);
        initview();

    }

    private void initview() {
        back = (WaveView) findViewById(R.id.back);
        back.setOnClickListener(this);
        ril_information = (WaveView) findViewById(R.id.ril_oppen_commany_information);//公司信息
        ril_information.setOnClickListener(this);
        tv_information = (TextView) findViewById(R.id.oppen_commany_information);//公司名称
        ril_positiontype = (WaveView) findViewById(R.id.ril_oppen_position_type);//职位类型
        ril_positiontype.setOnClickListener(this);
        tv_positiontype = (TextView) findViewById(R.id.oppen_commany_position_type);//选择的职位
        ril_jobtitle = (WaveView) findViewById(R.id.ril_oppen_job_title);//职位名称
        ril_jobtitle.setOnClickListener(this);
        tv_jobtitle = (TextView) findViewById(R.id.oppen_commany_job_title);//自我编辑职位名称
        ril_skillrequired = (WaveView) findViewById(R.id.ril_oppen_skills_required);//技能要求
        ril_skillrequired.setOnClickListener(this);
        tv_skillrequired = (TextView) findViewById(R.id.oppen_commany_skills_required);//几个技能（1个直接显示2.3显示几个技能）
        ril_salaryrange = (WaveView) findViewById(R.id.ril_oppen_salary_range);//薪资范围
        ril_salaryrange.setOnClickListener(this);
        tv_salaryrange = (TextView) findViewById(R.id.oppen_commany_salary_range);
        ril_jobnature = (WaveView) findViewById(R.id.ril_oppen_job_nature);//工作性质
        ril_jobnature.setOnClickListener(this);
        tv_jobnature = (TextView) findViewById(R.id.oppen_commany_job_nature);
        ril_Experience = (WaveView) findViewById(R.id.ril_oppen_Experience_requirements);//经验要求
        ril_Experience.setOnClickListener(this);
        tv_experience = (TextView) findViewById(R.id.oppen_commany_Experience_requirements);
        ril_education = (WaveView) findViewById(R.id.ril_oppen_educational_requirements);//学历要求
        ril_education.setOnClickListener(this);
        tv_education = (TextView) findViewById(R.id.oppen_commany_educational_requirements);
        ril_workcity = (WaveView) findViewById(R.id.ril_oppen_work_city);//工作城市
        ril_workcity.setOnClickListener(this);
        tv_workcity = (TextView) findViewById(R.id.oppen_commany_work_city);
        ril_workplace = (WaveView) findViewById(R.id.ril_oppen_work_place);//工作地点
        ril_workplace.setOnClickListener(this);
        tv_workplace = (TextView) findViewById(R.id.oppen_commany_work_place);
        ril_jobdescription = (WaveView) findViewById(R.id.ril_oppen_job_description);//职位描述
        ril_jobdescription.setOnClickListener(this);
        tv_jobdescription = (TextView) findViewById(R.id.oppen_commany_job_description);
        ril_welfare = (WaveView) findViewById(R.id.ril_oppen_corporate_welfare);//公司福利
        ril_welfare.setOnClickListener(this);
        tv_welfare = (TextView) findViewById(R.id.oppen_commany_corporate_welfare);
        send_resume = (RevealButton) findViewById(R.id.oppen_commany_send_resume);//发布简历
        send_resume.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.ril_oppen_commany_information://公司信息
                fpbd = fpb.getData();
                intent = new Intent(mactivity,Write_commany_title.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("fpbdinfo",fpbd);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.ril_oppen_position_type://职位类型

                break;
            case R.id.ril_oppen_job_title://职位名称
                intent.setClass(mactivity, Write_personal_info.class);
                intent.putExtra("type","jobname");
                startActivity(intent);
                //设置切换动画，从左边进入，上面退出
                overridePendingTransition(R.anim.activity_int_left, R.anim.activity_out_top);
                break;
            case R.id.ril_oppen_skills_required://技能要求
                break;
            case R.id.ril_oppen_salary_range://薪资范围
                break;
            case R.id.ril_oppen_job_nature://工作性质
                break;
            case R.id.ril_oppen_Experience_requirements://经验要求
                break;
            case R.id.ril_oppen_educational_requirements://学历要求
                break;
            case R.id.ril_oppen_work_city://工作城市
                break;
            case R.id.ril_oppen_work_place://工作地点
                break;
            case R.id.ril_oppen_job_description://职位描述
                break;
            case R.id.ril_oppen_corporate_welfare://公司福利
                break;
            case R.id.oppen_commany_send_resume://发布简历

                break;
            default:
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        getcommanyntitle();
    }

    private void getcommanyntitle() {
        if (NetBaseUtils.isConnnected(mactivity)) {
            new UserRequest(mactivity, handler).GETMYCOMMANY(infobean.getUserid(),GETRESUMELIST);
        }else{
            Toast.makeText(mactivity,R.string.net_error,Toast.LENGTH_SHORT).show();
        }
    }


}
