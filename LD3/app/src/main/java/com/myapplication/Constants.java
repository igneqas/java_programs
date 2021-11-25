package com.myapplication;

public class Constants {
    public final static String ADDRESS = "http:192.168.8.127:8080/LD_war_exploded/";
    public final static String LOGIN_URL = ADDRESS+ "user/login";
    public final static String GET_ALL_COURSES_URL = ADDRESS + "course/getAllCourses";
    public final static String GET_USER_URL = ADDRESS + "user/getUser/";
    public final static String UPDATE_USER_URL = ADDRESS + "user/updateUser/";
    public final static String DELETE_USER_URL = ADDRESS + "user/deleteUser/";
    public final static String GET_FOLDERS_BY_COURSE_URL = ADDRESS + "folder/getFoldersInCourse/";
}
