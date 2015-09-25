package codeforamerica.sanjose311.fragments;

/**
 * Created by bharath.yarlaga on 8/23/2015.
 */
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import codeforamerica.sanjose311.Adapters.RequestsAdapter;
import codeforamerica.sanjose311.R;
import codeforamerica.sanjose311.dto.RequestsDTO;

public class RequestsFeed extends Fragment {
    int color;

    public RequestsFeed() {
    }

    @SuppressLint("ValidFragment")
    public RequestsFeed(int color) {
        this.color = color;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        RecyclerView mRecyclerView = (RecyclerView)inflater.inflate(R.layout.requests_layout, container,
                false);
        mRecyclerView.setHasFixedSize(true);

        RequestsAdapter adapter = new RequestsAdapter(new String[]{"string","qaesd","dsdsdsd","edwqreqwerq"});
        mRecyclerView.setAdapter(adapter);
        return mRecyclerView;
    }
}

