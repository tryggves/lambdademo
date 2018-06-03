package lambdademo;

import java.time.LocalDate;
import java.util.Calendar;

public class Person {
	public enum Sex {
        MALE, FEMALE
    }

    String name;
    LocalDate birthday;
    Sex gender;
    String emailAddress;
    
    // Constructor
    public Person (String name, String birthday, char gender, String email) {
    	this.name = name;
    	this.birthday = LocalDate.parse(birthday);
    	if (gender=='M')
    		this.gender = Sex.MALE;
    	else
    		this.gender = Sex.FEMALE;
    	this.emailAddress = email;
    }

    public int getAge() {
        // ...
    	int birthyear = birthday.getYear();
    	int thisyear = Calendar.getInstance().get(Calendar.YEAR);
    	return thisyear - birthyear;
    }

    public void printPerson() {
        // ...
    	System.out.println(name);
    }
    
    public void printAge () {
    	System.out.println("Age: " + this.getAge() + ".");
    }
}
