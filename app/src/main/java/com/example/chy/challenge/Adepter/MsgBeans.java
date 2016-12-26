package com.example.chy.challenge.Adepter;

import java.util.List;

/**
 * Created by SJL on 2016/12/23.
 */

public class MsgBeans {

    /**
     * status : success
     * data : [{"id":"6","uid":"301","chat_uid":"305","last_content":"哈喽","status":2,"last_chat_id":"89","create_time":"7天前","my_face":"avatar20161209092556301.png","my_nickname":"","other_face":null,"other_nickname":null},{"id":"14","uid":"301","chat_uid":"319","last_content":"我","status":2,"last_chat_id":"62","create_time":"14天前","my_face":"avatar20161209092556301.png","my_nickname":"","other_face":"avatar20161209105334319.png","other_nickname":""},{"id":"13","uid":"301","chat_uid":"307","last_content":"I'm so excited ","status":2,"last_chat_id":"51","create_time":"14天前","my_face":"avatar20161209092556301.png","my_nickname":"","other_face":"","other_nickname":""},{"id":"12","uid":"301","chat_uid":"614","last_content":"heloo111","status":2,"last_chat_id":"48","create_time":"21天前","my_face":"avatar20161209092556301.png","my_nickname":"","other_face":null,"other_nickname":null},{"id":"10","uid":"301","chat_uid":"317","last_content":"The best ","status":2,"last_chat_id":"33","create_time":"23天前","my_face":"avatar20161209092556301.png","my_nickname":"","other_face":"avatar20161128171155317.png","other_nickname":""},{"id":"5","uid":"301","chat_uid":"302","last_content":"12345","status":2,"last_chat_id":"12","create_time":"37天前","my_face":"avatar20161209092556301.png","my_nickname":"","other_face":null,"other_nickname":null}]
     */

    private String status;
    /**
     * id : 6
     * uid : 301
     * chat_uid : 305
     * last_content : 哈喽
     * status : 2
     * last_chat_id : 89
     * create_time : 7天前
     * my_face : avatar20161209092556301.png
     * my_nickname :
     * other_face : null
     * other_nickname : null
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
        private String uid;
        private String chat_uid;
        private String last_content;
        private int status;
        private String last_chat_id;
        private String create_time;
        private String my_face;
        private String my_nickname;
        private Object other_face;
        private Object other_nickname;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getChat_uid() {
            return chat_uid;
        }

        public void setChat_uid(String chat_uid) {
            this.chat_uid = chat_uid;
        }

        public String getLast_content() {
            return last_content;
        }

        public void setLast_content(String last_content) {
            this.last_content = last_content;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getLast_chat_id() {
            return last_chat_id;
        }

        public void setLast_chat_id(String last_chat_id) {
            this.last_chat_id = last_chat_id;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getMy_face() {
            return my_face;
        }

        public void setMy_face(String my_face) {
            this.my_face = my_face;
        }

        public String getMy_nickname() {
            return my_nickname;
        }

        public void setMy_nickname(String my_nickname) {
            this.my_nickname = my_nickname;
        }

        public Object getOther_face() {
            return other_face;
        }

        public void setOther_face(Object other_face) {
            this.other_face = other_face;
        }

        public Object getOther_nickname() {
            return other_nickname;
        }

        public void setOther_nickname(Object other_nickname) {
            this.other_nickname = other_nickname;
        }
    }
}
