package com.huawei.ott.services;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;

import com.huawei.ott.net.NetResult;
import com.huawei.ott.net.VolleyManager;
import com.huawei.ott.utills.LogUtils;
import com.huawei.ott.utills.OttConstants;
import com.huawei.ott.utills.SPUtills;
import com.huawei.ott.utills.UIUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class HeartBitService extends Service
{

    private JSONObject mRequest;

    public HeartBitService()
    {

    }

    @Override
    public IBinder onBind(Intent intent)
    {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private Handler mPeriodicEventHandler;

    private final int PERIODIC_EVENT_TIMEOUT = 850*1000;

    @Override
    public void onCreate()
    {
        super.onCreate();
        mPeriodicEventHandler = new Handler();
        mPeriodicEventHandler.postDelayed(doPeriodicTask, PERIODIC_EVENT_TIMEOUT);
        HashMap map = new HashMap();
        String username = SPUtills.get(UIUtils.getContext(), OttConstants.username, "xxx").toString();
        map.put("userid", username);
        mRequest = new JSONObject(map);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        return super.onStartCommand(intent, flags, startId);
    }

    private void heartBitNet()
    {

        VolleyManager.getInstance().postParamJsonObject(OttConstants.HEARTBIT, mRequest, this, new NetResult()
        {
            @Override
            public void onFailure()
            {

            }

            @Override
            public <T> void onSucceed(T modle)
            {
                JSONObject object = (JSONObject)modle;
                try
                {
                    if(object.getBoolean("uservalid"))
                    {
                        LogUtils.d("uservalid is true");
                    }else
                    {
                        LogUtils.e("uservalid to be false");
                        stopSelf();
                    }
                }catch(JSONException e)
                {
                    LogUtils.e("uservalid to be false");
                    stopSelf();
                }
            }
        });
    }

    private Runnable doPeriodicTask = new Runnable()
    {
        public void run()
        {
            // your action here
            heartBitNet();
            mPeriodicEventHandler.postDelayed(doPeriodicTask, PERIODIC_EVENT_TIMEOUT);
        }
    };

    @Override
    public void onDestroy()
    {
        mPeriodicEventHandler.removeCallbacks(doPeriodicTask);
        super.onDestroy();
    }
}
