package com.huawei.ott;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.huawei.ott.utills.UIUtils;

/**
 * <下载页面>
 */
public class DownloadActivity extends Activity implements View.OnClickListener
{
    private ImageButton head_back_action, head_search_action;
    private ListView mListView;
    private TextView mTextView;

    /**
     * <页面创建>
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);
        initView();

    }

    /**
     * <布局填充>
     */
    private void initView()
    {
        mTextView = (TextView)findViewById(R.id.head_text);
        mTextView.setText(UIUtils.getString(R.string.download));
        mTextView.setTextColor(getResources().getColor(R.color.color_gray));
        //        mRec_data = new ArrayList();
        //        bdmeizhi bdmeizhi = null;
        //        for(int i = 0; i<20; i++) {
        //            mRec_data.add(new Recommend("今日热点："+i, bdmeizhi));
        //        }
        head_back_action = (ImageButton)findViewById(R.id.head_back_action);
        head_search_action = (ImageButton)findViewById(R.id.head_search_action);
        head_back_action.setOnClickListener(this);
        head_search_action.setOnClickListener(this);
        //        mListView = (ListView)findViewById(R.id.lv_download);
        //        mListView.setAdapter(new BaseListAdapter<Recommend>(mRec_data) {
        //            @Override
        //            protected ViewHolderHelper getAdapterHelper(int position, View convertView, ViewGroup parent){
        //                return ViewHolderHelper.get(UIUtils.getContext(), convertView, parent, R.layout.item_download, position);
        //            }
        //
        //            @Override
        //            protected void convert(ViewHolderHelper helper, final Recommend item){
        //
        //            }
        //        });

    }

    /**
     * <控件点击事件>
     */
    @Override
    public void onClick(View v)
    {
        switch(v.getId())
        {
            case R.id.head_search_action:
                Intent intent = new Intent(DownloadActivity.this, SearchActivity.class);
                startActivity(intent);
                break;
            case R.id.head_back_action:
                finish();
                break;
        }
    }
}
