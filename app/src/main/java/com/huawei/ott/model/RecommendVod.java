package com.huawei.ott.model;

import java.util.List;

/**
 * Created by Jonas on 2015/12/1.
 */
public class RecommendVod
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
        private String genres;
        private String externalContentCode;
        private String isfavorited;
        private String loyaltyCount;
        private String creditVodSendLoyalty;
        private String produceZone;
        private String visittimes;
        private String country;
        private String awards;
        private String subscriptionType;
        private String keyword;
        private String isPlayable;
        private List<CastsEntity> casts;
        private List<String> genreIds;
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

        public void setGenres(String genres)
        {
            this.genres = genres;
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

        public void setProduceZone(String produceZone)
        {
            this.produceZone = produceZone;
        }

        public void setVisittimes(String visittimes)
        {
            this.visittimes = visittimes;
        }

        public void setCountry(String country)
        {
            this.country = country;
        }

        public void setAwards(String awards)
        {
            this.awards = awards;
        }

        public void setSubscriptionType(String subscriptionType)
        {
            this.subscriptionType = subscriptionType;
        }

        public void setKeyword(String keyword)
        {
            this.keyword = keyword;
        }

        public void setIsPlayable(String isPlayable)
        {
            this.isPlayable = isPlayable;
        }

        public void setCasts(List<CastsEntity> casts)
        {
            this.casts = casts;
        }

        public void setGenreIds(List<String> genreIds)
        {
            this.genreIds = genreIds;
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

        public String getGenres()
        {
            return genres;
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

        public String getProduceZone()
        {
            return produceZone;
        }

        public String getVisittimes()
        {
            return visittimes;
        }

        public String getCountry()
        {
            return country;
        }

        public String getAwards()
        {
            return awards;
        }

        public String getSubscriptionType()
        {
            return subscriptionType;
        }

        public String getKeyword()
        {
            return keyword;
        }

        public String getIsPlayable()
        {
            return isPlayable;
        }

        public List<CastsEntity> getCasts()
        {
            return casts;
        }

        public List<String> getGenreIds()
        {
            return genreIds;
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

        public static class PictureEntity
        {
            /**
             * poster : http://221.226.2.232:17200/EPG/jsp/images/universal/film/poster/fileEntity/20150728/000302/0003020000064303/456b30ec-1683-44a1-b451-f36dd8c69801_1_L.jpg,http://221.226.2.232:17200/EPG/jsp/images/universal/film/poster/fileEntity/20150728/000302/0003020000064303/456b30ec-1683-44a1-b451-f36dd8c69801_2_M.jpg,http://221.226.2.232:17200/EPG/jsp/images/universal/film/poster/fileEntity/20150728/000302/0003020000064303/456b30ec-1683-44a1-b451-f36dd8c69801_3_S.jpg,http://221.226.2.232:17200/EPG/jsp/images/universal/film/poster/fileEntity/20150728/000302/0003020000064303/456b30ec-1683-44a1-b451-f36dd8c69801_0_0.jpg
             * ad : http://221.226.2.232:17200/EPG/jsp/images/universal/film/poster/fileEntity/20150728/000302/0003020000064303/7ccc8fb1-a302-402b-a050-669b702eb793.jpg
             */

            private String poster;
            private String ad;

            public void setPoster(String poster)
            {
                this.poster = poster;
            }

            public void setAd(String ad)
            {
                this.ad = ad;
            }

            public String getPoster()
            {
                return poster;
            }

            public String getAd()
            {
                return ad;
            }
        }

        public static class CastEntity
        {
            /**
             * actor : Willis Bruce,cast15072211,Gere Richard,Tom Cruise
             * director : Salom,Willis Bruce,cast15072211,cast15072111,cast150721,Gere Richard,Tom Cruise
             * adaptor : Salom,Willis Bruce,cast15072211,cast150721,Gere Richard
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
             * castId : 505
             * roleType : 1
             * castName : Salom
             * castCode : 505
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

        public static class MediafilesEntity
        {
            /**
             * id : 000000000000000002817
             * elapsetime : 6860
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
             * externalMediaCode : 000000000000000002817
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
             * value : Break up master
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
