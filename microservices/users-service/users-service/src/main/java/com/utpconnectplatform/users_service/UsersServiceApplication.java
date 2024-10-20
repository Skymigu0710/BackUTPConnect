package com.utpconnectplatform.users_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class UsersServiceApplication {

	public static void main(String[] args) {
		Scanner scan= new Scanner(System.in);
		SpringApplication.run(UsersServiceApplication.class, args);

	}

}
