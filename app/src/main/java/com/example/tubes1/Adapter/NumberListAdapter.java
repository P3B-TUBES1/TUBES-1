package com.example.tubes1.Adapter;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tubes1.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class NumberListAdapter extends BaseAdapter {
    private Activity activity;
    private List<NumberModel> lst_number;
    private MainPresenter presenter;

    public NumberListAdapter(Activity activity,MainPresenter presenter){
        this.activity = activity;
        this.lst_number = new LinkedList<NumberModel>();
        this.presenter = presenter;
    }
    @Override
    public int getCount(){
        return lst_number.size();
    }
    @Override
    public Object getItem(int i){
        return lst_number.get(i);
    }
    @Override
    public long getItemId(int i){
        return 0;
    }
    public void updateList(List<NumberModel> list){
        this.lst_number.clear();
        this.lst_number.addAll(list);
        this.notifyDataSetChanged();
    }
    public void remove(int i){
        Log.d("testtt","its called");
        this.presenter.deleteList(i);
        this.notifyDataSetChanged();
    }
    @Override
    public View getView(int i, View convertView, ViewGroup parent){
        ViewHolder viewHolder;
        if(convertView == null) {
            convertView = LayoutInflater.from(this.activity).inflate(R.layout.number_list_view, parent, false);
             viewHolder = new ViewHolder(convertView,this.presenter);
            convertView.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder)convertView.getTag();
        }
        viewHolder.setView((NumberModel)this.getItem(i),i);

        return convertView;
    }
    private class ViewHolder implements View.OnClickListener{
        private TextView operatorView;
        private TextView operandView;
        private ImageView deleteImage;
        private MainPresenter presenter;
        private int position;
        public ViewHolder(View view,MainPresenter presenter){
            this.operatorView = view.findViewById(R.id.list_view_operator);
            this.deleteImage = view.findViewById(R.id.trash_image);
            this.operandView = view.findViewById(R.id.list_view_operand);
            this.deleteImage.setOnClickListener(this);
            this.presenter = presenter;
        }
        @Override
        public void onClick(View v){
            this.presenter.deleteList(position);
        }
        public void setView( NumberModel model,int position){
            this.position = position;
            this.operatorView.setText(model.getOperator());
            this.operandView.setText(model.getOperand()+"");
        }
    }
}

