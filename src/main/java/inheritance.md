## Some Inheritance Related Questions


### Question : What are different types of Inheritance supported by java

Java supports single Inheritance, multi-level inheritance and at some extent multiple inheritances because Java allows a class to only extend another class, but an interface in Java can extend multiple inheritances.

### Question : What is the difference between single, multilevel, and hierarchical inheritance in Java?

#### Single Inheritance: 
One class inherits from one parent class.
Example: class Dog extends Animal
#### Multilevel Inheritance: 
A class is derived from a class that is also derived from another class. 
Example: class Labrador extends Dog extends Animal
#### Hierarchical Inheritance: 
Multiple classes inherit from a single parent class. 
Example: class Dog extends Animal and class Cat extends Animal

### Question : Why does Java not support multiple inheritance with classes?

Java does not support multiple inheritance (where a class can inherit from more than one superclass) due to several reasons, primarily to avoid complexity and ambiguity in the inheritance structure. Here’s a breakdown of the key reasons:
* First Diamond Problem
Ambiguity: Multiple inheritance can lead to a situation known as the "diamond problem." This occurs when a class inherits from two classes that both inherit from a common superclass. If the two parent classes have overridden a method from the common superclass, and the child class does not override it, there is ambiguity about which version of the method the child class should inherit.
Example: Suppose you have a class A with a method print(). Classes B and C both inherit from A and override print(). If a class D inherits from both B and C, it becomes unclear whether D should inherit the print() method from B or C.
* Second and more convincing reason to me is that multiple inheritances does complicate the design and creates problem during casting, constructor chaining etc and given that there are not many scenarios on which you need multiple inheritances its wise decision to omit it for the sake of simplicity.

Also, java avoids this ambiguity by supporting single inheritance with interfaces. Since the interface only has a method declaration and doesn't provide any implementation there will only be just one implementation of a specific method hence there would not be any ambiguity.

### Question : What is the difference between extends and implements keywords?

* extends: Used when a class inherits another class (single inheritance) or when an interface inherits another interface.
* implements: Used when a class implements an interface to follow a contract (multiple inheritance through interfaces).

### Question : Why Inheritance is used by Java programmers
* Code Reusability: Allows reuse of existing code, reducing duplication.
* Hierarchical Organization: Structures code in a clear hierarchy.
* Polymorphism: Enables objects to be treated as instances of their superclass.
* Encapsulation and Abstraction: Hides implementation details and defines a common interface.
* Maintenance: Simplifies updates and bug fixes across related classes.
* Framework Integration: Extends existing libraries and frameworks easily.

### Question : What is the difference between Inheritance and Abstraction?
abstraction involves creating a simplified model of a complex system by defining a common interface or abstract class. Abstraction hides implementation details and focuses on essential features, allowing subclasses to provide specific implementations. On the other hand, Inheritance allows code reuse. You can reuse the functionality you have already coded by using Inheritance.

### Question : What is the difference between Inheritance and Encapsulation? 
In Short:
* Inheritance deals with extending functionality by creating parent-child class relationships.
* Encapsulation deals with protecting data by bundling it together with methods that control access to it.

Inheritance is an object oriented concept which creates a parent-child relationship. It is one of the ways to reuse the code written for parent class but it also forms the basis of Polymorphism. On the other hand, Encapsulation is an object oriented concept which is used to hide the internal details of a class e.g. HashMap encapsulate how to store elements and how to calculate hash values.

### Question : What is the difference between Polymorphism and Inheritance

Inheritance and polymorphism are both key concepts in object-oriented programming (OOP), but they serve different purposes:
* Inheritance:

Definition: Inheritance allows a class (called a subclass or derived class) to inherit properties and methods from another class (called a superclass or base class). This promotes code reuse and establishes a natural hierarchy between classes.

Purpose: It helps in creating a new class based on an existing class, thus facilitating code extension and modification without altering the existing code.

Example: Suppose you have a base class Animal with methods like eat() and sleep(). If you create a subclass Dog that inherits from Animal, the Dog class will automatically have the eat() and sleep() methods without having to redefine them.
* Polymorphism:

Definition: Polymorphism allows objects of different classes to be treated as objects of a common superclass. It is typically implemented through method overriding or method overloading.

Purpose: It enables a single interface to be used for a general class of actions, allowing for flexibility and the ability to define methods with the same name but different implementations.

Example: If both Dog and Cat classes have a method makeSound(), polymorphism allows you to call makeSound() on an object of type Animal, and the appropriate method will be executed depending on whether the actual object is a Dog or a Cat.
In summary, inheritance deals with creating a new class from an existing class, while polymorphism deals with using a unified interface to interact with objects of different classes.

### Question : What is the difference between Composition and Inheritance in OOP?

In object-oriented programming (OOP), composition and inheritance are two fundamental ways to achieve code reuse and create relationships between classes. Here's a comparison of the two:

Composition

Definition: Composition is a design principle where one class is composed of one or more objects from other classes. It models a "has-a" relationship. For example, a Car "has a" Engine.

Purpose: It is used to build complex types by combining objects of other types. The composed class uses the functionality of its component objects.
Key Differences

Relationship: Inheritance establishes an "is-a" relationship (e.g., Dog is an Animal), while composition establishes a "has-a" relationship (e.g., Car has an Engine).

Flexibility: Composition is generally considered more flexible because it allows you to change components at runtime, whereas inheritance is more rigid due to the hierarchical nature. For example you can change the implementation at runtime by calling setXXX() method, but Inheritance cannot be changed i.e. you cannot ask a class to implement another class at runtime.

Coupling: Inheritance can lead to tight coupling between classes, whereas composition tends to result in looser coupling.

### Question : Can we override static method in Java?

No, static methods in Java cannot be overridden. This is because static methods belong to the class itself, not to instances of the class. When you declare a static method, it's associated with the class rather than with any particular object.
However, you can redefine a static method in a subclass. This is sometimes referred to as "method hiding," but it is not the same as overriding. When you hide a static method, the version that is called depends on the type of the reference used to call the method, not on the actual object type.

```java
class Parent {
    static void display() {
        System.out.println("Static method in Parent class");
    }
}

class Child extends Parent {
    static void display() {
        System.out.println("Static method in Child class");
    }
}

public class Main {
    public static void main(String[] args) {
        Parent p = new Parent();
        p.display(); // Outputs: Static method in Parent class

        Child c = new Child();
        c.display(); // Outputs: Static method in Child class

        Parent pc = new Child();
        pc.display(); // Outputs: Static method in Parent class
    }
}

```


### Question : Can we overload a static method in Java? 
Yes, you can overload a static method in Java. Overloading has nothing to do with runtime but the signature of each method must be different. In Java, to change the method signature, you must change either number of arguments, type of arguments or order of arguments

### Question :  Can you override a private or static method in Java? Why or why not?

* No,  you cannot override a private method in Java because the private method is not inherited by the subclass in Java, which is essential for overriding. In fact, a private method is not visible to anyone outside the class and, more importantly, a call to the private method is resolved at compile time by using Type information as opposed to runtime by using the actual object.

* Private methods cannot be overridden because they are not visible to the child class.

* Static methods cannot be overridden because they belong to the class, not the instance. You can, however, hide static methods in the subclass.

### Question : What is method hiding in Java? 
Since the static method cannot be overridden in Java, but if you declare the same static method in subclass then that would hide the method from the superclass. It means, if you call that method from subclass then the one in the subclass will be invoked but if you call the same method from superclass then the one in superclass will be invoked. This is known as method hiding in Java.

### Question : Can a class implement more than one interface in Java? 
Yes, A class can implement more than one interface in Java e.g. A class can be both Comparable and Serializable at the same time. This is why the interface should be the best use for defining Type as described in Effective Java. This feature allows one class to play a polymorphic role in the program.

### Question : Can a class extends more than one class in Java? 
Answer: No, a class can only extend just one more class in Java.  Though Every class also, by default extend the java.lang.Object class in Java.

### Question : Can an interface extends more than one interface in Java? 
Yes, unlike classes, an interface can extend more than one interface in Java. There are several example of this behavior in JDK itself e.g. java.util.List interface extends both Collection and Iterable interface to tell that it is a Collection as well as it allows iteration via Iterator.


### Question : What will happen if a class extends two interfaces and they both have a method with same name and signature?
In this case, a conflict will arise because the compiler will not able to link a method call due to ambiguity. You will get a compile time error in Java.

### Question : Can we pass an object of a subclass to a method expecting an object of the super class? 
Yes, you can pass that because subclass and superclass are related to each other by Inheritance which provides IS-A property.  I mean Banana is a Fruit so you can pass banana if somebody expect fruit. Now there are scenario, where you can't do e.g. when subclass violates the Liskov Substitution principle i.e. you cannot pass a plastic banana to someone expecting fruit :-), The eat() function will throw exception.

### Question : What is the difference between method overriding and method overloading in Java?

* Method Overriding: Happens when a subclass provides a specific implementation of a method that is already defined in the parent class. It is used for runtime polymorphism.
* Method Overloading: Occurs when multiple methods in the same class have the same name but different parameter lists. It is used for compile-time polymorphism.

### Question : What is constructor chaining in Java?
Constructor chaining is the process of calling one constructor from another constructor, either in the same class or between parent-child classes, to ensure that the parent class is properly initialized before the subclass.

### Question :  What are covariant return types in method overriding?
Covariant return types in Java allow a method in a subclass to override a method in its superclass and change the return type to a subtype of the original return type. This feature enhances the flexibility of method overriding, enabling more specific return types in subclass implementations.
Key Points:
1. Parent-Child Relationship: The return type in the overridden method (in the subclass) must be a subtype of the return type declared in the method being overridden (in the parent class).

2. Before Java 5: In earlier versions of Java, overriding methods were required to have the exact same return type. Covariant return types were introduced in Java 5, allowing the return type to be a more specific class.

3. Advantages:

Covariant return types enhance flexibility in method overriding.
They provide better type safety and eliminate the need for explicit casting.


```java
class Animal {
    Animal getAnimal() {
        return this;
    }
}

class Dog extends Animal {
    @Override
    Dog getAnimal() {  // Covariant return type (Dog is a subclass of Animal)
        return this;
    }
}
```

### Question : Explain the impact of inheritance on memory usage.

Inheritance impacts memory usage by requiring the object of the subclass to store both the subclass's and superclass's fields. However, polymorphism allows efficient memory use through the reference of the superclass, but the subclass object still occupies memory for both inherited and new fields.

### Question : If you have multiple default methods from different interfaces, how would you resolve the conflict in a subclass?
You can explicitly override the conflicting method in the subclass and decide which method to call using the InterfaceName.super.methodName() syntax.
```java
interface A {
    default void show() {
        System.out.println("A show");
    }
}

interface B {
    default void show() {
        System.out.println("B show");
    }
}

class C implements A, B {
    public void show() {
        A.super.show(); // Call A's show method
        B.super.show(); // Call B's show method
    }
}
```

### Explain how inheritance can be misused and lead to poor design.
Misusing inheritance occurs when classes are extended just for code reuse, rather than for establishing an actual "is-a" relationship. This can result in fragile code that is difficult to maintain. Composition is often a better alternative for code reuse.

### Question: What is the significance of the `final` keyword in Java?
The `final` keyword in Java is used to apply restrictions on classes, methods, and variables:
1. **Final Class**: Cannot be subclassed.
   ```java
   public final class Constants {
       // class body
   }
   ```
2. Final Method: Cannot be overridden by subclasses.
```java
public class Base {
    public final void display() {
        System.out.println("This is a final method.");
    }
}
```
3. Final Variable: Its value cannot be changed once assigned.

```java
public class Constants {
    public static final int MAX_VALUE = 100;
}
```











