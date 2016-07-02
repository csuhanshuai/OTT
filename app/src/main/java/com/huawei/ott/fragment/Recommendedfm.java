package com.huawei.ott.fragment;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.huawei.ott.CategoryActivity;
import com.huawei.ott.DownloadActivity;
import com.huawei.ott.PlayDetailActivity;
import com.huawei.ott.R;
import com.huawei.ott.SearchActivity;
import com.huawei.ott.base.BaseFragment;
import com.huawei.ott.base.BaseListAdapter;
import com.huawei.ott.holder.ViewHolderHelper;
import com.huawei.ott.model.RecommendVod;
import com.huawei.ott.net.VolleyManager;
import com.huawei.ott.utills.OttConstants;
import com.huawei.ott.utills.UIUtils;
import com.huawei.ott.widget.LoopImageHolder;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;


public class Recommendedfm extends BaseFragment
{

    private LoopImageHolder mLoopImageHolder;
    private LinearLayout mLlrecommend;
    private ImageView ib_download, ib_category;
    private LinearLayout iv_head_search;
    private BaseListAdapter mAdapter;
    private List<RecommendVod.ContentlistEntity> mVodlist;


    @Override
    public boolean setHeadIsVisibility()
    {

        return false;
    }

    @Override
    protected void addHeadContent(LayoutInflater inflater, RelativeLayout rl_head_content)
    {

        View view = inflater.inflate(R.layout.head_title_version2, rl_head_content);
        iv_head_search = (LinearLayout)view.findViewById(R.id.iv_head_search);
        ib_download = (ImageView)view.findViewById(R.id.ib_download);
        ib_category = (ImageView)view.findViewById(R.id.ib_category);
        iv_head_search.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getActivity(), SearchActivity.class);
                startActivity(intent);
            }
        });





        ib_category.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getActivity(), CategoryActivity.class);
                startActivity(intent);
            }
        });
        ib_download.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getActivity(), DownloadActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected JSONObject getparams()
    {
        HashMap map = new HashMap();
        map.put("count", -1);
        map.put("offset", 0);
        map.put("network", 1);
        return new JSONObject(map);
    }

    @Override
    protected <T> int onNetSucceed(T response)
    {
        RecommendVod recommendVod = VolleyManager.getInstance().gson.fromJson(response.toString(), RecommendVod.class);
        mVodlist = recommendVod.getContentlist();
        return STATE_SUCCEED;
    }

    @Override
    protected String setHeadTitle()
    {
        return OttConstants.RECOMMENDEDFM;
    }

    @Override
    protected String getRequestURL()
    {
        return OttConstants.STATICRECMFILM;
    }

    @Override
    protected View createAfterNetScceed()
    {
        View view = View.inflate(UIUtils.getContext(), R.layout.fm_demand, null);
        GridView gridview = (GridView)view.findViewById(R.id.gv_demdf);
        mAdapter = new BaseListAdapter<RecommendVod.ContentlistEntity>(mVodlist)
        {
            @Override
            protected ViewHolderHelper getAdapterHelper(int position, View convertView, ViewGroup parent)
            {
                return ViewHolderHelper.get(UIUtils.getContext(), convertView, parent, R.layout.item_grid_reco, position);
            }

            @Override
            protected void convert(ViewHolderHelper helper, final RecommendVod.ContentlistEntity item)
            {
                RecommendVod.ContentlistEntity.PictureEntity picture = item.getPicture();
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
                        RecommendVod.ContentlistEntity.CastEntity cast = item.getCast();
                        starter.putExtra(OttConstants.NAME, item.getName());
                        starter.putExtra(OttConstants.ID, item.getId());
                        starter.putExtra(OttConstants.ACTOR, cast.getActor());
                        starter.putExtra(OttConstants.DIRECTOR, cast.getDirector());
                        starter.putExtra(OttConstants.ADAPTOR, cast.getAdaptor());
                        starter.putExtra(OttConstants.PLAYTYPE, 1);
                        starter.putExtra(OttConstants.SCORE, item.getScoresum());
                        starter.putExtra(OttConstants.INTRODUCE, item.getIntroduce());
                        startActivity(starter);

                    }
                });
            }

        };
        gridview.setAdapter(mAdapter);
        return view;


    }

    @Override
    public void onPause()
    {
        super.onPause();
        if(mLoopImageHolder != null)
        {
            mLoopImageHolder.toogleAsyncLoop(false);
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser)
    {
        super.setUserVisibleHint(isVisibleToUser);
        if(mLoopImageHolder != null)
        {
            mLoopImageHolder.toogleAsyncLoop(isVisibleToUser);
        }
    }

    private void getNetData()
    {
        HashMap map = new HashMap();
        map.put(OttConstants.COUNT, 3);
        map.put(OttConstants.OFFSET, 0);
        map.put(OttConstants.NETWORK, 1);
        JSONObject request = new JSONObject(map);
        VolleyManager.getInstance().postParamJsonObject(OttConstants.STATICRECMFILM, request, this, new FmResult()
        {
            @Override
            public <T> void onSucceed(T modle)
            {
            }
        });
    }
}
