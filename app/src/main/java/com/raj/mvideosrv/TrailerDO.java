package com.raj.mvideosrv;

import java.io.Serializable;

public class TrailerDO implements Serializable {

    public String getVideoPath() {
        return videoPath;
    }

    public void setVideoPath(String videoPath) {
        this.videoPath = videoPath;
    }

    public boolean isVideoPaused() {
        return isVideoPaused;
    }

    public void setVideoPaused(boolean videoPaused) {
        isVideoPaused = videoPaused;
    }

    private String videoPath;
    private boolean isVideoPaused;

    public boolean isStarted() {
        return isStarted;
    }

    public void setStarted(boolean started) {
        isStarted = started;
    }

    private boolean isStarted;
}
