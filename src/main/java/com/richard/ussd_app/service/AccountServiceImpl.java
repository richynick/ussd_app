package com.richard.ussd_app.service;

import com.richard.ussd_app.dao.AccountDAO;
import com.richard.ussd_app.dto.AccountDTO;
import com.richard.ussd_app.model.Account;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import response.Response;

import javax.management.RuntimeErrorException;
import java.util.Optional;
import java.util.Random;

import static com.richard.ussd_app.utils.Constants.REQUEST_FAILED;
import static com.richard.ussd_app.utils.Constants.REQUEST_SUCCESSFUL;

@AllArgsConstructor
@NoArgsConstructor
@Service
public class AccountServiceImpl implements IAccountService{

    @Autowired
    private AccountDAO accountDAO;
//    @Override
//    public ResponseEntity<Response> createAccount(AccountDTO accountDTO) {
//        try{
//            Account account = new Account();
//            account.setAccountName(accountDTO.getAccountName());
//            account.setAccountNumber(generateAccountNumber());
//            Account newAccount = accountDAO.save(account);
//            return ResponseEntity.ok(new Response(REQUEST_SUCCESSFUL, "Created Successfully", newAccount));
//        }catch(Exception ex){
//            ex.printStackTrace();
//            return ResponseEntity.ok(new Response(REQUEST_FAILED, "Failed to create Account", null));
//        }
//
//    }

    @Override
    public Optional<Account> getAccount(Long id) {
        return accountDAO.findById(id);
    }

    @Override
    public ResponseEntity<Response> deposit(Long id, double amount) {
        Account account = getAccount(id).orElseThrow(() -> new RuntimeException("Account not found"));
        account.setAccountBalance(account.getAccountBalance() + amount);
        Account newAccountBal = accountDAO.save(account);
        return ResponseEntity.ok(new Response(REQUEST_SUCCESSFUL, "Account Balance Updated", newAccountBal));
    }

    @Override
    public ResponseEntity<Response> withdraw(Long id, double amount) {
        Account account = getAccount(id).orElseThrow(() -> new RuntimeException("Account not found"));
        if(account.getAccountBalance() < amount){
            throw new RuntimeException("insufficient funds");
        }
        account.setAccountBalance(account.getAccountBalance() - amount);
        Account newAccountStatus = accountDAO.save(account);
        return ResponseEntity.ok(new Response(REQUEST_SUCCESSFUL, "Transaction Successful", newAccountStatus));
    }

}
