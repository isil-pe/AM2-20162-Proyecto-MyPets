package com.isil.mypets.storage.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Alexander on 29/11/2016.
 */
public class ComentarioResponse {

    private int offset;

    @SerializedName("___class")
    private String type;

    @SerializedName("user-token")
    private String token;

    private String id;
    private String idUsuario;
    private String comentario;
    private String alias;
    private String objectId;

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
}
