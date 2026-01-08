package com.demo.SpringBootDemo;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@Lazy
public class CPU {
    public void process()
    {
        System.out.println("Processing...");
    }
}
