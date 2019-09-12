package com.example.tubes1;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.tubes1.Adapter.NumberListAdapter;

public class HomeFragment extends Fragment implements View.OnClickListener{
    private Button btnAdd;
    private Button btnRes;
    private Button btnClear;
    private Button btnSave;
    private IMainActivity ui;
    private ListView lstNumber;
    private TextView tvResult;

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
        if(lstNumber == null){
            this.lstNumber = view.findViewById(R.id.lst_number);
            ui.fetchLstNumber(lstNumber);
        }
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

        }else if(view.getId()==btnSave.getId()){

        }

    }

    public void previewResult(double result) {
       this.tvResult.setText(result+"");
    }
}
