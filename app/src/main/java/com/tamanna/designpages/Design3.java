package com.tamanna.designpages;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.DataBindingUtil;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.imageview.ShapeableImageView;
import com.tamanna.designpages.databinding.ActivityDesign3Binding;
import com.tamanna.designpages.databinding.ActivityMainBinding;

public class Design3 extends AppCompatActivity {

    public static final int PICK_IMAGE = 100;

    ActivityDesign3Binding binding;

    //ViewModel Declaration
    MyViewModel myViewModel;

    // Define the pic id
    private static final int pic_id = 123;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityDesign3Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        myViewModel = new ViewModelProvider(this).get(MyViewModel.class);

        myViewModel.getPhotoUri().observe(this, new Observer<Uri>() {

            @Override
            public void onChanged(Uri uri) {

                binding.shapableimg.setImageURI(uri);
                Toast.makeText(Design3.this, "Image picked ", Toast.LENGTH_SHORT).show();

            }
        });
        myViewModel.getPhotoset().observe(this, new Observer<Bitmap>() {

            @Override
            public void onChanged(Bitmap bitmap) {

                binding.shapableimg.setImageBitmap(bitmap);
                Toast.makeText(Design3.this, "Image picked ", Toast.LENGTH_SHORT).show();

            }
        });


        binding.cam2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                askPermission();

            }
        });

        binding.camera.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                askPermission();


                Toast.makeText(Design3.this, "clicked", Toast.LENGTH_SHORT).show();
            }
        });

        //Logout

        binding.logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(Design3.this, "Logged Out", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(Design3.this, Design2.class);
                startActivity(intent);

            }
        });


        //Gallery

        binding.shapableimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent("com.sec.android.app.myfiles.PICK_DATA");
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                // pass the constant to compare it
                // with the returned requestCode
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);


            }
        });

        binding.photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                 create an instance of the
//                 intent of the type image
                Intent intent = new Intent("com.sec.android.app.myfiles.PICK_DATA");
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                // pass the constant to compare it
                // with the returned requestCode
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);

            }
        });
    }


    private void askPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, pic_id);

        } else {
            openCamera();
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            openCamera();
        } else {
            Toast.makeText(this, "Camera permission to required", Toast.LENGTH_SHORT).show();
        }
    }


    // this function is triggered when user
    // selects the image from the imageChooser
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // compare the resultCode with the
        // SELECT_PICTURE constant
        if (resultCode == RESULT_OK && requestCode == PICK_IMAGE) {


            // update the preview image in the layout
            myViewModel.setPhotoUri(data.getData(),this);

        } else if (requestCode == pic_id) {
            // BitMap is data structure of image file which store the image in memory
             Bitmap photo = (Bitmap) data.getExtras().get("data");

            myViewModel.setPhotoset(photo);


            // Set the image in imageview for display


        } else {
            Toast.makeText(this, "Default Dp", Toast.LENGTH_SHORT).show();
        }
    }

    private void openCamera() {

        Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(camera, pic_id);

    }
}





