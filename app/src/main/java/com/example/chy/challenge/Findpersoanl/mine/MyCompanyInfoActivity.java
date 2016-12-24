package com.example.chy.challenge.Findpersoanl.mine;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.Window;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baoyz.actionsheet.ActionSheet;
import com.example.chy.challenge.Findpersoanl.mine.bean.CompanyInformation;
import com.example.chy.challenge.NetInfo.NetBaseConstant;
import com.example.chy.challenge.NetInfo.UserNetConstant;
import com.example.chy.challenge.R;
import com.example.chy.challenge.Utils.GalleryFinalUtil;
import com.example.chy.challenge.Utils.GetImageUtil;
import com.example.chy.challenge.Utils.ImgLoadUtil;
import com.example.chy.challenge.Utils.JsonResult;
import com.example.chy.challenge.Utils.PhotoWithPath;
import com.example.chy.challenge.Utils.PushImage;
import com.example.chy.challenge.Utils.PushImageUtil;
import com.example.chy.challenge.Utils.StringJoint;
import com.example.chy.challenge.Utils.ToastUtil;
import com.example.chy.challenge.Utils.VolleyUtil;
import com.example.chy.challenge.button.WaveView;
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
import cn.finalteam.galleryfinal.GalleryFinal;
import cn.finalteam.galleryfinal.model.PhotoInfo;
import de.hdodenhof.circleimageview.CircleImageView;

public class MyCompanyInfoActivity extends FragmentActivity {

    @BindView(R.id.back)
    WaveView back;
    @BindView(R.id.btn_confirm)
    WaveView btnConfirm;
    @BindView(R.id.iv_logo)
    CircleImageView ivLogo;
    @BindView(R.id.rl_logo)
    RelativeLayout rlLogo;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.rl_name)
    RelativeLayout rlName;
    @BindView(R.id.tv_website)
    TextView tvWebsite;
    @BindView(R.id.rl_website)
    RelativeLayout rlWebsite;
    @BindView(R.id.tv_industry)
    TextView tvIndustry;
    @BindView(R.id.rl_industry)
    RelativeLayout rlIndustry;
    @BindView(R.id.tv_scope)
    TextView tvScope;
    @BindView(R.id.rl_scope)
    RelativeLayout rlScope;
    @BindView(R.id.tv_period)
    TextView tvPeriod;
    @BindView(R.id.rl_period)
    RelativeLayout rlPeriod;

    private Context context;
    private CompanyInformation information;
    private UserInfoBean userInfoBean;
    private GalleryFinalUtil galleryFinalUtil;
    private ArrayList<PhotoInfo> mPhotoList;
    private ArrayList<PhotoWithPath> photoWithPaths;
    private final int REQUEST_CODE_CAMERA = 1000;
    private final int REQUEST_CODE_GALLERY = 1001;
    private String picname; //上传图片字符串

    public static final int RESULT_COMPANY_NAME = 0x110;
    public static final int RESULT_COMPANY_WEB = 0x111;
    public static final int RESULT_COMPANY_INDUSTRY = 0x112;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_my_company_info);
        ButterKnife.bind(this);
        context = this;
        userInfoBean = new UserInfoBean(context);
        galleryFinalUtil = new GalleryFinalUtil(1);
        mPhotoList = new ArrayList<>();
        photoWithPaths = new ArrayList<>();
        getData();
    }

    /**
     * 获取公司信息
     */
    private void getData() {
        String url = UserNetConstant.GETCOMPANYLIST_NO
                +"&userid=" + userInfoBean.getUserid();
        //+"&userid=301";
        VolleyUtil.VolleyGetRequest(context, url, new VolleyUtil.VolleyJsonCallback() {
            @Override
            public void onSuccess(String result) {
                if (JsonResult.JSONparser(context, result)) {
                    String data = "";
                    try {
                        JSONObject json = new JSONObject(result);
                        data = json.getString("data");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    List<CompanyInformation> informations = new Gson().fromJson(data, new TypeToken<List<CompanyInformation>>() {
                    }.getType());
                    information = informations.size() > 0 ? informations.get(0) : null;
                    setData();
                }
            }

            @Override
            public void onError() {

            }
        });
    }

    /**
     * 设置数据
     */
    private void setData() {
        if(information!=null){
            ImgLoadUtil.display(NetBaseConstant.NET_HOST + information.getLogo(), ivLogo);
            tvName.setText(information.getCompany_name());
            tvIndustry.setText(information.getIndustry());
            tvPeriod.setText(information.getFinancing());
            tvScope.setText(information.getCount());
            tvWebsite.setText(information.getCompany_web());
        }
    }

    @OnClick({R.id.back, R.id.btn_confirm, R.id.rl_logo, R.id.rl_name, R.id.rl_website, R.id.rl_industry, R.id.rl_scope, R.id.rl_period})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.btn_confirm:
                submit();
                break;
            case R.id.rl_logo:
                //修改公司logo
                showActionSheet();
                break;
            case R.id.rl_name:
                Intent intent = new Intent(context,EditInfoActivity.class);
                intent.putExtra("title","公司全称");
                intent.putExtra("description","公司全称是您所在公司的营业执照或劳动合同上的公司名称，请确保您填下完全匹配");
                intent.putExtra("hint","请输入公司全称");
                startActivityForResult(intent,RESULT_COMPANY_NAME);
                break;
            case R.id.rl_website:
                Intent intent1 = new Intent(context,EditInfoActivity.class);
                intent1.putExtra("title","公司全称");
                intent1.putExtra("description","公司全称是您所在公司的营业执照或劳动合同上的公司名称，请确保您填下完全匹配");
                intent1.putExtra("hint","请输入公司全称");
                startActivityForResult(intent1,RESULT_COMPANY_WEB);
                break;
            case R.id.rl_industry:
                Intent intent2 = new Intent(context,SelectIndustryActivity.class);
                startActivityForResult(intent2,RESULT_COMPANY_INDUSTRY);
                break;
            case R.id.rl_scope:
                
                break;
            case R.id.rl_period:

                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode != RESULT_CANCELED){
            switch (requestCode){
                case RESULT_COMPANY_NAME:
                    if(data!=null){
                        tvName.setText(data.getStringExtra("value"));
                    }
                    break;
                case RESULT_COMPANY_WEB:
                    if(data!=null){
                        tvWebsite.setText(data.getStringExtra("value"));
                    }
                case RESULT_COMPANY_INDUSTRY:
                    if(data!=null){
                        tvIndustry.setText(data.getStringExtra("value"));
                    }
            }
        }
    }

    /**
     * 上传修改信息
     */
    private void submit() {

    }

    /**
     * 相册拍照弹出框
     */
    private void showActionSheet() {
        ActionSheet.createBuilder(context, getSupportFragmentManager())
                .setCancelButtonTitle("取消")
                .setOtherButtonTitles("打开相册", "拍照")
                .setCancelableOnTouchOutside(true)
                .setListener(new ActionSheet.ActionSheetListener() {
                    @Override
                    public void onDismiss(ActionSheet actionSheet, boolean isCancel) {

                    }

                    @Override
                    public void onOtherButtonClick(ActionSheet actionSheet, int index) {

                        switch (index) {
                            case 0:
                                galleryFinalUtil.openAblum(context, mPhotoList, REQUEST_CODE_GALLERY, mOnHanlderResultCallback);
                                break;
                            case 1:
                                //获取拍照权限
                                if (galleryFinalUtil.openCamera(context, mPhotoList, REQUEST_CODE_CAMERA, mOnHanlderResultCallback)) {
                                    return;
                                } else {
                                    String[] perms = {"android.permission.CAMERA"};
                                    int permsRequestCode = 200;
                                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                        requestPermissions(perms, permsRequestCode);
                                    }
                                }
                                break;

                            default:
                                break;
                        }
                    }
                })
                .show();
    }


    /**
     * 选择图片后 返回的图片数据
     */

    private GalleryFinal.OnHanlderResultCallback mOnHanlderResultCallback = new GalleryFinal.OnHanlderResultCallback() {
        @Override
        public void onHanlderSuccess(int reqeustCode, List<PhotoInfo> resultList) {
            if (resultList != null) {
                photoWithPaths.clear();
                mPhotoList.clear();
                mPhotoList.addAll(resultList);
                photoWithPaths.addAll(GetImageUtil.getImgWithPaths(resultList));
                ImgLoadUtil.display("file:/" + mPhotoList.get(0).getPhotoPath(), ivLogo);
                //上传图片成功后发布
                new PushImageUtil().setPushIamge(context, photoWithPaths, new PushImage() {
                    @Override
                    public void success(boolean state) {
                        //获得图片字符串
                        ArrayList<String> picArray = new ArrayList<>();
                        for (PhotoWithPath photo : photoWithPaths) {
                            picArray.add(photo.getPicname());
                        }
                        picname = StringJoint.arrayJointchar(picArray, ",");
                    }

                    @Override
                    public void error() {
                        ToastUtil.showShort(context, "图片上传失败!");
                    }
                });
            }
        }

        @Override
        public void onHanlderFailure(int requestCode, String errorMsg) {
            Toast.makeText(context, errorMsg, Toast.LENGTH_SHORT).show();
        }
    };

    /**
     * 授权权限
     *
     * @param permsRequestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int permsRequestCode, String[] permissions, int[] grantResults) {

        switch (permsRequestCode) {

            case 200:

                boolean cameraAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                if (cameraAccepted) {
                    //授权成功之后，调用系统相机进行拍照操作等
                    galleryFinalUtil.openCamera(context, mPhotoList, REQUEST_CODE_CAMERA, mOnHanlderResultCallback);
                } else {
                    //用户授权拒绝之后，友情提示一下就可以了
                    ToastUtil.showShort(this, "已拒绝进入相机，如想开启请到设置中开启！");
                }

                break;

        }

    }
}
