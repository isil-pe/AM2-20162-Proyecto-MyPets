package com.isil.mypets.storage.entity;


import com.google.gson.annotations.SerializedName;
import com.isil.mypets.entity.ComentarioEntity;
import java.util.List;

public class ComentariosResponse {


    private int offset;

    private List<ComentarioEntity> data;

    @SerializedName("___class")
    private String type;

    @SerializedName("user-token")
    private String token;

    private Object nextPage;

    private int totalObjects;


    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public List<ComentarioEntity> getData() {
        return data;
    }

    public void setData(List<ComentarioEntity> data) {
        this.data = data;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
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
