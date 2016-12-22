package com.example.chy.challenge.findcommany;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import com.example.chy.challenge.Findpersoanl.TalentMain;
import com.example.chy.challenge.Findpersoanl.talentmain.Talent;
import com.example.chy.challenge.R;
import com.example.chy.challenge.button.WaveView;
import com.example.chy.challenge.findcommany.salarymain.Chance;

/**
 * Created by Administrator on 2016/11/19 0019.
 */

public class Work_Searching extends Activity implements View.OnClickListener{
    private WaveView back,choose_inauguration_redenvelope,choose_heightsalary,choose_lately,choose_hot,choose_new,choose_favorite,choose_postredpacket,choose_Interview_redenvelope;
    private Chance chance;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.work_searching);
        mContext = this;
        getView();
    }

    private void getView() {
        back = (WaveView) findViewById(R.id.back);
        back.setOnClickListener(this);
        choose_new = (WaveView) findViewById(R.id.choose_new);//最新
        choose_new.setOnClickListener(this);
        choose_hot = (WaveView) findViewById(R.id.choose_hot);//最热
        choose_hot.setOnClickListener(this);
        choose_lately = (WaveView) findViewById(R.id.choose_lately);//最近
        choose_lately.setOnClickListener(this);
        choose_favorite = (WaveView) findViewById(R.id.choose_favorite);//评价
        choose_favorite.setOnClickListener(this);
        choose_heightsalary = (WaveView) findViewById(R.id.choose_heightsalary);//高新
        choose_heightsalary.setOnClickListener(this);
        choose_postredpacket = (WaveView) findViewById(R.id.choose_postredpacket);//职位红包
        choose_postredpacket.setOnClickListener(this);
        choose_Interview_redenvelope = (WaveView) findViewById(R.id.choose_Interview_redenvelope);//面试红包
        choose_Interview_redenvelope.setOnClickListener(this);
        choose_inauguration_redenvelope = (WaveView) findViewById(R.id.choose_inauguration_redenvelope);//就职红包
        choose_inauguration_redenvelope.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        chance = (Chance) (SalaryMain.Salarytmainother.Salarytmain).getFragmentManager().findFragmentById(R.id.salary_layout);
        switch (v.getId()){
            case R.id.back:
                finish();
                break;
            case R.id.choose_new://最新
                chance.getIntentView1();
                finish();
                break;
            case R.id.choose_hot://最热
                chance.getIntentView2();
                finish();
                break;
            case R.id.choose_lately://最近
                chance.getIntentView3();
                finish();
                break;
            case R.id.choose_favorite://评价
                chance.getIntentView4();
                finish();
                break;
            case R.id.choose_heightsalary://高薪
                chance.getIntentView5();
                finish();
                break;
            case R.id.choose_postredpacket://职位红包
                chance.getIntentView6();
                finish();
                break;
            case R.id.choose_Interview_redenvelope://面试红包
                chance.getIntentView7();
                finish();
                break;
            case R.id.choose_inauguration_redenvelope://就职红包
                chance.getIntentView8();
                finish();
                break;
        }
    }
}
