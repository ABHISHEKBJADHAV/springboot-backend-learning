Core Java 8 Concepts:

* Lambda Expressions: BiFunction<Integer, Integer, Integer> add = (a, b) -> a + b;
* Functional Interfaces
* Stream API
* Method References
* Optional - To avoid NullPointerException and make null handling explicit.
* Default \& Static Methods in Interface
* Date \& Time API
* Collectors \& Parallel Streams





| Question                               | Expected Answer             |

| -------------------------------------- | --------------------------- |

| Why lambda needs functional interface? | To know method signature    |

| Why streams are lazy?                  | Performance \& optimization  |

| Can streams be reused?                 | ❌ No                       |

| Why Optional not used in fields?       | Serialization \& misuse risk |

| Why parallel stream is dangerous?      | Thread safety \& overhead    |





What is :: in Java?

:: is called a Method Reference.

It is a shortcut for lambda expression.



Date \& Time API

❌ Old Date problems:

* Mutable
* Not thread-safe
* Confusing

✅ Java 8 Solution:

LocalDate date = LocalDate.now();

LocalTime time = LocalTime.now();

LocalDateTime dt = LocalDateTime.now();

Useful Classes

* LocalDate
* LocalTime
* LocalDateTime
* ZonedDateTime
* Duration, Period





Importatnt stream api methods -

.mapToObj(c -> (char)c)

.mapToInt(Integer::intValue)

.collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()))

.filter(n -> Collections.frequency(list, n) > 1)

.sorted(Comparator.comparing(Employee::getSalary))

Map<String, Long> count = Arrays.stream(str.split(" ")).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));





**Collectors:**

Used with Stream API to transform stream into result



Count elements: long count = list.stream().collect(Collectors.counting());

Grouping: Map<String, List<Employee>> map = employees.stream().collect(Collectors.groupingBy(Employee::getDept));

Partitioning: Map<Boolean, List<Integer>> map = list.stream().collect(Collectors.partitioningBy(n -> n > 50));

Joining: String result = list.stream().collect(Collectors.joining(","));





| Method Reference     | Equivalent Lambda             |

| -------------------- | ----------------------------- |

| `LinkedHashMap::new` | `() -> new LinkedHashMap<>()` |



| Type                    | Example               |

| ----------------------- | --------------------- |

| Static method           | `ClassName::method`   |

| Instance method         | `obj::method`         |

| Arbitrary object method | `String::toUpperCase` |

| Constructor             | `ClassName::new`      |





Java 8 Interview Questions

Conceptual Questions

1. What is Java 8 and why is it important?
2. What is a Functional Interface?
3. What is a Lambda Expression?
4. Difference between Collection and Stream?
5. What is lazy evaluation in Stream API?
6. Difference between map() and flatMap()?
7. What are intermediate and terminal operations?
8. What is Optional and why introduced?
9. Can lambda access local variables?
10. What is method reference?
11. What is default method? Why introduced?
12. Difference between forEach() and peek()?
13. What is parallel stream? When should we avoid it?
14. Difference between Predicate, Function, Consumer, Supplier?
15. How does Java 8 improve multithreading?





Java 8 Interview Coding Questions?

1. Find first non-repeating character in a string
2. Find first repeating character in a string
3. Find character with maximum occurrence
4. Count frequency of each character in string
5. Remove duplicate characters from string
6. Reverse a string using streams
7. Check if string is palindrome using streams
8. Find duplicate elements in a list
9. Find second highest number in lit
10. Find nth highest number
11. Sort list of integers
12. Sort list of strings by length
13. Sort employees by salary
14. Group employees by department
15. Find highest paid employee
16. Count employees in each department
17. Find average salary of each department
18. Sum of even numbers
19. Separate even and odd numbers
20. Find numbers starting with 1
21. Convert list of strings to uppercase
22. Join strings with comma
23. Count total elements in stream
24. Find minimum and maximum number
25. Merge two lists and remove duplicates
26. Find common elements between two lists
27. Find missing number in array
28. Find longest string in list
29. Check if any number is divisible by 5
30. Check if all numbers are positive
31. Find top 3 maximum numbers
32. Convert map to list
33. Sort map by values
34. Find word frequency from sentence
35. Find duplicate words in a sentence
36. Flatten list of lists
37. Group numbers by even \& odd
38. Partition numbers > 50 and ≤ 50
39. Convert int\[] to List<Integer>
40. Generate infinite stream and limit



1️⃣ Find first non-repeating character:

String s = "swiss";



Character result =

s.chars().mapToObj(c -> (char)c)

&nbsp;.collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()))

&nbsp;.entrySet().stream()

&nbsp;.filter(e -> e.getValue() == 1)

&nbsp;.findFirst().get().getKey();





https://leetcode.com/discuss/post/3887612/50-java-interview-questions-for-programm-c8ci/

