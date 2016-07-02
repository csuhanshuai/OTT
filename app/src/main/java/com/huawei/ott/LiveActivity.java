package com.huawei.ott;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.huawei.ott.application.OttApplication;
import com.huawei.ott.base.BaseActivity;
import com.huawei.ott.base.BaseListAdapter;
import com.huawei.ott.holder.ViewHolderHelper;
import com.huawei.ott.model.PlayBillList;
import com.huawei.ott.net.NetResult;
import com.huawei.ott.net.VolleyManager;
import com.huawei.ott.utills.DateUtils;
import com.huawei.ott.utills.LogUtils;
import com.huawei.ott.utills.OttConstants;
import com.huawei.ott.utills.UIUtils;
import com.huawei.ottvideo.OttVideoType;
import com.huawei.ottvideo.OttVideoView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;

/**
 * <直播页面activity>
 */
public class LiveActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener
{

    private ListView mListView;
    private List<PlayBillList.PlaybilllistEntity> mPlaybilllistEntities;
    private OttVideoView mOvv;
    private String mId, mMediaId, mChanNo,mCatchupMediaId;
    private int mPlayType;
    private String YESTODAY = "YESTODAY";
    private String YESTODAY1 = "YESTODAY1";
    private String YESTODAY2 = "YESTODAY2";
    private String YESTODAY3 = "YESTODAY3";
    private String YESTODAY4 = "YESTODAY4";
    private String YESTODAY5 = "YESTODAY5";
    private String TOMORROW = "TOMORROW";
    private String TOMORROW1 = "TOMORROW1";
    private String TOMORROW2 = "TOMORROW2";
    private String TODAY = "TODAY";
    private String day = TODAY;
    private boolean fullscreen;
    private RadioButton rb1, rb2, rb3, rb4, rb5, rb6,rb7,rb8,rb9,rb10;
    private TextView tv_title;
    private HorizontalScrollView mHorizontalScrollView;
    private String mName;
    private BaseListAdapter mAdapter;
    private RelativeLayout mRl_progress;
    private String mIsplty;


    /**
     * <直播页面标题>
     *
     * @return 直播页面标题
     */
    @Override
    protected String setHeadTitle()
    {
        return "详情";
    }

    /**
     * <设置页面标题是否显示>
     *
     * @return false（不显示）
     */
    @Override
    public boolean setHeadIsVisibility()
    {
        return false;
    }

    /**
     * <设置请求参数>
     *
     * @return JSONObject对象
     */
    @Override
    protected JSONObject getParams()
    {
        String date = null;
        if(day.equals(TODAY))
        {
            date = DateUtils.getCurTimeAddND(0, "yyyyMMddHHmmss");
        }else if(day.equals(YESTODAY))
        {
            date = DateUtils.getCurTimeAddND(-1, "yyyyMMddHHmmss");
        }else if(day.equals(YESTODAY1))
        {
            date = DateUtils.getCurTimeAddND(-2, "yyyyMMddHHmmss");
        }else if(day.equals(YESTODAY2))
        {
            date = DateUtils.getCurTimeAddND(-3, "yyyyMMddHHmmss");
        }else if(day.equals(YESTODAY3))
        {
            date = DateUtils.getCurTimeAddND(-4, "yyyyMMddHHmmss");//yyyyMMddHHmmss
        }else if(day.equals(YESTODAY4))
        {
            date = DateUtils.getCurTimeAddND(-5, "yyyyMMddHHmmss");
        }else if(day.equals(YESTODAY5))
        {
            date = DateUtils.getCurTimeAddND(-6, "yyyyMMddHHmmss");
        }
        else if(day.equals(TOMORROW))
        {
            date = DateUtils.getCurTimeAddND(1, "yyyyMMddHHmmss");
        }
        else if(day.equals(TOMORROW1))
        {
            date = DateUtils.getCurTimeAddND(2, "yyyyMMddHHmmss");
        }
        else if(day.equals(TOMORROW2))
        {
            date = DateUtils.getCurTimeAddND(3, "yyyyMMddHHmmss");
        }

        HashMap map = new HashMap();
        map.put("begintime", "20151210");//yyyyMMddhhmmss
        //map.put("endtime", "20151211");//yyyyMMddhhmmss
        map.put("type", 1);
        map.put("count", 10);
        map.put("offset", 0);
        map.put("orderType", 0);
        JSONObject request = new JSONObject(map);
        return request;
    }

    /**
     * <设置请求地址>
     *
     * @return 请求地址
     */
    @Override
    protected String getRequestURL()
    {
        return OttConstants.PLAYBILLLIST;
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
        Intent intent = getIntent();
        mId = intent.getStringExtra("id");
        mPlayType = intent.getIntExtra("playType", 1);
        mMediaId = intent.getStringExtra("mediaid");
        mChanNo = intent.getStringExtra("channo");
        mName = intent.getStringExtra(OttConstants.NAME);
        mIsplty = intent.getStringExtra(OttConstants.ISPLTY);
        mCatchupMediaId = intent.getStringExtra("catchupMediaId");
        Gson gson = new Gson();
        PlayBillList playBillList = null;
        playBillList = gson.fromJson(response.toString(), com.huawei.ott.model.PlayBillList.class);
        mPlaybilllistEntities = playBillList.getPlaybilllist();
        if(null != mAdapter)
        {
            if(null == mPlaybilllistEntities)
            {
                mRl_progress.setVisibility(View.GONE);
            }
            {
                mListView.setVisibility(View.VISIBLE);
                mAdapter.setData(mPlaybilllistEntities);
            }
        }else
        {
            getNetPlay();
        }
        return checkNetDataResult(mPlaybilllistEntities);
    }

    /**
     * <播放视频>
     */
    private void getNetPlay()
    {
        HashMap map = new HashMap();
        map.put("contentid", mId);
        map.put("playtype", mPlayType);
        JSONObject request = new JSONObject(map);
        VolleyManager.getInstance().postParamJsonObject(OttConstants.PLAY, request, this, new NetResult()
        {
            /**
             * <访问网络失败>
             */
            @Override
            public void onFailure()
            {
                LogUtils.e("获取视频链接失败。。。");
            }

            /**
             * <访问网络成功>
             */
            @Override
            public <T> void onSucceed(T modle)
            {
                JSONObject object = (JSONObject)modle;
                try
                {
                    if(OttConstants.NETSUCCEED.equals(object.get("retcode").toString()))
                    {
                        String url = object.get("url").toString();
                        mOvv.setPathAutoPlay(url, mName, "1".equals(mIsplty) ? OttVideoType.TSTV : OttVideoType.TV);
                    }else
                    {
                        Toast.makeText(getApplicationContext(), object.get("retcode").toString(), Toast.LENGTH_SHORT).show();
                    }
                }catch(JSONException e)
                {
                    e.printStackTrace();
                }
            }
        });

    }


    /**
     * <播放视频>
     */
    private void getCatchUpPlay(String beginTime, String id)
    {
        HashMap map = new HashMap();
        map.put("contentid", mId);
        map.put("playtype", 4);
        //map.put("begintime",beginTime);
        map.put("playbillid", id);
        map.put("catchupMediaId",mCatchupMediaId);
        JSONObject request = new JSONObject(map);
        VolleyManager.getInstance().postParamJsonObject(OttConstants.PLAY, request, this, new NetResult()
        {
            /**
             * <访问网络失败>
             */
            @Override
            public void onFailure()
            {
                LogUtils.e("获取视频链接失败。。。");
            }

            /**
             * <访问网络成功>
             */
            @Override
            public <T> void onSucceed(T modle)
            {
                JSONObject object = (JSONObject)modle;
                try
                {
                    if(OttConstants.NETSUCCEED.equals(object.get("retcode").toString()))
                    {
                        String url = object.get("url").toString();
                        url =  url.replace(url.substring(url.indexOf("playseek"), url.indexOf("timezone")), "");
                        mOvv.switchPlayPath(url, OttVideoType.VOD);
                    }else
                    {
                        Toast.makeText(getApplicationContext(), object.get("retcode").toString(), Toast.LENGTH_SHORT).show();
                    }
                }catch(JSONException e)
                {
                    e.printStackTrace();
                }
            }
        });

    }

    /**
     * <直播节目列表页面布局>
     * <访问网络成功之后，创建布局>
     *
     * @return view
     */
    @Override
    protected View createAfterNetScceed()
    {
        View content = View.inflate(this, R.layout.fm_program, null);
        RadioGroup rgDay = (RadioGroup)content.findViewById(R.id.rg_day);
        mRl_progress = (RelativeLayout)content.findViewById(R.id.rl_progress);
        rgDay.setOnCheckedChangeListener(this);
        mHorizontalScrollView = (HorizontalScrollView)content.findViewById(R.id.hs_item);
        tv_title = (TextView)content.findViewById(R.id.tv_title);

        /**
         * rb1-6 表示直播页面上的六个按钮
         */
        tv_title.setText("Today");
        rb1 = (RadioButton)content.findViewById(R.id.tv_program_16);
        rb2 = (RadioButton)content.findViewById(R.id.tv_program_17);
        rb3 = (RadioButton)content.findViewById(R.id.tv_program_18);
        rb4 = (RadioButton)content.findViewById(R.id.tv_program_19);
        rb5 = (RadioButton)content.findViewById(R.id.tv_program_20);
        rb6 = (RadioButton)content.findViewById(R.id.tv_program_21);
        rb7 = (RadioButton)content.findViewById(R.id.tv_program_today);
        rb8 = (RadioButton)content.findViewById(R.id.tv_program_tomorrow);
        rb9 = (RadioButton)content.findViewById(R.id.tv_program_tomorrow1);
        rb10 = (RadioButton)content.findViewById(R.id.tv_program_tomorrow2);
        content.findViewById(R.id.back_finish).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                onBackPressed();
            }
        });

        rb1.setText(DateUtils.getCurTimeAddND(-6, "EEEE").substring(0, 3)+" "+DateUtils.getCurTimeAddND(-6, "dd/MM"));
        rb2.setText(DateUtils.getCurTimeAddND(-5, "EEEE").substring(0, 3)+" "+DateUtils.getCurTimeAddND(-5, "dd/MM"));
        rb3.setText(DateUtils.getCurTimeAddND(-4, "EEEE").substring(0, 3)+" "+DateUtils.getCurTimeAddND(-4, "dd/MM"));
        rb4.setText(DateUtils.getCurTimeAddND(-3, "EEEE").substring(0, 3)+" "+DateUtils.getCurTimeAddND(-3, "dd/MM"));
        rb5.setText(DateUtils.getCurTimeAddND(-2, "EEEE").substring(0, 3)+" "+DateUtils.getCurTimeAddND(-2, "dd/MM"));
        rb6.setText(DateUtils.getCurTimeAddND(-1, "EEEE").substring(0, 3)+" "+DateUtils.getCurTimeAddND(-1, "dd/MM"));
        //rb7.setText(DateUtils.getCurTimeAddND(3, "EEEE").substring(0, 3)+" "+DateUtils.getCurTimeAddND(3, "dd/MM"));
        rb8.setText(DateUtils.getCurTimeAddND(1, "EEEE").substring(0, 3)+" "+DateUtils.getCurTimeAddND(1, "dd/MM"));
        rb9.setText(DateUtils.getCurTimeAddND(2, "EEEE").substring(0, 3)+" "+DateUtils.getCurTimeAddND(2, "dd/MM"));
        rb10.setText(DateUtils.getCurTimeAddND(3, "EEEE").substring(0, 3)+" "+DateUtils.getCurTimeAddND(3, "dd/MM"));
        mHorizontalScrollView.post(new Runnable()
        {
            @Override
            public void run()
            {
                mHorizontalScrollView.scrollTo(1000, 0);
            }
        });

        mListView = (ListView)content.findViewById(R.id.lv_program);
        mAdapter = new BaseListAdapter<PlayBillList.PlaybilllistEntity>(mPlaybilllistEntities)
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
                return ViewHolderHelper.get(UIUtils.getContext(), convertView, parent, R.layout.item_list_program, position);
            }

            /**
             * <布局填充>
             * @param helper ViewHolderHelper对象
             * @param item 节目列表对象
             */
            @Override
            protected void convert(ViewHolderHelper helper, final PlayBillList.PlaybilllistEntity item)
            {
                helper.setText(R.id.tv_program_item_content, item.getName());
                String time = item.getStarttime();//2015-12-14 07:35:26 UTC+02:00
                time = time.substring(0, 19);
                time = time.replace("-", "").replace(" ", "").replace(":", "");
                final String time2 = time;
                String arr[] = item.getStarttime().split(" ");

                final String date = arr[1].substring(0, 5);
                final String id = item.getId();
                helper.setText(R.id.tv_program_item_time, date);
//                mIsplty = item.getIsppv();
                helper.setClickListener(R.id.ll_catch_up, new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        getCatchUpPlay(time2, id);
                    }
                });
            }
        };
        mListView.setAdapter(mAdapter);

        mOvv = (OttVideoView)content.findViewById(R.id.ovv_fragment);
        mOvv.setSettingVisibility(false);
        mOvv.setRecordVisibility(false);
        mOvv.setVoidDisplayHeight(OttApplication.getScreenHeight()*2/5);

        mOvv.setOnOTTClickListener(new OttVideoView.OTTClickListener()
        {
            /**
             * <播放器设置>
             */
            @Override
            public void actionSetting()
            {

            }

            /**
             * <播放器录制>
             */
            @Override
            public void actionRecord()
            {
                Intent intent = new Intent(LiveActivity.this, IndividualRecordActivity.class);
                intent.putExtra("id", mId);
                intent.putExtra("playType", mPlayType);
                intent.putExtra("channo", mChanNo);
                intent.putExtra("mediaid", mMediaId);
                startActivity(intent);
            }

            /**
             * <播放器全屏>
             */
            @Override
            public void actionFullScreen()
            {
                if(!fullscreen)
                {//设置RelativeLayout的全屏模式
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                    fullscreen = true;//改变全屏/`
                    mOvv.setVoidDisplayHeight(-1);
                    mOvv.hideTools();
                    getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
                }else
                {//设置RelativeLayout的窗口模式
                    fullscreen = false;//改变全屏/窗口的标记
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                    mOvv.setVoidDisplayHeight(OttApplication.getScreenHeight()*2/5);

                    WindowManager.LayoutParams attr = getWindow().getAttributes();
                    attr.flags &= (~WindowManager.LayoutParams.FLAG_FULLSCREEN);
                    getWindow().setAttributes(attr);
                    getWindow().clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
                }
            }
        });
        return content;
    }

    /**
     * <页面销毁之后的操作>
     * <释放播放器资源>
     */
    @Override
    public void onDestroy()
    {
        super.onDestroy();
        if(mOvv != null)
        {
            mOvv.release();
            mOvv = null;
        }
    }

    /**
     * <日期选择事件>
     */
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId)
    {
        switch(checkedId)
        {
            case R.id.tv_program_16:
                day = YESTODAY5;
                break;
            case R.id.tv_program_17:
                day = YESTODAY4;
                break;
            case R.id.tv_program_18:
                day = YESTODAY3;
                break;
            case R.id.tv_program_19:
                day = YESTODAY2;
                break;
            case R.id.tv_program_20:
                day = YESTODAY1;
                break;
            case R.id.tv_program_21:
                day = YESTODAY;
                break;
            case R.id.tv_program_today:
                day = TODAY;
                break;
            case R.id.tv_program_tomorrow:
                day = TOMORROW;
                break;
            case R.id.tv_program_tomorrow1:
                day = TOMORROW1;
                break;
            case R.id.tv_program_tomorrow2:
                day = TOMORROW2;
                break;

        }

        tv_title.setText(( (RadioButton)findViewById(group.getCheckedRadioButtonId()) ).getText());
        refreshListData();
    }

    private void refreshListData()
    {
        mListView.setVisibility(View.GONE);
        VolleyManager.getInstance().postParamJsonObject(getRequestURL(), getParams(), this, new NetResult() {
            @Override
            public void onFailure(){
                mRl_progress.setVisibility(View.GONE);
            }

            @Override
            public <T> void onSucceed(T modle){
                onNetSucceed(modle);
            }
        });
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON, WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);//保持屏幕不变黑
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onBackPressed()
    {
        if(!fullscreen)
        {
            super.onBackPressed();
        }else
        {//设置RelativeLayout的窗口模式
            fullscreen = false;//改变全屏/窗口的标记
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            mOvv.setVoidDisplayHeight(OttApplication.getScreenHeight()*2/5);

            WindowManager.LayoutParams attr = getWindow().getAttributes();
            attr.flags &= ( ~WindowManager.LayoutParams.FLAG_FULLSCREEN );
            getWindow().setAttributes(attr);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
    }
}
