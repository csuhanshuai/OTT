package com.huawei.ott.fragment;

import android.support.v4.app.Fragment;

import java.util.HashMap;
import java.util.Map;

public class FragmentFactory
{

    private static final int ONE = 0;
    private static final int TWO = 1;
    private static final int THREE = 2;
    private static final int FOUR = 3;
    private static final int PROGRAM = 4;
    private static final int CHANNEL = 5;

    private static Map<Integer,Fragment> fmCache = new HashMap<Integer,Fragment>();

    public static Fragment createFragment(int type)
    {
        Fragment fragment = fmCache.get(type);
        if(fragment == null)
        {
            switch(type)
            {
                case ONE:
                    fragment = new MyTVfm();
                    break;
                case TWO:
                    fragment = new Recommendedfm();
                    break;
                case THREE:
                    fragment = new Livefm();
                    break;
                case FOUR:
                    fragment = new Demandfm();
                    break;


            }
            fmCache.put(type, fragment);
        }
        return fragment;
    }
}
