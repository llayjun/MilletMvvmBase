package com.millet.z_basic.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.blankj.utilcode.util.ScreenUtils;
import com.github.chrisbanes.photoview.PhotoView;
import com.millet.z_basic.R;
import com.millet.z_basic.R2;
import com.millet.z_basic.base.RouterBasePath;
import com.millet.z_basic.base.dialog.ConfirmDialog;
import com.millet.z_basic.base.dialog.listener.OnPositiveClickListener;
import com.millet.z_basic.base.mvp.BaseMvpActivity;
import com.millet.z_basic.base.mvp.BaseMvpPresenter;
import com.millet.z_basic.ui.eventbus.EPreviewDeleteImage;
import com.millet.z_basic.ui.view.glideapp.GlideApp;
import com.millet.z_basic.ui.view.photoview.HackyViewPager;
import com.millet.z_basic.util.EventBusUtils;


import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 预览图片，可只查看，可删除，默认查看
 */
@Route(path = RouterBasePath.ROUTE_BASE_PREVIEW_IMAGE)
public class ImagePreviewActivity extends BaseMvpActivity {

    // 预览图片列表
    public static final String IMAGE_PREVIEW_LIST = "IMAGE_PREVIEW_LIST";

    // 点击的第几个预览的，默认第一个
    public static final String IMAGE_PREVIEW_LIST_POSITION = "IMAGE_PREVIEW_LIST_POSITION";

    // 是否可以删除，默认不可以删除
    public static final String IMAGE_PREVIEW_LIST_CAN_DELETE = "IMAGE_PREVIEW_LIST_CAN_DELETE";

    @BindView(R2.id.viewpager)
    HackyViewPager viewpager;
    @BindView(R2.id.iv_back)
    ImageView ivBack;
    @BindView(R2.id.iv_delete)
    ImageView ivDelete;
    @BindView(R2.id.tv_current)
    TextView tvCurrent;
    @BindView(R2.id.tv_total)
    TextView tvTotal;

    // data
    private List<String> pathList;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ScreenUtils.setFullScreen(this);
    }

    @Override
    protected BaseMvpPresenter createPresenter() {
        return new BaseMvpPresenter();
    }

    @Override
    public int getLayout() {
        return R.layout.activity_image_preview;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData(Bundle savedInstanceState) {
        if (null == getIntent()) return;
        pathList = getIntent().getStringArrayListExtra(IMAGE_PREVIEW_LIST);
        position = getIntent().getIntExtra(IMAGE_PREVIEW_LIST_POSITION, 0);
        boolean canDelete = getIntent().getBooleanExtra(IMAGE_PREVIEW_LIST_CAN_DELETE, false);
        if (canDelete) {
            ivDelete.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void loadData() {
        if (pathList != null && pathList.size() > 0) {
            if (pathList.size() == 1)
                findViewById(R.id.constraint_layout).setVisibility(View.GONE);
            tvCurrent.setText(String.format("%d", position + 1));
            tvTotal.setText(String.format("%d", pathList.size()));
            viewpager.setAdapter(new MyPagerAdapter());
            viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {
                    tvCurrent.setText(String.format("%d", position + 1));
                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });
            viewpager.setCurrentItem(position);
        }
    }

    @Override
    public void hideLoadingDialog() {

    }

    @Override
    public void showError(String message) {

    }

    @OnClick({R2.id.iv_back, R2.id.iv_delete})
    public void onViewClicked(View view) {
        int id = view.getId();
        if (id == R.id.iv_back) {
            finish();
        } else if (id == R.id.iv_delete) {
            new ConfirmDialog.Builder(this)
                    .setTitle("确定删除该图片")
                    .setCancelClick("取消", null)
                    .setConfirmClick("确定", new OnPositiveClickListener() {
                        @Override
                        public void onPositiveClick(View view, String content) {
                            EventBusUtils.post(new EPreviewDeleteImage(viewpager.getCurrentItem()));
                            finish();
                        }
                    }).create().show();
        }
    }

    private class MyPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return pathList.size();
        }

        @Override
        public View instantiateItem(ViewGroup container, int position) {
            PhotoView photoView = new PhotoView(container.getContext());
            GlideApp.with(ImagePreviewActivity.this).load(pathList.get(position)).into(photoView);
            container.addView(photoView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            return photoView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

    }
}
