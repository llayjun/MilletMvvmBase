package com.millet.z_basic.net;


import com.millet.z_basic.net.bean.BannerBean;
import com.millet.z_basic.net.bean.ResponseModel;

import java.util.HashMap;
import java.util.List;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * Created by leo
 * on 2019/8/14.
 * Retrofit 接口请求配置都在这
 */
public interface RetrofitApiService {

    //wanAndroid的
    @GET("banner/json")
    Observable<ResponseModel<List<BannerBean>>> getBanner();


    //Retrofit get请求
    @GET("xiandu/category/wow")
    Observable<String> getGank(@Query("en_name") String en_name);


    //Retrofit post请求
    @POST("add2gank")
    @FormUrlEncoded
    Observable<ResponseBody> postAddGank(@FieldMap HashMap<String, String> map);

    //Retrofit 上传文件,前面的sequence是单表单@Part("sequence") RequestBody sequence
    // 多表单 @FieldMap Map<String, String> usermaps
    @POST
    @Multipart
    Observable<ResponseBody> uploadPic(@Url String url, @Part("sequence") RequestBody sequence, @Part MultipartBody.Part file);

    //Retrofit下载文件
    @GET
    @Streaming
    //10以上用@streaming。不会造成oom，反正你用就是了
    Observable<ResponseBody> downloadFile(@Url String url);

    @GET
    @Streaming
    Observable<ResponseBody> downloadFile(@Url String url, @Header("RANGE") String range);

}
