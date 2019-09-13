package com.example.tubes1;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.tubes1.Adapter.NumberListAdapter;
import com.example.tubes1.Library.SwipeDismissListViewTouchListener;

public class HomeFragment extends Fragment implements View.OnClickListener{
    private Button btnAdd;
    private Button btnRes;
    private Button btnClear;
    private Button btnSave;
    private IMainActivity ui;
    private ListView lstNumber;
    private TextView tvResult;
    protected NumberListAdapter numberListAdapter;
    public static HomeFragment newInstance(){

        return new HomeFragment();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance){
        View view = inflater.inflate(R.layout.fragment_home,container,false);
        this.btnAdd = view.findViewById(R.id.btn_add);
        this.btnRes = view.findViewById(R.id.btn_result);
        this.btnClear = view.findViewById(R.id.btn_clear);
        this.btnSave = view.findViewById(R.id.btn_save);
        this.tvResult = view.findViewById(R.id.tv_result);
        this.btnAdd.setOnClickListener(this);
        this.btnRes.setOnClickListener(this);
        this.btnClear.setOnClickListener(this);
        this.btnSave.setOnClickListener(this);
        this.lstNumber = view.findViewById(R.id.lst_number);
        this.numberListAdapter = ui.fetchAdapter();
        this.lstNumber.setAdapter(numberListAdapter);
        this.onSwipeRemove();

        return view;
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof IMainActivity){
            this.ui = (IMainActivity) context;
        }else{
            throw new ClassCastException(context.toString()+"must implement FragmentListener");
        }
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==btnAdd.getId()){
            this.ui.changePage(2);
        }else if(view.getId()==btnRes.getId()){

        }else if(view.getId()==btnClear.getId()){
            this.tvResult.setText("0");
            this.ui.clearAll();
        }else if(view.getId()==btnSave.getId()){

        }

    }

    public void previewResult(double result) {
        this.tvResult.setText(result + "");
    }
    public void onSwipeRemove(){
        SwipeDismissListViewTouchListener touchListener = new SwipeDismissListViewTouchListener(
                lstNumber,
                new SwipeDismissListViewTouchListener.DismissCallbacks() {
                    @Override
                    public boolean canDismiss(int position) {
                        return true;
                    }

                    @Override
                    public void onDismiss(ListView listView, int[] reverseSortedPositions) {
                        for(int position:reverseSortedPositions){
                            numberListAdapter.remove(position);

                        }
                    }
                }
        );
        lstNumber.setOnTouchListener(touchListener);
    }
}
