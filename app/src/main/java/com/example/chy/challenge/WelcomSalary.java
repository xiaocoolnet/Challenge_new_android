package com.example.chy.challenge;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.chy.challenge.findcommany.SalaryMain;

public class WelcomSalary extends Activity implements Runnable{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcom_salary);
        new Thread(this).start();
    }

    @Override
    public void run() {
        try{
            Thread.sleep(2000);
            startActivity(new Intent(this,SalaryMain.class));
            //设置切换动画，从左边进入，左边退出
            overridePendingTransition(R.anim.activity_welcome_left, R.anim.activity_welcome_tright);
            finish();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
