package com.huawei.ott;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.huawei.ott.model.DownLoad;
import com.huawei.ott.net.NetResult;
import com.huawei.ott.net.VolleyManager;
import com.huawei.ott.utills.OttConstants;

import org.json.JSONObject;

import java.util.HashMap;


public class IndividualRecordActivity extends Activity
{

    private Button btn_ok;
    private String mediaId;
    private String channelNo;
    private String mId;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_individual_record);

        Intent intent = getIntent();
        mediaId = intent.getStringExtra("mediaid");
        channelNo = intent.getStringExtra("channo");
        mId = intent.getStringExtra("id");

        btn_ok = (Button)findViewById(R.id.ok_button);
        btn_ok.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                getData();

            }


        });
    }


    private void getData()
    {

        HashMap map = new HashMap();
        map.put("type", 2);
        map.put("channelNo", channelNo);
        map.put("mediaId", mediaId);
        //        map.put("beginTime", "20151212150000");
        map.put("checkExist", 0);
        map.put("programId", mId);
        JSONObject request = new JSONObject(map);

        VolleyManager.getInstance().postParamJsonObject(OttConstants.DOWNLOAD, request, this, new NetResult()
        {
            @Override
            public void onFailure()
            {

            }

            @Override
            public <T> void onSucceed(T modle)
            {
                DownLoad downLoad = VolleyManager.getInstance().gson.fromJson(modle.toString(), DownLoad.class);
            }
        });
    }

}
