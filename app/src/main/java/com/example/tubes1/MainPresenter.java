package com.example.tubes1;

import java.util.Iterator;
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
        this.numbers.add(new NumberModel(4,"*"));
        this.numbers.add(new NumberModel(2,"/"));
        this.mView.updateList(this.numbers);
    }
    public void deleteList(int i){
        this.numbers.remove(i);
        this.mView.updateList(numbers);
        mView.showResults();
    }
    public double getResult(){
        double result = 0;
        for(int i=0;i<numbers.size();i++){
            if(numbers.get(i).getOperator().equals("+")){
                result += numbers.get(i).getOperand();
            }
            else if(numbers.get(i).getOperator().equals("-")){
                result -= numbers.get(i).getOperand();
            }
            else if(numbers.get(i).getOperator().equals("*")){
                result *= numbers.get(i).getOperand();
            }
            else if(numbers.get(i).getOperator().equals("/")){
                result /= numbers.get(i).getOperand();
            }
        }
        return result;
    }
    public void clear(){
        this.numbers.clear();
        this.mView.updateList(numbers);
        this.mView.showResults();
    }
    public void save(){

    }
}
