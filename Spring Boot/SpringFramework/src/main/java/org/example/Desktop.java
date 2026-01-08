package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
@Lazy
public class Desktop implements Computer{
    CPU cpu;

    public CPU getCpu() {
        return cpu;
    }

    public Desktop() {
        System.out.println("Desktop created...");
    }

    @Autowired
    public void setCpu(CPU cpu) {
        this.cpu = cpu;
    }

    public void compile() {
        System.out.println("Desktop compiling...");
        cpu.process();
    }
}
