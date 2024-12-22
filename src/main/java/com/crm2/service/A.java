package com.crm2.service;

public class A {
    int x = 10;
    public static void main(String[] args) {

        A a1 = new A();
        System.out.println(a1.x);
        int y = a1.test1();
        System.out.println(y);

        B b = new B();

        int x = b.otpGen();
        System.out.println(x);
    }
    public int test1( ) {
        return  20;
    }

}
