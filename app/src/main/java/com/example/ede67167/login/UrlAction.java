package com.example.ede67167.login;

import com.example.ede67167.okhttpdemo.okhttpUnity;
import com.google.gson.Gson;
import com.squareup.okhttp.FormEncodingBuilder;

/**
 * Created by EDE67167 on 2015-12-24.
 */
public class UrlAction {
    public static final int   LOG_IN=0X1;
    public static final int   REGISTER=0X2;
    okhttpUnity m_okhttpUnity;
    public UrlAction()
    {
        m_okhttpUnity=new okhttpUnity();
    }
    public String GetUrl(int action)
    {
        String url="http://192.168.43.22:8080/struts2_android/mystruts/";
        switch(action)
        {
            case LOG_IN:
                url+="logIn";
                break;
            case REGISTER:
                url+="register";
                break;
        }
        return url;
    }
    public <E> void LogIn(String userName,String passWord,okhttpUnity.RequestInterface<E> callBack) {
        String url = GetUrl(LOG_IN);
        FormEncodingBuilder builder = new FormEncodingBuilder();
        builder.add("userID", userName);
        builder.add("passWord", passWord);
        m_okhttpUnity.Post(url, builder, callBack);
    }

    public <E> void Register(Object gsonObj,okhttpUnity.RequestInterface<E> callBack)
    {
        String url = GetUrl(REGISTER);
        Gson gson=new Gson();
        String gsonStr=gson.toJson(gsonObj);
        //m_okhttpUnity.Post(url, builder, callBack);
        m_okhttpUnity.PostGson(url, gsonStr, callBack);



    }
}
