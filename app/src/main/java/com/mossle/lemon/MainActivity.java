package com.mossle.lemon;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioGroup;

import com.mossle.lemon.contact.fragment.ContactsFragment;
import com.mossle.lemon.message.fragment.SessionsFragment;
import com.mossle.lemon.profile.ProfileFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {

    private List<Fragment> mFragments;

    private RadioGroup mRadioGroup;

    private int mCurrentFragmentIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        setupContainerRadioGroup();
        setupFragments();
    }

    private void setupContainerRadioGroup() {
        mRadioGroup = (RadioGroup) findViewById(R.id.containerRadioGroup);
        mRadioGroup.setOnCheckedChangeListener(this);
        mRadioGroup.check(mRadioGroup.getChildAt(mCurrentFragmentIndex).getId());
    }

    private void setupFragments() {
        mFragments = new ArrayList<>();

        Fragment sessionsFragment = SessionsFragment.newInstance();
        Fragment contactsFragment = ContactsFragment.newInstance();
        Fragment profileFragment = ProfileFragment.newInstance();

        mFragments.add(sessionsFragment);
        mFragments.add(contactsFragment);
        mFragments.add(profileFragment);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.contentFragment, sessionsFragment)
                .add(R.id.contentFragment, contactsFragment)
                .add(R.id.contentFragment, profileFragment)
                .show(sessionsFragment)
                .hide(contactsFragment)
                .hide(profileFragment)
                .commit();
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {

        int index = getClickFragmentIndex(checkedId);

        if(index == mCurrentFragmentIndex) {
            return;
        }

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.hide(mFragments.get(mCurrentFragmentIndex));
        if(!mFragments.get(index).isAdded()){
            fragmentTransaction.add(R.id.contentFragment, mFragments.get(index));
        }
        fragmentTransaction.show(mFragments.get(index)).commit();
        mCurrentFragmentIndex = index;
    }

    private int getClickFragmentIndex(int checkedID) {
        if (checkedID == R.id.sessionsRadioButton) {
            return 0;
        }
        if (checkedID == R.id.contactsRadioButton) {
            return 1;
        }
        if (checkedID == R.id.profileRadioButton) {
            return 2;
        }
        return 0;
    }
}