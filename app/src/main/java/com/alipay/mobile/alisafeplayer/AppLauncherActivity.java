package com.alipay.mobile.alisafeplayer;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;

import com.alipay.mobile.alisafeplayer.util.ApiHelper;
import com.alipay.mobile.alisafeplayer.util.AsyncHandler;
import com.alipay.mobile.alisafeplayer.util.EncryptedFileHelper;
import com.alipay.mobile.alisafeplayer.util.Storage;

/**
 * Created by ckb on 18/1/9.
 */

public class AppLauncherActivity extends Activity {


    private static final int REQUEST_WRITE_STORAGE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_launcher);

        if (checkPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE, REQUEST_WRITE_STORAGE)) {
            copyEncryptedFile();
            startMainActivity();
        }
    }

    private void startMainActivity() {
        getWindow().getDecorView().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(AppLauncherActivity.this, MainActivity.class));
                finish();
            }
        }, 1000);
    }

    public boolean checkPermission(String permission, int requestCode) {
        if (!hasPermission(permission)) {
            requestPermission(permission, requestCode);
            return false;
        }
        return true;
    }

    public boolean hasPermission(String permission) {
        if (ApiHelper.preM()) return true;
        return checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED;
    }

    @TargetApi(Build.VERSION_CODES.M)
    public void requestPermission(String permission, int requestCode) {
        if (shouldShowRequestPermissionRationale(permission)) {
            //TODO explain permission
        }
        requestPermissions(new String[]{permission}, requestCode);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_WRITE_STORAGE: {
                if (grantResults.length > 0) {
                    if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                        copyEncryptedFile();
                        startMainActivity();
                    } else {
                        finish();
                    }
                }
                break;
            }
            default: {
                break;
            }

        }
    }

    private void copyEncryptedFile() {
        AsyncHandler.post(new Runnable() {
            @Override
            public void run() {
                if (!Storage.getEncryptedFile().exists()) {
                    Storage.moveEncryptedToStorage(getAssets());
                }
            }
        });
    }

}