package com.isil.mypets.presenter;

import android.content.Context;
import com.isil.mypets.entity.NoticeEntity;

public interface NoticeView {
    Context getContext();
    void renderNotice(NoticeEntity notice);
}
