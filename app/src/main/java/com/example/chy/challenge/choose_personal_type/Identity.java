package com.example.chy.challenge.choose_personal_type;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import com.example.chy.challenge.Findpersoanl.TalentMain;
import com.example.chy.challenge.R;
import com.example.chy.challenge.WelcomSalary;
import com.example.chy.challenge.WelcomTalent;
import com.example.chy.challenge.button.RevealButton;
import com.example.chy.challenge.findcommany.SalaryMain;
import com.example.chy.challenge.login.register.Register_Commany_info;
import com.example.chy.challenge.login.register.Register_personal_info;
import com.example.chy.challenge.login.register.register_bean.UserInfo;
import com.example.chy.challenge.login.register.register_bean.UserInfoBean;

/**
 * Created by 77588 on 2016/9/1.
 */
public class Identity extends Activity implements View.OnClickListener {
    private RevealButton forpernson,formoney;
    private Intent intent;
    private String pagetype;
    private Activity mactivity;
    private UserInfoBean infobean;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.identity);
        mactivity = this;
        infobean  = new UserInfoBean(mactivity);
        intent = getIntent();
        pagetype = intent.getStringExtra("pagetype");
        initview();

    }

    private void initview() {
        forpernson = (RevealButton) findViewById(R.id.forperson);
        forpernson.setOnClickListener(this);
        formoney = (RevealButton) findViewById(R.id.formoney);
        formoney.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.forperson:
                if ("register".equals(pagetype)){
                    intent = new Intent(mactivity, Register_personal_info.class);
                    intent.putExtra("pagetype","register");
                    startActivity(intent);

                }else if ("login".equals(pagetype)){
                    if ("2".equals(infobean.getUsertype())||"3".equals(infobean.getUsertype())){
                        intent = new Intent(mactivity,WelcomTalent.class);
                        startActivity(intent);
                    }else if ("1".equals(infobean.getUsertype())){
//                        intent = new Intent(mactivity,WelcomTalent.class);
//                        startActivity(intent);
                        intent = new Intent(mactivity,Register_personal_info.class);
                        intent.putExtra("pagetype","login1");
                        startActivity(intent);
                    }else if ("0".equals(infobean.getUsertype())){
                        intent = new Intent(mactivity,Register_personal_info.class);
                        intent.putExtra("pagetype","login0");
                        startActivity(intent);
                    }
                }
                break;
            case R.id.formoney:
                if ("register".equals(pagetype)){
                    intent = new Intent(mactivity, Register_Commany_info.class);
                    intent.putExtra("pagetype","register");
                    startActivity(intent);
                }else if ("login".equals(pagetype)){
                    if ("1".equals(infobean.getUsertype())||"3".equals(infobean.getUsertype())){
                        intent = new Intent(mactivity,WelcomSalary.class);
                        startActivity(intent);
                    }else if ("2".equals(infobean.getUsertype())){
                        intent = new Intent(mactivity,Register_Commany_info.class);
                        intent.putExtra("pagetype","login2");
                        startActivity(intent);
                    }else if ("0".equals(infobean.getUsertype())){
                        intent = new Intent(mactivity,Register_Commany_info.class);
                        intent.putExtra("pagetype","login0");
                        startActivity(intent);
                    }
                }
                break;
            default:
                break;
        }
    }
}
