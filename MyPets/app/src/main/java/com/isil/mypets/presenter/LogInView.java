package com.isil.mypets.presenter;

import android.content.Context;

import com.isil.mypets.entity.UserEntity;

/**
 * Created by em on 8/06/16.
 */
public interface LogInView {

    void showLoading();
    void hideLoading();
    Context getContext();

    void onMessageError(String message);
    void gotoMain(UserEntity userEntity);
}
