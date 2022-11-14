package com.tamanna.designpages;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.io.IOException;


public class MyViewModel extends ViewModel {


 MutableLiveData<Bitmap> photoset = new MutableLiveData<Bitmap>();
 MutableLiveData<Uri> photoUri = new MutableLiveData<Uri>();


    public MutableLiveData<Bitmap> getPhotoset() {

        return photoset;
    }

    public void setPhotoset(Bitmap photo) {
      photoset.setValue(photo);

    }
    public MutableLiveData<Uri> getPhotoUri() {
        return photoUri;
    }

    public void setPhotoUri(Uri uri, Context context) {
        try {

            //getting bitmap from Uri
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(context.getContentResolver(), uri);
            photoset.setValue(bitmap);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
