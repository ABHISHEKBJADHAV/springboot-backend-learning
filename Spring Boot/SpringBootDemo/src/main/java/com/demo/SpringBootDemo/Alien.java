package com.demo.SpringBootDemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class Alien {

    @Autowired
//    @Qualifier("laptop")
    private Computer com;

    public void code() {
        System.out.println("coding...");
        com.compile();
    }
}
