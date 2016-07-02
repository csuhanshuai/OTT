package com.huawei.ott.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.huawei.ott.R;
import com.huawei.ott.net.NetResult;
import com.huawei.ott.net.VolleyManager;
import com.huawei.ott.utills.UIUtils;

import org.json.JSONObject;

import java.util.List;

/**
 * Created by Jonas on 2015/11/19.
 */
public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener
{

    private RelativeLayout rlcontent;
    //加载默认的状态
    protected static final int STATE_UNLOADED = 1;
    //加载的状态
    protected static final int STATE_LOADING = 2;
    //加载失败的状态
    protected static final int STATE_ERROR = 3;
    //加载空的状态
    protected static final int STATE_EMPTY = 4;
    //加载成功的状态
    protected static final int STATE_SUCCEED = 5;
    //转圈的view
    private View mLoadingView;
    //错误的view
    private View mErrorView;
    //空的view
    private View mEmptyView;
    /**
     * 成功的view
     */
    private View mSucceedView;
    /**
     * 默认的状态
     */
    protected int mState = STATE_UNLOADED;
    protected String fmName;
    private RelativeLayout rl_head_content;
    private boolean mSucceedViewFirst;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_fm);
        rlcontent = (RelativeLayout)findViewById(R.id.rl_content);
        rl_head_content = (RelativeLayout)findViewById(R.id.rl_temp_content);

        LayoutInflater inflater = LayoutInflater.from(this);
        addHeadContent(inflater, rl_head_content);
        findViewById(R.id.head_back_action).setOnClickListener(this);
        findViewById(R.id.head_search_action).setOnClickListener(this);

        String title = setHeadTitle();
        if(null != title)
        {
            ( (TextView)findViewById(R.id.head_text) ).setText(title);
        }

        mSucceedView = setbeforeSucceedContent(inflater);
        if(null != mSucceedView)
        {
            mSucceedViewFirst = true;
            rlcontent.addView(mSucceedView,
                    new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT));
        }
        init();
        findViewById(R.id.rl_head).setVisibility(!setHeadIsVisibility() ? View.GONE : View.VISIBLE);
    }

    /**
     * 隐藏 head 复写该方法
     */
    public boolean setHeadIsVisibility()
    {
        return true;
    }

    /**
     * 设置 head的title
     *
     * @return
     */
    protected abstract String setHeadTitle();


    /**
     * 在head下 添加 自定义布局
     *
     * @param inflater
     * @param rl_head_content
     */
    protected void addHeadContent(LayoutInflater inflater, RelativeLayout rl_head_content)
    {
    }


    private void init()
    {
        mLoadingView = createLoadingView();
        if(null != mLoadingView)
        {
            rlcontent.addView(mLoadingView,
                    new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT));
        }

        mErrorView = createErrorView();
        if(null != mErrorView)
        {
            rlcontent.addView(mErrorView,
                    new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT));
        }

        mEmptyView = createEmptyView();
        if(null != mEmptyView)
        {
            rlcontent.addView(mEmptyView,
                    new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT));
        }
        getNetData();
    }

    protected void showSafePagerView(final int state)
    {
        mState = state;
        UIUtils.runInMainThread(new Runnable()
        {
            @Override
            public void run()
            {
                showPagerView(state);
            }
        });
    }

    /**
     * 加载数据前 调用showPagerView(STATE_LOADING);
     * 当加载完数据之后 调用以显示结果界面 空 错 ok
     */
    protected void showPagerView(int mState)
    {
        this.mState = mState;

        if(null != mLoadingView)
        {
            mLoadingView.setVisibility(mState == STATE_UNLOADED || mState == STATE_LOADING ? View.VISIBLE : View.INVISIBLE);
        }
        if(null != mErrorView)
        {
            mErrorView.setVisibility(mState == STATE_ERROR ? View.VISIBLE : View.INVISIBLE);
        }
        if(null != mEmptyView)
        {
            mEmptyView.setVisibility(mState == STATE_EMPTY ? View.VISIBLE : View.INVISIBLE);
        }
        if(mState == STATE_SUCCEED && mSucceedView == null)
        {
            //先加载数据 后加载布局
            mSucceedView = createAfterNetScceed();
            rlcontent.addView(mSucceedView,
                    new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT));
        }
        //        if(null != mSucceedView) {
        //            mSucceedView.setVisibility(mState == STATE_SUCCEED ? View.VISIBLE : View.INVISIBLE);
        //        }
        if(null != mSucceedView && mState == STATE_LOADING && mSucceedViewFirst)
        {
            mSucceedView.setVisibility(View.VISIBLE);
        }
    }


    public void VolleyShow()
    {
        mState = STATE_LOADING;
        showSafePagerView(mState);
        VolleyManager.getInstance().postParamJsonObject(getRequestURL(), getParams(), this, new AcResult()
        {
            @Override
            public <T> void onSucceed(T modle)
            {
                showSafePagerView(mState = onNetSucceed(modle));
            }
        });
    }

    protected abstract JSONObject getParams();

    /**
     * 1,设置 请求 url地址 对象
     */
    protected abstract String getRequestURL();

    /**
     * 2,拿到数据之后 解析数据
     *
     * @param response
     * @return 加载状态 成功失败
     */
    protected abstract <T> int onNetSucceed(T response);


    /**
     * 3，拿到数据之后 加载界面
     *
     * @return
     */
    protected abstract View createAfterNetScceed();


    /**
     * 在获取网络数据前 加载 界面
     * 先加载 布局 在加载数据
     * 注意在 拿到网络数据后 再刷新数据 重写refreshDate()
     *
     * @param inflater
     * @return
     */
    protected View setbeforeSucceedContent(LayoutInflater inflater)
    {
        return null;
    }

    /**
     * 检查服务器返回的数据情况
     */
    public int checkNetDataResult(Object obj)
    {
        if(obj == null)
        {
            return mState = STATE_ERROR;
        }else if(obj instanceof List)
        {
            List list = (List)obj;
            if(list.size() == 0)
            {
                return mState = STATE_EMPTY;
            }
        }
        return mState = STATE_SUCCEED;
    }

    protected View createLoadingView()
    {
        return UIUtils.inflate(R.layout.loading_page_loading);
    }

    protected View createEmptyView()
    {
        return UIUtils.inflate(R.layout.loading_page_empty);
    }

    protected View createErrorView()
    {
        View view = UIUtils.inflate(R.layout.loading_page_error);
        Button retry = (Button)view.findViewById(R.id.page_bt);
        retry.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                mState = STATE_LOADING;
                VolleyShow();
            }
        });
        return view;
    }

    /**
     * 加载网络数据
     */
    public void getNetData()
    {
        mState = STATE_LOADING;
        VolleyShow();
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        //初始化 加载状态
        mState = STATE_UNLOADED;
    }

    @Override
    public void onClick(View v)
    {
        if(v.getId() == R.id.head_back_action)
        {
            finish();
        }else if(v.getId() == R.id.head_search_action)
        {
            searchAction();
        }
    }

    private void searchAction()
    {

    }

    public abstract class AcResult extends NetResult
    {

        @Override
        public void onFailure()
        {
            showSafePagerView(mState = STATE_ERROR);
        }
    }
}
