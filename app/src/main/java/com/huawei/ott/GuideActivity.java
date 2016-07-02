package com.huawei.ott;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;

import com.huawei.ott.utills.OttConstants;
import com.huawei.ott.utills.SPUtills;

import java.util.ArrayList;
import java.util.List;

/**
 * 引导页面
 */
public class GuideActivity extends Activity implements OnPageChangeListener, OnClickListener
{

    private List<ImageView> imageViewList; // viewpager的数据
    private LinearLayout llPointGroup; // 点的组
    private View mSelectPointView; // 选中的点view对象
    private int basicWidth; // 点之间的宽度
    private Button btnStartExperience; // 开始体验按钮

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); // 去标题, 需要在setContentView方法之前调用
        setContentView(R.layout.guide);

        initView();
    }

    /**
     * 初始化控件
     */
    private void initView()
    {
        ViewPager mViewPager = (ViewPager)findViewById(R.id.vp_guide);
        btnStartExperience = (Button)findViewById(R.id.btn_guide_start_experience);
        llPointGroup = (LinearLayout)findViewById(R.id.ll_guide_point_group);
        mSelectPointView = findViewById(R.id.select_point);

        initData();

        GuideAdapter mAdapter = new GuideAdapter();
        mViewPager.setAdapter(mAdapter);
        mViewPager.setOnPageChangeListener(this);
        btnStartExperience.setOnClickListener(this);

        // view绘制流程: measure -> layout -> draw
        // 监听mSelectPointView控件layout

        // 获得视图树的观察者, 监听全部布局的回调
        mSelectPointView.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener()
        {

            @Override
            public void onGlobalLayout()
            {
                // 只执行一次, 把当前的事件从视图树的观察者中移除掉
                mSelectPointView.getViewTreeObserver().removeGlobalOnLayoutListener(this);

                // 取出两个点之间的宽度
                basicWidth = llPointGroup.getChildAt(1).getLeft()-llPointGroup.getChildAt(0).getLeft();
            }
        });
    }

    /**
     * 初始化数据
     */
    private void initData()
    {
        int[] imageResIDs = {
                android.R.drawable.ic_menu_week, android.R.drawable.editbox_background_normal, android.R.drawable.menu_frame
        };

        // ctrl + 2 松手停顿 L
        imageViewList = new ArrayList<ImageView>();

        ImageView iv;
        View view;
        LayoutParams params;
        for(int i = 0; i<imageResIDs.length; i++)
        {
            iv = new ImageView(this);
            iv.setBackgroundResource(imageResIDs[i]);
            imageViewList.add(iv);

            // 根据图片的个数, 每循环一次向LinearLayout中添加一个点
            view = new View(this);
            view.setBackgroundResource(R.drawable.point_normal);
            params = new LayoutParams(10, 10);
            if(i != 0)
            {
                params.leftMargin = 10;
            }
            view.setLayoutParams(params);
            llPointGroup.addView(view);
        }
    }

    class GuideAdapter extends PagerAdapter
    {

        @Override
        public int getCount()
        {
            return imageViewList.size();
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1)
        {
            return arg0 == arg1;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object)
        {
            container.removeView((View)object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position)
        {
            ImageView iv = imageViewList.get(position);
            // 1. 向ViewPager中添加一个view对象
            container.addView(iv);

            // 2. 返回当前添加的view对象
            return iv;
        }
    }

    /**
     * 当页面正在滚动时
     * position 当前选中的是哪个页面
     * positionOffset 比例
     * positionOffsetPixels 偏移像素
     */
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels)
    {
        // 20 * (1 + 0.99) = 38
        int leftMargin = (int)( basicWidth*( position+positionOffset ) );

        RelativeLayout.LayoutParams params = (android.widget.RelativeLayout.LayoutParams)mSelectPointView.getLayoutParams();
        params.leftMargin = leftMargin;
        mSelectPointView.setLayoutParams(params);
    }

    /**
     * 当页面被选中
     */
    @Override
    public void onPageSelected(int position)
    {
        if(position == imageViewList.size()-1)
        {
            btnStartExperience.setVisibility(View.VISIBLE);
        }else
        {
            btnStartExperience.setVisibility(View.GONE);
        }
    }

    /**
     * 当页面滚动状态改变
     */
    @Override
    public void onPageScrollStateChanged(int state)
    {

    }

    @Override
    public void onClick(View v)
    {
        // 把IS_OPEN_MAIN_PAGER键, 在缓存中存储一个true.
        SPUtills.put(this, OttConstants.IS_OPEN_MAIN_PAGER, true);

        // 打开主页面
        startActivity(new Intent(this, MainActivity.class));

        finish();
    }

}
