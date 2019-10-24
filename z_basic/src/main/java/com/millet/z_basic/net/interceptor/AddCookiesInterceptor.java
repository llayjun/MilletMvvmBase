package com.millet.z_basic.net.interceptor;

import com.millet.z_basic.base.SystemConst;
import com.orhanobut.logger.Logger;
import com.tencent.mmkv.MMKV;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AddCookiesInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        final Request.Builder builder = chain.request().newBuilder();
        // 添加Cookie
        builder.addHeader("Cookie", MMKV.defaultMMKV().decodeString(SystemConst.MMKV_KEY.Cookies));
        Logger.d("我发送了cookie---------------" + MMKV.defaultMMKV().decodeString(SystemConst.MMKV_KEY.Cookies));
        return chain.proceed(builder.build());
    }

}
