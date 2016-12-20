package com.example.chy.challenge.Findpersoanl;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import com.example.chy.challenge.Findpersoanl.talentmain.Talent;
import com.example.chy.challenge.R;
import com.example.chy.challenge.button.WaveView;

/**
 * Created by Administrator on 2016/11/19 0019.
 */

public class Choose_Searching extends Activity implements View.OnClickListener{
    private WaveView back,choose_recommend,choose_lately,choose_hot,choose_new,choose_favorite,choose_online;
    private Talent talent;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.choose_searching);
        mContext = this;
        getView();
    }

    private void getView() {
        back = (WaveView) findViewById(R.id.back);
        back.setOnClickListener(this);
        choose_recommend = (WaveView) findViewById(R.id.choose_recommend);//推荐
        choose_recommend.setOnClickListener(this);
        choose_lately = (WaveView) findViewById(R.id.choose_lately);//最近
        choose_lately.setOnClickListener(this);
        choose_hot = (WaveView) findViewById(R.id.choose_hot);//最热
        choose_hot.setOnClickListener(this);
        choose_new = (WaveView) findViewById(R.id.choose_new);//最新
        choose_new.setOnClickListener(this);
        choose_favorite = (WaveView) findViewById(R.id.choose_favorite);//好评
        choose_favorite.setOnClickListener(this);
        choose_online = (WaveView) findViewById(R.id.choose_online);//在线
        choose_online.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        talent = (Talent) (TalentMain.Talentmainother.talentmain).getFragmentManager().findFragmentById(R.id.talent_layout);
        switch (v.getId()){
            case R.id.back:
                finish();
                break;
            case R.id.choose_recommend:
                talent.getMyCompanynews(3);
                talent.getIntentView1();
                finish();
                break;
            case R.id.choose_lately:
                talent.getMyCompanynews(4);
                talent.getIntentView2();
                finish();
                break;
            case R.id.choose_hot:
                talent.getMyCompanynews(5);
                talent.getIntentView3();
                finish();
                break;
            case R.id.choose_new:
                talent.getMyCompanynews(6);
                talent.getIntentView4();
                finish();
                break;
            case R.id.choose_favorite:
                talent.getMyCompanynews(7);
                talent.getIntentView5();
                finish();
                break;
            case R.id.choose_online:
                talent.getMyCompanynews(7);
                talent.getIntentView6();
                finish();
                break;
        }
    }
}
