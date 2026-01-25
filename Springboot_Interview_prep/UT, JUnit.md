Unit test case vs functional integration test case

Junit and Mockito

System design basics



#### **🔹JUnit:**



What is JUnit?

JUnit is a unit testing framework for Java applications.

It is used to test individual units of code like methods or classes.



Why Unit Testing?

* Catch bugs early
* Improve code quality
* Enable refactoring
* Mandatory in product companies



JUnit Key Annotations:

| Annotation  | Purpose                    |

| ----------- | -------------------------- |

| @Test       | Test method                |

| @BeforeEach | Runs before each test      |

| @AfterEach  | Runs after each test       |

| @BeforeAll  | Runs once before all tests |

| @AfterAll   | Runs once after all tests  |

| @Disabled   | Skip test                  |





Simple JUnit Example:

@Test

void testAddition() {

    assertEquals(5, calculator.add(2,3));

}



Common Assertions:

assertEquals - Verifies that the expected value is equal to the actual value.

assertNotNull - Verifies that the given object is not null.

assertTrue - Verifies that the given condition evaluates to true.

assertThrows - Verifies that a specific exception is thrown when executing a block of code.





#### **🔹Mockito:**



What is Mockito?

Mockito is a mocking framework used with JUnit to simulate dependencies.

It helps test a class without using real external dependencies.



Why Mockito is Needed?

Suppose:

* Service depends on DB
* DB is slow or unavailable

👉 Use mock objects instead of real ones.



Mock: Fake Object



Example:

@Mock

UserRepository repo;

@InjectMocks

UserService service;



Benefits

* Fast tests
* Isolated testing
* No real DB/API calls





#### **🔹**SOAP (Overview Only):



What is SOAP?

SOAP is a protocol for exchanging structured XML-based messages between applications.



Key Points

* XML only



Used In:

* Banking systems
* Government services
* Legacy enterprise systems



SOAP vs REST:

| SOAP       | REST                |

| ---------- | ------------------- |

| XML only   | JSON/XML            |

| Protocol   | Architectural style |

| Complex    | Simple              |

| Enterprise | Modern apps         |



