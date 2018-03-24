package com.visionproject.android.vision_progect_23;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.text.TextBlock;
import com.google.android.gms.vision.text.TextRecognizer;

public class Activity_4 extends AppCompatActivity {

    private static final int SELECTED_PICTURE=1;

    ImageView imageView;
    Button lets_start;
    Button edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_4);

        imageView = (ImageView)findViewById(R.id.ImageView_22);
        lets_start = (Button)findViewById(R.id.button_22);

        lets_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent,SELECTED_PICTURE);
            }
        });

        edit = (Button)findViewById(R.id.edit_button);


        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation_bar);
        helper_class.disableShiftMode(bottomNavigationView);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(2);
        menuItem.setChecked(true);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        Intent i2 = new Intent(Activity_4.this,MainActivity.class);
                        startActivity(i2);
                        return true;

                    case R.id.bott_click:
                        Intent i = new Intent(Activity_4.this,Activity_2.class);
                        startActivity(i);
                        return true;

                    case R.id.bott_gallery:
                        Intent i4 = new Intent(Activity_4.this,Activity_4.class);
                        startActivity(i4);
                        return true;

                    case R.id.about:
                        Intent i5 = new Intent(Activity_4.this,Activity_6.class);
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

        switch (requestCode){
            case SELECTED_PICTURE:
                if (resultCode == RESULT_OK){
                    Uri uri = data.getData();
                    String[]Projection={MediaStore.Images.Media.DATA};

                    Cursor cursor = getContentResolver().query(uri, Projection, null, null, null);
                    cursor.moveToFirst();

                    int columnIndex = cursor.getColumnIndex(Projection[0]);
                    String filepath = cursor.getString(columnIndex);
                    cursor.close();

                    final Bitmap file = BitmapFactory.decodeFile(filepath);
                    Drawable d =new BitmapDrawable(file);

                    imageView.setBackground(d);

                    edit.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            TextRecognizer textRecognizer = new TextRecognizer.Builder(getApplicationContext()).build();

                            if(!textRecognizer.isOperational())
                                Log.e("ERROR","Detector dependencies not available");
                            else{
                                Frame frame = new Frame.Builder().setBitmap(file).build();
                                SparseArray<TextBlock> items = textRecognizer.detect(frame);
                                StringBuilder stringBuilder = new StringBuilder();
                                for(int i=0;i<items.size();++i){
                                    TextBlock item = items.valueAt(i);
                                    stringBuilder.append(item.getValue());
                                }
                                String hello = stringBuilder.toString();
                                Intent intent = new Intent(Activity_4.this,Activity_5.class);
                                intent.putExtra("data",hello);
                                startActivity(intent);
                            }
                        }
                    });
                }
                break;
        }

    }
}
