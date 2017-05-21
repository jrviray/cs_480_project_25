package com.demo.photogallery;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.github.angads25.filepicker.controller.DialogSelectionListener;
import com.github.angads25.filepicker.model.DialogConfigs;
import com.github.angads25.filepicker.model.DialogProperties;
import com.github.angads25.filepicker.view.FilePickerDialog;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> contents = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button getDirectory = (Button) findViewById(R.id.b_get_directory);
        final TextView filePath = (TextView) findViewById(R.id.tv_directory_path);
        final Button showGallery = (Button) findViewById(R.id.b_view_gallery);
        final ListView itemContents = (ListView) findViewById(R.id.lv_file_contents);

        //initialize file chooser
        DialogProperties properties = new DialogProperties();

        properties.selection_mode = DialogConfigs.MULTI_MODE;
        properties.selection_type = DialogConfigs.DIR_SELECT;
        properties.root = new File(DialogConfigs.DEFAULT_DIR);
        properties.error_dir = new File(DialogConfigs.DEFAULT_DIR);
        properties.offset = new File(DialogConfigs.DEFAULT_DIR);
        properties.extensions = null;

        /**
         * Modifiable Fields for dialog
         * title
         * positive button
         * negative button
         */
        final FilePickerDialog dialog = new FilePickerDialog(MainActivity.this, properties);
        dialog.setTitle("Select a File");


        getDirectory.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dialog.show();

                dialog.setDialogSelectionListener(new DialogSelectionListener() {
                    @Override
                    public void onSelectedFilePaths(String[] files) {
                        contents = new ArrayList(Arrays.asList(files));
//                        files = files[0].split(", ");
//                        Log.d("Contents Array",contents.toString());
                    }
                });

                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, contents);
                itemContents.setAdapter(arrayAdapter);
            }
        });

        showGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent galleryIntent = new Intent(MainActivity.this, GalleryActivity.class);
                galleryIntent.putStringArrayListExtra("contents", contents);
                startActivity(galleryIntent);
            }
        });
    }



}


