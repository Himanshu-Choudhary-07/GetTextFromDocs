package com.visionproject.android.vision_progect_23;

import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Activity_3 extends AppCompatActivity {
    Button b1;
    ImageView v1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);

        b1 = (Button)findViewById(R.id.click_button);
        v1 = (ImageView)findViewById(R.id.camera_image);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,0);
            }
        });


        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation_bar);
        helper_class.disableShiftMode(bottomNavigationView);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        Intent i2 = new Intent(Activity_3.this,MainActivity.class);
                        startActivity(i2);
                        return true;

                    case R.id.bott_click:
                        Intent i = new Intent(Activity_3.this,Activity_2.class);
                        startActivity(i);
                        return true;

                    case R.id.bott_gallery:
                        Intent i4 = new Intent(Activity_3.this,Activity_4.class);
                        startActivity(i4);
                        return true;

                    case R.id.about:
                        Intent i5 = new Intent(Activity_3.this,Activity_6.class);
                        startActivity(i5);
                        return true;
                }
                return false;

            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bitmap bitmap = (Bitmap)data.getExtras().get("data");
        v1.setImageBitmap(bitmap);
    }
}
