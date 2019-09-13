package com.example.tubes1;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class ResultDialogFragment extends DialogFragment {
    private TextView tv_dialog;
    private Button btn_dialog;
    private IMainActivity ui;

    public static ResultDialogFragment newInstance(double result){
        ResultDialogFragment fragment = new ResultDialogFragment();
        Bundle args = new Bundle();
        args.putString("result", "The result is: "+result);
        fragment.setArguments(args);
        return fragment;
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
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dialog,container,false);
        tv_dialog = view.findViewById(R.id.tv_dialog);
        btn_dialog = view.findViewById(R.id.btn_dialog);
        tv_dialog.setText(getArguments().getString("result"));
        btn_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ui.hideDialog();
            }
        });
        return view;
    }
}
