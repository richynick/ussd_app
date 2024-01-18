package com.richard.ussd_app.service;

import com.richard.ussd_app.dao.UserDAO;
import com.richard.ussd_app.dto.UserDTO;
import com.richard.ussd_app.model.Account;
import com.richard.ussd_app.model.User;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import response.Response;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import static com.richard.ussd_app.utils.Constants.REQUEST_FAILED;
import static com.richard.ussd_app.utils.Constants.REQUEST_SUCCESSFUL;


@AllArgsConstructor
@NoArgsConstructor
@Service
public class UserServiceImpl implements IUser{

    @Autowired
    private UserDAO userDAO;
    @Override
    public ResponseEntity<Response> createUser(UserDTO userDTO) {
        try {
            User user = new User();
            user.setFirstName(userDTO.getFirstname());
            user.setLastName(userDTO.getLastname());
            user.setEmail(userDTO.getEmail());
            user.setPhoneNumber(userDTO.getPhone_number());
            user.setAddress(userDTO.getAddress());
            user.setBvn(userDTO.getBvn());

            Account account = new Account();
            account.setAccountName(userDTO.getFirstname() + " " + userDTO.getLastname());
            account.setAccountNumber(generateAccountNumber());
            account.setUser(user);
            System.out.println(account + "this is the content of account");

            user.setAccount(account);


            User newUser = userDAO.save(user);
            return ResponseEntity.ok(new Response(REQUEST_SUCCESSFUL, "Created Successfully", newUser));
        }catch(Exception ex){
            ex.printStackTrace();
            return ResponseEntity.ok(new Response(REQUEST_FAILED, "Failed to create Account", null));
        }
    }

    @Override
    public Optional<User> getUser(Long id) {
        return Optional.empty();
    }

    @Override
    public List<User> getAllUsers() {
        return null;
    }

    private String generateAccountNumber() {
        Random random = new Random();
        long accountNumber = Math.abs(random.nextLong());
        return String.format("%012d", accountNumber);
    }

}
