package com.isil.mypets.entity;

import java.io.Serializable;

/**
 * Created by Alexander on 27/11/2016.
 */
public class ComentarioEntity implements Serializable{

    private String id;
    private String idUsuario;
    private String comentario;
    private String alias;
    private String objectId;
    private String token;

    public ComentarioEntity() {
    }

    public ComentarioEntity(String id, String idUsuario, String comentario,String alias, String objectId) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.comentario = comentario;
        this.alias = alias;
        this.objectId = objectId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
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
