package com.example.chy.challenge.findcommany.mine;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import com.example.chy.challenge.Findpersoanl.mine.bean.MyCollectBean;
import com.example.chy.challenge.NetInfo.UserNetConstant;
import com.example.chy.challenge.R;
import com.example.chy.challenge.Utils.CommonAdapter;
import com.example.chy.challenge.Utils.JsonResult;
import com.example.chy.challenge.Utils.ToastUtil;
import com.example.chy.challenge.Utils.ViewHolder;
import com.example.chy.challenge.Utils.VolleyUtil;
import com.example.chy.challenge.button.WaveView;
import com.example.chy.challenge.findcommany.mine.bean.Mine_personal_Collect_bean;
import com.example.chy.challenge.login.register.register_bean.UserInfoBean;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MineCollectActivity extends Activity {

    @BindView(R.id.back)
    WaveView back;
    @BindView(R.id.btn_delete)
    WaveView btnDelete;
    @BindView(R.id.btn_cancel)
    WaveView btnCancel;
    @BindView(R.id.lv_collect)
    ListView lvCollect;
    @BindView(R.id.tv_delete)
    TextView tvDelete;

    private Context context;
    private UserInfoBean userInfoBean;
    private List<Mine_personal_Collect_bean> myCollectBeans;
    private CommonAdapter<Mine_personal_Collect_bean> adapter1;
    private CommonAdapter<Mine_personal_Collect_bean> adapter2;
    private String mResult;
    private boolean state = false; //删除选择框的开关
    private List<String> selected; //记录选择item的id集合

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_my_collect);
        ButterKnife.bind(this);
        context = this;
        userInfoBean = new UserInfoBean(context);
        myCollectBeans = new ArrayList<>();
        selected = new ArrayList<>();
        getData();
    }

    /**
     * 获取收藏列表
     */
    private void getData() {
        String url = UserNetConstant.GETFAVORITE
                + "&userid=" + userInfoBean.getUserid()
                + "&type=2";
        VolleyUtil.VolleyGetRequest(context, url, new VolleyUtil.VolleyJsonCallback() {
            @Override
            public void onSuccess(String result) {
                if (JsonResult.JSONparser(context, result)) {
                    mResult = result;
                    setAdpter();
                }
            }

            @Override
            public void onError() {

            }
        });
    }

    /**
     * 设置适配器
     */
    private void setAdpter() {
        myCollectBeans.clear();
        myCollectBeans.addAll(getBeanFromJson(mResult));
        adapter1 = new CommonAdapter<Mine_personal_Collect_bean>(context, myCollectBeans, R.layout.submit_resume) {
            @Override
            public void convert(ViewHolder holder, Mine_personal_Collect_bean myCollectBean) {
                holder.setText(R.id.company_work_position, myCollectBean.getTitle())
                        .setText(R.id.company_tv_manager, myCollectBean.getRealname())
                        .setImageByUrl(R.id.adapter_head_image, myCollectBean.getLogo())
                        .setText(R.id.company_manager_position, myCollectBean.getMyjob())
                        .setText(R.id.adapter_myjob, myCollectBean.getMyjob())
                        .setText(R.id.company_tv_salary, myCollectBean.getSalary())
                        .setText(R.id.company_tv_count, myCollectBean.getCount())
                        .setText(R.id.adapter_location_city, myCollectBean.getCity().substring(0,3))
                        .setText(R.id.adapter_jobtime_worklife, myCollectBean.getExperience())
                        .setText(R.id.adapter_personal_degree, myCollectBean.getEducation())
                        .setText(R.id.adapter_work_property, myCollectBean.getWork_property());
                holder.getView(R.id.rl_check).setVisibility(View.GONE);

            }
        };
        adapter2 = new CommonAdapter<Mine_personal_Collect_bean>(context, myCollectBeans, R.layout.item_collect_deletable) {
            @Override
            public void convert(ViewHolder holder, final Mine_personal_Collect_bean myCollectBean) {
                holder.setText(R.id.company_work_position, myCollectBean.getTitle())
                        .setText(R.id.company_tv_manager, myCollectBean.getRealname())
                        .setImageByUrl(R.id.adapter_head_image, myCollectBean.getLogo())
                        .setText(R.id.company_manager_position, myCollectBean.getMyjob())
                        .setText(R.id.adapter_myjob, myCollectBean.getMyjob())
                        .setText(R.id.company_tv_salary, myCollectBean.getSalary())
                        .setText(R.id.company_tv_count, myCollectBean.getCount())
                        .setText(R.id.adapter_location_city, myCollectBean.getCity().substring(0,3))
                        .setText(R.id.adapter_jobtime_worklife, myCollectBean.getExperience())
                        .setText(R.id.adapter_personal_degree, myCollectBean.getEducation())
                        .setText(R.id.adapter_work_property, myCollectBean.getWork_property());
                holder.getView(R.id.rl_check).setVisibility(View.VISIBLE);
                final CheckBox cb = holder.getView(R.id.cb_item);
                cb.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (cb.isChecked()) {
                            add(myCollectBean.getJobid());
                        } else {
                            remove(myCollectBean.getJobid());
                        }
                    }
                });
            }
        };
        if (state) {
            lvCollect.setAdapter(adapter2);
        } else {
            lvCollect.setAdapter(adapter1);
        }
    }

    /**
     * 从集合中移除选中id
     *
     * @param resumes_id
     */
    private void remove(String resumes_id) {
        if (selected.contains(resumes_id)) {
            selected.remove(resumes_id);
        }
    }

    /**
     * 添加选中id到集合中
     *
     * @param resumes_id
     */
    private void add(String resumes_id) {
        if (!selected.contains(resumes_id)) {
            selected.add(resumes_id);
        }
    }

    @OnClick({R.id.back, R.id.btn_delete, R.id.btn_cancel, R.id.tv_delete})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.btn_delete:
                state = true;
                btnDelete.setVisibility(View.GONE);
                btnCancel.setVisibility(View.VISIBLE);
                tvDelete.setVisibility(View.VISIBLE);
                setAdpter();
                break;
            case R.id.btn_cancel:
                state = false;
                btnDelete.setVisibility(View.VISIBLE);
                btnCancel.setVisibility(View.GONE);
                tvDelete.setVisibility(View.GONE);
                selected.clear();
                setAdpter();
                break;
            case R.id.tv_delete:
                deleteMyCollect();
                break;
        }
    }

    /**
     * 删除我的收藏
     */
    private void deleteMyCollect() {
        String url = UserNetConstant.CANCELFAVORITE
                + "&userid=" + userInfoBean.getUserid()
                + "&object_id=" + listToString()
                + "&type=2";
        VolleyUtil.VolleyGetRequest(this, url, new VolleyUtil.VolleyJsonCallback() {
            @Override
            public void onSuccess(String result) {
                if(JsonResult.JSONparser(context,result)){
                    ToastUtil.showShort(context,"删除成功");
                    selected.clear();
                    state = false;
                    btnDelete.setVisibility(View.VISIBLE);
                    btnCancel.setVisibility(View.GONE);
                    tvDelete.setVisibility(View.GONE);
                    getData();
                }else{
                    ToastUtil.showShort(context,"删除失败");
                    selected.clear();
                    state = false;
                    btnDelete.setVisibility(View.VISIBLE);
                    btnCancel.setVisibility(View.GONE);
                    tvDelete.setVisibility(View.GONE);
                    getData();
                }
            }

            @Override
            public void onError() {

            }
        });

    }

    /**
     * @return
     */
    private String listToString() {
        String str = "";
        if (selected.size() == 0) {
            return "";
        } else {
            for (int i = 0; i < selected.size() - 1; i++) {
                str += selected.get(i) + ",";
            }
            str += selected.get(selected.size() - 1);
        }
        return str;
    }

    private List<Mine_personal_Collect_bean> getBeanFromJson(String result) {
        String data = "";
        try {
            JSONObject json = new JSONObject(result);
            data = json.getString("data");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return new Gson().fromJson(data, new TypeToken<List<MyCollectBean>>() {
        }.getType());
    }
}
