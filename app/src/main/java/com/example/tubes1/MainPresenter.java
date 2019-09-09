package com.example.tubes1;

import java.util.LinkedList;
import java.util.List;

public class MainPresenter {
    private List<NumberModel> numbers;
    private IMainActivity mView;
    public MainPresenter(IMainActivity activity){
        this.mView = activity;
        this.numbers = new LinkedList<NumberModel>();
    }
    public void addOperand(){

    }
    public void load(){

    }
    public void deleteList(int i){
        this.numbers.remove(i);
        this.mView.updateList(numbers);
    }
    public void getResult(){

    }
    public void clear(){

    }
    public void save(){

    }
}
