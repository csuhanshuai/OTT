package com.huawei.ott.widget;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.huawei.ott.R;
import com.huawei.ott.application.OttApplication;
import com.huawei.ott.utills.UIUtils;

import java.util.ArrayList;
import java.util.List;

public class LoopImageHolder implements OnPageChangeListener {

    private List<String> data = new ArrayList<>();
    private ViewPager viewPager;
    private ViewGroup.LayoutParams params;
    private IndicatorView indicatorView;
    private AutoPlayTask autoPlayTask;
    private View mLoopImageView;
    private Context mContext;
    private onItemClickListener l;

    public LoopImageHolder(ViewGroup.LayoutParams params){
        this.params = params;
        mLoopImageView = initView();
    }

    public LoopImageHolder(){
        mLoopImageView = initView();
    }

    public LoopImageHolder(Context mContext){
        this.mContext = mContext;
        mLoopImageView = initView();
    }

    public View getLoopImageView(){
        return mLoopImageView;
    }

    public void setData(List<String> data){
        this.data = data;
        refreshView();
    }

    public List<String> getData(){
        return data;
    }

    public void refreshView(){
        data = getData();
        viewPager.setCurrentItem(0);
        indicatorView.setCount(data.size());
        autoPlayTask.start();
    }

    public View initView(){
        // 初始化头。由于使用相对布局方便放置可以跳动的点
        RelativeLayout loopView = new RelativeLayout(UIUtils.getContext());
        if(params == null) {
            // 设置轮播图的宽和高
            params = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, OttApplication.getScreenwidth()/3);
        }

        loopView.setLayoutParams(params);
        // 初始化轮播图

        viewPager = new ViewPager(UIUtils.getContext());
        // 初始化轮播图的宽和高

        RelativeLayout.LayoutParams rl = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);

        viewPager.setLayoutParams(rl);

        ViewPagerAdapter adapter = new ViewPagerAdapter();

        viewPager.setAdapter(adapter);
        // 初始化点

        indicatorView = new IndicatorView(UIUtils.getContext());

        RelativeLayout.LayoutParams rlp = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        // 设置到屏幕的下边
        rlp.addRule(loopView.ALIGN_PARENT_BOTTOM);
        rlp.addRule(loopView.ALIGN_PARENT_RIGHT);
        // 设置点的背景图片
        indicatorView.setIndicatorDrawable(UIUtils.getDrawable(R.drawable.indicator));
        // 设置点的间距
        rlp.setMargins(0, 0, 20, 10);
        indicatorView.setLayoutParams(rlp);

        indicatorView.setSelection(0);

        autoPlayTask = new AutoPlayTask();

        //        viewPager.setOnPageChangeListener(this);
        viewPager.addOnPageChangeListener(this);

        viewPager.setOnTouchListener(new OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event){

                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    autoPlayTask.stop();
                }else if(event.getAction() == MotionEvent.ACTION_UP) {
                    autoPlayTask.start();
                }

                return false;
            }
        });

        // 把点和轮播图添加到头里面去
        loopView.addView(viewPager);

        loopView.addView(indicatorView);

        //        viewPager.setPageTransformer(true, new ViewPager.PageTransformer() {
        //            @Override
        //            public void transformPage(View view, float position){
        //                final float scale = position < 0 ? position + 1f : Math.abs(1f - position);
        //                ViewHelper.setScaleX(view,scale);
        //                ViewHelper.setScaleY(view,scale);
        //                ViewHelper.setPivotX(view,view.getWidth() * 0.5f);
        //                ViewHelper.setPivotY(view, view.getHeight()*0.5f);
        //                ViewHelper.setAlpha(view, position<-1f || position>1f ? 0f : 1f-( scale-1f ));
        //            }
        //        });

        return loopView;
    }

    public void toogleAsyncLoop(boolean start){
        if(!start) {
            autoPlayTask.stop();
        }else {
            autoPlayTask.start();
        }
    }

    private class AutoPlayTask implements Runnable {

        private int AUTO_PLAY_TIME = 2000;

        private boolean has_auto_play = false;

        @Override
        public void run(){
            if(has_auto_play) {
                //has_auto_play = false;
                viewPager.setCurrentItem(viewPager.getCurrentItem()+1);
                UIUtils.postDelayed(this, AUTO_PLAY_TIME);
            }

        }

        public void stop(){
            has_auto_play = false;
            UIUtils.removeCallbacks(this);

        }

        public void start(){
            if(!has_auto_play) {
                has_auto_play = true;
                UIUtils.removeCallbacks(this);
                UIUtils.postDelayed(this, AUTO_PLAY_TIME);
            }

        }

    }

    private class ViewPagerAdapter extends PagerAdapter {

        @Override
        public void destroyItem(ViewGroup container, int position, Object object){
            // TODO Auto-generated method stub
            container.removeView((ImageView)object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, final int position){
            ImageView imageView = new ImageView(UIUtils.getContext());
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v){
                    if(null != l) {
                        l.onItemClicked(getData(), position%( getData().size() ));
                    }
                }
            });
            int size = getData().size();
            if(size>0) {
                Glide.with(mContext).load(getData().get(position%size)).centerCrop().into(imageView);
                //                Picasso.with(UIUtils.getContext()).load(getData().get(position%size)).into(imageView);
                // 如果是从网络获取图片的话。必须增加这个属性
                imageView.setScaleType(ScaleType.FIT_XY);
                container.addView(imageView);
            }
            return imageView;
        }

        @Override
        public int getCount(){
            // TODO Auto-generated method stub
            return Integer.MAX_VALUE;
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1){
            // TODO Auto-generated method stub
            return arg0 == arg1;
        }

    }

    @Override
    public void onPageScrollStateChanged(int arg0){
        // TODO Auto-generated method stub

    }

    @Override
    public void onPageScrolled(int arg0, float arg1, int arg2){
        // TODO Auto-generated method stub

    }

    @Override
    public void onPageSelected(int position){

        if(getData().size()>0) {
            indicatorView.setSelection(position%getData().size());
            indicatorView.getCount();
        }
    }

    public interface onItemClickListener {

        public <T> void onItemClicked(T data, int position);
    }

    public void setOnItemClickListener(onItemClickListener l){
        this.l = l;
    }

}
