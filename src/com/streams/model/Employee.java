package com.streams.model;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Employee {
	private Integer id;
	private String name;
	private Position position;
	private Double salary;
	private Date dateOfBirth;

	public Employee() {
	}

	public Employee(Integer id, String name, Position position, Double salary, Date dateOfBirth) {
		this.id = id;
		this.name = name;
		this.position = position;
		this.salary = salary;
		this.dateOfBirth = dateOfBirth;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	
	public Integer getAge(){
		Calendar dob = Calendar.getInstance();  
		dob.setTime(dateOfBirth);  
		Calendar today = Calendar.getInstance();  
		int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);  
		if (today.get(Calendar.MONTH) < dob.get(Calendar.MONTH)) {
		  age--;  
		} else if (today.get(Calendar.MONTH) == dob.get(Calendar.MONTH)
		    && today.get(Calendar.DAY_OF_MONTH) < dob.get(Calendar.DAY_OF_MONTH)) {
		  age--;  
		}
		return age;
	}

	@Override
	public String toString() {
		return this.name + "["+ this.position.getName() 
				+" - $"+ this.salary +"] - dob: " 
				+ new SimpleDateFormat("MM-dd-yyyy").format(this.dateOfBirth) + "("+ getAge() +" yrs old)";
	}
	
	public static void doAThing(Employee e){
		System.out.println("Doin a thing : " + e);
	}
}
