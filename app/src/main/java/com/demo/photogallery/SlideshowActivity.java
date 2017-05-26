package com.demo.photogallery;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

import android.app.Activity;
import android.app.ActionBar.LayoutParams;
import android.os.Bundle;
import android.view.View;

/**
 * Created by king on 5/23/17.
 */

public class SlideshowActivity extends AppCompatActivity {

    ArrayList<String> contents = new ArrayList<>();
    int transitionIn;
    int transitionOut;
    int duration;
    int counter;
    Uri imageUri;
    String[] files;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slideshow);

        final ImageButton nextImage = (ImageButton) findViewById(R.id.b_right_arrow);
        final ImageButton prevImage = (ImageButton) findViewById(R.id.b_left_arrow);
        final ImageSwitcher sw = (ImageSwitcher) findViewById(R.id.iv_slideshow_photo);
        final TextView imageName = (TextView) findViewById(R.id.tv_photo_name);

        transitionIn = getIntent().getIntExtra("transitionIn", -1);
        transitionOut = getIntent().getIntExtra("transitionOut", -1);

        files = getIntent().getStringArrayExtra("files");
        contents = getContents(files);
        contents = getContentsAsStrings(contents);

        duration = getDuration(getIntent().getIntExtra("duration", -1));

        sw.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                ImageView myView = new ImageView(getApplicationContext());
                myView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                myView.setLayoutParams(new
                        ImageSwitcher.LayoutParams(LayoutParams.WRAP_CONTENT,
                        LayoutParams.WRAP_CONTENT));
                return myView;
            }
        });

        Animation in = new AnimationUtils().loadAnimation(this, getAnimation(transitionIn));
        Animation out = new AnimationUtils().loadAnimation(this, getAnimation(transitionOut));

        sw.setInAnimation(in);
        sw.setOutAnimation(out);

        counter = 0;
        imageUri = Uri.fromFile(new File(contents.get(counter)));

        sw.postDelayed(new Runnable() {
            public void run() {
                if (counter == contents.size()){
                    finish();
                }
                sw.setImageURI(imageUri);
                // length of each repition
                sw.postDelayed(this, duration*1000);
                counter++;
            }
        }, 1000);


        nextImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sw.setImageURI(imageUri);
            }
        });

//        sw.setImageURI(imageUri);

    }

    public int getAnimation(int x) {
        switch (x) {
            case 0:
                return R.anim.fade_in_animation;
            case 1:
                return R.anim.fade_out_animation;
            case 2:
                return R.anim.rotate_animation;
            case 3:
                return R.anim.sequential_animation;
            case 4:
                return R.anim.slide_bottom_left_animation;
            case 5:
                return R.anim.slide_bottom_right_animation;
            case 6:
                return R.anim.slide_top_left_animation;
            case 7:
                return R.anim.slide_top_right_animation;
            case 8:
                return R.anim.slide_left_animation;
            case 9:
                return R.anim.slide_right_animation;
            case 11:
                return R.anim.slide_down_animation;
            case 12:
                return R.anim.slide_up_animation;
            case 13:
                return R.anim.zoom_in_animation;
            case 14:
                return R.anim.zoom_out_animation;
            default:
                return -1;
        }
    }

    public int getDuration(int x){
        switch (x){
            case 0:
                return 1;
            case 1:
                return 3;
            case 2:
                return 5;
            case 3:
                return 7;
            case 4:
                return 10;
            default:
                return -1;
        }
    }

    public ArrayList getContents(String[] files) {
        ArrayList<File> contents = new ArrayList<>();
        for (int i = 0; i < files.length; i++) {
            File file = new File(files[i]);
            if (file.isDirectory()) {
                contents.addAll(Arrays.asList(file.listFiles()));
            } else {
                contents.add(file);
            }
        }
        return contents;
    }

    public ArrayList getContentsAsStrings(ArrayList arrayList){
        ArrayList<String> contents = new ArrayList<>();
        for (int i = 0; i < arrayList.size(); i ++){
            contents.add(arrayList.get(i).toString());
        }
        return contents;
    }


}

