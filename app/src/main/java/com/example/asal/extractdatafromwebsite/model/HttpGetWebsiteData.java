package com.example.asal.extractdatafromwebsite.model;

import android.content.Intent;
import android.os.AsyncTask;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by asal on 2017-06-20.
 */

public class HttpGetWebsiteData extends AsyncTask<Void,Integer,String> {

    private  WebsieteService websieteService;
    private String website;
    private TextView textViewProgress;


    public HttpGetWebsiteData(WebsieteService websieteService, String website,TextView textViewProgress) {
        this.websieteService = websieteService;
        this.website = website;
        this.textViewProgress = textViewProgress;
    }



    @Override
    protected String doInBackground(Void... params) {

        String resultInXML;
        HttpURLConnection httpURLConnection = null;

        try {
            httpURLConnection = (HttpURLConnection)new URL(website).openConnection();
            InputStream is = httpURLConnection.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(is);
            resultInXML = readStream(is);
            return resultInXML;

        } catch (Exception e) {

            websieteService.failure(e);
            return null;
        }

    }


    private String readStream(InputStream is) {
        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();
        InputStreamReader isr = new InputStreamReader(is);
        br = new BufferedReader(isr);
        String oneLine;
        int counter = 0;

        try {
            while ((oneLine=br.readLine())!=null)
            {

                sb.append(oneLine);
                publishProgress(counter++);


            }
        } catch (Exception e) {
            websieteService.failure(e);
        }
        return sb.toString();
    }

    @Override
    protected void onPostExecute(String resultInXML) {
        super.onPostExecute(resultInXML);

        websieteService.success(resultInXML);
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        textViewProgress.setText(String.valueOf(values[0]));
    }
}
