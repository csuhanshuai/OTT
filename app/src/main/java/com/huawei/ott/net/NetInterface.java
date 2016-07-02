package com.huawei.ott.net;

import android.widget.Toast;

import com.huawei.ott.model.Authen;
import com.huawei.ott.model.Login;
import com.huawei.ott.utills.DESUtil;
import com.huawei.ott.utills.DeviceUtils;
import com.huawei.ott.utills.LogUtils;
import com.huawei.ott.utills.OttConstants;
import com.huawei.ott.utills.SPUtills;
import com.huawei.ott.utills.UIUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Random;

/**
 * Created by Jonas on 2015/12/3.
 */
public class NetInterface
{
    private String mMac;
    private String mDeviceId;
    private NetResult netResult;
    private Authen mAuthen;

    private static NetInterface mNetInterface = new NetInterface();
    private String mUsername;

    private NetInterface()
    {
    }

    public static NetInterface getInstance()
    {
        return mNetInterface;
    }

    public void login(NetResult Result, final String password)
    {
        this.netResult = Result;
        //        mMac = DeviceUtils.getLocalMacAddressFromWifiInfo(UIUtils.getContext());
        //        mDeviceId = DeviceUtils.getDeviceID(UIUtils.getContext());
        mMac = "f8:a4:5f:1f:0d:59";
        mDeviceId = "99000311914262";
        mMac = mMac.replaceAll(":", "");
        mUsername = SPUtills.get(UIUtils.getContext(), OttConstants.username, "xxx").toString();
        VolleyManager.getInstance().postParamJsonObject(OttConstants.EDSLOGIN+mUsername, null, this, new NetResult()
        {
            @Override
            public void onFailure()
            {
                LogUtils.e("error。。login...可能是网络无效");
                netResult.onFailure();
            }

            @Override
            public <T> void onSucceed(T modle)
            {
                Login login = VolleyManager.getInstance().gson.fromJson(modle.toString(), Login.class);
                startAuthentication(login, password);
            }
        });

    }

    /**
     * 用户认证
     *
     * @param login
     *         调用login接口返回封装好的数据
     * @param password
     */
    private void startAuthentication(Login login, String password)
    {

        int mRandom = new Random().nextInt(99999999);
        String ip = DeviceUtils.getLocalIpAddress();
        String s = mRandom+"$"+login.getEnctytoken()+"$"+mUsername+"$"+mDeviceId+"$"+ip+"$"+mMac+"$Reserved$"+"CTC";
        String mCaculate = DESUtil.caculate(s, password);
        HashMap map = new HashMap();

        map.put("terminalid", mDeviceId);
        map.put("userid", mUsername);
        map.put("userType", "1");
        map.put("mac", mMac);
        map.put("terminaltype", "AndroidPhone");
        map.put("osversion", DeviceUtils.getSDKVersion()+"");
        map.put("timezone", "");
        map.put("utcEnable", "1");
        map.put("authenticator", mCaculate);
        map.put("cnonce", mRandom);
        map.put("locale", "1");
        JSONObject request = new JSONObject(map);
        VolleyManager.getInstance().postParamJsonObject(OttConstants.AUTHENTICATE, request, this, new NetResult()
        {
            @Override
            public void onFailure()
            {
                LogUtils.e("认证失败。。login");
                Toast.makeText(UIUtils.getContext(), OttConstants.UPE, Toast.LENGTH_SHORT).show();
                //保存记住密码状态
                SPUtills.put(UIUtils.getContext(), OttConstants.remember, false);
                netResult.onFailure();
            }

            @Override
            public <T> void onSucceed(T modle)
            {
                try
                {
                    SPUtills.put(UIUtils.getContext(), OttConstants.AUTHENTICATECOIFIG, modle.toString());
                    if("authenticate fail .".equals(( (JSONObject)modle ).optString("retmsg")))
                    {
                        LogUtils.e("authenticate fail .");
                        Toast.makeText(UIUtils.getContext(), OttConstants.UPE, Toast.LENGTH_SHORT).show();
                        netResult.onFailure();
                        return;
                    }
                    mAuthen = VolleyManager.getInstance().gson.fromJson(modle.toString(), Authen.class);
                    String mSessionid = "JSESSIONID="+mAuthen.getSessionid();
                    //保存sessionid
                    SPUtills.put(UIUtils.getContext(), OttConstants.SESSIONID, mSessionid);
                    //byte[] encrypt = DES3Utils.encryptMode(mSessionid.getBytes());
                    //SPUtills.put(UIUtils.getContext(), OttConstants.SESSIONID, new String(encrypt, "gbk"));
                    LogUtils.d("authenticate success");
                    //获取终端在平台侧的配置参数。
                    VolleyManager.getInstance().setCoolie(mSessionid);
                    getcustomizeconfig();
                }catch(Exception e)
                {
                    e.printStackTrace();
                }
            }

        });
    }

    /**
     * 获取终端在平台侧的配置参数。
     */
    private void getcustomizeconfig()
    {
        HashMap map = new HashMap();
        map.put("queryType", 0);
        map.put("terminalType", "AndroidPhone");
        map.put("isFuzzyQuery", 0);
        JSONObject request = new JSONObject(map);

        VolleyManager.getInstance().postParamJsonObject(OttConstants.GETCUSTOMIZECONFIG, request, this, new NetResult()
        {
            @Override
            public void onFailure()
            {
                LogUtils.e("获取终端在平台侧的配置参数失败");
                netResult.onFailure();
            }

            @Override
            public <T> void onSucceed(T modle)
            {
                SPUtills.put(UIUtils.getContext(), OttConstants.AndroidPhoneConfig, modle.toString());
                getcustomizeconfig2();
            }

        });
    }

    /**
     * EPG系统参数
     */
    private void getcustomizeconfig2()
    {
        HashMap map = new HashMap();
        map.put("queryType", 2);
        JSONObject request = new JSONObject(map);

        VolleyManager.getInstance().postParamJsonObject(OttConstants.GETCUSTOMIZECONFIG, request, this, new NetResult()
        {
            @Override
            public void onFailure()
            {
                LogUtils.e("EPG系统参数获取失败");
                netResult.onFailure();
            }

            @Override
            public <T> void onSucceed(T modle)
            {
                SPUtills.put(UIUtils.getContext(), OttConstants.EPGConfig, modle.toString());
                getqueryLocation();
            }

        });
    }

    /**
     * 请求地理位置
     */
    private void getqueryLocation()
    {

        VolleyManager.getInstance().postParamJsonObject(OttConstants.QUERYLOCATION, null, this, new NetResult()
        {
            @Override
            public void onFailure()
            {
                LogUtils.e("请求地理位置失败");
                netResult.onFailure();
            }

            @Override
            public <T> void onSucceed(T modle)
            {
                try
                {
                    if(mAuthen.getLocation().equals(( (JSONObject)modle ).get("location").toString()))
                    {
                        LogUtils.d("地理位置验证成功");
                    }else
                    {
                        LogUtils.d("地理位置校验失败");
                    }
                }catch(JSONException e)
                {
                    e.printStackTrace();
                }
                queryprofile();
            }
        });
    }

    private void queryprofile()
    {
        HashMap map = new HashMap();
        map.put("type", 1);
        JSONObject request = new JSONObject(map);
        VolleyManager.getInstance().postParamJsonObject(OttConstants.QUERYPROFILE, request, this, new NetResult()
        {
            @Override
            public void onFailure()
            {
                LogUtils.e("queryprofile失败。");
            }

            @Override
            public <T> void onSucceed(T modle)
            {
                SPUtills.put(UIUtils.getContext(), OttConstants.QUERYPROFILECONFIG, modle.toString());
                switchProfile();
            }
        });
    }

    private void switchProfile()
    {
        HashMap map = new HashMap();
        map.put("id", mAuthen.getUsers().get(0).getIdentityid());
        map.put("password", mAuthen.getUsers().get(0).getPassword());
        JSONObject request = new JSONObject(map);
        VolleyManager.getInstance().postParamJsonObject(OttConstants.SWITCHPROFILE, request, this, new NetResult()
        {
            @Override
            public void onFailure()
            {
                LogUtils.e("switchProfile失败。");
                netResult.onFailure();
            }

            @Override
            public <T> void onSucceed(T modle)
            {
                netResult.onSucceed(modle);
            }
        });
    }
}
