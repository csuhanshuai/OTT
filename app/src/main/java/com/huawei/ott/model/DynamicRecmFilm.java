package com.huawei.ott.model;

import java.util.List;

/**
 * Created by Jonas on 2015/12/1.
 */
public class DynamicRecmFilm
{


    private String counttotal;
    private List<ContentlistEntity> contentlist;

    public void setCounttotal(String counttotal)
    {
        this.counttotal = counttotal;
    }

    public void setContentlist(List<ContentlistEntity> contentlist)
    {
        this.contentlist = contentlist;
    }

    public String getCounttotal()
    {
        return counttotal;
    }

    public List<ContentlistEntity> getContentlist()
    {
        return contentlist;
    }

    public static class ContentlistEntity
    {
        /**
         * id : 402
         * type : VIDEO_VOD
         * name : movieca
         * introduce : movieca kgdkfgkkfkdgkfkd fdkgl;dfkl; f gkl;dfkgl;kl; fdg dfkg kll;dfkagl;kdf;12343jkl;s kfdg dfadl;sfl;;adf; 134;kfl;akdf  kdlkfasdkfk sdfkld;lfk;dk;f 12rkwe;fk;sdkfk;dkv;z ;vkadf;dkf;1，现象描述 青海移动OTT T3000服务器有一块硬盘红灯常亮 2，可能原因 硬盘故障。 3，定位思路 收集T3000硬件日志，提供给T3000硬件维护人员分析。 使用一键日志收集带内日志。 收集设备带外日志，收集指导如下： 步骤 1 登录ISM界面 在IE 浏览器中输入https://xxx.xxx.xxx.xxx，在登录界面的UserName 和Password 栏分别输入用户名和密码，单击“Login”按钮进入。xxx.xxx.xxx.xxx 表示设备的BMC 管理口IP 地址； 注意：debug 界面的用户名和密码是ISM 界面具有Administator”级别的用户名和密码，只有ISM 界面具有Administator”级别的用户才可以登陆debug 界面。 ISM 界面和debug 界面出厂默认的用户名是admin，密码是Admin@storage。 步骤
         * foreignsn : 0003020000064301
         * sitcomnum : 0
         * price : -1
         * vodtype : 0
         * ratingid : 3
         * rentperiod : 0
         * starttime : 20150728054905
         * endtime : 20361231155959
         * issubscribed : 0
         * statictimes : 1
         * scoresum : 6
         * averagescore : 6
         * picture : {"icon":"http://221.226.2.232:17200/EPG/jsp/images/universal/film/poster/fileEntity/20150730/000302/kpi_movie570/7ac1f3b9-ccd1-40c9-8ce5-3f078ba15cfa.jpg"}
         * cast : {}
         * externalContentCode : 0003020000064301
         * mediafiles : [{"id":"000000000000000002815","elapsetime":"220","bitrate":"1385","isdownload":"0","definition":"2","HDCPEnable":"0","macrovision":"0","dimension":"2","formatOf3D":"1","supportTerminal":"1,2,3","fileFormat":"2","CGMSA":"0","encrypt":"1","analogOutputEnable":"1","videoCodec":"3","spId4VIP":"000302","externalMediaCode":"000000000000000002815","multiBitRate":"0"}]
         * isfavorited : 0
         * loyaltyCount : 0
         * creditVodSendLoyalty : 0
         * visittimes : 1679
         * subscriptionType : 2
         * names : [{"key":"en","value":"movieca"}]
         * isPlayable : 1
         * categoryIds : ["Entertain","movie"]
         */

        private String id;
        private String type;
        private String name;
        private String introduce;
        private String foreignsn;
        private String sitcomnum;
        private String price;
        private String vodtype;
        private String ratingid;
        private String rentperiod;
        private String starttime;
        private String endtime;
        private String issubscribed;
        private String statictimes;
        private String scoresum;
        private String averagescore;
        private PictureEntity picture;
        private CastEntity cast;
        private String externalContentCode;
        private String isfavorited;
        private String loyaltyCount;
        private String creditVodSendLoyalty;
        private String visittimes;
        private String subscriptionType;
        private String isPlayable;
        private List<MediafilesEntity> mediafiles;
        private List<NamesEntity> names;
        private List<String> categoryIds;

        public void setId(String id)
        {
            this.id = id;
        }

        public void setType(String type)
        {
            this.type = type;
        }

        public void setName(String name)
        {
            this.name = name;
        }

        public void setIntroduce(String introduce)
        {
            this.introduce = introduce;
        }

        public void setForeignsn(String foreignsn)
        {
            this.foreignsn = foreignsn;
        }

        public void setSitcomnum(String sitcomnum)
        {
            this.sitcomnum = sitcomnum;
        }

        public void setPrice(String price)
        {
            this.price = price;
        }

        public void setVodtype(String vodtype)
        {
            this.vodtype = vodtype;
        }

        public void setRatingid(String ratingid)
        {
            this.ratingid = ratingid;
        }

        public void setRentperiod(String rentperiod)
        {
            this.rentperiod = rentperiod;
        }

        public void setStarttime(String starttime)
        {
            this.starttime = starttime;
        }

        public void setEndtime(String endtime)
        {
            this.endtime = endtime;
        }

        public void setIssubscribed(String issubscribed)
        {
            this.issubscribed = issubscribed;
        }

        public void setStatictimes(String statictimes)
        {
            this.statictimes = statictimes;
        }

        public void setScoresum(String scoresum)
        {
            this.scoresum = scoresum;
        }

        public void setAveragescore(String averagescore)
        {
            this.averagescore = averagescore;
        }

        public void setPicture(PictureEntity picture)
        {
            this.picture = picture;
        }

        public void setCast(CastEntity cast)
        {
            this.cast = cast;
        }

        public void setExternalContentCode(String externalContentCode)
        {
            this.externalContentCode = externalContentCode;
        }

        public void setIsfavorited(String isfavorited)
        {
            this.isfavorited = isfavorited;
        }

        public void setLoyaltyCount(String loyaltyCount)
        {
            this.loyaltyCount = loyaltyCount;
        }

        public void setCreditVodSendLoyalty(String creditVodSendLoyalty)
        {
            this.creditVodSendLoyalty = creditVodSendLoyalty;
        }

        public void setVisittimes(String visittimes)
        {
            this.visittimes = visittimes;
        }

        public void setSubscriptionType(String subscriptionType)
        {
            this.subscriptionType = subscriptionType;
        }

        public void setIsPlayable(String isPlayable)
        {
            this.isPlayable = isPlayable;
        }

        public void setMediafiles(List<MediafilesEntity> mediafiles)
        {
            this.mediafiles = mediafiles;
        }

        public void setNames(List<NamesEntity> names)
        {
            this.names = names;
        }

        public void setCategoryIds(List<String> categoryIds)
        {
            this.categoryIds = categoryIds;
        }

        public String getId()
        {
            return id;
        }

        public String getType()
        {
            return type;
        }

        public String getName()
        {
            return name;
        }

        public String getIntroduce()
        {
            return introduce;
        }

        public String getForeignsn()
        {
            return foreignsn;
        }

        public String getSitcomnum()
        {
            return sitcomnum;
        }

        public String getPrice()
        {
            return price;
        }

        public String getVodtype()
        {
            return vodtype;
        }

        public String getRatingid()
        {
            return ratingid;
        }

        public String getRentperiod()
        {
            return rentperiod;
        }

        public String getStarttime()
        {
            return starttime;
        }

        public String getEndtime()
        {
            return endtime;
        }

        public String getIssubscribed()
        {
            return issubscribed;
        }

        public String getStatictimes()
        {
            return statictimes;
        }

        public String getScoresum()
        {
            return scoresum;
        }

        public String getAveragescore()
        {
            return averagescore;
        }

        public PictureEntity getPicture()
        {
            return picture;
        }

        public CastEntity getCast()
        {
            return cast;
        }

        public String getExternalContentCode()
        {
            return externalContentCode;
        }

        public String getIsfavorited()
        {
            return isfavorited;
        }

        public String getLoyaltyCount()
        {
            return loyaltyCount;
        }

        public String getCreditVodSendLoyalty()
        {
            return creditVodSendLoyalty;
        }

        public String getVisittimes()
        {
            return visittimes;
        }

        public String getSubscriptionType()
        {
            return subscriptionType;
        }

        public String getIsPlayable()
        {
            return isPlayable;
        }

        public List<MediafilesEntity> getMediafiles()
        {
            return mediafiles;
        }

        public List<NamesEntity> getNames()
        {
            return names;
        }

        public List<String> getCategoryIds()
        {
            return categoryIds;
        }

        //            {"icon":"http://221.226.2.232:17200/EPG/jsp/images/universal/film/poster/fileEntity/20150730/000302/kpi_movie650/753066b3-12b7-4b15-bc89-192d3aebe7e5.jpg"}
        public static class PictureEntity
        {
            private String icon;

            public String getIcon()
            {
                return icon;
            }

            public void setIcon(String icon)
            {
                this.icon = icon;
            }
        }

        public static class CastEntity {}

        public static class MediafilesEntity
        {
            /**
             * id : 000000000000000002815
             * elapsetime : 220
             * bitrate : 1385
             * isdownload : 0
             * definition : 2
             * HDCPEnable : 0
             * macrovision : 0
             * dimension : 2
             * formatOf3D : 1
             * supportTerminal : 1,2,3
             * fileFormat : 2
             * CGMSA : 0
             * encrypt : 1
             * analogOutputEnable : 1
             * videoCodec : 3
             * spId4VIP : 000302
             * externalMediaCode : 000000000000000002815
             * multiBitRate : 0
             */

            private String id;
            private String elapsetime;
            private String bitrate;
            private String isdownload;
            private String definition;
            private String HDCPEnable;
            private String macrovision;
            private String dimension;
            private String formatOf3D;
            private String supportTerminal;
            private String fileFormat;
            private String CGMSA;
            private String encrypt;
            private String analogOutputEnable;
            private String videoCodec;
            private String spId4VIP;
            private String externalMediaCode;
            private String multiBitRate;

            public void setId(String id)
            {
                this.id = id;
            }

            public void setElapsetime(String elapsetime)
            {
                this.elapsetime = elapsetime;
            }

            public void setBitrate(String bitrate)
            {
                this.bitrate = bitrate;
            }

            public void setIsdownload(String isdownload)
            {
                this.isdownload = isdownload;
            }

            public void setDefinition(String definition)
            {
                this.definition = definition;
            }

            public void setHDCPEnable(String HDCPEnable)
            {
                this.HDCPEnable = HDCPEnable;
            }

            public void setMacrovision(String macrovision)
            {
                this.macrovision = macrovision;
            }

            public void setDimension(String dimension)
            {
                this.dimension = dimension;
            }

            public void setFormatOf3D(String formatOf3D)
            {
                this.formatOf3D = formatOf3D;
            }

            public void setSupportTerminal(String supportTerminal)
            {
                this.supportTerminal = supportTerminal;
            }

            public void setFileFormat(String fileFormat)
            {
                this.fileFormat = fileFormat;
            }

            public void setCGMSA(String CGMSA)
            {
                this.CGMSA = CGMSA;
            }

            public void setEncrypt(String encrypt)
            {
                this.encrypt = encrypt;
            }

            public void setAnalogOutputEnable(String analogOutputEnable)
            {
                this.analogOutputEnable = analogOutputEnable;
            }

            public void setVideoCodec(String videoCodec)
            {
                this.videoCodec = videoCodec;
            }

            public void setSpId4VIP(String spId4VIP)
            {
                this.spId4VIP = spId4VIP;
            }

            public void setExternalMediaCode(String externalMediaCode)
            {
                this.externalMediaCode = externalMediaCode;
            }

            public void setMultiBitRate(String multiBitRate)
            {
                this.multiBitRate = multiBitRate;
            }

            public String getId()
            {
                return id;
            }

            public String getElapsetime()
            {
                return elapsetime;
            }

            public String getBitrate()
            {
                return bitrate;
            }

            public String getIsdownload()
            {
                return isdownload;
            }

            public String getDefinition()
            {
                return definition;
            }

            public String getHDCPEnable()
            {
                return HDCPEnable;
            }

            public String getMacrovision()
            {
                return macrovision;
            }

            public String getDimension()
            {
                return dimension;
            }

            public String getFormatOf3D()
            {
                return formatOf3D;
            }

            public String getSupportTerminal()
            {
                return supportTerminal;
            }

            public String getFileFormat()
            {
                return fileFormat;
            }

            public String getCGMSA()
            {
                return CGMSA;
            }

            public String getEncrypt()
            {
                return encrypt;
            }

            public String getAnalogOutputEnable()
            {
                return analogOutputEnable;
            }

            public String getVideoCodec()
            {
                return videoCodec;
            }

            public String getSpId4VIP()
            {
                return spId4VIP;
            }

            public String getExternalMediaCode()
            {
                return externalMediaCode;
            }

            public String getMultiBitRate()
            {
                return multiBitRate;
            }
        }

        public static class NamesEntity
        {
            /**
             * key : en
             * value : movieca
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
}
