package com.example.tubes1;

import java.util.List;

public interface IMainActivity {
    void updateList(List<NumberModel> list); // presenter ke adapter
    void changePage(int page); // fragment ke MainActivity
    void showResults(); // dari presenter -> dialogFragment

}
