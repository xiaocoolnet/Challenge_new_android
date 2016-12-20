package com.example.chy.challenge.Findpersoanl.mine;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.chy.challenge.Findpersoanl.mine.invitation.Invitation_Wait;
import com.example.chy.challenge.Findpersoanl.talentmain.talent.findpersonal.FindPersonalFragment;
import com.example.chy.challenge.R;
import com.example.chy.challenge.button.WaveView;

/**
 * Created by Administrator on 2016/11/20 0020.
 */

public class Mine_Interview_Invitation extends Activity implements View.OnClickListener{
    private WaveView back,invatation_all;
    private RelativeLayout invatation_wait,invatation_over;
    private TextView tv_wait,color_wait,tv_over,color_over;
    private Invitation_Wait iwait;
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    private Bundle bundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mine_interview_invitation);
        getView();
        fragmentManager = getFragmentManager();
        transaction = fragmentManager.beginTransaction();
        tv_wait.setSelected(true);
        color_wait.setTextColor(getResources().getColor(R.color.green));
        iwait = new Invitation_Wait();
        bundle = new Bundle();
        bundle.putString("pagename", "待面试");
        iwait.setArguments(bundle);
        transaction.replace(R.id.invitation_framlayout, iwait);
        transaction.commit();
    }

    private void getView() {
        back = (WaveView) findViewById(R.id.back);
        back.setOnClickListener(this);
        invatation_all = (WaveView) findViewById(R.id.invatation_all);
        invatation_all.setOnClickListener(this);

        invatation_wait = (RelativeLayout) findViewById(R.id.invatation_wait);
        invatation_wait.setOnClickListener(this);
        tv_wait = (TextView) findViewById(R.id.invatation_tv_wait);
        color_wait = (TextView) findViewById(R.id.invatation_color_wait);

        invatation_over = (RelativeLayout) findViewById(R.id.invatation_over);
        invatation_over.setOnClickListener(this);
        tv_over = (TextView) findViewById(R.id.invatation_tv_over);
        color_over = (TextView) findViewById(R.id.invatation_color_over);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back:
                finish();
                break;
            case R.id.invatation_all://全部面试邀请
                break;
            case R.id.invatation_wait://待面试
                fragmentManager = getFragmentManager();
                transaction = fragmentManager.beginTransaction();
                tv_wait.setSelected(true);
                color_wait.setVisibility(View.VISIBLE);
                color_wait.setTextColor(getResources().getColor(R.color.green));
                color_over.setVisibility(View.GONE);
                iwait = new Invitation_Wait();
                bundle = new Bundle();
                bundle.putString("pagename", "待面试");
                iwait.setArguments(bundle);
                transaction.replace(R.id.invitation_framlayout, iwait);
                transaction.commit();
                break;
            case R.id.invatation_over://已结束
                fragmentManager = getFragmentManager();
                transaction = fragmentManager.beginTransaction();
                tv_over.setSelected(true);
                color_over.setVisibility(View.VISIBLE);
                color_over.setTextColor(getResources().getColor(R.color.green));
                color_wait.setVisibility(View.GONE);
                iwait = new Invitation_Wait();
                bundle = new Bundle();
                bundle.putString("pagename", "已结束");
                iwait.setArguments(bundle);
                transaction.replace(R.id.invitation_framlayout, iwait);
                transaction.commit();
                break;
        }
    }
}
