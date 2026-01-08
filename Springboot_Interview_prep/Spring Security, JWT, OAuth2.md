Out of all REST methods GET is safest method because you are changing data on server. So by default spring enables CSRF on other methods but not on GET.

In Security config below thing is main - 
   @Bean

&nbsp;   public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

&nbsp;       httpSecurity.csrf(customizer -> customizer.disable())

&nbsp;           .authorizeHttpRequests(request->request.

&nbsp;                   requestMatchers("/auth/\*\*").permitAll().

&nbsp;                   anyRequest().authenticated())

&nbsp;           .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

&nbsp;           .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

&nbsp;       return httpSecurity.build();

&nbsp;   }
flow:
			So each and every request goes filter chain

&nbsp;						↓

&nbsp;			jwtFilter class activates and do filter for request.

&nbsp;						↓

&nbsp;		-----------------------------------------------------------------

&nbsp;		↓								↓

auth/login or auth/register						Any other request

&nbsp;		↓								↓					

JwtFilter will get called but					JwtFilter will get called and will check headers if everything is fine it	

will do nothing. No Security context.				will validate token if token is valid it will set context again call will come to

Again call will come to securityFilterChain			securityFilterChain it will now check if context with authrozed is there or not

but there logic is for request with /auth/\*\* it			if context is there it will pass.

wont check context and authorization. So it will just

pass it.



SecurityContextHolder.getContext().getAuthentication():-

SecurityContext - 🧾 The ID card of the current request.

If it already contains authentication, Spring considers the user already logged in.



1\. What/Why Spring Security?

Spring Security is a framework that provides authentication, authorization, and protection against common security vulnerabilities like CSRF, session fixation, and XSS.

It works by intercepting requests using a chain of security filters before they reach the DispatcherServlet.



2\. Core Architecture (Very Important Question) - Request Flow

Client

&nbsp; ↓

Security Filter Chain  ← Spring Security

&nbsp; ↓

DispatcherServlet

&nbsp; ↓

Controller



Note: Spring Security works at the filter level before Spring MVC.



3\. What is SecurityFilterChain?

curityFilterChain is an ordered collection of servlet filters where each filter performs a specific security responsibility such as authentication, authorization, exception handling, CSRF protection, etc.

Common filters you must know:

* UsernamePasswordAuthenticationFilter
* OncePerRequestFilter (your JWT filter extends this)
* AuthorizationFilter
* ExceptionTranslationFilter



4\. Authentication vs Authorization

| Authentication          | Authorization                   |

| ----------------------- | ------------------------------- |

| Who are you?            | What are you allowed to access? |

| Login                   | Role checking                   |

| Verified by credentials | Verified by roles/authorities   |



Golden line:

Authentication happens first, authorization happens after.



5\. Internal Login Flow (Interview Favorite)



POST /login

&nbsp;  ↓

UsernamePasswordAuthenticationFilter

&nbsp;  ↓

AuthenticationManager

&nbsp;  ↓

AuthenticationProvider

&nbsp;  ↓

UserDetailsService → Database

&nbsp;  ↓

PasswordEncoder.matches()

&nbsp;  ↓

Authentication Success

&nbsp;  ↓

SecurityContext updated



6\. Core Components You MUST Know

| Component              | Role                              				|

| ---------------------- | -------------------------------------------------------------|

| AuthenticationManager  | Orchestrates authentication       				|

| AuthenticationProvider | Performs actual validation        				|

| UserDetailsService     | Loads user from DB                				|

| PasswordEncoder        | Encrypts \& verifies passwords eg. BCryptPasswordEncoder      |

| SecurityContext        | Stores authenticated user         				|

| SecurityContextHolder  | Holds SecurityContext per request 				|



7\. JWT Based Security (Your Project Core Topic 🔥)

Login Flow:

Client → /auth/login → Auth Service

Credentials verified

JWT generated and returned



Subsequent Requests e.g. /home, /quizz/get/1

Client → sends JWT in Authorization header

JWT Filter → validates token

Username extracted

User loaded

Authentication set in SecurityContext

Controller accessed



Why JWT?

* Stateless
* Scalable
* Microservice friendly



9\. CSRF (They will ask)

CSRF is enabled by default in Spring Security for browser-based session apps.

For stateless JWT APIs, CSRF is disabled.







#### **🧾JWT Concept:** 

JWT is a digitally signed token that proves the identity of a user for a limited time.

JWT = User data + Expiry time + Digital Signature



1️⃣ Encryption \& Decryption (Core Idea): 

Person A wants to send message: "My password is 1234"

If A sends it directly → hacker can read it. So Encrypt it

* Encryption → converting readable data into unreadable form
* Decryption → converting it back to original data



2️⃣ Symmetric Key Encryption (One Key System)

Rule:

Same key is used for encryption and decryption

Example:

Person A \& Person B both share SecretKey = secretkey123

Problem:

If hacker gets it → game over.

Also how many users secret key you will keep with you impossible.

Used in: Actual data transfer after connection is established (HTTPS session, DB encryption, etc.)



3️⃣ Asymmetric Key Encryption (Two Key System – RSA)

Rule:

Two keys:

Public Key → everyone has

Private Key → only owner has

Example:

B generates: PublicKey\_B, PrivateKey\_B

B gives PublicKey\_B to A.

A encrypts message using PublicKey\_B

A sends encrypted data

Only B can decrypt using PrivateKey\_B



4️⃣ Digital Signature (Identity Proof)

Now B wants to reply and prove it’s really him.

B signs message using PrivateKey\_B

A verifies signature using PublicKey\_B

If verification passes →

✔️ Message is from B

✔️ Message was not changed



###### **🧑‍🤝‍🧑 Secure Message Flow - Person A → Person B**

Sender encrypts data using receiver’s public key and signs it using sender’s private key.

Receiver verifies the signature using sender’s public key and decrypts the data using receiver’s private key.



###### **JWT Structure:**

Header.Payload.Signature



1️⃣ Header

Tells:

which algorithm is used (HS256, RS256, etc.)



2️⃣ Payload

Contains:

user information (username, roles, etc.)

expiry time (exp)

issued time (iat)



3️⃣ Signature

Created using: Either a secret key (HMAC / symmetric) or private key (RSA / asymmetric)

This signature ensures:

✔️ token is not modified

✔️ token was issued by the trusted server





#### **OAUTH 2.0:**



OAuth2 is an **authorization** framework. OAuth2 is **NOT authentication**.

It answers: “What is this app allowed to access on behalf of the user?”

Authentication = who you are

Authorization = what you are allowed to do



🎯 Why OAuth2 exists

Imagine this problem:

You want to allow Swiggy to access your Google profile photo

but you don’t want to give Swiggy your Google password.

OAuth2 solves this.



🔄 OAuth2 Flow (Most important — Authorization Code Flow) - Let’s do a story.



🧍 You (User) → Swiggy App → Google



1️⃣ Swiggy redirects you to Google login

2️⃣ You login to Google

3️⃣ Google asks:



"Allow Swiggy to access your profile?"



4️⃣ You approve

5️⃣ Google sends authorization code to Swiggy

6️⃣ Swiggy exchanges code for access token

7️⃣ Swiggy uses access token to call Google APIs



At no point does Swiggy know your Google password but Swiggy still got limited access only to the data you approved.

