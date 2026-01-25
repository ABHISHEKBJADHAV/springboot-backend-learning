| UI Module                  | Spring Boot Service            |

| -------------------------- | ------------------------------ |

| Device Management          | Device Service                 |

| Vehicle Management         | Vehicle Service                |

| Driver Management          | Driver Service                 |

| Remote Software Management | OTA / Campaign Service         |

| Analytics                  | Analytics Service              |

| Identity \& Governance      | User \& Role Management Service |

| Data Science               | ML / Insights Service          |

| Data Logger                | Telemetry / Logging Service    |





Shared Angular Libraries

api-services-lib

* Contains only backend API calls
* One service file per backend microservice

common-lib

* Reusable UI components
* Shared models, pipes, directives
* Common layouts, tables, charts





Multi-Client \& Multi-Tenant Design (Key Differentiator ⭐)

This is NOT a client-specific project.



How the Platform Works

Company builds one complete Data Platform

Platform is demoed to multiple clients

Each client may select only required modules

Example:

* Client A uses 4/10 features
* Client B uses Analytics + OTA only



A separate project/team:

* Reuses existing modules
* Does minimal customization if needed





Dashboard Customization (Very Interview-Friendly)

Multiple Dashboard Types:

* Fleet Dashboard
* Campaign Dashboard

How It Works:

During user registration User is associated with a default dashboard.

On login: That dashboard loads automatically

Later: User can change dashboard preference via User Management Service





Configuration-Driven Platform (Very Strong Point)

☁️ Cloud Agnostic

Cloud-specific configs are externalized

Supports:

* AWS
* Azure

Switching cloud does not impact core logic



🌐 Multilingual Support

Language configs maintained externally

No hardcoded text

New language can be added without code changes



🎨 Theme Customization (Themification)

No hardcoded colors

Theme variables stored in config

Each customer can have:

* Custom colors
* Custom branding
* Applied dynamically at runtime



We used a lower Spring Boot version for the API Gateway because, the stable Spring Cloud release at that time did not fully support the newer Spring Boot version.



Spring Boot 3 migration issues

Spring Boot 3 introduced:

* Jakarta namespace (javax → jakarta)
* Removed deprecated APIs
* Changed security defaults	



Spring Cloud Gateway had delayed full support, so:

* Filters
* Security config
* Netty dependencies

⚠️ Broke easily on newer versions





#### **End-to-End Flow** (Simple Explanation):



User logs in

 	↓

User’s role, dashboard type, theme, language are fetched

 	↓

Angular loads only enabled modules

 	↓

API calls routed to respective Spring Boot microservices

 	↓

Data stored in service-specific databases

 	↓

Analytics \& reports generated dynamically

 	↓

Platform behaves differently for different clients — same codebase









If project is big and contains multiple services with heavy codebase. Then,

You can (and often should) create a separate JAR that contains utility, helper, and shared code, and then reuse it across services.

Example:

common-utils

shared-lib

Note: That utility should be independent, Stateless, Network-free, Environment-agnostic. So that it should fail and can be used anywhere.



Typical structure:

Create a separate Maven/Gradle project



common-utils

&nbsp;└── src/main/java

&nbsp;    ├── util

&nbsp;    │   ├── DateUtils.java

&nbsp;    │   ├── StringUtils.java

&nbsp;    ├── security

&nbsp;    │   ├── JwtUtils.java

&nbsp;    ├── exception

&nbsp;    │   ├── BusinessException.java

&nbsp;    ├── constants

&nbsp;    │   ├── ErrorCodes.java

&nbsp;    └── dto

&nbsp;        ├── CommonResponse.java



Add it in POM - 

<dependency>

&nbsp;   <groupId>com.yourcompany.platform</groupId>

&nbsp;   <artifactId>common-utils</artifactId>

&nbsp;   <version>1.0.0</version>

</dependency>





What SHOULD go into this shared JAR ✅



✔ Pure utility classes

* Date/time helpers
* String/collection utilities
* Validation helpers



✔ Common constants

* Error codes
* Common headers
* Regex patterns



✔ Shared DTOs

* Request/response wrappers
* Pagination objects



✔ Common exceptions

* BusinessException
* ValidationException



✔ Security helpers

* JWT parsing
* Token validation logic



✔ Common enums

* Status enums
* Role enums



