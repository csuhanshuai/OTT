package com.huawei.ott.net;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.huawei.ott.utills.LogUtils;
import com.huawei.ott.utills.UIUtils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created by Jonas on 2015/11/22.
 */
public class VolleyManager
{

    public Gson gson;
    private static VolleyManager sNetworkManager;
    public final RequestQueue mRequestQueue;
    private String cookie;


    private VolleyManager()
    {
        mRequestQueue = Volley.newRequestQueue(UIUtils.getContext());
        this.gson = new Gson();
    }

    public static VolleyManager getInstance()
    {
        if(sNetworkManager == null)
        {
            synchronized(VolleyManager.class)
            {
                if(sNetworkManager == null)
                {
                    sNetworkManager = new VolleyManager();
                }
            }
        }
        return sNetworkManager;
    }

    public void canll(Objects tag)
    {
        mRequestQueue.cancelAll(tag);
    }

    public void requestString(String url, Object tag, final NetResult result)
    {
        requestString(Request.Method.GET, url, null, tag, result);
    }

    public void postRequestString(String url, Map<String,String> params, Object tag, final NetResult result)
    {
        requestString(Request.Method.POST, url, params, tag, result);
    }

    private void requestString(int method, String url, final Map<String,String> params, Object tag, final NetResult result)
    {
        StringRequest request = new StringRequest(method, url, new com.android.volley.Response.Listener<String>()
        {
            @Override
            public void onResponse(String s)
            {
                result.onSucceed(s);
            }
        }, new com.android.volley.Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError volleyError)
            {
                StackTraceElement[] stackTrace = volleyError.getStackTrace();
                for(int i = stackTrace.length-1; i>=0; i--)
                {
                    StackTraceElement stackTraceElement = stackTrace[i];
                    LogUtils.e(stackTraceElement.toString());
                }
                LogUtils.e(volleyError.getStackTrace()+"");
                result.onFailure();
            }
        })
        {
            @Override
            protected Map<String,String> getParams() throws AuthFailureError
            {
                return params;
            }
        };
        request.setTag(tag);
        mRequestQueue.add(request);
    }


    public void requestJsonObject(String url, Object tag, final NetResult result)
    {
        requestJsonObject(Request.Method.GET, url, tag, result);
    }

    public void requestJsonObject(int method, String url, Object tag, final NetResult result)
    {
        JsonObjectRequest request = new JsonObjectRequest(method, url, null, new com.android.volley.Response.Listener<JSONObject>()
        {
            @Override
            public void onResponse(JSONObject jsonObject)
            {
                result.onSucceed(jsonObject);
            }
        }, new com.android.volley.Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError volleyError)
            {
                result.onFailure();
            }
        });
        request.setTag(tag);
        mRequestQueue.add(request);
    }


    public void requestJsonArray(String url, Object tag, final NetResult result)
    {
        JsonArrayRequest request = new JsonArrayRequest(url, new Response.Listener<JSONArray>()
        {
            @Override
            public void onResponse(JSONArray jsonArray)
            {
                result.onSucceed(jsonArray);
            }
        }, new com.android.volley.Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError volleyError)
            {
                result.onFailure();
            }
        });
        request.setTag(tag);
        mRequestQueue.add(request);
    }

    public void postParamJsonObject(String url, JSONObject json, Object tag, final NetResult netResult)
    {
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, json, new Response.Listener<JSONObject>()
        {
            @Override
            public void onResponse(JSONObject jsonObject)
            {

                netResult.onSucceed(jsonObject);
            }
        }, new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError volleyError)
            {

                netResult.onFailure();
            }
        })
        {

            @Override
            protected Response<JSONObject> parseNetworkResponse(NetworkResponse response)
            {
                Map<String,String> responseHeaders = response.headers;
                String rawCookies = responseHeaders.get("Set-Cookie");
                return super.parseNetworkResponse(response);
            }

            @Override
            public Map<String,String> getHeaders() throws AuthFailureError
            {
                Map<String,String> mHeaders = new HashMap<>();
                mHeaders.put("Cookie", cookie);
                return mHeaders;
            }
        };
        request.setTag(tag);
        VolleyManager.getInstance().mRequestQueue.add(request);
    }

    public void setCoolie(String cookie)
    {
        this.cookie = cookie;
    }

}
