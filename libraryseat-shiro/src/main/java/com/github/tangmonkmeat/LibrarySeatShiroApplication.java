package com.github.tangmonkmeat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author zwl
 */
@SpringBootApplication(scanBasePackages = "com.github.tangmonkmeat")
public class LibrarySeatShiroApplication {

    public static void main(String[] args) {
        SpringApplication.run(LibrarySeatShiroApplication.class, args);
    }

}
