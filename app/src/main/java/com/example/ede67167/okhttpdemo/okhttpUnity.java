package com.example.ede67167.okhttpdemo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.Headers;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.MultipartBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * Created by EDE67167 on 2015-12-15.
 */
public class okhttpUnity {
    final Handler mMainLoopHandler;
    final int TIME_OUT=30;
    static String mSessionId=null;
    public okhttpUnity()
    {
        mMainLoopHandler=new Handler(Looper.getMainLooper());
    }
    public static String GetSessionID()
    {
        return mSessionId;
    }
    public static void SetSessionID(String sessionID)
    {
        mSessionId=sessionID;
    }

    void FunctionTest()
    {
        RequestBody formBody = new FormEncodingBuilder()
                .add("platform", "android")
                .add("name", "bug")
                .add("subject", "XXXXXXXXXXXXXXX")
                .build();
        File file = new File(Environment.getExternalStorageDirectory(), "balabala.mp4");

        RequestBody fileBody = RequestBody.create(MediaType.parse("application/octet-stream"), file);
        RequestBody requestBody = new MultipartBuilder()
                .type(MultipartBuilder.FORM)
                .addPart(Headers.of(
                                "Content-Disposition", "form-data;name=\"username\""),
                        RequestBody.create(null, "张鸿洋"))
                .addPart(Headers.of(
                                "Content-Disposition", "form-data; name=\"mFile\"; filename =\"wjd.mp4\""),
                        fileBody)
                .build();

    }
    public interface RequestInterface<E>
    {
        //public void onFailure(Request request, IOException e);
        public void onFailure(String errorMessage);
        public void onResponse(E response);

    }

    public interface RequesInterface_2<E>
    {
        public void onFailure(Request request, IOException e);
        public void onResponse(E response,final int index);

    }
    public <E> void   Post(String urlStr, final RequestInterface<E> mainLoopReqInterface)
    {
        Post(urlStr, null, mainLoopReqInterface);
    }
    OkHttpClient CreateCommClient()
    {
        OkHttpClient mOkHttpClient = new OkHttpClient();
        mOkHttpClient.setConnectTimeout(TIME_OUT, TimeUnit.SECONDS);
        return mOkHttpClient;
    }

    Request CreateCommRequest(String urlStr, FormEncodingBuilder builder)
    {
        Request.Builder reqBuilder=new Request.Builder();
        reqBuilder=reqBuilder.url(urlStr);
        if(mSessionId!=null)
        {
            reqBuilder.header("cookie", mSessionId);
            //reqBuilder.addHeader("Accept", "application/json; q=0.5");
        }
        if(builder!=null)
        {

            reqBuilder=reqBuilder.post(builder.build());
        }


        Request request = reqBuilder.build();
        return request;
    }
    public <E> void   Post(String urlStr, FormEncodingBuilder builder, final RequestInterface<E> mainLoopReqInterface) {

        OkHttpClient mOkHttpClient = CreateCommClient();
        final Request request =CreateCommRequest(urlStr, builder);

        Callback callback=new okCallBackWrap<E>(mMainLoopHandler,mainLoopReqInterface);
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(callback);
    }

    public <E> void   PostGson(String urlStr,String gson, final RequestInterface<E> mainLoopReqInterface) {

        OkHttpClient mOkHttpClient =CreateCommClient();
        final MediaType gsonType = MediaType.parse("application/json; charset=utf-8");
        RequestBody body = RequestBody.create(gsonType, gson);
        final Request request = new Request.Builder()
                .url(urlStr)
                .post(body)
                .build();
//new call
        Call call = mOkHttpClient.newCall(request);
        Callback callback=new okCallBackWrap<E>(mMainLoopHandler,mainLoopReqInterface);
        call.enqueue(callback );
    }

    public <E> void   RequestGson(String urlStr, final RequestInterface<E> gsonInterface)
    {
        OkHttpClient mOkHttpClient = CreateCommClient();
        final Request request =CreateCommRequest(urlStr, null);
        Callback callback=new okCallBackWrap<String>(mMainLoopHandler,new RequestInterface<String>()
        {



            @Override
                        public void onFailure(String errorMessage) {
                            gsonInterface.onFailure("Connect time out!");
                        }

                        @Override
                        public void onResponse(String response){
                            Type[] myInterfaceType = gsonInterface.getClass().getGenericInterfaces();//.getGenericSuperclass();
                            Type type = ((ParameterizedType) myInterfaceType[0]).getActualTypeArguments()[0];
                            String gsonStr = null;
                            gsonStr =response;
                            Gson gson_decode = new Gson();
                            E mGsonArray_decode = gson_decode.fromJson(gsonStr, type);
                            gsonInterface.onResponse(mGsonArray_decode);
                        }
                    });
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(callback );
    }


  /* public <E> void   RequestGson(String urlStr, final RequestInterface<E> gsonInterface) {

       OkHttpClient mOkHttpClient = CreateCommClient();
       final Request request = CreateCommRequest( urlStr, null);
//new call
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new Callback(){

            @Override
            public void onFailure(Request request, IOException e) {
                gsonInterface.onFailure("Connect time out!");
            }

            @Override
            public void onResponse(Response response) throws IOException {

                if(response.isSuccessful()) {

                    Headers headers=response.headers();
                    Map<String, List<String>> headersMap=headers.toMultimap();
                   // String cookie=response.header("set-cookie");
                    Type[] myInterfaceType = gsonInterface.getClass().getGenericInterfaces();//.getGenericSuperclass();
                    Type type = ((ParameterizedType) myInterfaceType[0]).getActualTypeArguments()[0];
                    String gsonStr = response.body().string();
                    Gson gson_decode = new Gson();
                    E mGsonArray_decode = gson_decode.fromJson(gsonStr, type);
                    gsonInterface.onResponse(mGsonArray_decode);
                }
                else
                {
                    gsonInterface.onFailure("error id:"+response.code());
                }
            }
        });
    }*/

    /*public <E> void   RequesData(String urlStr, FormEncodingBuilder builder,final int index,final RequesInterface_2<E> gsonInterface) {

        OkHttpClient mOkHttpClient = new OkHttpClient();
        mOkHttpClient.setConnectTimeout(TIME_OUT, TimeUnit.SECONDS);
        final Request request = new Request.Builder()
                .url(urlStr)
                .post(builder.build())
                .build();
//new call
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new Callback(){

            @Override
            public void onFailure(Request request, IOException e) {
                gsonInterface.onFailure(request,e);
            }
            @Override
            public void onResponse(Response response) throws IOException {
                ByteArrayInputStream dataByteInputStream=null;
                InputStream inputStream = null;
                inputStream = response.body().byteStream();

                //ByteArrayOutputStream outStream = new ByteArrayOutputStream();
                //ByteArrayOutputStream outStream = new ByteArrayOutputStream();
                ByteArrayOutputStream outStream = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
               // byte[] bmp_buffer;
                int len = 0;
                while ((len = inputStream.read(buffer)) != -1) {
                    outStream.write(buffer, 0, len);
                }
                dataByteInputStream=new ByteArrayInputStream(outStream.toByteArray());
                outStream.close();
                inputStream.close();
                dataByteInputStream.close();
                //bmp_buffer = outStream.toByteArray();
                gsonInterface.onResponse( (E)dataByteInputStream,index);
            }
        });
    }*/
    void RequestUrl_test(String url,final Handler myHandler) {

        OkHttpClient mOkHttpClient = new OkHttpClient();
        mOkHttpClient.setConnectTimeout(30, TimeUnit.SECONDS);
        final Request request = new Request.Builder()
                .url("http://192.168.43.22:8080/struts2_android/mystruts/myresult")
                .build();
//new call
        Call call = mOkHttpClient.newCall(request);
//请求加入调度
        call.enqueue(new Callback() {
            int i;

            @Override
            public void onFailure(Request request, IOException e) {
                i = 0;
            }

            @Override
            public void onResponse(final Response response) throws IOException {
                InputStream inputStream = null;

                final String gsonStr = response.body().string();
                Type typeList = new TypeToken<ArrayList<GsonData>>() {
                }.getType();
                Gson gson_decode = new Gson();
                ArrayList<GsonData> mGsonArray_decode = gson_decode.fromJson(gsonStr, typeList);


                inputStream = response.body().byteStream();
                //byte[] dataIns=new byte[100];
                //int read_len;
                //read_len=inputStream.read(dataIns,0,dataIns.length);
                // String dataStr= inputStream.toString();
                long id = Thread.currentThread().getId();
                Headers responseHeaders = response.headers();
                for (int i = 0; i < responseHeaders.size(); i++) {
                    System.out.println("hearder_" + i + "  " + responseHeaders.name(i) + ": " + responseHeaders.value(i));
                }


                //final String final_htmlStr = htmlStr;
                ByteArrayOutputStream outStream = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                byte[] bmp_buffer;
                int len = 0;
                while ((len = inputStream.read(buffer)) != -1) {
                    outStream.write(buffer, 0, len);
                }
                outStream.close();
                inputStream.close();
                bmp_buffer = outStream.toByteArray();


                final Bitmap bitmap;
                BitmapFactory.Options ops = new BitmapFactory.Options();
                ops.inJustDecodeBounds = false;
                ops.inSampleSize = 2;

         /*
                try
                {
                    inputStream.reset();
                } catch (IOException e)
                {
                  e.printStackTrace();
                }
          */
                //bitmap=BitmapFactory.decodeStream(inputStream);
                bitmap = BitmapFactory.decodeByteArray(bmp_buffer, 0, bmp_buffer.length);
                myHandler.post(new Runnable() {

                    @Override
                    public void run() {


                        long id = Thread.currentThread().getId();
                        // This gets executed on the UI thread so it can safely modify Views
                       // ImageView imageView = (ImageView) findViewById(R.id.test_image);


                        //imageView.setImageBitmap(bitmap);


                    }
                });
            }
        });
    }
}
