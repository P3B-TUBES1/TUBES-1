package com.example.tubes1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

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
    private NumberListAdapter numberListAdapter;//test
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.presenter = new MainPresenter(this);
        this.navList = this.findViewById(R.id.nav_list_view);
        this.navListViewAdapter = new NavListViewAdapter(this);
        this.navList.setAdapter(this.navListViewAdapter);
        this.lstNumber = this.findViewById(R.id.lst_number);
        this.numberListAdapter= new NumberListAdapter(this,this.presenter);
        this.toolbar = this.findViewById(R.id.action_bar);
        toolbar.setTitle("Calculator");

        //zz
        //test;
    }
    @Override
    public void updateList(List<NumberModel> list){

    }
    @Override
    public void changePage(int page){
        Log.d("test","test");
    }
    @Override
    public void showResults(){

    }
}
