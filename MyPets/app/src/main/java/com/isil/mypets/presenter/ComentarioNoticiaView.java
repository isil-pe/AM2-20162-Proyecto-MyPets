package com.isil.mypets.presenter;

import com.isil.mypets.entity.ComentarioEntity;

import java.util.List;

/**
 * Created by Alexander on 29/11/2016.
 */
public interface ComentarioNoticiaView {

    void renderComentarios(List<ComentarioEntity> list);

}
