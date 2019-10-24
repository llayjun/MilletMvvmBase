package com.millet.z_basic.ui.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.millet.z_basic.ui.view.glideapp.GlideApp;

import java.util.List;

public class ImagePageAdapter extends PagerAdapter {
    private List<String> urls;

    public ImagePageAdapter(List<String> urls) {
        this.urls = urls;
    }

    @Override
    public int getCount() {
        return urls != null ? urls.size() : 0;
    }


    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        ImageView imageView = new ImageView(container.getContext());
        GlideApp.with(container.getContext()).load(urls.get(position)).into(imageView);
        container.addView(imageView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        return container;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        View contentView = (View) object;
        container.removeView(contentView);
    }

}
