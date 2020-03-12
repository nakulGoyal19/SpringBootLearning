package com.mocking;

public class Calculator {

    CalculatorService service = null;

    public Calculator()
    {
        this.service=new CalculatorService();
    }

    public Calculator(CalculatorService service){
        this.service=service;
    }

    public int performSquareSum(int i, int j)
    {
        return service.add(service.square(i),service.square(j));
    }

    public int add(int i,int j)
    {
        return i+j;
    }

    public int evaluateAddition(String expression){
        if(expression==null)
            return -1;
        int sum = 0;
        try{
            for(String s : expression.split("\\+")){
                sum += Integer.parseInt(s);
            }
        } catch (NumberFormatException n){
            System.out.println("Number format is wrong");
            return -1;
        }

        return sum;
    }
}
