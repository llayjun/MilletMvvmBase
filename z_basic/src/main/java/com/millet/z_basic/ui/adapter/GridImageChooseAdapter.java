package com.millet.z_basic.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.millet.z_basic.R;
import com.millet.z_basic.ui.view.glideapp.GlideApp;

import java.util.List;


/**
 * 图片选择后的Grid显示，微信9宫格
 * Created by zhangyinlei on 2018/3/2 0002.
 */

public class GridImageChooseAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static int TYPE_ADD = 0;//添加图片
    private static int TYPE_COMMON = 1;//普通图片展示

    private Context context;
    private LayoutInflater mLayoutInflater;

    //data
    private int mMaxAlbum;//最大选择图片的数量
    private List<String> mStringList;//图片url集合

    public GridImageChooseAdapter(Context context, List<String> mStringList, int maxAlbum) {
        this.context = context;
        this.mStringList = mStringList;
        this.mMaxAlbum = maxAlbum;
        this.mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ADD) {
            return new ItemViewHolderAdd(mLayoutInflater.inflate(R.layout.item_album_selected_add, parent, false));
        } else {
            return new ItemViewHolderCommon(mLayoutInflater.inflate(R.layout.item_album_selected_common, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        View itemView = null;
        if (holder instanceof ItemViewHolderAdd) {
            ItemViewHolderAdd itemViewHolderAdd = (ItemViewHolderAdd) holder;
            if (position >= mMaxAlbum) {
                itemViewHolderAdd.itemView.setVisibility(View.GONE);
            } else {
                itemViewHolderAdd.tvNum.setText(position + "/" + mMaxAlbum);
                itemViewHolderAdd.itemView.setVisibility(View.VISIBLE);
                itemView = ((ItemViewHolderAdd) holder).itemView;
                //添加图片功能
                if (mOnItemClickListener != null) {
                    itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mOnItemClickListener.onPickImageClick();
                        }
                    });
                }
            }
        } else if (holder instanceof ItemViewHolderCommon) {
            String url = mStringList.get(position);
            GlideApp.with(context).load(url).transform(new CenterCrop(), new RoundedCorners(10)).into(((ItemViewHolderCommon) holder).ivCommon);
            itemView = ((ItemViewHolderCommon) holder).itemView;
            //图片查看功能
            if (mOnItemClickListener != null) {
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mOnItemClickListener.onCommonItemClick(holder.itemView, position);
                    }
                });
            }
            //删除功能
            ((ItemViewHolderCommon) holder).ivRemove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mStringList.remove(position);
                    notifyDataSetChanged();
                }
            });
        }
    }

    @Override
    public int getItemViewType(int position) {
        return position == mStringList.size() ? TYPE_ADD : TYPE_COMMON;
    }

    @Override
    public int getItemCount() {
        return mStringList.size() + 1;//加一代表最后一个添加图片按钮
    }

    public static class ItemViewHolderAdd extends RecyclerView.ViewHolder {
        private TextView tvNum;

        public ItemViewHolderAdd(View itemView) {
            super(itemView);
            tvNum = itemView.findViewById(R.id.tv_album_selected_num);
        }
    }

    public static class ItemViewHolderCommon extends RecyclerView.ViewHolder {
        private ImageView ivCommon;
        private ImageView ivRemove;

        public ItemViewHolderCommon(View itemView) {
            super(itemView);
            ivCommon = itemView.findViewById(R.id.iv_album_selected);
            ivRemove = itemView.findViewById(R.id.iv_remove);
        }
    }

    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onCommonItemClick(View view, int position);

        void onPickImageClick();
    }

}
