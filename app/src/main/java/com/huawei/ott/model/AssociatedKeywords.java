package com.huawei.ott.model;

import java.util.List;

/**
 * Created by Jonas on 2015/12/1.
 */
public class AssociatedKeywords
{
    /**
     * keylist : ["1234567890123456","1234567890123456789","12345678901234567","12345678901","1234567890123","12345678901234","123456789012","123456789012345678","123456789012345","1234567890","","","","","","","","","","",""]
     */

    private List<String> keylist;

    public void setKeylist(List<String> keylist)
    {
        this.keylist = keylist;
    }

    public List<String> getKeylist()
    {
        return keylist;
    }
}
