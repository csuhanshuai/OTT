package com.mycalendar;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import adapter.CalendarAdapter;
import data.DateInfo;
import utils.DataUtils;
import utils.TimeUtils;

/**
 * 主界面
 * */
public class MainActivity extends Activity {

	/**
	 * 和viewpager相关变量
	 * */
	public MyViewPager viewPager = null;
	public MyPagerAdapter pagerAdapter = null;
	private int currPager = 500;
	private TextView shader;

	/**
	 * 和日历gridview相关变量
	 * */
	private GridView gridView = null;
	public CalendarAdapter adapter = null;
	private GridView currentView = null;
	public List<DateInfo> currList = null;
	public List<DateInfo> list = null;
	public int lastSelected = 0;

	/**
	 * 显示年月
	 * */
	public TextView showYearMonth = null;

	/**
	 * 第一个页面的年月
	 * */
	private int currentYear;
	private int currentMonth;

	private Button dialog;
	
	/**
	 * 收缩展开的面板
	 * */
	private Panel panel;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

		dialog = (Button)findViewById(R.id.dialog);
		dialog.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view)
			{
				showDialog();
			}
		});







        

    }


	public void showDialog() {
		LinearLayout calendarForm = (LinearLayout)getLayoutInflater()
				.inflate(R.layout.activity_main, null);


		initData();
		initView();


		new AlertDialog.Builder(MainActivity.this)
				.setTitle("")
				.setView(calendarForm)
				.setPositiveButton("确定", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialogInterface, int i)
					{
						finish();
					}
				})
				.setNegativeButton("取消", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialogInterface, int i)
					{
						finish();
					}
				})
				.create()
				.show();
	}
    
    /**
	 * 初始化view
	 * */
	private void initView() {
    	viewPager = (MyViewPager) findViewById(R.id.viewpager); 
    	shader = (TextView) findViewById(R.id.main_frame_shader);
    	panel = (Panel) findViewById(R.id.panel);
    	panel.setOpen(true, false);
    	shader.setVisibility(View.GONE);
    	pagerAdapter = new MyPagerAdapter();
    	viewPager.setAdapter(pagerAdapter);
    	viewPager.setCurrentItem(500);
    	viewPager.setPageMargin(0);
    	showYearMonth = (TextView) findViewById(R.id.main_year_month);
    	showYearMonth.setText(String.format("%04d年%02d月", currentYear, currentMonth));
    	
    	viewPager.setOnPageChangeListener(new OnPageChangeListener() {
			public void onPageScrollStateChanged(int arg0) {
				if (arg0 == 1) {
					shader.setText("");
					shader.setVisibility(View.VISIBLE);
				}
				if (arg0 == 0) {
					currentView = (GridView) viewPager.findViewById(currPager);
					if (currentView != null) {
						adapter = (CalendarAdapter) currentView.getAdapter();
						currList = adapter.getList();
						int pos = DataUtils.getDayFlag(currList, lastSelected);
						adapter.setSelectedPosition(pos);
						adapter.notifyDataSetInvalidated();
					}
					shader.setVisibility(View.GONE);
				}
			}

			public void onPageScrolled(int arg0, float arg1, int arg2) {
			}

			public void onPageSelected(int position) {
		    	int year = TimeUtils.getTimeByPosition(position, currentYear, currentMonth, "year");
		    	int month = TimeUtils.getTimeByPosition(position, currentYear, currentMonth, "month");
		    	showYearMonth.setText(String.format("%04d年%02d月", year, month));
				currPager = position;
				shader.setText(showYearMonth.getText());
			}
    	});
    }
    
	/**
	 * 初始化数据
	 * */
    private void initData() {
    	currentYear = TimeUtils.getCurrentYear();
    	currentMonth = TimeUtils.getCurrentMonth();
    	lastSelected = TimeUtils.getCurrentDay();
    	String formatDate = TimeUtils.getFormatDate(currentYear, currentMonth);
    	try {
    		list = TimeUtils.initCalendar(formatDate, currentMonth);
    	} catch (Exception e) {
    		finish();
    	}
    }
    
    /**
	 * 初始化日历的gridview
	 * */
    private GridView initCalendarView(int position) {
    	int year = TimeUtils.getTimeByPosition(position, currentYear, currentMonth, "year");
    	int month = TimeUtils.getTimeByPosition(position, currentYear, currentMonth, "month");
    	String formatDate = TimeUtils.getFormatDate(year, month);
    	try {
    		list = TimeUtils.initCalendar(formatDate, month);
    	} catch (Exception e) {
    		finish();
    	}
		gridView = new GridView(this);
    	adapter = new CalendarAdapter(this, list);
    	if (position == 500) {
    		currList = list;
	    	int pos = DataUtils.getDayFlag(list, lastSelected);
	    	adapter.setSelectedPosition(pos);
    	}
    	gridView.setAdapter(adapter);
    	gridView.setNumColumns(7);
    	gridView.setSelector(new ColorDrawable(Color.TRANSPARENT));
    	gridView.setGravity(Gravity.CENTER);
    	gridView.setOnItemClickListener(new OnItemClickListenerImpl(adapter, this));
    	return gridView;
    }
    
    /**
	 * viewpager的适配器，从第500页开始，最多支持0-1000页
	 * */
    private class MyPagerAdapter extends PagerAdapter {

		@Override
		public void setPrimaryItem(ViewGroup container, int position,
				Object object) {
			currentView = (GridView) object;
			adapter = (CalendarAdapter) currentView.getAdapter();
		}

		@Override
		public int getCount() {
			return 1000;
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView((View) object);
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			GridView gv = initCalendarView(position);
			gv.setId(position);
			container.addView(gv);
			return gv;
		}
    }
}
