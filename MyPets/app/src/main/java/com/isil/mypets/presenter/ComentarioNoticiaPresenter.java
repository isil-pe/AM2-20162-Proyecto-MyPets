package com.isil.mypets.presenter;



import android.util.Log;

import com.isil.mypets.entity.ComentarioEntity;
import com.isil.mypets.storage.entity.ComentarioRaw;
import com.isil.mypets.storage.entity.ComentarioResponse;
import com.isil.mypets.storage.entity.ComentariosResponse;
import com.isil.mypets.storage.request.ApiClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Alexander on 29/11/2016.
 */
public class ComentarioNoticiaPresenter {

    private static final String TAG = "NoticePresenter";
    private final String ERROR_MESSAGE= "Ocurriò un error";

    private static final String URL = "/v1/data/ComentarioNoticia";
    private ComentarioNoticiaView comentarioNoticiaView;

    public   void attachedView(ComentarioNoticiaView comentarioNoticiaView){
        this.comentarioNoticiaView = comentarioNoticiaView;
    }

    public  void detachView(){
        this.comentarioNoticiaView=null;
    }

    private void comentarioError(String messageError){
    }

    public void cargarComentariosNoticia(String id){
        Call<ComentariosResponse> call= ApiClient.getMyApiClient().obtenerComentariosNoticia(URL + "?where=id%3D%27" + id+"%27");

        call.enqueue(new Callback<ComentariosResponse>() {
            @Override
            public void onResponse(Call<ComentariosResponse> call, Response<ComentariosResponse> response) {

                if (response.isSuccessful()) {
                    ComentariosResponse r=response.body();
                    comentariosSuccessful(r);
                } else {
                }
            }

            private void comentariosSuccessful(ComentariosResponse r) {
                if (r != null){
                    List<ComentarioEntity> rEntities=r.getData();
                    comentarioNoticiaView.renderComentarios(rEntities);

                }
            }

            @Override
            public void onFailure(Call<ComentariosResponse> call, Throwable t) {
                String json = "Error";
                try {
                    json = new StringBuffer().append(t.getMessage()).toString();
                } catch (NullPointerException e) {
                }
                Log.v(TAG, "json Notice>>>>> " + json);

            }
        });
    }

    public void comentarNoticia(final ComentarioEntity entity){

        ComentarioRaw raw= new ComentarioRaw();
        raw.setId(entity.getId());
        raw.setIdUsuario(entity.getIdUsuario());
        raw.setAlias(entity.getAlias());
        raw.setComentario(entity.getComentario());

        Call<ComentarioResponse> call= ApiClient.getMyApiClient().comentarNoticia(raw);
        call.enqueue(new Callback<ComentarioResponse>() {
            @Override
            public void onResponse(Call<ComentarioResponse> call, Response<ComentarioResponse> response) {

                if (response.isSuccessful()) {
                    comentarioSuccessful(entity.getId());
                } else {

                }
            }

            @Override
            public void onFailure(Call<ComentarioResponse> call, Throwable t) {
                String json = "Error";
                try {
                    json = new StringBuffer().append(t.getMessage()).toString();
                } catch (NullPointerException e) {
                }
                Log.v(TAG, "json Notice>>>>> " + json);

            }
        });
    }



    private void comentarioSuccessful(String id) {
        cargarComentariosNoticia(id);
    }


}
