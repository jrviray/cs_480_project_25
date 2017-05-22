package com.demo.photogallery;

import android.app.DialogFragment;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
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
import java.util.List;

public class MainActivity extends AppCompatActivity implements SlideshowDialogFragment.SlideshowDialogListener{

    ArrayList<String> contents = new ArrayList<>();

    /**
     * List the contents within a file
     *
     * @param path
     * @return
     */
    public ArrayList getContents(String path) {
        File parentDir = new File(path);
        ArrayList contents = new ArrayList<>();
        // accept only jpg files
        contents.addAll(Arrays.asList(parentDir.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.toLowerCase().endsWith(".jpg");
            }
        })));
        return contents;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * Buttons and fields that the user interacts with
         */
        final Button displayGallery = (Button) findViewById(R.id.b_view_a_gallery);
        final Button makeSlideshow = (Button) findViewById(R.id.b_make_a_slideshow);
        final DialogHelper dialogHelper = new DialogHelper();

        //initialize file chooser
        DialogProperties properties = new DialogProperties();
        // Config for multiple file selection
        properties.selection_mode = DialogConfigs.MULTI_MODE;
        properties.selection_type = DialogConfigs.DIR_SELECT;
        properties.root = new File(DialogConfigs.DEFAULT_DIR);
        properties.error_dir = new File(DialogConfigs.DEFAULT_DIR);
        properties.offset = new File(DialogConfigs.DEFAULT_DIR);
        properties.extensions = null;
        // set the dialog box
        final FilePickerDialog dialog = new FilePickerDialog(MainActivity.this, properties);
        dialog.setTitle("Select a File");

        // //initialize file chooser
        DialogProperties dirSelectProp = new DialogProperties();
        // Config for single directory selection
        // use getContents to get all file within directory
        dirSelectProp.selection_mode = DialogConfigs.DIR_SELECT;
        dirSelectProp.selection_type = DialogConfigs.DIR_SELECT;
        dirSelectProp.root = new File(DialogConfigs.DEFAULT_DIR);
        dirSelectProp.error_dir = new File(DialogConfigs.DEFAULT_DIR);
        dirSelectProp.offset = new File(DialogConfigs.DEFAULT_DIR);
        dirSelectProp.extensions = null;
        // set the dialog box
        final FilePickerDialog dirSelect = new FilePickerDialog(MainActivity.this, dirSelectProp);
        dialog.setTitle("Select a File");


        /**
         * Button listeners. Executes an action upon button press.
         */

        displayGallery.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dirSelect.show();
                dirSelect.setDialogSelectionListener(new DialogSelectionListener() {
                    @Override
                    public void onSelectedFilePaths(String[] files) {
                        contents = getContents(files[0].toString());
                        Intent galleryIntent = new Intent(MainActivity.this, GalleryActivity.class);
                        galleryIntent.putStringArrayListExtra("contents", contents);
                        startActivity(galleryIntent);
                    }
                });

            }
        });

        makeSlideshow.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
//                Intent openSlideshowDialog = new Intent(MainActivity.this, SlideshowDialogFragment.class);
//                startActivity(openSlideshowDialog);
//                dialogHelper.showSlideshowDialog();
                dialogHelper.showSlideshowDialog();

            }
        });

    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {
        
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {

    }

    private class DialogHelper extends FragmentActivity implements SlideshowDialogFragment.SlideshowDialogListener {

        public void showSlideshowDialog() {
            // Create an instance of the dialog fragment and show it
            DialogFragment dialog = new SlideshowDialogFragment();
            dialog.show(MainActivity.this.getFragmentManager(), "SlideshowFragmentDialog");
        }

        @Override
        public void onDialogPositiveClick(DialogFragment dialog) {
        }
        @Override
        public void onDialogNegativeClick(DialogFragment dialog) {
        }

    }


}


