package com.huawei.ott.model;

import java.util.List;

/**
 * Created by Jonas on 2015/11/28.
 */
public class Customizeconfig
{

    /**
     * retcode : 0
     * extensionInfo : [{"key":"PostPadding","value":"3"},{"key":"stbLogUploadInterval","value":"3600"},{"key":"OTTBOSHURL","value":"http://127.0.0.1:8082/bosh"},{"key":"OTTXMPPPort","value":"5222"},{"key":"FFSkippingTimeBlock","value":"30"},{"key":"netrixPushServerUrl","value":"https://10.10.10.10:6743/iptv/report_user_info"},{"key":"DRMDelayBindingEnable","value":"0"},{"key":"IPTVIMPPort","value":"5222"},{"key":"IPTVXMPPPort","value":"5222"},{"key":"PrePadding","value":"2"},{"key":"OTTIMPPort","value":"5222"},{"key":"vasFavLimit","value":"10"},{"key":"IMDomain","value":"push.huawei.com"},{"key":"IPTVIMPIP","value":"127.0.0.1"},{"key":"OTTIMPURL","value":"http://127.0.0.1:8082/bosh"},{"key":"VerifyCodeExpireTime","value":"300"},{"key":"IPTVIMPURL","value":"http://127.0.0.1:8082/bosh"},{"key":"channelFavLimit","value":"15"},{"key":"REWSkippingTimeBlock","value":"10"},{"key":"playHeartBitInterval","value":"300"},{"key":"stbLogServerUrl","value":"sftp://IP:port@username&password/path"},{"key":"IPTVXMPPIP","value":"127.0.0.1"},{"key":"LicenseServerURLWhiteList","value":"http://10.10.10.10:33200|3GXBW9ZxwC6dBDbDQgPctKsOk9FbMaVJJbe1T7dxXdU="},{"key":"DownloadedBufferLength","value":"30"},{"key":"OTTIMPIP","value":"127.0.0.1"},{"key":"OTTLogServerURL","value":"sftp://username:password@IP:port/path"},{"key":"OTTXMPPIP","value":"127.0.0.1"},{"key":"NPVRSpaceRatio","value":"1"},{"key":"SocialUrl","value":"http://127.0.0.1:8020"},{"key":"vodFavLimit","value":"16"},{"key":"IPTVBOSHURL","value":"http://127.0.0.1:8082/bosh"}]
     */

    private String retcode;
    private List<ExtensionInfoEntity> extensionInfo;

    public void setRetcode(String retcode)
    {
        this.retcode = retcode;
    }

    public void setExtensionInfo(List<ExtensionInfoEntity> extensionInfo)
    {
        this.extensionInfo = extensionInfo;
    }

    public String getRetcode()
    {
        return retcode;
    }

    public List<ExtensionInfoEntity> getExtensionInfo()
    {
        return extensionInfo;
    }

    public static class ExtensionInfoEntity
    {
        /**
         * key : PostPadding
         * value : 3
         */

        private String key;
        private String value;

        public void setKey(String key)
        {
            this.key = key;
        }

        public void setValue(String value)
        {
            this.value = value;
        }

        public String getKey()
        {
            return key;
        }

        public String getValue()
        {
            return value;
        }
    }
}
