# JDBC



JDBC (Java Database Connectivity) **is an API** used to connect Java applications with databases, execute SQL queries, and process results.

JDBC - It acts as a bridge between Java code and the database.



JDBC Steps :-

1)Import packages

2)Load Driver

3)Register Driver

4)Create Connection

5)Create Statement

6)Execute Statement

7)Close Connection



JDBC architecture -

Java App → JDBC API → JDBC Driver → Database



How They Link JDBC with Spring Boot

Hibernate + JPA internally use JDBC to talk to the database.



❓ Most Common JDBC Interview Questions

1\. Difference between Statement and PreparedStatement



| Statement                   | PreparedStatement          |

| --------------------------- | -------------------------- |

| SQL compiled every time     | Precompiled                |

| Vulnerable to SQL Injection | **Prevents SQL Injection** |

| Slower                      | Faster                     |



2\. What is SQL Injection?

When attacker injects SQL through input:

example query be like - ' OR 1=1 --'

PreparedStatement prevents this.



3\. What is Connection Pooling?

Reuse database connections instead of creating new ones each time.

Improves performance \& scalability.



4\. What is ResultSet?

Object that holds the data returned from the database.



5\. What is AutoCommit?

By default JDBC commits every query automatically.



6\. Why JDBC is rarely used directly in Spring Boot?

Because Spring Data JPA \& Hibernate





# 🧬 **Hibernate**



Hibernate is an ORM (Object Relational Mapping) framework that maps Java objects to database tables and handles database operations automatically. It manages sessions, transactions, caching, lazy loading, and query generation, making database interaction efficient and clean.

* It sits on top of JDBC and removes the need to write most SQL.
* Hibernate generates SQL



**🏗️ Hibernate Architecture:-**

Spring Boot → JPA → Hibernate → JDBC → Database



🧩 Core Hibernate Components:



| Component        | Role                       |

| ---------------- | -------------------------- |

| `\\\\\\\*\\\\\\\*SessionFactory\\\\\\\*\\\\\\\*` | Heavy, one per app         |

| `\\\\\\\*\\\\\\\*Session\\\\\\\*\\\\\\\*`        | Lightweight, per request   |

| `\\\\\\\*\\\\\\\*Transaction\\\\\\\*\\\\\\\*`    | Commit / rollback          |

| `\\\\\\\*\\\\\\\*Query\\\\\\\*\\\\\\\*`          | HQL/JPQL queries           |

| `\\\\\\\*\\\\\\\*Entity\\\\\\\*\\\\\\\*`         | Java class mapped to table |



🔁 Hibernate vs JDBC:



| JDBC             | Hibernate          |

| ---------------- | ------------------ |

| Manual SQL       | Auto-generated SQL |

| Manual mapping   | Automatic mapping  |

| Low-level        | High-level ORM     |

| More boilerplate | Clean code         |



1\. What is ORM?

Mapping Java objects to DB tables.



2\. What is HQL?

Hibernate Query Language — object-oriented SQL.



3\. Difference between get() and load()

| get()                     | load()           |

| ------------------------- | ---------------- |

| Hits DB immediately       | Lazy loading     |

| Returns null if not found | Throws exception |





4\. What is Lazy vs Eager loading?

Eager: load immediately

Lazy: load data only when needed



5\. What is First Level Cache?

Hibernate Session-level cache (always enabled).



6\. What is Second Level Cache?

Shared cache across sessions (optional).



7\. What is N+1 problem?

When Hibernate executes 1 query for parent + N queries for children.

Solved using join fetch.



8\. What is Dirty Checking?

Hibernate automatically detects object changes and updates DB.



9\. What is Transaction?

Ensures ACID properties. Uses commit() and rollback().



###### **🔁 What is Transaction?**



A transaction is a group of database operations that must execute as one unit.

It follows ACID:



Atomicity – all or nothing

Consistency – DB stays valid

Isolation – transactions don’t interfere

Durability – committed data stays saved



Example:

Money transfer → debit + credit must both succeed or both fail.



What is @Transactional?

@Transactional is a Spring annotation that manages transactions automatically.



***Without it:-***

connection.setAutoCommit(false);

commit();

rollback();



***With it:-***

@Transactional

public void saveOrder() {

   orderRepo.save(order);

   paymentRepo.save(payment);

}



If any exception occurs → **automatic rollback.**



###### **🌊 What is Cascade?**

Cascade defines how operations on parent entity affect child entity.

🧑‍💻 Example

@OneToMany(cascade = CascadeType.ALL)

private List<OrderItem> items;



When you save Order, all OrderItems are saved automatically.



Cascade Types:



| Type      | Meaning                         |

| --------- | ------------------------------- |

| `PERSIST` | Save child when parent is saved |

| `MERGE`   | Update child                    |

| `REMOVE`  | Delete child                    |

| `REFRESH` | Reload                          |

| `DETACH`  | Detach from session             |

| `ALL`     | Everything                      |





🧪 Interview Rapid Questions



Q: What happens if exception occurs inside @Transactional method?

➡ Transaction rolls back.



Q: Difference between Cascade and FetchType?

➡ Cascade controls operations, FetchType controls loading.



Q: Is @Transactional needed with JPA?

➡ Yes, for proper transaction management.



###### **🧨 What is N+1 Problem?**



The N+1 problem happens when Hibernate executes:

* 1 query to fetch parent records
* N additional queries to fetch each child record individually



🧑‍💻 Example



***Entities:***

class Department {

   @OneToMany

   List<Employee> employees;

}



***Code:***

List<Department> depts = deptRepo.findAll();

for (Department d : depts) {

   d.getEmployees().size();

}



***SQL generated:***

SELECT \* FROM department;        -- 1 query

SELECT \* FROM employee WHERE dept\_id=1;  -- N queries

SELECT \* FROM employee WHERE dept\_id=2;

SELECT \* FROM employee WHERE dept\_id=3;

...



🚑 Why is it bad?

Because:

* More DB calls
* Slower performance
* High load on database



🛠 How to Solve It?

Use **JOIN FETCH**:

@Query("SELECT d FROM Department d JOIN FETCH d.employees")

List<Department> findAllWithEmployees();





###### **🧲 FetchType: LAZY vs EAGER**



What is FetchType?

It defines when related entities are loaded from the database.



⚖️ Comparison

| LAZY                    | EAGER                    |

| ----------------------- | ------------------------ |

| Faster      		  | slower      	     |

| Default for collections | Default for `@ManyToOne` |

| Better performance      | Risk of heavy query      |

| Data loaded on demand   | Data loaded immediately  |



Note : LAZY Can cause N+1 problem if misused



**🧠 EntityGraph — Modern N+1 Solution**
EntityGraph allows you to control fetching per query instead of at entity level.



@EntityGraph(attributePaths = {"employees"})

@Query("SELECT d FROM Department d")

List<Department> findAllWithEmployees();



🧪 Common Interview Questions



Q: Why not always use EAGER?

➡ Because it loads unnecessary data and hurts performance.



Q: Best practice?

➡ Use LAZY by default and override with JOIN FETCH or EntityGraph when needed.



Q: Difference between JOIN FETCH and EntityGraph?

| JOIN FETCH         | EntityGraph         |

| ------------------ | ------------------- |

| Hardcoded in query | Flexible per method |

| Less reusable      | Cleaner \& reusable  |





# **🌱 What is Spring Data JPA?**



Spring Data JPA is a **Spring framework module** that simplifies database access using JPA by reducing boilerplate code and providing ready-made CRUD operations.



Architecture:-



Controller

   ↓

Service

   ↓

Repository  →  Spring Data JPA  →  JPA  →  Hibernate  →  Database



**Spring Data JPA sits on top of JPA and internally uses Hibernate as the JPA provider.**





🧠 Important Interview Points:-



1️⃣ What problem does Spring Data JPA solve?

* Eliminates boilerplate DAO code
* No need to write SQL for common operations
* Automatic query generation



2️⃣ How does it create queries automatically?

By method name:

List<User> findByName(String name);

User findByEmailAndPassword(String email, String password);

Spring reads method name → builds JPQL → executes query.



3️⃣ Difference: JPA vs Hibernate vs Spring Data JPA

| Layer           | Role                       |

| --------------- | -------------------------- |

| JPA             | Specification              |

| Hibernate       | Implementation of JPA      |

| Spring Data JPA | Abstraction layer over JPA |



5️⃣ Is Spring Data JPA ORM?

❌ No

Hibernate is ORM

Spring Data JPA is a data access framework



💬 Typical Interview Questions



Q: Why use Spring Data JPA instead of JDBC?

A: Less code, better abstraction, automatic queries, transaction management, easier maintenance.



Q: How does Spring Data JPA work internally?

A: It creates runtime proxy implementations of repository interfaces and converts method names into JPQL queries.



Q: Can we write custom queries?

A: Yes, using @Query.





###### **🧩 What is JPA?**

JPA (Java Persistence API) is a specification that defines how Java objects are mapped to database tables and how data is persisted.



Important:

👉 JPA is not an implementation

👉 Hibernate is the most popular implementation of JPA



🏗️ What JPA Provides

1. Mapping Java classes to DB tables (@Entity)
2. Mapping fields to columns (@Column)
3. Managing object lifecycle
4. Handling transactions
5. Providing query mechanism (JPQL)



How JPA Works:-

Java Entity  ⇄  JPA  ⇄  Hibernate  ⇄  Database



You code against JPA interfaces, Hibernate does the real work.



🧪 Example



@Entity

public class Employee {

    @Id

    @GeneratedValue

    private Long id;

    private String name;

}



This mapping is JPA, execution is done by Hibernate.



Q: Is JPA an ORM?

A: JPA is a specification for ORM. Hibernate is the ORM implementation.





###### **🧮 What is JPQL?**

JPQL (Java Persistence Query Language) is a query language used to write queries on JPA entities, not on database tables.



**🧠 Why JPQL?**

Because your code should be database independent.

JPQL lets JPA generate the proper SQL for MySQL, PostgreSQL, etc.



Key Difference: JPQL vs SQL

| SQL               | JPQL                     |

| ----------------- | ------------------------ |

| Works on tables   | Works on \*\*entities\*\*    |

| Uses column names | Uses \*\*entity fields\*\*   |

| DB specific       | \*\*Database independent\*\* |



Q: Is JPQL compiled?

A: Yes, it is parsed and converted to SQL by the JPA provider (Hibernate).







##### **🏢 Real-Life Example: Restaurant System**



Imagine you are building a restaurant app.



👨‍🍳 You = Application Developer

🍽️ Database = Kitchen

🧾 Order Slip = Java Entity

🧑‍🍳 Cooking Rules = JPA

👨‍🔧 Cook = Hibernate

🤵 Waiter = Spring Data JPA



JPA = Cooking Rules Book (Specification): JPA itself does nothing. It’s just a rule book.

JPA only says: “This is how data should be saved, fetched, updated, deleted.”



Hibernate = The Actual Cook (Implementation):

So:

JPA tells what should be done

Hibernate does the actual work



Spring Data JPA = Smart Waiter

Spring Data JPA comes and says: “Bro, just tell me what you want.”

Normally, without Spring Data JPA, you would do this:

*EntityManager em = ...*

*em.persist(obj);*

*em.createQuery("...");*



With Spring Data JPA:

*interface UserRepository extends JpaRepository<User, Long> { }*



##### <b>Pagination:</b>



<b>1) Core Concepts - </b>

Pageable: Encapsulates page, size, and sort.

Page: A paged result that includes content and metadata (total pages, total elements, etc.).

Slice: Like Page but doesn’t compute total count (faster; good for “Load more” UX).



Default indexing: page is 0-based. So page 0 is the first page.





@RestController

@RequestMapping("/api/books")

public class BookController {

&nbsp;   private final BookRepository repo;



&nbsp;   public BookController(BookRepository repo) { this.repo = repo; }



&nbsp;   // Approach A: Explicit params

&nbsp;   @GetMapping

&nbsp;   public Page<Book> list(

&nbsp;           @RequestParam(defaultValue = "0") int page,

&nbsp;           @RequestParam(defaultValue = "10") int size,

&nbsp;           @RequestParam(defaultValue = "id") String sortBy,

&nbsp;           @RequestParam(defaultValue = "asc") String direction) {



&nbsp;       return service.getBooks(page, size, sortBy, direction);

&nbsp;   }



&nbsp;   // Approach B: Inject pageable automatically (?page=0\&size=10\&sort=title,desc)

&nbsp;   @GetMapping("/search")

&nbsp;   public Page<Book> byAuthor(@RequestParam String author,

&nbsp;                              @PageableDefault(size = 5, sort = "title") Pageable pageable) {

&nbsp;       return repo.findByAuthor(author, pageable);

&nbsp;   }

}





@Service

public class BookService {

&nbsp;   private final BookRepository repo;



&nbsp;   public BookService(BookRepository repo) { this.repo = repo; }



&nbsp;   public Page<Book> getBooks(int page, int size, String sortBy, String direction) {

&nbsp;       Sort sort = Sort.by(Sort.Direction.fromString(direction), sortBy);

&nbsp;       Pageable pageable = PageRequest.of(page, size, sort);

&nbsp;       return repo.findAll(pageable);

&nbsp;   }



&nbsp;   public Page<Book> getBooksByAuthor(String author, Pageable pageable) {

&nbsp;       return repo.findByAuthor(author, pageable);

&nbsp;   }

}





public interface BookRepository extends JpaRepository<Book, Long> {

&nbsp;   Page<Book> findByAuthor(String author, Pageable pageable);

}





