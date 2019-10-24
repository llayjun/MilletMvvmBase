package com.millet.z_basic.base.dialog;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.millet.z_basic.R;
import com.millet.z_basic.base.dialog.base_dialog.BaseDialog;
import com.millet.z_basic.base.dialog.listener.OnNegativeClickListener;
import com.millet.z_basic.base.dialog.listener.OnPositiveClickListener;

/**
 * 确认和取消弹框功能dialog
 */
public class ConfirmDialog extends BaseDialog {

    public ConfirmDialog(@NonNull Context context) {
        super(context);
    }

    public ConfirmDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    public static class Builder {
        private Context context;
        private ConfirmDialog confirmDialog;
        private String title, content, cancelText, confirmText;
        private boolean showContent = false;
        private OnPositiveClickListener onPositiveClickListener;
        private OnNegativeClickListener onNegativeClickListener;

        public Builder(Context context) {
            this.context = context;
        }

        public String getTitle() {
            return title;
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public String getContent() {
            return content;
        }

        public Builder setContent(String content) {
            this.content = content;
            return this;
        }

        public Builder setConfirmClick(String confirmText, OnPositiveClickListener onPositiveClickListener) {
            this.confirmText = confirmText;
            this.onPositiveClickListener = onPositiveClickListener;
            return this;
        }

        public Builder setCancelClick(String cancelText, OnNegativeClickListener onNegativeClickListener) {
            this.cancelText = cancelText;
            this.onNegativeClickListener = onNegativeClickListener;
            return this;
        }

        public ConfirmDialog create() {
            View view = LayoutInflater.from(context).inflate(R.layout.view_dialog_confirm, null);
            TextView tvTitle = (TextView) view.findViewById(R.id.tv_title);
            TextView tvContent = (TextView) view.findViewById(R.id.tv_content);
            TextView tvCancel = (TextView) view.findViewById(R.id.tv_cancel);
            TextView tvConfirm = (TextView) view.findViewById(R.id.tv_confirm);
            if (!TextUtils.isEmpty(title)) {
                tvTitle.setText(title);
            } else {
                tvTitle.setVisibility(View.GONE);
            }
            if (!TextUtils.isEmpty(content)) {
                tvContent.setText(content);
            } else {
                tvContent.setVisibility(View.GONE);
            }
            if (!TextUtils.isEmpty(cancelText)) {
                tvCancel.setText(cancelText);
                tvCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (null != onNegativeClickListener)
                            onNegativeClickListener.onNegativeClick(v, "");
                        confirmDialog.dismiss();
                    }
                });
            } else {
                tvCancel.setVisibility(View.GONE);
            }
            if (!TextUtils.isEmpty(confirmText)) {
                tvConfirm.setText(confirmText);
                tvConfirm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (null != onPositiveClickListener)
                            onPositiveClickListener.onPositiveClick(v, "");
                        confirmDialog.dismiss();
                    }
                });
            } else {
                tvConfirm.setVisibility(View.GONE);
            }
            confirmDialog = new ConfirmDialog(context, R.style.DialogNoTitleStyle);
            ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            confirmDialog.addContentView(view, layoutParams);
            return confirmDialog;
        }
    }

}
