package com.huawei.ott.net;

/**
 * Created by Jonas on 2015/11/22.
 */
public abstract class NetResult
{
    public abstract void onFailure();

    public abstract <T> void onSucceed(T modle);
}
