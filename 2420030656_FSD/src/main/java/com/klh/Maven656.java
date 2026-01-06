package com.klh;

class Bank {
    public double getInterestRate() {
        return 4.0;
    }
}

class SBI extends Bank {
    @Override
    public double getInterestRate() {
        return 7.0;
    }
}

class HDFC extends Bank {
    @Override
    public double getInterestRate() {
        return 7.5;
    }
}

public class Maven656 {
    public static void main(String[] args) {
    	// TODO Auto-generated method stub
/*Write a Java program to demonstrate method overriding
 *  by creating a superclass Bank with a method to return interest rate. 
 *  Override this method in subclasses such as SBI and HDFC.
 */
        Bank b1 = new Bank();
        Bank b2 = new SBI();
        Bank b3 = new HDFC();

        System.out.println(b1.getInterestRate());
        System.out.println(b2.getInterestRate());
        System.out.println(b3.getInterestRate());
    }
}