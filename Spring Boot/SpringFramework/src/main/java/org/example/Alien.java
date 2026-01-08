package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class Alien {

    Computer com;
    public void code() {
        System.out.println("coding...");
        com.compile();
    }

    public Computer getCom() {
        return com;
    }

    @Autowired
//    @Qualifier("laptop")
    @Qualifier("beast")
    public void setCom(Computer com) {
        this.com = com;
    }
}
