package com.mossle.lemonandroid.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mossle.lemonandroid.BaseFragment;
import com.mossle.lemonandroid.R;
import com.mossle.lemonandroid.util.L;

/**
 * Created by hello on 2015/12/14 20:17
 */
public class HomeFragment extends BaseFragment {
    public static Fragment getInstance(String title){
        HomeFragment homeFragment = new HomeFragment();
        Bundle bundle = new Bundle();
        bundle.putString("title", title);
        homeFragment.setArguments(bundle);
        return homeFragment;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    TextView mTextView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home,container,false);

        L.v("HomeFragment onCreateView");
        mTextView = (TextView) view.findViewById(R.id.title);

        Bundle bundle = getArguments();
        if(bundle!=null){
            String title = bundle.getString("title","没有内容");
            mTextView.setText(title);
        }
        return view;
    }

}
