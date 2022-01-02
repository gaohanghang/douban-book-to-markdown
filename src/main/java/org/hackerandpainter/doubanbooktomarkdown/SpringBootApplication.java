package org.hackerandpainter.doubanbooktomarkdown;

import org.springframework.boot.SpringApplication;

@org.springframework.boot.autoconfigure.SpringBootApplication
public class SpringBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootApplication.class, args);
        System.out.println("http://localhost:8080/swagger-ui/index.html");
    }

}
