package com.example.tubes1;

import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

public class AddFragment extends Fragment {
    private EditText operand;
    private Button submit_btn;
    public static AddFragment newInstance(){
        return new AddFragment();
    }
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance()){
//
//    }
}
