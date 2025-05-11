package com.lab1.application.entity.transactions;

import com.lab1.application.entity.model.TransactionType;

import java.util.UUID;

public class DepositTransaction extends BaseTransaction {

    public DepositTransaction(UUID userId, int amount) {
        super(userId, TransactionType.DEPOSIT, amount);
    }

}
