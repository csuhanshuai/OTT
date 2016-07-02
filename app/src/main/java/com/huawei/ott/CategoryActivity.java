package com.huawei.ott;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;

import com.huawei.ott.utills.UIUtils;

/**
 * <分类页面>
 */
public class CategoryActivity extends Activity implements View.OnClickListener
{
    private ImageButton head_back_action, head_search_action;
    private GridView mGridView;
    //private List<Recommend> mRec_data;
    private TextView mTextView;

    /**
     * <页面创建>
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        initView();

    }

    /**
     * <布局填充>
     */
    private void initView()
    {
        mTextView = (TextView)findViewById(R.id.head_text);
        mTextView.setText(UIUtils.getString(R.string.channel));
        mTextView.setTextColor(getResources().getColor(R.color.color_gray));
        head_back_action = (ImageButton)findViewById(R.id.head_back_action);
        head_search_action = (ImageButton)findViewById(R.id.head_search_action);
        head_back_action.setOnClickListener(this);
        head_search_action.setOnClickListener(this);
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
                Intent intent = new Intent(CategoryActivity.this, SearchActivity.class);
                startActivity(intent);
                break;
            case R.id.head_back_action:
                finish();
                break;
        }
    }
}
