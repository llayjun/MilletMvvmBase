package com.millet.z_basic.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.millet.z_basic.R;
import com.millet.z_basic.R2;
import com.millet.z_basic.base.SystemConst;
import com.millet.z_basic.base.RouterBasePath;
import com.millet.z_basic.base.mvp.BaseMvpActivity;
import com.millet.z_basic.base.mvp.BaseMvpPresenter;
import com.millet.z_basic.ui.view.glideapp.GlideApp;
import com.ms.banner.Banner;
import com.ms.banner.BannerConfig;
import com.ms.banner.Transformer;
import com.ms.banner.holder.BannerViewHolder;
import com.ms.banner.listener.OnBannerClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

@Route(path = RouterBasePath.ROUTER_BASE_GUIDE)
public class GuideActivity extends BaseMvpActivity {

    @BindView(R2.id.guide_banner)
    Banner guideBanner;

    // data
    private List<String> mList;

    @Override
    protected BaseMvpPresenter createPresenter() {
        return new BaseMvpPresenter();
    }

    @Override
    public int getLayout() {
        return R.layout.activity_guide;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData(Bundle savedInstanceState) {
        mList = new ArrayList<>();
    }

    @Override
    public void loadData() {
        mList.clear();
        mList.add(SystemConst.URLS.TestImageUrl);
        mList.add(SystemConst.URLS.TestImageUrl);
        mList.add(SystemConst.URLS.TestImageUrl);
        guideBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR)
                .setPages(mList, new CustomViewHolder())
                .setBannerAnimation(Transformer.Default)
                .setIndicatorGravity(BannerConfig.CENTER)
                .setLoop(false)
                .setAutoPlay(false)
                .setOnBannerClickListener(new OnBannerClickListener() {
                    @Override
                    public void onBannerClick(List datas, int position) {

                    }
                })
                .start();
    }

    private class CustomViewHolder implements BannerViewHolder<String> {

        @Override
        public View createView(Context context, int position, String data) {
            ImageView ivGuide = new ImageView(context);
            ivGuide.setScaleType(ImageView.ScaleType.FIT_XY);
            GlideApp.with(context).load(data).into(ivGuide);
            return ivGuide;
        }
    }

    @Override
    public void showLoadingDialog() {

    }

    @Override
    public void hideLoadingDialog() {

    }

    @Override
    public void showError(String message) {

    }

}
