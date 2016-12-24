package com.example.chy.challenge.Findpersoanl.mine.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/12/23 0023.
 */
public class CompanyIndustry implements Serializable{

    /**
     * term_id : 68
     * name : 数据分析
     * description :
     */

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
