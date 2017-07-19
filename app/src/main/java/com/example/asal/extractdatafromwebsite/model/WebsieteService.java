package com.example.asal.extractdatafromwebsite.model;

/**
 * Created by asal on 2017-06-20.
 */

public interface WebsieteService {

    public void success(String dataInXML);
    public void failure(Exception exception);

}
