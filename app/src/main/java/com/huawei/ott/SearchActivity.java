package com.huawei.ott;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.huawei.ott.application.OttApplication;
import com.huawei.ott.base.BaseListAdapter;
import com.huawei.ott.holder.ViewHolderHelper;
import com.huawei.ott.model.AssociatedKeywords;
import com.huawei.ott.model.HotKey;
import com.huawei.ott.model.RecommendVod;
import com.huawei.ott.net.NetResult;
import com.huawei.ott.net.VolleyManager;
import com.huawei.ott.utills.LogUtils;
import com.huawei.ott.utills.OttConstants;
import com.huawei.ott.utills.SPUtills;
import com.huawei.ott.utills.UIUtils;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * <搜索页面>
 * 启动时，从本地加载历史搜索关键字列表，从服务器获取热搜词列表
 * 输入搜索关键字时，实时获取关键字联想补全选项
 * 提交搜索关键字后，从服务器获取与关键字相关的热搜词列表，并加载视频搜索结果列表
 * 如果视频所艘结果列表不为空，那么点击指定一项结果可以进入视频播放页面
 */
public class SearchActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_cancel, btn_search;
    private ImageView keyword_delete_button;
    private RadioGroup rg_search;
    private ListView result_list;
    private RelativeLayout rl_search_pre;
    private GridView mGridView_search_record, mGridView_hot_search;
    private AutoCompleteTextView auto_AssKey;
    private HotKey hotKey;
    private AssociatedKeywords assKey;
    private List<String> mHot_key = new ArrayList<>();
    private List<String> mAss_key = new ArrayList<>();
    private List<String>key_noBlank=new ArrayList();
    private List<RecommendVod.ContentlistEntity> mVodlist;
    private ArrayAdapter<String> mAdapter;
    private List<String> keyRecord;
    private BaseListAdapter mHot_key_ada;
    private BaseListAdapter<String> mRecordAdapter;
    private BaseListAdapter<RecommendVod.ContentlistEntity> mVodAdapter;
    private String mSearch_recent;
    private Boolean hotIsClicked = false;
    private Boolean recordIsClicked = false;
    private FrameLayout fl_autokeyword;
    private ListView lv_autokeyword;
    private TextView empty;

    /**
     * <页面创建>
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        getHotKeywords();
        mSearch_recent = SPUtills.get(UIUtils.getContext(), OttApplication.username + OttConstants.SEARCHRECENT, "").toString();
        initView();
    }

    /**
     * <页面销毁，保存搜索关键字历史>
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Collections.reverse(keyRecord);
        if (keyRecord.size() != 0) {
            mSearch_recent = keyRecord.toString().substring(1, keyRecord.toString().length() - 1);
        } else {
            mSearch_recent = "";
        }
        SPUtills.put(UIUtils.getContext(), OttApplication.username + OttConstants.SEARCHRECENT, mSearch_recent);
    }

    /**
     * <进入搜索页面时，获取热搜词，供用户选择>
     */
    private void getHotKeywords() {
        VolleyManager.getInstance().postParamJsonObject(OttConstants.GETHOTKEYWORDS, null, this, new NetResult() {
            /**
             * <访问网络失败>
             */
            @Override
            public void onFailure() {
                LogUtils.e("失败。。");
            }

            /**
             * <访问网络成功>
             */
            @Override
            public <T> void onSucceed(T modle) {
                hotKey = VolleyManager.getInstance().gson.fromJson(modle.toString(), HotKey.class);
                mHot_key = hotKey.getKeylist();
                mHot_key_ada.setData(mHot_key);
                mHot_key_ada.notifyDataSetChanged();
            }
        });

    }

    /**
     * <实时获得搜索的关键词自动推荐功能，可点击进行自动补全>
     */
    private void getAssociatedKeywords(String typing) {
        HashMap map = new HashMap();
        map.put(OttConstants.KEY, typing);
        JSONObject request = new JSONObject(map);
        VolleyManager.getInstance().postParamJsonObject(OttConstants.GETASSOCIATEDKEYWORDS, request, this, new NetResult() {
            /**
             * <访问网络失败>
             */
            @Override
            public void onFailure() {
                LogUtils.e("失败。。");
            }

            /**
             * <访问网络成功>
             */
            @TargetApi(Build.VERSION_CODES.HONEYCOMB)
            @Override
            public <T> void onSucceed(T modle) {
                assKey = VolleyManager.getInstance().gson.fromJson(modle.toString(), AssociatedKeywords.class);
                mAss_key = assKey.getKeylist();
                mAdapter.clear();
                if (mAss_key != null) {

                    for(String s:mAss_key){
                        if(s!=""){
                            key_noBlank.add(s);
                        }
                    }
                    lv_autokeyword.setVisibility(View.VISIBLE);
                    empty.setVisibility(View.GONE);
                    if (mAdapter != null) {
                        mAdapter.addAll(key_noBlank);
                    }
                }else{
                    empty.setVisibility(View.VISIBLE);
                    lv_autokeyword.setVisibility(View.GONE);
                }
                mAdapter.notifyDataSetChanged();
            }
        });
    }

    /**
     * <条件查询，提交输入框的内容并跳转搜索结果页>
     */
    private void searchWithCondition(final String keyword) {
        HashMap map = new HashMap();
        map.put(OttConstants.KEY, keyword);
        map.put(OttConstants.CONTENTTYPE, OttConstants.ALLCONTENTTYPE);
        map.put(OttConstants.TYPE, 1);
        map.put(OttConstants.COUNT, -1);
        map.put(OttConstants.OFFSET, 0);
        JSONObject request = new JSONObject(map);
        VolleyManager.getInstance().postParamJsonObject(OttConstants.SEARCH, request, this, new NetResult() {
            /**
             * <访问网络失败>
             */
            @Override
            public void onFailure() {
                LogUtils.e("失败。。");
            }

            /**
             * <访问网络成功>
             */
            @Override
            public <T> void onSucceed(T modle) {
                RecommendVod recommendVod = VolleyManager.getInstance().gson.fromJson(modle.toString(), RecommendVod.class);
                mVodlist = recommendVod.getContentlist();
                mVodAdapter.setData(mVodlist);
                mVodAdapter.notifyDataSetChanged();
            }
        });

    }

    /**
     * <布局填充>
     */
    private void initView() {
        keyword_delete_button = (ImageView) findViewById(R.id.keyword_delete_button);
        auto_AssKey = (AutoCompleteTextView) findViewById(R.id.Auto_AssKey);
        mGridView_search_record = (GridView) findViewById(R.id.gv_search_records);
        mGridView_hot_search = (GridView) findViewById(R.id.gv_search_hot);
        rl_search_pre = (RelativeLayout) findViewById(R.id.rl_search_pre);
        result_list = (ListView) findViewById(R.id.result_list);

        keyRecord = new ArrayList<String>();
        for (String s : mSearch_recent.split(",")) {
            keyRecord.add(s.trim());
        }
        while (keyRecord.size() > 12) {
            keyRecord.remove(0);
        }
        Collections.reverse(keyRecord);

        mAdapter = new ArrayAdapter<String>(UIUtils.getContext(), R.layout.item_autotextview, key_noBlank);

        mRecordAdapter = new BaseListAdapter<String>(keyRecord) {
            /**
             * <布局填充，搜索关键字历史记录>
             * @param position 位置
             * @param convertView view对象
             * @param parent 父布局
             * @return ViewHolderHelper
             */

            @Override
            protected ViewHolderHelper getAdapterHelper(int position, View convertView, ViewGroup parent) {
                return ViewHolderHelper.get(UIUtils.getContext(), convertView, parent, R.layout.item_gv_search_record, position);
            }

            /**
             * <布局填充>
             * @param helper ViewHolderHelper对象
             * @param item 搜索记录对象
             */
            @Override
            protected void convert(ViewHolderHelper helper, final String item) {
                helper.setText(R.id.tv_gv_search, item);
                helper.setClickListener(R.id.tv_gv_search, new View.OnClickListener() {
                    /**
                     * <控件点击事件>
                     */
                    @Override
                    public void onClick(View v) {
                        recordIsClicked = true;
                        result_list.setVisibility(View.VISIBLE);
                        rl_search_pre.setVisibility(View.GONE);
                        auto_AssKey.setText(item);
                        auto_AssKey.setSelection(item.length());
                        auto_AssKey.clearFocus();
                        searchWithCondition(item);
                    }
                });
            }
        };
        mGridView_search_record.setAdapter(mRecordAdapter);

        mHot_key_ada = new BaseListAdapter<String>(mHot_key) {
            /**
             * <布局填充，热搜词>
             * @param position 位置
             * @param convertView view对象
             * @param parent 父布局
             * @return ViewHolderHelper
             */
            @Override
            protected ViewHolderHelper getAdapterHelper(int position, View convertView, ViewGroup parent) {
                return ViewHolderHelper.get(UIUtils.getContext(), convertView, parent, R.layout.item_gv_search_record, position);
            }

            /**
             * <布局填充>
             * @param helper ViewHolderHelper对象
             * @param item 热键列表对象
             */
            @Override
            protected void convert(ViewHolderHelper helper, final String item) {
                helper.setText(R.id.tv_gv_search, item);
                helper.setClickListener(R.id.tv_gv_search, new View.OnClickListener() {
                    /**
                     * <控件点击事件>
                     */
                    @Override
                    public void onClick(View v) {
                        hotIsClicked = true;
                        result_list.setVisibility(View.VISIBLE);
                        rl_search_pre.setVisibility(View.GONE);
                        auto_AssKey.setText(item);
                        auto_AssKey.setSelection(item.length());
                        auto_AssKey.clearFocus();
                        searchWithCondition(item);
                        addRecord();
                    }
                });
            }
        };
        mGridView_hot_search.setAdapter(mHot_key_ada);

        mVodAdapter = new BaseListAdapter<RecommendVod.ContentlistEntity>(mVodlist) {
            /**
             * <布局填充，搜索结果每一项的数据>
             * @param position 位置
             * @param convertView view对象
             * @param parent 父布局
             * @return ViewHolderHelper
             */
            @Override
            protected ViewHolderHelper getAdapterHelper(int position, View convertView, ViewGroup parent) {
                return ViewHolderHelper.get(UIUtils.getContext(), convertView, parent, R.layout.item_search, position);
            }

            /**
             * <布局填充>
             * @param helper ViewHolderHelper对象
             * @param item 内容列表对象
             */
            @Override
            protected void convert(ViewHolderHelper helper, final RecommendVod.ContentlistEntity item) {
                String actors = "";
                String directors = "";
                helper.setText(R.id.title_textview, item.getName());
                if (!TextUtils.isEmpty(item.getAveragescore())) {
                    helper.setText(R.id.title_textview_content, item.getAveragescore());
                } else {
                    helper.setText(R.id.title_textview_content, "");
                }
                RecommendVod.ContentlistEntity.PictureEntity picture = item.getPicture();
                String url = null;
                if (picture != null) {
                    url = picture.getPoster();
                    if (url != null) {
                        url = url.substring(url.lastIndexOf(",") + 1);
                    }
                }
                helper.setImageFromUrl(R.id.cover_imageview, url);
                try {
                    for (RecommendVod.ContentlistEntity.CastsEntity ce : item.getCasts()) {
                        if (ce.getRoleType().equals("0")) {
                            actors += ce.getCastName() + " ";
                        }
                        if (ce.getRoleType().equals("1")) {
                            directors += ce.getCastName() + " ";
                        }
                    }
                } catch (Exception e) {
                    actors = OttConstants.UNKNOW;
                    directors = OttConstants.UNKNOW;
                }
                helper.setText(R.id.director_textview_content, directors);
                helper.setText(R.id.actor_textview_content, actors);

                if (TextUtils.isEmpty(item.getStarttime())) {
                    helper.setText(R.id.release_textview_content, OttConstants.UNKNOW);

                } else {
                    helper.setText(R.id.release_textview_content, item.getStarttime().substring(0, 4));
                }
                helper.setClickListener(R.id.watch_button, new View.OnClickListener() {
                    /**
                     * <控件点击事件，进入相关视频的播放页面>
                     */
                    @Override
                    public void onClick(View v) {
                        Intent starter = new Intent(UIUtils.getContext(), PlayDetailActivity.class);
                        RecommendVod.ContentlistEntity.CastEntity cast = item.getCast();
                        starter.putExtra(OttConstants.NAME, item.getName());
                        starter.putExtra(OttConstants.ID, item.getId());
                        if (cast != null) {
                            starter.putExtra(OttConstants.ACTOR, cast.getActor());
                            starter.putExtra(OttConstants.DIRECTOR, cast.getDirector());
                            starter.putExtra(OttConstants.ADAPTOR, cast.getAdaptor());
                        }
                        starter.putExtra(OttConstants.PLAYTYPE, 1);
                        startActivity(starter);
                    }
                });
            }
        };
        result_list.setAdapter(mVodAdapter);

        btn_cancel = (Button) findViewById(R.id.btn_cancel);
        btn_cancel.setOnClickListener(this);
        btn_search = (Button) findViewById(R.id.btn_search);
        btn_search.setOnClickListener(this);

        fl_autokeyword = (FrameLayout) findViewById(R.id.fl_autokeyword);
        lv_autokeyword = (ListView) findViewById(R.id.lv_autokeyword);
        empty = (TextView) findViewById(R.id.empty);

        auto_AssKey.setOnFocusChangeListener(new View.OnFocusChangeListener(){
            public void onFocusChange(View v, boolean hasFocus){
                if(hasFocus){//获得焦点
                    hotIsClicked=false;
                    recordIsClicked=false;
                }
            }
        });

        auto_AssKey.addTextChangedListener(new TextWatcher() {
            /**
             * <text内容改变前事件监听>
             */
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            /**
             * <text内容改变事件监听>
             */
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String etStr = auto_AssKey.getText().toString().trim();
                if (TextUtils.isEmpty(etStr)) {
                    keyword_delete_button.setVisibility(View.INVISIBLE);
                    btn_cancel.setVisibility(View.VISIBLE);
                    btn_search.setVisibility(View.GONE);
                    empty.setVisibility(View.GONE);
                    rl_search_pre.setVisibility(View.VISIBLE);

                    mAdapter.clear();
                    mAdapter.notifyDataSetChanged();
                    fl_autokeyword.setVisibility(View.GONE);
                    result_list.setVisibility(View.GONE);

                } else {
                    keyword_delete_button.setVisibility(View.VISIBLE);
                    if (hotIsClicked == false && recordIsClicked == false) {
                        btn_cancel.setVisibility(View.GONE);
                        btn_search.setVisibility(View.VISIBLE);
                        /**
                         * 显示自动提示出来的条目
                         */
                        getAssociatedKeywords(etStr);
                        fl_autokeyword.setVisibility(View.VISIBLE);
                        rl_search_pre.setVisibility(View.GONE);
                        result_list.setVisibility(View.GONE);
                        lv_autokeyword.setAdapter(mAdapter);

                        lv_autokeyword.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                final String item = key_noBlank.get(position);
                                auto_AssKey.setText(item);
                                auto_AssKey.setSelection(item.length());
                                auto_AssKey.clearFocus();
                                result_list.setVisibility(View.VISIBLE);
                                rl_search_pre.setVisibility(View.GONE);
                                searchWithCondition(item);
                                addRecord();
                                fl_autokeyword.setVisibility(View.GONE);
                                btn_cancel.setVisibility(View.VISIBLE);
                                btn_search.setVisibility(View.GONE);
                            }
                        });

                    }


                }
            }

            /**
             * <text内容改变后事件监听>
             */
            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        if (TextUtils.isEmpty(mSearch_recent)) {
            findViewById(R.id.search_record_rl).setVisibility(View.GONE);
        }
    }


    /**
     * <控件点击事件>
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_cancel:
                /**
                 * <关闭搜索页，返回原先的界面>
                 */
                finish();
                break;
            case R.id.btn_search:
                /**
                 * <判断输入的关键字是否已存在记录中>
                 */

                addRecord();
                result_list.setVisibility(View.VISIBLE);
                rl_search_pre.setVisibility(View.GONE);
                fl_autokeyword.setVisibility(View.GONE);

                searchWithCondition(auto_AssKey.getText().toString().trim());

                btn_cancel.setVisibility(View.VISIBLE);
                btn_search.setVisibility(View.GONE);

                break;
            case R.id.clear_record_btn:
                /**
                 * <清空搜索记录并实时更新界面>
                 */
                SPUtills.put(UIUtils.getContext(), OttApplication.username + OttConstants.SEARCHRECENT, "");
                keyRecord = new ArrayList<>();
                mRecordAdapter.setData(keyRecord);
                mRecordAdapter.notifyDataSetChanged();

                View search_record_rl = findViewById(R.id.search_record_rl);
                search_record_rl.setVisibility(View.GONE);


                break;
            case R.id.keyword_delete_button:
                auto_AssKey.setText("");

                break;
        }
    }

    private void addRecord() {
        if (keyRecord.contains(auto_AssKey.getText().toString().trim())) {
            keyRecord.remove(keyRecord.indexOf(auto_AssKey.getText().toString().trim()));
        }
        keyRecord.add(0, auto_AssKey.getText().toString().trim());
    }


}
