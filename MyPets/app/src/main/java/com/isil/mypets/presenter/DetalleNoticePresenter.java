package com.isil.mypets.presenter;

import android.util.Log;

import com.isil.mypets.entity.NoticeEntity;
import com.isil.mypets.storage.entity.NoticeResponse;
import com.isil.mypets.storage.request.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetalleNoticePresenter {

    private static final String TAG = "NoticePresenter";
    private final String ERROR_MESSAGE= "Ocurriò un error";

    private static final String URL = "/v1/data/Noticia";
    private NoticeView noticeView;

    public   void attachedView(NoticeView noticeView){
        this.noticeView = noticeView;
    }

    public void findNotice(String id){
        Call<NoticeResponse> call= ApiClient.getMyApiClient().obtenerNoticia(URL + "/" + id);

        call.enqueue(new Callback<NoticeResponse>() {
            @Override
            public void onResponse(Call<NoticeResponse> call, Response<NoticeResponse> response) {

                if (response.isSuccessful()) {
                    NoticeResponse r=response.body();
                    noticeSuccessful(r);
                } else {
                }
            }

            @Override
            public void onFailure(Call<NoticeResponse> call, Throwable t) {
                String json = "Error";
                try {
                    json = new StringBuffer().append(t.getMessage()).toString();
                } catch (NullPointerException e) {
                }
                Log.v(TAG, "json Notice>>>>> " + json);

            }
        });
    }

    private void noticeSuccessful(NoticeResponse noticeResponse) {
        if (noticeResponse != null){
            NoticeEntity rEntities=new NoticeEntity();
            rEntities.setObjectId(noticeResponse.getObjectId());
            rEntities.setTitulo(noticeResponse.getTitulo());
            rEntities.setDescripcion(noticeResponse.getDescripcion());
            rEntities.setImagenURL(noticeResponse.getUrl());
            noticeView.renderNotice(rEntities);

        }
    }

    public  void detachView(){
        this.noticeView=null;
    }

    private void noticesError(String messageError){
    }
}
