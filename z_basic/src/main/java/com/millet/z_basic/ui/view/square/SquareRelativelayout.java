
package com.millet.z_basic.ui.view.square;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

public class SquareRelativelayout extends RelativeLayout {

    public SquareRelativelayout(Context context) {
        super(context);
    }

    public SquareRelativelayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }

}
