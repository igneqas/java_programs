package com.example.ld.control;

//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;
import com.example.ld.ds.CourseSystem;

import java.io.*;


public class RW {
    public static CourseSystem loadFromFile(String fileName) {
        CourseSystem courseSystem = null;
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(fileName));
            courseSystem = (CourseSystem) objectInputStream.readObject();
            objectInputStream.close();
        } catch (ClassNotFoundException ce) {
            ce.printStackTrace();
        } catch (Exception io) {
            io.printStackTrace();
        }
        return courseSystem;
    }

    //Does not return anything just writes to file
    public static void writeToFile(String fileName, CourseSystem courseSystem) {
        //Write as object to given file
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(fileName));
            outputStream.writeObject(courseSystem);
            outputStream.close();
        } catch (IOException io) {
            io.printStackTrace();
        }
    }
}
