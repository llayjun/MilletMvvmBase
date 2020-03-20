package com.millet.z_basic.base.mvvm;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.TextUtils;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.ViewModelProviders;

import com.blankj.utilcode.util.AdaptScreenUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.google.gson.JsonSyntaxException;
import com.gyf.immersionbar.ImmersionBar;
import com.millet.z_basic.R;
import com.millet.z_basic.base.mvp.IActivity;
import com.millet.z_basic.base.mvp.IBaseMvpView;
import com.millet.z_basic.base.mvvm.base.BaseViewModel;
import com.millet.z_basic.net.bean.Resource;
import com.millet.z_basic.net.dialog.CustomProgress;
import com.millet.z_basic.util.networks.NetWorkUtils;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.ConnectException;
import java.net.SocketTimeoutException;

public abstract class BaseMvvmActivity<VM extends BaseViewModel, VDB extends ViewDataBinding> extends RxAppCompatActivity implements IBaseMvpView, IActivity {

    protected VM mViewModel;

    protected VDB binding;

    protected CustomProgress dialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, getLayout());
        binding.setLifecycleOwner(this);
        initImmersionBar();
        createViewModel();
        initView();
        initData(savedInstanceState);
        loadData();
    }

    public void createViewModel() {
        if (mViewModel == null) {
            Class modelClass;
            Type type = getClass().getGenericSuperclass();
            if (type instanceof ParameterizedType) {
                modelClass = (Class) ((ParameterizedType) type).getActualTypeArguments()[0];
            } else {
                // 如果没有指定泛型参数，则默认使用BaseViewModel
                modelClass = BaseViewModel.class;
            }
            mViewModel = (VM) ViewModelProviders.of(this).get(modelClass);
            mViewModel.setObjectLifecycleTransformer(bindLifecycle());
        }
    }

    public LifecycleTransformer bindLifecycle() {
        return bindToLifecycle();
    }

    /**
     * 初始化沉浸式
     * Init immersion bar.
     */
    protected void initImmersionBar() {
        //设置共同沉浸式样式
        ImmersionBar.with(this).init();
    }

    /**
     * 今日头条适配方式
     *
     * @return
     */
    @Override
    public Resources getResources() {
        return AdaptScreenUtils.adaptWidth(super.getResources(), 1080);
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

    public Context getContext() {
        return this;
    }

    public abstract class OnCallback<T> implements Resource.OnHandleCallback<T> {
        @Override
        public void onLoading(String msg) {
            if (dialog == null) {
                dialog = CustomProgress.show(getContext(), "", true, null);
            }

            if (!TextUtils.isEmpty(msg)) {
                dialog.setMessage(msg);
            }

            if (!dialog.isShowing()) {
                dialog.show();
            }
        }

        @Override
        public void onError(Throwable throwable) {
            if (!NetWorkUtils.isNetworkConnected(getContext())) {
                ToastUtils.showShort(R.string.result_network_error);
                return;
            }
            if (throwable instanceof ConnectException) {
                ToastUtils.showShort(R.string.result_server_error);
            } else if (throwable instanceof SocketTimeoutException) {
                ToastUtils.showShort(R.string.result_server_timeout);
            } else if (throwable instanceof JsonSyntaxException) {
                ToastUtils.showShort(R.string.result_json_error);
            } else {
                ToastUtils.showShort(R.string.result_empty_error);
            }
        }

        @Override
        public void onFailure(String msg) {
            ToastUtils.showShort(msg);
        }

        @Override
        public void onCompleted() {
            if (dialog != null && dialog.isShowing()) {
                dialog.dismiss();
            }
        }

        @Override
        public void onProgress(int precent, long total) {

        }
    }

}
