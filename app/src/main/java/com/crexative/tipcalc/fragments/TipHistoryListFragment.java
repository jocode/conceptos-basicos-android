package com.crexative.tipcalc.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.crexative.tipcalc.R;
import com.crexative.tipcalc.activities.TipDetailActivity;
import com.crexative.tipcalc.adapters.OnItemClickListener;
import com.crexative.tipcalc.adapters.TipAdapter;
import com.crexative.tipcalc.models.TipRecord;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class TipHistoryListFragment extends Fragment implements TipHistoryListFragmentListener, OnItemClickListener {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private TipAdapter adapter;

    public TipHistoryListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tip_history_list, container, false);
        ButterKnife.bind(this, view);
        initAdapter();
        initRecyclerView();
        return view;
    }

    private void initAdapter() {
        if (adapter == null){
            adapter = new TipAdapter(getActivity().getApplicationContext(), this);
        }
    }

    private void initRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(TipRecord tipRecord) {
        Intent intent = new Intent(getActivity(), TipDetailActivity.class);
        intent.putExtra(TipDetailActivity.TIP_KEY, tipRecord.getTip());
        intent.putExtra(TipDetailActivity.BILL_TOTAL_KEY, tipRecord.getBill());
        intent.putExtra(TipDetailActivity.DATE_KEY, tipRecord.getDateFormat());
        startActivity(intent);
    }

    @Override
    public void addToList(TipRecord tipRecord) {
        adapter.add(tipRecord);
    }

    @Override
    public void clearList() {
        adapter.clear();
    }
}
