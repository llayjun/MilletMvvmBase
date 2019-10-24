package com.millet.android.activity;

import android.os.Bundle;

import androidx.lifecycle.Observer;

import com.millet.android.R;
import com.millet.android.databinding.ActivityMainBinding;
import com.millet.android.activity.viewmodel.MainViewModel;
import com.millet.android.view.GlideImageLoader;
import com.millet.z_basic.base.mvvm.BaseMvvmActivity;
import com.millet.z_basic.net.bean.BannerBean;
import com.millet.z_basic.net.bean.Resource;
import com.youth.banner.BannerConfig;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseMvvmActivity<MainViewModel, ActivityMainBinding> {

    @Override
    public int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        initBanner();
    }

    @Override
    public void initData(Bundle savedInstanceState) {

    }

    @Override
    public void loadData() {
        mViewModel.getBanner().observe(this, new Observer<Resource<List<BannerBean>>>() {
            @Override
            public void onChanged(Resource<List<BannerBean>> listResource) {
                listResource.handler(new OnCallback<List<BannerBean>>() {
                    @Override
                    public void onSuccess(List<BannerBean> data) {
                        updateBanner(data);
                    }
                });
            }
        });
    }

    @Override
    public void showError(String message) {

    }

    private void updateBanner(List<BannerBean> data) {
        if (data == null || data.size() <= 0) {
            return;
        }
        List<String> urls = new ArrayList<>();
        List<String> titles = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            urls.add(data.get(i).getImagePath());
            titles.add(data.get(i).getTitle());
        }
        binding.banner.setBannerTitles(titles);
        binding.banner.setImages(urls);
        binding.banner.start();
    }

    private void initBanner() {
        binding.banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        binding.banner.setImageLoader(new GlideImageLoader());
    }

}
