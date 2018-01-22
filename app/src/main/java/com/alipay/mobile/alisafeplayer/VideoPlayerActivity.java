package com.alipay.mobile.alisafeplayer;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.aliyun.vodplayer.media.AliyunLocalSource;
import com.aliyun.vodplayer.media.AliyunVodPlayer;
import com.aliyun.vodplayer.media.IAliyunVodPlayer;

/**
 * Created by ckb on 18/1/21.
 */

public class VideoPlayerActivity extends AppCompatActivity {

    private AliyunVodPlayer mAliVodPlayer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_player);

        mAliVodPlayer = new AliyunVodPlayer(this);

        initPlayer();
        prepareAndStart(getIntent().getStringExtra("video"));
    }

    private void initPlayer() {
        SurfaceView surfceView = findViewById(R.id.video_player_surface);
        surfceView.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                holder.setKeepScreenOn(true);
                if (mAliVodPlayer != null) {
                    mAliVodPlayer.setSurface(holder.getSurface());
                }
            }

            @Override
            public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {
                if (mAliVodPlayer != null) {
                    mAliVodPlayer.surfaceChanged();
                }
            }

            @Override
            public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
                if (mAliVodPlayer != null) {
                    mAliVodPlayer.setSurface(null);
                }
            }
        });
    }

    private void prepareAndStart(String videoFile) {
        if (TextUtils.isEmpty(videoFile)) {
            finish();
            return;
        }
        AliyunLocalSource.AliyunLocalSourceBuilder asb = new AliyunLocalSource.AliyunLocalSourceBuilder();
        asb.setSource(videoFile);
        AliyunLocalSource localSource = asb.build();
        mAliVodPlayer.setOnPreparedListener(new IAliyunVodPlayer.OnPreparedListener() {
            @Override
            public void onPrepared() {

                mAliVodPlayer.start();
            }
        });
        mAliVodPlayer.prepareAsync(localSource);
    }

    public static void startPlayerActivity(Context context, String videoFile) {
        Intent it = new Intent(context, VideoPlayerActivity.class);
        it.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        it.putExtra("video", videoFile);
        context.startActivity(it);
    }
}
