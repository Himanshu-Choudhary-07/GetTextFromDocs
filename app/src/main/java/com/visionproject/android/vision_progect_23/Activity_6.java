package com.visionproject.android.vision_progect_23;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class Activity_6 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_6);

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation_bar);
        helper_class.disableShiftMode(bottomNavigationView);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(3);
        menuItem.setChecked(true);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        Intent i2 = new Intent(Activity_6.this,MainActivity.class);
                        startActivity(i2);
                        return true;

                    case R.id.bott_click:
                        Intent i = new Intent(Activity_6.this,Activity_2.class);
                        startActivity(i);
                        return true;

                    case R.id.bott_gallery:
                        Intent i4 = new Intent(Activity_6.this,Activity_4.class);
                        startActivity(i4);
                        return true;

                    case R.id.about:
                        Intent i5 = new Intent(Activity_6.this,Activity_6.class);
                        startActivity(i5);
                        return true;
                }
                return false;

            }
        });
    }
}
