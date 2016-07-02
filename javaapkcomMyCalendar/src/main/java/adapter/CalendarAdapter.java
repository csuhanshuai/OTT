package adapter;

import java.util.List;

import utils.DataUtils;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import com.mycalendar.MainActivity;
import com.mycalendar.R;

import data.DateInfo;

/**
 * 日历gridview适配器
 * */
public class CalendarAdapter extends BaseAdapter {
	
	private List<DateInfo> list = null;
	private Context context = null;
	private int selectedPosition = -1;
	MainActivity activity;
	public CalendarAdapter(MainActivity activity, List<DateInfo> list) {
		this.context = activity;
		this.list = list;
		this.activity = activity;
	}
	
	public List<DateInfo> getList() {
		return list;
	}

	public int getCount() {
		return list.size();
	}

	public Object getItem(int position) {
		return list.get(position);
	}

	public long getItemId(int position) {
		return position;
	}
	
	/**
	 * 设置选中位置
	 * */
	public void setSelectedPosition(int position) {  
        selectedPosition = position;  
    }  

	/**
	 * 产生一个view
	 * */
	public View getView(int position, View convertView, ViewGroup group) {
		//通过viewholder做一些优化
		ViewHolder viewHolder;
		if (convertView == null) {
			viewHolder = new ViewHolder();
			LayoutInflater inflater = LayoutInflater.from(context);
			convertView = inflater.inflate(R.layout.gridview_item, null);
			viewHolder.date = (TextView) convertView.findViewById(R.id.item_date);
			viewHolder.nongliDate = (TextView) convertView.findViewById(R.id.item_nongli_date);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		//根据数据源设置单元格的字体颜色、背景等
		viewHolder.date.setText(list.get(position).getDate() + "");
		viewHolder.nongliDate.setText(list.get(position).getNongliDate());
		if (selectedPosition == position) {
			viewHolder.date.setTextColor(Color.WHITE);
			viewHolder.nongliDate.setTextColor(Color.WHITE);
			convertView.setBackgroundColor(Color.RED);
		} else {
			convertView.setBackgroundResource(R.drawable.item_bkg);
			viewHolder.date.setTextColor(Color.BLACK);
			viewHolder.nongliDate.setTextColor(Color.BLACK);
			if (list.get(position).isHoliday())
				viewHolder.nongliDate.setTextColor(Color.BLUE);
			else if (list.get(position).isThisMonth() == false) {
				viewHolder.date.setTextColor(Color.rgb(210, 210, 210));
			}
			else if (list.get(position).isWeekend()) {
				viewHolder.date.setTextColor(Color.rgb(255, 97, 0));
			}
		}
		if (list.get(position).getNongliDate().length() > 3)
			viewHolder.nongliDate.setTextSize(10);
		if (list.get(position).getNongliDate().length() >= 5)
			viewHolder.nongliDate.setTextSize(8);
		convertView.setLayoutParams(new GridView.LayoutParams(LayoutParams.WRAP_CONTENT, DataUtils.getScreenWidth(activity) / 7));
		return convertView;
	}
	
	private class ViewHolder {
		TextView date;
		TextView nongliDate;
	}
	
}
