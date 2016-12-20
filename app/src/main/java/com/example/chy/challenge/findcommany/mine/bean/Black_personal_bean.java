package com.example.chy.challenge.findcommany.mine.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/12/3 0003.
 */

public class Black_personal_bean {

    /**
     * status : success
     * data : [{"id":"2","userid":"301","blackid":"301","create_time":"1479101945","status":"1","reason":"拉黑","type":"1","blacks":[{"userid":"301","logo":"avatar20161124024739301.png","companyid":"1","company_name":"北京互联科技有限公司","company_web":"www.xiaocool.com","industry":"电子商务","count":"应届生","financing":"不限","creat_time":"1479955609","authentication":"","company_score":"","distance":"","produte_info":"","com_introduce":""}]}]
     */

    private String status;
    /**
     * id : 2
     * userid : 301
     * blackid : 301
     * create_time : 1479101945
     * status : 1
     * reason : 拉黑
     * type : 1
     * blacks : [{"userid":"301","logo":"avatar20161124024739301.png","companyid":"1","company_name":"北京互联科技有限公司","company_web":"www.xiaocool.com","industry":"电子商务","count":"应届生","financing":"不限","creat_time":"1479955609","authentication":"","company_score":"","distance":"","produte_info":"","com_introduce":""}]
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
        private String id;
        private String userid;
        private String blackid;
        private String create_time;
        private String status;
        private String reason;
        private String type;
        /**
         * userid : 301
         * logo : avatar20161124024739301.png
         * companyid : 1
         * company_name : 北京互联科技有限公司
         * company_web : www.xiaocool.com
         * industry : 电子商务
         * count : 应届生
         * financing : 不限
         * creat_time : 1479955609
         * authentication :
         * company_score :
         * distance :
         * produte_info :
         * com_introduce :
         */

        private List<BlacksBean> blacks;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUserid() {
            return userid;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }

        public String getBlackid() {
            return blackid;
        }

        public void setBlackid(String blackid) {
            this.blackid = blackid;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getReason() {
            return reason;
        }

        public void setReason(String reason) {
            this.reason = reason;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public List<BlacksBean> getBlacks() {
            return blacks;
        }

        public void setBlacks(List<BlacksBean> blacks) {
            this.blacks = blacks;
        }

        public static class BlacksBean {
            private String userid;
            private String logo;
            private String companyid;
            private String company_name;
            private String company_web;
            private String industry;
            private String count;
            private String financing;
            private String creat_time;
            private String authentication;
            private String company_score;
            private String distance;
            private String produte_info;
            private String com_introduce;

            public String getUserid() {
                return userid;
            }

            public void setUserid(String userid) {
                this.userid = userid;
            }

            public String getLogo() {
                return logo;
            }

            public void setLogo(String logo) {
                this.logo = logo;
            }

            public String getCompanyid() {
                return companyid;
            }

            public void setCompanyid(String companyid) {
                this.companyid = companyid;
            }

            public String getCompany_name() {
                return company_name;
            }

            public void setCompany_name(String company_name) {
                this.company_name = company_name;
            }

            public String getCompany_web() {
                return company_web;
            }

            public void setCompany_web(String company_web) {
                this.company_web = company_web;
            }

            public String getIndustry() {
                return industry;
            }

            public void setIndustry(String industry) {
                this.industry = industry;
            }

            public String getCount() {
                return count;
            }

            public void setCount(String count) {
                this.count = count;
            }

            public String getFinancing() {
                return financing;
            }

            public void setFinancing(String financing) {
                this.financing = financing;
            }

            public String getCreat_time() {
                return creat_time;
            }

            public void setCreat_time(String creat_time) {
                this.creat_time = creat_time;
            }

            public String getAuthentication() {
                return authentication;
            }

            public void setAuthentication(String authentication) {
                this.authentication = authentication;
            }

            public String getCompany_score() {
                return company_score;
            }

            public void setCompany_score(String company_score) {
                this.company_score = company_score;
            }

            public String getDistance() {
                return distance;
            }

            public void setDistance(String distance) {
                this.distance = distance;
            }

            public String getProdute_info() {
                return produte_info;
            }

            public void setProdute_info(String produte_info) {
                this.produte_info = produte_info;
            }

            public String getCom_introduce() {
                return com_introduce;
            }

            public void setCom_introduce(String com_introduce) {
                this.com_introduce = com_introduce;
            }
        }
    }
}
