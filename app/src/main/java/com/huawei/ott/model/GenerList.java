package com.huawei.ott.model;

import java.util.List;

/**
 * <频道类型>
 */
public class GenerList
{
    /**
     * retcode : 0
     * retmsg : Get genre list success!
     * genres : [{"genreId":"2118","genreType":"1","genreName":"Action"},{"genreId":"2018","genreType":"7","genreName":"Local Channel"},{"genreId":"1305","genreType":"2","genreName":"Sports"},{"genreId":"2020","genreType":"7","genreName":"News Channel"},{"genreId":"2024","genreType":"7","genreName":"Series Program"},{"genreId":"2216","genreType":"2","genreName":"Love"},{"genreId":"2021","genreType":"7","genreName":"Children Channel"},{"genreId":"2015","genreType":"2","genreName":"Islamic"},{"genreId":"2023","genreType":"7","genreName":"Program Genre"},{"genreId":"2022","genreType":"7","genreName":"Movie Channel"},{"genreId":"1908","genreType":"2","genreName":"Documentary"},{"genreId":"2017","genreType":"7","genreName":"Channel Genre"},{"genreId":"2316","genreType":"2","genreName":"war"},{"genreId":"2116","genreType":"1","genreName":"BG MOViES"},{"genreId":"2119","genreType":"1","genreName":"LOVE"},{"genreId":"1922","genreType":"2","genreName":"Musical"},{"genreId":"2019","genreType":"7","genreName":"National Channel"},{"genreId":"2025","genreType":"7","genreName":"Season Program"},{"genreId":"2319","genreType":"2","genreName":"ADI_VOD"},{"genreId":"2320","genreType":"7","genreName":"ADI_PROGRAM_2"},{"genreId":"2027","genreType":"7","genreName":"Common Program"},{"genreId":"2117","genreType":"1","genreName":"FOR Kids"},{"genreId":"2318","genreType":"7","genreName":"ADI_PROGRAM"},{"genreId":"2026","genreType":"7","genreName":"Sports Program"}]
     */

    private String retcode;
    private String retmsg;
    private List<GenresEntity> genres;

    public void setRetcode(String retcode)
    {
        this.retcode = retcode;
    }

    public void setRetmsg(String retmsg)
    {
        this.retmsg = retmsg;
    }

    public void setGenres(List<GenresEntity> genres)
    {
        this.genres = genres;
    }

    public String getRetcode()
    {
        return retcode;
    }

    public String getRetmsg()
    {
        return retmsg;
    }

    public List<GenresEntity> getGenres()
    {
        return genres;
    }

    public static class GenresEntity
    {
        /**
         * genreId : 2118
         * genreType : 1
         * genreName : Action
         */

        private String genreId;
        private String genreType;
        private String genreName;

        public void setGenreId(String genreId)
        {
            this.genreId = genreId;
        }

        public void setGenreType(String genreType)
        {
            this.genreType = genreType;
        }

        public void setGenreName(String genreName)
        {
            this.genreName = genreName;
        }

        public String getGenreId()
        {
            return genreId;
        }

        public String getGenreType()
        {
            return genreType;
        }

        public String getGenreName()
        {
            return genreName;
        }
    }
}
