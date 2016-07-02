package com.huawei.ott.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.GridView;
import android.widget.PopupWindow;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import com.huawei.ott.PlayDetailActivity;
import com.huawei.ott.R;
import com.huawei.ott.base.BaseFragment;
import com.huawei.ott.base.BaseListAdapter;
import com.huawei.ott.holder.ViewHolderHelper;
import com.huawei.ott.model.RecmVodListResp;
import com.huawei.ott.net.VolleyManager;
import com.huawei.ott.utills.OttConstants;
import com.huawei.ott.utills.UIUtils;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Demandfm extends BaseFragment implements RadioGroup.OnCheckedChangeListener
{

    private BaseListAdapter mAdapter;
    private CheckBox mTvdemandfilter;
    private PopupWindow mPop;
    private final int HOTTEST = 4;
    private int action = HOTTEST;
    private final int WEEKLYRANKINGS = 5;
    private final int TOBEOFFLINE = 3;
    private List<RecmVodListResp.VodlistEntity> mVodlist = new ArrayList<>();

    @Override
    protected String setHeadTitle()
    {
        return OttConstants.ONDEMAND;
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
    protected JSONObject getparams()
    {
        HashMap map = new HashMap();
        map.put(OttConstants.COUNT, -1);
        map.put(OttConstants.OFFSET, 0);
        map.put(OttConstants.ACTION, action);
        return new JSONObject(map);
    }

    @Override
    protected String getRequestURL()
    {
        return OttConstants.RECMVODLIST;
    }

    @Override
    public void onClick(View v)
    {
        super.onClick(v);
        if(v.getId() == R.id.tv_demand_filter)
        {
            showPopWindow();
        }

    }

    private void showPopWindow()
    {
        if(mPop == null)
        {
            View popc = View.inflate(getActivity(), R.layout.pop_recomend, null);
            RadioGroup line1 = (RadioGroup)popc.findViewById(R.id.rg_line1);
            RadioGroup line2 = (RadioGroup)popc.findViewById(R.id.rg_line2);
            RadioGroup line3 = (RadioGroup)popc.findViewById(R.id.rg_line3);
            line1.setOnCheckedChangeListener(this);
            line2.setOnCheckedChangeListener(this);
            line3.setOnCheckedChangeListener(this);

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

    @Override
    protected <T> int onNetSucceed(T response)
    {
        RecmVodListResp recmVodListResp = VolleyManager.getInstance().gson.fromJson(response.toString(), RecmVodListResp.class);
        mVodlist.clear();
        List<RecmVodListResp.VodlistEntity> vodlist = recmVodListResp.getVodlist();
        if(vodlist != null)
        {
            mVodlist.addAll(vodlist);
        }
        return checkNetDataResult(mVodlist);
    }

    @Override
    protected View createAfterNetScceed()
    {
        View view = View.inflate(UIUtils.getContext(), R.layout.fm_demand, null);
        GridView gridview = (GridView)view.findViewById(R.id.gv_demdf);
        mAdapter = new BaseListAdapter<RecmVodListResp.VodlistEntity>(mVodlist)
        {
            @Override
            protected ViewHolderHelper getAdapterHelper(int position, View convertView, ViewGroup parent)
            {
                return ViewHolderHelper.get(Demandfm.this.getActivity(), convertView, parent, R.layout.item_grid_reco, position);
            }

            @Override
            protected void convert(ViewHolderHelper helper, final RecmVodListResp.VodlistEntity item)
            {

                RecmVodListResp.VodlistEntity.PictureEntity picture = item.getPicture();
                String url = "";
                if(picture != null)
                {
                    String poster = picture.getPoster();
                    if(poster != null)
                    {
                        url = poster.substring(poster.lastIndexOf(",")+1);
                    }
                }
                helper.setText(R.id.tv_scroe, item.getScoresum());
                helper.setImageFromUrl(R.id.iv_item_reco_grid, url);
                helper.setText(R.id.tv_item_reco_grid, item.getName());
                helper.setClickListener(R.id.iv_item_reco_grid, new View.OnClickListener()
                {

                    @Override
                    public void onClick(View v)
                    {
                        Intent starter = new Intent(getActivity(), PlayDetailActivity.class);
                        RecmVodListResp.VodlistEntity.CastEntity cast = item.getCast();
                        starter.putExtra(OttConstants.NAME, item.getName());
                        starter.putExtra(OttConstants.ID, item.getId());
                        starter.putExtra(OttConstants.ACTOR, cast.getActor());
                        starter.putExtra(OttConstants.DIRECTOR, cast.getDirector());
                        starter.putExtra(OttConstants.ADAPTOR, cast.getAdaptor());
                        starter.putExtra(OttConstants.PLAYTYPE, 1);
                        starter.putExtra(OttConstants.SCORE, item.getAveragescore());
                        startActivity(starter);
                    }
                });
            }

        };
        gridview.setAdapter(mAdapter);
        return view;
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId)
    {
        switch(checkedId)
        {
            case R.id.tv_demand_sport:
                action = WEEKLYRANKINGS;
                break;
            case R.id.tv_demand_tv:
                action = TOBEOFFLINE;
                break;
            case R.id.tv_demand_movie:
                action = HOTTEST;
                break;
        }
        VolleyShow();
    }

    private void getNetData()
    {
        Context context = getActivity();
        if(context == null)
        {
            context = UIUtils.getContext();
        }
        String url = OttConstants.VODLIST;

        HashMap map = new HashMap();
        map.put(OttConstants.COUNT, -1);
        map.put(OttConstants.OFFSET, 0);
        JSONObject request = new JSONObject(map);
        VolleyManager.getInstance().postParamJsonObject(url, request, this, new FmResult()
        {
            @Override
            public <T> void onSucceed(T modle)
            {
            }
        });
    }

}
