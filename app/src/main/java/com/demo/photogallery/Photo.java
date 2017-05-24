package com.demo.photogallery;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;


public class Photo implements Parcelable {

    private String filePath;


    public Photo(String path) {
        filePath = path;
    }

    protected Photo(Parcel in) {
        filePath = in.readString();
    }

    public static final Creator<Photo> CREATOR = new Creator<Photo>() {
        @Override
        public Photo createFromParcel(Parcel in) {
            return new Photo(in);
        }

        @Override
        public Photo[] newArray(int size) {
            return new Photo[size];
        }
    };

    public void setFilePath(String path) {
        filePath = path;
    }

    public String getFilePath() {
        return filePath;
    }

    public static Photo[] getSpacePhotos(ArrayList arrayList) {

        Photo[] photos = new Photo[arrayList.size()];
        for (int i = 0; i < photos.length; i++) {
            photos[i] = new Photo(arrayList.get(i).toString());
        }
        return photos;
    }

    public static Photo getPhoto(Photo[] photos, int pos){
        return photos[pos];
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(filePath);
    }
}
