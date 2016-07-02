package com.huawei.ott.utills;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;

import com.huawei.ott.application.OttApplication;

/**
 *
 */
public class UIUtils
{


    public static Context getContext()
    {
        return OttApplication.getApplication();
    }

    public static Thread getMainThread()
    {
        return OttApplication.getMainThread();
    }

    public static long getMainThreadId()
    {
        return OttApplication.getMainThreadId();
    }

    /** 获取主线程的handler */
    public static Handler getHandler()
    {
        return OttApplication.getMainThreadHandler();
    }

    /** 延时在主线程执行runnable */
    public static boolean postDelayed(Runnable runnable, long delayMillis)
    {
        return getHandler().postDelayed(runnable, delayMillis);
    }

    /** 在主线程执行runnable */
    public static boolean post(Runnable runnable)
    {
        return getHandler().post(runnable);
    }

    /** 从主线程looper里面移除runnable */
    public static void removeCallbacks(Runnable runnable)
    {
        getHandler().removeCallbacks(runnable);
    }

    public static View inflate(int resId)
    {
        return LayoutInflater.from(getContext()).inflate(resId, null);
    }

    /** 获取资源 */
    public static Resources getResources()
    {

        return getContext().getResources();
    }

    /** 获取文字 */
    public static String getString(int resId)
    {
        return getResources().getString(resId);
    }

    /** 获取文字数组 */
    public static String[] getStringArray(int resId)
    {
        return getResources().getStringArray(resId);
    }

    /** 获取dimen */
    public static int getDimens(int resId)
    {
        return getResources().getDimensionPixelSize(resId);
    }

    /** 获取drawable */
    public static Drawable getDrawable(int resId)
    {
        return getResources().getDrawable(resId);
    }

    /** 获取颜色 */
    public static int getColor(int resId)
    {
        return getResources().getColor(resId);
    }

    /** 获取颜色选择器 */
    public static ColorStateList getColorStateList(int resId)
    {
        return getResources().getColorStateList(resId);
    }

    //判断当前的线程是不是在主线程
    public static boolean isRunInMainThread()
    {
        return android.os.Process.myTid() == getMainThreadId();
    }

    public static void runInMainThread(Runnable runnable)
    {
        if(isRunInMainThread())
        {
            runnable.run();
        }else
        {
            post(runnable);
        }
    }
}
