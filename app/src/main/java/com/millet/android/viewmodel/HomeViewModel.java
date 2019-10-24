package com.millet.android.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.millet.z_basic.base.mvvm.base.BaseViewModel;
import com.millet.z_basic.net.bean.BannerBean;
import com.millet.z_basic.net.bean.Resource;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * Created by leo
 * on 2019/10/16.
 */
public class HomeViewModel extends BaseViewModel<HomeRepository> {

    public HomeViewModel(@NonNull Application application) {
        super(application);
    }

    @Override
    protected HomeRepository createRepository() {
        return new HomeRepository();
    }

    public LiveData<Resource<List<BannerBean>>> getBanner() {
        return getRepository().getBannerList();
    }

    public LiveData<Resource<File>> downFile(String destDir, String fileName) {
        return getRepository().downFile(destDir, fileName);
    }

    public LiveData<Resource<String>> uoLoad(String sequence, Map<String, File> files) {
        return getRepository().upLoad(sequence, files);
    }

}
