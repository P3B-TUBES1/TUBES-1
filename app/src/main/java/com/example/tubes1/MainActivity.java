package com.example.tubes1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import com.example.tubes1.Adapter.NavListViewAdapter;

public class MainActivity extends AppCompatActivity implements IMainActivity{
    private MainPresenter presenter;
    private ListView navList;
    private NavListViewAdapter navListViewAdapter;
    private ListView lstNumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.presenter = new MainPresenter(this);
        this.navList = this.findViewById(R.id.nav_list_view);
        this.navListViewAdapter = new NavListViewAdapter(this);
        this.navList.setAdapter(this.navListViewAdapter);
        this.lstNumber = this.findViewById(R.id.lst_number);
    }
    @Override
    public void updateList(){

    }
    @Override
    public void changePage(int page){
        Log.d("test","test");
    }
    @Override
    public void showResults(){

    }
}
