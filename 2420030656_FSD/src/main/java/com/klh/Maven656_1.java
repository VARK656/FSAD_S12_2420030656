package com.klh;

class Vehicle {
    void display() {
        System.out.println("In a Vehicle");
    }
}

class Car extends Vehicle {
    void start() {
        System.out.println("Car is ready to start!");
    }
}

class ElectricCar extends Car {
    void charge() {
        System.out.println("ElectricCar is charging");
    }
}

public class Maven656_1 {
    public static void main(String[] args) {
    	// TODO Auto-generated method stub
    	/*Write a Java program to demonstrate multilevel
    	 * inheritance by creating classes Vehicle, Car,
    	 * and ElectricCar. Each class should have its own method.
    	 * Show how methods of all parent classes are accessible by the child class.
    	 */
        ElectricCar ec = new ElectricCar();
        ec.display();
        ec.start();
        ec.charge();
    }
}