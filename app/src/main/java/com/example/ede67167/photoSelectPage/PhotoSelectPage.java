package com.example.ede67167.photoSelectPage;

import android.graphics.YuvImage;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.ede67167.MyUri.ServerUri;
import com.example.ede67167.R;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.MultipartBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by EDE67167 on 2016-03-10.
 */
public class PhotoSelectPage extends android.support.v4.app.Fragment {
    private View mContainer;
    YuvImage img;
    //Button mBtn;
    @Bind(R.id.upload_pic) Button mBtn;
    int testCount;
   // private static final MediaType MEDIA_TYPE_PNG = MediaType.parse("image/jpg");
   private static final MediaType MEDIA_TYPE_PNG = MediaType.parse("image/bmp");
    private static final String IMGUR_CLIENT_ID = "...";
    @OnClick(R.id.upload_pic) void submit() {

        File file_1=new File("/storage/sdcard0/DCIM/cat/FL/PB295148.JPG");
        File file_2=new File("/storage/sdcard0/DCIM/cat/FL/PB295149.JPG");
        // inflater.getContext().getResources().openRawResourceFd()
        if(file_1.exists())
        {
            int i;
            i=0;
            i=0;
        }

        OkHttpClient mOkHttpClient = new OkHttpClient();
        mOkHttpClient.setConnectTimeout(15, TimeUnit.SECONDS);
                /*RequestBody requestBody = new MultipartBuilder()
                        .type(MultipartBuilder.FORM)
                        .addPart(
                                Headers.of("Content-Disposition", "form-data; name=\"UploadFile\""),
                                RequestBody.create(MEDIA_TYPE_PNG, file))
                        .build();*/
        RequestBody requestBody;
        MultipartBuilder builder = new MultipartBuilder().type(MultipartBuilder.FORM);
        builder=builder.addFormDataPart("UploadFile", "test1.JPG", RequestBody.create(MEDIA_TYPE_PNG, file_1));
        builder=builder.addFormDataPart("Title","Test title_1");
        builder=builder.addFormDataPart("Describe","Test Describe_1");
        builder=builder.addFormDataPart("UploadFile", "test2.JPG", RequestBody.create(MEDIA_TYPE_PNG, file_2));
        builder=builder.addFormDataPart("Title","Test title_2");
        builder=builder.addFormDataPart("Describe","Test Describe_2");
        requestBody=builder.build();
        // requestBody=builder.addFormDataPart("UploadFile", "test.JPG", RequestBody.create(MEDIA_TYPE_PNG, file)).build();
        //requestBody=builder.addFormDataPart("UploadFile",null, RequestBody.create(MEDIA_TYPE_PNG, file)).build();
        Request request = new Request.Builder()
                .url(ServerUri.SERVER_SUBMIT_PRODUCT)
                .post(requestBody)
                .build();
        Callback callback=new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                testCount++;
                testCount++;
            }

            @Override
            public void onResponse(Response response) throws IOException {
                testCount++;
                testCount++;
            }
        };
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(callback);
    }
    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final LayoutInflater fInflater=inflater;
       // InputStream

                mContainer=inflater.inflate(R.layout.p5_photoselect, null);
        /*mBtn=(Button)mContainer.findViewById(R.id.upload_pic);
        mBtn.setOnClickListener(new View.OnClickListener() {
              String  fileNames[];
            @Override
            public void onClick(View v)
        });*/

        return mContainer;
    }
}
