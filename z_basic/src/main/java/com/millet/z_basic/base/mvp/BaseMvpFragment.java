package com.millet.z_basic.base.mvp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.gyf.immersionbar.ImmersionBar;
import com.millet.z_basic.net.dialog.CustomProgress;
import com.trello.rxlifecycle2.components.support.RxFragment;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseMvpFragment<V extends IBaseMvpView, P extends BaseMvpPresenter<V>> extends RxFragment implements IBaseMvpView, IFragment {

    protected P mPresenter;

    protected View rootView;// 缓存Fragment view

    protected CustomProgress dialog;

    private Unbinder mUnbinder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(getLayout(), null);
        }
        ButterKnife.bind(this, rootView);
        // 缓存的rootView需要判断是否已经被加过parent，如果有parent需要从parent删除，要不然会发生这个rootView已经有parent的错误。
        ViewGroup parent = (ViewGroup) rootView.getParent();
        if (parent != null) {
            parent.removeView(rootView);
        }
        //  unbinder = ButterKnife.bind(this, rootView);
        if (mPresenter == null) {
            mPresenter = createPresenter();
        }
        mPresenter.attachMvpView((V) this);
        mPresenter.setActivity(getActivity());
        dialog = CustomProgress.show(getContext(), "加载中...", false, null);
        initImmersionBar();
        initView();
        initData(savedInstanceState);
        return rootView;
    }

    protected abstract P createPresenter();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
        if (mPresenter != null) {
            mPresenter.detachMvpView();
        }
    }

    /**
     * 初始化沉浸式
     * Init immersion bar.
     */
    protected void initImmersionBar() {
        //设置共同沉浸式样式
        ImmersionBar.with(this).init();
    }

    @Override
    public void showLoadingDialog() {
        if (null != dialog && !dialog.isShowing()) {
            dialog.show();
        }
    }

    @Override
    public void hideLoadingDialog() {
        if (null != dialog && dialog.isShowing()) {
            dialog.dismiss();
        }
    }

}
