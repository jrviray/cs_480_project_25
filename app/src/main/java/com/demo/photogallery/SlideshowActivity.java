package com.demo.photogallery;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Glide.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by king on 5/23/17.
 */

public class SlideshowActivity extends AppCompatActivity {

    ArrayList<String> contents = new ArrayList<>();
    int transition;
    int duration;

    public ArrayList getContents(String path){
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
        final ImageView imageView = (ImageView) findViewById(R.id.iv_slideshow_photo);
        final TextView imageName = (TextView) findViewById(R.id.tv_photo_name);

        //Bundle bundle = getIntent().getExtras();
        transition = getIntent().getIntExtra("transition", 0);
        duration = getIntent().getIntExtra("duration", 0);
        contents = getIntent().getStringArrayListExtra("contents");

        // Photo[] mPhotos = Photo.getSpacePhotos(contents);
        // crashes at this line
        // Photo photo = Photo.getPhoto(mPhotos,2);

        ArrayList<File> files = getContents(getIntent().getStringExtra("path"));
        Uri imageUri = Uri.fromFile(files.get(2));
        Glide.with(this)
                //.load(contents.get(2).toString())
                .load(imageUri)
                //.placeholder(R.drawable.ic_error)
                .crossFade(1000)
                .into(imageView);
        Log.d("glide","past glide");

//        SlideshowAdapter slideshowAdapter = new SlideshowAdapter(this, Photo.getSpacePhotos(contents));
//        slideshowAdapter.loadPhoto(imageView, 2);
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

