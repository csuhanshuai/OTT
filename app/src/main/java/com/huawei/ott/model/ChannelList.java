package com.huawei.ott.model;

import java.util.List;

/**
 * <頻道列表>
 */
public class ChannelList
{

    private String counttotal;
    private List<ChannellistEntity> channellist;

    public void setCounttotal(String counttotal)
    {
        this.counttotal = counttotal;
    }

    public void setChannellist(List<ChannellistEntity> channellist)
    {
        this.channellist = channellist;
    }

    public String getCounttotal()
    {
        return counttotal;
    }

    public List<ChannellistEntity> getChannellist()
    {
        return channellist;
    }

    public static class ChannellistEntity
    {
        /**
         * id : 81
         * foreignsn : XTV100000298
         * type : VIDEO_CHANNEL
         * name : TVOD-TSTV-CA-PG
         * introduce : TVOD-TSTV-CA-PG
         * mediaid : 000000000000000002062
         * previewenable : 0
         * previewlength : 0
         * previewcount : 3
         * haspip : 0
         * pipfccenable : 0
         * status : 3
         * channo : 1
         * fccenable : 0
         * encrypt : 1
         * bitrate : 0
         * playurl : http://221.226.2.232:17600/PLTV/88888888/224/3221225595/index.m3u8?rrsip=221.226.2.232:17600&zoneoffset=0&servicetype=1&icpid=&accounttype=1&limitflux=-1&limitdur=-1&accountinfo=%2C1000038000%2C218.17.167.106%2C20151201135906%2CXTV100000298%2C872E050AF5727DAB38BD6B31C4D9AFEF%2C-1%2C0%2C1%2C%2C%2C2%2C%2C%2C%2C2%2Cott06%2C0%2Cott06%2Cf8a45f1f0d59%2C2%2CEND&GuardEncType=2|http://221.226.2.232:17600/PLTV/88888888/224/3221225595/index.m3u8?rrsip=221.226.2.232:17600&zoneoffset=0&servicetype=2&icpid=&accounttype=1&limitflux=-1&limitdur=-1&accountinfo=%2C1000038000%2C218.17.167.106%2C20151201135906%2CXTV100000298%2C872E050AF5727DAB38BD6B31C4D9AFEF%2C-1%2C0%2C1%2C%2C%2C7%2C%2C%2C%2C4%2Cott06%2C0%2Cott06%2Cf8a45f1f0d59%2C2%2CEND&GuardEncType=2
         * definition : 0
         * iscpvr : 0
         * ispltv : 1
         * pltvlength : 7200
         * istvod : 1
         * isfavorited : 0
         * islocaltimeshift : 0
         * ratingid : 1
         * issubscribed : 0
         * recordlength : 504000
         * isppv : 1
         * cpvrsubscribed : 0
         * isnpvr : 0
         * npvrlength : 0
         * npvrsubscribed : 0
         * slstype : 0
         * pltvsubscribed : 0
         * localtimeshiftsubscribed : 0
         * tvodsubscribed : 0
         * pipbitrate : 0
         * HDCPEnable : 0
         * macrovision : 0
         * externalContentCode : XTV100000298
         * externalMediaCode : urn:Huawei:liveTV:XTV100000298:E100000463
         * logo : {"url":"http://221.226.2.232:17200/EPG/jsp/images/universal/film/logo/fileEntity/20150721/000302/XTV100000298/11047ff2-dfc4-493d-a772-60e29e991b5b.jpg","size":"64,64","location":"10,10","display":"10,10"}
         * picture : {}
         * statictimes : 0
         * averagescore : 0
         * supportTerminal : 1,2,3
         * fileFormat : 2
         * CGMSA : 0
         * BTVType : 1
         * playEnable : 1
         * genreIds : ["2020"]
         * videoCodec : H.264
         * catchupMediaId : 000000000000000002062
         * pltvMediaId : 000000000000000002062
         * price : -1
         * FECEnable : 0
         * pipFECEnable : 0
         * serviceAnnouncementMethod : 1
         * mbmsPushMode : 4
         * mbmsBearerType : 2
         * fluteOutputStreamType : 0
         * fluteMulticastPort : 0
         * mbmsCacheControl : {}
         * priceType : [{"key":"BTV","value":"0"}]
         * isLoyalty : 0
         * dimension : 2
         * formatOf3D : 1
         */

        private String id;
        private String foreignsn;
        private String type;
        private String name;
        private String introduce;
        private String mediaid;
        private String previewenable;
        private String previewlength;
        private String previewcount;
        private String haspip;
        private String pipfccenable;
        private String status;
        private String channo;
        private String fccenable;
        private String encrypt;
        private String bitrate;
        private String playurl;
        private String definition;
        private String iscpvr;
        private String ispltv;
        private String pltvlength;
        private String istvod;
        private String isfavorited;
        private String islocaltimeshift;
        private String ratingid;
        private String issubscribed;
        private String recordlength;
        private String isppv;
        private String cpvrsubscribed;
        private String isnpvr;
        private String npvrlength;
        private String npvrsubscribed;
        private String slstype;
        private String pltvsubscribed;
        private String localtimeshiftsubscribed;
        private String tvodsubscribed;
        private String pipbitrate;
        private String HDCPEnable;
        private String macrovision;
        private String externalContentCode;
        private String externalMediaCode;
        private LogoEntity logo;
        private String statictimes;
        private String averagescore;
        private String supportTerminal;
        private String fileFormat;
        private String CGMSA;
        private String BTVType;
        private String playEnable;
        private String videoCodec;
        private String catchupMediaId;
        private String pltvMediaId;
        private String price;
        private String FECEnable;
        private String pipFECEnable;
        private String serviceAnnouncementMethod;
        private String mbmsPushMode;
        private String mbmsBearerType;
        private String fluteOutputStreamType;
        private String fluteMulticastPort;
        private MbmsCacheControlEntity mbmsCacheControl;
        private String isLoyalty;
        private String dimension;
        private String formatOf3D;
        private List<String> genreIds;
        private List<PriceTypeEntity> priceType;

        public void setId(String id)
        {
            this.id = id;
        }

        public void setForeignsn(String foreignsn)
        {
            this.foreignsn = foreignsn;
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

        public void setMediaid(String mediaid)
        {
            this.mediaid = mediaid;
        }

        public void setPreviewenable(String previewenable)
        {
            this.previewenable = previewenable;
        }

        public void setPreviewlength(String previewlength)
        {
            this.previewlength = previewlength;
        }

        public void setPreviewcount(String previewcount)
        {
            this.previewcount = previewcount;
        }

        public void setHaspip(String haspip)
        {
            this.haspip = haspip;
        }

        public void setPipfccenable(String pipfccenable)
        {
            this.pipfccenable = pipfccenable;
        }

        public void setStatus(String status)
        {
            this.status = status;
        }

        public void setChanno(String channo)
        {
            this.channo = channo;
        }

        public void setFccenable(String fccenable)
        {
            this.fccenable = fccenable;
        }

        public void setEncrypt(String encrypt)
        {
            this.encrypt = encrypt;
        }

        public void setBitrate(String bitrate)
        {
            this.bitrate = bitrate;
        }

        public void setPlayurl(String playurl)
        {
            this.playurl = playurl;
        }

        public void setDefinition(String definition)
        {
            this.definition = definition;
        }

        public void setIscpvr(String iscpvr)
        {
            this.iscpvr = iscpvr;
        }

        public void setIspltv(String ispltv)
        {
            this.ispltv = ispltv;
        }

        public void setPltvlength(String pltvlength)
        {
            this.pltvlength = pltvlength;
        }

        public void setIstvod(String istvod)
        {
            this.istvod = istvod;
        }

        public void setIsfavorited(String isfavorited)
        {
            this.isfavorited = isfavorited;
        }

        public void setIslocaltimeshift(String islocaltimeshift)
        {
            this.islocaltimeshift = islocaltimeshift;
        }

        public void setRatingid(String ratingid)
        {
            this.ratingid = ratingid;
        }

        public void setIssubscribed(String issubscribed)
        {
            this.issubscribed = issubscribed;
        }

        public void setRecordlength(String recordlength)
        {
            this.recordlength = recordlength;
        }

        public void setIsppv(String isppv)
        {
            this.isppv = isppv;
        }

        public void setCpvrsubscribed(String cpvrsubscribed)
        {
            this.cpvrsubscribed = cpvrsubscribed;
        }

        public void setIsnpvr(String isnpvr)
        {
            this.isnpvr = isnpvr;
        }

        public void setNpvrlength(String npvrlength)
        {
            this.npvrlength = npvrlength;
        }

        public void setNpvrsubscribed(String npvrsubscribed)
        {
            this.npvrsubscribed = npvrsubscribed;
        }

        public void setSlstype(String slstype)
        {
            this.slstype = slstype;
        }

        public void setPltvsubscribed(String pltvsubscribed)
        {
            this.pltvsubscribed = pltvsubscribed;
        }

        public void setLocaltimeshiftsubscribed(String localtimeshiftsubscribed)
        {
            this.localtimeshiftsubscribed = localtimeshiftsubscribed;
        }

        public void setTvodsubscribed(String tvodsubscribed)
        {
            this.tvodsubscribed = tvodsubscribed;
        }

        public void setPipbitrate(String pipbitrate)
        {
            this.pipbitrate = pipbitrate;
        }

        public void setHDCPEnable(String HDCPEnable)
        {
            this.HDCPEnable = HDCPEnable;
        }

        public void setMacrovision(String macrovision)
        {
            this.macrovision = macrovision;
        }

        public void setExternalContentCode(String externalContentCode)
        {
            this.externalContentCode = externalContentCode;
        }

        public void setExternalMediaCode(String externalMediaCode)
        {
            this.externalMediaCode = externalMediaCode;
        }

        public void setLogo(LogoEntity logo)
        {
            this.logo = logo;
        }

        public void setStatictimes(String statictimes)
        {
            this.statictimes = statictimes;
        }

        public void setAveragescore(String averagescore)
        {
            this.averagescore = averagescore;
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

        public void setBTVType(String BTVType)
        {
            this.BTVType = BTVType;
        }

        public void setPlayEnable(String playEnable)
        {
            this.playEnable = playEnable;
        }

        public void setVideoCodec(String videoCodec)
        {
            this.videoCodec = videoCodec;
        }

        public void setCatchupMediaId(String catchupMediaId)
        {
            this.catchupMediaId = catchupMediaId;
        }

        public void setPltvMediaId(String pltvMediaId)
        {
            this.pltvMediaId = pltvMediaId;
        }

        public void setPrice(String price)
        {
            this.price = price;
        }

        public void setFECEnable(String FECEnable)
        {
            this.FECEnable = FECEnable;
        }

        public void setPipFECEnable(String pipFECEnable)
        {
            this.pipFECEnable = pipFECEnable;
        }

        public void setServiceAnnouncementMethod(String serviceAnnouncementMethod)
        {
            this.serviceAnnouncementMethod = serviceAnnouncementMethod;
        }

        public void setMbmsPushMode(String mbmsPushMode)
        {
            this.mbmsPushMode = mbmsPushMode;
        }

        public void setMbmsBearerType(String mbmsBearerType)
        {
            this.mbmsBearerType = mbmsBearerType;
        }

        public void setFluteOutputStreamType(String fluteOutputStreamType)
        {
            this.fluteOutputStreamType = fluteOutputStreamType;
        }

        public void setFluteMulticastPort(String fluteMulticastPort)
        {
            this.fluteMulticastPort = fluteMulticastPort;
        }

        public void setMbmsCacheControl(MbmsCacheControlEntity mbmsCacheControl)
        {
            this.mbmsCacheControl = mbmsCacheControl;
        }

        public void setIsLoyalty(String isLoyalty)
        {
            this.isLoyalty = isLoyalty;
        }

        public void setDimension(String dimension)
        {
            this.dimension = dimension;
        }

        public void setFormatOf3D(String formatOf3D)
        {
            this.formatOf3D = formatOf3D;
        }

        public void setGenreIds(List<String> genreIds)
        {
            this.genreIds = genreIds;
        }

        public void setPriceType(List<PriceTypeEntity> priceType)
        {
            this.priceType = priceType;
        }

        public String getId()
        {
            return id;
        }

        public String getForeignsn()
        {
            return foreignsn;
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

        public String getMediaid()
        {
            return mediaid;
        }

        public String getPreviewenable()
        {
            return previewenable;
        }

        public String getPreviewlength()
        {
            return previewlength;
        }

        public String getPreviewcount()
        {
            return previewcount;
        }

        public String getHaspip()
        {
            return haspip;
        }

        public String getPipfccenable()
        {
            return pipfccenable;
        }

        public String getStatus()
        {
            return status;
        }

        public String getChanno()
        {
            return channo;
        }

        public String getFccenable()
        {
            return fccenable;
        }

        public String getEncrypt()
        {
            return encrypt;
        }

        public String getBitrate()
        {
            return bitrate;
        }

        public String getPlayurl()
        {
            return playurl;
        }

        public String getDefinition()
        {
            return definition;
        }

        public String getIscpvr()
        {
            return iscpvr;
        }

        public String getIspltv()
        {
            return ispltv;
        }

        public String getPltvlength()
        {
            return pltvlength;
        }

        public String getIstvod()
        {
            return istvod;
        }

        public String getIsfavorited()
        {
            return isfavorited;
        }

        public String getIslocaltimeshift()
        {
            return islocaltimeshift;
        }

        public String getRatingid()
        {
            return ratingid;
        }

        public String getIssubscribed()
        {
            return issubscribed;
        }

        public String getRecordlength()
        {
            return recordlength;
        }

        public String getIsppv()
        {
            return isppv;
        }

        public String getCpvrsubscribed()
        {
            return cpvrsubscribed;
        }

        public String getIsnpvr()
        {
            return isnpvr;
        }

        public String getNpvrlength()
        {
            return npvrlength;
        }

        public String getNpvrsubscribed()
        {
            return npvrsubscribed;
        }

        public String getSlstype()
        {
            return slstype;
        }

        public String getPltvsubscribed()
        {
            return pltvsubscribed;
        }

        public String getLocaltimeshiftsubscribed()
        {
            return localtimeshiftsubscribed;
        }

        public String getTvodsubscribed()
        {
            return tvodsubscribed;
        }

        public String getPipbitrate()
        {
            return pipbitrate;
        }

        public String getHDCPEnable()
        {
            return HDCPEnable;
        }

        public String getMacrovision()
        {
            return macrovision;
        }

        public String getExternalContentCode()
        {
            return externalContentCode;
        }

        public String getExternalMediaCode()
        {
            return externalMediaCode;
        }

        public LogoEntity getLogo()
        {
            return logo;
        }

        public String getStatictimes()
        {
            return statictimes;
        }

        public String getAveragescore()
        {
            return averagescore;
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

        public String getBTVType()
        {
            return BTVType;
        }

        public String getPlayEnable()
        {
            return playEnable;
        }

        public String getVideoCodec()
        {
            return videoCodec;
        }

        public String getCatchupMediaId()
        {
            return catchupMediaId;
        }

        public String getPltvMediaId()
        {
            return pltvMediaId;
        }

        public String getPrice()
        {
            return price;
        }

        public String getFECEnable()
        {
            return FECEnable;
        }

        public String getPipFECEnable()
        {
            return pipFECEnable;
        }

        public String getServiceAnnouncementMethod()
        {
            return serviceAnnouncementMethod;
        }

        public String getMbmsPushMode()
        {
            return mbmsPushMode;
        }

        public String getMbmsBearerType()
        {
            return mbmsBearerType;
        }

        public String getFluteOutputStreamType()
        {
            return fluteOutputStreamType;
        }

        public String getFluteMulticastPort()
        {
            return fluteMulticastPort;
        }

        public MbmsCacheControlEntity getMbmsCacheControl()
        {
            return mbmsCacheControl;
        }

        public String getIsLoyalty()
        {
            return isLoyalty;
        }

        public String getDimension()
        {
            return dimension;
        }

        public String getFormatOf3D()
        {
            return formatOf3D;
        }

        public List<String> getGenreIds()
        {
            return genreIds;
        }

        public List<PriceTypeEntity> getPriceType()
        {
            return priceType;
        }

        public static class LogoEntity
        {
            /**
             * url : http://221.226.2.232:17200/EPG/jsp/images/universal/film/logo/fileEntity/20150721/000302/XTV100000298/11047ff2-dfc4-493d-a772-60e29e991b5b.jpg
             * size : 64,64
             * location : 10,10
             * display : 10,10
             */

            private String url;
            private String size;
            private String location;
            private String display;

            public void setUrl(String url)
            {
                this.url = url;
            }

            public void setSize(String size)
            {
                this.size = size;
            }

            public void setLocation(String location)
            {
                this.location = location;
            }

            public void setDisplay(String display)
            {
                this.display = display;
            }

            public String getUrl()
            {
                return url;
            }

            public String getSize()
            {
                return size;
            }

            public String getLocation()
            {
                return location;
            }

            public String getDisplay()
            {
                return display;
            }
        }

        public static class MbmsCacheControlEntity {}

        public static class PriceTypeEntity
        {
            /**
             * key : BTV
             * value : 0
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
