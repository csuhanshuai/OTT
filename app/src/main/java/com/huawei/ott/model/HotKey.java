package com.huawei.ott.model;

import java.util.List;

/**
 * <热键>
 */
public class HotKey
{
    /**
     * counttotal : 10
     * keylist : ["1234567890123456","1234567890123456789","tom","12345678901234567","12345678901","1234567890123","12345678901234","123456789012","123456789012345678","123456789012345"]
     */

    private String counttotal;
    private List<String> keylist;

    public void setCounttotal(String counttotal)
    {
        this.counttotal = counttotal;
    }

    public void setKeylist(List<String> keylist)
    {
        this.keylist = keylist;
    }

    public String getCounttotal()
    {
        return counttotal;
    }

    public List<String> getKeylist()
    {
        return keylist;
    }
}
