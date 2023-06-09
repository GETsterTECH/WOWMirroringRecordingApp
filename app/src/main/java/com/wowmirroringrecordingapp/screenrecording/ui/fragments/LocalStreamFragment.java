package com.wowmirroringrecordingapp.screenrecording.ui.fragments;

import static com.wowmirroringrecordingapp.screenrecording.ui.utils.MyUtils.DEBUG;
import static com.wowmirroringrecordingapp.screenrecording.ui.utils.MyUtils.isMyServiceRunning;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.wowmirroringrecordingapp.R;
import com.wowmirroringrecordingapp.activity.StartScreenMirroringRecording;
import com.wowmirroringrecordingapp.screenrecording.ui.services.ControllerService;
import com.wowmirroringrecordingapp.screenrecording.ui.services.recording.RecordingService;
import com.wowmirroringrecordingapp.screenrecording.ui.utils.MyUtils;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;


public class LocalStreamFragment extends Fragment {
    private static final String TAG = LocalStreamFragment.class.getSimpleName();

    private StartScreenMirroringRecording mActivity=null;
    String mUrl;
    private View mViewRoot;
    private ScrollView mScrollView;
    private TextView mTvLog;
    private EditText mEdUrl;
    private Button mBtnConnect;
    private boolean isTested = false;
    private String mLog;
    private StreamingReceiver mStreamReceiver = null;

    public LocalStreamFragment(){
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(MyUtils.KEY_STREAM_URL, mUrl);
        outState.putString(MyUtils.KEY_STREAM_LOG, mTvLog.getText().toString());
        outState.putBoolean(MyUtils.KEY_STREAM_IS_TESTED, isTested);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(savedInstanceState!=null){
            String tmpUrl = savedInstanceState.getString(MyUtils.KEY_STREAM_URL);
            String tmpLog = savedInstanceState.getString(MyUtils.KEY_STREAM_LOG);
            this.isTested = savedInstanceState.getBoolean(MyUtils.KEY_STREAM_IS_TESTED);
            if(isTested){
                mBtnConnect.setText("Connected");
                mBtnConnect.setEnabled(false);
                mEdUrl.setEnabled(false);
            }
            else{
                mBtnConnect.setText("Test");
                mBtnConnect.setEnabled(true);
                mEdUrl.setEnabled(true);
            }
            if(!TextUtils.isEmpty(tmpUrl))
            {
                mEdUrl.setText(tmpUrl);
                mUrl = tmpUrl;
            }
            if(!TextUtils.isEmpty(tmpLog))
            {
//                mTvLog.setText(tmpLog);
                mLog = tmpLog;
                appendLog(mLog);
            }

        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(mStreamReceiver ==null){
            registerSyncServiceReceiver();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mViewRoot = inflater.inflate(R.layout.fragment_local_stream, container, false);
        return mViewRoot;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        initViews();
    }

    @Override
    public void onResume() {
        super.onResume();
        if(mStreamReceiver ==null){
            registerSyncServiceReceiver();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if(mStreamReceiver!=null && getActivity()!=null){
            getActivity().unregisterReceiver(mStreamReceiver);
            mStreamReceiver = null;
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if(mStreamReceiver!=null && getActivity()!=null){
            getActivity().unregisterReceiver(mStreamReceiver);
            mStreamReceiver = null;
        }
    }

    private void initViews() {
        final TextInputLayout tilUrl = mViewRoot.findViewById(R.id.til_url);

        mBtnConnect = mViewRoot.findViewById(R.id.btn_connect);
        mBtnConnect.setEnabled(true);
        mEdUrl = mViewRoot.findViewById(R.id.ed_url);
        mTvLog = mViewRoot.findViewById(R.id.tvLog);
        mScrollView = mViewRoot.findViewById(R.id.scroll_log);
        mEdUrl.setText(MyUtils.SAMPLE_RMPT_URL);

        mBtnConnect.setOnClickListener(mConnectStreamServiceListener);

        mEdUrl.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                String tmp = s.toString();
                if (TextUtils.isEmpty(tmp)) {
                    tilUrl.setError("Please enter a Steaming URL!");
                    mBtnConnect.setEnabled(false);
                } else {
                    if(!MyUtils.isValidStreamUrlFormat(tmp)) {
                        tilUrl.setError("Wrong Url format (ex: rtmp://127.0.0.1/live/key)");
                        mBtnConnect.setEnabled(false);
                    }
                    else{
                        tilUrl.setError("");
                        mBtnConnect.setEnabled(true);
                        if(!tmp.equals(mUrl)){
                            isTested = false;
                            mBtnConnect.setText("Test");
                            mBtnConnect.setEnabled(true);
                            mUrl = mEdUrl.getText().toString();
                        }
                    }

                }
            }
        });

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public void setContext(StartScreenMirroringRecording StartScreenMirroringRecording) {
        this.mActivity = StartScreenMirroringRecording;
    }

    private void registerSyncServiceReceiver() {
        if(DEBUG) Log.i(TAG, "registerSyncServiceReceiver: registered");
        mStreamReceiver = new StreamingReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(MyUtils.ACTION_NOTIFY_FROM_STREAM_SERVICE);
        getActivity().registerReceiver(mStreamReceiver, intentFilter);

    }


    //Receiver
    private class StreamingReceiver extends BroadcastReceiver {
        private boolean isStarted = false;

        @Override
        public void onReceive(Context context, Intent intent) {

            String action = intent.getAction();
            if(DEBUG) Log.i(TAG, "onReceive: "+action);
            if(!TextUtils.isEmpty(action) &&
                    MyUtils.ACTION_NOTIFY_FROM_STREAM_SERVICE.equals(action)) {


            }
        }
    }

    private View.OnClickListener mConnectStreamServiceListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(mActivity ==null){
                MyUtils.showSnackBarNotification(mViewRoot, "Streaming is detached form application. Try later", Snackbar.LENGTH_LONG);
                Log.e(TAG, "onBtnConnect clicked", new RuntimeException("Activity is null") );
                return;
            }
            if(v.getId() != R.id.btn_connect)
                return;
            mActivity.mMode = MyUtils.MODE_STREAMING;
            mUrl = mEdUrl.getText().toString();
            if(!MyUtils.isValidStreamUrlFormat(mUrl)) {
                MyUtils.showSnackBarNotification(mViewRoot, "Wrong stream url format (ex: rtmp://127.192.123.1/live/stream)", Snackbar.LENGTH_INDEFINITE);
                mEdUrl.requestFocus();
            }
            else{
                if(isMyServiceRunning(getContext(), RecordingService.class))
                {
                    MyUtils.showSnackBarNotification(mViewRoot, "You are in RECORDING Mode. Please close Recording controller", Snackbar.LENGTH_INDEFINITE);
                    return;
                }


                if(isTested){
                    mActivity.mMode = MyUtils.MODE_STREAMING;



                    if(isMyServiceRunning(getContext(), ControllerService.class)){
                        MyUtils.showSnackBarNotification(mViewRoot,"Streaming service is running!", Snackbar.LENGTH_LONG);
                        mActivity.notifyUpdateStreamProfile();
//                        return;
                    }
                    else
                        mActivity.shouldStartControllerService();

                    mBtnConnect.setText("Connected");
                    mBtnConnect.setEnabled(false);
                    mEdUrl.setEnabled(false);
                    appendLog("Stream connected");
                }
                else{
                    mBtnConnect.setText("Testing");
                    appendLog("Test stream "+mUrl);

                }
            }
        }
    };

    private void appendLog(String msg) {
        mTvLog.append("\n"+msg);
        mScrollView.post(new Runnable() {
            @Override
            public void run() {
                mScrollView.fullScroll(View.FOCUS_DOWN);
            }
        });

    }



}
