package com.huawei.ott.model;

import java.util.List;

/**
 * <直播节目列表>
 */
public class PlayBillList
{

    private String counttotal;
    private List<PlaybilllistEntity> playbilllist;
    private List<PlaybillVersionEntity> playbillVersion;

    public void setCounttotal(String counttotal)
    {
        this.counttotal = counttotal;
    }

    public void setPlaybilllist(List<PlaybilllistEntity> playbilllist)
    {
        this.playbilllist = playbilllist;
    }

    public void setPlaybillVersion(List<PlaybillVersionEntity> playbillVersion)
    {
        this.playbillVersion = playbillVersion;
    }

    public String getCounttotal()
    {
        return counttotal;
    }

    public List<PlaybilllistEntity> getPlaybilllist()
    {
        return playbilllist;
    }

    public List<PlaybillVersionEntity> getPlaybillVersion()
    {
        return playbillVersion;
    }

    public static class PlaybilllistEntity
    {
        /**
         * id : 23107
         * type : PROGRAM
         * name : 3030-12070835 for english
         * introduce : This is english prgram of 3030- start at 20151207083528
         * foreignsn : 0003020000469774
         * channelid : 82
         * starttime : 2015-12-07 09:35:28 UTC+02:00
         * endtime : 2015-12-07 09:45:28 UTC+02:00
         * ratingid : 3
         * istvod : 1
         * issubscribed : 1
         * ppvsubscribed : 1
         * isppv : 1
         * picture : {}
         * cast : {"actor":"Gere Richard,Tom Cruise","director":"Gere Richard,Willis Bruce","adaptor":"Willis Bruce"}
         * casts : [{"castId":"11","roleType":"1","castName":"Gere Richard","castCode":"11"},{"castId":"11","roleType":"0","castName":"Gere Richard","castCode":"11"},{"castId":"103","roleType":"5","castName":"Willis Bruce","castCode":"103"},{"castId":"103","roleType":"1","castName":"Willis Bruce","castCode":"103"},{"castId":"13","roleType":"0","castName":"Tom Cruise","castCode":"13"}]
         * subNum : 0
         * externalContentCode : XTV10000029920151207083528
         * isnpvr : 0
         * seasonNum : 0
         * programType : program
         * visittimes : 0
         * tvodStatus : 1
         * contentRight : [{"mediaId":"000000000000000002065","businessType":"13","enable":"0"},{"mediaId":"000000000000000002065","businessType":"14","enable":"0"},{"mediaId":"000000000000000002065","businessType":"15","enable":"0"}]
         * isLive : 0
         * recordedMediaIds : ["000000000000000002065"]
         */

        private String id;
        private String type;
        private String name;
        private String introduce;
        private String foreignsn;
        private String channelid;
        private String starttime;
        private String endtime;
        private String ratingid;
        private String istvod;
        private String issubscribed;
        private String ppvsubscribed;
        private String isppv;
        private CastEntity cast;
        private String subNum;
        private String externalContentCode;
        private String isnpvr;
        private String seasonNum;
        private String programType;
        private String visittimes;
        private String tvodStatus;
        private String contentRight;
        private String isLive;
        private List<CastsEntity> casts;
        private List<String> recordedMediaIds;

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

        public void setChannelid(String channelid)
        {
            this.channelid = channelid;
        }

        public void setStarttime(String starttime)
        {
            this.starttime = starttime;
        }

        public void setEndtime(String endtime)
        {
            this.endtime = endtime;
        }

        public void setRatingid(String ratingid)
        {
            this.ratingid = ratingid;
        }

        public void setIstvod(String istvod)
        {
            this.istvod = istvod;
        }

        public void setIssubscribed(String issubscribed)
        {
            this.issubscribed = issubscribed;
        }

        public void setPpvsubscribed(String ppvsubscribed)
        {
            this.ppvsubscribed = ppvsubscribed;
        }

        public void setIsppv(String isppv)
        {
            this.isppv = isppv;
        }

        public void setCast(CastEntity cast)
        {
            this.cast = cast;
        }

        public void setSubNum(String subNum)
        {
            this.subNum = subNum;
        }

        public void setExternalContentCode(String externalContentCode)
        {
            this.externalContentCode = externalContentCode;
        }

        public void setIsnpvr(String isnpvr)
        {
            this.isnpvr = isnpvr;
        }

        public void setSeasonNum(String seasonNum)
        {
            this.seasonNum = seasonNum;
        }

        public void setProgramType(String programType)
        {
            this.programType = programType;
        }

        public void setVisittimes(String visittimes)
        {
            this.visittimes = visittimes;
        }

        public void setTvodStatus(String tvodStatus)
        {
            this.tvodStatus = tvodStatus;
        }

        public void setContentRight(String contentRight)
        {
            this.contentRight = contentRight;
        }

        public void setIsLive(String isLive)
        {
            this.isLive = isLive;
        }

        public void setCasts(List<CastsEntity> casts)
        {
            this.casts = casts;
        }

        public void setRecordedMediaIds(List<String> recordedMediaIds)
        {
            this.recordedMediaIds = recordedMediaIds;
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

        public String getChannelid()
        {
            return channelid;
        }

        public String getStarttime()
        {
            return starttime;
        }

        public String getEndtime()
        {
            return endtime;
        }

        public String getRatingid()
        {
            return ratingid;
        }

        public String getIstvod()
        {
            return istvod;
        }

        public String getIssubscribed()
        {
            return issubscribed;
        }

        public String getPpvsubscribed()
        {
            return ppvsubscribed;
        }

        public String getIsppv()
        {
            return isppv;
        }

        public CastEntity getCast()
        {
            return cast;
        }

        public String getSubNum()
        {
            return subNum;
        }

        public String getExternalContentCode()
        {
            return externalContentCode;
        }

        public String getIsnpvr()
        {
            return isnpvr;
        }

        public String getSeasonNum()
        {
            return seasonNum;
        }

        public String getProgramType()
        {
            return programType;
        }

        public String getVisittimes()
        {
            return visittimes;
        }

        public String getTvodStatus()
        {
            return tvodStatus;
        }

        public String getContentRight()
        {
            return contentRight;
        }

        public String getIsLive()
        {
            return isLive;
        }

        public List<CastsEntity> getCasts()
        {
            return casts;
        }

        public List<String> getRecordedMediaIds()
        {
            return recordedMediaIds;
        }

        public static class CastEntity
        {
            /**
             * actor : Gere Richard,Tom Cruise
             * director : Gere Richard,Willis Bruce
             * adaptor : Willis Bruce
             */

            private String actor;
            private String director;
            private String adaptor;

            public void setActor(String actor)
            {
                this.actor = actor;
            }

            public void setDirector(String director)
            {
                this.director = director;
            }

            public void setAdaptor(String adaptor)
            {
                this.adaptor = adaptor;
            }

            public String getActor()
            {
                return actor;
            }

            public String getDirector()
            {
                return director;
            }

            public String getAdaptor()
            {
                return adaptor;
            }
        }

        public static class CastsEntity
        {
            /**
             * castId : 11
             * roleType : 1
             * castName : Gere Richard
             * castCode : 11
             */

            private String castId;
            private String roleType;
            private String castName;
            private String castCode;

            public void setCastId(String castId)
            {
                this.castId = castId;
            }

            public void setRoleType(String roleType)
            {
                this.roleType = roleType;
            }

            public void setCastName(String castName)
            {
                this.castName = castName;
            }

            public void setCastCode(String castCode)
            {
                this.castCode = castCode;
            }

            public String getCastId()
            {
                return castId;
            }

            public String getRoleType()
            {
                return roleType;
            }

            public String getCastName()
            {
                return castName;
            }

            public String getCastCode()
            {
                return castCode;
            }
        }
    }

    public static class PlaybillVersionEntity
    {
        /**
         * channelId : 82
         * date : 20151130
         * version : 20151201092236
         */

        private String channelId;
        private String date;
        private String version;

        public void setChannelId(String channelId)
        {
            this.channelId = channelId;
        }

        public void setDate(String date)
        {
            this.date = date;
        }

        public void setVersion(String version)
        {
            this.version = version;
        }

        public String getChannelId()
        {
            return channelId;
        }

        public String getDate()
        {
            return date;
        }

        public String getVersion()
        {
            return version;
        }
    }
}
