package com.example.chy.challenge.login.register;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.chy.challenge.R;
import com.example.chy.challenge.button.Public_static_all;
import com.example.chy.challenge.button.WaveView;
import com.example.chy.challenge.login.register.register_bean.UserInfo;

/**
 * Created by Administrator on 2016/10/17 0017.
 */

public class Write_personal_info extends Activity implements View.OnClickListener{
    private RelativeLayout ril_text_num,ril_text_num_down;
    private LinearLayout ril_textnum_down;
    private TextView write_tv_textnum,write_tv_reminder,write_tv_textnum_down,write_tv_title,textView3;
    private EditText write_et_upcontent;
    private WaveView back,save;
    private Intent intent;
    private String type;
    private UserInfo info;
    private Activity mactivity;
    private int num;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.write_personal_info);
        mactivity = this;
        intent = getIntent();
        type = intent.getStringExtra("type");
        info = new UserInfo(mactivity);
        getView();
    }

    private void getView() {
        ril_text_num = (RelativeLayout) findViewById(R.id.ril_text_num);//上面的字数显示（）默认隐藏
        write_tv_textnum = (TextView) findViewById(R.id.write_tv_textnum);//上面的实时字数
        write_et_upcontent = (EditText) findViewById(R.id.write_et_upcontent);//输入框
        write_tv_reminder = (TextView) findViewById(R.id.write_tv_reminder);//下面的提示内容
        ril_textnum_down = (LinearLayout) findViewById(R.id.ril_textnum_down);//下面的字数显示（）默认显示
        write_tv_textnum_down = (TextView) findViewById(R.id.write_tv_textnum_down);//下面的实时字数
        ril_text_num_down = (RelativeLayout) findViewById(R.id.ril_text_num_down);
        textView3 = (TextView) findViewById(R.id.textView3);//下面总数
        back = (WaveView) findViewById(R.id.back);//返回
        back.setOnClickListener(this);
        save = (WaveView) findViewById(R.id.write_ril_save);//保存
        save.setOnClickListener(this);
        write_tv_title = (TextView) findViewById(R.id.write_tv_title);//标题


        write_et_upcontent.addTextChangedListener(new TextWatcher() {
            private CharSequence temp;
            private int selectionStart;
            private int selectionEnd;
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                temp = s;
                System.out.println("s="+s);
            }
            @Override
            public void afterTextChanged(Editable s) {
                if ("realname".equals(type)){
                    int number = num - s.length();
                    write_tv_textnum_down.setText("" + number);
                    selectionStart = write_et_upcontent.getSelectionStart();
                    selectionEnd = write_et_upcontent.getSelectionEnd();
                    if (temp.length() > num) {
                        s.delete(selectionStart - 1, selectionEnd);
                        int tempSelection = selectionStart;
                        write_et_upcontent.setText(s);
                        write_et_upcontent.setSelection(tempSelection);//设置光标在最后
                    }
                }else if ("myposition".equals(type)){
                    int number = num - s.length();
                    write_tv_textnum_down.setText("" + number);
                    selectionStart = write_et_upcontent.getSelectionStart();
                    selectionEnd = write_et_upcontent.getSelectionEnd();
                    if (temp.length() > num) {
                        s.delete(selectionStart - 1, selectionEnd);
                        int tempSelection = selectionStart;
                        write_et_upcontent.setText(s);
                        write_et_upcontent.setSelection(tempSelection);//设置光标在最后
                    }
                }else if ("myemail".equals(type)){
                    int number = num - s.length();
                    write_tv_textnum_down.setText("" + number);
                    selectionStart = write_et_upcontent.getSelectionStart();
                    selectionEnd = write_et_upcontent.getSelectionEnd();
                    if (temp.length() > num) {
                        s.delete(selectionStart - 1, selectionEnd);
                        int tempSelection = selectionStart;
                        write_et_upcontent.setText(s);
                        write_et_upcontent.setSelection(tempSelection);//设置光标在最后
                    }
                }else if ("currentcommany".equals(type)){
                    int number = num - s.length();
                    write_tv_textnum.setText("" + number);
                    selectionStart = write_et_upcontent.getSelectionStart();
                    selectionEnd = write_et_upcontent.getSelectionEnd();
                    if (temp.length() > num) {
                        s.delete(selectionStart - 1, selectionEnd);
                        int tempSelection = selectionStart;
                        write_et_upcontent.setText(s);
                        write_et_upcontent.setSelection(tempSelection);//设置光标在最后
                    }
                }else if ("jobname".equals(type)){
                    int number = num - s.length();
                    write_tv_textnum_down.setText("" + number);
                    selectionStart = write_et_upcontent.getSelectionStart();
                    selectionEnd = write_et_upcontent.getSelectionEnd();
                    if (temp.length() > num) {
                        s.delete(selectionStart - 1, selectionEnd);
                        int tempSelection = selectionStart;
                        write_et_upcontent.setText(s);
                        write_et_upcontent.setSelection(tempSelection);//设置光标在最后
                    }
                }else if ("realname1".equals(type)){
                    int number = num - s.length();
                    write_tv_textnum_down.setText("" + number);
                    selectionStart = write_et_upcontent.getSelectionStart();
                    selectionEnd = write_et_upcontent.getSelectionEnd();
                    if (temp.length() > num) {
                        s.delete(selectionStart - 1, selectionEnd);
                        int tempSelection = selectionStart;
                        write_et_upcontent.setText(s);
                        write_et_upcontent.setSelection(tempSelection);//设置光标在最后
                    }
                }else if ("companyname".equals(type)){
                    int number = num - s.length();
                    write_tv_textnum_down.setText("" + number);
                    selectionStart = write_et_upcontent.getSelectionStart();
                    selectionEnd = write_et_upcontent.getSelectionEnd();
                    if (temp.length() > num) {
                        s.delete(selectionStart - 1, selectionEnd);
                        int tempSelection = selectionStart;
                        write_et_upcontent.setText(s);
                        write_et_upcontent.setSelection(tempSelection);//设置光标在最后
                    }
                }else if ("schoolname".equals(type)){
                    int number = num - s.length();
                    write_tv_textnum_down.setText("" + number);
                    selectionStart = write_et_upcontent.getSelectionStart();
                    selectionEnd = write_et_upcontent.getSelectionEnd();
                    if (temp.length() > num) {
                        s.delete(selectionStart - 1, selectionEnd);
                        int tempSelection = selectionStart;
                        write_et_upcontent.setText(s);
                        write_et_upcontent.setSelection(tempSelection);//设置光标在最后
                    }
                }else if ("schoolmajor".equals(type)){
                    int number = num - s.length();
                    write_tv_textnum_down.setText("" + number);
                    selectionStart = write_et_upcontent.getSelectionStart();
                    selectionEnd = write_et_upcontent.getSelectionEnd();
                    if (temp.length() > num) {
                        s.delete(selectionStart - 1, selectionEnd);
                        int tempSelection = selectionStart;
                        write_et_upcontent.setText(s);
                        write_et_upcontent.setSelection(tempSelection);//设置光标在最后
                    }
                }else if ("projectname".equals(type)){
                    int number = num - s.length();
                    write_tv_textnum_down.setText("" + number);
                    selectionStart = write_et_upcontent.getSelectionStart();
                    selectionEnd = write_et_upcontent.getSelectionEnd();
                    if (temp.length() > num) {
                        s.delete(selectionStart - 1, selectionEnd);
                        int tempSelection = selectionStart;
                        write_et_upcontent.setText(s);
                        write_et_upcontent.setSelection(tempSelection);//设置光标在最后
                    }
                }else if ("starttime".equals(type)){
                    int number = num - s.length();
                    write_tv_textnum_down.setText("" + number);
                    selectionStart = write_et_upcontent.getSelectionStart();
                    selectionEnd = write_et_upcontent.getSelectionEnd();
                    if (temp.length() > num) {
                        s.delete(selectionStart - 1, selectionEnd);
                        int tempSelection = selectionStart;
                        write_et_upcontent.setText(s);
                        write_et_upcontent.setSelection(tempSelection);//设置光标在最后
                    }
                }else if ("endtime".equals(type)){
                    int number = num - s.length();
                    write_tv_textnum_down.setText("" + number);
                    selectionStart = write_et_upcontent.getSelectionStart();
                    selectionEnd = write_et_upcontent.getSelectionEnd();
                    if (temp.length() > num) {
                        s.delete(selectionStart - 1, selectionEnd);
                        int tempSelection = selectionStart;
                        write_et_upcontent.setText(s);
                        write_et_upcontent.setSelection(tempSelection);//设置光标在最后
                    }
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back:
                finish();
                break;
            case R.id.write_ril_save:
                if ("realname".equals(type)){//姓名
                    info.setRealname(write_et_upcontent.getText().toString()+"");
                    Public_static_all.iscompanyb = true;
                }else if ("myposition".equals(type)){//我的职位
                    Public_static_all.iscompanyc = true;
                    info.setMyjob(write_et_upcontent.getText().toString()+"");
                }else if ("myemail".equals(type)){//接受简历邮箱
                    Public_static_all.iscompanyd = true;
                    info.setEmail(write_et_upcontent.getText().toString()+"");
                }else if ("currentcommany".equals(type)){//当前公司
                    Public_static_all.iscompanye = true;
                    info.setCompany(write_et_upcontent.getText().toString()+"");
                }else if ("QQ".equals(type)){//QQ
                    Public_static_all.iscompanyf = true;
                    info.setQq(write_et_upcontent.getText().toString()+"");
                }else if ("QQ1".equals(type)){//职位选择
                    Public_static_all.iscompanym = true;
                    info.setQq(write_et_upcontent.getText().toString()+"");
                }else if ("weixin".equals(type)){//职位选择
                    Public_static_all.iscompanyg = true;
                    info.setWeixin(write_et_upcontent.getText().toString()+"");
                }else if ("weixin1".equals(type)){//职位选择
                    Public_static_all.iscompanyn = true;
                    info.setWeixin(write_et_upcontent.getText().toString()+"");
                }else if ("weibo".equals(type)){//职位选择
                    Public_static_all.iscompanyh = true;
                    info.setWeibo(write_et_upcontent.getText().toString()+"");
                }else if ("weibo1".equals(type)){//职位选择
                    Public_static_all.iscompanyo = true;
                    info.setWeibo(write_et_upcontent.getText().toString()+"");
                }else if ("realname1".equals(type)){//个人用户姓名
                    Public_static_all.iscompanyj = true;
                    info.setRealname(write_et_upcontent.getText().toString()+"");
                }else if ("companyname".equals(type)){//公司名称
                    Public_static_all.isJobd = true;
                    info.setCompany_nameone(write_et_upcontent.getText().toString()+"");
                }else if ("schoolname".equals(type)){//学校名称
                    Public_static_all.iseducationa = true;
                    info.setSchoolname(write_et_upcontent.getText().toString()+"");
                }else if ("schoolmajor".equals(type)){//专业名称
                    Public_static_all.iseducationb = true;
                    info.setSchoolmajor(write_et_upcontent.getText().toString()+"");
                }else if ("projectname".equals(type)){//项目名称
                    Public_static_all.idprojectnamea = true;
                    info.setProjectname(write_et_upcontent.getText().toString()+"");
                }else if ("starttime".equals(type)){//项目开始时间
                    Public_static_all.idprojectnamec = true;
                    info.setStarttime(write_et_upcontent.getText().toString()+"");
                }else if ("endtime".equals(type)){//项目开始时间
                    Public_static_all.idprojectnamed = true;
                    info.setStarttime(write_et_upcontent.getText().toString()+"");
                }
                finish();
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if ("realname".equals(type)){
            write_tv_title.setText("姓名");
            textView3.setText("/4");
            num = 4;
            write_tv_textnum_down.setText("4");
            write_tv_reminder.setText("请填写您真实的姓名");
        }else if ("myposition".equals(type)){
            write_tv_title.setText("我的职位");
            textView3.setText("/10");
            num = 10;
            write_tv_textnum_down.setText("10");
            write_tv_reminder.setText("请填写您的现任职位，不能包含特殊字符");
        }else if ("myemail".equals(type)){
            write_tv_title.setText("接收简历邮箱");
            textView3.setText("/20");
            num = 20;
            write_tv_textnum_down.setText("20");
            write_tv_reminder.setText("请填写您的公司邮箱");
        }else if ("currentcommany".equals(type)){
            write_tv_title.setText("职位选择");
            ril_text_num.setVisibility(View.VISIBLE);
            ril_textnum_down.setVisibility(View.GONE);
            write_tv_reminder.setText("公司全称是您所在公司的营业执照或劳动合同上的公司名称，请确保您的填写完全匹配");
            num = 50;
        }else if ("jobname".equals(type)){
            write_tv_title.setText("职位名称");
            write_tv_textnum_down.setText("4");
            textView3.setText("/4");
            num = 4;
            write_tv_reminder.setText("请填写您的职位名称");
        }else if ("realname1".equals(type)){
            write_tv_title.setText("姓名");
            textView3.setText("/4");
            num = 4;
            write_tv_textnum_down.setText("4");
            write_tv_reminder.setText("请填写您真实的姓名");
        }else if ("companyname".equals(type)){
            write_tv_title.setText("公司名称");
            textView3.setText("/20");
            num = 20;
            write_tv_textnum_down.setText("20");
        }else if ("schoolname".equals(type)){
            write_tv_title.setText("学校名称");
            textView3.setText("/20");
            num = 20;
            write_tv_textnum_down.setText("20");
        }else if ("schoolmajor".equals(type)){
            write_tv_title.setText("专业名称");
            textView3.setText("/20");
            num = 20;
            write_tv_textnum_down.setText("20");
        }else if ("projectname".equals(type)){
            write_tv_title.setText("项目名称");
            textView3.setText("/12");
            textView3.setTextColor(getResources().getColor(R.color.green));
            num = 12;
            write_tv_textnum_down.setText("12");
        }else if ("starttime".equals(type)){
            write_tv_title.setText("开始时间");
            textView3.setText("/10");
            textView3.setTextColor(getResources().getColor(R.color.green));
            num = 10;
            write_tv_textnum_down.setText("10");
        }else if ("endtime".equals(type)){
            write_tv_title.setText("结束时间");
            textView3.setText("/10");
            textView3.setTextColor(getResources().getColor(R.color.green));
            num = 10;
            write_tv_textnum_down.setText("10");
        }else if ("QQ".equals(type)){
            write_tv_title.setText("QQ");
            ril_text_num.setVisibility(View.GONE);
            ril_text_num_down.setVisibility(View.GONE);
        }else if ("QQ1".equals(type)){
            write_tv_title.setText("QQ");
            ril_text_num.setVisibility(View.GONE);
            ril_text_num_down.setVisibility(View.GONE);
        }else if ("weixin".equals(type)){
            write_tv_title.setText("weixin");
            ril_text_num.setVisibility(View.GONE);
            ril_text_num_down.setVisibility(View.GONE);
        }else if ("weixin1".equals(type)){
            write_tv_title.setText("weixin");
            ril_text_num.setVisibility(View.GONE);
            ril_text_num_down.setVisibility(View.GONE);
        }else if ("weibo".equals(type)){
            write_tv_title.setText("weibo");
            ril_text_num.setVisibility(View.GONE);
            ril_text_num_down.setVisibility(View.GONE);
        }else if ("weibo1".equals(type)){
            write_tv_title.setText("weibo");
            ril_text_num.setVisibility(View.GONE);
            ril_text_num_down.setVisibility(View.GONE);
        }
    }
}
