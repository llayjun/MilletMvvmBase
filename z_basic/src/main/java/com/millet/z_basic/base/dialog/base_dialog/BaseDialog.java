package com.millet.z_basic.base.dialog.base_dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.FloatRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StyleRes;

/**
 * Dialog 的基类
 */
public class BaseDialog extends Dialog {
    private final float DEF_SHOW_ALPHA = 0.5f;
    private final float DEF_DIS_ALPHA = 1f;

    /* 显示时的屏幕背景透明度 */
    private float mShowAlpha = DEF_SHOW_ALPHA;
    /* 关闭时的屏幕背景透明度 */
    private float mDisAlpha = DEF_DIS_ALPHA;

    protected Context mContext;

    public BaseDialog(@NonNull Context context) {
        super(context);
        this.mContext = context;
    }

    public BaseDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        this.mContext = context;
    }

    protected BaseDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        this.mContext = context;
    }


    /**
     * 设置弹框显示消失动画
     */
    public void setWindowAnim(@StyleRes int resId) {
        Window window = getWindow();
        if (null != window) {
            window.setWindowAnimations(resId);
        }
    }

    @Override
    public void show() {
        super.show();
        setBgAlpha(mShowAlpha);
    }

    @Override
    public void dismiss() {
        setBgAlpha(mDisAlpha);
        super.dismiss();
    }

    /**
     * 设置添加屏幕的背景透明度
     *
     * @param bgAlpha
     */
    private void setBgAlpha(@FloatRange(from = 0.0, to = 1.0) float bgAlpha) {
        Window window = ((Activity) mContext).getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        // 0.0-1.0
        lp.alpha = bgAlpha;
        window.setAttributes(lp);
    }

    public void setShowAlpha(float mShowAlpha) {
        this.mShowAlpha = mShowAlpha;
    }

    public void setDisAlpha(float mDisAlpha) {
        this.mDisAlpha = mDisAlpha;
    }
}
