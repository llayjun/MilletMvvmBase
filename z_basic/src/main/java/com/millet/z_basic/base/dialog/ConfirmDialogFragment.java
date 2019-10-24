package com.millet.z_basic.base.dialog;

import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.millet.z_basic.R;
import com.millet.z_basic.base.dialog.base_dialog.BaseDialogFragment;
import com.millet.z_basic.base.dialog.listener.OnNegativeClickListener;
import com.millet.z_basic.base.dialog.listener.OnPositiveClickListener;

/**
 * 确认和取消弹框功能dialog
 */
public class ConfirmDialogFragment extends BaseDialogFragment {

    private String title, content, cancelText, confirmText;
    private boolean showContent = false;
    private OnPositiveClickListener onPositiveClickListener;
    private OnNegativeClickListener onNegativeClickListener;

    public ConfirmDialogFragment() {
    }

    @Override
    public int getLayoutRes() {
        return R.layout.view_dialog_confirm;
    }

    public String getTitle() {
        return title;
    }

    public ConfirmDialogFragment setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getContent() {
        return content;
    }

    public ConfirmDialogFragment setContent(String content) {
        this.content = content;
        return this;
    }

    public ConfirmDialogFragment setConfirmClick(String confirmText, OnPositiveClickListener onPositiveClickListener) {
        this.confirmText = confirmText;
        this.onPositiveClickListener = onPositiveClickListener;
        return this;
    }

    public ConfirmDialogFragment setCancelClick(String cancelText, OnNegativeClickListener onNegativeClickListener) {
        this.cancelText = cancelText;
        this.onNegativeClickListener = onNegativeClickListener;
        return this;
    }

    @Override
    public void bindView(View v) {
        TextView tvTitle = (TextView) v.findViewById(R.id.tv_title);
        TextView tvContent = (TextView) v.findViewById(R.id.tv_content);
        TextView tvCancel = (TextView) v.findViewById(R.id.tv_cancel);
        TextView tvConfirm = (TextView) v.findViewById(R.id.tv_confirm);
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
                    dismiss();
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
                    dismiss();
                }
            });
        } else {
            tvConfirm.setVisibility(View.GONE);
        }
    }

}
