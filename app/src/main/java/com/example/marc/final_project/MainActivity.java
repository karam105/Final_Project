package com.example.marc.final_project;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.icu.text.UnicodeSetSpanner;
import android.media.Image;
import android.os.Debug;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.nio.channels.SelectionKey;

public class MainActivity extends AppCompatActivity
{
    RelativeLayout move_group;

    ImageView view;
    String backgroundImageName;
    SeekBar seekSize;
    TextView user_text;
    Button button;

    SeekBar tiltChange;

    String temp;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        view = (ImageView) findViewById(R.id.whiteBackground);
        backgroundImageName = String.valueOf(view.getTag());
        user_text = (TextView) findViewById(R.id.user_text);
        seekSize = (SeekBar) findViewById(R.id.size_changer);
        move_group = (RelativeLayout) findViewById(R.id.move_Group);
        button = (Button) findViewById(R.id.add_text);

        tiltChange = (SeekBar) findViewById(R.id.tilt_changer);

        view.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event)
            {
                int myNewX = (int) event.getX();
                int myNewY = (int) event.getY();

                if(event.getAction() == MotionEvent.ACTION_UP)
                {
                    button.setVisibility(View.VISIBLE);
                    button.setX(myNewX - 35);
                    button.setY(myNewY + 10);
                }

                else
                {
                    button.setVisibility(View.INVISIBLE);
                }

                return true;
            }
        });


        seekSize.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progressChangedValue = 0;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
            {
                progressChangedValue = progress;
                if (progressChangedValue <= 18)
                {
                    user_text.setTextSize(18);
                }
                else
                {
                    user_text.setTextSize(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar)
            {
                if (progressChangedValue <= 18)
                {
                    Toast.makeText(MainActivity.this, "Text size has reached smallest value of 18", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(MainActivity.this, "Current text size is: " + progressChangedValue, Toast.LENGTH_SHORT).show();
                }
            }
        });

        tiltChange.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int tiltChangeValue = 0;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
            {
                tiltChangeValue = progress;
                user_text.setRotation(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar)
            {
                Toast.makeText(MainActivity.this, "Tilt Value: " + tiltChangeValue, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void menuClick(View view)
    {
        registerForContextMenu(view);
        openContextMenu(view);
    }


    static final int REQUEST_TEXT = 1;
    public void sendText(View view)
    {
        Intent intent = new Intent(this, Main2Activity.class);
        startActivityForResult(intent, REQUEST_TEXT);
    }

    public void SizeChange(View view)
    {
        move_group.setVisibility(View.GONE);
        seekSize.setVisibility(View.VISIBLE);
        tiltChange.setVisibility(View.GONE);
    }

    public void MoveChange(View view)
    {
        move_group.setVisibility(View.VISIBLE);
        seekSize.setVisibility(View.GONE);
        tiltChange.setVisibility(View.GONE);
    }


    static final int REQUEST_COLOR = 2;
    public void ColorChange(View view)
    {
        Intent colorIntent = new Intent(this, Main3Activity.class);
        colorIntent.putExtra("Text", temp);
        startActivityForResult(colorIntent, REQUEST_COLOR);
    }

    public void TiltChange(View view)
    {
        move_group.setVisibility(View.GONE);
        seekSize.setVisibility(View.GONE);
        tiltChange.setVisibility(View.VISIBLE);
    }


    static final int REQUEST_FONT = 4;
    public void FontChange(View view)
    {
        Intent fontIntent = new Intent(this, Main4Activity.class);
        fontIntent.putExtra("Text", temp);
        startActivityForResult(fontIntent, REQUEST_FONT);
    }

    public void moveLeft(View view)
    {
        user_text.setX(user_text.getX() - 10);
    }

    public void moveUp(View view)
    {
        user_text.setY(user_text.getY() - 10);
    }

    public void moveDown(View view)
    {
        user_text.setY(user_text.getY() + 10);
    }

    public void moveRight(View view)
    {
        user_text.setX(user_text.getX() + 10);
    }

    final int CONEXT_TAKE_PHOTO = 1;
    final int MENU_PLAIN_IMAGE = 2;

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo)
    {
        menu.setHeaderTitle("Options");
        menu.add(Menu.NONE, CONEXT_TAKE_PHOTO, Menu.NONE, "Take Photo");
        menu.add(Menu.NONE, MENU_PLAIN_IMAGE, Menu.NONE, "Plain Image");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case CONEXT_TAKE_PHOTO:
                takePhoto();
                return true;

            case MENU_PLAIN_IMAGE:
                if(backgroundImageName.equals("white"))
                {
                    view.setVisibility(View.VISIBLE);
                }
                return true;

            default:
                return super.onContextItemSelected(item);
        }
    }

    public static final int REQUEST_CAM = 3;
    private void takePhoto()
    {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (intent.resolveActivity(getPackageManager()) != null)
        {
            startActivityForResult(intent, REQUEST_CAM);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode == REQUEST_CAM)
        {
            if (resultCode == RESULT_OK)
            {
                Bundle extras = data.getExtras();
                Bitmap imageBitmap = (Bitmap) extras.get("data");
                view.setVisibility(View.VISIBLE);
                view.setImageBitmap(imageBitmap);
            }
        }

        if(requestCode == REQUEST_TEXT)
        {
            if(resultCode == RESULT_OK)
            {
                temp = data.getStringExtra("TextValue");
                user_text.setText(temp);
            }
        }

        if(requestCode == REQUEST_COLOR)
        {
            if(resultCode == RESULT_OK)
            {
                Bundle extras = data.getExtras();
                String text = extras.getString("ColorText");
                int color = extras.getInt("ColorValue");
                int background_color = extras.getInt("BackgroundValue");
                user_text.setText(text);
                user_text.setTextColor(color);
                user_text.setBackgroundColor(background_color);
            }
        }
    }
}
