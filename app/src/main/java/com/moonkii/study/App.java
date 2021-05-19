package com.moonkii.study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App {
    public String getGreeting() {
        return "Hello World!";
    }

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);

//        try {
//            InetSocketAddress address = new InetSocketAddress(8000);
//            HttpServer httpServer = HttpServer.create(address, 0);
//            HttpHandler handler = new DemoHttpHandler();
//            httpServer.createContext("/", handler);
//            httpServer.start();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}
