package com.millet.z_basic.ui.view.scan.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.millet.z_basic.R;
import com.millet.z_basic.base.SystemConst;
import com.millet.z_basic.base.RouterBasePath;
import com.millet.z_basic.base.mvp.BaseMvpActivity;
import com.millet.z_basic.base.mvp.BaseMvpPresenter;
import com.millet.z_basic.ui.view.album.AlbumUtil;
import com.millet.z_basic.ui.view.scan.fragment.CaptureFragment;
import com.millet.z_basic.ui.view.scan.util.CodeUtils;
import com.millet.z_basic.ui.view.scan.util.FileUtils;
import com.zhihu.matisse.Matisse;

import java.util.ArrayList;
import java.util.List;

@Route(path = RouterBasePath.ROUTE_BASE_SCAN_CODE)
public class ScanCodeActivity extends BaseMvpActivity implements View.OnClickListener {

    public static final int SCAN_REQUEST_CODE = 100;

    public static final String SCAN_DATA = "SCAN_DATA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CaptureFragment captureFragment = new CaptureFragment();
        CodeUtils.setFragmentArgs(captureFragment, R.layout.view_scan);
        captureFragment.setAnalyzeCallback(analyzeCallback);
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_my_container, captureFragment).commit();
    }

    @Override
    protected BaseMvpPresenter createPresenter() {
        return new BaseMvpPresenter();
    }

    CodeUtils.AnalyzeCallback analyzeCallback = new CodeUtils.AnalyzeCallback() {
        @Override
        public void onAnalyzeSuccess(Bitmap mBitmap, String result) {
            Intent intent = new Intent();
            intent.putExtra(SCAN_DATA, result);
            setResult(RESULT_OK, intent);
            finish();
        }

        @Override
        public void onAnalyzeFailed() {
        }
    };

    @Override
    public int getLayout() {
        return R.layout.activity_scan;
    }

    @Override
    public void initView() {
        TextView tvAlbum = findViewById(R.id.tv_album);
        tvAlbum.setOnClickListener(this);
        ImageView ivClose = findViewById(R.id.iv_close);
        ivClose.setOnClickListener(this);
    }

    @Override
    public void initData(Bundle savedInstanceState) {

    }

    @Override
    public void loadData() {

    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.tv_album) {
            AlbumUtil.ChooseAlbum(this, 1);
        } else if (id == R.id.iv_close) {
            finish();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                // 选择照片
                case SystemConst.REQUEST_CODE_CHOOSE:
                    List<Uri> mSelected = Matisse.obtainResult(data);
                    List<String> albumList = new ArrayList<>();
                    for (Uri uri : mSelected) {
                        albumList.add(FileUtils.getRealPath(uri));
                    }
                    if (albumList.size() == 1) {
                        CodeUtils.analyzeBitmap(albumList.get(0), new CodeUtils.AnalyzeCallback() {
                            @Override
                            public void onAnalyzeSuccess(Bitmap mBitmap, String result) {
                                Intent intent = new Intent();
                                intent.putExtra(SCAN_DATA, result);
                                setResult(RESULT_OK, intent);
                                finish();
                            }

                            @Override
                            public void onAnalyzeFailed() {

                            }
                        });
                    }
                    break;
            }
        }
    }

    @Override
    public void hideLoadingDialog() {

    }

    @Override
    public void showError(String message) {

    }
}
