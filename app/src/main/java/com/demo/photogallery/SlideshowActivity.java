package com.demo.photogallery;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Looper;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Glide.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

/**
 * Created by king on 5/23/17.
 */

public class SlideshowActivity extends AppCompatActivity {

    ArrayList<String> contents = new ArrayList<>();
    int transition;
    int duration;
    Uri imageUri;

    public ArrayList getContents(String path) {
        File f = new File(path);
        ArrayList<File> files = new ArrayList<File>(Arrays.asList(f.listFiles()));
        return files;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slideshow);

        Log.d("slideshow", "were in the rigth activity");
        final ImageButton nextImage = (ImageButton) findViewById(R.id.b_right_arrow);
        final ImageButton prevImage = (ImageButton) findViewById(R.id.b_left_arrow);
        final ImageSwitcher sw = (ImageSwitcher) findViewById(R.id.iv_slideshow_photo);
        final TextView imageName = (TextView) findViewById(R.id.tv_photo_name);

        transition = getIntent().getIntExtra("transition", 0);
        duration = getIntent().getIntExtra("duration", 0);
        contents = getIntent().getStringArrayListExtra("contents");

        ArrayList<File> files = getContents(getIntent().getStringExtra("path"));
        imageUri = Uri.fromFile(files.get(2));

        sw.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                ImageView myView = new ImageView(getApplicationContext());
                myView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                myView.setLayoutParams(new
                        ImageSwitcher.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT,
                        ActionBar.LayoutParams.WRAP_CONTENT));
                return myView;
            }
        });

        Animation in = new AnimationUtils().loadAnimation(this, android.R.anim.fade_in);
        Animation out = new AnimationUtils().loadAnimation(this, android.R.anim.fade_out);

        sw.setInAnimation(in);
        sw.setOutAnimation(out);

        nextImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sw.setImageURI(imageUri);
            }
        });
//        Glide.with(this)
//                .load(imageUri)
//                .crossFade(1000)
//                .into(iv);


    }


//    private class SlideshowAdapter {
//
//        private Photo[] mPhotos;
//        private Context mContext;
//
//        public SlideshowAdapter(Context context, Photo[] photos) {
//            mContext = context;
//            mPhotos = photos;
//        }
//
//        public void getPhotos(ArrayList contents) {
//            mPhotos = Photo.getSpacePhotos(contents);
//        }
//
//        public int getItemCount() {
//            return (mPhotos.length);
//        }
//
//        public void loadPhoto(ImageView view, int position) {
//            Photo photo = mPhotos[position];
//
//
//        }
//
//    }

}

