package com.tamanna.designpages;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Design3 extends AppCompatActivity {

   FloatingActionButton camera, gallery;
   TextView logout;

    // Define the pic id
    private static final int pic_id = 123;
    private static final int gallery_id = 507;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_design3);


     camera=findViewById(R.id.camera);
     gallery = findViewById(R.id.photo);

     logout =findViewById(R.id.logout);

        camera.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                askPermission();

//                    Intent cameraIntent = new Intent();
//                    cameraIntent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
//                   startActivityForResult(cameraIntent,pic_id);


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
        gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent("com.sec.android.app.myfiles.PICK_DATA");
                intent.putExtra("CONTENT_TYPE", "*/*");
                intent.addCategory(Intent.CATEGORY_DEFAULT);
                startActivityForResult(intent, gallery_id);

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

    private void openCamera() {
        Intent camera=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(camera,pic_id);
    }


}

//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (requestCode == pic_id) {
//
//
//        }
//        }
//}



