package com.millet.z_basic.base.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.millet.z_basic.R;
import com.millet.z_basic.base.dialog.base_dialog.BaseDialog;


/**
 * 普通的dialog，可直接显示view
 */
public class CommonDialog extends BaseDialog {

    public CommonDialog(@NonNull Context context) {
        this(context, 0);
    }

    public CommonDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected CommonDialog(@NonNull Context context, boolean cancelable, @Nullable DialogInterface.OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    public static class Builder {
        private Context context;
        private View mView;
        private int mResLayoutId = -1;
        private ConfirmDialog confirmDialog;

        public Builder(Context context) {
            this.context = context;
        }

        public ConfirmDialog create() {
            if (null == mView) {
                mView = LayoutInflater.from(context).inflate(mResLayoutId, null);
            }
            confirmDialog = new ConfirmDialog(context, R.style.DialogNoTitleStyle);
            ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            confirmDialog.addContentView(mView, layoutParams);
            return confirmDialog;
        }

        public View getView() {
            return mView;
        }
    }

}
