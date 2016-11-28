package com.example.ede67167.DownLoadView;

import java.io.InputStream;

/**
 * Created by EDE67167 on 2016-01-28.
 */
public class DwData {
    private String title;

    public String getPhotoName() {
        return photoName;
    }

    public void setPhotoName(String photoName) {
        this.photoName = photoName;
    }

    private String photoName;
    private InputStream picStream;
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }



    public InputStream getPicStream() {
        return picStream;
    }

    public void setPicStream(InputStream picStream) {
        this.picStream = picStream;
    }
}
