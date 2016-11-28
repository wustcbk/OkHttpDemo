package com.example.ede67167.okhttpdemo;

import android.os.Handler;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Headers;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static com.example.ede67167.okhttpdemo.okhttpUnity.GetSessionID;
import static com.example.ede67167.okhttpdemo.okhttpUnity.SetSessionID;

/**
 * Created by EDE67167 on 2015-12-25.
 */
public class okCallBackWrap<E> implements Callback {
    final Handler mMainLoopHandler;
     final okhttpUnity.RequestInterface<E> mRequestInterface;

    okCallBackWrap(Handler handler, okhttpUnity.RequestInterface<E> requestInterface)
    {
        mMainLoopHandler=handler;
        mRequestInterface=requestInterface;
    }
    @Override
    public void onFailure(final Request request, final IOException e) {
        mMainLoopHandler.post(new Runnable() {
            @Override
            public void run() {
                mRequestInterface.onFailure("Connect time out!");
            }
        });
    }

    @Override
    public void onResponse(final Response response) throws IOException {
        final String str = response.body().string();

        mMainLoopHandler.post(new Runnable() {
            @Override
            public void run() {
                if(response.isSuccessful())
                {
                    if(GetSessionID()==null) {
                        Headers headers = response.headers();
                        Map<String, List<String>> headersMap = headers.toMultimap();
                        String sessionID = null;
                        String cookie = response.header("set-cookie");
                        if (cookie != null) {
                            sessionID = cookie.substring(0, cookie.indexOf(";"));
                        } else {
                            int i;
                            i = 0;
                            i = 0;
                        }
                        SetSessionID(sessionID);
                    }
                    mRequestInterface.onResponse((E) str);
                }
                else
                {
                    mRequestInterface.onFailure("error id:"+response.code());
                }
            }
        });
    }
}
