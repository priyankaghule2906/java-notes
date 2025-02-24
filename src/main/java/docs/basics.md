## Basic OOPs concept

### Question: What are the oops concepts?

1. ##### Encapsulation
   Definition: Encapsulation is the bundling of data (attributes) and methods (functions) that operate on the data into a single unit, called a class. It also restricts direct access to some of an object's components, which is a way of preventing accidental interference and misuse of the methods and data.
   Example: Using private variables with public getter and setter methods.
2. ##### Abstraction
   Definition: Abstraction is the concept of hiding the complex implementation details of a system and exposing only the necessary and relevant parts. It allows the user to focus on interacting with the system rather than understanding its internal workings.
   Example: Using an interface or an abstract class to define methods that must be implemented, without specifying the exact implementation.
3. ##### Inheritance
   Definition: Inheritance allows a new class (child class) to inherit attributes and methods from an existing class (parent class). It promotes code reusability and establishes a natural hierarchy between classes.
   Example: A Dog class can inherit from an Animal class, meaning it automatically has properties like name and age, and methods like eat() and sleep().
4. ##### Polymorphism
   Definition: Polymorphism allows objects of different classes to be treated as objects of a common super class. It means "many forms" and it allows one interface to be used for a general class of actions. The specific action is determined by the exact nature of the situation.
   Example: Method Overloading (compile-time polymorphism) and Method Overriding (runtime polymorphism).


### Question: What is a class in Java?
A class in Java is a blueprint for creating objects. It defines a datatype by bundling data and methods that work on the data into one single unit. A class can contain fields and methods to describe the behavior of an object.

```java
public class Car {
    // Fields
    private String color;
    private String model;

    // Constructor
    public Car(String color, String model) {
        this.color = color;
        this.model = model;
    }

    // Method
    public void displayDetails() {
        System.out.println("Car Model: " + model + ", Color: " + color);
    }
}
```
### Question: What is an object in Java?
An object is an instance of a class. It is created using the new keyword followed by the class constructor. An object has state (represented by fields) and behavior (represented by methods).

```java
public class Main {
    public static void main(String[] args) {
        Car myCar = new Car("Red", "Toyota");
        myCar.displayDetails();  // Output: Car Model: Toyota, Color: Red
    }
}
```


### Question: What is inheritance in Java?
Inheritance is a mechanism where one class (subclass) inherits the properties and behaviors (fields and methods) of another class (superclass). It allows for hierarchical classification and promotes code reuse.

### Question: Can a class in Java inherit multiple classes?
No, Java does not support multiple inheritance with classes. A class can inherit only one superclass. However, a class can implement multiple interfaces to achieve multiple inheritance in a different way.

### Question: What is polymorphism in Java?
Polymorphism in Java allows methods to do different things based on the object it is acting upon. There are two types of polymorphism: compile-time (method overloading) and runtime (method overriding).


* Method Overloading (Compile-time Polymorphism):
```java
public class MathUtils {
    public int add(int a, int b) {
        return a + b;
    }

    public double add(double a, double b) {
        return a + b;
    }
}
```
* Method Overriding (Runtime Polymorphism):
```java
class Animal {
    public void sound() {
        System.out.println("Animal makes a sound");
    }
}

class Dog extends Animal {
    @Override
    public void sound() {
        System.out.println("Dog barks");
    }
}

public class Main {
    public static void main(String[] args) {
        Animal myDog = new Dog();
        myDog.sound();  // Output: Dog barks
    }
}
```

### Question: What is encapsulation in Java?

Encapsulation is the wrapping of data (variables) and methods (functions) into a single unit called a class. It restricts direct access to some of the object's components, which can only be accessed through methods of their current class.

```java
public class Person {
    private String name;

    // Getter method
    public String getName() {
        return name;
    }

    // Setter method
    public void setName(String name) {
        this.name = name;
    }
}

```

### Question: What is abstraction in Java?

Abstraction is the concept of hiding the complex implementation details and showing only the necessary features of an object. It can be achieved using abstract classes and interfaces.

Abstract Class:

```java
abstract class Animal {
    public abstract void sound();

    public void sleep() {
        System.out.println("Sleeping");
    }
}

class Dog extends Animal {
    public void sound() {
        System.out.println("Bark");
    }
}

public class Main {
    public static void main(String[] args) {
        Dog myDog = new Dog();
        myDog.sound();  // Output: Bark
        myDog.sleep();  // Output: Sleeping
    }
}
```

Interface:

```java
interface Animal {
    void sound();
    void sleep();
}

class Dog implements Animal {
    public void sound() {
        System.out.println("Bark");
    }

    public void sleep() {
        System.out.println("Sleeping");
    }
}

public class Main {
    public static void main(String[] args) {
        Dog myDog = new Dog();
        myDog.sound();  // Output: Bark
        myDog.sleep();  // Output: Sleeping
    }
}

```

### Difference Between Abstract Class and Interface in Java

#### Before Java 8

| **Feature**               | **Abstract Class**                                    | **Interface**                                      |
|---------------------------|-------------------------------------------------------|---------------------------------------------------|
| **Methods**                | Can have abstract and non-abstract methods            | Can only have abstract methods (no method bodies) |
| **Fields**                 | Can have instance variables and constants             | Can only have constants (public, static, final)   |
| **Multiple Inheritance**   | Supports inheritance (single inheritance)             | Does not support multiple inheritance directly    |
| **Constructors**           | Can have constructors                                | Cannot have constructors                          |
| **Access Modifiers for Methods** | Can use any access modifier (public, protected, private) | All methods are implicitly public                 |
| **Use Case**               | Used when you need to share code among classes        | Used to define a contract that classes must follow|
| **Default Implementation** | Can have default implementation in methods            | No default implementation allowed                 |

#### After Java 8

| **Feature**               | **Abstract Class**                                    | **Interface**                                      |
|---------------------------|-------------------------------------------------------|---------------------------------------------------|
| **Methods**                | Can have abstract and non-abstract methods            | Can have abstract, default, and static methods    |
| **Fields**                 | Can have instance variables and constants             | Can have constants (public, static, final)        |
| **Multiple Inheritance**   | Supports inheritance (single inheritance)             | Supports multiple inheritance of default methods  |
| **Constructors**           | Can have constructors                                | Cannot have constructors                          |
| **Access Modifiers for Methods** | Can use any access modifier (public, protected, private) | Methods can be public or default (package-private) |
| **Default Implementation** | Can have default implementation in methods            | Can have default and static method implementations|
| **Static Methods**         | Can have static methods                              | Can have static methods (added in Java 8)         |
| **Private Methods**        | Allowed (since Java 9)                               | Allowed (since Java 9) for default/static methods |
| **Use Case**               | Used when you need to share code among classes        | Used to define a contract and provide optional default behavior|

### Question: What is the super keyword in Java?
The super keyword in Java is used to refer to the immediate parent class object. It can be used to access parent class methods and constructors

```java
class Animal {
    public void sound() {
        System.out.println("Animal makes a sound");
    }
}

class Dog extends Animal {
    public void sound() {
        super.sound();  // Calls the parent class method
        System.out.println("Dog barks");
    }
}

public class Main {
    public static void main(String[] args) {
        Dog myDog = new Dog();
        myDog.sound();  // Output: Animal makes a sound
                        //         Dog barks
    }
}

```
### Question: What is method overloading in Java?
Method overloading in Java is a feature that allows a class to have more than one method with the same name, but different parameters (different type, number, or both). It is a compile-time polymorphism.


```java
public class MathUtils {
    public int add(int a, int b) {
        return a + b;
    }

    public double add(double a, double b) {
        return a + b;
    }
}
```

### Question: What is method overriding in Java?
Method overriding in Java occurs when a subclass provides a specific implementation for a method that is already defined in its superclass. It is a runtime polymorphism.

```java
class Animal {
    public void sound() {
        System.out.println("Animal makes a sound");
    }
}

class Dog extends Animal {
    @Override
    public void sound() {
        System.out.println("Dog barks");
    }
}

public class Main {
    public static void main(String[] args) {
        Animal myDog = new Dog();
        myDog.sound();  // Output: Dog barks
    }
}

```

### Question: What is the this keyword in Java?

The this keyword in Java is a reference variable that refers to the current object. It can be used to refer to the current class instance variable, invoke the current class method, or invoke the current class constructor.

```java
public class Car {
    private String color;

    public Car(String color) {
        this.color = color;  // Refers to the instance variable
    }

    public void displayColor() {
        System.out.println("Car color: " + this.color);
    }
}
```

### Question: What is a constructor in Java?

A constructor in Java is a special method that is called when an object is instantiated. It is used to initialize the object's state. Constructors have the same name as the class and do not have a return type.

```java
public class Car {
    private String color;
    private String model;

    // Constructor
    public Car(String color, String model) {
        this.color = color;
        this.model = model;
    }

    public void displayDetails() {
        System.out.println("Car Model: " + model + ", Color: " + color);
    }
}

```

