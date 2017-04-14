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
		
		// Alternative 1. Simple straight forward implementation 
		System.out.println("Alternative 1");
		printPersonsOlderThan(myPersonList, 60);
		
		System.out.println("\nAlternative 2:");
		// Alternative 2. Print person with age in range 
		printPersonsWithinAgeRange(myPersonList, 40, 50);
		
		// Alternative 3. Instantiate Class implementing test interface
		System.out.println("\nAlternative 3:");
		printPersons(
			    myPersonList, new CheckPersonEligibleForSelectiveService());

		// Approach 4: Specify Search Criteria Code in an Anonymous Class implementing test interface
		// This is done when calling the printPersons() function, hence the selection is given at 
		// time of the call.
		System.out.println("\nAlternative 4");
		printPersons(myPersonList, new CheckPerson() {
			public boolean test(Person p) {
				return p.gender == Person.Sex.MALE && p.getAge() >= 18 && p.getAge() <= 60;
			}
		});

		// Approach 5: Same as number 4 but using a lambda expression for this 
		// functional interface (interface with one abstract function) makes the 
		// code more readable and compact.
		System.out.println("\nAlternative 5:");
		printPersons(myPersonList,
			(Person p) -> p.gender == Person.Sex.FEMALE && p.getAge() >= 18 && p.getAge() <= 70
		);
		
		/**
		// Here is an example of a lambda expression that does not return anything.
		Runnable r1 = () -> System.out.println("Hello world!");
		
		// This does not work...
		// Runnable r2 = (String s) -> System.out.println(s);
		
		r1.run();
		
		// Create some person instances
		 *
		 */
		
	}

	// Alternative 1: Very specific implementation 
	public static void printPersonsOlderThan(List<Person> roster, int age) {
	    for (Person p : roster) {
	        if (p.getAge() >= age) {
	            p.printPerson();
	        }
	    }
	}
	
	// Alternative 2; Somewhat more general implementation with age inside range
	public static void printPersonsWithinAgeRange(
		    List<Person> roster, int low, int high) {
		    for (Person p : roster) {
		        if (low <= p.getAge() && p.getAge() < high) {
		            p.printPerson();
		        }
		    }
		}

	// Alternative 3: Interface and a separate class implementing the interface.
	// Lots of code.
	public static void printPersons(List<Person> roster, CheckPerson tester) {
		for (Person p : roster) {
			if (tester.test(p)) {
				p.printPerson();
			}
		}
	}
	
}

// Alternative 3: Cont'd
interface CheckPerson {
    public boolean test(Person p);
}

class CheckPersonEligibleForSelectiveService implements CheckPerson {
    public boolean test(Person p) {
        return p.gender == Person.Sex.MALE &&
            p.getAge() >= 18 &&
            p.getAge() <= 25;
    }
}