package com.example.chy.challenge.Findpersoanl.talentmain.talent;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.chy.challenge.Findpersoanl.talentmain.Talent;
import com.example.chy.challenge.Findpersoanl.talentmain.talent.findpersonal.FindPersonalFragment;
import com.example.chy.challenge.R;

/**
 * Created by Administrator on 2016/11/9 0009.
 */

public class FindPersonal extends Fragment {
    private View view;
    private RadioGroup radiogroup_title;
    private RadioButton rbtn_recommend,rbtn_lately,rbtn_hot,rbtn_new,rbtn_favorite,rbtn_online;
    private TextView tv_recommend,tv_lately,tv_hot,tv_new,tv_favorite,tv_online;
    private FindPersonalFragment findpersonalfragment;
    private Bundle bundle;
    private String pagename;
    private  FragmentManager fragmentManager;
    private  FragmentTransaction transaction;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.findpersonal,container,false);
        findpersonaview();
        bundle = getArguments();
        if (bundle != null){
            if (bundle.getString("pagename") != null &&bundle.getString("pagename").length() > 0) {
                pagename = bundle.getString("pagename");
                fragmentManager = getFragmentManager();
                transaction = fragmentManager.beginTransaction();
                if ("推荐".equals(pagename)) {
                    rbtn_recommend.setChecked(true);
                    tv_recommend.setTextColor(getResources().getColor(R.color.green));
                    findpersonalfragment = new FindPersonalFragment();
                    bundle = new Bundle();
                    bundle.putString("pagename", pagename);
                    findpersonalfragment.setArguments(bundle);
                    transaction.replace(R.id.findpersonal_ril_all, findpersonalfragment);
                    transaction.commitAllowingStateLoss();
                } else if ("最近".equals(pagename)) {
                    rbtn_lately.setChecked(true);
                    tv_lately.setTextColor(getResources().getColor(R.color.green));
                    findpersonalfragment = new FindPersonalFragment();
                    bundle = new Bundle();
                    bundle.putString("pagename", pagename);
                    findpersonalfragment.setArguments(bundle);
                    transaction.replace(R.id.findpersonal_ril_all, findpersonalfragment);
                    transaction.commitAllowingStateLoss();
                } else if ("最热".equals(pagename)) {
                    rbtn_hot.setChecked(true);
                    tv_hot.setTextColor(getResources().getColor(R.color.green));
                    findpersonalfragment = new FindPersonalFragment();
                    bundle = new Bundle();
                    bundle.putString("pagename", pagename);
                    findpersonalfragment.setArguments(bundle);
                    transaction.replace(R.id.findpersonal_ril_all, findpersonalfragment);
                    transaction.commitAllowingStateLoss();
                } else if ("最新".equals(pagename)) {
                    rbtn_new.setChecked(true);
                    tv_new.setTextColor(getResources().getColor(R.color.green));
                    findpersonalfragment = new FindPersonalFragment();
                    bundle = new Bundle();
                    bundle.putString("pagename", pagename);
                    findpersonalfragment.setArguments(bundle);
                    transaction.replace(R.id.findpersonal_ril_all, findpersonalfragment);
                    transaction.commitAllowingStateLoss();
                } else if ("好评".equals(pagename)) {
                    rbtn_favorite.setChecked(true);
                    tv_favorite.setTextColor(getResources().getColor(R.color.green));
                    findpersonalfragment = new FindPersonalFragment();
                    bundle = new Bundle();
                    bundle.putString("pagename", pagename);
                    findpersonalfragment.setArguments(bundle);
                    transaction.replace(R.id.findpersonal_ril_all, findpersonalfragment);
                    transaction.commitAllowingStateLoss();
                } else if ("在线".equals(pagename)) {
                    rbtn_online.setChecked(true);
                    tv_online.setTextColor(getResources().getColor(R.color.green));
                    findpersonalfragment = new FindPersonalFragment();
                    bundle = new Bundle();
                    bundle.putString("pagename", pagename);
                    findpersonalfragment.setArguments(bundle);
                    transaction.replace(R.id.findpersonal_ril_all, findpersonalfragment);
                    transaction.commitAllowingStateLoss();
                }
            }
        }else{
            rbtn_recommend.setChecked(true);
            tv_recommend.setTextColor(getResources().getColor(R.color.green));
            findpersonalfragment = new FindPersonalFragment();
            bundle = new Bundle();
            bundle.putString("pagename", pagename);
            findpersonalfragment.setArguments(bundle);
            transaction.replace(R.id.findpersonal_ril_all, findpersonalfragment);
            transaction.commitAllowingStateLoss();
        }
        return view;
    }

    private void findpersonaview() {
        radiogroup_title = (RadioGroup) view.findViewById(R.id.findpersonal_rg);
        rbtn_recommend = (RadioButton) view.findViewById(R.id.findpersonal_rbtn_recommend);//推荐
        rbtn_lately = (RadioButton) view.findViewById(R.id.findpersonal_rbtn_lately);//最近
        rbtn_hot = (RadioButton) view.findViewById(R.id.findpersonal_rbtn_hot);//最热
        rbtn_new = (RadioButton) view.findViewById(R.id.findpersonal_rbtn_new);//最新
        rbtn_favorite = (RadioButton) view.findViewById(R.id.findpersonal_rbtn_favorite);//好评
        rbtn_online = (RadioButton) view.findViewById(R.id.findpersonal_rbtn_online);//在线
        tv_recommend = (TextView) view.findViewById(R.id.findpersonal_tv_recommend);//推荐
        tv_lately = (TextView) view.findViewById(R.id.findpersonal_tv_lately);//最近
        tv_hot = (TextView) view.findViewById(R.id.findpersonal_tv_hot);//最热
        tv_new = (TextView) view.findViewById(R.id.findpersonal_tv_new);//最新
        tv_favorite = (TextView) view.findViewById(R.id.findpersonal_tv_favorite);//好评
        tv_online = (TextView) view.findViewById(R.id.findpersonal_tv_online);//在线
        radiogroup_title.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                switch (checkedId){
                    case R.id.findpersonal_rbtn_recommend:
                        tv_recommend.setBackgroundResource(R.color.green);
                        tv_lately.setBackgroundResource(R.color.gray);
                        tv_hot.setBackgroundResource(R.color.gray);
                        tv_new.setBackgroundResource(R.color.gray);
                        tv_favorite.setBackgroundResource(R.color.gray);
                        tv_online.setBackgroundResource(R.color.gray);
                        findpersonalfragment = new FindPersonalFragment();
                        bundle = new Bundle();
                        bundle.putString("pagename", "推荐");
                        findpersonalfragment.setArguments(bundle);
                        transaction.replace(R.id.findpersonal_ril_all,findpersonalfragment);
                        transaction.commitAllowingStateLoss();
                        break;
                    case R.id.findpersonal_rbtn_lately:
                        tv_recommend.setBackgroundResource(R.color.gray);
                        tv_lately.setBackgroundResource(R.color.green);
                        tv_hot.setBackgroundResource(R.color.gray);
                        tv_new.setBackgroundResource(R.color.gray);
                        tv_favorite.setBackgroundResource(R.color.gray);
                        tv_online.setBackgroundResource(R.color.gray);
                        findpersonalfragment = new FindPersonalFragment();
                        bundle = new Bundle();
                        bundle.putString("pagename", "最近");
                        findpersonalfragment.setArguments(bundle);
                        transaction.replace(R.id.findpersonal_ril_all,findpersonalfragment);
                        transaction.commitAllowingStateLoss();
                        break;
                    case R.id.findpersonal_rbtn_hot:
                        tv_recommend.setBackgroundResource(R.color.gray);
                        tv_lately.setBackgroundResource(R.color.gray);
                        tv_hot.setBackgroundResource(R.color.green);
                        tv_new.setBackgroundResource(R.color.gray);
                        tv_favorite.setBackgroundResource(R.color.gray);
                        tv_online.setBackgroundResource(R.color.gray);
                        findpersonalfragment = new FindPersonalFragment();
                        bundle = new Bundle();
                        bundle.putString("pagename", "最热");
                        findpersonalfragment.setArguments(bundle);
                        transaction.replace(R.id.findpersonal_ril_all,findpersonalfragment);
                        transaction.commitAllowingStateLoss();
                        break;
                    case R.id.findpersonal_rbtn_new:
                        tv_recommend.setBackgroundResource(R.color.gray);
                        tv_lately.setBackgroundResource(R.color.gray);
                        tv_hot.setBackgroundResource(R.color.gray);
                        tv_new.setBackgroundResource(R.color.green);
                        tv_favorite.setBackgroundResource(R.color.gray);
                        tv_online.setBackgroundResource(R.color.gray);
                        findpersonalfragment = new FindPersonalFragment();
                        bundle = new Bundle();
                        bundle.putString("pagename", "最新");
                        findpersonalfragment.setArguments(bundle);
                        transaction.replace(R.id.findpersonal_ril_all,findpersonalfragment);
                        transaction.commitAllowingStateLoss();
                        break;
                    case R.id.findpersonal_rbtn_favorite:
                        tv_recommend.setBackgroundResource(R.color.gray);
                        tv_lately.setBackgroundResource(R.color.gray);
                        tv_hot.setBackgroundResource(R.color.gray);
                        tv_new.setBackgroundResource(R.color.gray);
                        tv_favorite.setBackgroundResource(R.color.green);
                        tv_online.setBackgroundResource(R.color.gray);
                        findpersonalfragment = new FindPersonalFragment();
                        bundle = new Bundle();
                        bundle.putString("pagename", "好评");
                        findpersonalfragment.setArguments(bundle);
                        transaction.replace(R.id.findpersonal_ril_all,findpersonalfragment);
                        transaction.commitAllowingStateLoss();
                        break;
                    case R.id.findpersonal_rbtn_online:
                        tv_recommend.setBackgroundResource(R.color.gray);
                        tv_lately.setBackgroundResource(R.color.gray);
                        tv_hot.setBackgroundResource(R.color.gray);
                        tv_new.setBackgroundResource(R.color.gray);
                        tv_favorite.setBackgroundResource(R.color.gray);
                        tv_online.setBackgroundResource(R.color.green);
                        findpersonalfragment = new FindPersonalFragment();
                        bundle = new Bundle();
                        bundle.putString("pagename", "在线");
                        findpersonalfragment.setArguments(bundle);
                        transaction.replace(R.id.findpersonal_ril_all,findpersonalfragment);
                        transaction.commitAllowingStateLoss();
                        break;
                }
            }
        });
    }
}
