package com.example.sagar.sicklab;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sagar.database.GetDataDisease;
import com.example.sagar.database.GetDataDiseaseReported;

import java.util.ArrayList;

public class Fragment3 extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private static String LOG_TAG = "Fragment3";

    public Fragment3() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_fragment3, container, false);


        FloatingActionButton myFab = (FloatingActionButton) view.findViewById(R.id.floatingActionButton);
        myFab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                Intent intent = new Intent(getActivity(), MakeReports.class);

                startActivityForResult(intent, 1);
            }
        });

        RecyclerView rv = (RecyclerView) view.findViewById(R.id.my_recycler_view);
//        mLayoutManager = new LinearLayoutManager(getActivity());
//        mRecyclerView.setLayoutManager(mLayoutManager);

        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(layoutManager);

        mAdapter = new MyRecyclerViewAdapter2(getDataSet());
        rv.setAdapter(mAdapter);


        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        ((MyRecyclerViewAdapter2) mAdapter).setOnItemClickListener(new MyRecyclerViewAdapter2
                .MyClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                Log.i(LOG_TAG, " Clicked on Item " + position);
            }
        });
    }

    private ArrayList<DataObject> getDataSet() {
        ArrayList results = new ArrayList<DataObject>();


        GetDataDiseaseReported received = new GetDataDiseaseReported(getActivity());
        int count = received.getNoOfData();


        for (int i = 0; i < count; i++) {
            DataObject obj = new DataObject("1st Report Date:" + received.getData(i + 1, 4) + "    "
                    + "No of Reports:" + received.getData(i + 1, 3),
                    "12.2" + " km away from You",

                    received.getData(i + 1, 1)
            );
            results.add(i, obj);
        }


        return results;
    }
}
