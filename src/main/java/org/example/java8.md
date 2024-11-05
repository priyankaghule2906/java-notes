# Questions and answers on Java 8 features

1. what is a functional interface in java 8

## 1. what is a functional interface in java 8
In Java 8, a functional interface is an interface that has only one abstract method, but can have any number of default or static methods
'@FunctionalInterface' annotation is useful for compilation time checking of your code. You cannot have more than one method besides static, default and abstract methods that override methods in Object in your @FunctionalInterface or any other interface used as a functional interface.
But you can use lambdas without this annotation as well as you can override methods without @Override annotation

This can be used in lambda expression:
```java
public interface Foo {
public void doSomething();
}
```
This cannot be used in lambda expression:
```java
public interface Foo {
  public void doSomething();
  public void doSomethingElse();
}
```

But this will give compilation error:
```java
@FunctionalInterface
public interface Foo {
  public void doSomething();
  public void doSomethingElse();
}
```