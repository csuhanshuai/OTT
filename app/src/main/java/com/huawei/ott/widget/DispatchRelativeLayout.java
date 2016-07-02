package com.huawei.ott.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

/**
 * Created by Jonas on 2015/12/11.
 */
public class DispatchRelativeLayout extends RelativeLayout
{
    private OnDispatchTouchEventListener dtl;

    public DispatchRelativeLayout(Context context)
    {
        super(context);
    }

    public DispatchRelativeLayout(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    public DispatchRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
    }


    public void setonDispatchTouchEventListener(OnDispatchTouchEventListener dtl)
    {
        this.dtl = dtl;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event)
    {
        if(null != dtl)
        {
            if(dtl.dispatchEvent(event))
            {
                return true;
            }
        }
        return super.dispatchTouchEvent(event);
    }

    public interface OnDispatchTouchEventListener
    {

        boolean dispatchEvent(MotionEvent event);
    }
}
