package com.myapplication;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;

public class RESTController {

    private static OutputStream outputStream;
    private static BufferedWriter bufferedWriter;

    public static String sendGet(String urlGet) throws IOException {
        URL url = new URL(urlGet);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestMethod("GET");
        httpURLConnection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        //setConnectionParameters(httpURLConnection, "GET");
        int code = httpURLConnection.getResponseCode();
        System.out.println("Response code: " + code);
        if (code == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            String line;
            StringBuffer response = new StringBuffer();
            while ((line = in.readLine()) != null) {
                response.append(line);
                //break;
            }
            in.close();
            return response.toString();
        } else return "Error";
    }

    public static String sendGet(String urlGet, String id) throws IOException {
        URL url = new URL(urlGet + id);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestMethod("GET");
        httpURLConnection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        //setConnectionParameters(httpURLConnection, "GET");
        int code = httpURLConnection.getResponseCode();
        System.out.println("Response code: " + code);
        if (code == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            String line;
            StringBuffer response = new StringBuffer();
            while ((line = in.readLine()) != null) {
                response.append(line);
                //break;
            }
            in.close();
            return response.toString();
        } else return "Error";
    }


public static String sendPost(String urlPost, String postDataParams) throws IOException {
        URL url = new URL(urlPost);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        //System.out.println(postDataParams);
        //Reikia papildomu connectiono nustatymu, jie bus bendri su Put metodu
        setConnectionParameters(httpURLConnection, "POST");
        outputStream = httpURLConnection.getOutputStream();
        bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
        bufferedWriter.write(postDataParams); //? bufferedWriter.flush();
        bufferedWriter.close();
        outputStream.close();

        int code = httpURLConnection.getResponseCode();
        System.out.println("Response code: " + code);
        if (code == HttpURLConnection.HTTP_OK) {
        BufferedReader in = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
        String line;
        StringBuffer response = new StringBuffer();
        while ((line = in.readLine()) != null) {
            response.append(line);
            break;
        }
        in.close();
        return response.toString();
    } else return "Error";
    }

    public static String sendPut(String urlPost, String putDataParams, String id) throws IOException {
        URL url = new URL(urlPost + id);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        setConnectionParameters(httpURLConnection, "PUT");
        outputStream = httpURLConnection.getOutputStream();
        bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
        bufferedWriter.write(putDataParams);
        bufferedWriter.close();
        outputStream.close();

        int code = httpURLConnection.getResponseCode();
        System.out.println("Response code: " + code);
        if (code == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            String line;
            StringBuffer response = new StringBuffer();
            while ((line = in.readLine()) != null) {
                response.append(line);
                break;
            }
            in.close();
            return response.toString();
        } else return "Error";
    }

    public static String sendDelete(String urlDelete, String id) throws IOException {
        URL url = new URL(urlDelete + id);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestMethod("DELETE");
        httpURLConnection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        int code = httpURLConnection.getResponseCode();
        System.out.println("Response code: " + code);

        if (code == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            String line;
            StringBuffer response = new StringBuffer();

            while ((line = in.readLine()) != null) {
                response.append(line);
                break;
            }
            in.close();
            return response.toString();

        } else {
            return "";
        }
    }

    private static void setConnectionParameters(HttpURLConnection httpURLConnection, String type) throws ProtocolException {
        httpURLConnection.setRequestMethod(type);
        httpURLConnection.setReadTimeout(20000);
        httpURLConnection.setConnectTimeout(20000);
        httpURLConnection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        httpURLConnection.setRequestProperty("Accept","application/json");
        httpURLConnection.setDoInput(true);
        httpURLConnection.setDoOutput(true);
    }
}
