package com.example.marc.final_project;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Main4Activity extends AppCompatActivity
{
    TextView user_font_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        user_font_text = (TextView) findViewById(R.id.user_font_text);
    }

    public void boldText(View view)
    {
        Typeface custom_font = getResources().getFont(R.font.avenir_next_bold);
        user_font_text.setTypeface(custom_font);
    }

    public void italicText(View view)
    {
        Typeface custom_font = getResources().getFont(R.font.avenir_next_italic);
        user_font_text.setTypeface(custom_font);
    }

    public void regularText(View view)
    {
        Typeface custom_font = getResources().getFont(R.font.avenir_next_regular);
        user_font_text.setTypeface(custom_font);
    }

    public void thinText(View view)
    {
        Typeface custom_font = getResources().getFont(R.font.avenir_next_thin);
        user_font_text.setTypeface(custom_font);
    }

    public void OK(View view)
    {
    }
}
