package com.lab1.application.entity.console;

import java.util.Scanner;

public class ConsoleRunner {
    private final static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Enter your action:");
        System.out.println("1 - create new user");
        System.out.println("2 - exit");
        int action = scanner.nextInt();
        if (action == 1) {
            NewUser.run();
        } else {
            System.out.println("Invalid action");
        }
    }

}
