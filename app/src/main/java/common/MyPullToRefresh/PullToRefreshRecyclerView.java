package common.MyPullToRefresh;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import com.example.ede67167.R;
import com.example.ede67167.RecyclerView.MyRecyclerView;
import com.handmark.pulltorefresh.library.PullToRefreshBase;

/**
 * Created by EDE67167 on 2016-02-29.
 */
public class PullToRefreshRecyclerView extends PullToRefreshBase<MyRecyclerView> {
    public PullToRefreshRecyclerView(Context context) {
        super(context);
    }

    public PullToRefreshRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PullToRefreshRecyclerView(Context context, Mode mode) {
        super(context, mode);
    }

    public PullToRefreshRecyclerView(Context context, Mode mode, AnimationStyle animStyle) {
        super(context, mode, animStyle);
    }

    @Override
    public Orientation getPullToRefreshScrollDirection() {
        return Orientation.VERTICAL;
    }

    @Override
    protected MyRecyclerView createRefreshableView(Context context, AttributeSet attrs) {
        MyRecyclerView view = new MyRecyclerView(context, attrs);
        view.setId(R.id.recyclerview);
        return view;
    }

    @Override
    protected boolean isReadyForPullEnd() {
        if(0==getRefreshableView().getChildCount())
        {
            return true;
        }
        else {
            int endItem = getRefreshableView().getChildCount() - 1;
            View view = getRefreshableView().getChildAt(endItem);
            if (null != view) {
                //Toast.makeText(MyApplication.getInstance().getApplicationContext(),"endItem:"+endItem+ "getRefreshableView().getBottom():"+getRefreshableView().getBottom()+" view.getBottom():"+view.getBottom(), Toast.LENGTH_SHORT).show();
                if (getRefreshableView().getBottom() + 1 >= view.getBottom())//Gary "+1"to solve the can't triger problem
                {
                    return true;
                } else {
                    return false;
                }
            }
            return false;
        }
    }

    @Override
    protected boolean isReadyForPullStart() {
        if(0==getRefreshableView().getChildCount())
        {
            return true;
        }
        else {
            View view = getRefreshableView().getChildAt(0);
            if (view != null) {
                if (view.getTop() >= getRefreshableView().getTop()) {
                    return true;
                } else {
                    return false;
                }
            }
            return false;
        }
    }

}
