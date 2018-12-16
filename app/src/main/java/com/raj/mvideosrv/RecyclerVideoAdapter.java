package com.raj.mvideosrv;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.VideoView;

import java.io.File;
import java.util.Vector;

import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by ANURAG on 17-11-2017.
 */

public class RecyclerVideoAdapter extends RecyclerView.Adapter<RecyclerVideoAdapter.RecyclerViewHolder> {

    private Vector<TrailerDO> vecVideos;
    private Context context;

    public RecyclerVideoAdapter(Context context, Vector<TrailerDO> vecVideos) {
        this.context = context;
        this.vecVideos = vecVideos;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater mInflater = LayoutInflater.from(context);
        View view = mInflater.inflate(R.layout.video_layout, parent, false);
        RecyclerViewHolder listHolder = new RecyclerViewHolder(view);
        return listHolder;
    }

    @Override
    public void onBindViewHolder(final RecyclerViewHolder holder, final int position) {
        final TrailerDO trailerDO = vecVideos.get(position);

        if (position == 0)
            holder.tv_Trailer.setText(context.getResources().getString(R.string.trailer_01));
        else if (position == 1)
            holder.tv_Trailer.setText(context.getResources().getString(R.string.trailer_02));
        else
            holder.tv_Trailer.setText(context.getResources().getString(R.string.trailer_03));

        setVideoToView(trailerDO.getVideoPath(), holder.videoViewTrailer, holder.iv_PlayIcon,trailerDO);

        /*holder.fl_VideoViews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                *//*if (holder.videoViewTrailer != null && holder.videoViewTrailer.isPlaying())
                    pauseVideo(holder.videoViewTrailer, holder.iv_PlayIcon,trailerDO);
                else {
                    if(trailerDO.isStarted())
                        startVideo(holder.videoViewTrailer, holder.iv_PlayIcon, trailerDO);
                    else
                        resumeVideo(holder.videoViewTrailer, holder.iv_PlayIcon, trailerDO);
                }*//*
            }
        });*/
    }

    @Override
    public int getItemCount() {
        if (vecVideos != null && vecVideos.size() > 0)
            return vecVideos.size();
        return 0;
    }

    public void refresh(Vector<TrailerDO> vecVideos) {
        this.vecVideos = vecVideos;
        notifyDataSetChanged();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
//        public RelativeLayout fl_VideoViews;
        public TextView tv_Trailer;
        public VideoView videoViewTrailer;
        public LinearLayout iv_PlayIcon;

        public RecyclerViewHolder(View view) {
            super(view);
            // Find all views ids
//            this.fl_VideoViews = view.findViewById(R.id.fl_VideoViews);
            this.tv_Trailer = view.findViewById(R.id.tv_Trailer);
            this.videoViewTrailer = view.findViewById(R.id.videoViewTrailer);
            this.iv_PlayIcon = view.findViewById(R.id.iv_PlayIcon);
        }
    }

    private void setVideoToView(String filePath, final VideoView videoViewTrailer, final LinearLayout ivPlayIcon,TrailerDO trailerDO) {
        videoViewTrailer.setZOrderOnTop(true);
        File videoFile = new File(filePath);
        if (videoFile.exists()) {
            if (videoViewTrailer != null && videoViewTrailer.isPlaying())
                videoViewTrailer.stopPlayback();

            videoViewTrailer.setVideoPath(filePath);
            videoViewTrailer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mp.start();
                    mp.seekTo(5000);
                    mp.pause();
                }
            });
            trailerDO.setStarted(true);
        }
    }

    private void pauseVideo(VideoView videoViewTrailer, ImageView ivPlayIcon,TrailerDO trailerDO) {
        ivPlayIcon.setVisibility(View.VISIBLE);
        videoViewTrailer.pause();
        ivPlayIcon.bringToFront();
    }

    /*private void resumeVideo(VideoView videoViewTrailer, ImageView ivPlayIcon,TrailerDO trailerDO) {
        videoViewTrailer.start();
        ivPlayIcon.setVisibility(View.GONE);
        trailerDO.setStarted(false);
    }
    private void startVideo(VideoView videoViewTrailer, ImageView ivPlayIcon,TrailerDO trailerDO) {
        videoViewTrailer.seekTo(500);
        videoViewTrailer.start();
        ivPlayIcon.setVisibility(View.GONE);
        trailerDO.setStarted(false);
    }*/
}
