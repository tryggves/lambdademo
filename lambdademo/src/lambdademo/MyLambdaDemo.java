package lambdademo;

import java.util.ArrayList;
import java.util.List;

// Testing Lambda expressions.
public class MyLambdaDemo {

	public static void main(String[] args) {
		List<Person> myPersonList = new ArrayList<Person>();
		// Create some persons and add them to the list
		myPersonList.add(new Person("Petter Smart", "1963-01-01", 'M', "petter.smart@test.no"));
		myPersonList.add(new Person("Ada Lovelace", "1972-12-01", 'F', "ada.lovelace@test.no"));
		myPersonList.add(new Person("Jan Balstad", "1994-05-17", 'M', "Jan Balstad@test.no"));
		myPersonList.add(new Person("Jorun Ytterstad", "1945-04-08", 'F', "jorun.ytterstad@test.no"));
		myPersonList.add(new Person("Karl Marx", "1905-08-24", 'M', "karl.marx@test.no"));
		
		// TODO Auto-generated method stub
		System.out.println("Starting Lambda expression demo...");
		
		// Print the list
		int i = 0;
		for (Person p : myPersonList) {
			System.out.print("Person " + (i++) + ": ");
			p.printPerson();
		}
		
		// Here is an example of a lambda expression that does not return anything.
		Runnable r1 = () -> System.out.println("Hello world!");
		
		// This does not work...
		// Runnable r2 = (String s) -> System.out.println(s);
		
		r1.run();
		
		// Create some person instances
		
	}

}
