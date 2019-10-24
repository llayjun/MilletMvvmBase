package com.millet.z_basic.base.dialog.base_dialog;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;

import androidx.annotation.FloatRange;


/**
 * PopupWindow 的基类
 */
public abstract class BasePopuWindow extends PopupWindow {
    private final float DEF_SHOW_ALPHA = 0.5f;
    private final float DEF_DISMISS_ALPHA = 1.0f;

    /* 显示时的屏幕背景透明度 */
    private float mShowAlpha;
    /* 关闭时的屏幕背景透明度 */
    private float mDisAlpha;
    private Context mContext;

    public BasePopuWindow(Context context) {
        super(context);
        this.mContext = context;
        init();
    }

    public BasePopuWindow(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        init();
    }

    public BasePopuWindow(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        init();
    }

    private void init() {
        mShowAlpha = DEF_SHOW_ALPHA;
        mDisAlpha = DEF_DISMISS_ALPHA;
    }

    /**
     * 设置弹窗宽高
     *
     * @param width  弹窗的宽度
     * @param height 弹窗的高度
     */
    public void setPopSize(int width, int height) {
        setWidth(width);
        setHeight(height);
    }

    /**
     * 点击空白处消失
     *
     * @param enabled {@code true}: 点击空白处消失<br>{@code false}: 点击空白处不消失
     */
    public void touchOutSideDismiss(boolean enabled) {
        setTouchable(enabled);
        setOutsideTouchable(enabled);
        setBackgroundDrawable(new BitmapDrawable());
    }

    /**
     * 设置弹窗显示时的背景透明度
     *
     * @param alpha 背景透明度
     */
    public void setShowAlpha(@FloatRange(from = 0.0, to = 1.0) float alpha) {
        this.mShowAlpha = alpha;
        setBgAlpha(mShowAlpha);
    }

    /**
     * 设置弹窗关闭时的背景透明度
     *
     * @param alpha 背景透明度
     */
    public void setDisAlpha(@FloatRange(from = 0.0, to = 1.0) float alpha) {
        this.mDisAlpha = alpha;
    }

    /**
     * 设置屏幕的背景透明度
     *
     * @param alpha 背景透明度
     */
    private void setBgAlpha(@FloatRange(from = 0.0, to = 1.0) float alpha) {
        // 设置背景颜色变暗
        WindowManager.LayoutParams lp = ((Activity) mContext).getWindow().getAttributes();
        lp.alpha = alpha;
        ((Activity) mContext).getWindow().setAttributes(lp);
    }

    @Override
    public void dismiss() {
        setBgAlpha(mDisAlpha);
        super.dismiss();
    }

    /**
     * @param anchor
     * @param xOff
     * @param yOff
     * @return
     */
    public void showAsDropDownNew(View anchor, int xOff, int yOff) {
        //解决华为7.0以上popwindow位置错乱
        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.N) {
            int[] mLocation = new int[2];
            anchor.getLocationInWindow(mLocation);
            showAtLocation(anchor, Gravity.NO_GRAVITY, mLocation[0] + xOff, mLocation[1] + anchor.getHeight());
        } else {
            showAsDropDown(anchor, xOff, yOff);
        }
    }

    public Context getContext() {
        return mContext;
    }

    /**
     * 获取弹窗显示时的背景透明度
     *
     * @return 背景透明度
     */
    public float getShowAlpha() {
        return mShowAlpha;
    }

    /**
     * 获取弹窗关闭时的背景透明度
     *
     * @return 背景透明度
     */
    public float getDisAlpha() {
        return mDisAlpha;
    }
}
