package com.streams.model;

public class EmployeeDto {
	private String name;
	private Double salary;
	private String jobTitle;

	public EmployeeDto() {
	}

	public EmployeeDto(String name, Double salary, String jobTitle) {
		this.name = name;
		this.salary = salary;
		this.jobTitle = jobTitle;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	@Override
	public String toString() {
		return name + " ($" + salary + ") - " + jobTitle;
	}
}
