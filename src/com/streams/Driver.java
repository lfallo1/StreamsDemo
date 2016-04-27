package com.streams;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.streams.dal.DataLayer;
import com.streams.model.Employee;

public class Driver {

	public static void main(String[] args) {
		List<Employee> employees = DataLayer.getInstance().getEmployees();
		
		//------- Stream functions -----------
		
		//top 5 highest paid employees
		employees.stream()
				.filter(x -> x.getSalary() > 400000.0)
				.sorted((e1, e2) -> -Double.compare(e1.getSalary(), e2.getSalary()))
				.limit(5)
				.collect(Collectors.toList())
				.forEach(e -> System.out.println(e));
		System.out.println("\r\n");
		
		//top 5 highest paid employees. then filtered down to only return developers
		employees.stream()
				.filter(x -> x.getSalary() > 400000.0)
				.sorted((e1, e2) -> -Double.compare(e1.getSalary(), e2.getSalary()))
				.limit(5)
				.filter(x -> x.getPosition().getName().equals("Developer"))
				.forEach(e -> System.out.println(e));
		System.out.println("\r\n");
		
		//unique list of positions
		employees.stream()
				.map(Employee::getPosition)
				.distinct()
				.forEach(p->System.out.println(p.getId() + " - " + p.getName()));
		System.out.println("\r\n");
		
		//5 oldest developers
		employees.stream()
			.sorted((e1, e2) -> -Integer.compare(e1.getAge(), e2.getAge()))
			.filter(e->e.getPosition().getName().equals("Developer"))
			.limit(5)
			.forEach(e->System.out.println(e.toString()));
		
		Stream<Employee> eStream = employees.stream()
		.sorted((e1, e2) -> -Integer.compare(e1.getAge(), e2.getAge()))
		.filter(e->e.getPosition().getName().equals("Developer"))
		.peek(e -> System.out.println("Before I processing, I was here... " + e));
		
		System.out.println("Waiting...");
		
		processStream(eStream);
	}
	
	private static void processStream(Stream<Employee> stream){
		stream.filter(e -> e.getSalary() > 500000)
			.forEach(e -> System.out.println("After consuming stream... " + e));
	}

}
