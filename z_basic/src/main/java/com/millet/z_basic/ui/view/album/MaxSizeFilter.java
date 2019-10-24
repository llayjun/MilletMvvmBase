package com.millet.z_basic.ui.view.album;

import android.content.Context;
import android.graphics.Point;

import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.filter.Filter;
import com.zhihu.matisse.internal.entity.Item;
import com.zhihu.matisse.internal.entity.UncapableCause;
import com.zhihu.matisse.internal.utils.PhotoMetadataUtils;

import java.util.HashSet;
import java.util.Set;

public class MaxSizeFilter extends Filter {

    private int mMinWidth;
    private int mMinHeight;
    private int mMinSize;

    MaxSizeFilter(int minWidth, int minHeight, int minSizeInBytes) {
        mMinWidth = minWidth;
        mMinHeight = minHeight;
        mMinSize = minSizeInBytes;
    }

    @Override
    public Set<MimeType> constraintTypes() {
        return new HashSet<MimeType>() {{
            add(MimeType.JPEG);
            add(MimeType.PNG);
        }};
    }

    @Override
    public UncapableCause filter(Context context, Item item) {
        if (!needFiltering(context, item))
            return null;
        Point size = PhotoMetadataUtils.getBitmapBound(context.getContentResolver(), item.getContentUri());
        if (size.x < mMinWidth || size.y < mMinHeight) {
            return new UncapableCause(UncapableCause.TOAST, "该图片不存在");
        }
        return null;
    }


}
