package com.example.sasata299.carpqrapplication.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.sasata299.carpqrapplication.ClickEvent;
import com.example.sasata299.carpqrapplication.MessageEvent;
import com.example.sasata299.carpqrapplication.R;
import com.example.sasata299.carpqrapplication.model.Hoge;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by sasata299 on 2016/11/04.
 */

public class OtherFragment extends Fragment implements View.OnClickListener {

    @BindView(R.id.button)
    Button button;

    @BindView(R.id.textView)
    TextView textView;

    public OtherFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.other_fragment, container, false);

        ButterKnife.bind(this, view);

        button.setOnClickListener(this);

        return view;
    }

    public void onClick(View v) {
        Hoge.doAction();
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent event) {
        Log.d("event bus", "received!");
        textView.setText(event.message);
    }
}
