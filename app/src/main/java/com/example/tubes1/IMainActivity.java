package com.example.tubes1;

public interface IMainActivity {
    void updateList(); // presenter ke adapter
    void changePage(int page); // fragment ke MainActivity
    void showResults(); // dari presenter -> dialogFragment

}
