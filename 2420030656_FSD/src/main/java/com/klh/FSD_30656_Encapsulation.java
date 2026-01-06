package com.klh;
class Student{
	private String name;
	private int id;
	private double cgpa;
	public void setInfo(String name,int id,double cgpa) {
		this.name=name;
		this.id=id;
		this.cgpa=cgpa;
	}
	public void getInfo() {
		System.out.println("Name of the Student is :"+name);
		System.out.println("Student's Id is :"+id);
		System.out.println("Student's cgpa is :"+cgpa);
	}
	
}
public class FSD_30656_Encapsulation {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*Write a Java program to demonstrate encapsulation
		 *  by creating a class Student with private data members
		 *   and public getter and setter methods.*/
		Student s1 = new Student();
		Student s2 = new Student();
		Student s3 = new Student();
		s1.setInfo("Vivek", 656, 9.67);
		s2.setInfo("Anil", 760, 9.5);
		s3.setInfo("Akhil", 777, 9.8);
		s1.getInfo();
		s2.getInfo();
		s3.getInfo();
		

	}

}
