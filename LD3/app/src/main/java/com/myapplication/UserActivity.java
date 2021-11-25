package com.myapplication;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.io.IOException;
import java.time.LocalDate;
import java.util.StringTokenizer;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import static com.myapplication.Constants.*;

public class UserActivity extends AppCompatActivity {

    private char userType;
    private String Id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        Intent intent = getIntent();
        String userId = intent.getStringExtra("userId");
        Id = userId;
        System.out.println(userId);

        EditText password = findViewById(R.id.passwordF);
        EditText name = findViewById(R.id.nameF);
        EditText surname = findViewById(R.id.surnameF);
        EditText email = findViewById(R.id.emailF);
        EditText phoneNumber = findViewById(R.id.phoneNumberF);
        EditText companyName = findViewById(R.id.companyNameF);
        EditText representative = findViewById(R.id.representativeF);
        TextView nameView = findViewById(R.id.nameV);
        TextView surnameView = findViewById(R.id.surnameV);
        TextView emailView = findViewById(R.id.emailV);
        TextView numberView = findViewById(R.id.numberV);
        TextView companyNameView = findViewById(R.id.companyNameV);
        TextView representativeView = findViewById(R.id.representativeV);

        Executor executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());
        executor.execute(() -> {
            String response;
            try {
                response = RESTController.sendGet(GET_USER_URL, userId);
            } catch (IOException e) {
                e.printStackTrace();
                response = null;
            }
            System.out.println(response);
            String finalResponse = response;
            handler.post(() -> {
                StringTokenizer tempString = new StringTokenizer(finalResponse, "{}");
                tempString.nextToken();
                String x = tempString.nextToken();
                StringTokenizer tempValues = new StringTokenizer(x, "\'");
                int a = 1;
                //String tempName = "", tempSurname = "", tempEmail = "", tempNumber = "", tempPassword = "";
                while(tempValues.hasMoreElements()){
                    if(a == 4) password.setText(tempValues.nextToken());
                    else if(a == 8) {
                        String l = tempValues.nextToken();
                        name.setText(l);
                        companyName.setText(l);
                    }
                    else if(a == 10) {
                        String l = tempValues.nextToken();
                        surname.setText(l);
                        representative.setText(l);
                    }
                    else if(a == 12) email.setText(tempValues.nextToken());
                    else if(a == 14) phoneNumber.setText(tempValues.nextToken());
                    else tempValues.nextToken();
                    a++;
                }
                //System.out.println(tempNumber);
                if(finalResponse.contains("telefono numeris")){
                    userType='P';
                    companyName.setVisibility(View.GONE);
                    representative.setVisibility(View.GONE);
                    companyNameView.setVisibility(View.GONE);
                    representativeView.setVisibility(View.GONE);

                } else{
                    userType='C';
                    name.setVisibility(View.GONE);
                    surname.setVisibility(View.GONE);
                    email.setVisibility(View.GONE);
                    phoneNumber.setVisibility(View.GONE);
                    nameView.setVisibility(View.GONE);
                    surnameView.setVisibility(View.GONE);
                    emailView.setVisibility(View.GONE);
                    numberView.setVisibility(View.GONE);
                }
            });
        });


    }

    public void updateUser(View view){
        EditText password = findViewById(R.id.passwordF);
        EditText name = findViewById(R.id.nameF);
        EditText surname = findViewById(R.id.surnameF);
        EditText email = findViewById(R.id.emailF);
        EditText phoneNumber = findViewById(R.id.phoneNumberF);
        EditText companyName = findViewById(R.id.companyNameF);
        EditText representative = findViewById(R.id.representativeF);
        String data = null;
        if(userType=='P') {
            data = "{\"password\":\"" + password.getText().toString() + "\", \"firstName\":\"" + name.getText().toString() + "\", \"lastName\":\"" + surname.getText().toString() + "\", \"email\":\"" + email.getText().toString() + "\", \"phoneNumber\":\"" + phoneNumber.getText().toString() + "\"}";
        }
        if(userType=='C') {
            data = "{\"password\":\"" + password.getText().toString() + "\", \"companyName\":\"" + companyName.getText().toString() + "\", \"representative\":\"" + representative.getText().toString() + "\"}";
        }

        Executor executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());
        String finalData = data;
        executor.execute(() -> {
            String response;
            try {
                response = RESTController.sendPut(UPDATE_USER_URL, finalData, Id);
            } catch (IOException e) {
                e.printStackTrace();
                response = null;
            }
            String finalResponse = response;
            System.out.println(response);
            handler.post(() -> {
                if (finalResponse != null && !finalResponse.equals("Error")) {
                    Intent intent = new Intent(UserActivity.this, MainCourseWindow.class);
                    intent.putExtra("userInfo", "= " + Id);
                    startActivity(intent);
                } else System.out.println("Išsaugoti nepavyko");
            });
        });
    }

    public void deleteUser(View view){
        Executor executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());
        executor.execute(() -> {
            String response;
            try {
                response = RESTController.sendDelete(DELETE_USER_URL, Id);
            } catch (IOException e) {
                e.printStackTrace();
                response = null;
            }
            String finalResponse = response;
            System.out.println(response);
            handler.post(() -> {
                if (finalResponse != null && !finalResponse.equals("Error")) {
                    Intent intent = new Intent(UserActivity.this, LoginActivity.class);
                    startActivity(intent);
                } else System.out.println("Nepavyko ištrinti");
            });
        });
    }
}