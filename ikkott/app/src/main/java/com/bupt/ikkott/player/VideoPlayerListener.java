package com.bupt.ikkott.player;

import android.content.Context;
import android.view.Gravity;
import android.view.SurfaceView;
import android.view.WindowManager;
import android.widget.FrameLayout;

import tv.danmaku.ijk.media.player.IMediaPlayer;

public class VideoPlayerListener implements IMediaPlayer.OnBufferingUpdateListener, IMediaPlayer.OnCompletionListener, IMediaPlayer.OnPreparedListener, IMediaPlayer.OnInfoListener, IMediaPlayer.OnVideoSizeChangedListener, IMediaPlayer.OnErrorListener, IMediaPlayer.OnSeekCompleteListener {
    private SurfaceView surfaceView;

    VideoPlayerListener(SurfaceView surfaceView) {
        this.surfaceView = surfaceView;
    }

    @Override
    public void onBufferingUpdate(IMediaPlayer iMediaPlayer, int i) {
        System.out.println(i);
    }

    @Override
    public void onCompletion(IMediaPlayer iMediaPlayer) {

    }

    @Override
    public boolean onError(IMediaPlayer iMediaPlayer, int i, int i1) {
        return false;
    }

    @Override
    public boolean onInfo(IMediaPlayer iMediaPlayer, int i, int i1) {
        return false;
    }

    @Override
    public void onPrepared(IMediaPlayer iMediaPlayer) {
        changeWidthAndHeight(iMediaPlayer);
    }

    @Override
    public void onSeekComplete(IMediaPlayer iMediaPlayer) {

    }

    @Override
    public void onVideoSizeChanged(IMediaPlayer iMediaPlayer, int i, int i1, int i2, int i3) {

    }

    public void changeWidthAndHeight(IMediaPlayer iMediaPlayer) {
        int videoWidth = iMediaPlayer.getVideoWidth();
        int videoHeight = iMediaPlayer.getVideoHeight();

        WindowManager wm = (WindowManager) surfaceView.getContext()
                .getSystemService(Context.WINDOW_SERVICE);
        int surfaceWidth = wm.getDefaultDisplay().getWidth();
        int surfaceHeight = wm.getDefaultDisplay().getHeight();

        float max;
        max = Math.max((float) videoWidth / (float) surfaceWidth, (float) videoHeight / (float) surfaceHeight);
        videoWidth = (int) Math.ceil((float) videoWidth / max);
        videoHeight = (int) Math.ceil((float) videoHeight / max);

        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(videoWidth, videoHeight);
        params.gravity = Gravity.CENTER;
        surfaceView.setLayoutParams(params);

    }
}
