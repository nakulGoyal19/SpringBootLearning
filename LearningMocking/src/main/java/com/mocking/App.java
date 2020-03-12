package com.mocking;

public class App {
    public static void main(String[] args){
        System.out.println("Mock Testing for calculator class \n Passing 3,4 in performSquareSum() method \n Output is : ");
        Calculator c=new Calculator();
        System.out.println(c.performSquareSum(3,4));
        String s = "1+2+3";
        System.out.println("Evaluate String exp. to add all numbers of "+s);
        System.out.println("Output is : "+c.evaluateAddition(s));
        System.out.println("End");

    }
}
