package com.huawei.ott.model;

import java.util.List;

/**
 * Created by Jonas on 2015/12/1.
 */
public class RecmVodListResp
{


    private String counttotal;
    private List<VodlistEntity> vodlist;

    public void setCounttotal(String counttotal)
    {
        this.counttotal = counttotal;
    }

    public void setVodlist(List<VodlistEntity> vodlist)
    {
        this.vodlist = vodlist;
    }

    public String getCounttotal()
    {
        return counttotal;
    }

    public List<VodlistEntity> getVodlist()
    {
        return vodlist;
    }

    public static class VodlistEntity
    {
        /**
         * id : 184
         * type : VIDEO_VOD
         * name : OTT_NOCA_Series@#W#%$%!#@$#$%$#%wwrewer
         * foreignsn : 00030scoresum20000022
         * sitcomnum : 40
         * price : -1
         * vodtype : 1
         * ratingid : 2
         * rentperiod : 0
         * starttime : 20150716082350
         * endtime : 20361231155959
         * issubscribed : 0
         * producedate : 2012-08-15
         * statictimes : 2
         * scoresum : 15
         * averagescore : 8
         * picture : {"poster":"http://221.226.2.232:17200/EPG/jsp/images/universal/film/poster/fileEntity/20150725/000302/0003020000022/44c72165-233c-4441-a397-5b1c05863081_1_L.jpg,http://221.226.2.232:17200/EPG/jsp/images/universal/film/poster/fileEntity/20150725/000302/0003020000022/44c72165-233c-4441-a397-5b1c05863081_2_M.jpg,http://221.226.2.232:17200/EPG/jsp/images/universal/film/poster/fileEntity/20150725/000302/0003020000022/44c72165-233c-4441-a397-5b1c05863081_3_S.jpg,http://221.226.2.232:17200/EPG/jsp/images/universal/film/poster/fileEntity/20150725/000302/0003020000022/44c72165-233c-4441-a397-5b1c05863081_0_0.jpg","ad":"http://221.226.2.232:17200/EPG/jsp/images/universal/film/poster/fileEntity/CMS_TEMP/ums/298cfec7-e77b-46bc-93fb-5ba76ae50ab2.png,http://221.226.2.232:17200/EPG/jsp/images/universal/film/poster/fileEntity/CMS_TEMP/ums/82c98f38-110c-44d1-9c32-8e0f87b666a0.png"}
         * cast : {"actor":"Willis Bruce,kpi-en","director":"Willis Bruce,A","adaptor":"Gere Richard,J"}
         * casts : [{"castId":"103","roleType":"1","castName":"Willis Bruce","castCode":"103"},{"castId":"269","roleType":"1","castName":"A","castCode":"269"},{"castId":"103","roleType":"0","castName":"Willis Bruce","castCode":"103"},{"castId":"502","roleType":"0","castName":"kpi-en","castCode":"502"},{"castId":"11","roleType":"5","castName":"Gere Richard","castCode":"11"},{"castId":"279","roleType":"5","castName":"J","castCode":"279"},{"castId":"302","roleType":"6","castName":"Do_You_Remember_Me_?","castCode":"302"},{"castId":"269","roleType":"6","castName":"A","castCode":"269"}]
         * genres : Musical
         * externalContentCode : 0003020000022
         * genreIds : ["1922"]
         * isfavorited : 1
         * loyaltyCount : 0
         * creditVodSendLoyalty : 0
         * visittimes : 96039
         * awards : OTT_NOCA_Series
         * subscriptionType : 2
         * seriesType : 0
         * names : [{"key":"en","value":"OTT_NOCA_Series@#W#%$%!#@$#$%$#%wwrewer"}]
         * keyword : OTT_NOCA_Series
         * isPlayable : 1
         * categoryIds : ["series"]
         * company : 102
         */

        private String id;
        private String type;
        private String name;
        private String foreignsn;
        private String sitcomnum;
        private String price;
        private String vodtype;
        private String ratingid;
        private String rentperiod;
        private String starttime;
        private String endtime;
        private String issubscribed;
        private String producedate;
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
        private String visittimes;
        private String awards;
        private String subscriptionType;
        private String seriesType;
        private String keyword;
        private String isPlayable;
        private String company;
        private List<CastsEntity> casts;
        private List<String> genreIds;
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

        public void setProducedate(String producedate)
        {
            this.producedate = producedate;
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

        public void setVisittimes(String visittimes)
        {
            this.visittimes = visittimes;
        }

        public void setAwards(String awards)
        {
            this.awards = awards;
        }

        public void setSubscriptionType(String subscriptionType)
        {
            this.subscriptionType = subscriptionType;
        }

        public void setSeriesType(String seriesType)
        {
            this.seriesType = seriesType;
        }

        public void setKeyword(String keyword)
        {
            this.keyword = keyword;
        }

        public void setIsPlayable(String isPlayable)
        {
            this.isPlayable = isPlayable;
        }

        public void setCompany(String company)
        {
            this.company = company;
        }

        public void setCasts(List<CastsEntity> casts)
        {
            this.casts = casts;
        }

        public void setGenreIds(List<String> genreIds)
        {
            this.genreIds = genreIds;
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

        public String getProducedate()
        {
            return producedate;
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

        public String getVisittimes()
        {
            return visittimes;
        }

        public String getAwards()
        {
            return awards;
        }

        public String getSubscriptionType()
        {
            return subscriptionType;
        }

        public String getSeriesType()
        {
            return seriesType;
        }

        public String getKeyword()
        {
            return keyword;
        }

        public String getIsPlayable()
        {
            return isPlayable;
        }

        public String getCompany()
        {
            return company;
        }

        public List<CastsEntity> getCasts()
        {
            return casts;
        }

        public List<String> getGenreIds()
        {
            return genreIds;
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
             * actor : Willis Bruce,kpi-en
             * director : Willis Bruce,A
             * adaptor : Gere Richard,J
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
             * castId : 103
             * roleType : 1
             * castName : Willis Bruce
             * castCode : 103
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

        public static class NamesEntity
        {
            /**
             * key : en
             * value : OTT_NOCA_Series@#W#%$%!#@$#$%$#%wwrewer
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
