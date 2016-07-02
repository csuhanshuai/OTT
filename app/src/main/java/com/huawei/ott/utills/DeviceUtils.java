package com.huawei.ott.utills;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.telephony.TelephonyManager;
import android.util.Log;

import com.huawei.ott.BuildConfig;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.List;
import java.util.UUID;

public class DeviceUtils
{
    protected static final String TAG = DeviceUtils.class.getSimpleName();

    // 移动
    private static final int CHINA_MOBILE = 1;
    // 联通
    private static final int UNICOM = 2;
    // 电信
    private static final int TELECOMMUNICATIONS = 3;
    // 失败
    private static final int ERROR = 0;

    /**
     * 手机唯一标识
     *
     * @param context
     * @return
     */
    public static String getDeviceId(Context context)
    {
        final TelephonyManager tm = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
        final String tmDevice, tmSerial, androidId;
        tmDevice = ""+tm.getDeviceId();
        tmSerial = ""+tm.getSimSerialNumber();
        androidId = ""+android.provider.Settings.Secure
                .getString(context.getContentResolver(), android.provider.Settings.Secure.ANDROID_ID);
        UUID deviceUuid = new UUID(androidId.hashCode(), ( (long)tmDevice.hashCode()<<32 )|tmSerial.hashCode());
        String uniqueId = deviceUuid.toString();
        return uniqueId;
    }

    /**
     * 手机MAC地址
     *
     * @param context
     * @return
     */
    public static String getMacAddressInfo(Context context)
    {
        WifiManager manager = (WifiManager)context.getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = manager.getConnectionInfo();
        return info.getMacAddress();
    }

    //    public static String getMac() {
    //        String macSerial = null;
    //        String str = "";
    //        try {
    //            Process pp = Runtime.getRuntime().exec(
    //                    "cat /sys/class/net/wlan0/address ");
    //            InputStreamReader ir = new InputStreamReader(pp.getInputStream());
    //            LineNumberReader input = new LineNumberReader(ir);
    //
    //
    //            for (; null != str;) {
    //                str = input.readLine();
    //                if (str != null) {
    //                    macSerial = str.trim();// 去空格
    //                    break;
    //                }
    //            }
    //        } catch (IOException ex) {
    //            // 赋予默认值
    //            ex.printStackTrace();
    //        }
    //        return macSerial;
    //    }


    /**
     * TelephonyManager对象
     *
     * @param context
     * @return
     */
    private static TelephonyManager getTelphoneManager(Context context)
    {
        return (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
    }

    /**
     * DeviceId
     *
     * @param context
     * @return
     */
    public static String getDeviceID(Context context)
    {
        return getTelphoneManager(context).getDeviceId();
    }

    /**
     * IMSI号
     *
     * @param context
     * @return
     */
    public static String getImis(Context context)
    {
        return getTelphoneManager(context).getSubscriberId();
    }

    /**
     * 厂商信息
     *
     * @return
     */
    public static String getProductInfo()
    {
        return android.os.Build.MODEL;
    }

    /**
     * release版本
     *
     * @return
     */
    public static String getReleaseVersion()
    {
        return android.os.Build.VERSION.RELEASE;
    }

    /**
     * SDK_INT 版本
     *
     * @return
     */
    public static int getSDKVersion()
    {
        return android.os.Build.VERSION.SDK_INT;
    }

    /**
     * 手机号码
     *
     * @param context
     * @return
     */
    public static String getPhoneNum(Context context)
    {
        return getTelphoneManager(context).getLine1Number();
    }

    /**
     * 当前运营商
     *
     * @param context
     * @return 返回0 表示失败 1表示为中国移动 2为中国联通 3为中国电信
     */
    public static int getProviderName(Context context)
    {
        String IMSI = getImis(context);
        if(IMSI == null)
        {
            return ERROR;
        }
        if(IMSI.startsWith("46000") || IMSI.startsWith("46002"))
        {
            return CHINA_MOBILE;
        }else if(IMSI.startsWith("46001"))
        {
            return UNICOM;
        }else if(IMSI.startsWith("46003"))
        {
            return TELECOMMUNICATIONS;
        }
        return ERROR;
    }

    /**
     * 手机CPU名字
     *
     * @return
     */
    public static String getCpuName()
    {
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        try
        {
            // 读取文件CPU信息
            fileReader = new FileReader("/pro/cpuinfo");
            bufferedReader = new BufferedReader(fileReader);
            String string = bufferedReader.readLine();
            String[] strings = string.split(":\\s+", 2);
            return strings[1];
        }catch(FileNotFoundException e)
        {
            Log.e(TAG, e.getLocalizedMessage());
        }catch(IOException e)
        {
            Log.e(TAG, e.getLocalizedMessage());
        }finally
        {
            if(bufferedReader != null)
            {
                try
                {
                    bufferedReader.close();
                }catch(IOException e)
                {
                    Log.e(TAG, e.getLocalizedMessage());
                }
            }
            if(fileReader != null)
            {
                try
                {
                    fileReader.close();
                }catch(IOException e)
                {
                    Log.e(TAG, e.getLocalizedMessage());
                }
            }
        }
        return null;
    }

    /**
     * 检查程序是否运行
     *
     * @param context
     * @param packageName
     * @return
     */
    public static boolean isAppRunning(Context context, String packageName)
    {
        boolean isAppRunning = false;
        ActivityManager am = (ActivityManager)context.getSystemService(Context.ACTIVITY_SERVICE);
        List<RunningTaskInfo> list = am.getRunningTasks(100);
        for(RunningTaskInfo info : list)
        {
            if(info.topActivity.getPackageName().equals(packageName) && info.baseActivity.getPackageName().equals(packageName))
            {
                isAppRunning = true;
                // find it, break
                break;
            }
        }
        return isAppRunning;
    }

    /**
     * 是否在最前面
     *
     * @param context
     * @param packageName
     * @return
     */
    public static boolean isTopActivity(Context context, String packageName)
    {
        ActivityManager activityManager = (ActivityManager)context.getSystemService(Context.ACTIVITY_SERVICE);
        List<RunningTaskInfo> tasksInfo = activityManager.getRunningTasks(1);
        if(tasksInfo.size()>0)
        {
            if(BuildConfig.DEBUG)
            {
                Log.d(TAG, ( "---------------包名-----------"+tasksInfo.get(0).topActivity.getPackageName() ));
            }
            // 应用程序位于堆栈的顶层
            if(packageName.equals(tasksInfo.get(0).topActivity.getPackageName()))
            {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断是否联网
     *
     * @param context
     * @return
     */
    public static boolean isNetworkConnected(Context context)
    {
        ConnectivityManager connectivityManager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isAvailable();
    }

    //根据Wifi信息获取本地Mac
    public static String getLocalMacAddressFromWifiInfo(Context context)
    {
        WifiManager wifi = (WifiManager)context.getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = wifi.getConnectionInfo();
        return info.getMacAddress();
    }

    //获取本地IP
    public static String getLocalIpAddress()
    {
        try
        {
            for(Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements(); )
            {
                NetworkInterface intf = en.nextElement();
                for(Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements(); )
                {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if(!inetAddress.isLoopbackAddress() && !inetAddress.isLinkLocalAddress())
                    {
                        return inetAddress.getHostAddress().toString();
                    }
                }
            }
        }catch(SocketException ex)
        {
            LogUtils.e("WifiPreference IpAddress："+ex.toString());
        }


        return null;
    }
}
