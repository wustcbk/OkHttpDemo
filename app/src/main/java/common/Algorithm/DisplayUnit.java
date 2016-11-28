package common.Algorithm;

import android.content.Context;

/**
 * Created by EDE67167 on 2015-12-03.
 */
public class DisplayUnit {
    static int PxToDp(Context context,int pixel)
    {
        int dp;
        dp=(int) context.getResources().getDisplayMetrics().density*pixel;
        return dp;
    }
    static int DpToPx(Context context,int dp)
    {
        int pixel;
        pixel=dp/(int) context.getResources().getDisplayMetrics().density;
        return pixel;
    }
}
