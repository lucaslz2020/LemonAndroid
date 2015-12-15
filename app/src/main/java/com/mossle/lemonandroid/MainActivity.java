package com.mossle.lemonandroid;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.mossle.lemonandroid.fragment.HomeFragment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {
    private ArrayList<Fragment> mFragmentArrayList;
    RadioGroup mRadioGroup;
    private int currentTabFragmentIndex=1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRadioGroup = (RadioGroup) findViewById(R.id.rg_tab);
        initTabs();
        mRadioGroup.setOnCheckedChangeListener(this);
        currentTabFragmentIndex = mFragmentArrayList.size() / 2;
        mRadioGroup.check(((RadioButton) (mRadioGroup.getChildAt(currentTabFragmentIndex))).getId());
    }
    public void initTabs(){
        mFragmentArrayList = new ArrayList<>(3);
        Fragment fragment01 =HomeFragment.getInstance("首页1111");
        Fragment fragment02 =HomeFragment.getInstance("首页2222");
        Fragment fragment03 =HomeFragment.getInstance("首页3");

        mFragmentArrayList.add(fragment01);
        mFragmentArrayList.add(fragment02);
        mFragmentArrayList.add(fragment03);
        getSupportFragmentManager().beginTransaction().add(R.id.tab_content, fragment01)
                .add(R.id.tab_content,fragment02)
                .add(R.id.tab_content, fragment03)
                .hide(fragment01)
                .show(fragment02)
                .hide(fragment03)
                .commit();

    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {

        int index = 1;

        if (checkedId == R.id.rbtn_tab_01) {
            index = 0;
        } else if (checkedId == R.id.rbtn_tab_02) {
            index = 1;
        } else if (checkedId == R.id.rbtn_tab_03) {
            index =2;
        }

        if(index==currentTabFragmentIndex){
            return;
        }

        FragmentTransaction trx = getSupportFragmentManager().beginTransaction();
        trx.hide(mFragmentArrayList.get(currentTabFragmentIndex));
        if(!mFragmentArrayList.get(index).isAdded()){
            trx.add(R.id.tab_content,mFragmentArrayList.get(index));
        }
        trx.show(mFragmentArrayList.get(index)).commit();
        currentTabFragmentIndex = index;
    }
}
