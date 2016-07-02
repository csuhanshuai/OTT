package com.huawei.ottvideo;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.AudioManager;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.huawei.dmpbase.DmpBase;
import com.huawei.dmpbase.DmpLog;
import com.huawei.playerinterface.DmpPlayer;
import com.huawei.playerinterface.MediaFactory;
import com.huawei.playerinterface.parameter.HAPlayerConstant;
import com.huawei.playerinterface.parameter.HASetParam;

import java.util.ArrayList;

/**
 * Created by Jonas on 2015/11/30.
 * 视频播放器
 */
public class OttVideoView extends LinearLayout implements SurfaceHolder.Callback, View.OnClickListener, SeekBar.OnSeekBarChangeListener, Animation.AnimationListener
{


    private static final int COMPLETED = 0;
    /**
     * 停止播放状态
     */
    private static final int VOID_STOP = 0;
    /**
     * 正在播放状态
     */
    private static final int VOID_PLAYING = 1;
    /**
     * 暂停播放
     */
    private static final int VOID_PAUSE = 3;
    /**
     * 继续播放
     */
    private static final int VOID_CONTINUE = 2;

    private String path = "";


    private Context context;
    private SurfaceView sfv = null;
    private SurfaceHolder sfh;

    private SeekBar mSeekBar;
    private CheckBox btn_play;
    private DmpPlayer player;
    private int runstate;
    private int mVideoWidth;
    private int mVideoHeight;
    private TextView live;
    private TextView auto;
    private TextView time;
    private TextView current_time;
    private OTTClickListener listener;
    private PopupWindow mPopupWindow;
    private TextView mAuto;
    private AudioManager mAudioManager;
    private int currentVolume;
    private int maxVolume;

    private String current_length = null;
    private TranslateAnimation mTa;
    private TranslateAnimation mTad;
    private RelativeLayout mTools;
    private boolean toolShow = true;
    private ViewGroup.LayoutParams mSfvLayoutParams;
    private float mMovY;
    private float mDownY;
    private boolean moveed;
    private TextView mVoiceShow;
    private float mIndex;
    private int voiceHeight;
    private LinearLayout mLl_buffering;
    private Runnable mAutoHideTolls;
    /**
     * 播放工具条 自动隐藏时间
     */
    private long AUTOHIDETOOL = 5000;
    private String movename;
    private OttVideoType mVideoType;
    private int bookMarkTime = 7200000;
    private float mDownX;
    private float mMovX;
    private int voiceWidth;
    private boolean fullscreen;

    private void assignViews()
    {
        live = (TextView)findViewById(R.id.live);
        findViewById(R.id.button1).setOnClickListener(this);
        auto = (TextView)findViewById(R.id.auto);
        time = (TextView)findViewById(R.id.time);
        current_time = (TextView)findViewById(R.id.current_time);
        findViewById(R.id.setting).setOnClickListener(this);
        findViewById(R.id.narrow).setOnClickListener(this);
        auto.setOnClickListener(this);
        auto.setVisibility(GONE);
    }

    public void setRecordVisibility(boolean visibility)
    {
        findViewById(R.id.button1).setVisibility(visibility ? VISIBLE : GONE);
    }

    public void setSettingVisibility(boolean visibility)
    {
        findViewById(R.id.setting).setVisibility(visibility ? VISIBLE : GONE);
    }


    public OttVideoView(Context context)
    {
        super(context);
    }

    public OttVideoView(Context context, AttributeSet attrs)
    {
        this(context, attrs, 0);
    }

    public SurfaceView getSurface()
    {
        return sfv;
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public OttVideoView(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        this.context = context;
        setOrientation(VERTICAL);
        LayoutInflater.from(context).inflate(R.layout.ottvideoviewlayout, this, true);
        mSeekBar = (SeekBar)findViewById(R.id.seekbar);
        mTools = (RelativeLayout)mSeekBar.getParent().getParent();
        sfv = (SurfaceView)findViewById(R.id.SurfaceView01);
        mAuto = (TextView)findViewById(R.id.auto);
        mVoiceShow = (TextView)findViewById(R.id.tv_voice);
        final TextView tv_Buffering = (TextView)findViewById(R.id.tv_Buffering);
        mLl_buffering = (LinearLayout)findViewById(R.id.ll_Buffering);
        sfh = sfv.getHolder();
        //为SurfaceHolder添加回调
        sfh.addCallback(this);
        //为进度条添加进度更改事
        mSeekBar.setOnSeekBarChangeListener(this);
        btn_play = (CheckBox)findViewById(R.id.Button01);
        assignViews();
        setClickable(true);
        initAnimation();
        mSfvLayoutParams = ( (RelativeLayout)sfv.getParent() ).getLayoutParams();
        //为不同按钮设置监听响应
        btn_play.setOnClickListener(this);
        mAudioManager = (AudioManager)context.getSystemService(Context.AUDIO_SERVICE);
        maxVolume = mAudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        currentVolume = mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);

        DmpBase.open(context);
                DmpLog.openLogCatLog(DmpLog.LOG_DEBUG);
        DmpLog.openLocalFileLog("/sdcard/Shark", DmpLog.LOG_DEBUG);
        int codecMode = MediaFactory.getAdapterDecoder(context, path);
        MediaFactory.setSurfaceHolderFormat(context, sfh, codecMode);
        player = MediaFactory.create(context, codecMode, path);

        //性能自适应开关。播放器自动根据设备自身性能匹配播放码率。
        player.setProperties(HASetParam.PERFORMANCE_ADAPTIVE, 1);

        //缓冲监听
        player.setOnBufferingUpdateListener(new DmpPlayer.OnBufferingUpdateListener() {
            @Override
            public void onBufferingUpdate(DmpPlayer dmpPlayer, int i){
                tv_Buffering.setText(i+"%");
                if(runstate != VOID_STOP && i == 0) {
                    mLl_buffering.setVisibility(VISIBLE);
                }else if(i>=100) {
                    tv_Buffering.setText("loading...");
                    mLl_buffering.setVisibility(GONE);
                    //缓冲完后开启自动隐藏
                    if(toolShow) {
                        handler.postDelayed(mAutoHideTolls, AUTOHIDETOOL);
                    }
                }
            }
        });
        //播放结束监听
        player.setOnCompletionListener(new DmpPlayer.OnCompletionListener()
        {
            @Override
            public void onCompletion(DmpPlayer dmpPlayer)
            {
                //视频播放完毕被回调
                Log.w("ott", "播放完毕");
                btn_play.setChecked(true);
                player.seekTo(0);
                player.stop();
                runstate = VOID_STOP;
                showTools();
                mLl_buffering.setVisibility(GONE);
                handler.removeCallbacks(mAutoHideTolls);//暂停了就不隐藏了
            }
        });

        player.setOnErrorListener(new DmpPlayer.OnErrorListener() {
            /**
             * <p>功能：视频播放异常时触发</p>
             * @param dmpPlayer 播放器
             * @param what ErrorCode 错误类型
             * @param extra 补充参数
             * @param obj 返回错误发生的时机，类型：String
             * @return true/false
             */
            @Override
            public boolean onError(DmpPlayer dmpPlayer, int what, int extra, Object obj){
                Log.e("error", "播放错误。。"+obj.toString());
                //发生错误重新播放
                runstate = VOID_STOP;
                String error = "play error";
                switch(what) {
                    case 100:
                        error = "播放服务终止，此时客户端必须释放播放器并重新创建才能使用";
                        break;
                    case 101:
                        error = "不支持的编码格式";
                        break;
                    case 102:
                        error = "不支持的文件封装格式。";
                        break;
                    case 103:
                        error = "打开媒体失败，被对端主动关闭";
                        break;
                    case 104:
                        error = "打开媒体超时";
                        break;
                    case 105:
                        error = "收到标准协议的错误，例如网络404，500等";
                        break;
                    case 107:
                        error = "CA播放失败。可能原因：CA注册失败，获取密钥失败，解密失败等";
                        break;
                    case 108:
                        error = "播放器内部错误";
                        break;
                    case 109:
                        error = "播放器其他外部错误";
                        break;

                }
                tv_Buffering.setText(error);
                btn_play.setChecked(true);
                if(!toolShow) {
                    showTools();
                }
                handler.removeCallbacks(mAutoHideTolls);//错了就不隐藏了
                return false;
            }
        });
        initPopwindow();

        mAutoHideTolls = new Runnable()
        {
            @Override
            public void run()
            {
                if(toolShow)
                {
                    Log.d("hidetool", "autohide...");
                    hideTools();
                }
            }
        };


    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh)
    {
        super.onSizeChanged(w, h, oldw, oldh);
        voiceHeight = getMeasuredHeight();
        voiceWidth = getMeasuredWidth();
        currentVolume = ( (int)( currentVolume*1f/maxVolume*1f*voiceHeight ) );
    }

    private void initPopwindow()
    {
        ArrayList<String> data = new ArrayList<>();
        data.add("Auto");
        data.add("High");
        data.add("Super");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), R.layout.pop_item_tv, data);
        View view = View.inflate(getContext(), R.layout.item_list_pop, null);
        ListView mlistview = (ListView)view.findViewById(R.id.lv_pop);
        mlistview.setAdapter(adapter);
        mPopupWindow = new PopupWindow(view, dip2px(47, context), dip2px(90, context));
        mPopupWindow.setFocusable(true);
        mPopupWindow.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        mPopupWindow.setContentView(view);
        mPopupWindow.setOutsideTouchable(true);
        mlistview.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id)
            {
                autoHideTools();
                if(position == 0)
                {
                    player.setProperties(HASetParam.DESIGNATED_BITRATE, 0);
                }else if(position == 1)
                {
                    player.setProperties(HASetParam.DESIGNATED_BITRATE, 1024000);
                }else if(position == 2)
                {
                    player.setProperties(HASetParam.DESIGNATED_BITRATE, 1024000*1.5);
                }
                mAuto.setText(( (TextView)view ).getText().toString());
                mPopupWindow.dismiss();
            }
        });
    }


    private void showpopWindow()
    {

        int[] location = new int[2];
        mAuto.getLocationInWindow(location);
        mPopupWindow.showAtLocation(mAuto, Gravity.NO_GRAVITY, location[0], location[1]-mPopupWindow.getHeight());

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder)
    {
        Log.w("debug", "SurfaceHolder被创建");
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height)
    {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder)
    {
        //销毁SurfaceHolder的时候记录当前的播放位置并停止播放
        if(player != null && player.isPlaying())
        {
            if(null != movename)
            {
                //当前进度写入文件
                SPUtills.put(getContext(), movename, player.getCurrentPosition());
            }
            player.stop();
        }else
        {
            if(null != movename)
            {
                //当前进度写入文件
                SPUtills.put(getContext(), movename, player.getCurrentPosition());
            }
        }
        Log.w("debug", "SurfaceHolder 被销毁,当前进度,"+player.getCurrentPosition());
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onClick(View v)
    {
        if(v.getId() == R.id.Button01)
        {
            if(runstate == VOID_STOP)
            {
                play(0);
                runstate = VOID_PLAYING;//开始
                mLl_buffering.setVisibility(VISIBLE);
                btn_play.setChecked(false);
                autoHideTools();
            }else if(runstate == VOID_PLAYING || runstate == VOID_CONTINUE)
            {
                player.pause();
                runstate = VOID_PAUSE;//暂停播放
                btn_play.setChecked(true);
                handler.removeCallbacks(mAutoHideTolls);//暂停了就不隐藏了

            }else if(runstate == VOID_PAUSE)
            {
                player.start();
                runstate = VOID_CONTINUE;
                btn_play.setChecked(false);
                autoHideTools();

            }
        }else if(v.getId() == R.id.narrow)
        {
            if(listener != null)
            {
                listener.actionFullScreen();
                fullscreen = !fullscreen;
                if(fullscreen)
                {
                    auto.setVisibility(VISIBLE);
                }else
                {
                    auto.setVisibility(GONE);
                }
            }
        }else if(v.getId() == R.id.setting)
        {
            if(listener != null)
            {
                listener.actionSetting();
            }
        }else if(v.getId() == R.id.live)
        {
            //TODO 直播或者时移

        }else if(v.getId() == R.id.auto)
        {
            showpopWindow();
        }else if(v.getId() == R.id.button1)
        {
            if(listener != null)
            {
                listener.actionRecord();
            }
        }
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
    {

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar)
    {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar)
    {
        //当进度条停止修改的时候触发
        //获取当前进度条的刻度
        int progress = seekBar.getProgress();
        if(player != null)
        {
            //设置当前播放的位置
            player.seekTo(progress);
            runstate = VOID_CONTINUE;
            btn_play.setChecked(false);
        }
        autoHideTools();
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void playerSet()
    {
        if(mVideoType == OttVideoType.VOD)
        {
            int codecMode = MediaFactory.getAdapterDecoder(getContext(), path);
            player = MediaFactory.create(getContext(), codecMode, path);
            MediaFactory.setSurfaceHolderFormat(getContext(), sfh, codecMode);
            player.setProperties(HASetParam.VIDEO_TYPE, HAPlayerConstant.VideoType.VOD);
            play((int)SPUtills.get(getContext(), movename, 0));
            live.setVisibility(GONE);
        }else if(mVideoType == OttVideoType.TV)
        {
            player.setProperties(HASetParam.VIDEO_TYPE, HAPlayerConstant.VideoType.TV);
            time.setVisibility(GONE);
            current_time.setVisibility(GONE);
            btn_play.setClickable(false);
            play(0);
        }else
        {
            player.setProperties(HASetParam.VIDEO_TYPE, HAPlayerConstant.VideoType.TSTV);
            player.setProperties(HASetParam.TSTV_LENGTH, 7200000);  //时移长度为7200秒
            player.setProperties(HASetParam.TIME_DIFF_UTC, -8*3600L);
            player.setProperties(HASetParam.HISTORY_PLAY_POINT, bookMarkTime);
            mSeekBar.setProgress(mSeekBar.getMax());
            live.setBackgroundResource(R.drawable.tslive_bg);
            live.setVisibility(VISIBLE);
            ( (LinearLayout)time.getParent() ).setVisibility(VISIBLE);
            play(bookMarkTime);
        }
        runstate = VOID_PLAYING;//开始

    }

    /**
     * 开始播放
     *
     * @param msec
     */
    protected void play(final int msec)
    {
        try
        {

            player.setOnVideoSizeChangedListener(new DmpPlayer.OnVideoSizeChangedListener()
            {
                @Override
                public void onVideoSizeChanged(DmpPlayer dmpPlayer, int i, int i1)
                {
                    dmpPlayer.setProperties(HASetParam.SCALE_MODE, HAPlayerConstant.ScaleMode.FULL_SCREEN);
                }
            });
            player.setDataSource(context, path);
            Log.d("debug", "开始装载");
            player.setDisplay(sfv);
            player.prepareAsync();
            player.setOnPreparedListener(new DmpPlayer.OnPreparedListener()
            {
                @Override
                public void onPrepared(DmpPlayer dmpPlayer)
                {
                    Log.d("debug", "装载完成");
                    mLl_buffering.setVisibility(GONE);
                    dmpPlayer.start();
                    //按照初始位置播放
                    player.seekTo(msec);
                    //设置进度条的最大进度为视频流的最大播放时长
                    int length = player.getDuration();
                    String[] formatLength = timeFormatMore(length);
                    mSeekBar.setMax(length);
                    String str_length = formatLength[0]+":"+formatLength[1]+":"+formatLength[2];
                    time.setText(str_length);
                    //开始线程，更新进度条的刻度
                    new Thread()
                    {
                        @Override
                        public void run()
                        {
                            while(runstate != VOID_STOP)
                            {
                                if(null != player)
                                {
                                    Message msg = new Message();
                                    msg.what = COMPLETED;
                                    handler.sendMessage(msg);
                                    int current = player.getCurrentPosition();
                                    String[] currentLength = timeFormatMore(current);
                                    mSeekBar.setProgress(current);
                                    current_length = currentLength[0]+":"+currentLength[1]+":"+currentLength[2];
                                }
                            }
                        }
                    }.start();
                }
            });
        }catch(Exception e)
        {
            e.printStackTrace();
        }

    }


    /**
     * 动态更新UI
     */
    private Handler handler = new Handler()
    {
        @Override
        public void handleMessage(Message msg)
        {
            if(msg.what == COMPLETED)
            {
                current_time.setText(current_length);
            }
            }
    };

    /**
     * 释放资源
     */
    public void release()
    {
        if(player != null)
        {
            player.stop();
            player.release();
            player = null;
            btn_play.setEnabled(true);
            runstate = VOID_STOP;
            Log.w("OttVideoView", "释放资源...");
            //关闭日志输出到文件
            DmpLog.closeLocalFileLog();
        }
    }

    public String getPath()
    {
        return path;
    }

    public void setPath(final String path, final String movename)
    {
        this.path = path;
        handler.postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                setPathAutoPlay(path, movename, OttVideoType.VOD);

            }
        }, 500);
    }

    /**
     * 这只视频路径 同时自动播放
     *
     * @param path
     */
    public void setPathAutoPlay(String path, String movename, OttVideoType videoType)
    {
        mVideoType = videoType;
        this.movename = movename;
        this.path = path;
        playerSet();

        btn_play.setChecked(false);
        mLl_buffering.setVisibility(VISIBLE);
    }

    public void switchPlayPath(String path, OttVideoType videoType)
    {
        mVideoType = videoType;
        this.path = path;
        movename = null;
        player.stop();
        player.release();
        playerSet();
//        movename = null;
//        player.stop();
//        player.release();

//        play(0);
        if(!toolShow)
        {
            showTools();
        }


    }

    public interface OTTClickListener
    {
        public void actionSetting();

        public void actionRecord();

        public void actionFullScreen();
    }

    public void setOnOTTClickListener(OTTClickListener listener)
    {
        this.listener = listener;
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(float dpValue, Context context)
    {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int)( dpValue*scale+0.5f );
    }


    /**
     * Java 毫秒转换为（时：分：秒）方法
     *
     * @param ms
     *         毫秒
     * @return 一个装了时 分 秒的数组
     */
    public static String[] timeFormatMore(int ms)
    {// 将毫秒数换算成x时x分x秒x毫秒
        String[] times = new String[3];
        int ss = 1000;
        int mi = ss*60;
        int hh = mi*60;
        long hour = ms/hh;
        long minute = ( ms-hour*hh )/mi;
        long second = ( ms-hour*hh-minute*mi )/ss;
        String strHour = hour<10 ? "0"+hour : ""+hour;
        String strMinute = minute<10 ? "0"+minute : ""+minute;
        String strSecond = second<10 ? "0"+second : ""+second;
        times[0] = strHour;
        times[1] = strMinute;
        times[2] = strSecond;
        return times;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        switch(event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                mDownY = event.getY();
                mDownX = event.getX();
                moveed = false;
                break;
            case MotionEvent.ACTION_MOVE:
                mMovX = event.getX();
                mMovY = event.getY();
                if(Math.abs(mMovY-mDownY)>Math.abs(mMovX-mDownX) && event.getX()>this.getMeasuredWidth()/2)
                {
                    moveed = true;
                    ( (LinearLayout)mVoiceShow.getParent() ).setVisibility(VISIBLE);
                    mIndex = ( ( mDownY-mMovY+currentVolume )*1.0f/voiceHeight );
                    adjustVoice(mIndex<0 ? 0 : mIndex>1 ? 1 : mIndex);
                }else if(mMovY<voiceHeight/2 && mDownY<voiceHeight/2)
                {
                    //                    System.out.println("......."+(event.getX()-mDownX));
                    //                    System.out.println(event.getX()-mDownX);
                    //                    player.seekTo(( (int)( player.getCurrentPosition()+( event.getX()-mDownX ) ) ));
                    //                    mDownX=event.getX();
                }
                //                System.out.println(event.getX()-mDownX);
                break;
            case MotionEvent.ACTION_UP:
                currentVolume = mIndex>1 ? 1*voiceHeight : mIndex<0 ? 0 : (int)( mIndex*voiceHeight );
                ( (LinearLayout)mVoiceShow.getParent() ).setVisibility(GONE);
                if(!moveed && ( mSfvLayoutParams.height == -1 || runstate == VOID_CONTINUE || runstate == VOID_PLAYING ))
                {
                    if(!toolShow)
                    {
                        showTools();
                    }else
                    {
                        hideTools();
                    }
                }else if(runstate == VOID_STOP || runstate == VOID_PAUSE)
                {
                    if(!toolShow)
                    {
                        showTools();
                    }
                }

        }
        return super.onTouchEvent(event);
    }

    private void initAnimation()
    {
        mTa = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 0,
                Animation.RELATIVE_TO_SELF, 1);
        mTad = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 1,
                Animation.RELATIVE_TO_SELF, 0);
        mTa.setDuration(500);
        mTa.setFillAfter(true);
        mTad.setDuration(500);
        mTad.setFillAfter(true);
        mTad.setAnimationListener(this);
        mTa.setAnimationListener(this);

    }

    public void hideTools()
    {
        if(toolShow)
        {
            handler.removeCallbacks(mAutoHideTolls);
            mTa.cancel();
            toolShow = false;
            mTools.startAnimation(mTa);
            btn_play.setClickable(false);
            mSeekBar.setClickable(false);
        }
    }

    public void showTools()
    {
        if(!toolShow)
        {
            mTad.cancel();
            toolShow = true;
            mTools.startAnimation(mTad);
            if(runstate != VOID_STOP && runstate != VOID_PAUSE)
            {
                handler.postDelayed(mAutoHideTolls, AUTOHIDETOOL);
            }
        }
    }

    public void setVoidDisplayHeight(int videoHeight)
    {
        mSfvLayoutParams.height = videoHeight;
    }

    public void adjustVoice(float index)
    {
        mVoiceShow.setText(( (int)( index*100 ) )+"%");
        index = index*maxVolume;
        if(mAudioManager != null)
        {
            mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, (int)index, 0);
        }
    }

    private void autoHideTools()
    {
        if(( toolShow && ( runstate == VOID_PLAYING || runstate == VOID_CONTINUE ) && !fullscreen ) || toolShow && fullscreen)
        {
            handler.removeCallbacks(mAutoHideTolls);
            handler.postDelayed(mAutoHideTolls, AUTOHIDETOOL);
        }
    }

    @Override
    public void onAnimationStart(Animation animation)
    {
        if(!toolShow)
        {
            findViewById(R.id.narrow).setClickable(false);
            btn_play.setClickable(false);
            mSeekBar.setClickable(false);
        }else
        {
            mSeekBar.setVisibility(VISIBLE);
        }
    }

    @Override
    public void onAnimationEnd(Animation animation)
    {
        if(toolShow)
        {
            btn_play.setClickable(true);
            mSeekBar.setClickable(true);
            findViewById(R.id.narrow).setClickable(true);
        }else
        {
            btn_play.setClickable(false);
            mSeekBar.setVisibility(GONE);
        }
    }

    @Override
    public void onAnimationRepeat(Animation animation)
    {

    }
}
