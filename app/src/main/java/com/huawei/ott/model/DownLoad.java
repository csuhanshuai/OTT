package com.huawei.ott.model;

/**
 * Created by Jonas on 2015/12/1.
 */
public class DownLoad
{
    /**
     * retcode : 83886081
     * retmsg : programId and beginTime cannot be null together.
     * pvrId : -1
     * type : -1
     */

    private String retcode;
    private String retmsg;
    private String pvrId;
    private String type;

    public void setRetcode(String retcode)
    {
        this.retcode = retcode;
    }

    public void setRetmsg(String retmsg)
    {
        this.retmsg = retmsg;
    }

    public void setPvrId(String pvrId)
    {
        this.pvrId = pvrId;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public String getRetcode()
    {
        return retcode;
    }

    public String getRetmsg()
    {
        return retmsg;
    }

    public String getPvrId()
    {
        return pvrId;
    }

    public String getType()
    {
        return type;
    }
}
