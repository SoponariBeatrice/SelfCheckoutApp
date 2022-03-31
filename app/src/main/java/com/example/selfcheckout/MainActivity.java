package com.example.selfcheckout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.opencv.android.OpenCVLoader;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity" ;
    EditText firstNameInput;
    EditText lastNameInput;
    EditText emailInput;
    EditText passwordInput;
    EditText username;
    TextView textViewResult;
    Button signUpButton;
    public static User user;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firstNameInput = (EditText) findViewById(R.id.firstName);
        lastNameInput = (EditText) findViewById(R.id.lastName);
        emailInput = (EditText) findViewById(R.id.email);
        passwordInput = (EditText) findViewById(R.id.password);
        username = (EditText) findViewById(R.id.username);
        signUpButton = (Button) findViewById(R.id.signupButton);
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
                user = new User(firstNameInput.getText().toString(),lastNameInput.getText().toString(),emailInput.getText().toString(),passwordInput.getText().toString(),username.getText().toString());
                Intent myIntent = new Intent(MainActivity.this, LoginActivity.class);
                sendPostRequestOnClick(user);
                startActivity(myIntent);

            }
        });
    }
    private void sendPostRequestOnClick(User newUser){
        JsonPlaceHolderApi api = RetrofitUser.getRetrofitInstance().create(JsonPlaceHolderApi.class);
        Log.e(TAG,newUser.toString());
        Call<User> call = api.registerUser(newUser);

        call.enqueue(new Callback<User>() {

            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Log.e(TAG,"Works " + newUser.getFirstname()+ newUser.getLastname());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e(TAG,"on failure: " + t.getMessage());

            }
        });
    }
}