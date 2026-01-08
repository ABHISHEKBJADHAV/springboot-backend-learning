package com.demo.SpringBootDemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@Lazy
public class Laptop implements Computer{

    @Autowired
    private CPU cpu;

    @Override
    public void compile() {
        System.out.println("Laptop Compiling...");
        cpu.process();
    }
}
