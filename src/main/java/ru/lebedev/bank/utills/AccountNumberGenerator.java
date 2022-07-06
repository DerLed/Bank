package ru.lebedev.bank.utills;

import ru.lebedev.bank.domain.account.Account;

public class AccountNumberGenerator {

    public static String genNumberAccount(Account account){
        String phoneNumber = account.getClient().getPhoneNumber();
        return String.valueOf(42).concat(String.valueOf(account.getDateOpened().hashCode())
                .substring(1, 6))
                .concat(phoneNumber.substring(phoneNumber.length()-6)
                );
    }
}
