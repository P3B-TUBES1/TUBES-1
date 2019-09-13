package com.example.tubes1;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.tubes1.Adapter.NavListViewAdapter;

public class NavFragment extends Fragment implements AdapterView.OnItemClickListener {
    private ListView navListView;
    private IMainActivity activity;
    private NavListViewAdapter nla;
    public NavFragment(){
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance){
        View view = inflater.inflate(R.layout.nav_fragment,container,false);
        this.navListView = view.findViewById(R.id.nav_list_view);
        this.navListView.setOnItemClickListener(this);
        this.activity = (IMainActivity)getContext();
        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> adapter, View view, int position, long id){
        String text = adapter.getItemAtPosition(position).toString();
        Log.d("textView",text);
        if(text.equalsIgnoreCase("home")){
            this.activity.changePage(1);
        }
        else if(text.equalsIgnoreCase("history")){
            this.activity.changePage(3);
        }
    }

}
