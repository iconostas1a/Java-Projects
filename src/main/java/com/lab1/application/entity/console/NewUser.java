package com.lab1.application.entity.console;

import com.lab1.application.entity.User;
import com.lab1.exceptions.InvalidDepositException;
import com.lab1.exceptions.InvalidWithdrawException;
import com.lab1.exceptions.NoTransactionHistoryException;

import java.util.Scanner;

public class NewUser {
    private static final Scanner scanner = new Scanner(System.in);

    public static void run() {
        System.out.println("Create login: "); // TODO проверка что логин не занят
        String login = scanner.nextLine();
        System.out.println("Create password: ");
        String password = scanner.nextLine();

        User newUser = new User(login, password);
        System.out.println("Your id: " + newUser.getId());

        while (true) {
            System.out.println("Choose action:");
            System.out.println("1 - deposit");
            System.out.println("2 - withdraw");
            System.out.println("3 - view transaction history");
            System.out.println("4 - exit");

            if (!scanner.hasNextInt()) {
                System.out.println("Invalid input");
                scanner.next();
                continue;
            }

            int action = scanner.nextInt();

            switch (action) {
                case 1:
                    System.out.println("Enter deposit amount:");
                    int amount = scanner.nextInt();
                    try {
                        newUser.deposit(amount);
                    } catch (InvalidDepositException e) {
                        System.out.println(e.getMessage());
                    }
                    System.out.println("Your balance: " + newUser.getBalance());
                    break;
                case 2:
                    System.out.println("Enter withdraw amount:");
                    int amount2 = scanner.nextInt();
                    try {
                        newUser.withdraw(amount2);
                    } catch (InvalidWithdrawException e) {
                        System.out.println(e.getMessage());
                    }
                    System.out.println("Your balance: " + newUser.getBalance());
                    break;
                case 3:
                    System.out.println("Transaction history:");
                    try {
                        newUser.printTransactionHistory();
                    } catch (NoTransactionHistoryException e) {
                        System.out.println("Ошибка: " + e.getMessage());
                    }
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid input");
            }
        }
    }
}
