package com.millet.z_basic.base.mvp;

import android.os.Bundle;

/**
 * @author qinzishuai
 * 描述：
 * 创建日期：2019/7/12
 */
public interface IActivity {

    int getLayout();

    void initView();

    void initData(Bundle savedInstanceState);

    void loadData();
}
