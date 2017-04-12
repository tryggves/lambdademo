package lambdademo;

import java.util.ArrayList;
import java.util.List;

// Testing Lambda expressions.
public class MyLambdaDemo {

	public static void main(String[] args) {
		List<Person> myPersonList = new ArrayList();
		
		// TODO Auto-generated method stub
		System.out.println("Starting Lambda expression demo...");
		
		// Here is an example of a lambda expression that does not return anything.
		Runnable r1 = () -> System.out.println("Hello world!");
		
		// This does not work...
		// Runnable r2 = (String s) -> System.out.println(s);
		
		r1.run();
		
		// Create some person instances
		
	}

}
