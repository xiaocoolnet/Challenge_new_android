package com.example.chy.challenge.findcommany.resume.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/12/21 0021.
 */

public class Resume_education_bean implements Serializable{

    /**
     * status : success
     * data : [{"userid":"301","school":"刻苦","major":"快咯","degree":"硕士","time":"2019年-2019年","experience":"哈饼","create_time":"1478144040"}]
     */

    private String status;
    /**
     * userid : 301
     * school : 刻苦
     * major : 快咯
     * degree : 硕士
     * time : 2019年-2019年
     * experience : 哈饼
     * create_time : 1478144040
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

    public static class DataBean implements Serializable{
        private String userid;
        private String school;
        private String major;
        private String degree;
        private String time;
        private String experience;
        private String create_time;

        public String getUserid() {
            return userid;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }

        public String getSchool() {
            return school;
        }

        public void setSchool(String school) {
            this.school = school;
        }

        public String getMajor() {
            return major;
        }

        public void setMajor(String major) {
            this.major = major;
        }

        public String getDegree() {
            return degree;
        }

        public void setDegree(String degree) {
            this.degree = degree;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getExperience() {
            return experience;
        }

        public void setExperience(String experience) {
            this.experience = experience;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }
    }
}
