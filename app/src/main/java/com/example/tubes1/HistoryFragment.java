package com.example.tubes1;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.tubes1.Adapter.HistoryListViewAdapter;

import java.util.LinkedList;
import java.util.List;

public class HistoryFragment extends Fragment {
    private ListView list_view_history;
    private HistoryListViewAdapter historyListViewAdapter;
    public static HistoryFragment newInstance(){
        return new HistoryFragment();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance){
        View view = inflater.inflate(R.layout.history_fragment,container,false);
        this.list_view_history = view.findViewById(R.id.list_view_history);
        this.historyListViewAdapter = new HistoryListViewAdapter((AppCompatActivity)getContext());
        this.list_view_history.setAdapter(this.historyListViewAdapter);
        fetchData();
        return view;
    }
    public void fetchData(){
        Storage storage = new Storage();
        String n = storage.readFile((AppCompatActivity)getContext(),"historyList");
        updateListView(n);
    }
    public void updateListView(String n){
        List<Double> doubleList = new LinkedList<>();
        String[] arr = n.split("\n");
        for(int i=1;i<arr.length;i++){
           double line  = Double.parseDouble(arr[i]);
           doubleList.add(line);
        }
        if(!doubleList.isEmpty()) {
            historyListViewAdapter.updateList(doubleList);
        }
    }
}
