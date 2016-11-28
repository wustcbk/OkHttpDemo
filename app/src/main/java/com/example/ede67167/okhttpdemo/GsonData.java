package com.example.ede67167.okhttpdemo;

import java.io.InputStream;

/**
 * Created by EDE67167 on 2015-12-15.
 */
public class GsonData
{
    private String mName;
    private int mId;
    private InputStream mByteArrayInputStream;
    private String mPhotoName;

    public String getName()
    {
        return mName;
    }
    public int getId()
    {
        return mId;
    }
    public void setName(String name)
    {
        this.mName=name;
    }
    void setId(int id)
    {
        this.mId=id;
    }
    public InputStream getDataByteStream()
    {
        return mByteArrayInputStream;
    }
    public void setDataByteStream(InputStream byteArrayInputStream)
    {
        mByteArrayInputStream=byteArrayInputStream;

    }
    public String getPhotoName()
    {
        return mPhotoName;
    }
    public void setPhotoName(String photoName)
    {
        mPhotoName=photoName;

    }

}