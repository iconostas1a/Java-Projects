package com.lab1.application.entity;

import com.lab1.application.entity.transactions.BaseTransaction;
import com.lab1.application.entity.transactions.DepositTransaction;
import com.lab1.application.entity.transactions.WithdrawTransaction;
import com.lab1.exceptions.InvalidDepositException;
import com.lab1.exceptions.InvalidWithdrawException;
import com.lab1.exceptions.NoTransactionHistoryException;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class User {
    @Getter
    private final UUID id = UUID.randomUUID();

    @Getter
    @NonNull
    private final String login;
    @Getter
    @NonNull
    private final String password;
    @Getter
    private int balance = 0;
    private final List<BaseTransaction> transactionHistory = new ArrayList<>();

    public void deposit(int amount) throws InvalidDepositException{
        if (amount <= 0) {
            throw new InvalidDepositException("Amount must be greater than 0");
        } else {
            balance += amount;
            transactionHistory.add(new DepositTransaction(id, amount));
        }
    }

    public void withdraw(int amount) throws InvalidWithdrawException {
        if (amount <= 0) {
            throw new InvalidWithdrawException("Amount must be greater than 0");
        }
        if (balance < amount) {
            throw new InvalidWithdrawException("Negative balance");
        } else {
            balance -= amount;
            transactionHistory.add(new WithdrawTransaction(id, amount));
        }
    }

    public void printTransactionHistory() throws NoTransactionHistoryException {
        if (transactionHistory.isEmpty()) {
            throw new IllegalStateException("Транзакции ещё не производились");
        } else {
            for (BaseTransaction transaction : transactionHistory) {
                System.out.println("User: " + transaction.getUserId());
                System.out.println("Transaction type: " + transaction.getType());
                System.out.println("Amount of money transferred: " + transaction.getAmount());
                System.out.println("-------------");
            }
        }
    }

}
