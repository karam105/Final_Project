package com.example.marc.final_project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Main2Activity extends AppCompatActivity
{
    EditText et;
    TextView visible_text;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        et = (EditText) findViewById(R.id.editText);
        visible_text = (TextView) findViewById(R.id.visible_text);

        et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {
                visible_text.setText(et.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {}
        });
    }

    public void finishClick(View view)
    {
        Intent intent = new Intent();
        intent.putExtra("TextValue", et.getText().toString());
        setResult(RESULT_OK, intent);
        finish();
    }
}
