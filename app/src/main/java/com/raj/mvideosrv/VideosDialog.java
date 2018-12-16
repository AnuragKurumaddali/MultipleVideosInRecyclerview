package com.raj.mvideosrv;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.widget.LinearLayout;

import java.io.File;
import java.util.Vector;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class VideosDialog extends Dialog {

    private DialogDismissListener dialogDismissListener;
    private Context context;
    private RecyclerView rv_Videos;
    private RecyclerVideoAdapter recyclerVideoAdapter;

    public VideosDialog(Context context, int themeResId,DialogDismissListener dialogDismissListener) {
        super(context, themeResId);
        this.context = context;
        this.dialogDismissListener = dialogDismissListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout ll_Dialog = (LinearLayout) getLayoutInflater().inflate(R.layout.dialog_videos, null);
        setContentView(ll_Dialog);

        initControls();
    }

    private void initControls() {
        rv_Videos = findViewById(R.id.rv_Videos);
        rv_Videos.setHasFixedSize(true);
        rv_Videos.setLayoutManager(new CustomLinearManager(getContext()));
        recyclerVideoAdapter = new RecyclerVideoAdapter(context,getTrailerVideos());
        rv_Videos.setAdapter(recyclerVideoAdapter);
    }

    public class CustomLinearManager extends LinearLayoutManager {

        private CustomLinearManager(Context context) {
            super(context, RecyclerView.VERTICAL, false);
        }

        @Override
        public boolean canScrollVertically() {
            return true;
        }

        @Override
        public boolean canScrollHorizontally() {
            return false;
        }
    }

    public Vector<TrailerDO> getTrailerVideos() {
        Vector<TrailerDO> vecTrailers = new Vector<>();
        File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/Temp/"+AppConstants.TRAILER_1+AppConstants.MP4);
        vecTrailers = addFileToTrailerVector(vecTrailers,file);
        file = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/Temp/"+AppConstants.TRAILER_2+AppConstants.MP4);
        vecTrailers = addFileToTrailerVector(vecTrailers,file);
        file = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/Temp/"+AppConstants.TRAILER_3+AppConstants.MP4);
        vecTrailers = addFileToTrailerVector(vecTrailers,file);

        return vecTrailers;
    }

    private Vector<TrailerDO> addFileToTrailerVector(Vector<TrailerDO> vecTrailers, File file){
        if (file.exists()) {
            TrailerDO trailerDO = new TrailerDO();
            trailerDO.setVideoPath(file.getAbsolutePath());
            vecTrailers.add(trailerDO);
        }
        return vecTrailers;
    }
}
