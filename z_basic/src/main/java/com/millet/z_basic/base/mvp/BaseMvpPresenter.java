package com.millet.z_basic.base.mvp;

import android.app.Activity;

import java.lang.ref.WeakReference;


public class BaseMvpPresenter<V extends IBaseMvpView> {

    /**
     * v层泛型引用
     */
    protected Activity mActivity;

    protected V mView;

    private WeakReference<V> weakReferenceView;

    //防止空指针
    protected V getView() {
        if (mView == null) {
            throw new IllegalStateException("view not attached");
        } else {
            return mView;
        }
    }

    public void setActivity(Activity activity) {
        mActivity = activity;
    }

    public void attachMvpView(V view) {
        if (view != null) {
            weakReferenceView = new WeakReference<>(view);
            this.mView = weakReferenceView.get();
        }
    }

    public void detachMvpView() {
        weakReferenceView.clear();
        weakReferenceView = null;
        mView = null;
    }

}
