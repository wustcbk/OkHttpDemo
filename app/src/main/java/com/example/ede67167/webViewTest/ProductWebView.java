package com.example.ede67167.webViewTest;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Animatable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

import com.example.ede67167.MyUri.ServerUri;
import com.example.ede67167.R;
import com.facebook.common.logging.FLog;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.controller.ControllerListener;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.image.ImageInfo;
import com.facebook.imagepipeline.image.QualityInfo;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by EDE67167 on 2016-03-02.
 */
public class ProductWebView extends android.support.v4.app.Fragment{
    private View mContainer;
    private WebView mWebView;
    private ValueCallback<Uri> mUploadMessage;
    private final static int FILECHOOSER_RESULTCODE = 1;
    static String mSessionid=null;

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == FILECHOOSER_RESULTCODE) {
            if ((null == mUploadMessage) || (resultCode != Activity.RESULT_OK||( data == null)))
            {
                return;
            }
            else {
                Uri result = data.getData();
                mUploadMessage.onReceiveValue(result);
            }
            mUploadMessage = null;
        }
    }

    @Nullable


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        Fresco.initialize(inflater.getContext());
        mContainer=inflater.inflate(R.layout.p4_my, null);
        mWebView = (WebView) mContainer.findViewById(R.id.id_webview);

     //   new Thread(new ConnectThread() ).start();







// 取得sessionid.


            // Uri uri = Uri.parse("https://raw.githubusercontent.com/facebook/fresco/gh-pages/static/fresco-logo.png");
        ControllerListener controllerListener = new BaseControllerListener<ImageInfo>() {
            @Override
            public void onFinalImageSet(
                    String id,
                    @Nullable ImageInfo imageInfo,
                    @Nullable Animatable anim) {
                if (imageInfo == null) {
                    return;
                }
                QualityInfo qualityInfo = imageInfo.getQualityInfo();
                FLog.d("Final image received! " +
                                "Size %d x %d",
                        "Quality level %d, good enough: %s, full quality: %s",
                        imageInfo.getWidth(),
                        imageInfo.getHeight(),
                        qualityInfo.getQuality(),
                        qualityInfo.isOfGoodEnoughQuality(),
                        qualityInfo.isOfFullQuality());
            }

            @Override
            public void onIntermediateImageSet(String id, @Nullable ImageInfo imageInfo) {
               // FLog.d("Intermediate image received");
                int i;
                i=0;
            }

            @Override
            public void onFailure(String id, Throwable throwable) {
                FLog.e(getClass(), throwable, "Error loading %s", id);
            }
        };

        Uri uri = Uri.parse("http://192.168.43.22:8080/struts2_android/photo/7.jpg");
        SimpleDraweeView draweeView = (SimpleDraweeView) mContainer.findViewById(R.id.id_pic);
        draweeView.setImageURI(uri);

        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setControllerListener(controllerListener)
                .setUri(uri)
                .build();
        draweeView.setController(controller);
        //设置WebView属性，能够执行Javascript脚本
        mWebView.getSettings().setJavaScriptEnabled(true);
        //加载需要显示的网页
     //   mWebView.loadUrl("http://192.168.43.22:8080/struts2_android/");
        mWebView.setWebChromeClient(new myWebChromeClient());
    //    picView.setImageResource(R.drawable.gary);
        //设置Web视图
     //   mWebView.setWebViewClient(new HelloWebViewClient ());
        return mContainer;
    }
    class myWebChromeClient extends WebChromeClient
    {
        //for 5.0+
        public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> filePathCallback,FileChooserParams fileChooserParams)
        {//don't know how to handle it yet
            return true;
        }

        // For Android 3.0+
        public void openFileChooser(ValueCallback<Uri> uploadMsg) {

            mUploadMessage = uploadMsg;
            Intent i = new Intent(Intent.ACTION_GET_CONTENT);
            i.addCategory(Intent.CATEGORY_OPENABLE);
            i.setType("image/*");
            ProductWebView.this.startActivityForResult(Intent.createChooser(i, "File Chooser"), FILECHOOSER_RESULTCODE);

        }

        // For Android 3.0+
        public void openFileChooser(ValueCallback uploadMsg, String acceptType) {
           mUploadMessage = uploadMsg;
            Intent i = new Intent(Intent.ACTION_GET_CONTENT);
            i.addCategory(Intent.CATEGORY_OPENABLE);
            i.setType("*/*");
            ProductWebView.this.startActivityForResult(
                    Intent.createChooser(i, "File Browser"),
                    FILECHOOSER_RESULTCODE);
        }

        //For Android 4.1,in my XIAOMI 2S will call this function
        public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType, String capture) {
            mUploadMessage = uploadMsg;
            Intent i = new Intent(Intent.ACTION_GET_CONTENT);
            i.addCategory(Intent.CATEGORY_OPENABLE);
            i.setType("image/*");
            ProductWebView.this.startActivityForResult(Intent.createChooser(i, "File Chooser"),FILECHOOSER_RESULTCODE);

        }
    }

    class ConnectThread implements Runnable
    {
        String cookieval;

        @Override
        public void run() {
            try {
                URL url = null;
                url = new URL(ServerUri.SERVER_TEST);
                HttpURLConnection con= null;

                con = (HttpURLConnection) url.openConnection();
                if(mSessionid!=null)
                {
                    //con = (HttpURLConnection) url.openConnection();
                    con.setRequestProperty("cookie", mSessionid);
                }
            //    Map<String, List<String>> headers= con.getHeaderFields();
                 cookieval = con.getHeaderField("set-cookie");

                if(cookieval != null) {
                    int i;
                    mSessionid = cookieval.substring(0, cookieval.indexOf(";"));
                    i=0;
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


        }
