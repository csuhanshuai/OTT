package com.huawei.ott.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.huawei.ott.MainActivity;
import com.huawei.ott.R;
import com.huawei.ott.SearchActivity;
import com.huawei.ott.utills.OttConstants;
import com.huawei.ott.utills.SPUtills;
import com.huawei.ott.utills.UIUtils;


public class MyTVfm extends Fragment implements View.OnClickListener
{
    private android.widget.TextView user;
    private android.widget.LinearLayout orderservice;
    private android.widget.LinearLayout parentalcontrol;
    private android.widget.LinearLayout mydevice;
    private android.widget.LinearLayout download;

    private RelativeLayout rlHead;
    private ImageButton headSearchAction;
    private ImageButton headBackAction;
    private TextView headText;

    private void assignViews(View inflate)
    {
        rlHead = (RelativeLayout)inflate.findViewById(R.id.rl_head);
        headSearchAction = (ImageButton)inflate.findViewById(R.id.head_search_action);
        headBackAction = (ImageButton)inflate.findViewById(R.id.head_back_action);
        headText = (TextView)inflate.findViewById(R.id.head_text);

        headText.setText(UIUtils.getString(R.string.head_mytv));
        headBackAction.setOnClickListener(this);
        headSearchAction.setOnClickListener(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View inflate = inflater.inflate(R.layout.fm_mytv, null);
        this.download = (LinearLayout)inflate.findViewById(R.id.download_ll);
        this.mydevice = (LinearLayout)inflate.findViewById(R.id.mydevice_ll);
        this.parentalcontrol = (LinearLayout)inflate.findViewById(R.id.parentalcontrol_ll);
        this.orderservice = (LinearLayout)inflate.findViewById(R.id.orderservice_ll);
        ( (TextView)inflate.findViewById(R.id.user) )
                .setText(SPUtills.get(UIUtils.getContext(), OttConstants.username, "null").toString());
        assignViews(inflate);

        return inflate;
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser)
    {

    }

    @Override
    public void onClick(View v)
    {
        switch(v.getId())
        {
            case R.id.head_search_action:
                Intent intent = new Intent(UIUtils.getContext(), SearchActivity.class);
                startActivity(intent);
                break;
            case R.id.head_back_action:
                ( (MainActivity)getActivity() ).mViewPager.setCurrentItem(1);
                break;
        }

    }
}
