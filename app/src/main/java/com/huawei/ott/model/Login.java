package com.huawei.ott.model;

/**
 * Created by Jonas on 2015/11/24.
 */
public class Login
{

    /**
     * enctytoken : E1C0B385BBF6535FC41F767DDA318F1E
     * encryptiontype : 0002
     * platformcode : 0200
     * epgurl : http://221.226.2.232:17200
     * version : MEM V200R007C21LG0001B023
     * epghttpsurl : https://221.226.2.232:17207
     * rootCerAddr : http://221.226.2.232:17200/EPG/CA/iptv_ca.der
     * upgAddr4IPTV : http://84.2.41.176:33600/UPGRADE/jsp/upgrade.jsp
     * upgAddr4OTT : http://173.18.1.173:33600/UPGRADE/jsp/upgrade.jsp
     */

    private String enctytoken;
    private String encryptiontype;
    private String platformcode;
    private String epgurl;
    private String version;
    private String epghttpsurl;
    private String rootCerAddr;
    private String upgAddr4IPTV;
    private String upgAddr4OTT;

    public void setEnctytoken(String enctytoken)
    {
        this.enctytoken = enctytoken;
    }

    public void setEncryptiontype(String encryptiontype)
    {
        this.encryptiontype = encryptiontype;
    }

    public void setPlatformcode(String platformcode)
    {
        this.platformcode = platformcode;
    }

    public void setEpgurl(String epgurl)
    {
        this.epgurl = epgurl;
    }

    public void setVersion(String version)
    {
        this.version = version;
    }

    public void setEpghttpsurl(String epghttpsurl)
    {
        this.epghttpsurl = epghttpsurl;
    }

    public void setRootCerAddr(String rootCerAddr)
    {
        this.rootCerAddr = rootCerAddr;
    }

    public void setUpgAddr4IPTV(String upgAddr4IPTV)
    {
        this.upgAddr4IPTV = upgAddr4IPTV;
    }

    public void setUpgAddr4OTT(String upgAddr4OTT)
    {
        this.upgAddr4OTT = upgAddr4OTT;
    }

    public String getEnctytoken()
    {
        return enctytoken;
    }

    public String getEncryptiontype()
    {
        return encryptiontype;
    }

    public String getPlatformcode()
    {
        return platformcode;
    }

    public String getEpgurl()
    {
        return epgurl;
    }

    public String getVersion()
    {
        return version;
    }

    public String getEpghttpsurl()
    {
        return epghttpsurl;
    }

    public String getRootCerAddr()
    {
        return rootCerAddr;
    }

    public String getUpgAddr4IPTV()
    {
        return upgAddr4IPTV;
    }

    public String getUpgAddr4OTT()
    {
        return upgAddr4OTT;
    }
}
