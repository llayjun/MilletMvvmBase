package com.millet.z_basic.net.bean;

/**
 * Created by leo
 * on 2019/10/16.
 */
public class ParamsBuilder {
    //okhttp 在线缓存时间，不设置就是不用
    private int onlineCacheTime;
    //okhttp 离线缓存时间，不设置就是不用
    private int offlineCacheTime;
    //离开页面的时候 是否取消网络。( 默认是取消 )
    private boolean cancleNet = true;

    //重连次数，默认为0 不重连。大于0 开启重连
    private int  retryCount;

    //同一网络还在加载时，有且只能请求一次(默认可以请求多次)
    //同一网络，oneTag只能用一次
    private String oneTag;

    //是否显示加载loading （默认显示）
    private boolean isShowDialog = true;

    //加载进度条上是否显示文字（默认不显示文字）
    private String loadingMessage;


    public static ParamsBuilder build() {
        return new ParamsBuilder();
    }

    public ParamsBuilder loadingMessage(String loadingMessage){
        this.loadingMessage = loadingMessage;
        return this;
    }


    public ParamsBuilder isShowDialog(boolean isShowDialog){
        this.isShowDialog = isShowDialog;
        return this;
    }


    public ParamsBuilder oneTag(String oneTag){
        this.oneTag = oneTag;
        return this;
    }


    public ParamsBuilder isRetry(int retryCount){
        this.retryCount = retryCount;
        return this;
    }


    public ParamsBuilder cancleNet(boolean cancleNet){
        this.cancleNet = cancleNet;
        return this;
    }


    public ParamsBuilder offlineCacheTime(int offlineCacheTime){
        this.offlineCacheTime = offlineCacheTime;
        return this;
    }

    public ParamsBuilder onlineCacheTime(int onlineCacheTime){
        this.onlineCacheTime = onlineCacheTime;
        return this;
    }


    public int getOnlineCacheTime() {
        return onlineCacheTime;
    }

    public void setOnlineCacheTime(int onlineCacheTime) {
        this.onlineCacheTime = onlineCacheTime;
    }

    public int getOfflineCacheTime() {
        return offlineCacheTime;
    }

    public void setOfflineCacheTime(int offlineCacheTime) {
        this.offlineCacheTime = offlineCacheTime;
    }

    public boolean isCancleNet() {
        return cancleNet;
    }

    public void setCancleNet(boolean cancleNet) {
        this.cancleNet = cancleNet;
    }

    public int getRetryCount() {
        return retryCount;
    }

    public void setRetryCount(int retryCount) {
        this.retryCount = retryCount;
    }

    public String getOneTag() {
        return oneTag;
    }

    public void setOneTag(String oneTag) {
        this.oneTag = oneTag;
    }

    public boolean isShowDialog() {
        return isShowDialog;
    }

    public void setShowDialog(boolean showDialog) {
        isShowDialog = showDialog;
    }

    public String getLoadingMessage() {
        return loadingMessage;
    }

    public void setLoadingMessage(String loadingMessage) {
        this.loadingMessage = loadingMessage;
    }
}
