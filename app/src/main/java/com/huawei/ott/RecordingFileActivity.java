package com.huawei.ott;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.huawei.ott.base.BaseActivity;
import com.huawei.ott.base.BaseListAdapter;
import com.huawei.ott.holder.ViewHolderHelper;
import com.huawei.ott.utills.OttConstants;
import com.huawei.ott.utills.UIUtils;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class RecordingFileActivity extends BaseActivity
{
    private ListView mListView;
    private List<FakeData> mData;

    @Override
    protected String setHeadTitle()
    {
        return "Check Recording Files";
    }

    @Override
    protected JSONObject getParams()
    {
        return null;
    }

    @Override
    protected String getRequestURL()
    {
        return OttConstants.CHANNELBOARD;
    }

    @Override
    protected <T> int onNetSucceed(T response)
    {
        mData = new ArrayList<FakeData>();
        for(int i = 0; i<6; i++)
        {
            mData.add(new FakeData("title No."+i, ""+i));
        }
        return STATE_SUCCEED;
    }

    @Override
    protected View createAfterNetScceed()
    {
        View content = View.inflate(this, R.layout.activity_recording_file, null);
        mListView = (ListView)content.findViewById(R.id.recording_list);
        mListView.setAdapter(new BaseListAdapter<FakeData>(mData)
        {
            @Override
            protected ViewHolderHelper getAdapterHelper(int position, View convertView, ViewGroup parent)
            {
                return ViewHolderHelper.get(UIUtils.getContext(), convertView, parent, R.layout.item_recording_file, position);
            }

            @Override
            protected void convert(ViewHolderHelper helper, FakeData item)
            {
                helper.setText(R.id.title_item_textview, item.title);
                helper.setText(R.id.size_item_textview, item.size);
                helper.setBackground(R.id.status_item_imageview, R.drawable.live_normal);


            }
        });
        return content;
    }


    class FakeData
    {
        String title;
        String size;

        public FakeData(String title, String size)
        {
            this.title = title;
            this.size = size;
        }


    }

}
