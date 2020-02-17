package com.example.aplikasifan;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    ToggleButton statusFan;
    ImageView gambarFan;
    ObjectAnimator animasiFan;
    Switch switchLigth;
    SeekBar speedFan;
    final int speed[] = {8000, 5000, 3000, 1000};
    GradientDrawable gd = new GradientDrawable();
    int index = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Animasi fan
        gambarFan = (ImageView)findViewById(R.id.imageView);
        animasiFan = ObjectAnimator.ofFloat(gambarFan, "rotation", 0, 360);
        animasiFan.setDuration(1000);
        animasiFan.setRepeatCount(ValueAnimator.INFINITE);
        animasiFan.setInterpolator(new LinearInterpolator());
        statusFan = (ToggleButton)findViewById(R.id.toggleButton);
        statusFan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(statusFan.isChecked()){
                    animasiFan.setDuration(speed[index]);
                    animasiFan.start();
                } else{
                    animasiFan.end();
                }
            }
        });

        // Animasi lampu
        gd.setShape(GradientDrawable.RECTANGLE);
        gd.setGradientType(GradientDrawable.RADIAL_GRADIENT);
        gd.setGradientRadius(330);

        switchLigth = (Switch)findViewById(R.id.switch1);
        switchLigth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(switchLigth.isChecked()){
                    gd.setColors(new int[]{ Color.MAGENTA, Color.TRANSPARENT });
                    gambarFan.setBackground(gd);
                } else{
                    gambarFan.setBackgroundColor(Color.TRANSPARENT);
                }
            }
        });

        speedFan = (SeekBar)findViewById(R.id.seekBar);
        speedFan.setMax(3);
        speedFan.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                  index = progress;
                  animasiFan.setDuration(speed[index]);
                  animasiFan.start();
//                if(progress > 80 && statusFan.isChecked()){
//                    animasiFan.setDuration(speed[3]);
//                    animasiFan.start();
//                } else if (progress > 40 && statusFan.isChecked()){
//                    animasiFan.setDuration(speed[2]);
//                    animasiFan.start();
//                } else if (progress > 1 && statusFan.isChecked()){
//                    animasiFan.setDuration(speed[1]);
//                    animasiFan.start();
//                } else{
//                    animasiFan.end();
//                }
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
    }

    protected void onRestoreInstanceState(Bundle savedState){
        super.onRestoreInstanceState(savedState);
    }
}
