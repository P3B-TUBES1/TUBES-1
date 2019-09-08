package com.example.tubes1;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.LinkedList;
import java.util.List;

public class NavListViewAdapter extends BaseAdapter {
    private Activity activity;
    private List<String> menu;

    public NavListViewAdapter(Activity activity){
        this.activity=activity;
        this.menu= new LinkedList<String>();
    }
    @Override
    public int getCount(){
        return this.menu.size();
    }
    @Override
    public Object getItem(int i){
        return menu.get(i);
    }
    @Override
    public long getItemId(int i){
        return 0;
    }
    @Override
    public View getView(int i, View convertView, ViewGroup parent){
        convertView = LayoutInflater.from(this.activity).inflate(R.layout.nav_list_view,parent,false);
        return convertView;
    }
}
