package com.github.tangmonkmeat;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author zwl
 */
@SpringBootApplication
@MapperScan(basePackages = "com.github.tangmonkmeat.mapper")
public class LibraryseatMbgApplication {

    public static void main(String[] args) {
        SpringApplication.run(LibraryseatMbgApplication.class, args);
    }

}
