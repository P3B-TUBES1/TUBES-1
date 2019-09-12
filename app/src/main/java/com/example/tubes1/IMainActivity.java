package com.example.tubes1;

import android.widget.ListView;

import java.util.List;

public interface IMainActivity {
    void updateList(List<NumberModel> list); // presenter ke adapter
    void changePage(int page); // fragment ke MainActivity
    void showResults(); // dari presenter -> dialogFragment
    void fetchLstNumber(ListView lstNumber);
    void addOperand(String operator, int number);
}
