package com.example.chy.challenge.Findpersoanl.sendcommany;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.chy.challenge.Findpersoanl.talentmain.talentbean.FindPersonalBean;
import com.example.chy.challenge.NetInfo.NetBaseConstant;
import com.example.chy.challenge.R;
import com.example.chy.challenge.button.RoundImageView;
import com.example.chy.challenge.button.WaveView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * Created by Administrator on 2016/10/25 0025.
 */

public class Write_commany_title extends Activity implements View.OnClickListener{
    private WaveView back,company_profile,product_introduction;
    private RelativeLayout write_image;
    private RoundImageView write_image_btn;
    private TextView tv_commantname,tv_commanyurl,tv_industry,tv_clerk,tv_financing,tv_iswrite;
    private ImageView imageiswrite;
    private Intent intent;
    private FindPersonalBean.DataBean fpbd;
    private Activity mactivity;
    private ImageLoader imageLoader = ImageLoader.getInstance();
    private DisplayImageOptions options;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.write_commany_title);
        mactivity = this;
        intent = getIntent();
        fpbd = (FindPersonalBean.DataBean) intent.getSerializableExtra("fpbdinfo");

        getcommanytitle();
        setcommanytitle();
    }

    private void setcommanytitle() {
//        if (fpbd != null){
//            //    通过AlertDialog.Builder这个类来实例化我们的一个AlertDialog的对象
//            AlertDialog.Builder builder = new AlertDialog.Builder(mactivity);
//            //    设置Title的内容
//            builder.setTitle("系统提示");
//            //    设置Content来显示一个信息
//            builder.setMessage("请填写公司信息");
//            builder.setPositiveButton("好的", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//
//                }
//            }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//                    dialog
//                }
//            }).create().show();
//        }else{
//
//        }
        if (fpbd != null){
            if (fpbd.getCompany_name() != null &&fpbd.getCompany_name().length() > 0){
                tv_commantname.setText(fpbd.getCompany_name()+"");
            }else if (fpbd.getLogo() != null &&fpbd.getLogo().length() > 0){
                imageLoader.displayImage(NetBaseConstant.NET_HOST+fpbd.getLogo(),write_image_btn,options);
            }else if (fpbd.getCompany_web() != null&&fpbd.getCompany_web().length() > 0){
                tv_commanyurl.setText(fpbd.getCompany_web()+"");
            }else if (fpbd.getIndustry() != null&&fpbd.getIndustry().length() > 0){
                tv_industry.setText(fpbd.getIndustry()+"");
            }else if (fpbd.getCount() != null&&fpbd.getCount().length() > 0){
                tv_clerk.setText(fpbd.getCount()+"");
            }else if (fpbd.getFinancing() != null&&fpbd.getFinancing().length() > 0){
                tv_financing.setText(fpbd.getFinancing()+"");
            }
            if (fpbd.getCom_introduce() != null&&fpbd.getCom_introduce().length() > 0){
                tv_iswrite.setText("已填写");
            }else{
                tv_iswrite.setText("未填写");
            }
            if (fpbd.getProdute_info() != null&&fpbd.getProdute_info().length() > 0){
                imageiswrite.setPressed(true);
            }else{
                imageiswrite.setPressed(false);
            }
        }else{
            tv_commantname.setText("");
            tv_commanyurl.setText("");
            tv_industry.setText("");
            tv_clerk.setText("");
            tv_financing.setText("");
            tv_iswrite.setText("未填写");
            imageiswrite.setPressed(false);
        }
    }

    private void getcommanytitle() {
        back = (WaveView) findViewById(R.id.back);//返回
        back.setOnClickListener(this);
        write_image = (RelativeLayout) findViewById(R.id.write_image);//头像
        write_image_btn = (RoundImageView) findViewById(R.id.write_image_btn);//头像所在
        tv_commantname = (TextView) findViewById(R.id.write_tv_commany_name);//公司全称
        tv_commanyurl = (TextView) findViewById(R.id.write_et_commany_url);//公司官网
        tv_industry = (TextView) findViewById(R.id.write_et_commany_industry);//所属行业
        tv_clerk = (TextView) findViewById(R.id.write_et_commany_clerk);//公司职员人数
        tv_financing = (TextView) findViewById(R.id.write_et_commany_financing);//融资阶段
        company_profile = (WaveView) findViewById(R.id.ril_company_profile);//公司简介
        company_profile.setOnClickListener(this);
        tv_iswrite = (TextView) findViewById(R.id.write_company_profile_iswrite);//公司介绍
        product_introduction = (WaveView) findViewById(R.id.ril_product_introduction);//产品介绍
        product_introduction.setOnClickListener(this);
        imageiswrite = (ImageView) findViewById(R.id.write_image_iswrite);//产品介绍是否填写标志
        options = new DisplayImageOptions.Builder().showImageOnLoading(R.mipmap.ic_wode).showImageOnFail(R.mipmap.ic_wode).cacheInMemory(true).cacheOnDisc(true).build();
//        imageiswrite.setPressed(true);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back:
                finish();
                break;
        }
    }
}
