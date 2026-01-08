package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component("beast")
@Lazy
public class Laptop implements Computer{
    CPU cpu;
    CPU cpu1;

    public CPU getCpu() {
        return cpu;
    }

    public Laptop() {
        System.out.println("Laptop Created...");
    }

    public Laptop(CPU cpu, CPU cpu1) {
        System.out.println("Laptop Created...");
    }

    public CPU getCpu1() {
        return cpu1;
    }


    public void setCpu1(CPU cpu1) {
        this.cpu1 = cpu1;
    }

    @Autowired
    public void setCpu(CPU cpu) {
        this.cpu = cpu;
    }

    public void compile() {
        System.out.println("Laptop Compiling...");
        cpu.process();
    }
}
