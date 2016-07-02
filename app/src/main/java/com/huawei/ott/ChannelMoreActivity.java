package com.huawei.ott;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.GridView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import com.huawei.ott.base.BaseActivity;
import com.huawei.ott.base.BaseListAdapter;
import com.huawei.ott.net.VolleyManager;
import com.huawei.ott.utills.OttConstants;
import com.huawei.ott.utills.UIUtils;

import org.json.JSONObject;

public class ChannelMoreActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener
{
    //private bdmeizhi mBdmeizhi;
    private final int CHINNEL_MOVIE = 0;
    private final int CHINNEL_SPOT = 2;
    private final int CHINNEL_TV = 3;
    private int chinnel = 4;
    private android.widget.GridView gvdemdf;
    private BaseListAdapter mAdapter;
    private CheckBox mTvdemandfilter;
    private PopupWindow mPop;

    @Override
    protected String setHeadTitle()
    {
        return OttConstants.CHANNELMORE;
    }

    @Override
    protected void addHeadContent(LayoutInflater inflater, RelativeLayout rl_head_content)
    {
        View inflate = inflater.inflate(R.layout.custom_headview, rl_head_content);
        mTvdemandfilter = (CheckBox)inflate.findViewById(R.id.tv_demand_filter);
        RadioGroup rgdemand = (RadioGroup)inflate.findViewById(R.id.rg_demand);
        mTvdemandfilter.setOnClickListener(this);
        rgdemand.setOnCheckedChangeListener(this);
    }

    @Override
    protected JSONObject getParams()
    {
        return null;
    }

    @Override
    protected String getRequestURL()
    {
        return "";
    }

    @Override
    protected <T> int onNetSucceed(T response)
    {
        //        Gson gson = VolleyManager.getInstance().gson;
        //        mBdmeizhi = null;
        //        mBdmeizhi = gson.fromJson((String)response, com.huawei.ott.model.bdmeizhi.class);
        return STATE_SUCCEED;
    }

    @Override
    protected View createAfterNetScceed()
    {
        fmName = OttConstants.DEMANDFM;
        View view = View.inflate(UIUtils.getContext(), R.layout.fm_demand, null);
        GridView gridview = (GridView)view.findViewById(R.id.gv_demdf);
        //        List<bdmeizhi.DataEntity> data = mBdmeizhi.getData();
        //        data.remove(data.size()-1);
        //        mAdapter = new BaseListAdapter<bdmeizhi.DataEntity>(data) {
        //            @Override
        //            protected ViewHolderHelper getAdapterHelper(int position, View convertView, ViewGroup parent){
        //                return ViewHolderHelper.get(ChannelMoreActivity.this, convertView, parent, R.layout.item_grid_reco, position);
        //            }
        //
        //            @Override
        //            protected void convert(ViewHolderHelper helper, bdmeizhi.DataEntity item){
        //                helper.setImageFromUrl(R.id.iv_item_reco_grid, item.getImage_url());
        //                helper.setText(R.id.tv_item_reco_grid, item.getAbs());
        //                helper.setClickListener(R.id.iv_item_reco_grid, new View.OnClickListener() {
        //
        //                    @Override
        //                    public void onClick(View v){
        //                        Intent starter = new Intent(ChannelMoreActivity.this, PlayDetailActivity.class);
        //                        startActivity(starter);
        //                    }
        //                });
        //            }
        //        };
        gridview.setAdapter(mAdapter);
        return view;
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId)
    {
        switch(checkedId)
        {
            case R.id.tv_demand_sport:
                chinnel = CHINNEL_SPOT;
                break;
            case R.id.tv_demand_tv:
                chinnel = CHINNEL_TV;
                break;
            case R.id.tv_demand_movie:
                chinnel = CHINNEL_MOVIE;
                break;
        }
        getNetData2();
    }

    private void getNetData2()
    {

        showPagerView(mState = STATE_LOADING);
        Context context = this;
        if(context == null)
        {
            context = UIUtils.getContext();
        }
        VolleyManager.getInstance().requestString(getRequestURL(), this, new AcResult()
        {
            @Override
            public <T> void onSucceed(T modle)
            {
                //                int succeed = onNetSucceed(modle);
                //                mBdmeizhi.getData().remove(mBdmeizhi.getData().size()-1);
                //                mAdapter.setData(mBdmeizhi.getData());
                //                showSafePagerView(succeed);
            }
        });
    }

    @Override
    public void onClick(View v)
    {
        super.onClick(v);
        if(v.getId() == R.id.tv_demand_filter)
        {
            showPopWindow();
        }
        switch(v.getId())
        {
            case R.id.head_search_action:
                Intent intent = new Intent(ChannelMoreActivity.this, SearchActivity.class);
                startActivity(intent);
                break;
        }

    }

    private void showPopWindow()
    {
        if(mPop == null)
        {
            View popc = View.inflate(this, R.layout.pop_recomend, null);
            RadioButton radioButton_hot = (RadioButton)popc.findViewById(R.id.tv_hot);
            radioButton_hot.setChecked(true);
            mPop = new PopupWindow(popc, -1, -2);
            mPop.setBackgroundDrawable(new ColorDrawable(0));
            //设置popwindow出现和消失动画
            mPop.setAnimationStyle(R.style.PopFilterAnimation);
            mPop.setOutsideTouchable(true);
            //缺少 会导致 mPop.isShowing()一直为false
            mPop.setFocusable(true);
            mPop.setOnDismissListener(new PopupWindow.OnDismissListener()
            {
                @Override
                public void onDismiss()
                {
                    mTvdemandfilter.setChecked(false);
                }
            });
        }
        mPop.showAsDropDown(mTvdemandfilter);
    }
}
