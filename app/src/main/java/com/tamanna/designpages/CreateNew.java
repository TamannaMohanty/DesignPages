package com.tamanna.designpages;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CreateNew extends AppCompatActivity {


    EditText newusername,newuserpwd;
    Button createnew,clear;

    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new);


        newusername=findViewById(R.id.name);
        newuserpwd=findViewById(R.id.pwd);
        createnew=findViewById(R.id.createnew);
        clear=findViewById(R.id.clear);

        preferences =getSharedPreferences("Userinfo",0);


                createnew.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        String newusernamevalue = newusername.getText().toString().trim();
                        String newpasswordvalue = newuserpwd.getText().toString().trim();

                        if (validateinfo(newusernamevalue,newpasswordvalue)) {


                            //open the file
                            SharedPreferences.Editor editor = preferences.edit();
                            //passing all theis in string.Passing key and value
                            editor.putString("newusername", newusernamevalue);
                            editor.putString("newuserpwd", newpasswordvalue);
                            //saving the value
                            editor.apply();


                        }
                        }
                }
                );



                clear.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        emptyField();
                    }
                });

            }

            public void emptyField() {

                newusername.setText("");
                newuserpwd.setText("");

            }


    private boolean validateinfo(String newusernamevalue,String newpasswordvalue) {

        if (newusernamevalue.length() == 0 && newpasswordvalue.length()==0 ){

            newusername.setError("Field cannot be empty");
            newuserpwd.setError("Field cannot be empty");

        }
       else if (newpasswordvalue.length()>6)
        {

            newuserpwd.setError("Please enter 6 characters only ");

        }
        return false;
    }

    }


        //public void createnew()

//        // Storing data into SharedPreferences
//        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref",MODE_PRIVATE);
//
//// Creating an Editor object to edit(write to the file)
//        SharedPreferences.Editor myEdit = sharedPreferences.edit();
//
//// Storing the key and its value as the data fetched from edittext
//        myEdit.putString("name", name.getText().toString());
//        myEdit.putInt("pwd", Integer.parseInt(pwd.getText().toString()));
//
//// Once the changes have been made,
//// we need to commit to apply those changes made,
//// otherwise, it will throw an error
//        myEdit.commit();
//
