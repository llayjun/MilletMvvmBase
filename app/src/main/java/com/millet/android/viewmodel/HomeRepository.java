package com.millet.android.viewmodel;

import androidx.lifecycle.MutableLiveData;

import com.millet.z_basic.base.SystemConst;
import com.millet.z_basic.base.mvvm.base.BaseModel;
import com.millet.z_basic.net.bean.BannerBean;
import com.millet.z_basic.net.bean.Resource;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * Created by leo
 * on 2019/10/15.
 */
public class HomeRepository extends BaseModel {

    public MutableLiveData<Resource<List<BannerBean>>> getBannerList() {
        MutableLiveData<Resource<List<BannerBean>>> liveData = new MutableLiveData<>();
        return observeGo(getApiService().getBanner(), liveData);
    }

    public MutableLiveData<Resource<File>> downFile(String destDir, String fileName) {
        MutableLiveData<Resource<File>> liveData = new MutableLiveData<>();
        return downLoadFile(getApiService().downloadFile(SystemConst.URLS.QQ_APK), liveData, destDir, fileName);
    }

    //断点下载
    public MutableLiveData<Resource<File>> downFile(String destDir, String fileName, long currentLength) {
        String range = "bytes=" + currentLength + "-";
        MutableLiveData<Resource<File>> liveData = new MutableLiveData<>();
        return downLoadFile(getApiService().downloadFile(SystemConst.URLS.QQ_APK, range), liveData, destDir, fileName, currentLength);
    }

    //上传文件
    public MutableLiveData<Resource<String>> upLoad(String sequence, Map<String, File> files) {
        MutableLiveData<Resource<String>> liveData = new MutableLiveData<>();
        return upLoadFile(SystemConst.URLS.DIFFERT_URL, sequence, files, liveData);
    }


}
