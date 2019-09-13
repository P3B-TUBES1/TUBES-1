package com.example.tubes1.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tubes1.R;
import com.example.tubes1.Storage;

import org.w3c.dom.Text;

import java.util.LinkedList;
import java.util.List;

public class HistoryListViewAdapter extends BaseAdapter {
    private List<Double> history;
    private Activity activity;
    private TextView history_list_view_item;
    public HistoryListViewAdapter(Activity activity){
        this.activity = activity;
        history = new LinkedList<>();
    }
    @Override
    public int getCount(){
        return history.size();
    }
    @Override
    public Object getItem(int i){
        return history.get(i);
    }
    @Override
    public long getItemId(int i){
        return 0;
    }
    public void updateList(List<Double> list){
        this.history.clear();
        this.history.addAll(list);
        this.notifyDataSetChanged();
    }
    @Override
    public View getView(int i , View convertView, ViewGroup parent){
        convertView = LayoutInflater.from(this.activity).inflate(R.layout.history_list_view,parent,false);
        this.history_list_view_item = convertView.findViewById(R.id.list_view_history_item);
        this.history_list_view_item.setText(this.history.get(i).toString()+"");
        return convertView;
    }
}
