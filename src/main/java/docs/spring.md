# What is spring?

Spring is an open source development framework for development of Java applications

Name some of the important Spring Modules?

Some of the important Spring Framework modules are:
* Spring Context – for dependency injection.
* Spring AOP – for aspect oriented programming.
* Spring DAO – for database operations using DAO pattern
* Spring JDBC – for JDBC and DataSource support.
* Spring ORM – for ORM tools support such as Hibernate
* Spring Web Module – for creating web applications.
* Spring MVC – Model-View-Controller implementation for creating web applications, web services etc

The Core Container consists of the spring-core, spring-beans, spring-context, spring-context-support, and spring-expression (Spring Expression Language) modules.


# What is the purpose of application.properties file?
application.properties file is used to configure property values to run the application in different environments. It can be used to configure database details and log generation etc.
application.properties file is present in src/main/resources directory.

# What is pom.xml file?
POM stands for Project Object Model. It is an XML file in which the configuration of a Maven project is defined. The <dependency> tag lists all the dependencies with <groupid>, <artifactid>, <version>, and <packaging> tags providing necessary details about the dependencies. Maven uses the information in pom.xml to build the project

# What design patterns are used in the Spring framework?
Spring framework uses a number of design patterns:

Factory Pattern: BeanFactory and ApplicationContext classes
Singleton Pattern: Singleton-scoped beans
Prototype Pattern: Prototype-scoped beans
Proxy Pattern: Spring Aspect Oriented Programming support
Template Method Pattern: JdbcTemplate, JmsTemplate, JpaTemplate etc.
Data Access Object Pattern: Spring DAO support
Model View Controller Pattern: Spring MVC
Front Controller Pattern: DispatcherServlet in Spring MVC
View Helper Pattern: custom JSP tags separate code from presentation in views.
Adapter Pattern: JMS adapters and JDBC adapters in Spring Integration

# What is Inversion of Control (IoC)?
Inversion of Control (IoC) is a design principle where the control of object creation, configuration, and lifecycle management is transferred from the application code to a container or framework. 
In simpler terms, IoC means that objects do not create other objects on which they depend. Instead, they get the objects they need from an external source (the IoC container).
IoC helps in decoupling the application components, making the code more modular, testable, and maintainable. Spring’s IoC container is responsible for instantiating, configuring, and assembling objects known as beans.

# What is the responsibility of an IoC container?
An IoC container performs the following tasks:

It instantiates the application class.
It identifies the beans along with their dependencies and wires the dependencies.
It manages the lifecycle of the beans from the time they are created till the time they are destroyed.
The IoC container uses the configuration metadata, in the form of an XML file or Java annotations, which contains instructions about the objects and their dependencies.

# Describe the two types of IoC containers.
The two types of IoC containers are BeanFactory and ApplicationContext. These are interfaces with various implementations that act as the IoC container.

BeanFactory provides the basic functionality of an IoC container while ApplicationContext adds extra functionality like AOP, message resource handling for internationalization, and WebApplicationContext for web applications.

# Give an example of the BeanFactory implementation.
The most commonly used implementation of BeanFactory is the XmlBeanFactory class. This container reads the metadata from an XML config file to create a fully configured application.

# What is ApplicationContext?
ApplicationContext is a type of IoC container. It extends the BeanFactory interface.

Similar to the BeanFactory, the ApplicationContext can load bean definitions, wire beans together, and return beans upon request. Additional features of ApplicationContext that are not part of the BeanFactory support for AOP and internationalization, publishing events, and application layer-specific contexts like WebApplicationContext.

# Give examples of the ApplicationContext implementations.
Commonly used implementations of ApplicationContext are:

**ClassPathApplicationXmlContext**: reads the configuration from an XML file for standalone Java applications.
**AnnotationConfigApplicationContext**: uses annotation-based configuration for standalone Java applications.
**AnnotationConfigWebApplicationContext** and **WebXmlApplicationContext**: for web applications.

# Difference Between BeanFactory and ApplicationContext

The default implementation of BeanFactory uses lazy initialization. It instantiates beans when the getBean() method is called. ApplicationContext extends the BeanFactory interface but the default implementation uses eager initialization. The beans are instantiated when the application starts. However, this behavior can be overridden.

BeanFactory does not support annotation-based dependency injection. This feature was included in ApplicationContext.

| **Feature**                                       | **ApplicationContext**                                           | **BeanFactory**                               |
|---------------------------------------------------|-------------------------------------------------------------------|-----------------------------------------------|
| Configuration Files                               | More than one configuration file possible                        | Only one configuration file or .xml file      |
| Event Publication (ApplicationEvent publication) | Supports event publication to beans registered as listeners      | Does not support                              |
| Internationalization (I18N)                      | Supports convenient MessageSource access for I18N                | Does not support                              |
| Application Life-Cycle Events                    | Supports application life-cycle events and validation            | Does not support                              |
| Enterprise Services                               | Supports many enterprise services such as JNDI access, EJB integration, remoting | Does not support                              |



# How is ApplicationContext configured in Spring?
There are multiple ways to configure application context:

Application context can be configured using XML. The context can be created using the ClassPathXmlApplicationContext, FileSystemXmlApplicationContext, or GenericXmlApplicationContext class which looks for an XML file defining the configuration.

Annotations can also be used to automatically register classes in the application context. The @Component annotation (along with @Controller, @Service, and @Repository annotations) is used on classes, and the AnnotationConfigApplicationContext class is used to create the application context.

Java configuration using the @Configuration annotation on classes and @Bean annotation on methods is another way to configure the application context. The AnnotationConfigApplicationContext class is used to create the context by scanning the annotations.

# What is WebApplicationContext?
WebApplicationContext interface extends ApplicationContext interface. It provides configuration for web applications.

WebApplicationContext defines request, session, and global application scopes in addition to the singleton and prototype scopes in ApplicationContext.

It has the ability to resolve themes and decide which servlet it is associated with.

# What happens if the context is not closed?#
Not closing the context leads to resource leaks. The close() method destroys all beans, releases the locks and closes the bean factory. Similarly, using a try-with-resources block also ensures that each resource is closed when the block exits.

# What is dependency injection?#
Dependency injection is a concept that states that the developer should not create objects manually in the code but specify how the objects should be created. 
The IoC container reads this information and instantiates the object with the required dependencies.
Dependency injection is the process of finding a bean to be autowired. If class A has a dependency on class B, then the process of identifying the dependency, 
creating an instance of class B, and autowiring the object of class B in class A is called dependency injection.

# How is dependency injection related to inversion of control?
Inversion of control (IoC) is a general concept which can be expressed in different ways. Dependency injection is an example of IoC.

IoC concept is that the control of creating and wiring the dependencies shifts from the developer to the framework. Dependency injection is the process by which the framework identifies the dependencies of an object, finds a match, and wires the dependency in the object.

# What are the types of dependency injection?
A dependency can be injected in several ways:

- Field injection
- Setter injection
- Constructor injection

# What is constructor injection?
In constructor injection, the IoC container creates the object by calling a constructor with a number of arguments, where each argument represents a dependency on another class.

The following code example uses a constructor to inject the Engine dependency in Vehicle class.
```java
@Component
Class Vehicle {
  private Engine engine;
  Vehicle(Engine engine) {
      this.engine = engine;
  }
  //...
}
```
If the class contains more than one constructor, then the @Autowired annotation must be used on a constructor to tell the spring container that this constructor is to be used for dependency injection. For a class with one constructor, like the one shown above, the @Autowired annotation is optional.

# How does setter injection work?
Setter injection works by calling setter methods to set the property values. Spring container creates the object by calling the no-argument constructor and then calls setter methods to populate the dependencies.
The Engine dependency is injected using the setEngine() method. When Spring finds the @Autowired annotation, it will call the setter method for dependency injection.
```java
@Component
Class Vehicle {

  private Engine engine;

    @Autowired
    void setEngine (Engine engine) {
        this.engine = engine;
    }

    Engine getEngine ( ) {
        return engine;
    }
    //...
}
```
# What is the difference between constructor and setter injection?
- Constructor injection is not partial while setter injection offers partial dependency injection. If an object has 5 fields, it is not possible to pass just 1 in a 5 argument constructor.
- Constructor injection does not override setter, whereas setter injection overrides constructor if both are defined. The IoC container by default chooses setter injection.
- Constructor injection works well if the number of properties is large, whereas setter injection would make the code longer in such a scenario.
- Setter injection is flexible because it is possible to change the value of the property without creating a new bean instance. In case of constructor injection, a new bean is needed if a property is modified.

# Which dependency injection approach is better?
All dependency injection approaches have the same outcome. This is a very debatable question with some people favoring one style while others touting for another.

The documentation for older versions of Spring suggested that constructor injection be used for all mandatory dependencies while setter injection for optional dependencies. However the @Required annotation on a setter method can be used to make it a mandatory dependency.

# What is method injection?
Any method can be used for setting the dependency if the @Autowired annotation is used on it. This is referred to as method injection. The method can have any name. As long as it has the @Autowired annotation, Spring will find a matching dependency to inject.

# What is a circular dependency, and how should it be resolved?
When beanA has a dependency on beanB and benB has a dependency on beanA, it results in a circular dependency. In this case both beans try to inject each other via constructor and Spring throws **BeanCurrentlyInCreationException**.

More than two beans can also result in a circular dependency as follows:

beanA => beanB => beanC => beanD => beanA

Spring creates beans in order in which they are needed. If a bean has a dependency, then the dependency is created first and then injected to complete the creation of the bean. In case of circular dependency, spring cannot decide which bean to create first.

Circular dependency issue arises when using constructor injection because the beans are created when the context is loaded. If using setter or field injection, the beans are created but their dependencies are injected only when they are needed. Thus the circular dependency issue can be avoided.

When using constructor injection, @Lazy annotation can be used. This tells spring that when initializing the bean, inject a proxy. The bean is fully created only when it is needed.

```java
@Component
public class ClassA {

  private ClassB classB;

  @Autowired
  public ClassA(@Lazy ClassB classB) {
      this.classB = classB;
  }
}
```

# What are beans in Spring?
Simply put, a Spring bean is a Java object. When Java objects are created by the Spring container, they are referred to as Spring Beans.

Beans are managed by the Spring container using the configuration metadata in the form of XML or Java annotations. The container instantiates, assembles and manages the lifecycle of a bean. For example, the @Component annotation on a class tells Spring framework that it has to manage the lifecycle of the objects of that class.

# What is the lifecycle of a Spring Bean?#
Spring container instantiates a bean and initializes it. It also injects the required dependencies. When the context is destroyed, all the initialized beans are also destroyed.

Spring provides post initialization and pre destruction methods for custom tasks. These methods can be invoked using XML config file or Java annotations.

# What are custom bean lifecycle methods?
During the lifecycle of the bean, Spring allows the developer to add custom code during bean initialization and bean destruction. 
This can include code for custom business logic at initialization or destruction as well as setting up and cleaning up resources like a database connection or a file etc.

The XML tags init-method and destroy-method are used to define the custom methods inside the <bean> tag. The @PostContruct and @PreDestroy annotations also accomplish the same task.

# What are some features of custom init and destroy methods?
Custom methods for initialization and destruction can have any name.
They can have any access modifier; private, public, or protected.
They can have any return type, but since the return value cannot be captured, void is mostly used as the return type.
They cannot have any input arguments.

# What information does the bean definition contain?#
Bean definition contains information for the container in the form of configuration metadata. Bean’s definition contains the following information:

How the bean is created.
Lifecycle details of the bean.
Dependencies of the bean.

# How can you provide a bean id when using annotations?
We can specify the bean id in the component scan annotation.

```java
@Component("bean1")
public class MyBean {
...
}
```
When Spring registers MyBean class as a bean, it will store bean1 as the bean id. The following code retrieves the bean:

```java
MyBean theBean = context.getBean("bean1", MyBean.class);
```
If explicit bean id is not provided Spring generates a default bean id. The default bean id is the class name with the first letter lower-case.

```java
@Component
public class MyBean {
...
}
```
Now, when Spring registers MyBean class as a bean, it will store myBean as the bean id. To retrieve the bean from the container, we need to use the default bean id:

```java
MyBean theBean = context.getBean("myBean", MyBean.class);
```

# Are Spring beans the same as JavaBeans?
JavaBeans are Java classes that follow certain coding conventions. They have a public no-arg constructor, have private properties allow access to properties using getter and setter methods and implement the java.io.Serializable interface.

Spring beans are objects whose lifecycle is managed by the Spring container. They do not follow the rigorous requirements of JavaBeans.

Spring beans are often the same as JavaBeans but they don’t have to be. Spring beans can have a constructor with arguments and may not implement the java.io.Serializable interface.

# How are beans created?#
Spring framework creates beans in an order. When Spring framework encounters a bean definition in XML file or through an annotation, it checks if the class is dependent on any other class. Suppose class A has a dependency on class B. In this case, Spring will create the object of class B. Once the dependency has been created, the bean for class A can be created by injecting the bean of class B in class A.

# What does the @Bean annotation do?
The @Bean annotation is used on a method to indicate that the method returns a bean to be managed by Spring. The name of the method indicates the bean id. This annotation is used in classes marked with the @Configuration annotation.

The @Bean annotation provides the same functionality as the <bean> tag in XML configuration.

The following configuration class creates the a bean of Vehicle type using the @Bean annotation. The bean id is vehicle.
```java
@Configuration
class VehicleConfig {

  @Bean
  public Vehicle vehicle() {
      return new Vehicle();
  }

}
```

To retrieve the bean, the bean id is used in the getBean() method as follows:

```java
Vehicle myVehicle = context.getBean("vehicle", Vehicle.class);
```
The @Bean annotation can also specify the initMethod() and destroyMethod() for the bean.

# Both @Bean and @Component annotations create beans. What is the difference between the two?
Some differences between the two annotations are:

@Component enables Spring to auto-detect and auto-configure beans while @Bean is used to explicitly declare a bean rather than letting Spring auto-detect it.
@Component is a class level annotation while @Bean is a method level annotation.
Since @Component annotation is used on a class, it keeps the bean definition and class declarations together while @Bean decouples them.

# How can dependencies be injected using the @Bean annotation?
Consider a case where there are two classes, Vehicle and Engine. Engine is a dependency of the Vehicle class. The @Bean annotation can be used to define the beans and inject the dependencies as follows:
```java
@Configuration
public class VehicleConfig{
  //create Engine bean
  @Bean
  public Engine myEngine(){
      return new Engine();
  }
  //create Vehicle bean and inject Engine bean in it
  @Bean
  public Vehicle myVehicle(){
      return new Vehicle(myEngine());
  }
}
```
Here, the dependency is injected by calling myEngine() when creating the vehicle bean.

# What are the different scopes of a bean?
In Spring 5, six scopes have been defined. They are:

Singleton. Only one instance of the bean per IoC container is created.
Prototype. A new instance of the bean is created every time.
Session. One bean is created for every Http session.
Request. One bean is created per Http request.
Application. One bean is created per ServletContext.
Websocket. One bean is created per WebSocket.

The singleton and prototype scopes can be used in any application while the last four scopes are only available for a web application.

# What is the default bean scope in Spring?
A Spring bean is initialized as a singleton bean by default.

# What is the default scope in the web context?
The default bean scope in Spring is Singleton and this applies to web application context as well.

# When are singleton and prototype scopes used?
Singleton scope is used for beans which are stateless, whereas prototype scope is for beans in which we need to maintain a state.

The IoC container handles both types differently. It manages the entire lifecycle of singleton beans but does not do so in the case of prototype beans. The developer is responsible for destroying the prototype beans.

# How is bean scope defined?
The @Scope annotation is used to set the scope of a bean.

@Component
@Scope("prototype")
public class ShoppingCart {

}
In an XML file, the scope attribute can be used as shown below:

<bean id="cart" class="io.datajek.spring.beanscope.ShoppingCart" scope="prototype" />

# Is the Singleton scope in Spring same as the Singleton design pattern?
According to the Singleton design pattern, there can be one bean per Java class. In Spring, singleton scope means one bean per bean id per Spring container (ApplicationContext).

The following XML code creates two beans of the same class:
```java
<bean id="vehicle1" class="io.datajek.spring.Vehicle" scope="singleton">
    <property name="numWheels" value="4"/>    
</bean>    
<bean id="vehicle2" class="io.datajek.spring.Vehicle" scope="singleton">
    <property name="numWheels" value="6"/>    
</bean>
```
Both beans have a different bean id so two Singletons will be created by the Spring container

# Are Singleton beans thread safe?
No, Singleton beans are not thread safe. Thread safety depends upon the implementation of the bean. Singleton is a design pattern that focuses on how beans are created, not how they are executed.

A singleton bean means there is one instance of the bean in the application context. All threads access the same class variables which can lead to inconsistency. Prototype scope on the other hand is thread safe but it is at the expense of performance because now there are multiple objects instead of one.

# Explain prototype bean scope.
Prototype scope means that every time the developer asks for an instance of the bean, the Spring container will create a new instance and return it. This is different from the singleton scope, where only one instance of the bean is created and the Spring container returns the reference of the same instance whenever it receives a request for the bean.

# Does Spring manage the complete lifecycle of beans?#
Spring manages the lifecycle of beans from construction to destruction. However, this statement is not true in the case of prototype beans. The Spring container instantiates, configures and assembles a prototype object. But it keeps no track of the object after it is handed over to the client.

For a prototype bean, the initialization method is called but the destruction method is not called and the client is responsible for destroying the bean and releasing the resources that it acquired.

# What is an inner bean?
A bean which exists within the scope of another bean is called inner bean.

When a bean is used as a property of another bean, it becomes an inner bean. In this case the <bean> tag is used inside the <property> or <constructor-arg> tag.

The container ignores the id or name tags for an inner bean. Inner beans are always anonymous and have a prototype scope.

For example, we can declare a bean of the Engine type as an inner bean inside the Vehicle bean. The following code shows how to set the properties of the outer and inner bean:
```java
<bean id="vehicle" class="io.datajek.spring.beanscope.Vehicle">
  <property name="numWheels" value ="4" >
  <property name="engine">
      <bean class="io.datajek.spring.beanscope.Engine">
          <property name="type" value="Combustion   Engine"></property>
          <property name="rpm" value="4000"></property>
      </bean>
  </property>
</bean>

```
The inner bean is not accessible outside the scope of the outer bean