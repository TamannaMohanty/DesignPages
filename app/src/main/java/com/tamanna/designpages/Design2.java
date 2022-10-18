package com.tamanna.designpages;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Design2 extends AppCompatActivity {


    EditText name , password;
    Button login;

    String username ="Tamanna Priyadarshini";
    String mypassword ="Spring";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_design2);

      name = findViewById(R.id.name);
      password=findViewById(R.id.pwd);
//      login=findViewById(R.id.login);




//      login.setOnClickListener(new View.OnClickListener() {
//          @Override
//          public void onClick(View view) {
//
//
//      }
//    });


}

    public void login(View view) {

        if(name.getText().toString().equals(username) && password.getText().toString().equals(mypassword))
        {
            Toast.makeText(Design2.this, "Login Successful", Toast.LENGTH_SHORT).show();

            Intent intent=new Intent(Design2.this,Design3.class);
            startActivity(intent);
        }

        else
        {

            Toast.makeText(Design2.this, "Enter Valid Name and Password", Toast.LENGTH_SHORT).show();





        }
    }
}