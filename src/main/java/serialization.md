# Serialization Related Question

1. What are Serialization and De-serialization in Java?
2. How do we Serialize object, write a program to serialize and deSerialize object and persist it in file
3. Difference between Externalizable and Serialization interface
4. How can you customize Serialization and DeSerialization process when you have implemented Serializable interface
5. Wap to explain how can we Serialize and DeSerialize object by implementing Externalizable interface
6. How can you avoid certain member variables of class from getting Serialized?
7. What is the use of serialVersionUID? 
8. How can we obtain the serialVersionUID for java classes which implement the Serializable interface?
9. What will be impact of not defining serialVersionUID in class
10. What are compatible and incompatible changes in Serialization process?
11.  What if Serialization is not available, is any any other alternative way to transfer object over network
12. Why static member variables are not part of java serialization process
13. What is significance of transient variables
14. What will happen if one the member of class does not implement Serializable interface
15.  What will happen if we have used List, Set and Map as member of class?
16. How you can avoid Deserialization process creating another instance of Singleton class
17. How can subclass avoid Serialization if its superClass has implemented Serialization interface


## 1. What are Serialization and De-serialization in Java?
Serialization is a process by which you can save or transfer the state of an object by converting it to a byte stream.

During Serialization, the Object is converted into a binary format, which can be persisted into a disk or sent over the network to any other running Java virtual machine. During deserialization, the reverse process is performed by creating an object from a binary stream.

FYI, the serialization process is platform-independent, which means an object serialized on one platform can be deserialized on a different platform.

## 2. How do we Serialize object, write a program to serialize and deSerialize object and persist it in file

In order to serialize object our class needs to implement java.io.Serializable interface. Serializable interface is Marker interface i.e. it does not have any methods of its own, but it tells Jvm that object has to converted into byte stream. Add a serialVersionUID (optional but recommended): It’s good practice to add a unique serialVersionUID to the class. This ID helps in version control for serializable classes, ensuring compatibility across different versions.

```java
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
class Employee implements Serializable {
    
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String name;
 
    public Employee(Integer id, String name) {
           this.id = id;
           this.name = name;
    }
    @Override
    public String toString() {
           return "Employee [id=" + id + ", name=" + name + "]";
    }
}
 
public class SerializeEmployee {
    public static void main(String[] args) {
           Employee object1 = new Employee(1, "amy");
           Employee object2 = new Employee(2, "ankit");
           try {
                  OutputStream fout = new FileOutputStream("ser.txt");
                  ObjectOutput oout = new ObjectOutputStream(fout);
                  System.out.println("Serialization process has started, serializing employee objects...");
                  oout.writeObject(object1);
                  oout.writeObject(object2);
                    oout.close();
                  System.out.println("Object Serialization completed.");
           } catch (IOException ioe) {
                  ioe.printStackTrace();
           }
    }
}
 
/*OUTPUT
 
Serialization process has started, serializing employee objects...
Object Serialization completed.
 
*/

```
```java
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
 
public class DeSerializeEmployee {
 
    public static void main(String[] args) {
           InputStream fin;
           try {
                  fin = new FileInputStream("ser.txt");
                  ObjectInput oin = new ObjectInputStream(fin);
 
                  System.out.println("DeSerialization process has started, "
                               + "displaying employee objects...");
                  Employee emp;
                  while ((emp = (Employee) oin.readObject()) != null) {
                        System.out.println(emp);
                  }
                  oin.close();
 
           } catch (EOFException e) {
                  System.out.println("File ended");
           }  catch (FileNotFoundException e) {
                  e.printStackTrace();
           } catch (IOException e) {
                  e.printStackTrace();
           } catch (ClassNotFoundException e) {
                  e.printStackTrace();
           }
 
           System.out.println("Object DeSerialization completed.");
 
    }
}
/*OUTPUT
 
DeSerialization process has started, displaying employee objects...
Employee [id=1, name=amy]
Employee [id=2, name=ankit]
File ended
Object DeSerialization completed.
 
*/
```

## 3. Difference between Externalizable and Serialization interface


| Feature                           | SERIALIZABLE                                                                                               | EXTERNALIZABLE                                                                                                                                   |
|-----------------------------------|-----------------------------------------------------------------------------------------------------------|---------------------------------------------------------------------------------------------------------------------------------------------------|
| **Methods**                       | It is a marker interface; it doesn’t have any methods.                                                     | It’s not a marker interface. It has methods called `writeExternal()` and `readExternal()`.                                                        |
| **Default Serialization Process** | YES, `Serializable` provides its own default serialization process; we just need to implement `Serializable` interface. | NO, we need to override `writeExternal()` and `readExternal()` for the serialization process to happen.                                          |
| **Customize Serialization Process** | We can customize the default serialization process by defining `readObject()` and `writeObject()` methods in our class. **Note:** We are not overriding these methods, just defining them in our class. | The serialization process is completely customized. We need to override the `Externalizable` interface’s `writeExternal()` and `readExternal()` methods. |
| **Control over Serialization**    | Provides less control over serialization, as defining `readObject()` and `writeObject()` is not mandatory. | Provides great control over the serialization process as overriding `writeExternal()` and `readExternal()` is mandatory.                         |
| **Constructor Call during Deserialization** | Constructor is not called during deserialization.                                                        | Constructor is called during deserialization.                                                                                                    |


## 4. How can you customize Serialization and DeSerialization process when you have implemented Serializable interface
Serialization process can be customized by defining writeObject()  method & DeSerialization process by defining readObject() method.

Let’s customize Serialization process by defining writeObject()  method :

```java
      private void writeObject(ObjectOutputStream os) {
           System.out.println("In, writeObject() method.");    
           try {
                  os.writeInt(this.id);
                  os.writeObject(this.name);
           } catch (Exception e) {
                  e.printStackTrace();
           }
    } 

```
We have serialized id and name manually by writing them in file.

Let’s customize DeSerialization process by defining readObject()  method :
```java
    private void readObject(ObjectInputStream ois) {
           System.out.println("In, readObject() method.");
           try {
                  id=ois.readInt();
                  name=(String)ois.readObject();
           } catch (Exception e) {
                  e.printStackTrace();
           }
    } 
```
We have DeSerialized id and name manually by reading them from file.


## 5. Wap to explain how can we Serialize and DeSerialize object by implementing Externalizable interface

For serializing object by implementing Externalizable interface, we need to override writeExternal() and readExternal() for serialization process to happen.

For Serialization process override writeExternal()  method & for DeSerialization process by override readExternal() method.

Let’s customize Serialization process by overriding writeExternal() method :

```java
public void writeExternal(ObjectOutput oo) throws IOException {
System.out.println("in writeExternal()");
oo.writeInt(id);
oo.writeObject(name);
}
```
We have serialized id and name manually by writing them in file.

Let’s customize DeSerialization process by overriding readExternal()  method :
```java
public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
System.out.println("in readExternal()");
this.id=in.readInt();
this.name=(String)in.readObject();
}
```
We have DeSerialized id and name manually by reading them from file.

## 6. How can you avoid certain member variables of class from getting Serialized?
Mark member variables as static or transient, and those member variables will no more be a part of Serialization.

## 7. What is the use of serialVersionUID?

When working with serialized data on different JVMs we need to ensure and verify that both the sender and receiver of the serialized objects have loaded the classes for that object that are compatible with respect to the serialization. serialVersionUID is used for this verification process.

During object serialization, the default Java serialization mechanism writes the metadata about the object, which includes the class name, field names, types, and superclass. This class definition is stored as a part of the serialized object. This stored metadata enables the deserialization process to reconstitute the objects and map the stream data into the class attributes with the appropriate type.

Every time an object is serialized the java serialization mechanism automatically computes a hash value. ObjectStreamClass’s computeSerialVersionUID() method passes the class name, sorted member names, modifiers, and interfaces to the secure hash algorithm (SHA), which returns a hash value called serialVersionUID or suid.

When the serialized object is retrieved, the JVM first evaluates the ‘suid’ of the serialized class and compares the ‘suid’ value with one of the objects. If the suid values match then the object is said to be compatible with the class and hence it is de-serialized. If not InvalidClassException exception is thrown. The only way to get rid of the InvalidClassException is to recompile and deploy the application again.

A serialVersionUID field can be declared explicitly by a Serliazable class, it must be static, final, and of type long.

## 8 How can we obtain the serialVersionUID for java classes which implement the Serializable interface?
serialver is a command which can be used to obtain the serialVersionUID for one or more java classes.

## 9. What will be impact of not defining serialVersionUID in class
If we  don’t define serialVersionUID in the class, and any modification is made in class, then we won’t be able to deSerialize our class because serialVersionUID generated by java compiler for modified class will be different from old serialized object. And deserialization process will end up throwing java.io.InvalidClassException  (because of serialVersionUID mismatch) 

## 10. What are compatible and incompatible changes in Serialization process?

Compatible Changes :  Compatible changes are those changes which does not affect deSerialization process even if class was updated after being serialized (provided serialVersionUID has been declared)
1. Adding new fields - We can add new member variables in class.
2. Adding writeObject()/readObject()  methods - We may add these methods to customize serialization process.
3. Removing writeObject()/readObject() methods - We may remove these methods and then default customization process will be used.
3. Changing access modifier of a field - The change to access modifiers i.e. public, default, protected, and private have no effect on the ability of serialization to assign values to the fields.
4. Changing a field from static to non static OR changing transient filed to non transient field. - it’s like addition of fields.

InCompatible Changes :  InCompatible changes are those changes which affect deSerialization process if class was updated after being serialized (provided serialVersionUID has been declared)
1. Deletion of fields.
2. Changing a nonstatic field to static or  non transient field to transient field. - it’s equal to deletion of fields.
3. Modifying the writeObject() / readObject() method - we must not modify these method, though adding or removing them completely is compatible change.

## 11.  What if Serialization is not available, is any any other alternative way to transfer object over network

* We can can convert JSON to transfer the object. JSON is helpful in stringifying and de stringifying object.
* Hibernate (ORM tool) helps in persisting object as it in database and later we can read persisted object.
* We can convert object into XML (as done in web services) and transfer object over network.

## 12. Why static member variables are not part of java serialization process

Serialization is applicable on objects or primitive data types only, but static members are class level variables, therefore, different object’s of same class have same value for static member.
So, serializing static member will consume unnecessary space and time.
Also, if modification is made in static member by any of the object, it won’t be in sync with other serialized object’s value.

## 13. What is significance of transient variables
Serialization is not applicable on transient variables (it helps in saving time and space during Serialization process), we must mark all rarely used variables as transient. We can initialize transient variables during deSerialization by customizing deSerialization process.

## 14. What will happen if one the member of class does not implement Serializable interface
This is classy question which will check your in depth knowledge of Serialization concepts. If any of the member does not implement Serializable than  NotSerializableException is thrown

## 15.  What will happen if we have used List, Set and Map as member of class?
This question which will check your in depth knowledge of Serialization and Java Api’s. ArrayList, HashSet and HashMap implements Serializable interface, so if we will use them as member of class they will get Serialized and DeSerialized as well

## 16. How you can avoid Deserialization process creating another instance of Singleton class
We can simply use readResove() method to return same instance of class, rather than creating a new one.

Defining readResolve() method ensures that we don't break singleton pattern during DeSerialization process.

```java
private Object readResolve() throws ObjectStreamException {
return INSTANCE;
}
```

Also define readObject() method, rather than creating new instance, assign current object to INSTANCE like done below :
```java
private void readObject(ObjectInputStream ois) throws IOException,ClassNotFoundException{
ois.defaultReadObject();
synchronized (SingletonClass.class) {
if (INSTANCE == null) {
INSTANCE = this;
}
}
}
```

## 17. How can subclass avoid Serialization if its superClass has implemented Serialization interface
If superClass has implemented Serializable that means subclass is also Serializable (as subclass always inherits all features from its parent class), for avoiding Serialization in sub-class we can define writeObject() method and throw NotSerializableException() from there as done below.
```java
private void writeObject(ObjectOutputStream os) throws NotSerializableException {
throw new NotSerializableException("This class cannot be Serialized");
} 
```