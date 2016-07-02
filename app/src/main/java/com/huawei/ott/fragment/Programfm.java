package com.huawei.ott.fragment;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.gson.Gson;
import com.huawei.ott.R;
import com.huawei.ott.base.BaseFragment;
import com.huawei.ott.base.BaseListAdapter;
import com.huawei.ott.holder.ViewHolderHelper;
import com.huawei.ott.model.PlayBillList;
import com.huawei.ott.utills.OttConstants;
import com.huawei.ott.utills.UIUtils;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Jonas on 2015/11/24.
 */
public class Programfm extends BaseFragment
{

    private ListView mListView;
    private List<PlayBillList.PlaybilllistEntity> mPlaybilllistEntities;

    @Override
    protected String setHeadTitle()
    {
        return null;
    }

    @Override
    protected JSONObject getparams()
    {
        HashMap map = new HashMap();
        map.put(OttConstants.BEGINTIME, "20150925120000");//yyyyMMddhhmmss
        map.put(OttConstants.TYPE, 0);
        map.put(OttConstants.COUNT, 10);
        map.put(OttConstants.OFFSET, 0);
        map.put(OttConstants.ORDERTYPE, 0);
        JSONObject request = new JSONObject(map);
        return request;
    }

    public boolean setHeadIsVisibility()
    {
        return false;
    }

    @Override
    protected String getRequestURL()
    {
        return OttConstants.PLAYBILLLIST;
    }

    @Override
    protected <T> int onNetSucceed(T response)
    {
        Gson gson = new Gson();
        PlayBillList playBillList = null;
        playBillList = gson.fromJson(response.toString(), com.huawei.ott.model.PlayBillList.class);
        mPlaybilllistEntities = playBillList.getPlaybilllist();
        return checkNetDataResult(mPlaybilllistEntities);
    }


    @Override
    protected View createAfterNetScceed()
    {
        View program = View.inflate(UIUtils.getContext(), R.layout.fm_program, null);
        mListView = (ListView)program.findViewById(R.id.lv_program);
        mListView.setAdapter(new BaseListAdapter<PlayBillList.PlaybilllistEntity>(mPlaybilllistEntities)
        {
            @Override
            protected ViewHolderHelper getAdapterHelper(int position, View convertView, ViewGroup parent)
            {
                return ViewHolderHelper.get(UIUtils.getContext(), convertView, parent, R.layout.item_list_program, position);
            }

            @Override
            protected void convert(ViewHolderHelper helper, PlayBillList.PlaybilllistEntity item)
            {
                helper.setText(R.id.tv_program_item_content, item.getName());
                helper.setText(R.id.tv_program_item_time, item.getStarttime());

            }
        });
        return program;
    }
}
