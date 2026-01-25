##### **SOLID Principles**



S – Single Responsibility Principle

* A class should have only one reason to change
* ❌ God classes
* ✅ One class = one responsibility



O – Open/Closed Principle

* Open for extension, closed for modification
* Achieved using interfaces + polymorphism



L – Liskov Substitution Principle

* Child class should not break parent behavior
* If Dog extends Animal, it must behave like an Animal



I – Interface Segregation Principle

* Don’t force classes to implement methods they don’t need
* Prefer multiple small interfaces instead of putting all methods in one interface and forcing class to implement all



D – Dependency Inversion Principle

* Depend on abstractions, not concrete classes
* This is why we use interfaces + Spring DI





Spring Boot Mapping of SOLID principles:



| SOLID | Spring Example                         |

| ----- | -------------------------------------- |

| SRP   | Service, Repository, Controller layers |

| OCP   | Adding new payment bean                |

| LSP   | Interface-based injection              |

| ISP   | JpaRepository small interfaces         |

| DIP   | `@Autowired` / constructor injection   |







##### **What is System Design** (interview-friendly definition)



System design is about planning how a software system is structured so that it is:

* Scalable  - (ASG)
* Reliable - (Secured)
* Available - (AWS AZ + Load balancer)
* Maintainable
* Performant





What does trade-off mean in system design?

A trade-off means:

You gain something, but you also give something up.

There is no perfect design, only choices based on priorities.



On-premise vs Cloud

“We trade full infrastructure control and lower long-term cost of on-premise for the scalability, faster setup, and managed services provided by cloud.”



1️⃣ Monolith vs Microservices

Trade-off:

* Monolith → simpler, faster to build
* Microservices → scalable, flexible, but complex

“We trade simplicity of a monolith for the scalability and independent deployment offered by microservices.”



2️⃣ Relational DB (Postgres) vs NoSQL

* Relational DB → strong consistency, complex queries
* NoSQL → high scalability, weaker consistency

“We trade horizontal scalability of NoSQL for the strong consistency and relational queries of PostgreSQL.”

