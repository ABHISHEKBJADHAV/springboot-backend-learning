1\. Why do we need abstract classes when we already have interfaces?

Answer:

Abstract classes allow partial implementation — we can provide both implemented and unimplemented methods, along with state (fields) and constructors.

Interfaces are mainly for contract definition and multiple inheritance of behavior.

Real use:

When base class has common code + common state, use abstract class.

When only behavior contract is needed, use interface.



2\. Can an abstract class have constructors and non-abstract methods?

Answer:

Yes.

Abstract class can have constructors, fields, and concrete methods.

Why?

Constructors are used to initialize variables of the abstract class when a child object is created.



3\. What happens if a subclass does not implement all abstract methods?

Answer:

Then the subclass itself must be declared abstract.

Otherwise, compilation error occurs.



4\. Can we create an object of abstract class using reference? How?

Answer:

We cannot instantiate an abstract class directly.

But we can create a reference of abstract class pointing to a child object.

Shape s = new Circle();  // This supports runtime polymorphism.



5\. When will you prefer abstract class over interface in real projects?

Answer:

When:

There is common base logic

There are instance variables

We want code reuse

Constructor initialization is needed

example:- **AbstractSecurityBuilder** in Spring



Interface -
An interface is a blueprint of a class that contains method declarations and constants.

It supports multiple inheritance and is used to achieve 100% abstraction (till Java 7).

From Java 8 onward, interfaces can also have default and static methods.



1\. Why do we use interface instead of abstract class?

Answer:

Interface is used when we want to define a contract that multiple unrelated classes can implement.

It supports multiple inheritance, whereas abstract class does not. Note: can implement-multiple interfaces but can extend only-one class

2. Can an interface have method implementation?

Answer:

Yes, from Java 8, interfaces can have:

default methods (with implementation)

static methods

But they cannot have instance variables or constructors

interface MyInterface {

&nbsp;   default void show() {

&nbsp;       System.out.println("Default method");

&nbsp;   }



&nbsp;   static void display() {

&nbsp;       System.out.println("Static method");

&nbsp;   }

}





4\. Why are variables in interface public static final by default?

Answer:

Because interface represents constants, not object state.

So variables must be:

public → accessible everywhere

static → shared

final → constant



6\. What problem did default methods solve?

Answer:

They allow adding new methods to interfaces without breaking existing implementations. Example - forEach method of Iterable interface.

YES — a default method can be overridden.

Important rules:

Override is optional

If two interfaces provide same default method → class MUST override (Diamond Problem)

You can still call the original one: Iterable.super.forEach(action);

e.g. -

interface A {

    default void fun() { }

}

interface B {

    default void fun() { }

}

class C implements A, B {

    public void fun() {

        A.super.fun();

    }

}

7. Can interface have main method? Can we run an interface?

Answer:

Yes. It can, and it can be executed.

Mostly used for testing / demo purpose

If interface contains a public static void main(String\[] args) method we can run it.



interface Main {

    static void main(String[] args) {

        System.out.println("Main method inside interface");

    }

}

You can run this interface directly.



Generic Types:-

Generics allow you to write type-safe, reusable code by using type parameters instead of concrete types.

public interface Iterable<T>

{

    default void forEach(Consumer<? super T> action)

    {

 	// Statements or logic

    }

}



<T> is a generic type parameter. Means, Here T is a placeholder.

Iterable<String> list; // Now T is String



What is <? super T>

answer:

Accept T or any of its superclasses.

e.g. Consumer<? super Integer>

Then, Consumer can be:

Consumer<Integer>

Consumer<Number>

Consumer<Object>



Bounded Generics:

Upper Bound - class Calculator<T extends Number> { }

Calculator<Integer> c1;

Calculator<Double> c2;



Lower Bound(Wildcards) - void addAll(List<? super Integer> list)

Accepts:

List<Integer>

List<Number>

List<Object>



Summary:

| Syntax          | Meaning                      |

| --------------- | ---------------------------- |

| `<?>`           | Any type                     |

| `<? extends T>` | T or its subclass (Producer) |

| `<? super T>`   | T or its parent (Consumer)   |





Common Questions



Q1. Why Generics?

Compile-time safety + no casting + reusable code.



Q2. What is Type Erasure?

Generic type information is removed at runtime.



Q3. Difference between List<T> and List<?>?

List<T> is specific type; List<?> is unknown type.



Q4. Why use <? super T> in Consumer?

To allow T and its parent types.



Q5. Can we use primitive types with Generics?

❌ No. Only wrapper classes.



Can we use Lambda without creating our own interface?

You cannot use a lambda completely alone in Java.

A lambda must be assigned to a functional interface — but you don’t have to create your own. Java 8 already provides many.

For addition, the best match is BiFunction<T, U, R>.





Stream api:
A Stream in Java is used to process collections of data in a functional, declarative, and pipeline-based way.

It does not store data, it only processes it.

Operations are of two types: Intermediate and Terminal.



1\. Difference between Collection and Stream?

| Collection                     | Stream                    |

| ------------------------------ | ------------------------- |

| Stores data                    | Processes data            |

| Can be iterated multiple times | Can be consumed only once |

| Eager evaluation               | Lazy evaluation           |

| External iteration             | Internal iteration        |



1️⃣ Lazy Evaluation: Intermediate operations are not executed until terminal operation is called.



2️⃣ Immutable: Streams do not modify original collection.



3️⃣ Single Use: Once consumed, stream cannot be reused.



Intermediate Operations: → return Stream

filter(), map(), flatMap(), sorted(), distinct(), limit()



Terminal Operations: → return result like collection. You can use only one terminal operation.

forEach(), collect(), reduce(), count(), anyMatch(), allMatch(), findFirst()



2\. Why Streams are better than loops?

Answer:

Cleaner code, easier parallelism, lazy execution, better readability, less bug-prone.



8\. What is parallel stream? When to use?

Answer:

Parallel stream splits tasks across multiple threads using ForkJoinPool.

Use when:

Data is large

Operations are CPU-intensive

Order does not matter



**Lambda expressions:**
Lambda expressions are core **Java 8** fundamentals.

A Lambda expression is a way to represent an anonymous function that can be passed as behavior.

It works with functional interfaces.



1\. Where are lambdas used in real projects?

Answer:

Streams, event handling, comparators, executors, callbacks, filtering logic.



2\. How lambda improves performance?

Answer:

Reduces object creation, improves readability, enables functional-style processing.



3\. What happens internally when lambda is compiled?

Answer:

Lambda is converted into a private static method and invoked using invokedynamic instruction.



4\. Can lambda throw checked exception?

Answer:

Only if the functional interface method declares it.

Threads:-
A Thread is a lightweight unit of execution.

Multithreading allows multiple threads to run concurrently within a process, improving performance and CPU utilization.

1. Difference between Process and Thread?
   | Process     | Thread                |

| ----------- | --------------------- |

| Heavyweight | Lightweight           |

| Own memory  | Shares process memory |

| Slower      | Faster                |

| Isolated    | Communicates easily   |

2. How to create a thread in Java? Which is better?

Answer:

* Extend Thread class
* Implement Runnable interface (preferred)
* Using ExecutorService (best for production)



3\. Why is Runnable preferred over extending Thread?

Answer:

Java supports single inheritance. Implementing Runnable allows class to extend other classes and separates task from thread.

4. What is synchronization? Why needed?

Answer:

Synchronization prevents race conditions when multiple threads access shared resources.

5. What is deadlock? How to prevent it?

Answer:

Deadlock occurs when two or more threads wait for each other’s resources forever.

Prevention:

* Avoid nested locks:- Minimize synchronized blocks inside each other.
* Use timeout locks
* Lock ordering



**7. What is volatile keyword?**

**Answer:**

**Ensures visibility of changes to variables across threads.**



**8. What is ExecutorService? Why use it?**

**Answer:**

**Framework for managing thread pools.**

**Provides better performance, resource management, and scalability.**



**9. What is thread-safe collection? Examples?**

**Answer:**

**Collections that handle concurrent access safely.**

**Examples:**

**ConcurrentHashMap, CopyOnWriteArrayList, BlockingQueue**



**10. What is race condition?**

**Answer:**

**When multiple threads access shared data and the result depends on execution order.**

