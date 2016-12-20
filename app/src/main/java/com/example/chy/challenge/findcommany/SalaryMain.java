package com.example.chy.challenge.findcommany;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

import com.example.chy.challenge.findcommany.salarymain.Chance;
import com.example.chy.challenge.Findpersoanl.talentmain.Messages;
import com.example.chy.challenge.findcommany.salarymain.MineForPerson;
import com.example.chy.challenge.findcommany.salarymain.Resume;
import com.example.chy.challenge.R;

/**
 * Created by 77588 on 2016/9/1.
 */
public class SalaryMain extends Activity implements View.OnClickListener{
    private RadioButton btnChance,btnResume,btnMessage,btnMine;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_salary);
        Salarytmainother.Salarytmain = this;
        initview();
        btnChance.setChecked(true);

        btnChance.setTextColor(getResources().getColor(R.color.green));
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        Chance chance = new Chance();
        transaction.replace(R.id.salary_layout,chance);
        transaction.commit();
    }

    private void initview() {
        btnChance = (RadioButton) findViewById(R.id.btnChance);
        btnChance.setOnClickListener(this);
        btnResume = (RadioButton) findViewById(R.id.btnResume);
        btnResume.setOnClickListener(this);
        btnMessage = (RadioButton) findViewById(R.id.btnMessage);
        btnMessage.setOnClickListener(this);
        btnMine = (RadioButton) findViewById(R.id.btnMine);
        btnMine.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        btnChance.setTextColor(getResources().getColor(R.color.gray3));
        btnMine.setTextColor(getResources().getColor(R.color.gray3));
        btnMessage.setTextColor(getResources().getColor(R.color.gray3));
        btnResume.setTextColor(getResources().getColor(R.color.gray3));
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        switch (view.getId()){
            case R.id.btnChance:
                btnChance.setTextColor(getResources().getColor(R.color.green));
                Chance chance = new Chance();
                transaction.replace(R.id.salary_layout,chance);
                transaction.commit();
                break;
            case R.id.btnResume:
                btnResume.setTextColor(getResources().getColor(R.color.green));
                Resume resume = new Resume();
                transaction.replace(R.id.salary_layout,resume);
                transaction.commit();
                break;
            case  R.id.btnMessage:
                btnMessage.setTextColor(getResources().getColor(R.color.green));
                Messages messages = new Messages();
                transaction.replace(R.id.salary_layout,messages);
                transaction.commit();
                break;
            case R.id.btnMine:
                btnMine.setTextColor(getResources().getColor(R.color.green));
                MineForPerson mineForPerson = new MineForPerson();
                transaction.replace(R.id.salary_layout, mineForPerson);
                transaction.commit();
                break;
            default:
                break;
        }
    }
    public static class Salarytmainother{
        public static Activity Salarytmain;
    }
}
