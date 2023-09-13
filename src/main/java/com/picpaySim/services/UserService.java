package com.picpaySim.services;

import com.picpaySim.domain.user.User;
import com.picpaySim.domain.user.UserType;
import com.picpaySim.dto.UserDTO;
import com.picpaySim.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    // Validação da transação
    public void validateTransaction(User sender, BigDecimal amount) throws Exception {
        if (sender.getUserType() == UserType.SELLERS) {
            throw new Exception("Sellers type can't realize transactions.");
        }

        if (sender.getBalance().compareTo(amount) < 0) {
            throw new Exception("insufficient funds");
        }
    }

    public User findUserById(Long id) throws Exception {
        return this.repository.findUserById(id).orElseThrow(() -> new Exception("User not found"));
    }

    public void saveUser(User user) {
        this.repository.save(user);
    }

    public void deleteUser(User user) {
        this.repository.delete(user);
    }

    public User createUser(UserDTO data) {
        User newUser = new User(data);
        this.saveUser(newUser);
        return newUser;
    }

    public List<User> getAllUsers() {
        return this.repository.findAll();
    }
}
