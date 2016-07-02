package com.huawei.ott.application;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;

import com.huawei.ott.utills.OttConstants;
import com.huawei.ott.utills.SPUtills;

/**
 * Created by Jonas on 2015/11/19.
 */
public class OttApplication extends Application
{
    //初始化 主线程中的上下文
    private static OttApplication mContext = null;
    //获取到主线程的handler
    private static Handler mMainThreadHandler = null;
    //获取到主线程的looper
    private static Looper mMainThreadLooper = null;
    //获取到主线程
    private static Thread mMainThead = null;
    //获取到主线程的id
    private static int mMainTheadId;

    private static int screenwidth;
    private static int screenHeight;
    public static String username;

    public static int getScreenHeight()
    {
        return screenHeight == 0 ? (int)SPUtills.get(getApplication(), OttConstants.screenh, 0) : screenHeight;
    }

    public static void setScreenHeight(int screenHeight)
    {
        if(OttApplication.screenHeight == 0)
        {
            SPUtills.put(getApplication(), OttConstants.screenh, screenHeight);
            OttApplication.screenHeight = screenHeight;
        }
    }

    public static int getScreenwidth()
    {
        return screenwidth == 0 ? (int)SPUtills.get(getApplication(), OttConstants.screenw, 0) : screenwidth;
    }

    public static void setScreenwidth(int screenwidth)
    {
        if(OttApplication.screenwidth == 0)
        {
            SPUtills.put(getApplication(), OttConstants.screenw, screenHeight);
            OttApplication.screenwidth = screenwidth;
        }
    }

    @Override
    public void onCreate()
    {
        super.onCreate();
        mContext = this;
        this.mMainThreadHandler = new Handler();
        this.mMainThreadLooper = getMainLooper();
        this.mMainThead = Thread.currentThread();
        //android.os.Process.myUid()   获取到用户id
        //android.os.Process.myPid()获取到进程id
        //android.os.Process.myTid()获取到调用线程的id
        this.mMainTheadId = android.os.Process.myTid();

    }

    public static OttApplication getApplication()
    {
        return mContext;
    }

    public static Handler getMainThreadHandler()
    {
        return mMainThreadHandler;
    }

    public static Looper getMainThreadLooper()
    {
        return mMainThreadLooper;
    }

    public static Thread getMainThread()
    {
        return mMainThead;
    }

    public static int getMainThreadId()
    {
        return mMainTheadId;
    }
}
