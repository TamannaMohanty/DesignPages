package com.tamanna.designpages;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.imageview.ShapeableImageView;

public class Design3 extends AppCompatActivity {

 public static final int PICK_IMAGE = 100;

   FloatingActionButton camera, gallery;
   TextView logout;
   ShapeableImageView imageView;
   ImageView camera2;
    Uri imageUri;
    Bitmap photo;

    //ViewModel Declaration
    MyViewModel myViewModel;

    // Define the pic id
    private static final int pic_id = 123;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_design3);


     camera=findViewById(R.id.camera);
     camera2=findViewById(R.id.cam2);
     gallery = findViewById(R.id.photo);
     imageView=findViewById(R.id.shapableimg);

     logout =findViewById(R.id.logout);

        myViewModel = new ViewModelProvider(this).get(MyViewModel.class);

     camera2.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {

             askPermission();

         }
     });

        camera.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

              askPermission();


                Toast.makeText(Design3.this, "clicked", Toast.LENGTH_SHORT).show();
            }
        });

        //Logout

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(Design3.this, "Logged Out", Toast.LENGTH_SHORT).show();

                Intent intent=new Intent(Design3.this,Design2.class);
                startActivity(intent);

            }
        });



        //Gallery

        imageView.setOnClickListener(new View.OnClickListener() {
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

        gallery.setOnClickListener(new View.OnClickListener() {
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
                imageView.setImageBitmap(photo);
//                Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                startActivity(intent);


            }
        });
    }



    private void askPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CAMERA},pic_id);

        }
        else
        {
            openCamera();
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED)
        {
            openCamera();
        }
        else
        {
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
        if (resultCode == RESULT_OK && requestCode == PICK_IMAGE){

            // Get the url of the image from data
            imageUri = data.getData();
            // update the preview image in the layout

            imageView.setImageURI(imageUri);

            Toast.makeText(this, "Image picked ", Toast.LENGTH_SHORT).show();
        }

       else if (requestCode == pic_id) {
            // BitMap is data structure of image file which store the image in memory
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            // Set the image in imageview for display
            imageView.setImageBitmap(photo);

    }
       else {
            Toast.makeText(this, "Default Dp", Toast.LENGTH_SHORT).show();
       }
    }


    private void openCamera() {
        Intent camera=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(camera,pic_id);

    }
}




