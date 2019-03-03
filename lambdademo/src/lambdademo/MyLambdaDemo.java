package lambdademo;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

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
		
		// Alternative 3. Instantiate Class implementing test interfac, in this case it 
		// is the class CheckPersonEligibleForSelectiveService that implements interface
		// CheckPerson.
		System.out.println("\nAlternative 3:");
		printPersons(
			    myPersonList, new CheckPersonEligibleForSelectiveService());

		// Approach 4: Specify Search Criteria Code in an Anonymous Class implementing test interface
		// with name CheckPerson including the implementation of the test() function.
		// This is done when calling the printPersons() function, hence the selection is given at 
		// time of the call.
		System.out.println("\nAlternative 4");
		printPersons(myPersonList, new CheckPerson() {
			public boolean test(Person p) {
				return p.gender == Person.Sex.MALE && p.getAge() >= 18 && p.getAge() <= 60;
			}
		});

		/**
		 * Lambda expression syntax:
		 * 1) Parentheses with comma separated list of parameters. Parenthesis can be omitted if
		 * there is only one parameter. Can also omit the type of the parameter...
		 * 
		 * 2) Body consisting of single expression or statement block.
		 */
		// Approach 5: Same as number 4 but using a lambda expression for this 
		// functional interface (interface with one abstract function) makes the 
		// code more readable and compact.
		System.out.println("\nAlternative 5:");
		printPersons(myPersonList,
			(Person p) -> p.gender == Person.Sex.FEMALE && p.getAge() >= 18 && p.getAge() <= 70
		);
		
		// Using return statement instead of expression in the body of the lambda statement.
		// When using statements, you have to enclose the statement in curly braces
		System.out.println("\nAlternative 5b:");
		printPersons(myPersonList,
			(Person p) -> {
				return p.gender == Person.Sex.FEMALE 
						&& p.getAge() >= 18 
						&& p.getAge() <= 70;
			}
		);
		
		// Approach 6: Now, use the out of the box functional interfaces that is found in
		// Java 8. See java.util.function package
		System.out.println("\nAlternative 6:");
		
		// Note the lambda expression in second parameter is a boolean expression taking a
		// Person as input parameter.
		printPersonsWithPredicate(myPersonList, 
			(Person p) -> p.gender == Person.Sex.FEMALE && p.getAge() >= 18 && p.getAge() <= 70
		);
		
		// Approach 7: Taking lambda expressions to the next level by using functional interface
		// for the filtering of the list as well as specifying the function to call for those
		// elements that pass the filter.
		System.out.println("\nAlternative 7:");
		processPersons(myPersonList, 
			p -> p.gender == Person.Sex.FEMALE && p.getAge() >= 18 && p.getAge() <= 70,
			p -> p.printPerson());
		
		// This is the same approach but using another call to the Person class
		processPersons(myPersonList, 
				p -> p.gender == Person.Sex.FEMALE && p.getAge() >= 18 && p.getAge() <= 70,
				p -> p.printAge());
			
		
		/**
		// Here is an example of a lambda expression that does not return anything.
		Runnable r1 = () -> System.out.println("Hello world!");
		
		// This does not work... Because the Runnable.run() function does not take a string parameter...
		// Runnable r2 = (String s) -> System.out.println(s);
		
		r1.run();
		
		// Create some person instances
		 *
		 */
		
	}

	// Alternative 1: Very specific implementation 
	public static void printPersonsOlderThan(List<Person> personGroup, int age) {
	    for (Person p : personGroup) {
	        if (p.getAge() >= age) {
	            p.printPerson();
	        }
	    }
	}
	
	// Alternative 2; Somewhat more general implementation with age inside range
	public static void printPersonsWithinAgeRange(
		    List<Person> personGroup, int low, int high) {
		    for (Person p : personGroup) {
		        if (low <= p.getAge() && p.getAge() < high) {
		            p.printPerson();
		        }
		    }
		}

	// Alternative 3: Interface and a separate class implementing the interface.
	// Lots of code.
	public static void printPersons(List<Person> personGroup, CheckPerson tester) {
		for (Person p : personGroup) {
			if (tester.test(p)) {
				p.printPerson();
			}
		}
	}
	
	// Alternative 6: Declare function and use out of the box fucntional interface; in this
	// case Predicate (java.util.function) that has one abstract function test().
	public static void printPersonsWithPredicate(List<Person> personGroup, Predicate<Person> tester) {
		for (Person p : personGroup) {
			if (tester.test(p)) {
				p.printPerson();
			}
		}
	}
	
	// Alternative 7: Expand on alternative 6 by also specifying the action on persons
	// passing the tester.test(p) predicate. This action is declared using the functional
	// interface Consumer<T> which has one method void accept(T t) and produces no result.
	public static void processPersons (List<Person> roster,
			Predicate<Person> tester,
			Consumer<Person> actOn) {
		for (Person p : roster) {
			if (tester.test(p)) {
				actOn.accept (p);
			}
		}
		
	}
	
}

// Alternative 3: Cont'd
// This is an example of a ***functional*** interface. A functional interface is
// an interface with only one function.
interface CheckPerson {
    public boolean test(Person p);
}

// Alternative 3: Cont'd
// This is the implementation class for the CheckPerson interface declared above
// It is used in the alternative 3 method invokation from the main() function above.
class CheckPersonEligibleForSelectiveService implements CheckPerson {
    public boolean test(Person p) {
        return p.gender == Person.Sex.MALE &&
            p.getAge() >= 18 &&
            p.getAge() <= 25;
    }
}


