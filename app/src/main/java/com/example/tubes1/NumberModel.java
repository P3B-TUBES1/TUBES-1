package com.example.tubes1;

public class NumberModel {
    private int digit;
    private String operator;

    public NumberModel(int digit,String operator){
        this.digit = digit;
        this.operator = operator;
    }
    public int getOperand(){
        return this.digit;
    }
    public String getOperator(){
        return this.operator;
    }
}
