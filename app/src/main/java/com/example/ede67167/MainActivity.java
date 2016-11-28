package com.example.ede67167;

import android.app.Activity;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.example.ede67167.DownLoadView.DwView;
import com.example.ede67167.Product.ProductAdapter;
import com.example.ede67167.Product.ProductView;
import com.example.ede67167.RecyclerView.MyRecyclerView;
import com.example.ede67167.ViewPager.MyViewPagerFramentAdapter;
import com.example.ede67167.firstPage.firstPage;
import com.example.ede67167.okhttpdemo.GsonData;
import com.example.ede67167.okhttpdemo.okhttpUnity;
import com.example.ede67167.photoSelectPage.PhotoSelectPage;
import com.example.ede67167.webViewTest.ProductWebView;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private ArrayList<Fragment> lists = new ArrayList<Fragment>();
   // private MyViewPagerAdapter myAdapter;
   private MyViewPagerFramentAdapter myAdapter;
    private MyRecyclerView mRecyclerView;
    //private GalleryAdapter mAdapter;
    private ProductAdapter mAdapter;
    private ArrayList<GsonData> mDatas;
    private okhttpUnity mOkhttpUnity;
    private boolean mHaveGetGsonData=false;
    int gIndex=0;
    private TextView mText_1;
    private TextView mText_2;
    private TextView mText_3;
    private TextView mText_4;
    private TextView mText_5;
    private ProductView mProductView;
    private PageBtnClickListener mPageBtnClickListener;
    private firstPage mP1_PageView;
    static private Activity mActivity;

    class PageBtnClickListener implements OnClickListener
    {

        @Override
        public void onClick(View v) {

            int pageIndex=0;
            if(v==mText_1)
            {
                pageIndex=0;
            }
            else if(v==mText_2)
            {
                pageIndex=1;
            }
            else if(v==mText_3)
            {
                pageIndex=2;
            }
            else if(v==mText_4)
            {
                pageIndex=3;
            }
            else if(v==mText_5)
            {
                pageIndex=4;
            }
            PageSelectIndicate( pageIndex);

        }
    }
    private void PageSelectIndicate(int pageIndex)
    {
        TextView textView;
        mText_1.setTextColor(Color.BLACK);
        mText_2.setTextColor(Color.BLACK);
        mText_3.setTextColor(Color.BLACK);
        mText_4.setTextColor(Color.BLACK);
        mText_5.setTextColor(Color.BLACK);
        if(0==pageIndex)
        {
            textView=mText_1;
        }
        else if(1==pageIndex)
        {
            textView=mText_2;
        }
        else if(2==pageIndex)
        {
            textView=mText_3;
        }
        else if(3==pageIndex)
        {
            textView=mText_4;
        }
        else
        {
            textView=mText_5;
        }
        viewPager.setCurrentItem(pageIndex);
        textView.setTextColor(Color.parseColor("#ffffff"));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        mActivity=this;
        byte[] bytes=new byte[100];
        final Handler myHandler = new Handler();
        mDatas=new ArrayList<GsonData>();
        mOkhttpUnity=new okhttpUnity();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // ButterKnife.bind(this);

        Observable.just("one", "two", "three", "four", "five")
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(/* an Observer */);

        int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
         int totalMem=(int) (Runtime.getRuntime().totalMemory() / 1024);
         int freeMem=(int) (Runtime.getRuntime().freeMemory() / 1024);


        mText_1 = (TextView) findViewById(R.id.textView1);
        mText_2 = (TextView) findViewById(R.id.textView2);
        mText_3 = (TextView) findViewById(R.id.textView3);
        mText_4 = (TextView) findViewById(R.id.textView4);
        mText_5 = (TextView) findViewById(R.id.textView5);

        android.support.v4.app.Fragment p1_View;
        android.support.v4.app.Fragment p2_View;
        android.support.v4.app.Fragment p3_View;
        android.support.v4.app.Fragment p4_View;
        android.support.v4.app.Fragment p5_View;

        p1_View=firstPage.newInstance("p1") ;
        lists.add(p1_View);
        p2_View=ProductView.newInstance("p2") ;
        lists.add(p2_View);
        p3_View=new DwView();
        Bundle bundle = new Bundle();
        bundle.putString("hello", "p3");
        p3_View.setArguments(bundle);
         lists.add(p3_View);
        //lists.add(p2_View);
        p4_View=new ProductWebView();
        lists.add(p4_View);

        p5_View=new PhotoSelectPage();
        lists.add(p5_View);

        myAdapter = new MyViewPagerFramentAdapter(getSupportFragmentManager(),lists);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPager.setAdapter(myAdapter);
        viewPager.setCurrentItem(0);
        PageSelectIndicate(0);

        mPageBtnClickListener=new PageBtnClickListener();
        mText_1.setOnClickListener(mPageBtnClickListener);
        mText_2.setOnClickListener(mPageBtnClickListener);
        mText_3.setOnClickListener(mPageBtnClickListener);
        mText_4.setOnClickListener(mPageBtnClickListener);
        mText_5.setOnClickListener(mPageBtnClickListener);


        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                PageSelectIndicate(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });









/*        mOkhttpUnity.RequestGson("http://192.168.43.22:8080/struts2_android/mystruts/gsonResult", new okhttpUnity.RequestInterface<ArrayList<GsonData>>()
                // mOkhttpUnity.RequestGson("http://192.168.43.22:8080/struct_hello/mystruts/myresult",new okhttpUnity.RequestGsonInterface<ArrayList<GsonData>>()
        {
            @Override
            public void onFailure(String errorMessage) {
                int i;
                i = 0;
            }


            @Override
            public void onResponse(ArrayList<GsonData> response) {
                final ArrayList<GsonData> final_response = response;
                myHandler.post(new Runnable() {

                    @Override
                    public void run() {
                        for (int i = 0; i < final_response.size(); i++) {
                            GsonData data = final_response.get(i);
                            data.setDataByteStream(null);
                            //mDatas.add(data);
                            mAdapter.addData(i, data);
                        }
                        synchronized (this) {
                            mHaveGetGsonData = true;
                        }

                    }


                });
            }
        });

        Runnable runable_1=new Runnable() {
            @Override
            public void run() {
               while(true)
               {
                           synchronized(this) {
                               if (mHaveGetGsonData) {
                                   int i=0;
                                   int dataSize=mDatas.size();
                                   while(i<mDatas.size()) {
                                       if (i == gIndex) {

                                           FormEncodingBuilder builder = new FormEncodingBuilder();
                                           builder.add("photo", mDatas.get(i).getPhotoName());
                                           mOkhttpUnity.RequesData("http://192.168.43.22:8080/struts2_android/mystruts/pictureResult", builder, i, new okhttpUnity.RequesInterface_2<InputStream>() {
                                               @Override
                                               public void onFailure(Request request, IOException e) {
                                                   gIndex++;
                                               }

                                               @Override
                                               public void onResponse(InputStream response, final int index) {
                                                   final InputStream final_response = response;
                                                   myHandler.post(new Runnable() {

                                                       @Override
                                                       public void run() {
                                                           mAdapter.updateData(index, final_response);
                                                           gIndex++;
                                                       }


                                                   });
                                               }
                                           });
                                           i++;
                                       }
                                       else
                                       {
                                           try {
                                               Thread.sleep(100);
                                           } catch (InterruptedException e) {
                                               e.printStackTrace();
                                           }
                                       }
                                   }

                                   break;
                               } else {
                                   try {
                                       Thread.sleep(100);
                                   } catch (InterruptedException e) {
                                       e.printStackTrace();
                                   }
                               }
                           }
                   }
               }
            };

        Thread thread_1=new Thread(runable_1);
        thread_1.start();*/





/*
         AssetManager assets = getAssets();
        try {

            InputStream inputStream = null;
            BufferedInputStream bufInputStream = null;
            inputStream=assets.open("files/characteroutput.txt");
            bufInputStream = new BufferedInputStream(inputStream);
            bufInputStream.read(bytes,0,10);
            int ii;
            ii=0;

        } catch (IOException e) {
            e.printStackTrace();
        }
       */


                //new DownloadWebpageTask().execute("http://www.baidu.cn");


                //final_inputStream.close();


/*
                //as find the thread not the ui thread,can't show the ui in this function,but now,don't find problem yet..
                i=0;
                WebView myWebView = (WebView) findViewById(R.id.myWed);
                myWebView.getSettings().setDefaultTextEncodingName("UTF-8") ;
                myWebView.loadData(htmlStr, "text/html", "UTF-8");
*/


            }

            @Override
            public boolean onCreateOptionsMenu(Menu menu) {
                // Inflate the menu; this adds items to the action bar if it is present.
                getMenuInflater().inflate(R.menu.menu_main, menu);
                return true;
            }

            @Override
            public boolean onOptionsItemSelected(MenuItem item) {
                // Handle action bar item clicks here. The action bar will
                // automatically handle clicks on the Home/Up button, so long
                // as you specify a parent activity in AndroidManifest.xml.
                int id = item.getItemId();

                //noinspection SimplifiableIfStatement
                if (id == R.id.action_settings) {
                    return true;
                }

                return super.onOptionsItemSelected(item);
            }

            private class DownloadWebpageTask extends AsyncTask<String, Void, String> {
                @Override
                protected String doInBackground(String... urls) {
                    // params comes from the execute() call: params[0] is the url.

                    return downloadUrl(urls[0]);
                    //return "Unable to retrieve web page. URL may be invalid.";


                }

                // onPostExecute displays the results of the AsyncTask.
                @Override
                protected void onPostExecute(String result) {
                    long id = Thread.currentThread().getId();
                    int i = 0;
                }

            }

            private String downloadUrl(String myurl) {
                InputStream is = null;
                // Only display the first 500 characters of the retrieved
                // web page content.
                int len = 500;
                String contentAsString = null;
//     OkHttpClient mOkHttpClient = new OkHttpClient();


                try {
                    URL url = null;
                    try {
                        url = new URL(myurl);
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                    HttpURLConnection conn = null;
                    try {
                        conn = (HttpURLConnection) url.openConnection();
                        conn.setReadTimeout(10000 /* milliseconds */);
                        conn.setConnectTimeout(15000 /* milliseconds */);
                        conn.setRequestMethod("GET");
                        conn.setDoInput(true);
                        // Starts the query
                        conn.connect();
                        int response = conn.getResponseCode();
                        Log.d("HttpExample", "The response is: " + response);
                        is = conn.getInputStream();
                        contentAsString = null;
                        contentAsString = readIt(is, len);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                    // Convert the InputStream into a string


                    // textView.setText(contentAsString);


                    // Makes sure that the InputStream is closed after the app is
                    // finished using it.
                } finally {
                    if (is != null) {
                        try {
                            is.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                    return contentAsString;
                }

            }

            //Reads an InputStream and converts it to a String.
            public String readIt(InputStream stream, int len) throws IOException, UnsupportedEncodingException {
                Reader reader = null;
                reader = new InputStreamReader(stream, "UTF-8");
                char[] buffer = new char[len];
                reader.read(buffer);
                return new String(buffer);
            }
          static public Activity GetActivity()
          {
              return mActivity;
          }


        }



