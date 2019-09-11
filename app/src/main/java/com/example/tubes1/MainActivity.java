package com.example.tubes1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.example.tubes1.Adapter.NavListViewAdapter;
import com.example.tubes1.Adapter.NumberListAdapter;

import java.util.List;

public class MainActivity extends AppCompatActivity implements IMainActivity{
    private Toolbar toolbar;
    private MainPresenter presenter;
    private ListView navList;
    private NavListViewAdapter navListViewAdapter;
    private ListView lstNumber;
    private NumberListAdapter numberListAdapter;
    private FragmentManager fragmentManager;
    private HomeFragment homeFragment;
    private AddFragment addFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.presenter = new MainPresenter(this);
        this.navList = this.findViewById(R.id.nav_list_view);
        this.navListViewAdapter = new NavListViewAdapter(this);
        this.navList.setAdapter(this.navListViewAdapter);
        this.homeFragment = HomeFragment.newInstance();
        this.addFragment = AddFragment.newInstance();
        this.fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = this.fragmentManager.beginTransaction();
        ft.add(R.id.fragment_container,this.homeFragment).commit();
        this.lstNumber = this.findViewById(R.id.lst_number);
        this.toolbar = this.findViewById(R.id.action_bar);
        toolbar.setTitle("Calculator");
        this.setSupportActionBar(toolbar);
        this.numberListAdapter = new NumberListAdapter(this, this.presenter);
//        this.lstNumber.setAdapter(this.numberListAdapter);
//        this.presenter.load();

    }
    @Override
    public void updateList(List<NumberModel> list) {
        this.numberListAdapter.updateList(list);
    }

    @Override
    public void changePage(int page) {
        //Log.d("test", "test");

        FragmentTransaction ft = this.fragmentManager.beginTransaction();
        if (page == 1) {
            if (this.homeFragment.isAdded()) {
                ft.show(this.homeFragment);
            } else {
                ft.add(R.id.fragment_container, this.homeFragment);

            }
            if (this.addFragment.isAdded()) {
                ft.hide(this.addFragment);
            }
        } else if (page == 2) {
            if (this.addFragment.isAdded()) {
                ft.show(this.addFragment);
            } else {
                ft.add(R.id.fragment_container, this.addFragment).addToBackStack(null);
            }
            if (this.homeFragment.isAdded()) {
                ft.hide(this.homeFragment);
            }
        }
        ft.commit();

    }
    @Override
    public void showResults(){

    }
}
