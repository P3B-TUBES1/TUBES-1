package com.example.tubes1;

import android.content.Context;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;

public class AddFragment extends Fragment {
    private IMainActivity ui;
    private Spinner spinner;
    private Button btn_submit;
    private EditText et_number;

    public static AddFragment newInstance(){
        return new AddFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance){
        View view = inflater.inflate(R.layout.fragment_add,container,false);
        this.spinner = view.findViewById(R.id.spinner_add);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity().getApplicationContext(), R.array.operands, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        this.et_number = view.findViewById(R.id.et_operand);
        this.btn_submit = view.findViewById(R.id.btn_submit);
        this.btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ui.addOperand(spinner.getSelectedItem().toString(),Integer.parseInt(et_number.getText().toString()));
                ui.showResults();
                ui.changePage(1);
            }
        });
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
}
