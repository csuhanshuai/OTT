package com.huawei.ott.fragment;

import android.content.Intent;
import android.graphics.Matrix;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import com.google.gson.Gson;
import com.huawei.ott.LiveActivity;
import com.huawei.ott.R;
import com.huawei.ott.base.BaseFragment;
import com.huawei.ott.base.BaseListAdapter;
import com.huawei.ott.holder.ViewHolderHelper;
import com.huawei.ott.model.ChannelList;
import com.huawei.ott.model.GenerList;
import com.huawei.ott.net.VolleyManager;
import com.huawei.ott.utills.OttConstants;
import com.huawei.ott.utills.UIUtils;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;

/**
 * <直播页面>
 */
public class Livefm extends BaseFragment implements RadioGroup.OnCheckedChangeListener
{

    private ListView mListView;
    private LinearLayout mLinearLayout;
    private ChannelList mChannelList;
    private GenerList mGenerList;
    private String ALLCHANNEL = "ALLCHANNEL";
    private String SPORT = "SPORT";
    private String NEWS = "NEWS";
    private String action = "ALLCHANNEL";
    private List<ChannelList.ChannellistEntity> mChannellistEntities;
    private List<GenerList.GenresEntity> mGenerListEntities;


    @Override
    protected void addHeadContent(LayoutInflater inflater, RelativeLayout rl_head_content)
    {
        View inflate = inflater.inflate(R.layout.custom_live_headview, rl_head_content);
        RadioGroup rglive = (RadioGroup)inflate.findViewById(R.id.rg_live);
        rglive.setOnCheckedChangeListener(this);
    }

    /**
     * <直播页面布局>
     * <访问网络成功之后，创建布局>
     *
     * @return
     */
    @Override
    protected View createAfterNetScceed()
    {
        List<ChannelList.ChannellistEntity> data = mChannelList.getChannellist();
        View live = View.inflate(UIUtils.getContext(), R.layout.fm_live, null);
        mListView = (ListView)live.findViewById(R.id.lv_live);
        mLinearLayout = (LinearLayout)live.findViewById(R.id.item_ll_live);
        mListView.setAdapter(new BaseListAdapter<ChannelList.ChannellistEntity>(data)
        {
            /**
             * <布局填充>
             * @param position 位置
             * @param convertView view对象
             * @param parent 父布局
             * @return ViewHolderHelper
             */
            @Override
            protected ViewHolderHelper getAdapterHelper(int position, View convertView, ViewGroup parent)
            {
                return ViewHolderHelper.get(UIUtils.getContext(), convertView, parent, R.layout.item_list_live, position);
            }

            /**
             * <布局填充>
             * @param helper ViewHolderHelper对象
             * @param item 频道列表对象
             */
            @Override
            protected void convert(ViewHolderHelper helper, final ChannelList.ChannellistEntity item)
            {

                helper.setText(R.id.tv_channel, item.getChanno());
                helper.setText(R.id.tv_channel_name, item.getIntroduce());
                helper.setText(R.id.tv_content, item.getName());
                //helper.setText(R.id.tv_type, item.getType());
                helper.setImageFromUrl(R.id.iv_icon, item.getLogo().getUrl());
                ImageView iv_icon = helper.findViewById(R.id.iv_icon);
                ViewGroup.LayoutParams params = iv_icon.getLayoutParams();
                params.height = 64;
                params.width = 64;
                iv_icon.setLayoutParams(params);
                iv_icon.setImageMatrix(new Matrix());
                helper.setClickListener(R.id.item_ll_live, new View.OnClickListener()
                {

                    /**
                     * <设置控件点击事件>
                     * @param v view对象
                     */
                    @Override
                    public void onClick(View v)
                    {
                        Intent starter = new Intent(getActivity(), LiveActivity.class);
                        starter.putExtra(OttConstants.ID, item.getId());
                        starter.putExtra(OttConstants.NAME, item.getName());
                        starter.putExtra(OttConstants.PLAYTYPE, 2);
                        starter.putExtra(OttConstants.CHANNO, item.getChanno());
                        starter.putExtra(OttConstants.MEDIAID, item.getMediaid());
                        starter.putExtra(OttConstants.ISPLTY, item.getIspltv());
                        starter.putExtra("catchupMediaId",item.getCatchupMediaId());
                        startActivity(starter);
                    }
                });
            }
        });
        return live;
    }

    /**
     * <设置标题>
     * <设置直播页面标题>
     *
     * @return 标题
     */
    @Override
    protected String setHeadTitle()
    {
        return OttConstants.LIVETV;
    }

    /**
     * <设置请求参数>
     * <设置直播页面请求参数>
     *
     * @return 直播请求参数的JSON对象
     */
    @Override
    protected JSONObject getparams()
    {
        HashMap map = new HashMap();
        map.put(OttConstants.FILTERLIST, action+",-1");
        JSONObject request = new JSONObject(map);
        return request;
    }

    /**
     * <获取频道类型>
     * <获取频道类型>
     */
    private void getGener()
    {
        HashMap map = new HashMap();
        JSONObject request = new JSONObject(map);
        VolleyManager.getInstance().postParamJsonObject(OttConstants.GETGENRELIST, request, this, new FmResult()
        {
            @Override
            public <T> void onSucceed(T modle)
            {
                Gson gson = new Gson();
                mGenerList = gson.fromJson(modle.toString(), com.huawei.ott.model.GenerList.class);
                mGenerListEntities = mGenerList.getGenres();
            }
        });
    }

    /**
     * <设置请求地址>
     * <设置直播页面请求地址>
     *
     * @return 直播页面请求地址
     */
    @Override
    protected String getRequestURL()
    {
        return OttConstants.CHANNELLIST;
    }

    /**
     * <解析JSON数据>
     * <解析JSON数据，并检查服务器返回的数据是否为空和类型是否正确的>
     *
     * @return 检查服务器返回的数据是否为空和类型是否正确的标识
     */
    @Override
    protected <T> int onNetSucceed(T response)
    {
        Gson gson = new Gson();
        mChannelList = gson.fromJson(response.toString(), com.huawei.ott.model.ChannelList.class);
        mChannellistEntities = mChannelList.getChannellist();
        return checkNetDataResult(mChannellistEntities);
    }

    /**
     * <页面上标签点击事件>
     * <根据id，进行相应操作>
     *
     * @param group
     *         RadioGroup对象
     * @param checkedId
     *         选择控件id
     */
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId)
    {
        switch(checkedId)
        {
            case R.id.tv_all_channels:
                action = ALLCHANNEL;
                break;
            case R.id.tv_sport:
                action = SPORT;
                break;
            case R.id.tv_news:
                action = NEWS;
                break;
        }
        VolleyShow();
    }
}
