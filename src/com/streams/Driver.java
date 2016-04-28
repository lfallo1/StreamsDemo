package com.streams;

import java.util.Comparator;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.streams.dal.DataLayer;
import com.streams.model.Employee;
import com.streams.model.EmployeeDto;

public class Driver {

	public static void main(String[] args) {
		
		List<Employee> employees = DataLayer.getInstance().getEmployees();
		
		//------- Stream functions -----------
		
		supplierExample(employees);
		
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
//		
//		processStream(eStream);
	}
	
	/**
	 * Four Categories
	 * (1) Consumer --> void accept(T t) ----- Also has BiConsumer
	 * (2) Supplier --> void get() ------ Supports method ref
	 * (3) Predicate --> Boolean test(T t) ------- BiPredicate
	 * (4) Function --> R apply(T t);  MethodRef ------- also has BiFunction
	 * 
	 * Others:
	 * BinaryOperator<T> ---> ex. (i1, i1) --> i1 + i1; alternatively can use static method ref with Integer::sum since Integer implements a static method Integer sum(int n1, int n2){ ... }
	 * IntPredicate
	 * IntFunction
	 * IntToDoubleFunction
	 * @param employees
	 */
	
	private static void consumersExample(List<Employee> employees){
		Consumer<Employee> consumer = e -> {
			Double jobRank = e.getPosition().getId().equals(1) ? 2.3 : e.getPosition().getId().equals(2) ? 1.33 : 3.44;
			Double rating = (e.getSalary() / e.getAge()) * jobRank; 
			System.out.println("Job rank for " + e + " is " + rating);
		};
		employees.stream()
			.limit(10)
			.forEach(e -> consumer.accept(e));
	}
	
	private static void supplierExample(final List<Employee> employees){
		
//		Supplier<Employee> supplier = () -> employees.get(0);
		
		//f***in aye... worst method ever.
		((Consumer<Employee>) e -> System.out.println(e))
			.accept(((Supplier<Employee>)() -> employees.get(0))
			.get());
	}
	
	private static void functionExample(List<Employee> employees){
		
		BiFunction<Employee, EmployeeDto, String> bif = (Employee e, EmployeeDto dto) -> {
			String dtoName = dto.getName();
			String eName = e.getName();
			return dtoName.equals(eName) ? eName : "No match";
		};
		
		bif.apply(employees.get(0), new EmployeeDto("harry", 50000.0, "Sales"));
		
		Function<Employee, Double> fn_old = new Function<Employee, Double>() {

			@Override
			public Double apply(Employee t) {
				return t.getSalary();
			}
		
		};
		Function<Employee, Double> fn = e -> e.getSalary();
		
		Function<Employee, Double> fn_alternate = Employee::getSalary;
		
		
		// (Employee e) -> e.getSalary() could be replaced with any of the above Function<T,R> functional interface implementations
		employees.stream()
			.filter(e -> e.getSalary() > 750000)
			.sorted(Comparator.comparing(Employee::getSalary).reversed())
			.map(e -> {
				EmployeeDto dto = new EmployeeDto(e.getName(), e.getSalary(), e.getPosition().getName());
				return dto;
			})
			.filter(d -> d.getJobTitle().equals("Developer"))
			.forEach(dto -> System.out.println(dto));
	}
	
	private static void predicateExample(List<Employee> employees){
		Predicate<EmployeeDto> p = dto -> dto.getJobTitle().equals("Developer");
		employees
			.stream()
			.map(e -> {
				EmployeeDto dto = new EmployeeDto(e.getName(), e.getSalary(), e.getPosition().getName());
				return dto;
			})
			.filter(p)
			.limit(5)
			.forEach(dto -> System.out.println(dto));
	}
	
	private static void comparatorExample(List<Employee> employees){
		Consumer<String> printer = System.out::println;
		
		Function<Employee, Double> fn_old = new Function<Employee, Double>() {

			@Override
			public Double apply(Employee t) {
				return t.getSalary();
			}
		
		};
		Function<Employee, Double> fn = e -> e.getSalary();
		
		Function<Employee, Double> fn_alternate = Employee::getSalary;
		
		
		// (Employee e) -> e.getSalary() could be replaced with any of the above Function<T,R> functional interface implementations
		employees.stream()
			.filter(e -> e.getSalary() > 750000)
			.sorted(Comparator.comparing((Employee e) -> e.getSalary()).reversed())
			.map(e -> {
				EmployeeDto dto = new EmployeeDto(e.getName(), e.getSalary(), e.getPosition().getName());
				return dto;
			})
			.filter(d -> d.getJobTitle().equals("Developer"))
			.forEach(dto -> printer.accept(dto.toString()));
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
	}

	private static void processStream(Stream<Employee> stream){
		stream.filter(e -> e.getSalary() > 500000)
			.forEach(e -> System.out.println("After consuming stream... " + e));
	}

}
