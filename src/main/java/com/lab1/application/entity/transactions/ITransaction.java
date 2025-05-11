package com.lab1.application.entity.transactions;

import com.lab1.application.entity.model.TransactionType;

import java.time.LocalDateTime;
import java.util.UUID;

public interface ITransaction {
    UUID getId();

    UUID getUserId();

    TransactionType getType();

    int getAmount();

    LocalDateTime getTimestamp();

}
