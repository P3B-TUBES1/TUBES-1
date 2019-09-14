package com.example.tubes1;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.tubes1.Adapter.NumberListAdapter;
import com.example.tubes1.Library.SaveStorage;
import com.example.tubes1.Library.SwipeDismissListViewTouchListener;

import java.util.LinkedList;
import java.util.List;

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
            this.ui.showDialog();
        }else if(view.getId()==btnClear.getId()){
            this.tvResult.setText("0");
            this.ui.clearAll();
        }else if(view.getId()==btnSave.getId()){
            this.ui.saveState(numberListAdapter.getLst_number());
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

    public void fetchData(){
        SaveStorage storage = new SaveStorage();
        if(storage.checkFile(getContext())) {
            String n = storage.readFile(getContext());
            String[] arr = n.split("/n");
            Log.d("Title", n);
            for (int i = 0; i < arr.length; i++) {
                String temp = arr[i];
                String operator = temp.substring(0, 1);
                double operand = Double.parseDouble(temp.substring(1));
                ui.addOperand(operator, (int) operand);
                Log.d("Title", operand + operator);
            }
        }
    }
}
