package org.example.config;

import org.example.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.*;

@ComponentScan("org.example")
public class AppConfig {

//    @Bean
//    public Alien alien(Computer com) //@Qualifier("laptop") - explicitly takes laptop object
//    {
//        Alien alien = new Alien();
//        alien.setCom(com);
//        return alien;
//    }
//
//    @Bean
//    @Primary
//    public Desktop desktop(CPU cpu)
//    {
//        Desktop desk=new Desktop();
//        desk.setCpu(cpu);
//        return desk;
//    }
//
//    @Bean
//    @Lazy
//    public Laptop laptop(CPU cpu)
//    {
//        Laptop laptop=new Laptop();
//        laptop.setCpu(cpu);
//        return laptop;
//    }
//
//    @Bean
//    @Scope("prototype")
//    public CPU cpu()
//    {
//        return new CPU();
//    }

}
