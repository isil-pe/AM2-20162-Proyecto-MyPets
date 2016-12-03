package com.isil.mypets.presenter;

import android.content.Context;

import com.isil.mypets.entity.NoticeEntity;

import java.util.List;


public interface NoticesView {
    Context getContext();
    void renderNotices(List<NoticeEntity> list);
}
