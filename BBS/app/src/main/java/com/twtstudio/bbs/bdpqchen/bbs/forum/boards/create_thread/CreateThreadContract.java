package com.twtstudio.bbs.bdpqchen.bbs.forum.boards.create_thread;

import android.os.Bundle;

import com.twtstudio.bbs.bdpqchen.bbs.commons.base.BasePresenter;
import com.twtstudio.bbs.bdpqchen.bbs.commons.base.BaseView;

/**
 * Created by bdpqchen on 17-5-27.
 */

interface CreateThreadContract {
    interface View extends BaseView{
        void onPublished();
        void onPublishFailed(String msg);
    }
    interface Presenter extends BasePresenter<View>{
        void doPublishThread(Bundle bundle);
    }
}
