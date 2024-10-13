package com.utpconnectplatform.users_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class UsersServiceApplication {

	public static void main(String[] args) {
		Scanner scan= new Scanner(System.in);
		SpringApplication.run(UsersServiceApplication.class, args);
		System.out.print("Hola, quien sigue a quién ??");
		System.out.println("ESCRIBE EL NOMBRE DEL SEGUIDOR");
		String follower= scan.next();
		System.out.println("ESCRIBE EL NOMBRE DEL SEGUIDO");
		String followed= scan.next();



	}

}
