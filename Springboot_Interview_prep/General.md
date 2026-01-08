🔥 Latest Spring Boot Version (Production)

📌 Spring Boot 4.0.1 — the newest stable release in the 4.x line.



🌱 Corresponding Spring Framework:

Spring Boot 4 runs on Spring Framework 7.x

Spring Boot 4.x  → Spring Framework 7.x



☕ Java Version Support

Spring Boot 4 has these Java requirements/compatibility:

✅ Minimum Java supported: Java 17

(The lowest you can run Spring Boot 4 on)

✅ Max / Recommended modern support: Java 25

(Long-Term Support (LTS) release from September 2025)



In general for Entity We keep id as primary key even if name is unique because primary keys and business keys serve different purposes.

Primary keys are used in:

1. Indexes
2. Foreign keys
3. Joins



User roles in -

"Roles must be eagerly fetched because Spring Security needs authorities during authentication and lazy loading causes session issues."

