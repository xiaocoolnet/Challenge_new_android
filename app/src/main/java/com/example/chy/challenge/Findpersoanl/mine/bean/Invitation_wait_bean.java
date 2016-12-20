package com.example.chy.challenge.Findpersoanl.mine.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/11/28 0028.
 */

public class Invitation_wait_bean {

    /**
     * status : success
     * data : [{"userid":"301","realname":"lala","sex":"0","photo":"avatar20161103112919301.png","jobtype":"技术专员/助理5555","address":"555","create_time":""}]
     */

    private String status;
    /**
     * userid : 301
     * realname : lala
     * sex : 0
     * photo : avatar20161103112919301.png
     * jobtype : 技术专员/助理5555
     * address : 555
     * create_time :
     */

    private List<DataBean> data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        private String userid;
        private String realname;
        private String sex;
        private String photo;
        private String jobtype;
        private String address;
        private String create_time;

        public String getUserid() {
            return userid;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }

        public String getRealname() {
            return realname;
        }

        public void setRealname(String realname) {
            this.realname = realname;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        public String getJobtype() {
            return jobtype;
        }

        public void setJobtype(String jobtype) {
            this.jobtype = jobtype;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }
    }
}
