package com.example.marc.final_project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Main2Activity extends AppCompatActivity
{
    EditText et;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        et = (EditText) findViewById(R.id.editText);
    }

    public void finishClick(View view)
    {
        Intent intent = new Intent();
        intent.putExtra("TextValue", et.getText().toString());
        setResult(RESULT_OK, intent);
        finish();
    }
}
