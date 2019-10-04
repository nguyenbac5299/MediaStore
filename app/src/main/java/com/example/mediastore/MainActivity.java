package com.example.mediastore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.CursorLoader;

import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    ListView listSong;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        readMedia();
    }

    private void readMedia() {
        String[] projection={MediaStore.MediaColumns.DISPLAY_NAME,
                             MediaStore.MediaColumns.DATE_ADDED,
                             MediaStore.MediaColumns.MIME_TYPE};
        CursorLoader loader= new CursorLoader(
                MainActivity.this,
                        MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                        projection,null,null,null);
        Cursor cursor= loader.loadInBackground();
        while (cursor.moveToNext())
        {
            String name= cursor.getString(0);
            adapter.add(name);
        }
        cursor.close();
    }


    private void addControls() {
        listSong= findViewById(R.id.listSong);
        adapter= new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1);
        listSong.setAdapter(adapter);
    }
}
