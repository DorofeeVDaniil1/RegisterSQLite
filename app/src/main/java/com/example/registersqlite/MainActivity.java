package com.example.registersqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText username, password, repassword;
    Button signup, signin;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = (EditText) findViewById(R.id.username);
        password = (EditText)findViewById(R.id.password);
        repassword = (EditText)findViewById(R.id.repassword);
        signin = (Button) findViewById(R.id.signinbtn);
        signup = (Button)findViewById(R.id.btnsignup);
        DB = new DBHelper(this);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String pas = password.getText().toString();
                String repass = repassword.getText().toString();

                if (user.equals("")||pas.equals("")||repass.equals("")){
                    Toast.makeText(MainActivity.this, "Plese enter all the fields", Toast.LENGTH_SHORT).show();
                }
                else{
                    if (pas.equals(repass)){
                        Boolean cheuser = DB.checkusername(user);
                        if (cheuser==false){
                            Boolean insert = DB.insertData(user,pas);
                            if (insert==true){
                                Toast.makeText(MainActivity.this, "Registered successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(),Home_Activity.class);
                                startActivity(intent);

                            }
                            else {
                                Toast.makeText(MainActivity.this, "Registered Failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else {
                            Toast.makeText(MainActivity.this, "User already exists! Please sign in!", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else {
                        Toast.makeText(MainActivity.this, "Password not matching", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}