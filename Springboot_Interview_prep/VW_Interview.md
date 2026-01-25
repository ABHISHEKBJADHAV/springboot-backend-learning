1️⃣ Subject vs Behavioural Subject (Observer Pattern – Rx / Event based)



Subject:

* Holds the state
* Maintains a list of observers
* Notifies observers when state changes



Behavioural Subject:

* Emits values + behavior (events)
* Common in RxJava / RxJS (Angular)
* Focuses on how data flows over time



BehaviorSubject<number> count = new BehaviorSubject(0);

count.subscribe(val => console.log(val));

count.next(1);



Key difference (1-liner):

Subject doesn’t need initial value, BehaviourSubject always needs an initial value and emits the last value to new subscribers.





2️⃣ Does BehaviourSubject require initialization?

YES – Mandatory because it always stores the latest value

new BehaviorSubject<User>(null);





3️⃣ How will you manage load in AWS if application stops due to traffic?

Expected answer (Architecture thinking):

Core AWS components:

* Load Balancer (ALB)
* Auto Scaling Group
* Multiple EC2 instances
* CloudWatch alarms





4️⃣ equals() vs == in Java

| Aspect            | `==`             | `equals()`     |

| ----------------- | ---------------- | -------------- |

| Compares          | Memory reference | Object content |

| Used for          | Primitives       | Objects        |

| Can be overridden | ❌                | ✅              |



String a = new String("abc");

String b = new String("abc");



a == b        // false

a.equals(b)  // true









5️⃣ HashCode, Hashing \& HashMap usage

hashCode()

* Integer representation of object
* Used to locate bucket in HashMap



HashMap working:

1. Call hashCode()
2. Find bucket
3. Use equals() to find exact key



Interview trap:

Same hashCode does NOT guarantee equals() true.





6️⃣ Lazy Loading in Angular

* Load modules only when needed
* Improves initial load time





{

  path: 'admin',

  loadChildren: () => import('./admin/admin.module')

    .then(m => m.AdminModule)

}





7️⃣ EC2 vs Lambda

| EC2               | Lambda            |

| ----------------- | ----------------- |

| Server-based      | Serverless        |

| Manual scaling    | Auto scaling      |

| Pay for uptime    | Pay per execution |

| Long running apps | Short tasks       |







8️⃣ Comparable vs Comparator

| Comparable       | Comparator      |

| ---------------- | --------------- |

| `compareTo()`    | `compare()`     |

| Natural ordering | Custom ordering |

| Modifies class   | External logic  |



class Employee implements Comparable<Employee> {

   public int compareTo(Employee e) {

      return this.id - e.id;

   }

}



e.g.

Integer.compareTo()



Interview line:

**Use Comparable for default sorting, Comparator for multiple sorting strategies.**

