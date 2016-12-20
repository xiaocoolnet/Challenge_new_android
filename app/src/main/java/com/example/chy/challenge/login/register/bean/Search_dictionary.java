package com.example.chy.challenge.login.register.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/12/16 0016.
 */

public class Search_dictionary implements Serializable{

    /**
     * status : success
     * data : [{"term_id":"26","name":"网络|通信|电子","description":"计算机/互联网/通信|电子/电气|机械/仪器仪表"},{"term_id":"27","name":"人力|行政|管理","description":"人事/行政/后勤|司机|高级管理"}]
     */

    private String status;
    /**
     * term_id : 26
     * name : 网络|通信|电子
     * description : 计算机/互联网/通信|电子/电气|机械/仪器仪表
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
        private String term_id;
        private String name;
        private String description;

        public String getTerm_id() {
            return term_id;
        }

        public void setTerm_id(String term_id) {
            this.term_id = term_id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }
}
