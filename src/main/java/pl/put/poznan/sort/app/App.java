package pl.put.poznan.sort.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main project class that starts the application server
 */
@SpringBootApplication(scanBasePackages = {"pl.put.poznan.sort.rest"})
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
