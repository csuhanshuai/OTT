package com.huawei.ott;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;

import com.huawei.ott.application.OttApplication;
import com.huawei.ott.net.NetResult;
import com.huawei.ott.net.VolleyManager;
import com.huawei.ott.utills.DisplayUtils;
import com.huawei.ott.utills.LogUtils;
import com.huawei.ott.utills.OttConstants;
import com.huawei.ott.utills.SPUtills;
import com.huawei.ott.utills.SystemUtils;
import com.huawei.ott.utills.UIUtils;

import org.json.JSONObject;

import java.util.HashMap;

/**
 * 欢迎界面
 */
public class WelcomeActivity extends Activity implements AnimationListener
{


    private Intent mIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);

        OttApplication.setScreenwidth(DisplayUtils.getWidthPx(this));
        OttApplication.setScreenHeight(DisplayUtils.getHeightPx(this));

        //开个服务检测更新
        HashMap map = new HashMap();
        String username = SPUtills.get(UIUtils.getContext(), OttConstants.username, "xxx").toString();
        map.put("userid", username);
        JSONObject mRequest = new JSONObject(map);
        mIntent = new Intent();
        mIntent.setClass(WelcomeActivity.this, LoginActivity.class);
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
                        String sessionid = SPUtills.get(WelcomeActivity.this, OttConstants.SESSIONID, "nnonoo").toString();
//                        String pwjm = SPUtills.get(UIUtils.getContext(), OttConstants.SESSIONID, "xxxx").toString();
//                        byte[] encrypt = pwjm.getBytes("gbk");
//                        String sessionid = new String(DES3Utils.decryptMode(encrypt));
//                        byte[] encrypt = sessionid.getBytes("gbk");
//                        VolleyManager.getInstance().setCoolie(new String(DES3Utils.decryptMode(encrypt)));
                        VolleyManager.getInstance().setCoolie(sessionid);
                        mIntent.setClass(WelcomeActivity.this, MainActivity.class);
                    }else
                    {
                        LogUtils.e("uservalid to be false");
                    }
                }catch(Exception e)
                {
                    LogUtils.e("uservalid to be false");
                    mIntent.setClass(WelcomeActivity.this, LoginActivity.class);
                }
            }
        });

        boolean serviceRunning = SystemUtils.isServiceRunning(this, "com.huawei.ott.services.HeartBitService");
        if(serviceRunning)
        {
        }else
        {

        }
        init();
    }


    private void init()
    {
        View rootView = findViewById(R.id.rl_welcome_root);

        RotateAnimation rotateAnima = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotateAnima.setDuration(1000);
        rotateAnima.setFillAfter(true); // 设置动画执行完毕时, 停留在完毕的状态下.

        ScaleAnimation scaleAnima = new ScaleAnimation(0, 1, 0, 1, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnima.setDuration(1000);
        scaleAnima.setFillAfter(true);

        AlphaAnimation alphaAnima = new AlphaAnimation(0, 1);
        alphaAnima.setDuration(2000);
        alphaAnima.setFillAfter(true);


        // 把三个动画合在一起, 组成一个集合动画
        AnimationSet setAnima = new AnimationSet(false);
        setAnima.addAnimation(rotateAnima);
        setAnima.addAnimation(scaleAnima);
        setAnima.addAnimation(alphaAnima);
        setAnima.setAnimationListener(this);


        rootView.startAnimation(setAnima);
    }

    @Override
    public void onAnimationEnd(Animation animation)
    {
        // 去文件中取是否打开过程序的值
        boolean isOpenMainPager = (boolean)SPUtills.get(UIUtils.getContext(), OttConstants.IS_OPEN_MAIN_PAGER, false);
        //        Intent intent = new Intent();
        //		if(isOpenMainPager) {
        //			// 已经打开过一次主界面, 直接进入主界面.
        //			intent.setClass(WelcomeActivity.this, LoginActivity.class);
        //		}else {
        //			// 没有打开过主界面, 进入引导页面.
        //			intent.setClass(WelcomeActivity.this, GuideActivity.class);
        //		}

        //        boolean serviceRunning = SystemUtils.isServiceRunning(this, "com.huawei.ott.services.HeartBitService");
        //        if(serviceRunning) {
        //            VolleyManager.getInstance().setCoolie(SPUtills.get(this, "sessionid", "nnonoo").toString());
        //            intent.setClass(WelcomeActivity.this, MainActivity.class);
        //        }else {
        //
        //            intent.setClass(WelcomeActivity.this, LoginActivity.class);
        //        }
        startActivity(mIntent);

        // 关闭掉欢迎界面
        finish();
    }

    @Override
    public void onAnimationRepeat(Animation animation)
    {
        // TODO Auto-generated method stub

    }

    @Override
    public void onAnimationStart(Animation animation)
    {
        // TODO Auto-generated method stub

    }


}
