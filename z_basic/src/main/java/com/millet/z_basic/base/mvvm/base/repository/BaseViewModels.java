//package com.millet.z_basic.base.mvvm.base;
//
//import android.app.Application;
//
//import androidx.annotation.NonNull;
//import androidx.lifecycle.AndroidViewModel;
//
//import com.millet.z_basic.base.mvvm.base.repository.BaseModels;
//import com.trello.rxlifecycle2.LifecycleTransformer;
//
//import java.util.ArrayList;
//
//import io.reactivex.disposables.CompositeDisposable;
//
//public abstract class BaseViewModels<T extends BaseModels> extends AndroidViewModel {
//    //这个是为了退出页面，取消请求的
//    public CompositeDisposable compositeDisposable;
//    private T repository;
//    private ArrayList<String> onNetTags;
//
//    protected abstract T createRepository();
//
//    public BaseViewModels(@NonNull Application application) {
//        super(application);
//        this.repository = createRepository();
//        compositeDisposable = new CompositeDisposable();
//        onNetTags = new ArrayList<>();
//    }
//
//    public void setObjectLifecycleTransformer(LifecycleTransformer objectLifecycleTransformer) {
//        repository.setObjectLifecycleTransformer(objectLifecycleTransformer);
//        repository.setCompositeDisposable(compositeDisposable);
//        repository.setOnNetTags(onNetTags);
//    }
//
//    public T getRepository() {
//        return repository;
//    }
//
//    @Override
//    protected void onCleared() {
//        super.onCleared();
//        //销毁后，取消当前页所有在执行的网络请求。
//        if (compositeDisposable != null) {
//            compositeDisposable.dispose();
//        }
//    }
//
//}
