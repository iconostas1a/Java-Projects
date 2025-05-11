package com.lab1.application.entity.transactions;

import com.lab1.application.entity.model.TransactionType;

import java.time.LocalDateTime;
import java.util.UUID;

public abstract class BaseTransaction implements ITransaction {

    protected final UUID id;
    protected final UUID userId;
    protected final TransactionType type;
    protected final int amount;
    protected final LocalDateTime timestamp;

    public BaseTransaction(UUID userId, TransactionType type, int amount) {
        this.id = UUID.randomUUID();
        this.userId = userId;
        this.type = type;
        this.amount = amount;
        this.timestamp = LocalDateTime.now();
    }

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public UUID getUserId() {
        return userId;
    }

    @Override
    public TransactionType getType() {
        return type;
    }

    @Override
    public int getAmount() {
        return amount;
    }

    @Override
    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
