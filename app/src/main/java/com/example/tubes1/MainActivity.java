package com.example.tubes1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.app.DialogFragment;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.widget.Adapter;
import android.widget.ListView;

import com.example.tubes1.Adapter.NavListViewAdapter;
import com.example.tubes1.Adapter.NumberListAdapter;
import com.example.tubes1.Library.SaveStorage;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

public class MainActivity extends AppCompatActivity implements IMainActivity {
    private Toolbar toolbar;
    private MainPresenter presenter;
    private ListView navList;
    private NavListViewAdapter navListViewAdapter;
    private FragmentManager fragmentManager;
    private HomeFragment homeFragment;
    private AddFragment addFragment;
    private HistoryFragment historyFragment;
    private ListView lstNumber;
    private NumberListAdapter numberListAdapter;
    private DrawerLayout drawerLayout;
    private ResultDialogFragment res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.presenter = new MainPresenter(this);
        this.numberListAdapter = new NumberListAdapter(this, this.presenter);
        // navigation list
        this.navList = this.findViewById(R.id.nav_list_view);
        this.navListViewAdapter = new NavListViewAdapter(this);
        this.navList.setAdapter(this.navListViewAdapter);
        //fragment
        this.homeFragment = HomeFragment.newInstance();
        this.addFragment = AddFragment.newInstance();
        this.historyFragment = historyFragment.newInstance();
        this.fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = this.fragmentManager.beginTransaction();
        ft.add(R.id.fragment_container, this.homeFragment).commit();
        //toolbar
        this.toolbar = this.findViewById(R.id.action_bar);
        this.toolbar.setTitle("Calculator");
        this.setSupportActionBar(toolbar);
        this.drawerLayout = this.findViewById(R.id.drawer_layout);
        //number adapter
        this.lstNumber = this.findViewById(R.id.lst_number);
        this.presenter.load();
    }

    @Override
    protected void onStart(){
        super.onStart();
        this.presenter.clear();
        this.showResults();
        this.homeFragment.fetchData();
        showResults();
    }

    @Override
    public void updateList(List<NumberModel> list) {
        this.numberListAdapter.updateList(list);
    }

    @Override
    public void changePage(int page) {
        //Log.d("test", "test");
        this.drawerLayout.closeDrawers();
        FragmentTransaction ft = this.fragmentManager.beginTransaction();
        if (page == 1) {
            this.toolbar.setTitle("Calculator");
            if (this.homeFragment.isAdded()) {
                ft.show(this.homeFragment);
            } else {
                ft.add(R.id.fragment_container, this.homeFragment);

            }
            if (this.addFragment.isAdded()) {
                ft.hide(this.addFragment);
            }
            if (this.historyFragment.isAdded()) {
                ft.hide(this.historyFragment);
            }

        } else if (page == 2) {
            this.toolbar.setTitle("Add");
            if (this.addFragment.isAdded()) {
                ft.show(this.addFragment);
            } else {
                ft.add(R.id.fragment_container, this.addFragment).addToBackStack(null);
            }
            if (this.homeFragment.isAdded()) {
                ft.hide(this.homeFragment);
            }
            if (this.historyFragment.isAdded()) {
                ft.hide(this.historyFragment);
            }
        } else if (page == 3) {
            this.toolbar.setTitle("History");
            if (this.historyFragment.isAdded()) {
                ft.show(this.historyFragment);
                this.historyFragment.fetchData();
            } else {
                ft.add(R.id.fragment_container, this.historyFragment).addToBackStack(null);
            }
            if (this.homeFragment.isAdded()) {
                ft.hide(this.homeFragment);
            }
            if (this.addFragment.isAdded()) {
                ft.hide(this.addFragment);
            }
        }

        ft.commit();
    }


    @Override
    public void showResults() {
        double result = this.presenter.getResult();
        this.homeFragment.previewResult(result);
    }

    @Override
    public void addOperand(String operator, int number){
        this.presenter.addOperand(operator,number);
    }
    @Override
    public NumberListAdapter fetchAdapter(){
        return this.numberListAdapter;
    }
    @Override
    public void clearAll(){
        double n = this.presenter.getResult();
        Storage storage = new Storage();
        storage.writeFile(n,this,"historyList");
        this.presenter.clear();
    }

    @Override
    public void showDialog() {
        FragmentManager fm = getSupportFragmentManager();
        this.res = ResultDialogFragment.newInstance(( this.presenter.getResult()));
        res.show(fm,"dialog");
    }

    @Override
    public void hideDialog() {
        this.res.dismiss();
    }

    @Override
    public void saveState(List<NumberModel> list){
        SaveStorage storage = new SaveStorage();
        storage.writeFile(list,this);
    }
}
