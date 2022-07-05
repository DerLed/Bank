package ru.lebedev.bank.utills;

import ru.lebedev.bank.domain.account.Account;

public class AccountNumberGenerator {

    public static String genNumberAccount(Account account){
        String phoneNumber = account.getClient().getPhoneNumber();
        return String.valueOf(account.getDateOpened().hashCode())
                .substring(0, 6)
                .concat(phoneNumber.substring(phoneNumber.length()-6)
                );
    }
}
