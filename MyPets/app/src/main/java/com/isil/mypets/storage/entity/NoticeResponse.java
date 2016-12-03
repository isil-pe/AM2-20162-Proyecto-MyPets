package com.isil.mypets.storage.entity;

import com.google.gson.annotations.SerializedName;

public class NoticeResponse {


    @SerializedName("___class")
    private String type;

    @SerializedName("user-token")
    private String token;

    private String title;
    private String descripcion;
    private String url;
    private String objectId;


    public String getTitulo() {
        return title;
    }

    public void setTitulo(String titulo) {
        this.title = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }
}
