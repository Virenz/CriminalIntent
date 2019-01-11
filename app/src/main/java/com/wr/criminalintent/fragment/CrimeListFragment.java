package com.wr.criminalintent.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wr.criminalintent.activity.CrimeActivity;
import com.wr.criminalintent.util.Crime;
import com.wr.criminalintent.util.CrimeLab;
import com.wr.criminalintent.R;
import com.wr.criminalintent.adapter.CrimeAdapter;

import java.util.List;

public class CrimeListFragment extends Fragment {

    private RecyclerView mCrimeRecyclerView;
    private CrimeAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_crime_list, container, false);
        mCrimeRecyclerView = (RecyclerView) view
                .findViewById(R.id.crime_recycler_view);
        mCrimeRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateUI();
        return view;
    }

    private void updateUI() {
        CrimeLab crimeLab = CrimeLab.get(getActivity());
        List<Crime> crimes = crimeLab.getCrimes();

        if (mAdapter == null) {
            mAdapter = new CrimeAdapter(getContext(), crimes);
            mCrimeRecyclerView.setAdapter(mAdapter);
            mAdapter.setItemClickListener(new CrimeAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(int position) {
                    Intent intent = CrimeActivity.newIntent(getActivity(), CrimeLab.get(getActivity()).getCrimes().get(position).getId());
                    startActivity(intent);
                }
            });
        } else {
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }
}
