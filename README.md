# MilletBase
Android基本库，持续更新

#### 相关代码
#### 拍照上传头衔带裁剪功能
```
CameraOrChooseDialog cameraOrChooseDialog = new CameraOrChooseDialog(MainActivity.this, 1);
cameraOrChooseDialog.show(getSupportFragmentManager(), "");

@Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                // 拍照
                case BaseConst.REQUEST_CODE_TAKE_PHOTO:
                    // CameraUtil.getImageFile(CameraUtil.mImageCacheName).getAbsolutePath()
                    UCrop.of(Uri.fromFile(CameraUtil.getImageFile(CameraUtil.mImageCacheName)), Uri.fromFile(new File(BaseConst.PIC_PATH + TimeUtils.getNowMills() + ".jpg")))
                            .withAspectRatio(1, 1)
                            .withMaxResultSize(300, 300)
                            .start(this);
                    break;
                // 选择照片
                case BaseConst.REQUEST_CODE_CHOOSE:
                    List<Uri> mSelected = Matisse.obtainResult(data);
                    UCrop.of(mSelected.get(0), Uri.fromFile(new File(BaseConst.PIC_PATH + TimeUtils.getNowMills() + ".jpg")))
                            .withAspectRatio(1, 1)
                            .withMaxResultSize(300, 300)
                            .start(this);
                    break;
                // 裁剪结果
                case UCrop.REQUEST_CROP:
                    Uri resultUri = UCrop.getOutput(data);
                    if (null != resultUri)

                        break;
            }
        }
    }
```

#### 扫描二维码功能
```
AndPermission.with(this)
                .runtime()
                .permission(Manifest.permission.CAMERA)
                .onGranted(data -> ARouter.getInstance().build(RouterBasePath.ROUTE_BASE_SCAN_CODE).navigation(MainActivity.this, ScanCodeActivity.SCAN_REQUEST_CODE))
                .onDenied(data -> ToastUtils.showShort("请确定打开相机权限！"))
                .start();

@Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == ScanCodeActivity.SCAN_REQUEST_CODE) {
                String info = data.getStringExtra(ScanCodeActivity.SCAN_DATA);
                if (!TextUtils.isEmpty(info)) {
                    ToastUtils.showShort(info);
                } else {
                    ToastUtils.showShort("请扫描正确的二维码");
                }
            }
        }
    }
```