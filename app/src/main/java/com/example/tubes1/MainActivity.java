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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.presenter = new MainPresenter(this);
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
        //number adapter
        this.numberListAdapter = new NumberListAdapter(this, this.presenter);
        this.presenter.load();


    }
    @Override
    protected void onStart(){
        super.onStart();
        this.showResults();

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
            if (this.historyFragment.isAdded()) {
                ft.show(this.historyFragment);
            } else {
                ft.add(R.id.fragment_container, this.historyFragment).addToBackStack(null);
            }
            if (this.homeFragment.isAdded()) {
                ft.hide(this.homeFragment);
            }
            if (this.addFragment.isAdded()) {
                ft.hide(this.addFragment);
            }
            ft.commit();

        }
    }

    @Override
    public void fetchLstNumber(ListView lstNumber) {
        this.lstNumber = lstNumber;
        this.lstNumber.setAdapter(this.numberListAdapter);
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


}
