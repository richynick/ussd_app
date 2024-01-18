package com.richard.ussd_app.dao;

import com.richard.ussd_app.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AccountDAO extends JpaRepository<Account, Long> {

    @Query(value = "select a.accountBalance from Account a where a.accountNumber =?1")
    Account getAccountBalance(String accountNumber);
}
