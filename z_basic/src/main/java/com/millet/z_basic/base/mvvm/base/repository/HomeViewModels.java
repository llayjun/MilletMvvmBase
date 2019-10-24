//package com.millet.z_basic.base.mvvm.base.repository;
//
//import android.app.Application;
//
//import androidx.annotation.NonNull;
//import androidx.lifecycle.LiveData;
//
//import com.millet.z_basic.base.mvvm.base.BaseViewModels;
//import com.millet.z_basic.net.bean.BannerBean;
//import com.millet.z_basic.net.bean.Resource;
//
//import java.io.File;
//import java.util.List;
//import java.util.Map;
//
///**
// * Created by leo
// * on 2019/10/16.
// */
//public class HomeViewModels extends BaseViewModels<HomeRepositorys> {
//
//    public HomeViewModels(@NonNull Application application) {
//        super(application);
//    }
//
//    @Override
//    protected HomeRepositorys createRepository() {
//        return new HomeRepositorys();
//    }
//
//    public LiveData<Resource<List<BannerBean>>> getBanner() {
//        return getRepository().getBannerList();
//    }
//
//    public LiveData<Resource<File>> downFile(String destDir, String fileName) {
//        return getRepository().downFile(destDir, fileName);
//    }
//
//    public LiveData<Resource<String>> uoLoad(String sequence, Map<String, File> files) {
//        return getRepository().upLoad(sequence, files);
//    }
//
//}
