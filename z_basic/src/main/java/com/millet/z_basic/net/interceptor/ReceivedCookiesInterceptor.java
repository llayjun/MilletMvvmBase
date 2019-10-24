package com.millet.z_basic.net.interceptor;

import com.millet.z_basic.base.SystemConst;
import com.orhanobut.logger.Logger;
import com.tencent.mmkv.MMKV;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

public class ReceivedCookiesInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Response originalResponse = chain.proceed(chain.request());
        if (!originalResponse.headers("Set-Cookie").isEmpty()) {
            StringBuilder stringBuilder = new StringBuilder();
            // 解析Cookie
            for (String header : originalResponse.headers("Set-Cookie")) {
                stringBuilder.append(header);
                if (header.contains("JSESSIONID")) {
                    String cookie = header.substring(header.indexOf("JSESSIONID"), header.indexOf(";"));
                    MMKV.defaultMMKV().encode(SystemConst.MMKV_KEY.Cookies, cookie);
                }
            }
            Logger.d("cookie全部-----   " + stringBuilder.toString());
        }
        return originalResponse;
    }
}
