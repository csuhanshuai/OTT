package com.huawei.ott;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.huawei.ott.application.OttApplication;
import com.huawei.ott.base.BaseActivity;
import com.huawei.ott.base.BaseListAdapter;
import com.huawei.ott.holder.ViewHolderHelper;
import com.huawei.ott.model.DynamicRecmFilm;
import com.huawei.ott.net.NetResult;
import com.huawei.ott.net.VolleyManager;
import com.huawei.ott.utills.LogUtils;
import com.huawei.ott.utills.OttConstants;
import com.huawei.ott.utills.UIUtils;
import com.huawei.ottvideo.OttVideoView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;

/**
 * <节目详情页面>
 */
public class PlayDetailActivity extends BaseActivity
{
    private GridView mGridView;
    private CheckBox mCollection;
    private String mAdaptor;
    private String mDirector;
    private String mActor;
    private String mName;
    private String mId;
    private int mPlayType;
    private OttVideoView mOvv;
    private View mInflate;
    private List<DynamicRecmFilm.ContentlistEntity> mContentlist;
    private boolean fullscreen;
    private ViewGroup.LayoutParams mLayoutParams;
    private ViewGroup.LayoutParams mSflayoutParams;
    private String mScore;
    private View mContent;

    /**
     * <设置页面标题是否可见>
     *
     * @return false(不可见)
     */
    @Override
    public boolean setHeadIsVisibility()
    {
        return false;
    }

    /**
     * <页面创建>
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        Intent intent = getIntent();
        mName = intent.getStringExtra(OttConstants.NAME);
        mActor = intent.getStringExtra(OttConstants.ACTOR);
        mDirector = intent.getStringExtra(OttConstants.DIRECTOR);
        mAdaptor = intent.getStringExtra(OttConstants.ADAPTOR);
        mPlayType = intent.getIntExtra(OttConstants.PLAYTYPE, 1);
        mScore = intent.getStringExtra(OttConstants.SCORE);
        mId = intent.getStringExtra(OttConstants.ID);
        initView();
        getNetPlay();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON, WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);//保持屏幕不变黑
        super.onCreate(savedInstanceState);
    }

    /**
     * <布局填充>
     */
    private void initView()
    {
        mContent = View.inflate(this, R.layout.activity_play_detail, null);
        mInflate = mContent;
        ( (TextView)mInflate.findViewById(R.id.tv_move_name) ).setText(mName);
        mInflate.findViewById(R.id.back_finish).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                onBackPressed();
            }
        });
        ( (TextView)mInflate.findViewById(R.id.tv_ep_name_content) ).setText(mDirector);
        ( (TextView)mInflate.findViewById(R.id.tv_ep_rank_content) ).setText(mActor);
        ( (TextView)mInflate.findViewById(R.id.tv_move_num) ).setText(mScore);
        mCollection = (CheckBox)mInflate.findViewById(R.id.cb_love);
        mOvv = (OttVideoView)mInflate.findViewById(R.id.ovv);
        mOvv.setSettingVisibility(false);
        mOvv.setRecordVisibility(false);
        mSflayoutParams = mOvv.getSurface().getLayoutParams();
        mLayoutParams = mOvv.getLayoutParams();
        mOvv.setVoidDisplayHeight(OttApplication.getScreenHeight()*2/5);
        mCollection.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                if(isChecked)
                {
                    //添加到收藏目录
                    addFavarite();

                }else
                {
                    //删除添加
                    deleteFavarite();
                }
            }
        });
        mOvv.setOnOTTClickListener(new OttVideoView.OTTClickListener()
        {
            /**
             * <播放器設置>
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
                Intent intent = new Intent(PlayDetailActivity.this, IndividualRecordActivity.class);
                intent.putExtra("mediaId", mId);
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
                    //                    mContent.setSystemUiVisibility(View.INVISIBLE);
                    getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
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
        });
        mGridView = (GridView)mInflate.findViewById(R.id.gv_play_detail);
    }

    /**
     * <设置页面标题>
     *
     * @return 页面标题
     */
    @Override
    protected String setHeadTitle()
    {
        return "detail";
    }

    /**
     * <设置请求参数>、
     *
     * @return 存放请求参数的JSONObject对象
     */
    @Override
    protected JSONObject getParams()
    {
        HashMap map = new HashMap();
        map.put("vodid", mId);
        map.put("count", 9);
        map.put("offset", 0);
        map.put("type", 1);

        return new JSONObject(map);
    }

    /**
     * <设置请求地址>、
     *
     * @return 请求地址
     */
    @Override
    protected String getRequestURL()
    {
        return OttConstants.QUERYDYNAMICRECMFILM;
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
        DynamicRecmFilm dynamicRecmFilm = VolleyManager.getInstance().gson.fromJson(response.toString(), DynamicRecmFilm.class);
        mContentlist = dynamicRecmFilm.getContentlist();
        return checkNetDataResult(mContentlist);
    }

    /**
     * <节目详情页面布局>
     * <访问网络成功之后，创建布局>
     *
     * @return
     */
    @Override
    protected View createAfterNetScceed()
    {
        mGridView.setAdapter(new BaseListAdapter<DynamicRecmFilm.ContentlistEntity>(mContentlist)
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
                return ViewHolderHelper.get(UIUtils.getContext(), convertView, parent, R.layout.item_grid_reco, position);
            }

            /**
             * <布局填充>
             * @param helper ViewHolderHelper对象
             * @param item 节目内容列表对象
             */
            @Override
            protected void convert(ViewHolderHelper helper, DynamicRecmFilm.ContentlistEntity item)
            {
                DynamicRecmFilm.ContentlistEntity.PictureEntity picture = item.getPicture();
                helper.setImageFromUrl(R.id.iv_item_reco_grid, picture.getIcon());
                helper.setText(R.id.tv_item_reco_grid, item.getName());
                helper.setText(R.id.tv_scroe, item.getScoresum());
            }
        });
        return mInflate;
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
                String nf = object.optString("retmsg");
                if(TextUtils.isEmpty(nf))
                {
                    try
                    {

                        String url = object.get("url").toString();
                        if(url != null)
                        {
                            mOvv.setPath(url, mName);
                        }else
                        {
                            Toast.makeText(getApplicationContext(), OttConstants.errorurl, Toast.LENGTH_SHORT).show();
                        }
                    }catch(JSONException e)
                    {
                        e.printStackTrace();
                    }
                }else
                {
                    Toast.makeText(getApplicationContext(), nf, Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    /**
     * 添加收藏
     */
    private void addFavarite()
    {
        HashMap map = new HashMap();
        map.put("action", "ADD");
        map.put("contentid", mId);

        JSONObject request = new JSONObject(map);
        VolleyManager.getInstance().postParamJsonObject(OttConstants.FAVARITEOPERATION, request, this, new NetResult()
        {
            /**
             * <访问网络失败>
             */
            @Override
            public void onFailure()
            {
                LogUtils.e("添加到收藏失败。。。");
            }

            /**
             * <访问网络成功>
             */
            @Override
            public <T> void onSucceed(T modle)
            {
                //收藏列表添加操作
                LogUtils.e("添加成功");
            }
        });

    }

    /**
     * 删除收藏
     */

    private void deleteFavarite()
    {
        HashMap map = new HashMap();
        map.put("action", "DELETE");
        map.put("contentid", mId);

        JSONObject request = new JSONObject(map);
        VolleyManager.getInstance().postParamJsonObject(OttConstants.FAVARITEOPERATION, request, this, new NetResult()
        {
            /**
             * <访问网络失败>
             */
            @Override
            public void onFailure()
            {
                LogUtils.e("删除收藏失败。。。");
            }

            /**
             * <访问网络成功>
             */
            @Override
            public <T> void onSucceed(T modle)
            {
                //收藏列表添加操作
                LogUtils.e("删除成功");
            }
        });

    }

    /**
     * <页面销毁之后的操作>
     * <释放播放器资源>
     */
    @Override
    public void onDestroy()
    {
        mOvv.release();
        super.onDestroy();
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
