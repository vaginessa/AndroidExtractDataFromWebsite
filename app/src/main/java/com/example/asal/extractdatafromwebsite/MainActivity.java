package com.example.asal.extractdatafromwebsite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.asal.extractdatafromwebsite.model.HttpGetWebsiteData;
import com.example.asal.extractdatafromwebsite.model.WebsieteService;

public class MainActivity extends AppCompatActivity implements View.OnClickListener , WebsieteService{

    TextView textView,textViewPogress;
    Button btnLoad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        textView = (TextView)findViewById(R.id.textViewData);
        btnLoad = (Button)findViewById(R.id.btnLoad);
        btnLoad.setOnClickListener(this);
        textViewPogress = (TextView)findViewById(R.id.textViewProgress);
    }


    @Override
    public void onClick(View v) {
        String website = "https://www.w3schools.com/xml/simple.xml";
        HttpGetWebsiteData httpGetWebsiteData = new HttpGetWebsiteData(this,website,textViewPogress);

        //run doInBackground(....)
        httpGetWebsiteData.execute();





    }

    @Override
    public void success(String dataInXML) {

        textView.setText(dataInXML);

    }

    @Override
    public void failure(Exception exception) {

    }
}
