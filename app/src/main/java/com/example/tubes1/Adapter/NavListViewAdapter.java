package com.example.tubes1.Adapter;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.tubes1.*;

import androidx.appcompat.app.AppCompatActivity;

import java.util.LinkedList;
import java.util.List;



public class NavListViewAdapter extends BaseAdapter {
    private Activity activity;
    private List<String> menu;

    public NavListViewAdapter(Activity activity){
        this.activity= activity;
        this.menu= new LinkedList<String>();
        this.menu.add("Home");
        this.menu.add("History");
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
        TextView textView = convertView.findViewById(R.id.nav_list_view_text);
        String text = this.getItem(i).toString();
        textView.setText(text);
        return convertView;
    }

}
