package com.huawei.ott;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.huawei.ott.application.OttApplication;
import com.huawei.ott.net.NetInterface;
import com.huawei.ott.net.NetResult;
import com.huawei.ott.services.HeartBitService;
import com.huawei.ott.utills.DES3Utils;
import com.huawei.ott.utills.DeviceUtils;
import com.huawei.ott.utills.OttConstants;
import com.huawei.ott.utills.SPUtills;
import com.huawei.ott.utills.UIUtils;

import java.io.UnsupportedEncodingException;

/**
 * 登录界面
 */
public class LoginActivity extends AppCompatActivity
{


    private String username;
    private String password;
    private ProgressBar mLogin_progress;
    private Button mLoginbt;
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private TextView mTextView;
    private ImageButton mImageButton_search, mImageButton_back;
    private CheckBox mAutologin;
    private CheckBox mRemenber;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mTextView = (TextView)findViewById(R.id.head_text);
        mLoginbt = (Button)findViewById(R.id.email_sign_in_button);
        LinearLayout outer = (LinearLayout)findViewById(R.id.ll_outer);
        final InputMethodManager imm = (InputMethodManager)getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        outer.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                if(event.getAction() == MotionEvent.ACTION_DOWN)
                {
                    imm.hideSoftInputFromWindow(LoginActivity.this.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                }
                return false;
            }
        });
        mEmailView = (AutoCompleteTextView)findViewById(R.id.email);
        mPasswordView = (EditText)findViewById(R.id.password);
        mTextView.setText(UIUtils.getString(R.string.login));
        mTextView.setTextColor(getResources().getColor(R.color.color_gray));
        mImageButton_search = (ImageButton)findViewById(R.id.head_search_action);
        mLogin_progress = (ProgressBar)findViewById(R.id.login_progress);
        mImageButton_search.setVisibility(View.GONE);
        mImageButton_back = (ImageButton)findViewById(R.id.head_back_action);
        mImageButton_back.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                finish();
            }
        });

        mAutologin = (CheckBox)findViewById(R.id.cb_auto_login);
        mRemenber = (CheckBox)findViewById(R.id.cb_remember_me);
        mAutologin.setChecked((Boolean)SPUtills.get(UIUtils.getContext(), OttConstants.AUTOLOGIN, false));
        mRemenber.setChecked((Boolean)SPUtills.get(UIUtils.getContext(), OttConstants.remember, false));
        username = SPUtills.get(UIUtils.getContext(), OttConstants.username, "null").toString();
        if(!"null".equals(username))
        {
            mEmailView.setText(username);
        }
        //回显帐号密码
        if(mRemenber.isChecked())
        {
            try
            {
                String pwjm = SPUtills.get(UIUtils.getContext(), OttConstants.password, "默认值").toString();
                byte[] encrypt = pwjm.getBytes("gbk");
                password = new String(DES3Utils.decryptMode(encrypt));
                mPasswordView.setText(password);
            }catch(UnsupportedEncodingException e)
            {
                e.printStackTrace();
            }
        }else
        {
            mAutologin.setChecked(false);
        }

        //自动登录
        findViewById(R.id.ll_auto_login).setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(!TextUtils.isEmpty(mPasswordView.getText().toString()) && !TextUtils.isEmpty(mEmailView.getText().toString()))
                {
                    mAutologin.setChecked(!mAutologin.isChecked());
                    if(mAutologin.isChecked())
                    {
                        mRemenber.setChecked(true);
                    }
                    SPUtills.put(UIUtils.getContext(), OttConstants.AUTOLOGIN, mAutologin.isChecked());
                }else
                {
                    Toast.makeText(getApplicationContext(), OttConstants.UPNULL, Toast.LENGTH_SHORT).show();
                    mAutologin.setChecked(false);
                    mRemenber.setChecked(false);
                }
            }
        });
        //记住密码
        findViewById(R.id.ll_remember).setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                mRemenber.setChecked(!mRemenber.isChecked());
                if(!mRemenber.isChecked())
                {
                    mAutologin.setChecked(false);
                }else
                {
                    if(!TextUtils.isEmpty(mPasswordView.getText().toString()) && !TextUtils.isEmpty(mEmailView.getText().toString()))
                    {

                    }else
                    {
                        Toast.makeText(getApplicationContext(), OttConstants.UPNULL, Toast.LENGTH_SHORT).show();
                        mAutologin.setChecked(false);
                        mRemenber.setChecked(false);
                    }
                }
                //保存记住密码状态
                SPUtills.put(UIUtils.getContext(), OttConstants.remember, mRemenber.isChecked());
            }
        });
        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener()
        {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent)
            {
                if(id == R.id.login || id == EditorInfo.IME_NULL)
                {
                    return true;
                }
                return false;
            }
        });

        mLoginbt.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if(DeviceUtils.isNetworkConnected(LoginActivity.this))
                {
                    password = mPasswordView.getText().toString();
                    OttApplication.username = username = mEmailView.getText().toString();
                    if(!TextUtils.isEmpty(password) && !TextUtils.isEmpty(username))
                    {
                        if(mRemenber.isChecked())
                        {
                            //保存记住密码状态
                            SPUtills.put(UIUtils.getContext(), OttConstants.remember, true);
                            //是否保存密码
                            try
                            {
                                byte[] encrypt = DES3Utils.encryptMode(password.getBytes());
                                SPUtills.put(UIUtils.getContext(), OttConstants.password, new String(encrypt, "gbk"));
                            }catch(Exception e)
                            {
                                e.printStackTrace();
                            }
                        }
                        SPUtills.put(UIUtils.getContext(), OttConstants.username, username);
                        login();
                    }else
                    {
                        Toast.makeText(LoginActivity.this, OttConstants.UPNULL, Toast.LENGTH_SHORT).show();
                    }
                }else
                {
                    Toast.makeText(getApplicationContext(), OttConstants.NONET, Toast.LENGTH_SHORT).show();
                }
            }
        });
        //是否自动登录
        if(mAutologin.isChecked())
        {
            login();
        }

    }

    /**
     * 发起登录
     */
    private void login()
    {
        mLogin_progress.setVisibility(View.VISIBLE);
        NetInterface.getInstance().login(new NetResult()
        {
            @Override
            public void onFailure()
            {
                loginError();
            }

            @Override
            public <T> void onSucceed(T modle)
            {
                Intent service = new Intent(LoginActivity.this, HeartBitService.class);
                startService(service);
                SPUtills.put(UIUtils.getContext(), OttConstants.SWITCHPROFILECONFIG, modle.toString());
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                mLogin_progress.setVisibility(View.GONE);
                mLoginbt.setText(UIUtils.getString(R.string.login));
                finish();
            }
        }, password);

    }

    /**
     * 登录错误
     */
    public void loginError()
    {
        //        SPUtills.put(UIUtils.getContext(), OttConstants.remember, false);//不记住密码
        SPUtills.put(UIUtils.getContext(), OttConstants.AUTOLOGIN, false);//不自动登录
        mLogin_progress.setVisibility(View.GONE);//去掉加载状态
        mLoginbt.setText(UIUtils.getString(R.string.login));
        //        mRemenber.setChecked(false);
        mAutologin.setChecked(false);
    }
}

