package com.example.chy.challenge.findcommany.mine.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/12/3 0003.
 */

public class Submit_Resume_bean {

    /**
     * status : success
     * data : [{"jr_companyid":"1","jr_jobid":"4","jr_userid":"301","jr_create_time":"","applys":{"realname":"lala","myjob":"iOS","jobid":"4","userid":"301","jobtype":"技术专员/助理","work_property":"全职","title":"职位2","skill":"3个技能","salary":"2至2","experience":"应届生","education":"研究生","city":"云南省-迪庆藏族自治州","address":"The ","description_job":"1~2万","create_time":"","welfare":"","logo":"avatar20161124024739301.png","companyid":"1","company_name":"北京互联科技有限公司","company_web":"www.xiaocool.com","industry":"电子商务","count":"应届生","financing":"不限","authentication":"","company_score":"","distance":"","com_introduce":"","produte_info":""}}]
     */

    private String status;
    /**
     * jr_companyid : 1
     * jr_jobid : 4
     * jr_userid : 301
     * jr_create_time :
     * applys : {"realname":"lala","myjob":"iOS","jobid":"4","userid":"301","jobtype":"技术专员/助理","work_property":"全职","title":"职位2","skill":"3个技能","salary":"2至2","experience":"应届生","education":"研究生","city":"云南省-迪庆藏族自治州","address":"The ","description_job":"1~2万","create_time":"","welfare":"","logo":"avatar20161124024739301.png","companyid":"1","company_name":"北京互联科技有限公司","company_web":"www.xiaocool.com","industry":"电子商务","count":"应届生","financing":"不限","authentication":"","company_score":"","distance":"","com_introduce":"","produte_info":""}
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
        private String jr_companyid;
        private String jr_jobid;
        private String jr_userid;
        private String jr_create_time;
        /**
         * realname : lala
         * myjob : iOS
         * jobid : 4
         * userid : 301
         * jobtype : 技术专员/助理
         * work_property : 全职
         * title : 职位2
         * skill : 3个技能
         * salary : 2至2
         * experience : 应届生
         * education : 研究生
         * city : 云南省-迪庆藏族自治州
         * address : The
         * description_job : 1~2万
         * create_time :
         * welfare :
         * logo : avatar20161124024739301.png
         * companyid : 1
         * company_name : 北京互联科技有限公司
         * company_web : www.xiaocool.com
         * industry : 电子商务
         * count : 应届生
         * financing : 不限
         * authentication :
         * company_score :
         * distance :
         * com_introduce :
         * produte_info :
         */

        private ApplysBean applys;

        public String getJr_companyid() {
            return jr_companyid;
        }

        public void setJr_companyid(String jr_companyid) {
            this.jr_companyid = jr_companyid;
        }

        public String getJr_jobid() {
            return jr_jobid;
        }

        public void setJr_jobid(String jr_jobid) {
            this.jr_jobid = jr_jobid;
        }

        public String getJr_userid() {
            return jr_userid;
        }

        public void setJr_userid(String jr_userid) {
            this.jr_userid = jr_userid;
        }

        public String getJr_create_time() {
            return jr_create_time;
        }

        public void setJr_create_time(String jr_create_time) {
            this.jr_create_time = jr_create_time;
        }

        public ApplysBean getApplys() {
            return applys;
        }

        public void setApplys(ApplysBean applys) {
            this.applys = applys;
        }

        public static class ApplysBean {
            private String realname;
            private String myjob;
            private String jobid;
            private String userid;
            private String jobtype;
            private String work_property;
            private String title;
            private String skill;
            private String salary;
            private String experience;
            private String education;
            private String city;
            private String address;
            private String description_job;
            private String create_time;
            private String welfare;
            private String logo;
            private String companyid;
            private String company_name;
            private String company_web;
            private String industry;
            private String count;
            private String financing;
            private String authentication;
            private String company_score;
            private String distance;
            private String com_introduce;
            private String produte_info;

            public String getRealname() {
                return realname;
            }

            public void setRealname(String realname) {
                this.realname = realname;
            }

            public String getMyjob() {
                return myjob;
            }

            public void setMyjob(String myjob) {
                this.myjob = myjob;
            }

            public String getJobid() {
                return jobid;
            }

            public void setJobid(String jobid) {
                this.jobid = jobid;
            }

            public String getUserid() {
                return userid;
            }

            public void setUserid(String userid) {
                this.userid = userid;
            }

            public String getJobtype() {
                return jobtype;
            }

            public void setJobtype(String jobtype) {
                this.jobtype = jobtype;
            }

            public String getWork_property() {
                return work_property;
            }

            public void setWork_property(String work_property) {
                this.work_property = work_property;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getSkill() {
                return skill;
            }

            public void setSkill(String skill) {
                this.skill = skill;
            }

            public String getSalary() {
                return salary;
            }

            public void setSalary(String salary) {
                this.salary = salary;
            }

            public String getExperience() {
                return experience;
            }

            public void setExperience(String experience) {
                this.experience = experience;
            }

            public String getEducation() {
                return education;
            }

            public void setEducation(String education) {
                this.education = education;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public String getDescription_job() {
                return description_job;
            }

            public void setDescription_job(String description_job) {
                this.description_job = description_job;
            }

            public String getCreate_time() {
                return create_time;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
            }

            public String getWelfare() {
                return welfare;
            }

            public void setWelfare(String welfare) {
                this.welfare = welfare;
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

            public String getCom_introduce() {
                return com_introduce;
            }

            public void setCom_introduce(String com_introduce) {
                this.com_introduce = com_introduce;
            }

            public String getProdute_info() {
                return produte_info;
            }

            public void setProdute_info(String produte_info) {
                this.produte_info = produte_info;
            }
        }
    }
}
