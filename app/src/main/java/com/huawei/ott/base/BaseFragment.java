package com.huawei.ott.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.huawei.ott.MainActivity;
import com.huawei.ott.R;
import com.huawei.ott.SearchActivity;
import com.huawei.ott.net.NetResult;
import com.huawei.ott.net.VolleyManager;
import com.huawei.ott.utills.LogUtils;
import com.huawei.ott.utills.UIUtils;

import org.json.JSONObject;

import java.util.List;

/**
 * Created by Jonas on 2015/11/19.
 */
public abstract class BaseFragment extends Fragment implements View.OnClickListener
{

    private android.widget.RelativeLayout rlcontent;
    protected View mView;
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

    private View mLoadingView;//转圈的view
    private View mErrorView;//错误的view
    private View mEmptyView;//空的view
    private View mSucceedView;//成功的view

    protected int mState = STATE_UNLOADED;//默认的状态
    private RelativeLayout rl_head_content;
    private boolean mSucceedViewFirst;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        mView = inflater.inflate(R.layout.base_fm, null);
        this.rlcontent = (RelativeLayout)mView.findViewById(R.id.rl_content);
        rl_head_content = (RelativeLayout)mView.findViewById(R.id.rl_temp_content);

        addHeadContent(inflater, rl_head_content);
        mView.findViewById(R.id.head_back_action).setOnClickListener(this);
        mView.findViewById(R.id.head_search_action).setOnClickListener(this);
        String title = setHeadTitle();
        if(null != title)
        {
            ( (TextView)mView.findViewById(R.id.head_text) ).setText(title);
        }

        mSucceedView = setbeforeSucceedContent(inflater);
        if(null != mSucceedView)
        {
            mSucceedViewFirst = true;
            rlcontent.addView(mSucceedView,
                    new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                            RelativeLayout.LayoutParams.MATCH_PARENT));
        }
        init();

        mView.findViewById(R.id.rl_head).setVisibility(!setHeadIsVisibility() ? View.GONE : View.VISIBLE);
        return mView;
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
        showSafePagerView(mState);
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
            mLoadingView.setVisibility(mState == STATE_UNLOADED
                    || mState == STATE_LOADING ? View.VISIBLE : View.INVISIBLE);
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
        if(null != mSucceedView)
        {
            mSucceedView.setVisibility(mState == STATE_SUCCEED ? View.VISIBLE : View.INVISIBLE);
        }
        if(null != mSucceedView && mState == STATE_LOADING && mSucceedViewFirst)
        {
            mSucceedView.setVisibility(View.VISIBLE);
        }
    }

    protected void VolleyShow()
    {
        mState = STATE_LOADING;
        showSafePagerView(mState);

        JSONObject params = getparams();
        VolleyManager.getInstance().postParamJsonObject(getRequestURL(), params, this, new FmResult()
        {
            @Override
            public <T> void onSucceed(T modle)
            {
                showSafePagerView(onNetSucceed(modle));
            }

            @Override
            public void onFailure()
            {
                super.onFailure();
            }
        });
    }

    protected abstract JSONObject getparams();

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
     * @param isVisibleToUser
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser)
    {
        if(isVisibleToUser && mState == STATE_UNLOADED)
        {
            mState = STATE_LOADING;
            VolleyShow();
        }
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
            ( (MainActivity)getActivity() ).mViewPager.setCurrentItem(1);
        }else if(v.getId() == R.id.head_search_action)
        {
            searchAction();
        }
    }

    private void searchAction()
    {
        Intent intent = new Intent(UIUtils.getContext(), SearchActivity.class);
        startActivity(intent);
    }

    public abstract class FmResult extends NetResult
    {

        @Override
        public void onFailure()
        {
            LogUtils.e("获取网络数据失败。。");
            showSafePagerView(mState = STATE_ERROR);
        }
    }
}
