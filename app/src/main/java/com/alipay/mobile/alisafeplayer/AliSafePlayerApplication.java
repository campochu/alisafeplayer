package com.alipay.mobile.alisafeplayer;

import android.app.Application;
import android.util.Log;

import com.alipay.mobile.alisafeplayer.util.EncryptedFileHelper;
import com.alivc.player.VcPlayerLog;

/**
 * Created by ckb on 18/1/21.
 */

public class AliSafePlayerApplication extends Application {

    static {
        VcPlayerLog.enableLog();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("MY_SHA1",EncryptedFileHelper.getCertificateSHA1Fingerprint(this));
    }
}
