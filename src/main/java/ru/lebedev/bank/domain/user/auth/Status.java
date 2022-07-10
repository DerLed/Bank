package ru.lebedev.bank.domain.user.auth;

import lombok.ToString;

@ToString
public enum Status {
    ACTIVE, BANNED, DELETED
}