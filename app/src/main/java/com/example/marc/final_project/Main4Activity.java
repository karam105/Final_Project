package com.example.marc.final_project;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Main4Activity extends AppCompatActivity
{
    TextView user_font_text;

    int fontNumber = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        user_font_text = (TextView) findViewById(R.id.user_font_text);

        Intent intent = getIntent();
        String text = intent.getStringExtra("Text");
        user_font_text.setText(text);
    }

    public void boldText(View view)
    {
        Typeface custom_font = getResources().getFont(R.font.avenir_next_bold);
        user_font_text.setTypeface(custom_font);
        fontNumber = 1;
    }

    public void italicText(View view)
    {
        Typeface custom_font = getResources().getFont(R.font.avenir_next_italic);
        user_font_text.setTypeface(custom_font);
        fontNumber = 2;
    }

    public void regularText(View view)
    {
        Typeface custom_font = getResources().getFont(R.font.avenir_next_regular);
        user_font_text.setTypeface(custom_font);
        fontNumber = 3;
    }

    public void thinText(View view)
    {
        Typeface custom_font = getResources().getFont(R.font.avenir_next_thin);
        user_font_text.setTypeface(custom_font);
        fontNumber = 4;
    }

    public void OK(View view)
    {
        Intent intent = new Intent();
        Bundle extras = new Bundle();
        extras.putInt("Font", fontNumber);
        intent.putExtras(extras);
        setResult(RESULT_OK, intent);
        finish();
    }
}
