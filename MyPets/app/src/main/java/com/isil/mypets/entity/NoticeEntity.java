package com.isil.mypets.entity;

import java.io.Serializable;

/**
 * Created by Alumno-J on 23/11/2016.
 */
public class NoticeEntity implements Serializable{

    private String title;
    private String descripcion;
    private String imagenURL;
    private String objectId;
    private String token;

    public NoticeEntity() {    }

    public NoticeEntity(String title, String descripcion, String imagenURL, String objectId) {
        this.title = title;
        this.descripcion = descripcion;
        this.imagenURL = imagenURL;
        this.objectId = objectId;
    }

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

    public String getImagenURL() {
        return imagenURL;
    }

    public void setImagenURL(String imagenURL) {
        this.imagenURL = imagenURL;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
