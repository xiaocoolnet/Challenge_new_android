package com.example.chy.challenge.NetInfo;

/**
 * Created by mac on 16/5/9.
 */
public interface UserNetConstant extends NetBaseConstant {
    //登录
    public final static String NET_USER_LOGIN = NET_BASE_HOST + "&a=applogin";
   //验证码
    public final static String NET_USER_CODE = NET_BASE_HOST + "&a=SendMobileCode";
   //验证手机是否注册
    public final static String CHECK_PHONE = NET_BASE_HOST + "&a=checkphone";
    //注册
    public final static String REGIST = NET_BASE_HOST + "&a=AppRegister";
    //修改密码
    public final static String CHANGE_PASSWORD = NET_BASE_HOST + "&a=forgetpwd";
    //上传头像
    public final static String UPLOADAVATAR= NET_BASE_HOST + "&a=uploadavatar";
    //修改公司企业资料
    public final static String UPDATECOMMANY= NET_BASE_HOST + "&a=savecompanyinfo";
    //获取公司信息
    public final static String GETMYCOMMANY= NET_BASE_HOST + "&a=getMyCompany_info";
    //获取招聘列表
 public final static String GETJOBLIST= NET_BASE_HOST + "&a=getjoblist";
 //获取简历列表
 public final static String GETRESUME= NET_BASE_HOST + "&a=getResumeList";
    //修改个人资料
    public final static String UPDATEPERSONAL= NET_BASE_HOST + "&a=savepersonalinfo";
    //发布教育经历
    public final static String PUBLISHEDUCATION= NET_BASE_HOST + "&a=PublishEducation";
    //获取黑名单type=1个人获取被拉黑的企业列表，type=2企业获取被拉黑的个人列表
    public final static String GETBLACKlIST= NET_BASE_HOST + "&a=getBlackList";
    //取消拉黑（1个人拉黑企业，2企业拉黑个人),blackid(被拉黑用户id,不写则删除全部)
    public final static String DELBLACKLIST= NET_BASE_HOST + "&a=delBlackList";
    //验证旧密码
    public final static String OLDPASSWORD= NET_BASE_HOST + "&a=oldpassword";
    //修改密码
    public final static String UPDATEPASS= NET_BASE_HOST + "&a=Modify_password";
    //修改手机号码
    public final static String UPDATEPHONE= NET_BASE_HOST + "&a=UpdateUserPhone";
    //获取个人和企业的基本信息
    public final static String GETMYINFO= NET_BASE_HOST + "&a=getMyinfo";
    //获取我的面试邀请列表
    public final static String GETMYINVITED= NET_BASE_HOST + "&a=getMyInvited";
    //获取企业信息列表(以及我的) (不含招聘列表)
    public final static String GETCOMPANYLIST_NO= NET_BASE_HOST + "&a=getCompanyList_no";
    //获取企业信息列表（包含招聘列表）
    public final static String GETCOMPANYLIST= NET_BASE_HOST + "&a=getCompanyList";
    //添加公司信息
    public final static String COMPANY_INFO= NET_BASE_HOST + "&a=company_info";
    //发布 工作经历
    public final static String PUBLISHWORK= NET_BASE_HOST + "&a=PublishWork";
    //发布 工作经历
    public final static String PUBLISHADVANTAGE= NET_BASE_HOST + "&a=PublishAdvantage";
    //发布 求职意向
    public final static String PUBLISHINTENSION= NET_BASE_HOST + "&a=PublishIntension";
    //获取我的投递记录
    public final static String GETMYAPPLYJOB= NET_BASE_HOST + "&a=getMyApplyJob";
    //获取我的收藏列表type(1、招聘、2、简历)
    public final static String GETFAVORITE= NET_BASE_HOST + "&a=getfavoritelist";
    //判断是否投递过简历
    public final static String APPLYJOB_JUDGE = NET_BASE_HOST + "&a=ApplyJob_judge";
    //发送简历
    public final static String APPLYJOB = NET_BASE_HOST + "&a=ApplyJob";
    //判断是否收藏过
    public final static String CHECKHADFAVORITE = NET_BASE_HOST + "&a=CheckHadFavorite";
    //收藏
    public final static String ADDFAVORITE = NET_BASE_HOST + "&a=addfavorite";
    //取消收藏
    public final static String CANCELFAVORITE = NET_BASE_HOST + "&a=cancelfavorite";
    //获取字典内容
    public final static String GETDICTIONARYLIST = NET_BASE_HOST + "&a=getDictionaryList";
    //获取我的简历
    public final static String GETMYRESUME = NET_BASE_HOST + "&a=getMyResume";
    //获取我的优势
    public final static String GETMYADVANTAGE = NET_BASE_HOST + "&a=getMyAdvantage";
    //获取我的求职意向
    public final static String GETMYINTENSION = NET_BASE_HOST + "&a=getMyIntension";
    //获取我的工作实习经历
    public final static String GETMYWORKLIST = NET_BASE_HOST + "&a=getMyWorkList";
    //发布项目经验
    public final static String PUBLISHPROJECT = NET_BASE_HOST + "&a=PublishProject";
    //获取教育经历
    public final static String GETMYEDULIST = NET_BASE_HOST + "&a=getMyEduList";
    //获取消息列表
    public final static String GET_CHAT_LIST_DATA = NET_BASE_HOST + "&a=xcGetChatListData";
}
