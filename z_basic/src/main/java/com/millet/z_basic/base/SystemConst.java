package com.millet.z_basic.base;

import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.SDCardUtils;
import com.millet.z_basic.BuildConfig;

public class SystemConst {

    // debug模式
    public static final boolean DEBUG = BuildConfig.DEBUG;

    // 自定义图片存储地址
    public static final String PIC_PATH = SDCardUtils.getSDCardPathByEnvironment() + "/" + AppUtils.getAppPackageName() + "/ImageCache/";

    /**
     * MMKV 键
     */
    public static class MMKV_KEY {
        public static final String Cookies = "Cookies";
    }


    // 选择相册模块，拍照模块，裁剪图片
    public static final int REQUEST_CODE_CHOOSE = 111;
    public static final int REQUEST_CODE_TAKE_PHOTO = 112;
    public static final int REQUEST_CODE_CROP_PHOTO = 113;

    public static class URLS {
        // 图片地址测试
        public static final String TestImageUrl = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1572331012&di=cbe6d508e4b7334d6684a6c7474bfd1a&imgtype=jpg&er=1&src=http%3A%2F%2Fk.zol-img.com.cn%2Fdcbbs%2F24868%2Fa24867190_01000.jpg";

        //域名
        public static final String DEFAULT_SERVER = "https://www.wanandroid.com/";
        public static final String QQ_APK = "https://greenvalley.oss-cn-shanghai.aliyuncs.com/greenvalley_release_apk";
        public static final String DIFFERT_URL = "https://ijixin.com/brain/rest/assessmentRecord/upload";
    }

}
