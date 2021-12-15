package pl.put.poznan.sort.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(scanBasePackages = {"pl.put.poznan.sort.rest"})
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
