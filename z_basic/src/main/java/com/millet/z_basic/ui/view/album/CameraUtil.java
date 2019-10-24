package com.millet.z_basic.ui.view.album;

import android.Manifest;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;

import androidx.fragment.app.Fragment;

import com.blankj.utilcode.util.ToastUtils;
import com.millet.z_basic.base.SystemConst;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;

import java.io.File;
import java.util.List;


/**
 * Created by zhangyinlei on 2018/3/3 0003.
 */

public class CameraUtil {

    public static String CAMERA_IMAGE_NAME = "img.jpg";

    public static String mImageCacheName = getImageName();

    /**
     * Actviity拍照
     *
     * @param activity
     */
    public static void startCamera(final Activity activity) {
        AndPermission.with(activity)
                .runtime()
                .permission(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA)
                .onGranted(new Action<List<String>>() {
                    @Override
                    public void onAction(List<String> data) {
                        mImageCacheName = getImageName();
                        File file = getImageFile(mImageCacheName);
                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                            ContentValues contentValues = new ContentValues(1);
                            contentValues.put(MediaStore.Images.Media.DATA, file.getAbsolutePath());
                            Uri uri = activity.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
                            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                        } else {
                            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
                        }
                        intent.putExtra(MediaStore.Images.Media.ORIENTATION, 0);
                        activity.startActivityForResult(intent, SystemConst.REQUEST_CODE_TAKE_PHOTO);
                    }
                })
                .onDenied(new Action<List<String>>() {
                    @Override
                    public void onAction(List<String> data) {
                        ToastUtils.showShort("请打开相关权限！");
                    }
                })
                .start();
    }

    /**
     * Fragment拍照
     *
     * @param fragment
     */
    public static void startCamera(final Fragment fragment) {
        AndPermission.with(fragment)
                .runtime()
                .permission(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA)
                .onGranted(new Action<List<String>>() {
                    @Override
                    public void onAction(List<String> data) {
                        mImageCacheName = getImageName();
                        File file = getImageFile(mImageCacheName);
                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                            ContentValues contentValues = new ContentValues(1);
                            contentValues.put(MediaStore.Images.Media.DATA, file.getAbsolutePath());
                            Uri uri = fragment.getActivity().getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
                            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                        } else {
                            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
                        }
                        intent.putExtra(MediaStore.Images.Media.ORIENTATION, 0);
                        fragment.startActivityForResult(intent, SystemConst.REQUEST_CODE_TAKE_PHOTO);
                    }
                })
                .onDenied(new Action<List<String>>() {
                    @Override
                    public void onAction(List<String> data) {
                        ToastUtils.showShort("请打开相关权限！");
                    }
                })
                .start();
    }

    /**
     * @return 返回图片地址
     */
    public static File getImageFile(String name) {
        return new File(SystemConst.PIC_PATH, name);
    }

    /**
     * @return 返回图片的名称
     */
    private static String getImageName() {
        return System.currentTimeMillis() + CAMERA_IMAGE_NAME;
    }

    /**
     * @param activity
     * @param uri
     */
    public static void startPhotoZoom(Activity activity, Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        //这段代码判断，在安卓7.0以前版本是不需要的。特此注意。不然这里也会抛出异常
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("circleCrop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectY", 1);
        intent.putExtra("aspectX", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 150);
        intent.putExtra("outputY", 150);
//        intent.putExtra("scale",true);//自由截取
        intent.putExtra("return-data", true);
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        activity.startActivityForResult(intent, SystemConst.REQUEST_CODE_CROP_PHOTO);
    }

}
