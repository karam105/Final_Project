package com.example.marc.final_project;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

public class Main3Activity extends AppCompatActivity
{
    RelativeLayout text_color_group;
    RelativeLayout background_color_group;
    RelativeLayout titles;
    TextView user_color_text;

    SeekBar text_sbR;
    SeekBar text_sbG;
    SeekBar text_sbB;
    SeekBar text_alpha;
    private float text_seekR, text_seekG, text_seekB, text_seekA;

    SeekBar background_sbR;
    SeekBar background_sbG;
    SeekBar background_sbB;
    SeekBar background_alpha;
    private float background_seekR, background_seekG, background_seekB, background_seekA;

    int text_color;
    int background_color;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        text_color_group = (RelativeLayout) findViewById(R.id.text_color_group);
        background_color_group = (RelativeLayout) findViewById(R.id.background_color_group);
        titles = (RelativeLayout) findViewById(R.id.titles);
        user_color_text = (TextView) findViewById(R.id.user_color_text);

        text_sbR = (SeekBar) findViewById(R.id.text_red_bar);
        text_sbG = (SeekBar) findViewById(R.id.text_green_bar);
        text_sbB = (SeekBar) findViewById(R.id.text_blue_bar);
        text_alpha = (SeekBar) findViewById(R.id.text_alpha_bar);

        background_sbR = (SeekBar) findViewById(R.id.background_red_bar);
        background_sbG = (SeekBar) findViewById(R.id.background_green_bar);
        background_sbB = (SeekBar) findViewById(R.id.background_blue_bar);
        background_alpha = (SeekBar) findViewById(R.id.background_alpha_bar);

        text_seekA = text_alpha.getProgress();
        background_seekA = background_alpha.getProgress();

        Intent intent = getIntent();
        String text = intent.getStringExtra("Text");
        user_color_text.setText(text);

        text_sbR.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                text_seekR = progress;
                changeTextColor();
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });
        text_sbG.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                text_seekG = progress;
                changeTextColor();
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });
        text_sbB.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                text_seekB = progress;
                changeTextColor();
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });
        text_alpha.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                text_seekA = progress;
                changeTextColor();
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });


        background_sbR.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                background_seekR = progress;
                changeBackgroundColor();
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });
        background_sbG.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                background_seekG = progress;
                changeBackgroundColor();
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });
        background_sbB.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                background_seekB = progress;
                changeBackgroundColor();
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });
        background_alpha.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                background_seekA = progress;
                changeBackgroundColor();
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });
    }

    public void changeColor(View view)
    {
        text_color_group.setVisibility(View.VISIBLE);
        titles.setVisibility(View.VISIBLE);
        background_color_group.setVisibility(View.INVISIBLE);
    }

    public void backgroundChange(View view)
    {
        background_color_group.setVisibility(View.VISIBLE);
        titles.setVisibility(View.VISIBLE);
        text_color_group.setVisibility(View.INVISIBLE);
    }

    private void changeTextColor()
    {
        text_color = Color.argb(text_seekA /100, text_seekR /100, text_seekG /100, text_seekB /100);
        user_color_text.setTextColor(text_color);
    }

    private void changeBackgroundColor()
    {
        background_color = Color.argb(background_seekA /100,background_seekR /100, background_seekG /100, background_seekB /100);
        user_color_text.setBackgroundColor(background_color);
    }

    public void finished(View view)
    {
        Intent intent = new Intent();
        Bundle extras = new Bundle();
        extras.putString("ColorText", user_color_text.getText().toString());
        extras.putInt("ColorValue", text_color);
        extras.putInt("BackgroundValue", background_color);
        intent.putExtras(extras);
        setResult(RESULT_OK, intent);
        finish();
    }
}
