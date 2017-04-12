package lambdademo;

// Testing Lambda expressions.
public class MyLambdaDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Starting Lambda expression demo...");
		
		// Here is an example of a lambda expression that does not return anything.
		Runnable r1 = () -> System.out.println("Hello world!");
		
		// This does not work...
		// Runnable r2 = (String s) -> System.out.println(s);
		
		r1.run();
		
	}

}
