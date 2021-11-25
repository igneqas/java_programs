package com.myapplication;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.io.IOException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import static com.myapplication.Constants.LOGIN_URL;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void validate(View view) {
        EditText username = findViewById(R.id.usernameField);
        EditText password = findViewById(R.id.passwordField);
        String data = "{\"username\":\"" + username.getText().toString() + "\", \"password\":\"" + password.getText().toString() + "\"}";

        //UserLogin userLogin = new UserLogin();
        //userLogin.execute(data);
        Executor executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());
        executor.execute(() -> {
            String response;
            try {
                response = RESTController.sendPost(LOGIN_URL, data);
            } catch (IOException e) {
                e.printStackTrace();
                response = null;
            }
            String finalResponse = response;
            handler.post(() -> {
                if (finalResponse != null && !finalResponse.equals("Error") && !finalResponse.equals("Vartotojo vardas arba slaptazodis ivesti neteisingai")) {
                    Intent intent = new Intent(LoginActivity.this, MainCourseWindow.class);
                    intent.putExtra("userInfo", finalResponse);
                    startActivity(intent);
                } else System.out.println("Vartotojo vardas arba slaptazodis ivesti neteisingai");
            });
        });

    }

    /*private final class UserLogin extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(LoginActivity.this, "Siunciami duomenys", Toast.LENGTH_LONG).show();
        }

        @Override
        protected String doInBackground(String... strings) {
            try {
                return RESTController.sendPost(LOGIN_URL, strings[0]);
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(String response) {
            super.onPostExecute(response);
            System.out.println(response);
            if (response != null && !response.equals("Error") && !response.equals("Vartotojo vardas arba slaptazodis ivesti neteisingai")) {
                Intent intent = new Intent(LoginActivity.this, MainCourseWindow.class);
                startActivity(intent);
            } else {
                Toast.makeText(LoginActivity.this, "Kazkas negerai", Toast.LENGTH_LONG).show();
            }
        }
    }*/

}

