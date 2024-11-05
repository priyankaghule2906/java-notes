# Exception Handling 

1. What is an Exception in Java?
2. What are the Exception Handling Keywords in Java?
3. Explain Java Exception Hierarchy?
4. What are the important methods of Java Exception Class?
5. Explain Java 7 ARM Feature and multi-catch block?
6. What is the difference between Checked and Unchecked Exceptions in Java?
7. What is the difference between the throw and throws keyword in Java?
8. How to write custom exceptions in Java?
9. What is OutOfMemoryError in Java?
10. What are different scenarios causing “Exception in thread main”?
11. What is the difference between final, finally, and finalize in Java?
12. What happens when an exception is thrown by the main method?
13. Can we have an empty catch block?
14. Provide some Java Exception Handling Best Practices?
15. What Is the Difference Between an Exception and Error?
16. Is There Any Way of Throwing a Checked Exception from a Method That Does Not Have a Throws Clause?
17. Will the Following Code Compile
18. What Are the Rules We Need to Follow When Overriding a Method That Throws an Exception?
19. What Is a Stacktrace and How Does It Relate to an Exception
20. Can You Throw Any Exception Inside a Lambda Expression’s Body

## 1. What is an Exception in Java?
An exception is an error event that can happen during the execution of a program and disrupts its normal flow. The exception can arise from different kinds of situations such as wrong data entered by the user, hardware failure, network connection failure, etc. Whenever any error occurs while executing a java statement, an exception object is created, and then JRE tries to find an exception handler to handle the exception. If a suitable exception handler is found then the exception object is passed to the handler code to process the exception, known as catching the exception. If no handler is found then the application throws the exception to the runtime environment and JRE terminates the program. Java Exception handling framework is used to handle runtime errors only, compile-time errors are not handled by exception handling framework.

## 2. What are the Exception Handling Keywords in Java?
There are four keywords used in java exception handling.

**throw**: Sometimes we explicitly want to create an exception object and then throw it to halt the normal processing of the program. The throw keyword is used to throw exceptions to the runtime to handle it.
**throws**: When we are throwing any checked exception in a method and not handling it, then we need to use the throws keyword in the method signature to let the caller program know the exceptions that might be thrown by the method. The caller method might handle these exceptions or propagate them to its caller method using the throws keyword. We can provide multiple exceptions in the throws clause and it can be used with the main() method also.
**try-catch**: We use try-catch block for exception handling in our code. try is the start of the block and catch is at the end of the try block to handle the exceptions. We can have multiple catch blocks with a try and try-catch blocks can be nested also. catch block requires a parameter that should be of type Exception.
**finally**: The finally block is optional and can be used only with a try-catch block. Since exception halts the process of execution, we might have some resources open that will not get closed, so we can use the finally block. The finally block gets executed always, whether an exception occurs or not.
## 3. Explain Java Exception Hierarchy?
Java Exceptions are hierarchical and inheritance is used to categorize different types of exceptions. Throwable is the parent class of Java Exceptions Hierarchy and it has two child objects – Error and Exception. Exceptions are further divided into checked exceptions and runtime exceptions. Errors are exceptional scenarios that are out of the scope of application and it’s not possible to anticipate and recover from them, for example, hardware failure, JVM crash, or out-of-memory error. Checked Exceptions are exceptional scenarios that we can anticipate in a program and try to recover from it, for example, FileNotFoundException. We should catch this exception and provide a useful message to the user and log it properly for debugging purposes. Exception is the parent class of all Checked Exceptions. Runtime Exceptions are caused by bad programming, for example, trying to retrieve an element from the Array. We should check the length of the array first before trying to retrieve the element otherwise it might throw ArrayIndexOutOfBoundException at runtime. RuntimeException is the parent class of all runtime exceptions.
[exception.md](exception.md)
## 4. What are the important methods of Java Exception Class?Exception and all of its subclasses don’t provide any specific methods and all of the methods are defined in the base class Throwable.

String getMessage() - This method returns the message String of Throwable and the message can be provided while creating the exception through its constructor.
String getLocalizedMessage() - This method is provided so that subclasses can override it to provide the locale-specific messages to the calling program. Throwable class implementation of this method simply use getMessage() method to return the exception message.
synchronized Throwable getCause() - This method returns the cause of the exception or null if the cause is unknown.
String toString() - This method returns the information about Throwable in String format, the returned String contains the name of Throwable class and localized message.
void printStackTrace() - This method prints the stack trace information to the standard error stream, this method is overloaded and we can pass PrintStream or PrintWriter as an argument to write the stack trace information to the file or stream.

## 5. Explain Java 7 ARM Feature and multi-catch block?

If you are catching a lot of exceptions in a single try block, you will notice that catch block code looks very ugly and mostly consists of redundant code to log the error, keeping this in mind Java 7 one of the features was the multi-catch block where we can catch multiple exceptions in a single catch block. The catch block with this feature looks like below:

```java
catch(IOException | SQLException | Exception ex){
logger.error(ex);
throw new MyException(ex.getMessage());
}
```
Most of the time, we use finally block just to close the resources and sometimes we forget to close them and get runtime exceptions when the resources are exhausted. These exceptions are hard to debug and we might need to look into each place where we are using that type of resource to make sure we are closing it. So java 7 one of the improvements was try-with-resources where we can create a resource in the try statement itself and use it inside the try-catch block. When the execution comes out of the try-catch block, the runtime environment automatically closes these resources. Sample of try-catch block with this improvement is:

```java
try (MyResource mr = new MyResource()) {
System.out.println("MyResource created in try-with-resources");
} catch (Exception e) {
e.printStackTrace();
}

```

## 6. What is the difference between Checked and Unchecked Exceptions in Java?
1. Checked Exceptions should be handled in the code using try-catch block or else the method should use the throws keyword to let the caller know about the checked exceptions that might be thrown from the method. Unchecked Exceptions are not required to be handled in the program or to mention them in the throws clause of the method.
2. Exception is the superclass of all checked exceptions whereas RuntimeException is the superclass of all unchecked exceptions. Note that RuntimeException is the child class of Exception.
3. Checked exceptions are error scenarios that require to be handled in the code, or else you will get compile time error. For example, if you use FileReader to read a file, it throws FileNotFoundException and we must catch it in the try-catch block or throw it again to the caller method. Unchecked exceptions are mostly caused by poor programming, for example, NullPointerException when invoking a method on an object reference without making sure that it’s not null. For example, I can write a method to remove all the vowels from the string. It’s the caller’s responsibility to make sure not to pass a null string. I might change the method to handle these scenarios but ideally, the caller should take care of this.
## 7. What is the difference between the throw and throws keyword in Java?
throws keyword is used with method signature to declare the exceptions that the method might throw whereas throw keyword is used to disrupt the flow of the program and handing over the exception object to runtime to handle it.
## 8. How to write custom exceptions in Java?

We can extend Exception class or any of its subclasses to create our custom exception class. The custom exception class can have its own variables and methods that we can use to pass error codes or other exception-related information to the exception handler. A simple example of a custom exception is shown below.
```java
import java.io.IOException;

public class MyException extends IOException {

	private static final long serialVersionUID = 4664456874499611218L;
	
	private String errorCode="Unknown_Exception";
	
	public MyException(String message, String errorCode){
		super(message);
		this.errorCode=errorCode;
	}
	
	public String getErrorCode(){
		return this.errorCode;
	}


}
```


## 9. What is OutOfMemoryError in Java?
OutOfMemoryError in Java is a subclass of java.lang.VirtualMachineError and it’s thrown by JVM when it ran out of heap memory. We can fix this error by providing more memory to run the java application through java options. $>java MyProgram -Xms1024m -Xmx1024m -XX:PermSize=64M -XX:MaxPermSize=256m
## 10. What are different scenarios causing “Exception in thread main”?
Some of the common main thread exception scenarios are:

* Exception in thread main java.lang.UnsupportedClassVersionError: This exception comes when your java class is compiled from another JDK version and you are trying to run it from another java version.
* Exception in thread main java.lang.NoClassDefFoundError: There are two variants of this exception. The first one is where you provide the class full name with .class extension. The second scenario is when Class is not found.
* Exception in thread main java.lang.NoSuchMethodError: main: This exception comes when you are trying to run a class that doesn’t have the main method.
* Exception in thread “main” java.lang.ArithmeticException: Whenever an exception is thrown from the main method, it prints the exception in the console. The first part explains that an exception is thrown from the main method, the second part prints the exception class name and then after a colon, it prints the exception message.
## 11. What is the difference between final, finally, and finalize in Java?
final and finally are keywords in java whereas finalize is a method. final keyword can be used with class variables so that they can’t be reassigned, with the class to avoid extending by classes and with methods to avoid overriding by subclasses, finally keyword is used with try-catch block to provide statements that will always get executed even if some exception arises, usually finally is used to close resources. finalize() method is executed by Garbage Collector before the object is destroyed, it’s a great way to make sure all the global resources are closed. Out of the three, only finally is related to java exception handling.
## 12. What happens when an exception is thrown by the main method?
When an exception is thrown by a main() method, Java Runtime terminates the program and prints the exception message and stack trace in the system console.
## 13. Can we have an empty catch block?
We can have an empty catch block but it’s an example of bad programming. We should never have an empty catch block because if the exception is caught by that block, we will have no information about the exception and it wil be a nightmare to debug it. There should be at least a logging statement to log the exception details in console or log files.
## 14. Provide some Java Exception Handling Best Practices?
Some of the best practices related to Java Exception Handling are:

Use Specific Exceptions for ease of debugging.

Throw Exceptions Early (Fail-Fast) in the program.

Catch Exceptions late in the program, let the caller handle the exception.

Use Java 7 ARM feature to make sure resources are closed or use finally block to close them properly.

Always log exception messages for debugging purposes.

Use multi-catch block for cleaner close.

Use custom exceptions to throw a single type of exception from your application API.

Follow naming convention, always end with Exception.

Document the Exceptions Thrown by a method using @throws in javadoc.

Exceptions are costly, so throw it only when it makes sense. Else you can catch them and provide a null or empty response.

## 15. What Is the Difference Between an Exception and Error?
An exception is an event that represents a condition from which is possible to recover, whereas error represents an external situation usually impossible to recover from.

All errors thrown by the JVM are instances of Error or one of its subclasses, the more common ones include but are not limited to:

OutOfMemoryError – thrown when the JVM cannot allocate more objects because it is out memory, and the garbage collector was unable to make more available
StackOverflowError – occurs when the stack space for a thread has run out, typically because an application recurses too deeply
ExceptionInInitializerError – signals that an unexpected exception occurred during the evaluation of a static initializer
NoClassDefFoundError – is thrown when the classloader tries to load the definition of a class and couldn’t find it, usually because the required class files were not found in the classpath
UnsupportedClassVersionError – occurs when the JVM attempts to read a class file and determines that the version in the file is not supported, normally because the file was generated with a newer version of Java
Although an error can be handled with a try statement, this is not a recommended practice since there is no guarantee that the program will be able to do anything reliably after the error was thrown.

## 16. Is There Any Way of Throwing a Checked Exception from a Method That Does Not Have a Throws Clause?

Yes. We can take advantage of the type erasure performed by the compiler and make it think we are throwing an unchecked exception, when, in fact; we’re throwing a checked exception:
```java
public <T extends Throwable> T sneakyThrow(Throwable ex) throws T {
    throw (T) ex;
}

public void methodWithoutThrows() {
    this.<RuntimeException>sneakyThrow(new Exception("Checked Exception"));
}
```

## 17. Will the Following Code Compile
```java
void doSomething() {
    // ...
    throw new RuntimeException(new Exception("Chained Exception"));
}

```
Yes. When chaining exceptions, the compiler only cares about the first one in the chain and, because it detects an unchecked exception, we don’t need to add a throws clause.

## 18. What Are the Rules We Need to Follow When Overriding a Method That Throws an Exception?

Several rules dictate how exceptions must be declared in the context of inheritance.

1. Checked Exception Restriction: If the superclass method declares a checked exception (like IOException), the overriding method in the subclass can only declare the same checked exception or a subclass of it.
The overriding method cannot declare a broader checked exception than what the superclass method declares. For instance, if the superclass method throws IOException, the subclass method can throw FileNotFoundException (a subclass of IOException) but not Exception (a superclass of IOException).
Unchecked Exceptions:

2. Unchecked exceptions (subclasses of RuntimeException) do not follow the same restriction as checked exceptions.
The subclass method can declare additional unchecked exceptions that the superclass method does not declare, or it can choose not to declare any unchecked exceptions explicitly. This means you can freely add or omit unchecked exceptions when overriding.
No New Checked Exceptions:

3. The overriding method cannot add new checked exceptions that are not declared by the superclass method. For example, if the superclass method does not throw any checked exceptions, the subclass method cannot introduce a new checked exception.
No-Throw Override:

4. If the superclass method throws an exception, the overriding method in the subclass does not have to throw any exceptions. The subclass can choose to handle the exception internally or avoid it entirely, effectively "narrowing" the exceptions thrown.

## 19. What Is a Stacktrace and How Does It Relate to an Exception
A stack trace provides the names of the classes and methods that were called, from the start of the application to the point an exception occurred.

It’s a very useful debugging tool since it enables us to determine exactly where the exception was thrown in the application and the original causes that led to it.

## 20. Can You Throw Any Exception Inside a Lambda Expression’s Body
Yes, you can throw exceptions inside a lambda expression’s body, but there are restrictions based on the type of exception and the functional interface the lambda is assigned to.

1. Unchecked Exceptions:
   You can throw unchecked exceptions (subclasses of RuntimeException like NullPointerException, IllegalArgumentException, etc.) inside a lambda without declaring them. This is because unchecked exceptions don’t need to be declared in the throws clause and can be thrown freely within a lambda expression.
2. Checked Exceptions:
   If the lambda expression throws a checked exception (like IOException), it must be declared in the throws clause of the functional interface’s method. This means that the lambda can only throw checked exceptions if the functional interface allows it.
   For example, if you’re using a standard functional interface like Runnable or Consumer, which does not allow checked exceptions, you cannot directly throw a checked exception from within the lambda. You would either need to catch the checked exception within the lambda or wrap it in an unchecked exception (like RuntimeException).
   Example Scenarios
   Scenario 1: Throwing an Unchecked Exception
 ```java
   Runnable r = () -> {
   throw new RuntimeException("Unchecked exception in lambda");
   };

```  
This is valid because RuntimeException is unchecked, and Runnable.run() does not have any throws declarations.

Scenario 2: Throwing a Checked Exception with a Custom Functional Interface
If you create a custom functional interface with a method that declares a checked exception, the lambda can throw that checked exception:

```java

@FunctionalInterface
interface CheckedExceptionFunction {
void apply() throws IOException;
}

CheckedExceptionFunction func = () -> {
throw new IOException("Checked exception in lambda");
};
 ```
This is valid because apply() in CheckedExceptionFunction declares IOException, so the lambda can throw it.

Scenario 3: Throwing a Checked Exception in a Standard Functional Interface (Not Allowed)
Using standard functional interfaces like Consumer or Runnable, which do not allow checked exceptions, would produce a compile-time error:

```java

Consumer<String> consumer = s -> {
throw new IOException("Cannot throw checked exception here");
};  // Compile error: unhandled exception
```
To work around this, you can catch the checked exception within the lambda or rethrow it as an unchecked exception:

```java

Consumer<String> consumer = s -> {
try {
throw new IOException("Checked exception caught in lambda");
} catch (IOException e) {
throw new RuntimeException(e);
}
};
```