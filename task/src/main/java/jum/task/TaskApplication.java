package jum.task;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootApplication
//@ComponentScan(basePackageClasses = {"jum.task"})

public class TaskApplication {
    public static void main(String[] args) {
        System.out.println("this is a task test"+ new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        SpringApplication.run(TaskApplication.class, args);

    }

}
