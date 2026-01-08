What is Maven?

Maven is a build automation and dependency management tool for Java projects.

It helps in:

* Managing libraries (JARs) - Centralized dependency management
* Building the project
* Running tests
* Packaging \& deploying applications
* Standard project structure
* Easy integration with CI/CD pipelines



Maven automatically downloads dependencies from - Maven Central Repository(~/.m2/repository) Or your company's internal repository



POM Structure -

<groupId>com.company</groupId>

<artifactId>my-app</artifactId>

<version>1.0.0</version>

<dependencies>...</dependencies>

<build>...</build>



Maven lifecycle-
validate → compile → test → package → verify → install → deploy



🔹 Typical Interview Questions



Q1: What problem does Maven solve?

➡ Dependency management, build automation, standardization.



Q2: What is POM?

➡ Configuration file containing project \& build information.



Q3: Where are dependencies stored?

➡ Local repository: .m2/repository



Q4: What is mvn install?

➡ Builds the project and stores the artifact in local repository.



Q5: What is dependency conflict and how Maven solves it?

➡ Using dependency tree and nearest definition rule.


Advance questions:-	
2. What is Transitive Dependency?

spring-boot-starter-web → spring-web → jackson



3\. What is SNAPSHOT version?

A version under development:1.0.0-SNAPSHOT



6\. What is Parent POM?

A common POM that holds:

dependency versions

plugin versions

common configuration

All child projects inherit it.



10\. What is the Super POM?

Default Maven configuration inherited by every project:

repositories

plugins

lifecycles



Common Maven Errors \& Fixes:-

| Error                       | Reason               | Fix                      |

| --------------------------- | -------------------- | ------------------------ |

| `Cannot resolve dependency` | Network / repo issue | Check internet, proxy    |

| `ClassNotFoundException`    | Missing dependency   | Add dependency           |

| `Version conflict`          | Multiple versions    | Use dependencyManagement |

| `Build failure`             | Plugin misconfig     | Check plugin version     |

| `Corrupted jar`             | Broken download      | Delete `.m2/repository`  |



