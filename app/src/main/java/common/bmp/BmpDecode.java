package common.bmp;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by EDE67167 on 2015-12-03.
 */
public class BmpDecode {
    public static int calculateInSampleSize(int srcWidth, int srcHeight, int reqWidth, int reqHeight)
    {
        // Raw height and width of image

        int inSampleSize = 1;

        if (srcHeight > reqHeight && srcWidth > reqWidth) {
/*
            final int halfHeight = srcHeight / 2;
            final int halfWidth = srcWidth / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) > reqHeight
                    && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
                }
       */
            int xScale=srcWidth/reqWidth;
            int yScale=srcHeight/reqHeight;
            inSampleSize=Math.min(xScale, yScale);

            }


        return inSampleSize;
    }
    public static Bitmap DecodePicMatchImageView(Context context,ImageView imgView,int resouceID)
    {
        int target_viewH,target_viewW;
        int src_viewH,src_viewW;
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(context.getResources(), resouceID, options);
        target_viewH=imgView.getLayoutParams().height;
        target_viewW=imgView.getLayoutParams().width;
        src_viewH=options.outHeight;
        src_viewW=options.outWidth;
        options.inSampleSize=calculateInSampleSize(src_viewW, src_viewH, target_viewW, target_viewH);
        options.inJustDecodeBounds = false;
        Bitmap bitmap=BitmapFactory.decodeResource(context.getResources(),resouceID, options);
        return bitmap;
    }

    public static Bitmap DecodePicMatchImageView(ImageView imgView,InputStream inputStream)
    {
        Bitmap bitmap=null;
        int target_viewH,target_viewW;
        int src_viewH,src_viewW;
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        inputStream.mark(0);
        BitmapFactory.decodeStream(inputStream, null, options);
        target_viewH=imgView.getLayoutParams().height;
        target_viewW=imgView.getLayoutParams().width;
        src_viewH=options.outHeight;
        src_viewW=options.outWidth;
        options.inSampleSize=calculateInSampleSize(src_viewW, src_viewH, target_viewW, target_viewH);
        //options.inSampleSize=1;
        options.inJustDecodeBounds = false;
         int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
         int totalMem=(int) (Runtime.getRuntime().totalMemory() / 1024);
         int freeMem=(int) (Runtime.getRuntime().freeMemory() / 1024);
        Log.d("test","maxMemory="+maxMemory);
        int byteCount=0;
        try {
            inputStream.reset();
            bitmap=BitmapFactory.decodeStream(inputStream, null, options);
            byteCount=bitmap.getByteCount();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
        if(inputStream!=null)
        {
            try {
                inputStream.reset();
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        }
        maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
        totalMem=(int) (Runtime.getRuntime().totalMemory() / 1024);
       freeMem=(int) (Runtime.getRuntime().freeMemory() / 1024);
        return bitmap;
    }

    public static Bitmap DecodePicMatchImageView(ImageView imgView,String  filePath,Context context)
    {
        Bitmap bitmap=null;
        AssetManager assets = context.getAssets();
        InputStream inputStream = null;
        try {
            inputStream = assets.open(filePath);
            bitmap=DecodePicMatchImageView( imgView, inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }

}
