package com.bupt.ikkott;

import android.view.View;

import com.bupt.ikkott.player.VideoPlayerIJK;

import tv.danmaku.ijk.media.player.IjkMediaPlayer;


public class VideoPlayer {

    private static boolean isPlay;
    private VideoPlayerIJK ijkPlayer;
    private String resource;

    public VideoPlayer(VideoPlayerIJK ijkPlayer, String resource) {
        this.ijkPlayer = ijkPlayer;
        this.resource = resource;
        isPlay = true;

        try {
            IjkMediaPlayer.loadLibrariesOnce(null);
            IjkMediaPlayer.native_profileBegin("libijkplayer.so");
        } catch (Exception e) {
            e.printStackTrace();
        }

        ijkPlayer.getMeasuredWidth();
        ijkPlayer.getMeasuredHeight();
        ijkPlayer.setVideoPath(resource);

        play();
        ijkPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPlay) {
                    pause();
                    isPlay = false;
                } else {
                    play();
                    isPlay = true;
                }
            }
        });
    }

    public void play() {
        ijkPlayer.start();
    }

    public void pause() {
        ijkPlayer.pause();
    }

}
