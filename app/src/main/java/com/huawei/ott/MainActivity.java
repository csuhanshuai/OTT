package com.huawei.ott;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioGroup;

import com.huawei.ott.application.OttApplication;
import com.huawei.ott.fragment.FragmentFactory;
import com.huawei.ott.utills.DisplayUtils;
import com.huawei.ott.widget.DispatchRelativeLayout;

/**
 * 主界面 四个fm依托的 activity
 */

public class MainActivity extends FragmentActivity implements View.OnClickListener, DispatchRelativeLayout.OnDispatchTouchEventListener
{

    public ViewPager mViewPager;
    private int[] pagers = {0, 1, 2, 3};
    private int[] checkedId = {R.id.radio0, R.id.radio1, R.id.radio2, R.id.radio3};
    private RadioGroup mRadioGroup;
    private LinearLayout mDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mViewPager = (ViewPager)findViewById(R.id.vp);
        ((DispatchRelativeLayout)findViewById(R.id.dprl)).setonDispatchTouchEventListener(this);
        mRadioGroup = (RadioGroup)findViewById(R.id.radioGroup1);
        mDialog = (LinearLayout)findViewById(R.id.ll_exit);
        MyFragmentAdapter adapter = new MyFragmentAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(adapter);

        MyOnpagerChangerListener listener = new MyOnpagerChangerListener();
        mViewPager.setOnPageChangeListener(listener);
        mViewPager.setOffscreenPageLimit(5);
        mViewPager.setCurrentItem(1);
        OttApplication.setScreenwidth(DisplayUtils.getWidthPx(this));
        OttApplication.setScreenHeight(DisplayUtils.getHeightPx(this));
        setOnClickeners();
    }

    private void setOnClickeners()
    {
        findViewById(R.id.radio0).setOnClickListener(this);
        findViewById(R.id.radio1).setOnClickListener(this);
        findViewById(R.id.radio2).setOnClickListener(this);
        findViewById(R.id.radio3).setOnClickListener(this);
    }

    class MyFragmentAdapter extends FragmentStatePagerAdapter
    {

        public MyFragmentAdapter(FragmentManager fm)
        {
            super(fm);
        }

        @Override
        public Fragment getItem(int position)
        {

            return FragmentFactory.createFragment(pagers[position]);
        }

        @Override
        public int getCount()
        {
            return pagers.length;
        }
    }

    class MyOnpagerChangerListener implements ViewPager.OnPageChangeListener
    {

        @Override
        public void onPageScrollStateChanged(int arg0)
        {

        }

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels)
        {
        }

        @Override
        public void onPageSelected(int position)
        {
            int id = -1;
            switch(position)
            {
                case 0://mytv页面
                    id = R.id.radio0;
                    break;
                case 1://recommend页面
                    id = R.id.radio1;
                    break;
                case 2://live tv页面
                    id = R.id.radio2;
                    break;
                case 3:// on demand页面
                    id = R.id.radio3;
                    break;
            }
            mRadioGroup.check(id);
        }
    }

    @Override
    public void onClick(View v)
    {
        int index = -1;
        switch(v.getId())
        {
            case R.id.radio0:
                index = 0;
                break;
            case R.id.radio1:
                index = 1;
                break;
            case R.id.radio2:
                index = 2;
                break;
            case R.id.radio3:
                index = 3;
                break;

            default:
                break;
        }
        mViewPager.setCurrentItem(index);
    }

    @Override
    public void onBackPressed()
    {
        if(BuildConfig.DEBUG)
        {
            finish();
        }

        mDialog.setVisibility(mDialog.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
    }

    public void exitno(View v)
    {
        mDialog.setVisibility(View.GONE);
    }

    public void exityes(View v)
    {
        finish();
    }

    @Override
    public boolean dispatchEvent(MotionEvent event)
    {
        if(mDialog.getVisibility() == View.VISIBLE)
        {
            mDialog.setVisibility(View.GONE);
            return true;
        }
        return false;
    }
}
