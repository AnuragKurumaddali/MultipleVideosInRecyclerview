package com.raj.mvideosrv;

import android.app.Activity;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class MainActivity extends Activity implements View.OnClickListener {

    private Button btn_Click;
    private boolean isVideoDialogShown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initControls();
    }

    private void initControls() {
        btn_Click = findViewById(R.id.btn_Click);
        btn_Click.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_Click:
                if (!isVideoDialogShown)
                    showPreviewDialog();

                break;
            default:
        }
    }

    public void showPreviewDialog() {
        isVideoDialogShown = true;

        VideosDialog videosDialog = new VideosDialog(this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen, new DialogDismissListener() {
            @Override
            public void onDialogDismiss() {
                isVideoDialogShown = false;
            }
        });

        if (!videosDialog.isShowing()) {
            int dialogWidth, dialogHeight;
            dialogWidth = 1920;
            dialogHeight = 1200;
            Rect displayRectangle = new Rect();
            try {
                Window window = getWindow();
                window.getDecorView().getWindowVisibleDisplayFrame(displayRectangle);
                videosDialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE, WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);
                videosDialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
                videosDialog.getWindow().getAttributes().windowAnimations = R.style.EmergencyDialogAnimation;
                videosDialog.getWindow().setLayout(dialogWidth, dialogHeight);
                videosDialog.setCanceledOnTouchOutside(true);
                videosDialog.setCancelable(true);
                videosDialog.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
