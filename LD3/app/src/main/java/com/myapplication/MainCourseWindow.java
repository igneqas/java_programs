package com.myapplication;

import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.myapplication.ds.Course;
import com.myapplication.ds.Folder;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import static com.myapplication.Constants.*;

public class MainCourseWindow extends AppCompatActivity {

    List<Course> allCourses = new ArrayList<>();

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_course_window);
        Intent intent = getIntent();
        String userData = intent.getStringExtra("userInfo");
        System.out.println("new window: " + userData);


        Executor executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());
        executor.execute(() -> {
            String response;
            try {
                response = RESTController.sendGet(GET_ALL_COURSES_URL);
            } catch (IOException e) {
                e.printStackTrace();
                response = null;
            }
            String finalResponse = response;
            handler.post(() -> {
                if (finalResponse != null && !finalResponse.equals("Error")) {
                    StringTokenizer tempString = new StringTokenizer(finalResponse, "}");
                    while(tempString.hasMoreElements()){
                        String x = tempString.nextToken().replaceAll(", Kursas\\{", "").replaceAll("\\[Kursas", "").replaceAll("]", "").replaceAll("\\{", "").trim();
                        //System.out.println(x);
                        if(x == "")
                            continue;
                        StringTokenizer tempValues = new StringTokenizer(x, "\'");
                        int a = 1;
                        int id = 0;
                        String name = "", description = "";
                        LocalDate dateCreated = LocalDate.now();
                        while(tempValues.hasMoreElements()){
                            if(a == 2) id = Integer.parseInt(tempValues.nextToken());
                            else if(a == 4) name = tempValues.nextToken();
                            else if(a == 6) description = tempValues.nextToken();
                            else if(a == 8) dateCreated = LocalDate.parse(tempValues.nextToken());
                            else tempValues.nextToken();
                            a++;
                        }
                        if(id != 0)
                            allCourses.add(new Course(id, name, description, dateCreated));
                    }
                    ListView courseList = findViewById(R.id.courseList);
                    ArrayAdapter<Course> arrayAdapter = new ArrayAdapter<>(MainCourseWindow.this, android.R.layout.simple_list_item_1, allCourses);
                    courseList.setAdapter(arrayAdapter);


                    courseList.setOnItemClickListener(((parent, view, position, courseId) -> {
                        Executor executor1 = Executors.newSingleThreadExecutor();
                        Handler handler1 = new Handler(Looper.getMainLooper());
                        executor1.execute(() -> {
                            String response1;
                            try {
                                response1 = RESTController.sendGet(GET_FOLDERS_BY_COURSE_URL, String.valueOf(allCourses.get(position).getId()));
                            } catch (IOException e) {
                                e.printStackTrace();
                                response1 = null;
                            }
                            //System.out.println(response1);
                            String finalResponse1 = response1;
                            //System.out.println(response);
                            handler1.post(() -> {
                                List<Folder> folders = new ArrayList<>();
                                if (finalResponse1 != null && !finalResponse1.equals("Error")) {
                                    StringTokenizer tempString1 = new StringTokenizer(finalResponse1, "}");
                                    while(tempString1.hasMoreElements()) {
                                        String x = tempString1.nextToken().replaceAll(", Folder\\{", "").replaceAll("\\[Folder", "").replaceAll("]", "").replaceAll("\\{", "").trim();
                                        System.out.println(x);
                                        StringTokenizer tempValues1 = new StringTokenizer(x, "\'");
                                        int a1 = 1;
                                        int id1 = 0;
                                        String name1 = "";
                                        while(tempValues1.hasMoreElements()){
                                            if(a1 == 2) id1 = Integer.parseInt(tempValues1.nextToken());
                                            else if(a1 == 4) name1 = tempValues1.nextToken();
                                            else tempValues1.nextToken();
                                            a1++;
                                        }
                                        if(id1 != 0)
                                            folders.add(new Folder(id1, name1));
                                    }
                                    ListView folderList = findViewById(R.id.folderList);
                                    ArrayAdapter<Folder> arrayAdapter1 = new ArrayAdapter<>(MainCourseWindow.this, android.R.layout.simple_list_item_1, folders);
                                    folderList.setAdapter(arrayAdapter1);
                                }
                            });
                        });

                    }));

                } else System.out.println("Kursu nerasta");
            });
        });
    }

    public void openUserActivity(View view){
        Intent intent = getIntent();
        String id = intent.getStringExtra("userInfo").split(",")[0].split("=")[1].trim();
        //System.out.println(id);
        Intent newIntent = new Intent(MainCourseWindow.this, UserActivity.class);
        newIntent.putExtra("userId", id);
        startActivity(newIntent);
    }

    public void signOut(View view){
        Intent intent = new Intent(MainCourseWindow.this, LoginActivity.class);
        startActivity(intent);
    }

}