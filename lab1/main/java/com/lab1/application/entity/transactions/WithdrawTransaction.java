package com.lab1.application.entity.transactions;

import com.lab1.application.entity.model.TransactionType;

import java.util.UUID;

public class WithdrawTransaction extends BaseTransaction {

    public WithdrawTransaction(UUID accountId, int amount) {
        super(accountId, TransactionType.WITHDRAWAL, amount);
    }

}
