package com.millet.z_basic.base.mvp;


public interface IBaseMvpView {
    /**
     * 获取view层的dialog
     *
     * @return retuen
     */
    void showLoadingDialog();

    /***
     * 关闭view层的dialog
     */
    void hideLoadingDialog();

    /**
     * 显示错误消息
     *
     * @param message
     */
    void showError(String message);

}
