package com.kolexia.www.simplemath;

import android.app.Dialog;
import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.SeekBar;
import android.widget.VideoView;

import java.util.ArrayList;

/**
 * Created by mike on 12/9/15.
 */
public class DialogVideo extends Dialog {
    Button btn_ok,btn_play,btn_pause,btn_stop;
    public static VideoView vw;
    public static SeekBar seekBar;
    public static boolean isPause,isPlay;
    public static int duration;
    public DialogVideo(Context context){
        super(context);
    }
    public void pause(){
        if(isPlay) {
            if (isPause) {
                btn_pause.setText("Pause");
                isPause = false;
                vw.start();
            } else {
                btn_pause.setText("Resume");
                isPause = true;
                vw.pause();
            }
        }
    }
    private Runnable onEverySecond=new Runnable() {

        @Override
        public void run() {

            if(seekBar != null) {
                seekBar.setProgress(vw.getCurrentPosition());
            }

            if(vw.isPlaying()) {
                seekBar.postDelayed(onEverySecond, 1000);
            }

        }
    };
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_video);
        isPause = false;
        isPlay = false;
        Log.i("test", "android.resource://" + getContext().getPackageName() + "/" + R.raw.testing);
        duration = 0;
        vw = (VideoView)findViewById(R.id.video_view);
        vw.setVideoURI(Uri.parse("android.resource://" + getContext().getPackageName() + "/" + R.raw.tugas));
        vw.setMediaController(new MediaController(getContext()));
        vw.requestFocus();
        seekBar = (SeekBar)findViewById(R.id.seekBar);
        vw.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                seekBar.setMax(vw.getDuration());
                seekBar.postDelayed(onEverySecond, 1000);
            }
        });
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {

                if (fromUser) {
                    // this is when actually seekbar has been seeked to a new position
                    vw.seekTo(progress);
                }
            }
        });
        btn_play = (Button)findViewById(R.id.btn_play);
        btn_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn_pause.setText("Pause");
                isPause = false;
                isPlay = true;
                vw.seekTo(duration);
                vw.start();
            }
        });
        btn_pause = (Button)findViewById(R.id.btn_pause);
        btn_pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pause();
            }
        });
        btn_stop = (Button)findViewById(R.id.btn_stop);
        btn_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vw.pause();
                vw.seekTo(0);
                btn_pause.setText("Pause");
                isPause = false;
                isPlay = false;
            }
        });
        btn_ok = (Button)findViewById(R.id.btn_ok);
        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogVideo.this.dismiss();
            }
        });
    }
}
