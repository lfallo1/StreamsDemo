package com.streams;

import java.util.Comparator;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.streams.dal.DataLayer;
import com.streams.model.Employee;

public class Driver {

	public static void main(String[] args) {
		
		List<Employee> employees = DataLayer.getInstance().getEmployees();
		
		//------- Stream functions -----------
		
		sortingExample(employees);
		
		//top 5 highest paid employees
//		employees.stream()
//				.filter(x -> x.getSalary() > 400000.0)
//				.sorted((e1, e2) -> -Double.compare(e1.getSalary(), e2.getSalary()))
//				.limit(5)
//				.collect(Collectors.toList())
//				.forEach(e -> System.out.println(e));
//		System.out.println("\r\n");
//		
//		//top 5 highest paid employees. then filtered down to only return developers
//		employees.stream()
//				.filter(x -> x.getSalary() > 400000.0)
//				.sorted((e1, e2) -> -Double.compare(e1.getSalary(), e2.getSalary()))
//				.limit(5)
//				.filter(x -> x.getPosition().getName().equals("Developer"))
//				.forEach(e -> System.out.println(e));
//		System.out.println("\r\n");
//		
//		//unique list of positions
//		employees.stream()
//				.map(Employee::getPosition)
//				.distinct()
//				.forEach(p->System.out.println(p.getId() + " - " + p.getName()));
//		System.out.println("\r\n");
//		
//		//5 oldest developers
//		employees.stream()
//			.sorted((e1, e2) -> -Integer.compare(e1.getAge(), e2.getAge()))
//			.filter(e->e.getPosition().getName().equals("Developer"))
//			.limit(5)
//			.forEach(e->System.out.println(e.toString()));
//		
//		Stream<Employee> eStream = employees.stream()
//		.sorted((e1, e2) -> -Integer.compare(e1.getAge(), e2.getAge()))
//		.filter(e->e.getPosition().getName().equals("Developer"))
//		.peek(e -> System.out.println("Before I processing, I was here... " + e));
//		
//		System.out.println("Waiting...");
//		
//		processStream(eStream);
	}
	
	private static void anonymousClasses(){
		Consumer<String> printer = System.out::println;
		printer.accept("Harry");
		
		BinaryOperator<Integer> sum = Integer::sum;
		System.out.println("res is: " + sum.apply(34, 55));
		
		Comparator<String> c = (s1, s2) -> s1.compareTo(s2);
		
		Function<Employee, String> f = e -> {
			return e.getName() + " is " + e.getAge() + " years old and makes $" + e.getSalary();
		};
	}
	
	private static void sortingExample(List<Employee> employees) {
	//		Comparator<String> c = new Comparator<String>() {
	//		@Override
	//		public int compare(String o1, String o2) {
	//			return o1.compareTo(o2);
	//		}
	//	};
		
		Comparator<String> c = (s1, s2) -> s1.compareTo(s2);
		
		Function<Employee, String> f = e -> {
			return e.getName() + " is " + e.getAge() + " years old and makes $" + e.getSalary();
		};
		
		//old way
//		List<String> names = employees.stream().map(Employee::getName).collect(Collectors.toList());
//		names.sort(c);
//		names.stream().forEach(e -> System.out.println(e));
		
		//with streams
		employees.stream()
			.sorted((e1, e2) -> e1.getName().compareTo(e2.getName()))
			.collect(Collectors.toList())
			.forEach(e -> System.out.println(f.apply(e)));
		
		Consumer<String> printer = System.out::println;
		printer.accept("Harry");
		
		BinaryOperator<Integer> sum = Integer::sum;
		System.out.println("res is: " + sum.apply(34, 55));
	}

	private static void processStream(Stream<Employee> stream){
		stream.filter(e -> e.getSalary() > 500000)
			.forEach(e -> System.out.println("After consuming stream... " + e));
	}

}
