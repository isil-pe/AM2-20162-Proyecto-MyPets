package com.isil.mypets.storage.entity;

import com.google.gson.annotations.SerializedName;
import com.isil.mypets.entity.NoticeEntity;
import java.util.List;

public class NoticesResponse {

    //private String message;

    private int offset;

    private List<NoticeEntity> data;


    @SerializedName("___class")
    private String type;

    @SerializedName("user-token")
    private String token;


    private Object nextPage;

    private int totalObjects;


   /* public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }*/

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public List<NoticeEntity> getData() {
        return data;
    }

    public void setData(List<NoticeEntity> data) {
        this.data = data;
    }

    public Object getNextPage() {
        return nextPage;
    }

    public void setNextPage(Object nextPage) {
        this.nextPage = nextPage;
    }

    public int getTotalObjects() {
        return totalObjects;
    }

    public void setTotalObjects(int totalObjects) {
        this.totalObjects = totalObjects;
    }
}
