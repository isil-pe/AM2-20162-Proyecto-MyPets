package com.isil.mypets.presenter;


import android.support.annotation.NonNull;
import android.util.Log;

import com.isil.mypets.adapter.NoticeAdapter;
import com.isil.mypets.entity.NoticeEntity;
import com.isil.mypets.storage.entity.NoticesResponse;
import com.isil.mypets.storage.request.ApiClient;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NoticePresenter {

    private static final String TAG = "NoticePresenter";
    private final String ERROR_MESSAGE= "Ocurriò un error";

    private NoticesView noticesView;

    public   void attachedView(NoticesView noticesView){
        this.noticesView = noticesView;
    }

    public void lstNotices(){

        Call<NoticesResponse> call= ApiClient.getMyApiClient().lstNotices();
        call.enqueue(new Callback<NoticesResponse>() {
            @Override
            public void onResponse(Call<NoticesResponse> call, Response<NoticesResponse> response) {

                if(response.isSuccessful()){
                    NoticesResponse r=response.body();
                    noticeSuccessful(r);
                }else {
                }
            }

            @Override
            public void onFailure(Call<NoticesResponse> call, Throwable t) {
                String json = "Error";
                try{
                    json = new StringBuffer().append(t.getMessage()).toString();
                }catch (NullPointerException e) {}
                Log.v(TAG, "json Notice>>>>> " + json);

            }
        });
    }

    private void noticeSuccessful(NoticesResponse noticesResponse) {
        if (noticesResponse != null){
            List<NoticeEntity> rEntities=noticesResponse.getData();
            noticesView.renderNotices(rEntities);

        }
    }

    public  void detachView(){
        this.noticesView=null;
    }

    private void noticesError(String messageError){
    }


}
