package com.wr.criminalintent.activity;

import android.support.v4.app.Fragment;

import com.wr.criminalintent.fragment.CrimeListFragment;

public class CrimeListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new CrimeListFragment();
    }
}
