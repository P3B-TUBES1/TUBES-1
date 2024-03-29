package com.example.tubes1;

import android.widget.ListView;

import com.example.tubes1.Adapter.NumberListAdapter;

import java.util.List;

public interface IMainActivity {
    void updateList(List<NumberModel> list); // presenter ke adapter
    void changePage(int page); // fragment ke MainActivity
    void showResults(); // dari presenter -> dialogFragment
    void addOperand(String operator, int number);
    void clearAll();
    void showDialog();
    void hideDialog();
    void saveState(List<NumberModel> list);
    NumberListAdapter fetchAdapter();
}
