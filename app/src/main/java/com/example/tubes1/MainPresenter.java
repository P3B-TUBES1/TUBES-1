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

    public void addOperand(String operator, int number){
        this.numbers.add(new NumberModel(number,operator));
        this.mView.updateList(this.numbers);
    }

    public void load(){

        this.numbers.add(new NumberModel(5,"+"));
        this.numbers.add(new NumberModel(3,"-"));
        this.mView.updateList(this.numbers);
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
