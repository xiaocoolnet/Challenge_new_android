package com.example.chy.challenge.Findpersoanl;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.RadioButton;

import com.example.chy.challenge.Findpersoanl.talentmain.Messages;
import com.example.chy.challenge.Findpersoanl.talentmain.MineForCompany;
import com.example.chy.challenge.Findpersoanl.talentmain.talent.NoPosition;
import com.example.chy.challenge.Findpersoanl.talentmain.Talent;
import com.example.chy.challenge.R;
import com.example.chy.challenge.talent.Settings;

/**
 * Created by 77588 on 2016/9/1.
 */
public class TalentMain extends Activity implements View.OnClickListener{
    private RadioButton btnTalent,btnMessage,btnMine;
    private boolean HadPosition = true;
    private Intent intent;
    private String pagetype;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main_talent);
        Talentmainother.talentmain = this;
        intent = getIntent();
        pagetype = intent.getStringExtra("pagetype");
        initview();
        btnTalent.setChecked(true);
        btnTalent.setTextColor(getResources().getColor(R.color.green));
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        Talent talent = new Talent();
        transaction.replace(R.id.talent_layout,talent);
        transaction.commit();
    }

    private void initview() {
        btnTalent = (RadioButton) findViewById(R.id.btnTalent);
        btnTalent.setOnClickListener(this);
        btnMessage = (RadioButton) findViewById(R.id.btnMessage);
        btnMessage.setOnClickListener(this);
        btnMine = (RadioButton) findViewById(R.id.btnMine);
        btnMine.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        btnTalent.setTextColor(getResources().getColor(R.color.gray));
        btnMessage.setTextColor(getResources().getColor(R.color.gray));
        btnMine.setTextColor(getResources().getColor(R.color.gray));
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        switch (view.getId()){
            case R.id.btnTalent:
                btnTalent.setTextColor(getResources().getColor(R.color.green));
                Talent talent = new Talent();
                transaction.replace(R.id.talent_layout,talent);
                transaction.commitAllowingStateLoss();
                break;
            case R.id.btnMessage:
                btnMessage.setTextColor(getResources().getColor(R.color.green));
                Messages messages = new Messages();
                transaction.replace(R.id.talent_layout,messages);
                transaction.commitAllowingStateLoss();
                break;
            case R.id.btnMine:
                btnMine.setTextColor(getResources().getColor(R.color.green));
                MineForCompany mineForCompany = new MineForCompany();
                transaction.replace(R.id.talent_layout, mineForCompany);
                transaction.commitAllowingStateLoss();
                break;
            default:
                break;
        }
    }
    public static class Talentmainother{
        public static Activity talentmain;
    }
}
