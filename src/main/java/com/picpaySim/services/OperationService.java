package com.picpaySim.services;

import com.picpaySim.domain.operation.Operation;
import com.picpaySim.domain.user.User;
import com.picpaySim.dto.OperationDTO;
import com.picpaySim.repositories.OperationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

// obs: metodo createOperation ficou bem grande. A fazer: dividir o metodo em mais partes

@Service
public class OperationService {
    @Autowired
    private UserService userService;

    @Autowired
    private OperationRepository repository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private NotificationService notificationService;

    public Operation createOperation(OperationDTO operation) throws Exception {
        User sender = this.userService.findUserById(operation.senderId());
        User receiver = this.userService.findUserById(operation.receiverId());

        userService.validateTransaction(sender, operation.value());

        boolean isValid = this.validation(sender, operation.value());
        if(!isValid) {
            throw new Exception("unauthorized transaction");
        }

        Operation newOperation = new Operation();
        newOperation.setAmount(operation.value());
        newOperation.setSender(sender);
        newOperation.setReceiver(receiver);
        newOperation.setTimestamp(LocalDateTime.now());

        sender.setBalance(sender.getBalance().subtract(operation.value()));
        receiver.setBalance(receiver.getBalance().add(operation.value()));

        this.repository.save(newOperation);
        this.userService.saveUser(sender);
        this.userService.saveUser(receiver);

        this.notificationService.sendNotification(sender, "transaction completed");
        this.notificationService.sendNotification(receiver, "transaction completed");

        return newOperation;
    }

    public Boolean validation(User sender, BigDecimal value) {
        ResponseEntity<Map> validationResponse = restTemplate.getForEntity("https://run.mocky.io/v3/8fafdd68-a090-496f-8c9a-3442cf30dae6", Map.class);

        if(validationResponse.getStatusCode() == HttpStatus.OK) {
            String message = (String)validationResponse.getBody().get("message");
            return "Authorized".equalsIgnoreCase(message);
        } else return false;
    }
}
