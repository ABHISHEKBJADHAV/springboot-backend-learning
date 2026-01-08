package org.example;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class CPU {
    public CPU() {
        System.out.println("CPU created...");
    }

    public void process()
    {
        System.out.println("Processing...");
    }
}
