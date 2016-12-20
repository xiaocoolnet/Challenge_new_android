package com.example.chy.challenge.Findpersoanl.talentmain;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.chy.challenge.Findpersoanl.mine.Black_Friend;
import com.example.chy.challenge.Findpersoanl.mine.Mine_Company_invitation;
import com.example.chy.challenge.Findpersoanl.mine.Mine_Interview_Invitation;
import com.example.chy.challenge.Findpersoanl.mine.Mine_recruitment;
import com.example.chy.challenge.Findpersoanl.mine.Mine_reward;
import com.example.chy.challenge.Findpersoanl.mine.Mine_vipinfo;
import com.example.chy.challenge.Findpersoanl.mine.Set_Up;
import com.example.chy.challenge.R;
import com.example.chy.challenge.button.WaveView;
import com.example.chy.challenge.login.register.Register_personal_info;

/**
 * Created by 77588 on 2016/9/1.
 */
public class MineForCompany extends Fragment implements View.OnClickListener{
    private WaveView company_setting,company_sendnews,company_update,company_approve,company_information,company_collection,company_reward,company_record,company_invite,black_friend;
    private View rootView;
    private Context mContext;
    private Intent intent;
    private LinearLayout mine_vip;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_mine_company, container, false);
        mContext = getActivity();
        initview();
        return rootView;
    }

    private void initview() {
        company_setting = (WaveView) rootView.findViewById(R.id.mine_company_setting);//设置
        company_setting.setOnClickListener(this);

        company_sendnews = (WaveView) rootView.findViewById(R.id.mine_company_sendnews);//发布职位
        company_sendnews.setOnClickListener(this);

        company_update = (WaveView) rootView.findViewById(R.id.mine_company_update);//修改个人信息
        company_update.setOnClickListener(this);

        company_approve = (WaveView) rootView.findViewById(R.id.mine_company_approve);//认证公司信息
        company_approve.setOnClickListener(this);

        company_information = (WaveView) rootView.findViewById(R.id.mine_company_information);//我的公司信息
        company_information.setOnClickListener(this);

        company_collection = (WaveView) rootView.findViewById(R.id.mine_company_collection);//我的收藏
        company_collection.setOnClickListener(this);

        company_reward = (WaveView) rootView.findViewById(R.id.ming_company_reward);//我的悬赏
        company_reward.setOnClickListener(this);

        company_record = (WaveView) rootView.findViewById(R.id.mine_company_record);//我的招聘记录
        company_record.setOnClickListener(this);

        company_invite = (WaveView) rootView.findViewById(R.id.mine_company_invite);//我的面试邀请
        company_invite.setOnClickListener(this);

        black_friend = (WaveView) rootView.findViewById(R.id.mine_black_friend);//我的黑名单
        black_friend.setOnClickListener(this);

        mine_vip = (LinearLayout) rootView.findViewById(R.id.mine_vip);//vip信息
        mine_vip.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.mine_company_setting://设置
                intent = new Intent(mContext, Set_Up.class);
                intent.putExtra("usertype","company");
                startActivity(intent);
                break;
            case R.id.mine_company_sendnews://发布职位
                break;
            case R.id.mine_company_update://修改个人信息
                intent  = new Intent(mContext, Register_personal_info.class);
                intent.putExtra("pagetype","company");
                startActivity(intent);
                break;
            case R.id.mine_company_approve://认证公司信息
                intent = new Intent(mContext, Mine_reward.class);
                startActivity(intent);
                break;
            case R.id.mine_company_information://我的公司信息
//                intent = new Intent(mContext, Mine_Company_invitation.class);
//                startActivity(intent);
                Toast.makeText(mContext,"暂未开通此功能",Toast.LENGTH_SHORT).show();
                break;
            case R.id.mine_company_collection://我的收藏

                break;
            case R.id.ming_company_reward://我的悬赏
//                intent = new Intent(mContext, Mine_reward.class);
//                startActivity(intent);
                Toast.makeText(mContext,"暂未开通此功能",Toast.LENGTH_SHORT).show();
                break;
            case R.id.mine_company_record://我的招聘记录
                intent = new Intent(mContext, Mine_recruitment.class);
                startActivity(intent);
                break;
            case R.id.mine_company_invite://我的面试邀请
                intent = new Intent(mContext, Mine_Interview_Invitation.class);
                startActivity(intent);
                break;
            case R.id.mine_black_friend://我的黑名单
                intent = new Intent(mContext, Black_Friend.class);
                startActivity(intent);
                break;
            case R.id.mine_vip://vip信息
                intent = new Intent(mContext, Mine_vipinfo.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}

