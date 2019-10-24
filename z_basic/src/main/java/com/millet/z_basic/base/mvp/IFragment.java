package com.millet.z_basic.base.mvp;

import android.os.Bundle;


public interface IFragment {

    int getLayout();

    void initView();

    void initData(Bundle savedInstanceState);

    void loadData();
}
