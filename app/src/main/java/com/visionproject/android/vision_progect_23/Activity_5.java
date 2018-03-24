package com.visionproject.android.vision_progect_23;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.google.cloud.translate.Translate;
import com.google.cloud.translate.Translation;
import com.google.cloud.translate.Translate.TranslateOption;
import com.google.cloud.translate.TranslateOptions;


import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Activity_5 extends AppCompatActivity {
    EditText editText;
    String string_one;
    private Button mButton;
    private TextView mTextView;
    private String translatedText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_5);

        Intent i2 = getIntent();
        string_one = i2.getStringExtra("data");

        editText = (EditText)findViewById(R.id.editText);
        editText.setText(string_one);

        mTextView = (TextView) findViewById(R.id.translateView);
        mButton = (Button) findViewById(R.id.translateButton);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editText.setVisibility(View.GONE);
                PopupMenu popupMenu = new PopupMenu(getApplicationContext(),view);
                popupMenu.getMenuInflater().inflate(R.menu.language,popupMenu.getMenu());

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {

                        switch (menuItem.getItemId()){
                            case R.id.hindi_language:
                                //translateTextWithOptions(string_one,"English", "Hindi");
                                editText.setVisibility(View.GONE);
                                mTextView.setVisibility(View.VISIBLE);
                               // mTextView.setText(translatedText);
                        }

                        return true;
                    }
                });
            }
        });


        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation_bar);
        helper_class.disableShiftMode(bottomNavigationView);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        Intent i2 = new Intent(Activity_5.this,MainActivity.class);
                        startActivity(i2);
                        return true;

                    case R.id.bott_click:
                        Intent i = new Intent(Activity_5.this,Activity_2.class);
                        startActivity(i);
                        return true;

                    case R.id.bott_gallery:
                        Intent i4 = new Intent(Activity_5.this,Activity_4.class);
                        startActivity(i4);
                        return true;

                    case R.id.about:
                        Intent i5 = new Intent(Activity_5.this,Activity_6.class);
                        startActivity(i5);
                        return true;
                }
                return false;

            }
        });


    }
}
