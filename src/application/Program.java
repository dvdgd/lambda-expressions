package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;

import entities.Employee;

public class Program {

	public static void main(String[] args) {

		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		List<Employee> employee = new ArrayList<>();
		
		System.out.print("Enter full file path: ");
		String path = sc.nextLine();
		
//		***CSV FILE***
//		Maria,maria@gmail.com,3200.00
//		Alex,alex@gmail.com,1900.00
//		Marco,marco@gmail.com,1700.00
//		Bob,bob@gmail.com,3500.00
//		Anna,anna@gmail.com,2800.00
		
		
		try (BufferedReader br = new BufferedReader(new FileReader(path))) {
			
			String line = br.readLine();
			while (line != null) {
				String[] fields = line.split(",");
				employee.add(new Employee(fields[0], fields[1], Double.parseDouble(fields[2])));
				line = br.readLine();
			}
			
			System.out.print("Enter salary: ");
			Double salary = sc.nextDouble();
			
			List<String> filterEmail = employee.stream()
					.filter(e -> e.getSalary() > salary)
					.map(e -> e.getEmail())
					.collect(Collectors.toList());
			
			System.out.println("Email of people whose salary is more than " + String.format("%.2f", salary) + ":");
			filterEmail.forEach(System.out::println);
			
			double sum = employee.stream()
					.filter(e -> e.getName().charAt(0) == 'M')
					.map(e -> e.getSalary())
					.reduce(0.0, (x,y) -> x + y);
			
			System.out.println("Sum of salary of people whose name starts with 'M': " + String.format("%.2f", sum));
		}
		catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}
		sc.close();
	}
}