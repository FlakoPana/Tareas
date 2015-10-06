package com.kopo.saludarobot;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import org.apache.http.util.EntityUtils;

public class httpHandler extends AsyncTask<String,String,Integer> {
    private Context mContexto;

    public httpHandler(Context contexto){
        mContexto = contexto.getApplicationContext();
    }

    @Override
    protected void onPreExecute() {
        Toast.makeText(mContexto, "Let's start!", Toast.LENGTH_LONG).show();
    }

    @Override
    protected Integer doInBackground(String... params) {
        HttpURLConnection connection;
        try {
            connection = (HttpURLConnection) new URL(params[0])
                    .openConnection();
            return connection.getResponseCode();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return -1;

    }

    public String post(String URL){
        try{
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(URL);

            HttpResponse resp = httpclient.execute(httppost);
            HttpEntity ent = resp.getEntity();

            String text = EntityUtils.toString(ent);
            return  text;
        }catch (Exception e){
            return "error";
        }

    }
}