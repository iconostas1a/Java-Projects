package com.lab1.application.entity.console;

import com.lab1.application.entity.User;

import java.util.Scanner;

public class TransactionChoice {
    private static Scanner scanner = new Scanner(System.in);

    public static void run(User userId) {
        System.out.println("Choose action:");
        System.out.println("1 - deposit");
        System.out.println("2 - withdraw");
        System.out.println("3 - view transaction history");
        int action = scanner.nextInt();

        switch(action) {
            case 1:
                System.out.println("Enter deposit amount:");
                String amount = scanner.nextLine();
        }
    }
}
